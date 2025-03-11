package com.excel.reader.service.impl;

import com.excel.reader.annotation.TimedExecution;
import com.excel.reader.entities.ImportOther;
import com.excel.reader.entities.dto.ImportOtherDTO;
import com.excel.reader.model.ImportOtherSearchParams;
import com.excel.reader.repo.ImportOtherServiceRepository;
import com.excel.reader.service.EntityExcelHelper;
import com.excel.reader.service.ImportOtherService;
import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Types;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportOtherServiceImpl implements ImportOtherService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ImportOtherServiceRepository exportImportOtherRepository;
    @Autowired
    private EntityExcelHelper entityExcelHelper;

    @TimedExecution("StoredProcedureQuery.ImportOther.saveAll")
    @Override
    public void saveAll(List<ImportOther> otherBatch) {
        this.batchInsert(otherBatch);
    }

    @Transactional
    public int batchInsert(List<ImportOther> entities) {
        List<ImportOtherDTO> dtos = entities.stream()
                .map(r -> ImportOtherDTO.convertToDTO(r))
                .collect(Collectors.toList());

        try (var connection = DataSourceUtils.getConnection(dataSource)) {
            SQLServerConnection sqlServerConnection = connection.unwrap(SQLServerConnection.class);

            // Create SQLServerDataTable matching the SQL Server table type
            SQLServerDataTable dataTable = new SQLServerDataTable();
            dataTable.addColumnMetadata("cikis_ulkesi_adi", Types.NVARCHAR);
            dataTable.addColumnMetadata("cikis_ulkesi_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("fatura_tutari", Types.NVARCHAR);
            dataTable.addColumnMetadata("fatura_tutari_doviz_turu_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("file_name", Types.VARCHAR);
            dataTable.addColumnMetadata("gonderen_adi", Types.NVARCHAR);
            dataTable.addColumnMetadata("gonderici_alici_adi", Types.NVARCHAR);
            dataTable.addColumnMetadata("gonderici_alici_vergi_no", Types.NVARCHAR);
            dataTable.addColumnMetadata("gtip_aciklamasi", Types.NVARCHAR);
            dataTable.addColumnMetadata("gtip_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("hesaplanmis_kalem_kiymeti_usd_degeri", Types.NVARCHAR);
            dataTable.addColumnMetadata("istatistiki_kiymet_usd_degeri", Types.NVARCHAR);
            dataTable.addColumnMetadata("kalem_rejim_aciklamasi", Types.NVARCHAR);
            dataTable.addColumnMetadata("kalem_rejim_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("kalem_sira_no", Types.NVARCHAR);
            dataTable.addColumnMetadata("mense_ulke_adi", Types.NVARCHAR);
            dataTable.addColumnMetadata("mense_ulke_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("net_agirlik_kg", Types.NVARCHAR);
            dataTable.addColumnMetadata("olcu_birimi_aciklamasi", Types.NVARCHAR);
            dataTable.addColumnMetadata("olcu_esya_miktari", Types.NVARCHAR);
            dataTable.addColumnMetadata("row_int", Types.INTEGER);
            dataTable.addColumnMetadata("sheet_name", Types.VARCHAR);
            dataTable.addColumnMetadata("tcgb_gumruk_idaresi_adi", Types.NVARCHAR);
            dataTable.addColumnMetadata("tcgb_gumruk_idaresi_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("tcgb_kapanis_tarihi", Types.NVARCHAR);
            dataTable.addColumnMetadata("tcgb_tescil_no", Types.NVARCHAR);
            dataTable.addColumnMetadata("tcgb_tescil_tarihi", Types.NVARCHAR);
            dataTable.addColumnMetadata("teslim_sekli_kodu", Types.NVARCHAR);
            dataTable.addColumnMetadata("ticari_tanimi_31", Types.NVARCHAR);

            // Populate SQLServerDataTable with DTO data
            for (ImportOtherDTO dto : dtos) {
                dataTable.addRow(
                        dto.getCikisUlkesiAdi(),
                        dto.getCikisUlkesiKodu(),
                        dto.getFaturaTutari(),
                        dto.getFaturaTutariDovizTuruKodu(),
                        dto.getFileName(),
                        dto.getGonderenAdi(),
                        dto.getGondericiAliciAdi(),
                        dto.getGondericiAliciVergiNo(),
                        dto.getGtipAciklamasi(),
                        dto.getGtipKodu(),
                        dto.getHesaplanmisKalemKiymetiUsdDegeri(),
                        dto.getIstatistikiKiymetUsdDegeri(),
                        dto.getKalemRejimAciklamasi(),
                        dto.getKalemRejimKodu(),
                        dto.getKalemSiraNo(),
                        dto.getMenseUlkeAdi(),
                        dto.getMenseUlkeKodu(),
                        dto.getNetAgirlikKg(),
                        dto.getOlcuBirimiAciklamasi(),
                        dto.getOlcuEsyaMiktari(),
                        dto.getRowInt(),
                        dto.getSheetName(),
                        dto.getTcgbGumrukIdaresiAdi(),
                        dto.getTcgbGumrukIdaresiKodu(),
                        dto.getTcgbKapanisTarihi(),
                        dto.getTcgbTescilNo(),
                        dto.getTcgbTescilTarihi(),
                        dto.getTeslimSekliKodu(),
                        dto.getTicariTanimi31()
                );
            }

            // Call stored procedure
            String sql = "{call usp_BatchInsertImportOthers(?)}";
            try (SQLServerPreparedStatement stmt = (SQLServerPreparedStatement) sqlServerConnection.prepareStatement(sql)) {
                stmt.setStructured(1, "dbo.ImportOthersType", dataTable);
                stmt.execute();

                // Get affected rows from the result set
                if (stmt.getMoreResults()) {
                    try (var rs = stmt.getResultSet()) {
                        if (rs.next()) {
                            return rs.getInt(1); // Number of rows inserted
                        }
                    }
                }
                return 0; // Default return if no result set
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute batch insert", e);
        }
    }


    @Override
    public InputStreamResource exportImportOtherBySearchParams(ImportOtherSearchParams importOtherSearchParams) throws IOException {
        return null;
    }

    @Override
    public Page<ImportOther> getImportOtherBySearchParams(ImportOtherSearchParams importOtherSearchParams) {
        return null;
    }
}
