/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class PhieuKiemKe {
    private int id;
    private String soPhieu;
    private Date ngayKiemKe;
    private int idKho;
    private String ghiChu;

    public PhieuKiemKe() {}

    // Getter và Setter (Bạn có thể dùng Alt+Insert trong NetBeans để tạo nhanh)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getSoPhieu() { return soPhieu; }
    public void setSoPhieu(String soPhieu) { this.soPhieu = soPhieu; }
    public Date getNgayKiemKe() { return ngayKiemKe; }
    public void setNgayKiemKe(Date ngayKiemKe) { this.ngayKiemKe = ngayKiemKe; }
    public int getIdKho() { return idKho; }
    public void setIdKho(int idKho) { this.idKho = idKho; }
    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
}   