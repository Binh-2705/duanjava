package controller;

import dao.Sanphamdao;
import dao.Danhmucdao;
import dao.Donvidao;
import model.Sanpham;
import model.Danhmuc;
import model.Donvi;
import view.SanphamView;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SanphamController {

    private SanphamView view;
    private Sanphamdao spDao = new Sanphamdao();
    private Danhmucdao dmDao = new Danhmucdao();
    private Donvidao dvDao = new Donvidao();

    public SanphamController(SanphamView view) {
        this.view = view;

        loadDanhMuc();
        loadDonVi();
        loadTable();

        addEvents();
    }

    private void addEvents() {

        // nút Thêm
        view.btnThem.addActionListener(e -> addSanPham());

        // nút Sửa
        view.btnSua.addActionListener(e -> updateSanPham());

        // nút Xóa
        view.btnXoa.addActionListener(e -> deleteSanPham());

        // nút Làm mới
        view.btnLamMoi.addActionListener(e -> clearForm());

        // Click bảng
        view.tblSanPham.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                fillFormFromTable();
            }
        });
    }

    // ================== LOAD COMBOBOX ==================
    private void loadDanhMuc() {
        ArrayList<Danhmuc> list = dmDao.getAll();
        view.cboDanhMuc.removeAllItems();

        for (Danhmuc dm : list) {
            view.cboDanhMuc.addItem(dm.getId() + " - " + dm.getTen());
        }
    }

    private void loadDonVi() {
        ArrayList<Donvi> list = dvDao.getAll();
        view.cboDonVi.removeAllItems();

        for (Donvi dv : list) {
            view.cboDonVi.addItem(dv.getId() + " - " + dv.getTendv());
        }
    }

    // ================== LOAD TABLE ==================
    private void loadTable() {
        view.tableModel.setRowCount(0);

        ArrayList<Sanpham> list = spDao.getAll();

        for (Sanpham sp : list) {
            view.tableModel.addRow(new Object[]{
                    sp.getId(), sp.getMaSanPham(), sp.getTensp(),
                    sp.getIdDanhMuc(), sp.getIdDonVi(),
                    sp.getGiaNhap(), sp.getGiaBan(), sp.getMoTa()
            });
        }
    }

    // ================== CRUD ==================

    private void addSanPham() {
        try {
            String ma = view.txtMaSP.getText().trim();
            String ten = view.txtTenSP.getText().trim();
            double giaNhap = Double.parseDouble(view.txtGiaNhap.getText().trim());
            double giaBan = Double.parseDouble(view.txtGiaBan.getText().trim());
            String moTa = view.txtMoTa.getText().trim();

            int idDM = Integer.parseInt(view.cboDanhMuc.getSelectedItem().toString().split(" - ")[0]);
            int idDV = Integer.parseInt(view.cboDonVi.getSelectedItem().toString().split(" - ")[0]);

            spDao.insert(new Sanpham(ma, ten, idDM, idDV, giaNhap, giaBan, moTa));

            loadTable();
            clearForm();

            JOptionPane.showMessageDialog(view, "Thêm sản phẩm thành công!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
        }
    }

    private void updateSanPham() {
        try {
            if (view.txtID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Chưa chọn sản phẩm để sửa!");
                return;
            }

            int id = Integer.parseInt(view.txtID.getText().trim());
            String ma = view.txtMaSP.getText().trim();
            String ten = view.txtTenSP.getText().trim();
            double giaNhap = Double.parseDouble(view.txtGiaNhap.getText().trim());
            double giaBan = Double.parseDouble(view.txtGiaBan.getText().trim());
            String moTa = view.txtMoTa.getText().trim();

            int idDM = Integer.parseInt(view.cboDanhMuc.getSelectedItem().toString().split(" - ")[0]);
            int idDV = Integer.parseInt(view.cboDonVi.getSelectedItem().toString().split(" - ")[0]);

            Sanpham sp = new Sanpham(id, ma, ten, idDM, idDV, giaNhap, giaBan, moTa);
            spDao.update(sp);

            loadTable();
            clearForm();

            JOptionPane.showMessageDialog(view, "Sửa thành công!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
        }
    }

    private void deleteSanPham() {
        try {
            int row = view.tblSanPham.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(view, "Hãy chọn sản phẩm muốn xóa!");
                return;
            }

           int idSP = Integer.parseInt(view.tblSanPham.getValueAt(row, 0).toString());
spDao.delete(idSP);


            loadTable();
            clearForm();

            JOptionPane.showMessageDialog(view, "Xóa thành công!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
        }
    }

    // ================== FORM SUPPORT ==================

    private void fillFormFromTable() {
        int row = view.tblSanPham.getSelectedRow();
        if (row == -1) return;

        view.txtID.setText(view.tblSanPham.getValueAt(row, 0).toString());
        view.txtMaSP.setText(view.tblSanPham.getValueAt(row, 1).toString());
        view.txtTenSP.setText(view.tblSanPham.getValueAt(row, 2).toString());
        view.txtGiaNhap.setText(view.tblSanPham.getValueAt(row, 5).toString());
        view.txtGiaBan.setText(view.tblSanPham.getValueAt(row, 6).toString());
        view.txtMoTa.setText(view.tblSanPham.getValueAt(row, 7).toString());
    }

    private void clearForm() {
        view.txtID.setText("");
        view.txtMaSP.setText("");
        view.txtTenSP.setText("");
        view.txtGiaNhap.setText("");
        view.txtGiaBan.setText("");
        view.txtMoTa.setText("");

        view.cboDanhMuc.setSelectedIndex(0);
        view.cboDonVi.setSelectedIndex(0);
    }
}
