/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Admin
 */
// DieuChinhTonController.java
// DieuChinhTonController.java
import dao.DieuChinhTondao;
import dao.TonKhodao;
import dao.Sanphamdao;
import dao.Khodao;
import model.DieuChinhTon;
import view.DieuChinhTonView;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DieuChinhTonController {
    private DieuChinhTonView view;
    private DieuChinhTondao dcDao;
    private TonKhodao tkDao;
    private Sanphamdao spDao;
    private Khodao khoDao;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public DieuChinhTonController(DieuChinhTonView view) {
        this.view = view;
        this.dcDao = new DieuChinhTondao();
        this.tkDao = new TonKhodao();
        this.spDao = new Sanphamdao();
        this.khoDao = new Khodao();
        
        loadComboBoxes();
        loadTable();
        addEvents();
    }

    private void loadComboBoxes() {
        // Load sản phẩm
        view.cboSanPham.removeAllItems();
        ArrayList<model.Sanpham> spList = spDao.getAll();
        for (model.Sanpham sp : spList) {
            view.cboSanPham.addItem(sp.getId() + " - " + sp.getTensp());
        }
        
        // Load kho
        view.cboKho.removeAllItems();
        ArrayList<model.Kho> khoList = khoDao.getAll();
        for (model.Kho k : khoList) {
            view.cboKho.addItem(k.getId() + " - " + k.getTenKho());
        }
    }

    private void loadTable() {
        view.tableModel.setRowCount(0);
        ArrayList<DieuChinhTon> list = dcDao.getAll();
        for (DieuChinhTon dc : list) {
            view.tableModel.addRow(new Object[]{
                dc.getId(), dc.getTenSanPham(), dc.getTenKho(),
                dc.getSoLuongCu(), dc.getSoLuongMoi(), dc.getSoLuongThayDoi(),
                dc.getLyDo(), dateFormat.format(dc.getNgayDieuChinh()),
                dc.getNguoiDieuChinh()
            });
        }
    }

    private void addEvents() {
        // Khi chọn sản phẩm và kho, load số lượng hiện tại
        view.cboSanPham.addActionListener(e -> loadSoLuongHienTai());
        view.cboKho.addActionListener(e -> loadSoLuongHienTai());

        view.tblDieuChinh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.tblDieuChinh.getSelectedRow();
                if (row >= 0) {
                    view.txtID.setText(view.tableModel.getValueAt(row, 0).toString());
                }
            }
        });

        view.btnDieuChinh.addActionListener(e -> {
            try {
                if (view.cboSanPham.getSelectedIndex() < 0 || 
                    view.cboKho.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(view, "Hãy chọn sản phẩm và kho!");
                    return;
                }
                
                int soLuongCu = Integer.parseInt(view.txtSoLuongCu.getText().trim());
                int soLuongMoi = Integer.parseInt(view.txtSoLuongMoi.getText().trim());
                String lyDo = view.txtLyDo.getText().trim();
                String nguoiDieuChinh = view.txtNguoiDieuChinh.getText().trim();
                
                if (lyDo.isEmpty() || nguoiDieuChinh.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Lý do và người điều chỉnh không được để trống!");
                    return;
                }
                
                int idSanPham = Integer.parseInt(view.cboSanPham.getSelectedItem().toString().split(" - ")[0]);
                int idKho = Integer.parseInt(view.cboKho.getSelectedItem().toString().split(" - ")[0]);
                
                // Tạo phiếu điều chỉnh
                DieuChinhTon dc = new DieuChinhTon(idSanPham, idKho, soLuongCu, soLuongMoi, lyDo, nguoiDieuChinh);
                dcDao.insert(dc);
                
                // Cập nhật tồn kho
                tkDao.updateTonKho(idSanPham, idKho, 0, soLuongMoi); // idViTri = 0 tạm thời
                
                loadTable();
                clearForm();
                JOptionPane.showMessageDialog(view, "Điều chỉnh tồn kho thành công!");
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Số lượng phải là số nguyên!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
            }
        });

        view.btnXoa.addActionListener(e -> {
            if (view.txtID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Hãy chọn phiếu điều chỉnh để xóa!");
                return;
            }
            
            int id = Integer.parseInt(view.txtID.getText());
            
            int confirm = JOptionPane.showConfirmDialog(view,
                    "Bạn có chắc chắn muốn xóa phiếu điều chỉnh này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                dcDao.delete(id);
                loadTable();
                clearForm();
                JOptionPane.showMessageDialog(view, "Xóa phiếu điều chỉnh thành công!");
            }
        });

        view.btnLamMoi.addActionListener(e -> clearForm());
    }

    private void loadSoLuongHienTai() {
        try {
            if (view.cboSanPham.getSelectedIndex() >= 0 && 
                view.cboKho.getSelectedIndex() >= 0) {
                
                int idSanPham = Integer.parseInt(view.cboSanPham.getSelectedItem().toString().split(" - ")[0]);
                int idKho = Integer.parseInt(view.cboKho.getSelectedItem().toString().split(" - ")[0]);
                
                int soLuongHienTai = tkDao.getSoLuongTon(idSanPham, idKho);
                view.txtSoLuongCu.setText(String.valueOf(soLuongHienTai));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        view.txtID.setText("");
        view.txtSoLuongCu.setText("");
        view.txtSoLuongMoi.setText("");
        view.txtLyDo.setText("");
        view.txtNguoiDieuChinh.setText("");
        if (view.cboSanPham.getItemCount() > 0) view.cboSanPham.setSelectedIndex(0);
        if (view.cboKho.getItemCount() > 0) view.cboKho.setSelectedIndex(0);
        view.tblDieuChinh.clearSelection();
    }
}