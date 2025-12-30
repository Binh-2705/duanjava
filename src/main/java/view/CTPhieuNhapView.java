/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CTPhieuNhapView extends JDialog {
    public JTable tblChiTiet;
    public DefaultTableModel tableModel;

    public CTPhieuNhapView(Frame parent, String soPhieu) {
        super(parent, "Chi tiết phiếu nhập: " + soPhieu, true);
        setSize(800, 450);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Header màu xanh lá
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(230, 248, 245));
        JLabel lblTitle = new JLabel("DANH SÁCH SẢN PHẨM TRONG PHIẾU: " + soPhieu);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(new Color(0, 128, 128));
        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        // Bảng chi tiết
        tableModel = new DefaultTableModel(
            new String[]{"ID SP", "Số lượng", "Giá nhập", "Vị trí kệ"}, 0
        );
        tblChiTiet = new JTable(tableModel);
        tblChiTiet.setRowHeight(30);
        
        // Style tiêu đề bảng màu xanh lá
        tblChiTiet.getTableHeader().setBackground(new Color(0, 150, 136));
        tblChiTiet.getTableHeader().setForeground(Color.WHITE);
        tblChiTiet.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        add(new JScrollPane(tblChiTiet), BorderLayout.CENTER);
    }
}