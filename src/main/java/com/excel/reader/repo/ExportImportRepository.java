package com.excel.reader.repo;

import com.excel.reader.entities.ExportImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportImportRepository extends JpaRepository<ExportImport, Integer> {

    @Procedure(procedureName = "saveExportImport")
    void saveExportImport(ExportImport exportImport);

    // JPA Query to check if a record exists based on the provided conditions
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN 1 ELSE 0 END FROM ExportImport e WHERE e.fileName = :fileName AND e.sheetName = :sheetName AND e.rowNumber = :rowNumber")
    int checkIfExportImportExists(String fileName, String sheetName, int rowNumber);

    @Query("SELECT CASE WHEN MAX(e.rowNumber) = :lastRowNum THEN true ELSE false END FROM ExportImport e " +
            "WHERE e.fileName = :normalizedFileName AND e.sheetName = :sheetName")
    boolean isLastRowForFileProceed(@Param("normalizedFileName") String normalizedFileName,
                                    @Param("sheetName") String sheetName,
                                    @Param("lastRowNum") int lastRowNum);
}
