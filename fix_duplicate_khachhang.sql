-- =============================================
-- SCRIPT XÓA DỮ LIỆU TRÙNG LẶP KHÁCH HÀNG
-- =============================================

USE QLBanXe;
GO

-- 1. Kiểm tra dữ liệu trùng lặp
SELECT MaKhachHang, TenKhachHang, COUNT(*) as SoLuong
FROM KhachHang 
GROUP BY MaKhachHang, TenKhachHang
HAVING COUNT(*) > 1;

-- 2. Xóa dữ liệu trùng lặp, giữ lại record có ID nhỏ nhất
WITH CTE AS (
    SELECT MaKhachHang, TenKhachHang, SoDienThoai, Email, DiaChi, NgayDangKy,
           ROW_NUMBER() OVER (PARTITION BY MaKhachHang ORDER BY NgayDangKy ASC) as rn
    FROM KhachHang
)
DELETE FROM KhachHang 
WHERE EXISTS (
    SELECT 1 FROM CTE 
    WHERE CTE.MaKhachHang = KhachHang.MaKhachHang 
    AND CTE.rn > 1
    AND CTE.NgayDangKy = KhachHang.NgayDangKy
);

-- 3. Kiểm tra lại sau khi xóa
SELECT MaKhachHang, TenKhachHang, NgayDangKy
FROM KhachHang 
ORDER BY MaKhachHang;

PRINT N'✓ Đã xóa dữ liệu trùng lặp khách hàng';