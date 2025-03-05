package com.excel.reader.repo;

import com.excel.reader.entities.EkimIhr2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EkimIhr2Repository extends JpaRepository<EkimIhr2, Integer> {

    @Query(value ="SELECT TOP (1) [rowNumber] FROM [dbo].[2023-eki̇m-i̇hr-2] order by [rowNumber] desc"
            ,nativeQuery = true)
    Integer findLastRowNumber();

    @Modifying
    @Transactional
    @Query(value = "EXEC saveEkimIhr2 :rowNumber, :tcgbGumrukIdaresiKodu, :tcgbGumrukIdaresiAdi, :tcgbTescilNo, :tcgbTescilTarihi, :tcgbKapanisTarihi, " +
            ":gondericiAliciVergiNo, :gondericiAliciAdi, :aliciAdi, :gidecegiUlke17Kodu, :gidecegiUlke17Adi, " +
            ":menseUlkeKodu, :menseUlkeAdi, :teslimSekliKodu, :kalemSiraNo, :kalemRejimKodu, :kalemRejimAciklamasi, " +
            ":gtipKodu, :gtipAciklamasi, :ticariTanimi31, :tcgbStatuAciklamasi, :faturaTutari, :faturaTutariDovizTuruKodu, " +
            ":olcuEsyaMiktari, :olcuBirimiAciklamasi, :netAgirlikKg, :hesaplanmisKalemKiymetiUsdDegeri, :istatistikiKiymetUsdDegeri",
            nativeQuery = true)
    void saveEkimIhr2(
            @Param("rowNumber") Integer rowNumber,
            @Param("tcgbGumrukIdaresiKodu") String tcgbGumrukIdaresiKodu,
            @Param("tcgbGumrukIdaresiAdi") String tcgbGumrukIdaresiAdi,
            @Param("tcgbTescilNo") String tcgbTescilNo,
            @Param("tcgbTescilTarihi") String tcgbTescilTarihi,
            @Param("tcgbKapanisTarihi") String tcgbKapanisTarihi,
            @Param("gondericiAliciVergiNo") String gondericiAliciVergiNo,
            @Param("gondericiAliciAdi") String gondericiAliciAdi,
            @Param("aliciAdi") String aliciAdi,
            @Param("gidecegiUlke17Kodu") String gidecegiUlke17Kodu,
            @Param("gidecegiUlke17Adi") String gidecegiUlke17Adi,
            @Param("menseUlkeKodu") String menseUlkeKodu,
            @Param("menseUlkeAdi") String menseUlkeAdi,
            @Param("teslimSekliKodu") String teslimSekliKodu,
            @Param("kalemSiraNo") String kalemSiraNo,
            @Param("kalemRejimKodu") String kalemRejimKodu,
            @Param("kalemRejimAciklamasi") String kalemRejimAciklamasi,
            @Param("gtipKodu") String gtipKodu,
            @Param("gtipAciklamasi") String gtipAciklamasi,
            @Param("ticariTanimi31") String ticariTanimi31,
            @Param("tcgbStatuAciklamasi") String tcgbStatuAciklamasi,
            @Param("faturaTutari") String faturaTutari,
            @Param("faturaTutariDovizTuruKodu") String faturaTutariDovizTuruKodu,
            @Param("olcuEsyaMiktari") String olcuEsyaMiktari,
            @Param("olcuBirimiAciklamasi") String olcuBirimiAciklamasi,
            @Param("netAgirlikKg") String netAgirlikKg,
            @Param("hesaplanmisKalemKiymetiUsdDegeri") String hesaplanmisKalemKiymetiUsdDegeri,
            @Param("istatistikiKiymetUsdDegeri") String istatistikiKiymetUsdDegeri
    );


}
