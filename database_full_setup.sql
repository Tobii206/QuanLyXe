-- =============================================
-- SCRIPT HOÀN CHỈNH: TẠO DATABASE PORSCHE TỪ ĐẦU
-- Bao gồm: Xóa DB cũ + Tạo bảng + Thêm dữ liệu
-- =============================================

-- =============================================
-- BƯỚC 1: XÓA DATABASE CŨ (NẾU CÓ)
-- =============================================
USE master;
GO

IF EXISTS (SELECT name FROM sys.databases WHERE name = 'QLBanXe')
BEGIN
    ALTER DATABASE QLBanXe SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE QLBanXe;
    PRINT N'✓ Đã xóa database cũ';
END
GO

-- =============================================
-- BƯỚC 2: TẠO DATABASE MỚI
-- =============================================
CREATE DATABASE QLBanXe;
GO
PRINT N'✓ Đã tạo database QLBanXe';
GO

USE QLBanXe;
GO

-- =============================================
-- BƯỚC 3: TẠO CÁC BẢNG
-- =============================================

-- Bảng Chức vụ
CREATE TABLE ChucVu (
    MaChucVu VARCHAR(10) PRIMARY KEY,
    TenChucVu NVARCHAR(50) NOT NULL
);
PRINT N'✓ Đã tạo bảng ChucVu';

-- Bảng Nhân viên
CREATE TABLE NhanVien (
    MaNhanVien VARCHAR(10) PRIMARY KEY,
    hoTen NVARCHAR(100) NOT NULL,
    GioiTinh NVARCHAR(10),
    SoDienThoai VARCHAR(15),
    Email VARCHAR(100),
    MaChucVu VARCHAR(10),
    TrangThai INT DEFAULT 0,
    FOREIGN KEY (MaChucVu) REFERENCES ChucVu(MaChucVu)
);
PRINT N'✓ Đã tạo bảng NhanVien';

-- Bảng Đăng nhập
CREATE TABLE DangNhap (
    TaiKhoan VARCHAR(50) PRIMARY KEY,
    MatKhau VARCHAR(50) NOT NULL,
    MaNhanVien VARCHAR(10),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien)
);
PRINT N'✓ Đã tạo bảng DangNhap';

-- Bảng Khách hàng
CREATE TABLE KhachHang (
    MaKhachHang VARCHAR(10) PRIMARY KEY,
    TenKhachHang NVARCHAR(100) NOT NULL,
    GioiTinh NVARCHAR(10),
    SoDienThoai VARCHAR(15),
    Email VARCHAR(100),
    DiaChi NVARCHAR(200),
    TrangThai INT DEFAULT 0,
    NgayDangKy DATE DEFAULT GETDATE()
);
PRINT N'✓ Đã tạo bảng KhachHang';

-- Bảng Xuất xứ
CREATE TABLE XuatXu (
    MaXuatXu VARCHAR(10) PRIMARY KEY,
    TenXuatXu NVARCHAR(50) NOT NULL
);
PRINT N'✓ Đã tạo bảng XuatXu';

-- Bảng Chất liệu
CREATE TABLE ChatLieu (
    MaChatLieu VARCHAR(10) PRIMARY KEY,
    TenChatLieu NVARCHAR(100) NOT NULL
);
PRINT N'✓ Đã tạo bảng ChatLieu';

-- Bảng Màu sắc
CREATE TABLE MauSac (
    MaMauSac VARCHAR(10) PRIMARY KEY,
    TenMau NVARCHAR(50) NOT NULL
);
PRINT N'✓ Đã tạo bảng MauSac';

-- Bảng Kích thước (Phiên bản)
CREATE TABLE KichThuoc (
    MaKichThuoc VARCHAR(10) PRIMARY KEY,
    TenKichThuoc NVARCHAR(50) NOT NULL
);
PRINT N'✓ Đã tạo bảng KichThuoc';

-- Bảng Sản phẩm
CREATE TABLE SanPham (
    MaSanPham VARCHAR(10) PRIMARY KEY,
    TenSanPham NVARCHAR(100) NOT NULL,
    MaChatLieu VARCHAR(10),
    MaXuatSu VARCHAR(10),
    SoLuong INT DEFAULT 0,
    DonGia BIGINT,
    MoTa NVARCHAR(500),
    TrangThai INT DEFAULT 1,
    FOREIGN KEY (MaChatLieu) REFERENCES ChatLieu(MaChatLieu),
    FOREIGN KEY (MaXuatSu) REFERENCES XuatXu(MaXuatXu)
);
PRINT N'✓ Đã tạo bảng SanPham';

