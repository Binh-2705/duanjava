/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class DanhmucView extends JFrame {

    public JTextField txtID, txtTen;
    public JButton btnThem, btnSua, btnXoa, btnLamMoi;
    public JTable tblDanhMuc;
    public DefaultTableModel tableModel;

    public DanhmucView() {
        setTitle("Quản lý Danh mục sản phẩm");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Font fontLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontButton = new Font("Segoe UI", Font.BOLD, 13);

        // ===== HEADER =====
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(230, 248, 245));
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(180, 220, 215)));

        JLabel lblTitle = new JLabel("QUẢN LÝ DANH MỤC SẢN PHẨM");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 128, 128));

        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        // ===== MAIN PANEL =====
        JPanel pnlMain = new JPanel(new BorderLayout());

        // ===== FORM + BUTTONS PANEL (BÊN TRÁI) =====
        JPanel pnlLeft = new JPanel();
        pnlLeft.setLayout(new BorderLayout());
        pnlLeft.setPreferredSize(new Dimension(320, 0));
        pnlLeft.setBackground(Color.WHITE);
        pnlLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Form
        JPanel pnlForm = new JPanel();
        pnlForm.setLayout(new BoxLayout(pnlForm, BoxLayout.Y_AXIS));
        pnlForm.setBackground(Color.WHITE);

        JLabel lblID = new JLabel("ID:");
        lblID.setFont(fontLabel);
        txtID = new JTextField();
        txtID.setFont(fontLabel);
        txtID.setEnabled(false);
        txtID.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // rộng hơn

        JLabel lblTen = new JLabel("Tên danh mục:");
        lblTen.setFont(fontLabel);
        txtTen = new JTextField();
        txtTen.setFont(fontLabel);
        txtTen.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // rộng hơn

        pnlForm.add(lblID);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlForm.add(txtID);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlForm.add(lblTen);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlForm.add(txtTen);

        pnlLeft.add(pnlForm, BorderLayout.NORTH);

        // Buttons ở dưới cùng
        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new GridLayout(4, 1, 0, 10)); // 4 nút theo cột dọc
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
            btn.setFocusPainted(false);
            btn.setFont(fontButton);
            btn.setPreferredSize(new Dimension(120, 35));
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35)); // rộng theo panel
            pnlButtons.add(btn);
        }

        pnlLeft.add(pnlButtons, BorderLayout.SOUTH);

        pnlMain.add(pnlLeft, BorderLayout.WEST);

        // ===== TABLE =====
        tableModel = new DefaultTableModel(new String[]{"ID", "Tên danh mục"}, 0);
        tblDanhMuc = new JTable(tableModel);
        tblDanhMuc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblDanhMuc.setRowHeight(25);

        tblDanhMuc.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblDanhMuc.getTableHeader().setBackground(new Color(0, 150, 136));
        tblDanhMuc.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tblDanhMuc);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        pnlMain.add(scroll, BorderLayout.CENTER);

        add(pnlMain, BorderLayout.CENTER);

        setVisible(true);
    }
}

