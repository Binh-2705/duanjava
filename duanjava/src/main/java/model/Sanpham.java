package model;

public class Sanpham {
    private int id;
    private String maSanPham;
    private String tensp;
    private int idDanhMuc;
    private int idDonVi;
    private double giaNhap;
    private double giaBan;
    private String moTa;

    // Constructor cho UPDATE (có ID)
    public Sanpham(int id, String maSanPham, String tensp, int idDanhMuc, int idDonVi,
                   double giaNhap, double giaBan, String moTa) {
        this.id = id;
        this.maSanPham = maSanPham;
        this.tensp = tensp;
        this.idDanhMuc = idDanhMuc;
        this.idDonVi = idDonVi;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.moTa = moTa;
    }

    // Constructor cho INSERT (không ID)
    public Sanpham(String maSanPham, String tensp, int idDanhMuc, int idDonVi,
                   double giaNhap, double giaBan, String moTa) {
        this.maSanPham = maSanPham;
        this.tensp = tensp;
        this.idDanhMuc = idDanhMuc;
        this.idDonVi = idDonVi;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.moTa = moTa;
    }

    // GETTER
    public int getId() { return id; }
    public String getMaSanPham() { return maSanPham; }
    public String getTensp() { return tensp; }
    public int getIdDanhMuc() { return idDanhMuc; }
    public int getIdDonVi() { return idDonVi; }
    public double getGiaNhap() { return giaNhap; }
    public double getGiaBan() { return giaBan; }
    public String getMoTa() { return moTa; }

    // SETTER (cần cho update)
    public void setId(int id) { this.id = id; }
    public void setMaSanPham(String maSanPham) { this.maSanPham = maSanPham; }
    public void setTensp(String tensp) { this.tensp = tensp; }
    public void setIdDanhMuc(int idDanhMuc) { this.idDanhMuc = idDanhMuc; }
    public void setIdDonVi(int idDonVi) { this.idDonVi = idDonVi; }
    public void setGiaNhap(double giaNhap) { this.giaNhap = giaNhap; }
    public void setGiaBan(double giaBan) { this.giaBan = giaBan; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
}