-- Bảng Chi tiết sản phẩm
CREATE TABLE ChiTietSanPham (
    MaChiTietSP VARCHAR(10) PRIMARY KEY,
    MaSanPham VARCHAR(10),
    MaMauSac VARCHAR(10),
    MaKichThuoc VARCHAR(10),
    SoLuong INT DEFAULT 0,
    DonGia BIGINT,
    FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham),
    FOREIGN KEY (MaMauSac) REFERENCES MauSac(MaMauSac),
    FOREIGN KEY (MaKichThuoc) REFERENCES KichThuoc(MaKichThuoc)
);
PRINT N'✓ Đã tạo bảng ChiTietSanPham';

-- Bảng Thông tin xe
CREATE TABLE ThongTinXe (
    MaXe VARCHAR(10) PRIMARY KEY,
    MaSanPham VARCHAR(10),
    MaMauSac VARCHAR(10),
    MaKichThuoc VARCHAR(10),
    SoKhung VARCHAR(50),
    SoMay VARCHAR(50),
    NamSanXuat INT,
    GiaBan BIGINT,
    TinhTrang NVARCHAR(50),
    NgayNhapKho DATE,
    NgayBan DATE,
    FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham),
    FOREIGN KEY (MaMauSac) REFERENCES MauSac(MaMauSac),
    FOREIGN KEY (MaKichThuoc) REFERENCES KichThuoc(MaKichThuoc)
);
PRINT N'✓ Đã tạo bảng ThongTinXe';

-- Bảng Phiếu đặt xe
CREATE TABLE PhieuDatXe (
    MaPhieuDat VARCHAR(10) PRIMARY KEY,
    MaKhachHang VARCHAR(10),
    MaNhanVien VARCHAR(10),
    MaSanPham VARCHAR(10),
    MaMauSac VARCHAR(10),
    MaKichThuoc VARCHAR(10),
    YeuCauCauHinh NVARCHAR(500),
    GiaTriDuKien BIGINT,
    TienDatCoc BIGINT,
    PhanTramCoc INT,
    NgayDat DATE DEFAULT GETDATE(),
    NgayDuKienVe DATE,
    TienDoNhap NVARCHAR(100),
    MaXe VARCHAR(10),
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
    FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham),
    FOREIGN KEY (MaMauSac) REFERENCES MauSac(MaMauSac),
    FOREIGN KEY (MaKichThuoc) REFERENCES KichThuoc(MaKichThuoc)
);
PRINT N'✓ Đã tạo bảng PhieuDatXe';

-- Bảng Phiếu giảm giá
CREATE TABLE PhieuGiamGia (
    MaPhieu VARCHAR(10) PRIMARY KEY,
    TenPhieu NVARCHAR(200),
    TenHinhThucGG NVARCHAR(50),
    GiaTri INT,
    SoLuong INT,
    NgayBatDau DATE,
    NgayKetThuc DATE,
    DieuKienApDung NVARCHAR(500),
    TrangThai INT DEFAULT 1
);
PRINT N'✓ Đã tạo bảng PhieuGiamGia';

-- Bảng Hóa đơn
CREATE TABLE HoaDon (
    MaHoaDon VARCHAR(10) PRIMARY KEY,
    MaKhachHang VARCHAR(10),
    MaNhanVien VARCHAR(10),
    TongTien BIGINT,
    TrangThai INT DEFAULT 0,
    NgayTao DATE DEFAULT GETDATE(),
    SoDienThoai VARCHAR(15),
    Email VARCHAR(100),
    TenKhachHang NVARCHAR(100),
    MaPhieu VARCHAR(10),
    TongTienGoc BIGINT,
    TienGiam BIGINT,
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
    FOREIGN KEY (MaPhieu) REFERENCES PhieuGiamGia(MaPhieu)
);
PRINT N'✓ Đã tạo bảng HoaDon';

-- Bảng Chi tiết hóa đơn
CREATE TABLE ChiTietHoaDon (
    MaChiTietHoaDon VARCHAR(10) PRIMARY KEY,
    MaHoaDon VARCHAR(10),
    MaChiTietSP VARCHAR(10),
    SoLuong INT,
    DonGia BIGINT,
    FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
    FOREIGN KEY (MaChiTietSP) REFERENCES ChiTietSanPham(MaChiTietSP)
);
PRINT N'✓ Đã tạo bảng ChiTietHoaDon';

