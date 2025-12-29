package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Sanpham;

public class Sanphamdao {

    DB db = new DB();

    // Lấy tất cả
    public ArrayList<Sanpham> getAll() {
        ArrayList<Sanpham> list = new ArrayList<>();
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM sanpham");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Sanpham(
                        rs.getInt("id"),
                        rs.getString("maSanPham"),
                        rs.getString("ten"),
                        rs.getInt("idDanhMuc"),
                        rs.getInt("idDonVi"),
                        rs.getDouble("giaNhap"),
                        rs.getDouble("giaBan"),
                        rs.getString("moTa")
                ));
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm
    public void insert(Sanpham sp) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO sanpham(maSanPham, ten, idDanhMuc, idDonVi, giaNhap, giaBan, moTa) "
                  + "VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            ps.setString(1, sp.getMaSanPham());
            ps.setString(2, sp.getTensp());
            ps.setInt(3, sp.getIdDanhMuc());
            ps.setInt(4, sp.getIdDonVi());
            ps.setDouble(5, sp.getGiaNhap());
            ps.setDouble(6, sp.getGiaBan());
            ps.setString(7, sp.getMoTa());

            ps.executeUpdate();
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Sửa
    public void update(Sanpham sp) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE sanpham SET maSanPham=?, ten=?, idDanhMuc=?, idDonVi=?, giaNhap=?, giaBan=?, moTa=? WHERE id=?"
            );

            ps.setString(1, sp.getMaSanPham());
            ps.setString(2, sp.getTensp());
            ps.setInt(3, sp.getIdDanhMuc());
            ps.setInt(4, sp.getIdDonVi());
            ps.setDouble(5, sp.getGiaNhap());
            ps.setDouble(6, sp.getGiaBan());
            ps.setString(7, sp.getMoTa());
            ps.setInt(8, sp.getId());

            ps.executeUpdate();
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Xóa
    public void delete(int id) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM sanpham WHERE id=?");

            ps.setInt(1, id);
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) { e.printStackTrace(); }
    }
}
