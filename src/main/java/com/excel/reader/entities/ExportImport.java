package com.excel.reader.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "ExportImport")
public class ExportImport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // Automatically incrementing ID field

    @Column(name = "File_Name", nullable = false, length = 255)
    private String fileName;  // File name field

    @Column(name = "Sheet_Name", length = 255)
    private String sheetName;  // File name field

    @Column(name = "Row_Number", nullable = false)
    private Integer rowNumber;  // Row number field

    @Column(name = "Row_Data", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String rowData;  // Row data as JSON string
}
