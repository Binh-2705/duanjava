/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Admin
 */
// KhoView.java

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class KhoView extends JFrame {

    public JTextField txtID, txtMaKho, txtTenKho, txtDiaChi, txtSDT, txtGhiChu;
    public JButton btnThem, btnSua, btnXoa, btnLamMoi;
    public JTable tblKho;
    public DefaultTableModel tableModel;

    public KhoView() {
        setTitle("Quản lý Kho");
        setSize(1000, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Font fontLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontButton = new Font("Segoe UI", Font.BOLD, 13);

        // HEADER
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(230, 248, 245));
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(180, 220, 215)));

        JLabel lblTitle = new JLabel("QUẢN LÝ KHO");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 128, 128));
        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        // MAIN PANEL
        JPanel pnlMain = new JPanel(new BorderLayout());

        // LEFT: FORM
        JPanel pnlLeft = new JPanel(new BorderLayout());
        pnlLeft.setPreferredSize(new Dimension(400, 0));
        pnlLeft.setBackground(Color.WHITE);
        pnlLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // FORM PANEL
        JPanel pnlForm = new JPanel();
        pnlForm.setLayout(new BoxLayout(pnlForm, BoxLayout.Y_AXIS));
        pnlForm.setBackground(Color.WHITE);

        // Các trường nhập liệu
        addFormField(pnlForm, "ID:", txtID = new JTextField(), true, fontLabel);
        addFormField(pnlForm, "Mã kho:", txtMaKho = new JTextField(), false, fontLabel);
        addFormField(pnlForm, "Tên kho:", txtTenKho = new JTextField(), false, fontLabel);
        addFormField(pnlForm, "Địa chỉ:", txtDiaChi = new JTextField(), false, fontLabel);
        addFormField(pnlForm, "SĐT:", txtSDT = new JTextField(), false, fontLabel);
        
        JLabel lblGhiChu = new JLabel("Ghi chú:");
        lblGhiChu.setFont(fontLabel);
        txtGhiChu = new JTextField();
        txtGhiChu.setFont(fontLabel);
        txtGhiChu.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        
        pnlForm.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlForm.add(lblGhiChu);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlForm.add(txtGhiChu);

        pnlLeft.add(pnlForm, BorderLayout.CENTER);

        // BUTTONS PANEL
        JPanel pnlButtons = new JPanel(new GridLayout(4, 1, 0, 10));
        pnlButtons.setBackground(Color.WHITE);
        pnlButtons.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLamMoi = new JButton("Làm mới");

        JButton[] arrButtons = {btnThem, btnSua, btnXoa, btnLamMoi};
        Color btnColor = new Color(0, 150, 136);

        for (JButton btn : arrButtons) {
            btn.setBackground(btnColor);
            btn.setForeground(Color.WHITE);
            btn.setFont(fontButton);
            btn.setFocusPainted(false);
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
            pnlButtons.add(btn);
        }

        pnlLeft.add(pnlButtons, BorderLayout.SOUTH);
        pnlMain.add(pnlLeft, BorderLayout.WEST);

        // TABLE
        tableModel = new DefaultTableModel(new String[]{
            "ID", "Mã kho", "Tên kho", "Địa chỉ", "SĐT", "Ghi chú"
        }, 0);
        tblKho = new JTable(tableModel);
        tblKho.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblKho.setRowHeight(25);

        tblKho.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblKho.getTableHeader().setBackground(new Color(0, 150, 136));
        tblKho.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tblKho);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        pnlMain.add(scroll, BorderLayout.CENTER);

        add(pnlMain, BorderLayout.CENTER);
        setVisible(true);
    }

    private void addFormField(JPanel panel, String labelText, JTextField textField, 
                             boolean disabled, Font font) {
        JLabel label = new JLabel(labelText);
        label.setFont(font);
        textField.setFont(font);
        textField.setEnabled(!disabled);
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(textField);
    }
}