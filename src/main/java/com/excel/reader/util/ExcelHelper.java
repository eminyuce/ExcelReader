package com.excel.reader.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class ExcelHelper {

    public static String removeDuplicateSpaces(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        // Replace multiple spaces with a single space
        String result = input.replaceAll("\\s+", " ");

        return result;
    }

    public static String getCellValue(Cell cell) {
        var message = getCellValueString(cell);
        return message.length() > 2550 ? message.substring(0, 2550) : message; // Trim if too long
    }

    public static String getCellValueString(Cell cell) {
        if (cell == null) return "";

        try {
            return switch (cell.getCellType()) {
                case STRING -> cell.getStringCellValue();
                case NUMERIC -> {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        yield cell.getLocalDateTimeCellValue().toString(); // Handle date values properly
                    } else {
                        yield String.valueOf(cell.getNumericCellValue());
                    }
                }
                case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
                case FORMULA -> {
                    try {
                        yield cell.getStringCellValue(); // Try as STRING first
                    } catch (IllegalStateException e) {
                        yield String.valueOf(cell.getNumericCellValue()); // Fallback to NUMERIC
                    }
                }
                case BLANK, ERROR -> "BLANK/ERROR";
                case _NONE -> "N/A";
                default -> cell.toString(); // Default case, ensuring no exception occurs
            };
        } catch (Exception e) {
            return "Exception: " + e.getMessage();
        }
    }


}
