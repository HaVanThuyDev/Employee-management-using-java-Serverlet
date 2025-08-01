package Dao;

import Model.Account;
import Utils.MySQLConnectionExample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // Constructor mặc định
    public AccountDao() {
        // Không cần khởi tạo gì thêm ở đây
    }

    // Lấy tất cả tài khoản
   // Count all accounts for pagination
public int getAccountCount() {
    String query = "SELECT COUNT(*) AS total FROM Taikhoan";
    try (Connection conn = new MySQLConnectionExample().getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            return rs.getInt("total");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0; // Return 0 if an error occurs
}



    // Đăng nhập
    public Account login(String soDienThoai, String matKhau) {
        String query = "SELECT * FROM Taikhoan WHERE soDienThoai = ? AND matKhau = ? AND status = 'approved'";
        try (Connection conn = new MySQLConnectionExample().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, soDienThoai);
            ps.setString(2, matKhau);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                            rs.getInt("stt"),
                            rs.getString("hoTen"),
                            rs.getString("soDienThoai"),
                            rs.getString("matKhau"),
                            rs.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Đăng nhập thất bại
    }

    // Đăng ký
    public boolean register(String hoTen, String soDienThoai, String matKhau) {
        String query = "INSERT INTO Taikhoan (hoTen, soDienThoai, matKhau, status) VALUES (?, ?, ?, 'pending')";
        try (Connection conn = new MySQLConnectionExample().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, hoTen);
            ps.setString(2, soDienThoai);
            ps.setString(3, matKhau);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Đăng ký thành công

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Đăng ký thất bại
    }
// Fetch paginated accounts
public List<Account> getPaginatedAccounts(int page, int itemsPerPage) {
    List<Account> accountList = new ArrayList<>();
    String query = "SELECT * FROM Taikhoan LIMIT ? OFFSET ?";
    int offset = (page - 1) * itemsPerPage;

    try (Connection conn = new MySQLConnectionExample().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, itemsPerPage);
        ps.setInt(2, offset);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Account account = new Account(
                        rs.getInt("stt"),
                        rs.getString("hoTen"),
                        rs.getString("soDienThoai"),
                        rs.getString("matKhau"),
                        rs.getString("status")
                );
                accountList.add(account);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return accountList;
}

    // Cập nhật tài khoản
    public boolean updateAccount(String soDienThoai, String matKhau, String hoTen) {
        String query = "UPDATE Taikhoan SET matKhau = ?, hoTen = ? WHERE soDienThoai = ?";
        try (Connection conn = new MySQLConnectionExample().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, matKhau);
            ps.setString(2, hoTen);
            ps.setString(3, soDienThoai);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Cập nhật thành công

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Cập nhật thất bại
    }

    // Cập nhật trạng thái tài khoản
    public boolean updateAccountStatus(String soDienThoai, String status) {
        String query = "UPDATE Taikhoan SET status = ? WHERE soDienThoai = ?";
        try (Connection conn = new MySQLConnectionExample().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, status);
            ps.setString(2, soDienThoai);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Cập nhật thành công

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Cập nhật thất bại
    }
// Search accounts by keyword
// Search accounts by keyword
public List<Account> searchAccounts(String keyword) {
    List<Account> accountList = new ArrayList<>();
    String query = "SELECT * FROM Taikhoan WHERE hoTen LIKE ? OR soDienThoai LIKE ?";
    try (Connection conn = new MySQLConnectionExample().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        String searchKeyword = "%" + keyword + "%";
        ps.setString(1, searchKeyword);
        ps.setString(2, searchKeyword);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Account account = new Account(
                        rs.getInt("stt"),
                        rs.getString("hoTen"),
                        rs.getString("soDienThoai"),
                        rs.getString("matKhau"),
                        rs.getString("status")
                );
                accountList.add(account);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return accountList;
}


    // Xóa tài khoản
    public boolean deleteAccount(String soDienThoai) {
        String query = "DELETE FROM Taikhoan WHERE soDienThoai = ?";
        try (Connection conn = new MySQLConnectionExample().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, soDienThoai);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Xóa thành công

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Xóa thất bại
    }

    // Kiểm tra số điện thoại có tồn tại không
    public boolean isPhoneExist(String soDienThoai) {
        String query = "SELECT 1 FROM Taikhoan WHERE soDienThoai = ?";
        try (Connection conn = new MySQLConnectionExample().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, soDienThoai);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Trả về true nếu tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu không tồn tại hoặc lỗi xảy ra
    }

    public List<Account> getAllAccounts() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean addAccount(Account newAccount) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean updateAccount(String phone, String hashedPassword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
  
}