PRINT N'';
PRINT N'========================================';
PRINT N'✓ ĐÃ TẠO XONG TẤT CẢ CÁC BẢNG!';
PRINT N'========================================';
PRINT N'';
GO

-- =============================================
-- BƯỚC 4: THÊM DỮ LIỆU MẪU
-- =============================================

-- 1. Chức vụ
INSERT INTO ChucVu (MaChucVu, TenChucVu) VALUES 
('CV001', N'Quản lý'),
('CV002', N'Sales'),
('CV003', N'Kế toán'),
('CV004', N'Hậu mãi');
PRINT N'✓ Đã thêm 4 chức vụ';

-- 2. Nhân viên
INSERT INTO NhanVien (MaNhanVien, hoTen, GioiTinh, SoDienThoai, Email, MaChucVu, TrangThai) VALUES
('NV001', N'Nguyễn Văn Admin', N'Nam', '0901111111', 'admin@porsche.vn', 'CV001', 0),
('NV002', N'Trần Thị Sales', N'Nữ', '0902222222', 'sales1@porsche.vn', 'CV002', 0),
('NV003', N'Lê Văn Kế toán', N'Nam', '0903333333', 'ketoan@porsche.vn', 'CV003', 0),
('NV004', N'Phạm Thị Hậu mãi', N'Nữ', '0904444444', 'haumai@porsche.vn', 'CV004', 0);
PRINT N'✓ Đã thêm 4 nhân viên';

-- 3. Tài khoản đăng nhập
INSERT INTO DangNhap (TaiKhoan, MatKhau, MaNhanVien) VALUES
('admin', '123456', 'NV001'),
('sales1', '123456', 'NV002'),
('ketoan1', '123456', 'NV003'),
('haumai1', '123456', 'NV004');
PRINT N'✓ Đã thêm 4 tài khoản đăng nhập';

-- 4. Khách hàng
INSERT INTO KhachHang (MaKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, DiaChi, TrangThai, NgayDangKy) VALUES
('KH001', N'Nguyễn Văn A', N'Nam', '0901234567', 'nva@email.com', N'Hà Nội', 0, GETDATE()),
('KH002', N'Trần Thị B', N'Nữ', '0902345678', 'ttb@email.com', N'TP.HCM', 0, GETDATE()),
('KH003', N'Lê Văn C', N'Nam', '0903456789', 'lvc@email.com', N'Đà Nẵng', 0, GETDATE()),
('KH004', N'Phạm Thị D', N'Nữ', '0904567890', 'ptd@email.com', N'Hải Phòng', 0, GETDATE()),
('KH005', N'Hoàng Văn E', N'Nam', '0905678901', 'hve@email.com', N'Cần Thơ', 0, GETDATE());
PRINT N'✓ Đã thêm 5 khách hàng';

-- 5. Xuất xứ
INSERT INTO XuatXu (MaXuatXu, TenXuatXu) VALUES
('XX001', N'Đức (Germany)'),
('XX002', N'Nhật Bản'),
('XX003', N'Hàn Quốc'),
('XX004', N'Mỹ'),
('XX005', N'Anh');
PRINT N'✓ Đã thêm 5 xuất xứ';

-- 6. Chất liệu
INSERT INTO ChatLieu (MaChatLieu, TenChatLieu) VALUES
('CL001', N'Hợp kim nhôm cao cấp'),
('CL002', N'Sợi carbon'),
('CL003', N'Thép không gỉ'),
('CL004', N'Titan'),
('CL005', N'Nhựa composite');
PRINT N'✓ Đã thêm 5 chất liệu';

-- 7. Màu sắc
INSERT INTO MauSac (MaMauSac, TenMau) VALUES
('MS001', N'Đen (Black)'),
('MS002', N'Trắng (White)'),
('MS003', N'Đỏ (Red)'),
('MS004', N'Xanh dương (Blue)'),
('MS005', N'Xám (Grey)'),
('MS006', N'Bạc (Silver)'),
('MS007', N'Vàng (Yellow)'),
('MS008', N'Xanh lá (Green)');
PRINT N'✓ Đã thêm 8 màu sắc';

-- 8. Kích thước (Phiên bản)
INSERT INTO KichThuoc (MaKichThuoc, TenKichThuoc) VALUES
('KT001', N'Base'),
('KT002', N'S (Sport)'),
('KT003', N'GTS'),
('KT004', N'Turbo');
PRINT N'✓ Đã thêm 4 phiên bản';

