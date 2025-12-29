/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
// TonKho.java

import java.util.Date;

public class TonKho {
    private int id;
    private int idSanPham;
    private String tenSanPham; // For display
    private int idKho;
    private String tenKho; // For display
    private int idViTri;
    private String tenViTri; // For display
    private int soLuong;
    private Date ngayCapNhat;

    public TonKho() {}

    public TonKho(int id, int idSanPham, int idKho, int idViTri, int soLuong, Date ngayCapNhat) {
        this.id = id;
        this.idSanPham = idSanPham;
        this.idKho = idKho;
        this.idViTri = idViTri;
        this.soLuong = soLuong;
        this.ngayCapNhat = ngayCapNhat;
    }

    public TonKho(int idSanPham, int idKho, int idViTri, int soLuong) {
        this.idSanPham = idSanPham;
        this.idKho = idKho;
        this.idViTri = idViTri;
        this.soLuong = soLuong;
        this.ngayCapNhat = new Date();
    }

    // Getter và Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdSanPham() { return idSanPham; }
    public void setIdSanPham(int idSanPham) { this.idSanPham = idSanPham; }
    public String getTenSanPham() { return tenSanPham; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }
    public int getIdKho() { return idKho; }
    public void setIdKho(int idKho) { this.idKho = idKho; }
    public String getTenKho() { return tenKho; }
    public void setTenKho(String tenKho) { this.tenKho = tenKho; }
    public int getIdViTri() { return idViTri; }
    public void setIdViTri(int idViTri) { this.idViTri = idViTri; }
    public String getTenViTri() { return tenViTri; }
    public void setTenViTri(String tenViTri) { this.tenViTri = tenViTri; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public Date getNgayCapNhat() { return ngayCapNhat; }
    public void setNgayCapNhat(Date ngayCapNhat) { this.ngayCapNhat = ngayCapNhat; }
}