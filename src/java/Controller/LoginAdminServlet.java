package Controller;

import Dao.AdminDao;
import Model.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginAdminServlet", urlPatterns = {"/LoginAdminServlet"})
public class LoginAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Serve the login page
        forwardToLogin(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tenTaiKhoan = request.getParameter("tenTaiKhoan");
        String matKhau = request.getParameter("matKhau");

        if (isValidCredentials(tenTaiKhoan, matKhau)) {
            attemptLogin(request, response, tenTaiKhoan, matKhau);
        } else {
            request.setAttribute("errorMessage", "Tên tài khoản và mật khẩu không được để trống!");
            forwardToLogin(request, response);
        }
    }

    private boolean isValidCredentials(String tenTaiKhoan, String matKhau) {
        return tenTaiKhoan != null && !tenTaiKhoan.isEmpty() && matKhau != null && !matKhau.isEmpty();
    }

    private void attemptLogin(HttpServletRequest request, HttpServletResponse response, String tenTaiKhoan, String matKhau)
            throws ServletException, IOException {
        AdminDao adminDao = new AdminDao();
        if (adminDao.login(tenTaiKhoan, matKhau)) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", adminDao.getAdminByUsername(tenTaiKhoan));
            response.sendRedirect(request.getContextPath() + "/View/HomeAdmin.jsp");
        } else {
            request.setAttribute("errorMessage", "Tên tài khoản hoặc mật khẩu không đúng!");
            forwardToLogin(request, response);
        }
    }

    private void forwardToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/View/LoginAdmin.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for handling admin login.";
    }
}
