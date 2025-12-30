/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Admin
 */
// TonKhoController.java
import dao.TonKhodao;
import dao.Sanphamdao;
import dao.Khodao;
import dao.ViTriKhodao;
import model.TonKho;
import model.Sanpham;
import model.Kho;
import model.ViTriKho;
import view.TonKhoView;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TonKhoController {
    private TonKhoView view;
    private TonKhodao tkDao;
    private Sanphamdao spDao;
    private Khodao khoDao;
    private ViTriKhodao vtDao;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public TonKhoController(TonKhoView view) {
        this.view = view;
        this.tkDao = new TonKhodao();
        this.spDao = new Sanphamdao();
        this.khoDao = new Khodao();
        this.vtDao = new ViTriKhodao();
        
        loadComboBoxes();
        loadTable();
        addEvents();
    }

    private void loadComboBoxes() {
        // Load sản phẩm
        view.cboSanPham.removeAllItems();
        ArrayList<Sanpham> spList = spDao.getAll();
        for (Sanpham sp : spList) {
            view.cboSanPham.addItem(sp.getId() + " - " + sp.getTensp());
        }
        
        // Load kho
        view.cboKho.removeAllItems();
        ArrayList<Kho> khoList = khoDao.getAll();
        for (Kho k : khoList) {
            view.cboKho.addItem(k.getId() + " - " + k.getTenKho());
        }
        
        // Load vị trí
        view.cboViTri.removeAllItems();
        ArrayList<ViTriKho> vtList = vtDao.getAll();
        for (ViTriKho vt : vtList) {
            view.cboViTri.addItem(vt.getId() + " - " + vt.getTenViTri() + " (" + vt.getTenKho() + ")");
        }
    }

    private void loadTable() {
        view.tableModel.setRowCount(0);
        ArrayList<TonKho> list = tkDao.getAll();
        for (TonKho tk : list) {
            view.tableModel.addRow(new Object[]{
                tk.getId(), tk.getTenSanPham(), tk.getTenKho(),
                tk.getTenViTri(), tk.getSoLuong(),
                dateFormat.format(tk.getNgayCapNhat())
            });
        }
    }

    private void addEvents() {
        view.tblTonKho.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.tblTonKho.getSelectedRow();
                if (row >= 0) {
                    view.txtID.setText(view.tableModel.getValueAt(row, 0).toString());
                    
                    // Tìm và set selected item cho combobox
                    String spName = view.tableModel.getValueAt(row, 1).toString();
                    String khoName = view.tableModel.getValueAt(row, 2).toString();
                    String vtName = view.tableModel.getValueAt(row, 3).toString();
                    
                    // Set số lượng
                    view.txtSoLuong.setText(view.tableModel.getValueAt(row, 4).toString());
                }
            }
        });

        view.btnCapNhat.addActionListener(e -> {
            try {
                if (view.cboSanPham.getSelectedIndex() < 0 || 
                    view.cboKho.getSelectedIndex() < 0 ||
                    view.cboViTri.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(view, "Hãy chọn đầy đủ thông tin!");
                    return;
                }
                
                int idSanPham = Integer.parseInt(view.cboSanPham.getSelectedItem().toString().split(" - ")[0]);
                int idKho = Integer.parseInt(view.cboKho.getSelectedItem().toString().split(" - ")[0]);
                int idViTri = Integer.parseInt(view.cboViTri.getSelectedItem().toString().split(" - ")[0]);
                int soLuong = Integer.parseInt(view.txtSoLuong.getText().trim());
                
                if (soLuong < 0) {
                    JOptionPane.showMessageDialog(view, "Số lượng không được âm!");
                    return;
                }
                
                tkDao.updateTonKho(idSanPham, idKho, idViTri, soLuong);
                loadTable();
                clearForm();
                JOptionPane.showMessageDialog(view, "Cập nhật tồn kho thành công!");
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Số lượng phải là số nguyên!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
            }
        });

        view.btnLamMoi.addActionListener(e -> clearForm());
    }

    private void clearForm() {
        view.txtID.setText("");
        view.txtSoLuong.setText("");
        if (view.cboSanPham.getItemCount() > 0) view.cboSanPham.setSelectedIndex(0);
        if (view.cboKho.getItemCount() > 0) view.cboKho.setSelectedIndex(0);
        if (view.cboViTri.getItemCount() > 0) view.cboViTri.setSelectedIndex(0);
        view.tblTonKho.clearSelection();
    }
}