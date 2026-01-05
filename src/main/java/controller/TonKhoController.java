package controller;

import dao.TonKhoDAO;
import dao.KhoDAO;
import dao.ViTriDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import model.TonKho;
import model.Kho;
import model.ViTri;
import view.TonKhoView;
import javax.swing.DefaultComboBoxModel;

public class TonKhoController {

    private TonKhoView view;
    private TonKhoDAO tonKhoDAO;
    private KhoDAO khoDAO;
    private ViTriDAO viTriDAO;

    public TonKhoController(TonKhoView view) {
        this.view = view;
        this.tonKhoDAO = new TonKhoDAO();
        this.khoDAO = new KhoDAO();
        this.viTriDAO = new ViTriDAO();

        loadKhoComboBox();
        loadTable();
        addEvents();
    }

    private void loadKhoComboBox() {
        List<Kho> khoList = khoDAO.getAll();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        
        for (Kho kho : khoList) {
            model.addElement(kho.getId() + " - " + kho.getTenKho());
        }
        
        view.cbKho.setModel(model);
        loadViTriComboBox(); 
    }

    private void loadViTriComboBox() {
        if (view.cbKho.getSelectedIndex() == -1) return;
        
        String selected = (String) view.cbKho.getSelectedItem();
        int idKho = Integer.parseInt(selected.split(" - ")[0]);
        
        List<ViTri> viTriList = viTriDAO.getByKhoId(idKho);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("0 - Không xác định");
        
        for (ViTri viTri : viTriList) {
            model.addElement(viTri.getId() + " - " + viTri.getMaViTri());
        }
        
        view.cbViTri.setModel(model);
    }

    private void loadTable() {
        view.tableModel.setRowCount(0);
        List<TonKho> list = tonKhoDAO.getAll();

        for (TonKho tonKho : list) {
            String tenKho = "Không xác định";
            Kho kho = khoDAO.getById(tonKho.getIdKho());
            if (kho != null) {
                tenKho = kho.getTenKho();
            }
            
            String maViTri = "Không xác định";
            if (tonKho.getIdViTri() != 0) {
                ViTri viTri = viTriDAO.getById(tonKho.getIdViTri());
                if (viTri != null) {
                    maViTri = viTri.getMaViTri();
                }
            }
            
            view.tableModel.addRow(new Object[]{
                tonKho.getId(),
                "SP-" + tonKho.getIdSanPham(),
                tenKho,
                maViTri,
                tonKho.getSoLuong(),
                getLoaiCapNhat(tonKho.getSoLuong()),
            });
        }
    }

    private String getLoaiCapNhat(int soLuong) {
        if (soLuong > 0) return "Nhập kho";
        else if (soLuong < 0) return "Xuất kho";
        else return "Điều chỉnh";
    }

