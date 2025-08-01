package Controller;

import Dao.MesengerDao;
import Model.Messenger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "MessengerServlet", urlPatterns = {"/MessengerServlet"})
public class MessengerServlet extends HttpServlet {

    private final MesengerDao messengerDao = new MesengerDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy tất cả tin nhắn từ database
        List<Messenger> messages = messengerDao.getAllMessages();

        // Gửi dữ liệu sang JSP
        request.setAttribute("messages", messages);
        RequestDispatcher dispatcher = request.getRequestDispatcher("messenger.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Thêm tin nhắn
            String sender = request.getParameter("sender");
            String content = request.getParameter("content");

            Messenger message = new Messenger();
            message.setSender(sender);
            message.setContent(content);
            message.setCreatedAt(LocalDateTime.now());

            messengerDao.addMessage(message);

        } else if ("delete".equals(action)) {
            // Xóa tin nhắn theo ID
            int id = Integer.parseInt(request.getParameter("id"));
            messengerDao.deleteMessage(id);
        }

        // Quay lại trang hiển thị danh sách tin nhắn
        response.sendRedirect("MessengerServlet");
    }
}
