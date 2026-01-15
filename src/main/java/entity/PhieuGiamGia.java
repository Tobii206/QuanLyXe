package entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
public class PhieuGiamGia {
    private String maPhieu;
    private String tenPhieu;
    private String tenHinhThucGG;
    private BigDecimal giaTri;
    private int soLuong;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String dieuKienApDung;
    private Boolean trangThai;
    
    public String getMaPhieu() { return maPhieu; }
    public void setMaPhieu(String maPhieu) { this.maPhieu = maPhieu; }
    
    public String getTenPhieu() { return tenPhieu; }
    public void setTenPhieu(String tenPhieu) { this.tenPhieu = tenPhieu; }
    
    public String getTenHinhThucGG() { return tenHinhThucGG; }
    public void setTenHinhThucGG(String tenHinhThucGG) { this.tenHinhThucGG = tenHinhThucGG; }
    
    public BigDecimal getGiaTri() { return giaTri; }
    public void setGiaTri(BigDecimal giaTri) { this.giaTri = giaTri; }
    
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    
    public Date getNgayBatDau() { return ngayBatDau; }
    public void setNgayBatDau(Date ngayBatDau) { this.ngayBatDau = ngayBatDau; }
    
    public Date getNgayKetThuc() { return ngayKetThuc; }
    public void setNgayKetThuc(Date ngayKetThuc) { this.ngayKetThuc = ngayKetThuc; }
    
    public String getDieuKienApDung() { return dieuKienApDung; }
    public void setDieuKienApDung(String dieuKienApDung) { this.dieuKienApDung = dieuKienApDung; }
    
    public Boolean getTrangThai() { return trangThai; }
    public void setTrangThai(Boolean trangThai) { this.trangThai = trangThai; }
    
    // Method for boolean check
    public boolean isTrangThai() {
        return trangThai != null && trangThai;
    }
    
    @Override
    public String toString() {
        return this.maPhieu;
    }
}
