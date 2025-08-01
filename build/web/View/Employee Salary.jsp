<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.Employeesalary"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee salary</title>
    <!-- Boxicons -->
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="./Css/employeesalary.css">
</head>
<body>
    <!-- SIDEBAR -->
    <section id="sidebar">
        <a href="#" class="brand">
            <i class='bx bxs-smile'></i>
            <span class="text">Home</span>
        </a>
        <ul class="side-menu top">
            <li class="active">
                <a href="HomeAdmin">
                    <i class='bx bxs-home'></i>
                    <span class="text">Home</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <i class='bx bxs-group'></i>
                    <span class="text">Nhân viên</span>
                </a>
            </li>
            <li>
                <a href="AccountServlet">
                    <i class='bx bxs-user'></i>
                    <span class="text">Tài khoản</span>
                </a>
            </li>
            <li>
                <a href="employeesalaryServlet">
                    <i class='bx bxs-dollar-circle'></i>
                    <span class="text">Lương</span>
                </a>
            </li>
            <li>
                <a href="StatisticalServlet">
                    <i class='bx bxs-bar-chart-square'></i>
                    <span class="text">Thống kê</span>
                </a>
            </li>
            <li>
				<a href="#">
				<i class='bx bxs-message'></i>

					<span class="text">Báo cáo</span>
				</a>
	    </li>
            <li>
                <a href="#">
                    <i class='bx bxs-bell'></i>
                    <span class="text">Thông báo</span>
                </a>
            </li>
        </ul>
        <ul class="side-menu">
            <li>
                <a href="#">
                    <i class='bx bxs-cog'></i>
                    <span class="text">Cài đặt</span>
                </a>
            </li>
            <li>
                <a href="#" class="logout">
                    <i class='bx bxs-log-out-circle'></i>
                    <span class="text">Thoát</span>
                </a>
            </li>
        </ul>
    </section>
    <!-- SIDEBAR -->

    <!-- CONTENT -->
    <section id="content">
        <!-- NAVBAR -->
        <nav>
            <i class='bx bx-menu'></i>
            <a href="#" class="nav-link">Search</a>
            <form action="StaffServlet" method="GET">
                <div class="form-input">
                    <input type="search" name="keyword" placeholder="Tìm kiếm" value="">
                    <input type="hidden" name="action" value="search">
                    <button type="submit" class="search-btn"><i class='bx bx-search'></i></button>
                </div>
            </form>
            <a href="#" class="notification">
                <i class='bx bxs-bell'></i>
                <span class="num">8</span>
            </a>
            <a href="#" class="profile">
                <img src="https://static.vecteezy.com/system/resources/previews/012/210/707/non_2x/worker-employee-businessman-avatar-profile-icon-vector.jpg" alt="Avatar">
            </a>
        </nav>

        <!-- TABLE TO DISPLAY EMPLOYEE SALARIES -->
        <div class="salary-list">
            <h1>LIST OF EMPLOYEE SALARIES</h1>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Họ Tên</th>
                        <th>Lương Cơ Bản</th>
                        <th>Số Ngày Làm</th>
                        <th>Lương Phụ Cấp</th>
                        <th>Tổng Lương</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        // Lấy danh sách nhân viên từ request attribute
                        List<Employeesalary> employeeList = (List<Employeesalary>) request.getAttribute("employeeList");
                        if (employeeList != null && !employeeList.isEmpty()) {
                            for (Employeesalary employee : employeeList) {
                    %>
                    <tr>
                        <td><%= employee.getId() %></td>
                        <td><%= employee.getHoTen() %></td>
                        <td><%= employee.getLuongCoBan() %></td>
                        <td><%= employee.getSoNgayLam() %></td>
                        <td><%= employee.getLuongPhuCap() %></td>
                        <td><%= employee.getTongLuong()%></td>
                    </tr>
                    <% 
                            }
                        } else { 
                    %>
                    <tr>
                        <td colspan="6">Không có dữ liệu để hiển thị.</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </section>
    <!-- CONTENT END -->

    <script src="script.js"></script>
</body>
</html>
