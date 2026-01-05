package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.ForgotPasswordController;

public class LoginView extends JFrame {

    public JTextField txtUser;
    public JPasswordField txtPass;
    public JButton btnLogin;
    public JCheckBox chkRemember;
    public JLabel lblForgot, lblRegister;

    public LoginView() {
        setTitle("Login");
        setSize(720, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        JPanel main = new JPanel(new GridLayout(1, 2));
        add(main);

        /* ===== LEFT PANEL ===== */
        JPanel left = new JPanel();
        left.setBackground(new Color(178, 223, 219)); // xanh mint
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBorder(BorderFactory.createEmptyBorder(90, 40, 90, 40));

        JLabel lblWelcome = new JLabel("QUẢN LÝ HỆ THỐNG");
        lblWelcome.setForeground(new Color(0, 105, 92));
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JLabel lblSub = new JLabel("Đăng nhập để tiếp tục");
        lblSub.setForeground(new Color(0, 121, 107));
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        left.add(lblWelcome);
        left.add(Box.createVerticalStrut(15));
        left.add(lblSub);

        /* ===== RIGHT PANEL ===== */
        JPanel right = new JPanel(new GridBagLayout());
        right.setBackground(new Color(249, 252, 251)); // trắng ngà
        right.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 0, 8, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel lblTitle = new JLabel("Sign In");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        gbc.gridy = 0;
        right.add(lblTitle, gbc);

        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(font);
        gbc.gridy = 1;
        right.add(lblUser, gbc);

        txtUser = new JTextField();
        txtUser.setFont(font);
        txtUser.setPreferredSize(new Dimension(260, 32));
        gbc.gridy = 2;
        right.add(txtUser, gbc);

        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(font);
        gbc.gridy = 3;
        right.add(lblPass, gbc);

        txtPass = new JPasswordField();
        txtPass.setFont(font);
        txtPass.setPreferredSize(new Dimension(260, 32));
        gbc.gridy = 4;
        right.add(txtPass, gbc);

        chkRemember = new JCheckBox("Remember me");
        chkRemember.setBackground(right.getBackground());
        gbc.gridy = 5;
        right.add(chkRemember, gbc);

        // ===== FORGOT PASSWORD =====
        lblForgot = new JLabel("<HTML><U>Forgot password?</U></HTML>");
        lblForgot.setForeground(new Color(38, 166, 154));
        lblForgot.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lblForgot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ForgotPasswordView fp = new ForgotPasswordView();
                new ForgotPasswordController(fp);
            }
        });

        gbc.gridy = 6;
        right.add(lblForgot, gbc);

        // ===== LOGIN BUTTON =====
        btnLogin = new JButton("LOGIN");
        btnLogin.setBackground(new Color(38, 166, 154));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setFocusPainted(false);
        btnLogin.setPreferredSize(new Dimension(140, 36));

        gbc.gridy = 7;
        gbc.insets = new Insets(15, 0, 0, 0);
        right.add(btnLogin, gbc);

       

        main.add(left);
        main.add(right);

        setVisible(true);
    }
}
