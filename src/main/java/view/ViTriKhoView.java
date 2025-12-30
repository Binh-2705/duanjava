/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Admin
 */
// ViTriKhoView.java

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViTriKhoView extends JFrame {

    public JTextField txtID, txtMaViTri, txtTenViTri, txtMoTa;
    public JComboBox<String> cboKho;
    public JButton btnThem, btnSua, btnXoa, btnLamMoi;
    public JTable tblViTri;
    public DefaultTableModel tableModel;

    public ViTriKhoView() {
        setTitle("Quản lý Vị trí Kho");
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

        JLabel lblTitle = new JLabel("QUẢN LÝ VỊ TRÍ KHO");
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
        addFormField(pnlForm, "Mã vị trí:", txtMaViTri = new JTextField(), false, fontLabel);
        addFormField(pnlForm, "Tên vị trí:", txtTenViTri = new JTextField(), false, fontLabel);
        
        JLabel lblKho = new JLabel("Kho:");
        lblKho.setFont(fontLabel);
        cboKho = new JComboBox<>();
        cboKho.setFont(fontLabel);
        cboKho.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        JLabel lblMoTa = new JLabel("Mô tả:");
        lblMoTa.setFont(fontLabel);
        txtMoTa = new JTextField();
        txtMoTa.setFont(fontLabel);
        txtMoTa.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        pnlForm.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlForm.add(lblKho);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlForm.add(cboKho);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlForm.add(lblMoTa);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlForm.add(txtMoTa);

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
            "ID", "Mã vị trí", "Tên vị trí", "Kho", "Mô tả"
        }, 0);
        tblViTri = new JTable(tableModel);
        tblViTri.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblViTri.setRowHeight(25);

        tblViTri.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblViTri.getTableHeader().setBackground(new Color(0, 150, 136));
        tblViTri.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tblViTri);
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