package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BaoCaoView extends JFrame {
    public JSpinner spTuNgay, spDenNgay;
    public JButton btnLocNhap, btnLocXuat;
    public JTable tblBaoCao;
    public DefaultTableModel tableModel;
    public JLabel lblTongCong;

    public BaoCaoView() {
        setTitle("HỆ THỐNG BÁO CÁO TỔNG HỢP ");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel pnlFilter = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        pnlFilter.setBorder(BorderFactory.createTitledBorder("Bộ lọc thời gian"));

        pnlFilter.add(new JLabel("Từ ngày:"));
        spTuNgay = new JSpinner(new SpinnerDateModel());
        spTuNgay.setEditor(new JSpinner.DateEditor(spTuNgay, "dd/MM/yyyy"));
        pnlFilter.add(spTuNgay);

        pnlFilter.add(new JLabel("Đến ngày:"));
        spDenNgay = new JSpinner(new SpinnerDateModel());
        spDenNgay.setEditor(new JSpinner.DateEditor(spDenNgay, "dd/MM/yyyy"));
        pnlFilter.add(spDenNgay);

        btnLocNhap = new JButton("Báo Cáo Nhập");
        btnLocNhap.setBackground(new Color(0, 150, 136));
        btnLocNhap.setForeground(Color.WHITE);
        
        btnLocXuat = new JButton("Báo Cáo Xuất");
        btnLocXuat.setBackground(new Color(50, 50, 150));
        btnLocXuat.setForeground(Color.WHITE);

        pnlFilter.add(btnLocNhap);
        pnlFilter.add(btnLocXuat);
        add(pnlFilter, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();
        tblBaoCao = new JTable(tableModel);
        tblBaoCao.setRowHeight(30);
        tblBaoCao.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        add(new JScrollPane(tblBaoCao), BorderLayout.CENTER);

        JPanel pnlStatus = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 10));
        pnlStatus.setBackground(new Color(235, 235, 235));
        lblTongCong = new JLabel("Tổng số phiếu: 0");
        lblTongCong.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTongCong.setForeground(Color.RED);
        pnlStatus.add(lblTongCong);
        add(pnlStatus, BorderLayout.SOUTH);
    }
}