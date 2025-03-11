package com.excel.reader.service;

import com.excel.reader.annotation.TimedExecution;
import com.excel.reader.entities.ExportImportAralik;
import com.excel.reader.entities.ExportImportOther;
import com.excel.reader.entities.ImportOther;
import com.excel.reader.entities.dto.ReportTotalProcessedDTO;
import com.excel.reader.util.ExcelHelper;
import com.excel.reader.util.GeneralHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pjfanning.xlsx.StreamingReader;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static com.excel.reader.util.ExcelHelper.getCellValue;

@Component
@Slf4j
public class ExcelJsonWriter {

    // Batch size - adjust this value based on your needs
    static int BATCH_SIZE = 50000;

    @Autowired
    private ImportOtherService importOtherService;

    @Autowired
    private ExportImportAralikService exportImportAralikService;

    @Autowired
    private ExportImportOtherService exportImportOtherService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void processExcelFilesFromDirectoryPath(String pathname, String batchSize) {
        File directory = new File(pathname);
        if (StringUtils.isNotEmpty(batchSize)) {
            BATCH_SIZE = Integer.parseInt(batchSize);
            if (BATCH_SIZE <= 0) {
                throw new RuntimeException("Batch size cannot be less than 0");
            }
            log.info("BATCH_SIZE is set: {}", batchSize);
        } else {
            log.info("DEFAULT BATCH_SIZE is used: {}", BATCH_SIZE);
        }
        // Check if the directory exists and is a directory
        if (!directory.exists() || !directory.isDirectory()) {
            log.error("The specified path is not a valid directory: {}", pathname);
            return;
        }

        // List all files in the directory
        File[] files = directory.listFiles();
        if (files != null) {
            List<File> fileList = Arrays.asList(files);
            Collections.reverse(fileList);
            files = fileList.toArray(new File[0]); // Convert back to array if needed
        }
        if (files == null || files.length == 0) {
            log.warn("The directory is empty: {}", pathname);
            return;
        }
        List<ReportTotalProcessedDTO> reportTotalProcessedDTOS = exportImportOtherService.getReportTotalProcessed();
        for (File file : files) {
            if (file.isFile()) {
                String fullPath = file.getAbsolutePath();
                if (file.getName().startsWith("~$")) {
                    log.error("Skipping temporary file: " + fullPath);
                    return;
                }
                try {
                    OPCPackage pkg = OPCPackage.open(file);


                    log.info("****** START FILE ***********");
                    log.info("Processing FileName: {}", fullPath);
                    // Find matching ReportTotalProcessedDTO or use a default if not found
                    String fileName = GeneralHelper.replaceTurkishChars(file.getName());
                    Optional<ReportTotalProcessedDTO> matchingReport = reportTotalProcessedDTOS.stream()
                            .filter(r -> fileName.toLowerCase().equals(GeneralHelper.replaceTurkishChars(r.getFileName()).toLowerCase()))
                            .findFirst();

                    ReportTotalProcessedDTO report = matchingReport.orElse(null); // or provide a default DTO
                    if (report == null) {
                        log.warn("No matching report found for file: {}", fileName);
                    } else {
                        log.info("Found report for file: {} - Total: {}, MaxRowInt: {}",
                                fileName, report.getTotal(), report.getMaxRowInt());
                    }

                    // Call the method with the report (null-safe handling depends on the method)
                    readExcelFileAndSaveItsDataAsJson(fullPath, report);
                    log.info("******* END FILE ************");

                    // Your processing logic here
                } catch (Exception e) {
                    log.error("Unexpected error while processing Excel file: " + fullPath);
                    e.printStackTrace();
                }

            }
        }
    }

