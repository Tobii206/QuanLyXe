/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 * Session quản lý thông tin người dùng đăng nhập
 * Hỗ trợ cả Nhân viên và Khách hàng
 */
public class Session {
    // Thông tin chung
    public static String userId;
    public static String userName;
    public static String userType; // "NHANVIEN" hoặc "KHACHHANG"
    public static String role; // "QUANLY", "SALES", "KETOAN", "HAUMAI", "KHACHHANG"
    
    // Đối tượng người dùng
    private static NhanVien currentNhanVien;
    private static KhachHang currentKhachHang;
    
    // Tương thích với code cũ
    public static String MaNhanVien;
    public static String TenNhanVien;

    // Đăng nhập Nhân viên
    public static void setCurrentUser(NhanVien nv) {
        currentNhanVien = nv;
        currentKhachHang = null;
        if (nv != null) {
            userId = nv.getMaNhanVien();
            userName = nv.getHoTen();
            userType = "NHANVIEN";
            role = getRoleFromChucVu(nv.getMaChucVu());
            
            // Tương thích code cũ
            MaNhanVien = nv.getMaNhanVien();
            TenNhanVien = nv.getHoTen();
        }
    }
    
    // Đăng nhập Khách hàng
    public static void setCurrentKhachHang(KhachHang kh) {
        currentKhachHang = kh;
        currentNhanVien = null;
        if (kh != null) {
            userId = kh.getMaKhachHang();
            userName = kh.getTenKhachHang();
            userType = "KHACHHANG";
            role = "KHACHHANG";
            
            // Tương thích code cũ
            MaNhanVien = null;
            TenNhanVien = null;
        }
    }
    
    // Lấy role từ mã chức vụ
    private static String getRoleFromChucVu(String maChucVu) {
        if (maChucVu == null) return "NHANVIEN";
        switch (maChucVu) {
            case "CV001": return "QUANLY";
            case "CV002": return "SALES";
            case "CV003": return "KETOAN";
            case "CV004": return "HAUMAI";
            default: return "NHANVIEN";
        }
    }
    
    // Kiểm tra quyền
    public static boolean isQuanLy() {
        return "QUANLY".equals(role);
    }
    
    public static boolean isNhanVien() {
        return "NHANVIEN".equals(userType);
    }
    
    public static boolean isKhachHang() {
        return "KHACHHANG".equals(userType);
    }
    
    // Getter
    public static NhanVien getCurrentUser() {
        return currentNhanVien;
    }
    
    public static NhanVien getCurrentNhanVien() {
        return currentNhanVien;
    }
    
    public static KhachHang getCurrentKhachHang() {
        return currentKhachHang;
    }
    
    // Đăng xuất
    public static void logout() {
        currentNhanVien = null;
        currentKhachHang = null;
        userId = null;
        userName = null;
        userType = null;
        role = null;
        MaNhanVien = null;
        TenNhanVien = null;
    }
}
