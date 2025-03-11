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
@Table(name = "export_import_others") // Assuming the table name is export_import_others
public class ExportImportOther implements Serializable {
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

    @JsonProperty("TCGB Gümrük İdaresi Kodu") // Use Unicode for non-breaking space
    @Column(name = "TCGB Gümrük İdaresi Kodu", length = 2550)
    private String tcgbGumrukIdaresiKodu;

    @JsonProperty("TCGB Gümrük İdaresi Adı")
    @Column(name = "TCGB Gümrük İdaresi Adı", length = 2550)
    private String tcgbGumrukIdaresiAdi;

    @JsonProperty("TCGB Tescil No")
    @Column(name = "TCGB Tescil No", length = 2550)
    private String tcgbTescilNo;

    @JsonProperty("TCGB Tescil Tarihi")
    @Column(name = "TCGB Tescil Tarihi", length = 2550)
    private String tcgbTescilTarihi;

    @JsonProperty("TCGB Kapanış Tarihi")
    @Column(name = "TCGB Kapanış Tarihi", length = 2550)
    private String tcgbKapanisTarihi;

    @JsonProperty("Gönderici / Alıcı Vergi No")
    @Column(name = "Gönderici / Alıcı Vergi No", length = 2550)
    private String gondericiAliciVergiNo;

    @JsonProperty("Gönderici/Alıcı Adı")
    @Column(name = "Gönderici/Alıcı Adı", length = 2550)
    private String gondericiAliciAdi;

    @JsonProperty("Alıcı Adı")
    @Column(name = "Alıcı Adı", length = 2550)
    private String aliciAdi;

    @JsonProperty("Gideceği Ülke (17) Kodu")
    @Column(name = "Gideceği Ülke (17) Kodu", length = 2550)
    private String gidecegiUlkeKodu;

    @JsonProperty("Gideceği Ülke (17) Adı")
    @Column(name = "Gideceği Ülke (17) Adı", length = 2550)
    private String gidecegiUlkeAdi;

    @JsonProperty("Menşe Ülke Kodu")
    @Column(name = "Menşe Ülke Kodu", length = 2550)
    private String menseUlkeKodu;

    @JsonProperty("Menşe Ülke Adı")
    @Column(name = "Menşe Ülke Adı", length = 2550)
    private String menseUlkeAdi;

    @JsonProperty("Teslim Şekli Kodu")
    @Column(name = "Teslim Şekli Kodu", length = 2550)
    private String teslimSekliKodu;

    @JsonProperty("Kalem Sıra No")
    @Column(name = "Kalem Sıra No", length = 2550)
    private String kalemSiraNo;

    @JsonProperty("Kalem Rejim Kodu")
    @Column(name = "Kalem Rejim Kodu", length = 2550)
    private String kalemRejimKodu;

    @JsonProperty("Kalem Rejim Açıklaması")
    @Column(name = "Kalem Rejim Açıklaması", length = 2550)
    private String kalemRejimAciklamasi;

    @JsonProperty("Kalem Rejim Açıklması")
    @Column(name = "Kalem Rejim Açıklması", length = 2550)
    private String kalemRejimAciklmasi;

    @JsonProperty("GTIP Kodu")
    @Column(name = "GTIP Kodu", length = 2550)
    private String gtipKodu;

    @JsonProperty("GTIP Açıklaması")
    @Column(name = "GTIP Açıklaması", length = 2550)
    private String gtipAciklamasi;

    @JsonProperty("31. Ticari Tanımı")
    @Column(name = "31. Ticari Tanımı", length = 2550)
    private String ticariTanimi;

    @JsonProperty("TCGB Statü Açıklaması")
    @Column(name = "TCGB Statü Açıklaması", length = 2550)
    private String tcgbStatuAciklamasi;

    @JsonProperty("Fatura Tutarı")
    @Column(name = "Fatura Tutarı", length = 2550)
    private String faturaTutari;

    @JsonProperty("Fatura Tutarı Döviz Türü Kodu")
    @Column(name = "Fatura Tutarı Döviz Türü Kodu", length = 2550)
    private String faturaTutariDovizTuruKodu;

    @JsonProperty("Ölçü (Eşya) Miktarı")
    @Column(name = "Ölçü (Eşya) Miktarı", length = 2550)
    private String olcuEsyaMiktari;

    @JsonProperty("Ölçü Birimi Açıklaması")
    @Column(name = "Ölçü Birimi Açıklaması", length = 2550)
    private String olcuBirimiAciklamasi;

    @JsonProperty("Net Ağırlık (Kg)")
    @Column(name = "Net Ağırlık (Kg)", length = 2550)
    private String netAgirlikKg;

    @JsonProperty("Hesaplanmış Kalem Kıymeti USD Değeri")
    @Column(name = "Hesaplanmış Kalem Kıymeti USD Değeri", length = 2550)
    private String hesaplanmisKalemKiymetiUsdDegeri;

    @JsonProperty("İstatistiki Kıymet USD Değeri")
    @Column(name = "İstatistiki Kıymet USD Değeri", length = 2550)
    private String istatistikiKiymetUsdDegeri;

    // Constructors, Getters, and Setters...
}