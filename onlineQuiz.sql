USE [online_quiz]
GO
/****** Object:  Table [dbo].[answer]    Script Date: 7/13/2021 3:41:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[answer](
	[questionId] [int] NOT NULL,
	[answer] [nvarchar](max) NOT NULL,
	[isTrue] [bit] NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_answer] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[question]    Script Date: 7/13/2021 3:41:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[question](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[question] [nvarchar](max) NOT NULL,
	[dateCreate] [date] NOT NULL,
	[userId] [int] NULL,
 CONSTRAINT [PK_question] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user]    Script Date: 7/13/2021 3:41:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[isTeacher] [bit] NOT NULL,
	[email] [nvarchar](200) NOT NULL,
 CONSTRAINT [PK_user] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[answer] ON 

INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (9, N'Bỏ cuộc', 1, 24)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (9, N'Cho tôi qua đi mà', 0, 25)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (9, N'Không thể qua', 0, 26)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (9, N'Câu này khó quá', 0, 27)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (10, N'con chim', 0, 28)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (10, N'con rắn', 0, 29)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (10, N'con người', 1, 30)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (10, N'con tê giác', 0, 31)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (11, N'HIV', 0, 32)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (11, N'Gãy tay', 1, 33)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (11, N'Siđa', 0, 34)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (11, N'Bệnh sĩ', 0, 35)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (13, N'Cái chổi', 0, 40)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (13, N'Cái trống', 0, 41)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (13, N'Cái gậy', 0, 42)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (13, N'Cái bàn chải đánh răng', 1, 43)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (15, N'Lab321', 0, 52)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (15, N'Lab123', 0, 53)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (15, N'Lab231', 1, 54)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (15, N'Lab132', 0, 55)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (16, N'3/2/1930', 1, 56)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (16, N'3/2/1945', 0, 57)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (16, N'23/3/1930', 0, 58)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (16, N'23/3/1945', 0, 59)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (17, N'Nguyễn Xuân Phúc', 1, 60)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (17, N'Nguyên Phú Trọng', 0, 61)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (17, N'Tô Lâm', 0, 62)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (17, N'Phan Văn Giang', 0, 63)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (18, N'Hà Nội', 1, 64)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (18, N'Tp Hồ Chí Minh', 0, 65)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (18, N'Đà Nẵng', 0, 66)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (18, N'Hải Phòng', 0, 67)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (20, N'Nhật Bản', 1, 72)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (20, N'Hàn Quốc', 0, 73)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (20, N'Trung Quốc', 0, 74)
INSERT [dbo].[answer] ([questionId], [answer], [isTrue], [id]) VALUES (20, N'Việt Nam', 0, 75)
SET IDENTITY_INSERT [dbo].[answer] OFF
GO
SET IDENTITY_INSERT [dbo].[question] ON 

INSERT [dbo].[question] ([id], [question], [dateCreate], [userId]) VALUES (9, N'Làm thế nào để qua được câu này?', CAST(N'2020-04-11' AS Date), 15)
INSERT [dbo].[question] ([id], [question], [dateCreate], [userId]) VALUES (10, N'Sở thú bị cháy, con gì chạy ra đầu tiên?', CAST(N'2020-04-11' AS Date), 15)
INSERT [dbo].[question] ([id], [question], [dateCreate], [userId]) VALUES (11, N'Bệnh gì bác sỹ bó tay?', CAST(N'2020-04-11' AS Date), 15)
INSERT [dbo].[question] ([id], [question], [dateCreate], [userId]) VALUES (13, N'Cái gì đánh cha, đánh má, đánh anh, đánh chị, đánh em?', CAST(N'2020-04-11' AS Date), 15)
INSERT [dbo].[question] ([id], [question], [dateCreate], [userId]) VALUES (15, N'Mã môn Web Java Lab là gì?', CAST(N'2021-06-23' AS Date), 16)
INSERT [dbo].[question] ([id], [question], [dateCreate], [userId]) VALUES (16, N'Ngày thành lập Đảng Cộng Sản Việt Nam?', CAST(N'2021-06-23' AS Date), 15)
INSERT [dbo].[question] ([id], [question], [dateCreate], [userId]) VALUES (17, N'Chủ tịch nước Việt Nam nhiệm kỳ 2021-2026 là ai?', CAST(N'2021-06-23' AS Date), 16)
INSERT [dbo].[question] ([id], [question], [dateCreate], [userId]) VALUES (18, N'Thủ đô Việt Nam là ở đâu?', CAST(N'2021-06-24' AS Date), 13)
INSERT [dbo].[question] ([id], [question], [dateCreate], [userId]) VALUES (20, N'Núi Phú Sĩ nằm ở nước nào?', CAST(N'2021-06-25' AS Date), 13)
SET IDENTITY_INSERT [dbo].[question] OFF
GO
SET IDENTITY_INSERT [dbo].[user] ON 

INSERT [dbo].[user] ([id], [username], [password], [isTeacher], [email]) VALUES (13, N'admin', N'123456', 1, N'loidv2@fsoft.com.vn')
INSERT [dbo].[user] ([id], [username], [password], [isTeacher], [email]) VALUES (15, N'phatnt', N'123456', 1, N'phatnt@gmail.com')
INSERT [dbo].[user] ([id], [username], [password], [isTeacher], [email]) VALUES (17, N'user', N'123456', 0, N'user@gmail.com')
INSERT [dbo].[user] ([id], [username], [password], [isTeacher], [email]) VALUES (18, N'teacher', N'123456', 1, N'dungnh@gmail.com')
INSERT [dbo].[user] ([id], [username], [password], [isTeacher], [email]) VALUES (1020, N'user3', N'123456', 1, N'dungnh@gmail.com')
INSERT [dbo].[user] ([id], [username], [password], [isTeacher], [email]) VALUES (1021, N'user4', N'123456', 1, N'dungnh@gmail.com')
INSERT [dbo].[user] ([id], [username], [password], [isTeacher], [email]) VALUES (1022, N'techer2', N'123456', 1, N'dungnh@gmail.com')
SET IDENTITY_INSERT [dbo].[user] OFF
GO
