/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Admin
 */
// KhoController.java

import dao.Khodao;
import model.Kho;
import view.KhoView;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class KhoController {
    private KhoView view;
    private Khodao dao;

    public KhoController(KhoView view) {
        this.view = view;
        this.dao = new Khodao();
        loadTable();
        addEvents();
    }

    private void loadTable() {
        view.tableModel.setRowCount(0);
        ArrayList<Kho> list = dao.getAll();
        for (Kho k : list) {
            view.tableModel.addRow(new Object[]{
                k.getId(), k.getMaKho(), k.getTenKho(),
                k.getDiaChi(), k.getSdt(), k.getGhiChu()
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
                    view.txtMaKho.setText(view.tableModel.getValueAt(row, 1).toString());
                    view.txtTenKho.setText(view.tableModel.getValueAt(row, 2).toString());
                    view.txtDiaChi.setText(view.tableModel.getValueAt(row, 3).toString());
                    view.txtSDT.setText(view.tableModel.getValueAt(row, 4).toString());
                    view.txtGhiChu.setText(view.tableModel.getValueAt(row, 5).toString());
                }
            }
        });

        view.btnThem.addActionListener(e -> {
            String maKho = view.txtMaKho.getText().trim();
            String tenKho = view.txtTenKho.getText().trim();
            
            if (maKho.isEmpty() || tenKho.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Mã kho và tên kho không được để trống!");
                return;
            }
            
            dao.insert(new Kho(maKho, tenKho, view.txtDiaChi.getText(), 
                             view.txtSDT.getText(), view.txtGhiChu.getText()));
            loadTable();
            clearForm();
            JOptionPane.showMessageDialog(view, "Thêm kho thành công!");
        });

        view.btnSua.addActionListener(e -> {
            if (view.txtID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Hãy chọn kho để sửa!");
                return;
            }
            
            int id = Integer.parseInt(view.txtID.getText());
            String maKho = view.txtMaKho.getText().trim();
            String tenKho = view.txtTenKho.getText().trim();
            
            if (maKho.isEmpty() || tenKho.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Mã kho và tên kho không được để trống!");
                return;
            }
            
            dao.update(new Kho(id, maKho, tenKho, view.txtDiaChi.getText(),
                             view.txtSDT.getText(), view.txtGhiChu.getText()));
            loadTable();
            clearForm();
            JOptionPane.showMessageDialog(view, "Cập nhật kho thành công!");
        });

        view.btnXoa.addActionListener(e -> {
            if (view.txtID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Hãy chọn kho để xóa!");
                return;
            }
            
            int id = Integer.parseInt(view.txtID.getText());
            
            int confirm = JOptionPane.showConfirmDialog(view,
                    "Bạn có chắc chắn muốn xóa kho này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                dao.delete(id);
                loadTable();
                clearForm();
                JOptionPane.showMessageDialog(view, "Xóa kho thành công!");
            }
        });

        view.btnLamMoi.addActionListener(e -> clearForm());
    }

    private void clearForm() {
        view.txtID.setText("");
        view.txtMaKho.setText("");
        view.txtTenKho.setText("");
        view.txtDiaChi.setText("");
        view.txtSDT.setText("");
        view.txtGhiChu.setText("");
        view.tblKho.clearSelection();
    }
}