package Controller;

import Dao.StatisticalDao;
import Dao.StaffDao;
import Dao.EmployeesalaryDao;
import Model.Employeesalary;
import Model.wage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "StatisticalServlet", urlPatterns = {"/StatisticalServlet"})
public class StatisticalServlet extends HttpServlet {

    private StatisticalDao statisticalDao;
    private EmployeesalaryDao employeesalaryDao; 
    private StaffDao staffDao;

    @Override
    public void init() throws ServletException {
        statisticalDao = new StatisticalDao();
        employeesalaryDao = new EmployeesalaryDao(); 
        staffDao = new StaffDao(); 
    }

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String action = request.getParameter("action");
    
    if ("exportEmployeeSalaries".equals(action)) {
        exportEmployeeSalaries(request, response);
    } else if ("export".equals(action)) {
        exportDataAsJson(response);
    } else {
        loadStatistics(request, response);
    }
}
 

    private void loadStatistics(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            // Lấy dữ liệu từ DAO
            double totalSalary = statisticalDao.getTotalSalary();
            int employeeCount = statisticalDao.getEmployeeCount();
            int accountCount = statisticalDao.getAccountCount();
            int managerCount = statisticalDao.getManagerCount();

            List<Employeesalary> employees = employeesalaryDao.getAllEmployees(); 
            List<wage> staffList = staffDao.getAllStaff(); 

            // Đặt dữ liệu vào request attributes
            request.setAttribute("totalSalary", totalSalary);
            request.setAttribute("employeeCount", employeeCount);
            request.setAttribute("accountCount", accountCount);
            request.setAttribute("managerCount", managerCount);
            request.setAttribute("employees", employees); 
            request.setAttribute("staffList", staffList); 

            // Chuyển tiếp dữ liệu đến JSP
            request.getRequestDispatcher("/View/Statistical.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi tải dữ liệu thống kê.");
            request.getRequestDispatcher("/View/Error.jsp").forward(request, response);
        }
    }
    private void exportDataAsJson(HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    try {
        // Fetch staff data using the getAllStaff method
        List<wage> staffList = staffDao.getAllStaff();

        // Convert staff data to a JSON array
        JSONArray staffArray = new JSONArray();

        for (wage staff : staffList) {
            JSONObject staffObj = new JSONObject();
            staffObj.put("mnv", staff.getMnv());               // Employee ID
            staffObj.put("hoTen", staff.getHoTen());           // Full Name
            staffObj.put("ngaySinh", staff.getNgaySinh());     // Date of Birth
            staffObj.put("diaChi", staff.getDiaChi());         // Address
            staffObj.put("soDienThoai", staff.getSoDienThoai());// Phone Number
            staffObj.put("chucVu", staff.getChucVu());         // Position
            staffArray.put(staffObj);
        }

        // Send the JSON array as the response
        response.getWriter().write(staffArray.toString());
    } catch (Exception e) {
        e.printStackTrace(); // Log the error details for debugging
        JSONObject errorResponse = new JSONObject();
        errorResponse.put("error", "Error exporting data: " + e.getMessage());
        response.getWriter().write(errorResponse.toString());
    }
}
private void exportEmployeeSalaries(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    try {
        // Fetch employee salary data
        List<Employeesalary> employees = employeesalaryDao.getAllEmployees();
        JSONArray employeeArray = new JSONArray();

        for (Employeesalary employee : employees) {
            JSONObject employeeObj = new JSONObject();
            employeeObj.put("id", employee.getId());
            employeeObj.put("hoTen", employee.getHoTen());
            employeeObj.put("luongCoBan", employee.getLuongCoBan());
            employeeObj.put("soNgayLam", employee.getSoNgayLam());
            employeeObj.put("luongPhuCap", employee.getLuongPhuCap());
            employeeObj.put("tongLuong", employee.getTongLuong());
            employeeArray.put(employeeObj);
        }

        // Write JSON array as the response
        response.getWriter().write(employeeArray.toString());

    } catch (Exception e) {
        e.printStackTrace();

        // Handle error by sending a JSON error message
        JSONObject errorResponse = new JSONObject();
        errorResponse.put("error", "Error exporting employee salary data: " + e.getMessage());
        response.getWriter().write(errorResponse.toString());
    }
}

    @Override
    public String getServletInfo() {
        return "Servlet to handle statistical data and export to JSON for Excel export.";
    }
}
