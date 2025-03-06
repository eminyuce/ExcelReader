package com.excel.reader.service;

import com.excel.reader.entities.ExportImport;
import com.excel.reader.repo.ExportImportRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportImportService {
    @Autowired
    private ExportImportRepository exportImportRepository;

    public int checkIfExportImportExists(ExportImport exportImport) {
        int exists = exportImportRepository.checkIfExportImportExists(
                exportImport.getFileName(),
                exportImport.getSheetName(),
                exportImport.getRowNumber()
        );

        return exists;
    }

    public void save(ExportImport exportImport) {
        exportImportRepository.save(exportImport);
    }

    public boolean isLastRowForFileProceed(String normalizedFileName, String sheetName, int lastRowNum) {
        return exportImportRepository.isLastRowForFileProceed(normalizedFileName, sheetName, lastRowNum);
    }

    @Transactional
    public void saveBatch(List<ExportImport> records) {
        exportImportRepository.saveAll(records); // JPA's batch save
    }

    public int findLastRowNumber(String fileName, String sheetName) {
        Integer lastRowNumber = 0; //exportImportRepository.findLastRowNumber(fileName, sheetName);
        var result = lastRowNumber == null ? 0 : lastRowNumber;
        System.out.println("lastRowNumber:" + result);
        return result;
    }
}
