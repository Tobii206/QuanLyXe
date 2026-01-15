package dao;

import entity.Xe;
import java.util.List;

public interface XeDao {
    List<Xe> findAllActive();
    Xe create(Xe entity);
    void update(Xe entity);
    void deleteById(Integer id);
    List<Xe> findAll();
    Xe findById(Integer id);
    Xe findByTen(String tenXe);
}
