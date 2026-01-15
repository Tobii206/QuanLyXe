-- =============================================
-- SCRIPT DỪNG TỰ ĐỘNG TẠO DỮ LIỆU KHÁCH HÀNG
-- =============================================

USE QLBanXe;
GO

-- 1. Disable tất cả trigger trên bảng KhachHang (nếu có)
DECLARE @sql NVARCHAR(MAX) = '';
SELECT @sql = @sql + 'DISABLE TRIGGER ' + t.name + ' ON KhachHang;' + CHAR(13)
FROM sys.triggers t
WHERE t.parent_id = OBJECT_ID('KhachHang');

IF LEN(@sql) > 0
BEGIN
    PRINT N'Đang disable trigger...';
    EXEC sp_executesql @sql;
    PRINT N'✓ Đã disable tất cả trigger trên bảng KhachHang';
END
ELSE
BEGIN
    PRINT N'⚠ Không có trigger nào trên bảng KhachHang';
END

-- 2. Xóa dữ liệu trùng lặp
PRINT N'Đang xóa dữ liệu trùng lặp...';

-- Backup dữ liệu trước khi xóa
SELECT * INTO KhachHang_Backup FROM KhachHang;
PRINT N'✓ Đã backup dữ liệu vào bảng KhachHang_Backup';

-- Xóa dữ liệu trùng lặp, giữ lại record cũ nhất
WITH CTE AS (
    SELECT *, ROW_NUMBER() OVER (PARTITION BY MaKhachHang ORDER BY NgayDangKy ASC) as rn
    FROM KhachHang
)
DELETE FROM CTE WHERE rn > 1;

-- 3. Kiểm tra kết quả
PRINT N'=== KẾT QUẢ SAU KHI XÓA ===';
SELECT 
    COUNT(*) as TongSoKhachHang,
    MIN(NgayDangKy) as NgayDangKyDauTien,
    MAX(NgayDangKy) as NgayDangKyCuoi
FROM KhachHang;

SELECT MaKhachHang, TenKhachHang, NgayDangKy 
FROM KhachHang 
ORDER BY MaKhachHang;

PRINT N'✓ Hoàn thành dừng tự động tạo dữ liệu';