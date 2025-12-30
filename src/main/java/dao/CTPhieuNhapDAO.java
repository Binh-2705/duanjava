package dao;

import model.CTPhieuNhap;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CTPhieuNhapDAO {
    DB db = new DB();

    public List<CTPhieuNhap> getListByPhieuNhap(int idPN) {
        List<CTPhieuNhap> list = new ArrayList<>();

        String sql = "SELECT id, idPhieuNhap, idSanPham, soLuong, giaNhap, idViTri FROM ct_phieunhap WHERE idPhieuNhap = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPN);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CTPhieuNhap ct = new CTPhieuNhap();
                ct.setId(rs.getInt("id"));
                ct.setIdPhieuNhap(rs.getInt("idPhieuNhap"));
                ct.setIdSanPham(rs.getInt("idSanPham")); 
                ct.setSoLuong(rs.getInt("soLuong"));
                ct.setGiaNhap(rs.getDouble("giaNhap")); 
                ct.setIdViTri(rs.getInt("idViTri"));
                list.add(ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(CTPhieuNhap ct) {
        String sql = "INSERT INTO ct_phieunhap(idPhieuNhap, idSanPham, soLuong, giaNhap, idViTri) VALUES (?,?,?,?,?)";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ct.getIdPhieuNhap());
            ps.setInt(2, ct.getIdSanPham());
            ps.setInt(3, ct.getSoLuong());
            ps.setDouble(4, ct.getGiaNhap());
            ps.setInt(5, ct.getIdViTri());
            ps.executeUpdate();
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }
}