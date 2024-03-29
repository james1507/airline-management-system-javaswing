﻿use master
go 

create database qlmb
go

use qlmb
go

-- Những table là 1: SANBAY, TUYENBAY, LOAIMAYBAY, HANGMAYBAY, HANHKHACH, NHANVIEN, DONGIA, HANGVE
-- Những table la nhiều: MAYBAY, CHUYENBAY, CHITIETCHUYENBAY, VECHUYENBAY, PHIEUDATCHO, PHIEUDAT_HANGVE 
-- HOADON, HOADONCHITIET
create table SANBAY
(
	MaSanBay varchar(10) primary key,
	TenSanBay nvarchar(50),
	QuocGia nvarchar(50),
	DiaDiem nvarchar(50)
)
go

create table TUYENBAY
(
	MaTuyenBay varchar(10) primary key,
	MaSanBayDi varchar(10),
	MaSanBayDen varchar(10),
	FOREIGN KEY (MaSanBayDi) REFERENCES SANBAY(MaSanBay),
	FOREIGN KEY (MaSanBayDen) REFERENCES SANBAY(MaSanBay)
)
go

create table HANGMAYBAY
(
	MaHang varchar(10) primary key,
	TenHang nvarchar(50),
	QuocGia nvarchar(50)
)
go


create table HANHKHACH
(
	CMND varchar(10) primary key,
	HoTen nvarchar(50),
	GioiTinh bit default 1,
	DienThoai varchar(10),
	Email varchar(50),
	DiaChi nvarchar(50)
)
go

create table NHANVIEN
(
	MaNhanVien varchar(10) primary key,
	MatKhau varchar(30),
	VaiTro Nvarchar(30),
	HoTen nvarchar(50),
	GioiTinh bit,
	DienThoai varchar(10),
	Email varchar(50),
	DiaChi nvarchar(50),
	Hinh varchar(100)
)
go

create table HANGVE
(
	MaHangVe varchar(10) primary key,
	TenHangVe nvarchar(50)
)
go

create table MAYBAY 
(
	MaMayBay varchar(10) primary key,
	MaHang varchar(10),
	FOREIGN KEY (MaHang) REFERENCES HANGMAYBAY(MaHang)
)
go

create table CHUYENBAY
(
	MaChuyenBay varchar(10) primary key,
	NgayDi date,
	NgayDen date,
	GioKhoiHanh time,
	SoGheThuongGia int,
	SoGhePhoThong int,
	MaTuyenBay varchar(10),
	MaMayBay varchar(10),
	FOREIGN KEY (MaTuyenBay) REFERENCES TUYENBAY(MaTuyenBay),
	FOREIGN KEY (MaMayBay) REFERENCES MAYBAY(MaMayBay)
)
go

create table LOAIVE
(
	MaLoaiVe varchar(10) primary key,
	TenLoaiVe nvarchar(50)
)
go

create table VEMAYBAY
(
	MaVe varchar(10) primary key,
	MaHangVe varchar(10),
	MaLoaiVe varchar(10),
	MaChuyenBay varchar(10),
	GiaBan float,
	FOREIGN KEY (MaLoaiVe) REFERENCES LOAIVE(MaLoaiVe),
	FOREIGN KEY (MaHangVe) REFERENCES HANGVE(MaHangVe),
	FOREIGN KEY (MaChuyenBay) REFERENCES CHUYENBAY(MaChuyenBay),
)
go

create table DICHVU
(
	MaDichVu varchar(10),
	Mave varchar(10),
	TenDichVu Nvarchar(50),
	GiaDichVu float,
	primary key (MaDichVu, Mave),
	FOREIGN KEY (MaVe) REFERENCES VEMAYBAY(MaVe)
)
go

create table HOADON
(
	MaHoaDon varchar(10) primary key,
	NgayLap varchar(20),
	CMND varchar(10),
	MaNhanVien varchar(10),
	FOREIGN KEY (CMND) REFERENCES HANHKHACH(CMND),
	FOREIGN KEY (MaNhanVien) REFERENCES NHANVIEN(MaNhanVien)
)
go

create table HOADONCHITIET
(
	MaHoaDon varchar(10),
	MaVe varchar(10),
	SoGheDat int,
	primary key (MaHoaDon, MaVe),
	FOREIGN KEY (MaHoaDon) REFERENCES HOADON(MaHoaDon),
	FOREIGN KEY (MaVe) REFERENCES VEMAYBAY(MaVe)
)
go

--Phần nhập dữ liệu
--Table hãng máy bay
insert into HANGMAYBAY values('HMB01', N'JetStar Pacific Airlines', N'Việt Nam')
insert into HANGMAYBAY values('HMB02', N'Aegean Airlines', N'Hy Lạp')
insert into HANGMAYBAY values('HMB03', N'Wamos Air', N'Tây Ban Nha')
insert into HANGMAYBAY values('HMB04', N'United Airlines', N'Hoa Kỳ')
insert into HANGMAYBAY values('HMB05', N'EgyptAir', N'Ai Cập')

