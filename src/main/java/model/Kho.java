package model;

public class Kho {
    private int id;
    private String tenKho;
    private String diaChi;

    public Kho() {}

    public Kho(String tenKho, String diaChi) {
        this.tenKho = tenKho;
        this.diaChi = diaChi;
    }

    public Kho(int id, String tenKho, String diaChi) {
        this.id = id;
        this.tenKho = tenKho;
        this.diaChi = diaChi;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTenKho() { return tenKho; }
    public void setTenKho(String tenKho) { this.tenKho = tenKho; }
    
    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    @Override
    public String toString() {
        return tenKho;
    }
}