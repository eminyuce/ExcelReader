package com.excel.reader.service;

import com.excel.reader.entities.ExportImportAralik;
import com.excel.reader.entities.ExportImportOther;
import com.excel.reader.entities.dto.ExportImportAralikDTO;
import com.excel.reader.entities.dto.ExportImportOtherDTO;
import com.excel.reader.entities.dto.ReportTotalProcessedDTO;
import com.excel.reader.repo.ExportImportAralikRepository;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExportImportAralikService {

    @Autowired
    private ExportImportAralikRepository exportImportAralikRepository;
    @Autowired
    private DataSource dataSource;

    public void save(ExportImportAralik item) {
        exportImportAralikRepository.save(item);
    }

    public void saveAll(List<ExportImportAralik> aralikBatch) {
       // exportImportAralikRepository.saveAll(aralikBatch);
        this.batchInsert(aralikBatch);
    }

    @Transactional
    public int batchInsert(List<ExportImportAralik> entities) {
        List<ExportImportAralikDTO> dtos = entities.stream()
                .map(r -> ExportImportAralikDTO.convertToDTO(r))
                .collect(Collectors.toList());

        try (Connection connection = DataSourceUtils.getConnection(dataSource)) {
            // Unwrap the HikariCP connection to get the native SQL Server connection
            com.microsoft.sqlserver.jdbc.SQLServerConnection sqlServerConnection =
                    connection.unwrap(com.microsoft.sqlserver.jdbc.SQLServerConnection.class);

            // Create SQLServerDataTable matching the table type
            SQLServerDataTable dataTable = new SQLServerDataTable();
            dataTable.addColumnMetadata("alici_adi_unvani", Types.NVARCHAR);
            dataTable.addColumnMetadata("alici_kimlik_no", Types.NVARCHAR);
            dataTable.addColumnMetadata("beyanname_no", Types.NVARCHAR);
            dataTable.addColumnMetadata("brut_agirlik", Types.NVARCHAR);
            dataTable.addColumnMetadata("cikis_ulkesi_adi", Types.NVARCHAR);
            dataTable.addColumnMetadata("cikis_ulkesi_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("cikistaki_aracin_kayitli_oldugu_ulke_adi", Types.NVARCHAR);
            dataTable.addColumnMetadata("cikistaki_aracin_kayitli_oldugu_ulke_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("esya_ticari_tanimi", Types.NVARCHAR);
            dataTable.addColumnMetadata("file_name", Types.VARCHAR);
            dataTable.addColumnMetadata("gidecegi_ulke_adi", Types.NVARCHAR);
            dataTable.addColumnMetadata("gidecegi_ulke_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("gonderici_adi_unvani", Types.NVARCHAR);
            dataTable.addColumnMetadata("gonderici_kimlik_no", Types.NVARCHAR);
            dataTable.addColumnMetadata("gtip_aciklamasi", Types.NVARCHAR);
            dataTable.addColumnMetadata("gtip_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("gumruk_istatistik_tarihi", Types.NVARCHAR);
            dataTable.addColumnMetadata("hesaplanmis_kalem_kiymeti_usd_degeri", Types.NVARCHAR);
            dataTable.addColumnMetadata("istatistiki_birim_aciklamasi", Types.NVARCHAR);
            dataTable.addColumnMetadata("istatistiki_birim_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("istatistiki_kiymet_usd_degeri", Types.NVARCHAR);
            dataTable.addColumnMetadata("kalem_sira_numarasi", Types.NVARCHAR);
            dataTable.addColumnMetadata("kullanici_birim_kiymeti_usd_degeri", Types.NVARCHAR);
            dataTable.addColumnMetadata("mense_ulke_adi", Types.NVARCHAR);
            dataTable.addColumnMetadata("mense_ulke_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("navlun_tutari_tl_degeri", Types.NVARCHAR);
            dataTable.addColumnMetadata("net_agirlik", Types.NVARCHAR);
            dataTable.addColumnMetadata("olcu_birimi", Types.NVARCHAR);
            dataTable.addColumnMetadata("olcu_birimi_aciklamasi", Types.NVARCHAR);
            dataTable.addColumnMetadata("olcu_birimi_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("row_int", Types.INTEGER);
            dataTable.addColumnMetadata("sheet_name", Types.VARCHAR);
            dataTable.addColumnMetadata("sigorta_tutari", Types.NVARCHAR);
            dataTable.addColumnMetadata("sinirdaki_aracin_tasima_sekli_aciklamasi", Types.NVARCHAR);
            dataTable.addColumnMetadata("sinirdaki_aracin_tasima_sekli_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("tcgb_bolge_mudurlugu_adi", Types.NVARCHAR);
            dataTable.addColumnMetadata("tcgb_bolge_mudurlugu_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("tcgb_gumruk_idaresi_adi", Types.NVARCHAR);
            dataTable.addColumnMetadata("tcgb_gumruk_idaresi_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("teslim_sekli_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("ticaret_yapilan_ulke_adi", Types.NVARCHAR);
            dataTable.addColumnMetadata("ticaret_yapilan_ulke_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("yukleme_bosaltma_yapilan_gumruk_idaresi_adi", Types.NVARCHAR);
            dataTable.addColumnMetadata("yukleme_bosaltma_yapilan_gumruk_idaresi_kodu", Types.NVARCHAR);

            // Populate the data table with DTO data
            for (ExportImportAralikDTO dto : dtos) {
                dataTable.addRow(
                        dto.getAliciAdiUnvani(),
                        dto.getAliciKimlikNo(),
                        dto.getBeyannameNo(),
                        dto.getBrutAgirlik(),
                        dto.getCikisUlkesiAdi(),
                        dto.getCikisUlkesiKodu(),
                        dto.getCikistakiAracinKayitliOlduguUlkeAdi(),
                        dto.getCikistakiAracinKayitliOlduguUlkeKodu(),
                        dto.getEsyaTicariTanimi(),
                        dto.getFileName(),
                        dto.getGidecegiUlkeAdi(),
                        dto.getGidecegiUlkeKodu(),
                        dto.getGondericiAdiUnvani(),
                        dto.getGondericiKimlikNo(),
                        dto.getGtipAciklamasi(),
                        dto.getGtipKodu(),
                        dto.getGumrukIstatistikTarihi(),
                        dto.getHesaplanmisKalemKiymetiUsdDegeri(),
                        dto.getIstatistikiBirimAciklamasi(),
                        dto.getIstatistikiBirimKodu(),
                        dto.getIstatistikiKiymetUsdDegeri(),
                        dto.getKalemSiraNumarasi(),
                        dto.getKullaniciBirimKiymetiUsdDegeri(),
                        dto.getMenseUlkeAdi(),
                        dto.getMenseUlkeKodu(),
                        dto.getNavlunTutariTlDegeri(),
                        dto.getNetAgirlik(),
                        dto.getOlcuBirimi(),
                        dto.getOlcuBirimiAciklamasi(),
                        dto.getOlcuBirimiKodu(),
                        dto.getRowInt(),
                        dto.getSheetName(),
                        dto.getSigortaTutari(),
                        dto.getSinirdakiAracinTasimaSekliAciklamasi(),
                        dto.getSinirdakiAracinTasimaSekliKodu(),
                        dto.getTcgbBolgeMudurluguAdi(),
                        dto.getTcgbBolgeMudurluguKodu(),
                        dto.getTcgbGumrukIdaresiAdi(),
                        dto.getTcgbGumrukIdaresiKodu(),
                        dto.getTeslimSekliKodu(),
                        dto.getTicaretYapilanUlkeAdi(),
                        dto.getTicaretYapilanUlkeKodu(),
                        dto.getYuklemeBosaltmaYapilanGumrukIdaresiAdi(),
                        dto.getYuklemeBosaltmaYapilanGumrukIdaresiKodu()
                );
            }

            // Execute the stored procedure using the unwrapped connection
            String sql = "{call usp_BatchInsertExportImportAralik(?)}";
            try (SQLServerPreparedStatement stmt = (SQLServerPreparedStatement) sqlServerConnection.prepareStatement(sql)) {
                stmt.setStructured(1, "dbo.ExportImportAralikType", dataTable);
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
            throw new RuntimeException("Failed to execute batch insert for export_import_aralik", e);
        }
    }

    public int findLastRowNumber(String fileName, String sheetName) {
        Integer lastRowNumber = exportImportAralikRepository.findLastRowNumber(fileName, sheetName);
        var result = lastRowNumber == null ? 0 : lastRowNumber;
        System.out.println("lastRowNumber:" + result);
        return result;
    }
}
