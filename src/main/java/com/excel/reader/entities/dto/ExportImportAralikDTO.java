package com.excel.reader.entities.dto;

import com.excel.reader.entities.ExportImportAralik;
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
public class ExportImportAralikDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer rowInt;
    private String fileName;
    private String sheetName;

    @JsonProperty("TCGB Bölge Müdürlüğü Kodu")
    private String tcgbBolgeMudurluguKodu;

    @JsonProperty("TCGB Bölge Müdürlüğü Adı")
    private String tcgbBolgeMudurluguAdi;

    @JsonProperty("TCGB Gümrük İdaresi Kodu")
    private String tcgbGumrukIdaresiKodu;

    @JsonProperty("TCGB Gümrük İdaresi Adı")
    private String tcgbGumrukIdaresiAdi;

    @JsonProperty("Yükleme/Boşaltma Yapılan Gümrük İdaresi Kodu")
    private String yuklemeVeyaBosaltmaYapilanGumrukIdaresiKodu;

    @JsonProperty("Yükleme/Boşaltma Yapılan Gümrük İdaresi Adı")
    private String yuklemeVeyaBosaltmaYapilanGumrukIdaresiAdi;

    @JsonProperty("Gümrük İstatistik Tarihi (Bordro Tarihi)")
    private String gumrukIstatistikTarihi;

    @JsonProperty("Çıkış Ülkesi Kodu")
    private String cikisUlkesiKodu;

    @JsonProperty("Çıkış Ülkesi Adı")
    private String cikisUlkesiAdi;

    @JsonProperty("Sigorta Tutarı")
    private String sigortaTutari;

    @JsonProperty("Gönderici Kimlik No")
    private String gondericiKimlikNo;

    @JsonProperty("Gönderici Adı Ünvanı")
    private String gondericiAdiUnvani;

    @JsonProperty("Alıcı Kimlik No")
    private String aliciKimlikNo;

    @JsonProperty("Alıcı Adı Ünvanı")
    private String aliciAdiUnvani;

    @JsonProperty("Beyanname No")
    private String beyannameNo;

    @JsonProperty("Sınırdaki Aracın Taşıma Şekli Açıklaması")
    private String sinirdakiAracinTasimaSekliAciklamasi;

    @JsonProperty("Sınırdaki Aracın Taşıma Şekli Kodu")
    private String sinirdakiAracinTasimaSekliKodu;

    @JsonProperty("Ticaret Yapılan Ülke Adı")
    private String ticaretYapilanUlkeAdi;

    @JsonProperty("Ticaret Yapılan Ülke Kodu")
    private String ticaretYapilanUlkeKodu;

    @JsonProperty("Gideceği Ülke Adı")
    private String gidecegiUlkeAdi;

    @JsonProperty("Gideceği Ülke Kodu")
    private String gidecegiUlkeKodu;

    @JsonProperty("Çıkıştaki Aracın Kayıtlı Olduğu Ülke Adı")
    private String cikistakiAracinKayitliOlduguUlkeAdi;

    @JsonProperty("Çıkıştaki Aracın Kayıtlı Olduğu Ülke Kodu")
    private String cikistakiAracinKayitliOlduguUlkeKodu;

    @JsonProperty("Teslim Şekli Kodu")
    private String teslimSekliKodu;

    @JsonProperty("Kalem Sıra Numarası")
    private String kalemSiraNumarasi;

    @JsonProperty("GTIP KODU")
    private String gtipKodu;

    @JsonProperty("GTIP Açıklaması")
    private String gtipAciklamasi;

    @JsonProperty("Kullanıcı Birim Kıymeti USD Değeri")
    private String kullaniciBirimKiymetiUsdDegeri;

    @JsonProperty("Navlun Tutarı TL Değeri")
    private String navlunTutariTlDegeri;

    @JsonProperty("Menşe Ülke Kodu")
    private String menseUlkeKodu;

    @JsonProperty("Menşe Ülke Adı")
    private String menseUlkeAdi;

    @JsonProperty("Net Ağırlık")
    private String netAgirlik;

    @JsonProperty("Brüt Ağırlık")
    private String brutAgirlik;

    @JsonProperty("İstatistiki Kıymet USD Değeri")
    private String istatistikiKiymetUsdDegeri;

    @JsonProperty("Hesaplanmış Kalem Kıymeti USD Değeri")
    private String hesaplanmisKalemKiymetiUsdDegeri;

    @JsonProperty("Ölçü Birimi")
    private String olcuBirimi;

    @JsonProperty("Ölçü Birimi Kodu")
    private String olcuBirimiKodu;

    @JsonProperty("Ölçü Birimi Açıklaması")
    private String olcuBirimiAciklamasi;

    @JsonProperty("İstatistiki Birim Kodu")
    private String istatistikiBirimKodu;

    @JsonProperty("İstatistiki Birim Açıklaması")
    private String istatistikiBirimAciklamasi;

    @JsonProperty("Eşya Ticari Tanımı")
    private String esyaTicariTanimi;

    @JsonProperty("Yükleme/Bosaltma Yapılan Gümrük İdaresi Adı")
    private String yuklemeBosaltmaYapilanGumrukIdaresiAdi;

    @JsonProperty("Yükleme/Bosaltma Yapılan Gümrük İdaresi Kodu")
    private String yuklemeBosaltmaYapilanGumrukIdaresiKodu;

    public static ExportImportAralikDTO convertToDTO(ExportImportAralik entity) {
        return ExportImportAralikDTO.builder()
                .rowInt(entity.getRowInt())
                .fileName(entity.getFileName())
                .sheetName(entity.getSheetName())
                .tcgbBolgeMudurluguKodu(entity.getTcgbBolgeMudurluguKodu())
                .tcgbBolgeMudurluguAdi(entity.getTcgbBolgeMudurluguAdi())
                .tcgbGumrukIdaresiKodu(entity.getTcgbGumrukIdaresiKodu())
                .tcgbGumrukIdaresiAdi(entity.getTcgbGumrukIdaresiAdi())
                .yuklemeVeyaBosaltmaYapilanGumrukIdaresiKodu(entity.getYuklemeVeyaBosaltmaYapilanGumrukIdaresiKodu())
                .yuklemeVeyaBosaltmaYapilanGumrukIdaresiAdi(entity.getYuklemeVeyaBosaltmaYapilanGumrukIdaresiAdi())
                .gumrukIstatistikTarihi(entity.getGumrukIstatistikTarihi())
                .cikisUlkesiKodu(entity.getCikisUlkesiKodu())
                .cikisUlkesiAdi(entity.getCikisUlkesiAdi())
                .sigortaTutari(entity.getSigortaTutari())
                .gondericiKimlikNo(entity.getGondericiKimlikNo())
                .gondericiAdiUnvani(entity.getGondericiAdiUnvani())
                .aliciKimlikNo(entity.getAliciKimlikNo())
                .aliciAdiUnvani(entity.getAliciAdiUnvani())
                .beyannameNo(entity.getBeyannameNo())
                .sinirdakiAracinTasimaSekliAciklamasi(entity.getSinirdakiAracinTasimaSekliAciklamasi())
                .sinirdakiAracinTasimaSekliKodu(entity.getSinirdakiAracinTasimaSekliKodu())
                .ticaretYapilanUlkeAdi(entity.getTicaretYapilanUlkeAdi())
                .ticaretYapilanUlkeKodu(entity.getTicaretYapilanUlkeKodu())
                .gidecegiUlkeAdi(entity.getGidecegiUlkeAdi())
                .gidecegiUlkeKodu(entity.getGidecegiUlkeKodu())
                .cikistakiAracinKayitliOlduguUlkeAdi(entity.getCikistakiAracinKayitliOlduguUlkeAdi())
                .cikistakiAracinKayitliOlduguUlkeKodu(entity.getCikistakiAracinKayitliOlduguUlkeKodu())
                .teslimSekliKodu(entity.getTeslimSekliKodu())
                .kalemSiraNumarasi(entity.getKalemSiraNumarasi())
                .gtipKodu(entity.getGtipKodu())
                .gtipAciklamasi(entity.getGtipAciklamasi())
                .kullaniciBirimKiymetiUsdDegeri(entity.getKullaniciBirimKiymetiUsdDegeri())
                .navlunTutariTlDegeri(entity.getNavlunTutariTlDegeri())
                .menseUlkeKodu(entity.getMenseUlkeKodu())
                .menseUlkeAdi(entity.getMenseUlkeAdi())
                .netAgirlik(entity.getNetAgirlik())
                .brutAgirlik(entity.getBrutAgirlik())
                .istatistikiKiymetUsdDegeri(entity.getIstatistikiKiymetUsdDegeri())
                .hesaplanmisKalemKiymetiUsdDegeri(entity.getHesaplanmisKalemKiymetiUsdDegeri())
                .olcuBirimi(entity.getOlcuBirimi())
                .olcuBirimiKodu(entity.getOlcuBirimiKodu())
                .olcuBirimiAciklamasi(entity.getOlcuBirimiAciklamasi())
                .istatistikiBirimKodu(entity.getIstatistikiBirimKodu())
                .istatistikiBirimAciklamasi(entity.getIstatistikiBirimAciklamasi())
                .esyaTicariTanimi(entity.getEsyaTicariTanimi())
                .build();
    }

}
