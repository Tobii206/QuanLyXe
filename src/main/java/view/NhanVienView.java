package view;

//import .Repository_NhanVien;
import Repository.Repository_NhanVien;
import dao.ChucVuDao;
import dao.DangNhapDao;
import dao.NhanVienDao;
import entity.ChucVu;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import entity.NhanVien;

public class NhanVienView extends javax.swing.JInternalFrame {

    private Repository.Repository_NhanVien rp = new Repository_NhanVien();
     private ChucVuDao daoChucVu = new ChucVuDao();
     private NhanVienDao daoNhanVien = new NhanVienDao();
    private DefaultTableModel mol;
    private int i;
    private int t = 1;
    

    public NhanVienView() {
        initComponents();
        txt_maNhanVien.setEditable(false);
txt_maNhanVien.setText(daoNhanVien.getNextMaNhanVien());
        this.fillTable(rp.getByStatus(0));
        this.fillComboLocVT();
        fillComboChucVu();
        this.fillComboTT();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }

    public void fillTable(List<NhanVien> list) {
    mol = (DefaultTableModel) tbl_QLNV.getModel();
    mol.setRowCount(0);
    for (NhanVien nv : list) {
        mol.addRow(new Object[]{
            nv.getMaNhanVien(),
            nv.getHoTen(),
            nv.getGioiTinh(),
            nv.getSoDienThoai(),
            nv.getEmail(),
            daoChucVu.getTenChucVuByMa(nv.getMaChucVu()),
            nv.getTrangThai() == 0 ? "Đang làm" : "Đã nghỉ"
        });
    }
    }

public void fillComboChucVu() {
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
    for (ChucVu cv : daoChucVu.getAll()) {
        model.addElement(cv.getTenChucVu()); // ✅ chỉ tên
    }
    cbo_chucVu.setModel(model);
}

