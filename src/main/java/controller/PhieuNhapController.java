package controller;

import dao.PhieuNhapDAO;
import dao.NhaCungCapDAO; 
import model.PhieuNhap;
import model.NhaCungCap; // Import model NCC
import view.PhieuNhapView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PhieuNhapController {
    private PhieuNhapView view;
    private PhieuNhapDAO phieuNhapDAO;
    private NhaCungCapDAO nccDAO; 

    // List tạm để lưu NCC nhằm ánh xạ giữa Tên (trên combobox) và ID (trong CSDL)
    private List<NhaCungCap> listNCC; 

    public PhieuNhapController(PhieuNhapView view) {
        this.view = view;
        this.phieuNhapDAO = new PhieuNhapDAO();
        this.nccDAO = new NhaCungCapDAO(); // Khởi tạo DAO NCC

        // 1. Load dữ liệu
        loadComboBoxNCC();
        loadTable();
        
        // 2. Gán sự kiện
        addEvents();
    }

    // --- HÀM LOAD DỮ LIỆU ---

    private void loadComboBoxNCC() {
        // Load danh sách NCC vào ComboBox
        listNCC = nccDAO.getAll(); // Giả sử NhaCungCapDAO có hàm getAll()
        view.cboNCC.removeAllItems();
        for (NhaCungCap ncc : listNCC) {
            view.cboNCC.addItem(ncc.getTen()); // Chỉ hiển thị tên
        }
    }
    
    // Hàm phụ trợ: Lấy ID từ tên NCC đang chọn trong ComboBox
    private int getSelectedNccID() {
        int index = view.cboNCC.getSelectedIndex();
        if (index >= 0 && index < listNCC.size()) {
            return listNCC.get(index).getId();
        }
        return -1; // Lỗi hoặc chưa chọn
    }

    // Hàm phụ trợ: Set ComboBox chọn đúng tên NCC dựa vào ID (dùng khi click bảng)
    private void setSelectedNccByID(int idNCC) {
        for (int i = 0; i < listNCC.size(); i++) {
            if (listNCC.get(i).getId() == idNCC) {
                view.cboNCC.setSelectedIndex(i);
                break;
            }
        }
    }

    private void loadTable() {
        DefaultTableModel model = (DefaultTableModel) view.tblPhieuNhap.getModel();
        model.setRowCount(0);
        List<PhieuNhap> list = phieuNhapDAO.getAll();
        for (PhieuNhap pn : list) {
            // Để hiển thị tên NCC thay vì ID, cần tìm tên trong listNCC
            String tenNCC = "N/A";
            for(NhaCungCap ncc : listNCC) {
                if(ncc.getId() == pn.getIdNCC()) {
                    tenNCC = ncc.getTen();
                    break;
                }
            }
            
            model.addRow(new Object[]{
                pn.getId(), 
                pn.getSoPhieu(), 
                pn.getNgayNhap(), 
                pn.getIdNCC(), // Cột này có thể ẩn hoặc để nguyên
                pn.getGhiChu()
            });
        }
    }

    // --- HÀM SỰ KIỆN ---

    private void addEvents() {
        
        // 1. SỰ KIỆN CLICK BẢNG (Đưa dữ liệu lên Form)
        view.tblPhieuNhap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.tblPhieuNhap.getSelectedRow();
                if (row >= 0) {
                    // Lấy dữ liệu từ bảng
                    String id = view.tblPhieuNhap.getValueAt(row, 0).toString();
                    String soPhieu = view.tblPhieuNhap.getValueAt(row, 1).toString();
                    Object objDate = view.tblPhieuNhap.getValueAt(row, 2); // Ngày nhập
                    int idNCC = Integer.parseInt(view.tblPhieuNhap.getValueAt(row, 3).toString());
                    String ghiChu = view.tblPhieuNhap.getValueAt(row, 4).toString();

                    // Đưa lên Form
                    view.txtID.setText(id);
                    view.txtSoPhieu.setText(soPhieu);
                    view.txtGhiChu.setText(ghiChu);
                    
                    // Xử lý Ngày nhập (JSpinner)
                    if (objDate instanceof Date) {
                        view.spNgayNhap.setValue((Date) objDate);
                    } else if (objDate instanceof java.sql.Timestamp) {
                         view.spNgayNhap.setValue(new Date(((java.sql.Timestamp)objDate).getTime()));
                    }

                    // Xử lý ComboBox NCC
                    setSelectedNccByID(idNCC);
                }
            }
        });

        // 2. NÚT THÊM
        view.btnThem.addActionListener(e -> {
            try {
                // Lấy dữ liệu
                String soPhieu = view.txtSoPhieu.getText();
                Date ngayNhap = (Date) view.spNgayNhap.getValue(); // Lấy từ Spinner
                int idNCC = getSelectedNccID();
                String ghiChu = view.txtGhiChu.getText();

                // Validate cơ bản
                if (soPhieu.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Vui lòng nhập số phiếu!");
                    return;
                }
                if (idNCC == -1) {
                    JOptionPane.showMessageDialog(view, "Vui lòng chọn nhà cung cấp!");
                    return;
                }

                PhieuNhap pn = new PhieuNhap();
                pn.setSoPhieu(soPhieu);
                pn.setNgayNhap(ngayNhap);
                pn.setIdNCC(idNCC);
                pn.setGhiChu(ghiChu);

                // Insert
                int idMoi = phieuNhapDAO.insert(pn);
                if (idMoi > 0) {
                    JOptionPane.showMessageDialog(view, "Thêm thành công! ID: " + idMoi);
                    loadTable();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Thêm thất bại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
            }
        });

        // 3. NÚT SỬA
        view.btnSua.addActionListener(e -> {
            try {
                if (view.txtID.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Vui lòng chọn phiếu cần sửa!");
                    return;
                }

                int id = Integer.parseInt(view.txtID.getText());
                String soPhieu = view.txtSoPhieu.getText();
                Date ngayNhap = (Date) view.spNgayNhap.getValue();
                int idNCC = getSelectedNccID();
                String ghiChu = view.txtGhiChu.getText();

                PhieuNhap pn = new PhieuNhap();
                pn.setId(id);
                pn.setSoPhieu(soPhieu);
                pn.setNgayNhap(ngayNhap);
                pn.setIdNCC(idNCC);
                pn.setGhiChu(ghiChu);

                phieuNhapDAO.update(pn);
                JOptionPane.showMessageDialog(view, "Cập nhật thành công!");
                loadTable();
                clearForm();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // 4. NÚT XÓA
        view.btnXoa.addActionListener(e -> {
            int row = view.tblPhieuNhap.getSelectedRow();
            if (row >= 0) {
                int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc muốn xóa phiếu này?");
                if (confirm == JOptionPane.YES_OPTION) {
                    int id = (int) view.tblPhieuNhap.getValueAt(row, 0);
                    phieuNhapDAO.delete(id);
                    loadTable();
                    clearForm();
                    JOptionPane.showMessageDialog(view, "Đã xóa!");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng cần xóa!");
            }
        });

        // 5. NÚT LÀM MỚI
        view.btnLamMoi.addActionListener(e -> clearForm());
        
        
        view.tblPhieuNhap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // ... code lấy dữ liệu lên form cũ của bạn giữ nguyên ...

                // THÊM: Nếu nhấn đúp chuột 2 lần thì hiện bảng chi tiết
                if (e.getClickCount() == 2) {
                    int row = view.tblPhieuNhap.getSelectedRow();
                    if (row >= 0) {
                        int idPN = Integer.parseInt(view.tblPhieuNhap.getValueAt(row, 0).toString());
                        String soPhieu = view.tblPhieuNhap.getValueAt(row, 1).toString();
                    }
                }
            }
        });

    }

    private void clearForm() {
        view.txtID.setText("");
        view.txtSoPhieu.setText("");
        view.txtGhiChu.setText("");
        view.spNgayNhap.setValue(new Date()); // Reset về ngày hiện tại
        if (view.cboNCC.getItemCount() > 0) {
            view.cboNCC.setSelectedIndex(0);
        }
        view.tblPhieuNhap.clearSelection();
    }

}