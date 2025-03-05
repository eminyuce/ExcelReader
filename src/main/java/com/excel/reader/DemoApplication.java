package com.excel.reader;

import com.excel.reader.service.ExcelReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {
    // java -Xms2g -Xmx2g -jar ExcelHelper-0.0.1-SNAPSHOT.jar "C:\Logistic\02 1 ŞUBAT 1.xlsx"
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoApplication.class);
        ExcelReader service = context.getBean(ExcelReader.class);
        service.processDirector(args);
    }


}
