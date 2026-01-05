package controller;

import model.UserModel;
import view.LoginView;
import view.MainView;

import javax.swing.*;

public class LoginController {

    private LoginView view;
    private UserModel model;

    public LoginController(LoginView view) {
        this.view = view;
        this.model = new UserModel();

        view.btnLogin.addActionListener(e -> login());
    }

    private void login() {
        String user = view.txtUser.getText();
        String pass = new String(view.txtPass.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Không được để trống!");
            return;
        }

        if (model.checkLogin(user, pass)) {
            JOptionPane.showMessageDialog(view, "Đăng nhập thành công!");

            view.dispose(); // đóng form login

            MainView mv = new MainView();
            mv.setVisible(true);
            new MainController(mv); // controller chính

        } else {
            JOptionPane.showMessageDialog(view, "Sai tài khoản hoặc mật khẩu!");
        }
    }
}
