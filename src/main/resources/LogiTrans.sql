USE [master]
GO
/****** Object:  Database [LogiTrans2]    Script Date: 3/7/2025 12:37:57 PM ******/
CREATE DATABASE [LogiTrans2]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'LogiTrans2', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\LogiTrans2.mdf' , SIZE = 9876992KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'LogiTrans2_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\LogiTrans2_log.ldf' , SIZE = 2564096KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [LogiTrans2] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [LogiTrans2].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [LogiTrans2] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [LogiTrans2] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [LogiTrans2] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [LogiTrans2] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [LogiTrans2] SET ARITHABORT OFF 
GO
ALTER DATABASE [LogiTrans2] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [LogiTrans2] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [LogiTrans2] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [LogiTrans2] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [LogiTrans2] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [LogiTrans2] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [LogiTrans2] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [LogiTrans2] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [LogiTrans2] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [LogiTrans2] SET  DISABLE_BROKER 
GO
ALTER DATABASE [LogiTrans2] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [LogiTrans2] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [LogiTrans2] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [LogiTrans2] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [LogiTrans2] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [LogiTrans2] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [LogiTrans2] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [LogiTrans2] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [LogiTrans2] SET  MULTI_USER 
GO
ALTER DATABASE [LogiTrans2] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [LogiTrans2] SET DB_CHAINING OFF 
GO
ALTER DATABASE [LogiTrans2] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [LogiTrans2] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [LogiTrans2] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [LogiTrans2] SET QUERY_STORE = OFF
GO
USE [LogiTrans2]
GO
USE [LogiTrans2]
GO
/****** Object:  Sequence [dbo].[2023-eki̇m-i̇hr-2_seq]    Script Date: 3/7/2025 12:37:58 PM ******/
CREATE SEQUENCE [dbo].[2023-eki̇m-i̇hr-2_seq] 
 AS [bigint]
 START WITH 1
 INCREMENT BY 50
 MINVALUE -9223372036854775808
 MAXVALUE 9223372036854775807
 CACHE 
GO
/****** Object:  UserDefinedTableType [dbo].[ExportImportAralikType]    Script Date: 3/7/2025 12:37:58 PM ******/
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
/****** Object:  UserDefinedTableType [dbo].[ExportImportOthersType]    Script Date: 3/7/2025 12:37:58 PM ******/
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
/****** Object:  Table [dbo].[export_import_aralik]    Script Date: 3/7/2025 12:37:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[export_import_aralik](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[alici_adi_unvani] [text] NULL,
	[alici_kimlik_no] [text] NULL,
	[beyanname_no] [text] NULL,
	[brut_agirlik] [text] NULL,
	[cikis_ulkesi_adi] [text] NULL,
	[cikis_ulkesi_kodu] [text] NULL,
	[cikistaki_aracin_kayitli_oldugu_ulke_adi] [text] NULL,
	[cikistaki_aracin_kayitli_oldugu_ulke_kodu] [text] NULL,
	[esya_ticari_tanimi] [text] NULL,
	[file_name] [varchar](255) NULL,
	[gidecegi_ulke_adi] [text] NULL,
	[gidecegi_ulke_kodu] [text] NULL,
	[gonderici_adi_unvani] [text] NULL,
	[gonderici_kimlik_no] [text] NULL,
	[gtip_aciklamasi] [text] NULL,
	[gtip_kodu] [text] NULL,
	[gumruk_istatistik_tarihi] [text] NULL,
	[hesaplanmis_kalem_kiymeti_usd_degeri] [text] NULL,
	[istatistiki_birim_aciklamasi] [text] NULL,
	[istatistiki_birim_kodu] [text] NULL,
	[istatistiki_kiymet_usd_degeri] [text] NULL,
	[kalem_sira_numarasi] [text] NULL,
	[kullanici_birim_kiymeti_usd_degeri] [text] NULL,
	[mense_ulke_adi] [text] NULL,
	[mense_ulke_kodu] [text] NULL,
	[navlun_tutari_tl_degeri] [text] NULL,
	[net_agirlik] [text] NULL,
	[olcu_birimi] [text] NULL,
	[olcu_birimi_aciklamasi] [text] NULL,
	[olcu_birimi_kodu] [text] NULL,
	[row_int] [int] NULL,
	[sheet_name] [varchar](255) NULL,
	[sigorta_tutari] [text] NULL,
	[sinirdaki_aracin_tasima_sekli_aciklamasi] [text] NULL,
	[sinirdaki_aracin_tasima_sekli_kodu] [text] NULL,
	[tcgb_bolge_mudurlugu_adi] [text] NULL,
	[tcgb_bolge_mudurlugu_kodu] [text] NULL,
	[tcgb_gumruk_idaresi_adi] [text] NULL,
	[tcgb_gumruk_idaresi_kodu] [text] NULL,
	[teslim_sekli_kodu] [text] NULL,
	[ticaret_yapilan_ulke_adi] [text] NULL,
	[ticaret_yapilan_ulke_kodu] [text] NULL,
	[yukleme_bosaltma_yapilan_gumruk_idaresi_adi] [text] NULL,
	[yukleme_bosaltma_yapilan_gumruk_idaresi_kodu] [text] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[export_import_others]    Script Date: 3/7/2025 12:37:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[export_import_others](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[alıcı adı] [text] NULL,
	[fatura tutarı] [text] NULL,
	[fatura tutarı döviz türü kodu] [text] NULL,
	[file_name] [varchar](255) NULL,
	[gideceği ülke (17) adı] [text] NULL,
	[gideceği ülke (17) kodu] [text] NULL,
	[gönderici/alıcı adı] [text] NULL,
	[gönderici / alıcı vergi no] [text] NULL,
	[gtip açıklaması] [text] NULL,
	[gtip kodu] [text] NULL,
	[hesaplanmış kalem kıymeti usd değeri] [text] NULL,
	[i̇statistiki kıymet usd değeri] [text] NULL,
	[kalem rejim açıklaması] [text] NULL,
	[kalem rejim açıklması] [text] NULL,
	[kalem rejim kodu] [text] NULL,
	[kalem sıra no] [text] NULL,
	[menşe ülke adı] [text] NULL,
	[menşe ülke kodu] [text] NULL,
	[net ağırlık (kg)] [text] NULL,
	[ölçü birimi açıklaması] [text] NULL,
	[ölçü (eşya) miktarı] [text] NULL,
	[row_int] [int] NULL,
	[sheet_name] [varchar](255) NULL,
	[tcgb gümrük i̇daresi adı] [text] NULL,
	[tcgb gümrük i̇daresi kodu] [text] NULL,
	[tcgb kapanış tarihi] [text] NULL,
	[tcgb statü açıklaması] [text] NULL,
	[tcgb tescil no] [text] NULL,
	[tcgb tescil tarihi] [text] NULL,
	[teslim şekli kodu] [text] NULL,
	[31_ ticari tanımı] [text] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  StoredProcedure [dbo].[ReportTotalProceesed]    Script Date: 3/7/2025 12:37:58 PM ******/
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

  ) T order by [file_name] desc

END
GO
/****** Object:  StoredProcedure [dbo].[usp_BatchInsertExportImportAralik]    Script Date: 3/7/2025 12:37:58 PM ******/
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
/****** Object:  StoredProcedure [dbo].[usp_BatchInsertExportImportOthers]    Script Date: 3/7/2025 12:37:58 PM ******/
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
USE [master]
GO
ALTER DATABASE [LogiTrans2] SET  READ_WRITE 
GO
