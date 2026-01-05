package controller;

import dao.KhachHangDAO;
import model.KhachHang;
import view.KhachHangView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
        updateTable(dao.getAll());
    }

    private void updateTable(ArrayList<KhachHang> list) {
        view.tableModel.setRowCount(0);
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

        view.txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { handleSearch(); }
            public void removeUpdate(DocumentEvent e) { handleSearch(); }
            public void changedUpdate(DocumentEvent e) { handleSearch(); }
            
            private void handleSearch() {
                String keyword = view.txtTimKiem.getText();
                updateTable(dao.search(keyword));
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

        view.btnLamMoi.addActionListener(e -> {
            clearForm();
            view.txtTimKiem.setText("");
            loadTable();
        });
    }

    private void clearForm() {
        view.txtID.setText(""); view.txtTen.setText("");
        view.txtSdt.setText(""); view.txtDiaChi.setText(""); view.txtEmail.setText("");
    }
}