/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Admin
 */
// DieuChinhTonView.java


import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;

public class DieuChinhTonView extends JFrame {

    public JTextField txtID, txtSoLuongCu, txtSoLuongMoi, txtLyDo, txtNguoiDieuChinh;
    public JComboBox<String> cboSanPham, cboKho;
    public JButton btnDieuChinh, btnXoa, btnLamMoi;
    public JTable tblDieuChinh;
    public DefaultTableModel tableModel;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public DieuChinhTonView() {
        setTitle("Điều chỉnh Tồn kho");
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

        JLabel lblTitle = new JLabel("ĐIỀU CHỈNH TỒN KHO");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 128, 128));
        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        // MAIN PANEL
        JPanel pnlMain = new JPanel(new BorderLayout());

        // LEFT: FORM PANEL
        JPanel pnlLeft = new JPanel(new BorderLayout());
        pnlLeft.setPreferredSize(new Dimension(400, 0));
        pnlLeft.setBackground(Color.WHITE);
        pnlLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlForm = new JPanel();
        pnlForm.setLayout(new BoxLayout(pnlForm, BoxLayout.Y_AXIS));
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setBorder(BorderFactory.createTitledBorder("Thông tin điều chỉnh"));

        addFormField(pnlForm, "Sản phẩm:", cboSanPham = new JComboBox<>(), fontLabel);
        addFormField(pnlForm, "Kho:", cboKho = new JComboBox<>(), fontLabel);
        addFormField(pnlForm, "Số lượng cũ:", txtSoLuongCu = new JTextField(), true, fontLabel);
        addFormField(pnlForm, "Số lượng mới:", txtSoLuongMoi = new JTextField(), false, fontLabel);
        
        JLabel lblLyDo = new JLabel("Lý do:");
        lblLyDo.setFont(fontLabel);
        txtLyDo = new JTextField();
        txtLyDo.setFont(fontLabel);
        txtLyDo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        
        JLabel lblNguoi = new JLabel("Người điều chỉnh:");
        lblNguoi.setFont(fontLabel);
        txtNguoiDieuChinh = new JTextField();
        txtNguoiDieuChinh.setFont(fontLabel);
        txtNguoiDieuChinh.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        pnlForm.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlForm.add(lblLyDo);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlForm.add(txtLyDo);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlForm.add(lblNguoi);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlForm.add(txtNguoiDieuChinh);

        JPanel pnlButtons = new JPanel(new GridLayout(3, 1, 0, 10));
        pnlButtons.setBackground(Color.WHITE);
        pnlButtons.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        btnDieuChinh = new JButton("Điều chỉnh");
        btnXoa = new JButton("Xóa phiếu");
        btnLamMoi = new JButton("Làm mới");

        JButton[] arrButtons = {btnDieuChinh, btnXoa, btnLamMoi};
        Color btnColor = new Color(0, 150, 136);

        for (JButton btn : arrButtons) {
            btn.setBackground(btnColor);
            btn.setForeground(Color.WHITE);
            btn.setFont(fontButton);
            btn.setFocusPainted(false);
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
            pnlButtons.add(btn);
        }

        pnlForm.add(pnlButtons);
        pnlLeft.add(pnlForm, BorderLayout.NORTH);

        // TABLE
        tableModel = new DefaultTableModel(new String[]{
            "ID", "Sản phẩm", "Kho", "SL cũ", "SL mới", "Thay đổi", "Lý do", "Ngày", "Người điều chỉnh"
        }, 0);
        tblDieuChinh = new JTable(tableModel);
        tblDieuChinh.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblDieuChinh.setRowHeight(25);

        tblDieuChinh.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblDieuChinh.getTableHeader().setBackground(new Color(0, 150, 136));
        tblDieuChinh.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tblDieuChinh);
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