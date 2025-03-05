package com.excel.reader.service;

import com.excel.reader.entities.ExportImport;
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

    @Autowired
    private  ExportImportService exportImportService;
    private static final ObjectMapper  objectMapper = new ObjectMapper();

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

        for (File file : files) {
            if (file.isFile()) {
                String fullPath = file.getAbsolutePath();
                logger.info("****************** START FILE *********************************");
                logger.info("Processing FileName: {}", fullPath);
                readExcelFileAndSaveItsDataAsJson(fullPath);
                logger.info("******************** END FILE *******************************");
            }
        }
    }

    private void readExcelFileAndSaveItsDataAsJson(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            logger.error("The file does not exist or is not a valid file: {}", filePath);
            return;
        }

        logger.info("The file exists: {}", filePath);
        String normalizedFileName = file.getName();

        // Batch size - adjust this value based on your needs
        final int BATCH_SIZE = 1000;
        List<ExportImport> batchRecords = new ArrayList<>(BATCH_SIZE);

        try (FileInputStream is = new FileInputStream(filePath);
             Workbook workbook = StreamingReader.builder()
                     .rowCacheSize(5000)
                     .bufferSize(4096)
                     .open(is)) {

            for (Sheet sheet : workbook) {
                logger.info("Reading Sheet: {}", sheet.getSheetName());

                boolean isFirstRow = true;
                List<String> headers = new ArrayList<>();
                if (exportImportService.isLastRowForFileProceed(normalizedFileName, sheet.getSheetName(), sheet.getLastRowNum())) {
                    logger.info("The file already proceed: {}", filePath);
                    continue;
                }

                for (Row row : sheet) {
                    int rowNumber = row.getRowNum() + 1;

                    ExportImport exportImport = new ExportImport();
                    exportImport.setFileName(normalizedFileName);
                    exportImport.setSheetName(sheet.getSheetName());
                    exportImport.setRowNumber(rowNumber);

                    if (exportImportService.checkIfExportImportExists(exportImport) != 0) {
                        logger.debug("Record already exists for file: {}, sheet: {}, row: {}",
                                normalizedFileName, sheet.getSheetName(), rowNumber);
                        continue;
                    }

                    if (isFirstRow) {
                        isFirstRow = false;
                        for (Cell cell : row) {
                            headers.add(getCellValue(cell));
                        }
                        saveHeaderRow(normalizedFileName, sheet.getSheetName(), headers);
                        continue;
                    }

                    // Process data row
                    Map<String, Object> rowData = new LinkedHashMap<>();
                    int cellIndex = 0;
                    for (Cell cell : row) {
                        if (cellIndex < headers.size()) {
                            rowData.put(headers.get(cellIndex), getCellValue(cell));
                        }
                        cellIndex++;
                    }
                    rowData.put("sheetName", sheet.getSheetName());

                    // Convert row to JSON
                    String rowJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rowData);

                    // Add to batch
                    exportImport.setRowData(rowJson);
                    batchRecords.add(exportImport);

                    // Save batch when it reaches the batch size
                    if (batchRecords.size() >= BATCH_SIZE) {
                        exportImportService.saveBatch(batchRecords);
                        logger.info("Saved batch of {} rows for sheet: {}", batchRecords.size(), sheet.getSheetName());
                        batchRecords.clear();
                    }
                }

                // Save any remaining records in the batch
                if (!batchRecords.isEmpty()) {
                    exportImportService.saveBatch(batchRecords);
                    logger.info("Saved final batch of {} rows for sheet: {}", batchRecords.size(), sheet.getSheetName());
                    batchRecords.clear();
                }
            }
        } catch (IOException e) {
            logger.error("Error reading Excel file: {}", filePath, e);
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error while processing Excel file: {}", filePath, e);
            throw new RuntimeException("Unexpected error: " + e.getMessage(), e);
        }
    }

    private void readExcelFileHeaderAndThreeRowToSeeData(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            logger.error("The file does not exist or is not a valid file: {}", filePath);
            return;
        }

        logger.info("The file exists: {}", filePath);

        try (FileInputStream is = new FileInputStream(filePath);
             Workbook workbook = StreamingReader.builder()
                     .rowCacheSize(3)
                     .bufferSize(4096)
                     .open(is)) {

            for (Sheet sheet : workbook) {
                logger.info("Reading Sheet: {}", sheet.getSheetName());

                boolean isFirstRow = true;
                List<String> headers = new ArrayList<>();
                int rowCount = 0;

                for (Row row : sheet) {
                    if (isFirstRow) {
                        isFirstRow = false;
                        for (Cell cell : row) {
                            headers.add(getCellValue(cell));
                        }
                        Map<String, Object> headerData = new LinkedHashMap<>();
                        headerData.put("sheetName", sheet.getSheetName());
                        headerData.put("headers", headers);
                        String headerJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(headerData);
                        logger.info("Header JSON:\n{}", headerJson);
                        continue;
                    }

                    if (rowCount < 3) {
                        Map<String, Object> rowData = new LinkedHashMap<>();
                        int cellIndex = 0;
                        for (Cell cell : row) {
                            if (cellIndex < headers.size()) {
                                rowData.put(headers.get(cellIndex), getCellValue(cell));
                            }
                            cellIndex++;
                        }
                        rowData.put("rowNumber", rowCount + 1);
                        String rowJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rowData);
                        logger.info("Row {} JSON:\n{}", rowCount + 1, rowJson);
                        rowCount++;
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Error reading Excel file for preview: {}", filePath, e);
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        }
    }


    // Save header row as a separate entry
    private void saveHeaderRow(String fileName, String sheetName, List<String> headers) throws IOException {
        ExportImport headerEntry = new ExportImport();
        headerEntry.setFileName(fileName);
        headerEntry.setSheetName(sheetName);
        headerEntry.setRowNumber(0); // Header row as 0

        if (exportImportService.checkIfExportImportExists(headerEntry) == 0) {
            Map<String, Object> headerData = new LinkedHashMap<>();
            headerData.put("sheetName", sheetName);
            headerData.put("headers", headers);
            String headerJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(headerData);
            headerEntry.setRowData(headerJson);
            exportImportService.save(headerEntry);
            logger.info("Saved Header: {}", headerEntry);
        }
    }
}