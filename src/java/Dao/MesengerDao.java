package Dao;

import Utils.MySQLConnectionExample;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import Model.Messenger;

public class MesengerDao {

    // Lấy tất cả tin nhắn từ database
    public List<Messenger> getAllMessages() {
        List<Messenger> messages = new ArrayList<>();
        String query = "SELECT * FROM Messenger";
        try (Connection connection = new MySQLConnectionExample().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                Messenger message = new Messenger(
                        resultSet.getInt("id"),
                        resultSet.getString("sender"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("createdAt").toLocalDateTime()
                );
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    // Thêm tin nhắn vào database
    public boolean addMessage(Messenger message) {
        String query = "INSERT INTO Messenger (sender, content) VALUES (?, ?)";
        try (Connection connection = new MySQLConnectionExample().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, message.getSender()); // Tên người gửi
            statement.setString(2, message.getContent()); // Nội dung tin nhắn
            
            return statement.executeUpdate() > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa tin nhắn khỏi database theo ID
    public boolean deleteMessage(int id) {
        String query = "DELETE FROM Messenger WHERE id = ?";
        try (Connection connection = new MySQLConnectionExample().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, id); // ID tin nhắn cần xóa
            
            return statement.executeUpdate() > 0; // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
