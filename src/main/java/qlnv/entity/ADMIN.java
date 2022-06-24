package qlnv.entity;

public class ADMIN {
    private String tendangnhap, matkhau;
    private String hoTen;
    public ADMIN() {
    }

    public ADMIN(String tendangnhap, String matkhau, String hoTen) {
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.hoTen = hoTen;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
}
