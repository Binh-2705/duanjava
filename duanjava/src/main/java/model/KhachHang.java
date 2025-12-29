package model;

public class KhachHang {
    private int id;
    private String ten;
    private String sdt;
    private String diaChi;
    private String email;

    public KhachHang() {}

    public KhachHang(int id, String ten, String sdt, String diaChi, String email) {
        this.id = id;
        this.ten = ten;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.email = email;
    }

    public KhachHang(String ten, String sdt, String diaChi, String email) {
        this.ten = ten;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.email = email;
    }

    // Getter và Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }
    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}