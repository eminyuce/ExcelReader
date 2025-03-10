package com.excel.reader.service;

import com.excel.reader.entities.ExportImportAralik;
import com.excel.reader.entities.ExportImportOther;
import com.excel.reader.entities.dto.ReportTotalProcessedDTO;
import com.excel.reader.util.ExcelHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pjfanning.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static com.excel.reader.util.ExcelHelper.getCellValue;

@Component
public class ExcelJsonWriter {

    private static final Logger logger = LoggerFactory.getLogger(ExcelJsonWriter.class);
    // Batch size - adjust this value based on your needs
    final int BATCH_SIZE = 100000;

    @Autowired
    private ExportImportAralikService exportImportAralikService;

    @Autowired
    private ExportImportOtherService exportImportOtherService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void processDirector(String pathname) {
        File directory = new File(pathname);

        // Check if the directory exists and is a directory
        if (!directory.exists() || !directory.isDirectory()) {
            logger.error("The specified path is not a valid directory: {}", pathname);
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
            logger.warn("The directory is empty: {}", pathname);
            return;
        }
        List<ReportTotalProcessedDTO> reportTotalProcessedDTOS = exportImportOtherService.getReportTotalProcessed();
        for (File file : files) {
            if (file.isFile()) {
                String fullPath = file.getAbsolutePath();
                logger.info("****************** START FILE *********************************");
                logger.info("Processing FileName: {}", fullPath);
                // Find matching ReportTotalProcessedDTO or use a default if not found
                String fileName = replaceTurkishChars(file.getName());
                Optional<ReportTotalProcessedDTO> matchingReport = reportTotalProcessedDTOS.stream()
                        .filter(r -> fileName.toLowerCase().equals(replaceTurkishChars(r.getFileName()).toLowerCase()))
                        .findFirst();

                ReportTotalProcessedDTO report = matchingReport.orElse(null); // or provide a default DTO
                if (report == null) {
                    logger.warn("No matching report found for file: {}", fileName);
                } else {
                    logger.info("Found report for file: {} - Total: {}, MaxRowInt: {}",
                            fileName, report.getTotal(), report.getMaxRowInt());
                }

                // Call the method with the report (null-safe handling depends on the method)
                readExcelFileAndSaveItsDataAsJson(fullPath, report);
                logger.info("******************** END FILE *******************************");
            }
        }
    }

    private void readExcelFileAndSaveItsDataAsJson(String filePath, ReportTotalProcessedDTO report) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            logger.error("The file does not exist or is not a valid file: {}", filePath);
            return;
        }

        logger.info("The file exists: {}", filePath);
        String normalizedFileName = file.getName();

        // Batch lists for Aralik and Other entities
        List<ExportImportAralik> aralikBatch = new ArrayList<>(BATCH_SIZE);
        List<ExportImportOther> otherBatch = new ArrayList<>(BATCH_SIZE);

        try (FileInputStream is = new FileInputStream(filePath);
             Workbook workbook = StreamingReader.builder()
                     .rowCacheSize(5000)
                     .bufferSize(4096)
                     .open(is)) {

            for (Sheet sheet : workbook) {
                logger.info("Reading Sheet: {}", sheet.getSheetName());
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
                        String rowJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rowData);

                        // Process based on file name
                        if (normalizedFileName.contains("Aralık")) {
                            try {
                                logger.debug("ExportImportAralik.rowJson:" + rowJson);
                                var item = objectMapper.readValue(rowJson, ExportImportAralik.class);
                                item.setFileName(normalizedFileName);
                                item.setSheetName(sheet.getSheetName());
                                item.setRowInt(rowNumber);

                                // Add to batch list
                                aralikBatch.add(item);

                                // Check if batch size reached
                                if (aralikBatch.size() >= BATCH_SIZE) {
                                    exportImportAralikService.saveAll(aralikBatch);
                                    logger.info("Saved batch of {} Aralik records", aralikBatch.size());
                                    aralikBatch.clear();
                                }
                            } catch (JsonProcessingException e) {
                                logger.error("ExportImportAralik Exception:", e);
                            }
                        } else {
                            try {
                                logger.debug("ExportImportOther.rowJson:" + rowJson);
                                var item = objectMapper.readValue(rowJson, ExportImportOther.class);
                                item.setFileName(normalizedFileName);
                                item.setSheetName(sheet.getSheetName());
                                item.setRowInt(rowNumber);

                                // Add to batch list
                                otherBatch.add(item);

                                // Check if batch size reached
                                if (otherBatch.size() >= BATCH_SIZE) {
                                    exportImportOtherService.saveAll(otherBatch);
                                    logger.info("Saved batch of {} Other records", otherBatch.size());
                                    otherBatch.clear();
                                }
                            } catch (JsonProcessingException e) {
                                logger.error("ExportImportOther:", e);
                            }
                        }
                    } catch (Exception e) {
                        logger.error("Exception", e);
                    }
                }


                // Save remaining Aralik records
                if (!aralikBatch.isEmpty()) {
                    exportImportAralikService.saveAll(aralikBatch);
                    logger.info("Saved final batch of {} Aralik records", aralikBatch.size());
                    aralikBatch.clear();
                }

                // Save remaining Other records
                if (!otherBatch.isEmpty()) {
                    exportImportOtherService.saveAll(otherBatch);
                    logger.info("Saved final batch of {} Other records", otherBatch.size());
                    otherBatch.clear();
                }

                break;
            }
        } catch (IOException e) {
            logger.error("Error reading Excel file: {}", filePath, e);
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error while processing Excel file: {}", filePath, e);
            throw new RuntimeException("Unexpected error: " + e.getMessage(), e);
        }
    }

    private int findLastRowNumber(ReportTotalProcessedDTO report) {
        if (report == null)
            return 0;
        return report.getMaxRowInt();
    }

    public static String replaceTurkishChars(String input) {
        if (input == null) return null;
        return input.replace("İ", "I").replace("ı", "i")
                .replace("Ğ", "G").replace("ğ", "g")
                .replace("Ş", "S").replace("ş", "s")
                .replace("Ç", "C").replace("ç", "c")
                .replace("Ö", "O").replace("ö", "o")
                .replace("Ü", "U").replace("ü", "u");
    }


}