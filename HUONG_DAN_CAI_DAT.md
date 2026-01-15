# HƯỚNG DẪN CÀI ĐẶT HỆ THỐNG QUẢN LÝ BÁN XE CAO CẤP

## 🚗 Giới thiệu
Hệ thống quản lý bán xe cao cấp (Porsche) - Mỗi xe là duy nhất với số khung, số máy riêng biệt.

## 1. YÊU CẦU HỆ THỐNG

- **Java JDK**: 17 trở lên
- **SQL Server**: 2019 trở lên  
- **NetBeans IDE**: 19 trở lên (hoặc IDE tương tự)
- **Maven**: 3.6 trở lên

## 2. CÀI ĐẶT DATABASE

### Bước 1: Tạo database ban đầu
1. Mở SQL Server Management Studio (SSMS)
2. Chạy toàn bộ script trong file database hiện tại để tạo các bảng cơ bản
3. Kiểm tra database `QLBanXe` đã được tạo thành công

### Bước 2: Cập nhật database với các chức năng mới
1. Mở file `database_update.sql`
2. Chạy toàn bộ script trong SSMS
3. Script sẽ tự động:
   - Tạo bảng `DonDatHang` (Đơn đặt hàng nhập xe)
   - Tạo bảng `LichBaoDuong` (Lịch bảo dưỡng)
   - Thêm cột `NgayDangKy` và `LoaiKhachHang` vào bảng `KhachHang`
   - Thêm dữ liệu mẫu

### Bước 3: Kiểm tra kết nối
1. Mở file `src/main/resources/db.properties`
2. Cập nhật thông tin kết nối:
```properties
db.url=jdbc:sqlserver://localhost:1433;databaseName=QLBanXe;encrypt=false;
db.user=sa
db.password=123
```

## 3. CẤU TRÚC HỆ THỐNG MỚI

### 🔄 Luồng nghiệp vụ chính:
```
1. Phiếu đặt xe → 2. Theo dõi nhập xe → 3. Xe về kho → 4. Lập hóa đơn bán
```

### 📊 Cấu trúc Database:

#### Bảng chính:
1. **ThongTinXe** - Thông tin xe cụ thể (mỗi xe duy nhất)
   - Mã xe, Số khung, Số máy, Biển số
   - Cấu hình chi tiết, Giá nhập, Giá bán
   - Tình trạng: Chờ nhập, Đã về kho, Đã bán, Đang bảo dưỡng

2. **PhieuDatXe** - Phiếu đặt xe của khách hàng
   - Yêu cầu cấu hình, Tiền đặt cọc
   - Tiến độ nhập: Chờ xác nhận → Đã xác nhận → Đang sản xuất → Đang vận chuyển → Về cảng → Về kho → Hoàn thành
   - Liên kết với xe cụ thể khi xe về

3. **LichSuXe** - Lịch sử từng xe
   - Nhập kho, Bán, Bảo dưỡng, Sửa chữa, Kiểm tra
   - Người thực hiện, Chi phí

4. **LichBaoDuong** - Lịch bảo dưỡng
   - Liên kết với xe cụ thể
   - Loại dịch vụ, Chi phí, Trạng thái

5. **KhachHang** - Thêm phân loại
   - Phân loại: Thường, VIP, Thân thiết
   - Ngày đăng ký

6. **HoaDon** - Cập nhật
   - Liên kết với xe cụ thể (MaXe)
   - Liên kết với phiếu đặt (MaPhieuDat)

### 📁 Các Module:

#### ✅ Đã hoàn thành:
1. **Quản lý Sản phẩm** - `SanPhamView.java`
   - Quản lý dòng xe, màu sắc, kích thước

2. **Quản lý Xe cụ thể** - `ThongTinXeDao.java`
   - Thêm/sửa/xóa thông tin xe
   - Theo dõi tình trạng từng xe
   - Quản lý số khung, số máy, biển số

