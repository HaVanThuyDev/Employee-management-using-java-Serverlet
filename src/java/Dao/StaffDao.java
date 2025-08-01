package Dao;

import Model.wage;
import Utils.MySQLConnectionExample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {

    private Connection conn;
    private PreparedStatement ps;

    // Fetch all staff records
    public List<wage> getAllStaff() {
        List<wage> staffList = new ArrayList<>();
        String query = "SELECT * FROM Nhanvien"; // Replace "staff" with your table name
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String mnv = rs.getString("mnv");
                String hoTen = rs.getString("hoTen");
                String ngaySinh = rs.getString("ngaySinh");
                String diaChi = rs.getString("diaChi");
                String soDienThoai = rs.getString("soDienThoai");
                String chucVu = rs.getString("chucVu");

                // Create a wage object
                wage staff = new wage(mnv, hoTen, ngaySinh, diaChi, soDienThoai, chucVu);
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return staffList;
    }

    // Add a new staff record
    public boolean addStaff(wage staff) {
        String query = "INSERT INTO Nhanvien (mnv, hoTen, ngaySinh, diaChi, soDienThoai, chucVu) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, staff.getMnv());
            ps.setString(2, staff.getHoTen());
            ps.setString(3, staff.getNgaySinh());
            ps.setString(4, staff.getDiaChi());
            ps.setString(5, staff.getSoDienThoai());
            ps.setString(6, staff.getChucVu());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Return true if insertion was successful
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false; // Return false if insertion failed
    }

    // Update a staff record
    public boolean updateStaff(wage staff) {
        String query = "UPDATE Nhanvien SET hoTen = ?, ngaySinh = ?, diaChi = ?, soDienThoai = ?, chucVu = ? WHERE mnv = ?";
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, staff.getHoTen());
            ps.setString(2, staff.getNgaySinh());
            ps.setString(3, staff.getDiaChi());
            ps.setString(4, staff.getSoDienThoai());
            ps.setString(5, staff.getChucVu());
            ps.setString(6, staff.getMnv());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Return true if update was successful
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false; // Return false if update failed
    }

    // Delete a staff record by mnv
    public boolean deleteStaff(String mnv) {
        String query = "DELETE FROM Nhanvien WHERE mnv = ?";
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, mnv);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Return true if deletion was successful
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false; // Return false if deletion failed
    }

    // Search staff by name
    public List<wage> searchStaffByName(String name) {
        List<wage> staffList = new ArrayList<>();
        String query = "SELECT * FROM Nhanvien WHERE hoTen LIKE ?";
        try {
            MySQLConnectionExample connectionExample = new MySQLConnectionExample();
            conn = connectionExample.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String mnv = rs.getString("mnv");
                String hoTen = rs.getString("hoTen");
                String ngaySinh = rs.getString("ngaySinh");
                String diaChi = rs.getString("diaChi");
                String soDienThoai = rs.getString("soDienThoai");
                String chucVu = rs.getString("chucVu");

                // Create a wage object
                wage staff = new wage(mnv, hoTen, ngaySinh, diaChi, soDienThoai, chucVu);
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return staffList;
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
