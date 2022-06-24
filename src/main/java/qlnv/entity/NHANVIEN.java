package qlnv.entity;
import java.sql.Date;
public class NHANVIEN {

	private String maNV;
	private String hoten;
	private String gioitinh;
	
	private Date ngaysinh;
	private String diachi;
	private String sdt;
	private String email;
	private String luongcoban;
	private String trangthai;
	private int loaiNV;

	public NHANVIEN() {
	}

	public NHANVIEN(String maNV, String hoten, String gioitinh, Date ngaysinh, String diachi, String sdt, String email, String luongcoban, String trangthai, int loaiNV) {
		this.maNV = maNV;
		this.hoten = hoten;
		this.gioitinh = gioitinh;
		this.ngaysinh = ngaysinh;
		this.diachi = diachi;
		this.sdt = sdt;
		this.email = email;
		this.luongcoban = luongcoban;
		this.trangthai = trangthai;
		this.loaiNV = loaiNV;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public Date getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLuongcoban() {
		return luongcoban;
	}

	public void setLuongcoban(String luongcoban) {
		this.luongcoban = luongcoban;
	}

	public int getLoaiNV() {
		return loaiNV;
	}

	public void setLoaiNV(int loaiNV) {
		this.loaiNV = loaiNV;
	}

}
