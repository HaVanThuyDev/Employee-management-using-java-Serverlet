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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private final AccountDao accountDao = new AccountDao();
    private static final String LOGIN_ACTION = "login";
    private static final String REGISTER_ACTION = "register";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/View/LoginUse.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (LOGIN_ACTION.equals(action)) {
            handleLogin(request, response);
        } else if (REGISTER_ACTION.equals(action)) {
            handleRegistration(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }
    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String soDienThoai = request.getParameter("soDienThoai");
        String matKhau = request.getParameter("matKhau");

        Account account = accountDao.login(soDienThoai, matKhau);
        if (account != null) {
            if ("approved".equals(account.getStatus())) {
                // Store user in session
                HttpSession session = request.getSession();
                session.setAttribute("user", account);
                session.setAttribute("userName", account.getHoTen());
                response.sendRedirect(request.getContextPath() + "/View/HomeWage.jsp");
            } else {
                // Account not approved
                request.setAttribute("errorMessage", "Tài khoản của bạn chưa được duyệt.");
                request.getRequestDispatcher("/View/LoginUse.jsp").forward(request, response);
            }
        } else {
            // Login failed
            request.setAttribute("errorMessage", "Số điện thoại hoặc mật khẩu không đúng.");
            request.getRequestDispatcher("/View/LoginUse.jsp").forward(request, response);
        }
    }

    private void handleRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String hoTen = request.getParameter("hoTen");
        String soDienThoai = request.getParameter("soDienThoai");
        String matKhau = request.getParameter("matKhau");

        boolean isRegistered = accountDao.register(hoTen, soDienThoai, matKhau);
        if (isRegistered) {
            request.setAttribute("message", "Đăng ký thành công, vui lòng đăng nhập.");
        } else {
            request.setAttribute("errorMessage", "Đăng ký thất bại, xin vui lòng thử lại.");
        }
        request.getRequestDispatcher("/View/LoginUse.jsp").forward(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Handles login and registration functionality.";
    }
}
