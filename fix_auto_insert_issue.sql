-- =============================================
-- SCRIPT FIX VẤN ĐỀ TỰ ĐỘNG TẠO DỮ LIỆU KHÁCH HÀNG
-- =============================================

USE QLBanXe;
GO

-- 1. Xóa tất cả khách hàng trùng lặp
PRINT N'Đang xóa dữ liệu khách hàng trùng lặp...';

-- Xóa tất cả trừ bản ghi đầu tiên của mỗi MaKhachHang
WITH CTE AS (
    SELECT *, ROW_NUMBER() OVER (PARTITION BY MaKhachHang ORDER BY NgayDangKy ASC) as rn
    FROM KhachHang
)
DELETE FROM CTE WHERE rn > 1;

-- 2. Kiểm tra kết quả
SELECT COUNT(*) as TongSoKhachHang FROM KhachHang;
SELECT MaKhachHang, TenKhachHang, NgayDangKy FROM KhachHang ORDER BY MaKhachHang;

-- 3. Tạo constraint để tránh trùng lặp trong tương lai
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'IX_KhachHang_MaKhachHang_Unique')
BEGIN
    CREATE UNIQUE INDEX IX_KhachHang_MaKhachHang_Unique 
    ON KhachHang (MaKhachHang);
    PRINT N'✓ Đã tạo unique constraint cho MaKhachHang';
END

PRINT N'✓ Hoàn thành fix vấn đề tự động tạo dữ liệu';