3. **Quản lý Khách hàng** - `KhachHangView.java`
   - Phân loại khách hàng (Thường, VIP, Thân thiết)
   - Theo dõi lịch sử mua hàng

4. **Quản lý Nhân viên** - `NhanVienView.java`
   - Phân quyền (Admin/Nhân viên)
   - Theo dõi hiệu suất

5. **Phiếu đặt xe** - `PhieuDatXeDao.java`
   - Tạo phiếu đặt xe theo yêu cầu khách
   - Theo dõi tiến độ nhập xe
   - Quản lý đặt cọc
   - Liên kết với xe khi xe về

6. **Lịch sử xe** - `LichSuXeDao.java`
   - Ghi nhận mọi hành động với xe
   - Theo dõi chi phí phát sinh

7. **Bán hàng** - `BanHangView.java`
   - Lập hóa đơn từ phiếu đặt
   - Áp dụng khuyến mãi (%)
   - Liên kết với xe cụ thể

8. **Phiếu giảm giá** - `KhuyenMaiView.java`
   - Tạo/quản lý voucher (%)
   - Điều kiện áp dụng

9. **Lịch sử hóa đơn** - `LichSuView.java`
   - Xem chi tiết từng xe đã bán
   - Tìm kiếm, báo cáo

10. **Lịch bảo dưỡng** - `LichBaoDuongDao.java`
    - Đặt lịch bảo dưỡng theo xe
    - Quản lý dịch vụ, chi phí
    - Nhắc nhở khách hàng

## 4. CHẠY ỨNG DỤNG

### Bước 1: Import project
1. Mở NetBeans IDE
2. File → Open Project
3. Chọn thư mục `DuAn1_Banxe`

### Bước 2: Build project
```bash
mvn clean compile
```

### Bước 3: Chạy ứng dụng
1. Nhấn F6 hoặc Run Project
2. Đăng nhập với tài khoản:
   - **Admin**: `nv1` / `123456`
   - **Nhân viên**: `nv2` / `123456`

## 5. TÍNH NĂNG CHÍNH

### Quản lý thông tin xe
- ✅ Thêm mẫu xe mới
- ✅ Cập nhật thông tin: giá, màu, năm, tùy chọn
- ✅ Đánh dấu xe ngừng kinh doanh
- ✅ Lọc xe theo dòng, năm, tình trạng, giá
- ✅ Theo dõi tồn kho

### Đặt hàng nhập xe
- 🆕 Nhập yêu cầu cấu hình xe
- 🆕 Tạo đơn đặt hàng
- 🆕 Khách đặt cọc (10-30%)
- 🆕 Theo dõi tiến độ nhập xe
- 🆕 Cập nhật khách khi xe về

### Quản lý khách hàng - hậu mãi
- ✅ Tạo hồ sơ khách hàng
- ✅ Theo dõi đơn hàng
- ✅ Gửi thông báo ưu đãi
- 🆕 Yêu cầu tư vấn
- 🆕 Hỗ trợ bảo dưỡng
- 🆕 Gửi thông báo lịch bảo dưỡng định kỳ
- 🆕 Đánh giá dịch vụ

### Nhân viên - phân quyền
- ✅ Thêm nhân viên
- ✅ Phân quyền (Quản lý, Sales, Kế toán)
- ✅ Cập nhật thông tin
- ✅ Theo dõi hiệu suất
- ✅ Đổi mật khẩu

### Phiếu giảm giá
- ✅ Tạo phiếu giảm giá (%)
- ✅ Kiểm tra điều kiện sử dụng
- ✅ Áp dụng voucher vào đơn hàng
- ✅ Quản lý danh sách voucher
- ✅ Hủy voucher hết hạn
- ✅ Báo cáo hiệu quả

## 6. CẤU TRÚC CODE

