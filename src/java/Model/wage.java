package Model;

import java.util.Objects;

public class wage {
    // Attributes
    private String mnv;           // Mã nhân viên
    private String hoTen;         // Họ tên
    private String ngaySinh;      // Ngày sinh
    private String diaChi;        // Địa chỉ
    private String soDienThoai;   // Số điện thoại
    private String chucVu;        // Chức vụ

    // Constructor
    public wage(String mnv, String hoTen, String ngaySinh, String diaChi, String soDienThoai, String chucVu) {
        this.mnv = mnv;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.chucVu = chucVu;
    }

    // Default Constructor
    public wage() {
    }

    // Getter and Setter for mnv
    public String getMnv() {
        return mnv;
    }

    public void setMnv(String mnv) {
        this.mnv = mnv;
    }

    // Getter and Setter for hoTen
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    // Getter and Setter for ngaySinh
    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    // Getter and Setter for diaChi
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    // Getter and Setter for soDienThoai
    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    // Getter and Setter for chucVu
    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    // Override equals for duplication check (based on mnv)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        wage wage = (wage) o;
        return Objects.equals(mnv, wage.mnv); // So sánh dựa trên mã nhân viên
    }

    // Override hashCode to ensure consistency with equals
    @Override
    public int hashCode() {
        return Objects.hash(mnv);
    }

    // toString method for debugging and display
    @Override
    public String toString() {
        return "wage{" +
                "mnv='" + mnv + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", ngaySinh='" + ngaySinh + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", chucVu='" + chucVu + '\'' +
                '}';
    }
}
