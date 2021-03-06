USE [master]
GO
/****** Object:  Database [presupuesto]    Script Date: 31/5/2017 6:26:52 p. m. ******/
CREATE DATABASE [presupuesto]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'presupuesto', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\DATA\presupuesto.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'presupuesto_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\DATA\presupuesto_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [presupuesto] SET COMPATIBILITY_LEVEL = 130
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [presupuesto].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [presupuesto] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [presupuesto] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [presupuesto] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [presupuesto] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [presupuesto] SET ARITHABORT OFF 
GO
ALTER DATABASE [presupuesto] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [presupuesto] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [presupuesto] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [presupuesto] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [presupuesto] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [presupuesto] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [presupuesto] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [presupuesto] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [presupuesto] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [presupuesto] SET  DISABLE_BROKER 
GO
ALTER DATABASE [presupuesto] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [presupuesto] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [presupuesto] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [presupuesto] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [presupuesto] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [presupuesto] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [presupuesto] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [presupuesto] SET RECOVERY FULL 
GO
ALTER DATABASE [presupuesto] SET  MULTI_USER 
GO
ALTER DATABASE [presupuesto] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [presupuesto] SET DB_CHAINING OFF 
GO
ALTER DATABASE [presupuesto] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [presupuesto] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [presupuesto] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [presupuesto] SET QUERY_STORE = OFF
GO
USE [presupuesto]
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [presupuesto]
GO
/****** Object:  Table [dbo].[categoria]    Script Date: 31/5/2017 6:26:52 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[categoria](
	[categoria_id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](50) NOT NULL,
	[descripcion] [nvarchar](250) NOT NULL,
	[user_id] [int] NOT NULL,
 CONSTRAINT [PK_categoria] PRIMARY KEY CLUSTERED 
(
	[categoria_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[cuenta]    Script Date: 31/5/2017 6:26:52 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cuenta](
	[cuenta_id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](50) NOT NULL,
	[monto] [decimal](9, 2) NOT NULL,
	[user_id] [int] NOT NULL,
 CONSTRAINT [PK_cuenta] PRIMARY KEY CLUSTERED 
(
	[cuenta_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[permiso]    Script Date: 31/5/2017 6:26:52 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[permiso](
	[permiso_id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](50) NOT NULL,
	[descripcion] [nvarchar](250) NOT NULL,
 CONSTRAINT [PK_permiso] PRIMARY KEY CLUSTERED 
(
	[permiso_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[permiso_rol]    Script Date: 31/5/2017 6:26:52 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[permiso_rol](
	[permiso_id] [int] NOT NULL,
	[rol_id] [int] NOT NULL,
 CONSTRAINT [PK_permiso_rol] PRIMARY KEY CLUSTERED 
(
	[permiso_id] ASC,
	[rol_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[permiso_usuario]    Script Date: 31/5/2017 6:26:52 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[permiso_usuario](
	[permiso_id] [int] NOT NULL,
	[user_id] [int] NOT NULL,
 CONSTRAINT [PK_permiso_usuario] PRIMARY KEY CLUSTERED 
(
	[permiso_id] ASC,
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[presupuesto]    Script Date: 31/5/2017 6:26:52 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[presupuesto](
	[presupuesto_id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](50) NOT NULL,
	[monto_limite] [decimal](9, 2) NOT NULL,
	[categoria_id] [int] NOT NULL,
 CONSTRAINT [PK_presupuesto] PRIMARY KEY CLUSTERED 
(
	[presupuesto_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[recuperarPass]    Script Date: 31/5/2017 6:26:52 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[recuperarPass](
	[fechahorasolicitud] [varchar](150) NULL,
	[codigoRecuperacion] [varchar](150) NULL,
	[user_id] [int] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[rol]    Script Date: 31/5/2017 6:26:52 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[rol](
	[rol_id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](50) NOT NULL,
	[descripcion] [nvarchar](250) NOT NULL,
 CONSTRAINT [PK_rol] PRIMARY KEY CLUSTERED 
(
	[rol_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[rol_usuario]    Script Date: 31/5/2017 6:26:52 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[rol_usuario](
	[rol_id] [int] NOT NULL,
	[user_id] [int] NOT NULL,
 CONSTRAINT [PK_rol_usuario] PRIMARY KEY CLUSTERED 
(
	[rol_id] ASC,
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[sub_categoria]    Script Date: 31/5/2017 6:26:52 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sub_categoria](
	[subcategoria_id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](50) NOT NULL,
	[descripcion] [nvarchar](250) NOT NULL,
	[categoria_id] [int] NOT NULL,
 CONSTRAINT [PK_sub_categoria] PRIMARY KEY CLUSTERED 
(
	[subcategoria_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[transaccion]    Script Date: 31/5/2017 6:26:52 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[transaccion](
	[transaccion_id] [int] NOT NULL,
	[tipo] [nvarchar](50) NOT NULL,
	[detalle] [nvarchar](250) NOT NULL,
	[cuenta_id] [int] NOT NULL,
	[categoria_id] [int] NOT NULL,
 CONSTRAINT [PK_transaccion] PRIMARY KEY CLUSTERED 
(
	[transaccion_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[usuario]    Script Date: 31/5/2017 6:26:52 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[usuario](
	[user_id] [int] IDENTITY(1,1) NOT NULL,
	[email] [varchar](150) NULL,
	[password] [varchar](150) NULL,
	[nombrecompleto] [varchar](150) NULL,
	[nombreusuario] [varchar](150) NULL,
	[fecha] [varchar](150) NULL,
 CONSTRAINT [PK_usuario] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[cuenta]  WITH CHECK ADD  CONSTRAINT [FK_cuenta_cuenta] FOREIGN KEY([user_id])
REFERENCES [dbo].[usuario] ([user_id])
GO
ALTER TABLE [dbo].[cuenta] CHECK CONSTRAINT [FK_cuenta_cuenta]
GO
ALTER TABLE [dbo].[permiso_rol]  WITH CHECK ADD  CONSTRAINT [FK_permiso_rol_permiso] FOREIGN KEY([permiso_id])
REFERENCES [dbo].[permiso] ([permiso_id])
GO
ALTER TABLE [dbo].[permiso_rol] CHECK CONSTRAINT [FK_permiso_rol_permiso]
GO
ALTER TABLE [dbo].[permiso_rol]  WITH CHECK ADD  CONSTRAINT [FK_permiso_rol_rol] FOREIGN KEY([rol_id])
REFERENCES [dbo].[rol] ([rol_id])
GO
ALTER TABLE [dbo].[permiso_rol] CHECK CONSTRAINT [FK_permiso_rol_rol]
GO
ALTER TABLE [dbo].[permiso_usuario]  WITH CHECK ADD  CONSTRAINT [FK_permiso_usuario_permiso] FOREIGN KEY([permiso_id])
REFERENCES [dbo].[permiso] ([permiso_id])
GO
ALTER TABLE [dbo].[permiso_usuario] CHECK CONSTRAINT [FK_permiso_usuario_permiso]
GO
ALTER TABLE [dbo].[permiso_usuario]  WITH CHECK ADD  CONSTRAINT [FK_permiso_usuario_usuario] FOREIGN KEY([user_id])
REFERENCES [dbo].[usuario] ([user_id])
GO
ALTER TABLE [dbo].[permiso_usuario] CHECK CONSTRAINT [FK_permiso_usuario_usuario]
GO
ALTER TABLE [dbo].[presupuesto]  WITH CHECK ADD  CONSTRAINT [FK_presupuesto_categoria] FOREIGN KEY([categoria_id])
REFERENCES [dbo].[categoria] ([categoria_id])
GO
ALTER TABLE [dbo].[presupuesto] CHECK CONSTRAINT [FK_presupuesto_categoria]
GO
ALTER TABLE [dbo].[recuperarPass]  WITH CHECK ADD  CONSTRAINT [FK_recuperarPass_usuario] FOREIGN KEY([user_id])
REFERENCES [dbo].[usuario] ([user_id])
GO
ALTER TABLE [dbo].[recuperarPass] CHECK CONSTRAINT [FK_recuperarPass_usuario]
GO
ALTER TABLE [dbo].[rol_usuario]  WITH CHECK ADD  CONSTRAINT [FK_rol_usuario_rol] FOREIGN KEY([rol_id])
REFERENCES [dbo].[rol] ([rol_id])
GO
ALTER TABLE [dbo].[rol_usuario] CHECK CONSTRAINT [FK_rol_usuario_rol]
GO
ALTER TABLE [dbo].[rol_usuario]  WITH CHECK ADD  CONSTRAINT [FK_rol_usuario_usuario] FOREIGN KEY([user_id])
REFERENCES [dbo].[usuario] ([user_id])
GO
ALTER TABLE [dbo].[rol_usuario] CHECK CONSTRAINT [FK_rol_usuario_usuario]
GO
ALTER TABLE [dbo].[sub_categoria]  WITH CHECK ADD  CONSTRAINT [FK_sub_categoria_categoria] FOREIGN KEY([categoria_id])
REFERENCES [dbo].[categoria] ([categoria_id])
GO
ALTER TABLE [dbo].[sub_categoria] CHECK CONSTRAINT [FK_sub_categoria_categoria]
GO
ALTER TABLE [dbo].[transaccion]  WITH CHECK ADD  CONSTRAINT [FK_transaccion_categoria] FOREIGN KEY([categoria_id])
REFERENCES [dbo].[categoria] ([categoria_id])
GO
ALTER TABLE [dbo].[transaccion] CHECK CONSTRAINT [FK_transaccion_categoria]
GO
ALTER TABLE [dbo].[transaccion]  WITH CHECK ADD  CONSTRAINT [FK_transaccion_cuenta] FOREIGN KEY([cuenta_id])
REFERENCES [dbo].[cuenta] ([cuenta_id])
GO
ALTER TABLE [dbo].[transaccion] CHECK CONSTRAINT [FK_transaccion_cuenta]
GO
USE [master]
GO
ALTER DATABASE [presupuesto] SET  READ_WRITE 
GO
