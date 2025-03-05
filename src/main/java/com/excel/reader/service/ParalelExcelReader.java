package com.excel.reader.service;

import com.excel.reader.entities.EkimIhr2;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class ParalelExcelReader {

    @Autowired
    private EkimIhr2Service service;

    private static final int BATCH_SIZE = 5000; // Adjust batch size for better performance
    private static final int THREAD_COUNT = 8; // Number of threads for parallel execution

    public void readFileIhr2023() throws IOException {
        readExcelFileParallel("D:\\2023 İHR\\2023-EKİM-İHR-2.xlsx");
    }

    public void readExcelFileParallel(String filePath) throws IOException {
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
                    saveBatch(records);
                    records.clear();
                    System.out.println("RowNumber: " + row.getRowNum());
                }
            }

            // Save remaining records
            if (!records.isEmpty()) {
                saveBatch(records);
            }
        }
    }

    private void saveBatch(List<EkimIhr2> batch) {
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
