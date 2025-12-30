package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ViTri;

public class ViTriDAO {

    DB db = new DB();

    public List<ViTri> getAll() {
        List<ViTri> list = new ArrayList<>();
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT v.*, k.tenKho FROM vitri v " +
                    "LEFT JOIN kho k ON v.idKho = k.id " +
                    "ORDER BY k.tenKho, v.maViTri"
            );
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ViTri viTri = new ViTri();
                viTri.setId(rs.getInt("id"));
                viTri.setMaViTri(rs.getString("maViTri"));
                viTri.setIdKho(rs.getInt("idKho"));
                viTri.setGhiChu(rs.getString("ghiChu"));
                viTri.setTenKho(rs.getString("tenKho"));
                list.add(viTri);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<ViTri> getByKhoId(int idKho) {
        List<ViTri> list = new ArrayList<>();
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM vitri WHERE idKho = ? ORDER BY maViTri"
            );
            
            ps.setInt(1, idKho);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ViTri viTri = new ViTri();
                viTri.setId(rs.getInt("id"));
                viTri.setMaViTri(rs.getString("maViTri"));
                viTri.setIdKho(rs.getInt("idKho"));
                viTri.setGhiChu(rs.getString("ghiChu"));
                list.add(viTri);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(ViTri viTri) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO vitri(maViTri, idKho, ghiChu) VALUES (?, ?, ?)"
            );

            ps.setString(1, viTri.getMaViTri());
            ps.setInt(2, viTri.getIdKho());
            ps.setString(3, viTri.getGhiChu());
            int result = ps.executeUpdate();
            conn.close();
            
            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(ViTri viTri) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE vitri SET maViTri=?, idKho=?, ghiChu=? WHERE id=?"
            );

            ps.setString(1, viTri.getMaViTri());
            ps.setInt(2, viTri.getIdKho());
            ps.setString(3, viTri.getGhiChu());
            ps.setInt(4, viTri.getId());
            int result = ps.executeUpdate();
            conn.close();
            
            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM vitri WHERE id=?"
            );

            ps.setInt(1, id);
            int result = ps.executeUpdate();
            conn.close();
            
            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ViTri getById(int id) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT v.*, k.tenKho FROM vitri v " +
                    "LEFT JOIN kho k ON v.idKho = k.id " +
                    "WHERE v.id=?"
            );

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ViTri viTri = new ViTri();
                viTri.setId(rs.getInt("id"));
                viTri.setMaViTri(rs.getString("maViTri"));
                viTri.setIdKho(rs.getInt("idKho"));
                viTri.setGhiChu(rs.getString("ghiChu"));
                viTri.setTenKho(rs.getString("tenKho"));
                conn.close();
                return viTri;
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ViTri> findByMaViTri(String maViTri) {
        List<ViTri> list = new ArrayList<>();
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT v.*, k.tenKho FROM vitri v " +
                    "LEFT JOIN kho k ON v.idKho = k.id " +
                    "WHERE v.maViTri LIKE ? " +
                    "ORDER BY k.tenKho, v.maViTri"
            );

            ps.setString(1, "%" + maViTri + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ViTri viTri = new ViTri();
                viTri.setId(rs.getInt("id"));
                viTri.setMaViTri(rs.getString("maViTri"));
                viTri.setIdKho(rs.getInt("idKho"));
                viTri.setGhiChu(rs.getString("ghiChu"));
                viTri.setTenKho(rs.getString("tenKho"));
                list.add(viTri);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean isMaViTriExists(String maViTri, int idKho, int excludeId) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT COUNT(*) FROM vitri WHERE maViTri = ? AND idKho = ? AND id != ?"
            );
            
            ps.setString(1, maViTri);
            ps.setInt(2, idKho);
            ps.setInt(3, excludeId);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            int count = rs.getInt(1);
            
            conn.close();
            
            return count > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}