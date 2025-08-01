package Controller;

import Model.Notification;
import Dao.NotificationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "NotificationServlet", urlPatterns = {"/NotificationServlet"})
public class NotificationServlet extends HttpServlet {

    private NotificationDao notificationDao;
    private static final Logger LOGGER = Logger.getLogger(NotificationServlet.class.getName());

    @Override
    public void init() throws ServletException {
        // Initialize the DAO for database interactions
        notificationDao = new NotificationDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action == null ? "list" : action) {
                case "delete":
                    handleDeleteNotification(request, response);
                    break;
                default:
                    handleListNotifications(request, response);
                    break;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error processing GET request", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "add":
                    handleAddNotification(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/NotificationServlet");
                    break;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error processing POST request", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }

    /**
     * Handles listing all notifications.
     */
    private void handleListNotifications(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Notification> notifications = notificationDao.getAllNotifications(); // Fetch all notifications
        request.setAttribute("notifications", notifications); // Set notifications in request scope
        request.getRequestDispatcher("/View/Notification.jsp").forward(request, response); // Forward to JSP
    }

    /**
     * Handles adding a new notification.
     */
    private void handleAddNotification(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        String text = request.getParameter("text");
        Notification newNotification = new Notification(text);

        if (!notificationDao.addNotification(newNotification)) {
            request.setAttribute("error", "Failed to add notification.");
        }

        response.sendRedirect(request.getContextPath() + "/NotificationServlet");
    }

    /**
     * Handles deleting a notification by ID.
     */
    private void handleDeleteNotification(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean isDeleted = notificationDao.deleteNotification(id);

        if (!isDeleted) {
            request.setAttribute("error", "Failed to delete notification.");
        }

        response.sendRedirect(request.getContextPath() + "/NotificationServlet");
    }

    @Override
    public String getServletInfo() {
        return "Servlet for managing notifications.";
    }
}
