package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Kho;

public class KhoDAO {

    DB db = new DB();

    public List<Kho> getAll() {
        List<Kho> list = new ArrayList<>();
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM kho ORDER BY tenKho");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Kho kho = new Kho();
                kho.setId(rs.getInt("id"));
                kho.setTenKho(rs.getString("tenKho"));
                kho.setDiaChi(rs.getString("diaChi"));
                list.add(kho);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(Kho kho) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO kho(tenKho, diaChi) VALUES (?, ?)"
            );

            ps.setString(1, kho.getTenKho());
            ps.setString(2, kho.getDiaChi());
            int result = ps.executeUpdate();
            conn.close();
            
            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Kho kho) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE kho SET tenKho=?, diaChi=? WHERE id=?"
            );

            ps.setString(1, kho.getTenKho());
            ps.setString(2, kho.getDiaChi());
            ps.setInt(3, kho.getId());
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
                    "DELETE FROM kho WHERE id=?"
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

    public Kho getById(int id) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM kho WHERE id=?"
            );

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Kho kho = new Kho();
                kho.setId(rs.getInt("id"));
                kho.setTenKho(rs.getString("tenKho"));
                kho.setDiaChi(rs.getString("diaChi"));
                conn.close();
                return kho;
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Kho> findByTen(String tenKho) {
        List<Kho> list = new ArrayList<>();
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM kho WHERE tenKho LIKE ? ORDER BY tenKho"
            );

            ps.setString(1, "%" + tenKho + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Kho kho = new Kho();
                kho.setId(rs.getInt("id"));
                kho.setTenKho(rs.getString("tenKho"));
                kho.setDiaChi(rs.getString("diaChi"));
                list.add(kho);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean isKhoInUse(int id) {
        try {
            Connection conn = db.getConnection();
            
            PreparedStatement ps1 = conn.prepareStatement(
                    "SELECT COUNT(*) FROM vitri WHERE idKho = ?"
            );
            ps1.setInt(1, id);
            ResultSet rs1 = ps1.executeQuery();
            rs1.next();
            int countViTri = rs1.getInt(1);
            
            PreparedStatement ps2 = conn.prepareStatement(
                    "SELECT COUNT(*) FROM tonkho WHERE idKho = ?"
            );
            ps2.setInt(1, id);
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();
            int countTonKho = rs2.getInt(1);
            
            conn.close();
            
            return countViTri > 0 || countTonKho > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}