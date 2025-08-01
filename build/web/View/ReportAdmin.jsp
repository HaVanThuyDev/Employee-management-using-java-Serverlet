<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="./Css/Office Staf.css">
    <title>Danh sách báo cáo</title>
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
                <a href="ReportServlet?action=view">
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
            <form action="ReportServlet" method="GET">
                <div class="form-input">
                    <input type="search" name="keyword" placeholder="Tìm kiếm báo cáo" value="${param.keyword}">
                    <input type="hidden" name="action" value="view">
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
            <h1>Danh sách báo cáo</h1>
            <table border="1" cellspacing="0" cellpadding="10" style="width: 100%; text-align: center;">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Ngày</th>
                        <th>Nội dung</th>
                        <th>Hành động</th
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty reports}">
                            <c:forEach var="report" items="${reports}">
                                <tr>
                                    <td>${report.stt}</td>
                                    <td>${report.ngay}</td>
                                    <td>${report.text}</td>
                                    <td>
                                        <a href="ReportServlet?action=delete&stt=${report.stt}" onclick="return confirm('Bạn có chắc muốn xóa báo cáo này không?');">
                                            Xóa
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4">Không có báo cáo nào để hiển thị</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </main>
    </section>
    <!-- CONTENT -->

    <script src="script.js"></script>
</body>
</html>
