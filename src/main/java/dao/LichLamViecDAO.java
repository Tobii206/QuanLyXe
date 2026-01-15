package dao;

import entity.LichLamViec;
import uitl.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO cho Lịch làm việc
 */
public class LichLamViecDAO {
    
    /**
     * Lấy lịch làm việc theo mã nhân viên
     */
    public List<LichLamViec> getByMaNhanVien(String maNhanVien) {
        List<LichLamViec> list = new ArrayList<>();
        String sql = """
            SELECT ll.*, nv.hoTen as TenNhanVien
            FROM LichLamViec ll
            LEFT JOIN NhanVien nv ON ll.MaNhanVien = nv.MaNhanVien
            WHERE ll.MaNhanVien = ?
            ORDER BY ll.NgayLamViec DESC
        """;
        
        try {
            ResultSet rs = XJdbc.executeQuery(sql, maNhanVien);
            while (rs.next()) {
                LichLamViec lich = new LichLamViec();
                lich.setMaLich(rs.getInt("MaLich"));
                lich.setMaNhanVien(rs.getString("MaNhanVien"));
                lich.setTenNhanVien(rs.getString("TenNhanVien"));
                lich.setNgayLamViec(rs.getDate("NgayLamViec"));
                lich.setCaLamViec(rs.getString("CaLamViec"));
                lich.setGhiChu(rs.getString("GhiChu"));
                lich.setTrangThai(rs.getInt("TrangThai"));
                list.add(lich);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    /**
     * Lấy tất cả lịch làm việc
     */
    public List<LichLamViec> getAll() {
        List<LichLamViec> list = new ArrayList<>();
        String sql = """
            SELECT ll.*, nv.hoTen as TenNhanVien
            FROM LichLamViec ll
            LEFT JOIN NhanVien nv ON ll.MaNhanVien = nv.MaNhanVien
            ORDER BY ll.NgayLamViec DESC
        """;
        
        try {
            ResultSet rs = XJdbc.executeQuery(sql);
            while (rs.next()) {
                LichLamViec lich = new LichLamViec();
                lich.setMaLich(rs.getInt("MaLich"));
                lich.setMaNhanVien(rs.getString("MaNhanVien"));
                lich.setTenNhanVien(rs.getString("TenNhanVien"));
                lich.setNgayLamViec(rs.getDate("NgayLamViec"));
                lich.setCaLamViec(rs.getString("CaLamViec"));
                lich.setGhiChu(rs.getString("GhiChu"));
                lich.setTrangThai(rs.getInt("TrangThai"));
                list.add(lich);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    /**
     * Thêm lịch làm việc
     */
    public int insert(LichLamViec lich) {
        String sql = """
            INSERT INTO LichLamViec (MaNhanVien, NgayLamViec, CaLamViec, GhiChu, TrangThai)
            VALUES (?, ?, ?, ?, ?)
        """;
        return XJdbc.executeUpdate(sql, 
            lich.getMaNhanVien(),
            lich.getNgayLamViec(),
            lich.getCaLamViec(),
            lich.getGhiChu(),
            lich.getTrangThai()
        );
    }
    
    /**
     * Cập nhật lịch làm việc
     */
    public int update(LichLamViec lich) {
        String sql = """
            UPDATE LichLamViec 
            SET MaNhanVien = ?, NgayLamViec = ?, CaLamViec = ?, GhiChu = ?, TrangThai = ?
            WHERE MaLich = ?
        """;
        return XJdbc.executeUpdate(sql,
            lich.getMaNhanVien(),
            lich.getNgayLamViec(),
            lich.getCaLamViec(),
            lich.getGhiChu(),
            lich.getTrangThai(),
            lich.getMaLich()
        );
    }
    
    /**
     * Xóa lịch làm việc
     */
    public int delete(int maLich) {
        String sql = "DELETE FROM LichLamViec WHERE MaLich = ?";
        return XJdbc.executeUpdate(sql, maLich);
    }
}