```
src/main/java/
├── dao/                        # Data Access Objects
│   ├── ThongTinXeDao.java     # DAO cho xe cụ thể
│   ├── PhieuDatXeDao.java     # DAO cho phiếu đặt xe
│   ├── LichSuXeDao.java       # DAO cho lịch sử xe
│   ├── LichBaoDuongDao.java   # DAO cho lịch bảo dưỡng
│   ├── KhachHangDao.java
│   ├── NhanVienDao.java
│   ├── SanPhamDao.java
│   ├── HoaDonDao.java
│   ├── KhuyenMaiDao.java
│   └── ...
├── entity/                     # Entities
│   ├── ThongTinXe.java        # Entity xe cụ thể
│   ├── PhieuDatXe.java        # Entity phiếu đặt
│   ├── LichSuXe.java          # Entity lịch sử xe
│   ├── LichBaoDuong.java      # Entity lịch bảo dưỡng
│   ├── KhachHang.java
│   ├── NhanVien.java
│   ├── SanPham.java
│   ├── HoaDon.java
│   ├── PhieuGiamGia.java
│   └── ...
├── view/                       # Views (GUI)
│   ├── SanPhamView.java       # Quản lý dòng xe
│   ├── KhachHangView.java     # Quản lý khách hàng
│   ├── NhanVienView.java      # Quản lý nhân viên
│   ├── BanHangView.java       # Bán hàng
│   ├── KhuyenMaiView.java     # Khuyến mãi
│   ├── LichSuView.java        # Lịch sử hóa đơn
│   └── ...
└── uitl/                       # Utilities
    ├── XJdbc.java
    └── XQuery.java
```

## 7. LƯU Ý QUAN TRỌNG

### 🚗 Đặc điểm xe cao cấp
- **Mỗi xe là duy nhất**: Có số khung, số máy riêng
- **Không có số lượng**: Mỗi xe được quản lý riêng biệt
- **Theo dõi chi tiết**: Lịch sử đầy đủ từ nhập đến bán

### 💰 Định dạng số tiền
- Tất cả số tiền hiển thị với dấu phẩy ngăn cách hàng nghìn
- Ví dụ: 3,000,000,000 (3 tỷ) thay vì 3000000000

### 🎫 Phiếu giảm giá
- Tất cả voucher tính theo phần trăm (%)
- Giá trị từ 0-100%
- Hiển thị: "10%" thay vì "10"

### 📋 Tiến độ nhập xe
1. **Chờ xác nhận** - Phiếu đặt mới tạo
2. **Đã xác nhận** - Nhà phân phối xác nhận
3. **Đang sản xuất** - Xe đang được sản xuất
4. **Đang vận chuyển** - Xe đang trên đường về VN
5. **Về cảng** - Xe đã về cảng, chờ thủ tục
6. **Về kho** - Xe đã về showroom
7. **Hoàn thành** - Đã lập hóa đơn bán
8. **Hủy** - Khách hủy đơn

### 🚘 Tình trạng xe
1. **Chờ nhập** - Xe đang được đặt hàng
2. **Đã về kho** - Xe đã về showroom, sẵn sàng bán
3. **Đã bán** - Xe đã có chủ
4. **Đang bảo dưỡng** - Xe đang được bảo dưỡng

### 🔧 Trạng thái lịch bảo dưỡng
1. **Đã đặt lịch** - Khách đã đặt lịch
2. **Đang thực hiện** - Đang bảo dưỡng
3. **Hoàn thành** - Đã hoàn thành
4. **Hủy** - Khách hủy lịch

### 👥 Phân loại khách hàng
1. **Thường** - Khách hàng mới
2. **VIP** - Khách hàng mua từ 2 xe trở lên
3. **Thân thiết** - Khách hàng lâu năm, thường xuyên bảo dưỡng

## 8. HỖ TRỢ

Nếu gặp vấn đề, kiểm tra:
1. Kết nối database trong `db.properties`
2. Các bảng đã được tạo đầy đủ
3. Dữ liệu mẫu đã được import
4. Log lỗi trong console

---
**Phiên bản**: 2.0  
**Ngày cập nhật**: 02/12/2025
