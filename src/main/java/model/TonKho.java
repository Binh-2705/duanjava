/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class TonKho {
    private int id;
    private int idSanPham;
    private int idKho;
    private int idViTri;
    private int soLuong;
    
    private String tenSanPham;
    private String tenKho;
    private String maViTri;

    public TonKho() {}

    public TonKho(int idSanPham, int idKho, int idViTri, int soLuong) {
        this.idSanPham = idSanPham;
        this.idKho = idKho;
        this.idViTri = idViTri;
        this.soLuong = soLuong;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getIdSanPham() { return idSanPham; }
    public void setIdSanPham(int idSanPham) { this.idSanPham = idSanPham; }
    
    public int getIdKho() { return idKho; }
    public void setIdKho(int idKho) { this.idKho = idKho; }
    
    public int getIdViTri() { return idViTri; }
    public void setIdViTri(int idViTri) { this.idViTri = idViTri; }
    
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    
    public String getTenSanPham() { return tenSanPham; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }
    
    public String getTenKho() { return tenKho; }
    public void setTenKho(String tenKho) { this.tenKho = tenKho; }
    
    public String getMaViTri() { return maViTri; }
    public void setMaViTri(String maViTri) { this.maViTri = maViTri; }
}