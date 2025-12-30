/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.PhieuXuat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuXuatDAO {
    DB db = new DB();

    public List<PhieuXuat> getAll() {
        List<PhieuXuat> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuXuat";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PhieuXuat px = new PhieuXuat();
                px.setId(rs.getInt("id"));
                px.setSoPhieu(rs.getString("soPhieu"));
                px.setNgayXuat(rs.getTimestamp("ngayXuat"));
                px.setIdKhachHang(rs.getInt("idKhachHang"));
                px.setGhiChu(rs.getString("ghiChu"));
                list.add(px);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public int insert(PhieuXuat px) {
        String sql = "INSERT INTO PhieuXuat(soPhieu, ngayXuat, idKhachHang, ghiChu) VALUES (?,?,?,?)";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, px.getSoPhieu());
            ps.setTimestamp(2, new Timestamp(px.getNgayXuat().getTime()));
            ps.setInt(3, px.getIdKhachHang());
            ps.setString(4, px.getGhiChu());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return -1;
    }
    
    public void update(PhieuXuat px) {
    String sql = "UPDATE PhieuXuat SET soPhieu=?, ngayXuat=?, idKhachHang=?, ghiChu=? WHERE id=?";
    try (Connection conn = db.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, px.getSoPhieu());
        ps.setTimestamp(2, new Timestamp(px.getNgayXuat().getTime()));
        ps.setInt(3, px.getIdKhachHang());
        ps.setString(4, px.getGhiChu());
        ps.setInt(5, px.getId());
        ps.executeUpdate();
    } catch (Exception e) { e.printStackTrace(); }
}
    
    public void delete(int id) {
        String sql = "DELETE FROM PhieuXuat WHERE id=?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
