package dao;

import java.sql.*;
import java.util.ArrayList;
import model.KhachHang;

public class KhachHangDAO {
    DB db = new DB();

    public ArrayList<KhachHang> getAll() {
        ArrayList<KhachHang> list = new ArrayList<>();
        try (Connection conn = db.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM KhachHang");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new KhachHang(
                    rs.getInt("id"), rs.getString("ten"),
                    rs.getString("sdt"), rs.getString("diaChi"), rs.getString("email")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void insert(KhachHang kh) {
        try (Connection conn = db.getConnection()) {
            String sql = "INSERT INTO KhachHang(ten, sdt, diaChi, email) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kh.getTen());
            ps.setString(2, kh.getSdt());
            ps.setString(3, kh.getDiaChi());
            ps.setString(4, kh.getEmail());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(KhachHang kh) {
        try (Connection conn = db.getConnection()) {
            String sql = "UPDATE KhachHang SET ten=?, sdt=?, diaChi=?, email=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kh.getTen());
            ps.setString(2, kh.getSdt());
            ps.setString(3, kh.getDiaChi());
            ps.setString(4, kh.getEmail());
            ps.setInt(5, kh.getId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(String id) {
        try (Connection conn = db.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM KhachHang WHERE id=?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}