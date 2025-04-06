package com.excel.reader.service.impl;

import com.excel.reader.annotation.TimedExecution;
import com.excel.reader.entities.ExportImportOther;
import com.excel.reader.entities.dto.ExportImportOtherDTO;
import com.excel.reader.entities.dto.ReportTotalProcessedDTO;
import com.excel.reader.model.ExportImportOtherSearchParams;
import com.excel.reader.model.ExportImportOtherSpecification;
import com.excel.reader.repo.ExportImportOtherRespository;
import com.excel.reader.service.EntityExcelHelper;
import com.excel.reader.service.ExportImportOtherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;
import java.util.stream.Collectors;


@Component
@Slf4j
public class ExportImportOtherServiceImpl implements ExportImportOtherService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ExportImportOtherRespository exportImportOtherRepository;
    @Autowired
    private EntityExcelHelper entityExcelHelper;

    @TimedExecution("StoredProcedureQuery.ExportImportOthersType.saveAll")
    public void saveAll(List<ExportImportOther> otherBatch) {
        this.batchInsertPostgreSQL(otherBatch);
    }

    @TimedExecution("exportExportImportOtherBySearchParams")
    @Override
    public InputStreamResource exportExportImportOtherBySearchParams(ExportImportOtherSearchParams exportImportOtherSearchParams) throws IOException {
        var export = new ExportImportOtherSpecification(exportImportOtherSearchParams);
        var exportTransactionsList = exportImportOtherRepository.findAll(export);

        if (exportTransactionsList.isEmpty()) {
            log.info("No records to download");
            // Return an empty InputStreamResource
            return new InputStreamResource(new ByteArrayInputStream(new byte[0]));
        }

        var result = entityExcelHelper.createExportImportOtherWorkbook(exportTransactionsList);

        return new InputStreamResource(result);
    }

    @TimedExecution("getExportImportOtherBySearchParams")
    @Override
    public Page<ExportImportOther> getExportImportOtherBySearchParams(ExportImportOtherSearchParams searchParams) {

        var page = searchParams.getPage();
        Pageable paging = null;

        log.info("Sort by {}", page.getSortBy());

        if (page.getSortBy() == null || page.getSortDirection() == null || page.getSortDirection().length() == 0) {
            paging = PageRequest.of(page.getPageNum(), page.getPageSize(), Sort.by(Sort.Direction.ASC, "id"));
        } else {
            Sort.Direction direction = Sort.Direction.fromString(page.getSortDirection());
            paging = PageRequest.of(page.getPageNum(), page.getPageSize(),
                    Sort.by(direction, page.getSortBy()).and(Sort.by(Sort.Direction.ASC, "id")));
        }

        var tranSpec = new ExportImportOtherSpecification(searchParams);
        Page<ExportImportOther> transactions = exportImportOtherRepository.findAll(tranSpec, paging);

        return transactions;
    }


    @Transactional
    public int batchInsert(List<ExportImportOther> entities) {
        List<ExportImportOtherDTO> dtos = entities.stream()
                .map(r -> ExportImportOtherDTO.convertToDTO(r))
                .collect(Collectors.toList());

        try (Connection connection = DataSourceUtils.getConnection(dataSource)) {
            // Unwrap the HikariCP connection to get the native SQL Server connection
            com.microsoft.sqlserver.jdbc.SQLServerConnection sqlServerConnection =
                    connection.unwrap(com.microsoft.sqlserver.jdbc.SQLServerConnection.class);

            // Create SQLServerDataTable matching the table type
            SQLServerDataTable dataTable = new SQLServerDataTable();
            dataTable.addColumnMetadata("alıcı_adı", Types.NVARCHAR);
            dataTable.addColumnMetadata("fatura_tutarı", Types.NVARCHAR);
            dataTable.addColumnMetadata("fatura_tutarı_döviz_türü_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("file_name", Types.VARCHAR);
            dataTable.addColumnMetadata("gideceği_ülke_adı", Types.NVARCHAR);
            dataTable.addColumnMetadata("gideceği_ülke_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("gönderici_alıcı_adı", Types.NVARCHAR);
            dataTable.addColumnMetadata("gönderici_alıcı_vergi_no", Types.NVARCHAR);
            dataTable.addColumnMetadata("gtip_açıklaması", Types.NVARCHAR);
            dataTable.addColumnMetadata("gtip_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("hesaplanmış_kalem_kıymeti_usd_değeri", Types.NVARCHAR);
            dataTable.addColumnMetadata("i̇statistiki_kıymet_usd_değeri", Types.NVARCHAR);
            dataTable.addColumnMetadata("kalem_rejim_açıklaması", Types.NVARCHAR);
            dataTable.addColumnMetadata("kalem_rejim_açıklması", Types.NVARCHAR);
            dataTable.addColumnMetadata("kalem_rejim_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("kalem_sıra_no", Types.NVARCHAR);
            dataTable.addColumnMetadata("menşe_ülke_adı", Types.NVARCHAR);
            dataTable.addColumnMetadata("menşe_ülke_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("net_ağırlık_kg", Types.NVARCHAR);
            dataTable.addColumnMetadata("ölçü_birimi_açıklaması", Types.NVARCHAR);
            dataTable.addColumnMetadata("ölçü_eşya_miktarı", Types.NVARCHAR);
            dataTable.addColumnMetadata("row_int", Types.INTEGER);
            dataTable.addColumnMetadata("sheet_name", Types.VARCHAR);
            dataTable.addColumnMetadata("tcgb_gümrük_i̇daresi_adı", Types.NVARCHAR);
            dataTable.addColumnMetadata("tcgb_gümrük_i̇daresi_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("tcgb_kapanış_tarihi", Types.NVARCHAR);
            dataTable.addColumnMetadata("tcgb_statü_açıklaması", Types.NVARCHAR);
            dataTable.addColumnMetadata("tcgb_tescil_no", Types.NVARCHAR);
            dataTable.addColumnMetadata("tcgb_tescil_tarihi", Types.NVARCHAR);
            dataTable.addColumnMetadata("teslim_şekli_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("ticari_tanımı", Types.NVARCHAR);


            // Populate the data table with DTO data
            for (ExportImportOtherDTO dto : dtos) {
                dataTable.addRow(
                        dto.getAliciAdi(),                          // String (NVARCHAR)
                        dto.getFaturaTutari(),                      // String (NVARCHAR)
                        dto.getFaturaTutariDovizTuruKodu(),         // String (NVARCHAR)
                        dto.getFileName(),                          // String (VARCHAR)
                        dto.getGidecegiUlkeAdi(),                   // String (NVARCHAR)
                        dto.getGidecegiUlkeKodu(),                  // String (NVARCHAR)
                        dto.getGondericiAliciAdi(),                 // String (NVARCHAR)
                        dto.getGondericiAliciVergiNo(),             // String (NVARCHAR)
                        dto.getGtipAciklamasi(),                    // String (NVARCHAR)
                        dto.getGtipKodu(),                          // String (NVARCHAR)
                        dto.getHesaplanmisKalemKiymetiUsdDegeri(),  // String (NVARCHAR)
                        dto.getIstatistikiKiymetUsdDegeri(),        // String (NVARCHAR)
                        dto.getKalemRejimAciklamasi(),              // String (NVARCHAR)
                        dto.getKalemRejimAciklmasi(),               // String (NVARCHAR)
                        dto.getKalemRejimKodu(),                    // String (NVARCHAR)
                        dto.getKalemSiraNo(),                       // String (NVARCHAR)
                        dto.getMenseUlkeAdi(),                      // String (NVARCHAR)
                        dto.getMenseUlkeKodu(),                     // String (NVARCHAR)
                        dto.getNetAgirlikKg(),                      // String (NVARCHAR)
                        dto.getOlcuBirimiAciklamasi(),              // String (NVARCHAR)
                        dto.getOlcuEsyaMiktari(),                   // String (NVARCHAR)
                        dto.getRowInt(),                            // Integer (INTEGER)
                        dto.getSheetName(),                         // String (VARCHAR)
                        dto.getTcgbGumrukIdaresiAdi(),              // String (NVARCHAR)
                        dto.getTcgbGumrukIdaresiKodu(),             // String (NVARCHAR)
                        dto.getTcgbKapanisTarihi(),                 // String (NVARCHAR)
                        dto.getTcgbStatuAciklamasi(),               // String (NVARCHAR)
                        dto.getTcgbTescilNo(),                      // String (NVARCHAR)
                        dto.getTcgbTescilTarihi(),                  // String (NVARCHAR)
                        dto.getTeslimSekliKodu(),                   // String (NVARCHAR)
                        dto.getTicariTanimi()                       // String (NVARCHAR)
                );
            }

            // Execute the stored procedure using the unwrapped connection
            String sql = "{call usp_BatchInsertExportImportOthers(?)}";
            try (SQLServerPreparedStatement stmt = (SQLServerPreparedStatement) sqlServerConnection.prepareStatement(sql)) {
                stmt.setStructured(1, "dbo.ExportImportOthersType", dataTable);
                stmt.execute();

                // Get the affected rows from the result set
                if (stmt.getMoreResults()) {
                    try (var rs = stmt.getResultSet()) {
                        if (rs.next()) {
                            var resultSetRow = rs.getInt(1);
                            System.out.println("the affected rows from the result set:" + resultSetRow);
                            return resultSetRow;
                        }
                    }
                }
                return 0; // Default return if no result set
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute batch insert", e);
        }
    }


    @Transactional
    public int batchInsertPostgreSQL(List<ExportImportOther> entities) {
        List<ExportImportOtherDTO> dtos = entities.stream()
                .map(r -> ExportImportOtherDTO.convertToDTO(r))
                .collect(Collectors.toList());

        try (Connection connection = DataSourceUtils.getConnection(dataSource)) {

            // Convert DTOs to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = null;
            try {
                jsonData = objectMapper.writeValueAsString(dtos);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

            // Prepare the stored procedure call
            String callProcedure = "{? = call usp_batch_insert_export_import_others(?)}";

            try (CallableStatement stmt = connection.prepareCall(callProcedure)) {
                // Register the return parameter
                stmt.registerOutParameter(1, Types.INTEGER);

                // Set the JSON parameter
                stmt.setString(2, jsonData);

                // Execute the stored procedure
                stmt.execute();

                // Get the affected rows
                int affectedRows = stmt.getInt(1);
                System.out.println("Affected rows: " + affectedRows);
                return affectedRows;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute batch insert for export_import_others", e);
        }

    }

    // It did not work out because its file name in DB does not have Turkish chars, file name in file system has Turkish Chars
    @Transactional(readOnly = true)
    public int findLastRowNumber(String fileName, String sheetName) {
        String trimmedFileName = fileName != null ? fileName.trim() : null;
        String trimmedSheetName = sheetName != null ? sheetName.trim() : null;
        log.debug("Executing query with fileName: '{}', sheetName: '{}'", trimmedFileName, trimmedSheetName);
        Integer lastRowNumber = exportImportOtherRepository.findLastRowNumber(trimmedFileName, trimmedSheetName);
        int result = lastRowNumber == null ? 0 : lastRowNumber;
        log.debug("Query result: {}", result);
        System.out.println("lastRowNumber:" + lastRowNumber);
        return result;
    }

    @TimedExecution("StoredProcedureQuery.ReportTotalProceesed")
    @Transactional(readOnly = true)
    public List<ReportTotalProcessedDTO> getReportTotalProcessed() {
        var query = entityManager.createStoredProcedureQuery("dbo.ReportTotalProceesed");

        query.execute();

        List<Object[]> results = query.getResultList();

        return results.stream().map(row -> new ReportTotalProcessedDTO(
                (String) row[0],
                ((Number) row[1]).intValue(),  // Safer casting
                (String) row[2],
                (String) row[3],
                ((Number) row[4]).intValue()   // Safer casting
        )).collect(Collectors.toList());
    }


}
