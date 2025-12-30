package controller;

import view.DanhmucView;
import view.DonviView;
import view.MainView;
import view.SanphamView;
import view.NhaCungCapView;
import view.KhachHangView;
import view.KhoView;
import view.ViTriKhoView;
import view.TonKhoView;
import view.DieuChinhTonView;
import javax.swing.JOptionPane;
import view.PhieuNhapView;
import view.PhieuXuatView;
import view.PhieuKiemKeView;
public class MainController {

    private MainView view;

    public MainController(MainView view) {
        this.view = view;
        addEvents();
    }

    private void addEvents() {
        // ========== QUẢN LÝ SẢN PHẨM ==========
        // Sự kiện Danh mục
        view.menuDanhMuc.addActionListener(e -> {
            DanhmucView dv = new DanhmucView();
            new DanhmucController(dv);
            dv.setVisible(true);
        });

        // Sự kiện Sản phẩm
        view.menuSanPham.addActionListener(e -> {
            SanphamView sp = new SanphamView();
            new SanphamController(sp);
            sp.setVisible(true);
        });

        // Sự kiện Đơn vị
        view.menuDonVi.addActionListener(e -> {
            DonviView dv = new DonviView();
            new DonviController(dv);
            dv.setVisible(true);
        });

        // ========== KHO & TỒN KHO ==========
        // Sự kiện Kho
        view.menuKho.addActionListener(e -> {
            KhoView kv = new KhoView();
            new KhoController(kv);
            kv.setVisible(true);
        });

        // Sự kiện Vị trí lưu trữ
        view.menuViTri.addActionListener(e -> {
            ViTriKhoView vtkv = new ViTriKhoView();
            new ViTriKhoController(vtkv);
            vtkv.setVisible(true);
        });

        // Sự kiện Tồn kho
        view.menuTonKho.addActionListener(e -> {
            TonKhoView tkv = new TonKhoView();
            new TonKhoController(tkv);
            tkv.setVisible(true);
        });
        
        // Thêm vào phương thức addEvents() trong MainController.java, sau sự kiện menuTonKho:
        // Sự kiện Điều chỉnh tồn
        view.menuDieuChinhTon.addActionListener(e -> {
            DieuChinhTonView dctv = new DieuChinhTonView();
            new DieuChinhTonController(dctv);
            dctv.setVisible(true);
        });

        // ========== KHÁCH HÀNG – NCC ==========
        // Sự kiện Khách hàng
        view.menuKhach.addActionListener(e -> {
            KhachHangView khV = new KhachHangView();
            new KhachHangController(khV);
            khV.setVisible(true);
        });

        // Sự kiện Nhà cung cấp
        view.menuNCC.addActionListener(e -> {
            NhaCungCapView nccV = new NhaCungCapView();
            new NhaCungCapController(nccV);
            nccV.setVisible(true);
        });

        // ========== NHẬP – KIỂM KÊ ==========
        view.menuNhap.addActionListener(e -> {
            PhieuNhapView pnv = new PhieuNhapView(); 
            new PhieuNhapController(pnv);         
            pnv.setVisible(true);               
        });

        // ========== XUAT ==========
        view.menuXuat.addActionListener(e -> {
            PhieuXuatView pxv = new PhieuXuatView(); 
            new PhieuXuatController(pxv);      
            pxv.setVisible(true);        
        }); 

        // Sự kiện Kiểm kê (tạm thời để trống)
        view.menuKiemKe.addActionListener(e -> {
            PhieuKiemKeView pkView = new PhieuKiemKeView();
            new PhieuKiemKeController(pkView);
            pkView.setVisible(true);
        });

        
        
        
        
        // ========== BÁO CÁO ==========
        // Sự kiện Báo cáo tổng hợp (tạm thời để trống)
        view.menuBaoCao.addActionListener(e -> {
            JOptionPane.showMessageDialog(view, "Chức năng Báo cáo tổng hợp đang phát triển...");
        });

        
        
        // Thêm chức năng Điều chỉnh tồn kho (tạm thời có thể mở qua menu Tồn kho)
        // Hoặc bạn có thể thêm menu item riêng
        
    }

    // Phương thức để mở form Điều chỉnh tồn kho
    public void openDieuChinhTon() {
        DieuChinhTonView dctv = new DieuChinhTonView();
        new DieuChinhTonController(dctv);
        dctv.setVisible(true);
    }
}