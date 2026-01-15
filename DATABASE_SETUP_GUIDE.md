# 🚗 HƯỚNG DẪN CÀI ĐẶT DATABASE PORSCHE

## 📋 Thứ tự chạy script SQL

### Bước 1: Xóa database cũ (nếu cần)
```sql
USE master;
GO
DROP DATABASE IF EXISTS QLBanXe;
GO
```

### Bước 2: Tạo cấu trúc database
Chạy file: **`database_create_tables.sql`**

Script này sẽ:
- Tạo database `QLBanXe`
- Tạo tất cả các bảng (ChucVu, NhanVien, KhachHang, SanPham, HoaDon, v.v.)
- Thiết lập các ràng buộc và khóa ngoại

### Bước 3: Thêm dữ liệu mẫu
Chạy file: **`database_sample_data.sql`**

Script này sẽ thêm:
- 4 chức vụ
- 4 nhân viên
- 4 tài khoản đăng nhập
- 5 khách hàng
- 4 dòng xe Porsche
- 12 biến thể sản phẩm
- 4 xe cụ thể với số khung, số máy
- 4 phiếu đặt xe
- 3 phiếu giảm giá
- 3 hóa đơn mẫu

---

## 🔐 Tài khoản đăng nhập

Sau khi chạy xong, bạn có thể đăng nhập bằng:

| Username | Email | Mật khẩu | Chức vụ |
|----------|-------|----------|---------|
| `admin` | `admin@porsche.vn` | `123456` | Quản lý |
| `sales1` | `sales1@porsche.vn` | `123456` | Sales |
| `ketoan1` | `ketoan@porsche.vn` | `123456` | Kế toán |
| `haumai1` | `haumai@porsche.vn` | `123456` | Hậu mãi |

**Lưu ý:** Bạn có thể đăng nhập bằng **username** HOẶC **email**

---

## 🚗 Dữ liệu xe Porsche

| Dòng xe | Giá bán | Số lượng |
|---------|---------|----------|
| Porsche 911 Carrera | 8 tỷ VNĐ | 5 xe |
| Porsche Cayenne | 5 tỷ VNĐ | 8 xe |
| Porsche Panamera | 6 tỷ VNĐ | 6 xe |
| Porsche Macan | 3.5 tỷ VNĐ | 10 xe |

---

## ⚠️ Xử lý lỗi thường gặp

### Lỗi: "Database 'QLBanXe' already exists"
**Giải pháp:** Xóa database cũ trước:
```sql
USE master;
GO
DROP DATABASE QLBanXe;
GO
```

### Lỗi: "Invalid object name 'ChucVu'"
**Nguyên nhân:** Chưa chạy script tạo bảng

**Giải pháp:** Chạy `database_create_tables.sql` trước

### Lỗi: "Violation of PRIMARY KEY constraint"
**Nguyên nhân:** Dữ liệu đã tồn tại

**Giải pháp:** Xóa dữ liệu cũ hoặc xóa database và chạy lại từ đầu

---

## 🔄 Khởi động lại từ đầu

Nếu muốn reset toàn bộ:

```sql
-- 1. Xóa database
USE master;
GO
DROP DATABASE IF EXISTS QLBanXe;
GO

-- 2. Chạy database_create_tables.sql

-- 3. Chạy database_sample_data.sql
```

---

## ✅ Kiểm tra sau khi cài đặt

```sql
USE QLBanXe;
GO

-- Kiểm tra số lượng dữ liệu
SELECT 'ChucVu' as Bang, COUNT(*) as SoLuong FROM ChucVu
UNION ALL
SELECT 'NhanVien', COUNT(*) FROM NhanVien
UNION ALL
SELECT 'DangNhap', COUNT(*) FROM DangNhap
UNION ALL
SELECT 'KhachHang', COUNT(*) FROM KhachHang
UNION ALL
SELECT 'SanPham', COUNT(*) FROM SanPham
UNION ALL
SELECT 'ChiTietSanPham', COUNT(*) FROM ChiTietSanPham
UNION ALL
SELECT 'ThongTinXe', COUNT(*) FROM ThongTinXe
UNION ALL
SELECT 'PhieuDatXe', COUNT(*) FROM PhieuDatXe
UNION ALL
SELECT 'PhieuGiamGia', COUNT(*) FROM PhieuGiamGia
UNION ALL
SELECT 'HoaDon', COUNT(*) FROM HoaDon;
```

Kết quả mong đợi:
- ChucVu: 4
- NhanVien: 4
- DangNhap: 4
- KhachHang: 5
- SanPham: 4
- ChiTietSanPham: 12
- ThongTinXe: 4
- PhieuDatXe: 4
- PhieuGiamGia: 3
- HoaDon: 3

---

## 📞 Hỗ trợ

Nếu gặp vấn đề, kiểm tra:
1. SQL Server đã chạy chưa?
2. Có quyền tạo database không?
3. File `db.properties` đã cấu hình đúng chưa?
