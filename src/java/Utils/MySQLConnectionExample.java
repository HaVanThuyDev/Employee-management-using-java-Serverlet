package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionExample {

    private static final String URL = "jdbc:mysql://localhost:3306/Doantotnghiep";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "11032003";

    // Method to establish a database connection
    public Connection getConnection() throws SQLException {
        try {
            // Ensure the JDBC driver is loaded
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish and return the connection
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
            throw new SQLException("Database driver not found.", e);
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
            throw e;
        }
    }

    // Main method for testing connection
    public static void main(String[] args) {
        MySQLConnectionExample example = new MySQLConnectionExample();
        try (Connection connection = example.getConnection()) {
            if (connection != null) {
                System.out.println("Connected to the database successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }
}