--Table máy bay
insert into MAYBAY values('MB01', 'HMB01')
insert into MAYBAY values('MB02', 'HMB02')
insert into MAYBAY values('MB03', 'HMB03')
insert into MAYBAY values('MB04', 'HMB04')
insert into MAYBAY values('MB05', 'HMB05')

-- Table sân bay 
insert into SANBAY values('SB01', N'Tân Sơn Nhất', N'Việt Nam', N'Hồ Chí Minh')
insert into SANBAY values('SB02', N'Eleftherios Venizelos', N'Hy Lạp', N'Athens')
insert into SANBAY values('SB03', N'Madrid Barajas', N'Tây Ban Nha', N'Madrid')
insert into SANBAY values('SB04', N'Chicago OHare', N'Hoa Kỳ', N'Chicago')
insert into SANBAY values('SB05', N'Cairo', N'Ai Cập', N'Cairo')

-- Table tuyến bay
insert into TUYENBAY values('TB01', 'SB01', 'SB02')
insert into TUYENBAY values('TB02', 'SB02', 'SB03')
insert into TUYENBAY values('TB03', 'SB03', 'SB04')
insert into TUYENBAY values('TB04', 'SB04', 'SB05')
insert into TUYENBAY values('TB05', 'SB05', 'SB01')

--Table hành khách 
insert into HANHKHACH values('221462830', N'Hồ Hoàng Tông', 0, '0856985586', 'tonghh@gmail.com', N'Phú Yên')
insert into HANHKHACH values('123456789', N'Ngô Tuấn Đức', 1, '0123456789', 'ducnt@gmail.com', N'TP Hồ Chí Minh')
insert into HANHKHACH values('123456788', N'Nguyễn Minh Ý', 1, '0123456788', 'minhy@gmail.com', N'Phú Yên')
insert into HANHKHACH values('123456787', N'Đoàn Quốc Trung', 1, '0123456786', 'trungqd@gmail.com', N'TP Hồ Chí Minh')
insert into HANHKHACH values('123456786', N'Phạm Thị Thanh Hằng', 0, '0123456787', 'hangptt@gmail.com', N'TP Hồ Chí Minh')

--Table nhân viên 
insert into NHANVIEN values('NV01', '123', N'Nhân viên', N'Nguyễn Mạnh Thắng', 1, '0123456789', 'manhthang@gmail.com', N'Bắc Ninh', 'thang.jpg')
insert into NHANVIEN values('NV02', '123', N'Trưởng phòng', N'Đoàn Thành Nam Định', 1, '0123456788', 'dinhdoan@gmail.com', N'Nam Định', 'dinh.jpg')
insert into NHANVIEN values('NV03', '123', N'Nhân viên', N'Lê Phương Nam', 1, '0123456787', 'phuongnam@gmail.com', N'Sơn Tây', 'nam.jpg')
insert into NHANVIEN values('NV04', '123', N'Trưởng phòng', N'Nguyễn Văn An', 1, '0123456786', 'nguyenvanan@gmail.com', N'Hải Phòng', 'an.jpg')
insert into NHANVIEN values('NV05', '123', N'Nhân viên', N'Phan Thanh Hằng', 0, '0123456785', 'hangpt@gmail.com', N'TP Hồ Chí Minh', 'hang.jpg')

--Table chuyến bay 
set dateformat YMD
insert into CHUYENBAY values('CB01','2019-12-01','2019-12-02','08:20:00','18','151','TB04','MB04')
insert into CHUYENBAY values('CB02','2019-12-01','2019-12-03','22:00:00','24','156','TB01','MB01')
insert into CHUYENBAY values('CB03','2019-12-03','2019-12-05','07:30:00','8','195','TB02','MB02')
insert into CHUYENBAY values('CB04','2019-12-04','2019-12-05','11:00:00','28','183','TB05','MB05')
insert into CHUYENBAY values('CB05','2019-12-11','2019-12-13','18:20:00','16','168','TB03','MB03')

-- Table hạng vé 
insert into HANGVE values('HV01', N'Thương gia')
insert into HANGVE values('HV02', N'Phổ thông')

--Table loại vé 
insert into LOAIVE values('LV01', N'Khứ hồi')
insert into LOAIVE values('LV02', N'Một chiều')

--Table vé máy bay
insert into VEMAYBAY values('V01','HV01','LV01','CB01','11')
insert into VEMAYBAY values('V02','HV02','LV02','CB02','7')
insert into VEMAYBAY values('V03','HV02','LV01','CB03','12')
insert into VEMAYBAY values('V04','HV01','LV01','CB04','14')
insert into VEMAYBAY values('V05','HV02','LV01','CB05','6')

