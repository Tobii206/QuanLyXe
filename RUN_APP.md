# 🚀 HƯỚNG DẪN CHẠY ỨNG DỤNG

## ✅ Hoàn thành cài đặt!

Hệ thống đã sẵn sàng với:
- ✅ Hệ thống phân quyền hoàn chỉnh
- ✅ Đăng nhập thống nhất (Nhân viên + Khách hàng)
- ✅ Database với dữ liệu mẫu
- ✅ Build thành công

---

## 🎯 Bước 1: Tạo Database

Chạy file SQL trong SQL Server Management Studio:
```
database_full_setup.sql
```

Script này sẽ:
- Xóa database cũ (nếu có)
- Tạo tất cả bảng
- Thêm dữ liệu mẫu

---

## 🎯 Bước 2: Chạy ứng dụng

### Cách 1: Từ NetBeans
1. Mở project trong NetBeans
2. Nhấn F6 hoặc Run Project

### Cách 2: Từ Command Line
```bash
cd D:\DuAn1_Banxe
"D:\NetBeans-19\netbeans\java\maven\bin\mvn.cmd" exec:java -Dexec.mainClass="DA1.banXe.banXe"
```

### Cách 3: Từ PowerShell
```powershell
cd D:\DuAn1_Banxe
& "D:\NetBeans-19\netbeans\java\maven\bin\mvn.cmd" exec:java `"-Dexec.mainClass=DA1.banXe.banXe`"
```

---

## 🔐 Đăng nhập

### Nhân viên & Quản lý

| Loại | Email | Username | Mật khẩu |
|------|-------|----------|----------|
| **Quản lý** | `admin@porsche.vn` | `admin` | `123456` |
| **Sales** | `sales1@porsche.vn` | `sales1` | `123456` |
| **Kế toán** | `ketoan@porsche.vn` | `ketoan1` | `123456` |
| **Hậu mãi** | `haumai@porsche.vn` | `haumai1` | `123456` |

### Khách hàng

| Tên | Email |
|-----|-------|
| Nguyễn Văn A | `nva@email.com` |
| Trần Thị B | `ttb@email.com` |
| Lê Văn C | `lvc@email.com` |

**Lưu ý:** Bạn có thể đăng nhập bằng **Email** HOẶC **Username**

---

## 🎨 Giao diện

Sau khi đăng nhập, bạn sẽ thấy:

### Quản lý
- Quản lý nhân viên
- Quản lý kho xe
- Báo cáo tổng hợp
- Phê duyệt đơn hàng
- Tất cả chức năng

### Nhân viên Sales
- Bán hàng
- Tạo hóa đơn
- Quản lý khách hàng
- Đặt xe

### Nhân viên Kế toán
- Xem báo cáo doanh thu
- Quản lý hóa đơn
- Xuất báo cáo

### Nhân viên Hậu mãi
- Quản lý lịch bảo dưỡng
- Chăm sóc khách hàng
- Xem lịch sử xe

### Khách hàng
- Xem danh sách xe
- Đặt xe
- Xem lịch sử mua hàng

---

## 📊 Dữ liệu mẫu

### Xe Porsche

| Dòng xe | Giá bán | Số lượng |
|---------|---------|----------|
| Porsche 911 Carrera | 8 tỷ VNĐ | 5 xe |
| Porsche Cayenne | 5 tỷ VNĐ | 8 xe |
| Porsche Panamera | 6 tỷ VNĐ | 6 xe |
| Porsche Macan | 3.5 tỷ VNĐ | 10 xe |

### Thống kê
- 4 nhân viên
- 5 khách hàng
- 4 dòng xe
- 12 biến thể sản phẩm
- 4 xe cụ thể đã về kho
- 3 phiếu giảm giá

---

## ⚙️ Cấu hình Database

File: `src/main/resources/db.properties`

```properties
db.url=jdbc:sqlserver://localhost:1433;databaseName=QLBanXe;encrypt=false
db.username=sa
db.password=123456
```

Nếu cần thay đổi:
1. Mở file `db.properties`
2. Sửa `db.username` và `db.password`
3. Lưu lại và chạy lại ứng dụng

---

## 🐛 Xử lý lỗi

### Lỗi: "Cannot connect to database"
**Giải pháp:**
1. Kiểm tra SQL Server đã chạy chưa
2. Kiểm tra username/password trong `db.properties`
3. Kiểm tra database `QLBanXe` đã tồn tại chưa

### Lỗi: "Email hoặc mật khẩu không đúng"
**Giải pháp:**
1. Kiểm tra đã chạy script `database_full_setup.sql` chưa
2. Thử đăng nhập bằng: `admin@porsche.vn` / `123456`
3. Hoặc: `admin` / `123456`

### Lỗi: Build failed
**Giải pháp:**
```bash
mvn clean install
```

---

## 📚 Tài liệu tham khảo

- `PHAN_QUYEN_GUIDE.md` - Hướng dẫn phân quyền chi tiết
- `DATABASE_SETUP_GUIDE.md` - Hướng dẫn cài đặt database
- `LOGIN_GUIDE.md` - Hướng dẫn đăng nhập

---

## 🎉 Chúc mừng!

Bạn đã cài đặt thành công hệ thống bán xe Porsche với:
- ✅ Phân quyền đầy đủ
- ✅ Đăng nhập linh hoạt
- ✅ Dữ liệu mẫu hoàn chỉnh
- ✅ Giao diện thân thiện

**Bắt đầu khám phá ngay!** 🚗💨
