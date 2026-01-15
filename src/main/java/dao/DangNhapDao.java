/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.KhachHang;
import entity.NhanVien;
import java.sql.ResultSet;
import uitl.XJdbc;

/**
 * DAO xử lý đăng nhập cho cả Nhân viên và Khách hàng
 */
public class DangNhapDao {
    
    /**
     * Kiểm tra đăng nhập Nhân viên
     */
    public static boolean checkLoginNhanVien(String user, String pass) {
        String sql = """
            SELECT nv.MaNhanVien, nv.hoTen
            FROM NhanVien nv
            JOIN DangNhap dn ON nv.MaNhanVien = dn.MaNhanVien
            WHERE (dn.TaiKhoan = ? OR nv.Email = ?) AND dn.MatKhau = ?
        """;
        try {
            ResultSet rs = XJdbc.executeQuery(sql, user, user, pass);
            boolean found = rs.next(); 
            rs.getStatement().getConnection().close(); 
            return found;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Kiểm tra đăng nhập Khách hàng (dùng email)
     */
    public static boolean checkLoginKhachHang(String email, String matKhau) {
        String sql = "SELECT * FROM KhachHang WHERE Email = ? AND TrangThai = 0";
        try {
            ResultSet rs = XJdbc.executeQuery(sql, email);
            boolean found = rs.next();
            rs.getStatement().getConnection().close();
            // Lưu ý: Trong thực tế nên mã hóa mật khẩu
            // Hiện tại chỉ kiểm tra email tồn tại
            return found;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Kiểm tra đăng nhập tổng hợp (tự động phát hiện Nhân viên hoặc Khách hàng)
     */
    public static boolean checkLogin(String user, String pass) {
        return checkLoginNhanVien(user, pass) || checkLoginKhachHang(user, pass);
    }

    /**
     * Lấy thông tin Nhân viên theo username hoặc email
     */
    public NhanVien getNhanVienByUsername(String username) {
        NhanVien nv = null;
        String sql = """
            SELECT nv.MaNhanVien, nv.hoTen, nv.Email, nv.SoDienThoai, nv.GioiTinh, nv.MaChucVu, nv.TrangThai
            FROM NhanVien nv
            LEFT JOIN DangNhap dn ON nv.MaNhanVien = dn.MaNhanVien
            WHERE dn.TaiKhoan = ? OR nv.Email = ?
        """;
        try {
            ResultSet rs = XJdbc.executeQuery(sql, username, username);
            if (rs.next()) {
                nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setHoTen(rs.getString("hoTen"));
                nv.setEmail(rs.getString("Email"));
                nv.setSoDienThoai(rs.getString("SoDienThoai"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setMaChucVu(rs.getString("MaChucVu"));
                nv.setTrangThai(rs.getInt("TrangThai"));
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
    }
    
    /**
     * Lấy thông tin Khách hàng theo email
     */
    public KhachHang getKhachHangByEmail(String email) {
        KhachHang kh = null;
        String sql = "SELECT * FROM KhachHang WHERE Email = ?";
        try {
            ResultSet rs = XJdbc.executeQuery(sql, email);
            if (rs.next()) {
                kh = new KhachHang();
                kh.setMaKhachHang(rs.getString("MaKhachHang"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                kh.setEmail(rs.getString("Email"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setTrangThai(rs.getInt("TrangThai"));
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kh;
    }
    
    /**
     * Xác định loại người dùng (NHANVIEN hoặc KHACHHANG)
     */
    public String getUserType(String username) {
        // Kiểm tra Nhân viên trước
        String sql = """
            SELECT COUNT(*) as cnt FROM NhanVien nv
            LEFT JOIN DangNhap dn ON nv.MaNhanVien = dn.MaNhanVien
            WHERE dn.TaiKhoan = ? OR nv.Email = ?
        """;
        try {
            ResultSet rs = XJdbc.executeQuery(sql, username, username);
            if (rs.next() && rs.getInt("cnt") > 0) {
                rs.getStatement().getConnection().close();
                return "NHANVIEN";
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Kiểm tra Khách hàng
        sql = "SELECT COUNT(*) as cnt FROM KhachHang WHERE Email = ?";
        try {
            ResultSet rs = XJdbc.executeQuery(sql, username);
            if (rs.next() && rs.getInt("cnt") > 0) {
                rs.getStatement().getConnection().close();
                return "KHACHHANG";
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Đổi mật khẩu cho Nhân viên
     */
    public boolean changePasswordNhanVien(String username, String oldPassword, String newPassword) {
        // Kiểm tra mật khẩu cũ
        if (!checkLoginNhanVien(username, oldPassword)) {
            return false;
        }
        
        String sql = """
            UPDATE DangNhap 
            SET MatKhau = ? 
            WHERE MaNhanVien = (
                SELECT nv.MaNhanVien FROM NhanVien nv
                LEFT JOIN DangNhap dn ON nv.MaNhanVien = dn.MaNhanVien
                WHERE dn.TaiKhoan = ? OR nv.Email = ?
            )
        """;
        try {
            int result = XJdbc.executeUpdate(sql, newPassword, username, username);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Đổi mật khẩu cho Khách hàng (nếu có bảng DangNhap cho KH)
     * Hiện tại KH chưa có mật khẩu trong DB, method này để dự phòng
     */
    public boolean changePasswordKhachHang(String email, String oldPassword, String newPassword) {
        // TODO: Implement khi có bảng lưu mật khẩu cho Khách hàng
        return false;
    }
}