    @TimedExecution("readExcelFileAndSaveItsDataAsJson")
    private void readExcelFileAndSaveItsDataAsJson(String filePath, ReportTotalProcessedDTO report) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            log.error("The file does not exist or is not a valid file: {}", filePath);
            return;
        }

        log.info("The file exists: {}", filePath);
        String normalizedFileName = file.getName();

        // Batch lists for Aralik and Other entities
        List<ImportOther> importOtherBatch = new ArrayList<>(BATCH_SIZE);
        List<ExportImportAralik> aralikBatch = new ArrayList<>(BATCH_SIZE);
        List<ExportImportOther> otherBatch = new ArrayList<>(BATCH_SIZE);

        try (FileInputStream is = new FileInputStream(filePath);
             Workbook workbook = StreamingReader.builder()
                     .rowCacheSize(5000)
                     .bufferSize(4096)
                     .open(is)) {

            for (Sheet sheet : workbook) {
                log.info("Reading Sheet: {}", sheet.getSheetName());
                int lastRowNumber = findLastRowNumber(report);
                boolean isFirstRow = true;
                List<String> headers = new ArrayList<>();

                for (Row row : sheet) {
                    try {
                        int rowNumber = row.getRowNum() + 1;
                        if (isFirstRow) {
                            isFirstRow = false;
                            for (Cell cell : row) {
                                headers.add(ExcelHelper.removeDuplicateSpaces(getCellValue(cell)));
                            }
                            continue;
                        }

                        if (row.getRowNum() <= lastRowNumber) continue;

                        // Process data row
                        Map<String, Object> rowData = new LinkedHashMap<>();
                        int cellIndex = 0;
                        for (Cell cell : row) {
                            if (cellIndex < headers.size()) {
                                rowData.put(headers.get(cellIndex), getCellValue(cell));
                            }
                            cellIndex++;
                        }

                        // Convert row to JSON
                        String rowJson = getRowJson(rowData);

                        // YOU SHOULD DEFINE YOUR JAVA ENTITY AND ITS SERVICE AND REPO LAYER FOR BATCH SAVING
                        if (file.getAbsolutePath().toUpperCase().contains("IMPORT")) {
                            try {
                                log.debug("ImportOther.rowJson:" + rowJson);
                                var item = objectMapper.readValue(rowJson, ImportOther.class);
                                item.setFileName(normalizedFileName);
                                item.setSheetName(sheet.getSheetName());
                                item.setRowInt(rowNumber);

                                // Add to batch list
                                importOtherBatch.add(item);

                                // Check if batch size reached
                                if (importOtherBatch.size() >= BATCH_SIZE) {
                                    importOtherService.saveAll(importOtherBatch);
                                    log.info("Saved batch of {} Aralik records", importOtherBatch.size());
                                    importOtherBatch.clear();
                                }
                            } catch (JsonProcessingException e) {
                                log.error("ExportImportAralik Exception:", e);
                            }
                        } else if (normalizedFileName.contains("Aralık")) {
                            try {
                                log.debug("ExportImportAralik.rowJson:" + rowJson);
                                var item = objectMapper.readValue(rowJson, ExportImportAralik.class);
                                item.setFileName(normalizedFileName);
                                item.setSheetName(sheet.getSheetName());
                                item.setRowInt(rowNumber);

                                // Add to batch list
                                aralikBatch.add(item);

                                // Check if batch size reached
                                if (aralikBatch.size() >= BATCH_SIZE) {
                                    exportImportAralikService.saveAll(aralikBatch);
                                    log.info("Saved batch of {} Aralik records", aralikBatch.size());
                                    aralikBatch.clear();
                                }
                            } catch (JsonProcessingException e) {
                                log.error("ExportImportAralik Exception:", e);
                            }
                        } else {
                            try {
                                log.debug("ExportImportOther.rowJson:" + rowJson);
                                var item = objectMapper.readValue(rowJson, ExportImportOther.class);
                                item.setFileName(normalizedFileName);
                                item.setSheetName(sheet.getSheetName());
                                item.setRowInt(rowNumber);

                                // Add to batch list
                                otherBatch.add(item);

                                // Check if batch size reached
                                if (otherBatch.size() >= BATCH_SIZE) {
                                    exportImportOtherService.saveAll(otherBatch);
                                    log.info("Saved batch of {} Other records", otherBatch.size());
                                    otherBatch.clear();
                                }
                            } catch (JsonProcessingException e) {
                                log.error("ExportImportOther:", e);
                            }
                        }
                    } catch (Exception e) {
                        log.error("Exception", e);
                    }
                }


                // Save remaining Aralik records
                if (!aralikBatch.isEmpty()) {
                    exportImportAralikService.saveAll(aralikBatch);
                    log.info("Saved final batch of {} Aralik records", aralikBatch.size());
                    aralikBatch.clear();
                }

                // Save remaining Other records
                if (!otherBatch.isEmpty()) {
                    exportImportOtherService.saveAll(otherBatch);
                    log.info("Saved final batch of {} Other records", otherBatch.size());
                    otherBatch.clear();
                }

                break;
            }
        } catch (IOException e) {
            log.error("Error reading Excel file: {}", filePath, e);
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        } catch (Exception e) {
            log.error("Unexpected error while processing Excel file: {}", filePath, e);
            throw new RuntimeException("Unexpected error: " + e.getMessage(), e);
        }
    }

    @TimedExecution("GetRowJson")
    private String getRowJson(Map<String, Object> rowData) throws JsonProcessingException {
        return objectMapper.writeValueAsString(rowData);
    }

    private int findLastRowNumber(ReportTotalProcessedDTO report) {
        return report == null ? 0 : report.getMaxRowInt();
    }

}