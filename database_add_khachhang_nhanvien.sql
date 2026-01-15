-- Tạo bảng KhachHang
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'KhachHang')
BEGIN
    CREATE TABLE KhachHang (
        MaKhachHang NVARCHAR(50) PRIMARY KEY,
        TenKhachHang NVARCHAR(100) NOT NULL,
        GioiTinh NVARCHAR(10),
        SoDienThoai NVARCHAR(15),
        Email NVARCHAR(100),
        DiaChi NVARCHAR(255),
        TrangThai BIT DEFAULT 0,
        NgayDangKy DATETIME DEFAULT GETDATE()
    );
    PRINT 'Đã tạo bảng KhachHang';
END
ELSE
BEGIN
    PRINT 'Bảng KhachHang đã tồn tại';
END
GO

-- Tạo bảng ChucVu nếu chưa có
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'ChucVu')
BEGIN
    CREATE TABLE ChucVu (
        MaChucVu NVARCHAR(50) PRIMARY KEY,
        TenChucVu NVARCHAR(100) NOT NULL
    );
    
    -- Thêm dữ liệu mẫu
    INSERT INTO ChucVu (MaChucVu, TenChucVu) VALUES 
    ('CV001', N'Quản lý'),
    ('CV002', N'Nhân viên');
    
    PRINT 'Đã tạo bảng ChucVu và thêm dữ liệu mẫu';
END
ELSE
BEGIN
    PRINT 'Bảng ChucVu đã tồn tại';
END
GO

-- Tạo bảng NhanVien
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'NhanVien')
BEGIN
    CREATE TABLE NhanVien (
        MaNhanVien NVARCHAR(50) PRIMARY KEY,
        hoTen NVARCHAR(100) NOT NULL,
        GioiTinh NVARCHAR(10),
        SoDienThoai NVARCHAR(15),
        Email NVARCHAR(100),
        MaChucVu NVARCHAR(50),
        TrangThai INT DEFAULT 0,
        FOREIGN KEY (MaChucVu) REFERENCES ChucVu(MaChucVu)
    );
    PRINT 'Đã tạo bảng NhanVien';
END
ELSE
BEGIN
    PRINT 'Bảng NhanVien đã tồn tại';
END
GO

-- Tạo bảng DangNhap nếu chưa có
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'DangNhap')
BEGIN
    CREATE TABLE DangNhap (
        MaDangNhap INT IDENTITY(1,1) PRIMARY KEY,
        TaiKhoan NVARCHAR(50) NOT NULL UNIQUE,
        MatKhau NVARCHAR(255) NOT NULL,
        MaNhanVien NVARCHAR(50),
        FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien)
    );
    PRINT 'Đã tạo bảng DangNhap';
END
ELSE
BEGIN
    PRINT 'Bảng DangNhap đã tồn tại';
END
GO

-- Thêm dữ liệu mẫu cho NhanVien
IF NOT EXISTS (SELECT * FROM NhanVien WHERE MaNhanVien = 'NV001')
BEGIN
    INSERT INTO NhanVien (MaNhanVien, hoTen, GioiTinh, SoDienThoai, Email, MaChucVu, TrangThai)
    VALUES ('NV001', N'Nguyễn Văn A', N'Nam', '0123456789', 'admin@example.com', 'CV001', 0);
    
    -- Thêm tài khoản đăng nhập mặc định
    INSERT INTO DangNhap (TaiKhoan, MatKhau, MaNhanVien)
    VALUES ('admin', '123456', 'NV001');
    
    PRINT 'Đã thêm nhân viên và tài khoản đăng nhập mẫu';
END
GO

-- Thêm dữ liệu mẫu cho KhachHang
IF NOT EXISTS (SELECT * FROM KhachHang WHERE MaKhachHang = 'KH001')
BEGIN
    INSERT INTO KhachHang (MaKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, DiaChi, TrangThai)
    VALUES 
    ('KH001', N'Trần Thị B', N'Nữ', '0987654321', 'khach1@example.com', N'Hà Nội', 0),
    ('KH002', N'Lê Văn C', N'Nam', '0912345678', 'khach2@example.com', N'TP.HCM', 0);
    
    PRINT 'Đã thêm khách hàng mẫu';
END
GO

PRINT 'Hoàn thành tạo cấu trúc database!';
