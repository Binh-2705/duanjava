/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PhieuKiemKeView extends JFrame {
    public JTextField txtID, txtSoPhieu;
    public JComboBox<String> cboKho;
    public JSpinner spNgayKiem;
    public JTextArea txtGhiChu;
    public JButton btnThem, btnSua, btnXoa, btnLamMoi, btnXemChiTiet;
    public JTable tblKiemKe;
    public DefaultTableModel tableModel;

    public PhieuKiemKeView() {
        setTitle("Quản lý Kiểm Kê Kho");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Color mainGreen = new Color(0, 150, 136);

        // Header
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(230, 248, 245));
        JLabel lblTitle = new JLabel("QUẢN LÝ PHIẾU KIỂM KÊ");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 128, 128));
        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        // Left Panel (Form)
        JPanel pnlLeft = new JPanel();
        pnlLeft.setPreferredSize(new Dimension(320, 0));
        pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));
        pnlLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlLeft.setBackground(Color.WHITE);

        txtID = new JTextField(); txtID.setEnabled(false);
        txtSoPhieu = new JTextField();
        cboKho = new JComboBox<>();
        spNgayKiem = new JSpinner(new SpinnerDateModel());
        spNgayKiem.setEditor(new JSpinner.DateEditor(spNgayKiem, "dd/MM/yyyy HH:mm"));
        txtGhiChu = new JTextArea(4, 20);
        
        pnlLeft.add(new JLabel("ID:")); pnlLeft.add(txtID);
        pnlLeft.add(Box.createVerticalStrut(10));
        pnlLeft.add(new JLabel("Số phiếu:")); pnlLeft.add(txtSoPhieu);
        pnlLeft.add(Box.createVerticalStrut(10));
        pnlLeft.add(new JLabel("Ngày kiểm kê:")); pnlLeft.add(spNgayKiem);
        pnlLeft.add(Box.createVerticalStrut(10));
        pnlLeft.add(new JLabel("Kho kiểm tra:")); pnlLeft.add(cboKho);
        pnlLeft.add(Box.createVerticalStrut(10));
        pnlLeft.add(new JLabel("Ghi chú:")); pnlLeft.add(new JScrollPane(txtGhiChu));

        // Buttons
        JPanel pnlBtns = new JPanel(new GridLayout(5, 1, 0, 10));
        pnlBtns.setBackground(Color.WHITE);
        pnlBtns.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        btnThem = new JButton("Tạo phiếu mới");
        btnSua = new JButton("Cập nhật kết quả");
        btnXoa = new JButton("Xóa phiếu");
        btnLamMoi = new JButton("Làm mới");
        btnXemChiTiet = new JButton("Xem chi tiết chênh lệch");

        JButton[] btns = {btnThem, btnSua, btnXoa, btnLamMoi, btnXemChiTiet};
        for (JButton b : btns) {
            b.setBackground(mainGreen);
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Segoe UI", Font.BOLD, 13));
            pnlBtns.add(b);
        }
        pnlLeft.add(pnlBtns);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Số Phiếu", "Ngày Kiểm", "ID Kho", "Ghi Chú"}, 0);
        tblKiemKe = new JTable(tableModel);
        tblKiemKe.getTableHeader().setBackground(mainGreen);
        tblKiemKe.getTableHeader().setForeground(Color.WHITE);
        
        add(pnlLeft, BorderLayout.WEST);
        add(new JScrollPane(tblKiemKe), BorderLayout.CENTER);
    }
}