package view;

import dao.*;
import entity.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import uitl.XJdbc;

/**
 * BanHangView - Màn hình bán hàng
 */
public class BanHangView extends JInternalFrame {

    private KhachHangDao khachHangDao = new KhachHangDao();
    private SanPhamDao sanPhamDao = new SanPhamDao();
    private HoaDonDao hoaDonDao = new HoaDonDao();
    private ChiTietHoaDonDao chiTietHoaDonDao = new ChiTietHoaDonDao();
    private PhieuGiamGiaDAO khuyenMaiDao = new PhieuGiamGiaDAO();
    
    private List<BanHang> lsBanHang = new ArrayList<>();
    private List<SanPham> lstSanPham = new ArrayList<>();
    private List<PhieuGiamGia> danhSachKhuyenMai = new ArrayList<>();
    
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

    public BanHangView() {
        initComponents();
        setTitle("Bán Hàng");
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        
        // Load dữ liệu ban đầu
        loadData();
        setupEventHandlers();
    }

    private void loadData() {
        fillToTableDanhSachSP();
        loadKhuyenMai();
        generateMaHoaDon();
    }

    private void setupEventHandlers() {
        // Double click để chọn sản phẩm
        tblDanhSachSp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    chonSanPham();
                }
            }
        });
        
        // Click để chọn khách hàng
        btnChonKhachHang.addActionListener(e -> chonKhachHang());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        btnChonKhachHang = new javax.swing.JButton();
        
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        btnTao = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cboKhuyenMai = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cboLoaiThanhToan = new javax.swing.JComboBox<>();
        btnThanhToan = new javax.swing.JButton();
        
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSachSp = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiemSam = new javax.swing.JButton();
        btnChonSanPham = new javax.swing.JButton();
        
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnXoaSanPham = new javax.swing.JButton();
        btnXoaTatCa = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);

        // Panel thông tin khách hàng
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));
        jLabel1.setText("Mã khách hàng:");
        jLabel2.setText("Tên khách hàng:");
        jLabel3.setText("Số điện thoại:");
        btnChonKhachHang.setText("Chọn Khách Hàng");

        // Panel thông tin hóa đơn
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin hóa đơn"));
        jLabel4.setText("Mã hóa đơn:");
        btnTao.setText("Tạo");
        btnTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoActionPerformed(evt);
            }
        });
        
        jLabel5.setText("Tổng tiền:");
        jLabel6.setText("Khuyến mãi:");
        jLabel7.setText("Giảm giá:");
        jLabel8.setText("Thành tiền:");
        jLabel14.setText("Loại thanh toán:");
        
        cboLoaiThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trả hết", "Đặt cọc" }));
        cboLoaiThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiThanhToanActionPerformed(evt);
            }
        });
        
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        // Panel danh sách sản phẩm
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));
        tblDanhSachSp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Mã CTSP", "Tên sản phẩm", "Giá", "Số lượng"}
        ));
        jScrollPane1.setViewportView(tblDanhSachSp);
        
        btnTimKiemSam.setText("Tìm kiếm sản phẩm");
        btnChonSanPham.setText("Chọn sản phẩm");
        btnChonSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonSanPhamActionPerformed(evt);
            }
        });

        // Panel giỏ hàng
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));
        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Mã CTSP", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"}
        ));
        jScrollPane2.setViewportView(tblGioHang);
        
        btnXoaSanPham.setText("Xóa sản phẩm");
        btnXoaTatCa.setText("Xóa tất cả");
        btnXoaTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTatCaActionPerformed(evt);
            }
        });

        // Layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        // Layout cho các panel con
        setupPanelLayouts();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setupPanelLayouts() {
        // Layout cho jPanel1 (Thông tin khách hàng)
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaKhachHang)
                    .addComponent(txtTenKhachHang)
                    .addComponent(txtSDT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChonKhachHang)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonKhachHang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        // Layout cho jPanel2 (Thông tin hóa đơn)
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTao))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTongTien)
                            .addComponent(cboKhuyenMai, 0, 150, Short.MAX_VALUE)
                            .addComponent(txtGiamGia)
                            .addComponent(txtThanhTien)
                            .addComponent(cboLoaiThanhToan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnThanhToan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cboLoaiThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThanhToan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        // Layout cho jPanel3 (Danh sách sản phẩm)
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnTimKiemSam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChonSanPham)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiemSam)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonSanPham))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addContainerGap())
        );

        // Layout cho jPanel4 (Giỏ hàng)
        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnXoaSanPham)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaTatCa)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaSanPham)
                    .addComponent(btnXoaTatCa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addContainerGap())
        );
    }

    private void btnTaoActionPerformed(java.awt.event.ActionEvent evt) {
        generateMaHoaDon();
    }

    private void btnChonSanPhamActionPerformed(java.awt.event.ActionEvent evt) {
        chonSanPham();
    }

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {
        thanhToan();
    }

    private void btnXoaTatCaActionPerformed(java.awt.event.ActionEvent evt) {
        if (lsBanHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng đang trống!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá toàn bộ giỏ hàng?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            lsBanHang.clear();
            fillToTableGioHang();
            tinhTongTien();
        }
    }

    private void cboLoaiThanhToanActionPerformed(java.awt.event.ActionEvent evt) {
        String loaiThanhToan = (String) cboLoaiThanhToan.getSelectedItem();
        
        // Khi chọn "Đặt cọc", tự động tính 20% từ tổng tiền
        if ("Đặt cọc".equals(loaiThanhToan)) {
            try {
                String tongTienText = txtTongTien.getText().replace(",", "").replace(".", "").trim();
                if (!tongTienText.isEmpty()) {
                    double tongTien = Double.parseDouble(tongTienText);
                    
                    // Tính giảm giá nếu có
                    String giamGiaText = txtGiamGia.getText().replace(",", "").replace(".", "").trim();
                    double tienGiam = giamGiaText.isEmpty() ? 0 : Double.parseDouble(giamGiaText);
                    
                    // Tổng tiền sau giảm giá
                    double tongTienSauGiam = tongTien - tienGiam;
                    
                    // Tính 20% làm tiền cọc
                    double tienCoc = tongTienSauGiam * 0.2;
                    
                    // Hiển thị tiền cọc trong txtThanhTien
                    txtThanhTien.setText(String.format("%,.0f", tienCoc));
                }
            } catch (NumberFormatException ex) {
                // Nếu lỗi thì không làm gì
            }
        } else if ("Trả hết".equals(loaiThanhToan)) {
            // Khi chọn "Trả hết", hiển thị toàn bộ số tiền sau giảm giá
            try {
                String tongTienText = txtTongTien.getText().replace(",", "").replace(".", "").trim();
                if (!tongTienText.isEmpty()) {
                    double tongTien = Double.parseDouble(tongTienText);
                    
                    // Tính giảm giá nếu có
                    String giamGiaText = txtGiamGia.getText().replace(",", "").replace(".", "").trim();
                    double tienGiam = giamGiaText.isEmpty() ? 0 : Double.parseDouble(giamGiaText);
                    
                    // Tổng tiền sau giảm giá
                    double thanhTien = tongTien - tienGiam;
                    
                    // Hiển thị toàn bộ số tiền trong txtThanhTien
                    txtThanhTien.setText(String.format("%,.0f", thanhTien));
                }
            } catch (NumberFormatException ex) {
                // Nếu lỗi thì không làm gì
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonKhachHang;
    private javax.swing.JButton btnChonSanPham;
    private javax.swing.JButton btnTao;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTimKiemSam;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.JButton btnXoaTatCa;
    private javax.swing.JComboBox<String> cboKhuyenMai;
    private javax.swing.JComboBox<String> cboLoaiThanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblDanhSachSp;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

    // Các method xử lý logic
    private void fillToTableDanhSachSP() {
        DefaultTableModel model = (DefaultTableModel) tblDanhSachSp.getModel();
        model.setRowCount(0);
        
        // Load sản phẩm từ database
        try {
            lstSanPham = sanPhamDao.getAll();
            for (SanPham sp : lstSanPham) {
                Object[] data = {
                    sp.getMaSanPham(),
                    sp.getTenSanPham(),
                    String.format("%,.0f", sp.getDonGia()),
                    sp.getSoLuong()
                };
                model.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillToTableGioHang() {
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);
        
        for (BanHang bh : lsBanHang) {
            Object[] data = {
                bh.getMaChiTietSP(),
                bh.getTenSanPham(),
                bh.getSoLuong(),
                String.format("%,.0f", bh.getDonGia()),
                String.format("%,.0f", bh.getThanhTien())
            };
            model.addRow(data);
        }
    }

    private void chonKhachHang() {
        ChonKhachHang dialog = new ChonKhachHang(null, true);
        dialog.setVisible(true);
        
        if (dialog.selectedKhachHang != null) {
            KhachHang kh = dialog.selectedKhachHang;
            txtMaKhachHang.setText(kh.getMaKhachHang());
            txtTenKhachHang.setText(kh.getTenKhachHang());
            txtSDT.setText(kh.getSoDienThoai());
        }
    }

    private void chonSanPham() {
        int selectedRow = tblDanhSachSp.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!");
            return;
        }
        
        String maSP = tblDanhSachSp.getValueAt(selectedRow, 0).toString();
        String tenSP = tblDanhSachSp.getValueAt(selectedRow, 1).toString();
        String giaStr = tblDanhSachSp.getValueAt(selectedRow, 2).toString().replace(",", "");
        double gia = Double.parseDouble(giaStr);
        
        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        boolean found = false;
        for (BanHang bh : lsBanHang) {
            if (bh.getMaChiTietSP().equals(maSP)) {
                bh.setSoLuong(bh.getSoLuong() + 1);
                bh.setThanhTien(bh.getSoLuong() * bh.getDonGia());
                found = true;
                break;
            }
        }
        
        if (!found) {
            BanHang bh = new BanHang();
            bh.setMaChiTietSP(maSP);
            bh.setTenSanPham(tenSP);
            bh.setSoLuong(1);
            bh.setDonGia(gia);
            bh.setThanhTien(gia);
            lsBanHang.add(bh);
        }
        
        fillToTableGioHang();
        tinhTongTien();
    }

    private void tinhTongTien() {
        double tongTien = 0;
        for (BanHang bh : lsBanHang) {
            tongTien += bh.getThanhTien();
        }
        txtTongTien.setText(String.format("%,.0f", tongTien));
        
        // Tự động tính thành tiền dựa trên loại thanh toán
        cboLoaiThanhToanActionPerformed(null);
    }

    private void loadKhuyenMai() {
        try {
            danhSachKhuyenMai = khuyenMaiDao.getAll();
            cboKhuyenMai.removeAllItems();
            cboKhuyenMai.addItem("Không áp dụng");
            
            for (PhieuGiamGia km : danhSachKhuyenMai) {
                cboKhuyenMai.addItem(km.getTenPhieu());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateMaHoaDon() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String maHD = "HD" + timestamp.substring(timestamp.length() - 6);
        txtMaHoaDon.setText(maHD);
    }

    private void thanhToan() {
        if (lsBanHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng trống!");
            return;
        }
        
        if (txtMaKhachHang.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng!");
            return;
        }
        
        try {
            // Tạo hóa đơn
            HoaDon hd = new HoaDon();
            hd.setMaHoaDon(txtMaHoaDon.getText());
            hd.setMaKhachHang(txtMaKhachHang.getText());
            hd.setMaNhanVien("NV001"); // Tạm thời hardcode
            
            double tongTien = Double.parseDouble(txtTongTien.getText().replace(",", ""));
            double thanhTien = Double.parseDouble(txtThanhTien.getText().replace(",", ""));
            
            hd.setTongTien((long) tongTien);
            hd.setNgayTao(new java.sql.Date(System.currentTimeMillis()));
            
            // Xác định trạng thái dựa trên loại thanh toán
            String loaiThanhToan = (String) cboLoaiThanhToan.getSelectedItem();
            if ("Đặt cọc".equals(loaiThanhToan)) {
                hd.setTrangThai(0); // Chưa thanh toán
                double tienCoc = thanhTien;
                // Lưu tiền cọc vào database nếu có cột TienTraTruoc
            } else {
                hd.setTrangThai(1); // Đã thanh toán
            }
            
            // Lưu hóa đơn
            hoaDonDao.insert(hd);
            
            // Lưu chi tiết hóa đơn
            for (BanHang bh : lsBanHang) {
                ChiTietHoaDon cthd = new ChiTietHoaDon();
                cthd.setMaChiTietHoaDon("CH" + System.currentTimeMillis());
                cthd.setMaHoaDon(txtMaHoaDon.getText());
                cthd.setMaChiTietSP(bh.getMaChiTietSP());
                cthd.setSoLuong(bh.getSoLuong());
                cthd.setDonGia(BigDecimal.valueOf(bh.getDonGia()));
                
                chiTietHoaDonDao.insert(cthd);
            }
            
            // Thông báo thành công
            if ("Đặt cọc".equals(loaiThanhToan)) {
                double tienCoc = thanhTien;
                double conLai = tongTien - tienCoc;
                JOptionPane.showMessageDialog(this,
                        String.format("Đã nhận đặt cọc 20%%: %,.0f VND\n" +
                                "Tổng tiền: %,.0f VND\n" +
                                "Còn lại: %,.0f VND\n" +
                                "Hóa đơn vẫn ở trạng thái chưa thanh toán.",
                                tienCoc, tongTien, conLai));
            } else {
                JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
            }
            
            // Reset form
            clearForm();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi thanh toán: " + e.getMessage());
        }
    }

    private void clearForm() {
        txtMaKhachHang.setText("");
        txtTenKhachHang.setText("");
        txtSDT.setText("");
        txtTongTien.setText("");
        txtGiamGia.setText("");
        txtThanhTien.setText("");
        lsBanHang.clear();
        fillToTableGioHang();
        generateMaHoaDon();
        cboKhuyenMai.setSelectedIndex(0);
        cboLoaiThanhToan.setSelectedIndex(0);
    }
}