package com.excel.reader.service;


import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class ImageCompressionService {

    private static final String INPUT_DIR = "C:\\Users\\YUCE\\Desktop\\images"; // Change to your directory
    private static final String OUTPUT_DIR = "C:\\Users\\YUCE\\Desktop\\images\\output"; // Change for compressed images

    public void processImages(int targetReduction) {
        File inputDir = new File(INPUT_DIR);
        File outputDir = new File(OUTPUT_DIR);

        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        File[] files = inputDir.listFiles((dir, name) -> name.toLowerCase().matches(".*\\.(jpg|jpeg|png|bmp)"));

        if (files == null || files.length == 0) {
            System.out.println("No image files found in the directory.");
            return;
        }

        for (File file : files) {
            try {
                String outputFileName = getJpgFilename(file.getName());
                File outputFile = new File(outputDir, outputFileName);
                compressAndConvertToJpg(file, outputFile, targetReduction);
            } catch (IOException e) {
                System.err.println("Failed to process: " + file.getName());
                e.printStackTrace();
            }
        }
        System.out.println("Image compression and conversion completed.");
    }

    private void compressAndConvertToJpg(File inputFile, File outputFile, int targetReduction) throws IOException {
        long originalSize = Files.size(inputFile.toPath());
        float quality = 0.9f; // Start with high quality

        while (quality > 0.1) { // Reduce step-by-step until target reduction is met
            Thumbnails.of(inputFile)
                    .scale(1.0) // Keep original size
                    .outputQuality(quality)
                    .outputFormat("jpg") // Convert everything to .jpg
                    .toFile(outputFile);

            long newSize = Files.size(outputFile.toPath());

            if (newSize <= originalSize * (1 - (targetReduction / 100.0))) {
                System.out.println("Compressed " + inputFile.getName() + " to " + (newSize / 1024) + " KB at " + (quality * 100) + "% quality.");
                return;
            }

            quality -= 0.1f; // Decrease quality
        }

        System.out.println("Saved " + inputFile.getName() + " at lowest quality attempt.");
    }

    private String getJpgFilename(String originalFilename) {
        return originalFilename.replaceAll("\\.(png|bmp|jpeg)$", "") + ".jpg";
    }
}