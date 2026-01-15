package dao;

import entity.HoaDonCho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import uitl.XJdbc;
import uitl.XQuery;

/**
 *
 * @author Admin
 */
public class HoaDonChoDao {
    String getAllSql = """
    SELECT hd.*, nv.hoTen AS TenNhanVien
    FROM HoaDon hd
    INNER JOIN NhanVien nv ON nv.MaNhanVien = hd.MaNhanVien
""";

    public List<HoaDonCho> getAll(){
        return XQuery.getBeanList(HoaDonCho.class, getAllSql);
    }

    String insertSql = """
                       INSERT INTO [dbo].[HoaDon]
                                  ([MaHoaDon]
                       		   ,[NgayTao]
                       		   ,[MaNhanVien]
                                  ,[MaKhachHang]
                                  ,[TrangThaiKH]
                                  ,[TongTien])
                            VALUES
                                  (?,?,?,?,?,?)
                       """;
    public int insert(HoaDonCho cho){
        Object[] values = {
            cho.getMaHoaDon(),
            cho.getNgayTao(),
            cho.getMaNhanVien(),
            cho.getMaKhachHang(),
            cho.isTrangThaiKH(),
            cho.getTongTien()
        };
        return XJdbc.executeUpdate(insertSql, values);
    }

    public String taoMaHoaDonTuTang() {
        String sql = "SELECT MAX(RIGHT(MaHoaDon, 4)) FROM HoaDon";
        try (Connection conn = XJdbc.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            int so = 0;
            if (rs.next() && rs.getString(1) != null) {
                so = Integer.parseInt(rs.getString(1));
            }
            return String.format("HD%04d", so + 1);
        } catch (Exception e) {
            e.printStackTrace();
            return "HD0001";
        }
    }

    public static void main(String[] args) {
        HoaDonChoDao dao = new HoaDonChoDao();
        List<HoaDonCho> lst = dao.getAll();
        for (HoaDonCho cho : lst) {
            System.out.println(cho.toString());
        }
    }

    String findByMaKhachHangSql = """
    SELECT hd.*, nv.hoTen AS TenNhanVien, kh.TenKhachHang
    FROM HoaDon hd
    INNER JOIN NhanVien nv ON nv.MaNhanVien = hd.MaNhanVien
    INNER JOIN KhachHang kh ON kh.MaKhachHang = hd.MaKhachHang
    WHERE hd.MaKhachHang = ?
""";

    public List<HoaDonCho> findByMaKhachHang(String maKH) {
        return XQuery.getBeanList(HoaDonCho.class, findByMaKhachHangSql, maKH);
    }
}
