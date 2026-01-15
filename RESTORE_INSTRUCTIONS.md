# HƯỚNG DẪN RESTORE VỀ PHIÊN BẢN CŨ

## ⚠️ **QUAN TRỌNG**
Project không có Git, nên cần restore thủ công.

## 🗑️ **BƯỚC 1: XÓA CÁC FILE MỚI**

### Xóa các file entity mới (đã tạo cho v2.0)
```
src/main/java/entity/KhachHang.java (XÓA - file mới)
src/main/java/entity/NhanVien.java (XÓA - file mới)
```

### Xóa các file DAO mới
```
src/main/java/dao/KhachHangDao.java (XÓA - file mới)
src/main/java/dao/NhanVienDao.java (XÓA - file mới)
```

### Xóa các file view mới
```
src/main/java/view/QuanLyXeView.java (XÓA)
src/main/java/view/QuanLyPhieuDatView.java (XÓA)
src/main/java/view/MainMenuView.java (XÓA - nếu có)
```

### Xóa các file test
```
src/main/java/test/TestNewFeatures.java (XÓA)
src/main/java/Main.java (XÓA - nếu có)
```

## 🔄 **BƯỚC 2: RESTORE CÁC FILE CŨ**

### Restore các view đã bị comment out
```
src/main/java/view/KhachHangView.java.bak → Đổi tên thành KhachHangView.java
src/main/java/view/NhanVienView.java.bak → Đổi tên thành NhanVienView.java
src/main/java/view/ChonKhachHang.java.bak → Đổi tên thành ChonKhachHang.java
src/main/java/Repository/Repository_NhanVien.java.bak → Đổi tên thành Repository_NhanVien.java
```

### Sửa lại menumain.java
Uncomment các dòng đã bị comment:
- Dòng 430: Uncomment NhanVienView
- Dòng 438: Uncomment KhachHangView

### Sửa lại BanHangView.java
Uncomment dòng 25: `import view.ChonKhachHang;`
Uncomment method `btnChonKhachHangActionPerformed`

## 🗄️ **BƯỚC 3: RESTORE DATABASE**

### Nếu có backup database cũ:
1. Mở SQL Server Management Studio
2. Right-click database `QLBanXe`
3. Tasks → Restore → Database
4. Chọn file backup

### Nếu KHÔNG có backup:
Database mới vẫn có thể dùng được, chỉ cần chạy script:
```sql
-- Chạy file: fix_all_compatibility.sql
-- Script này tạo view tương thích với code cũ
```

## ✅ **BƯỚC 4: COMPILE VÀ CHẠY**

```bash
mvn clean compile
```

Nếu compile thành công, chạy ứng dụng từ NetBeans.

## 🎯 **KẾT QUẢ MONG ĐỢI**

Sau khi restore:
- ✅ Hệ thống compile thành công
- ✅ Đăng nhập được
- ✅ Các chức năng cũ hoạt động bình thường
- ✅ Phiếu giảm giá vẫn hiển thị theo % (đã sửa trước đó)

## 📋 **DANH SÁCH FILE CẦN XÓA**

Để dễ dàng, copy các lệnh sau vào PowerShell:

```powershell
# Xóa entities mới
Remove-Item "src\main\java\entity\KhachHang.java" -ErrorAction SilentlyContinue
Remove-Item "src\main\java\entity\NhanVien.java" -ErrorAction SilentlyContinue

# Xóa DAOs mới
Remove-Item "src\main\java\dao\KhachHangDao.java" -ErrorAction SilentlyContinue
Remove-Item "src\main\java\dao\NhanVienDao.java" -ErrorAction SilentlyContinue

# Xóa views mới
Remove-Item "src\main\java\view\QuanLyXeView.java" -ErrorAction SilentlyContinue
Remove-Item "src\main\java\view\QuanLyPhieuDatView.java" -ErrorAction SilentlyContinue
Remove-Item "src\main\java\view\MainMenuView.java" -ErrorAction SilentlyContinue

# Xóa test files
Remove-Item "src\main\java\test\TestNewFeatures.java" -ErrorAction SilentlyContinue
Remove-Item "src\main\java\Main.java" -ErrorAction SilentlyContinue

# Restore files cũ
Rename-Item "src\main\java\view\KhachHangView.java.bak" "KhachHangView.java" -ErrorAction SilentlyContinue
Rename-Item "src\main\java\view\NhanVienView.java.bak" "NhanVienView.java" -ErrorAction SilentlyContinue
Rename-Item "src\main\java\view\ChonKhachHang.java.bak" "ChonKhachHang.java" -ErrorAction SilentlyContinue
Rename-Item "src\main\java\Repository\Repository_NhanVien.java.bak" "Repository_NhanVien.java" -ErrorAction SilentlyContinue

Write-Host "Đã restore xong! Bây giờ chạy: mvn clean compile"
```

## 🆘 **NẾU VẪN GẶP LỖI**

Nếu sau khi restore vẫn lỗi, có thể:
1. Các file .bak không tồn tại → Cần restore từ backup khác
2. Database không tương thích → Chạy script `fix_all_compatibility.sql`
3. Còn file conflict → Kiểm tra lại danh sách file đã xóa

---
**Lưu ý:** Sau khi restore, hệ thống sẽ về trạng thái trước khi bắt đầu v2.0, nhưng vẫn giữ được:
- ✅ Phiếu giảm giá theo % (đã sửa)
- ✅ Hiển thị số tiền với dấu phẩy (đã sửa)
- ✅ Double-click chọn sản phẩm (đã sửa)
