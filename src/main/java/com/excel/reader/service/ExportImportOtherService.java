package com.excel.reader.service;

import com.excel.reader.entities.ExportImportOther;
import com.excel.reader.entities.dto.ReportTotalProcessedDTO;
import com.excel.reader.model.ExportImportOtherSearchParams;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ExportImportOtherService {

    List<ReportTotalProcessedDTO> getReportTotalProcessed();

    void saveAll(List<ExportImportOther> otherBatch);

    InputStreamResource exportExportImportOtherBySearchParams(ExportImportOtherSearchParams exportImportOtherSearchParams) throws IOException;

    Page<ExportImportOther> getExportImportOtherBySearchParams(ExportImportOtherSearchParams exportImportOtherSearchParams);
}