insert into VEMAYBAY values('V06','HV02','LV02','CB05','12')
insert into VEMAYBAY values('V07','HV02','LV02','CB04','14')
insert into VEMAYBAY values('V08','HV02','LV02','CB03','16')
insert into VEMAYBAY values('V09','HV01','LV01','CB02','18')
insert into VEMAYBAY values('V10','HV01','LV01','CB01','20')

insert into VEMAYBAY values('V11','HV01','LV01','CB04','19')
insert into VEMAYBAY values('V12','HV01','LV02','CB01','17')
insert into VEMAYBAY values('V13','HV01','LV01','CB05','15')
insert into VEMAYBAY values('V14','HV02','LV02','CB03','13')
insert into VEMAYBAY values('V15','HV02','LV01','CB02','11')

insert into VEMAYBAY values('V16','HV02','LV01','CB02','6')
insert into VEMAYBAY values('V17','HV01','LV02','CB05','12')
insert into VEMAYBAY values('V18','HV02','LV01','CB03','18')
insert into VEMAYBAY values('V19','HV01','LV01','CB04','24')
insert into VEMAYBAY values('V20','HV02','LV02','CB01','12')

--Table dịch vụ 
insert into DICHVU values('DV01','V01',N'Suất ăn đặc biệt','0.75')
insert into DICHVU values('DV02','V02',N'Wifi','0.2')
insert into DICHVU values('DV03','V03',N'Suất ăn đặc biệt','0.75')
insert into DICHVU values('DV04','V04',N'Wifi','0.2')
insert into DICHVU values('DV05','V05',N'Suất ăn đặc biệt','0.75')

insert into DICHVU values('DV01','V05',N'Suất ăn đặc biệt','0.75')
insert into DICHVU values('DV02','V04',N'Wifi','0.2')
insert into DICHVU values('DV04','V02',N'Wifi','0.2')
insert into DICHVU values('DV05','V01',N'Suất ăn đặc biệt','0.75')

insert into DICHVU values('DV06','V06',N'Suất ăn đặc biệt','0.75')
insert into DICHVU values('DV07','V07',N'Wifi','0.2')
insert into DICHVU values('DV08','V08',N'Suất ăn đặc biệt','0.75')
insert into DICHVU values('DV09','V09',N'Wifi','0.2')
insert into DICHVU values('DV10','V10',N'Suất ăn đặc biệt','0.75')

insert into DICHVU values('DV06','V10',N'Suất ăn đặc biệt','0.75')
insert into DICHVU values('DV07','V08',N'Wifi','0.2')
insert into DICHVU values('DV08','V09',N'Suất ăn đặc biệt','0.75')
insert into DICHVU values('DV09','V07',N'Wifi','0.2')

insert into DICHVU values('DV11','V11',N'Suất ăn đặc biệt','0.75')
insert into DICHVU values('DV12','V12',N'Wifi','0.2')
insert into DICHVU values('DV13','V13',N'Suất ăn đặc biệt','0.75')
insert into DICHVU values('DV14','V14',N'Wifi','0.2')
insert into DICHVU values('DV15','V15',N'Suất ăn đặc biệt','0.75')

insert into DICHVU values('DV11','V14',N'Suất ăn đặc biệt','0.75')
insert into DICHVU values('DV12','V11',N'Wifi','0.2')
insert into DICHVU values('DV13','V15',N'Suất ăn đặc biệt','0.75')

insert into DICHVU values('DV14','V16',N'Suất ăn đặc biệt','0.75')
insert into DICHVU values('DV15','V17',N'Wifi','0.2')
insert into DICHVU values('DV16','V18',N'Suất ăn đặc biệt','0.75')
insert into DICHVU values('DV17','V19',N'Wifi','0.2')
insert into DICHVU values('DV18','V20',N'Suất ăn đặc biệt','0.75')
insert into DICHVU values('DV19','V14',N'Suất ăn đặc biệt','0.75')

--Table hóa đơn 
set dateformat YMD 
insert into HOADON values('HD01', '2019-11-11', '221462830', 'NV04')
insert into HOADON values('HD02', '2018-10-13', '123456789', 'NV01')
insert into HOADON values('HD03', '2017-09-14', '123456788', 'NV05')
insert into HOADON values('HD04', '2016-08-17', '123456787', 'NV03')
insert into HOADON values('HD05', '2015-07-18', '123456786', 'NV02')

insert into HOADON values('HD06', '2014-07-02', '123456788', 'NV01')
insert into HOADON values('HD07', '2013-08-04', '123456786', 'NV02')
insert into HOADON values('HD08', '2012-03-06', '123456789', 'NV03')
insert into HOADON values('HD09', '2011-03-08', '123456787', 'NV04')
insert into HOADON values('HD10', '2010-01-10', '221462830', 'NV05')

insert into HOADON values('HD11', '2009-02-21', '123456788', 'NV05')
insert into HOADON values('HD12', '2011-01-22', '123456789', 'NV04')
insert into HOADON values('HD13', '2013-04-23', '123456788', 'NV03')
insert into HOADON values('HD14', '2015-05-24', '123456789', 'NV02')
insert into HOADON values('HD15', '2017-06-25', '123456788', 'NV01')