    public void fillComboLocVT() {
        DefaultComboBoxModel cmol = (DefaultComboBoxModel) cboLocCV.getModel();
        cmol.removeAllElements();
        cmol.addElement("Tất cả");
        cmol.addElement("Quản lý");
        cmol.addElement("Nhân viên");
    }

public void fillComboTT() {
    DefaultComboBoxModel cmol = (DefaultComboBoxModel) cbo_trangThai.getModel();
    cmol.removeAllElements();
    cmol.addElement("Đang làm");
    cmol.addElement("Đã nghỉ");
}

private void filterTable(String tenChucVu, String keyword) {
    List<NhanVien> filteredList = new ArrayList<>();
    keyword = keyword.toLowerCase();

    for (NhanVien nv : rp.getByStatus(0)) {
        String tenCV_NV = daoChucVu.getTenChucVuByMa(nv.getMaChucVu());
        boolean matchesChucVu = tenChucVu.equals("Tất cả")
                || tenCV_NV.equalsIgnoreCase(tenChucVu);

        boolean matchesKeyword =
                nv.getMaNhanVien().toLowerCase().contains(keyword)
                || nv.getHoTen().toLowerCase().contains(keyword)
                || nv.getSoDienThoai().toLowerCase().contains(keyword)
                || nv.getEmail().toLowerCase().contains(keyword);

        if (matchesChucVu && matchesKeyword) {
            filteredList.add(nv);
        }
    }

    fillTable(filteredList);
}


public void showData() {
int row = tbl_QLNV.getSelectedRow();
    if (row >= 0) {
        String maNV = tbl_QLNV.getValueAt(row, 0).toString();
        txt_maNhanVien.setText(maNV);
        txt_hoVaTen.setText(tbl_QLNV.getValueAt(row, 1).toString());

        String gioiTinh = tbl_QLNV.getValueAt(row, 2).toString();
        if (gioiTinh.equalsIgnoreCase("Nam")) {
            rdo_Nam.setSelected(true);
        } else {
            rdo_Nu.setSelected(true);
        }

        txt_SoDienThoai.setText(tbl_QLNV.getValueAt(row, 3).toString());
        txt_email.setText(tbl_QLNV.getValueAt(row, 4).toString());

        cbo_chucVu.setSelectedItem(tbl_QLNV.getValueAt(row, 5).toString());
        cbo_trangThai.setSelectedItem(tbl_QLNV.getValueAt(row, 6).toString());
        
        // Load mật khẩu từ bảng DangNhap
        try {
            String sql = "SELECT MatKhau FROM DangNhap WHERE MaNhanVien = ?";
            java.sql.ResultSet rs = uitl.XJdbc.executeQuery(sql, maNV);
            if (rs.next()) {
                txtMatKhau.setText(rs.getString("MatKhau"));
            } else {
                txtMatKhau.setText("");
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            txtMatKhau.setText("");
            e.printStackTrace();
        }
    }
}

    private void clearForm() {
    txt_maNhanVien.setText(daoNhanVien.getNextMaNhanVien()); // Tự sinh mã mới
    txt_hoVaTen.setText("");
    txt_SoDienThoai.setText("");
    txt_email.setText("");
    rdo_Nam.setSelected(true);
    cbo_chucVu.setSelectedIndex(0);
    cbo_trangThai.setSelectedIndex(0);}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btn_sua = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnDoiMatKhau = new javax.swing.JButton();
        btn_them = new javax.swing.JButton();
        rdo_Nu = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btn_xoa = new javax.swing.JButton();
        txt_maNhanVien = new javax.swing.JTextField();
        txt_SoDienThoai = new javax.swing.JTextField();
        cbo_chucVu = new javax.swing.JComboBox<>();
        txt_hoVaTen = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_QLNV = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        rdo_Nam = new javax.swing.JRadioButton();
        cbo_trangThai = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        cboLocCV = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnXemLich = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();

        setResizable(true);
        setPreferredSize(new java.awt.Dimension(1100, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Edit.png"))); // NOI18N
        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_sua, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, 90, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Email:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Số điện thoại:");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        btnDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh.png"))); // NOI18N
        btnDoiMatKhau.setText("Đổi Mật Khẩu");
        btnDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauActionPerformed(evt);
            }
        });
        getContentPane().add(btnDoiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, 200, -1));

        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });
        getContentPane().add(btn_them, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, 200, 60));

        buttonGroup1.add(rdo_Nu);
        rdo_Nu.setText("Nữ");
        rdo_Nu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_NuActionPerformed(evt);
            }
        });
        getContentPane().add(rdo_Nu, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Chức Vụ:");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, -1, -1));

        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });
        getContentPane().add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 160, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Mã nhân viên:");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        btn_xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        btn_xoa.setText("Xóa");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_xoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 100, 100, -1));

        txt_maNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maNhanVienActionPerformed(evt);
            }
        });
        getContentPane().add(txt_maNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 150, -1));

        txt_SoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SoDienThoaiActionPerformed(evt);
            }
        });
        getContentPane().add(txt_SoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 150, -1));

        cbo_chucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_chucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_chucVuActionPerformed(evt);
            }
        });
        getContentPane().add(cbo_chucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 160, -1));

        txt_hoVaTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hoVaTenActionPerformed(evt);
            }
        });
        getContentPane().add(txt_hoVaTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 150, -1));

        tbl_QLNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Họ và tên", "Giới tính", "Số điện thoại", "Email", "Chức Vụ", "Trạng thái"
            }
        ));
        tbl_QLNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_QLNVMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_QLNV);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 950, 330));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Trạng thái:");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, -1, 19));

        buttonGroup1.add(rdo_Nam);
        rdo_Nam.setText("Nam");
        rdo_Nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_NamActionPerformed(evt);
            }
        });
        getContentPane().add(rdo_Nam, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, -1, -1));

        cbo_trangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_trangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_trangThaiActionPerformed(evt);
            }
        });
        getContentPane().add(cbo_trangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 160, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 204, 204), null, null), "Tìm kiếm ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        cboLocCV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocCVActionPerformed(evt);
            }
        });

        jLabel6.setText("Chức Vụ:");

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnTimKiem.setBackground(new java.awt.Color(204, 255, 255));
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        jLabel7.setText("Tìm kiếm: ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(cboLocCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 339, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem)
                .addGap(31, 31, 31))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboLocCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem)
                    .addComponent(jLabel7))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 950, 80));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Họ và tên:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Giới tính:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("Thông tin nhân viên");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, -1, 32));

        btnXemLich.setBackground(new java.awt.Color(153, 153, 153));
        btnXemLich.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXemLich.setText("Lịch làm việc");
        btnXemLich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemLichActionPerformed(evt);
            }
        });
        getContentPane().add(btnXemLich, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 283, 180, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mật khẩu:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, -1, -1));
        getContentPane().add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, 160, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
