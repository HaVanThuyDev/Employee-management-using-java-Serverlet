package Controller;

import Dao.EmployeesalaryDao;
import Model.Employeesalary;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "EnteremployeesalariesServlet", urlPatterns = {"/EnteremployeesalariesServlet"})
public class EnteremployeesalariesServlet extends HttpServlet {

    private EmployeesalaryDao employeeSalaryDao = new EmployeesalaryDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            addEmployee(request, response);
        } else if ("update".equals(action)) {
            updateEmployee(request, response);
        } else if ("delete".equals(action)) {
            deleteEmployee(request, response);
        } else {
            listEmployees(request, response);
        }
    }

    private void addEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String hoTen = request.getParameter("hoTen");
        double luongCoBan = Double.parseDouble(request.getParameter("luongCoBan"));
        int soNgayLam = Integer.parseInt(request.getParameter("soNgayLam"));
        double luongPhuCap = Double.parseDouble(request.getParameter("luongPhuCap"));
        
        Employeesalary newEmployee = new Employeesalary(0, hoTen, luongCoBan, soNgayLam, luongPhuCap);
        boolean added = employeeSalaryDao.addEmployeeSalary(newEmployee);

        if (added) {
            response.sendRedirect("EnteremployeesalariesServlet");
        } else {
            response.getWriter().println("Failed to add employee.");
        }
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String hoTen = request.getParameter("hoTen");
        double luongCoBan = Double.parseDouble(request.getParameter("luongCoBan"));
        int soNgayLam = Integer.parseInt(request.getParameter("soNgayLam"));
        double luongPhuCap = Double.parseDouble(request.getParameter("luongPhuCap"));

        Employeesalary employee = new Employeesalary(id, hoTen, luongCoBan, soNgayLam, luongPhuCap);
        boolean updated = employeeSalaryDao.updateEmployeeSalary(employee);

        if (updated) {
            response.sendRedirect("EnteremployeesalariesServlet");
        } else {
            response.getWriter().println("Failed to update employee.");
        }
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean deleted = employeeSalaryDao.deleteEmployeeSalary(id);

        if (deleted) {
            response.sendRedirect("EnteremployeesalariesServlet");
        } else {
            response.getWriter().println("Failed to delete employee.");
        }
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Employeesalary> employeeList = employeeSalaryDao.getAllEmployees();
        request.setAttribute("employeeList", employeeList);
        request.getRequestDispatcher("/View/Enteremployeesalaries.jsp").forward(request, response); // Show the JSP page
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
        return "Handles employee salary CRUD operations.";
    }
}
