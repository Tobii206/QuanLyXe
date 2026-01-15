-- Tạo bảng DangNhap để tương thích với hệ thống cũ
USE QLBanXe;
GO

-- Tạo bảng DangNhap nếu chưa có
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'DangNhap')
BEGIN
    CREATE TABLE DangNhap (
        MaDangNhap INT PRIMARY KEY IDENTITY(1,1),
        MaNhanVien VARCHAR(20) NOT NULL,
        TaiKhoan VARCHAR(50) UNIQUE NOT NULL,
        MatKhau VARCHAR(100) NOT NULL,
        FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(maNhanVien)
    );
    
    PRINT 'Đã tạo bảng DangNhap';
END
GO

-- Thêm dữ liệu đăng nhập từ bảng NhanVien
INSERT INTO DangNhap (MaNhanVien, TaiKhoan, MatKhau)
SELECT maNhanVien, taiKhoan, matKhau
FROM NhanVien
WHERE maNhanVien NOT IN (SELECT MaNhanVien FROM DangNhap);
GO

PRINT 'Đã đồng bộ dữ liệu đăng nhập';
PRINT '';
PRINT '=== TÀI KHOẢN ĐĂNG NHẬP ===';
PRINT 'Admin: admin / 123456';
PRINT 'Sales: sales1 / 123456';
PRINT 'Kế toán: ketoan1 / 123456';
PRINT 'Hậu mãi: haumai1 / 123456';
GO
