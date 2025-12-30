/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
// DieuChinhTon.java

import java.util.Date;

public class DieuChinhTon {
    private int id;
    private int idSanPham;
    private String tenSanPham; // For display
    private int idKho;
    private String tenKho; // For display
    private int soLuongCu;
    private int soLuongMoi;
    private int soLuongThayDoi;
    private String lyDo;
    private Date ngayDieuChinh;
    private String nguoiDieuChinh;

    public DieuChinhTon() {}

    public DieuChinhTon(int idSanPham, int idKho, int soLuongCu, int soLuongMoi, String lyDo, String nguoiDieuChinh) {
        this.idSanPham = idSanPham;
        this.idKho = idKho;
        this.soLuongCu = soLuongCu;
        this.soLuongMoi = soLuongMoi;
        this.soLuongThayDoi = soLuongMoi - soLuongCu;
        this.lyDo = lyDo;
        this.nguoiDieuChinh = nguoiDieuChinh;
        this.ngayDieuChinh = new Date();
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
    public int getSoLuongCu() { return soLuongCu; }
    public void setSoLuongCu(int soLuongCu) { this.soLuongCu = soLuongCu; }
    public int getSoLuongMoi() { return soLuongMoi; }
    public void setSoLuongMoi(int soLuongMoi) { this.soLuongMoi = soLuongMoi; }
    public int getSoLuongThayDoi() { return soLuongThayDoi; }
    public void setSoLuongThayDoi(int soLuongThayDoi) { this.soLuongThayDoi = soLuongThayDoi; }
    public String getLyDo() { return lyDo; }
    public void setLyDo(String lyDo) { this.lyDo = lyDo; }
    public Date getNgayDieuChinh() { return ngayDieuChinh; }
    public void setNgayDieuChinh(Date ngayDieuChinh) { this.ngayDieuChinh = ngayDieuChinh; }
    public String getNguoiDieuChinh() { return nguoiDieuChinh; }
    public void setNguoiDieuChinh(String nguoiDieuChinh) { this.nguoiDieuChinh = nguoiDieuChinh; }
}