insert into HOADON values('HD16', '2012-07-16', '123456786', 'NV02')
insert into HOADON values('HD17', '2015-07-19', '123456786', 'NV05')
insert into HOADON values('HD18', '2017-06-22', '221462830', 'NV04')
insert into HOADON values('HD19', '2012-04-25', '221462830', 'NV01')
insert into HOADON values('HD20', '2011-01-28', '123456786', 'NV03')

insert into HOADON values('HD21', '2018-04-27', '123456787', 'NV05')
insert into HOADON values('HD22', '2017-06-06', '123456786', 'NV04')
insert into HOADON values('HD23', '2014-06-16', '123456787', 'NV03')
insert into HOADON values('HD24', '2019-07-05', '123456789', 'NV04')
insert into HOADON values('HD25', '2017-01-17', '123456786', 'NV01')

insert into HOADON values('HD26', '2017-02-06', '221462830', 'NV04')
insert into HOADON values('HD27', '2018-05-15', '123456786', 'NV04')
insert into HOADON values('HD28', '2011-05-24', '123456789', 'NV05')
insert into HOADON values('HD29', '2013-10-22', '123456788', 'NV03')
insert into HOADON values('HD30', '2014-04-28', '123456788', 'NV04')

insert into HOADON values('HD31', '2012-04-20', '123456787', 'NV03')
insert into HOADON values('HD32', '2015-06-06', '123456789', 'NV03')
insert into HOADON values('HD33', '2016-02-28', '123456788', 'NV01')
insert into HOADON values('HD34', '2014-10-07', '221462830', 'NV05')
insert into HOADON values('HD35', '2014-04-04', '221462830', 'NV03')

insert into HOADON values('HD36', '2018-10-14', '221462830', 'NV04')
insert into HOADON values('HD37', '2014-04-24', '123456787', 'NV03')
insert into HOADON values('HD38', '2017-05-27', '221462830', 'NV05')
insert into HOADON values('HD39', '2011-11-26', '123456789', 'NV02')
insert into HOADON values('HD40', '2017-03-21', '123456789', 'NV03')

insert into HOADON values('HD41', '2016-01-13', '123456787', 'NV04')
insert into HOADON values('HD42', '2014-06-27', '123456789', 'NV04')
insert into HOADON values('HD43', '2019-11-29', '123456788', 'NV02')
insert into HOADON values('HD44', '2012-02-24', '123456788', 'NV03')
insert into HOADON values('HD45', '2015-05-11', '123456788', 'NV01')

insert into HOADON values('HD46', '2012-11-11', '123456787', 'NV04')
insert into HOADON values('HD47', '2013-07-26', '123456787', 'NV03')
insert into HOADON values('HD48', '2014-11-02', '123456789', 'NV01')
insert into HOADON values('HD49', '2017-03-02', '123456787', 'NV04')
insert into HOADON values('HD50', '2015-06-28', '123456789', 'NV04')

insert into HOADON values('HD51', '2011-07-23', '221462830', 'NV05')
insert into HOADON values('HD52', '2017-12-16', '123456788', 'NV03')
insert into HOADON values('HD53', '2015-04-02', '123456786', 'NV05')
insert into HOADON values('HD54', '2014-09-14', '221462830', 'NV02')
insert into HOADON values('HD55', '2016-10-24', '123456786', 'NV03')

insert into HOADON values('HD56', '2013-06-20', '123456786', 'NV04')
insert into HOADON values('HD57', '2012-12-05', '123456786', 'NV03')
insert into HOADON values('HD58', '2012-06-12', '123456789', 'NV01')
insert into HOADON values('HD59', '2010-09-08', '221462830', 'NV02')
insert into HOADON values('HD60', '2012-07-16', '123456789', 'NV04')

insert into HOADON values('HD61', '2013-06-10', '123456786', 'NV02')
insert into HOADON values('HD62', '2012-11-09', '123456786', 'NV04')
insert into HOADON values('HD63', '2012-04-23', '123456787', 'NV03')
insert into HOADON values('HD64', '2017-10-19', '123456788', 'NV03')
insert into HOADON values('HD65', '2017-02-09', '123456789', 'NV01')

insert into HOADON values('HD66', '2017-02-12', '123456786', 'NV05')
insert into HOADON values('HD67', '2018-02-12', '123456788', 'NV04')
insert into HOADON values('HD68', '2019-01-23', '123456788', 'NV02')
insert into HOADON values('HD69', '2017-06-05', '123456789', 'NV03')
insert into HOADON values('HD70', '2015-05-19', '123456786', 'NV04')

