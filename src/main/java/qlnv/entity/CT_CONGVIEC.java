package qlnv.entity;

public class CT_CONGVIEC {
	private int madg, soluong;

	public CT_CONGVIEC() {
	}

	public CT_CONGVIEC(int madg, int soluong) {

		this.madg = madg;
		this.soluong = soluong;
	}

	public int getMadg() {
		return madg;
	}

	public void setMadg(int madg) {
		this.madg = madg;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
}
