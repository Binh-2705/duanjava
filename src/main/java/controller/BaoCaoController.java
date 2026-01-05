package controller;

import dao.BaoCaoDAO;
import view.BaoCaoView;
import java.util.Date;
import javax.swing.JOptionPane;

public class BaoCaoController {
    private BaoCaoView view;
    private BaoCaoDAO dao;

    public BaoCaoController(BaoCaoView view) {
        this.view = view;
        this.dao = new BaoCaoDAO();
        
        view.btnLocNhap.addActionListener(e -> {
            Date tu = (Date) view.spTuNgay.getValue();
            Date den = (Date) view.spDenNgay.getValue();
            dao.loadBaoCaoNhap(view.tableModel, tu, den);
            updateSummary("Phiếu Nhập");
        });

        view.btnLocXuat.addActionListener(e -> {
            Date tu = (Date) view.spTuNgay.getValue();
            Date den = (Date) view.spDenNgay.getValue();
            dao.loadBaoCaoXuat(view.tableModel, tu, den);
            updateSummary("Phiếu Xuất");
        });
    }

    private void updateSummary(String title) {
        int count = view.tableModel.getRowCount();
        view.lblTongCong.setText("Tổng số " + title + " tìm thấy: " + count);
        if (count == 0) {
            JOptionPane.showMessageDialog(view, "Không tìm thấy dữ liệu trong khoảng thời gian này!");
        }
    }
}