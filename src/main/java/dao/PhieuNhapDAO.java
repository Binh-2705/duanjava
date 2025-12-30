/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.PhieuNhap;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuNhapDAO {
    DB db = new DB();
    public List<PhieuNhap> getAll() {
        List<PhieuNhap> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuNhap";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PhieuNhap pn = new PhieuNhap();
                pn.setId(rs.getInt("id"));
                pn.setSoPhieu(rs.getString("soPhieu"));
                pn.setNgayNhap(rs.getTimestamp("ngayNhap"));
                pn.setIdNCC(rs.getInt("idNCC"));
                pn.setGhiChu(rs.getString("ghiChu"));
                list.add(pn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int insert(PhieuNhap pn) {
        String sql = "INSERT INTO PhieuNhap(soPhieu, ngayNhap, idNCC, ghiChu) VALUES (?,?,?,?)";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, pn.getSoPhieu());
            ps.setTimestamp(2, new Timestamp(pn.getNgayNhap().getTime()));
            ps.setInt(3, pn.getIdNCC());
            ps.setString(4, pn.getGhiChu());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void update(PhieuNhap pn) {
        String sql = "UPDATE PhieuNhap SET soPhieu=?, ngayNhap=?, idNCC=?, ghiChu=? WHERE id=?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pn.getSoPhieu());
            ps.setTimestamp(2, new Timestamp(pn.getNgayNhap().getTime()));
            ps.setInt(3, pn.getIdNCC());
            ps.setString(4, pn.getGhiChu());
            ps.setInt(5, pn.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM PhieuNhap WHERE id=?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
