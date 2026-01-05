package view;

import java.awt.*;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainView extends JFrame {

    public JMenuItem menuDanhMuc, menuSanPham, menuDonVi;
    public JMenuItem menuKho, menuViTri, menuTonKho, menuDieuChinhTon;
    public JMenuItem menuNhap, menuXuat, menuKiemKe;
    public JMenuItem menuKhach, menuNCC, menuBaoCao;

    public MainView() {
        setTitle("PHẦN MỀM QUẢN LÝ KHO HÀNG");
        setSize(1000, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ========== MENU BAR (Flat UI) ==========
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(245, 245, 245));
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));

        Font menuFont = new Font("Segoe UI", Font.BOLD, 14);

        JMenu menu1 = new JMenu("Quản lý Sản phẩm");
        JMenu menu2 = new JMenu("Kho & Tồn kho");
        JMenu menu3 = new JMenu("Nhập – Xuất – Kiểm kê");
        JMenu menu4 = new JMenu("Khách hàng – NCC – Báo cáo");

        menu1.setFont(menuFont);
        menu2.setFont(menuFont);
        menu3.setFont(menuFont);
        menu4.setFont(menuFont);

        menuDanhMuc = new JMenuItem("Danh mục sản phẩm");
        menuSanPham = new JMenuItem("Sản phẩm");
        menuDonVi = new JMenuItem("Đơn vị tính");
        menuKho = new JMenuItem("Kho");
        menuViTri = new JMenuItem("Vị trí lưu trữ");
        menuTonKho = new JMenuItem("Tồn kho");
        menuNhap = new JMenuItem("Phiếu nhập");
        menuXuat = new JMenuItem("Phiếu xuất");
        menuKiemKe = new JMenuItem("Kiểm kê");

        menuKhach = new JMenuItem("Khách hàng");
        menuNCC = new JMenuItem("Nhà cung cấp");
        menuBaoCao = new JMenuItem("Báo cáo tổng hợp");

        menu1.add(menuDanhMuc);
        menu1.add(menuSanPham);
        menu1.add(menuDonVi);

        menu2.add(menuKho);
        menu2.add(menuViTri);
        menu2.add(menuTonKho);


        menu3.add(menuNhap);
        menu3.add(menuXuat);
        menu3.add(menuKiemKe);

        menu4.add(menuKhach);
        menu4.add(menuNCC);
        menu4.add(menuBaoCao);

        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        menuBar.add(menu4);

        setJMenuBar(menuBar);

        // ========== HEADER CARD STYLE ==========
        JPanel header = new JPanel();
        header.setBackground(new Color(230, 248, 245)); 
        header.setBorder(BorderFactory.createLineBorder(new Color(180, 220, 215), 2));
        header.setPreferredSize(new Dimension(0, 110));
        header.setLayout(new BorderLayout());

        JLabel lbTitle = new JLabel("PHẦN MỀM QUẢN LÝ KHO HÀNG", SwingConstants.CENTER);
        lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lbTitle.setForeground(new Color(0, 128, 128));

        header.add(lbTitle, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);

        // ========== CENTER PLACEHOLDER ==========
        JLabel center = new JLabel("Chọn chức năng từ menu để bắt đầu", SwingConstants.CENTER);
        center.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        center.setForeground(new Color(100, 100, 100));

        add(center, BorderLayout.CENTER);

        // ========== FOOTER ==========
        JLabel footer = new JLabel("© 2025 - Quản lý kho hàng - Bình Nguyễn", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        footer.setForeground(new Color(120, 120, 120));
        footer.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));

        add(footer, BorderLayout.SOUTH);

        setVisible(true);
    }
}

