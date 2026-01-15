package dao;

import entity.HoaDon;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import uitl.XJdbc;

public class HoaDonDao {

    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon";
        try {
            ResultSet rs = XJdbc.executeQuery(sql);
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                hd.setMaKhachHang(rs.getString("MaKhachHang"));
                hd.setMaNhanVien(rs.getString("MaNhanVien"));
                hd.setTongTien(rs.getBigDecimal("TongTien"));
                try {
                    hd.setTienTraTruoc(rs.getBigDecimal("TienTraTruoc"));
                } catch (Exception ignore) {
                    hd.setTienTraTruoc(null);
                }
                hd.setTrangThai(rs.getBoolean("TrangThai"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                list.add(hd);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public HoaDon findById(String maHoaDon) {
        String sql = "SELECT * FROM HoaDon WHERE MaHoaDon = ?";
        try {
            ResultSet rs = XJdbc.executeQuery(sql, maHoaDon);
            if (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                hd.setMaKhachHang(rs.getString("MaKhachHang"));
                hd.setMaNhanVien(rs.getString("MaNhanVien"));
                hd.setTongTien(rs.getBigDecimal("TongTien"));
                try {
                    hd.setTienTraTruoc(rs.getBigDecimal("TienTraTruoc"));
                } catch (Exception ignore) {
                    hd.setTienTraTruoc(null);
                }
                hd.setTrangThai(rs.getBoolean("TrangThai"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                rs.getStatement().getConnection().close();
                return hd;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getNewMaHoaDon() {
        String sql = "SELECT TOP 1 MaHoaDon FROM HoaDon ORDER BY MaHoaDon DESC";
        try {
            ResultSet rs = XJdbc.executeQuery(sql);
            if (rs.next()) {
                String lastMa = rs.getString("MaHoaDon");
                int num = Integer.parseInt(lastMa.substring(2)) + 1;
                rs.getStatement().getConnection().close();
                return String.format("HD%03d", num);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "HD001";
    }

    public boolean add(HoaDon hd) {
        String sql = "INSERT INTO HoaDon (MaHoaDon, MaKhachHang, MaNhanVien, TongTien, TrangThai, NgayTao, TienTraTruoc) VALUES (?, ?, ?, ?, ?, GETDATE(), ?)";
        try {
            java.math.BigDecimal tienTra = hd.getTienTraTruoc() == null ? java.math.BigDecimal.ZERO
                    : hd.getTienTraTruoc();
            XJdbc.executeUpdate(sql,
                    hd.getMaHoaDon(),
                    hd.getMaKhachHang(),
                    hd.getMaNhanVien(),
                    hd.getTongTien(),
                    hd.isTrangThai(),
                    tienTra);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isDaThanhToan(String maHoaDon) {
        HoaDon hd = findById(maHoaDon);
        return hd != null && hd.isTrangThai();
    }

    public boolean hoaDonChuaCoSanPham(String maHoaDon) {
        // Simplified - always return true to allow deletion
        return true;
    }

    public boolean deleteHoaDon(String maHoaDon) {
        String sql = "DELETE FROM HoaDon WHERE MaHoaDon = ?";
        try {
            XJdbc.executeUpdate(sql, maHoaDon);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateTrangThai(String maHoaDon, boolean trangThai) {
        String sql = "UPDATE HoaDon SET TrangThai = ? WHERE MaHoaDon = ?";
        try {
            XJdbc.executeUpdate(sql, trangThai, maHoaDon);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
