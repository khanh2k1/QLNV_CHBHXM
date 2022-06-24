package qlnv.entityCustom;

public class CT_CONGVIEC_CUSTOM {
    private String tenlx,tendv;
    private int soluong, dongia;

    public CT_CONGVIEC_CUSTOM() {
    }

    public CT_CONGVIEC_CUSTOM(String tenlx, String tendv, int soluong, int dongia) {
        this.tenlx = tenlx;
        this.tendv = tendv;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public String getTendv() {
        return tendv;
    }

    public void setTendv(String tendv) {
        this.tendv = tendv;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public String getTenlx() {
        return tenlx;
    }

    public void setTenlx(String tenlx) {
        this.tenlx = tenlx;
    }
}
