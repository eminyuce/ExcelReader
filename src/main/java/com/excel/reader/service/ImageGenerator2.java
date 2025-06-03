package com.excel.reader.service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageGenerator2 {

    // Inner class to hold all configurable image generation parameters
    public static class ImageConfig {
        public List<String> titleContent; // Changed to List<String>
        public String info;
        public List<String> lines;
        public List<String> footerContent; // Changed to List<String>
        public int width;
        public int height;
        public int padding;
        public Color background;
        public Color titleColor;
        public Color infoColor;
        public Color lineColor;
        public Color footerColor;
        public Color separatorColor;
        public int titleSeparatorMargin;
        public int footerSeparatorMargin;
        public int infoMarginTop;
        public Font titleFont;
        public Font infoFont;
        public Font mainFont;
        public Font footerFont;
        public Color borderColor;
        public int borderThickness;

        // Constructor with default values
        public ImageConfig() {
            this.titleContent = List.of("Default Title"); // Updated default
            this.info = "Default Info";
            this.lines = List.of("Default line 1", "Default line 2");
            this.footerContent = List.of("Default Footer"); // Updated default
            this.width = 800;
            this.height = 1200;
            this.padding = 60;
            this.background = new Color(245, 248, 255);
            this.titleColor = new Color(0, 51, 102);
            this.infoColor = new Color(80, 80, 80);
            this.lineColor = new Color(20, 20, 20);
            this.footerColor = new Color(100, 0, 0);
            this.separatorColor = new Color(180, 180, 180);
            this.titleSeparatorMargin = 20;
            this.footerSeparatorMargin = 20;
            this.infoMarginTop = 30;
            this.titleFont = new Font("Arial Black", Font.BOLD, 60);
            this.infoFont = new Font("Verdana", Font.BOLD, 30);
            this.mainFont = new Font("Verdana", Font.PLAIN, 28);
            this.footerFont = new Font("Verdana", Font.ITALIC, 24);
            this.borderColor = Color.DARK_GRAY;
            this.borderThickness = 5;
        }
    }

    public static void generateImage() {
        // Create a new configuration object
        ImageConfig config = new ImageConfig();

        // Customize the configuration with the new text
        // Title is now a List<String>
        config.titleContent = List.of(
                "Dominic Williams, Founder & Chief Scientist at DFINITY Foundation, takes the stage to unveil the Self-Writing Internet."
        );
        config.info = "New to the Internet Computer?";
        config.lines = List.of(
                "ICP enables you to build anything without traditional IT and Big Tech. It hosts decentralized serverless compute that's simpler, immune to cyber attack, unstoppable, and controllable by DAOs.",
                "Create web3 social networks and media, socialfi, games, multi-chain dapps, Al, or an enterprise app. The internet is evolving."
        );
        // Footer is now a List<String>, with each element representing a logical line/paragraph
        config.footerContent = List.of(
                "More about ICP: https://internetcomputer.org/",
                "Start building, check out the Dev Docs: https://docs.internetcomputer.org/",
                "Join the community: https://linktr.ee/icp_hubs_network",
                "Subscribe to stay up to date on the latest ICP technologies, tools, and ecosystem announcements."
        );

        // Adjust font sizes and margins for better fit with new content
        config.titleFont = new Font("Arial Black", Font.BOLD, 42); // Slightly smaller title font for better wrapping
        config.infoFont = new Font("Verdana", Font.BOLD, 28);
        config.mainFont = new Font("Verdana", Font.PLAIN, 24); // Slightly smaller main font for better wrapping
        config.footerFont = new Font("Verdana", Font.ITALIC, 18); // Slightly smaller footer font for better wrapping
        config.padding = 40; // Reduced padding to give more space for text
        config.titleSeparatorMargin = 30; // Increased margin below title for more space
        config.infoMarginTop = 45; // Increased margin below title separator
        config.footerSeparatorMargin = 30; // Increased margin above footer

        config.borderColor = new Color(50, 50, 50);
        config.borderThickness = 8;

        // Call the method to generate the image with the specified content and output path.
        // Ensure the directory 'D:\Photos\' exists or change to a writable path.
        generateStructuredImage("D:\\Photos\\structured_output.png", config);
    }

    /**
     * Generates a structured image resembling a website layout with a title, info,
     * main content lines, and a footer, using a configuration object.
     *
     * @param outputPath The file path where the generated image will be saved.
     * @param config The ImageConfig object containing all content and styling parameters.
     */
    private static void generateStructuredImage(String outputPath, ImageConfig config) {
        // Create the output directory if it doesn't exist
        File outputFile = new File(outputPath);
        File outputDirectory = outputFile.getParentFile();
        if (outputDirectory != null && !outputDirectory.exists()) {
            if (!outputDirectory.mkdirs()) {
                System.err.println("Failed to create output directory: " + outputDirectory.getAbsolutePath());
                return; // Exit if directory cannot be created
            }
        }

        try {
            // Create a buffered image with the specified width, height, and type (ARGB for transparency)
            BufferedImage image = new BufferedImage(config.width, config.height, BufferedImage.TYPE_INT_ARGB);
            // Get the Graphics2D object to draw on the image
            Graphics2D g = image.createGraphics();

            // Enable anti-aliasing for smoother text and shapes
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // Fill the background of the image
            g.setColor(config.background);
            g.fillRect(0, 0, config.width, config.height);

            // --- Draw Border around the entire image ---
            g.setColor(config.borderColor);
            g.setStroke(new BasicStroke(config.borderThickness)); // Set stroke thickness for the border
            // Draw a rectangle from (0,0) to (width, height) to create the border
            // Adjusting by half the thickness to ensure the border is fully visible within the image bounds
            g.drawRect(config.borderThickness / 2, config.borderThickness / 2,
                    config.width - config.borderThickness, config.height - config.borderThickness);
            g.setStroke(new BasicStroke(1)); // Reset stroke to default for other drawings


            // --- Draw TITLE (now left-aligned and wrapped) ---
            g.setFont(config.titleFont);
            g.setColor(config.titleColor);
            // Define drawable width for title, with a small buffer to prevent overflow
            int titleDrawableWidth = config.width - (2 * config.padding) - 10;

            // Collect all wrapped sub-lines for the title
            List<String> allWrappedTitleSubLines = new ArrayList<>();
            for (String line : config.titleContent) {
                allWrappedTitleSubLines.addAll(wrapText(g, line, titleDrawableWidth));
            }

            // Calculate total height of the wrapped title block
            int totalTitleHeight = allWrappedTitleSubLines.size() * g.getFontMetrics().getHeight();

            // Calculate starting Y position for the title block (top-aligned with padding)
            int currentTitleY = config.padding + g.getFontMetrics().getAscent();
            for (String subLine : allWrappedTitleSubLines) {
                drawLeftAlignedText(g, subLine, config.padding, currentTitleY); // Left-aligned
                currentTitleY += g.getFontMetrics().getHeight();
            }
            // Get the bottom edge of the title block for subsequent element positioning
            int titleBottom = currentTitleY - g.getFontMetrics().getDescent();


            // --- Draw Separator Line below Title ---
            g.setColor(config.separatorColor);
            int titleSeparatorY = titleBottom + config.titleSeparatorMargin; // Margin below the title
            g.drawLine(config.padding, titleSeparatorY, config.width - config.padding, titleSeparatorY);


            // --- Draw INFO (remains centered) ---
            g.setFont(config.infoFont);
            g.setColor(config.infoColor);
            // Calculate Y position for info, with a margin below the title separator
            int infoY = titleSeparatorY + config.infoMarginTop + g.getFontMetrics().getAscent();
            drawCenteredText(g, config.info, config.width, infoY);
            // Get the bottom edge of the info section
            int infoBottom = infoY + g.getFontMetrics().getDescent();


            // --- Draw FOOTER (pre-calculate its position to define main content area, now left-aligned and wrapped) ---
            g.setFont(config.footerFont);
            g.setColor(config.footerColor);
            // Define drawable width for footer, with a small buffer to prevent overflow
            int footerDrawableWidth = config.width - (2 * config.padding) - 10;

            // Collect all wrapped sub-lines for the footer
            List<String> allWrappedFooterSubLines = new ArrayList<>();
            for (String line : config.footerContent) {
                allWrappedFooterSubLines.addAll(wrapText(g, line, footerDrawableWidth));
            }

            // Calculate total height of the wrapped footer block
            int totalFooterHeight = allWrappedFooterSubLines.size() * g.getFontMetrics().getHeight();

            // Calculate Y position for the footer block, ensuring it's padded from the bottom
            int footerBlockStartY = config.height - config.padding - totalFooterHeight + g.getFontMetrics().getAscent();
            // Get the top edge of the footer section (before drawing)
            int footerTop = footerBlockStartY - g.getFontMetrics().getAscent();


            // --- Draw Separator Line above Footer ---
            g.setColor(config.separatorColor);
            int footerSeparatorY = footerTop - config.footerSeparatorMargin; // Margin above the footer
            g.drawLine(config.padding, footerSeparatorY, config.width - config.padding, footerSeparatorY);


            // --- Draw MAIN LINES (with text wrapping and vertical centering) ---
            g.setFont(config.mainFont);
            g.setColor(config.lineColor);
            FontMetrics fm = g.getFontMetrics();

            // Define the drawable width for wrapped text (image width minus left and right padding)
            int mainContentDrawableWidth = config.width - (2 * config.padding) - 10;

            // --- Pass 1: Calculate total height of wrapped text ---
            // This is necessary to vertically center the entire block of main content
            int totalWrappedMainContentHeight = 0;
            for (String line : config.lines) {
                List<String> wrappedSubLines = wrapText(g, line, mainContentDrawableWidth);
                totalWrappedMainContentHeight += wrappedSubLines.size() * fm.getHeight();
            }

            // Determine the vertical space available for main lines (between infoBottom and footerSeparatorY)
            int availableHeightForMain = footerSeparatorY - infoBottom;

            // Calculate the starting Y position to center the block of main lines within the available space
            // We add fm.getAscent() because drawString draws from the baseline
            int currentDrawingY = infoBottom + (availableHeightForMain - totalWrappedMainContentHeight) / 2 + fm.getAscent();

            // --- Pass 2: Draw the wrapped text ---
            for (String line : config.lines) {
                // Wrap the current line into sub-lines
                List<String> wrappedSubLines = wrapText(g, line, mainContentDrawableWidth);
                // Draw each sub-line, left-aligned within the padded area
                for (String subLine : wrappedSubLines) {
                    drawLeftAlignedText(g, subLine, config.padding, currentDrawingY); // Use padding for left alignment
                    currentDrawingY += fm.getHeight(); // Move Y down for the next sub-line
                }
            }

            // --- Draw Footer (after calculating its position) ---
            int currentFooterDrawingY = footerBlockStartY; // Start drawing from the calculated block start Y
            for (String subLine : allWrappedFooterSubLines) {
                drawLeftAlignedText(g, subLine, config.padding, currentFooterDrawingY); // Left-aligned
                currentFooterDrawingY += g.getFontMetrics().getHeight();
            }


            // Dispose the graphics context to release system resources
            g.dispose();
            // Write the generated image to a file
            ImageIO.write(image, "png", outputFile);
            System.out.println("Image saved to " + outputPath);

        } catch (IOException e) {
            // Print stack trace if an I/O error occurs (e.g., file writing issue)
            e.printStackTrace();
            System.err.println("Error generating image: " + e.getMessage());
        }
    }

    /**
     * Helper method to draw text centered horizontally on the Graphics2D context.
     * This method is used for single lines (info).
     *
     * @param g The Graphics2D object to draw on.
     * @param text The string to draw.
     * @param width The total width of the drawing area.
     * @param y The Y coordinate for the baseline of the text.
     */
    private static void drawCenteredText(Graphics2D g, String text, int width, int y) {
        FontMetrics fm = g.getFontMetrics();
        // Calculate the X coordinate to center the text
        int x = (width - fm.stringWidth(text)) / 2;
        g.drawString(text, x, y);
    }

    /**
     * Helper method to draw text left-aligned on the Graphics2D context.
     *
     * @param g The Graphics2D object to draw on.
     * @param text The string to draw.
     * @param x The X coordinate for the starting position of the text (left edge).
     * @param y The Y coordinate for the baseline of the text.
     */
    private static void drawLeftAlignedText(Graphics2D g, String text, int x, int y) {
        g.drawString(text, x, y);
    }

    /**
     * Helper method to wrap text into multiple lines based on a maximum width.
     * Handles newline characters within the text.
     *
     * @param g The Graphics2D object (used to get FontMetrics).
     * @param text The string to wrap.
     * @param maxWidth The maximum width a line can occupy.
     * @return A list of strings, where each string is a wrapped line.
     */
    private static List<String> wrapText(Graphics2D g, String text, int maxWidth) {
        FontMetrics fm = g.getFontMetrics();
        List<String> wrappedLines = new ArrayList<>();

        // Handle explicit newline characters first
        String[] paragraphLines = text.split("\n");

        for (String paragraphLine : paragraphLines) {
            String currentLine = "";
            // Split words by space, but also consider empty string if a word is just a space
            String[] words = paragraphLine.split(" ", -1); // -1 ensures trailing empty strings are included

            for (String word : words) {
                // If word is empty (e.g., from multiple spaces), just continue
                if (word.isEmpty() && !currentLine.isEmpty()) {
                    // If currentLine is not empty, add a space to maintain spacing
                    String testLine = currentLine + " ";
                    if (fm.stringWidth(testLine) < maxWidth) {
                        currentLine = testLine;
                        continue;
                    }
                }

                String testLine = currentLine.isEmpty() ? word : currentLine + " " + word;

                if (fm.stringWidth(testLine) < maxWidth) {
                    currentLine = testLine;
                } else {
                    if (!currentLine.isEmpty()) {
                        wrappedLines.add(currentLine);
                    }
                    currentLine = word;
                }
            }
            if (!currentLine.isEmpty()) {
                wrappedLines.add(currentLine);
            }
        }
        return wrappedLines;
    }
}