    private void addEvents() {

        // Click bảng
        view.tblTonKho.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.tblTonKho.getSelectedRow();
                if (row >= 0) {
                    view.txtID.setText(view.tableModel.getValueAt(row, 0).toString());
                    
                    String spText = view.tableModel.getValueAt(row, 1).toString();
                    String idSanPham = spText.replace("SP-", "");
                    view.txtIdSanPham.setText(idSanPham);
                    
                    String tenKho = view.tableModel.getValueAt(row, 2).toString();
                    for (int i = 0; i < view.cbKho.getItemCount(); i++) {
                        String item = view.cbKho.getItemAt(i);
                        if (item.contains(tenKho)) {
                            view.cbKho.setSelectedIndex(i);
                            loadViTriComboBox();
                            break;
                        }
                    }
                    
                    String maViTri = view.tableModel.getValueAt(row, 3).toString();
                    for (int i = 0; i < view.cbViTri.getItemCount(); i++) {
                        String item = view.cbViTri.getItemAt(i);
                        if (item.contains(maViTri)) {
                            view.cbViTri.setSelectedIndex(i);
                            break;
                        }
                    }
                    
                    view.txtSoLuong.setText(view.tableModel.getValueAt(row, 4).toString());
                }
            }
        });

        view.cbKho.addActionListener(e -> loadViTriComboBox());
        
        view.btnLoadKho.addActionListener(e -> loadKhoComboBox());

        view.btnCapNhat.addActionListener(e -> {
            try {
                int idSanPham = Integer.parseInt(view.txtIdSanPham.getText().trim());
                
                if (view.cbKho.getSelectedIndex() == -1) {
                    javax.swing.JOptionPane.showMessageDialog(view, "Vui lòng chọn kho!");
                    return;
                }
                
                String selectedKho = (String) view.cbKho.getSelectedItem();
                int idKho = Integer.parseInt(selectedKho.split(" - ")[0]);
                
                int idViTri = 0;
                if (view.cbViTri.getSelectedIndex() > 0) {
                    String selectedViTri = (String) view.cbViTri.getSelectedItem();
                    idViTri = Integer.parseInt(selectedViTri.split(" - ")[0]);
                }
                
                int soLuong = Integer.parseInt(view.txtSoLuong.getText().trim());
                
                String loai = (String) view.cbLoaiCapNhat.getSelectedItem();
                if (loai.equals("Xuất kho")) {
                    soLuong = -soLuong;
                }
                
                TonKho tonKho = new TonKho();
                tonKho.setIdSanPham(idSanPham);
                tonKho.setIdKho(idKho);
                tonKho.setIdViTri(idViTri);
                tonKho.setSoLuong(soLuong);
                
                if (tonKhoDAO.capNhatTonKho(tonKho)) {
                    javax.swing.JOptionPane.showMessageDialog(view, "Cập nhật tồn kho thành công!");
                    loadTable();
                    clearForm();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(view, "Cập nhật tồn kho thất bại!");
                }
                
            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(view, "Vui lòng nhập số hợp lệ!");
            }
        });

        view.btnDieuChinh.addActionListener(e -> {
            try {
                int idSanPham = Integer.parseInt(view.txtIdSanPham.getText().trim());
                
                if (view.cbKho.getSelectedIndex() == -1) {
                    javax.swing.JOptionPane.showMessageDialog(view, "Vui lòng chọn kho!");
                    return;
                }
                
                String selectedKho = (String) view.cbKho.getSelectedItem();
                int idKho = Integer.parseInt(selectedKho.split(" - ")[0]);
                
                int idViTri = 0;
                if (view.cbViTri.getSelectedIndex() > 0) {
                    String selectedViTri = (String) view.cbViTri.getSelectedItem();
                    idViTri = Integer.parseInt(selectedViTri.split(" - ")[0]);
                }
                
                int soLuong = Integer.parseInt(view.txtSoLuong.getText().trim());
                
                int confirm = javax.swing.JOptionPane.showConfirmDialog(view,
                    "Điều chỉnh tồn kho sẽ ghi đè số lượng hiện tại. Tiếp tục?",
                    "Xác nhận",
                    javax.swing.JOptionPane.YES_NO_OPTION);

                if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                    TonKho tonKho = new TonKho();
                    tonKho.setIdSanPham(idSanPham);
                    tonKho.setIdKho(idKho);
                    tonKho.setIdViTri(idViTri);
                    tonKho.setSoLuong(soLuong);
                    
                    if (tonKhoDAO.dieuChinhTonKho(tonKho)) {
                        javax.swing.JOptionPane.showMessageDialog(view, "Điều chỉnh tồn kho thành công!");
                        loadTable();
                        clearForm();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(view, "Điều chỉnh tồn kho thất bại!");
                    }
                }
                
            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(view, "Vui lòng nhập số hợp lệ!");
            }
        });

        view.btnXoa.addActionListener(e -> {
            int row = view.tblTonKho.getSelectedRow();
            if (row >= 0) {
                int id = Integer.parseInt(view.tableModel.getValueAt(row, 0).toString());
                String spText = view.tableModel.getValueAt(row, 1).toString();
                String soLuong = view.tableModel.getValueAt(row, 4).toString();

                int confirm = javax.swing.JOptionPane.showConfirmDialog(view,
                    "Bạn có chắc chắn muốn xóa phiếu " + spText + " (SL: " + soLuong + ")?",
                    "Xác nhận",
                    javax.swing.JOptionPane.YES_NO_OPTION);

                if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                    if (tonKhoDAO.delete(id)) {
                        javax.swing.JOptionPane.showMessageDialog(view, "Xóa phiếu thành công!");
                        loadTable();
                        clearForm();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(view, "Xóa phiếu thất bại!");
                    }
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(view, "Vui lòng chọn phiếu cần xóa!");
            }
        });

        view.btnLamMoi.addActionListener(e -> clearForm());
    }

    private void clearForm() {
        view.txtID.setText("");
        view.txtIdSanPham.setText("");
        view.txtSoLuong.setText("");
        if (view.cbKho.getItemCount() > 0) {
            view.cbKho.setSelectedIndex(0);
        }
        view.tblTonKho.clearSelection();
    }
}