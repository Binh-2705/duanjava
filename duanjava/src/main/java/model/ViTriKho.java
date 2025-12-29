/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
// ViTriKho.java


public class ViTriKho {
    private int id;
    private String maViTri;
    private String tenViTri;
    private String moTa;
    private int idKho;
    private String tenKho; // For display

    public ViTriKho() {}

    public ViTriKho(int id, String maViTri, String tenViTri, String moTa, int idKho) {
        this.id = id;
        this.maViTri = maViTri;
        this.tenViTri = tenViTri;
        this.moTa = moTa;
        this.idKho = idKho;
    }

    public ViTriKho(String maViTri, String tenViTri, String moTa, int idKho) {
        this.maViTri = maViTri;
        this.tenViTri = tenViTri;
        this.moTa = moTa;
        this.idKho = idKho;
    }

    // Getter và Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMaViTri() { return maViTri; }
    public void setMaViTri(String maViTri) { this.maViTri = maViTri; }
    public String getTenViTri() { return tenViTri; }
    public void setTenViTri(String tenViTri) { this.tenViTri = tenViTri; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    public int getIdKho() { return idKho; }
    public void setIdKho(int idKho) { this.idKho = idKho; }
    public String getTenKho() { return tenKho; }
    public void setTenKho(String tenKho) { this.tenKho = tenKho; }
}