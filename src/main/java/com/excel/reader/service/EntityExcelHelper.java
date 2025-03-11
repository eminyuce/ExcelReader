package com.excel.reader.service;

import com.excel.reader.entities.ExportImportAralik;
import com.excel.reader.entities.ExportImportOther;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class EntityExcelHelper {

    public ByteArrayInputStream createExportImportAralikWorkbook(List<ExportImportAralik> exportTransactionsList) throws IOException {
        if (CollectionUtils.isEmpty(exportTransactionsList)) {
            return null;
        }

        System.setProperty("javax.xml.transform.TransformerFactory",
                "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");

        String sheetName = "ExportImportAralik";

        // Sheet headers based on ExportImportAralik fields
        String[] headers = {
                "ID", "Row Int", "File Name", "Sheet Name",
                "TCGB Bölge Müdürlüğü Kodu", "TCGB Bölge Müdürlüğü Adı",
                "TCGB Gümrük İdaresi Kodu", "TCGB Gümrük İdaresi Adı",
                "Yükleme/Boşaltma Gümrük İdaresi Kodu", "Yükleme/Boşaltma Gümrük İdaresi Adı",
                "Gümrük İstatistik Tarihi", "Çıkış Ülkesi Kodu", "Çıkış Ülkesi Adı",
                "Sigorta Tutarı", "Gönderici Kimlik No", "Gönderici Adı Ünvanı",
                "Alıcı Kimlik No", "Alıcı Adı Ünvanı", "Beyanname No",
                "Sınırdaki Aracın Taşıma Şekli Açıklaması", "Sınırdaki Aracın Taşıma Şekli Kodu",
                "Ticaret Yapılan Ülke Adı", "Ticaret Yapılan Ülke Kodu",
                "Gideceği Ülke Adı", "Gideceği Ülke Kodu",
                "Çıkıştaki Aracın Kayıtlı Ülke Adı", "Çıkıştaki Aracın Kayıtlı Ülke Kodu",
                "Teslim Şekli Kodu", "Kalem Sıra Numarası", "GTIP Kodu", "GTIP Açıklaması",
                "Kullanıcı Birim Kıymeti USD", "Navlun Tutarı TL",
                "Menşe Ülke Kodu", "Menşe Ülke Adı", "Net Ağırlık", "Brüt Ağırlık",
                "İstatistiki Kıymet USD", "Hesaplanmış Kalem Kıymeti USD",
                "Ölçü Birimi", "Ölçü Birimi Kodu", "Ölçü Birimi Açıklaması",
                "İstatistiki Birim Kodu", "İstatistiki Birim Açıklaması", "Eşya Ticari Tanımı"
        };

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            XSSFSheet sheet = workbook.createSheet(sheetName);

            // Header styling
            XSSFFont hdrFont = workbook.createFont();
            hdrFont.setBold(true);
            hdrFont.setColor(IndexedColors.WHITE.getIndex());

            XSSFColor hdrColor = new XSSFColor(new java.awt.Color(0x4F, 0x81, 0xBD), null);
            XSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setFont(hdrFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setFillForegroundColor(hdrColor);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Row styles
            XSSFCellStyle whiteStyle = workbook.createCellStyle();
            whiteStyle.setBorderTop(BorderStyle.THIN);
            whiteStyle.setBorderBottom(BorderStyle.THIN);
            whiteStyle.setBorderLeft(BorderStyle.THIN);
            whiteStyle.setBorderRight(BorderStyle.THIN);
            whiteStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            whiteStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            XSSFCellStyle altStyle = workbook.createCellStyle();
            altStyle.setBorderTop(BorderStyle.THIN);
            altStyle.setBorderBottom(BorderStyle.THIN);
            altStyle.setBorderLeft(BorderStyle.THIN);
            altStyle.setBorderRight(BorderStyle.THIN);
            XSSFColor lightBlue = new XSSFColor(new java.awt.Color(0xC5, 0xD9, 0xF1), null);
            altStyle.setFillForegroundColor(lightBlue);
            altStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            int rowNum = 0;
            int cellNum = 0;

            // Create header row
            Row row = sheet.createRow(rowNum++);
            for (String header : headers) {
                Cell cell = row.createCell(cellNum++);
                cell.setCellValue(header);
                cell.setCellStyle(headerStyle);
            }

            int numCols = cellNum;

            // Create data rows
            for (ExportImportAralik record : exportTransactionsList) {
                row = sheet.createRow(rowNum++);
                cellNum = 0;
                XSSFCellStyle rowStyle = (rowNum % 2 == 0) ? altStyle : whiteStyle;

                row.createCell(cellNum++).setCellValue(record.getId() != null ? record.getId() : 0);
                row.createCell(cellNum++).setCellValue(record.getRowInt() != null ? record.getRowInt() : 0);
                row.createCell(cellNum++).setCellValue(record.getFileName());
                row.createCell(cellNum++).setCellValue(record.getSheetName());
                row.createCell(cellNum++).setCellValue(record.getTcgbBolgeMudurluguKodu());
                row.createCell(cellNum++).setCellValue(record.getTcgbBolgeMudurluguAdi());
                row.createCell(cellNum++).setCellValue(record.getTcgbGumrukIdaresiKodu());
                row.createCell(cellNum++).setCellValue(record.getTcgbGumrukIdaresiAdi());
                row.createCell(cellNum++).setCellValue(record.getYuklemeVeyaBosaltmaYapilanGumrukIdaresiKodu());
                row.createCell(cellNum++).setCellValue(record.getYuklemeVeyaBosaltmaYapilanGumrukIdaresiAdi());
                row.createCell(cellNum++).setCellValue(record.getGumrukIstatistikTarihi());
                row.createCell(cellNum++).setCellValue(record.getCikisUlkesiKodu());
                row.createCell(cellNum++).setCellValue(record.getCikisUlkesiAdi());
                row.createCell(cellNum++).setCellValue(record.getSigortaTutari());
                row.createCell(cellNum++).setCellValue(record.getGondericiKimlikNo());
                row.createCell(cellNum++).setCellValue(record.getGondericiAdiUnvani());
                row.createCell(cellNum++).setCellValue(record.getAliciKimlikNo());
                row.createCell(cellNum++).setCellValue(record.getAliciAdiUnvani());
                row.createCell(cellNum++).setCellValue(record.getBeyannameNo());
                row.createCell(cellNum++).setCellValue(record.getSinirdakiAracinTasimaSekliAciklamasi());
                row.createCell(cellNum++).setCellValue(record.getSinirdakiAracinTasimaSekliKodu());
                row.createCell(cellNum++).setCellValue(record.getTicaretYapilanUlkeAdi());
                row.createCell(cellNum++).setCellValue(record.getTicaretYapilanUlkeKodu());
                row.createCell(cellNum++).setCellValue(record.getGidecegiUlkeAdi());
                row.createCell(cellNum++).setCellValue(record.getGidecegiUlkeKodu());
                row.createCell(cellNum++).setCellValue(record.getCikistakiAracinKayitliOlduguUlkeAdi());
                row.createCell(cellNum++).setCellValue(record.getCikistakiAracinKayitliOlduguUlkeKodu());
                row.createCell(cellNum++).setCellValue(record.getTeslimSekliKodu());
                row.createCell(cellNum++).setCellValue(record.getKalemSiraNumarasi());
                row.createCell(cellNum++).setCellValue(record.getGtipKodu());
                row.createCell(cellNum++).setCellValue(record.getGtipAciklamasi());
                row.createCell(cellNum++).setCellValue(record.getKullaniciBirimKiymetiUsdDegeri());
                row.createCell(cellNum++).setCellValue(record.getNavlunTutariTlDegeri());
                row.createCell(cellNum++).setCellValue(record.getMenseUlkeKodu());
                row.createCell(cellNum++).setCellValue(record.getMenseUlkeAdi());
                row.createCell(cellNum++).setCellValue(record.getNetAgirlik());
                row.createCell(cellNum++).setCellValue(record.getBrutAgirlik());
                row.createCell(cellNum++).setCellValue(record.getIstatistikiKiymetUsdDegeri());
                row.createCell(cellNum++).setCellValue(record.getHesaplanmisKalemKiymetiUsdDegeri());
                row.createCell(cellNum++).setCellValue(record.getOlcuBirimi());
                row.createCell(cellNum++).setCellValue(record.getOlcuBirimiKodu());
                row.createCell(cellNum++).setCellValue(record.getOlcuBirimiAciklamasi());
                row.createCell(cellNum++).setCellValue(record.getIstatistikiBirimKodu());
                row.createCell(cellNum++).setCellValue(record.getIstatistikiBirimAciklamasi());
                row.createCell(cellNum++).setCellValue(record.getEsyaTicariTanimi());

                for (int i = 0; i < cellNum; i++) {
                    row.getCell(i).setCellStyle(rowStyle);
                }
            }

            // Auto-size columns
            for (int i = 0; i < numCols; i++) {
                sheet.autoSizeColumn(i);
            }

            // Add autofilter
            sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, numCols - 1));

            workbook.write(out);
            out.close();

            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public ByteArrayInputStream createExportImportOtherWorkbook(List<ExportImportOther> exportTransactionsList) throws IOException {
        if (CollectionUtils.isEmpty(exportTransactionsList)) {
            return null;
        }

        System.setProperty("javax.xml.transform.TransformerFactory",
                "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");

        String sheetName = "ExportImportOther";

        // Sheet headers based on ExportImportOther fields
        String[] headers = {
                "ID", "Row Int", "File Name", "Sheet Name",
                "TCGB Gümrük İdaresi Kodu", "TCGB Gümrük İdaresi Adı",
                "TCGB Tescil No", "TCGB Tescil Tarihi", "TCGB Kapanış Tarihi",
                "Gönderici / Alıcı Vergi No", "Gönderici/Alıcı Adı", "Alıcı Adı",
                "Gideceği Ülke (17) Kodu", "Gideceği Ülke (17) Adı",
                "Menşe Ülke Kodu", "Menşe Ülke Adı", "Teslim Şekli Kodu",
                "Kalem Sıra No", "Kalem Rejim Kodu", "Kalem Rejim Açıklaması",
                "Kalem Rejim Açıklması", "GTIP Kodu", "GTIP Açıklaması",
                "31. Ticari Tanımı", "TCGB Statü Açıklaması",
                "Fatura Tutarı", "Fatura Tutarı Döviz Türü Kodu",
                "Ölçü (Eşya) Miktarı", "Ölçü Birimi Açıklaması",
                "Net Ağırlık (Kg)", "Hesaplanmış Kalem Kıymeti USD Değeri",
                "İstatistiki Kıymet USD Değeri"
        };

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            XSSFSheet sheet = workbook.createSheet(sheetName);

            // Header styling
            XSSFFont hdrFont = workbook.createFont();
            hdrFont.setBold(true);
            hdrFont.setColor(IndexedColors.WHITE.getIndex());

            XSSFColor hdrColor = new XSSFColor(new java.awt.Color(0x4F, 0x81, 0xBD), null);
            XSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setFont(hdrFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setFillForegroundColor(hdrColor);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Row styles
            XSSFCellStyle whiteStyle = workbook.createCellStyle();
            whiteStyle.setBorderTop(BorderStyle.THIN);
            whiteStyle.setBorderBottom(BorderStyle.THIN);
            whiteStyle.setBorderLeft(BorderStyle.THIN);
            whiteStyle.setBorderRight(BorderStyle.THIN);
            whiteStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            whiteStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            XSSFCellStyle altStyle = workbook.createCellStyle();
            altStyle.setBorderTop(BorderStyle.THIN);
            altStyle.setBorderBottom(BorderStyle.THIN);
            altStyle.setBorderLeft(BorderStyle.THIN);
            altStyle.setBorderRight(BorderStyle.THIN);
            XSSFColor lightBlue = new XSSFColor(new java.awt.Color(0xC5, 0xD9, 0xF1), null);
            altStyle.setFillForegroundColor(lightBlue);
            altStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            int rowNum = 0;
            int cellNum = 0;

            // Create header row
            Row row = sheet.createRow(rowNum++);
            for (String header : headers) {
                Cell cell = row.createCell(cellNum++);
                cell.setCellValue(header);
                cell.setCellStyle(headerStyle);
            }

            int numCols = cellNum;

            // Create data rows
            for (ExportImportOther record : exportTransactionsList) {
                row = sheet.createRow(rowNum++);
                cellNum = 0;
                XSSFCellStyle rowStyle = (rowNum % 2 == 0) ? altStyle : whiteStyle;

                row.createCell(cellNum++).setCellValue(record.getId() != null ? record.getId() : 0);
                row.createCell(cellNum++).setCellValue(record.getRowInt() != null ? record.getRowInt() : 0);
                row.createCell(cellNum++).setCellValue(record.getFileName());
                row.createCell(cellNum++).setCellValue(record.getSheetName());
                row.createCell(cellNum++).setCellValue(record.getTcgbGumrukIdaresiKodu());
                row.createCell(cellNum++).setCellValue(record.getTcgbGumrukIdaresiAdi());
                row.createCell(cellNum++).setCellValue(record.getTcgbTescilNo());
                row.createCell(cellNum++).setCellValue(record.getTcgbTescilTarihi());
                row.createCell(cellNum++).setCellValue(record.getTcgbKapanisTarihi());
                row.createCell(cellNum++).setCellValue(record.getGondericiAliciVergiNo());
                row.createCell(cellNum++).setCellValue(record.getGondericiAliciAdi());
                row.createCell(cellNum++).setCellValue(record.getAliciAdi());
                row.createCell(cellNum++).setCellValue(record.getGidecegiUlkeKodu());
                row.createCell(cellNum++).setCellValue(record.getGidecegiUlkeAdi());
                row.createCell(cellNum++).setCellValue(record.getMenseUlkeKodu());
                row.createCell(cellNum++).setCellValue(record.getMenseUlkeAdi());
                row.createCell(cellNum++).setCellValue(record.getTeslimSekliKodu());
                row.createCell(cellNum++).setCellValue(record.getKalemSiraNo());
                row.createCell(cellNum++).setCellValue(record.getKalemRejimKodu());
                row.createCell(cellNum++).setCellValue(record.getKalemRejimAciklamasi());
                row.createCell(cellNum++).setCellValue(record.getKalemRejimAciklmasi());
                row.createCell(cellNum++).setCellValue(record.getGtipKodu());
                row.createCell(cellNum++).setCellValue(record.getGtipAciklamasi());
                row.createCell(cellNum++).setCellValue(record.getTicariTanimi());
                row.createCell(cellNum++).setCellValue(record.getTcgbStatuAciklamasi());
                row.createCell(cellNum++).setCellValue(record.getFaturaTutari());
                row.createCell(cellNum++).setCellValue(record.getFaturaTutariDovizTuruKodu());
                row.createCell(cellNum++).setCellValue(record.getOlcuEsyaMiktari());
                row.createCell(cellNum++).setCellValue(record.getOlcuBirimiAciklamasi());
                row.createCell(cellNum++).setCellValue(record.getNetAgirlikKg());
                row.createCell(cellNum++).setCellValue(record.getHesaplanmisKalemKiymetiUsdDegeri());
                row.createCell(cellNum++).setCellValue(record.getIstatistikiKiymetUsdDegeri());

                for (int i = 0; i < cellNum; i++) {
                    row.getCell(i).setCellStyle(rowStyle);
                }
            }

            // Auto-size columns
            for (int i = 0; i < numCols; i++) {
                sheet.autoSizeColumn(i);
            }

            // Add autofilter
            sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, numCols - 1));

            workbook.write(out);
            out.close();

            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}