/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class ViTri {
    private int id;
    private String maViTri;
    private int idKho;
    private String ghiChu;
    
    private String tenKho;

    public ViTri() {}

    public ViTri(String maViTri, int idKho, String ghiChu) {
        this.maViTri = maViTri;
        this.idKho = idKho;
        this.ghiChu = ghiChu;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getMaViTri() { return maViTri; }
    public void setMaViTri(String maViTri) { this.maViTri = maViTri; }
    
    public int getIdKho() { return idKho; }
    public void setIdKho(int idKho) { this.idKho = idKho; }
    
    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
    
    public String getTenKho() { return tenKho; }
    public void setTenKho(String tenKho) { this.tenKho = tenKho; }

    @Override
    public String toString() {
        return maViTri + (tenKho != null ? " - " + tenKho : "");
    }
}