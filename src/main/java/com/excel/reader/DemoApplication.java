package com.excel.reader;

import com.excel.reader.service.CompanyService;
import com.excel.reader.service.ExcelJsonWriter;
import com.excel.reader.service.ImageCompressionService;
import com.excel.reader.service.ImageGenerator2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {
    // java -Xms2g -Xmx2g -jar ExcelHelper-0.0.1-SNAPSHOT.jar "C:\Logistic\02 1 ŞUBAT 1.xlsx"
    public static void main(String[] args) {
        //ApplicationContext context = SpringApplication.run(DemoApplication.class);
        //processExcelFilesFromDirectoryPath(args, context);
        //generateCompanyPlaceDataFromGooglePlaceAPI(args, context);
       // context.getBean(ImageCompressionService.class).processImages(80);

        //generateTransparentFiles();


        //extracted();

        ImageGenerator2.generateImage();
        // extracted2();
    }

    private static void generateDanismanlikImages() {
        String text = "Hello, World!";

        String[] educationList = new String[]
                {
                        "Amerika vatandaşıyım. Ciddi, sevgiye dayalı bir ilişki ve evlilik isteyen, birlikte Amerika’da bir hayat kurmayı hayal eden biriyle tanışmak isterim."
                };

        for (int i = 1; i < 7; i++) {
            generateImages(i, educationList[i-1]);
        }
    }


    private static void generateImages(int index, String text) {
        // Image settings
        int width = 800;
        int height = 800;
        Color backgroundColor = new Color(200, 220, 255); // Light blue background
        Color textColor = new Color(0, 51, 102); // Dark navy text
        Color shadowColor = new Color(0, 0, 0, 100); // Semi-transparent black shadow
        Color borderColor = Color.BLACK; // Black border
        int borderWidth = 10; // Border stroke width
        String fontName = "Verdana"; // High-quality font
        String outputPath = "C:\\Users\\eminy\\OneDrive\\Masaüstü\\ruh-beden\\WB02L08B3\\Crizal - Multipurpose Responsive Template + Admin\\crizal\\arkaplan\\danismanlik-0" + index + ".jpg";

        try {
            // Create a BufferedImage with RGB type
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();

            // Set high-quality rendering
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            // Fill the background with a gradient
            GradientPaint gradient = new GradientPaint(
                    0, 0, backgroundColor,
                    0, height, new Color(230, 240, 255), true);
            graphics.setPaint(gradient);
            graphics.fillRect(0, 0, width, height);

            // Calculate dynamic font size
            float fontSize = calculateFontSize(graphics, text, fontName, width - 100); // 100px padding
            Font font = new Font(fontName, Font.BOLD, (int) fontSize);
            graphics.setFont(font);
            FontMetrics metrics = graphics.getFontMetrics(font);

            // Split text into lines if too long
            String[] lines = wrapText(text, metrics, width - 100);
            int lineHeight = metrics.getHeight();
            int totalTextHeight = lines.length * lineHeight;

            // Calculate starting Y position to center text block vertically
            int startY = (height - totalTextHeight) / 2 + metrics.getAscent();

            // Draw text with shadow
            for (int i = 0; i < lines.length; i++) {
                int textWidth = metrics.stringWidth(lines[i]);
                int x = (width - textWidth) / 2; // Center horizontally
                int y = startY + (i * lineHeight);

                // Draw shadow
                graphics.setColor(shadowColor);
                graphics.drawString(lines[i], x + 2, y + 2);

                // Draw text
                graphics.setColor(textColor);
                graphics.drawString(lines[i], x, y);
            }

            // Draw black border around the image
            graphics.setColor(borderColor);
            graphics.setStroke(new BasicStroke(borderWidth));
            graphics.drawRect(borderWidth / 2, borderWidth / 2, width - borderWidth, height - borderWidth);

            // Dispose of graphics
            graphics.dispose();

            // Save the image as JPEG
            File outputFile = new File(outputPath);
            ImageIO.write(image, "jpg", outputFile);
            System.out.println("Image saved to " + outputPath);

        } catch (Exception e) {
            System.err.println("Error generating image: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Calculate dynamic font size to ensure text fits
    private static float calculateFontSize(Graphics2D graphics, String text, String fontName, int maxWidth) {
        float fontSize = 48f; // Start with a reasonable size

        while (fontSize > 12) {
            Font font = new Font(fontName, Font.BOLD, (int) fontSize);
            graphics.setFont(font);
            FontMetrics metrics = graphics.getFontMetrics(font);

            // Wrap the text with the current font size
            String[] lines = wrapText(text, metrics, maxWidth);

            // Check if each line fits within maxWidth
            boolean allLinesFit = true;
            for (String line : lines) {
                if (metrics.stringWidth(line) > maxWidth) {
                    allLinesFit = false;
                    break;
                }
            }

            if (allLinesFit) {
                break; // Font size is good
            }
            fontSize -= 2; // Otherwise decrease font size and retry
        }
        return fontSize;
    }


    // Wrap text into multiple lines if too long
    private static String[] wrapText(String text, FontMetrics metrics, int maxWidth) {
        String[] words = text.split(" ");
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            String testLine = currentLine.length() == 0 ? word : currentLine + " " + word;
            if (metrics.stringWidth(testLine) <= maxWidth) {
                currentLine.append(currentLine.length() == 0 ? word : " " + word);
            } else {
                if (currentLine.length() > 0) {
                    lines.add(currentLine.toString());
                }
                currentLine = new StringBuilder(word);
            }
        }
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }

        return lines.toArray(new String[0]);
    }

    private static void extracted1() {
        File inputFolder = new File("C:\\Users\\YUCE\\Downloads\\WB0C89H44 (2)\\Fabrex - Multipurpose Business and Admin Template\\fabrex\\img\\logos\\original");  // Resimlerin olduğu klasör
        File logoFile = new File("C:\\Users\\YUCE\\Downloads\\WB0C89H44 (2)\\Fabrex - Multipurpose Business and Admin Template\\fabrex\\img\\logos\\chatgpt-logo.png");        // Kullanılacak logo
        File outputFolder = new File("C:\\Users\\YUCE\\Downloads\\WB0C89H44 (2)\\Fabrex - Multipurpose Business and Admin Template\\fabrex\\img\\logos\\output_logo2");

        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }

        BufferedImage originalLogo;
        try {
            originalLogo = ImageIO.read(logoFile);
        } catch (IOException e) {
            System.err.println("Logo okunamadı: " + e.getMessage());
            return;
        }

        File[] inputFiles = inputFolder.listFiles((dir, name) -> {
            String lower = name.toLowerCase();
            return lower.endsWith(".jpg") || lower.endsWith(".jpeg") || lower.endsWith(".png");
        });

        if (inputFiles == null || inputFiles.length == 0) {
            System.out.println("Girdi klasöründe resim bulunamadı.");
            return;
        }

        for (File inputFile : inputFiles) {
            try {
                BufferedImage refImage = ImageIO.read(inputFile);
                if (refImage == null) continue;

                int width = refImage.getWidth();
                int height = refImage.getHeight();

                BufferedImage resizedLogo = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = resizedLogo.createGraphics();
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

                g2d.drawImage(originalLogo, 0, 0, width, height, null);
                g2d.dispose();

                String nameWithoutExt = inputFile.getName().replaceFirst("[.][^.]+$", "");
                File outputFile = new File(outputFolder, nameWithoutExt + ".png");
                ImageIO.write(resizedLogo, "png", outputFile);

                System.out.println("Oluşturuldu: " + outputFile.getAbsolutePath());

            } catch (IOException e) {
                System.err.println("Hata: " + inputFile.getName());
                e.printStackTrace();
            }
        }
    }

    private static void extracted() {
        File inputFolder = new File("C:\\Users\\eminy\\OneDrive\\Masaüstü\\ruh-beden\\WB02L08B3\\Crizal - Multipurpose Responsive Template + Admin\\crizal\\img\\logos\\original"); // Change "images" to your folder path

        File outputFolder = new File(inputFolder.getParent(), inputFolder.getName() + "_output");
        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }

        File[] files = inputFolder.listFiles((dir, name) -> {
            String lowerName = name.toLowerCase();
            return lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg") || lowerName.endsWith(".png") || lowerName.endsWith(".bmp");
        });

        if (files != null) {
            for (File file : files) {
                try {
                    BufferedImage original = ImageIO.read(file);
                    if (original == null) continue;

                    int width = original.getWidth();
                    int height = original.getHeight();

                    BufferedImage transparentImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2d = transparentImage.createGraphics();

                    // Transparent background
                    g2d.setComposite(AlphaComposite.Clear);
                    g2d.fillRect(0, 0, width, height);

                    // Start drawing shapes and text
                    g2d.setComposite(AlphaComposite.Src);
                    g2d.setColor(Color.RED);

                    // Draw rectangle border
                    int borderThickness = Math.max(4, width / 100); // Adaptive border thickness
                    g2d.setStroke(new BasicStroke(borderThickness));
                    g2d.drawRect(borderThickness / 2, borderThickness / 2, width - borderThickness, height - borderThickness);

                    // Draw centered text
                    g2d.setFont(new Font("Arial", Font.BOLD, width / 15));
                    String text = "Ruh, Beden ve Denge";
                    FontMetrics fm = g2d.getFontMetrics();
                    int textWidth = fm.stringWidth(text);
                    int textHeight = fm.getHeight();
                    int x = (width - textWidth) / 2;
                    int y = (height - textHeight) / 2 + fm.getAscent();
                    g2d.drawString(text, x, y);

                    g2d.dispose();

                    String fileNameWithoutExt = file.getName().replaceFirst("[.][^.]+$", "");
                    File outputFile = new File(outputFolder, fileNameWithoutExt + ".png");

                    ImageIO.write(transparentImage, "png", outputFile);
                    System.out.println("Created: " + outputFile.getAbsolutePath());

                } catch (IOException e) {
                    System.err.println("Error processing file: " + file.getName());
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No images found in the input folder.");
        }
    }

    private static void generateTransparentFiles() {
        // Input folder path
        File inputFolder = new File("C:\\Users\\eminy\\OneDrive\\Masaüstü\\ruh-beden\\WB02L08B3\\Crizal - Multipurpose Responsive Template + Admin\\crizal\\img\\logos\\original"); // Change "images" to your folder path

        // Output folder (next to input folder)
        File outputFolder = new File(inputFolder.getParent(), inputFolder.getName() + "_output");

        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }

        File[] files = inputFolder.listFiles((dir, name) -> {
            String lowerName = name.toLowerCase();
            return lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg") || lowerName.endsWith(".png") || lowerName.endsWith(".bmp");
        });

        if (files != null) {
            for (File file : files) {
                try {
                    BufferedImage original = ImageIO.read(file);
                    if (original == null) continue;

                    BufferedImage transparent = new BufferedImage(
                            original.getWidth(),
                            original.getHeight(),
                            BufferedImage.TYPE_INT_ARGB
                    );

                    // Create fully transparent image
                    Graphics2D g2d = transparent.createGraphics();
                    g2d.dispose();

                    // Output file
                    String fileNameWithoutExt = file.getName().replaceFirst("[.][^.]+$", "");
                    File outputFile = new File(outputFolder, fileNameWithoutExt + ".png");

                    ImageIO.write(transparent, "png", outputFile);
                    System.out.println("Created transparent image: " + outputFile.getAbsolutePath());

                } catch (IOException e) {
                    System.err.println("Error processing file: " + file.getName());
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No image files found in the input folder.");
        }
    }

    private static void generateCompanyPlaceDataFromGooglePlaceAPI(String[] args, ApplicationContext context) {
        context.getBean(CompanyService.class).generateCompanyPlaceDataFromGooglePlaceAPI(Integer.parseInt(args[0]));
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

