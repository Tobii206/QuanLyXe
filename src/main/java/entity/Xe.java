/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Nguyen Ngoc
 */
public class Xe {
    private int id;
    private String ten;
    private String moTa;
    private String hangXe;
    private int trangThai;
    private java.util.Date ngayTao;

    public Xe() {
    }

    public Xe(int id, String ten, String moTa, String hangXe, int trangThai) {
        this.id = id;
        this.ten = ten;
        this.moTa = moTa;
        this.hangXe = hangXe;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHangXe() {
        return hangXe;
    }

    public void setHangXe(String hangXe) {
        this.hangXe = hangXe;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public java.util.Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(java.util.Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    
}
