package qlnv.entity;

import java.util.List;

public class NANGLUC {
	private String maNV;
	private List<Integer> listMaDV;

	public NANGLUC() {
	}

	public NANGLUC(String maNV, List<Integer> listMaDV) {
		this.maNV = maNV;
		this.listMaDV = listMaDV;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public List<Integer> getListMaDV() {
		return listMaDV;
	}

	public void setListMaDV(List<Integer> listMaDV) {
		this.listMaDV = listMaDV;
	}
}
