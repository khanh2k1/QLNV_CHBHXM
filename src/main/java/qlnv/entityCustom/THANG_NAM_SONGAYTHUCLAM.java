package qlnv.entityCustom;

public class THANG_NAM_SONGAYTHUCLAM {
    private int thang, nam, songaythuclam;

    public THANG_NAM_SONGAYTHUCLAM() {
    }

    public THANG_NAM_SONGAYTHUCLAM(int thang, int nam, int songaythuclam) {
        this.thang = thang;
        this.nam = nam;
        this.songaythuclam = songaythuclam;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public int getSongaythuclam() {
        return songaythuclam;
    }

    public void setSongaythuclam(int songaythuclam) {
        this.songaythuclam = songaythuclam;
    }
}
