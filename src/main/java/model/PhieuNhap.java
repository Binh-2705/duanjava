/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class PhieuNhap {
    private int id;
    private String soPhieu;
    private Date ngayNhap;
    private int idNCC;
    private String ghiChu;

    public PhieuNhap() {}

    public PhieuNhap(int id, String soPhieu, Date ngayNhap, int idNCC, String ghiChu) {
        this.id = id;
        this.soPhieu = soPhieu;
        this.ngayNhap = ngayNhap;
        this.idNCC = idNCC;
        this.ghiChu = ghiChu;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSoPhieu() { return soPhieu; }
    public void setSoPhieu(String soPhieu) { this.soPhieu = soPhieu; }

    public Date getNgayNhap() { return ngayNhap; }
    public void setNgayNhap(Date ngayNhap) { this.ngayNhap = ngayNhap; }

    public int getIdNCC() { return idNCC; }
    public void setIdNCC(int idNCC) { this.idNCC = idNCC; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
}
