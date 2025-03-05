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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

//@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.demo.*", "com.example.demo.entity", "com.example.demo.repo", "com.example.demo.service"})
public class ParalelSaveV2 {
    private static final int BATCH_SIZE = 5000; // Adjust batch size for better performance
    private static final int THREAD_COUNT = 8; // Number of threads for parallel execution
    private static final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    private static final AtomicInteger processedRows = new AtomicInteger(0);

    public static void test(String[] args) throws IOException {
        ApplicationContext context = SpringApplication.run(ParalelSaveV2.class);
        EkimIhr2Service service = context.getBean(EkimIhr2Service.class);
        readExcelFileParallel("D:\\2023 İHR\\2023-EKİM-İHR-2.xlsx", service);
        shutdownExecutor();
    }

    public static void readExcelFileParallel(String filePath, EkimIhr2Service service) throws IOException {
        IOUtils.setByteArrayMaxOverride(444_740_852);
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

            List<CompletableFuture<Void>> futures = new ArrayList<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                records.add(EkimIhr2.mapRowToEntity(row));

                if (records.size() >= BATCH_SIZE) {
                    futures.add(saveBatchAsync(new ArrayList<>(records), service));
                    records.clear();
                    System.out.println("Processed up to row: " + row.getRowNum() +
                            ", Total processed: " + processedRows.get());
                }
            }

            // Save remaining records
            if (!records.isEmpty()) {
                futures.add(saveBatchAsync(new ArrayList<>(records), service));
            }

            // Wait for all batches to complete
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        }
    }

    private static CompletableFuture<Void> saveBatchAsync(List<EkimIhr2> batch, EkimIhr2Service service) {
        return CompletableFuture.runAsync(() -> {
            try {
                service.saveAllEkimIhr2List(batch);
                processedRows.addAndGet(batch.size());
            } catch (Exception e) {
                System.err.println("Error processing batch: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }, executorService);
    }

    private static void shutdownExecutor() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }


}