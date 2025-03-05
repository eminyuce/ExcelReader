package com.excel.reader.entities;

import jakarta.persistence.*;
import lombok.*;
import org.apache.poi.ss.usermodel.Row;

import static com.excel.reader.util.ExcelHelper.getStringCellValue;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Table(name = "2023-EKİM-İHR-2", schema = "dbo")
public class EkimIhr2 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "rowNumber")
    private Integer rowNumber;

    @Column(name = "[TCGB  Gümrük İdaresi Kodu]")
    private String tcgbGumrukIdaresiKodu;

    @Column(name = "[TCGB  Gümrük İdaresi Adı]")
    private String tcgbGumrukIdaresiAdi;

    @Column(name = "[TCGB Tescil No]")
    private String tcgbTescilNo;

    @Column(name = "[TCGB Tescil Tarihi]")
    private String tcgbTescilTarihi;

    @Column(name = "[TCGB Kapanış Tarihi]")
    private String tcgbKapanisTarihi;

    @Column(name = "[Gönderici / Alıcı Vergi No]")
    private String gondericiAliciVergiNo;

    @Column(name = "[Gönderici/Alıcı Adı]")
    private String gondericiAliciAdi;

    @Column(name = "[Alıcı Adı]")
    private String aliciAdi;

    @Column(name = "[Gideceği Ülke (17) Kodu]")
    private String gidecegiUlke17Kodu;

    @Column(name = "[Gideceği Ülke (17)  Adı]")
    private String gidecegiUlke17Adi;

    @Column(name = "[Menşe Ülke Kodu]")
    private String menseUlkeKodu;

    @Column(name = "[Menşe Ülke Adı]")
    private String menseUlkeAdi;

    @Column(name = "[Teslim Şekli Kodu]")
    private String teslimSekliKodu;

    @Column(name = "[Kalem Sıra No]")
    private String kalemSiraNo;

    @Column(name = "[Kalem Rejim Kodu]")
    private String kalemRejimKodu;

    @Column(name = "[Kalem Rejim Açıklması]")
    private String kalemRejimAciklamasi;

    @Column(name = "[GTIP Kodu]")
    private String gtipKodu;

    @Column(name = "[GTIP Açıklaması]")
    private String gtipAciklamasi;

    @Column(name = "[31# Ticari Tanımı]")
    private String ticariTanimi31;

    @Column(name = "[TCGB Statü Açıklaması]")
    private String tcgbStatuAciklamasi;

    @Column(name = "[Fatura Tutarı]")
    private String faturaTutari;

    @Column(name = "[Fatura Tutarı Döviz Türü Kodu]")
    private String faturaTutariDovizTuruKodu;

    @Column(name = "[Ölçü (Eşya) Miktarı]")
    private String olcuEsyaMiktari;

    @Column(name = "[Ölçü Birimi Açıklaması]")
    private String olcuBirimiAciklamasi;

    @Column(name = "[Net Ağırlık (Kg)]")
    private String netAgirlikKg;

    @Column(name = "[Hesaplanmış Kalem Kıymeti USD Değeri]")
    private String hesaplanmisKalemKiymetiUsdDegeri;

    @Column(name = "[İstatistiki Kıymet USD Değeri]")
    private String istatistikiKiymetUsdDegeri;

    public static EkimIhr2 mapRowToEntity(Row row) {
        EkimIhr2 entity = new EkimIhr2();
        entity.setRowNumber(row.getRowNum());
        entity.setTcgbGumrukIdaresiKodu(getStringCellValue(row, 0));
        entity.setTcgbGumrukIdaresiAdi(getStringCellValue(row, 1));
        entity.setTcgbTescilNo(getStringCellValue(row, 2));
        entity.setTcgbTescilTarihi(getStringCellValue(row, 3));
        entity.setTcgbKapanisTarihi(getStringCellValue(row, 4));
        entity.setGondericiAliciVergiNo(getStringCellValue(row, 5));
        entity.setGondericiAliciAdi(getStringCellValue(row, 6));
        entity.setAliciAdi(getStringCellValue(row, 7));
        entity.setGidecegiUlke17Kodu(getStringCellValue(row, 8));
        entity.setGidecegiUlke17Adi(getStringCellValue(row, 9));
        entity.setMenseUlkeKodu(getStringCellValue(row, 10));
        entity.setMenseUlkeAdi(getStringCellValue(row, 11));
        entity.setTeslimSekliKodu(getStringCellValue(row, 12));
        entity.setKalemSiraNo(getStringCellValue(row, 13));
        entity.setKalemRejimKodu(getStringCellValue(row, 14));
        entity.setKalemRejimAciklamasi(getStringCellValue(row, 15));
        entity.setGtipKodu(getStringCellValue(row, 16));
        entity.setGtipAciklamasi(getStringCellValue(row, 17));
        entity.setTicariTanimi31(getStringCellValue(row, 18));
        entity.setTcgbStatuAciklamasi(getStringCellValue(row, 19));
        entity.setFaturaTutari(getStringCellValue(row, 20));
        entity.setFaturaTutariDovizTuruKodu(getStringCellValue(row, 21));
        entity.setOlcuEsyaMiktari(getStringCellValue(row, 22));
        entity.setOlcuBirimiAciklamasi(getStringCellValue(row, 23));
        entity.setNetAgirlikKg(getStringCellValue(row, 24));
        entity.setHesaplanmisKalemKiymetiUsdDegeri(getStringCellValue(row, 25));
        entity.setIstatistikiKiymetUsdDegeri(getStringCellValue(row, 26));
        return entity;
    }

}
