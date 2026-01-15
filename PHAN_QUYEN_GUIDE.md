# 🔐 HƯỚNG DẪN HỆ THỐNG PHÂN QUYỀN

## 📋 Tổng quan

Hệ thống hỗ trợ đăng nhập cho 3 loại người dùng:
1. **Quản lý** - Quyền cao nhất
2. **Nhân viên** - Sales, Kế toán, Hậu mãi
3. **Khách hàng** - Xem và mua hàng

---

## 🔑 Tài khoản mẫu

### Nhân viên & Quản lý

| Loại | Email | Username | Mật khẩu | Quyền |
|------|-------|----------|----------|-------|
| Quản lý | `admin@porsche.vn` | `admin` | `123456` | Toàn quyền |
| Sales | `sales1@porsche.vn` | `sales1` | `123456` | Bán hàng |
| Kế toán | `ketoan@porsche.vn` | `ketoan1` | `123456` | Tài chính |
| Hậu mãi | `haumai@porsche.vn` | `haumai1` | `123456` | Chăm sóc KH |

### Khách hàng

| Tên | Email | Mật khẩu |
|-----|-------|----------|
| Nguyễn Văn A | `nva@email.com` | _(chưa có)_ |
| Trần Thị B | `ttb@email.com` | _(chưa có)_ |
| Lê Văn C | `lvc@email.com` | _(chưa có)_ |

---

## 💻 Cách sử dụng

### 1. Đăng nhập

```java
// Nhập email hoặc username
txtEmail.setText("admin@porsche.vn");  // hoặc "admin"
txtPassword.setText("123456");
```

### 2. Kiểm tra quyền trong code

```java
// Kiểm tra loại người dùng
if (Session.isQuanLy()) {
    // Chỉ Quản lý mới thấy
    btnXoaNhanVien.setVisible(true);
}

if (Session.isNhanVien()) {
    // Tất cả nhân viên (bao gồm Quản lý)
    menuBanHang.setEnabled(true);
}

if (Session.isKhachHang()) {
    // Chỉ khách hàng
    menuDatXe.setVisible(true);
    menuQuanLy.setVisible(false);
}
```

### 3. Lấy thông tin người dùng

```java
// Thông tin chung
String userId = Session.userId;           // Mã người dùng
String userName = Session.userName;       // Tên người dùng
String userType = Session.userType;       // "NHANVIEN" hoặc "KHACHHANG"
String role = Session.role;               // "QUANLY", "SALES", "KETOAN", "HAUMAI", "KHACHHANG"

// Lấy đối tượng cụ thể
NhanVien nv = Session.getCurrentNhanVien();
if (nv != null) {
    System.out.println("Chức vụ: " + nv.getMaChucVu());
}

KhachHang kh = Session.getCurrentKhachHang();
if (kh != null) {
    System.out.println("Địa chỉ: " + kh.getDiaChi());
}
```

---

## 🎯 Phân quyền chức năng

### Quản lý (CV001)
- ✅ Quản lý nhân viên (thêm, sửa, xóa)
- ✅ Xem báo cáo tổng hợp
- ✅ Quản lý kho xe
- ✅ Phê duyệt đơn hàng
- ✅ Quản lý khuyến mãi
- ✅ Tất cả quyền của nhân viên

### Sales (CV002)
- ✅ Bán hàng
- ✅ Tạo hóa đơn
- ✅ Quản lý khách hàng
- ✅ Đặt xe cho khách
- ❌ Xóa nhân viên
- ❌ Xem báo cáo tài chính

### Kế toán (CV003)
- ✅ Xem báo cáo doanh thu
- ✅ Quản lý hóa đơn
- ✅ Xuất báo cáo tài chính
- ❌ Bán hàng
- ❌ Quản lý nhân viên

### Hậu mãi (CV004)
- ✅ Quản lý lịch bảo dưỡng
- ✅ Chăm sóc khách hàng
- ✅ Xem lịch sử xe
- ❌ Bán hàng
- ❌ Quản lý kho

### Khách hàng
- ✅ Xem danh sách xe
- ✅ Đặt xe
- ✅ Xem lịch sử mua hàng
- ✅ Đăng ký bảo dưỡng
- ❌ Truy cập quản lý
- ❌ Xem thông tin nhân viên

---

## 🔧 Cấu hình phân quyền

### Thêm kiểm tra quyền vào form

```java
public class NhanVienView extends JFrame {
    public NhanVienView() {
        initComponents();
        
        // Kiểm tra quyền khi mở form
        if (!Session.isQuanLy()) {
            btnXoa.setEnabled(false);
            btnThem.setEnabled(false);
            JOptionPane.showMessageDialog(this, 
                "Bạn không có quyền quản lý nhân viên!");
        }
    }
}
```

### Ẩn/hiện menu theo quyền

```java
private void setupMenuByRole() {
    if (Session.isKhachHang()) {
        // Khách hàng chỉ thấy menu cơ bản
        menuQuanLy.setVisible(false);
        menuNhanVien.setVisible(false);
        menuBaoCao.setVisible(false);
    } else if (Session.isNhanVien()) {
        // Nhân viên thấy menu bán hàng
        menuBanHang.setVisible(true);
        
        if (!Session.isQuanLy()) {
            // Nhân viên thường không thấy menu quản lý
            menuQuanLy.setVisible(false);
        }
    }
}
```

---

## 📝 Ví dụ thực tế

### Form Bán hàng

```java
private void btnTaoHoaDonActionPerformed(ActionEvent evt) {
    // Chỉ nhân viên mới được tạo hóa đơn
    if (!Session.isNhanVien()) {
        JOptionPane.showMessageDialog(this, 
            "Chỉ nhân viên mới có thể tạo hóa đơn!");
        return;
    }
    
    // Lưu mã nhân viên vào hóa đơn
    hoaDon.setMaNhanVien(Session.userId);
    hoaDon.setTenNhanVien(Session.userName);
    
    // Tiếp tục xử lý...
}
```

### Form Quản lý nhân viên

```java
private void btnXoaNhanVienActionPerformed(ActionEvent evt) {
    // Chỉ quản lý mới được xóa nhân viên
    if (!Session.isQuanLy()) {
        JOptionPane.showMessageDialog(this, 
            "Chỉ Quản lý mới có quyền xóa nhân viên!");
        return;
    }
    
    // Tiếp tục xử lý xóa...
}
```

---

## 🚀 Đăng xuất

```java
private void btnDangXuatActionPerformed(ActionEvent evt) {
    Session.logout();
    
    // Mở lại form đăng nhập
    new DangNhapJF().setVisible(true);
    this.dispose();
}
```

---

## ⚠️ Lưu ý bảo mật

1. **Luôn kiểm tra quyền** trước khi thực hiện thao tác quan trọng
2. **Không tin tưởng UI** - Kiểm tra quyền ở cả backend (DAO)
3. **Mã hóa mật khẩu** trong thực tế (hiện tại chưa mã hóa)
4. **Log hoạt động** của người dùng để audit

---

## 📞 Hỗ trợ

Nếu cần thêm quyền mới:
1. Thêm chức vụ mới vào bảng `ChucVu`
2. Cập nhật method `getRoleFromChucVu()` trong `Session.java`
3. Thêm method kiểm tra quyền mới (ví dụ: `isKyThuat()`)
4. Áp dụng kiểm tra quyền vào các form cần thiết
