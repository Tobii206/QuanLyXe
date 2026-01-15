package dao;

import entity.NhanVien;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import uitl.XJdbc;

public class NhanVienDao {
    
    public List<NhanVien> getAll() {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
        try {
            ResultSet rs = XJdbc.executeQuery(sql);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setHoTen(rs.getString("hoTen"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setSoDienThoai(rs.getString("SoDienThoai"));
                nv.setEmail(rs.getString("Email"));
                nv.setMaChucVu(rs.getString("MaChucVu"));
                nv.setTrangThai(rs.getInt("TrangThai"));
                list.add(nv);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public NhanVien findById(String maNhanVien) {
        String sql = "SELECT * FROM NhanVien WHERE MaNhanVien = ?";
        try {
            ResultSet rs = XJdbc.executeQuery(sql, maNhanVien);
            if (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setHoTen(rs.getString("hoTen"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setSoDienThoai(rs.getString("SoDienThoai"));
                nv.setEmail(rs.getString("Email"));
                nv.setMaChucVu(rs.getString("MaChucVu"));
                nv.setTrangThai(rs.getInt("TrangThai"));
                rs.getStatement().getConnection().close();
                return nv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void insert(NhanVien nv) {
        String sql = "INSERT INTO NhanVien (MaNhanVien, hoTen, GioiTinh, SoDienThoai, Email, MaChucVu, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            XJdbc.executeUpdate(sql,
                nv.getMaNhanVien(),
                nv.getHoTen(),
                nv.getGioiTinh(),
                nv.getSoDienThoai(),
                nv.getEmail(),
                nv.getMaChucVu(),
                nv.getTrangThai()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void update(NhanVien nv) {
        String sql = "UPDATE NhanVien SET hoTen = ?, GioiTinh = ?, SoDienThoai = ?, Email = ?, MaChucVu = ?, TrangThai = ? WHERE MaNhanVien = ?";
        try {
            XJdbc.executeUpdate(sql,
                nv.getHoTen(),
                nv.getGioiTinh(),
                nv.getSoDienThoai(),
                nv.getEmail(),
                nv.getMaChucVu(),
                nv.getTrangThai(),
                nv.getMaNhanVien()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(String maNhanVien) {
        String sql = "UPDATE NhanVien SET TrangThai = 1 WHERE MaNhanVien = ?";
        try {
            XJdbc.executeUpdate(sql, maNhanVien);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getNextMaNhanVien() {
        String sql = "SELECT TOP 1 MaNhanVien FROM NhanVien ORDER BY MaNhanVien DESC";
        try {
            ResultSet rs = XJdbc.executeQuery(sql);
            if (rs.next()) {
                String lastMa = rs.getString("MaNhanVien");
                int num = Integer.parseInt(lastMa.substring(2)) + 1;
                rs.getStatement().getConnection().close();
                return String.format("NV%03d", num);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "NV001";
    }
}
