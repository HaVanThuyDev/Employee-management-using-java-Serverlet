package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "HomeAdmin", urlPatterns = {"/HomeAdmin"})
public class HomeAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chuyển tiếp trực tiếp đến HomeAdmin.jsp
        request.getRequestDispatcher("/View/HomeAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Xử lý cả yêu cầu POST như GET
    }

    @Override
    public String getServletInfo() {
        return "Servlet for HomeAdmin navigation, forwarding to HomeAdmin.jsp only.";
    }
}
