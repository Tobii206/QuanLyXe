# 🔐 HƯỚNG DẪN ĐĂNG NHẬP

## Cách đăng nhập

Bạn có thể đăng nhập bằng **Email** hoặc **Tài khoản**

---

## 📧 Đăng nhập bằng EMAIL (Khuyến nghị)

### Quản lý:
- **Email:** `admin@porsche.vn`
- **Mật khẩu:** `123456`

### Sales (Nhân viên bán hàng):
- **Email:** `sales1@porsche.vn`
- **Mật khẩu:** `123456`

### Kế toán:
- **Email:** `ketoan@porsche.vn`
- **Mật khẩu:** `123456`

### Hậu mãi:
- **Email:** `haumai@porsche.vn`
- **Mật khẩu:** `123456`

---

## 👤 Đăng nhập bằng TÀI KHOẢN

### Quản lý:
- **Tài khoản:** `admin`
- **Mật khẩu:** `123456`

### Nhân viên:
- **Tài khoản:** `sales1` / `ketoan1` / `haumai1`
- **Mật khẩu:** `123456`

---

## 🚀 Cách chạy ứng dụng

### Từ NetBeans:
1. Mở project trong NetBeans
2. Tìm file: `DA1/banXe/banXe.java`
3. Click chuột phải → **Run File** (hoặc nhấn F6)
4. Cửa sổ đăng nhập sẽ hiện ra

### Từ Command Line:
```bash
cd D:\DuAn1_Banxe
mvn clean package
java -cp target/DuAn1_Banxe-1.0.jar DA1.banXe.banXe
```

---

## ⚠️ Lưu ý

1. **Đã chạy SQL scripts chưa?**
   - `database_add_khachhang_nhanvien.sql` (tạo cấu trúc)
   - `database_sample_data.sql` (thêm dữ liệu)

2. **Kiểm tra kết nối database** trong `db.properties`

3. **SQL Server đã chạy chưa?**

---

## 🎯 Ví dụ đăng nhập

**Cách 1 - Dùng Email:**
```
Email/Tài khoản: admin@porsche.vn
Mật khẩu: 123456
```

**Cách 2 - Dùng Username:**
```
Email/Tài khoản: admin
Mật khẩu: 123456
```

Cả 2 cách đều hoạt động! 😊
