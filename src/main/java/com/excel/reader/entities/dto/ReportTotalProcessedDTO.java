package com.excel.reader.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportTotalProcessedDTO {
    private String fileName;
    private Integer total;
    private String sheetName1;
    private String sheetName2;
    private Integer maxRowInt;
}