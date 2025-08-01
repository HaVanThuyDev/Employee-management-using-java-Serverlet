package Dao;

import Model.Employeesalary; // Ensure this import aligns with your package structure
import Utils.MySQLConnectionExample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticalDao {

    private Connection conn;
    private PreparedStatement ps;

    // Get total salary from "luong" table
    public double getTotalSalary() {
        double totalSalary = 0;
        String query = "SELECT SUM(tongLuong) AS totalSalary FROM Luong"; // Replace "tongLuong" with your actual column name
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalSalary = rs.getDouble("totalSalary");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return totalSalary;
    }

    // Get the total number of employees from "nhanvien" table
    public int getEmployeeCount() {
        int employeeCount = 0;
        String query = "SELECT COUNT(*) AS employeeCount FROM Nhanvien"; // Replace with your actual table name
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employeeCount = rs.getInt("employeeCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return employeeCount;
    }

    // Get the total number of accounts from "taikhoan" table
    public int getAccountCount() {
        int accountCount = 0;
        String query = "SELECT COUNT(*) AS accountCount FROM Taikhoan"; // Replace with your actual table name
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                accountCount = rs.getInt("accountCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return accountCount;
    }

    // Get the number of distinct manager positions from "nhanvien" table
    public int getManagerCount() {
        int managerCount = 0;
        String query = "SELECT COUNT(*) AS managerCount FROM Nhanvien WHERE chucVu = 'Quản lí'"; // Update value as per actual data
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                managerCount = rs.getInt("managerCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return managerCount;
    }

    // Add a new salary record
    public boolean addEmployeeSalary(Employeesalary employee) {
        String query = "INSERT INTO Luong (tenNhanVien, luongCoBan, soNgayLam, luongPhuCap) VALUES (?, ?, ?, ?)";
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, employee.getHoTen());
            ps.setDouble(2, employee.getLuongCoBan());
            ps.setInt(3, employee.getSoNgayLam());
            ps.setDouble(4, employee.getLuongPhuCap());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // True if addition was successful
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false; // False if addition failed
    }

    // Update an existing salary record
    public boolean updateEmployeeSalary(Employeesalary employee) {
        String query = "UPDATE Luong SET tenNhanVien = ?, luongCoBan = ?, soNgayLam = ?, luongPhuCap = ? WHERE id = ?";
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, employee.getHoTen());
            ps.setDouble(2, employee.getLuongCoBan());
            ps.setInt(3, employee.getSoNgayLam());
            ps.setDouble(4, employee.getLuongPhuCap());
            ps.setInt(5, employee.getId()); // Assuming getId() returns the identifier for the salary record
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // True if update was successful
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false; // False if update failed
    }

    // Delete an existing salary record
    public boolean deleteEmployeeSalary(int id) {
        String query = "DELETE FROM Luong WHERE id = ?";
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // True if deletion was successful
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false; // False if deletion failed
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
