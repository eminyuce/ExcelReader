package com.excel.reader.repo;

import com.excel.reader.entities.ExportImportAralik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportImportAralikRepository extends JpaRepository<ExportImportAralik, Integer> {

    @Query(value = "SELECT TOP 1 row_int FROM export_import_aralik WHERE file_name = :fileName AND sheet_name = :sheetName ORDER BY row_int DESC", nativeQuery = true)
    Integer findLastRowNumber(@Param("fileName") String fileName,
                              @Param("sheetName") String sheetName);

}
