package com.excel.reader.service;

import com.excel.reader.entities.ExportImport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pjfanning.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.excel.reader.util.ExcelHelper.getCellValue;

@Component
public class ExcelJsonWriter {

    @Autowired
    ExportImportService exportImportService;

    public void processDirector(String pathname) {
        var directory = new File(pathname);

        // Check if the directory exists and is a directory
        if (directory.exists() && directory.isDirectory()) {
            // List all files in the directory
            String[] files = directory.list();

            if (files != null) {
                for (String fileName : files) {
                    String fullPath;
                    fullPath = pathname + "\\" + fileName;
                    System.out.println("****************** START FILE*********************************");
                    System.out.println("Processing FileName:" + fullPath);
                    readExcelFileAndSaveItsDataAsJson(fullPath);
                    System.out.println("********************END FILE*******************************");
                }
            } else {
                System.out.println("The directory is empty.");
            }
        } else {
            System.out.println("The specified path is not a valid directory.");
        }
    }

    private void readExcelFileAndSaveItsDataAsJson(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.out.println("The file does not exist or is not a valid file: " + filePath);
            return;
        } else {
            System.out.println("The file exists: " + filePath);
        }

        ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper for JSON conversion

        try (FileInputStream is = new FileInputStream(filePath);
             Workbook workbook = StreamingReader.builder()
                     .rowCacheSize(3)  // Number of rows to keep in memory
                     .bufferSize(4096)  // Buffer size for streaming
                     .open(is)) {

            for (Sheet sheet : workbook) {
                System.out.println("Reading Sheet: " + sheet.getSheetName());

                boolean isFirstRow = true; // Flag to identify the header row
                List<String> headers = new ArrayList<>(); // Store headers

                for (Row row : sheet) {
                    int rowNumber = row.getRowNum(); // Get the actual row number from Excel (1-based)

                    // Save row to repository
                    ExportImport exportImport = new ExportImport();
                    exportImport.setFileName(file.getName());
                    exportImport.setSheetName(sheet.getSheetName());
                    exportImport.setRowNumber(rowNumber);

                    var exists = exportImportService.checkIfExportImportExists(exportImport);
                    if (exists == 0) {

                        if (isFirstRow) {
                            isFirstRow = false;
                            // Capture the header row
                            for (Cell cell : row) {
                                headers.add(getCellValue(cell));
                            }
                            // Optionally save headers as a separate entry (if needed)
                            Map<String, Object> headerData = new LinkedHashMap<>();
                            headerData.put("sheetName", sheet.getSheetName());
                            headerData.put("headers", headers);
                            String headerJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(headerData);
                            ExportImport headerEntry = new ExportImport();
                            headerEntry.setFileName(file.getName());
                            headerEntry.setRowData(headerJson);
                            headerEntry.setRowNumber(0); // Header row as 0
                            exportImportService.save(headerEntry);
                            System.out.println("Saved Header: " + headerEntry);
                            continue; // Skip to next row after capturing headers
                        }


                        // If the record doesn't exist, call stored procedure or save the entity


                        // Process each data row
                        Map<String, Object> rowData = new LinkedHashMap<>(); // Preserve insertion order
                        int cellIndex = 0;
                        for (Cell cell : row) {
                            if (cellIndex < headers.size()) { // Ensure we don't exceed header count
                                rowData.put(headers.get(cellIndex), getCellValue(cell));
                            }
                            cellIndex++;
                        }
                        rowData.put("sheetName", sheet.getSheetName()); // Add sheet name to row data

                        // Convert row to JSON
                        String rowJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rowData);


                        exportImport.setRowData(rowJson);
                        exportImportService.save(exportImport);
                        System.out.println("Saved Row " + rowNumber + ": " + exportImport);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        }
    }


    private void readExcelFileHeaderAndThreeRowToSeeData(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.out.println("The file does not exist or is not a valid file: " + filePath);
            return;
        } else {
            System.out.println("The file exists: " + filePath);
        }

        ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper for JSON conversion

        try (FileInputStream is = new FileInputStream(filePath);
             Workbook workbook = StreamingReader.builder()
                     .rowCacheSize(3)  // Number of rows to keep in memory
                     .bufferSize(4096)  // Buffer size for streaming
                     .open(is)) {

            for (Sheet sheet : workbook) {
                System.out.println("Reading Sheet: " + sheet.getSheetName());

                boolean isFirstRow = true; // Flag to identify the header row
                List<String> headers = new ArrayList<>(); // Store headers
                int rowCount = 0;

                for (Row row : sheet) {
                    if (isFirstRow) {
                        isFirstRow = false;
                        // Capture the header row
                        for (Cell cell : row) {
                            headers.add(getCellValue(cell));
                        }
                        // Print headers as JSON
                        Map<String, Object> headerData = new LinkedHashMap<>();
                        headerData.put("sheetName", sheet.getSheetName());
                        headerData.put("headers", headers);
                        String headerJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(headerData);
                        System.out.println("Header JSON:\n" + headerJson);
                        continue; // Skip to next row after capturing headers
                    }

                    if (rowCount < 3) {
                        // Capture and print each row as separate JSON
                        Map<String, Object> rowData = new LinkedHashMap<>(); // Preserve insertion order
                        int cellIndex = 0;
                        for (Cell cell : row) {
                            if (cellIndex < headers.size()) { // Ensure we don't exceed header count
                                rowData.put(headers.get(cellIndex), getCellValue(cell));
                            }
                            cellIndex++;
                        }
                        // Add row number for clarity
                        rowData.put("rowNumber", rowCount + 1);
                        // Convert and print each row as JSON
                        String rowJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rowData);
                        System.out.println("Row " + (rowCount + 1) + " JSON:\n" + rowJson);
                        rowCount++;
                    } else {
                        break; // Stop after 3 rows
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        }
    }

}
