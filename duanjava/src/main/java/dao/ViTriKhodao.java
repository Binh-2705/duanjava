/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Admin
 */
// ViTriKhodao.java

import java.sql.*;
import java.util.ArrayList;
import model.ViTriKho;

public class ViTriKhodao {
    DB db = new DB();

    public ArrayList<ViTriKho> getAll() {
        ArrayList<ViTriKho> list = new ArrayList<>();
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT vt.*, k.tenKho FROM vi_tri_kho vt " +
                        "LEFT JOIN kho k ON vt.idKho = k.id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ViTriKho vt = new ViTriKho(
                    rs.getInt("id"),
                    rs.getString("maViTri"),
                    rs.getString("tenViTri"),
                    rs.getString("moTa"),
                    rs.getInt("idKho")
                );
                vt.setTenKho(rs.getString("tenKho"));
                list.add(vt);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void insert(ViTriKho viTri) {
        try (Connection conn = db.getConnection()) {
            String sql = "INSERT INTO vi_tri_kho(maViTri, tenViTri, moTa, idKho) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, viTri.getMaViTri());
            ps.setString(2, viTri.getTenViTri());
            ps.setString(3, viTri.getMoTa());
            ps.setInt(4, viTri.getIdKho());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(ViTriKho viTri) {
        try (Connection conn = db.getConnection()) {
            String sql = "UPDATE vi_tri_kho SET maViTri=?, tenViTri=?, moTa=?, idKho=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, viTri.getMaViTri());
            ps.setString(2, viTri.getTenViTri());
            ps.setString(3, viTri.getMoTa());
            ps.setInt(4, viTri.getIdKho());
            ps.setInt(5, viTri.getId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        try (Connection conn = db.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM vi_tri_kho WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}