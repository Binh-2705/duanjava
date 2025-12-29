package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DonviView extends JFrame {

    public JTextField txtID, txtTen;
    public JButton btnThem, btnSua, btnXoa, btnLamMoi;
    public JTable tblDonVi;
    public DefaultTableModel tableModel;

    public DonviView() {

        setTitle("Quản lý Đơn vị tính");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Font fontLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontButton = new Font("Segoe UI", Font.BOLD, 13);

        // ===== HEADER =====
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(230, 248, 245));
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(180, 220, 215)));

        JLabel lblTitle = new JLabel("QUẢN LÝ ĐƠN VỊ TÍNH");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 128, 128));

        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        // ===== MAIN =====
        JPanel pnlMain = new JPanel(new BorderLayout());

        // LEFT: FORM + BUTTONS
        JPanel pnlLeft = new JPanel(new BorderLayout());
        pnlLeft.setPreferredSize(new Dimension(320, 0));
        pnlLeft.setBackground(Color.WHITE);
        pnlLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // FORM
        JPanel pnlForm = new JPanel();
        pnlForm.setLayout(new BoxLayout(pnlForm, BoxLayout.Y_AXIS));
        pnlForm.setBackground(Color.WHITE);

        JLabel lblID = new JLabel("ID:");
        lblID.setFont(fontLabel);
        txtID = new JTextField();
        txtID.setFont(fontLabel);
        txtID.setEnabled(false);
        txtID.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel lblTen = new JLabel("Tên đơn vị:");
        lblTen.setFont(fontLabel);
        txtTen = new JTextField();
        txtTen.setFont(fontLabel);
        txtTen.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        pnlForm.add(lblID);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlForm.add(txtID);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 10)));

        pnlForm.add(lblTen);
        pnlForm.add(Box.createRigidArea(new Dimension(0, 5)));
        pnlForm.add(txtTen);

        pnlLeft.add(pnlForm, BorderLayout.NORTH);

        // BUTTONS
        JPanel pnlButtons = new JPanel(new GridLayout(4, 1, 0, 10));
        pnlButtons.setBackground(Color.WHITE);
        pnlButtons.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLamMoi = new JButton("Làm mới");

        JButton[] arrButtons = {btnThem, btnSua, btnXoa, btnLamMoi};
        Color btnColor = new Color(0, 150, 136);

        for (JButton btn : arrButtons) {
            btn.setBackground(btnColor);
            btn.setForeground(Color.WHITE);
            btn.setFont(fontButton);
            btn.setFocusPainted(false);
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
            pnlButtons.add(btn);
        }

        pnlLeft.add(pnlButtons, BorderLayout.SOUTH);
        pnlMain.add(pnlLeft, BorderLayout.WEST);

        // TABLE
        tableModel = new DefaultTableModel(new String[]{"ID", "Tên đơn vị"}, 0);
        tblDonVi = new JTable(tableModel);
        tblDonVi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblDonVi.setRowHeight(25);

        tblDonVi.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblDonVi.getTableHeader().setBackground(new Color(0, 150, 136));
        tblDonVi.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tblDonVi);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        pnlMain.add(scroll, BorderLayout.CENTER);

        add(pnlMain, BorderLayout.CENTER);
        setVisible(true);
    }
}
