
package Model;
import java.time.LocalDate;

public class Report {
    private int stt;           // Số thứ tự
    private LocalDate ngay;    // Ngày
    private String text;       // Nội dung báo cáo

    // Constructor không tham số
    public Report() {
    }

    // Constructor có tham số
    public Report(int stt, LocalDate ngay, String text) {
        this.stt = stt;
        this.ngay = ngay;
        this.text = text;
    }

    // Getter và Setter
    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public LocalDate getNgay() {
        return ngay;
    }

    public void setNgay(LocalDate ngay) {
        this.ngay = ngay;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Phương thức toString để hiển thị thông tin
    @Override
    public String toString() {
        return "Report{" +
                "stt=" + stt +
                ", ngay=" + ngay +
                ", text='" + text + '\'' +
                '}';
    }
}

