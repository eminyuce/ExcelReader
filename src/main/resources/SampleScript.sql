
/****** Object:  UserDefinedTableType [dbo].[ExportImportAralikType]    Script Date: 3/11/2025 11:22:20 PM ******/
CREATE TYPE [dbo].[ExportImportAralikType] AS TABLE(
	[alici_adi_unvani] [nvarchar](max) NULL,
	[alici_kimlik_no] [nvarchar](max) NULL,
	[beyanname_no] [nvarchar](max) NULL,
	[brut_agirlik] [nvarchar](max) NULL,
	[cikis_ulkesi_adi] [nvarchar](max) NULL,
	[cikis_ulkesi_kodu] [nvarchar](max) NULL,
	[cikistaki_aracin_kayitli_oldugu_ulke_adi] [nvarchar](max) NULL,
	[cikistaki_aracin_kayitli_oldugu_ulke_kodu] [nvarchar](max) NULL,
	[esya_ticari_tanimi] [nvarchar](max) NULL,
	[file_name] [varchar](255) NULL,
	[gidecegi_ulke_adi] [nvarchar](max) NULL,
	[gidecegi_ulke_kodu] [nvarchar](max) NULL,
	[gonderici_adi_unvani] [nvarchar](max) NULL,
	[gonderici_kimlik_no] [nvarchar](max) NULL,
	[gtip_aciklamasi] [nvarchar](max) NULL,
	[gtip_kodu] [nvarchar](max) NULL,
	[gumruk_istatistik_tarihi] [nvarchar](max) NULL,
	[hesaplanmis_kalem_kiymeti_usd_degeri] [nvarchar](max) NULL,
	[istatistiki_birim_aciklamasi] [nvarchar](max) NULL,
	[istatistiki_birim_kodu] [nvarchar](max) NULL,
	[istatistiki_kiymet_usd_degeri] [nvarchar](max) NULL,
	[kalem_sira_numarasi] [nvarchar](max) NULL,
	[kullanici_birim_kiymeti_usd_degeri] [nvarchar](max) NULL,
	[mense_ulke_adi] [nvarchar](max) NULL,
	[mense_ulke_kodu] [nvarchar](max) NULL,
	[navlun_tutari_tl_degeri] [nvarchar](max) NULL,
	[net_agirlik] [nvarchar](max) NULL,
	[olcu_birimi] [nvarchar](max) NULL,
	[olcu_birimi_aciklamasi] [nvarchar](max) NULL,
	[olcu_birimi_kodu] [nvarchar](max) NULL,
	[row_int] [int] NULL,
	[sheet_name] [varchar](255) NULL,
	[sigorta_tutari] [nvarchar](max) NULL,
	[sinirdaki_aracin_tasima_sekli_aciklamasi] [nvarchar](max) NULL,
	[sinirdaki_aracin_tasima_sekli_kodu] [nvarchar](max) NULL,
	[tcgb_bolge_mudurlugu_adi] [nvarchar](max) NULL,
	[tcgb_bolge_mudurlugu_kodu] [nvarchar](max) NULL,
	[tcgb_gumruk_idaresi_adi] [nvarchar](max) NULL,
	[tcgb_gumruk_idaresi_kodu] [nvarchar](max) NULL,
	[teslim_sekli_kodu] [nvarchar](max) NULL,
	[ticaret_yapilan_ulke_adi] [nvarchar](max) NULL,
	[ticaret_yapilan_ulke_kodu] [nvarchar](max) NULL,
	[yukleme_bosaltma_yapilan_gumruk_idaresi_adi] [nvarchar](max) NULL,
	[yukleme_bosaltma_yapilan_gumruk_idaresi_kodu] [nvarchar](max) NULL
)
GO
/****** Object:  UserDefinedTableType [dbo].[ExportImportOthersType]    Script Date: 3/11/2025 11:22:20 PM ******/
CREATE TYPE [dbo].[ExportImportOthersType] AS TABLE(
	[alıcı_adı] [nvarchar](max) NULL,
	[fatura_tutarı] [nvarchar](max) NULL,
	[fatura_tutarı_döviz_türü_kodu] [nvarchar](max) NULL,
	[file_name] [varchar](255) NULL,
	[gideceği_ülke_adı] [nvarchar](max) NULL,
	[gideceği_ülke_kodu] [nvarchar](max) NULL,
	[gönderici_alıcı_adı] [nvarchar](max) NULL,
	[gönderici_alıcı_vergi_no] [nvarchar](max) NULL,
	[gtip_açıklaması] [nvarchar](max) NULL,
	[gtip_kodu] [nvarchar](max) NULL,
	[hesaplanmış_kalem_kıymeti_usd_değeri] [nvarchar](max) NULL,
	[i̇statistiki_kıymet_usd_değeri] [nvarchar](max) NULL,
	[kalem_rejim_açıklaması] [nvarchar](max) NULL,
	[kalem_rejim_açıklması] [nvarchar](max) NULL,
	[kalem_rejim_kodu] [nvarchar](max) NULL,
	[kalem_sıra_no] [nvarchar](max) NULL,
	[menşe_ülke_adı] [nvarchar](max) NULL,
	[menşe_ülke_kodu] [nvarchar](max) NULL,
	[net_ağırlık_kg] [nvarchar](max) NULL,
	[ölçü_birimi_açıklaması] [nvarchar](max) NULL,
	[ölçü_eşya_miktarı] [nvarchar](max) NULL,
	[row_int] [int] NULL,
	[sheet_name] [varchar](255) NULL,
	[tcgb_gümrük_i̇daresi_adı] [nvarchar](max) NULL,
	[tcgb_gümrük_i̇daresi_kodu] [nvarchar](max) NULL,
	[tcgb_kapanış_tarihi] [nvarchar](max) NULL,
	[tcgb_statü_açıklaması] [nvarchar](max) NULL,
	[tcgb_tescil_no] [nvarchar](max) NULL,
	[tcgb_tescil_tarihi] [nvarchar](max) NULL,
	[teslim_şekli_kodu] [nvarchar](max) NULL,
	[ticari_tanımı] [nvarchar](max) NULL
)
GO
/****** Object:  UserDefinedTableType [dbo].[ImportOthersType]    Script Date: 3/11/2025 11:22:20 PM ******/
CREATE TYPE [dbo].[ImportOthersType] AS TABLE(
	[cikis_ulkesi_adi] [varchar](2550) NULL,
	[cikis_ulkesi_kodu] [varchar](2550) NULL,
	[fatura_tutari] [varchar](2550) NULL,
	[fatura_tutari_doviz_turu_kodu] [varchar](2550) NULL,
	[file_name] [varchar](255) NULL,
	[gonderen_adi] [varchar](2550) NULL,
	[gonderici_alici_adi] [varchar](2550) NULL,
	[gonderici_alici_vergi_no] [varchar](2550) NULL,
	[gtip_aciklamasi] [varchar](2550) NULL,
	[gtip_kodu] [varchar](2550) NULL,
	[hesaplanmis_kalem_kiymeti_usd_degeri] [varchar](2550) NULL,
	[istatistiki_kiymet_usd_degeri] [varchar](2550) NULL,
	[kalem_rejim_aciklamasi] [varchar](2550) NULL,
	[kalem_rejim_kodu] [varchar](2550) NULL,
	[kalem_sira_no] [varchar](2550) NULL,
	[mense_ulke_adi] [varchar](2550) NULL,
	[mense_ulke_kodu] [varchar](2550) NULL,
	[net_agirlik_kg] [varchar](2550) NULL,
	[olcu_birimi_aciklamasi] [varchar](2550) NULL,
	[olcu_esya_miktari] [varchar](2550) NULL,
	[row_int] [int] NULL,
	[sheet_name] [varchar](255) NULL,
	[tcgb_gumruk_idaresi_adi] [varchar](2550) NULL,
	[tcgb_gumruk_idaresi_kodu] [varchar](2550) NULL,
	[tcgb_kapanis_tarihi] [varchar](2550) NULL,
	[tcgb_tescil_no] [varchar](2550) NULL,
	[tcgb_tescil_tarihi] [varchar](2550) NULL,
	[teslim_sekli_kodu] [varchar](2550) NULL,
	[ticari_tanimi_31] [varchar](2550) NULL
)
GO
/****** Object:  Table [dbo].[export_import_aralik]    Script Date: 3/11/2025 11:22:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[export_import_aralik](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[alici_adi_unvani] [varchar](2550) NULL,
	[alici_kimlik_no] [varchar](2550) NULL,
	[beyanname_no] [varchar](2550) NULL,
	[brut_agirlik] [varchar](2550) NULL,
	[cikis_ulkesi_adi] [varchar](2550) NULL,
	[cikis_ulkesi_kodu] [varchar](2550) NULL,
	[cikistaki_aracin_kayitli_oldugu_ulke_adi] [varchar](2550) NULL,
	[cikistaki_aracin_kayitli_oldugu_ulke_kodu] [varchar](2550) NULL,
	[esya_ticari_tanimi] [varchar](2550) NULL,
	[file_name] [varchar](255) NULL,
	[gidecegi_ulke_adi] [varchar](2550) NULL,
	[gidecegi_ulke_kodu] [varchar](2550) NULL,
	[gonderici_adi_unvani] [varchar](2550) NULL,
	[gonderici_kimlik_no] [varchar](2550) NULL,
	[gtip_aciklamasi] [varchar](2550) NULL,
	[gtip_kodu] [varchar](2550) NULL,
	[gumruk_istatistik_tarihi] [varchar](2550) NULL,
	[hesaplanmis_kalem_kiymeti_usd_degeri] [varchar](2550) NULL,
	[istatistiki_birim_aciklamasi] [varchar](2550) NULL,
	[istatistiki_birim_kodu] [varchar](2550) NULL,
	[istatistiki_kiymet_usd_degeri] [varchar](2550) NULL,
	[kalem_sira_numarasi] [varchar](2550) NULL,
	[kullanici_birim_kiymeti_usd_degeri] [varchar](2550) NULL,
	[mense_ulke_adi] [varchar](2550) NULL,
	[mense_ulke_kodu] [varchar](2550) NULL,
	[navlun_tutari_tl_degeri] [varchar](2550) NULL,
	[net_agirlik] [varchar](2550) NULL,
	[olcu_birimi] [varchar](2550) NULL,
	[olcu_birimi_aciklamasi] [varchar](2550) NULL,
	[olcu_birimi_kodu] [varchar](2550) NULL,
	[row_int] [int] NULL,
	[sheet_name] [varchar](255) NULL,
	[sigorta_tutari] [varchar](2550) NULL,
	[sinirdaki_aracin_tasima_sekli_aciklamasi] [varchar](2550) NULL,
	[sinirdaki_aracin_tasima_sekli_kodu] [varchar](2550) NULL,
	[tcgb_bolge_mudurlugu_adi] [varchar](2550) NULL,
	[tcgb_bolge_mudurlugu_kodu] [varchar](2550) NULL,
	[tcgb_gumruk_idaresi_adi] [varchar](2550) NULL,
	[tcgb_gumruk_idaresi_kodu] [varchar](2550) NULL,
	[teslim_sekli_kodu] [varchar](2550) NULL,
	[ticaret_yapilan_ulke_adi] [varchar](2550) NULL,
	[ticaret_yapilan_ulke_kodu] [varchar](2550) NULL,
	[yukleme_bosaltma_yapilan_gumruk_idaresi_adi] [varchar](2550) NULL,
	[yukleme_bosaltma_yapilan_gumruk_idaresi_kodu] [varchar](2550) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[export_import_others]    Script Date: 3/11/2025 11:22:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[export_import_others](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[alıcı adı] [varchar](2550) NULL,
	[fatura tutarı] [varchar](2550) NULL,
	[fatura tutarı döviz türü kodu] [varchar](2550) NULL,
	[file_name] [varchar](255) NULL,
	[gideceği ülke (17) adı] [varchar](2550) NULL,
	[gideceği ülke (17) kodu] [varchar](2550) NULL,
	[gönderici/alıcı adı] [varchar](2550) NULL,
	[gönderici / alıcı vergi no] [varchar](2550) NULL,
	[gtip açıklaması] [varchar](2550) NULL,
	[gtip kodu] [varchar](2550) NULL,
	[hesaplanmış kalem kıymeti usd değeri] [varchar](2550) NULL,
	[i̇statistiki kıymet usd değeri] [varchar](2550) NULL,
	[kalem rejim açıklaması] [varchar](2550) NULL,
	[kalem rejim açıklması] [varchar](2550) NULL,
	[kalem rejim kodu] [varchar](2550) NULL,
	[kalem sıra no] [varchar](2550) NULL,
	[menşe ülke adı] [varchar](2550) NULL,
	[menşe ülke kodu] [varchar](2550) NULL,
	[net ağırlık (kg)] [varchar](2550) NULL,
	[ölçü birimi açıklaması] [varchar](2550) NULL,
	[ölçü (eşya) miktarı] [varchar](2550) NULL,
	[row_int] [int] NULL,
	[sheet_name] [varchar](255) NULL,
	[tcgb gümrük i̇daresi adı] [varchar](2550) NULL,
	[tcgb gümrük i̇daresi kodu] [varchar](2550) NULL,
	[tcgb kapanış tarihi] [varchar](2550) NULL,
	[tcgb statü açıklaması] [varchar](2550) NULL,
	[tcgb tescil no] [varchar](2550) NULL,
	[tcgb tescil tarihi] [varchar](2550) NULL,
	[teslim şekli kodu] [varchar](2550) NULL,
	[31_ ticari tanımı] [varchar](2550) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[import_others]    Script Date: 3/11/2025 11:22:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[import_others](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[cikis_ulkesi_adi] [varchar](2550) NULL,
	[cikis_ulkesi_kodu] [varchar](2550) NULL,
	[fatura_tutari] [varchar](2550) NULL,
	[fatura_tutari_doviz_turu_kodu] [varchar](2550) NULL,
	[file_name] [varchar](255) NULL,
	[gonderen_adi] [varchar](2550) NULL,
	[gonderici_alici_adi] [varchar](2550) NULL,
	[gonderici_alici_vergi_no] [varchar](2550) NULL,
	[gtip_aciklamasi] [varchar](2550) NULL,
	[gtip_kodu] [varchar](2550) NULL,
	[hesaplanmis_kalem_kiymeti_usd_degeri] [varchar](2550) NULL,
	[istatistiki_kiymet_usd_degeri] [varchar](2550) NULL,
	[kalem_rejim_aciklamasi] [varchar](2550) NULL,
	[kalem_rejim_kodu] [varchar](2550) NULL,
	[kalem_sira_no] [varchar](2550) NULL,
	[mense_ulke_adi] [varchar](2550) NULL,
	[mense_ulke_kodu] [varchar](2550) NULL,
	[net_agirlik_kg] [varchar](2550) NULL,
	[olcu_birimi_aciklamasi] [varchar](2550) NULL,
	[olcu_esya_miktari] [varchar](2550) NULL,
	[row_int] [int] NULL,
	[sheet_name] [varchar](255) NULL,
	[tcgb_gumruk_idaresi_adi] [varchar](2550) NULL,
	[tcgb_gumruk_idaresi_kodu] [varchar](2550) NULL,
	[tcgb_kapanis_tarihi] [varchar](2550) NULL,
	[tcgb_tescil_no] [varchar](2550) NULL,
	[tcgb_tescil_tarihi] [varchar](2550) NULL,
	[teslim_sekli_kodu] [varchar](2550) NULL,
	[ticari_tanimi_31] [varchar](2550) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  StoredProcedure [dbo].[ReportTotalProceesed]    Script Date: 3/11/2025 11:22:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ReportTotalProceesed]
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
SELECT * FROM (

SELECT  [file_name],count(*) Total, min(sheet_name) sheet_name1,max(sheet_name) sheet_name2,max([row_int]) maxRowInt
   
  FROM [dbo].[export_import_aralik]
  group by [file_name]

  UNION 

  SELECT  [file_name],count(*) Total, min(sheet_name) sheet_name1,max(sheet_name) sheet_name2,max([row_int]) maxRowInt
   
  FROM [dbo].[export_import_others]
  group by [file_name]

    UNION 

  SELECT  [file_name],count(*) Total, min(sheet_name) sheet_name1,max(sheet_name) sheet_name2,max([row_int]) maxRowInt
   
  FROM [dbo].[import_others]
  group by [file_name]

  ) T order by [file_name] desc

END
GO
/****** Object:  StoredProcedure [dbo].[usp_BatchInsertExportImportAralik]    Script Date: 3/11/2025 11:22:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- Then create the stored procedure
CREATE PROCEDURE [dbo].[usp_BatchInsertExportImportAralik]
    @BatchData [dbo].[ExportImportAralikType] READONLY
AS
BEGIN
    SET NOCOUNT ON;
    
    BEGIN TRY
        -- Insert all records in one go
        INSERT INTO [dbo].[export_import_aralik]
        (
            [alici_adi_unvani],
            [alici_kimlik_no],
            [beyanname_no],
            [brut_agirlik],
            [cikis_ulkesi_adi],
            [cikis_ulkesi_kodu],
            [cikistaki_aracin_kayitli_oldugu_ulke_adi],
            [cikistaki_aracin_kayitli_oldugu_ulke_kodu],
            [esya_ticari_tanimi],
            [file_name],
            [gidecegi_ulke_adi],
            [gidecegi_ulke_kodu],
            [gonderici_adi_unvani],
            [gonderici_kimlik_no],
            [gtip_aciklamasi],
            [gtip_kodu],
            [gumruk_istatistik_tarihi],
            [hesaplanmis_kalem_kiymeti_usd_degeri],
            [istatistiki_birim_aciklamasi],
            [istatistiki_birim_kodu],
            [istatistiki_kiymet_usd_degeri],
            [kalem_sira_numarasi],
            [kullanici_birim_kiymeti_usd_degeri],
            [mense_ulke_adi],
            [mense_ulke_kodu],
            [navlun_tutari_tl_degeri],
            [net_agirlik],
            [olcu_birimi],
            [olcu_birimi_aciklamasi],
            [olcu_birimi_kodu],
            [row_int],
            [sheet_name],
            [sigorta_tutari],
            [sinirdaki_aracin_tasima_sekli_aciklamasi],
            [sinirdaki_aracin_tasima_sekli_kodu],
            [tcgb_bolge_mudurlugu_adi],
            [tcgb_bolge_mudurlugu_kodu],
            [tcgb_gumruk_idaresi_adi],
            [tcgb_gumruk_idaresi_kodu],
            [teslim_sekli_kodu],
            [ticaret_yapilan_ulke_adi],
            [ticaret_yapilan_ulke_kodu],
            [yukleme_bosaltma_yapilan_gumruk_idaresi_adi],
            [yukleme_bosaltma_yapilan_gumruk_idaresi_kodu]
        )
        SELECT 
            [alici_adi_unvani],
            [alici_kimlik_no],
            [beyanname_no],
            [brut_agirlik],
            [cikis_ulkesi_adi],
            [cikis_ulkesi_kodu],
            [cikistaki_aracin_kayitli_oldugu_ulke_adi],
            [cikistaki_aracin_kayitli_oldugu_ulke_kodu],
            [esya_ticari_tanimi],
            [file_name],
            [gidecegi_ulke_adi],
            [gidecegi_ulke_kodu],
            [gonderici_adi_unvani],
            [gonderici_kimlik_no],
            [gtip_aciklamasi],
            [gtip_kodu],
            [gumruk_istatistik_tarihi],
            [hesaplanmis_kalem_kiymeti_usd_degeri],
            [istatistiki_birim_aciklamasi],
            [istatistiki_birim_kodu],
            [istatistiki_kiymet_usd_degeri],
            [kalem_sira_numarasi],
            [kullanici_birim_kiymeti_usd_degeri],
            [mense_ulke_adi],
            [mense_ulke_kodu],
            [navlun_tutari_tl_degeri],
            [net_agirlik],
            [olcu_birimi],
            [olcu_birimi_aciklamasi],
            [olcu_birimi_kodu],
            [row_int],
            [sheet_name],
            [sigorta_tutari],
            [sinirdaki_aracin_tasima_sekli_aciklamasi],
            [sinirdaki_aracin_tasima_sekli_kodu],
            [tcgb_bolge_mudurlugu_adi],
            [tcgb_bolge_mudurlugu_kodu],
            [tcgb_gumruk_idaresi_adi],
            [tcgb_gumruk_idaresi_kodu],
            [teslim_sekli_kodu],
            [ticaret_yapilan_ulke_adi],
            [ticaret_yapilan_ulke_kodu],
            [yukleme_bosaltma_yapilan_gumruk_idaresi_adi],
            [yukleme_bosaltma_yapilan_gumruk_idaresi_kodu]
        FROM @BatchData;
        
        SELECT @@ROWCOUNT AS AffectedRows;
    END TRY
    BEGIN CATCH
        THROW;
    END CATCH
END
GO
/****** Object:  StoredProcedure [dbo].[usp_BatchInsertExportImportOthers]    Script Date: 3/11/2025 11:22:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE  PROCEDURE [dbo].[usp_BatchInsertExportImportOthers]
    @BatchData [dbo].[ExportImportOthersType] READONLY
AS
BEGIN
    SET NOCOUNT ON;
    
    BEGIN TRY
        -- Insert all records in one go
        INSERT INTO [dbo].[export_import_others]
        (
            [alıcı adı],
            [fatura tutarı],
            [fatura tutarı döviz türü kodu],
            [file_name],
            [gideceği ülke (17) adı],
            [gideceği ülke (17) kodu],
            [gönderici/alıcı adı],
            [gönderici / alıcı vergi no],
            [gtip açıklaması],
            [gtip kodu],
            [hesaplanmış kalem kıymeti usd değeri],
            [i̇statistiki kıymet usd değeri],
            [kalem rejim açıklaması],
            [kalem rejim açıklması],
            [kalem rejim kodu],
            [kalem sıra no],
            [menşe ülke adı],
            [menşe ülke kodu],
            [net ağırlık (kg)],
            [ölçü birimi açıklaması],
            [ölçü (eşya) miktarı],
            [row_int],
            [sheet_name],
            [tcgb gümrük i̇daresi adı],
            [tcgb gümrük i̇daresi kodu],
            [tcgb kapanış tarihi],
            [tcgb statü açıklaması],
            [tcgb tescil no],
            [tcgb tescil tarihi],
            [teslim şekli kodu],
            [31_ ticari tanımı]
        )
        SELECT 
            [alıcı_adı],
            [fatura_tutarı],
            [fatura_tutarı_döviz_türü_kodu],
            [file_name],
            [gideceği_ülke_adı],
            [gideceği_ülke_kodu],
            [gönderici_alıcı_adı],
            [gönderici_alıcı_vergi_no],
            [gtip_açıklaması],
            [gtip_kodu],
            [hesaplanmış_kalem_kıymeti_usd_değeri],
            [i̇statistiki_kıymet_usd_değeri],
            [kalem_rejim_açıklaması],
            [kalem_rejim_açıklması],
            [kalem_rejim_kodu],
            [kalem_sıra_no],
            [menşe_ülke_adı],
            [menşe_ülke_kodu],
            [net_ağırlık_kg],
            [ölçü_birimi_açıklaması],
            [ölçü_eşya_miktarı],
            [row_int],
            [sheet_name],
            [tcgb_gümrük_i̇daresi_adı],
            [tcgb_gümrük_i̇daresi_kodu],
            [tcgb_kapanış_tarihi],
            [tcgb_statü_açıklaması],
            [tcgb_tescil_no],
            [tcgb_tescil_tarihi],
            [teslim_şekli_kodu],
            [ticari_tanımı]
        FROM @BatchData;
        
        SELECT @@ROWCOUNT AS AffectedRows;
    END TRY
    BEGIN CATCH
        THROW;
    END CATCH
END
GO
/****** Object:  StoredProcedure [dbo].[usp_BatchInsertImportOthers]    Script Date: 3/11/2025 11:22:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:      <Your Name>
-- Create date: 2025-03-11
-- Description: Bulk insert records into import_others table
-- =============================================
CREATE PROCEDURE [dbo].[usp_BatchInsertImportOthers]
    @BatchData [dbo].[ImportOthersType] READONLY
AS
BEGIN
    SET NOCOUNT ON;
    
    BEGIN TRY
        -- Insert all records in one go
        INSERT INTO [dbo].[import_others]
        (
            [cikis_ulkesi_adi],
            [cikis_ulkesi_kodu],
            [fatura_tutari],
            [fatura_tutari_doviz_turu_kodu],
            [file_name],
            [gonderen_adi],
            [gonderici_alici_adi],
            [gonderici_alici_vergi_no],
            [gtip_aciklamasi],
            [gtip_kodu],
            [hesaplanmis_kalem_kiymeti_usd_degeri],
            [istatistiki_kiymet_usd_degeri],
            [kalem_rejim_aciklamasi],
            [kalem_rejim_kodu],
            [kalem_sira_no],
            [mense_ulke_adi],
            [mense_ulke_kodu],
            [net_agirlik_kg],
            [olcu_birimi_aciklamasi],
            [olcu_esya_miktari],
            [row_int],
            [sheet_name],
            [tcgb_gumruk_idaresi_adi],
            [tcgb_gumruk_idaresi_kodu],
            [tcgb_kapanis_tarihi],
            [tcgb_tescil_no],
            [tcgb_tescil_tarihi],
            [teslim_sekli_kodu],
            [ticari_tanimi_31]
        )
        SELECT 
            [cikis_ulkesi_adi],
            [cikis_ulkesi_kodu],
            [fatura_tutari],
            [fatura_tutari_doviz_turu_kodu],
            [file_name],
            [gonderen_adi],
            [gonderici_alici_adi],
            [gonderici_alici_vergi_no],
            [gtip_aciklamasi],
            [gtip_kodu],
            [hesaplanmis_kalem_kiymeti_usd_degeri],
            [istatistiki_kiymet_usd_degeri],
            [kalem_rejim_aciklamasi],
            [kalem_rejim_kodu],
            [kalem_sira_no],
            [mense_ulke_adi],
            [mense_ulke_kodu],
            [net_agirlik_kg],
            [olcu_birimi_aciklamasi],
            [olcu_esya_miktari],
            [row_int],
            [sheet_name],
            [tcgb_gumruk_idaresi_adi],
            [tcgb_gumruk_idaresi_kodu],
            [tcgb_kapanis_tarihi],
            [tcgb_tescil_no],
            [tcgb_tescil_tarihi],
            [teslim_sekli_kodu],
            [ticari_tanimi_31]
        FROM @BatchData;
        
        -- Return the number of rows affected
        SELECT @@ROWCOUNT AS AffectedRows;
    END TRY
    BEGIN CATCH
        -- Re-throw the error to the caller
        THROW;
    END CATCH
END
