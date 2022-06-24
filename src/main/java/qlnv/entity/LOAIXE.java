package qlnv.entity;

public class LOAIXE{

	private int maLX;
	private String tenLX;

	public LOAIXE(int maLX, String tenLX) {
		this.maLX = maLX;
		this.tenLX = tenLX;
	}

	public LOAIXE() {
	}

	public int getMaLX() {
		return maLX;
	}

	public void setMaLX(int maLX) {
		this.maLX = maLX;
	}

	public String getTenLX() {
		return tenLX;
	}

	public void setTenLX(String tenLX) {
		this.tenLX = tenLX;
	}
}
