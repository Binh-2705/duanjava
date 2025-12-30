package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PhieuNhapView extends JFrame {

    // ===== FORM =====
    public JTextField txtID, txtSoPhieu;
    public JTextArea txtGhiChu;
    public JComboBox<String> cboNCC;
    public JSpinner spNgayNhap;

    // ===== BUTTON =====
    // Đã thêm btnXemChiTiet vào đây
    public JButton btnThem, btnSua, btnXoa, btnLamMoi, btnXemChiTiet; 

    // ===== TABLE =====
    public JTable tblPhieuNhap;
    public DefaultTableModel tableModel;

    public PhieuNhapView() {
        setTitle("Quản lý Phiếu Nhập");
        setSize(1100, 600); // Tăng nhẹ kích thước để bảng rộng rãi hơn
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Font fontLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontButton = new Font("Segoe UI", Font.BOLD, 13);

        /* ================= HEADER ================= */
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(230, 248, 245));
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(180, 220, 215)));

        JLabel lblTitle = new JLabel("QUẢN LÝ PHIẾU NHẬP");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 128, 128));

        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        /* ================= MAIN ================= */
        JPanel pnlMain = new JPanel(new BorderLayout());

        /* ================= LEFT (FORM) ================= */
        JPanel pnlLeft = new JPanel(new BorderLayout());
        pnlLeft.setPreferredSize(new Dimension(350, 0));
        pnlLeft.setBackground(Color.WHITE);
        pnlLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlForm = new JPanel();
        pnlForm.setLayout(new BoxLayout(pnlForm, BoxLayout.Y_AXIS));
        pnlForm.setBackground(Color.WHITE);

        pnlForm.add(createLabel("ID:", fontLabel));
        txtID = createTextField(fontLabel, false);
        pnlForm.add(txtID);

        pnlForm.add(Box.createVerticalStrut(10));
        pnlForm.add(createLabel("Số phiếu:", fontLabel));
        txtSoPhieu = createTextField(fontLabel, true);
        pnlForm.add(txtSoPhieu);

        pnlForm.add(Box.createVerticalStrut(10));
        pnlForm.add(createLabel("Ngày nhập:", fontLabel));
        spNgayNhap = new JSpinner(new SpinnerDateModel());
        spNgayNhap.setEditor(new JSpinner.DateEditor(spNgayNhap, "dd/MM/yyyy HH:mm"));
        spNgayNhap.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        pnlForm.add(spNgayNhap);

        pnlForm.add(Box.createVerticalStrut(10));
        pnlForm.add(createLabel("Nhà cung cấp:", fontLabel));
        cboNCC = new JComboBox<>();
        cboNCC.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        pnlForm.add(cboNCC);

        pnlForm.add(Box.createVerticalStrut(10));
        pnlForm.add(createLabel("Ghi chú:", fontLabel));
        txtGhiChu = new JTextArea(3, 20);
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setWrapStyleWord(true);
        JScrollPane spNote = new JScrollPane(txtGhiChu);
        spNote.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        pnlForm.add(spNote);

        pnlLeft.add(pnlForm, BorderLayout.NORTH);

        /* ================= BUTTON ================= */
        // Sửa GridLayout từ 4 thành 5 dòng để chứa thêm nút mới
        JPanel pnlButtons = new JPanel(new GridLayout(5, 1, 0, 10)); 
        pnlButtons.setBackground(Color.WHITE);
        pnlButtons.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLamMoi = new JButton("Làm mới");
        btnXemChiTiet = new JButton("Xem chi tiết phiếu"); // Nút mới

        // Thêm nút mới vào mảng để định dạng màu sắc
        JButton[] btns = {btnThem, btnSua, btnXoa, btnLamMoi, btnXemChiTiet};
        Color btnColor = new Color(0, 150, 136);

        for (JButton btn : btns) {
            btn.setBackground(btnColor);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setFont(fontButton);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            pnlButtons.add(btn);
        }

        pnlLeft.add(pnlButtons, BorderLayout.SOUTH);
        pnlMain.add(pnlLeft, BorderLayout.WEST);

        /* ================= TABLE ================= */
        tableModel = new DefaultTableModel(
                new String[]{"ID", "Số phiếu", "Ngày nhập", "ID NCC", "Ghi chú"}, 0
        );
        tblPhieuNhap = new JTable(tableModel);
        tblPhieuNhap.setRowHeight(30);
        tblPhieuNhap.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblPhieuNhap.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tblPhieuNhap.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblPhieuNhap.getTableHeader().setBackground(new Color(0, 150, 136));
        tblPhieuNhap.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tblPhieuNhap);
        pnlMain.add(scroll, BorderLayout.CENTER);

        add(pnlMain, BorderLayout.CENTER);
        setVisible(true);
    }

    private JLabel createLabel(String text, Font font) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(font);
        return lbl;
    }

    private JTextField createTextField(Font font, boolean enable) {
        JTextField txt = new JTextField();
        txt.setFont(font);
        txt.setEnabled(enable);
        txt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        return txt;
    }
}