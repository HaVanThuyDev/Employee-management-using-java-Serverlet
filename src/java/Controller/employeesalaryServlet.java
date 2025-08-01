package Controller;

import Dao.EmployeesalaryDao;
import Model.Employeesalary;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "employeesalaryServlet", urlPatterns = {"/employeesalaryServlet"})
public class employeesalaryServlet extends HttpServlet {

    private final EmployeesalaryDao employeesalaryDao = new EmployeesalaryDao();

    // Xử lý yêu cầu GET/POST
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Đặt mã hóa UTF-8 cho request và response
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            // Lấy danh sách lương nhân viên từ DAO
          List<Employeesalary> employeeList = employeesalaryDao.getAllEmployees();
if (employeeList == null || employeeList.isEmpty()) {
    System.out.println("Danh sách lương nhân viên trống hoặc không lấy được.");
    request.setAttribute("message", "Không có dữ liệu để hiển thị.");
} else {
    System.out.println("Danh sách lương nhân viên: " + employeeList);
    request.setAttribute("employeeList", employeeList);
}


            // Chuyển tiếp yêu cầu đến trang JSP
            request.getRequestDispatcher("/View/Employee Salary.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // Đặt thông báo lỗi nếu xảy ra lỗi
            request.setAttribute("message", "Đã xảy ra lỗi khi tải danh sách lương nhân viên.");
            request.getRequestDispatcher("/View/Error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet to handle employee salary operations.";
    }
}
