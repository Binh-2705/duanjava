/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Admin
 */
// TonKhoView.java

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;

public class TonKhoView extends JFrame {

    public JTextField txtID, txtSoLuong;
    public JComboBox<String> cboSanPham, cboKho, cboViTri;
    public JButton btnCapNhat, btnLamMoi;
    public JTable tblTonKho;
    public DefaultTableModel tableModel;

    public TonKhoView() {
        setTitle("Quản lý Tồn kho");
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Font fontLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontButton = new Font("Segoe UI", Font.BOLD, 13);

        // HEADER
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(230, 248, 245));
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(180, 220, 215)));

        JLabel lblTitle = new JLabel("QUẢN LÝ TỒN KHO");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 128, 128));
        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        // MAIN PANEL
        JPanel pnlMain = new JPanel(new BorderLayout());

        // TOP: FILTER PANEL
        JPanel pnlFilter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlFilter.setBackground(Color.WHITE);
        pnlFilter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblFilter = new JLabel("Lọc theo:");
        lblFilter.setFont(fontLabel);
        JComboBox<String> cboFilterKho = new JComboBox<>(new String[]{"Tất cả kho"});
        JComboBox<String> cboFilterSanPham = new JComboBox<>(new String[]{"Tất cả sản phẩm"});
        JButton btnFilter = new JButton("Lọc");

        pnlFilter.add(lblFilter);
        pnlFilter.add(new JLabel("Kho:"));
        pnlFilter.add(cboFilterKho);
        pnlFilter.add(Box.createRigidArea(new Dimension(20, 0)));
        pnlFilter.add(new JLabel("Sản phẩm:"));
        pnlFilter.add(cboFilterSanPham);
        pnlFilter.add(Box.createRigidArea(new Dimension(20, 0)));
        pnlFilter.add(btnFilter);

        // LEFT: UPDATE PANEL
        JPanel pnlLeft = new JPanel(new BorderLayout());
        pnlLeft.setPreferredSize(new Dimension(400, 0));
        pnlLeft.setBackground(Color.WHITE);
        pnlLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlUpdate = new JPanel();
        pnlUpdate.setLayout(new BoxLayout(pnlUpdate, BoxLayout.Y_AXIS));
        pnlUpdate.setBackground(Color.WHITE);
        pnlUpdate.setBorder(BorderFactory.createTitledBorder("Cập nhật tồn kho"));

        addFormField(pnlUpdate, "Sản phẩm:", cboSanPham = new JComboBox<>(), fontLabel);
        addFormField(pnlUpdate, "Kho:", cboKho = new JComboBox<>(), fontLabel);
        addFormField(pnlUpdate, "Vị trí:", cboViTri = new JComboBox<>(), fontLabel);
        addFormField(pnlUpdate, "Số lượng:", txtSoLuong = new JTextField(), false, fontLabel);

        JPanel pnlButtons = new JPanel(new GridLayout(2, 1, 0, 10));
        pnlButtons.setBackground(Color.WHITE);
        pnlButtons.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        btnCapNhat = new JButton("Cập nhật tồn");
        btnLamMoi = new JButton("Làm mới");

        JButton[] arrButtons = {btnCapNhat, btnLamMoi};
        Color btnColor = new Color(0, 150, 136);

        for (JButton btn : arrButtons) {
            btn.setBackground(btnColor);
            btn.setForeground(Color.WHITE);
            btn.setFont(fontButton);
            btn.setFocusPainted(false);
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
            pnlButtons.add(btn);
        }

        pnlUpdate.add(pnlButtons);
        pnlLeft.add(pnlUpdate, BorderLayout.NORTH);
        pnlLeft.add(pnlFilter, BorderLayout.SOUTH);

        // TABLE
        tableModel = new DefaultTableModel(new String[]{
            "ID", "Sản phẩm", "Kho", "Vị trí", "Số lượng", "Ngày cập nhật"
        }, 0);
        tblTonKho = new JTable(tableModel);
        tblTonKho.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblTonKho.setRowHeight(25);

        tblTonKho.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblTonKho.getTableHeader().setBackground(new Color(0, 150, 136));
        tblTonKho.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tblTonKho);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        pnlMain.add(pnlLeft, BorderLayout.WEST);
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

    private void addFormField(JPanel panel, String labelText, JComboBox<String> comboBox, Font font) {
        JLabel label = new JLabel(labelText);
        label.setFont(font);
        comboBox.setFont(font);
        comboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(comboBox);
    }
}