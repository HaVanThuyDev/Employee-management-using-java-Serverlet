package Dao;

import Model.Notification;
import Utils.MySQLConnectionExample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDao {

    private Connection conn;
    private PreparedStatement ps;

    // Lấy danh sách tất cả thông báo
  public List<Notification> getAllNotifications() throws SQLException {
    List<Notification> notifications = new ArrayList<>();
    String query = "SELECT * FROM Notification";

    try {
        MySQLConnectionExample connectionExample = new MySQLConnectionExample();
        conn = connectionExample.getConnection();
        ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String text = rs.getString("text");
            notifications.add(new Notification(id, text));
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    } finally {
        if (ps != null) ps.close();
        if (conn != null) conn.close();
    }
    return notifications;
}

    public boolean addNotification(Notification notification) {
        String query = "INSERT INTO Notification (text) VALUES (?)";
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, notification.getText());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm thông báo: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources();
        }
    }

    // Cập nhật thông báo
    public boolean updateNotification(Notification notification) {
        String query = "UPDATE Notification SET text = ? WHERE id = ?";
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, notification.getText());
            ps.setInt(2, notification.getId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật thông báo: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources();
        }
    }

    // Lấy thông báo theo ID
    public Notification getNotificationById(int id) {
        String query = "SELECT * FROM Notification WHERE id = ?";
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Notification(rs.getInt("id"), rs.getString("text"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy thông báo theo ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return null;
    }

    // Xóa thông báo
    public boolean deleteNotification(int id) {
        String query = "DELETE FROM Notification WHERE id = ?";
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa thông báo: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources();
        }
    }

    // Đóng tài nguyên kết nối
    private void closeResources() {
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
