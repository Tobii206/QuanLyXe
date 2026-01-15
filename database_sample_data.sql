-- =============================================
-- SCRIPT TẠO DỮ LIỆU MẪU CHO HỆ THỐNG BÁN XE PORSCHE
-- LƯU Ý: Chạy script tạo bảng (database_create_tables.sql) TRƯỚC KHI chạy script này
-- =============================================
USE QLBanXe;
GO

-- =============================================
-- 1. CHỨC VỤ
-- =============================================
IF NOT EXISTS (SELECT * FROM ChucVu WHERE MaChucVu = 'CV001')
BEGIN
INSERT INTO ChucVu (MaChucVu, TenChucVu) VALUES 
('CV001', N'Quản lý'),
('CV002', N'Sales'),
('CV003', N'Kế toán'),
('CV004', N'Hậu mãi');
PRINT N'✓ Đã thêm dữ liệu Chức vụ';
END

-- =============================================
-- 2. NHÂN VIÊN
-- =============================================
IF NOT EXISTS (SELECT * FROM NhanVien WHERE MaNhanVien = 'NV001')
BEGIN
INSERT INTO NhanVien (MaNhanVien, hoTen, GioiTinh, SoDienThoai, Email, MaChucVu, TrangThai) VALUES
('NV001', N'Nguyễn Văn Admin', N'Nam', '0901111111', 'admin@porsche.vn', 'CV001', 0),
('NV002', N'Trần Thị Sales', N'Nữ', '0902222222', 'sales1@porsche.vn', 'CV002', 0),
('NV003', N'Lê Văn Kế toán', N'Nam', '0903333333', 'ketoan@porsche.vn', 'CV003', 0),
('NV004', N'Phạm Thị Hậu mãi', N'Nữ', '0904444444', 'haumai@porsche.vn', 'CV004', 0);
PRINT N'✓ Đã thêm dữ liệu Nhân viên';
END

-- =============================================
-- 3. TÀI KHOẢN ĐĂNG NHẬP
-- =============================================
IF NOT EXISTS (SELECT * FROM DangNhap WHERE TaiKhoan = 'admin')
BEGIN
INSERT INTO DangNhap (TaiKhoan, MatKhau, MaNhanVien) VALUES
('admin', '123456', 'NV001'),
('sales1', '123456', 'NV002'),
('ketoan1', '123456', 'NV003'),
('haumai1', '123456', 'NV004');
PRINT N'✓ Đã thêm tài khoản đăng nhập';
END

-- =============================================
-- 4. KHÁCH HÀNG
-- =============================================
IF NOT EXISTS (SELECT * FROM KhachHang WHERE MaKhachHang = 'KH001')
BEGIN
INSERT INTO KhachHang (MaKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, DiaChi, TrangThai, NgayDangKy) VALUES
('KH001', N'Nguyễn Văn A', N'Nam', '0901234567', 'nva@email.com', N'Hà Nội', 0, GETDATE()),
('KH002', N'Trần Thị B', N'Nữ', '0902345678', 'ttb@email.com', N'TP.HCM', 0, GETDATE()),
('KH003', N'Lê Văn C', N'Nam', '0903456789', 'lvc@email.com', N'Đà Nẵng', 0, GETDATE()),
('KH004', N'Phạm Thị D', N'Nữ', '0904567890', 'ptd@email.com', N'Hải Phòng', 0, GETDATE()),
('KH005', N'Hoàng Văn E', N'Nam', '0905678901', 'hve@email.com', N'Cần Thơ', 0, GETDATE());
PRINT N'✓ Đã thêm dữ liệu Khách hàng';
END

-- =============================================
-- 5. XUẤT XỨ
-- =============================================
IF NOT EXISTS (SELECT * FROM XuatXu WHERE MaXuatXu = 'XX001')
BEGIN
INSERT INTO XuatXu (MaXuatXu, TenXuatXu) VALUES
('XX001', N'Đức (Germany)'),
('XX002', N'Nhật Bản'),
('XX003', N'Hàn Quốc'),
('XX004', N'Mỹ'),
('XX005', N'Anh');
PRINT N'✓ Đã thêm dữ liệu Xuất xứ';
END

-- =============================================
-- 6. CHẤT LIỆU
-- =============================================
IF NOT EXISTS (SELECT * FROM ChatLieu WHERE MaChatLieu = 'CL001')
BEGIN
INSERT INTO ChatLieu (MaChatLieu, TenChatLieu) VALUES
('CL001', N'Hợp kim nhôm cao cấp'),
('CL002', N'Sợi carbon'),
('CL003', N'Thép không gỉ'),
('CL004', N'Titan'),
('CL005', N'Nhựa composite');
PRINT N'✓ Đã thêm dữ liệu Chất liệu';
END

