/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

public class PhieuXuatView extends JFrame {

    // ===== FORM FIELDS =====
    public JTextField txtID, txtSoPhieu;
    public JTextArea txtGhiChu;
    public JComboBox<String> cboKhachHang;
    public JSpinner spNgayXuat;

    // ===== BUTTONS =====
    public JButton btnThem, btnSua, btnXoa, btnLamMoi;

    // ===== TABLE =====
    public JTable tblPhieuXuat;
    public DefaultTableModel tableModel;

    public PhieuXuatView() {
        setTitle("Quản lý Phiếu Xuất");
        setSize(1000, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Font fontLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontButton = new Font("Segoe UI", Font.BOLD, 13);

        /* ================= HEADER ================= */
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(230, 248, 245));
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(180, 220, 215)));

        JLabel lblTitle = new JLabel("QUẢN LÝ PHIẾU XUẤT");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(50, 50, 150));

        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        /* ================= MAIN CONTENT ================= */
        JPanel pnlMain = new JPanel(new BorderLayout());

        /* --- LEFT: FORM NHẬP LIỆU --- */
        JPanel pnlLeft = new JPanel(new BorderLayout());
        pnlLeft.setPreferredSize(new Dimension(350, 0));
        pnlLeft.setBackground(Color.WHITE);
        pnlLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlForm = new JPanel();
        pnlForm.setLayout(new BoxLayout(pnlForm, BoxLayout.Y_AXIS));
        pnlForm.setBackground(Color.WHITE);

        // ID (Read-only)
        pnlForm.add(createLabel("ID:", fontLabel));
        txtID = createTextField(fontLabel, false);
        pnlForm.add(txtID);

        // Số phiếu
        pnlForm.add(Box.createVerticalStrut(10));
        pnlForm.add(createLabel("Số phiếu xuất:", fontLabel));
        txtSoPhieu = createTextField(fontLabel, true);
        pnlForm.add(txtSoPhieu);

        // Ngày xuất
        pnlForm.add(Box.createVerticalStrut(10));
        pnlForm.add(createLabel("Ngày xuất:", fontLabel));
        spNgayXuat = new JSpinner(new SpinnerDateModel());
        spNgayXuat.setEditor(new JSpinner.DateEditor(spNgayXuat, "dd/MM/yyyy HH:mm"));
        spNgayXuat.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        pnlForm.add(spNgayXuat);

        // Khách hàng
        pnlForm.add(Box.createVerticalStrut(10));
        pnlForm.add(createLabel("Khách hàng:", fontLabel));
        cboKhachHang = new JComboBox<>();
        cboKhachHang.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        pnlForm.add(cboKhachHang);

        // Ghi chú
        pnlForm.add(Box.createVerticalStrut(10));
        pnlForm.add(createLabel("Ghi chú:", fontLabel));
        txtGhiChu = new JTextArea(3, 20);
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setWrapStyleWord(true);
        JScrollPane spNote = new JScrollPane(txtGhiChu);
        spNote.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        pnlForm.add(spNote);

        pnlLeft.add(pnlForm, BorderLayout.NORTH);

        /* --- BUTTONS AREA --- */
        JPanel pnlButtons = new JPanel(new GridLayout(4, 1, 0, 10));
        pnlButtons.setBackground(Color.WHITE);
        pnlButtons.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        btnThem = new JButton("Thêm Phiếu");
        btnSua = new JButton("Sửa Phiếu");
        btnXoa = new JButton("Xóa Phiếu");
        btnLamMoi = new JButton("Làm mới");

        JButton[] btns = {btnThem, btnSua, btnXoa, btnLamMoi};
        Color btnColor = new Color(0, 150, 136);

        for (JButton btn : btns) {
            btn.setBackground(btnColor);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setFont(fontButton);
            pnlButtons.add(btn);
        }

        pnlLeft.add(pnlButtons, BorderLayout.SOUTH);
        pnlMain.add(pnlLeft, BorderLayout.WEST);

        /* --- RIGHT: TABLE DANH SÁCH --- */
        tableModel = new DefaultTableModel(
            new String[]{"ID", "Số phiếu", "Ngày xuất", "ID Khách hàng", "Ghi chú"}, 0
        );
        tblPhieuXuat = new JTable(tableModel);
        tblPhieuXuat.setRowHeight(25);
        tblPhieuXuat.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        // Style cho Header của bảng
        tblPhieuXuat.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblPhieuXuat.getTableHeader().setBackground(new Color(0, 150, 136));
        tblPhieuXuat.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tblPhieuXuat);
        pnlMain.add(scroll, BorderLayout.CENTER);

        add(pnlMain, BorderLayout.CENTER);
        setVisible(true);
    }

    /* ================= HELPER METHODS ================= */
    private JLabel createLabel(String text, Font font) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(font);
        return lbl;
    }

    private JTextField createTextField(Font font, boolean enable) {
        JTextField txt = new JTextField();
        txt.setFont(font);
        txt.setEnabled(enable);
        txt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        return txt;
    }
}