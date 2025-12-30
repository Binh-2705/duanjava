/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Admin
 */
// DieuChinhTondao.java
import java.sql.*;
import java.util.ArrayList;
import model.DieuChinhTon;

public class DieuChinhTondao {
    DB db = new DB();

    public ArrayList<DieuChinhTon> getAll() {
        ArrayList<DieuChinhTon> list = new ArrayList<>();
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT dc.*, sp.ten as tenSanPham, k.tenKho " +
                        "FROM dieu_chinh_ton dc " +
                        "LEFT JOIN sanpham sp ON dc.idSanPham = sp.id " +
                        "LEFT JOIN kho k ON dc.idKho = k.id " +
                        "ORDER BY dc.ngayDieuChinh DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DieuChinhTon dc = new DieuChinhTon(
                    rs.getInt("idSanPham"),
                    rs.getInt("idKho"),
                    rs.getInt("soLuongCu"),
                    rs.getInt("soLuongMoi"),
                    rs.getString("lyDo"),
                    rs.getString("nguoiDieuChinh")
                );
                dc.setId(rs.getInt("id"));
                dc.setTenSanPham(rs.getString("tenSanPham"));
                dc.setTenKho(rs.getString("tenKho"));
                dc.setNgayDieuChinh(rs.getTimestamp("ngayDieuChinh"));
                list.add(dc);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void insert(DieuChinhTon dieuChinh) {
        try (Connection conn = db.getConnection()) {
            String sql = "INSERT INTO dieu_chinh_ton(idSanPham, idKho, soLuongCu, soLuongMoi, soLuongThayDoi, lyDo, ngayDieuChinh, nguoiDieuChinh) " +
                        "VALUES (?, ?, ?, ?, ?, ?, NOW(), ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, dieuChinh.getIdSanPham());
            ps.setInt(2, dieuChinh.getIdKho());
            ps.setInt(3, dieuChinh.getSoLuongCu());
            ps.setInt(4, dieuChinh.getSoLuongMoi());
            ps.setInt(5, dieuChinh.getSoLuongThayDoi());
            ps.setString(6, dieuChinh.getLyDo());
            ps.setString(7, dieuChinh.getNguoiDieuChinh());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        try (Connection conn = db.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM dieu_chinh_ton WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}