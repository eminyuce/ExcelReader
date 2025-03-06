package com.excel.reader.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Table(name = "export_import_others") // Assuming the table name is export_import_others
public class ExportImportOther {

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

    @JsonProperty("TCGB Gümrük İdaresi Kodu") // Use Unicode for non-breaking space
    @Column(name = "TCGB Gümrük İdaresi Kodu", columnDefinition = "TEXT")
    private String tcgbGumrukIdaresiKodu;

    @JsonProperty("TCGB Gümrük İdaresi Adı")
    @Column(name = "TCGB Gümrük İdaresi Adı", columnDefinition = "TEXT")
    private String tcgbGumrukIdaresiAdi;

    @JsonProperty("TCGB Tescil No")
    @Column(name = "TCGB Tescil No", columnDefinition = "TEXT")
    private String tcgbTescilNo;

    @JsonProperty("TCGB Tescil Tarihi")
    @Column(name = "TCGB Tescil Tarihi", columnDefinition = "TEXT")
    private String tcgbTescilTarihi;

    @JsonProperty("TCGB Kapanış Tarihi")
    @Column(name = "TCGB Kapanış Tarihi", columnDefinition = "TEXT")
    private String tcgbKapanisTarihi;

    @JsonProperty("Gönderici / Alıcı Vergi No")
    @Column(name = "Gönderici / Alıcı Vergi No", columnDefinition = "TEXT")
    private String gondericiAliciVergiNo;

    @JsonProperty("Gönderici/Alıcı Adı")
    @Column(name = "Gönderici/Alıcı Adı", columnDefinition = "TEXT")
    private String gondericiAliciAdi;

    @JsonProperty("Alıcı Adı")
    @Column(name = "Alıcı Adı", columnDefinition = "TEXT")
    private String aliciAdi;

    @JsonProperty("Gideceği Ülke (17) Kodu")
    @Column(name = "Gideceği Ülke (17) Kodu", columnDefinition = "TEXT")
    private String gidecegiUlkeKodu;

    @JsonProperty("Gideceği Ülke (17) Adı")
    @Column(name = "Gideceği Ülke (17) Adı", columnDefinition = "TEXT")
    private String gidecegiUlkeAdi;

    @JsonProperty("Menşe Ülke Kodu")
    @Column(name = "Menşe Ülke Kodu", columnDefinition = "TEXT")
    private String menseUlkeKodu;

    @JsonProperty("Menşe Ülke Adı")
    @Column(name = "Menşe Ülke Adı", columnDefinition = "TEXT")
    private String menseUlkeAdi;

    @JsonProperty("Teslim Şekli Kodu")
    @Column(name = "Teslim Şekli Kodu", columnDefinition = "TEXT")
    private String teslimSekliKodu;

    @JsonProperty("Kalem Sıra No")
    @Column(name = "Kalem Sıra No", columnDefinition = "TEXT")
    private String kalemSiraNo;

    @JsonProperty("Kalem Rejim Kodu")
    @Column(name = "Kalem Rejim Kodu", columnDefinition = "TEXT")
    private String kalemRejimKodu;

    @JsonProperty("Kalem Rejim Açıklaması")
    @Column(name = "Kalem Rejim Açıklaması", columnDefinition = "TEXT")
    private String kalemRejimAciklamasi;

    @JsonProperty("Kalem Rejim Açıklması")
    @Column(name = "Kalem Rejim Açıklması", columnDefinition = "TEXT")
    private String kalemRejimAciklmasi;

    @JsonProperty("GTIP Kodu")
    @Column(name = "GTIP Kodu", columnDefinition = "TEXT")
    private String gtipKodu;

    @JsonProperty("GTIP Açıklaması")
    @Column(name = "GTIP Açıklaması", columnDefinition = "TEXT")
    private String gtipAciklamasi;

    @JsonProperty("31. Ticari Tanımı")
    @Column(name = "31. Ticari Tanımı", columnDefinition = "TEXT")
    private String ticariTanimi;

    @JsonProperty("TCGB Statü Açıklaması")
    @Column(name = "TCGB Statü Açıklaması", columnDefinition = "TEXT")
    private String tcgbStatuAciklamasi;

    @JsonProperty("Fatura Tutarı")
    @Column(name = "Fatura Tutarı", columnDefinition = "TEXT")
    private String faturaTutari;

    @JsonProperty("Fatura Tutarı Döviz Türü Kodu")
    @Column(name = "Fatura Tutarı Döviz Türü Kodu", columnDefinition = "TEXT")
    private String faturaTutariDovizTuruKodu;

    @JsonProperty("Ölçü (Eşya) Miktarı")
    @Column(name = "Ölçü (Eşya) Miktarı", columnDefinition = "TEXT")
    private String olcuEsyaMiktari;

    @JsonProperty("Ölçü Birimi Açıklaması")
    @Column(name = "Ölçü Birimi Açıklaması", columnDefinition = "TEXT")
    private String olcuBirimiAciklamasi;

    @JsonProperty("Net Ağırlık (Kg)")
    @Column(name = "Net Ağırlık (Kg)", columnDefinition = "TEXT")
    private String netAgirlikKg;

    @JsonProperty("Hesaplanmış Kalem Kıymeti USD Değeri")
    @Column(name = "Hesaplanmış Kalem Kıymeti USD Değeri", columnDefinition = "TEXT")
    private String hesaplanmisKalemKiymetiUsdDegeri;

    @JsonProperty("İstatistiki Kıymet USD Değeri")
    @Column(name = "İstatistiki Kıymet USD Değeri", columnDefinition = "TEXT")
    private String istatistikiKiymetUsdDegeri;

    // Constructors, Getters, and Setters...
}