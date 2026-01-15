-- =============================================
-- SCRIPT THÊM BẢNG XE VÀ CHITIETXE CHO TRANG KHÁCH HÀNG
-- =============================================
USE QLBanXe;
GO

-- =============================================
-- 1. TẠO BẢNG XE (Dòng xe Porsche)
-- =============================================
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Xe')
BEGIN
    CREATE TABLE Xe (
        Id INT IDENTITY(1,1) PRIMARY KEY,
        Ten NVARCHAR(100) NOT NULL,
        MoTa NVARCHAR(500),
        HangXe NVARCHAR(50) DEFAULT N'Porsche',
        TrangThai INT DEFAULT 1, -- 1: Hoạt động, 0: Ngừng kinh doanh
        NgayTao DATETIME DEFAULT GETDATE()
    );
    PRINT N'✓ Đã tạo bảng Xe';
END
ELSE
BEGIN
    PRINT N'⚠ Bảng Xe đã tồn tại';
END
GO

-- =============================================
-- 2. TẠO BẢNG CHITIETXE (Chi tiết từng xe)
-- =============================================
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'ChiTietXe')
BEGIN
    CREATE TABLE ChiTietXe (
        Id INT IDENTITY(1,1) PRIMARY KEY,
        IdXe INT NOT NULL,
        IdDongCo INT, -- ID động cơ (có thể link với bảng khác)
        NamSX INT NOT NULL, -- Năm sản xuất
        TinhTrangXe NVARCHAR(50) DEFAULT N'Mới 100%',
        GiaBan DECIMAL(18,0) NOT NULL,
        SoLuongTon INT DEFAULT 0,
        MoTa NVARCHAR(1000),
        MauSac NVARCHAR(50),
        PhienBan NVARCHAR(50),
        NgayNhapKho DATETIME DEFAULT GETDATE(),
        FOREIGN KEY (IdXe) REFERENCES Xe(Id)
    );
    PRINT N'✓ Đã tạo bảng ChiTietXe';
END
ELSE
BEGIN
    PRINT N'⚠ Bảng ChiTietXe đã tồn tại';
END
GO

-- =============================================
-- 3. THÊM DỮ LIỆU MẪU CHO BẢNG XE
-- =============================================
IF NOT EXISTS (SELECT * FROM Xe WHERE Id = 1)
BEGIN
    SET IDENTITY_INSERT Xe ON;
    
    INSERT INTO Xe (Id, Ten, MoTa, HangXe, TrangThai) VALUES
    (1, N'Porsche 911 Carrera', N'Xe thể thao huyền thoại với động cơ boxer 6 xi-lanh đặt sau, công suất 385 mã lực', N'Porsche', 1),
    (2, N'Porsche Cayenne', N'SUV hạng sang với hiệu suất vượt trội, kết hợp hoàn hảo giữa sức mạnh và tiện nghi', N'Porsche', 1),
    (3, N'Porsche Panamera', N'Sedan thể thao 4 cửa sang trọng, động cơ V8 mạnh mẽ', N'Porsche', 1),
    (4, N'Porsche Macan', N'SUV compact linh hoạt, phù hợp cho đô thị và địa hình', N'Porsche', 1),
    (5, N'Porsche Taycan', N'Xe thể thao điện thuần túy đầu tiên của Porsche, công nghệ tương lai', N'Porsche', 1),
    (6, N'Porsche 718 Cayman', N'Xe thể thao 2 cửa với thiết kế cân đối hoàn hảo', N'Porsche', 1);
    
    SET IDENTITY_INSERT Xe OFF;
    PRINT N'✓ Đã thêm 6 dòng xe Porsche';
END
GO

