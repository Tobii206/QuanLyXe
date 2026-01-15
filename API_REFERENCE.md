# TÀI LIỆU THAM KHẢO API/DAO

## 1. ThongTinXeDao - Quản lý xe cụ thể

### Các phương thức chính:

```java
// Lấy tất cả xe
List<ThongTinXe> getAll()

// Tìm xe theo mã
ThongTinXe findById(String maXe)

// Tìm xe theo tình trạng
List<ThongTinXe> findByTinhTrang(String tinhTrang)
// Ví dụ: "Chờ nhập", "Đã về kho", "Đã bán", "Đang bảo dưỡng"

// Tìm xe theo dòng sản phẩm
List<ThongTinXe> findBySanPham(String maSanPham)

// Tìm xe theo số khung
List<ThongTinXe> findBySoKhung(String soKhung)

// Thêm xe mới
boolean insert(ThongTinXe xe)

// Cập nhật thông tin xe
boolean update(ThongTinXe xe)

// Cập nhật tình trạng xe
boolean updateTinhTrang(String maXe, String tinhTrang)

// Cập nhật ngày bán (tự động đổi tình trạng thành "Đã bán")
boolean updateNgayBan(String maXe, java.sql.Date ngayBan)

// Xóa xe
boolean delete(String maXe)

// Tạo mã xe mới tự động
String getNewMaXe() // Trả về: XE00001, XE00002, ...

// Đếm số xe theo tình trạng
int countByTinhTrang(String tinhTrang)
```

### Ví dụ sử dụng:

```java
ThongTinXeDao xeDao = new ThongTinXeDao();

// Lấy tất cả xe trong kho
List<ThongTinXe> xeTrongKho = xeDao.findByTinhTrang("Đã về kho");

// Thêm xe mới
ThongTinXe xeMoi = new ThongTinXe();
xeMoi.setMaXe(xeDao.getNewMaXe());
xeMoi.setMaSanPham("SP00001");
xeMoi.setSoKhung("WP0ZZZ98ZTS392128");
xeMoi.setGiaBan(new BigDecimal("3000000000"));
xeDao.insert(xeMoi);

// Cập nhật khi bán xe
xeDao.updateNgayBan("XE00001", new java.sql.Date(System.currentTimeMillis()));
```

---

## 2. PhieuDatXeDao - Quản lý phiếu đặt xe

### Các phương thức chính:

```java
// Lấy tất cả phiếu đặt
List<PhieuDatXe> getAll()

// Tìm phiếu theo mã
PhieuDatXe findById(String maPhieuDat)

// Tìm phiếu theo tiến độ
List<PhieuDatXe> findByTienDoNhap(String tienDoNhap)
// Ví dụ: "Chờ xác nhận", "Đang sản xuất", "Đang vận chuyển", ...

// Tìm phiếu theo khách hàng
List<PhieuDatXe> findByKhachHang(String maKhachHang)

// Tìm phiếu theo xe
PhieuDatXe findByMaXe(String maXe)

// Thêm phiếu đặt mới
boolean insert(PhieuDatXe phieuDat)

// Cập nhật phiếu đặt
boolean update(PhieuDatXe phieuDat)

// Cập nhật tiến độ nhập
boolean updateTienDoNhap(String maPhieuDat, String tienDoNhap)

// Cập nhật mã xe khi xe về (tự động đổi tiến độ thành "Hoàn thành")
boolean updateMaXe(String maPhieuDat, String maXe)

// Xóa phiếu đặt
boolean delete(String maPhieuDat)

// Tạo mã phiếu mới tự động
String getNewMaPhieuDat() // Trả về: PDX0001, PDX0002, ...

// Đếm số phiếu theo tiến độ
int countByTienDo(String tienDoNhap)
```

### Ví dụ sử dụng:

```java
PhieuDatXeDao phieuDao = new PhieuDatXeDao();

// Tạo phiếu đặt mới
PhieuDatXe phieu = new PhieuDatXe();
phieu.setMaPhieuDat(phieuDao.getNewMaPhieuDat());
phieu.setMaKhachHang("KH00001");
phieu.setMaNhanVien("NV00001");
phieu.setMaSanPham("SP00001");
phieu.setYeuCauCauHinh("Màu đen, nội thất da, camera 360");
phieu.setGiaTriDuKien(new BigDecimal("3000000000"));
phieu.setTienDatCoc(new BigDecimal("300000000")); // 10%
phieu.setTienDoNhap("Chờ xác nhận");
phieuDao.insert(phieu);

// Cập nhật tiến độ
phieuDao.updateTienDoNhap("PDX0001", "Đang sản xuất");

// Khi xe về, liên kết với xe cụ thể
phieuDao.updateMaXe("PDX0001", "XE00005");
```

