package view;

import javax.swing.*;
import java.awt.*;

public class ForgotPasswordView extends JFrame {

    public JTextField txtUser;
    public JPasswordField txtNewPass;
    public JButton btnReset;

    public ForgotPasswordView() {
        setTitle("Quên mật khẩu");
        setSize(350, 230);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 0, 8, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        panel.add(new JLabel("Username"), gbc);

        txtUser = new JTextField();
        gbc.gridy = 1;
        panel.add(txtUser, gbc);

        gbc.gridy = 2;
        panel.add(new JLabel("Mật khẩu mới"), gbc);

        txtNewPass = new JPasswordField();
        gbc.gridy = 3;
        panel.add(txtNewPass, gbc);

        btnReset = new JButton("Đổi mật khẩu");
        btnReset.setBackground(new Color(38, 166, 154));
        btnReset.setForeground(Color.WHITE);
        btnReset.setFocusPainted(false);
        gbc.gridy = 4;
        gbc.insets = new Insets(15, 0, 0, 0);
        panel.add(btnReset, gbc);

        add(panel);
        setVisible(true);
    }
}
