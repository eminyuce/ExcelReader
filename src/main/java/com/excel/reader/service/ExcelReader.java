package com.excel.reader.service;

import com.excel.reader.DemoApplication;
import com.excel.reader.entities.EkimIhr2;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pjfanning.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static com.excel.reader.util.ExcelHelper.getCellValue;


@Service
public class ExcelReader {

    @Autowired
    private EkimIhr2Service service;

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
                    readExcelFileHeaderAndThreeRowToSeeData(fullPath);
                    System.out.println("********************END FILE*******************************");
                }
            } else {
                System.out.println("The directory is empty.");
            }
        } else {
            System.out.println("The specified path is not a valid directory.");
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

        try (FileInputStream is = new FileInputStream(filePath);
             Workbook workbook = StreamingReader.builder()
                     .rowCacheSize(3)  // Number of rows to keep in memory
                     .bufferSize(4096)  // Buffer size for streaming
                     .open(is)) {

            for (Sheet sheet : workbook) {
                System.out.println("Reading Sheet: " + sheet.getSheetName());

                boolean isFirstRow = true; // Flag to skip the header row
                int rowCount = 0;

                for (Row row : sheet) {
                    if (isFirstRow) {
                        isFirstRow = false;
                        // Print the header row
                        System.out.print("Header: ");
                        for (Cell cell : row) {
                            System.out.print(getCellValue(cell) + " --- ");
                        }
                        System.out.println();
                        continue; // Skip header row
                    }

                    if (rowCount < 3) {
                        // Print first 3 data rows
                        System.out.print("Row " + (rowCount + 1) + ": ");
                        for (Cell cell : row) {
                            System.out.print(getCellValue(cell) + " --- ");
                        }
                        System.out.println();
                        rowCount++;
                    } else {
                        break; // Stop after reading 3 data rows
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        }
    }

    private void readExcelFileHeaderAndThreeRowToSeeAsJsonData(String filePath) {
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
                List<Map<String, Object>> rows = new ArrayList<>(); // Store rows as key-value pairs
                int rowCount = 0;

                for (Row row : sheet) {
                    if (isFirstRow) {
                        isFirstRow = false;
                        // Capture the header row
                        for (Cell cell : row) {
                            headers.add(getCellValue(cell));
                        }
                        continue; // Skip to next row after capturing headers
                    }

                    if (rowCount < 3) {
                        // Capture first 3 data rows
                        Map<String, Object> rowData = new LinkedHashMap<>(); // Preserve insertion order
                        int cellIndex = 0;
                        for (Cell cell : row) {
                            if (cellIndex < headers.size()) { // Ensure we don't exceed header count
                                rowData.put(headers.get(cellIndex), getCellValue(cell));
                            }
                            cellIndex++;
                        }
                        rows.add(rowData);
                        rowCount++;
                    } else {
                        break; // Stop after 3 rows
                    }
                }

                // Create JSON structure
                Map<String, Object> sheetData = new LinkedHashMap<>();
                sheetData.put("sheetName", sheet.getSheetName());
                sheetData.put("headers", headers);
                sheetData.put("rows", rows);

                // Convert to JSON and print
                String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(sheetData);
                System.out.println("JSON Output:\n" + jsonOutput);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        }
    }

    public void readExcelFileWithStreamingReader(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.out.println("The file does not exist or is not a valid file: " + filePath);
            return;
        } else {
            System.out.println("Th file exists: " + filePath);
        }

        try (FileInputStream is = new FileInputStream(filePath);
             Workbook workbook = StreamingReader.builder()
                     .rowCacheSize(100)  // Number of rows to keep in memory
                     .bufferSize(4096)   // Buffer size for streaming
                     .open(is)) {
            Integer lastRowNumber = service.getLastRowNumber(file.getName());
            System.out.println("The last Row Number: " + lastRowNumber);
            for (Sheet sheet : workbook) {
                System.out.println("Reading Sheet: " + sheet.getSheetName());

                boolean isFirstRow = true; // Skip the header row

                for (Row row : sheet) {
                    if (isFirstRow) {
                        isFirstRow = false;
                        continue;  // Skip header row
                    }
                    if (row.getRowNum() <= lastRowNumber) continue;

                    EkimIhr2 entity = EkimIhr2.mapRowToEntity(row, file.getName());
                    service.saveEkimIhr2Async(entity);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        }
    }

    public void readExcelFileIterator(String filePath) throws IOException {

        IOUtils.setByteArrayMaxOverride(444_740_852); // Adjust this value as needed
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.out.println("The file does not exist or is not a valid file: " + filePath);
            return;
        }
        Integer lastRowNumber = service.getLastRowNumber(file.getName());
        System.out.println("The last Row Number: " + lastRowNumber);

        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() == 0) continue; // Skip header row
            if (row.getRowNum() <= lastRowNumber) continue;

            service.saveEkimIhr2Async(EkimIhr2.mapRowToEntity(row, file.getName()));
            System.out.println("RowNumber: " + row.getRowNum());
        }


        workbook.close();
        fis.close();
    }


    public static void readExcelFileIterator_v2(String filePath) throws IOException {
        ApplicationContext context = SpringApplication.run(DemoApplication.class);
        EkimIhr2Service service = context.getBean(EkimIhr2Service.class);
        IOUtils.setByteArrayMaxOverride(444_740_852);

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.out.println("The file does not exist or is not a valid file: " + filePath);
            return;
        }

        Integer lastRowNumber = service.getLastRowNumber(file.getName());
        System.out.println("The lastRowNumber: " + lastRowNumber);

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int totalRows = sheet.getPhysicalNumberOfRows();

            // Start reading from lastRowNumber + 1
            for (int rowIndex = lastRowNumber + 1; rowIndex < totalRows; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue; // Skip empty rows

                service.saveEkimIhr2Async(EkimIhr2.mapRowToEntity(row, file.getName()));
                System.out.println("RowNumber: " + row.getRowNum());
            }
        } finally {
            SpringApplication.exit(context);
        }
    }

}
