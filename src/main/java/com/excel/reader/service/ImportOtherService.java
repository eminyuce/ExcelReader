package com.excel.reader.service;

import com.excel.reader.entities.ImportOther;
import com.excel.reader.model.ImportOtherSearchParams;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ImportOtherService {
    void saveAll(List<ImportOther> otherBatch);

    InputStreamResource exportImportOtherBySearchParams(ImportOtherSearchParams importOtherSearchParams) throws IOException;

    Page<ImportOther> getImportOtherBySearchParams(ImportOtherSearchParams importOtherSearchParams);
}
