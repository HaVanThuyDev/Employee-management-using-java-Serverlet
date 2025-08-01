package Dao;

import Model.Report;
import Utils.MySQLConnectionExample;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class ReportDao {

    // hiển thị database 
    public List<Report> getAllReports() {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT * FROM Report";
        try (Connection connection = new MySQLConnectionExample().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                Report report = new Report(
                        resultSet.getInt("stt"),
                        resultSet.getDate("ngay").toLocalDate(),
                        resultSet.getString("text")
                );
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    // thêm
    public boolean addReport(Report report) {
    String query = "INSERT INTO Report (ngay, text) VALUES (?, ?)";
    try (Connection connection = new MySQLConnectionExample().getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        
        statement.setDate(1, Date.valueOf(report.getNgay())); // Lưu ngày
        statement.setString(2, report.getText());             // Lưu nội dung
        return statement.executeUpdate() > 0; // Trả về true nếu thêm thành công
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


    // sửa
    public boolean updateReport(Report report) {
        String query = "UPDATE Report SET ngay = ?, text = ? WHERE stt = ?";
        try (Connection connection = new MySQLConnectionExample().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setDate(1, Date.valueOf(report.getNgay()));
            statement.setString(2, report.getText());
            statement.setInt(3, report.getStt());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // xóa
    public boolean deleteReport(int stt) {
        String query = "DELETE FROM Report WHERE stt = ?";
        try (Connection connection = new MySQLConnectionExample().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, stt);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to display all records (for testing purposes)
    
}
