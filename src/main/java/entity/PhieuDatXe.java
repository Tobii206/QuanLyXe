package entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Entity cho Phiếu đặt xe
 */
public class PhieuDatXe {
    private String maPhieuDat;
    private String maKhachHang;
    private String maNhanVien;
    private String maSanPham;
    private String maMauSac;
    private String maKichThuoc;
    private String yeuCauCauHinh;
    private BigDecimal giaTriDuKien;
    private BigDecimal tienDatCoc;
    private Date ngayDat;
    private Date ngayDuKienVe;
    private String tienDoNhap; // Chờ xác nhận, Đã xác nhận, Đang sản xuất, Đang vận chuyển, Về cảng, Về kho, Hoàn thành, Hủy
    private String maXe; // Liên kết với xe cụ thể khi xe về
    private String ghiChu;

    public PhieuDatXe() {
    }

    public String getMaPhieuDat() {
        return maPhieuDat;
    }

    public void setMaPhieuDat(String maPhieuDat) {
        this.maPhieuDat = maPhieuDat;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getMaMauSac() {
        return maMauSac;
    }

    public void setMaMauSac(String maMauSac) {
        this.maMauSac = maMauSac;
    }

    public String getMaKichThuoc() {
        return maKichThuoc;
    }

    public void setMaKichThuoc(String maKichThuoc) {
        this.maKichThuoc = maKichThuoc;
    }

    public String getYeuCauCauHinh() {
        return yeuCauCauHinh;
    }

    public void setYeuCauCauHinh(String yeuCauCauHinh) {
        this.yeuCauCauHinh = yeuCauCauHinh;
    }

    public BigDecimal getGiaTriDuKien() {
        return giaTriDuKien;
    }

    public void setGiaTriDuKien(BigDecimal giaTriDuKien) {
        this.giaTriDuKien = giaTriDuKien;
    }

    public BigDecimal getTienDatCoc() {
        return tienDatCoc;
    }

    public void setTienDatCoc(BigDecimal tienDatCoc) {
        this.tienDatCoc = tienDatCoc;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public Date getNgayDuKienVe() {
        return ngayDuKienVe;
    }

    public void setNgayDuKienVe(Date ngayDuKienVe) {
        this.ngayDuKienVe = ngayDuKienVe;
    }

    public String getTienDoNhap() {
        return tienDoNhap;
    }

    public void setTienDoNhap(String tienDoNhap) {
        this.tienDoNhap = tienDoNhap;
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
