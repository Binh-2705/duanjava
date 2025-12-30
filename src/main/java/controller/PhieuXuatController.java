package controller;

import dao.PhieuXuatDAO;
import dao.KhachHangDAO;
import model.PhieuXuat;
import model.KhachHang;
import view.PhieuXuatView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PhieuXuatController {

    private PhieuXuatView view;
    private PhieuXuatDAO phieuXuatDao; 
    private KhachHangDAO khDAO;
    private List<KhachHang> listKH;

    public PhieuXuatController(PhieuXuatView view) {
        this.view = view;
        // Sửa: Dùng tên biến phieuXuatDao (viết thường chữ p)
        this.phieuXuatDao = new PhieuXuatDAO(); 
        this.khDAO = new KhachHangDAO();

        loadData();
        addEvents();
    }

    private void loadData() {
      
        listKH = khDAO.getAll();
        view.cboKhachHang.removeAllItems();
        if (listKH != null) {
            for (KhachHang kh : listKH) {
                view.cboKhachHang.addItem(kh.getTen());
            }
        }

        // Load dữ liệu vào Table
        DefaultTableModel model = (DefaultTableModel) view.tblPhieuXuat.getModel();
        model.setRowCount(0);
        
        // Sửa: Gọi qua biến phieuXuatDao
        List<PhieuXuat> listPX = phieuXuatDao.getAll(); 
        for (PhieuXuat px : listPX) {
            model.addRow(new Object[]{
                px.getId(), 
                px.getSoPhieu(), 
                px.getNgayXuat(), 
                px.getIdKhachHang(), 
                px.getGhiChu()
            });
        }
    }

    private int getSelectedKhachHangID() {
        int index = view.cboKhachHang.getSelectedIndex();
        if (index >= 0 && index < listKH.size()) {
            return listKH.get(index).getId();
        }
        return -1;
    }

    private void setSelectedKhachHangByID(int idKH) {
        for (int i = 0; i < listKH.size(); i++) {
            if (listKH.get(i).getId() == idKH) {
                view.cboKhachHang.setSelectedIndex(i);
                break;
            }
        }
    }

    // --- HÀM SỰ KIỆN ---

    private void addEvents() {

        // 1. SỰ KIỆN CLICK BẢNG
        view.tblPhieuXuat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.tblPhieuXuat.getSelectedRow();
                if (row >= 0) {
                    view.txtID.setText(view.tblPhieuXuat.getValueAt(row, 0).toString());
                    view.txtSoPhieu.setText(view.tblPhieuXuat.getValueAt(row, 1).toString());
                    
                    Object objDate = view.tblPhieuXuat.getValueAt(row, 2);
                    if (objDate instanceof Date) {
                        view.spNgayXuat.setValue((Date) objDate);
                    }
                    
                    int idKH = Integer.parseInt(view.tblPhieuXuat.getValueAt(row, 3).toString());
                    setSelectedKhachHangByID(idKH);
                    
                    view.txtGhiChu.setText(view.tblPhieuXuat.getValueAt(row, 4).toString());
                }
            }
        });

        // 2. NÚT THÊM
        view.btnThem.addActionListener(e -> {
            try {
                String soPhieu = view.txtSoPhieu.getText();
                if (soPhieu.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Vui lòng nhập số phiếu!");
                    return;
                }

                PhieuXuat px = new PhieuXuat();
                px.setSoPhieu(soPhieu);
                px.setNgayXuat((Date) view.spNgayXuat.getValue());
                px.setIdKhachHang(getSelectedKhachHangID());
                px.setGhiChu(view.txtGhiChu.getText());

                // Sửa: Gọi qua biến phieuXuatDao
                if (phieuXuatDao.insert(px) > 0) { 
                    JOptionPane.showMessageDialog(view, "Thêm phiếu xuất thành công!");
                    loadData();
                    clearForm();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // 3. NÚT SỬA
        view.btnSua.addActionListener(e -> {
            try {
                if (view.txtID.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Vui lòng chọn phiếu cần sửa!");
                    return;
                }

                PhieuXuat px = new PhieuXuat();
                px.setId(Integer.parseInt(view.txtID.getText()));
                px.setSoPhieu(view.txtSoPhieu.getText());
                px.setNgayXuat((Date) view.spNgayXuat.getValue());
                px.setIdKhachHang(getSelectedKhachHangID());
                px.setGhiChu(view.txtGhiChu.getText());

                // Sửa: Gọi qua biến phieuXuatDao
                phieuXuatDao.update(px); 
                JOptionPane.showMessageDialog(view, "Cập nhật thành công!");
                loadData();
                clearForm();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // 4. NÚT XÓA
        view.btnXoa.addActionListener(e -> {
            int row = view.tblPhieuXuat.getSelectedRow();
            if (row >= 0) {
                int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc muốn xóa phiếu xuất này?");
                if (confirm == JOptionPane.YES_OPTION) {
                    int id = (int) view.tblPhieuXuat.getValueAt(row, 0);
                    // Sửa: Gọi qua biến phieuXuatDao
                    phieuXuatDao.delete(id); 
                    loadData();
                    clearForm();
                    JOptionPane.showMessageDialog(view, "Đã xóa!");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng cần xóa!");
            }
        });

        // 5. NÚT LÀM MỚI
        view.btnLamMoi.addActionListener(e -> clearForm());
    }

    private void clearForm() {
        view.txtID.setText("");
        view.txtSoPhieu.setText("");
        view.txtGhiChu.setText("");
        view.spNgayXuat.setValue(new Date());
        if (view.cboKhachHang.getItemCount() > 0) {
            view.cboKhachHang.setSelectedIndex(0);
        }
        view.tblPhieuXuat.clearSelection();
    }
}