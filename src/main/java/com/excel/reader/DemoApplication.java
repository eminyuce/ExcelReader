package com.excel.reader;

import com.excel.reader.service.ExcelReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
       // SpringApplication.run(DemoApplication.class, args);
        ApplicationContext context = SpringApplication.run(DemoApplication.class);
        ExcelReader service = context.getBean(ExcelReader.class);
        service.readExcelFileIteratorEasyExcel("D:\\2023 İHR\\2023-EKİM-İHR-2.xlsx");
    }
}
