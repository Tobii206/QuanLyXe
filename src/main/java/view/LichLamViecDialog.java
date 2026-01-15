package view;

import dao.LichLamViecDAO;
import entity.LichLamViec;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import com.toedter.calendar.JDateChooser;
import java.util.Date;

/**
 * Dialog hiển thị và quản lý lịch làm việc của nhân viên
 */
public class LichLamViecDialog extends JDialog {
    
    private JTable tblLich;
    private DefaultTableModel model;
    private LichLamViecDAO dao;
    private String maNhanVien;
    private String tenNhanVien;
    
    // Form fields
    private JDateChooser dateChooser;
    private JComboBox<String> cboCaLam;
    private JTextField txtGhiChu;
    private JComboBox<String> cboTrangThai;
    
    public LichLamViecDialog(Frame parent, String maNhanVien, String tenNhanVien) {
        super(parent, "Lịch Làm Việc - " + tenNhanVien, true);
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.dao = new LichLamViecDAO();
        initComponents();
        loadData();
        setLocationRelativeTo(parent);
    }
    
    private void initComponents() {
        setSize(900, 600);
        setLayout(new BorderLayout(10, 10));
        
        // Panel form nhập liệu
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin lịch làm việc"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Ngày làm việc
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Ngày:"), gbc);
        gbc.gridx = 1;
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setDate(new Date());
        formPanel.add(dateChooser, gbc);
        
        // Ca làm việc
        gbc.gridx = 2; gbc.gridy = 0;
        formPanel.add(new JLabel("Ca làm:"), gbc);
        gbc.gridx = 3;
        cboCaLam = new JComboBox<>(new String[]{"Sáng", "Chiều", "Tối", "Cả ngày"});
        formPanel.add(cboCaLam, gbc);
        
        // Ghi chú
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Ghi chú:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        txtGhiChu = new JTextField(30);
        formPanel.add(txtGhiChu, gbc);
        
        // Trạng thái
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        formPanel.add(new JLabel("Trạng thái:"), gbc);
        gbc.gridx = 1;
        cboTrangThai = new JComboBox<>(new String[]{"Chưa làm", "Đã làm", "Nghỉ"});
        formPanel.add(cboTrangThai, gbc);
        
        // Buttons
        gbc.gridx = 2; gbc.gridwidth = 2;
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        JButton btnLamMoi = new JButton("Làm mới");
        
        btnThem.addActionListener(e -> themLich());
        btnSua.addActionListener(e -> suaLich());
        btnXoa.addActionListener(e -> xoaLich());
        btnLamMoi.addActionListener(e -> lamMoi());
        
        btnPanel.add(btnThem);
        btnPanel.add(btnSua);
        btnPanel.add(btnXoa);
        btnPanel.add(btnLamMoi);
        formPanel.add(btnPanel, gbc);
        
        add(formPanel, BorderLayout.NORTH);
        
        // Bảng
        String[] columns = {"Mã Lịch", "Ngày", "Ca Làm", "Ghi Chú", "Trạng Thái"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblLich = new JTable(model);
        tblLich.setRowHeight(25);
        tblLich.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fillForm();
            }
        });
        JScrollPane scrollPane = new JScrollPane(tblLich);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel button đóng
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnDong = new JButton("Đóng");
        btnDong.addActionListener(e -> dispose());
        bottomPanel.add(btnDong);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private void loadData() {
        model.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        List<LichLamViec> list = dao.getByMaNhanVien(maNhanVien);
        
        for (LichLamViec lich : list) {
            String trangThai = "";
            switch (lich.getTrangThai()) {
                case 0: trangThai = "Chưa làm"; break;
                case 1: trangThai = "Đã làm"; break;
                case 2: trangThai = "Nghỉ"; break;
            }
            
            model.addRow(new Object[]{
                lich.getMaLich(),
                sdf.format(lich.getNgayLamViec()),
                lich.getCaLamViec(),
                lich.getGhiChu(),
                trangThai
            });
        }
    }
    
    private void fillForm() {
        int row = tblLich.getSelectedRow();
        if (row >= 0) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date ngay = sdf.parse(tblLich.getValueAt(row, 1).toString());
                dateChooser.setDate(ngay);
                cboCaLam.setSelectedItem(tblLich.getValueAt(row, 2).toString());
                txtGhiChu.setText(tblLich.getValueAt(row, 3).toString());
                cboTrangThai.setSelectedItem(tblLich.getValueAt(row, 4).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void lamMoi() {
        dateChooser.setDate(new Date());
        cboCaLam.setSelectedIndex(0);
        txtGhiChu.setText("");
        cboTrangThai.setSelectedIndex(0);
        tblLich.clearSelection();
    }
    
    private void themLich() {
        if (dateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày!");
            return;
        }
        
        LichLamViec lich = new LichLamViec();
        lich.setMaNhanVien(maNhanVien);
        lich.setNgayLamViec(new java.sql.Date(dateChooser.getDate().getTime()));
        lich.setCaLamViec(cboCaLam.getSelectedItem().toString());
        lich.setGhiChu(txtGhiChu.getText());
        lich.setTrangThai(cboTrangThai.getSelectedIndex());
        
        int result = dao.insert(lich);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Thêm lịch thành công!");
            loadData();
            lamMoi();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm lịch thất bại!");
        }
    }
    
    private void suaLich() {
        int row = tblLich.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn lịch cần sửa!");
            return;
        }
        
        if (dateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày!");
            return;
        }
        
        int maLich = Integer.parseInt(tblLich.getValueAt(row, 0).toString());
        
        LichLamViec lich = new LichLamViec();
        lich.setMaLich(maLich);
        lich.setMaNhanVien(maNhanVien);
        lich.setNgayLamViec(new java.sql.Date(dateChooser.getDate().getTime()));
        lich.setCaLamViec(cboCaLam.getSelectedItem().toString());
        lich.setGhiChu(txtGhiChu.getText());
        lich.setTrangThai(cboTrangThai.getSelectedIndex());
        
        int result = dao.update(lich);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Sửa lịch thành công!");
            loadData();
            lamMoi();
        } else {
            JOptionPane.showMessageDialog(this, "Sửa lịch thất bại!");
        }
    }
    
    private void xoaLich() {
        int row = tblLich.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn lịch cần xóa!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc muốn xóa lịch này?", 
            "Xác nhận", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            int maLich = Integer.parseInt(tblLich.getValueAt(row, 0).toString());
            int result = dao.delete(maLich);
            
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Xóa lịch thành công!");
                loadData();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa lịch thất bại!");
            }
        }
    }
}
