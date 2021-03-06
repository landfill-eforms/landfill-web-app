USE [master]
GO
/****** Object:  Database [test]    Script Date: 5/12/2017 7:57:47 AM ******/
CREATE DATABASE [test]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'test', FILENAME = N'D:\RDSDBDATA\DATA\test.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 10%)
 LOG ON 
( NAME = N'test_log', FILENAME = N'D:\RDSDBDATA\DATA\test_log.ldf' , SIZE = 1536KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [test] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [test].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [test] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [test] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [test] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [test] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [test] SET ARITHABORT OFF 
GO
ALTER DATABASE [test] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [test] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [test] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [test] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [test] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [test] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [test] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [test] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [test] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [test] SET  DISABLE_BROKER 
GO
ALTER DATABASE [test] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [test] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [test] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [test] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [test] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [test] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [test] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [test] SET RECOVERY FULL 
GO
ALTER DATABASE [test] SET  MULTI_USER 
GO
ALTER DATABASE [test] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [test] SET DB_CHAINING OFF 
GO
ALTER DATABASE [test] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [test] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [test] SET DELAYED_DURABILITY = DISABLED 
GO
USE [test]
GO
/****** Object:  User [sa]    Script Date: 5/12/2017 7:57:48 AM ******/
CREATE USER [sa] FOR LOGIN [sa] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [gkang]    Script Date: 5/12/2017 7:57:48 AM ******/
CREATE USER [gkang] FOR LOGIN [gkang] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [aquach]    Script Date: 5/12/2017 7:57:48 AM ******/
CREATE USER [aquach] FOR LOGIN [aquach] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [ahuang]    Script Date: 5/12/2017 7:57:48 AM ******/
CREATE USER [ahuang] FOR LOGIN [ahuang] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [sa]
GO
ALTER ROLE [db_owner] ADD MEMBER [gkang]
GO
ALTER ROLE [db_owner] ADD MEMBER [aquach]
GO
ALTER ROLE [db_owner] ADD MEMBER [ahuang]
GO
/****** Object:  Table [dbo].[ApplicationSettings]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ApplicationSettings](
	[Name] [varchar](255) NOT NULL,
	[Value] [varchar](255) NOT NULL,
 CONSTRAINT [PK_ApplicationSettings] PRIMARY KEY CLUSTERED 
(
	[Name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[EmailRecipients]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EmailRecipients](
	[EmailRecipientPK] [int] IDENTITY(1,1) NOT NULL,
	[RecipientTypeString] [varchar](255) NOT NULL,
	[EmailAddress] [varchar](255) NOT NULL,
	[Name] [varchar](255) NOT NULL,
	[ScheduledEmailFK] [int] NOT NULL,
 CONSTRAINT [PK_EmailRecipients] PRIMARY KEY CLUSTERED 
(
	[EmailRecipientPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[IMEData]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[IMEData](
	[IMEPK] [int] IDENTITY(1,1) NOT NULL,
	[IMENumberFK] [int] NOT NULL,
	[InspectorFK] [int] NOT NULL,
	[MethaneLevel] [int] NOT NULL,
	[DateTime] [smalldatetime] NOT NULL,
	[Description] [varchar](8000) NOT NULL,
 CONSTRAINT [PK_IMEData] PRIMARY KEY CLUSTERED 
(
	[IMEPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[IMENumbers]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[IMENumbers](
	[IMENumberPK] [int] IDENTITY(1,1) NOT NULL,
	[SiteString] [varchar](255) NOT NULL,
	[DateCode] [smallint] NOT NULL,
	[Sequence] [smallint] NOT NULL,
	[StatusString] [varchar](255) NOT NULL,
	[UnverifiedDataSetFK] [int] NULL,
 CONSTRAINT [PK_IMENumbers] PRIMARY KEY CLUSTERED 
(
	[IMENumberPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [IX_IMENumbers] UNIQUE NONCLUSTERED 
(
	[SiteString] ASC,
	[DateCode] ASC,
	[Sequence] ASC,
	[StatusString] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[IMENumbersXRefMonitoringPoints]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[IMENumbersXRefMonitoringPoints](
	[IMENumberFK] [int] NOT NULL,
	[MonitoringPointString] [varchar](255) NOT NULL,
 CONSTRAINT [PK_MonitoringPointsXRefIMENumbers] PRIMARY KEY CLUSTERED 
(
	[MonitoringPointString] ASC,
	[IMENumberFK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[IMERepairData]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[IMERepairData](
	[IMERepairPK] [int] IDENTITY(1,1) NOT NULL,
	[DateTime] [smalldatetime] NOT NULL,
	[Water] [bit] NOT NULL,
	[Soil] [bit] NOT NULL,
	[Description] [varchar](8000) NOT NULL,
	[Crew] [varchar](50) NOT NULL,
	[IMEFK] [int] NOT NULL,
 CONSTRAINT [PK_IMERepair] PRIMARY KEY CLUSTERED 
(
	[IMERepairPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[IndividualReportQueries]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[IndividualReportQueries](
	[ReportQueryFK] [int] NOT NULL,
	[LastQueried] [smalldatetime] NULL,
 CONSTRAINT [IX_IndividualReportQueries] UNIQUE NONCLUSTERED 
(
	[ReportQueryFK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[InstantaneousData]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InstantaneousData](
	[InstantaneousPK] [int] IDENTITY(1,1) NOT NULL,
	[MonitoringPointString] [varchar](255) NOT NULL,
	[InstrumentFK] [int] NOT NULL,
	[InspectorFK] [int] NOT NULL,
	[BarometricPressure] [smallint] NOT NULL,
	[MethaneLevel] [int] NOT NULL,
	[StartTime] [smalldatetime] NOT NULL,
	[EndTime] [smalldatetime] NOT NULL,
 CONSTRAINT [PK_InstantaneousTable] PRIMARY KEY CLUSTERED 
(
	[InstantaneousPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[InstantaneousDataXRefIMENumbers]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InstantaneousDataXRefIMENumbers](
	[InstantaneousFK] [int] NOT NULL,
	[IMENumberFK] [int] NOT NULL,
 CONSTRAINT [PK_InstantaneousDataXRefIMENumbers] PRIMARY KEY CLUSTERED 
(
	[InstantaneousFK] ASC,
	[IMENumberFK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Instruments]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Instruments](
	[InstrumentPK] [int] IDENTITY(1,1) NOT NULL,
	[SerialNumber] [varchar](255) NOT NULL,
	[InstrumentTypeFK] [int] NOT NULL,
	[InstrumentStatusString] [varchar](255) NOT NULL,
	[ServiceDueDate] [smalldatetime] NULL,
	[LastServiceDate] [smalldatetime] NULL,
	[PurchaseDate] [smalldatetime] NULL,
	[SiteString] [varchar](255) NULL,
	[InventoryNumber] [varchar](255) NULL,
	[Description] [varchar](8000) NULL,
	[CreatedByFK] [int] NULL,
	[CreatedDate] [smalldatetime] NULL,
	[ModifiedByFK] [int] NULL,
	[ModifiedDate] [smalldatetime] NULL,
 CONSTRAINT [PK_Instruments] PRIMARY KEY CLUSTERED 
(
	[InstrumentPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [IX_Instruments] UNIQUE NONCLUSTERED 
(
	[SerialNumber] ASC,
	[InstrumentTypeFK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[InstrumentTypes]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InstrumentTypes](
	[InstrumentTypePK] [int] IDENTITY(1,1) NOT NULL,
	[Type] [varchar](50) NOT NULL,
	[Manufacturer] [varchar](50) NOT NULL,
	[Description] [varchar](8000) NOT NULL,
	[Instantaneous] [bit] NOT NULL,
	[Probe] [bit] NOT NULL,
	[MethanePercent] [bit] NOT NULL,
	[MethanePPM] [bit] NOT NULL,
	[HydrogenSulfidePPM] [bit] NOT NULL,
	[OxygenPercent] [bit] NOT NULL,
	[CarbonDioxidePercent] [bit] NOT NULL,
	[NitrogenPercent] [bit] NOT NULL,
	[Pressure] [bit] NOT NULL,
	[CreatedByFK] [int] NULL,
	[CreatedDate] [smalldatetime] NULL,
	[ModifiedByFK] [int] NULL,
	[ModifiedDate] [smalldatetime] NULL,
 CONSTRAINT [PK_InstrumentTypes] PRIMARY KEY CLUSTERED 
(
	[InstrumentTypePK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [IX_InstrumentTypes] UNIQUE NONCLUSTERED 
(
	[Type] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[IntegratedData]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[IntegratedData](
	[IntegratedPK] [int] IDENTITY(1,1) NOT NULL,
	[MonitoringPointString] [varchar](255) NOT NULL,
	[InstrumentFK] [int] NOT NULL,
	[InspectorFK] [int] NOT NULL,
	[BagNumber] [smallint] NOT NULL,
	[Volume] [smallint] NOT NULL,
	[SampleId] [varchar](255) NOT NULL,
	[BarometricPressure] [smallint] NOT NULL,
	[MethaneLevel] [int] NOT NULL,
	[StartTime] [smalldatetime] NOT NULL,
	[EndTime] [smalldatetime] NOT NULL,
 CONSTRAINT [PK_IntegratedData] PRIMARY KEY CLUSTERED 
(
	[IntegratedPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ISEData]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ISEData](
	[ISEPK] [int] IDENTITY(1,1) NOT NULL,
	[ISENumberFK] [int] NOT NULL,
	[InspectorFK] [int] NOT NULL,
	[MethaneLevel] [int] NOT NULL,
	[DateTime] [smalldatetime] NOT NULL,
	[Description] [varchar](8000) NOT NULL,
 CONSTRAINT [PK_ISEData] PRIMARY KEY CLUSTERED 
(
	[ISEPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ISENumbers]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ISENumbers](
	[ISENumberPK] [int] IDENTITY(1,1) NOT NULL,
	[SiteString] [varchar](255) NOT NULL,
	[DateCode] [smallint] NOT NULL,
	[Sequence] [smallint] NOT NULL,
	[StatusString] [varchar](255) NOT NULL,
	[MonitoringPointString] [varchar](255) NOT NULL,
	[UnverifiedDataSetFK] [int] NULL,
 CONSTRAINT [PK_ISENumbers] PRIMARY KEY CLUSTERED 
(
	[ISENumberPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ISERepairData]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ISERepairData](
	[ISERepairPK] [int] IDENTITY(1,1) NOT NULL,
	[DateTime] [smalldatetime] NOT NULL,
	[Water] [bit] NOT NULL,
	[Soil] [bit] NOT NULL,
	[Description] [varchar](8000) NOT NULL,
	[Crew] [varchar](50) NOT NULL,
	[ISEFK] [int] NOT NULL,
 CONSTRAINT [PK_ISERepairData] PRIMARY KEY CLUSTERED 
(
	[ISERepairPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ProbeData]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProbeData](
	[ProbePK] [int] IDENTITY(1,1) NOT NULL,
	[MonitoringPointString] [varchar](255) NOT NULL,
	[Date] [date] NOT NULL,
	[MethaneLevel] [int] NOT NULL,
	[PressureLevel] [int] NOT NULL,
	[Description] [varchar](8000) NOT NULL,
	[BarometricPressure] [smallint] NOT NULL,
	[Accessible] [bit] NOT NULL,
 CONSTRAINT [PK_ProbeData] PRIMARY KEY CLUSTERED 
(
	[ProbePK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ProbeDataXRefInspectors]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProbeDataXRefInspectors](
	[ProbeFK] [int] NOT NULL,
	[InspectorFK] [int] NOT NULL,
 CONSTRAINT [PK_ProbeDataXRefInspectors] PRIMARY KEY CLUSTERED 
(
	[ProbeFK] ASC,
	[InspectorFK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ReportQueries]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ReportQueries](
	[ReportQueryPK] [int] IDENTITY(1,1) NOT NULL,
	[ReportTypeString] [varchar](255) NOT NULL,
	[ReportPeriodString] [varchar](255) NOT NULL,
	[StartDate] [date] NULL,
	[EndDate] [date] NULL,
	[SiteString] [varchar](255) NOT NULL,
	[DateCreated] [smalldatetime] NULL,
 CONSTRAINT [PK_ReportQueries] PRIMARY KEY CLUSTERED 
(
	[ReportQueryPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ReportQueriesXRefExceedanceTypes]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ReportQueriesXRefExceedanceTypes](
	[ReportQueryFK] [int] NOT NULL,
	[ExceedanceTypeString] [varchar](255) NOT NULL,
 CONSTRAINT [PK_ReportQueriesXRefExceedanceTypes] PRIMARY KEY CLUSTERED 
(
	[ReportQueryFK] ASC,
	[ExceedanceTypeString] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ScheduledEmails]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ScheduledEmails](
	[ScheduledEmailPK] [int] IDENTITY(1,1) NOT NULL,
	[ScheduleFK] [int] NOT NULL,
	[Subject] [varchar](255) NOT NULL,
	[Body] [varchar](max) NOT NULL,
 CONSTRAINT [PK_ScheduledReports] PRIMARY KEY CLUSTERED 
(
	[ScheduledEmailPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ScheduledEmailsXRefEmailRecipients]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ScheduledEmailsXRefEmailRecipients](
	[ScheduledEmailFK] [int] NOT NULL,
	[EmailRecipientFK] [int] NOT NULL,
 CONSTRAINT [PK_ScheduledReportsXRefEmailRecipients] PRIMARY KEY CLUSTERED 
(
	[ScheduledEmailFK] ASC,
	[EmailRecipientFK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ScheduledEmailsXRefUserGroups]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ScheduledEmailsXRefUserGroups](
	[ScheduledEmailFK] [int] NOT NULL,
	[UserGroupFK] [int] NOT NULL,
 CONSTRAINT [PK_ScheduledReportsXRefUserGroups] PRIMARY KEY CLUSTERED 
(
	[ScheduledEmailFK] ASC,
	[UserGroupFK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ScheduledNotifications]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ScheduledNotifications](
	[Test] [varchar](255) NOT NULL,
	[ScheduledEmailFK] [int] NOT NULL,
 CONSTRAINT [IX_ScheduledNotifications] UNIQUE NONCLUSTERED 
(
	[Test] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ScheduledReportQueries]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ScheduledReportQueries](
	[ReportQueryFK] [int] NOT NULL,
	[ScheduledReportFK] [int] NOT NULL,
 CONSTRAINT [IX_ScheduledReportQueries] UNIQUE NONCLUSTERED 
(
	[ReportQueryFK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ScheduledReports]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ScheduledReports](
	[ScheduledEmailFK] [int] NOT NULL,
 CONSTRAINT [IX_ScheduledReports] UNIQUE NONCLUSTERED 
(
	[ScheduledEmailFK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Schedules]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Schedules](
	[SchedulePK] [int] IDENTITY(1,1) NOT NULL,
	[RecurrenceString] [varchar](255) NOT NULL,
	[Offset] [smalldatetime] NOT NULL,
	[PeriodBoundaryString] [varchar](255) NOT NULL,
	[Frequency] [int] NOT NULL,
	[Active] [bit] NOT NULL,
	[LastOccurrence] [smalldatetime] NULL,
 CONSTRAINT [PK_Schedules] PRIMARY KEY CLUSTERED 
(
	[SchedulePK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UnverifiedDataSets]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UnverifiedDataSets](
	[UnverifiedDataSetPK] [int] IDENTITY(1,1) NOT NULL,
	[Filename] [varchar](255) NULL,
	[InspectorFK] [int] NOT NULL,
	[SiteString] [varchar](255) NOT NULL,
	[UploadedByFK] [int] NULL,
	[UploadedDate] [smalldatetime] NULL,
	[ModifiedByFK] [int] NULL,
	[ModifiedDate] [smalldatetime] NULL,
 CONSTRAINT [PK_UnverifiedDataSets] PRIMARY KEY CLUSTERED 
(
	[UnverifiedDataSetPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UnverifiedInstantaneousData]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UnverifiedInstantaneousData](
	[UnverifiedInstantaneousPK] [int] IDENTITY(1,1) NOT NULL,
	[MonitoringPointString] [varchar](255) NOT NULL,
	[InstrumentFK] [int] NULL,
	[BarometricPressure] [smallint] NULL,
	[MethaneLevel] [int] NOT NULL,
	[StartTime] [smalldatetime] NOT NULL,
	[EndTime] [smalldatetime] NOT NULL,
	[UnverifiedDataSetFK] [int] NOT NULL,
 CONSTRAINT [PK_UnverifiedInstantaneousData] PRIMARY KEY CLUSTERED 
(
	[UnverifiedInstantaneousPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UnverifiedInstantaneousDataXRefIMENumbers]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UnverifiedInstantaneousDataXRefIMENumbers](
	[UnverifiedInstantaneousFK] [int] NOT NULL,
	[IMENumberFK] [int] NOT NULL,
 CONSTRAINT [PK_UnverifiedInstantaneousDataXRefIMENumbers] PRIMARY KEY CLUSTERED 
(
	[UnverifiedInstantaneousFK] ASC,
	[IMENumberFK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UnverifiedIntegratedData]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UnverifiedIntegratedData](
	[UnverifiedIntegratedPK] [int] IDENTITY(1,1) NOT NULL,
	[MonitoringPointString] [varchar](255) NOT NULL,
	[InstrumentFK] [int] NULL,
	[SampleId] [varchar](255) NOT NULL,
	[BagNumber] [smallint] NULL,
	[Volume] [smallint] NULL,
	[BarometricPressure] [smallint] NULL,
	[MethaneLevel] [int] NOT NULL,
	[StartTime] [smalldatetime] NOT NULL,
	[EndTime] [smalldatetime] NOT NULL,
	[UnverifiedDataSetFK] [int] NOT NULL,
 CONSTRAINT [PK_UnverifiedIntegratedData] PRIMARY KEY CLUSTERED 
(
	[UnverifiedIntegratedPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UnverifiedProbeData]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UnverifiedProbeData](
	[UnverifiedProbePK] [int] IDENTITY(1,1) NOT NULL,
	[MonitoringPointString] [varchar](255) NOT NULL,
	[Date] [date] NOT NULL,
	[MethaneLevel] [int] NOT NULL,
	[PressureLevel] [int] NOT NULL,
	[Description] [varchar](8000) NOT NULL,
	[BarometricPressure] [smallint] NOT NULL,
	[Accessible] [bit] NOT NULL,
	[UnverifiedDataSetFK] [int] NOT NULL,
 CONSTRAINT [PK_UnverifiedProbeData] PRIMARY KEY CLUSTERED 
(
	[UnverifiedProbePK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UnverifiedProbeDataXRefInspectors]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UnverifiedProbeDataXRefInspectors](
	[UnverifiedProbeFK] [int] NOT NULL,
	[InspectorFK] [int] NOT NULL,
 CONSTRAINT [PK_UnverifiedProbeDataXRefInspectors] PRIMARY KEY CLUSTERED 
(
	[UnverifiedProbeFK] ASC,
	[InspectorFK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UnverifiedWarmspotData]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UnverifiedWarmspotData](
	[UnverifiedWarmspotPK] [int] IDENTITY(1,1) NOT NULL,
	[MonitoringPointString] [varchar](255) NOT NULL,
	[InstrumentFK] [int] NULL,
	[MethaneLevel] [int] NOT NULL,
	[Date] [date] NOT NULL,
	[Description] [varchar](8000) NOT NULL,
	[Size] [varchar](50) NOT NULL,
	[UnverifiedDataSetFK] [int] NOT NULL,
 CONSTRAINT [PK_UnverifiedWarmspotData] PRIMARY KEY CLUSTERED 
(
	[UnverifiedWarmspotPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UserActivity]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserActivity](
	[UserActivityPK] [int] IDENTITY(1,1) NOT NULL,
	[UserFK] [int] NOT NULL,
	[Date] [smalldatetime] NOT NULL,
	[Activity] [varchar](8000) NOT NULL,
 CONSTRAINT [PK_UserActivities] PRIMARY KEY CLUSTERED 
(
	[UserActivityPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UserGroups]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserGroups](
	[UserGroupPK] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](50) NOT NULL,
	[Description] [varchar](255) NULL,
	[CreatedByFK] [int] NULL,
	[CreatedDate] [smalldatetime] NULL,
	[ModifiedByFK] [int] NULL,
	[ModifiedDate] [smalldatetime] NULL,
 CONSTRAINT [PK_UserGroups] PRIMARY KEY CLUSTERED 
(
	[UserGroupPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [IX_UserGroups] UNIQUE NONCLUSTERED 
(
	[Name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UserGroupsXRefUserPermissions]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserGroupsXRefUserPermissions](
	[UserGroupFK] [int] NOT NULL,
	[UserPermissionString] [varchar](255) NOT NULL,
 CONSTRAINT [PK_UserGroupsXRefUserRoles] PRIMARY KEY CLUSTERED 
(
	[UserGroupFK] ASC,
	[UserPermissionString] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Users]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[UserPK] [int] IDENTITY(1,1) NOT NULL,
	[Username] [varchar](50) NOT NULL,
	[Password] [varchar](60) NOT NULL,
	[Firstname] [varchar](50) NOT NULL,
	[Middlename] [varchar](50) NOT NULL,
	[Lastname] [varchar](50) NOT NULL,
	[EmailAddress] [varchar](50) NOT NULL,
	[EmployeeId] [varchar](50) NOT NULL,
	[Enabled] [bit] NOT NULL,
	[LastLogin] [smalldatetime] NULL,
	[CreatedByFK] [int] NULL,
	[CreatedDate] [smalldatetime] NULL,
	[ModifiedByFK] [int] NULL,
	[ModifiedDate] [smalldatetime] NULL,
 CONSTRAINT [PK_Users_1] PRIMARY KEY CLUSTERED 
(
	[UserPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [IX_Users] UNIQUE NONCLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UsersXRefUserGroups]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UsersXRefUserGroups](
	[UserFK] [int] NOT NULL,
	[UserGroupFK] [int] NOT NULL,
 CONSTRAINT [PK_UsersXRefUserGroups] PRIMARY KEY CLUSTERED 
(
	[UserFK] ASC,
	[UserGroupFK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[WarmspotData]    Script Date: 5/12/2017 7:57:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[WarmspotData](
	[WarmspotPK] [int] IDENTITY(1,1) NOT NULL,
	[MonitoringPointString] [varchar](255) NOT NULL,
	[InstrumentFK] [int] NOT NULL,
	[InspectorFK] [int] NOT NULL,
	[MethaneLevel] [int] NOT NULL,
	[Date] [date] NOT NULL,
	[Description] [varchar](8000) NOT NULL,
	[Size] [varchar](50) NOT NULL,
 CONSTRAINT [PK_WarmspotData] PRIMARY KEY CLUSTERED 
(
	[WarmspotPK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[EmailRecipients]  WITH CHECK ADD  CONSTRAINT [FK_EmailRecipients_ScheduledEmails] FOREIGN KEY([ScheduledEmailFK])
REFERENCES [dbo].[ScheduledEmails] ([ScheduledEmailPK])
GO
ALTER TABLE [dbo].[EmailRecipients] CHECK CONSTRAINT [FK_EmailRecipients_ScheduledEmails]
GO
ALTER TABLE [dbo].[IMEData]  WITH CHECK ADD  CONSTRAINT [FK_IMEData_IMENumbers] FOREIGN KEY([IMENumberFK])
REFERENCES [dbo].[IMENumbers] ([IMENumberPK])
GO
ALTER TABLE [dbo].[IMEData] CHECK CONSTRAINT [FK_IMEData_IMENumbers]
GO
ALTER TABLE [dbo].[IMEData]  WITH CHECK ADD  CONSTRAINT [FK_IMEData_Users] FOREIGN KEY([InspectorFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[IMEData] CHECK CONSTRAINT [FK_IMEData_Users]
GO
ALTER TABLE [dbo].[IMENumbers]  WITH CHECK ADD  CONSTRAINT [FK_IMENumbers_UnverifiedDataSets] FOREIGN KEY([UnverifiedDataSetFK])
REFERENCES [dbo].[UnverifiedDataSets] ([UnverifiedDataSetPK])
GO
ALTER TABLE [dbo].[IMENumbers] CHECK CONSTRAINT [FK_IMENumbers_UnverifiedDataSets]
GO
ALTER TABLE [dbo].[IMENumbersXRefMonitoringPoints]  WITH CHECK ADD  CONSTRAINT [FK_IMENumbersXRefMonitoringPoints_IMENumbers] FOREIGN KEY([IMENumberFK])
REFERENCES [dbo].[IMENumbers] ([IMENumberPK])
GO
ALTER TABLE [dbo].[IMENumbersXRefMonitoringPoints] CHECK CONSTRAINT [FK_IMENumbersXRefMonitoringPoints_IMENumbers]
GO
ALTER TABLE [dbo].[IMERepairData]  WITH CHECK ADD  CONSTRAINT [FK_IMERepairData_IMEData] FOREIGN KEY([IMEFK])
REFERENCES [dbo].[IMEData] ([IMEPK])
GO
ALTER TABLE [dbo].[IMERepairData] CHECK CONSTRAINT [FK_IMERepairData_IMEData]
GO
ALTER TABLE [dbo].[IndividualReportQueries]  WITH CHECK ADD  CONSTRAINT [FK_IndividualReportQueries_ReportQueries] FOREIGN KEY([ReportQueryFK])
REFERENCES [dbo].[ReportQueries] ([ReportQueryPK])
GO
ALTER TABLE [dbo].[IndividualReportQueries] CHECK CONSTRAINT [FK_IndividualReportQueries_ReportQueries]
GO
ALTER TABLE [dbo].[InstantaneousData]  WITH CHECK ADD  CONSTRAINT [FK_InstantaneousData_InstantaneousData] FOREIGN KEY([InstantaneousPK])
REFERENCES [dbo].[InstantaneousData] ([InstantaneousPK])
GO
ALTER TABLE [dbo].[InstantaneousData] CHECK CONSTRAINT [FK_InstantaneousData_InstantaneousData]
GO
ALTER TABLE [dbo].[InstantaneousData]  WITH CHECK ADD  CONSTRAINT [FK_InstantaneousData_Instruments] FOREIGN KEY([InstrumentFK])
REFERENCES [dbo].[Instruments] ([InstrumentPK])
GO
ALTER TABLE [dbo].[InstantaneousData] CHECK CONSTRAINT [FK_InstantaneousData_Instruments]
GO
ALTER TABLE [dbo].[InstantaneousData]  WITH CHECK ADD  CONSTRAINT [FK_InstantaneousData_Users] FOREIGN KEY([InspectorFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[InstantaneousData] CHECK CONSTRAINT [FK_InstantaneousData_Users]
GO
ALTER TABLE [dbo].[InstantaneousDataXRefIMENumbers]  WITH CHECK ADD  CONSTRAINT [FK_InstantaneousDataXRefIMENumbers_IMENumbers] FOREIGN KEY([IMENumberFK])
REFERENCES [dbo].[IMENumbers] ([IMENumberPK])
GO
ALTER TABLE [dbo].[InstantaneousDataXRefIMENumbers] CHECK CONSTRAINT [FK_InstantaneousDataXRefIMENumbers_IMENumbers]
GO
ALTER TABLE [dbo].[InstantaneousDataXRefIMENumbers]  WITH CHECK ADD  CONSTRAINT [FK_InstantaneousDataXRefIMENumbers_InstantaneousData] FOREIGN KEY([InstantaneousFK])
REFERENCES [dbo].[InstantaneousData] ([InstantaneousPK])
GO
ALTER TABLE [dbo].[InstantaneousDataXRefIMENumbers] CHECK CONSTRAINT [FK_InstantaneousDataXRefIMENumbers_InstantaneousData]
GO
ALTER TABLE [dbo].[Instruments]  WITH CHECK ADD  CONSTRAINT [FK_Instruments_InstrumentTypes] FOREIGN KEY([InstrumentTypeFK])
REFERENCES [dbo].[InstrumentTypes] ([InstrumentTypePK])
GO
ALTER TABLE [dbo].[Instruments] CHECK CONSTRAINT [FK_Instruments_InstrumentTypes]
GO
ALTER TABLE [dbo].[Instruments]  WITH CHECK ADD  CONSTRAINT [FK_Instruments_Users] FOREIGN KEY([CreatedByFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[Instruments] CHECK CONSTRAINT [FK_Instruments_Users]
GO
ALTER TABLE [dbo].[Instruments]  WITH CHECK ADD  CONSTRAINT [FK_Instruments_Users1] FOREIGN KEY([ModifiedByFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[Instruments] CHECK CONSTRAINT [FK_Instruments_Users1]
GO
ALTER TABLE [dbo].[InstrumentTypes]  WITH CHECK ADD  CONSTRAINT [FK_InstrumentTypes_Users] FOREIGN KEY([ModifiedByFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[InstrumentTypes] CHECK CONSTRAINT [FK_InstrumentTypes_Users]
GO
ALTER TABLE [dbo].[InstrumentTypes]  WITH CHECK ADD  CONSTRAINT [FK_InstrumentTypes_Users1] FOREIGN KEY([CreatedByFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[InstrumentTypes] CHECK CONSTRAINT [FK_InstrumentTypes_Users1]
GO
ALTER TABLE [dbo].[IntegratedData]  WITH CHECK ADD  CONSTRAINT [FK_IntegratedData_Instruments] FOREIGN KEY([InstrumentFK])
REFERENCES [dbo].[Instruments] ([InstrumentPK])
GO
ALTER TABLE [dbo].[IntegratedData] CHECK CONSTRAINT [FK_IntegratedData_Instruments]
GO
ALTER TABLE [dbo].[IntegratedData]  WITH CHECK ADD  CONSTRAINT [FK_IntegratedData_Users] FOREIGN KEY([InspectorFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[IntegratedData] CHECK CONSTRAINT [FK_IntegratedData_Users]
GO
ALTER TABLE [dbo].[ISEData]  WITH CHECK ADD  CONSTRAINT [FK_ISEData_ISENumbers] FOREIGN KEY([ISENumberFK])
REFERENCES [dbo].[ISENumbers] ([ISENumberPK])
GO
ALTER TABLE [dbo].[ISEData] CHECK CONSTRAINT [FK_ISEData_ISENumbers]
GO
ALTER TABLE [dbo].[ISEData]  WITH CHECK ADD  CONSTRAINT [FK_ISEData_Users] FOREIGN KEY([InspectorFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[ISEData] CHECK CONSTRAINT [FK_ISEData_Users]
GO
ALTER TABLE [dbo].[ISENumbers]  WITH CHECK ADD  CONSTRAINT [FK_ISENumbers_UnverifiedDataSets] FOREIGN KEY([UnverifiedDataSetFK])
REFERENCES [dbo].[UnverifiedDataSets] ([UnverifiedDataSetPK])
GO
ALTER TABLE [dbo].[ISENumbers] CHECK CONSTRAINT [FK_ISENumbers_UnverifiedDataSets]
GO
ALTER TABLE [dbo].[ISERepairData]  WITH CHECK ADD  CONSTRAINT [FK_ISERepairData_ISEData] FOREIGN KEY([ISEFK])
REFERENCES [dbo].[ISEData] ([ISEPK])
GO
ALTER TABLE [dbo].[ISERepairData] CHECK CONSTRAINT [FK_ISERepairData_ISEData]
GO
ALTER TABLE [dbo].[ProbeDataXRefInspectors]  WITH CHECK ADD  CONSTRAINT [FK_ProbeDataXRefInspectors_ProbeData] FOREIGN KEY([ProbeFK])
REFERENCES [dbo].[ProbeData] ([ProbePK])
GO
ALTER TABLE [dbo].[ProbeDataXRefInspectors] CHECK CONSTRAINT [FK_ProbeDataXRefInspectors_ProbeData]
GO
ALTER TABLE [dbo].[ProbeDataXRefInspectors]  WITH CHECK ADD  CONSTRAINT [FK_ProbeDataXRefInspectors_Users] FOREIGN KEY([InspectorFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[ProbeDataXRefInspectors] CHECK CONSTRAINT [FK_ProbeDataXRefInspectors_Users]
GO
ALTER TABLE [dbo].[ReportQueriesXRefExceedanceTypes]  WITH CHECK ADD  CONSTRAINT [FK_ReportQueriesXRefExceedanceTypes_ReportQueries] FOREIGN KEY([ReportQueryFK])
REFERENCES [dbo].[ReportQueries] ([ReportQueryPK])
GO
ALTER TABLE [dbo].[ReportQueriesXRefExceedanceTypes] CHECK CONSTRAINT [FK_ReportQueriesXRefExceedanceTypes_ReportQueries]
GO
ALTER TABLE [dbo].[ScheduledEmails]  WITH CHECK ADD  CONSTRAINT [FK_ScheduledEmails_Schedules] FOREIGN KEY([ScheduleFK])
REFERENCES [dbo].[Schedules] ([SchedulePK])
GO
ALTER TABLE [dbo].[ScheduledEmails] CHECK CONSTRAINT [FK_ScheduledEmails_Schedules]
GO
ALTER TABLE [dbo].[ScheduledEmailsXRefEmailRecipients]  WITH CHECK ADD  CONSTRAINT [FK_ScheduledEmailsXRefEmailRecipients_ScheduledReports] FOREIGN KEY([ScheduledEmailFK])
REFERENCES [dbo].[ScheduledEmails] ([ScheduledEmailPK])
GO
ALTER TABLE [dbo].[ScheduledEmailsXRefEmailRecipients] CHECK CONSTRAINT [FK_ScheduledEmailsXRefEmailRecipients_ScheduledReports]
GO
ALTER TABLE [dbo].[ScheduledEmailsXRefEmailRecipients]  WITH CHECK ADD  CONSTRAINT [FK_ScheduledReportsXRefEmailRecipients_EmailRecipients] FOREIGN KEY([EmailRecipientFK])
REFERENCES [dbo].[EmailRecipients] ([EmailRecipientPK])
GO
ALTER TABLE [dbo].[ScheduledEmailsXRefEmailRecipients] CHECK CONSTRAINT [FK_ScheduledReportsXRefEmailRecipients_EmailRecipients]
GO
ALTER TABLE [dbo].[ScheduledEmailsXRefUserGroups]  WITH CHECK ADD  CONSTRAINT [FK_ScheduledEmailsXRefUserGroups_ScheduledReports] FOREIGN KEY([ScheduledEmailFK])
REFERENCES [dbo].[ScheduledEmails] ([ScheduledEmailPK])
GO
ALTER TABLE [dbo].[ScheduledEmailsXRefUserGroups] CHECK CONSTRAINT [FK_ScheduledEmailsXRefUserGroups_ScheduledReports]
GO
ALTER TABLE [dbo].[ScheduledEmailsXRefUserGroups]  WITH CHECK ADD  CONSTRAINT [FK_ScheduledReportsXRefUserGroups_UserGroups] FOREIGN KEY([UserGroupFK])
REFERENCES [dbo].[UserGroups] ([UserGroupPK])
GO
ALTER TABLE [dbo].[ScheduledEmailsXRefUserGroups] CHECK CONSTRAINT [FK_ScheduledReportsXRefUserGroups_UserGroups]
GO
ALTER TABLE [dbo].[ScheduledNotifications]  WITH CHECK ADD  CONSTRAINT [FK_ScheduledNotifications_ScheduledEmails] FOREIGN KEY([ScheduledEmailFK])
REFERENCES [dbo].[ScheduledEmails] ([ScheduledEmailPK])
GO
ALTER TABLE [dbo].[ScheduledNotifications] CHECK CONSTRAINT [FK_ScheduledNotifications_ScheduledEmails]
GO
ALTER TABLE [dbo].[ScheduledReportQueries]  WITH CHECK ADD  CONSTRAINT [FK_ScheduledReportQueries_ReportQueries] FOREIGN KEY([ReportQueryFK])
REFERENCES [dbo].[ReportQueries] ([ReportQueryPK])
GO
ALTER TABLE [dbo].[ScheduledReportQueries] CHECK CONSTRAINT [FK_ScheduledReportQueries_ReportQueries]
GO
ALTER TABLE [dbo].[ScheduledReportQueries]  WITH CHECK ADD  CONSTRAINT [FK_ScheduledReportQueries_ScheduledReports] FOREIGN KEY([ScheduledReportFK])
REFERENCES [dbo].[ScheduledReports] ([ScheduledEmailFK])
GO
ALTER TABLE [dbo].[ScheduledReportQueries] CHECK CONSTRAINT [FK_ScheduledReportQueries_ScheduledReports]
GO
ALTER TABLE [dbo].[ScheduledReports]  WITH CHECK ADD  CONSTRAINT [FK_ScheduledReports_ScheduledEmails] FOREIGN KEY([ScheduledEmailFK])
REFERENCES [dbo].[ScheduledEmails] ([ScheduledEmailPK])
GO
ALTER TABLE [dbo].[ScheduledReports] CHECK CONSTRAINT [FK_ScheduledReports_ScheduledEmails]
GO
ALTER TABLE [dbo].[UnverifiedDataSets]  WITH CHECK ADD  CONSTRAINT [FK_UnverifiedDataSets_Users] FOREIGN KEY([InspectorFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[UnverifiedDataSets] CHECK CONSTRAINT [FK_UnverifiedDataSets_Users]
GO
ALTER TABLE [dbo].[UnverifiedDataSets]  WITH CHECK ADD  CONSTRAINT [FK_UnverifiedDataSets_Users1] FOREIGN KEY([ModifiedByFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[UnverifiedDataSets] CHECK CONSTRAINT [FK_UnverifiedDataSets_Users1]
GO
ALTER TABLE [dbo].[UnverifiedDataSets]  WITH CHECK ADD  CONSTRAINT [FK_UnverifiedDataSets_Users2] FOREIGN KEY([UploadedByFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[UnverifiedDataSets] CHECK CONSTRAINT [FK_UnverifiedDataSets_Users2]
GO
ALTER TABLE [dbo].[UnverifiedInstantaneousData]  WITH CHECK ADD  CONSTRAINT [FK_UnverifiedInstantaneousData_Instruments] FOREIGN KEY([InstrumentFK])
REFERENCES [dbo].[Instruments] ([InstrumentPK])
GO
ALTER TABLE [dbo].[UnverifiedInstantaneousData] CHECK CONSTRAINT [FK_UnverifiedInstantaneousData_Instruments]
GO
ALTER TABLE [dbo].[UnverifiedInstantaneousData]  WITH CHECK ADD  CONSTRAINT [FK_UnverifiedInstantaneousData_UnverifiedDataSets] FOREIGN KEY([UnverifiedDataSetFK])
REFERENCES [dbo].[UnverifiedDataSets] ([UnverifiedDataSetPK])
GO
ALTER TABLE [dbo].[UnverifiedInstantaneousData] CHECK CONSTRAINT [FK_UnverifiedInstantaneousData_UnverifiedDataSets]
GO
ALTER TABLE [dbo].[UnverifiedInstantaneousDataXRefIMENumbers]  WITH CHECK ADD  CONSTRAINT [FK_UnverifiedInstantaneousDataXRefIMENumbers_IMENumbers] FOREIGN KEY([IMENumberFK])
REFERENCES [dbo].[IMENumbers] ([IMENumberPK])
GO
ALTER TABLE [dbo].[UnverifiedInstantaneousDataXRefIMENumbers] CHECK CONSTRAINT [FK_UnverifiedInstantaneousDataXRefIMENumbers_IMENumbers]
GO
ALTER TABLE [dbo].[UnverifiedInstantaneousDataXRefIMENumbers]  WITH CHECK ADD  CONSTRAINT [FK_UnverifiedInstantaneousDataXRefIMENumbers_UnverifiedInstantaneousData] FOREIGN KEY([UnverifiedInstantaneousFK])
REFERENCES [dbo].[UnverifiedInstantaneousData] ([UnverifiedInstantaneousPK])
GO
ALTER TABLE [dbo].[UnverifiedInstantaneousDataXRefIMENumbers] CHECK CONSTRAINT [FK_UnverifiedInstantaneousDataXRefIMENumbers_UnverifiedInstantaneousData]
GO
ALTER TABLE [dbo].[UnverifiedIntegratedData]  WITH CHECK ADD  CONSTRAINT [FK_UnverifiedIntegratedData_Instruments] FOREIGN KEY([InstrumentFK])
REFERENCES [dbo].[Instruments] ([InstrumentPK])
GO
ALTER TABLE [dbo].[UnverifiedIntegratedData] CHECK CONSTRAINT [FK_UnverifiedIntegratedData_Instruments]
GO
ALTER TABLE [dbo].[UnverifiedIntegratedData]  WITH CHECK ADD  CONSTRAINT [FK_UnverifiedIntegratedData_UnverifiedDataSets] FOREIGN KEY([UnverifiedDataSetFK])
REFERENCES [dbo].[UnverifiedDataSets] ([UnverifiedDataSetPK])
GO
ALTER TABLE [dbo].[UnverifiedIntegratedData] CHECK CONSTRAINT [FK_UnverifiedIntegratedData_UnverifiedDataSets]
GO
ALTER TABLE [dbo].[UnverifiedProbeData]  WITH CHECK ADD  CONSTRAINT [FK_UnverifiedProbeData_UnverifiedDataSets] FOREIGN KEY([UnverifiedDataSetFK])
REFERENCES [dbo].[UnverifiedDataSets] ([UnverifiedDataSetPK])
GO
ALTER TABLE [dbo].[UnverifiedProbeData] CHECK CONSTRAINT [FK_UnverifiedProbeData_UnverifiedDataSets]
GO
ALTER TABLE [dbo].[UnverifiedProbeDataXRefInspectors]  WITH CHECK ADD  CONSTRAINT [FK_UnverifiedProbeDataXRefInspectors_UnverifiedProbeData] FOREIGN KEY([UnverifiedProbeFK])
REFERENCES [dbo].[UnverifiedProbeData] ([UnverifiedProbePK])
GO
ALTER TABLE [dbo].[UnverifiedProbeDataXRefInspectors] CHECK CONSTRAINT [FK_UnverifiedProbeDataXRefInspectors_UnverifiedProbeData]
GO
ALTER TABLE [dbo].[UnverifiedProbeDataXRefInspectors]  WITH CHECK ADD  CONSTRAINT [FK_UnverifiedProbeDataXRefInspectors_Users] FOREIGN KEY([InspectorFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[UnverifiedProbeDataXRefInspectors] CHECK CONSTRAINT [FK_UnverifiedProbeDataXRefInspectors_Users]
GO
ALTER TABLE [dbo].[UnverifiedWarmspotData]  WITH CHECK ADD  CONSTRAINT [FK_UnverifiedWarmspotData_UnverifiedDataSets] FOREIGN KEY([UnverifiedDataSetFK])
REFERENCES [dbo].[UnverifiedDataSets] ([UnverifiedDataSetPK])
GO
ALTER TABLE [dbo].[UnverifiedWarmspotData] CHECK CONSTRAINT [FK_UnverifiedWarmspotData_UnverifiedDataSets]
GO
ALTER TABLE [dbo].[UserActivity]  WITH CHECK ADD  CONSTRAINT [FK_UserActivities_Users] FOREIGN KEY([UserFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[UserActivity] CHECK CONSTRAINT [FK_UserActivities_Users]
GO
ALTER TABLE [dbo].[UserGroups]  WITH CHECK ADD  CONSTRAINT [FK_UserGroups_Users] FOREIGN KEY([CreatedByFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[UserGroups] CHECK CONSTRAINT [FK_UserGroups_Users]
GO
ALTER TABLE [dbo].[UserGroups]  WITH CHECK ADD  CONSTRAINT [FK_UserGroups_Users1] FOREIGN KEY([ModifiedByFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[UserGroups] CHECK CONSTRAINT [FK_UserGroups_Users1]
GO
ALTER TABLE [dbo].[UserGroupsXRefUserPermissions]  WITH CHECK ADD  CONSTRAINT [FK_UserGroupsXRefUserRoles_UserGroups] FOREIGN KEY([UserGroupFK])
REFERENCES [dbo].[UserGroups] ([UserGroupPK])
GO
ALTER TABLE [dbo].[UserGroupsXRefUserPermissions] CHECK CONSTRAINT [FK_UserGroupsXRefUserRoles_UserGroups]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_Users_Users] FOREIGN KEY([ModifiedByFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_Users]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_Users_Users1] FOREIGN KEY([CreatedByFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_Users1]
GO
ALTER TABLE [dbo].[UsersXRefUserGroups]  WITH CHECK ADD  CONSTRAINT [FK_UsersXRefUserGroups_UserGroups] FOREIGN KEY([UserGroupFK])
REFERENCES [dbo].[UserGroups] ([UserGroupPK])
GO
ALTER TABLE [dbo].[UsersXRefUserGroups] CHECK CONSTRAINT [FK_UsersXRefUserGroups_UserGroups]
GO
ALTER TABLE [dbo].[UsersXRefUserGroups]  WITH CHECK ADD  CONSTRAINT [FK_UsersXRefUserGroups_Users] FOREIGN KEY([UserFK])
REFERENCES [dbo].[Users] ([UserPK])
GO
ALTER TABLE [dbo].[UsersXRefUserGroups] CHECK CONSTRAINT [FK_UsersXRefUserGroups_Users]
GO
/****** Object:  DdlTrigger [rds_deny_backups_trigger]    Script Date: 5/12/2017 7:57:49 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [rds_deny_backups_trigger] ON DATABASE WITH EXECUTE AS 'dbo' FOR
 ADD_ROLE_MEMBER, GRANT_DATABASE AS BEGIN
   SET NOCOUNT ON;
   SET ANSI_PADDING ON;
 
   DECLARE @data xml;
   DECLARE @user sysname;
   DECLARE @role sysname;
   DECLARE @type sysname;
   DECLARE @sql NVARCHAR(MAX);
   DECLARE @permissions TABLE(name sysname PRIMARY KEY);
   
   SELECT @data = EVENTDATA();
   SELECT @type = @data.value('(/EVENT_INSTANCE/EventType)[1]', 'sysname');
    
   IF @type = 'ADD_ROLE_MEMBER' BEGIN
      SELECT @user = @data.value('(/EVENT_INSTANCE/ObjectName)[1]', 'sysname'),
       @role = @data.value('(/EVENT_INSTANCE/RoleName)[1]', 'sysname');

      IF @role IN ('db_owner', 'db_backupoperator') BEGIN
         SELECT @sql = 'DENY BACKUP DATABASE, BACKUP LOG TO ' + QUOTENAME(@user);
         EXEC(@sql);
      END
   END ELSE IF @type = 'GRANT_DATABASE' BEGIN
      INSERT INTO @permissions(name)
      SELECT Permission.value('(text())[1]', 'sysname') FROM
       @data.nodes('/EVENT_INSTANCE/Permissions/Permission')
      AS DatabasePermissions(Permission);
      
      IF EXISTS (SELECT * FROM @permissions WHERE name IN ('BACKUP DATABASE',
       'BACKUP LOG'))
         RAISERROR('Cannot grant backup database or backup log', 15, 1) WITH LOG;       
   END
END


GO
ENABLE TRIGGER [rds_deny_backups_trigger] ON DATABASE
GO
USE [master]
GO
ALTER DATABASE [test] SET  READ_WRITE 
GO
