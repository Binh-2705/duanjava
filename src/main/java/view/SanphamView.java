package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class SanphamView extends JFrame {

    public JTextField txtID, txtMaSP, txtTenSP, txtGiaNhap, txtGiaBan;
    public JTextArea txtMoTa;
    public JComboBox<String> cboDanhMuc, cboDonVi;

    public JButton btnThem, btnSua, btnXoa, btnLamMoi;

    public JTable tblSanPham;
    public DefaultTableModel tableModel;

    public SanphamView() {
        setTitle("Quản lý Sản phẩm");
        setSize(1000, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Font fontLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontButton = new Font("Segoe UI", Font.BOLD, 13);

     
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(230, 248, 245));
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(180, 220, 215)));

        JLabel lblTitle = new JLabel("QUẢN LÝ SẢN PHẨM");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 128, 128));
        pnlHeader.add(lblTitle);

        add(pnlHeader, BorderLayout.NORTH);

      
        JPanel pnlMain = new JPanel(new BorderLayout());
        add(pnlMain, BorderLayout.CENTER);

   
        JPanel pnlLeft = new JPanel(new BorderLayout());
        pnlLeft.setPreferredSize(new Dimension(350, 0));
        pnlLeft.setBorder(new EmptyBorder(10, 15, 10, 15));
        pnlLeft.setBackground(Color.WHITE);

        JPanel pnlForm = new JPanel();
        pnlForm.setLayout(new BoxLayout(pnlForm, BoxLayout.Y_AXIS));
        pnlForm.setBackground(Color.WHITE);

       
        pnlForm.add(createLabel("ID:", fontLabel));
        txtID = createTextField(false);
        pnlForm.add(txtID);

        pnlForm.add(Box.createVerticalStrut(10));

       
        pnlForm.add(createLabel("Mã sản phẩm:", fontLabel));
        txtMaSP = createTextField(true);
        pnlForm.add(txtMaSP);

        pnlForm.add(Box.createVerticalStrut(10));

       
        pnlForm.add(createLabel("Tên sản phẩm:", fontLabel));
        txtTenSP = createTextField(true);
        pnlForm.add(txtTenSP);

        pnlForm.add(Box.createVerticalStrut(10));

    
        pnlForm.add(createLabel("Danh mục:", fontLabel));
        cboDanhMuc = new JComboBox<>();
        cboDanhMuc.setFont(fontLabel);
        cboDanhMuc.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        pnlForm.add(cboDanhMuc);

        pnlForm.add(Box.createVerticalStrut(10));

        // Đơn vị
        pnlForm.add(createLabel("Đơn vị tính:", fontLabel));
        cboDonVi = new JComboBox<>();
        cboDonVi.setFont(fontLabel);
        cboDonVi.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        pnlForm.add(cboDonVi);

        pnlForm.add(Box.createVerticalStrut(10));

        // Giá nhập
        pnlForm.add(createLabel("Giá nhập:", fontLabel));
        txtGiaNhap = createTextField(true);
        pnlForm.add(txtGiaNhap);

        pnlForm.add(Box.createVerticalStrut(10));

        // Giá bán
        pnlForm.add(createLabel("Giá bán:", fontLabel));
        txtGiaBan = createTextField(true);
        pnlForm.add(txtGiaBan);

        pnlForm.add(Box.createVerticalStrut(10));

        // Mô tả
        pnlForm.add(createLabel("Mô tả:", fontLabel));
        txtMoTa = new JTextArea(4, 20);
        txtMoTa.setFont(fontLabel);
        txtMoTa.setLineWrap(true);
        txtMoTa.setWrapStyleWord(true);
        JScrollPane spMoTa = new JScrollPane(txtMoTa);
        spMoTa.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        pnlForm.add(spMoTa);

        pnlLeft.add(pnlForm, BorderLayout.NORTH);

        // Buttons
        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new GridLayout(4, 1, 0, 10));
        pnlButtons.setBackground(Color.WHITE);
        pnlButtons.setBorder(new EmptyBorder(20, 0, 0, 0));

        btnThem = createButton("Thêm", fontButton);
        btnSua = createButton("Sửa", fontButton);
        btnXoa = createButton("Xóa", fontButton);
        btnLamMoi = createButton("Làm mới", fontButton);

        pnlButtons.add(btnThem);
        pnlButtons.add(btnSua);
        pnlButtons.add(btnXoa);
        pnlButtons.add(btnLamMoi);

        pnlLeft.add(pnlButtons, BorderLayout.SOUTH);

        pnlMain.add(pnlLeft, BorderLayout.WEST);

        // ===== TABLE =====
        tableModel = new DefaultTableModel(
            new String[]{"ID", "Mã SP", "Tên SP", "Danh mục", "Đơn vị", "Giá nhập", "Giá bán", "Mô tả"}, 0
        );
        tblSanPham = new JTable(tableModel);
        tblSanPham.setRowHeight(25);
        tblSanPham.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        // Header
        tblSanPham.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblSanPham.getTableHeader().setBackground(new Color(0, 150, 136));
        tblSanPham.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tblSanPham);
        scroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        pnlMain.add(scroll, BorderLayout.CENTER);

        setVisible(true);
    }

    private JLabel createLabel(String text, Font font) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(font);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JTextField createTextField(boolean enable) {
        JTextField txt = new JTextField();
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txt.setEnabled(enable);
        return txt;
    }

    private JButton createButton(String text, Font font) {
        JButton btn = new JButton(text);
        btn.setFont(font);
        btn.setBackground(new Color(0, 150, 136));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(120, 35));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        return btn;
    }
}
