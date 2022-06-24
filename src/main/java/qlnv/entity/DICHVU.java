package qlnv.entity;


public class DICHVU {

	private int madv;
	private String tendv;

	public DICHVU(int madv, String tendv) {
		this.madv = madv;
		this.tendv = tendv;
	}

	public DICHVU() {
	}

	public int getMadv() {
		return madv;
	}

	public void setMadv(int madv) {
		this.madv = madv;
	}

	public String getTendv() {
		return tendv;
	}

	public void setTendv(String tendv) {
		this.tendv = tendv;
	}
}
