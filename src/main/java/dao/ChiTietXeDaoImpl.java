/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.ChiTietXeDao;
import entity.ChiTietXe;
import uitl.XQuery;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nguyen Ngoc
 */
public class ChiTietXeDaoImpl implements ChiTietXeDao {

    @Override
    public List<ChiTietXe> findByXe(int idXe) {
        String sql = "SELECT * FROM ChiTietXe WHERE IdXe = ? AND SoLuongTon > 0";
        return XQuery.getBeanList(ChiTietXe.class, sql, idXe);
    }

    @Override
    public List<ChiTietXe> findAllAvailableChiTietXe() {
        String sql = "SELECT ct.* FROM ChiTietXe ct JOIN Xe x ON ct.IdXe = x.Id WHERE ct.SoLuongTon > 0 AND x.TrangThai = 1";
        return XQuery.getBeanList(ChiTietXe.class, sql);
    }

    @Override
    public ChiTietXe create(ChiTietXe entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(ChiTietXe entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChiTietXe> findAll() {
        String sql = "SELECT * FROM ChiTietXe";
        return XQuery.getBeanList(ChiTietXe.class, sql);
    }

    @Override
    public ChiTietXe findById(Integer id) {
        String sql = "SELECT * FROM ChiTietXe WHERE Id = ?";
        return XQuery.getSingleBean(ChiTietXe.class, sql, id);
    }

    @Override
    public List<ChiTietXe> findByTenXe(String tenXe) {
        String sql = "SELECT ct.* FROM ChiTietXe ct "
                + "JOIN Xe x ON ct.IdXe = x.Id "
                + "WHERE x.Ten LIKE ? AND ct.SoLuongTon > 0";
        return XQuery.getBeanList(ChiTietXe.class, sql, "%" + tenXe + "%");
    }

    @Override
    public List<Object[]> findForTuVan(double nganSach, String tinhTrang) {
        List<Object[]> list = new ArrayList<>();
        try {
            String sql = "SELECT ct.Id, x.Ten, ct.NamSX, ct.TinhTrangXe, "
                   + "CONCAT('Động cơ ', ct.IdDongCo), ct.PhienBan, ct.GiaBan, ct.MoTa "
                   + "FROM ChiTietXe ct "
                   + "JOIN Xe x ON ct.IdXe = x.Id "
                   + "WHERE ct.GiaBan <= ? AND ct.SoLuongTon > 0 AND x.TrangThai = 1";

            java.sql.ResultSet rs;
            if (!tinhTrang.equals("Tất cả")) {
                sql += " AND ct.TinhTrangXe LIKE ?";
                rs = uitl.XJdbc.executeQuery(sql, nganSach, "%" + tinhTrang + "%");
            } else {
                rs = uitl.XJdbc.executeQuery(sql, nganSach);
            }
            
            while (rs.next()) {
                Object[] row = new Object[8];
                row[0] = rs.getInt(1);      // Id
                row[1] = rs.getString(2);   // Ten xe
                row[2] = rs.getInt(3);      // NamSX
                row[3] = rs.getString(4);   // TinhTrangXe
                row[4] = rs.getString(5);   // Dong co
                row[5] = rs.getString(6);   // PhienBan
                row[6] = rs.getDouble(7);   // GiaBan
                row[7] = rs.getString(8);   // MoTa
                list.add(row);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
 
}
