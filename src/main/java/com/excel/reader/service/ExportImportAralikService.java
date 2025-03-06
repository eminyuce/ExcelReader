package com.excel.reader.service;

import com.excel.reader.entities.ExportImportAralik;
import com.excel.reader.repo.ExportImportAralikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExportImportAralikService {

    @Autowired
    private ExportImportAralikRepository exportImportAralikRepository;


    public void save(ExportImportAralik item) {
        exportImportAralikRepository.save(item);
    }
}
