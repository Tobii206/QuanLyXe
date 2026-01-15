package entity;

import java.math.BigDecimal;
import java.sql.Date;

public class LichBaoDuong {
    private String maLichBD;
    private String maKhachHang;
    private String maXe; // Mã xe cụ thể
    private String bienSoXe;
    private Date ngayBaoDuong;
    private String loaiDichVu; // Bảo dưỡng định kỳ, Sửa chữa, Thay thế linh kiện
    private String noiDung;
    private BigDecimal chiPhi;
    private String trangThai; // Đã đặt lịch, Đang thực hiện, Hoàn thành, Hủy
    private String nhanVienThucHien;
    private String ghiChu;

    public LichBaoDuong() {
    }

    public LichBaoDuong(String maLichBD, String maKhachHang, String maXe, String bienSoXe,
                        Date ngayBaoDuong, String loaiDichVu, String noiDung, BigDecimal chiPhi,
                        String trangThai, String nhanVienThucHien, String ghiChu) {
        this.maLichBD = maLichBD;
        this.maKhachHang = maKhachHang;
        this.maXe = maXe;
        this.bienSoXe = bienSoXe;
        this.ngayBaoDuong = ngayBaoDuong;
        this.loaiDichVu = loaiDichVu;
        this.noiDung = noiDung;
        this.chiPhi = chiPhi;
        this.trangThai = trangThai;
        this.nhanVienThucHien = nhanVienThucHien;
        this.ghiChu = ghiChu;
    }

    public String getMaLichBD() {
        return maLichBD;
    }

    public void setMaLichBD(String maLichBD) {
        this.maLichBD = maLichBD;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public String getBienSoXe() {
        return bienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
    }

    public Date getNgayBaoDuong() {
        return ngayBaoDuong;
    }

    public void setNgayBaoDuong(Date ngayBaoDuong) {
        this.ngayBaoDuong = ngayBaoDuong;
    }

    public String getLoaiDichVu() {
        return loaiDichVu;
    }

    public void setLoaiDichVu(String loaiDichVu) {
        this.loaiDichVu = loaiDichVu;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public BigDecimal getChiPhi() {
        return chiPhi;
    }

    public void setChiPhi(BigDecimal chiPhi) {
        this.chiPhi = chiPhi;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getNhanVienThucHien() {
        return nhanVienThucHien;
    }

    public void setNhanVienThucHien(String nhanVienThucHien) {
        this.nhanVienThucHien = nhanVienThucHien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
