package dao;

import java.sql.*;
import java.util.ArrayList;
import model.NhaCungCap;

public class NhaCungCapDAO {
    DB db = new DB();

    public ArrayList<NhaCungCap> getAll() {
        ArrayList<NhaCungCap> list = new ArrayList<>();
        try (Connection conn = db.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM NhaCungCap");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new NhaCungCap(
                    rs.getInt("id"), rs.getString("ten"),
                    rs.getString("sdt"), rs.getString("diaChi"), rs.getString("email")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void insert(NhaCungCap ncc) {
        try (Connection conn = db.getConnection()) {
            String sql = "INSERT INTO NhaCungCap(ten, sdt, diaChi, email) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ncc.getTen());
            ps.setString(2, ncc.getSdt());
            ps.setString(3, ncc.getDiaChi());
            ps.setString(4, ncc.getEmail());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(NhaCungCap ncc) {
        try (Connection conn = db.getConnection()) {
            String sql = "UPDATE NhaCungCap SET ten=?, sdt=?, diaChi=?, email=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ncc.getTen());
            ps.setString(2, ncc.getSdt());
            ps.setString(3, ncc.getDiaChi());
            ps.setString(4, ncc.getEmail());
            ps.setInt(5, ncc.getId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(String id) {
        try (Connection conn = db.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM NhaCungCap WHERE id=?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}