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
public class KhoView extends JFrame {

    public JTextField txtID, txtTenKho, txtDiaChi;
    public JButton btnThem, btnSua, btnXoa, btnLamMoi;
    public JTable tblKho;
    public DefaultTableModel tableModel;

    public KhoView() {
        setTitle("Quản lý Kho");
        setSize(900, 550); // Rộng hơn một chút vì có thêm cột địa chỉ
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Font fontLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontButton = new Font("Segoe UI", Font.BOLD, 13);

        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(230, 248, 245));
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(180, 220, 215)));

        JLabel lblTitle = new JLabel("QUẢN LÝ KHO");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 128, 128));

        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        JPanel pnlMain = new JPanel(new BorderLayout());

        JPanel pnlLeft = new JPanel();
        pnlLeft.setLayout(new BorderLayout());
        pnlLeft.setPreferredSize(new Dimension(350, 0)); // Rộng hơn một chút
        pnlLeft.setBackground(Color.WHITE);
        pnlLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlForm = new JPanel();
        pnlForm.setLayout(new BoxLayout(pnlForm, BoxLayout.Y_AXIS));
        pnlForm.setBackground(Color.WHITE);

        JLabel lblID = new JLabel("ID:");
        lblID.setFont(fontLabel);
        txtID = new JTextField();
        txtID.setFont(fontLabel);
        txtID.setEnabled(false);
        txtID.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel lblTenKho = new JLabel("Tên kho:");
        lblTenKho.setFont(fontLabel);
        txtTenKho = new JTextField();
        txtTenKho.setFont(fontLabel);
        txtTenKho.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        lblDiaChi.setFont(fontLabel);
        txtDiaChi = new JTextField();
        txtDiaChi.setFont(fontLabel);
        txtDiaChi.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        pnlForm.add(lblID);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlForm.add(txtID);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlForm.add(lblTenKho);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlForm.add(txtTenKho);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlForm.add(lblDiaChi);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlForm.add(txtDiaChi);

        pnlLeft.add(pnlForm, BorderLayout.NORTH);

        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new GridLayout(4, 1, 0, 10)); 
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
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
            pnlButtons.add(btn);
        }

        pnlLeft.add(pnlButtons, BorderLayout.SOUTH);

        pnlMain.add(pnlLeft, BorderLayout.WEST);

        tableModel = new DefaultTableModel(new String[]{"ID", "Tên kho", "Địa chỉ"}, 0);
        tblKho = new JTable(tableModel);
        tblKho.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblKho.setRowHeight(25);

        tblKho.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblKho.getTableHeader().setBackground(new Color(0, 150, 136));
        tblKho.getTableHeader().setForeground(Color.WHITE);

        tblKho.getColumnModel().getColumn(0).setPreferredWidth(50);  
        tblKho.getColumnModel().getColumn(1).setPreferredWidth(150); 
        tblKho.getColumnModel().getColumn(2).setPreferredWidth(200); 

        JScrollPane scroll = new JScrollPane(tblKho);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        pnlMain.add(scroll, BorderLayout.CENTER);

        add(pnlMain, BorderLayout.CENTER);

        setVisible(true);
    }
}