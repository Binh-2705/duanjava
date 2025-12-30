package controller;

import dao.Donvidao;
import model.Donvi;
import view.DonviView;

import javax.swing.*;
import java.util.ArrayList;

public class DonviController {

    private DonviView view;
    private Donvidao dao = new Donvidao();

    public DonviController(DonviView view) {
        this.view = view;

        loadTable();
        addEvents();
    }

    // Load dữ liệu
    private void loadTable() {
        view.tableModel.setRowCount(0);
        ArrayList<Donvi> list = dao.getAll();

        for (Donvi dv : list) {
            view.tableModel.addRow(new Object[]{
                dv.getId(),
                dv.getTendv()
            });
        }
    }

    // Gắn sự kiện
    private void addEvents() {

        // Chọn dòng → đổ lên form
        view.tblDonVi.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = view.tblDonVi.getSelectedRow();
                if (row >= 0) {
                    view.txtID.setText(view.tblDonVi.getValueAt(row, 0).toString());
                    view.txtTen.setText(view.tblDonVi.getValueAt(row, 1).toString());
                }
            }
        });

        // Thêm
        view.btnThem.addActionListener(e -> {
            String ten = view.txtTen.getText().trim();

            if (ten.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Tên đơn vị không được để trống!");
                return;
            }

            dao.insert(new Donvi(0, ten));
            loadTable();
            JOptionPane.showMessageDialog(view, "Thêm thành công!");
        });

        // Sửa
        view.btnSua.addActionListener(e -> {
            if (view.txtID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Hãy chọn dòng để sửa!");
                return;
            }

            int id = Integer.parseInt(view.txtID.getText());
            String ten = view.txtTen.getText().trim();

            dao.update(new Donvi(id, ten));
            loadTable();
            JOptionPane.showMessageDialog(view, "Cập nhật thành công!");
        });

        // Xóa
        view.btnXoa.addActionListener(e -> {
            if (view.txtID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Hãy chọn dòng để xóa!");
                return;
            }

            int id = Integer.parseInt(view.txtID.getText());

            int confirm = JOptionPane.showConfirmDialog(view,
                    "Bạn có chắc chắn muốn xóa?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                dao.delete(id);
                loadTable();
                JOptionPane.showMessageDialog(view, "Xóa thành công!");
            }
        });

        // Làm mới
        view.btnLamMoi.addActionListener(e -> {
            view.txtID.setText("");
            view.txtTen.setText("");
            view.tblDonVi.clearSelection();
        });
    }
}
