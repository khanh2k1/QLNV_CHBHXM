package qlnv.entity;

import java.util.Collection;
public class LOAINHANVIEN {

	private int maloai;
	private String tenloai;

	public LOAINHANVIEN() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LOAINHANVIEN(int maloai, String tenloai) {
		this.maloai = maloai;
		this.tenloai = tenloai;
	}


	public int getMaloai() {
		return maloai;
	}

	public void setMaloai(int maloai) {
		this.maloai = maloai;
	}

	public String getTenloai() {
		return tenloai;
	}

	public void setTenloai(String tenloai) {
		this.tenloai = tenloai;
	}
}
