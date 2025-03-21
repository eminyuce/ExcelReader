package com.excel.reader;

import com.excel.reader.service.CompanyService;
import com.excel.reader.service.ExcelJsonWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {
    // java -Xms2g -Xmx2g -jar ExcelHelper-0.0.1-SNAPSHOT.jar "C:\Logistic\02 1 ŞUBAT 1.xlsx"
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoApplication.class);
        // processExcelFilesFromDirectoryPath(args, context);
        CompanyService service = context.getBean(CompanyService.class);
        service.getAllCompanies(Integer.parseInt(args[0]));
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

