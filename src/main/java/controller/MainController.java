package controller;

import view.*;
import javax.swing.JOptionPane;

public class MainController {

    private MainView view;

    public MainController(MainView view) {
        this.view = view;
        addEvents();
    }

    private void addEvents() {
        
       
        view.menuDanhMuc.addActionListener(e -> {
            DanhmucView dv = new DanhmucView();
            new DanhmucController(dv);
            dv.setVisible(true);
        });

        // Sự kiện Đơn vị
        view.menuDonVi.addActionListener(e -> {
            DonviView dv = new DonviView();
            new DonviController(dv);
            dv.setVisible(true);
        });

        // Sự kiện Sản phẩm
        view.menuSanPham.addActionListener(e -> {
            SanphamView sp = new SanphamView();
            new SanphamController(sp);
            sp.setVisible(true);
        });
        
        // Sự kiện Kho
        view.menuKho.addActionListener(e -> {
            KhoView kv = new KhoView();
            new KhoController(kv);
            kv.setVisible(true);
        });
        
        // Sự kiện Vị trí
        view.menuViTri.addActionListener(e -> {
            ViTriView vtv = new ViTriView();
            new ViTriController(vtv);
            vtv.setVisible(true);
        });
        
        // Sự kiện Tồn kho
        view.menuTonKho.addActionListener(e -> {
            TonKhoView tkv = new TonKhoView();
            new TonKhoController(tkv);
            tkv.setVisible(true);
        });

        // Sự kiện Nhập hàng
        view.menuNhap.addActionListener(e -> {
            PhieuNhapView pnv = new PhieuNhapView(); 
            new PhieuNhapController(pnv);         
            pnv.setVisible(true);                
        });

        // Sự kiện Xuất hàng
        view.menuXuat.addActionListener(e -> {
            PhieuXuatView pxv = new PhieuXuatView(); 
            new PhieuXuatController(pxv);      
            pxv.setVisible(true);        
        }); 

        // Sự kiện Kiểm kê 
        view.menuKiemKe.addActionListener(e -> {
            PhieuKiemKeView pkView = new PhieuKiemKeView();
            new PhieuKiemKeController(pkView);
            pkView.setVisible(true);
        });
            
        // Sự kiện Nhà cung cấp
        view.menuNCC.addActionListener(e -> {
            NhaCungCapView nccV = new NhaCungCapView();
            new NhaCungCapController(nccV);
            nccV.setVisible(true);
        });

        // Sự kiện Khách hàng
        view.menuKhach.addActionListener(e -> {
            KhachHangView khV = new KhachHangView();
            new KhachHangController(khV);
            khV.setVisible(true);
        });

        // Sự kiện Báo cáo
        view.menuBaoCao.addActionListener(e -> {
            BaoCaoView bcView = new BaoCaoView();
            new BaoCaoController(bcView);
            bcView.setVisible(true);
        });
    }   
}       