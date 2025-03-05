package com.excel.reader.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

public class ExcelHelper {

    // Helper method to get string cell value
    public static String getStringCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        return ExcelHelper.getCellValue(cell);
    }

    public static String getCellValue(Cell cell) {
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
                case BLANK, ERROR -> "";
                default -> cell.toString(); // Default case, ensuring no exception occurs
            };
        } catch (Exception e) {
            return ""; // Fail-safe handling
        }
    }

}
