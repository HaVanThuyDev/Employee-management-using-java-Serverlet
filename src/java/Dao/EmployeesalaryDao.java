package Dao;

import Model.Employeesalary;
import Utils.MySQLConnectionExample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeesalaryDao {

    private Connection conn;
    private PreparedStatement ps;

    // Fetch all employee salary records
    public List<Employeesalary> getAllEmployees() {
        List<Employeesalary> employeeList = new ArrayList<>();
        String query = "SELECT * FROM Luong";

        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String hoTen = rs.getString("tenNhanVien");
                double luongCoBan = rs.getDouble("luongCoBan");
                int soNgayLam = rs.getInt("soNgayLam");
                double luongPhuCap = rs.getDouble("luongPhuCap");

                Employeesalary employee = new Employeesalary(id, hoTen, luongCoBan, soNgayLam, luongPhuCap);
                employeeList.add(employee);
            }

            System.out.println("Dữ liệu lấy được từ database: " + employeeList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return employeeList;
    }

    // Add a new salary record
    public boolean addEmployeeSalary(Employeesalary employee) {
    String query = "INSERT INTO Luong (tenNhanVien, luongCoBan, soNgayLam, luongPhuCap) VALUES (?, ?, ?, ?)";
    try {
        MySQLConnectionExample connectionExample = new MySQLConnectionExample();
        conn = connectionExample.getConnection();
        ps = conn.prepareStatement(query);

        // Set parameters
        ps.setString(1, employee.getHoTen());
        ps.setDouble(2, employee.getLuongCoBan());
        ps.setInt(3, employee.getSoNgayLam());
        ps.setDouble(4, employee.getLuongPhuCap());

        // Execute update
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeResources();
    }
    return false;
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
            ps.setInt(5, employee.getId()); // Use employee ID to identify the record

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Return true if update was successful
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return false; // Return false if update failed
    }

    // Delete an existing salary record
    public boolean deleteEmployeeSalary(int id) {
        String query = "DELETE FROM Luong WHERE id = ?";
        
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id); // Set the ID parameter for deletion
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Return true if deletion was successful
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return false; // Return false if deletion failed
    }

    // Close resources
    private void closeResources() {
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employeesalary> getAllSalaries() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
