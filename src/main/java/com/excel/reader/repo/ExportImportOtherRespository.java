package com.excel.reader.repo;

import com.excel.reader.entities.ExportImportOther;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ExportImportOtherRespository extends JpaRepository<ExportImportOther, Integer> {

    @Query(value = "SELECT TOP 1 row_int FROM [dbo].[export_import_others] WHERE file_name = :fileName AND sheet_name = :sheetName ORDER BY row_int DESC", nativeQuery = true)
    Integer findLastRowNumber(@Param("fileName") String fileName,
                              @Param("sheetName") String sheetName);

    @Transactional(readOnly = true)
    @QueryHints({@QueryHint(name = "org.hibernate.fetchSize", value = "5000")})
    Page<ExportImportOther> findAll(Specification<ExportImportOther> spec, Pageable pageable);

    @Transactional(readOnly = true)
    @QueryHints({@QueryHint(name = "org.hibernate.fetchSize", value = "5000")})
    public List<ExportImportOther> findAll(Specification<ExportImportOther> spec);

}
