package Controller;

import Dao.ReportDao;
import Model.Report;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "employeereportServlet", urlPatterns = {"/employeereportServlet"})
public class employeereportServlet extends HttpServlet {
    private final ReportDao reportDao = new ReportDao(); // Sử dụng DAO để thao tác với cơ sở dữ liệu

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chuyển hướng tới trang JSP hiển thị form thêm báo cáo
        request.getRequestDispatcher("/View/employeereport.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        // Kiểm tra action và chỉ xử lý thêm báo cáo
        if (action == null || !action.equals("add")) {
            request.setAttribute("message", "Invalid action. Only 'add' is supported.");
            request.getRequestDispatcher("/View/employeereport.jsp").forward(request, response);
            return;
        }

        addReport(request, response);
    }

    private void addReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String ngayStr = request.getParameter("ngay");
        String text = request.getParameter("text");

        // Kiểm tra dữ liệu đầu vào
        if (ngayStr == null || ngayStr.isEmpty() || text == null || text.isEmpty()) {
            request.setAttribute("message", "Dữ liệu không hợp lệ, vui lòng kiểm tra lại.");
            request.getRequestDispatcher("/View/employeereport.jsp").forward(request, response);
            return;
        }

        try {
            // Chuyển đổi ngày từ chuỗi sang LocalDate
            LocalDate ngay = LocalDate.parse(ngayStr);

            // Tạo đối tượng Report
            Report report = new Report(0, ngay, text); // `0` đại diện cho ID tự tăng trong database

            // Lưu báo cáo vào cơ sở dữ liệu qua DAO
            boolean success = reportDao.addReport(report);

            // Phản hồi kết quả
            if (success) {
                request.setAttribute("message", "success.");
            } else {
                request.setAttribute("message", "Không thêm được báo cáo, vui lòng thử lại.");
            }

            // Chuyển hướng về trang report.jsp
            request.getRequestDispatcher("/View/employeereport.jsp").forward(request, response);

        } catch (DateTimeParseException e) {
            // Lỗi định dạng ngày
            request.setAttribute("message", "Ngày không hợp lệ, vui lòng nhập lại theo định dạng yyyy-MM-dd.");
            request.getRequestDispatcher("/View/employeereport.jsp").forward(request, response);
        } catch (Exception e) {
            // Lỗi hệ thống
            log("Error adding report", e);
            request.setAttribute("message", "Có lỗi xảy ra khi thêm báo cáo: " + e.getMessage());
            request.getRequestDispatcher("/View/employeereport.jsp").forward(request, response);
        }
    }
}
