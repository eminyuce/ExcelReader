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
@Table(name = "import_others")
public class ImportOther implements Serializable {
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

    @JsonProperty("TCGB Gümrük İdaresi Kodu")
    @Column(name = "TCGB_Gumruk_Idaresi_Kodu", length = 2550)
    private String tcgbGumrukIdaresiKodu;

    @JsonProperty("TCGB Gümrük İdaresi Adı")
    @Column(name = "TCGB_Gumruk_Idaresi_Adi", length = 2550)
    private String tcgbGumrukIdaresiAdi;

    @JsonProperty("TCGB Tescil No")
    @Column(name = "TCGB_Tescil_No", length = 2550)
    private String tcgbTescilNo;

    @JsonProperty("TCGB Tescil Tarihi")
    @Column(name = "TCGB_Tescil_Tarihi", length = 2550)
    private String tcgbTescilTarihi;

    @JsonProperty("TCGB Kapanış Tarihi")
    @Column(name = "TCGB_Kapanis_Tarihi", length = 2550)
    private String tcgbKapanisTarihi;

    @JsonProperty("Gönderici / Alıcı Vergi No")
    @Column(name = "Gonderici_Alici_Vergi_No", length = 2550)
    private String gondericiAliciVergiNo;

    @JsonProperty("Gönderici / Alıcı Adı")
    @Column(name = "Gonderici_Alici_Adi", length = 2550)
    private String gondericiAliciAdi;

    @JsonProperty("Gönderen Adı")
    @Column(name = "Gonderen_Adi", length = 2550)
    private String gonderenAdi;

    @JsonProperty("Çıkış Ülkesi Kodu")
    @Column(name = "Cikis_Ulkesi_Kodu", length = 2550)
    private String cikisUlkesiKodu;

    @JsonProperty("Çıkış Ülkesi Adı")
    @Column(name = "Cikis_Ulkesi_Adi", length = 2550)
    private String cikisUlkesiAdi;

    @JsonProperty("Menşe Ülke Kodu")
    @Column(name = "Mense_Ulke_Kodu", length = 2550)
    private String menseUlkeKodu;

    @JsonProperty("Menşe Ülke Adı")
    @Column(name = "Mense_Ulke_Adi", length = 2550)
    private String menseUlkeAdi;

    @JsonProperty("Teslim Şekli Kodu")
    @Column(name = "Teslim_Sekli_Kodu", length = 2550)
    private String teslimSekliKodu;

    @JsonProperty("Kalem Sıra No")
    @Column(name = "Kalem_Sira_No", length = 2550)
    private String kalemSiraNo;

    @JsonProperty("Kalem Rejim Kodu")
    @Column(name = "Kalem_Rejim_Kodu", length = 2550)
    private String kalemRejimKodu;

    @JsonProperty("Kalem Rejim Açıklması")
    @Column(name = "Kalem_Rejim_Aciklamasi", length = 2550)
    private String kalemRejimAciklamasi;

    @JsonProperty("GTIP Kodu")
    @Column(name = "GTIP_Kodu", length = 2550)
    private String gtipKodu;

    @JsonProperty("GTIP Açıklaması")
    @Column(name = "GTIP_Aciklamasi", length = 2550)
    private String gtipAciklamasi;

    @JsonProperty("31. Ticari Tanımı")
    @Column(name = "Ticari_Tanimi_31", length = 2550)
    private String ticariTanimi31;

    @JsonProperty("Fatura Tutarı")
    @Column(name = "Fatura_Tutari", length = 2550)
    private String faturaTutari;

    @JsonProperty("Fatura Tutarı Döviz Türü Kodu")
    @Column(name = "Fatura_Tutari_Doviz_Turu_Kodu", length = 2550)
    private String faturaTutariDovizTuruKodu;

    @JsonProperty("Ölçü (Eşya) Miktarı")
    @Column(name = "Olcu_Esya_Miktari", length = 2550)
    private String olcuEsyaMiktari;

    @JsonProperty("Ölçü Birimi Açıklaması")
    @Column(name = "Olcu_Birimi_Aciklamasi", length = 2550)
    private String olcuBirimiAciklamasi;

    @JsonProperty("Net Ağırlık (Kg)")
    @Column(name = "Net_Agirlik_Kg", length = 2550)
    private String netAgirlikKg;

    @JsonProperty("Hesaplanmış Kalem Kıymeti USD Değeri")
    @Column(name = "Hesaplanmis_Kalem_Kiymeti_USD_Degeri", length = 2550)
    private String hesaplanmisKalemKiymetiUsdDegeri;

    @JsonProperty("İstatistiki Kıymet USD Değeri")
    @Column(name = "Istatistiki_Kiymet_USD_Degeri", length = 2550)
    private String istatistikiKiymetUsdDegeri;
}