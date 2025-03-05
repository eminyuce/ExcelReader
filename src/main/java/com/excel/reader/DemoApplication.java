package com.excel.reader;

import com.excel.reader.entities.EkimIhr2;
import com.excel.reader.service.EkimIhr2Service;
import com.github.pjfanning.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@SpringBootApplication
@ComponentScan(basePackages = {"com.excel.reader.*", "com.excel.reader.entity", "com.excel.reader.repo", "com.excel.reader.service"})
public class DemoApplication {
    private static final int BATCH_SIZE = 100;
    public static void main(String[] args) throws IOException {
        // Start Spring application context
        readExcelFileIteratorEasyExcel("D:\\2023 İHR\\2023-EKİM-İHR-2.xlsx");
    }

    public static void readExcelFileIteratorEasyExcel(String filePath) {
        List<EkimIhr2> entityList = new ArrayList<>();

        try (
                FileInputStream is = new FileInputStream(new File(filePath));
                Workbook workbook = StreamingReader.builder()
                        .rowCacheSize(100)  // Number of rows to keep in memory
                        .bufferSize(4096)   // Buffer size for streaming
                        .open(is)) {

            for (Sheet sheet : workbook) {
                System.out.println("Reading Sheet: " + sheet.getSheetName());

                boolean isFirstRow = true; // Skip the header row

                for (Row row : sheet) {
                    if (isFirstRow) {
                        isFirstRow = false;
                        continue;  // Skip header row
                    }

                    EkimIhr2 entity = EkimIhr2.mapRowToEntity(row);
                    entityList.add(entity);
                    System.out.println(entity.toString());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        }

    }




    public static void readExcelFileIterator(String filePath) throws IOException {


        ApplicationContext context = SpringApplication.run(DemoApplication.class);
        EkimIhr2Service service = context.getBean(EkimIhr2Service.class);
        IOUtils.setByteArrayMaxOverride(444_740_852); // Adjust this value as needed
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.out.println("The file does not exist or is not a valid file: " + filePath);
            return;
        }
        Integer lastRowNumber = service.getLastRowNumber();
        System.out.println("The lastRowNumber: " + lastRowNumber);

        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() == 0) continue; // Skip header row
            if (row.getRowNum() <= lastRowNumber) continue;

            service.saveEkimIhr2Async(EkimIhr2.mapRowToEntity(row));
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

        Integer lastRowNumber = service.getLastRowNumber();
        System.out.println("The lastRowNumber: " + lastRowNumber);

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int totalRows = sheet.getPhysicalNumberOfRows();

            // Start reading from lastRowNumber + 1
            for (int rowIndex = lastRowNumber + 1; rowIndex < totalRows; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue; // Skip empty rows

                service.saveEkimIhr2Async(EkimIhr2.mapRowToEntity(row));
                System.out.println("RowNumber: " + row.getRowNum());
            }
        } finally {
            SpringApplication.exit(context);
        }
    }



}