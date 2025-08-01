<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Dao.MD5DAO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="./Css/Office Staf.css">
    <title>Account Management</title>
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
                <a href="StaffServlet">
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
				<a href="ReportServlet">
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
            <form action="AccountServlet" method="GET">
                <div class="form-input">
                    <input type="search" name="keyword" placeholder="Tìm kiếm" value="${param.keyword}">
                    <button type="submit" class="search-btn"><i class='bx bx-search'></i></button>
                </div>
            </form>
            <a href="#" class="notification">
                <i class='bx bxs-bell'></i>
                <span class="num">8</span>
            </a>
            <a href="#" class="profile">
                <img src="https://static.vecteezy.com/system/resources/previews/012/210/707/non_2x/worker-employee-businessman-avatar-profile-icon-vector.jpg">
            </a>
        </nav>
        <!-- NAVBAR -->

        <!-- MAIN CONTENT -->
        <main>
            <h1>Account Management</h1>

            <!-- Message Feedback -->
            <c:if test="${not empty message}">
                <p style="color: green;">${message}</p>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <p style="color: red;">${errorMessage}</p>
            </c:if>

            <!-- Account List Table -->
            <table border="1" cellpadding="10">
    <thead>
        <tr>
            <th>STT</th>
            <th>Họ Tên</th>
            <th>Số Điện Thoại</th>
            <th>Mật khẩu </th>
            <th>Trạng Thái</th>
            <th>Hành Động</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="account" items="${accounts}">
            <tr>
                <td>${account.stt}</td>
                <td>${account.hoTen}</td>
                <td>${account.soDienThoai}</td>
                <td>${account.matKhau}</td> <!-- Hiển thị mật khẩu đã mã hóa -->
                <td>${account.status}</td>
                <td>
                    <a href="AccountServlet?action=approve&phone=${account.soDienThoai}" style="color: green;">Duyệt</a> |
                    <a href="AccountServlet?action=block&phone=${account.soDienThoai}" style="color: orange;">Đóng</a> |
                    <a href="AccountServlet?action=delete&phone=${account.soDienThoai}" style="color: red;">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>


            <!-- Pagination -->
            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="AccountServlet?page=${currentPage - 1}" class="prev">&laquo; Trang trước</a>
                </c:if>
                <c:forEach begin="1" end="${totalPages}" var="page">
                    <a href="AccountServlet?page=${page}" class="${page == currentPage ? 'active' : ''}">${page}</a>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <a href="AccountServlet?page=${currentPage + 1}" class="next">Trang sau &raquo;</a>
                </c:if>
            </div>
        </main>
    </section>
    <!-- CONTENT -->

    <script src="script.js"></script>
</body>
</html>