try {
        int row = tbl_QLNV.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để sửa");
            return;
        }

        String maNV = txt_maNhanVien.getText().trim();
        String hoTen = txt_hoVaTen.getText().trim();
        String sdt = txt_SoDienThoai.getText().trim();
        String email = txt_email.getText().trim();

        if (maNV.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã nhân viên");
            return;
        }

        if (hoTen.isEmpty() || !hoTen.matches("^[\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Tên không hợp lệ. Chỉ được chứa chữ và khoảng trắng.");
            return;
        }

        if (sdt.isEmpty() || !sdt.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Phải đúng 10 chữ số.");
            return;
        }

        if (email.isEmpty() || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng");
            return;
        }

        for (NhanVien nvOld : rp.getAll()) {
            if (!nvOld.getMaNhanVien().equalsIgnoreCase(maNV)) {
                if (nvOld.getSoDienThoai().equals(sdt)) {
                    JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại");
                    return;
                }
                if (nvOld.getEmail().equalsIgnoreCase(email)) {
                    JOptionPane.showMessageDialog(this, "Email đã tồn tại");
                    return;
                }
            }
        }

        String gioiTinh = rdo_Nam.isSelected() ? "Nam" : "Nữ";
        String tenChucVu = cbo_chucVu.getSelectedItem().toString();
