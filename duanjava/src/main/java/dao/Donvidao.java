package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Donvi;

public class Donvidao {

    DB db = new DB();

    // Lấy toàn bộ
    public ArrayList<Donvi> getAll() {
        ArrayList<Donvi> list = new ArrayList<>();

        String sql = "SELECT * FROM donvi";

        try (
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                list.add(new Donvi(
                        rs.getInt("id"),
                        rs.getString("ten")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Thêm
    public void insert(Donvi dv) {
        String sql = "INSERT INTO donvi(ten) VALUES (?)";

        try (
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, dv.getTendv());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Sửa
    public void update(Donvi dv) {
        String sql = "UPDATE donvi SET ten=? WHERE id=?";

        try (
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, dv.getTendv());
            ps.setInt(2, dv.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xóa
    public void delete(int id) {
        String sql = "DELETE FROM donvi WHERE id=?";

        try (
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tìm theo ID
    public Donvi findById(int id) {
        String sql = "SELECT * FROM donvi WHERE id=?";

        try (
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Donvi(
                        rs.getInt("id"),
                        rs.getString("ten")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
