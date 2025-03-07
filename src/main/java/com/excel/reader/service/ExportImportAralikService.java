package com.excel.reader.service;

import com.excel.reader.entities.ExportImportAralik;
import com.excel.reader.repo.ExportImportAralikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportImportAralikService {

    @Autowired
    private ExportImportAralikRepository exportImportAralikRepository;


    public void save(ExportImportAralik item) {
        exportImportAralikRepository.save(item);
    }

    public void saveAll(List<ExportImportAralik> aralikBatch) {
        exportImportAralikRepository.saveAll(aralikBatch);
    }
    public int findLastRowNumber(String fileName, String sheetName) {
        Integer lastRowNumber = exportImportAralikRepository.findLastRowNumber(fileName,sheetName);
        var result = lastRowNumber == null ? 0 : lastRowNumber;
        System.out.println("lastRowNumber:" + result);
        return result;
    }
}
