
use master
go
create database Huong_Bai1
go
use Huong_Bai1
go

Create table [SanPham]
(
	[MaSP] Integer Identity NOT NULL,
	[TenSP] Nvarchar(255) NULL,
	[NhaSanXuat] Nvarchar(255) NULL,
	[MaLoaiSP] Integer NOT NULL,
Primary Key ([MaSP])
) 
go

Create table [LoaiSanPham]
(
	[MaLoaiSP] Integer Identity NOT NULL,
	[TenLoaiSP] Nvarchar(255) NULL,
Primary Key ([MaLoaiSP])
) 
go


Alter table [SanPham] add  foreign key([MaLoaiSP]) references [LoaiSanPham] ([MaLoaiSP])  on update no action on delete no action 
go




select * from LoaiSanPham