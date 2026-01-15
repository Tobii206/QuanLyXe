/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.XeDao;
import entity.Xe;
import uitl.XQuery;

import java.util.List;

/**
 *
 * @author Nguyen Ngoc
 */
public class XeDaoImpl implements XeDao {

    @Override
    public List<Xe> findAllActive() {
        String sqlFindAllActive = "SELECT * FROM Xe WHERE TrangThai = 1";
        return XQuery.getBeanList(Xe.class, sqlFindAllActive);
    }

    @Override
    public Xe create(Xe entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Xe entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Xe> findAll() {
        String sqlAll = "SELECT * FROM Xe";
        return XQuery.getBeanList(Xe.class, sqlAll);
    }

    @Override
    public Xe findById(Integer id) {
        String sqlFindById = "SELECT * FROM Xe WHERE Id = ?";
        return XQuery.getSingleBean(Xe.class, sqlFindById, id);
    }

    @Override
    public Xe findByTen(String tenXe) {
        String sql = "SELECT * FROM Xe WHERE Ten LIKE ?";
        return XQuery.getSingleBean(Xe.class, sql, "%" + tenXe + "%");
    }

}
