package controller;

import view.DanhmucView;
import view.DonviView;
import view.MainView;
import view.SanphamView;
import view.NhaCungCapView;
import view.KhachHangView;
import view.KhoView;
import view.ViTriView;
import view.TonKhoView;

public class MainController {

    private MainView view;

    public MainController(MainView view) {
        this.view = view;
        addEvents();
    }

    private void addEvents() {
        // Sự kiện Danh mục
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
        
        // ========== ĐÂY LÀ PHẦN CỦA BẠN (NGƯỜI 4) ==========
        
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
    }
}