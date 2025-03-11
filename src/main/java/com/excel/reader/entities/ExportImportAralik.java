package com.excel.reader.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Table(name = "export_import_aralik")
public class ExportImportAralik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Row_Int")
    private Integer rowInt;

    @Column(name = "File_Name")
    private String fileName;

    @Column(name = "Sheet_Name")
    private String sheetName;

    @JsonProperty("TCGB Bölge Müdürlüğü Kodu")
    @Column(name = "TCGB_Bolge_Mudurlugu_Kodu", length = 2550)
    private String tcgbBolgeMudurluguKodu;

    @JsonProperty("TCGB Bölge Müdürlüğü Adı")
    @Column(name = "TCGB_Bolge_Mudurlugu_Adi", length = 2550)
    private String tcgbBolgeMudurluguAdi;

    @JsonProperty("TCGB Gümrük İdaresi Kodu")
    @Column(name = "TCGB_Gumruk_Idaresi_Kodu", length = 2550)
    private String tcgbGumrukIdaresiKodu;

    @JsonProperty("TCGB Gümrük İdaresi Adı")
    @Column(name = "TCGB_Gumruk_Idaresi_Adi", length = 2550)
    private String tcgbGumrukIdaresiAdi;

    @JsonProperty("Yükleme/Boşaltma Yapılan Gümrük İdaresi Kodu")
    @Column(name = "Yukleme_Bosaltma_Yapilan_Gumruk_Idaresi_Kodu", length = 2550)
    private String yuklemeVeyaBosaltmaYapilanGumrukIdaresiKodu;

    @JsonProperty("Yükleme/Boşaltma Yapılan Gümrük İdaresi Adı")
    @Column(name = "Yukleme_Bosaltma_Yapilan_Gumruk_Idaresi_Adi", length = 2550)
    private String yuklemeVeyaBosaltmaYapilanGumrukIdaresiAdi;

    @JsonProperty("Gümrük İstatistik Tarihi (Bordro Tarihi)")
    @Column(name = "Gumruk_Istatistik_Tarihi", length = 2550)
    private String gumrukIstatistikTarihi;

    @JsonProperty("Çıkış Ülkesi Kodu")
    @Column(name = "Cikis_Ulkesi_Kodu", length = 2550)
    private String cikisUlkesiKodu;

    @JsonProperty("Çıkış Ülkesi Adı")
    @Column(name = "Cikis_Ulkesi_Adi", length = 2550)
    private String cikisUlkesiAdi;

    @JsonProperty("Sigorta Tutarı")
    @Column(name = "Sigorta_Tutari", length = 2550)
    private String sigortaTutari;

    @JsonProperty("Gönderici Kimlik No")
    @Column(name = "Gonderici_Kimlik_No", length = 2550)
    private String gondericiKimlikNo;

    @JsonProperty("Gönderici Adı Ünvanı")
    @Column(name = "Gonderici_Adi_Unvani", length = 2550)
    private String gondericiAdiUnvani;

    @JsonProperty("Alıcı Kimlik No")
    @Column(name = "Alici_Kimlik_No", length = 2550)
    private String aliciKimlikNo;

    @JsonProperty("Alıcı Adı Ünvanı")
    @Column(name = "Alici_Adi_Unvani", length = 2550)
    private String aliciAdiUnvani;

    @JsonProperty("Beyanname No")
    @Column(name = "Beyanname_No", length = 2550)
    private String beyannameNo;

    @JsonProperty("Sınırdaki Aracın Taşıma Şekli Açıklaması")
    @Column(name = "Sinirdaki_Aracin_Tasima_Sekli_Aciklamasi", length = 2550)
    private String sinirdakiAracinTasimaSekliAciklamasi;

    @JsonProperty("Sınırdaki Aracın Taşıma Şekli Kodu")
    @Column(name = "Sinirdaki_Aracin_Tasima_Sekli_Kodu", length = 2550)
    private String sinirdakiAracinTasimaSekliKodu;

    @JsonProperty("Ticaret Yapılan Ülke Adı")
    @Column(name = "Ticaret_Yapilan_Ulke_Adi", length = 2550)
    private String ticaretYapilanUlkeAdi;

    @JsonProperty("Ticaret Yapılan Ülke Kodu")
    @Column(name = "Ticaret_Yapilan_Ulke_Kodu", length = 2550)
    private String ticaretYapilanUlkeKodu;

    @JsonProperty("Gideceği Ülke Adı")
    @Column(name = "Gidecegi_Ulke_Adi", length = 2550)
    private String gidecegiUlkeAdi;

    @JsonProperty("Gideceği Ülke Kodu")
    @Column(name = "Gidecegi_Ulke_Kodu", length = 2550)
    private String gidecegiUlkeKodu;

    @JsonProperty("Çıkıştaki Aracın Kayıtlı Olduğu Ülke Adı")
    @Column(name = "Cikistaki_Aracin_Kayitli_Oldugu_Ulke_Adi", length = 2550)
    private String cikistakiAracinKayitliOlduguUlkeAdi;

    @JsonProperty("Çıkıştaki Aracın Kayıtlı Olduğu Ülke Kodu")
    @Column(name = "Cikistaki_Aracin_Kayitli_Oldugu_Ulke_Kodu", length = 2550)
    private String cikistakiAracinKayitliOlduguUlkeKodu;

    @JsonProperty("Teslim Şekli Kodu")
    @Column(name = "Teslim_Sekli_Kodu", length = 2550)
    private String teslimSekliKodu;

    @JsonProperty("Kalem Sıra Numarası")
    @Column(name = "Kalem_Sira_Numarasi", length = 2550)
    private String kalemSiraNumarasi;

    @JsonProperty("GTIP KODU")
    @Column(name = "GTIP_Kodu", length = 2550)
    private String gtipKodu;

    @JsonProperty("GTIP Açıklaması")
    @Column(name = "GTIP_Aciklamasi", length = 2550)
    private String gtipAciklamasi;

    @JsonProperty("Kullanıcı Birim Kıymeti USD Değeri")
    @Column(name = "Kullanici_Birim_Kiymeti_USD_Degeri", length = 2550)
    private String kullaniciBirimKiymetiUsdDegeri;

    @JsonProperty("Navlun Tutarı TL Değeri")
    @Column(name = "Navlun_Tutari_TL_Degeri", length = 2550)
    private String navlunTutariTlDegeri;

    @JsonProperty("Menşe Ülke Kodu")
    @Column(name = "Mense_Ulke_Kodu", length = 2550)
    private String menseUlkeKodu;

    @JsonProperty("Menşe Ülke Adı")
    @Column(name = "Mense_Ulke_Adi", length = 2550)
    private String menseUlkeAdi;

    @JsonProperty("Net Ağırlık")
    @Column(name = "Net_Agirlik", length = 2550)
    private String netAgirlik;

    @JsonProperty("Brüt Ağırlık")
    @Column(name = "Brut_Agirlik", length = 2550)
    private String brutAgirlik;

    @JsonProperty("İstatistiki Kıymet USD Değeri")
    @Column(name = "Istatistiki_Kiymet_USD_Degeri", length = 2550)
    private String istatistikiKiymetUsdDegeri;

    @JsonProperty("Hesaplanmış Kalem Kıymeti USD Değeri")
    @Column(name = "Hesaplanmis_Kalem_Kiymeti_USD_Degeri", length = 2550)
    private String hesaplanmisKalemKiymetiUsdDegeri;

    @JsonProperty("Ölçü Birimi")
    @Column(name = "Olcu_Birimi", length = 2550)
    private String olcuBirimi;

    @JsonProperty("Ölçü Birimi Kodu")
    @Column(name = "Olcu_Birimi_Kodu", length = 2550)
    private String olcuBirimiKodu;

    @JsonProperty("Ölçü Birimi Açıklaması")
    @Column(name = "Olcu_Birimi_Aciklamasi", length = 2550)
    private String olcuBirimiAciklamasi;

    @JsonProperty("İstatistiki Birim Kodu")
    @Column(name = "Istatistiki_Birim_Kodu", length = 2550)
    private String istatistikiBirimKodu;

    @JsonProperty("İstatistiki Birim Açıklaması")
    @Column(name = "Istatistiki_Birim_Aciklamasi", length = 2550)
    private String istatistikiBirimAciklamasi;

    @JsonProperty("Eşya Ticari Tanımı")
    @Column(name = "Esya_Ticari_Tanimi", length = 2550)
    private String esyaTicariTanimi;

}