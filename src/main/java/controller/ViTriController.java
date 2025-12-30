package controller;

import dao.ViTriDAO;
import dao.KhoDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import model.ViTri;
import model.Kho;
import view.ViTriView;
import javax.swing.DefaultComboBoxModel;

public class ViTriController {

    private ViTriView view;
    private ViTriDAO viTriDAO;
    private KhoDAO khoDAO;

    public ViTriController(ViTriView view) {
        this.view = view;
        this.viTriDAO = new ViTriDAO();
        this.khoDAO = new KhoDAO();

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
    }

    private void loadTable() {
        view.tableModel.setRowCount(0);
        List<ViTri> list = viTriDAO.getAll();

        for (ViTri viTri : list) {
            String tenKho = "Không xác định";
            Kho kho = khoDAO.getById(viTri.getIdKho());
            if (kho != null) {
                tenKho = kho.getTenKho();
            }
            
            view.tableModel.addRow(new Object[]{
                viTri.getId(),
                viTri.getMaViTri(),
                tenKho,
                viTri.getGhiChu()
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
                    
                    String tenKho = view.tableModel.getValueAt(row, 2).toString();
                    for (int i = 0; i < view.cbKho.getItemCount(); i++) {
                        String item = view.cbKho.getItemAt(i);
                        if (item.contains(tenKho)) {
                            view.cbKho.setSelectedIndex(i);
                            break;
                        }
                    }
                    
                    view.txtGhiChu.setText(view.tableModel.getValueAt(row, 3).toString());
                }
            }
        });

        view.btnLoadKho.addActionListener(e -> loadKhoComboBox());

        view.btnThem.addActionListener(e -> {
            String maViTri = view.txtMaViTri.getText().trim();
            String ghiChu = view.txtGhiChu.getText().trim();
            
            if (view.cbKho.getSelectedIndex() == -1) {
                javax.swing.JOptionPane.showMessageDialog(view, "Vui lòng chọn kho!");
                return;
            }
            
            if (maViTri.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(view, "Mã vị trí không được để trống!");
                return;
            }

            String selected = (String) view.cbKho.getSelectedItem();
            int idKho = Integer.parseInt(selected.split(" - ")[0]);

            ViTri viTri = new ViTri();
            viTri.setMaViTri(maViTri);
            viTri.setIdKho(idKho);
            viTri.setGhiChu(ghiChu);

            if (viTriDAO.insert(viTri)) {
                javax.swing.JOptionPane.showMessageDialog(view, "Thêm vị trí thành công!");
                loadTable();
                clearForm();
            } else {
                javax.swing.JOptionPane.showMessageDialog(view, "Thêm vị trí thất bại!");
            }
        });

        view.btnSua.addActionListener(e -> {
            if (view.txtID.getText().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(view, "Vui lòng chọn vị trí cần sửa!");
                return;
            }

            int id = Integer.parseInt(view.txtID.getText());
            String maViTri = view.txtMaViTri.getText().trim();
            String ghiChu = view.txtGhiChu.getText().trim();
            
            if (view.cbKho.getSelectedIndex() == -1) {
                javax.swing.JOptionPane.showMessageDialog(view, "Vui lòng chọn kho!");
                return;
            }
            
            if (maViTri.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(view, "Mã vị trí không được để trống!");
                return;
            }

            String selected = (String) view.cbKho.getSelectedItem();
            int idKho = Integer.parseInt(selected.split(" - ")[0]);

            int confirm = javax.swing.JOptionPane.showConfirmDialog(view,
                "Bạn có chắc chắn muốn sửa vị trí này?",
                "Xác nhận",
                javax.swing.JOptionPane.YES_NO_OPTION);

            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                ViTri viTri = new ViTri();
                viTri.setId(id);
                viTri.setMaViTri(maViTri);
                viTri.setIdKho(idKho);
                viTri.setGhiChu(ghiChu);

                if (viTriDAO.update(viTri)) {
                    javax.swing.JOptionPane.showMessageDialog(view, "Sửa vị trí thành công!");
                    loadTable();
                    clearForm();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(view, "Sửa vị trí thất bại!");
                }
            }
        });

        view.btnXoa.addActionListener(e -> {
            int row = view.tblViTri.getSelectedRow();
            if (row >= 0) {
                int id = Integer.parseInt(view.tableModel.getValueAt(row, 0).toString());
                String maViTri = view.tableModel.getValueAt(row, 1).toString();

                int confirm = javax.swing.JOptionPane.showConfirmDialog(view,
                    "Bạn có chắc chắn muốn xóa vị trí '" + maViTri + "'?",
                    "Xác nhận",
                    javax.swing.JOptionPane.YES_NO_OPTION);

                if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                    if (viTriDAO.delete(id)) {
                        javax.swing.JOptionPane.showMessageDialog(view, "Xóa vị trí thành công!");
                        loadTable();
                        clearForm();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(view, "Xóa vị trí thất bại!");
                    }
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(view, "Vui lòng chọn vị trí cần xóa!");
            }
        });

        view.btnLamMoi.addActionListener(e -> clearForm());
    }

    private void clearForm() {
        view.txtID.setText("");
        view.txtMaViTri.setText("");
        view.txtGhiChu.setText("");
        if (view.cbKho.getItemCount() > 0) {
            view.cbKho.setSelectedIndex(0);
        }
        view.tblViTri.clearSelection();
    }
}