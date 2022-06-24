package qlnv.entityCustom;

import java.sql.Timestamp;

public class CONGVIECCUANHANVIEN {

    private int dongia;
    private String manv, hoten, tendv, tenlx;
    private Timestamp ngaylam;

    public CONGVIECCUANHANVIEN() {

    }

    public CONGVIECCUANHANVIEN(int dongia, String hoten, String tendv, String tenlx, Timestamp ngaylam, String manv) {
        this.dongia = dongia;
        this.hoten = hoten;
        this.tendv = tendv;
        this.tenlx = tenlx;
        this.ngaylam = ngaylam;
        this.manv = manv;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getTendv() {
        return tendv;
    }

    public void setTendv(String tendv) {
        this.tendv = tendv;
    }

    public String getTenlx() {
        return tenlx;
    }

    public void setTenlx(String tenlx) {
        this.tenlx = tenlx;
    }

    public Timestamp getNgaylam() {
        return ngaylam;
    }

    public void setNgaylam(Timestamp ngaylam) {
        this.ngaylam = ngaylam;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }
}
