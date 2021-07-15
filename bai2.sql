use master
go
create database Huong_Bai2
go
use Huong_Bai2
go

Create table [NHANVIEN]
(
	[MaNV] Integer Identity NOT NULL,
	[HoTen] Nvarchar(50) NULL,
	[DOB] Nvarchar(255) NULL,
	[DiaChi] Nvarchar(255) NULL,
	[SDT] Nvarchar(255) NULL,
	[GioiTinh] Nvarchar(255) NULL,
Primary Key ([MaNV])
) 
go

Create table [SANPHAM]
(
	[MaSP] Integer Identity NOT NULL,
	[TenSP] Nvarchar(255) NULL,
	[DVTinh] Nvarchar(255) NULL,
	[Gia] Integer NULL,
Primary Key ([MaSP])
) 
go

Create table [HOADON]
(
	[MaHD] Integer Identity NOT NULL,
	[LoaiHD] Nvarchar(255) NULL,
	[NgayLap] Nvarchar(255) NULL,
	[NgayGiaoNhan] Char(1) NULL,
	[MaNV] Integer NOT NULL,
Primary Key ([MaHD])
) 
go

Create table [CHITIETHOADON]
(
	[MaHD] Integer NOT NULL,
	[MaSP] Integer NOT NULL,
	[SoLuong] Integer NULL,
	[DonGia] Integer NULL,
Primary Key ([MaHD],[MaSP])
) 
go


Alter table [HOADON] add  foreign key([MaNV]) references [NHANVIEN] ([MaNV])  on update no action on delete no action 
go
Alter table [CHITIETHOADON] add  foreign key([MaSP]) references [SANPHAM] ([MaSP])  on update no action on delete no action 
go
Alter table [CHITIETHOADON] add  foreign key([MaHD]) references [HOADON] ([MaHD])  on update no action on delete no action 
