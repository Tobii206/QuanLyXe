/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
/**
 *
 * @author Nga Cọt
 */
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ChiTietSanPham {
    private String maChiTietSP;
    private int soLuong;
    private BigDecimal gia;
    private String maSanPham;
    private String maMauSac;
    private String maKichThuoc;
    
    // Tương thích với code cũ
    public String getMaChiTietSP() { return maChiTietSP; }
    public void setMaChiTietSP(String maChiTietSP) { this.maChiTietSP = maChiTietSP; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public BigDecimal getGia() { return gia; }
    public void setGia(BigDecimal gia) { this.gia = gia; }
    public String getMaSanPham() { return maSanPham; }
    public void setMaSanPham(String maSanPham) { this.maSanPham = maSanPham; }
    public String getMaMauSac() { return maMauSac; }
    public void setMaMauSac(String maMauSac) { this.maMauSac = maMauSac; }
    public String getMaKichThuoc() { return maKichThuoc; }
    public void setMaKichThuoc(String maKichThuoc) { this.maKichThuoc = maKichThuoc; }
}
