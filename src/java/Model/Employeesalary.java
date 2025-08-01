package Model;

public class Employeesalary {
    private int id;               // ID của nhân viên
    private String hoTen;         // Họ tên của nhân viên
    private double luongCoBan;    // Lương cơ bản
    private int soNgayLam;        // Số ngày làm
    private double luongPhuCap;  // Lương phụ cấp

    // Constructor với tất cả các tham số
    public Employeesalary(int id, String hoTen, double luongCoBan, int soNgayLam, double luongPhuCap) {
        this.id = id;
        this.hoTen = hoTen;
        this.luongCoBan = luongCoBan;
        this.soNgayLam = soNgayLam;
        this.luongPhuCap = luongPhuCap;
    }

    // Constructor mặc định
    public Employeesalary() {
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan; // Tính lại tổng lương có thể cần cập nhật lại
    }

    public int getSoNgayLam() {
        return soNgayLam;
    }

    public void setSoNgayLam(int soNgayLam) {
        this.soNgayLam = soNgayLam; // Tính lại tổng lương có thể cần cập nhật lại
    }

    public double getLuongPhuCap() {
        return luongPhuCap;
    }

    public void setLuongPhuCap(double luongPhuCap) {
        this.luongPhuCap = luongPhuCap; // Tính lại tổng lương có thể cần cập nhật lại
    }

    public double getTongLuong() {
        double luongChinh = (soNgayLam > 20) ? luongCoBan * soNgayLam : (luongCoBan * soNgayLam) * 0.8;
        return luongChinh + luongPhuCap; // Tính và trả về tổng lương
    }

    @Override
    public String toString() {
        return "Luong{" +
                "id=" + id +
                ", hoTen='" + hoTen + '\'' +
                ", luongCoBan=" + luongCoBan +
                ", soNgayLam=" + soNgayLam +
                ", luongPhuCap=" + luongPhuCap +
                ", tongLuong=" + getTongLuong() + // Sử dụng getTongLuong để lấy tổng lương
                '}';
    }
}
