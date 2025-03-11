package com.excel.reader.entities.dto;

import com.excel.reader.entities.ImportOther;
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
public class ImportOtherDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

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

    @JsonProperty("Gönderici / Alıcı Adı")
    private String gondericiAliciAdi;

    @JsonProperty("Gönderen Adı")
    private String gonderenAdi;

    @JsonProperty("Çıkış Ülkesi Kodu")
    private String cikisUlkesiKodu;

    @JsonProperty("Çıkış Ülkesi Adı")
    private String cikisUlkesiAdi;

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

    @JsonProperty("Kalem Rejim Açıklması")
    private String kalemRejimAciklamasi;

    @JsonProperty("GTIP Kodu")
    private String gtipKodu;

    @JsonProperty("GTIP Açıklaması")
    private String gtipAciklamasi;

    @JsonProperty("31. Ticari Tanımı")
    private String ticariTanimi31;

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


    public static ImportOtherDTO convertToDTO(ImportOther r) {
        if (r == null) {
            return null;
        }

        return ImportOtherDTO.builder()
                .id(r.getId())
                .rowInt(r.getRowInt())
                .fileName(r.getFileName())
                .sheetName(r.getSheetName())
                .tcgbGumrukIdaresiKodu(r.getTcgbGumrukIdaresiKodu())
                .tcgbGumrukIdaresiAdi(r.getTcgbGumrukIdaresiAdi())
                .tcgbTescilNo(r.getTcgbTescilNo())
                .tcgbTescilTarihi(r.getTcgbTescilTarihi())
                .tcgbKapanisTarihi(r.getTcgbKapanisTarihi())
                .gondericiAliciVergiNo(r.getGondericiAliciVergiNo())
                .gondericiAliciAdi(r.getGondericiAliciAdi())
                .gonderenAdi(r.getGonderenAdi())
                .cikisUlkesiKodu(r.getCikisUlkesiKodu())
                .cikisUlkesiAdi(r.getCikisUlkesiAdi())
                .menseUlkeKodu(r.getMenseUlkeKodu())
                .menseUlkeAdi(r.getMenseUlkeAdi())
                .teslimSekliKodu(r.getTeslimSekliKodu())
                .kalemSiraNo(r.getKalemSiraNo())
                .kalemRejimKodu(r.getKalemRejimKodu())
                .kalemRejimAciklamasi(r.getKalemRejimAciklamasi())
                .gtipKodu(r.getGtipKodu())
                .gtipAciklamasi(r.getGtipAciklamasi())
                .ticariTanimi31(r.getTicariTanimi31())
                .faturaTutari(r.getFaturaTutari())
                .faturaTutariDovizTuruKodu(r.getFaturaTutariDovizTuruKodu())
                .olcuEsyaMiktari(r.getOlcuEsyaMiktari())
                .olcuBirimiAciklamasi(r.getOlcuBirimiAciklamasi())
                .netAgirlikKg(r.getNetAgirlikKg())
                .hesaplanmisKalemKiymetiUsdDegeri(r.getHesaplanmisKalemKiymetiUsdDegeri())
                .istatistikiKiymetUsdDegeri(r.getIstatistikiKiymetUsdDegeri())
                .build();
    }

}