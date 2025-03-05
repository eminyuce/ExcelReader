package com.excel.reader.service;

import com.excel.reader.DemoApplication;
import com.excel.reader.entities.EkimIhr2;
import com.github.pjfanning.xlsx.StreamingReader;
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
import java.util.Iterator;


@Service
public class ExcelReader {

    @Autowired
    private EkimIhr2Service service;

    public void processDirector(String[] args) {
        String pathname = args[0];
        var directory = new File(pathname);

        // Check if the directory exists and is a directory
        if (directory.exists() && directory.isDirectory()) {
            // List all files in the directory
            String[] files = directory.list();

            if (files != null) {
                for (String fileName : files) {
                    System.out.println("Processing FileName:" + pathname + "\\" + fileName);
                    if (fileName.contains("Aralik")) continue;
                    readExcelFileWithStreamingReader(pathname + "\\" + fileName);
                }
            } else {
                System.out.println("The directory is empty.");
            }
        } else {
            System.out.println("The specified path is not a valid directory.");
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
