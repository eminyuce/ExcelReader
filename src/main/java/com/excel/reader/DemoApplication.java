package com.excel.reader;

import com.excel.reader.service.ExcelJsonWriter;
import org.apache.commons.cli.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {
    // java -Xms2g -Xmx2g -jar ExcelHelper-0.0.1-SNAPSHOT.jar -Excel_Path_Directory "C:\Logistic\2023_IHR" -Batch_Size "5000"
    public static void main(String[] args) throws ParseException {
        // Start the Spring application context
        ApplicationContext context = SpringApplication.run(DemoApplication.class);
        ExcelJsonWriter service = context.getBean(ExcelJsonWriter.class);
        processExcelFiles(args, service);
    }

    private static void processExcelFiles(String[] args, ExcelJsonWriter service) throws ParseException {
        // Create a CommandLineParser and define command line options
        Options options = new Options();

        Option firstInput = new Option("f", "Excel_Path_Directory", true, "Excel files directory path");
        firstInput.setRequired(false);
        options.addOption(firstInput);

        Option secondInput = new Option("s", "Batch_Size", true, "Batch processing size");
        secondInput.setRequired(false);
        options.addOption(secondInput);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        // Retrieve values of arguments
        String excelPathDirectory = cmd.getOptionValue("Excel_Path_Directory");
        String batchSize = cmd.getOptionValue("Batch_Size");

        if (excelPathDirectory != null) {
            // Call the service method with the arguments
            service.processExcelFilesFromDirectoryPath(excelPathDirectory, batchSize);
        } else {
            System.out.println("App is running as web app");
        }
    }
}
