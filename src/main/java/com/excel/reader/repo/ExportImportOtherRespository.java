package com.excel.reader.repo;

import com.excel.reader.entities.ExportImportOther;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportImportOtherRespository extends JpaRepository<ExportImportOther, Integer> {
}
