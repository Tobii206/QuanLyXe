-- =============================================
-- SCRIPT THÊM BẢNG LỊCH LÀM VIỆC
-- =============================================

USE QLBanXe;
GO

-- Kiểm tra và tạo bảng LichLamViec nếu chưa có
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'LichLamViec')
BEGIN
    CREATE TABLE LichLamViec (
        MaLich INT IDENTITY(1,1) PRIMARY KEY,
        MaNhanVien VARCHAR(10) NOT NULL,
        NgayLamViec DATE NOT NULL,
        CaLamViec NVARCHAR(20) NOT NULL, -- 'Sáng', 'Chiều', 'Tối', 'Cả ngày'
        GhiChu NVARCHAR(500),
        TrangThai INT DEFAULT 0, -- 0: Chưa làm, 1: Đã làm, 2: Nghỉ
        NgayTao DATETIME DEFAULT GETDATE(),
        FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien)
    );
    PRINT N'✓ Đã tạo bảng LichLamViec';
END
ELSE
BEGIN
    PRINT N'⚠ Bảng LichLamViec đã tồn tại';
END
GO

-- Thêm dữ liệu mẫu cho lịch làm việc
IF NOT EXISTS (SELECT * FROM LichLamViec WHERE MaLich = 1)
BEGIN
    INSERT INTO LichLamViec (MaNhanVien, NgayLamViec, CaLamViec, GhiChu, TrangThai) VALUES
    -- Lịch cho NV001 (Admin)
    ('NV001', GETDATE(), N'Cả ngày', N'Quản lý chung', 1),
    ('NV001', DATEADD(DAY, 1, GETDATE()), N'Cả ngày', N'Họp ban giám đốc', 0),
    ('NV001', DATEADD(DAY, 2, GETDATE()), N'Sáng', N'Kiểm tra kho', 0),
    
    -- Lịch cho NV002 (Sales)
    ('NV002', GETDATE(), N'Cả ngày', N'Tư vấn khách hàng', 1),
    ('NV002', DATEADD(DAY, 1, GETDATE()), N'Sáng', N'Gặp khách VIP', 0),
    ('NV002', DATEADD(DAY, 1, GETDATE()), N'Chiều', N'Bàn giao xe', 0),
    ('NV002', DATEADD(DAY, 2, GETDATE()), N'Cả ngày', N'Hội chợ xe', 0),
    
    -- Lịch cho NV003 (Kế toán)
    ('NV003', GETDATE(), N'Cả ngày', N'Đối chiếu sổ sách', 1),
    ('NV003', DATEADD(DAY, 1, GETDATE()), N'Sáng', N'Báo cáo tài chính', 0),
    ('NV003', DATEADD(DAY, 2, GETDATE()), N'Cả ngày', N'Kiểm toán', 0),
    
    -- Lịch cho NV004 (Hậu mãi)
    ('NV004', GETDATE(), N'Cả ngày', N'Bảo dưỡng xe khách hàng', 1),
    ('NV004', DATEADD(DAY, 1, GETDATE()), N'Sáng', N'Sửa chữa xe', 0),
    ('NV004', DATEADD(DAY, 1, GETDATE()), N'Chiều', N'Tư vấn bảo dưỡng', 0),
    ('NV004', DATEADD(DAY, 2, GETDATE()), N'Cả ngày', N'Kiểm tra định kỳ', 0);
    
    PRINT N'✓ Đã thêm 14 lịch làm việc mẫu';
END
ELSE
BEGIN
    PRINT N'⚠ Dữ liệu lịch làm việc đã tồn tại';
END
GO

PRINT N'';
PRINT N'========================================';
PRINT N'✓ HOÀN TẤT THÊM BẢNG LỊCH LÀM VIỆC!';
PRINT N'========================================';
