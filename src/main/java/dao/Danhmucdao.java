package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Danhmuc;

public class Danhmucdao {

    DB db = new DB();

    // Lấy tất cả danh mục
    public ArrayList<Danhmuc> getAll() {
        ArrayList<Danhmuc> list = new ArrayList<>();
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM danhmuc");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Danhmuc(
                        rs.getInt("id"),
                        rs.getString("ten")
                ));
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm danh mục
    public void insert(Danhmuc dm) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO danhmuc(ten) VALUES (?)"
            );

            ps.setString(1, dm.getTen());
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cập nhật danh mục
    public void update(Danhmuc dm) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE danhmuc SET ten=? WHERE id=?"
            );

            ps.setString(1, dm.getTen());
            ps.setInt(2, dm.getId());
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xóa danh mục
    public void delete(String id) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM danhmuc WHERE id=?"
            );

            ps.setString(1, id);
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tìm theo ID
    public Danhmuc findById(String id) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM danhmuc WHERE id=?"
            );

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Danhmuc dm = new Danhmuc(
                        rs.getInt("id"),
                        rs.getString("ten")
                );
                conn.close();
                return dm;
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}