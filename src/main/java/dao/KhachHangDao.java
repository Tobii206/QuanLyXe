package dao;

import entity.KhachHang;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import uitl.XJdbc;

public class KhachHangDao {
    
    public List<KhachHang> getAll() {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang";
        try {
            ResultSet rs = XJdbc.executeQuery(sql);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKhachHang(rs.getString("MaKhachHang"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                kh.setEmail(rs.getString("Email"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setTrangThai(rs.getInt("TrangThai"));
                kh.setNgayDangKy(rs.getDate("NgayDangKy"));
                list.add(kh);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public KhachHang findById(String maKhachHang) {
        String sql = "SELECT * FROM KhachHang WHERE MaKhachHang = ?";
        try {
            ResultSet rs = XJdbc.executeQuery(sql, maKhachHang);
            if (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKhachHang(rs.getString("MaKhachHang"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                kh.setEmail(rs.getString("Email"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setTrangThai(rs.getInt("TrangThai"));
                kh.setNgayDangKy(rs.getDate("NgayDangKy"));
                rs.getStatement().getConnection().close();
                return kh;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void insert(KhachHang kh) {
        String sql = "INSERT INTO KhachHang (MaKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, DiaChi, TrangThai, NgayDangKy) VALUES (?, ?, ?, ?, ?, ?, ?, GETDATE())";
        try {
            XJdbc.executeUpdate(sql, 
                kh.getMaKhachHang(),
                kh.getTenKhachHang(),
                kh.getGioiTinh(),
                kh.getSoDienThoai(),
                kh.getEmail(),
                kh.getDiaChi(),
                kh.getTrangThai()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void update(KhachHang kh) {
        String sql = "UPDATE KhachHang SET TenKhachHang = ?, GioiTinh = ?, SoDienThoai = ?, Email = ?, DiaChi = ?, TrangThai = ? WHERE MaKhachHang = ?";
        try {
            XJdbc.executeUpdate(sql,
                kh.getTenKhachHang(),
                kh.getGioiTinh(),
                kh.getSoDienThoai(),
                kh.getEmail(),
                kh.getDiaChi(),
                kh.getTrangThai(),
                kh.getMaKhachHang()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(String maKhachHang) {
        String sql = "UPDATE KhachHang SET TrangThai = 1 WHERE MaKhachHang = ?";
        try {
            XJdbc.executeUpdate(sql, maKhachHang);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deletehet(String maKhachHang) {
        String sql = "DELETE FROM KhachHang WHERE MaKhachHang = ?";
        try {
            XJdbc.executeUpdate(sql, maKhachHang);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<KhachHang> findAllFields(String keyword) {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang WHERE MaKhachHang LIKE ? OR TenKhachHang LIKE ? OR SoDienThoai LIKE ? OR Email LIKE ?";
        String search = "%" + keyword + "%";
        try {
            ResultSet rs = XJdbc.executeQuery(sql, search, search, search, search);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKhachHang(rs.getString("MaKhachHang"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                kh.setEmail(rs.getString("Email"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setTrangThai(rs.getInt("TrangThai"));
                kh.setNgayDangKy(rs.getDate("NgayDangKy"));
                list.add(kh);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<KhachHang> findGioiTinh(String gioiTinh) {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang WHERE GioiTinh = ?";
        try {
            ResultSet rs = XJdbc.executeQuery(sql, gioiTinh);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKhachHang(rs.getString("MaKhachHang"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                kh.setEmail(rs.getString("Email"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setTrangThai(rs.getInt("TrangThai"));
                kh.setNgayDangKy(rs.getDate("NgayDangKy"));
                list.add(kh);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<KhachHang> findTrangThai(boolean trangThai) {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang WHERE TrangThai = ?";
        try {
            ResultSet rs = XJdbc.executeQuery(sql, trangThai ? 1 : 0);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKhachHang(rs.getString("MaKhachHang"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                kh.setEmail(rs.getString("Email"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setTrangThai(rs.getInt("TrangThai"));
                kh.setNgayDangKy(rs.getDate("NgayDangKy"));
                list.add(kh);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public KhachHang findByEmail(String email) {
        String sql = "SELECT * FROM KhachHang WHERE Email = ?";
        try {
            ResultSet rs = XJdbc.executeQuery(sql, email);
            if (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKhachHang(rs.getString("MaKhachHang"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                kh.setEmail(rs.getString("Email"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setTrangThai(rs.getInt("TrangThai"));
                kh.setNgayDangKy(rs.getDate("NgayDangKy"));
                rs.getStatement().getConnection().close();
                return kh;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public KhachHang findByMa(String maKhachHang) {
        return findById(maKhachHang);
    }
    
    public String getNewMaKhachHang() {
        String sql = "SELECT TOP 1 MaKhachHang FROM KhachHang ORDER BY MaKhachHang DESC";
        try {
            ResultSet rs = XJdbc.executeQuery(sql);
            if (rs.next()) {
                String lastMa = rs.getString("MaKhachHang");
                int num = Integer.parseInt(lastMa.substring(2)) + 1;
                rs.getStatement().getConnection().close();
                return String.format("KH%03d", num);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "KH001";
    }
}