insert into HOADON values('HD71', '2017-04-25', '123456789', 'NV02')
insert into HOADON values('HD72', '2010-11-25', '123456788', 'NV04')
insert into HOADON values('HD73', '2012-08-19', '123456786', 'NV03')
insert into HOADON values('HD74', '2016-04-11', '123456788', 'NV01')
insert into HOADON values('HD75', '2010-04-09', '123456788', 'NV03')

insert into HOADON values('HD76', '2017-06-03', '123456788', 'NV05')
insert into HOADON values('HD77', '2011-06-26', '221462830', 'NV02')
insert into HOADON values('HD78', '2018-06-04', '123456787', 'NV02')
insert into HOADON values('HD79', '2011-01-25', '123456786', 'NV02')
insert into HOADON values('HD80', '2011-07-06', '123456786', 'NV03')

insert into HOADON values('HD81', '2011-01-22', '123456787', 'NV02')
insert into HOADON values('HD82', '2016-01-25', '221462830', 'NV01')
insert into HOADON values('HD83', '2015-09-19', '123456788', 'NV03')
insert into HOADON values('HD84', '2016-11-25', '123456788', 'NV01')
insert into HOADON values('HD85', '2012-12-24', '123456788', 'NV05')

insert into HOADON values('HD86', '2018-04-07', '123456786', 'NV01')
insert into HOADON values('HD87', '2011-01-02', '221462830', 'NV02')
insert into HOADON values('HD88', '2015-08-04', '123456787', 'NV02')
insert into HOADON values('HD89', '2015-05-24', '123456788', 'NV04')
insert into HOADON values('HD90', '2015-11-03', '221462830', 'NV05')

insert into HOADON values('HD91', '2015-10-27', '123456787', 'NV05')
insert into HOADON values('HD92', '2014-03-18', '123456788', 'NV01')
insert into HOADON values('HD93', '2014-03-05', '123456789', 'NV04')
insert into HOADON values('HD94', '2012-12-17', '221462830', 'NV04')
insert into HOADON values('HD95', '2018-06-21', '123456789', 'NV04')

insert into HOADON values('HD96', '2011-08-12', '123456787', 'NV02')
insert into HOADON values('HD97', '2012-11-10', '221462830', 'NV05')
insert into HOADON values('HD98', '2016-12-03', '123456789', 'NV04')
insert into HOADON values('HD99', '2011-08-13', '123456789', 'NV02')
insert into HOADON values('HD100', '2012-03-21', '123456787', 'NV05')

insert into HOADON values('HD101', '2019-09-09', '123456788', 'NV05')
insert into HOADON values('HD102', '2019-11-02', '123456788', 'NV02')
insert into HOADON values('HD103', '2019-07-18', '123456789', 'NV02')
insert into HOADON values('HD104', '2019-02-20', '123456788', 'NV04')
insert into HOADON values('HD105', '2019-10-20', '123456787', 'NV03')

insert into HOADON values('HD106', '2019-12-29', '123456786', 'NV01')
insert into HOADON values('HD107', '2019-01-09', '123456787', 'NV01')
insert into HOADON values('HD108', '2019-06-27', '123456789', 'NV01')
insert into HOADON values('HD109', '2019-04-8', '123456787', 'NV02')
insert into HOADON values('HD110', '2019-06-04', '123456788', 'NV01')

insert into HOADON values('HD111', '2019-11-17', '123456788', 'NV03')
insert into HOADON values('HD112', '2019-05-23', '123456787', 'NV02')
insert into HOADON values('HD113', '2019-07-24', '123456789', 'NV05')
insert into HOADON values('HD114', '2019-03-28', '123456788', 'NV01')
insert into HOADON values('HD115', '2019-08-17', '123456787', 'NV04')

--Table hóa đơn chi tiết
insert into HOADONCHITIET values('HD01', 'V15',3)
insert into HOADONCHITIET values('HD02', 'V14',6)
insert into HOADONCHITIET values('HD03', 'V20',5)
insert into HOADONCHITIET values('HD04', 'V11',6)
insert into HOADONCHITIET values('HD05', 'V02',9)

insert into HOADONCHITIET values('HD06', 'V08',7)
insert into HOADONCHITIET values('HD07', 'V09',9)
insert into HOADONCHITIET values('HD08', 'V16',7)
insert into HOADONCHITIET values('HD09', 'V03',2)
insert into HOADONCHITIET values('HD10', 'V14',8)

insert into HOADONCHITIET values('HD11', 'V13',7)
insert into HOADONCHITIET values('HD12', 'V19',5)
insert into HOADONCHITIET values('HD13', 'V04',2)
insert into HOADONCHITIET values('HD14', 'V07',3)
insert into HOADONCHITIET values('HD15', 'V08',3)

insert into HOADONCHITIET values('HD16', 'V04',5)
insert into HOADONCHITIET values('HD17', 'V01',3)
insert into HOADONCHITIET values('HD18', 'V19',8)
insert into HOADONCHITIET values('HD19', 'V10',8)
insert into HOADONCHITIET values('HD20', 'V12',10)

