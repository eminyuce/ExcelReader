package com.excel.reader.repo;

import com.excel.reader.entities.ExportImportOther;
import com.excel.reader.entities.dto.ReportTotalProcessedDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ExportImportOtherRespository extends JpaRepository<ExportImportOther, Integer> {

    @Query(value = "SELECT TOP 1 row_int FROM [dbo].[export_import_others] WHERE file_name = :fileName AND sheet_name = :sheetName ORDER BY row_int DESC", nativeQuery = true)
    Integer findLastRowNumber(@Param("fileName") String fileName,
                              @Param("sheetName") String sheetName);


}
