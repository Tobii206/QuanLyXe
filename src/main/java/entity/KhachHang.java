package entity;

import java.util.Date;

public class KhachHang {
    private String MaKhachHang;
    private String TenKhachHang;
    private String GioiTinh;
    private String SoDienThoai;
    private String Email;
    private String DiaChi;
    private int TrangThai;
    private Date NgayDangKy;

    public KhachHang() {
    }

    public KhachHang(String MaKhachHang, String TenKhachHang, String GioiTinh, String SoDienThoai, String Email, String DiaChi, int TrangThai, Date NgayDangKy) {
        this.MaKhachHang = MaKhachHang;
        this.TenKhachHang = TenKhachHang;
        this.GioiTinh = GioiTinh;
        this.SoDienThoai = SoDienThoai;
        this.Email = Email;
        this.DiaChi = DiaChi;
        this.TrangThai = TrangThai;
        this.NgayDangKy = NgayDangKy;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public boolean isTrangThai() {
        return TrangThai == 1;
    }

    public Date getNgayDangKy() {
        return NgayDangKy;
    }

    public void setNgayDangKy(Date NgayDangKy) {
        this.NgayDangKy = NgayDangKy;
    }
    
    // Alias method for compatibility
    public int getId() {
        try {
            return Integer.parseInt(MaKhachHang.replaceAll("[^0-9]", ""));
        } catch (Exception e) {
            return 0;
        }
    }
}
