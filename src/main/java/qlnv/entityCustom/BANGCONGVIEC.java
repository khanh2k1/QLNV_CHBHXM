package qlnv.entityCustom;

public class BANGCONGVIEC {
    private int madg, dongia, soluong;
    private String tenlx, tendv;

    public BANGCONGVIEC() {
    }

    public BANGCONGVIEC(int madg, int dongia, int soluong, String tenlx, String tendv) {
        this.madg = madg;
        this.dongia = dongia;
        this.soluong = soluong;
        this.tenlx = tenlx;
        this.tendv = tendv;
    }

    public int getMadg() {
        return madg;
    }

    public void setMadg(int madg) {
        this.madg = madg;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getTenlx() {
        return tenlx;
    }

    public void setTenlx(String tenlx) {
        this.tenlx = tenlx;
    }

    public String getTendv() {
        return tendv;
    }

    public void setTendv(String tendv) {
        this.tendv = tendv;
    }
}
