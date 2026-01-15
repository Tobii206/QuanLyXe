/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietXeDaoImpl;
import dao.XeDaoImpl;
import entity.ChiTietXe;
import entity.Xe;

/**
 *
 * @author Nguyen Ngoc
 */
public class ChiTietXePL extends javax.swing.JPanel {
    private ChiTietXeDaoImpl repo = new ChiTietXeDaoImpl();
    private XeDaoImpl xeDao = new XeDaoImpl();
    private DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates new form ChiTietXePL
     */
    public ChiTietXePL() {
        initComponents();
        initData();
        loadData();
    };

    private void initData() {
        model = (DefaultTableModel) tblChiTietXePorscher.getModel();
    }

    private void loadData() {
        loadComboBoxData();
        fillToTable();
    }
     private void loadComboBoxData() {
        try {
            System.out.println("=== Load ComboBox Xe ===");
            List<Xe> listXe = xeDao.findAllActive();
            System.out.println("Số lượng Xe: " + (listXe != null ? listXe.size() : "null"));
            
            cboChonXe.removeAllItems();
            cboSoSanhVoiXeKhac.removeAllItems();
            
            cboChonXe.addItem("Tất cả");
            cboSoSanhVoiXeKhac.addItem("Chọn xe so sánh");
            
            if (listXe != null) {
                for (Xe xe : listXe) {
                    System.out.println("  - Xe: " + xe.getTen());
                    cboChonXe.addItem(xe.getTen());
                    cboSoSanhVoiXeKhac.addItem(xe.getTen());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }

    private void fillToTable() {
        try {
            model.setRowCount(0);
            System.out.println("=== Bắt đầu load dữ liệu ChiTietXe ===");
            
            List<ChiTietXe> listChiTietXe = repo.findAllAvailableChiTietXe();
            System.out.println("Số lượng ChiTietXe: " + (listChiTietXe != null ? listChiTietXe.size() : "null"));
            
            if (listChiTietXe == null || listChiTietXe.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có dữ liệu xe trong database!");
                return;
            }
            
            for (ChiTietXe ct : listChiTietXe) {
                System.out.println("Load xe ID: " + ct.getIdXe());
                Xe xe = xeDao.findById(ct.getIdXe());
                
                Object[] row = {
                    ct.getId(),
                    xe != null ? xe.getTen() : "N/A",
                    "Động cơ " + ct.getIdDongCo(),
                    ct.getNamSX(),
                    ct.getTinhTrangXe(),
                    String.format("%,.0f VND", ct.getGiaBan()),
                    ct.getSoLuongTon(),
                    ct.getMoTa()
                };
                model.addRow(row);
            }
            System.out.println("=== Hoàn tất load " + listChiTietXe.size() + " xe ===");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải bảng: " + e.getMessage());
        }
    }

    private void fillToTableByXe(int idXe) {
        try {
            model.setRowCount(0);
            List<ChiTietXe> listChiTietXe = repo.findByXe(idXe);
            
            for (ChiTietXe ct : listChiTietXe) {
                Xe xe = xeDao.findById(ct.getIdXe());
                
                Object[] row = {
                    ct.getId(),
                    xe != null ? xe.getTen() : "N/A",
                    "Động cơ " + ct.getIdDongCo(),
                    ct.getNamSX(),
                    ct.getTinhTrangXe(),
                    String.format("%,.0f VND", ct.getGiaBan()),
                    ct.getSoLuongTon(),
                    ct.getMoTa()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi lọc dữ liệu: " + e.getMessage());
        }
    }

    private void showComparison(String tenXe1, String tenXe2) {
    try {
        StringBuilder comparison = new StringBuilder();
        comparison.append("SO SÁNH CHI TIẾT\n");
        comparison.append("====================================\n\n");
        
        // Tìm thông tin xe từ bảng ChiTietXe
        List<ChiTietXe> xe1List = repo.findByTenXe(tenXe1);
        List<ChiTietXe> xe2List = repo.findByTenXe(tenXe2);
        
        if (xe1List.isEmpty() || xe2List.isEmpty()) {
            comparison.append("Không tìm thấy đủ thông tin để so sánh\n");
            comparison.append("- Xe 1: ").append(tenXe1).append(xe1List.isEmpty() ? " (Không có dữ liệu)" : "").append("\n");
            comparison.append("- Xe 2: ").append(tenXe2).append(xe2List.isEmpty() ? " (Không có dữ liệu)" : "");
            txtChiTietXeSoSanh.setText(comparison.toString());
            return;
        }
        
        ChiTietXe xe1 = xe1List.get(0);
        ChiTietXe xe2 = xe2List.get(0);
        
        // Lấy thông tin xe từ bảng Xe
        Xe thongTinXe1 = xeDao.findByTen(tenXe1);
        Xe thongTinXe2 = xeDao.findByTen(tenXe2);
        
        // So sánh chi tiết
        comparison.append(tenXe1.toUpperCase()).append("\n");
        comparison.append("------------------------------------\n");
        comparison.append("- Tên xe: ").append(thongTinXe1 != null ? thongTinXe1.getTen() : "N/A").append("\n");
        comparison.append("- Động cơ ID: ").append(xe1.getIdDongCo()).append("\n");
        comparison.append("- Năm SX: ").append(xe1.getNamSX()).append("\n");
        comparison.append("- Tình trạng: ").append(xe1.getTinhTrangXe()).append("\n");
        comparison.append("- Giá bán: ").append(String.format("%,.0f VND", xe1.getGiaBan())).append("\n");
        comparison.append("- Số lượng: ").append(xe1.getSoLuongTon()).append("\n");
        comparison.append("- Mô tả: ").append(xe1.getMoTa()).append("\n\n");
        
        comparison.append(tenXe2.toUpperCase()).append("\n");
        comparison.append("------------------------------------\n");
        comparison.append("- Tên xe: ").append(thongTinXe2 != null ? thongTinXe2.getTen() : "N/A").append("\n");
        comparison.append("- Động cơ ID: ").append(xe2.getIdDongCo()).append("\n");
        comparison.append("- Năm SX: ").append(xe2.getNamSX()).append("\n");
        comparison.append("- Tình trạng: ").append(xe2.getTinhTrangXe()).append("\n");
        comparison.append("- Giá bán: ").append(String.format("%,.0f VND", xe2.getGiaBan())).append("\n");
        comparison.append("- Số lượng: ").append(xe2.getSoLuongTon()).append("\n");
        comparison.append("- Mô tả: ").append(xe2.getMoTa()).append("\n\n");
        
        // Phân tích so sánh đơn giản
        comparison.append("PHÂN TÍCH SO SÁNH\n");
        comparison.append("------------------------------------\n");
        
        // So sánh giá
        if (xe1.getGiaBan() > xe2.getGiaBan()) {
            double chenhLech = xe1.getGiaBan() - xe2.getGiaBan();
            comparison.append("- Giá bán: ").append(tenXe2).append(" rẻ hơn ")
                     .append(String.format("%,.0f VND", chenhLech)).append("\n");
        } else if (xe1.getGiaBan() < xe2.getGiaBan()) {
            double chenhLech = xe2.getGiaBan() - xe1.getGiaBan();
            comparison.append("- Giá bán: ").append(tenXe1).append(" rẻ hơn ")
                     .append(String.format("%,.0f VND", chenhLech)).append("\n");
        } else {
            comparison.append("- Giá bán: Hai xe có giá ngang nhau\n");
        }
        
        // So sánh năm sản xuất
        if (xe1.getNamSX() > xe2.getNamSX()) {
            comparison.append("- Năm SX: ").append(tenXe1).append(" mới hơn ").append(xe1.getNamSX() - xe2.getNamSX()).append(" năm\n");
        } else if (xe1.getNamSX() < xe2.getNamSX()) {
            comparison.append("- Năm SX: ").append(tenXe2).append(" mới hơn ").append(xe2.getNamSX() - xe1.getNamSX()).append(" năm\n");
        } else {
            comparison.append("- Năm SX: Hai xe cùng năm sản xuất\n");
        }
        
        // So sánh số lượng
        if (xe1.getSoLuongTon() > xe2.getSoLuongTon()) {
            comparison.append("- Số lượng: ").append(tenXe1).append(" có sẵn nhiều hơn\n");
        } else if (xe1.getSoLuongTon() < xe2.getSoLuongTon()) {
            comparison.append("- Số lượng: ").append(tenXe2).append(" có sẵn nhiều hơn\n");
        } else {
            comparison.append("- Số lượng: Hai xe có số lượng tồn bằng nhau\n");
        }
        
        txtChiTietXeSoSanh.setText(comparison.toString());
        
    } catch (Exception e) {
        txtChiTietXeSoSanh.setText("Lỗi khi so sánh: " + e.getMessage());
    }
}

    private void showSelectedCarDetail() {
        int selectedRow = tblChiTietXePorscher.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) model.getValueAt(selectedRow, 0);
            try {
                ChiTietXe chiTietXe = repo.findById(id);
                if (chiTietXe != null) {
                    Xe xe = xeDao.findById(chiTietXe.getIdXe());
                    
                    StringBuilder detail = new StringBuilder();
                    detail.append("CHI TIẾT XE: ").append(xe != null ? xe.getTen() : "N/A").append("\n\n");
                    detail.append("Thông số kỹ thuật:\n");
                    detail.append("- ID: ").append(chiTietXe.getId()).append("\n");
                    detail.append("- Năm sản xuất: ").append(chiTietXe.getNamSX()).append("\n");
                    detail.append("- Tình trạng: ").append(chiTietXe.getTinhTrangXe()).append("\n");
                    detail.append("- Giá bán: ").append(String.format("%,.0f VND", chiTietXe.getGiaBan())).append("\n");
                    detail.append("- Số lượng tồn: ").append(chiTietXe.getSoLuongTon()).append("\n");
                    detail.append("- Mô tả: ").append(chiTietXe.getMoTa()).append("\n");
                    
                    txtChiTietXeSoSanh.setText(detail.toString());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị chi tiết: " + e.getMessage());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cboChonXe = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboSoSanhVoiXeKhac = new javax.swing.JComboBox<>();
        btnRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTietXePorscher = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtChiTietXeSoSanh = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 153, 153));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("CHI TIẾT XE PORSCHE");

        cboChonXe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboChonXe(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Chọn xe:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("So sánh với xe khác:");

        cboSoSanhVoiXeKhac.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSoSanhVoiXeKhac(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        tblChiTietXePorscher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Tên xe", "Động cơ", "NSX", "Tình trạng", "Giá bán", "Số lượng", "Mô tả"
            }
        ));
        tblChiTietXePorscher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietXePorscherMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblChiTietXePorscher);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("CHI TIẾT XE SO SÁNH");

        txtChiTietXeSoSanh.setColumns(20);
        txtChiTietXeSoSanh.setRows(5);
        jScrollPane2.setViewportView(txtChiTietXeSoSanh);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboChonXe, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboSoSanhVoiXeKhac, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(352, 352, 352))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(315, 315, 315))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboChonXe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cboSoSanhVoiXeKhac, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboChonXe(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboChonXe
         if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String selected = (String) cboChonXe.getSelectedItem();
            if ("Tất cả".equals(selected)) {
                fillToTable();
            } else {
                List<Xe> listXe = xeDao.findAllActive();
                if (listXe != null) {
                    for (Xe xe : listXe) {
                        if (xe.getTen().equals(selected)) {
                            fillToTableByXe(xe.getId());
                            break;
                        }
                    }
                }
            }
        }
            
                 
    }//GEN-LAST:event_cboChonXe

    private void cboSoSanhVoiXeKhac(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSoSanhVoiXeKhac
         if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String selectedCompare = (String) cboSoSanhVoiXeKhac.getSelectedItem();
            String selectedMain = (String) cboChonXe.getSelectedItem();

            if (!"Chọn xe so sánh".equals(selectedCompare)
                && selectedMain != null && !"Tất cả".equals(selectedMain)) {
                showComparison(selectedMain, selectedCompare);
            } else {
                txtChiTietXeSoSanh.setText("Vui lòng chọn xe chính và xe để so sánh.");
            }
        }
    }//GEN-LAST:event_cboSoSanhVoiXeKhac

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadData();
        JOptionPane.showMessageDialog(this, "Đã làm mới dữ liệu!");
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void tblChiTietXePorscherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietXePorscherMouseClicked
        if (evt.getClickCount() == 1) { // Click đơn
            //showSelectedCarDetail();
        }
    }//GEN-LAST:event_tblChiTietXePorscherMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> cboChonXe;
    private javax.swing.JComboBox<String> cboSoSanhVoiXeKhac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblChiTietXePorscher;
    private javax.swing.JTextArea txtChiTietXeSoSanh;
    // End of variables declaration//GEN-END:variables
}
