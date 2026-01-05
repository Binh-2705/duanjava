package model;

import dao.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserModel {

    // ===== ĐĂNG NHẬP =====
    public boolean checkLogin(String username, String password) {
        DB db = new DB();
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ===== KIỂM TRA USER TỒN TẠI (QUÊN MK) =====
    public boolean checkUserExists(String username) {
        DB db = new DB();
        String sql = "SELECT * FROM users WHERE username=?";
        try (
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ===== CẬP NHẬT MẬT KHẨU =====
    public boolean updatePassword(String username, String newPassword) {
        DB db = new DB();
        String sql = "UPDATE users SET password=? WHERE username=?";
        try (
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, newPassword);
            ps.setString(2, username);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
