package Controller;

import Dao.ReportDao;
import Model.Report;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ReportServlet", urlPatterns = {"/ReportServlet"})
public class ReportServlet extends HttpServlet {

    private final ReportDao reportDao = new ReportDao(); // Khởi tạo đối tượng DAO

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "view"; // Giá trị mặc định
        }

        try {
            switch (action) {
                case "view":
                    displayReports(request, response);
                    break;
                case "delete":
                    deleteReport(request, response);
                    break;
                default:
                    response.getWriter().println("Invalid action");
            }
        } catch (Exception e) {
            log("Error in doGet", e); // Ghi lại log chi tiết
            throw new ServletException("Error handling GET request: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            response.getWriter().println("Action is required.");
            return;
        }

        try {
            switch (action) {
                case "add":
                    addReport(request, response);
                    break;
                case "update":
                    updateReport(request, response);
                    break;
                default:
                    response.getWriter().println("Invalid action");
            }
        } catch (Exception e) {
            log("Error in doPost", e);
            throw new ServletException("Error handling POST request: " + e.getMessage(), e);
        }
    }

    private void displayReports(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Report> reports = reportDao.getAllReports();
            if (reports == null || reports.isEmpty()) {
                request.setAttribute("message", "Không có báo cáo nào để hiển thị.");
            }
            request.setAttribute("reports", reports);
            request.getRequestDispatcher("/View/ReportAdmin.jsp").forward(request, response);
        } catch (Exception e) {
            log("Error fetching reports", e);
            throw new ServletException("Không thể hiển thị danh sách báo cáo: " + e.getMessage(), e);
        }
    }

    private void addReport(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String ngayStr = request.getParameter("ngay");
        String text = request.getParameter("text");

        if (ngayStr == null || text == null || text.isEmpty()) {
            response.getWriter().println("Dữ liệu không hợp lệ, vui lòng kiểm tra lại.");
            return;
        }

        try {
            LocalDate ngay = LocalDate.parse(ngayStr);
            Report report = new Report(0, ngay, text);
            boolean success = reportDao.addReport(report);

            if (success) {
                response.sendRedirect("ReportServlet?action=view");
            } else {
                response.getWriter().println("Không thêm được báo cáo.");
            }
        } catch (DateTimeParseException e) {
            response.getWriter().println("Ngày không hợp lệ, vui lòng nhập lại theo định dạng yyyy-MM-dd.");
        } catch (Exception e) {
            log("Error adding report", e);
            response.getWriter().println("Có lỗi xảy ra khi thêm báo cáo: " + e.getMessage());
        }
    }

    private void updateReport(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String sttStr = request.getParameter("stt");
        String ngayStr = request.getParameter("ngay");
        String text = request.getParameter("text");

        if (sttStr == null || ngayStr == null || text == null || text.isEmpty()) {
            response.getWriter().println("Dữ liệu không hợp lệ, vui lòng kiểm tra lại.");
            return;
        }

        try {
            int stt = Integer.parseInt(sttStr);
            LocalDate ngay = LocalDate.parse(ngayStr);
            Report report = new Report(stt, ngay, text);
            boolean success = reportDao.updateReport(report);

            if (success) {
                response.sendRedirect("ReportServlet?action=view");
            } else {
                response.getWriter().println("Không cập nhật được báo cáo.");
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("STT phải là một số nguyên.");
        } catch (DateTimeParseException e) {
            response.getWriter().println("Ngày không hợp lệ, vui lòng nhập lại theo định dạng yyyy-MM-dd.");
        } catch (Exception e) {
            log("Error updating report", e);
            response.getWriter().println("Có lỗi xảy ra khi cập nhật báo cáo: " + e.getMessage());
        }
    }

    private void deleteReport(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String sttStr = request.getParameter("stt");

        if (sttStr == null || sttStr.isEmpty()) {
            response.getWriter().println("STT không được để trống.");
            return;
        }

        try {
            int stt = Integer.parseInt(sttStr);
            boolean success = reportDao.deleteReport(stt);

            if (success) {
                response.sendRedirect("ReportServlet?action=view");
            } else {
                response.getWriter().println("Không xóa được báo cáo.");
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("STT phải là một số nguyên.");
        } catch (Exception e) {
            log("Error deleting report", e);
            response.getWriter().println("Có lỗi xảy ra khi xóa báo cáo: " + e.getMessage());
        }
    }
}
