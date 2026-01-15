package entity;

import lombok.*;
import java.math.BigDecimal;


/**
 *
 * @author Admin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonChiTiet {
    private String maHoaDon;
    private String maChiTietHoaDon;
    private String maSanPham;
    private int soLuong;
    private String maMauSac;
    private String maKichThuoc;
    private String maChatLieu;
    private String maXuatSu;
    private BigDecimal donGia;
    
    // Tương thích với code cũ
    public String getMaHoaDon() { return maHoaDon; }
    public String getMaChiTietHoaDon() { return maChiTietHoaDon; }
    public String getMaSanPham() { return maSanPham; }
    public int getSoLuong() { return soLuong; }
    public String getMaMauSac() { return maMauSac; }
    public String getMaKichThuoc() { return maKichThuoc; }
    public String getMaChatLieu() { return maChatLieu; }
    public String getMaXuatSu() { return maXuatSu; }
    public BigDecimal getDonGia() { return donGia; }
}
