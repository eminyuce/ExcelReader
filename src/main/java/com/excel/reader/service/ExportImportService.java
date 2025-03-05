package com.excel.reader.service;

import com.excel.reader.entities.ExportImport;
import com.excel.reader.repo.ExportImportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void saveExportImport(ExportImport exportImport) {
        exportImportRepository.saveExportImport(exportImport);
    }

    public void save(ExportImport exportImport) {
        exportImportRepository.save(exportImport);
    }

    public boolean isLastRowForFileProceed(String normalizedFileName, String sheetName, int lastRowNum) {
        return exportImportRepository.isLastRowForFileProceed(normalizedFileName,sheetName,lastRowNum);
    }
}
