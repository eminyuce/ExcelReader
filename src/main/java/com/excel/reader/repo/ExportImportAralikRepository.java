package com.excel.reader.repo;

import com.excel.reader.entities.ExportImportAralik;
import jakarta.persistence.QueryHint;
import jakarta.persistence.Tuple;
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
public interface ExportImportAralikRepository extends JpaRepository<ExportImportAralik, Integer> {

    @Query(value = "SELECT TOP 1 row_int FROM export_import_aralik WHERE file_name = :fileName AND sheet_name = :sheetName ORDER BY row_int DESC", nativeQuery = true)
    Integer findLastRowNumber(@Param("fileName") String fileName,
                              @Param("sheetName") String sheetName);


    @Transactional(readOnly = true)
    @QueryHints({@QueryHint(name = "org.hibernate.fetchSize", value = "5000")})
    Page<ExportImportAralik> findAll(Specification<ExportImportAralik> spec, Pageable pageable);

    @Transactional(readOnly = true)
    @QueryHints({@QueryHint(name = "org.hibernate.fetchSize", value = "5000")})
    public List<ExportImportAralik> findAll(Specification<ExportImportAralik> spec);

    @Query("SELECT min(e.id) AS id, e.gondericiAdiUnvani as gonderiSirket, e.aliciAdiUnvani as aliciSirket " +
            "FROM ExportImportAralik e " +
            "WHERE e.gondericiAdiUnvani <> '' AND e.aliciAdiUnvani <> '' " +
            "GROUP BY e.gondericiAdiUnvani, e.aliciAdiUnvani " +
            "ORDER BY min(e.id)")
    Page<Tuple> findMinIdAndGroupedCompanies(Pageable pageable);
}
