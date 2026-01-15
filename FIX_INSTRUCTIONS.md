# Hướng dẫn Fix Lỗi - Đã Hoàn Thành ✅

## Các lỗi đã được sửa:
✅ Tạo entity `KhachHang.java` với đầy đủ thuộc tính
✅ Tạo entity `NhanVien.java` với đầy đủ thuộc tính  
✅ Tạo DAO `KhachHangDao.java` với các phương thức CRUD
✅ Tạo DAO `NhanVienDao.java` với các phương thức CRUD
✅ Tạo file `DangNhapJDiaLog.java` - Form đăng nhập
✅ Cập nhật `Session.java` để lưu thông tin user
✅ Tất cả 35 lỗi compilation đã được giải quyết

## Các file đã tạo/sửa:
1. `src/main/java/entity/KhachHang.java` - Entity khách hàng
2. `src/main/java/entity/NhanVien.java` - Entity nhân viên
3. `src/main/java/dao/KhachHangDao.java` - DAO quản lý khách hàng
4. `src/main/java/dao/NhanVienDao.java` - DAO quản lý nhân viên
5. `src/main/java/Login/DangNhapJDiaLog.java` - Form đăng nhập
6. `src/main/java/entity/Session.java` - Quản lý session user
7. `database_add_khachhang_nhanvien.sql` - Script tạo bảng database

## Bước tiếp theo:

### 1. Chạy SQL Script để tạo bảng trong database:
- Mở SQL Server Management Studio
- Kết nối đến database của bạn
- Mở file `database_add_khachhang_nhanvien.sql`
- Chạy script này để tạo các bảng: KhachHang, NhanVien, ChucVu, DangNhap

### 2. Build và chạy ứng dụng:
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="Login.DangNhapJDiaLog"
```

### 3. Đăng nhập với tài khoản mặc định:
- Tài khoản: `admin`
- Mật khẩu: `123456`

## Cấu trúc Database đã tạo:

### Bảng KhachHang:
- MaKhachHang (PK)
- TenKhachHang
- GioiTinh
- SoDienThoai
- Email
- DiaChi
- TrangThai (0: Đang hoạt động, 1: Ngừng hoạt động)
- NgayDangKy

### Bảng NhanVien:
- MaNhanVien (PK)
- hoTen
- GioiTinh
- SoDienThoai
- Email
- MaChucVu (FK)
- TrangThai (0: Đang làm, 1: Đã nghỉ)

### Bảng ChucVu:
- MaChucVu (PK)
- TenChucVu

### Bảng DangNhap:
- MaDangNhap (PK)
- TaiKhoan
- MatKhau
- MaNhanVien (FK)

## Lưu ý:
- Tất cả các file đã được tạo và không có lỗi compilation
- Database script đã bao gồm dữ liệu mẫu để test
- Ứng dụng sẵn sàng để chạy sau khi tạo database
