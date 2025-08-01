package Dao;

import Model.Admin;
import Utils.MySQLConnectionExample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {

    private Connection conn;
    private PreparedStatement ps;

    // Method to verify login credentials
    public boolean login(String tenTaiKhoan, String matKhau) {
        String query = "SELECT * FROM Admin WHERE tenTaiKhoan = ? AND matKhau = ?"; // Replace "admin" with your table name
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);

            // Set the username and password parameters
            ps.setString(1, tenTaiKhoan);
            ps.setString(2, matKhau);

            ResultSet rs = ps.executeQuery();

            // If a matching record exists, return true
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false; // Return false if credentials are invalid
    }

    // Method to fetch admin details by username
    public Admin getAdminByUsername(String tenTaiKhoan) {
        String query = "SELECT * FROM Admin WHERE tenTaiKhoan = ?"; // Replace "admin" with your table name
        Admin admin = null;
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);

            // Set the username parameter
            ps.setString(1, tenTaiKhoan);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int stt = rs.getInt("stt");
                String username = rs.getString("tenTaiKhoan");
                String password = rs.getString("matKhau");

                // Create an Admin object
                admin = new Admin(stt, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return admin;
    }

    // Close database resources
    private void closeResources() {
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
