package com.excel.reader.service;

import com.excel.reader.entities.ExportImportAralik;
import com.excel.reader.model.ExportImportAralikSearchParams;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ExportImportAralikService {

    void saveAll(List<ExportImportAralik> aralikBatch);

    Page<ExportImportAralik> getExportImportAralikBySearchParams(ExportImportAralikSearchParams exportImportAralikSearchParams) throws IOException;

    InputStreamResource exportExportImportAralikBySearchParams(ExportImportAralikSearchParams exportImportAralikSearchParams) throws IOException;
}
