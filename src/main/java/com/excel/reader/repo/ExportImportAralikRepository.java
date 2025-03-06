package com.excel.reader.repo;

import com.excel.reader.entities.ExportImportAralik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportImportAralikRepository extends JpaRepository<ExportImportAralik, Integer> {

}
