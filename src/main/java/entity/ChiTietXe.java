/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Nguyen Ngoc
 */
public class ChiTietXe {
    private int id;
    private int idXe;
    private int idMauXe;
    private int idDongCo;
    private int namSX;
    private String moTa;
    private int soLuongTon;
    private double giaNhap;
    private double giaBan;
    private String tinhTrangXe;

    public ChiTietXe() {
    }

    public ChiTietXe(int id, int idXe, int idMauXe, int idDongCo, int namSX, String moTa, int soLuongTon, double giaNhap, double giaBan, String tinhTrangXe) {
        this.id = id;
        this.idXe = idXe;
        this.idMauXe = idMauXe;
        this.idDongCo = idDongCo;
        this.namSX = namSX;
        this.moTa = moTa;
        this.soLuongTon = soLuongTon;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.tinhTrangXe = tinhTrangXe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdXe() {
        return idXe;
    }

    public void setIdXe(int idXe) {
        this.idXe = idXe;
    }

    public int getIdMauXe() {
        return idMauXe;
    }

    public void setIdMauXe(int idMauXe) {
        this.idMauXe = idMauXe;
    }

    public int getIdDongCo() {
        return idDongCo;
    }

    public void setIdDongCo(int idDongCo) {
        this.idDongCo = idDongCo;
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

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
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
