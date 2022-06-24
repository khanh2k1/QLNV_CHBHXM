package qlnv.entity;

import java.sql.Timestamp;


public class CONGVIEC {
	private int madg;
	private String manv;
	private Timestamp ngaylam; // thoi diem lam viec
	private int tongtien;

	public CONGVIEC() {
	}

	public CONGVIEC(int madg, String manv, Timestamp ngaylam, int tongtien) {
		this.madg = madg;
		this.manv = manv;
		this.ngaylam = ngaylam;
		this.tongtien = tongtien;
	}

	public int getMadg() {
		return madg;
	}

	public void setMadg(int madg) {
		this.madg = madg;
	}

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public Timestamp getNgaylam() {
		return ngaylam;
	}

	public void setNgaylam(Timestamp ngaylam) {
		this.ngaylam = ngaylam;
	}

	public int getTongtien() {
		return tongtien;
	}

	public void setTongtien(int tongtien) {
		this.tongtien = tongtien;
	}
}
