package com.excel.reader.service;

import com.excel.reader.entities.ExportImportAralik;
import com.excel.reader.entities.ExportImportOther;
import com.excel.reader.repo.ExportImportOtherRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExportImportOtherService {

    @Autowired
    private  ExportImportOtherRespository exportImportOtherRespository;

    public void save(ExportImportOther item) {
        exportImportOtherRespository.save(item);
    }

}
