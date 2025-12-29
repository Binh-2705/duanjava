package controller;

import dao.NhaCungCapDAO;
import model.NhaCungCap;
import view.NhaCungCapView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class NhaCungCapController {
    private NhaCungCapView view;
    private NhaCungCapDAO dao;

    public NhaCungCapController(NhaCungCapView view) {
        this.view = view;
        this.dao = new NhaCungCapDAO();
        loadTable();
        addEvents();
    }

    private void loadTable() {
        view.tableModel.setRowCount(0);
        ArrayList<NhaCungCap> list = dao.getAll();
        for (NhaCungCap n : list) {
            view.tableModel.addRow(new Object[]{n.getId(), n.getTen(), n.getSdt(), n.getDiaChi(), n.getEmail()});
        }
    }

    private void addEvents() {
        view.tblNCC.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.tblNCC.getSelectedRow();
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
            dao.insert(new NhaCungCap(view.txtTen.getText(), view.txtSdt.getText(), view.txtDiaChi.getText(), view.txtEmail.getText()));
            loadTable(); clearForm();
        });

        view.btnSua.addActionListener(e -> {
            int id = Integer.parseInt(view.txtID.getText());
            dao.update(new NhaCungCap(id, view.txtTen.getText(), view.txtSdt.getText(), view.txtDiaChi.getText(), view.txtEmail.getText()));
            loadTable(); clearForm();
        });

        view.btnXoa.addActionListener(e -> {
            int row = view.tblNCC.getSelectedRow();
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