package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.TonKho;

public class TonKhoDAO {

    DB db = new DB();

    public List<TonKho> getAll() {
        List<TonKho> list = new ArrayList<>();
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT t.*, s.ten as tenSanPham, k.tenKho, v.maViTri " +
                    "FROM tonkho t " +
                    "LEFT JOIN sanpham s ON t.idSanPham = s.id " +
                    "LEFT JOIN kho k ON t.idKho = k.id " +
                    "LEFT JOIN vitri v ON t.idViTri = v.id " +
                    "ORDER BY k.tenKho, s.ten"
            );
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TonKho tonKho = new TonKho();
                tonKho.setId(rs.getInt("id"));
                tonKho.setIdSanPham(rs.getInt("idSanPham"));
                tonKho.setIdKho(rs.getInt("idKho"));
                tonKho.setIdViTri(rs.getInt("idViTri"));
                tonKho.setSoLuong(rs.getInt("soLuong"));
                tonKho.setTenSanPham(rs.getString("tenSanPham"));
                tonKho.setTenKho(rs.getString("tenKho"));
                tonKho.setMaViTri(rs.getString("maViTri"));
                list.add(tonKho);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<TonKho> getBySanPhamKho(int idSanPham, int idKho) {
        List<TonKho> list = new ArrayList<>();
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT t.*, s.ten as tenSanPham, k.tenKho, v.maViTri " +
                    "FROM tonkho t " +
                    "LEFT JOIN sanpham s ON t.idSanPham = s.id " +
                    "LEFT JOIN kho k ON t.idKho = k.id " +
                    "LEFT JOIN vitri v ON t.idViTri = v.id " +
                    "WHERE t.idSanPham = ? AND t.idKho = ? " +
                    "ORDER BY t.idViTri"
            );
            
            ps.setInt(1, idSanPham);
            ps.setInt(2, idKho);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TonKho tonKho = new TonKho();
                tonKho.setId(rs.getInt("id"));
                tonKho.setIdSanPham(rs.getInt("idSanPham"));
                tonKho.setIdKho(rs.getInt("idKho"));
                tonKho.setIdViTri(rs.getInt("idViTri"));
                tonKho.setSoLuong(rs.getInt("soLuong"));
                tonKho.setTenSanPham(rs.getString("tenSanPham"));
                tonKho.setTenKho(rs.getString("tenKho"));
                tonKho.setMaViTri(rs.getString("maViTri"));
                list.add(tonKho);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean capNhatTonKho(TonKho tonKho) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            conn.setAutoCommit(false); 
            
            PreparedStatement checkStmt = conn.prepareStatement(
                    "SELECT id, soLuong FROM tonkho WHERE idSanPham = ? AND idKho = ? AND idViTri = ?"
            );
            checkStmt.setInt(1, tonKho.getIdSanPham());
            checkStmt.setInt(2, tonKho.getIdKho());
            checkStmt.setInt(3, tonKho.getIdViTri());
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next()) {
                int currentQty = rs.getInt("soLuong");
                int newQty = currentQty + tonKho.getSoLuong();
                
                PreparedStatement updateStmt = conn.prepareStatement(
                        "UPDATE tonkho SET soLuong = ? WHERE id = ?"
                );
                updateStmt.setInt(1, newQty);
                updateStmt.setInt(2, rs.getInt("id"));
                updateStmt.executeUpdate();
            } else {
                PreparedStatement insertStmt = conn.prepareStatement(
                        "INSERT INTO tonkho(idSanPham, idKho, idViTri, soLuong) VALUES (?, ?, ?, ?)"
                );
                insertStmt.setInt(1, tonKho.getIdSanPham());
                insertStmt.setInt(2, tonKho.getIdKho());
                insertStmt.setInt(3, tonKho.getIdViTri());
                insertStmt.setInt(4, tonKho.getSoLuong());
                insertStmt.executeUpdate();
            }
            
            conn.commit(); 
            conn.close();
            return true;

        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean dieuChinhTonKho(TonKho tonKho) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            conn.setAutoCommit(false);
            
            PreparedStatement checkStmt = conn.prepareStatement(
                    "SELECT id FROM tonkho WHERE idSanPham = ? AND idKho = ? AND idViTri = ?"
            );
            checkStmt.setInt(1, tonKho.getIdSanPham());
            checkStmt.setInt(2, tonKho.getIdKho());
            checkStmt.setInt(3, tonKho.getIdViTri());
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next()) {
                PreparedStatement updateStmt = conn.prepareStatement(
                        "UPDATE tonkho SET soLuong = ? WHERE id = ?"
                );
                updateStmt.setInt(1, tonKho.getSoLuong());
                updateStmt.setInt(2, rs.getInt("id"));
                updateStmt.executeUpdate();
            } else {
                PreparedStatement insertStmt = conn.prepareStatement(
                        "INSERT INTO tonkho(idSanPham, idKho, idViTri, soLuong) VALUES (?, ?, ?, ?)"
                );
                insertStmt.setInt(1, tonKho.getIdSanPham());
                insertStmt.setInt(2, tonKho.getIdKho());
                insertStmt.setInt(3, tonKho.getIdViTri());
                insertStmt.setInt(4, tonKho.getSoLuong());
                insertStmt.executeUpdate();
            }
            
            conn.commit();
            conn.close();
            return true;

        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM tonkho WHERE id=?"
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

    public TonKho getById(int id) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT t.*, s.ten as tenSanPham, k.tenKho, v.maViTri " +
                    "FROM tonkho t " +
                    "LEFT JOIN sanpham s ON t.idSanPham = s.id " +
                    "LEFT JOIN kho k ON t.idKho = k.id " +
                    "LEFT JOIN vitri v ON t.idViTri = v.id " +
                    "WHERE t.id=?"
            );

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                TonKho tonKho = new TonKho();
                tonKho.setId(rs.getInt("id"));
                tonKho.setIdSanPham(rs.getInt("idSanPham"));
                tonKho.setIdKho(rs.getInt("idKho"));
                tonKho.setIdViTri(rs.getInt("idViTri"));
                tonKho.setSoLuong(rs.getInt("soLuong"));
                tonKho.setTenSanPham(rs.getString("tenSanPham"));
                tonKho.setTenKho(rs.getString("tenKho"));
                tonKho.setMaViTri(rs.getString("maViTri"));
                conn.close();
                return tonKho;
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getTongTonKhoBySanPham(int idSanPham) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT SUM(soLuong) as tong FROM tonkho WHERE idSanPham = ?"
            );
            
            ps.setInt(1, idSanPham);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                int tong = rs.getInt("tong");
                conn.close();
                return rs.wasNull() ? 0 : tong;
            }
            
            conn.close();
            return 0;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}