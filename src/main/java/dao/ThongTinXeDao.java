package dao;

import entity.ThongTinXe;
import uitl.XJdbc;
import uitl.XQuery;
import java.util.List;

public class ThongTinXeDao {
    
    public List<ThongTinXe> getAll() {
        String sql = "SELECT * FROM ThongTinXe ORDER BY NgayNhapKho DESC";
        return XQuery.getBeanList(ThongTinXe.class, sql);
    }
    
    public ThongTinXe findById(String maXe) {
        String sql = "SELECT * FROM ThongTinXe WHERE MaXe = ?";
        List<ThongTinXe> list = XQuery.getBeanList(ThongTinXe.class, sql, maXe);
        return list.isEmpty() ? null : list.get(0);
    }
    
    public List<ThongTinXe> findByTinhTrang(String tinhTrang) {
        String sql = "SELECT * FROM ThongTinXe WHERE TinhTrang = ? ORDER BY NgayNhapKho DESC";
        return XQuery.getBeanList(ThongTinXe.class, sql, tinhTrang);
    }
    
    public List<ThongTinXe> findBySanPham(String maSanPham) {
        String sql = "SELECT * FROM ThongTinXe WHERE MaSanPham = ? ORDER BY NgayNhapKho DESC";
        return XQuery.getBeanList(ThongTinXe.class, sql, maSanPham);
    }
    
    public List<ThongTinXe> findBySoKhung(String soKhung) {
        String sql = "SELECT * FROM ThongTinXe WHERE SoKhung LIKE ?";
        return XQuery.getBeanList(ThongTinXe.class, sql, "%" + soKhung + "%");
    }
    
    public boolean insert(ThongTinXe xe) {
        String sql = "INSERT INTO ThongTinXe (MaXe, MaSanPham, MaMauSac, MaKichThuoc, " +
                    "SoKhung, SoMay, BienSo, NamSanXuat, CauHinhChiTiet, GiaNhap, GiaBan, " +
                    "TinhTrang, NgayNhapKho, GhiChu) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int rows = XJdbc.executeUpdate(sql,
            xe.getMaXe(),
            xe.getMaSanPham(),
            xe.getMaMauSac(),
            xe.getMaKichThuoc(),
            xe.getSoKhung(),
            xe.getSoMay(),
            xe.getBienSo(),
            xe.getNamSanXuat(),
            xe.getCauHinhChiTiet(),
            xe.getGiaNhap(),
            xe.getGiaBan(),
            xe.getTinhTrang(),
            xe.getNgayNhapKho(),
            xe.getGhiChu()
        );
        return rows > 0;
    }
    
    public boolean update(ThongTinXe xe) {
        String sql = "UPDATE ThongTinXe SET MaSanPham = ?, MaMauSac = ?, MaKichThuoc = ?, " +
                    "SoKhung = ?, SoMay = ?, BienSo = ?, NamSanXuat = ?, CauHinhChiTiet = ?, " +
                    "GiaNhap = ?, GiaBan = ?, TinhTrang = ?, NgayNhapKho = ?, NgayBan = ?, " +
                    "GhiChu = ? WHERE MaXe = ?";
        int rows = XJdbc.executeUpdate(sql,
            xe.getMaSanPham(),
            xe.getMaMauSac(),
            xe.getMaKichThuoc(),
            xe.getSoKhung(),
            xe.getSoMay(),
            xe.getBienSo(),
            xe.getNamSanXuat(),
            xe.getCauHinhChiTiet(),
            xe.getGiaNhap(),
            xe.getGiaBan(),
            xe.getTinhTrang(),
            xe.getNgayNhapKho(),
            xe.getNgayBan(),
            xe.getGhiChu(),
            xe.getMaXe()
        );
        return rows > 0;
    }
    
    public boolean updateTinhTrang(String maXe, String tinhTrang) {
        String sql = "UPDATE ThongTinXe SET TinhTrang = ? WHERE MaXe = ?";
        int rows = XJdbc.executeUpdate(sql, tinhTrang, maXe);
        return rows > 0;
    }
    
    public boolean updateNgayBan(String maXe, java.sql.Date ngayBan) {
        String sql = "UPDATE ThongTinXe SET NgayBan = ?, TinhTrang = N'Đã bán' WHERE MaXe = ?";
        int rows = XJdbc.executeUpdate(sql, ngayBan, maXe);
        return rows > 0;
    }
    
    public boolean delete(String maXe) {
        String sql = "DELETE FROM ThongTinXe WHERE MaXe = ?";
        int rows = XJdbc.executeUpdate(sql, maXe);
        return rows > 0;
    }
    
    public String getNewMaXe() {
        String sql = "SELECT TOP 1 MaXe FROM ThongTinXe ORDER BY MaXe DESC";
        List<ThongTinXe> list = XQuery.getBeanList(ThongTinXe.class, sql);
        if (list.isEmpty()) {
            return "XE00001";
        }
        String lastMa = list.get(0).getMaXe();
        int num = Integer.parseInt(lastMa.substring(2)) + 1;
        return String.format("XE%05d", num);
    }
    
    // Thống kê xe theo tình trạng
    public int countByTinhTrang(String tinhTrang) {
        String sql = "SELECT COUNT(*) as total FROM ThongTinXe WHERE TinhTrang = ?";
        try {
            return XJdbc.executeQuery(sql, tinhTrang).getInt("total");
        } catch (Exception e) {
            return 0;
        }
    }
}
