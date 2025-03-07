package com.excel.reader.service;

import com.excel.reader.entities.ExportImportOther;
import com.excel.reader.entities.dto.ExportImportOtherDTO;
import com.excel.reader.entities.dto.ReportTotalProcessedDTO;
import com.excel.reader.repo.ExportImportOtherRespository;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExportImportOtherService {
    private static final Logger logger = LoggerFactory.getLogger(ExportImportOtherService.class);
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private DataSource dataSource;

    @Autowired
    private ExportImportOtherRespository exportImportOtherRespository;

    public void save(ExportImportOther item) {
        exportImportOtherRespository.save(item);
    }

    public void saveAll(List<ExportImportOther> otherBatch) {
        this.batchInsert(otherBatch);
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

    // It did not work out because its file name in DB does not have Turkish chars, file name in file system has Turkish Chars
    @Transactional(readOnly = true)
    public int findLastRowNumber(String fileName, String sheetName) {
        String trimmedFileName = fileName != null ? fileName.trim() : null;
        String trimmedSheetName = sheetName != null ? sheetName.trim() : null;
        logger.debug("Executing query with fileName: '{}', sheetName: '{}'", trimmedFileName, trimmedSheetName);
        Integer lastRowNumber = exportImportOtherRespository.findLastRowNumber(trimmedFileName, trimmedSheetName);
        int result = lastRowNumber == null ? 0 : lastRowNumber;
        logger.debug("Query result: {}", result);
        System.out.println("lastRowNumber:" + lastRowNumber);
        return result;
    }

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