-- =============================================
-- 7. MÀU SẮC
-- =============================================
IF NOT EXISTS (SELECT * FROM MauSac WHERE MaMauSac = 'MS001')
BEGIN
INSERT INTO MauSac (MaMauSac, TenMau) VALUES
('MS001', N'Đen (Black)'),
('MS002', N'Trắng (White)'),
('MS003', N'Đỏ (Red)'),
('MS004', N'Xanh dương (Blue)'),
('MS005', N'Xám (Grey)'),
('MS006', N'Bạc (Silver)'),
('MS007', N'Vàng (Yellow)'),
('MS008', N'Xanh lá (Green)');
PRINT N'✓ Đã thêm dữ liệu Màu sắc';
END

-- =============================================
-- 8. KÍCH THƯỚC (Phiên bản)
-- =============================================
IF NOT EXISTS (SELECT * FROM KichThuoc WHERE MaKichThuoc = 'KT001')
BEGIN
INSERT INTO KichThuoc (MaKichThuoc, TenKichThuoc) VALUES
('KT001', N'Base'),
('KT002', N'S (Sport)'),
('KT003', N'GTS'),
('KT004', N'Turbo');
PRINT N'✓ Đã thêm dữ liệu Kích thước/Phiên bản';
END

-- =============================================
-- 9. SẢN PHẨM (XE PORSCHE)
-- =============================================
IF NOT EXISTS (SELECT * FROM SanPham WHERE MaSanPham = 'SP001')
BEGIN
INSERT INTO SanPham (MaSanPham, TenSanPham, MaChatLieu, MaXuatSu, SoLuong, DonGia, MoTa, TrangThai) VALUES
('SP001', N'Porsche 911 Carrera', 'CL001', 'XX001', 5, 8000000000, N'Xe thể thao huyền thoại, động cơ boxer 6 xi-lanh', 1),
('SP002', N'Porsche Cayenne', 'CL001', 'XX001', 8, 5000000000, N'SUV hạng sang, mạnh mẽ và tiện nghi', 1),
('SP003', N'Porsche Panamera', 'CL001', 'XX001', 6, 6000000000, N'Sedan thể thao 4 cửa, sang trọng', 1),
('SP004', N'Porsche Macan', 'CL001', 'XX001', 10, 3500000000, N'SUV compact, linh hoạt và thể thao', 1);
PRINT N'✓ Đã thêm dữ liệu Sản phẩm Porsche';
END

-- =============================================
-- 10. CHI TIẾT SẢN PHẨM
-- =============================================
IF NOT EXISTS (SELECT * FROM ChiTietSanPham WHERE MaChiTietSP = 'CTSP001')
BEGIN
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
PRINT N'✓ Đã thêm dữ liệu Chi tiết sản phẩm';
END

-- =============================================
-- 11. THÔNG TIN XE (Xe cụ thể với số khung, số máy)
-- =============================================
IF NOT EXISTS (SELECT * FROM ThongTinXe WHERE MaXe = 'XE001')
BEGIN
INSERT INTO ThongTinXe (MaXe, MaSanPham, MaMauSac, MaKichThuoc, SoKhung, SoMay, NamSanXuat, GiaBan, TinhTrang, NgayNhapKho) VALUES
('XE001', 'SP001', 'MS001', 'KT003', 'WP0ZZZ99ZTS392001', 'M97.21-001', 2024, 8000000000, N'Đã về kho', GETDATE()),
('XE002', 'SP002', 'MS002', 'KT002', 'WP1ZZZ92ZTS392002', 'M48.00-002', 2024, 5000000000, N'Đã về kho', GETDATE()),
('XE003', 'SP003', 'MS003', 'KT001', 'WP0ZZZ97ZTS392003', 'M46.00-003', 2024, 6000000000, N'Đã về kho', GETDATE()),
('XE004', 'SP004', 'MS004', 'KT002', 'WP1ZZZ95ZTS392004', 'M48.01-004', 2024, 3500000000, N'Đã về kho', GETDATE());
PRINT N'✓ Đã thêm dữ liệu Thông tin xe';
END

