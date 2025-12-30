/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
// Kho.java

public class Kho {
    private int id;
    private String maKho;
    private String tenKho;
    private String diaChi;
    private String sdt;
    private String ghiChu;

    public Kho() {}

    public Kho(int id, String maKho, String tenKho, String diaChi, String sdt, String ghiChu) {
        this.id = id;
        this.maKho = maKho;
        this.tenKho = tenKho;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.ghiChu = ghiChu;
    }

    public Kho(String maKho, String tenKho, String diaChi, String sdt, String ghiChu) {
        this.maKho = maKho;
        this.tenKho = tenKho;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.ghiChu = ghiChu;
    }

    // Getter và Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMaKho() { return maKho; }
    public void setMaKho(String maKho) { this.maKho = maKho; }
    public String getTenKho() { return tenKho; }
    public void setTenKho(String tenKho) { this.tenKho = tenKho; }
    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }
    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
}