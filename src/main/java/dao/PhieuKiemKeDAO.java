/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.PhieuKiemKe;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuKiemKeDAO {
    DB db = new DB();

    public List<PhieuKiemKe> getAll() {
        List<PhieuKiemKe> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuKiemKe ORDER BY id DESC";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhieuKiemKe pk = new PhieuKiemKe();
                pk.setId(rs.getInt("id"));
                pk.setSoPhieu(rs.getString("soPhieu"));
                pk.setNgayKiemKe(rs.getTimestamp("ngayKiemKe"));
                pk.setIdKho(rs.getInt("idKho"));
                pk.setGhiChu(rs.getString("ghiChu"));
                list.add(pk);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void insert(PhieuKiemKe pk) {
        String sql = "INSERT INTO PhieuKiemKe (soPhieu, ngayKiemKe, idKho, ghiChu) VALUES (?, ?, ?, ?)";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pk.getSoPhieu());
            ps.setTimestamp(2, new Timestamp(pk.getNgayKiemKe().getTime()));
            ps.setInt(3, pk.getIdKho());
            ps.setString(4, pk.getGhiChu());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(PhieuKiemKe pk) {
        String sql = "UPDATE PhieuKiemKe SET soPhieu=?, ngayKiemKe=?, idKho=?, ghiChu=? WHERE id=?";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pk.getSoPhieu());
            ps.setTimestamp(2, new Timestamp(pk.getNgayKiemKe().getTime()));
            ps.setInt(3, pk.getIdKho());
            ps.setString(4, pk.getGhiChu());
            ps.setInt(5, pk.getId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        String sql = "DELETE FROM PhieuKiemKe WHERE id=?";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}