package dao;

import entity.HoaDonChiTiet;
import java.util.List;
import uitl.XJdbc;
import uitl.XQuery;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietDao {

    String findById = """
                     SELECT ct.*, hd.MaSanPham AS MaSanPham, hd.MaMauSac AS MaMauSac, hd.MaKichThuoc AS MaKichThuoc, sp.MaChatLieu AS MaChatLieu, ct.DonGia AS DonGia, sp.MaXuatSu AS MaXuatSu
                     FROM ChiTietHoaDon ct
                     INNER JOIN ChiTietSanPham hd ON ct.MaChiTietSP = hd.MaChiTietSP
                     INNER JOIN SanPham sp ON sp.MaSanPham = hd.MaSanPham
                     WHERE ct.MaHoaDon = ?
                     """;

    public List<HoaDonChiTiet> findById(String MaHoaDon) {
        return XQuery.getBeanList(HoaDonChiTiet.class, findById, MaHoaDon);
    }
}