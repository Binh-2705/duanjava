/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PhieuKiemKeDAO;
import model.PhieuKiemKe;
import view.PhieuKiemKeView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class PhieuKiemKeController {
    private PhieuKiemKeView view;
    private PhieuKiemKeDAO dao;

    public PhieuKiemKeController(PhieuKiemKeView view) {
        this.view = view;
        this.dao = new PhieuKiemKeDAO();
        initController();
    }

    private void initController() {
        loadData(); // Đổ dữ liệu lên bảng khi vừa mở
        addEvents(); // Gán sự kiện cho các nút
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
        // Sự kiện khi click vào một dòng trong bảng
        view.tblKiemKe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.tblKiemKe.getSelectedRow();
                if (row >= 0) {
                    view.txtID.setText(view.tblKiemKe.getValueAt(row, 0).toString());
                    view.txtSoPhieu.setText(view.tblKiemKe.getValueAt(row, 1).toString());
                    view.txtGhiChu.setText(view.tblKiemKe.getValueAt(row, 4).toString());
                    // Lưu ý: Cần thêm logic để set lại giá trị cho JSpinner và JComboBox nếu cần
                }
            }
        });

        // Nút Thêm phiếu mới
        view.btnThem.addActionListener(e -> {
            PhieuKiemKe pk = new PhieuKiemKe();
            pk.setSoPhieu(view.txtSoPhieu.getText());
            pk.setNgayKiemKe((Date) view.spNgayKiem.getValue());
            pk.setIdKho(1); // Mặc định là 1 hoặc lấy từ cboKho
            pk.setGhiChu(view.txtGhiChu.getText());

            dao.insert(pk);
            loadData();
            JOptionPane.showMessageDialog(view, "Tạo phiếu kiểm kê thành công!");
        });

        // Nút Cập nhật (Sửa)
        view.btnSua.addActionListener(e -> {
            int id = Integer.parseInt(view.txtID.getText());
            PhieuKiemKe pk = new PhieuKiemKe();
            pk.setId(id);
            pk.setSoPhieu(view.txtSoPhieu.getText());
            pk.setNgayKiemKe((Date) view.spNgayKiem.getValue());
            pk.setIdKho(1);
            pk.setGhiChu(view.txtGhiChu.getText());

            dao.update(pk);
            loadData();
            JOptionPane.showMessageDialog(view, "Cập nhật kết quả thành công!");
        });

        // Nút Xóa
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

        // Nút Làm mới
        view.btnLamMoi.addActionListener(e -> clearForm());
        
        // Nút Xem chi tiết (để xem chênh lệch từng món hàng)
        view.btnXemChiTiet.addActionListener(e -> {
            int row = view.tblKiemKe.getSelectedRow();
            if (row >= 0) {
                int idPK = (int) view.tblKiemKe.getValueAt(row, 0);
                String soPhieu = view.tblKiemKe.getValueAt(row, 1).toString();
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
    }

    private void showChiTietKiemKe(int idPK, String soPhieu) {
        // Phần này sẽ gọi Dialog chi tiết kiểm kê giống như phần Phiếu Nhập
        JOptionPane.showMessageDialog(view, "Chức năng xem bảng chênh lệch hàng hóa cho phiếu: " + soPhieu);
    }
}
