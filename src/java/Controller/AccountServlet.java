package Controller;

import Dao.AccountDao;
import Dao.MD5DAO; 
import Model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AccountServlet", urlPatterns = {"/AccountServlet"})
public class AccountServlet extends HttpServlet {

    private final AccountDao accountDao = new AccountDao();
    private final MD5DAO md5Dao = new MD5DAO(); // Create an instance of MD5DAO

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String phone = request.getParameter("phone");
        String keyword = request.getParameter("keyword");
        String pageParam = request.getParameter("page");

        try {
            if (action != null && phone != null) {
                handleAction(action, phone, request);
            }
        } catch (Exception e) {
            request.setAttribute("message", "Error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        int currentPage = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
        int itemsPerPage = 10;
        int totalItems;
        List<Account> accounts;

        if (keyword != null && !keyword.trim().isEmpty()) {
            accounts = accountDao.searchAccounts(keyword);
            totalItems = accounts.size();
        } else {
            totalItems = accountDao.getAccountCount();
            accounts = accountDao.getPaginatedAccounts(currentPage, itemsPerPage);
        }

        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        request.setAttribute("accounts", accounts);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/View/Account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("createAccount".equalsIgnoreCase(action)) {
            createAccount(request);
        } else if ("updatePassword".equalsIgnoreCase(action)) {
            updatePassword(request);
        } else {
            doGet(request, response);
        }
    }

    private void createAccount(HttpServletRequest request) {
        String hoTen = request.getParameter("hoTen");
        String soDienThoai = request.getParameter("soDienThoai");
        String matKhau = request.getParameter("matKhau");
        String status = "active"; // Hoặc một trạng thái phù hợp

        // Mã hóa mật khẩu trước khi lưu
        String hashedPassword = md5Dao.hashWithMD5(matKhau); // Mã hóa MD5 Pass

        Account newAccount = new Account(0, hoTen, soDienThoai, hashedPassword, status);
        boolean success = accountDao.addAccount(newAccount);
        String message = success ? "Account created successfully!" : "Failed to create account.";
        request.setAttribute("message", message);
    }

    private void updatePassword(HttpServletRequest request) {
        String phone = request.getParameter("phone");
        String newPassword = request.getParameter("newPassword");

        String hashedPassword = md5Dao.hashWithMD5(newPassword); // Use MD5DAO's method

        boolean success = accountDao.updateAccount(phone, hashedPassword);
        String message = success ? "Password updated successfully!" : "Failed to update password.";
        request.setAttribute("message", message);
    }

    private void handleAction(String action, String phone, HttpServletRequest request) {
        boolean success = false;
        String actionMessage;

        switch (action.toLowerCase()) {
            case "delete":
                success = accountDao.deleteAccount(phone);
                actionMessage = success ? "Account deleted successfully!" : "Failed to delete account.";
                break;

            case "approve":
                success = accountDao.updateAccountStatus(phone, "approved");
                actionMessage = success ? "Account approved successfully!" : "Failed to approve account.";
                break;

            case "block":
                success = accountDao.updateAccountStatus(phone, "blocked");
                actionMessage = success ? "Account blocked successfully!" : "Failed to block account.";
                break;

            default:
                actionMessage = "Invalid action!";
        }

        request.setAttribute("message", actionMessage);
    }
}
