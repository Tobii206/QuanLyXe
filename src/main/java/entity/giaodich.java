package entity;

import lombok.*;
import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class giaodich {
    private String maKhachHang;
    private String maHoaDon;
    private String tenSanPham;
    private int soLuong;
    private BigDecimal tongTien;
    private boolean trangThai;
    
    // Tương thích với code cũ
    public String getMaKhachHang() { return maKhachHang; }
    public String getMaHoaDon() { return maHoaDon; }
    public String getTenSanPham() { return tenSanPham; }
    public int getSoLuong() { return soLuong; }
    public BigDecimal getTongTien() { return tongTien; }
    public boolean isTrangThai() { return trangThai; }
}
