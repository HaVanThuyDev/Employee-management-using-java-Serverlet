package Model;

import java.time.LocalDateTime;

public class Messenger {
    private int id;               // ID tự động tăng
    private String sender;        // Tên người gửi
    private String content;       // Nội dung tin nhắn
    private LocalDateTime createdAt; // Thời gian tạo tin nhắn

    // Constructor không tham số
    public Messenger() {
    }

    // Constructor có tham số đầy đủ
    public Messenger(int id, String sender, String content, LocalDateTime createdAt) {
        this.id = id;
        this.sender = sender;
        this.content = content;
        this.createdAt = createdAt;
    }

    // Constructor để thêm mới tin nhắn (không cần ID và createdAt)
    public Messenger(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    // Getter và Setter cho ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter và Setter cho sender
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    // Getter và Setter cho content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Getter và Setter cho createdAt
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Phương thức tiện ích để hiển thị tin nhắn
    @Override
    public String toString() {
        return "Messenger{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