insert into HOADONCHITIET values('HD21', 'V10',5)
insert into HOADONCHITIET values('HD22', 'V02',9)
insert into HOADONCHITIET values('HD23', 'V18',3)
insert into HOADONCHITIET values('HD24', 'V08',4)
insert into HOADONCHITIET values('HD25', 'V08',3)

insert into HOADONCHITIET values('HD26', 'V07',9)
insert into HOADONCHITIET values('HD27', 'V05',7)
insert into HOADONCHITIET values('HD28', 'V09',8)
insert into HOADONCHITIET values('HD29', 'V06',5)
insert into HOADONCHITIET values('HD30', 'V10',7)

insert into HOADONCHITIET values('HD31', 'V13',3)
insert into HOADONCHITIET values('HD32', 'V03',6)
insert into HOADONCHITIET values('HD33', 'V05',5)
insert into HOADONCHITIET values('HD34', 'V15',9)
insert into HOADONCHITIET values('HD35', 'V11',9)

insert into HOADONCHITIET values('HD36', 'V09',6)
insert into HOADONCHITIET values('HD37', 'V07',3)
insert into HOADONCHITIET values('HD38', 'V01',2)
insert into HOADONCHITIET values('HD39', 'V11',7)
insert into HOADONCHITIET values('HD40', 'V11',7)

insert into HOADONCHITIET values('HD41', 'V13',5)
insert into HOADONCHITIET values('HD42', 'V05',6)
insert into HOADONCHITIET values('HD43', 'V08',3)
insert into HOADONCHITIET values('HD44', 'V06',6)
insert into HOADONCHITIET values('HD45', 'V17',1)

insert into HOADONCHITIET values('HD46', 'V12',3)
insert into HOADONCHITIET values('HD47', 'V07',8)
insert into HOADONCHITIET values('HD48', 'V07',8)
insert into HOADONCHITIET values('HD49', 'V05',5)
insert into HOADONCHITIET values('HD50', 'V05',1)

insert into HOADONCHITIET values('HD51', 'V10',5)
insert into HOADONCHITIET values('HD52', 'V19',6)
insert into HOADONCHITIET values('HD53', 'V02',2)
insert into HOADONCHITIET values('HD54', 'V14',10)
insert into HOADONCHITIET values('HD55', 'V05',3)

insert into HOADONCHITIET values('HD56', 'V13',5)
insert into HOADONCHITIET values('HD57', 'V13',8)
insert into HOADONCHITIET values('HD58', 'V10',9)
insert into HOADONCHITIET values('HD59', 'V18',4)
insert into HOADONCHITIET values('HD60', 'V12',3)

insert into HOADONCHITIET values('HD61', 'V08',2)
insert into HOADONCHITIET values('HD62', 'V18',4)
insert into HOADONCHITIET values('HD63', 'V08',2)
insert into HOADONCHITIET values('HD64', 'V03',3)
insert into HOADONCHITIET values('HD65', 'V01',8)

insert into HOADONCHITIET values('HD66', 'V12',6)
insert into HOADONCHITIET values('HD67', 'V12',1)
insert into HOADONCHITIET values('HD68', 'V01',10)
insert into HOADONCHITIET values('HD69', 'V11',7)
insert into HOADONCHITIET values('HD70', 'V13',7)

insert into HOADONCHITIET values('HD71', 'V15',2)
insert into HOADONCHITIET values('HD72', 'V12',4)
insert into HOADONCHITIET values('HD73', 'V09',1)
insert into HOADONCHITIET values('HD74', 'V07',9)
insert into HOADONCHITIET values('HD75', 'V16',3)

insert into HOADONCHITIET values('HD76', 'V06',8)
insert into HOADONCHITIET values('HD77', 'V02',7)
insert into HOADONCHITIET values('HD78', 'V13',1)
insert into HOADONCHITIET values('HD79', 'V03',10)
insert into HOADONCHITIET values('HD80', 'V11',8)

insert into HOADONCHITIET values('HD81', 'V17',2)
insert into HOADONCHITIET values('HD82', 'V12',7)
insert into HOADONCHITIET values('HD83', 'V16',5)
insert into HOADONCHITIET values('HD84', 'V02',10)
insert into HOADONCHITIET values('HD85', 'V11',7)

insert into HOADONCHITIET values('HD86', 'V12',5)
insert into HOADONCHITIET values('HD87', 'V18',2)
insert into HOADONCHITIET values('HD88', 'V11',4)
insert into HOADONCHITIET values('HD89', 'V11',3)
insert into HOADONCHITIET values('HD90', 'V13',10)

insert into HOADONCHITIET values('HD91', 'V12',6)
insert into HOADONCHITIET values('HD92', 'V07',6)
insert into HOADONCHITIET values('HD93', 'V13',4)
insert into HOADONCHITIET values('HD94', 'V19',2)
insert into HOADONCHITIET values('HD95', 'V08',3)

