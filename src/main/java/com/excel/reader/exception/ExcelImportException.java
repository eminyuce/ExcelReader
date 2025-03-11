package com.excel.reader.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Builder
public class ExcelImportException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final int row;
    private final int cell;
    /*
     * potentially more info: Expected format versus what was found, etc;
     *
     */
    private String additionalInfo;


    public String excelErrorInfo() {
        return "Importing cell at (Row: " + row + ", Cell: " + cell + ") value [" + additionalInfo + "]";
    }

}
