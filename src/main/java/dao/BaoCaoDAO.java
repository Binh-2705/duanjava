package dao;

import java.sql.*;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class BaoCaoDAO {
    DB db = new DB();
    public void loadBaoCaoNhap(DefaultTableModel model, Date tuNgay, Date denNgay) {
        model.setRowCount(0);
        model.setColumnIdentifiers(new Object[]{"ID", "Số Phiếu Nhập", "Ngày Nhập", "Nhà Cung Cấp", "Ghi Chú"});

        try (Connection conn = db.getConnection()) {
            String sql = "SELECT pn.id, pn.soPhieu, pn.ngayNhap, ncc.ten, pn.ghiChu " +
                         "FROM PhieuNhap pn " +
                         "JOIN NhaCungCap ncc ON pn.idNCC = ncc.id " +
                         "WHERE pn.ngayNhap BETWEEN ? AND ?";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, new java.sql.Timestamp(tuNgay.getTime()));
            ps.setTimestamp(2, new java.sql.Timestamp(denNgay.getTime()));
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"), rs.getString("soPhieu"), 
                    rs.getTimestamp("ngayNhap"), rs.getString("ten"), rs.getString("ghiChu")
                });
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void loadBaoCaoXuat(DefaultTableModel model, Date tuNgay, Date denNgay) {
        model.setRowCount(0);
        model.setColumnIdentifiers(new Object[]{"ID", "Số Phiếu Xuất", "Ngày Xuất", "Khách Hàng", "Ghi Chú"});

        try (Connection conn = db.getConnection()) {
            String sql = "SELECT px.id, px.soPhieu, px.ngayXuat, kh.ten, px.ghiChu " +
                         "FROM PhieuXuat px " +
                         "JOIN KhachHang kh ON px.idKhachHang = kh.id " +
                         "WHERE px.ngayXuat BETWEEN ? AND ?";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, new java.sql.Timestamp(tuNgay.getTime()));
            ps.setTimestamp(2, new java.sql.Timestamp(denNgay.getTime()));
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"), rs.getString("soPhieu"), 
                    rs.getTimestamp("ngayXuat"), rs.getString("ten"), rs.getString("ghiChu")
                });
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}