-- =============================================
-- 12. PHIẾU ĐẶT XE
-- =============================================
IF NOT EXISTS (SELECT * FROM PhieuDatXe WHERE MaPhieuDat = 'PDX001')
BEGIN
INSERT INTO PhieuDatXe (MaPhieuDat, MaKhachHang, MaNhanVien, MaSanPham, MaMauSac, MaKichThuoc, YeuCauCauHinh, GiaTriDuKien, TienDatCoc, PhanTramCoc, NgayDat, TienDoNhap) VALUES
('PDX001', 'KH001', 'NV002', 'SP001', 'MS001', 'KT003', N'Màu đen, nội thất da, camera 360', 8000000000, 800000000, 10, GETDATE(), N'Chờ xác nhận'),
('PDX002', 'KH002', 'NV002', 'SP002', 'MS002', 'KT002', N'Màu trắng, cửa sổ trời', 5000000000, 1000000000, 20, GETDATE(), N'Đang sản xuất'),
('PDX003', 'KH003', 'NV002', 'SP003', 'MS003', 'KT001', N'Màu đỏ, ghế massage', 6000000000, 600000000, 10, GETDATE(), N'Đang vận chuyển'),
('PDX004', 'KH004', 'NV002', 'SP004', 'MS004', 'KT002', N'Màu xanh, hệ thống âm thanh Bose', 3500000000, 700000000, 20, GETDATE(), N'Về cảng');
PRINT N'✓ Đã thêm dữ liệu Phiếu đặt xe';
END

-- =============================================
-- 13. PHIẾU GIẢM GIÁ
-- =============================================
IF NOT EXISTS (SELECT * FROM PhieuGiamGia WHERE MaPhieu = 'PGG001')
BEGIN
INSERT INTO PhieuGiamGia (MaPhieu, TenPhieu, TenHinhThucGG, GiaTri, SoLuong, NgayBatDau, NgayKetThuc, DieuKienApDung, TrangThai) VALUES
('PGG001', N'Giảm giá 5% cho khách VIP', N'Phần trăm', 5, 100, GETDATE(), DATEADD(MONTH, 3, GETDATE()), N'Áp dụng cho khách VIP', 1),
('PGG002', N'Giảm giá 10% mua xe đầu tiên', N'Phần trăm', 10, 50, GETDATE(), DATEADD(MONTH, 6, GETDATE()), N'Khách hàng mới', 1),
('PGG003', N'Giảm giá 3% thanh toán trước', N'Phần trăm', 3, 200, GETDATE(), DATEADD(MONTH, 1, GETDATE()), N'Thanh toán 100% trước', 1);
PRINT N'✓ Đã thêm dữ liệu Phiếu giảm giá';
END

-- =============================================
-- 14. HÓA ĐƠN MẪU
-- =============================================
IF NOT EXISTS (SELECT * FROM HoaDon WHERE MaHoaDon = 'HD001')
BEGIN
INSERT INTO HoaDon (MaHoaDon, MaKhachHang, MaNhanVien, TongTien, TrangThai, NgayTao) VALUES
('HD001', 'KH001', 'NV002', 8000000000, 1, DATEADD(DAY, -5, GETDATE())),
('HD002', 'KH002', 'NV002', 5000000000, 1, DATEADD(DAY, -3, GETDATE())),
('HD003', 'KH003', 'NV002', 6000000000, 0, GETDATE());
PRINT N'✓ Đã thêm dữ liệu Hóa đơn';
END

-- =============================================
-- 15. CHI TIẾT HÓA ĐƠN
-- =============================================
IF NOT EXISTS (SELECT * FROM ChiTietHoaDon WHERE MaHoaDon = 'HD001')
BEGIN
INSERT INTO ChiTietHoaDon (MaHoaDon, MaChiTietSP, SoLuong, DonGia) VALUES
('HD001', 'CTSP001', 1, 8000000000),
('HD002', 'CTSP004', 1, 5000000000),
('HD003', 'CTSP007', 1, 6000000000);
PRINT N'✓ Đã thêm dữ liệu Chi tiết hóa đơn';
END

-- =============================================
-- HOÀN TẤT
-- =============================================
PRINT N'';
PRINT N'========================================';
PRINT N'✓ ĐÃ TẠO XONG DỮ LIỆU PORSCHE!';
PRINT N'========================================';
PRINT N'';
PRINT N'Thống kê:';
PRINT N'- Chức vụ: 4';
PRINT N'- Nhân viên: 4';
PRINT N'- Tài khoản: 4 (mật khẩu: 123456)';
PRINT N'- Khách hàng: 5';
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
PRINT N'- admin@porsche.vn / 123456 (Quản lý)';
PRINT N'- sales1@porsche.vn / 123456 (Sales)';
PRINT N'- ketoan@porsche.vn / 123456 (Kế toán)';
PRINT N'- haumai@porsche.vn / 123456 (Hậu mãi)';
PRINT N'';
PRINT N'Hoặc dùng username: admin, sales1, ketoan1, haumai1';
GO
