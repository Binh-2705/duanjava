package controller;

import dao.Danhmucdao;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import model.Danhmuc;
import view.DanhmucView;

public class DanhmucController {

    private DanhmucView view;
    private Danhmucdao dao;

    public DanhmucController(DanhmucView view) {
        this.view = view;
        this.dao = new Danhmucdao();

        loadTable();
        addEvents();
    }

    private void loadTable() {
        view.tableModel.setRowCount(0);
        ArrayList<Danhmuc> list = dao.getAll();

        for (Danhmuc d : list) {
            view.tableModel.addRow(new Object[]{d.getId(), d.getTen()});
        }
    }

    private void addEvents() {

        // Click bảng
        view.tblDanhMuc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.tblDanhMuc.getSelectedRow();
                if (row >= 0) {
                    view.txtID.setText(view.tableModel.getValueAt(row, 0).toString());
                    view.txtTen.setText(view.tableModel.getValueAt(row, 1).toString());
                }
            }
        });

        // Thêm
        view.btnThem.addActionListener(e -> {
            String ten = view.txtTen.getText();

            dao.insert(new Danhmuc(ten));
            loadTable();
            clearForm();
        });

        // Sửa
        view.btnSua.addActionListener(e -> {
            int id = Integer.parseInt(view.txtID.getText());
            String ten = view.txtTen.getText();

            dao.update(new Danhmuc(id, ten));
            loadTable();
            clearForm();
        });

        // Xóa
        view.btnXoa.addActionListener(e -> {
            int row = view.tblDanhMuc.getSelectedRow();
            if (row >= 0) {
                String id = view.tableModel.getValueAt(row, 0).toString();
                dao.delete(id);
                loadTable();
                clearForm();
            }
        });

        // Làm mới
        view.btnLamMoi.addActionListener(e -> clearForm());
    }

    private void clearForm() {
        view.txtID.setText("");
        view.txtTen.setText("");
    }
}
