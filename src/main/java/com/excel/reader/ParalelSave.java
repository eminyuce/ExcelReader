package com.excel.reader;

import com.excel.reader.entities.EkimIhr2;
import com.excel.reader.service.EkimIhr2Service;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ParalelSave {
    private static final int BATCH_SIZE = 5000; // Adjust batch size for better performance
    private static final int THREAD_COUNT = 8; // Number of threads for parallel execution

    public static void test(String[] args) throws IOException {
        ApplicationContext context = SpringApplication.run(ParalelSave.class);
        EkimIhr2Service service = context.getBean(EkimIhr2Service.class);
        readExcelFileParallel("D:\\2023 İHR\\2023-EKİM-İHR-2.xlsx", service);
    }

    public static void readExcelFileParallel(String filePath, EkimIhr2Service service) throws IOException {
        IOUtils.setByteArrayMaxOverride(444_740_852); // Adjust this value as needed
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.out.println("The file does not exist: " + filePath);
            return;
        }

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            List<EkimIhr2> records = new ArrayList<>();
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) rowIterator.next(); // Skip header row

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                records.add(EkimIhr2.mapRowToEntity(row));

                if (records.size() >= BATCH_SIZE) {
                    saveBatch(records, service);
                    records.clear();
                    System.out.println("RowNumber: " + row.getRowNum());
                }
            }

            // Save remaining records
            if (!records.isEmpty()) {
                saveBatch(records, service);
            }
        }
    }

    private static void saveBatch(List<EkimIhr2> batch, EkimIhr2Service service) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        // Copy the batch into an immutable list for thread safety
        List<EkimIhr2> batchCopy = new ArrayList<>(batch);

        executor.submit(() -> service.saveAllEkimIhr2List(batchCopy));

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
