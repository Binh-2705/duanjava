/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Admin
 */
// ViTriKhoController.java

import dao.ViTriKhodao;
import dao.Khodao;
import model.ViTriKho;
import model.Kho;
import view.ViTriKhoView;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ViTriKhoController {
    private ViTriKhoView view;
    private ViTriKhodao vtDao;
    private Khodao khoDao;

    public ViTriKhoController(ViTriKhoView view) {
        this.view = view;
        this.vtDao = new ViTriKhodao();
        this.khoDao = new Khodao();
        
        loadKhoComboBox();
        loadTable();
        addEvents();
    }

    private void loadKhoComboBox() {
        view.cboKho.removeAllItems();
        ArrayList<Kho> list = khoDao.getAll();
        for (Kho k : list) {
            view.cboKho.addItem(k.getId() + " - " + k.getTenKho());
        }
    }

    private void loadTable() {
        view.tableModel.setRowCount(0);
        ArrayList<ViTriKho> list = vtDao.getAll();
        for (ViTriKho vt : list) {
            view.tableModel.addRow(new Object[]{
                vt.getId(), vt.getMaViTri(), vt.getTenViTri(),
                vt.getTenKho(), vt.getMoTa()
            });
        }
    }

    private void addEvents() {
        view.tblViTri.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.tblViTri.getSelectedRow();
                if (row >= 0) {
                    view.txtID.setText(view.tableModel.getValueAt(row, 0).toString());
                    view.txtMaViTri.setText(view.tableModel.getValueAt(row, 1).toString());
                    view.txtTenViTri.setText(view.tableModel.getValueAt(row, 2).toString());
                    view.txtMoTa.setText(view.tableModel.getValueAt(row, 4).toString());
                    
                    // Set selected kho
                    String khoInfo = view.tableModel.getValueAt(row, 3).toString();
                    for (int i = 0; i < view.cboKho.getItemCount(); i++) {
                        if (view.cboKho.getItemAt(i).toString().contains(khoInfo)) {
                            view.cboKho.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        });

        view.btnThem.addActionListener(e -> {
            String maViTri = view.txtMaViTri.getText().trim();
            String tenViTri = view.txtTenViTri.getText().trim();
            
            if (maViTri.isEmpty() || tenViTri.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Mã vị trí và tên vị trí không được để trống!");
                return;
            }
            
            if (view.cboKho.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(view, "Hãy chọn kho!");
                return;
            }
            
            int idKho = Integer.parseInt(view.cboKho.getSelectedItem().toString().split(" - ")[0]);
            
            vtDao.insert(new ViTriKho(maViTri, tenViTri, view.txtMoTa.getText(), idKho));
            loadTable();
            clearForm();
            JOptionPane.showMessageDialog(view, "Thêm vị trí thành công!");
        });

        view.btnSua.addActionListener(e -> {
            if (view.txtID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Hãy chọn vị trí để sửa!");
                return;
            }
            
            int id = Integer.parseInt(view.txtID.getText());
            String maViTri = view.txtMaViTri.getText().trim();
            String tenViTri = view.txtTenViTri.getText().trim();
            
            if (maViTri.isEmpty() || tenViTri.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Mã vị trí và tên vị trí không được để trống!");
                return;
            }
            
            if (view.cboKho.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(view, "Hãy chọn kho!");
                return;
            }
            
            int idKho = Integer.parseInt(view.cboKho.getSelectedItem().toString().split(" - ")[0]);
            
            vtDao.update(new ViTriKho(id, maViTri, tenViTri, view.txtMoTa.getText(), idKho));
            loadTable();
            clearForm();
            JOptionPane.showMessageDialog(view, "Cập nhật vị trí thành công!");
        });

        view.btnXoa.addActionListener(e -> {
            if (view.txtID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Hãy chọn vị trí để xóa!");
                return;
            }
            
            int id = Integer.parseInt(view.txtID.getText());
            
            int confirm = JOptionPane.showConfirmDialog(view,
                    "Bạn có chắc chắn muốn xóa vị trí này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                vtDao.delete(id);
                loadTable();
                clearForm();
                JOptionPane.showMessageDialog(view, "Xóa vị trí thành công!");
            }
        });

        view.btnLamMoi.addActionListener(e -> clearForm());
    }

    private void clearForm() {
        view.txtID.setText("");
        view.txtMaViTri.setText("");
        view.txtTenViTri.setText("");
        view.txtMoTa.setText("");
        if (view.cboKho.getItemCount() > 0) {
            view.cboKho.setSelectedIndex(0);
        }
        view.tblViTri.clearSelection();
    }
}
