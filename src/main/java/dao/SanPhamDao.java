package dao;

import entity.SanPham;
import java.util.List;
import uitl.XQuery;

/**
 *
 * @author Admin
 */
public class SanPhamDao {
    String getAllSql = "SELECT * FROM SanPham";
    public List<SanPham> getAll(){
    return XQuery.getBeanList(SanPham.class, getAllSql);
    }
    String findByIdsql = "SELECT * FROM SanPham WHERE MaSanPham = ?";
    public SanPham findById(String MaSanPham){
    return XQuery.getSingleBean(SanPham.class, findByIdsql, MaSanPham);
    }
}
