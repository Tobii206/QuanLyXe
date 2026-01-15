package dao;

import entity.ChiTietXe;
import java.util.List;

public interface ChiTietXeDao {
    List<ChiTietXe> findByXe(int idXe);
    List<ChiTietXe> findAllAvailableChiTietXe();
    ChiTietXe create(ChiTietXe entity);
    void update(ChiTietXe entity);
    void deleteById(Integer id);
    List<ChiTietXe> findAll();
    ChiTietXe findById(Integer id);
    List<ChiTietXe> findByTenXe(String tenXe);
    List<Object[]> findForTuVan(double nganSach, String tinhTrang);
}
