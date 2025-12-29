/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Admin
 */
// Khodao.java
import java.sql.*;
import java.util.ArrayList;
import model.Kho;

public class Khodao {
    DB db = new DB();

    public ArrayList<Kho> getAll() {
        ArrayList<Kho> list = new ArrayList<>();
        try (Connection conn = db.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM kho");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Kho(
                    rs.getInt("id"),
                    rs.getString("maKho"),
                    rs.getString("tenKho"),
                    rs.getString("diaChi"),
                    rs.getString("sdt"),
                    rs.getString("ghiChu")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void insert(Kho kho) {
        try (Connection conn = db.getConnection()) {
            String sql = "INSERT INTO kho(maKho, tenKho, diaChi, sdt, ghiChu) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kho.getMaKho());
            ps.setString(2, kho.getTenKho());
            ps.setString(3, kho.getDiaChi());
            ps.setString(4, kho.getSdt());
            ps.setString(5, kho.getGhiChu());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(Kho kho) {
        try (Connection conn = db.getConnection()) {
            String sql = "UPDATE kho SET maKho=?, tenKho=?, diaChi=?, sdt=?, ghiChu=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kho.getMaKho());
            ps.setString(2, kho.getTenKho());
            ps.setString(3, kho.getDiaChi());
            ps.setString(4, kho.getSdt());
            ps.setString(5, kho.getGhiChu());
            ps.setInt(6, kho.getId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        try (Connection conn = db.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM kho WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public Kho findById(int id) {
        try (Connection conn = db.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM kho WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Kho(
                    rs.getInt("id"),
                    rs.getString("maKho"),
                    rs.getString("tenKho"),
                    rs.getString("diaChi"),
                    rs.getString("sdt"),
                    rs.getString("ghiChu")
                );
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
}