-- 9. Sản phẩm (Xe Porsche)
INSERT INTO SanPham (MaSanPham, TenSanPham, MaChatLieu, MaXuatSu, SoLuong, DonGia, MoTa, TrangThai) VALUES
('SP001', N'Porsche 911 Carrera', 'CL001', 'XX001', 5, 8000000000, N'Xe thể thao huyền thoại, động cơ boxer 6 xi-lanh', 1),
('SP002', N'Porsche Cayenne', 'CL001', 'XX001', 8, 5000000000, N'SUV hạng sang, mạnh mẽ và tiện nghi', 1),
('SP003', N'Porsche Panamera', 'CL001', 'XX001', 6, 6000000000, N'Sedan thể thao 4 cửa, sang trọng', 1),
('SP004', N'Porsche Macan', 'CL001', 'XX001', 10, 3500000000, N'SUV compact, linh hoạt và thể thao', 1);
PRINT N'✓ Đã thêm 4 dòng xe Porsche';

-- 10. Chi tiết sản phẩm
INSERT INTO ChiTietSanPham (MaChiTietSP, MaSanPham, MaMauSac, MaKichThuoc, SoLuong, DonGia) VALUES
-- Porsche 911 Carrera
('CTSP001', 'SP001', 'MS001', 'KT003', 2, 8000000000),
('CTSP002', 'SP001', 'MS003', 'KT003', 2, 8000000000),
('CTSP003', 'SP001', 'MS006', 'KT004', 1, 8500000000),
-- Porsche Cayenne
('CTSP004', 'SP002', 'MS002', 'KT002', 3, 5000000000),
('CTSP005', 'SP002', 'MS001', 'KT002', 3, 5000000000),
('CTSP006', 'SP002', 'MS005', 'KT003', 2, 5500000000),
-- Porsche Panamera
('CTSP007', 'SP003', 'MS003', 'KT001', 2, 6000000000),
('CTSP008', 'SP003', 'MS002', 'KT002', 2, 6200000000),
('CTSP009', 'SP003', 'MS001', 'KT003', 2, 6500000000),
-- Porsche Macan
('CTSP010', 'SP004', 'MS004', 'KT002', 4, 3500000000),
('CTSP011', 'SP004', 'MS002', 'KT002', 3, 3500000000),
('CTSP012', 'SP004', 'MS001', 'KT003', 3, 3800000000);
PRINT N'✓ Đã thêm 12 biến thể sản phẩm';

-- 11. Thông tin xe cụ thể
INSERT INTO ThongTinXe (MaXe, MaSanPham, MaMauSac, MaKichThuoc, SoKhung, SoMay, NamSanXuat, GiaBan, TinhTrang, NgayNhapKho) VALUES
('XE001', 'SP001', 'MS001', 'KT003', 'WP0ZZZ99ZTS392001', 'M97.21-001', 2024, 8000000000, N'Đã về kho', GETDATE()),
('XE002', 'SP002', 'MS002', 'KT002', 'WP1ZZZ92ZTS392002', 'M48.00-002', 2024, 5000000000, N'Đã về kho', GETDATE()),
('XE003', 'SP003', 'MS003', 'KT001', 'WP0ZZZ97ZTS392003', 'M46.00-003', 2024, 6000000000, N'Đã về kho', GETDATE()),
('XE004', 'SP004', 'MS004', 'KT002', 'WP1ZZZ95ZTS392004', 'M48.01-004', 2024, 3500000000, N'Đã về kho', GETDATE());
PRINT N'✓ Đã thêm 4 xe cụ thể';

-- 12. Phiếu đặt xe
INSERT INTO PhieuDatXe (MaPhieuDat, MaKhachHang, MaNhanVien, MaSanPham, MaMauSac, MaKichThuoc, YeuCauCauHinh, GiaTriDuKien, TienDatCoc, PhanTramCoc, NgayDat, TienDoNhap) VALUES
('PDX001', 'KH001', 'NV002', 'SP001', 'MS001', 'KT003', N'Màu đen, nội thất da, camera 360', 8000000000, 800000000, 10, GETDATE(), N'Chờ xác nhận'),
('PDX002', 'KH002', 'NV002', 'SP002', 'MS002', 'KT002', N'Màu trắng, cửa sổ trời', 5000000000, 1000000000, 20, GETDATE(), N'Đang sản xuất'),
('PDX003', 'KH003', 'NV002', 'SP003', 'MS003', 'KT001', N'Màu đỏ, ghế massage', 6000000000, 600000000, 10, GETDATE(), N'Đang vận chuyển'),
('PDX004', 'KH004', 'NV002', 'SP004', 'MS004', 'KT002', N'Màu xanh, hệ thống âm thanh Bose', 3500000000, 700000000, 20, GETDATE(), N'Về cảng');
PRINT N'✓ Đã thêm 4 phiếu đặt xe';

