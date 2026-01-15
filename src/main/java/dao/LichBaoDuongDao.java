package dao;

import entity.LichBaoDuong;
import uitl.XJdbc;
import uitl.XQuery;
import java.util.List;

public class LichBaoDuongDao {
    
    public List<LichBaoDuong> getAll() {
        String sql = "SELECT * FROM LichBaoDuong ORDER BY NgayBaoDuong DESC";
        return XQuery.getBeanList(LichBaoDuong.class, sql);
    }
    
    public LichBaoDuong findById(String maLichBD) {
        String sql = "SELECT * FROM LichBaoDuong WHERE MaLichBD = ?";
        List<LichBaoDuong> list = XQuery.getBeanList(LichBaoDuong.class, sql, maLichBD);
        return list.isEmpty() ? null : list.get(0);
    }
    
    public List<LichBaoDuong> findByKhachHang(String maKhachHang) {
        String sql = "SELECT * FROM LichBaoDuong WHERE MaKhachHang = ? ORDER BY NgayBaoDuong DESC";
        return XQuery.getBeanList(LichBaoDuong.class, sql, maKhachHang);
    }
    
    public List<LichBaoDuong> findByMaXe(String maXe) {
        String sql = "SELECT * FROM LichBaoDuong WHERE MaXe = ? ORDER BY NgayBaoDuong DESC";
        return XQuery.getBeanList(LichBaoDuong.class, sql, maXe);
    }
    
    public List<LichBaoDuong> findByTrangThai(String trangThai) {
        String sql = "SELECT * FROM LichBaoDuong WHERE TrangThai = ? ORDER BY NgayBaoDuong DESC";
        return XQuery.getBeanList(LichBaoDuong.class, sql, trangThai);
    }
    
    public List<LichBaoDuong> findByNgay(java.sql.Date tuNgay, java.sql.Date denNgay) {
        String sql = "SELECT * FROM LichBaoDuong WHERE NgayBaoDuong BETWEEN ? AND ? ORDER BY NgayBaoDuong";
        return XQuery.getBeanList(LichBaoDuong.class, sql, tuNgay, denNgay);
    }
    
    public boolean insert(LichBaoDuong lichBD) {
        String sql = "INSERT INTO LichBaoDuong (MaLichBD, MaKhachHang, MaXe, BienSoXe, " +
                    "NgayBaoDuong, LoaiDichVu, NoiDung, ChiPhi, TrangThai, NhanVienThucHien, GhiChu) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int rows = XJdbc.executeUpdate(sql,
            lichBD.getMaLichBD(),
            lichBD.getMaKhachHang(),
            lichBD.getMaXe(),
            lichBD.getBienSoXe(),
            lichBD.getNgayBaoDuong(),
            lichBD.getLoaiDichVu(),
            lichBD.getNoiDung(),
            lichBD.getChiPhi(),
            lichBD.getTrangThai(),
            lichBD.getNhanVienThucHien(),
            lichBD.getGhiChu()
        );
        return rows > 0;
    }
    
    public boolean update(LichBaoDuong lichBD) {
        String sql = "UPDATE LichBaoDuong SET MaKhachHang = ?, MaXe = ?, BienSoXe = ?, " +
                    "NgayBaoDuong = ?, LoaiDichVu = ?, NoiDung = ?, ChiPhi = ?, " +
                    "TrangThai = ?, NhanVienThucHien = ?, GhiChu = ? WHERE MaLichBD = ?";
        int rows = XJdbc.executeUpdate(sql,
            lichBD.getMaKhachHang(),
            lichBD.getMaXe(),
            lichBD.getBienSoXe(),
            lichBD.getNgayBaoDuong(),
            lichBD.getLoaiDichVu(),
            lichBD.getNoiDung(),
            lichBD.getChiPhi(),
            lichBD.getTrangThai(),
            lichBD.getNhanVienThucHien(),
            lichBD.getGhiChu(),
            lichBD.getMaLichBD()
        );
        return rows > 0;
    }
    
    public boolean updateTrangThai(String maLichBD, String trangThai) {
        String sql = "UPDATE LichBaoDuong SET TrangThai = ? WHERE MaLichBD = ?";
        int rows = XJdbc.executeUpdate(sql, trangThai, maLichBD);
        return rows > 0;
    }
    
    public boolean delete(String maLichBD) {
        String sql = "DELETE FROM LichBaoDuong WHERE MaLichBD = ?";
        int rows = XJdbc.executeUpdate(sql, maLichBD);
        return rows > 0;
    }
    
    public String getNewMaLichBD() {
        String sql = "SELECT TOP 1 MaLichBD FROM LichBaoDuong ORDER BY MaLichBD DESC";
        List<LichBaoDuong> list = XQuery.getBeanList(LichBaoDuong.class, sql);
        if (list.isEmpty()) {
            return "LBD0001";
        }
        String lastMa = list.get(0).getMaLichBD();
        int num = Integer.parseInt(lastMa.substring(3)) + 1;
        return String.format("LBD%04d", num);
    }
    
    // Lấy lịch bảo dưỡng sắp tới (trong vòng 7 ngày)
    public List<LichBaoDuong> getLichSapToi() {
        String sql = "SELECT * FROM LichBaoDuong " +
                    "WHERE TrangThai = N'Đã đặt lịch' " +
                    "AND NgayBaoDuong BETWEEN GETDATE() AND DATEADD(day, 7, GETDATE()) " +
                    "ORDER BY NgayBaoDuong";
        return XQuery.getBeanList(LichBaoDuong.class, sql);
    }
    
    // Thống kê số lịch theo trạng thái
    public int countByTrangThai(String trangThai) {
        String sql = "SELECT COUNT(*) as total FROM LichBaoDuong WHERE TrangThai = ?";
        try {
            return XJdbc.executeQuery(sql, trangThai).getInt("total");
        } catch (Exception e) {
            return 0;
        }
    }
}
