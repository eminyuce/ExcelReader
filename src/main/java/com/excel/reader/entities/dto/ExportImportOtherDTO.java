package com.excel.reader.entities.dto;

import com.excel.reader.entities.ExportImportOther;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ExportImportOtherDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer rowInt;

    private String fileName;

    private String sheetName;

    @JsonProperty("TCGB Gümrük İdaresi Kodu")
    private String tcgbGumrukIdaresiKodu;

    @JsonProperty("TCGB Gümrük İdaresi Adı")
    private String tcgbGumrukIdaresiAdi;

    @JsonProperty("TCGB Tescil No")
    private String tcgbTescilNo;

    @JsonProperty("TCGB Tescil Tarihi")
    private String tcgbTescilTarihi;

    @JsonProperty("TCGB Kapanış Tarihi")
    private String tcgbKapanisTarihi;

    @JsonProperty("Gönderici / Alıcı Vergi No")
    private String gondericiAliciVergiNo;

    @JsonProperty("Gönderici/Alıcı Adı")
    private String gondericiAliciAdi;

    @JsonProperty("Alıcı Adı")
    private String aliciAdi;

    @JsonProperty("Gideceği Ülke (17) Kodu")
    private String gidecegiUlkeKodu;

    @JsonProperty("Gideceği Ülke (17) Adı")
    private String gidecegiUlkeAdi;

    @JsonProperty("Menşe Ülke Kodu")
    private String menseUlkeKodu;

    @JsonProperty("Menşe Ülke Adı")
    private String menseUlkeAdi;

    @JsonProperty("Teslim Şekli Kodu")
    private String teslimSekliKodu;

    @JsonProperty("Kalem Sıra No")
    private String kalemSiraNo;

    @JsonProperty("Kalem Rejim Kodu")
    private String kalemRejimKodu;

    @JsonProperty("Kalem Rejim Açıklaması")
    private String kalemRejimAciklamasi;

    @JsonProperty("Kalem Rejim Açıklması")
    private String kalemRejimAciklmasi;

    @JsonProperty("GTIP Kodu")
    private String gtipKodu;

    @JsonProperty("GTIP Açıklaması")
    private String gtipAciklamasi;

    @JsonProperty("31. Ticari Tanımı")
    private String ticariTanimi;

    @JsonProperty("TCGB Statü Açıklaması")
    private String tcgbStatuAciklamasi;

    @JsonProperty("Fatura Tutarı")
    private String faturaTutari;

    @JsonProperty("Fatura Tutarı Döviz Türü Kodu")
    private String faturaTutariDovizTuruKodu;

    @JsonProperty("Ölçü (Eşya) Miktarı")
    private String olcuEsyaMiktari;

    @JsonProperty("Ölçü Birimi Açıklaması")
    private String olcuBirimiAciklamasi;

    @JsonProperty("Net Ağırlık (Kg)")
    private String netAgirlikKg;

    @JsonProperty("Hesaplanmış Kalem Kıymeti USD Değeri")
    private String hesaplanmisKalemKiymetiUsdDegeri;

    @JsonProperty("İstatistiki Kıymet USD Değeri")
    private String istatistikiKiymetUsdDegeri;

    public static ExportImportOtherDTO convertToDTO(ExportImportOther entity) {
        return ExportImportOtherDTO.builder()
                .rowInt(entity.getRowInt())
                .fileName(entity.getFileName())
                .sheetName(entity.getSheetName())
                .tcgbGumrukIdaresiKodu(entity.getTcgbGumrukIdaresiKodu())
                .tcgbGumrukIdaresiAdi(entity.getTcgbGumrukIdaresiAdi())
                .tcgbTescilNo(entity.getTcgbTescilNo())
                .tcgbTescilTarihi(entity.getTcgbTescilTarihi())
                .tcgbKapanisTarihi(entity.getTcgbKapanisTarihi())
                .gondericiAliciVergiNo(entity.getGondericiAliciVergiNo())
                .gondericiAliciAdi(entity.getGondericiAliciAdi())
                .aliciAdi(entity.getAliciAdi())
                .gidecegiUlkeKodu(entity.getGidecegiUlkeKodu())
                .gidecegiUlkeAdi(entity.getGidecegiUlkeAdi())
                .menseUlkeKodu(entity.getMenseUlkeKodu())
                .menseUlkeAdi(entity.getMenseUlkeAdi())
                .teslimSekliKodu(entity.getTeslimSekliKodu())
                .kalemSiraNo(entity.getKalemSiraNo())
                .kalemRejimKodu(entity.getKalemRejimKodu())
                .kalemRejimAciklamasi(entity.getKalemRejimAciklamasi())
                .kalemRejimAciklmasi(entity.getKalemRejimAciklmasi())
                .gtipKodu(entity.getGtipKodu())
                .gtipAciklamasi(entity.getGtipAciklamasi())
                .ticariTanimi(entity.getTicariTanimi())
                .tcgbStatuAciklamasi(entity.getTcgbStatuAciklamasi())
                .faturaTutari(entity.getFaturaTutari())
                .faturaTutariDovizTuruKodu(entity.getFaturaTutariDovizTuruKodu())
                .olcuEsyaMiktari(entity.getOlcuEsyaMiktari())
                .olcuBirimiAciklamasi(entity.getOlcuBirimiAciklamasi())
                .netAgirlikKg(entity.getNetAgirlikKg())
                .hesaplanmisKalemKiymetiUsdDegeri(entity.getHesaplanmisKalemKiymetiUsdDegeri())
                .istatistikiKiymetUsdDegeri(entity.getIstatistikiKiymetUsdDegeri())
                .build();
    }
}