---

## 3. LichSuXeDao - Quản lý lịch sử xe

### Các phương thức chính:

```java
// Lấy tất cả lịch sử
List<LichSuXe> getAll()

// Tìm lịch sử theo mã
LichSuXe findById(Integer maLichSu)

// Lấy lịch sử của một xe
List<LichSuXe> findByMaXe(String maXe)

// Tìm theo hành động
List<LichSuXe> findByHanhDong(String hanhDong)
// Ví dụ: "Nhập kho", "Bán", "Bảo dưỡng", "Sửa chữa", "Kiểm tra"

// Thêm lịch sử mới
boolean insert(LichSuXe lichSu)

// Xóa lịch sử
boolean delete(Integer maLichSu)

// Ghi nhận hành động nhanh
boolean ghiNhanHanhDong(String maXe, String hanhDong, String nguoiThucHien, String chiTiet)
```

### Ví dụ sử dụng:

```java
LichSuXeDao lichSuDao = new LichSuXeDao();

// Ghi nhận xe nhập kho
lichSuDao.ghiNhanHanhDong(
    "XE00001", 
    "Nhập kho", 
    "NV00001", 
    "Xe nhập về từ cảng Hải Phòng, kiểm tra đầy đủ"
);

// Ghi nhận bán xe
lichSuDao.ghiNhanHanhDong(
    "XE00001", 
    "Bán", 
    "NV00001", 
    "Bán cho khách hàng KH00001, hóa đơn HD00001"
);

// Xem lịch sử của một xe
List<LichSuXe> lichSu = lichSuDao.findByMaXe("XE00001");
for (LichSuXe ls : lichSu) {
    System.out.println(ls.getNgayGhiNhan() + ": " + ls.getHanhDong());
}
```

---

## 4. LichBaoDuongDao - Quản lý lịch bảo dưỡng

### Các phương thức chính:

```java
// Lấy tất cả lịch bảo dưỡng
List<LichBaoDuong> getAll()

// Tìm lịch theo mã
LichBaoDuong findById(String maLichBD)

// Tìm lịch theo khách hàng
List<LichBaoDuong> findByKhachHang(String maKhachHang)

// Tìm lịch theo xe
List<LichBaoDuong> findByMaXe(String maXe)

// Tìm lịch theo trạng thái
List<LichBaoDuong> findByTrangThai(String trangThai)
// Ví dụ: "Đã đặt lịch", "Đang thực hiện", "Hoàn thành", "Hủy"

// Tìm lịch theo khoảng ngày
List<LichBaoDuong> findByNgay(java.sql.Date tuNgay, java.sql.Date denNgay)

// Thêm lịch mới
boolean insert(LichBaoDuong lichBD)

// Cập nhật lịch
boolean update(LichBaoDuong lichBD)

// Cập nhật trạng thái
boolean updateTrangThai(String maLichBD, String trangThai)

// Xóa lịch
boolean delete(String maLichBD)

// Tạo mã lịch mới tự động
String getNewMaLichBD() // Trả về: LBD0001, LBD0002, ...

// Lấy lịch sắp tới (trong 7 ngày)
List<LichBaoDuong> getLichSapToi()

// Đếm số lịch theo trạng thái
int countByTrangThai(String trangThai)
```

### Ví dụ sử dụng:

```java
LichBaoDuongDao lichBDDao = new LichBaoDuongDao();

// Đặt lịch bảo dưỡng
LichBaoDuong lich = new LichBaoDuong();
lich.setMaLichBD(lichBDDao.getNewMaLichBD());
lich.setMaKhachHang("KH00001");
lich.setMaXe("XE00001");
lich.setBienSoXe("30A-12345");
lich.setNgayBaoDuong(java.sql.Date.valueOf("2025-02-01"));
lich.setLoaiDichVu("Bảo dưỡng định kỳ");
lich.setNoiDung("Bảo dưỡng 10,000km: Thay dầu, lọc gió");
lich.setChiPhi(new BigDecimal("8000000"));
lich.setTrangThai("Đã đặt lịch");
lichBDDao.insert(lich);

// Lấy lịch sắp tới để nhắc khách
List<LichBaoDuong> lichSapToi = lichBDDao.getLichSapToi();
for (LichBaoDuong l : lichSapToi) {
    System.out.println("Nhắc khách " + l.getMaKhachHang() + 
                      " bảo dưỡng ngày " + l.getNgayBaoDuong());
}
```

