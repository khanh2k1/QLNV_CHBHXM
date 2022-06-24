package qlnv.entity;

public class LUONG {
	private String maNV;
	private int thang, nam, songaythuclam;
	private long tongdoanhthu;

	public LUONG(String maNV, int thang, int nam, int songaythuclam, long tongdoanhthu) {
		this.maNV = maNV;
		this.thang = thang;
		this.nam = nam;
		this.songaythuclam = songaythuclam;
		this.tongdoanhthu = tongdoanhthu;
	}

	public LUONG() {
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
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

	public long getTongdoanhthu() {
		return tongdoanhthu;
	}

	public void setTongdoanhthu(long tongdoanhthu) {
		this.tongdoanhthu = tongdoanhthu;
	}
}
