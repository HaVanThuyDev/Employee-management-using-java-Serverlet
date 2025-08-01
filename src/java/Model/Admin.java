
package Model;
public class Admin {
    private int stt;              
    private String tenTaiKhoan;  
    private String matKhau;      

    public Admin(int stt, String tenTaiKhoan, String matKhau) {
        this.stt = stt;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
    }
    public Admin() {
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    @Override
    public String toString() {
        return "Admin{" +
                "stt=" + stt +
                ", tenTaiKhoan='" + tenTaiKhoan + '\'' +
                ", matKhau='" + matKhau + '\'' +
                '}';
    }
}

