package dao;

import entity.PhieuDatXe;
import uitl.XJdbc;
import uitl.XQuery;
import java.util.List;

public class PhieuDatXeDao {
    
    public List<PhieuDatXe> getAll() {
        String sql = "SELECT * FROM PhieuDatXe ORDER BY NgayDat DESC";
        return XQuery.getBeanList(PhieuDatXe.class, sql);
    }
    
    public PhieuDatXe findById(String maPhieuDat) {
        String sql = "SELECT * FROM PhieuDatXe WHERE MaPhieuDat = ?";
        List<PhieuDatXe> list = XQuery.getBeanList(PhieuDatXe.class, sql, maPhieuDat);
        return list.isEmpty() ? null : list.get(0);
    }
    
    public List<PhieuDatXe> findByTienDoNhap(String tienDoNhap) {
        String sql = "SELECT * FROM PhieuDatXe WHERE TienDoNhap = ? ORDER BY NgayDat DESC";
        return XQuery.getBeanList(PhieuDatXe.class, sql, tienDoNhap);
    }
    
    public List<PhieuDatXe> findByKhachHang(String maKhachHang) {
        String sql = "SELECT * FROM PhieuDatXe WHERE MaKhachHang = ? ORDER BY NgayDat DESC";
        return XQuery.getBeanList(PhieuDatXe.class, sql, maKhachHang);
    }
    
    public PhieuDatXe findByMaXe(String maXe) {
        String sql = "SELECT * FROM PhieuDatXe WHERE MaXe = ?";
        List<PhieuDatXe> list = XQuery.getBeanList(PhieuDatXe.class, sql, maXe);
        return list.isEmpty() ? null : list.get(0);
    }
    
    public boolean insert(PhieuDatXe phieuDat) {
        String sql = "INSERT INTO PhieuDatXe (MaPhieuDat, MaKhachHang, MaNhanVien, MaSanPham, " +
                    "MaMauSac, MaKichThuoc, YeuCauCauHinh, GiaTriDuKien, TienDatCoc, NgayDat, " +
                    "NgayDuKienVe, TienDoNhap, MaXe, GhiChu) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int rows = XJdbc.executeUpdate(sql, 
            phieuDat.getMaPhieuDat(),
            phieuDat.getMaKhachHang(),
            phieuDat.getMaNhanVien(),
            phieuDat.getMaSanPham(),
            phieuDat.getMaMauSac(),
            phieuDat.getMaKichThuoc(),
            phieuDat.getYeuCauCauHinh(),
            phieuDat.getGiaTriDuKien(),
            phieuDat.getTienDatCoc(),
            phieuDat.getNgayDat(),
            phieuDat.getNgayDuKienVe(),
            phieuDat.getTienDoNhap(),
            phieuDat.getMaXe(),
            phieuDat.getGhiChu()
        );
        return rows > 0;
    }
    
    public boolean update(PhieuDatXe phieuDat) {
        String sql = "UPDATE PhieuDatXe SET MaKhachHang = ?, MaNhanVien = ?, MaSanPham = ?, " +
                    "MaMauSac = ?, MaKichThuoc = ?, YeuCauCauHinh = ?, GiaTriDuKien = ?, " +
                    "TienDatCoc = ?, NgayDat = ?, NgayDuKienVe = ?, TienDoNhap = ?, " +
                    "MaXe = ?, GhiChu = ? WHERE MaPhieuDat = ?";
        int rows = XJdbc.executeUpdate(sql,
            phieuDat.getMaKhachHang(),
            phieuDat.getMaNhanVien(),
            phieuDat.getMaSanPham(),
            phieuDat.getMaMauSac(),
            phieuDat.getMaKichThuoc(),
            phieuDat.getYeuCauCauHinh(),
            phieuDat.getGiaTriDuKien(),
            phieuDat.getTienDatCoc(),
            phieuDat.getNgayDat(),
            phieuDat.getNgayDuKienVe(),
            phieuDat.getTienDoNhap(),
            phieuDat.getMaXe(),
            phieuDat.getGhiChu(),
            phieuDat.getMaPhieuDat()
        );
        return rows > 0;
    }
    
    public boolean updateTienDoNhap(String maPhieuDat, String tienDoNhap) {
        String sql = "UPDATE PhieuDatXe SET TienDoNhap = ? WHERE MaPhieuDat = ?";
        int rows = XJdbc.executeUpdate(sql, tienDoNhap, maPhieuDat);
        return rows > 0;
    }
    
    public boolean updateMaXe(String maPhieuDat, String maXe) {
        String sql = "UPDATE PhieuDatXe SET MaXe = ?, TienDoNhap = N'Hoàn thành' WHERE MaPhieuDat = ?";
        int rows = XJdbc.executeUpdate(sql, maXe, maPhieuDat);
        return rows > 0;
    }
    
    public boolean delete(String maPhieuDat) {
        String sql = "DELETE FROM PhieuDatXe WHERE MaPhieuDat = ?";
        int rows = XJdbc.executeUpdate(sql, maPhieuDat);
        return rows > 0;
    }
    
    public String getNewMaPhieuDat() {
        String sql = "SELECT TOP 1 MaPhieuDat FROM PhieuDatXe ORDER BY MaPhieuDat DESC";
        List<PhieuDatXe> list = XQuery.getBeanList(PhieuDatXe.class, sql);
        if (list.isEmpty()) {
            return "PDX0001";
        }
        String lastMa = list.get(0).getMaPhieuDat();
        int num = Integer.parseInt(lastMa.substring(3)) + 1;
        return String.format("PDX%04d", num);
    }
    
    // Thống kê phiếu đặt theo tiến độ
    public int countByTienDo(String tienDoNhap) {
        String sql = "SELECT COUNT(*) as total FROM PhieuDatXe WHERE TienDoNhap = ?";
        try {
            return XJdbc.executeQuery(sql, tienDoNhap).getInt("total");
        } catch (Exception e) {
            return 0;
        }
    }
}
