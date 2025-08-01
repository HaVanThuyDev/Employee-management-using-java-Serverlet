package Model;

public class Notification {
    private int id;
    private String text;

    // Constructor để khởi tạo cả id và text
    public Notification(int id, String text) {
        this.id = id;
        this.text = text;
    }

    // Constructor để khởi tạo chỉ với text
    public Notification(String text) {
        this.text = text;
    }

    // Phương thức getter để truy cập thuộc tính id
    public int getId() {
        return id;
    }

    // Phương thức setter để thay đổi thuộc tính id
    public void setId(int id) {
        this.id = id;
    }

    // Phương thức getter để truy cập thuộc tính text
    public String getText() {
        return text;
    }

    // Phương thức setter để thay đổi thuộc tính text
    public void setText(String text) {
        this.text = text;
    }

    // Phương thức để hiển thị thông tin của đối tượng Notification
    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
