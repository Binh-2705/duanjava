/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class CTKiemKe {
    private int id;
    private int idPhieuKiemKe;
    private int idSanPham;
    private int soHeThong; 
    private int soThucTe; 
    private int chenhLech; 
    private String tenSanPham;

    public CTKiemKe() {}

    // Các Getter và Setter đơn giản
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdPhieuKiemKe() { return idPhieuKiemKe; }
    public void setIdPhieuKiemKe(int idPhieuKiemKe) { this.idPhieuKiemKe = idPhieuKiemKe; }

    public int getIdSanPham() { return idSanPham; }
    public void setIdSanPham(int idSanPham) { this.idSanPham = idSanPham; }

    public int getSoHeThong() { return soHeThong; }
    public void setSoHeThong(int soHeThong) { this.soHeThong = soHeThong; }

    public int getSoThucTe() { return soThucTe; }
    public void setSoThucTe(int soThucTe) { this.soThucTe = soThucTe; }

    public int getChenhLech() { return chenhLech; }
    public void setChenhLech(int chenhLech) { this.chenhLech = chenhLech; }

    public String getTenSanPham() { return tenSanPham; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }
}