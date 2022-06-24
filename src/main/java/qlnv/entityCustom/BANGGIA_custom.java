package qlnv.entityCustom;


public class BANGGIA_custom {
    private int madg;
    private String tenlx, tendv, trangthai, dongia;

    public BANGGIA_custom() {
    }

    public BANGGIA_custom(int madg, String dongia, String tenlx, String tendv, String trangthai) {
        this.madg = madg;
        this.dongia = dongia;
        this.tenlx = tenlx;
        this.tendv = tendv;
        this.trangthai = trangthai;
    }

    public int getMadg() {
        return madg;
    }

    public void setMadg(int madg) {
        this.madg = madg;
    }

    public String getDongia() {
        return dongia;
    }

    public void setDongia(String dongia) {
        this.dongia = dongia;
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

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
