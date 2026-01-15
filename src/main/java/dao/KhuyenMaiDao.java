/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.KhuyenMai;
import java.util.List;
import uitl.XJdbc;
import uitl.XQuery;

/**
 *
 * @author Lenovo
 */
public class KhuyenMaiDao {
    private final String GET_ALL_SQL = "SELECT * FROM PhieuGiamGia";

    // Lấy phiếu theo trạng thái active
    private final String GET_ALL_ACTIVE_SQL = "SELECT * FROM PhieuGiamGia WHERE trangThai = 1";

    // Lấy phiếu theo mã
    private final String FIND_BY_ID_SQL = "SELECT * FROM PhieuGiamGia WHERE maPhieu = ?";

    /**
     * Lấy tất cả phiếu
     */
    public List<KhuyenMai> getAll() {
        return XQuery.getBeanList(KhuyenMai.class, GET_ALL_SQL);
    }

    /**
     * Lấy tất cả phiếu đang hoạt động (trangThai = 1)
     */
    public List<KhuyenMai> getAllActive() {
        return XQuery.getBeanList(KhuyenMai.class, GET_ALL_ACTIVE_SQL);
    }

    /**
     * Tìm phiếu theo mã
     */
    public KhuyenMai findById(String maPhieu) {
        List<KhuyenMai> list = XQuery.getBeanList(KhuyenMai.class, FIND_BY_ID_SQL, maPhieu);
        return list.isEmpty() ? null : list.get(0);
    }
}
 
