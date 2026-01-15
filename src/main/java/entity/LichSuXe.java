package entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Entity cho lịch sử xe - Theo dõi từng hành động với xe cụ thể
 */
public class LichSuXe {
    private Integer maLichSu;
    private String maXe;
    private Timestamp ngayGhiNhan;
    private String hanhDong; // Nhập kho, Bán, Bảo dưỡng, Sửa chữa, Kiểm tra
    private String nguoiThucHien;
    private String chiTiet;
    private BigDecimal chiPhi;

    public LichSuXe() {
    }

    public Integer getMaLichSu() {
        return maLichSu;
    }

    public void setMaLichSu(Integer maLichSu) {
        this.maLichSu = maLichSu;
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public Timestamp getNgayGhiNhan() {
        return ngayGhiNhan;
    }

    public void setNgayGhiNhan(Timestamp ngayGhiNhan) {
        this.ngayGhiNhan = ngayGhiNhan;
    }

    public String getHanhDong() {
        return hanhDong;
    }

    public void setHanhDong(String hanhDong) {
        this.hanhDong = hanhDong;
    }

    public String getNguoiThucHien() {
        return nguoiThucHien;
    }

    public void setNguoiThucHien(String nguoiThucHien) {
        this.nguoiThucHien = nguoiThucHien;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    public BigDecimal getChiPhi() {
        return chiPhi;
    }

    public void setChiPhi(BigDecimal chiPhi) {
        this.chiPhi = chiPhi;
    }
}