insert into HOADONCHITIET values('HD96', 'V15',9)
insert into HOADONCHITIET values('HD97', 'V19',1)
insert into HOADONCHITIET values('HD98', 'V10',3)
insert into HOADONCHITIET values('HD99', 'V09',7)
insert into HOADONCHITIET values('HD100', 'V08',7)

-----------------------------------------------------
insert into HOADONCHITIET values('HD01', 'V18',1)
insert into HOADONCHITIET values('HD02', 'V18',4)
insert into HOADONCHITIET values('HD03', 'V06',5)
insert into HOADONCHITIET values('HD04', 'V13',10)
insert into HOADONCHITIET values('HD05', 'V08',3)

insert into HOADONCHITIET values('HD06', 'V03',4)
insert into HOADONCHITIET values('HD10', 'V19',6)

insert into HOADONCHITIET values('HD11', 'V18',5)
insert into HOADONCHITIET values('HD12', 'V15',7)
insert into HOADONCHITIET values('HD13', 'V07',5)
insert into HOADONCHITIET values('HD14', 'V19',8)
insert into HOADONCHITIET values('HD15', 'V05',9)

insert into HOADONCHITIET values('HD16', 'V08',2)
insert into HOADONCHITIET values('HD17', 'V19',6)
insert into HOADONCHITIET values('HD18', 'V04',4)
insert into HOADONCHITIET values('HD19', 'V01',9)
insert into HOADONCHITIET values('HD20', 'V13',9)

insert into HOADONCHITIET values('HD21', 'V19',8)
insert into HOADONCHITIET values('HD22', 'V03',9)
insert into HOADONCHITIET values('HD24', 'V10',7)

insert into HOADONCHITIET values('HD26', 'V02',3)
insert into HOADONCHITIET values('HD27', 'V09',7)
insert into HOADONCHITIET values('HD28', 'V06',2)
insert into HOADONCHITIET values('HD29', 'V10',9)
insert into HOADONCHITIET values('HD30', 'V19',1)

insert into HOADONCHITIET values('HD31', 'V11',9)
insert into HOADONCHITIET values('HD32', 'V13',2)
insert into HOADONCHITIET values('HD33', 'V18',8)
insert into HOADONCHITIET values('HD34', 'V11',3)
insert into HOADONCHITIET values('HD35', 'V13',6)

insert into HOADONCHITIET values('HD36', 'V06',9)

insert into HOADONCHITIET values('HD41', 'V06',8)
insert into HOADONCHITIET values('HD42', 'V16',9)
insert into HOADONCHITIET values('HD43', 'V07',1)
insert into HOADONCHITIET values('HD44', 'V20',7)
insert into HOADONCHITIET values('HD45', 'V14',3)

insert into HOADONCHITIET values('HD51', 'V02',6)
insert into HOADONCHITIET values('HD52', 'V07',8)
insert into HOADONCHITIET values('HD53', 'V07',7)
insert into HOADONCHITIET values('HD54', 'V07',3)

insert into HOADONCHITIET values('HD60', 'V15',6)

insert into HOADONCHITIET values('HD61', 'V06',9)
insert into HOADONCHITIET values('HD62', 'V05',9)
insert into HOADONCHITIET values('HD63', 'V17',5)
insert into HOADONCHITIET values('HD64', 'V07',6)
insert into HOADONCHITIET values('HD65', 'V05',8)

insert into HOADONCHITIET values('HD66', 'V05',6)
insert into HOADONCHITIET values('HD67', 'V11',8)
insert into HOADONCHITIET values('HD68', 'V11',6)
insert into HOADONCHITIET values('HD69', 'V08',9)
insert into HOADONCHITIET values('HD70', 'V19',10)

insert into HOADONCHITIET values('HD71', 'V03',5)
insert into HOADONCHITIET values('HD72', 'V13',4)
insert into HOADONCHITIET values('HD73', 'V19',2)
insert into HOADONCHITIET values('HD74', 'V04',9)
insert into HOADONCHITIET values('HD75', 'V17',8)

insert into HOADONCHITIET values('HD76', 'V20',3)
insert into HOADONCHITIET values('HD77', 'V04',5)
insert into HOADONCHITIET values('HD78', 'V09',3)
insert into HOADONCHITIET values('HD79', 'V07',5)
insert into HOADONCHITIET values('HD80', 'V06',6)

insert into HOADONCHITIET values('HD81', 'V15',8)
insert into HOADONCHITIET values('HD82', 'V06',5)
insert into HOADONCHITIET values('HD83', 'V02',5)
insert into HOADONCHITIET values('HD84', 'V16',1)

insert into HOADONCHITIET values('HD86', 'V03',8)

insert into HOADONCHITIET values('HD91', 'V14',9)
insert into HOADONCHITIET values('HD92', 'V06',3)
insert into HOADONCHITIET values('HD93', 'V02',9)
insert into HOADONCHITIET values('HD94', 'V09',8)
insert into HOADONCHITIET values('HD95', 'V09',2)

