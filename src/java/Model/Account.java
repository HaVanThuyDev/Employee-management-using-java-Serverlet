package Model;

import Dao.MD5DAO; // Đảm bảo bạn đã import lớp MD5DAO

public class Account {
    private int stt; // Account index
    private String hoTen; // Full name
    private String soDienThoai; // Phone number
    private String matKhau; // Password
    private String status; // Account status

    private static final MD5DAO md5Dao = new MD5DAO(); // Tạo thể hiện MD5DAO 

    // Default constructor
    public Account() {
    }

    // Constructor with all parameters
    public Account(int stt, String hoTen, String soDienThoai, String matKhau, String status) {
        this.stt = stt;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        setMatKhau(matKhau); // Mã hóa mật khẩu khi xây dựng đối tượng
        this.status = status;
    }

    // Constructor without password (for safer operations)
    public Account(int stt, String hoTen, String soDienThoai, String status) {
        this.stt = stt;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.status = status;
    }

    // Getters and Setters
    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = md5Dao.hashWithMD5(matKhau); // Mã hóa mật khẩu trước khi lưu
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // ToString method for debugging purposes
    @Override
    public String toString() {
        return "Account{" +
                "stt=" + stt +
                ", hoTen='" + hoTen + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