String maChucVu = daoChucVu.getMaChucVuByTen(tenChucVu);
        int trangThai = cbo_trangThai.getSelectedItem().toString().equals("Đang làm") ? 0 : 1;

        NhanVien nv = new NhanVien(
                maNV, hoTen, gioiTinh, sdt, email, maChucVu, trangThai
        );

        if (rp.update(nv) > 0) {
            fillTable(rp.getAll());
            JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công");
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thất bại");
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi hệ thống, không thể cập nhật nhân viên");
    }
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed
        try {
            int row = tbl_QLNV.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để đổi mật khẩu");
                return;
            }
            
            String maNV = tbl_QLNV.getValueAt(row, 0).toString();
            String matKhauMoi = txtMatKhau.getText().trim();
            
            if (matKhauMoi.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu mới!");
                txtMatKhau.requestFocus();
                return;
            }
            
            if (matKhauMoi.length() < 6) {
                JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 6 ký tự!");
                txtMatKhau.requestFocus();
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc muốn đổi mật khẩu cho nhân viên " + maNV + "?",
                "Xác nhận", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                DangNhapDao dao = new DangNhapDao();
                String sql = "UPDATE DangNhap SET MatKhau = ? WHERE MaNhanVien = ?";
                int result = uitl.XJdbc.executeUpdate(sql, matKhauMoi, maNV);
                
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công!");
                    txtMatKhau.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Đổi mật khẩu thất bại!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        try {
        String maNV = daoNhanVien.getNextMaNhanVien().trim();
        String hoTen = txt_hoVaTen.getText().trim();
        String sdt = txt_SoDienThoai.getText().trim();
        String email = txt_email.getText().trim();

        if (maNV.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã nhân viên");
            return;
        }

        if (hoTen.isEmpty() || !hoTen.matches("^[\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Tên không hợp lệ. Chỉ được chứa chữ và khoảng trắng.");
            return;
        }

        if (sdt.isEmpty() || !sdt.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Phải đúng 10 chữ số.");
            return;
        }

        if (email.isEmpty() || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng");
            return;
        }

        for (NhanVien nvOld : rp.getAll()) {
            if (nvOld.getMaNhanVien().equalsIgnoreCase(maNV)) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại");
                return;
            }
            if (nvOld.getSoDienThoai().equals(sdt)) {
                JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại");
                return;
            }
            if (nvOld.getEmail().equalsIgnoreCase(email)) {
                JOptionPane.showMessageDialog(this, "Email đã tồn tại");
                return;
            }
        }

        String gioiTinh = rdo_Nam.isSelected() ? "Nam" : "Nữ";
        String tenChucVu = cbo_chucVu.getSelectedItem().toString();
        String maChucVu = daoChucVu.getMaChucVuByTen(tenChucVu); // đổi tên -> mã
        int trangThai = cbo_trangThai.getSelectedItem().toString().equals("Đang làm") ? 0 : 1;

        NhanVien nv = new NhanVien(
                maNV, hoTen, gioiTinh, sdt, email, maChucVu, trangThai
        );

        if (rp.add(nv) > 0) {
            fillTable(rp.getAll());
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi thêm nhân viên");
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi hệ thống, không thể thêm nhân viên");
    }
    }//GEN-LAST:event_btn_themActionPerformed

    private void rdo_NuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_NuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_NuActionPerformed

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        try {
            int row = tbl_QLNV.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa");
                return;
            }

            String maNV = tbl_QLNV.getValueAt(row, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn chuyển nhân viên này thành nghỉ việc?", "Xác nhận", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {

                if (rp.delete(maNV)) {
                    JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công");
                    fillTable(rp.getAll());
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên để xóa");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống, không thể xóa nhân viên");
        }
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void txt_maNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maNhanVienActionPerformed

    private void txt_SoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SoDienThoaiActionPerformed

    private void cbo_chucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_chucVuActionPerformed

    }//GEN-LAST:event_cbo_chucVuActionPerformed

    private void txt_hoVaTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hoVaTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hoVaTenActionPerformed

    private void tbl_QLNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_QLNVMouseClicked
        i = tbl_QLNV.getSelectedRow();
        showData();
    }//GEN-LAST:event_tbl_QLNVMouseClicked

    private void rdo_NamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_NamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_NamActionPerformed

    private void cbo_trangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_trangThaiActionPerformed

    }//GEN-LAST:event_cbo_trangThaiActionPerformed

    private void cboLocCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocCVActionPerformed

    }//GEN-LAST:event_cboLocCVActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String keyword = txtTimKiem.getText().trim();
        String vaiTro = cboLocCV.getSelectedItem() != null ? cboLocCV.getSelectedItem().toString() : "";
        filterTable(vaiTro, keyword);
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnXemLichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemLichActionPerformed
        int row = tbl_QLNV.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để xem lịch làm việc!");
            return;
        }
        
        String maNV = tbl_QLNV.getValueAt(row, 0).toString();
        String tenNV = tbl_QLNV.getValueAt(row, 1).toString();
        
        // Mở dialog lịch làm việc
        LichLamViecDialog dialog = new LichLamViecDialog(null, maNV, tenNV);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnXemLichActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoiMatKhau;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXemLich;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboLocCV;
    private javax.swing.JComboBox<String> cbo_chucVu;
    private javax.swing.JComboBox<String> cbo_trangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdo_Nam;
    private javax.swing.JRadioButton rdo_Nu;
    private javax.swing.JTable tbl_QLNV;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txt_SoDienThoai;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_hoVaTen;
    private javax.swing.JTextField txt_maNhanVien;
    // End of variables declaration//GEN-END:variables
}