insert into HOADONCHITIET values('HD96', 'V02',6)
insert into HOADONCHITIET values('HD97', 'V15',5)
insert into HOADONCHITIET values('HD98', 'V16',6)
insert into HOADONCHITIET values('HD99', 'V17',5)
insert into HOADONCHITIET values('HD100', 'V06',7)

----------------------------------------------------
insert into HOADONCHITIET values('HD01', 'V02',4)
insert into HOADONCHITIET values('HD02', 'V12',7)
insert into HOADONCHITIET values('HD03', 'V13',2)
insert into HOADONCHITIET values('HD04', 'V17',7)

insert into HOADONCHITIET values('HD06', 'V11',8)
insert into HOADONCHITIET values('HD07', 'V18',3)
insert into HOADONCHITIET values('HD08', 'V06',9)
insert into HOADONCHITIET values('HD09', 'V04',1)
insert into HOADONCHITIET values('HD10', 'V07',3)

insert into HOADONCHITIET values('HD11', 'V10',2)
insert into HOADONCHITIET values('HD12', 'V20',6)
insert into HOADONCHITIET values('HD13', 'V12',6)
insert into HOADONCHITIET values('HD14', 'V04',4)
insert into HOADONCHITIET values('HD15', 'V19',5)

insert into HOADONCHITIET values('HD16', 'V17',3)
insert into HOADONCHITIET values('HD17', 'V03',9)
insert into HOADONCHITIET values('HD20', 'V06',4)

insert into HOADONCHITIET values('HD21', 'V05',3)
insert into HOADONCHITIET values('HD25', 'V14',7)

insert into HOADONCHITIET values('HD26', 'V04',6)
insert into HOADONCHITIET values('HD27', 'V20',3)
insert into HOADONCHITIET values('HD29', 'V18',10)

insert into HOADONCHITIET values('HD31', 'V03',7)
insert into HOADONCHITIET values('HD32', 'V15',9)

insert into HOADONCHITIET values('HD36', 'V16',5)
insert into HOADONCHITIET values('HD37', 'V04',6)
insert into HOADONCHITIET values('HD38', 'V12',1)
insert into HOADONCHITIET values('HD39', 'V06',9)

--------------------------------------------------

insert into HOADONCHITIET values('HD41', 'V20',6)
insert into HOADONCHITIET values('HD42', 'V02',8)
insert into HOADONCHITIET values('HD43', 'V15',4)
insert into HOADONCHITIET values('HD44', 'V02',8)
insert into HOADONCHITIET values('HD45', 'V18',5)

insert into HOADONCHITIET values('HD46', 'V16',2)
insert into HOADONCHITIET values('HD47', 'V03',8)
insert into HOADONCHITIET values('HD48', 'V13',2)
insert into HOADONCHITIET values('HD49', 'V15',8)
insert into HOADONCHITIET values('HD50', 'V04',5)

insert into HOADONCHITIET values('HD51', 'V04',3)
insert into HOADONCHITIET values('HD53', 'V15',3)
insert into HOADONCHITIET values('HD54', 'V02',8)
insert into HOADONCHITIET values('HD55', 'V19',6)

insert into HOADONCHITIET values('HD56', 'V19',9)
insert into HOADONCHITIET values('HD57', 'V19',4)
insert into HOADONCHITIET values('HD58', 'V13',9)
insert into HOADONCHITIET values('HD59', 'V03',8)
insert into HOADONCHITIET values('HD60', 'V09',9)

insert into HOADONCHITIET values('HD61', 'V13',8)
insert into HOADONCHITIET values('HD62', 'V16',10)
insert into HOADONCHITIET values('HD63', 'V15',9)
insert into HOADONCHITIET values('HD64', 'V17',7)
insert into HOADONCHITIET values('HD65', 'V12',4)

insert into HOADONCHITIET values('HD66', 'V16',9)
insert into HOADONCHITIET values('HD67', 'V05',6)
insert into HOADONCHITIET values('HD68', 'V13',2)

insert into HOADONCHITIET values('HD71', 'V12',9)
insert into HOADONCHITIET values('HD73', 'V12',4)
insert into HOADONCHITIET values('HD75', 'V08',7)

insert into HOADONCHITIET values('HD80', 'V09',8)
------------------------------------------------

insert into HOADONCHITIET values('HD81', 'V09',6)
insert into HOADONCHITIET values('HD82', 'V17',9)
insert into HOADONCHITIET values('HD84', 'V07',5)
insert into HOADONCHITIET values('HD85', 'V19',6)

insert into HOADONCHITIET values('HD86', 'V13',7)
insert into HOADONCHITIET values('HD87', 'V08',6)
insert into HOADONCHITIET values('HD88', 'V15',7)
insert into HOADONCHITIET values('HD89', 'V06',10)
insert into HOADONCHITIET values('HD90', 'V19',9)