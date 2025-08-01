package Controller;

import Dao.AccountDao;
import Model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "EmployeeAccountServlet", urlPatterns = {"/employeeaccountServlet"})
public class EmployeeAccountServlet extends HttpServlet {

    private AccountDao accountDao;

    @Override
    public void init() throws ServletException {
        accountDao = new AccountDao(); // Initialize DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get existing session

        // Check if the user is logged in
        if (session != null && session.getAttribute("user") != null) {
            Account loggedInUser = (Account) session.getAttribute("user"); // Get user info from session

            // Refresh user information from the database (optional, to ensure latest data)
            Account userFromDB = accountDao.searchAccounts(loggedInUser.getSoDienThoai())
                    .stream()
                    .findFirst()
                    .orElse(null);

            // Check if the user exists in the database
            if (userFromDB != null) {
                request.setAttribute("account", userFromDB); // Set account data for the JSP
                request.getRequestDispatcher("/View/employeeaccount.jsp").forward(request, response);
            } else {
                // Redirect to login if the account no longer exists
                response.sendRedirect(request.getContextPath() + "/LoginServlet");
            }
        } else {
            // Redirect to login if no user is logged in
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get existing session

        // Check if the user is logged in
        if (session != null && session.getAttribute("user") != null) {
            Account loggedInUser = (Account) session.getAttribute("user"); // Get user info from session

            // Retrieve updated account details from the request
            String hoTen = request.getParameter("hoTen");
            String matKhau = request.getParameter("matKhau");

            // Validate input
            if (hoTen == null || hoTen.isEmpty() || matKhau == null || matKhau.isEmpty()) {
                request.setAttribute("errorMessage", "Họ tên và mật khẩu không được để trống.");
                request.setAttribute("account", loggedInUser); // Show original account details
                request.getRequestDispatcher("/View/employeeaccount.jsp").forward(request, response);
                return;
            }

            // Update account information in the database
            boolean isUpdated = accountDao.updateAccount(loggedInUser.getSoDienThoai(), matKhau, hoTen);

            if (isUpdated) {
                // Update the session with the latest account info
                loggedInUser.setHoTen(hoTen);
                loggedInUser.setMatKhau(matKhau);
                session.setAttribute("user", loggedInUser);

                request.setAttribute("successMessage", "Thông tin tài khoản đã được cập nhật thành công.");
            } else {
                request.setAttribute("errorMessage", "Cập nhật thông tin tài khoản thất bại.");
            }

            // Refresh the user information from the database and forward to the JSP
            Account userFromDB = accountDao.searchAccounts(loggedInUser.getSoDienThoai())
                    .stream()
                    .findFirst()
                    .orElse(null);

            request.setAttribute("account", userFromDB); // Set updated account data
            request.getRequestDispatcher("/View/employeeaccount.jsp").forward(request, response);
        } else {
            // Redirect to login if no user is logged in
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
        }
    }

    @Override
    public String getServletInfo() {
        return "Employee Account Management Servlet - Displays and updates the logged-in user's account information.";
    }
}
