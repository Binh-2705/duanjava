package controller;

import model.UserModel;
import view.ForgotPasswordView;

import javax.swing.*;

public class ForgotPasswordController {

    public ForgotPasswordController(ForgotPasswordView view) {
        UserModel model = new UserModel();

        view.btnReset.addActionListener(e -> {
            String user = view.txtUser.getText();
            String newPass = new String(view.txtNewPass.getPassword());

            if (user.isEmpty() || newPass.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Không được để trống!");
                return;
            }

            if (!model.checkUserExists(user)) {
                JOptionPane.showMessageDialog(view, "Tài khoản không tồn tại!");
                return;
            }

            if (model.updatePassword(user, newPass)) {
                JOptionPane.showMessageDialog(view, "Đổi mật khẩu thành công!");
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "Đổi mật khẩu thất bại!");
            }
        });
    }
}
