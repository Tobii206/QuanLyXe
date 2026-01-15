-- Script cập nhật database đơn giản cho hệ thống v2.0
-- Chạy script này sau khi đã có database cơ bản

USE QLBanXe;
GO

-- 1. Cập nhật bảng KhachHang hiện có - thêm các cột mới
IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'KhachHang' AND COLUMN_NAME = 'phanLoai')
BEGIN
    ALTER TABLE KhachHang ADD phanLoai NVARCHAR(20) DEFAULT N'Thường';
    PRINT 'Đã thêm cột phanLoai vào KhachHang';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'KhachHang' AND COLUMN_NAME = 'ngayTao')
BEGIN
    ALTER TABLE KhachHang ADD ngayTao DATE DEFAULT GETDATE();
    PRINT 'Đã thêm cột ngayTao vào KhachHang';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'KhachHang' AND COLUMN_NAME = 'nguoiTao')
BEGIN
    ALTER TABLE KhachHang ADD nguoiTao VARCHAR(20);
    PRINT 'Đã thêm cột nguoiTao vào KhachHang';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'KhachHang' AND COLUMN_NAME = 'trangThai')
BEGIN
    ALTER TABLE KhachHang ADD trangThai NVARCHAR(20) DEFAULT N'Hoạt động';
    PRINT 'Đã thêm cột trangThai vào KhachHang';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'KhachHang' AND COLUMN_NAME = 'diemTichLuy')
BEGIN
    ALTER TABLE KhachHang ADD diemTichLuy INT DEFAULT 0;
    PRINT 'Đã thêm cột diemTichLuy vào KhachHang';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'KhachHang' AND COLUMN_NAME = 'nguonKhach')
BEGIN
    ALTER TABLE KhachHang ADD nguonKhach NVARCHAR(50);
    PRINT 'Đã thêm cột nguonKhach vào KhachHang';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'KhachHang' AND COLUMN_NAME = 'cccd')
BEGIN
    ALTER TABLE KhachHang ADD cccd VARCHAR(20);
    PRINT 'Đã thêm cột cccd vào KhachHang';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'KhachHang' AND COLUMN_NAME = 'ghiChu')
BEGIN
    ALTER TABLE KhachHang ADD ghiChu NVARCHAR(500);
    PRINT 'Đã thêm cột ghiChu vào KhachHang';
END

-- Cập nhật dữ liệu cho các cột mới
UPDATE KhachHang SET 
    phanLoai = N'Thường',
    ngayTao = GETDATE(),
    trangThai = N'Hoạt động',
    diemTichLuy = 0
WHERE phanLoai IS NULL;

-- 2. Cập nhật bảng NhanVien hiện có - thêm các cột mới
IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'NhanVien' AND COLUMN_NAME = 'chucVu')
BEGIN
    ALTER TABLE NhanVien ADD chucVu NVARCHAR(30) DEFAULT N'Nhân viên';
    PRINT 'Đã thêm cột chucVu vào NhanVien';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'NhanVien' AND COLUMN_NAME = 'taiKhoan')
BEGIN
    ALTER TABLE NhanVien ADD taiKhoan VARCHAR(50);
    PRINT 'Đã thêm cột taiKhoan vào NhanVien';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'NhanVien' AND COLUMN_NAME = 'matKhau')
BEGIN
    ALTER TABLE NhanVien ADD matKhau VARCHAR(100);
    PRINT 'Đã thêm cột matKhau vào NhanVien';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'NhanVien' AND COLUMN_NAME = 'ngayVaoLam')
BEGIN
    ALTER TABLE NhanVien ADD ngayVaoLam DATE;
    PRINT 'Đã thêm cột ngayVaoLam vào NhanVien';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'NhanVien' AND COLUMN_NAME = 'luongCoBan')
BEGIN
    ALTER TABLE NhanVien ADD luongCoBan DECIMAL(15,2);
    PRINT 'Đã thêm cột luongCoBan vào NhanVien';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'NhanVien' AND COLUMN_NAME = 'quyenHan')
