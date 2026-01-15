package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.math.BigDecimal;
/**
 *
 * @author Admin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoaDon2 {
    private String maHoaDon;
    private BigDecimal tongTien;
    private Date ngayTao;
    private String soDienThoai;
    private BigDecimal tienGiam;
    private BigDecimal tongTienGoc;
    private BigDecimal giaTri;
    private BigDecimal tienSauGiam;
    private boolean trangThai;
    private String maNhanVien;
    private String tenKhachHang;
    private String maKhachHang;
    private String hoTen;
    
    // Tương thích với code cũ
    public String getMaHoaDon() { return maHoaDon; }
    public BigDecimal getTongTien() { return tongTien; }
    public Date getNgayTao() { return ngayTao; }
    public String getSoDienThoai() { return soDienThoai; }
    public BigDecimal getTienGiam() { return tienGiam; }
    public BigDecimal getTongTienGoc() { return tongTienGoc; }
    public BigDecimal getGiaTri() { return giaTri; }
    public BigDecimal getTienSauGiam() { return tienSauGiam; }
    public boolean isTrangThai() { return trangThai; }
    public String getMaNhanVien() { return maNhanVien; }
    public String getTenKhachHang() { return tenKhachHang; }
    public String getMaKhachHang() { return maKhachHang; }
    public String getHoTen() { return hoTen; }
}
