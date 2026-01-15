package dao;

import entity.LichSuXe;
import uitl.XJdbc;
import uitl.XQuery;
import java.util.List;

public class LichSuXeDao {
    
    public List<LichSuXe> getAll() {
        String sql = "SELECT * FROM LichSuXe ORDER BY NgayGhiNhan DESC";
        return XQuery.getBeanList(LichSuXe.class, sql);
    }
    
    public LichSuXe findById(Integer maLichSu) {
        String sql = "SELECT * FROM LichSuXe WHERE MaLichSu = ?";
        List<LichSuXe> list = XQuery.getBeanList(LichSuXe.class, sql, maLichSu);
        return list.isEmpty() ? null : list.get(0);
    }
    
    public List<LichSuXe> findByMaXe(String maXe) {
        String sql = "SELECT * FROM LichSuXe WHERE MaXe = ? ORDER BY NgayGhiNhan DESC";
        return XQuery.getBeanList(LichSuXe.class, sql, maXe);
    }
    
    public List<LichSuXe> findByHanhDong(String hanhDong) {
        String sql = "SELECT * FROM LichSuXe WHERE HanhDong = ? ORDER BY NgayGhiNhan DESC";
        return XQuery.getBeanList(LichSuXe.class, sql, hanhDong);
    }
    
    public boolean insert(LichSuXe lichSu) {
        String sql = "INSERT INTO LichSuXe (MaXe, HanhDong, NguoiThucHien, ChiTiet, ChiPhi) " +
                    "VALUES (?, ?, ?, ?, ?)";
        int rows = XJdbc.executeUpdate(sql,
            lichSu.getMaXe(),
            lichSu.getHanhDong(),
            lichSu.getNguoiThucHien(),
            lichSu.getChiTiet(),
            lichSu.getChiPhi()
        );
        return rows > 0;
    }
    
    public boolean delete(Integer maLichSu) {
        String sql = "DELETE FROM LichSuXe WHERE MaLichSu = ?";
        int rows = XJdbc.executeUpdate(sql, maLichSu);
        return rows > 0;
    }
    
    // Ghi nhận hành động nhanh
    public boolean ghiNhanHanhDong(String maXe, String hanhDong, String nguoiThucHien, String chiTiet) {
        LichSuXe lichSu = new LichSuXe();
        lichSu.setMaXe(maXe);
        lichSu.setHanhDong(hanhDong);
        lichSu.setNguoiThucHien(nguoiThucHien);
        lichSu.setChiTiet(chiTiet);
        lichSu.setChiPhi(java.math.BigDecimal.ZERO);
        return insert(lichSu);
    }
}