---

## 5. Luồng nghiệp vụ hoàn chỉnh

### A. Khách đặt xe mới

```java
// 1. Tạo phiếu đặt xe
PhieuDatXeDao phieuDao = new PhieuDatXeDao();
PhieuDatXe phieu = new PhieuDatXe();
phieu.setMaPhieuDat(phieuDao.getNewMaPhieuDat());
phieu.setMaKhachHang("KH00001");
phieu.setMaNhanVien("NV00001");
phieu.setMaSanPham("SP00001");
phieu.setYeuCauCauHinh("Màu đen, nội thất da");
phieu.setGiaTriDuKien(new BigDecimal("3000000000"));
phieu.setTienDatCoc(new BigDecimal("300000000"));
phieu.setTienDoNhap("Chờ xác nhận");
phieuDao.insert(phieu);

// 2. Cập nhật tiến độ khi nhà phân phối xác nhận
phieuDao.updateTienDoNhap("PDX0001", "Đã xác nhận");
phieuDao.updateTienDoNhap("PDX0001", "Đang sản xuất");
phieuDao.updateTienDoNhap("PDX0001", "Đang vận chuyển");
phieuDao.updateTienDoNhap("PDX0001", "Về cảng");
```

### B. Xe về kho

```java
ThongTinXeDao xeDao = new ThongTinXeDao();
LichSuXeDao lichSuDao = new LichSuXeDao();

// 1. Tạo thông tin xe cụ thể
ThongTinXe xe = new ThongTinXe();
xe.setMaXe(xeDao.getNewMaXe());
xe.setMaSanPham("SP00001");
xe.setSoKhung("WP0ZZZ98ZTS392128");
xe.setSoMay("M97.21-005");
xe.setGiaBan(new BigDecimal("3000000000"));
xe.setTinhTrang("Đã về kho");
xe.setNgayNhapKho(new java.sql.Date(System.currentTimeMillis()));
xeDao.insert(xe);

// 2. Liên kết xe với phiếu đặt
phieuDao.updateMaXe("PDX0001", xe.getMaXe());

// 3. Ghi nhận lịch sử
lichSuDao.ghiNhanHanhDong(
    xe.getMaXe(), 
    "Nhập kho", 
    "NV00001", 
    "Xe nhập về từ cảng, kiểm tra đầy đủ"
);
```

### C. Bán xe

```java
HoaDonDao hoaDonDao = new HoaDonDao();

// 1. Tạo hóa đơn
HoaDon hoaDon = new HoaDon();
hoaDon.setMaHoaDon(hoaDonDao.getNewMaHoaDon());
hoaDon.setMaKhachHang("KH00001");
hoaDon.setMaNhanVien("NV00001");
hoaDon.setMaXe("XE00005");
hoaDon.setMaPhieuDat("PDX0001");
hoaDon.setTongTien(new BigDecimal("3000000000"));
hoaDon.setTrangThai(true);
hoaDonDao.add(hoaDon);

// 2. Cập nhật trạng thái xe
xeDao.updateNgayBan("XE00005", new java.sql.Date(System.currentTimeMillis()));

// 3. Ghi nhận lịch sử
lichSuDao.ghiNhanHanhDong(
    "XE00005", 
    "Bán", 
    "NV00001", 
    "Bán cho khách hàng KH00001"
);
```

---

## 6. View để xem thông tin đầy đủ

```sql
-- Xem thông tin xe chi tiết
SELECT * FROM V_ThongTinXeChiTiet;

-- Kết quả bao gồm:
-- MaXe, SoKhung, SoMay, BienSo, TenSanPham, TenMau, KichThuoc,
-- NamSanXuat, CauHinhChiTiet, GiaNhap, GiaBan, TinhTrang,
-- NgayNhapKho, NgayBan, MaPhieuDat, MaKhachHang, TenKhachHang,
-- TienDatCoc, GhiChu
```

---

**Phiên bản**: 2.0  
**Ngày cập nhật**: 02/12/2025
