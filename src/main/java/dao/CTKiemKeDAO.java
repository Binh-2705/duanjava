/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.CTKiemKe; 

public class CTKiemKeDAO {
    DB db = new DB();

    public List<CTKiemKe> getListByPhieu(int idPK) {
        List<CTKiemKe> list = new ArrayList<>();
        String sql = "SELECT ct.*, sp.ten as tenSP FROM ct_kiemke ct " +
                     "JOIN sanpham sp ON ct.idSanPham = sp.id WHERE ct.idPhieuKiemKe = ?";

        // Khai báo ps và conn cùng trong try để tự động đóng
        try (Connection conn = db.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPK);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CTKiemKe ct = new CTKiemKe();
                    ct.setId(rs.getInt("id"));
                    ct.setIdPhieuKiemKe(rs.getInt("idPhieuKiemKe"));
                    ct.setSoHeThong(rs.getInt("soHeThong")); 
                    ct.setSoThucTe(rs.getInt("soThucTe"));
                    ct.setChenhLech(rs.getInt("chenhLech"));
                    ct.setTenSanPham(rs.getString("tenSP"));
                    list.add(ct);
                }
            }
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
        return list;
    }
    
    public void insert(CTKiemKe ct) {
    String sql = "INSERT INTO ct_kiemke (idPhieuKiemKe, idSanPham, soThucTe, soHeThong, chenhLech) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, ct.getIdPhieuKiemKe());
        ps.setInt(2, ct.getIdSanPham());
        ps.setInt(3, ct.getSoThucTe());
        ps.setInt(4, ct.getSoHeThong());
        ps.setInt(5, ct.getChenhLech());
        ps.executeUpdate();
            } catch (Exception e) {
        e.printStackTrace();
        }
    }
}