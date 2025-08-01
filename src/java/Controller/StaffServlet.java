package Controller;

import Dao.StaffDao;
import Model.wage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StaffServlet", urlPatterns = {"/StaffServlet"})
public class StaffServlet extends HttpServlet {

    private final StaffDao staffDao = new StaffDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";// Default actio
        }

        switch (action) {
            case "list":
                listStaff(request, response);
                break;
            case "delete":
                deleteStaff(request, response);
                break;
            case "search":
                searchStaff(request, response);
                break;
            default:
                listStaff(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addStaff(request, response);
                break;
            case "update":
                updateStaff(request, response);
                break;
            default:
                listStaff(request, response);
                break;
        }
    }

    private void listStaff(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<wage> staffList = staffDao.getAllStaff();
        request.setAttribute("staffList", staffList);
        request.getRequestDispatcher("/View/OffceStaf.jsp").forward(request, response);
    }

    private void addStaff(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String mnv = request.getParameter("mnv");
        String hoTen = request.getParameter("hoTen");
        String ngaySinh = request.getParameter("ngaySinh");
        String diaChi = request.getParameter("diaChi");
        String soDienThoai = request.getParameter("soDienThoai");
        String chucVu = request.getParameter("chucVu");

        wage newStaff = new wage(mnv, hoTen, ngaySinh, diaChi, soDienThoai, chucVu);
        boolean isAdded = staffDao.addStaff(newStaff);

        if (isAdded) {
            response.sendRedirect("StaffServlet?action=list");
        } else {
            request.setAttribute("error", "Failed to add staff.");
            request.getRequestDispatcher("/View/OffceStaf.jsp").forward(request, response);
        }
    }

    private void updateStaff(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String mnv = request.getParameter("mnv");
        String hoTen = request.getParameter("hoTen");
        String ngaySinh = request.getParameter("ngaySinh");
        String diaChi = request.getParameter("diaChi");
        String soDienThoai = request.getParameter("soDienThoai");
        String chucVu = request.getParameter("chucVu");

        wage updatedStaff = new wage(mnv, hoTen, ngaySinh, diaChi, soDienThoai, chucVu);
        boolean isUpdated = staffDao.updateStaff(updatedStaff);

        if (isUpdated) {
            response.sendRedirect("StaffServlet?action=list");
        } else {
            request.setAttribute("error", "Failed to update staff.");
            request.getRequestDispatcher("/View/OffceStaf.jsp").forward(request, response);
        }
    }
    private void deleteStaff(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
    String mnv = request.getParameter("mnv"); // Lấy mã nhân viên từ request

    boolean isDeleted = staffDao.deleteStaff(mnv); // Gọi hàm xóa từ DAO

    if (isDeleted) {
        // Chuyển hướng về danh sách nhân viên nếu xóa thành công
        response.sendRedirect("StaffServlet?action=list");
    } else {
        // Hiển thị thông báo lỗi nếu xóa thất bại
        request.setAttribute("error", "Failed to delete staff.");
        request.getRequestDispatcher("/View/OffceStaf.jsp").forward(request, response);
    }
}


    private void searchStaff(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchKeyword = request.getParameter("keyword");
        List<wage> staffList = staffDao.searchStaffByName(searchKeyword);
        request.setAttribute("staffList", staffList);
        request.getRequestDispatcher("/View/OffceStaf.jsp").forward(request, response);
    }
}
