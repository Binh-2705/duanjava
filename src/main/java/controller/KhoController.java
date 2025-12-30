package controller;

import dao.KhoDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import model.Kho;
import view.KhoView;

public class KhoController {

    private KhoView view;
    private KhoDAO dao;

    public KhoController(KhoView view) {
        this.view = view;
        this.dao = new KhoDAO();

        loadTable();
        addEvents();
    }

    private void loadTable() {
        view.tableModel.setRowCount(0);
        List<Kho> list = dao.getAll();

        for (Kho kho : list) {
            view.tableModel.addRow(new Object[]{
                kho.getId(),
                kho.getTenKho(),
                kho.getDiaChi()
            });
        }
    }

    private void addEvents() {

        view.tblKho.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.tblKho.getSelectedRow();
                if (row >= 0) {
                    view.txtID.setText(view.tableModel.getValueAt(row, 0).toString());
                    view.txtTenKho.setText(view.tableModel.getValueAt(row, 1).toString());
                    view.txtDiaChi.setText(view.tableModel.getValueAt(row, 2).toString());
                }
            }
        });

        view.btnThem.addActionListener(e -> {
            String tenKho = view.txtTenKho.getText().trim();
            String diaChi = view.txtDiaChi.getText().trim();

            if (tenKho.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(view, "Tên kho không được để trống!");
                return;
            }

            Kho kho = new Kho();
            kho.setTenKho(tenKho);
            kho.setDiaChi(diaChi);

            if (dao.insert(kho)) {
                javax.swing.JOptionPane.showMessageDialog(view, "Thêm kho thành công!");
                loadTable();
                clearForm();
            } else {
                javax.swing.JOptionPane.showMessageDialog(view, "Thêm kho thất bại!");
            }
        });

        view.btnSua.addActionListener(e -> {
            if (view.txtID.getText().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(view, "Vui lòng chọn kho cần sửa!");
                return;
            }

            int id = Integer.parseInt(view.txtID.getText());
            String tenKho = view.txtTenKho.getText().trim();
            String diaChi = view.txtDiaChi.getText().trim();

            if (tenKho.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(view, "Tên kho không được để trống!");
                return;
            }

            int confirm = javax.swing.JOptionPane.showConfirmDialog(view,
                "Bạn có chắc chắn muốn sửa kho này?",
                "Xác nhận",
                javax.swing.JOptionPane.YES_NO_OPTION);

            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                Kho kho = new Kho();
                kho.setId(id);
                kho.setTenKho(tenKho);
                kho.setDiaChi(diaChi);

                if (dao.update(kho)) {
                    javax.swing.JOptionPane.showMessageDialog(view, "Sửa kho thành công!");
                    loadTable();
                    clearForm();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(view, "Sửa kho thất bại!");
                }
            }
        });

        view.btnXoa.addActionListener(e -> {
            int row = view.tblKho.getSelectedRow();
            if (row >= 0) {
                int id = Integer.parseInt(view.tableModel.getValueAt(row, 0).toString());
                String tenKho = view.tableModel.getValueAt(row, 1).toString();

                int confirm = javax.swing.JOptionPane.showConfirmDialog(view,
                    "Bạn có chắc chắn muốn xóa kho '" + tenKho + "'?",
                    "Xác nhận",
                    javax.swing.JOptionPane.YES_NO_OPTION);

                if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                    if (dao.delete(id)) {
                        javax.swing.JOptionPane.showMessageDialog(view, "Xóa kho thành công!");
                        loadTable();
                        clearForm();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(view, "Xóa kho thất bại!");
                    }
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(view, "Vui lòng chọn kho cần xóa!");
            }
        });

        view.btnLamMoi.addActionListener(e -> clearForm());
    }

    private void clearForm() {
        view.txtID.setText("");
        view.txtTenKho.setText("");
        view.txtDiaChi.setText("");
        view.tblKho.clearSelection();
    }
}