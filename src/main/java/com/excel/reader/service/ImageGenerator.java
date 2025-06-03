package com.excel.reader.service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageGenerator {

    public static void generateImagesByText() {
        String firstLineText = " "; // Prominent first line
        List<String> otherLinesText = new ArrayList<>();

        // Generate a single image with multi-styled text
        generateMultiStyleImage(1, firstLineText, otherLinesText);
    }

    /**
     * Generates an image with a prominent first line and differently styled subsequent lines.
     * @param index The index for the output file name.
     * @param firstLine The text for the first, most prominent line.
     * @param otherLines A list of strings for the subsequent lines.
     */
    private static void generateMultiStyleImage(int index, String firstLine, List<String> otherLines) {
        // Higher resolution for sharper output
        int width = 800; // Increased from 1600
        int height = 1200; // Increased from 1600

        // Colors for good contrast
        Color backgroundColorStart = new Color(200, 220, 255); // Light blue start
        Color backgroundColorEnd = new Color(240, 245, 255); // Lighter end for gradient
        Color textColorMain = new Color(0, 51, 102); // Dark navy for main text
        Color textColorFirstLine = new Color(102, 0, 0); // Darker red for first line
        Color shadowColor = new Color(0, 0, 0, 80); // Subtle shadow with transparency
        Color borderColor = Color.RED;
        int borderWidth = 30; // Scaled for higher resolution (was 20)

        // Font choices for readability and impact
        String fontNameMain = "Verdana";
        String fontNameFirstLine = "Arial Black";
        String outputPath = "D:\\Photos\\dating" + index + ".png"; // Changed to PNG for better quality

        try {
            // Use ARGB for potential transparency
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();

            // High-quality rendering hints
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            // Draw gradient background
            GradientPaint gradient = new GradientPaint(
                    0, 0, backgroundColorStart,
                    0, height, backgroundColorEnd, true);
            graphics.setPaint(gradient);
            graphics.fillRect(0, 0, width, height);

            // --- First Line Styling ---
            float firstLineDesiredSize = 180f; // Scaled for higher resolution (was 120f)
            Font fontFirstLine = new Font(fontNameFirstLine, Font.BOLD, (int) firstLineDesiredSize);
            FontMetrics metricsFirstLine = graphics.getFontMetrics(fontFirstLine);

            float actualFirstLineFontSize = calculateFontSize(graphics, firstLine, fontNameFirstLine, width - (2 * borderWidth + 120), true, firstLineDesiredSize);
            fontFirstLine = new Font(fontNameFirstLine, Font.BOLD, (int) actualFirstLineFontSize);
            metricsFirstLine = graphics.getFontMetrics(fontFirstLine);

            List<String> wrappedFirstLine = wrapText(firstLine, metricsFirstLine, width - (2 * borderWidth + 120));

            // --- Other Lines Styling ---
            StringBuilder otherLinesCombined = new StringBuilder();
            for (String line : otherLines) {
                otherLinesCombined.append(line).append("\n");
            }
            String combinedOtherText = otherLinesCombined.toString().trim();

            float otherLinesDesiredSize = 90f; // Scaled for higher resolution (was 60f)
            Font fontOtherLines = new Font(fontNameMain, Font.PLAIN, (int) otherLinesDesiredSize);
            FontMetrics metricsOtherLines = graphics.getFontMetrics(fontOtherLines);

            float actualOtherLinesFontSize = calculateFontSize(graphics, combinedOtherText, fontNameMain, width - (2 * borderWidth + 120), false, otherLinesDesiredSize);
            fontOtherLines = new Font(fontNameMain, Font.PLAIN, (int) actualOtherLinesFontSize);
            metricsOtherLines = graphics.getFontMetrics(fontOtherLines);

            List<String> wrappedOtherLines = new ArrayList<>();
            for (String line : otherLines) {
                wrappedOtherLines.addAll(wrapText(line, metricsOtherLines, width - (2 * borderWidth + 120)));
            }

            // --- Drawing Logic ---
            // Calculate total text height
            int totalTextHeight = (wrappedFirstLine.size() * metricsFirstLine.getHeight()) +
                    (wrappedOtherLines.size() * metricsOtherLines.getHeight()) +
                    20; // Small gap between first line and other lines

            int startY = (height - totalTextHeight) / 2;
            int currentY = startY;

            // Draw First Line with subtle shadow
            graphics.setFont(fontFirstLine);
            for (String line : wrappedFirstLine) {
                int textWidth = metricsFirstLine.stringWidth(line);
                int x = (width - textWidth) / 2;
                currentY += metricsFirstLine.getAscent();

                // Draw shadow
                graphics.setColor(shadowColor);
                graphics.drawString(line, x + 3, currentY + 3);
                // Draw main text
                graphics.setColor(textColorFirstLine);
                graphics.drawString(line, x, currentY);

                currentY += metricsFirstLine.getHeight();
            }

            // Add gap between first line and other lines
            currentY += 20;

            // Draw Other Lines with subtle shadow
            graphics.setFont(fontOtherLines);
            for (String line : wrappedOtherLines) {
                int textWidth = metricsOtherLines.stringWidth(line);
                int x = (width - textWidth) / 2;

                // Draw shadow
                graphics.setColor(shadowColor);
                graphics.drawString(line, x + 2, currentY + 2);
                // Draw main text
                graphics.setColor(textColorMain);
                graphics.drawString(line, x, currentY);

                currentY += metricsOtherLines.getHeight();
            }

            // Draw border
            graphics.setColor(borderColor);
            graphics.setStroke(new BasicStroke(borderWidth));
            graphics.drawRect(borderWidth / 2, borderWidth / 2, width - borderWidth, height - borderWidth);

            graphics.dispose();

            // Save as PNG for lossless quality
            File outputFile = new File(outputPath);
            ImageIO.write(image, "png", outputFile);
            System.out.println("Image saved to " + outputPath);

        } catch (IOException e) {
            System.err.println("Failed to save image to " + outputPath + ": " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error generating image: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Calculates the appropriate font size to fit text within the given width.
     * @param g Graphics2D context
     * @param text Text to measure
     * @param fontName Font name
     * @param maxWidth Maximum allowed width
     * @param isFirstLine Whether this is the first line (bold style)
     * @param initialSize Starting font size
     * @return Adjusted font size
     */
    private static float calculateFontSize(Graphics2D g, String text, String fontName, int maxWidth, boolean isFirstLine, float initialSize) {
        float fontSize = initialSize;
        Font font = new Font(fontName, isFirstLine ? Font.BOLD : Font.PLAIN, (int) fontSize);
        FontMetrics metrics = g.getFontMetrics(font);

        String[] lines = text.split("\n");
        int widestLineLength = 0;
        for (String line : lines) {
            int currentLineLength = metrics.stringWidth(line);
            if (currentLineLength > widestLineLength) {
                widestLineLength = currentLineLength;
            }
        }

        while (widestLineLength > maxWidth && fontSize > 12) { // Adjusted minimum font size
            fontSize -= 0.5f; // Finer decrement for precision
            font = new Font(fontName, isFirstLine ? Font.BOLD : Font.PLAIN, (int) fontSize);
            metrics = g.getFontMetrics(font);
            widestLineLength = 0;
            for (String line : lines) {
                int currentLineLength = metrics.stringWidth(line);
                if (currentLineLength > widestLineLength) {
                    widestLineLength = currentLineLength;
                }
            }
        }
        return fontSize;
    }

    /**
     * Wraps text to fit within the specified width.
     * @param text Text to wrap
     * @param metrics Font metrics for width calculation
     * @param maxWidth Maximum allowed width
     * @return List of wrapped lines
     */
    private static List<String> wrapText(String text, FontMetrics metrics, int maxWidth) {
        List<String> finalLines = new ArrayList<>();
        String[] hardLines = text.split("\n");

        for (String hardLine : hardLines) {
            if (metrics.stringWidth(hardLine) <= maxWidth) {
                finalLines.add(hardLine);
            } else {
                String currentLine = "";
                String[] words = hardLine.split("\\s+");

                for (String word : words) {
                    String potentialLine = currentLine.isEmpty() ? word : currentLine + " " + word;
                    if (metrics.stringWidth(potentialLine) <= maxWidth) {
                        currentLine = potentialLine;
                    } else {
                        if (!currentLine.isEmpty()) {
                            finalLines.add(currentLine);
                        }
                        currentLine = word;
                    }
                }
                if (!currentLine.isEmpty()) {
                    finalLines.add(currentLine);
                }
            }
        }
        return finalLines;
    }
}