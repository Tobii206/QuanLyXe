/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Nguyen Ngoc
 */
public class ChiTietXeViewModel {
    private int id;
    private String tenXe;        
    private String loaiDongCo;   
    private int namSX;
    private String moTa;
    private int soLuongTon;
    private double giaBan;
    private String tinhTrangXe;

    public ChiTietXeViewModel() {
    }

    public ChiTietXeViewModel(int id, String tenXe, String loaiDongCo, int namSX, String moTa, int soLuongTon, double giaBan, String tinhTrangXe) {
        this.id = id;
        this.tenXe = tenXe;
        this.loaiDongCo = loaiDongCo;
        this.namSX = namSX;
        this.moTa = moTa;
        this.soLuongTon = soLuongTon;
        this.giaBan = giaBan;
        this.tinhTrangXe = tinhTrangXe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public String getLoaiDongCo() {
        return loaiDongCo;
    }

    public void setLoaiDongCo(String loaiDongCo) {
        this.loaiDongCo = loaiDongCo;
    }

    public int getNamSX() {
        return namSX;
    }

    public void setNamSX(int namSX) {
        this.namSX = namSX;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getTinhTrangXe() {
        return tinhTrangXe;
    }

    public void setTinhTrangXe(String tinhTrangXe) {
        this.tinhTrangXe = tinhTrangXe;
    }
    
    
}
