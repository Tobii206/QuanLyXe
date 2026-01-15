-- Thêm cột TienTraTruoc vào bảng HoaDon để lưu số tiền đã trả trước (đặt cọc)
USE QLBanXe;
GO

-- Kiểm tra và thêm cột TienTraTruoc nếu chưa có
IF NOT EXISTS (SELECT * FROM sys.columns WHERE object_id = OBJECT_ID(N'HoaDon') AND name = 'TienTraTruoc')
BEGIN
    ALTER TABLE HoaDon
    ADD TienTraTruoc DECIMAL(18, 2) DEFAULT 0;
    
    PRINT 'Đã thêm cột TienTraTruoc vào bảng HoaDon';
END
ELSE
BEGIN
    PRINT 'Cột TienTraTruoc đã tồn tại trong bảng HoaDon';
END
GO

-- Cập nhật giá trị mặc định cho các hóa đơn cũ
UPDATE HoaDon
SET TienTraTruoc = TongTien
WHERE TienTraTruoc IS NULL OR TienTraTruoc = 0;
GO

PRINT 'Hoàn thành cập nhật cột TienTraTruoc';
GO
