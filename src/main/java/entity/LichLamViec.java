package entity;

import java.util.Date;

/**
 * Entity Lịch làm việc
 */
public class LichLamViec {
    private int maLich;
    private String maNhanVien;
    private String tenNhanVien;
    private Date ngayLamViec;
    private String caLamViec; // "Sáng", "Chiều", "Tối"
    private String ghiChu;
    private int trangThai; // 0: Chưa làm, 1: Đã làm, 2: Nghỉ

    public LichLamViec() {
    }

    public LichLamViec(int maLich, String maNhanVien, String tenNhanVien, Date ngayLamViec, String caLamViec, String ghiChu, int trangThai) {
        this.maLich = maLich;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.ngayLamViec = ngayLamViec;
        this.caLamViec = caLamViec;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public int getMaLich() {
        return maLich;
    }

    public void setMaLich(int maLich) {
        this.maLich = maLich;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public Date getNgayLamViec() {
        return ngayLamViec;
    }

    public void setNgayLamViec(Date ngayLamViec) {
        this.ngayLamViec = ngayLamViec;
    }

    public String getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(String caLamViec) {
        this.caLamViec = caLamViec;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
