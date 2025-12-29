package controller;

import dao.KhachHangDAO;
import model.KhachHang;
import view.KhachHangView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class KhachHangController {
    private KhachHangView view;
    private KhachHangDAO dao;

    public KhachHangController(KhachHangView view) {
        this.view = view;
        this.dao = new KhachHangDAO();
        loadTable();
        addEvents();
    }

    private void loadTable() {
        view.tableModel.setRowCount(0);
        ArrayList<KhachHang> list = dao.getAll();
        for (KhachHang k : list) {
            view.tableModel.addRow(new Object[]{k.getId(), k.getTen(), k.getSdt(), k.getDiaChi(), k.getEmail()});
        }
    }

    private void addEvents() {
        view.tblKhachHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.tblKhachHang.getSelectedRow();
                if (row >= 0) {
                    view.txtID.setText(view.tableModel.getValueAt(row, 0).toString());
                    view.txtTen.setText(view.tableModel.getValueAt(row, 1).toString());
                    view.txtSdt.setText(view.tableModel.getValueAt(row, 2).toString());
                    view.txtDiaChi.setText(view.tableModel.getValueAt(row, 3).toString());
                    view.txtEmail.setText(view.tableModel.getValueAt(row, 4).toString());
                }
            }
        });

        view.btnThem.addActionListener(e -> {
            dao.insert(new KhachHang(view.txtTen.getText(), view.txtSdt.getText(), view.txtDiaChi.getText(), view.txtEmail.getText()));
            loadTable(); clearForm();
        });

        view.btnSua.addActionListener(e -> {
            int id = Integer.parseInt(view.txtID.getText());
            dao.update(new KhachHang(id, view.txtTen.getText(), view.txtSdt.getText(), view.txtDiaChi.getText(), view.txtEmail.getText()));
            loadTable(); clearForm();
        });

        view.btnXoa.addActionListener(e -> {
            int row = view.tblKhachHang.getSelectedRow();
            if (row >= 0) {
                dao.delete(view.tableModel.getValueAt(row, 0).toString());
                loadTable(); clearForm();
            }
        });

        view.btnLamMoi.addActionListener(e -> clearForm());
    }

    private void clearForm() {
        view.txtID.setText(""); view.txtTen.setText("");
        view.txtSdt.setText(""); view.txtDiaChi.setText(""); view.txtEmail.setText("");
    }
}