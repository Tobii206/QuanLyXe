-- =============================================
-- SCRIPT KIỂM TRA TRIGGER VÀ STORED PROCEDURE TỰ ĐỘNG TẠO DỮ LIỆU
-- =============================================

USE QLBanXe;
GO

-- 1. Kiểm tra tất cả trigger trong database
PRINT N'=== DANH SÁCH TRIGGER ===';
SELECT 
    t.name AS TriggerName,
    OBJECT_NAME(t.parent_id) AS TableName,
    t.type_desc AS TriggerType,
    t.is_disabled AS IsDisabled
FROM sys.triggers t
WHERE t.parent_id > 0
ORDER BY OBJECT_NAME(t.parent_id);

-- 2. Kiểm tra stored procedure có thể tự động chạy
PRINT N'=== DANH SÁCH STORED PROCEDURE ===';
SELECT 
    p.name AS ProcedureName,
    p.type_desc AS Type,
    p.create_date AS CreateDate,
    p.modify_date AS ModifyDate
FROM sys.procedures p
ORDER BY p.name;

-- 3. Kiểm tra job scheduler (nếu có)
PRINT N'=== DANH SÁCH SQL AGENT JOB ===';
IF EXISTS (SELECT * FROM sys.databases WHERE name = 'msdb')
BEGIN
    SELECT 
        j.name AS JobName,
        j.enabled AS IsEnabled,
        j.description AS Description
    FROM msdb.dbo.sysjobs j
    WHERE j.name LIKE '%KhachHang%' OR j.name LIKE '%Insert%' OR j.name LIKE '%Sample%'
    ORDER BY j.name;
END

-- 4. Kiểm tra dữ liệu hiện tại
PRINT N'=== DỮ LIỆU KHÁCH HÀNG HIỆN TẠI ===';
SELECT 
    MaKhachHang, 
    TenKhachHang, 
    NgayDangKy,
    DATEDIFF(MINUTE, NgayDangKy, GETDATE()) AS PhutTruoc
FROM KhachHang 
ORDER BY NgayDangKy DESC;

-- 5. Kiểm tra constraint và default value
PRINT N'=== CONSTRAINT VÀ DEFAULT VALUE ===';
SELECT 
    c.COLUMN_NAME,
    c.COLUMN_DEFAULT,
    c.IS_NULLABLE
FROM INFORMATION_SCHEMA.COLUMNS c
WHERE c.TABLE_NAME = 'KhachHang'
ORDER BY c.ORDINAL_POSITION;