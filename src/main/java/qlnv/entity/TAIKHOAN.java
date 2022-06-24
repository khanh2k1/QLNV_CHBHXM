package qlnv.entity;

public class TAIKHOAN {
	

	private String tendangnhap;
	private String matkhau;
	private String maNV;
	private boolean quyen;

	public TAIKHOAN() {
	}

	public TAIKHOAN(String tendangnhap, String matkhau, String maNV, boolean quyen) {
		this.tendangnhap = tendangnhap;
		this.matkhau = matkhau;
		this.maNV = maNV;
		this.quyen = quyen;
	}

	public String getTendangnhap() {
		return tendangnhap;
	}

	public void setTendangnhap(String tendangnhap) {
		this.tendangnhap = tendangnhap;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public boolean isQuyen() {
		return quyen;
	}

	public void setQuyen(boolean quyen) {
		this.quyen = quyen;
	}
}
