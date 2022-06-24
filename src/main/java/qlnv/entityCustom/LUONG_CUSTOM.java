package qlnv.entityCustom;

public class LUONG_CUSTOM {

    private String manv, hoten;
    private int songaythuclam;
    private int luongcoban;
    private long tongdoanhthu;
    private long luongthuclanh;

    public LUONG_CUSTOM() {
    }

    public LUONG_CUSTOM(String manv, String hoten, int songaythuclam, int luongcoban, long tongdoanhthu, long luongthuclanh) {
        this.manv = manv;
        this.hoten= hoten;
        this.songaythuclam = songaythuclam;
        this.luongcoban = luongcoban;
        this.tongdoanhthu = tongdoanhthu;
        this.luongthuclanh = luongthuclanh;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public int getSongaythuclam() {
        return songaythuclam;
    }

    public void setSongaythuclam(int songaythuclam) {
        this.songaythuclam = songaythuclam;
    }

    public long getTongdoanhthu() {
        return tongdoanhthu;
    }

    public void setTongdoanhthu(long tongdoanhthu) {
        this.tongdoanhthu = tongdoanhthu;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public long getLuongthuclanh() {
        return luongthuclanh;
    }

    public void setLuongthuclanh(long luongthuclanh) {
        this.luongthuclanh = luongthuclanh;
    }

    public int getLuongcoban() {
        return luongcoban;
    }

    public void setLuongcoban(int luongcoban) {
        this.luongcoban = luongcoban;
    }
}