BEGIN
    ALTER TABLE NhanVien ADD quyenHan NVARCHAR(1000);
    PRINT 'Đã thêm cột quyenHan vào NhanVien';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'NhanVien' AND COLUMN_NAME = 'ngayTao')
BEGIN
    ALTER TABLE NhanVien ADD ngayTao DATE DEFAULT GETDATE();
    PRINT 'Đã thêm cột ngayTao vào NhanVien';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'NhanVien' AND COLUMN_NAME = 'nguoiTao')
BEGIN
    ALTER TABLE NhanVien ADD nguoiTao VARCHAR(20);
    PRINT 'Đã thêm cột nguoiTao vào NhanVien';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'NhanVien' AND COLUMN_NAME = 'ghiChu')
BEGIN
    ALTER TABLE NhanVien ADD ghiChu NVARCHAR(500);
    PRINT 'Đã thêm cột ghiChu vào NhanVien';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'NhanVien' AND COLUMN_NAME = 'diaChi')
BEGIN
    ALTER TABLE NhanVien ADD diaChi NVARCHAR(255);
    PRINT 'Đã thêm cột diaChi vào NhanVien';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'NhanVien' AND COLUMN_NAME = 'cccd')
BEGIN
    ALTER TABLE NhanVien ADD cccd VARCHAR(20);
    PRINT 'Đã thêm cột cccd vào NhanVien';
END

-- Cập nhật dữ liệu cho NhanVien
UPDATE NhanVien SET 
    chucVu = N'Nhân viên',
    taiKhoan = 'nv' + MaNhanVien,
    matKhau = '123456',
    ngayVaoLam = GETDATE(),
    ngayTao = GETDATE()
WHERE chucVu IS NULL;

-- Cập nhật admin
UPDATE NhanVien SET 
    chucVu = N'Quản lý',
    taiKhoan = 'admin',
    quyenHan = '{"all": true}'
WHERE MaNhanVien = 'NV00001';

-- 3. Cập nhật bảng PhieuDatXe hiện có - thêm các cột mới
IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'PhieuDatXe' AND COLUMN_NAME = 'phanTramCoc')
BEGIN
    ALTER TABLE PhieuDatXe ADD phanTramCoc DECIMAL(5,2);
    PRINT 'Đã thêm cột phanTramCoc vào PhieuDatXe';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'PhieuDatXe' AND COLUMN_NAME = 'ngayGiaoThucTe')
BEGIN
    ALTER TABLE PhieuDatXe ADD ngayGiaoThucTe DATE;
    PRINT 'Đã thêm cột ngayGiaoThucTe vào PhieuDatXe';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'PhieuDatXe' AND COLUMN_NAME = 'lyDoHuy')
BEGIN
    ALTER TABLE PhieuDatXe ADD lyDoHuy NVARCHAR(500);
    PRINT 'Đã thêm cột lyDoHuy vào PhieuDatXe';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'PhieuDatXe' AND COLUMN_NAME = 'nguoiLienHe')
BEGIN
    ALTER TABLE PhieuDatXe ADD nguoiLienHe NVARCHAR(100);
    PRINT 'Đã thêm cột nguoiLienHe vào PhieuDatXe';
END

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'PhieuDatXe' AND COLUMN_NAME = 'soDienThoaiLienHe')
BEGIN
    ALTER TABLE PhieuDatXe ADD soDienThoaiLienHe VARCHAR(15);
    PRINT 'Đã thêm cột soDienThoaiLienHe vào PhieuDatXe';
END

-- Cập nhật dữ liệu PhieuDatXe
UPDATE PhieuDatXe SET 
    phanTramCoc = CASE 
        WHEN GiaTriDuKien > 0 AND TienDatCoc > 0 
        THEN (TienDatCoc * 100.0 / GiaTriDuKien)
        ELSE 10.0 
    END
WHERE phanTramCoc IS NULL;

PRINT 'Hoàn tất cập nhật database đơn giản!';
PRINT 'Các bảng hiện có đã được cập nhật với các cột mới.';
PRINT 'Hệ thống v2.0 đã sẵn sàng!';