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
import javax.swing.JComboBox;
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
public class ViTriView extends JFrame {

    public JTextField txtID, txtMaViTri, txtGhiChu;
    public JComboBox<String> cbKho;
    public JButton btnThem, btnSua, btnXoa, btnLamMoi, btnLoadKho;
    public JTable tblViTri;
    public DefaultTableModel tableModel;

    public ViTriView() {
        setTitle("Quản lý Vị trí lưu trữ");
        setSize(1000, 600); // Rộng hơn vì có nhiều cột
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Font fontLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontButton = new Font("Segoe UI", Font.BOLD, 13);

        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(230, 248, 245));
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(180, 220, 215)));

        JLabel lblTitle = new JLabel("QUẢN LÝ VỊ TRÍ LƯU TRỮ");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 128, 128));

        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        JPanel pnlMain = new JPanel(new BorderLayout());

        JPanel pnlLeft = new JPanel();
        pnlLeft.setLayout(new BorderLayout());
        pnlLeft.setPreferredSize(new Dimension(400, 0)); 
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

        JLabel lblMaViTri = new JLabel("Mã vị trí:");
        lblMaViTri.setFont(fontLabel);
        txtMaViTri = new JTextField();
        txtMaViTri.setFont(fontLabel);
        txtMaViTri.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel lblKho = new JLabel("Kho:");
        lblKho.setFont(fontLabel);
        JPanel pnlKho = new JPanel(new BorderLayout(5, 0));
        pnlKho.setBackground(Color.WHITE);
        cbKho = new JComboBox<>();
        cbKho.setFont(fontLabel);
        btnLoadKho = new JButton("⟳");
        btnLoadKho.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnLoadKho.setPreferredSize(new Dimension(30, 25));
        btnLoadKho.setBackground(new Color(220, 220, 220));
        btnLoadKho.setFocusPainted(false);
        
        pnlKho.add(cbKho, BorderLayout.CENTER);
        pnlKho.add(btnLoadKho, BorderLayout.EAST);

        JLabel lblGhiChu = new JLabel("Ghi chú:");
        lblGhiChu.setFont(fontLabel);
        txtGhiChu = new JTextField();
        txtGhiChu.setFont(fontLabel);
        txtGhiChu.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        pnlForm.add(lblID);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlForm.add(txtID);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlForm.add(lblMaViTri);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlForm.add(txtMaViTri);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlForm.add(lblKho);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlForm.add(pnlKho);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlForm.add(lblGhiChu);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlForm.add(txtGhiChu);

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

        tableModel = new DefaultTableModel(new String[]{"ID", "Mã vị trí", "Kho", "Ghi chú"}, 0);
        tblViTri = new JTable(tableModel);
        tblViTri.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblViTri.setRowHeight(25);

        tblViTri.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblViTri.getTableHeader().setBackground(new Color(0, 150, 136));
        tblViTri.getTableHeader().setForeground(Color.WHITE);

        tblViTri.getColumnModel().getColumn(0).setPreferredWidth(50);  
        tblViTri.getColumnModel().getColumn(1).setPreferredWidth(100);  
        tblViTri.getColumnModel().getColumn(2).setPreferredWidth(150);  
        tblViTri.getColumnModel().getColumn(3).setPreferredWidth(200); 

        JScrollPane scroll = new JScrollPane(tblViTri);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        pnlMain.add(scroll, BorderLayout.CENTER);

        add(pnlMain, BorderLayout.CENTER);

        setVisible(true);
    }
}