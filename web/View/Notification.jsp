<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Boxicons -->
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="./Css/Notification.css">

    <title>Notification Management</title>
    <style>
        .notification-container {
            padding: 20px;
            margin: 20px auto;
            max-width: 1400px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background: #f9f9f9;
        }

        .notification-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .notification-form {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
        }

        .notification-form input[type="text"] {
            flex: 1;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .notification-form button {
            padding: 10px 20px;
            background-color: #28a745;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
        }

        .notification-form button:hover {
            background-color: #218838;
        }

        .notification-list {
            list-style-type: none;
            padding: 0;
        }

        .notification-list li {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            margin-bottom: 10px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .notification-list a {
            text-decoration: none;
            color: white;
            background-color: #dc3545;
            padding: 5px 10px;
            border-radius: 5px;
            font-weight: bold;
        }

        .notification-list a:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
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
                    <i class='bx bxs-cog' ></i>
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
    <section id="content">
        <nav>
            <i class='bx bx-menu'></i>
            <a href="#" class="nav-link">Search</a>
            <form action="" method="GET">
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
                <img src="https://static.vecteezy.com/system/resources/previews/012/210/707/non_2x/worker-employee-businessman-avatar-profile-icon-vector.jpg">
            </a>
        </nav>
        <div class="notification-container">
            <h2>Manage Notifications</h2>

            <!-- Form to Add Notification -->
            <form action="NotificationServlet" method="POST" class="notification-form">
                <input type="text" name="text" placeholder="Nhập thông báo" required>
                <input type="hidden" name="action" value="add">
                <button type="submit">Thêm</button>
            </form>

            <!-- List of Notifications -->
            <ul class="notification-list">
                <c:forEach var="notification" items="${notifications}">
                    <li>
                        <span>${notification.text}</span>
                        <a href="NotificationServlet?action=delete&id=${notification.id}" 
                           onclick="return confirm('Xác nhận xóa thông báo?')">Xóa</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </section>
</body>
</html>