-- =============================================
-- 4. THÊM DỮ LIỆU MẪU CHO BẢNG CHITIETXE
-- =============================================
IF NOT EXISTS (SELECT * FROM ChiTietXe WHERE Id = 1)
BEGIN
    SET IDENTITY_INSERT ChiTietXe ON;
    
    INSERT INTO ChiTietXe (Id, IdXe, IdDongCo, NamSX, TinhTrangXe, GiaBan, SoLuongTon, MoTa, MauSac, PhienBan) VALUES
    -- Porsche 911 Carrera
    (1, 1, 1, 2024, N'Mới 100%', 8000000000, 3, N'Động cơ 3.0L Boxer 6, 385 HP, 0-100km/h: 4.2s', N'Đen', N'Base'),
    (2, 1, 2, 2024, N'Mới 100%', 8500000000, 2, N'Động cơ 3.0L Boxer 6, 385 HP, gói Sport Chrono', N'Đỏ', N'S'),
    (3, 1, 3, 2024, N'Mới 100%', 9200000000, 1, N'Động cơ 3.0L Boxer 6 Twin-Turbo, 450 HP', N'Bạc', N'GTS'),
    
    -- Porsche Cayenne
    (4, 2, 4, 2024, N'Mới 100%', 5000000000, 5, N'Động cơ V6 3.0L Turbo, 340 HP, AWD', N'Trắng', N'Base'),
    (5, 2, 5, 2024, N'Mới 100%', 5800000000, 3, N'Động cơ V6 3.0L Turbo, 440 HP, Sport Package', N'Xám', N'S'),
    (6, 2, 6, 2024, N'Mới 100%', 7500000000, 2, N'Động cơ V8 4.0L Twin-Turbo, 550 HP', N'Đen', N'Turbo'),
    
    -- Porsche Panamera
    (7, 3, 7, 2024, N'Mới 100%', 6000000000, 4, N'Động cơ V6 2.9L Twin-Turbo, 330 HP', N'Xanh dương', N'Base'),
    (8, 3, 8, 2024, N'Mới 100%', 7200000000, 2, N'Động cơ V8 4.0L Twin-Turbo, 460 HP', N'Đỏ', N'4S'),
    (9, 3, 9, 2024, N'Mới 100%', 9500000000, 1, N'Động cơ V8 4.0L Twin-Turbo, 630 HP', N'Đen', N'Turbo S'),
    
    -- Porsche Macan
    (10, 4, 10, 2024, N'Mới 100%', 3500000000, 6, N'Động cơ 4-cylinder 2.0L Turbo, 265 HP', N'Trắng', N'Base'),
    (11, 4, 11, 2024, N'Mới 100%', 4200000000, 4, N'Động cơ V6 2.9L Twin-Turbo, 380 HP', N'Xám', N'S'),
    (12, 4, 12, 2024, N'Mới 100%', 4800000000, 2, N'Động cơ V6 2.9L Twin-Turbo, 440 HP', N'Đỏ', N'GTS'),
    
    -- Porsche Taycan (Điện)
    (13, 5, 13, 2024, N'Mới 100%', 5500000000, 3, N'Động cơ điện 2 motor, 408 HP, phạm vi 431km', N'Xanh lá', N'Base'),
    (14, 5, 14, 2024, N'Mới 100%', 7000000000, 2, N'Động cơ điện 2 motor, 530 HP, phạm vi 412km', N'Bạc', N'4S'),
    (15, 5, 15, 2024, N'Mới 100%', 10500000000, 1, N'Động cơ điện 2 motor, 761 HP, phạm vi 388km', N'Đen', N'Turbo S'),
    
    -- Porsche 718 Cayman
    (16, 6, 16, 2024, N'Mới 100%', 4500000000, 4, N'Động cơ 4-cylinder 2.0L Turbo, 300 HP', N'Vàng', N'Base'),
    (17, 6, 17, 2024, N'Mới 100%', 5200000000, 2, N'Động cơ 4-cylinder 2.5L Turbo, 350 HP', N'Cam', N'S'),
    (18, 6, 18, 2024, N'Mới 100%', 6500000000, 1, N'Động cơ 4-cylinder 2.5L Turbo, 400 HP', N'Đỏ', N'GTS');
    
    SET IDENTITY_INSERT ChiTietXe OFF;
    PRINT N'✓ Đã thêm 18 chi tiết xe Porsche';
END
GO

-- =============================================
-- HOÀN TẤT
-- =============================================
PRINT N'';
PRINT N'========================================';
PRINT N'✓ ĐÃ TẠO XONG BẢNG XE VÀ CHITIETXE!';
PRINT N'========================================';
PRINT N'';
PRINT N'Thống kê:';
PRINT N'- Dòng xe: 6 (911, Cayenne, Panamera, Macan, Taycan, 718 Cayman)';
PRINT N'- Chi tiết xe: 18 biến thể';
PRINT N'- Tổng số lượng tồn: 51 xe';
PRINT N'';
PRINT N'💰 Giá bán:';
PRINT N'- Porsche 911 Carrera: 8.0 - 9.2 tỷ VNĐ';
PRINT N'- Porsche Cayenne: 5.0 - 7.5 tỷ VNĐ';
PRINT N'- Porsche Panamera: 6.0 - 9.5 tỷ VNĐ';
PRINT N'- Porsche Macan: 3.5 - 4.8 tỷ VNĐ';
PRINT N'- Porsche Taycan: 5.5 - 10.5 tỷ VNĐ';
PRINT N'- Porsche 718 Cayman: 4.5 - 6.5 tỷ VNĐ';
PRINT N'';
PRINT N'📝 Hướng dẫn:';
PRINT N'1. Chạy script này trong SQL Server Management Studio';
PRINT N'2. Khởi động lại ứng dụng Java';
PRINT N'3. Đăng nhập và xem trang chủ khách hàng';
PRINT N'4. Dữ liệu xe sẽ hiển thị trong bảng "CHI TIẾT XE PORSCHE"';
GO
