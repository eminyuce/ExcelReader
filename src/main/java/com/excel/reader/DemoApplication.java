package com.excel.reader;

import com.excel.reader.service.ExcelJsonWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {
    //java -Xms2g -Xmx2g "-Dspring.config.location=./application.properties" -jar ExcelHelper-0.0.1-SNAPSHOT.jar "C:\Logistic\2023_IHR" "5000"
    public static void main(String[] args) {

        var context = SpringApplication.run(DemoApplication.class);
        processExcelFilesFromDirectoryPath(args, context);
    }

    private static void processExcelFilesFromDirectoryPath(String[] args, ApplicationContext context) {
        ExcelJsonWriter service = context.getBean(ExcelJsonWriter.class);
        // Check if second argument exists; if not, pass null or a default value
        if (args.length == 1) {
            service.processExcelFilesFromDirectoryPath(args[0], null);  // Passing null for the second argument
        } else if (args.length > 1) {
            service.processExcelFilesFromDirectoryPath(args[0], args[1]);
        } else {
            // Handle case when no arguments are passed (if needed)
            System.out.println("No input files provided!");
        }
    }
}