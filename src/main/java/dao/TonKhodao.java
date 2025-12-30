/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Admin
 */
// TonKhodao.java


import java.sql.*;
import java.util.ArrayList;
import model.TonKho;

public class TonKhodao {
    DB db = new DB();

    public ArrayList<TonKho> getAll() {
        ArrayList<TonKho> list = new ArrayList<>();
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT tk.*, sp.ten as tenSanPham, k.tenKho, vt.tenViTri " +
                        "FROM ton_kho tk " +
                        "LEFT JOIN sanpham sp ON tk.idSanPham = sp.id " +
                        "LEFT JOIN kho k ON tk.idKho = k.id " +
                        "LEFT JOIN vi_tri_kho vt ON tk.idViTri = vt.id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TonKho tk = new TonKho(
                    rs.getInt("id"),
                    rs.getInt("idSanPham"),
                    rs.getInt("idKho"),
                    rs.getInt("idViTri"),
                    rs.getInt("soLuong"),
                    rs.getTimestamp("ngayCapNhat")
                );
                tk.setTenSanPham(rs.getString("tenSanPham"));
                tk.setTenKho(rs.getString("tenKho"));
                tk.setTenViTri(rs.getString("tenViTri"));
                list.add(tk);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void updateTonKho(int idSanPham, int idKho, int idViTri, int soLuong) {
        try (Connection conn = db.getConnection()) {
            // Kiểm tra xem đã tồn tại chưa
            String checkSql = "SELECT * FROM ton_kho WHERE idSanPham=? AND idKho=? AND idViTri=?";
            PreparedStatement checkPs = conn.prepareStatement(checkSql);
            checkPs.setInt(1, idSanPham);
            checkPs.setInt(2, idKho);
            checkPs.setInt(3, idViTri);
            ResultSet rs = checkPs.executeQuery();
            
            if (rs.next()) {
                // Update nếu đã tồn tại
                String updateSql = "UPDATE ton_kho SET soLuong=?, ngayCapNhat=NOW() WHERE id=?";
                PreparedStatement updatePs = conn.prepareStatement(updateSql);
                updatePs.setInt(1, soLuong);
                updatePs.setInt(2, rs.getInt("id"));
                updatePs.executeUpdate();
            } else {
                // Insert nếu chưa tồn tại
                String insertSql = "INSERT INTO ton_kho(idSanPham, idKho, idViTri, soLuong, ngayCapNhat) VALUES (?, ?, ?, ?, NOW())";
                PreparedStatement insertPs = conn.prepareStatement(insertSql);
                insertPs.setInt(1, idSanPham);
                insertPs.setInt(2, idKho);
                insertPs.setInt(3, idViTri);
                insertPs.setInt(4, soLuong);
                insertPs.executeUpdate();
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public int getSoLuongTon(int idSanPham, int idKho) {
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT SUM(soLuong) as total FROM ton_kho WHERE idSanPham=? AND idKho=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idSanPham);
            ps.setInt(2, idKho);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }
}