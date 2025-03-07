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
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Row_Int")
    private Integer rowInt;

    @Column(name = "File_Name", length = 255)
    private String fileName;

    @Column(name = "Sheet_Name", length = 255)
    private String sheetName;

    @JsonProperty("TCGB Bölge Müdürlüğü Kodu")
    @Column(name = "TCGB_Bolge_Mudurlugu_Kodu", columnDefinition = "TEXT")
    private String tcgbBolgeMudurluguKodu;

    @JsonProperty("TCGB Bölge Müdürlüğü Adı")
    @Column(name = "TCGB_Bolge_Mudurlugu_Adi", columnDefinition = "TEXT")
    private String tcgbBolgeMudurluguAdi;

    @JsonProperty("TCGB Gümrük İdaresi Kodu")
    @Column(name = "TCGB_Gumruk_Idaresi_Kodu", columnDefinition = "TEXT")
    private String tcgbGumrukIdaresiKodu;

    @JsonProperty("TCGB Gümrük İdaresi Adı")
    @Column(name = "TCGB_Gumruk_Idaresi_Adi", columnDefinition = "TEXT")
    private String tcgbGumrukIdaresiAdi;

    @JsonProperty("Yükleme/Boşaltma Yapılan Gümrük İdaresi Kodu")
    @Column(name = "Yukleme_Bosaltma_Yapilan_Gumruk_Idaresi_Kodu", columnDefinition = "TEXT")
    private String yuklemeVeyaBosaltmaYapilanGumrukIdaresiKodu;

    @JsonProperty("Yükleme/Boşaltma Yapılan Gümrük İdaresi Adı")
    @Column(name = "Yukleme_Bosaltma_Yapilan_Gumruk_Idaresi_Adi", columnDefinition = "TEXT")
    private String yuklemeVeyaBosaltmaYapilanGumrukIdaresiAdi;

    @JsonProperty("Gümrük İstatistik Tarihi (Bordro Tarihi)")
    @Column(name = "Gumruk_Istatistik_Tarihi", columnDefinition = "TEXT")
    private String gumrukIstatistikTarihi;

    @JsonProperty("Çıkış Ülkesi Kodu")
    @Column(name = "Cikis_Ulkesi_Kodu", columnDefinition = "TEXT")
    private String cikisUlkesiKodu;

    @JsonProperty("Çıkış Ülkesi Adı")
    @Column(name = "Cikis_Ulkesi_Adi", columnDefinition = "TEXT")
    private String cikisUlkesiAdi;

    @JsonProperty("Sigorta Tutarı")
    @Column(name = "Sigorta_Tutari", columnDefinition = "TEXT")
    private String sigortaTutari;

    @JsonProperty("Gönderici Kimlik No")
    @Column(name = "Gonderici_Kimlik_No", columnDefinition = "TEXT")
    private String gondericiKimlikNo;

    @JsonProperty("Gönderici Adı Ünvanı")
    @Column(name = "Gonderici_Adi_Unvani", columnDefinition = "TEXT")
    private String gondericiAdiUnvani;

    @JsonProperty("Alıcı Kimlik No")
    @Column(name = "Alici_Kimlik_No", columnDefinition = "TEXT")
    private String aliciKimlikNo;

    @JsonProperty("Alıcı Adı Ünvanı")
    @Column(name = "Alici_Adi_Unvani", columnDefinition = "TEXT")
    private String aliciAdiUnvani;

    @JsonProperty("Beyanname No")
    @Column(name = "Beyanname_No", columnDefinition = "TEXT")
    private String beyannameNo;

    @JsonProperty("Sınırdaki Aracın Taşıma Şekli Açıklaması")
    @Column(name = "Sinirdaki_Aracin_Tasima_Sekli_Aciklamasi", columnDefinition = "TEXT")
    private String sinirdakiAracinTasimaSekliAciklamasi;

    @JsonProperty("Sınırdaki Aracın Taşıma Şekli Kodu")
    @Column(name = "Sinirdaki_Aracin_Tasima_Sekli_Kodu", columnDefinition = "TEXT")
    private String sinirdakiAracinTasimaSekliKodu;

    @JsonProperty("Ticaret Yapılan Ülke Adı")
    @Column(name = "Ticaret_Yapilan_Ulke_Adi", columnDefinition = "TEXT")
    private String ticaretYapilanUlkeAdi;

    @JsonProperty("Ticaret Yapılan Ülke Kodu")
    @Column(name = "Ticaret_Yapilan_Ulke_Kodu", columnDefinition = "TEXT")
    private String ticaretYapilanUlkeKodu;

    @JsonProperty("Gideceği Ülke Adı")
    @Column(name = "Gidecegi_Ulke_Adi", columnDefinition = "TEXT")
    private String gidecegiUlkeAdi;

    @JsonProperty("Gideceği Ülke Kodu")
    @Column(name = "Gidecegi_Ulke_Kodu", columnDefinition = "TEXT")
    private String gidecegiUlkeKodu;

    @JsonProperty("Çıkıştaki Aracın Kayıtlı Olduğu Ülke Adı")
    @Column(name = "Cikistaki_Aracin_Kayitli_Oldugu_Ulke_Adi", columnDefinition = "TEXT")
    private String cikistakiAracinKayitliOlduguUlkeAdi;

    @JsonProperty("Çıkıştaki Aracın Kayıtlı Olduğu Ülke Kodu")
    @Column(name = "Cikistaki_Aracin_Kayitli_Oldugu_Ulke_Kodu", columnDefinition = "TEXT")
    private String cikistakiAracinKayitliOlduguUlkeKodu;

    @JsonProperty("Teslim Şekli Kodu")
    @Column(name = "Teslim_Sekli_Kodu", columnDefinition = "TEXT")
    private String teslimSekliKodu;

    @JsonProperty("Kalem Sıra Numarası")
    @Column(name = "Kalem_Sira_Numarasi", columnDefinition = "TEXT")
    private String kalemSiraNumarasi;

    @JsonProperty("GTIP KODU")
    @Column(name = "GTIP_Kodu", columnDefinition = "TEXT")
    private String gtipKodu;

    @JsonProperty("GTIP Açıklaması")
    @Column(name = "GTIP_Aciklamasi", columnDefinition = "TEXT")
    private String gtipAciklamasi;

    @JsonProperty("Kullanıcı Birim Kıymeti USD Değeri")
    @Column(name = "Kullanici_Birim_Kiymeti_USD_Degeri", columnDefinition = "TEXT")
    private String kullaniciBirimKiymetiUsdDegeri;

    @JsonProperty("Navlun Tutarı TL Değeri")
    @Column(name = "Navlun_Tutari_TL_Degeri", columnDefinition = "TEXT")
    private String navlunTutariTlDegeri;

    @JsonProperty("Menşe Ülke Kodu")
    @Column(name = "Mense_Ulke_Kodu", columnDefinition = "TEXT")
    private String menseUlkeKodu;

    @JsonProperty("Menşe Ülke Adı")
    @Column(name = "Mense_Ulke_Adi", columnDefinition = "TEXT")
    private String menseUlkeAdi;

    @JsonProperty("Net Ağırlık")
    @Column(name = "Net_Agirlik", columnDefinition = "TEXT")
    private String netAgirlik;

    @JsonProperty("Brüt Ağırlık")
    @Column(name = "Brut_Agirlik", columnDefinition = "TEXT")
    private String brutAgirlik;

    @JsonProperty("İstatistiki Kıymet USD Değeri")
    @Column(name = "Istatistiki_Kiymet_USD_Degeri", columnDefinition = "TEXT")
    private String istatistikiKiymetUsdDegeri;

    @JsonProperty("Hesaplanmış Kalem Kıymeti USD Değeri")
    @Column(name = "Hesaplanmis_Kalem_Kiymeti_USD_Degeri", columnDefinition = "TEXT")
    private String hesaplanmisKalemKiymetiUsdDegeri;

    @JsonProperty("Ölçü Birimi")
    @Column(name = "Olcu_Birimi", columnDefinition = "TEXT")
    private String olcuBirimi;

    @JsonProperty("Ölçü Birimi Kodu")
    @Column(name = "Olcu_Birimi_Kodu", columnDefinition = "TEXT")
    private String olcuBirimiKodu;

    @JsonProperty("Ölçü Birimi Açıklaması")
    @Column(name = "Olcu_Birimi_Aciklamasi", columnDefinition = "TEXT")
    private String olcuBirimiAciklamasi;

    @JsonProperty("İstatistiki Birim Kodu")
    @Column(name = "Istatistiki_Birim_Kodu", columnDefinition = "TEXT")
    private String istatistikiBirimKodu;

    @JsonProperty("İstatistiki Birim Açıklaması")
    @Column(name = "Istatistiki_Birim_Aciklamasi", columnDefinition = "TEXT")
    private String istatistikiBirimAciklamasi;

    @JsonProperty("Eşya Ticari Tanımı")
    @Column(name = "Esya_Ticari_Tanimi", columnDefinition = "TEXT")
    private String esyaTicariTanimi;

}