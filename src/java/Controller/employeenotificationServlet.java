package Controller;

import Dao.NotificationDao;
import Model.Notification;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "employeenotificationServlet", urlPatterns = {"/employeenotificationServlet"})
public class employeenotificationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Khởi tạo NotificationDao để lấy dữ liệu từ DB
            NotificationDao notificationDao = new NotificationDao();
            List<Notification> notifications = notificationDao.getAllNotifications();

            // Lấy thuộc tính text từ mỗi Notification
            List<String> notificationTexts = new ArrayList<>();
            for (Notification notification : notifications) {
                notificationTexts.add(notification.getText());
                System.out.println("Notification Texts: " + notificationTexts);

            }

            // Gán danh sách notificationTexts vào request attribute
            request.setAttribute("notificationTexts", notificationTexts);

            // Chuyển dữ liệu tới JSP
            request.getRequestDispatcher("/View/employee notification.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving data from database.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response); // Xử lý GET giống POST
    }
}
