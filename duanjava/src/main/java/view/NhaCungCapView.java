package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NhaCungCapView extends JFrame {
    public JTextField txtID, txtTen, txtSdt, txtDiaChi, txtEmail;
    public JButton btnThem, btnSua, btnXoa, btnLamMoi;
    public JTable tblNCC;
    public DefaultTableModel tableModel;

    public NhaCungCapView() {
        setTitle("Quản lý Nhà cung cấp");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(230, 248, 245));
        JLabel lblTitle = new JLabel("QUẢN LÝ NHÀ CUNG CẤP");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 128, 128));
        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        // Main Panel
        JPanel pnlMain = new JPanel(new BorderLayout());
        
        // Form Panel (Trái)
        JPanel pnlLeft = new JPanel();
        pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));
        pnlLeft.setPreferredSize(new Dimension(300, 0));
        pnlLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtID = new JTextField(); txtID.setEnabled(false);
        txtTen = new JTextField();
        txtSdt = new JTextField();
        txtDiaChi = new JTextField();
        txtEmail = new JTextField();

        pnlLeft.add(new JLabel("ID:")); pnlLeft.add(txtID);
        pnlLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlLeft.add(new JLabel("Tên nhà cung cấp:")); pnlLeft.add(txtTen);
        pnlLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlLeft.add(new JLabel("Số điện thoại:")); pnlLeft.add(txtSdt);
        pnlLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlLeft.add(new JLabel("Địa chỉ:")); pnlLeft.add(txtDiaChi);
        pnlLeft.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlLeft.add(new JLabel("Email:")); pnlLeft.add(txtEmail);

        // Buttons
        JPanel pnlButtons = new JPanel(new GridLayout(2, 2, 5, 5));
        btnThem = new JButton("Thêm"); btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa"); btnLamMoi = new JButton("Làm mới");
        pnlButtons.add(btnThem); pnlButtons.add(btnSua);
        pnlButtons.add(btnXoa); pnlButtons.add(btnLamMoi);
        pnlLeft.add(Box.createRigidArea(new Dimension(0, 20)));
        pnlLeft.add(pnlButtons);

        pnlMain.add(pnlLeft, BorderLayout.WEST);

        // Table (Phải)
        tableModel = new DefaultTableModel(new String[]{"ID", "Tên", "SĐT", "Địa chỉ", "Email"}, 0);
        tblNCC = new JTable(tableModel);
        pnlMain.add(new JScrollPane(tblNCC), BorderLayout.CENTER);

        add(pnlMain, BorderLayout.CENTER);
        setVisible(true);
    }
}