-- 13. Phiếu giảm giá
INSERT INTO PhieuGiamGia (MaPhieu, TenPhieu, TenHinhThucGG, GiaTri, SoLuong, NgayBatDau, NgayKetThuc, DieuKienApDung, TrangThai) VALUES
('PGG001', N'Giảm giá 5% cho khách VIP', N'Phần trăm', 5, 100, GETDATE(), DATEADD(MONTH, 3, GETDATE()), N'Áp dụng cho khách VIP', 1),
('PGG002', N'Giảm giá 10% mua xe đầu tiên', N'Phần trăm', 10, 50, GETDATE(), DATEADD(MONTH, 6, GETDATE()), N'Khách hàng mới', 1),
('PGG003', N'Giảm giá 3% thanh toán trước', N'Phần trăm', 3, 200, GETDATE(), DATEADD(MONTH, 1, GETDATE()), N'Thanh toán 100% trước', 1);
PRINT N'✓ Đã thêm 3 phiếu giảm giá';

-- 14. Hóa đơn mẫu
INSERT INTO HoaDon (MaHoaDon, MaKhachHang, MaNhanVien, TongTien, TrangThai, NgayTao) VALUES
('HD001', 'KH001', 'NV002', 8000000000, 1, DATEADD(DAY, -5, GETDATE())),
('HD002', 'KH002', 'NV002', 5000000000, 1, DATEADD(DAY, -3, GETDATE())),
('HD003', 'KH003', 'NV002', 6000000000, 0, GETDATE());
PRINT N'✓ Đã thêm 3 hóa đơn';

-- 15. Chi tiết hóa đơn
INSERT INTO ChiTietHoaDon (MaChiTietHoaDon, MaHoaDon, MaChiTietSP, SoLuong, DonGia) VALUES
('CH001', 'HD001', 'CTSP001', 1, 8000000000),
('CH002', 'HD002', 'CTSP004', 1, 5000000000),
('CH003', 'HD003', 'CTSP007', 1, 6000000000);
PRINT N'✓ Đã thêm 3 chi tiết hóa đơn';

GO

-- =============================================
-- HOÀN TẤT
-- =============================================
PRINT N'';
PRINT N'========================================';
PRINT N'✓✓✓ HOÀN TẤT CÀI ĐẶT DATABASE! ✓✓✓';
PRINT N'========================================';
PRINT N'';
PRINT N'📊 Thống kê dữ liệu:';
PRINT N'- Chức vụ: 4';
PRINT N'- Nhân viên: 4';
PRINT N'- Tài khoản: 4 (mật khẩu: 123456)';
PRINT N'- Khách hàng: 5';
PRINT N'- Xuất xứ: 5';
PRINT N'- Chất liệu: 5';
PRINT N'- Màu sắc: 8';
PRINT N'- Phiên bản: 4';
PRINT N'- Sản phẩm: 4 dòng xe Porsche';
PRINT N'- Chi tiết SP: 12 biến thể';
PRINT N'- Thông tin xe: 4 xe cụ thể';
PRINT N'- Phiếu đặt xe: 4';
PRINT N'- Phiếu giảm giá: 3';
PRINT N'- Hóa đơn: 3';
PRINT N'';
PRINT N'🚗 Dòng xe Porsche:';
PRINT N'- Porsche 911 Carrera: 8 tỷ VNĐ';
PRINT N'- Porsche Cayenne: 5 tỷ VNĐ';
PRINT N'- Porsche Panamera: 6 tỷ VNĐ';
PRINT N'- Porsche Macan: 3.5 tỷ VNĐ';
PRINT N'';
PRINT N'🔐 Tài khoản đăng nhập:';
PRINT N'- admin / 123456 (hoặc admin@porsche.vn)';
PRINT N'- sales1 / 123456 (hoặc sales1@porsche.vn)';
PRINT N'- ketoan1 / 123456 (hoặc ketoan@porsche.vn)';
PRINT N'- haumai1 / 123456 (hoặc haumai@porsche.vn)';
PRINT N'';
PRINT N'✅ Bạn có thể đăng nhập bằng USERNAME hoặc EMAIL';
PRINT N'';
GO
