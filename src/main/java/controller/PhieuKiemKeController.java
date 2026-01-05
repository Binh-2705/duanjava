/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CTKiemKeDAO;
import dao.PhieuKiemKeDAO;
import dao.KhoDAO; 
import model.PhieuKiemKe;
import model.Kho; 
import view.PhieuKiemKeView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel; // THÊM MỚI
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.CTKiemKe;
import view.CTKiemKeView;

public class PhieuKiemKeController {
    private PhieuKiemKeView view;
    private PhieuKiemKeDAO dao;
    private KhoDAO khoDAO; 
    private CTKiemKeDAO ctKiemKeDAO = new CTKiemKeDAO();
    private dao.TonKhoDAO tonKhoDAO = new dao.TonKhoDAO();
    
    
    public PhieuKiemKeController(PhieuKiemKeView view) {
        this.view = view;
        this.dao = new PhieuKiemKeDAO();
        this.khoDAO = new KhoDAO(); 
        initController();
    }

    private void initController() {
        loadKhoComboBox(); 
        loadData(); 
        addEvents(); 
    }

    // --- HÀM THÊM MỚI ĐỂ ĐỔ DỮ LIỆU KHO ---
    private void loadKhoComboBox() {
        List<Kho> listKho = khoDAO.getAll();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Kho k : listKho) {
            model.addElement(k.getId() + " - " + k.getTenKho());
        }
        view.cboKho.setModel(model);
    }

    private void loadData() {
        view.tableModel.setRowCount(0);
        List<PhieuKiemKe> list = dao.getAll();
        for (PhieuKiemKe pk : list) {
            view.tableModel.addRow(new Object[]{
                pk.getId(),
                pk.getSoPhieu(),
                pk.getNgayKiemKe(),
                pk.getIdKho(),
                pk.getGhiChu()
            });
        }
    }

    private void addEvents() {
        view.tblKiemKe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.tblKiemKe.getSelectedRow();
                if (row >= 0) {
                    view.txtID.setText(view.tableModel.getValueAt(row, 0).toString());
                    view.txtSoPhieu.setText(view.tableModel.getValueAt(row, 1).toString());
                    view.txtGhiChu.setText(view.tableModel.getValueAt(row, 4).toString());
                    
                    // THÊM: Tự động chọn kho trên ComboBox khi click vào bảng
                    String idKho = view.tableModel.getValueAt(row, 3).toString();
                    for (int i = 0; i < view.cboKho.getItemCount(); i++) {
                        if (view.cboKho.getItemAt(i).startsWith(idKho + " -")) {
                            view.cboKho.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        });

        
        view.btnThem.addActionListener(e -> {
            PhieuKiemKe pk = new PhieuKiemKe();
            pk.setSoPhieu(view.txtSoPhieu.getText());
            pk.setNgayKiemKe((Date) view.spNgayKiem.getValue());

            String selectedKho = (String) view.cboKho.getSelectedItem();
            int idKhoSelected = Integer.parseInt(selectedKho.split(" - ")[0]);
            pk.setIdKho(idKhoSelected);
            pk.setGhiChu(view.txtGhiChu.getText());

            // 1. Lưu phiếu và lấy ID vừa tạo
            int newIdPK = dao.insert(pk); 

            if (newIdPK > 0) {
                // 2. Lấy danh sách tồn kho của kho đó
                List<model.TonKho> listTon = tonKhoDAO.getByKho(idKhoSelected);

                // 3. Tự động đổ vào chi tiết kiểm kê
                for (model.TonKho tk : listTon) {
                    CTKiemKe ct = new CTKiemKe();
                    ct.setIdPhieuKiemKe(newIdPK);
                    ct.setIdSanPham(tk.getIdSanPham());
                    ct.setSoHeThong(tk.getSoLuong()); // Đây chính là số máy tính đang có
                    ct.setSoThucTe(tk.getSoLuong());  // Ban đầu cho Thực tế = Hệ thống
                    ct.setChenhLech(0);

                    ctKiemKeDAO.insert(ct);
                }

                loadData();
                clearForm();
                JOptionPane.showMessageDialog(view, "Tạo phiếu mới và nạp danh sách sản phẩm thành công!");
            } else {
                JOptionPane.showMessageDialog(view, "Lỗi khi tạo phiếu!");
            }
        });

        
        view.btnSua.addActionListener(e -> {
            if (view.txtID.getText().isEmpty()) return;
            int id = Integer.parseInt(view.txtID.getText());
            PhieuKiemKe pk = new PhieuKiemKe();
            pk.setId(id);
            pk.setSoPhieu(view.txtSoPhieu.getText());
            pk.setNgayKiemKe((Date) view.spNgayKiem.getValue());
            
            // SỬA: Lấy ID kho từ ComboBox
            String selectedKho = (String) view.cboKho.getSelectedItem();
            int idKho = Integer.parseInt(selectedKho.split(" - ")[0]);
            pk.setIdKho(idKho);
            
            pk.setGhiChu(view.txtGhiChu.getText());

            dao.update(pk);
            loadData();
            JOptionPane.showMessageDialog(view, "Cập nhật kết quả thành công!");
        });

        
        view.btnXoa.addActionListener(e -> {
            String idStr = view.txtID.getText();
            if (!idStr.isEmpty()) {
                int id = Integer.parseInt(idStr);
                int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc muốn xóa phiếu này?");
                if (confirm == JOptionPane.YES_OPTION) {
                    dao.delete(id);
                    loadData();
                    clearForm();
                }
            }
        });

        
        view.btnLamMoi.addActionListener(e -> clearForm());
        
        
        view.btnXemChiTiet.addActionListener(e -> {
            int row = view.tblKiemKe.getSelectedRow();
            if (row >= 0) {
                int idPK = (int) view.tableModel.getValueAt(row, 0);
                String soPhieu = view.tableModel.getValueAt(row, 1).toString();
                showChiTietKiemKe(idPK, soPhieu);
            } else {
                JOptionPane.showMessageDialog(view, "Chọn một phiếu để xem chi tiết!");
            }
        });
    }

    private void clearForm() {
        view.txtID.setText("");
        view.txtSoPhieu.setText("");
        view.txtGhiChu.setText("");
        view.spNgayKiem.setValue(new Date());
        if (view.cboKho.getItemCount() > 0) view.cboKho.setSelectedIndex(0);
    }

    private void showChiTietKiemKe(int idPK, String soPhieu) {
    CTKiemKeView detailView = new CTKiemKeView(view, true);
    detailView.setTitle("Bảng chênh lệch hàng hóa - Phiếu: " + soPhieu);
    
    DefaultTableModel model = (DefaultTableModel) detailView.tblChiTiet.getModel();
    model.setRowCount(0); // Xóa dữ liệu cũ trên bảng

    // Lấy dữ liệu thật từ Database
    List<CTKiemKe> list = ctKiemKeDAO.getListByPhieu(idPK);
    
    for (CTKiemKe ct : list) {
    model.addRow(new Object[]{
        ct.getTenSanPham(),
        ct.getSoHeThong(), 
        ct.getSoThucTe(),  
        ct.getChenhLech()  
    });
}

    detailView.setVisible(true);
    }
}