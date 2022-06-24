package qlnv.entity;

public class BANGGIA {

	private int madg;
	private int madv;
	private int malx;
	private int dongia;
	private String trangthai;

	public BANGGIA() {
	}

	public BANGGIA(int madg, int madv, int malx, int dongia, String trangthai) {
		this.madg = madg;
		this.madv = madv;
		this.malx = malx;
		this.dongia = dongia;
		this.trangthai = trangthai;
	}

	public int getMadg() {
		return madg;
	}

	public void setMadg(int madg) {
		this.madg = madg;
	}

	public int getMadv() {
		return madv;
	}

	public void setMadv(int madv) {
		this.madv = madv;
	}

	public int getMalx() {
		return malx;
	}

	public void setMalx(int malx) {
		this.malx = malx;
	}

	public int getDongia() {
		return dongia;
	}

	public void setDongia(int dongia) {
		this.dongia = dongia;
	}

	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}
}
