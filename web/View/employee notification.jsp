
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>employee notification</title>
    <!-- ======= Styles ====== -->
    <link rel="stylesheet" href="./Css/employeenotification.css">
</head>

<body>
    <!-- =============== Navigation ================ -->
    <div class="container">
      <div class="navigation">
            <ul>
                <li>
                    <a href="#">
                        <span class="icon">
                            <ion-icon name="home-outline"></ion-icon>
                        </span>
                        <span class="title">Brand Name</span>
                    </a>
                </li>
                <li>
                    <a href="HomeServlet">
                        <span class="icon">
                            <ion-icon name="home-outline"></ion-icon>
                        </span>
                        <span class="title">software employee</span>
                    </a>
                </li>
                <li>
                    <a href="employeenotificationServlet">
                        <span class="icon">
                            <ion-icon name="notifications-outline"></ion-icon>
                        </span>
                        <span class="title">Thông báo</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/employeeaccountServlet">
                        <span class="icon">
                            <ion-icon name="people-outline"></ion-icon>
                        </span>
                        <span class="title">Tài khoản</span>
                    </a>
                </li>
                 <li>
                    <a href="#">
                        <span class="icon">
                            <ion-icon name="chatbubble-outline"></ion-icon>
                        </span>
                        <span class="title">Báo cáo</span>
                    </a>
                </li>
                <li>
                    <a href="EnteremployeesalariesServlet">
                        <span class="icon">
                            <ion-icon name="cash-outline"></ion-icon>
                        </span>
                        <span class="title">Lương</span>
                    </a>
                </li>
                <li>
                    <a href="LoginServlet">
                        <span class="icon">
                            <ion-icon name="log-out-outline"></ion-icon>
                        </span>
                        <span class="title">Sign Out</span>
                    </a>
                </li>
            </ul>
        </div>

        <!-- ========================= Main ==================== -->
        <div class="main">
            <div class="topbar">
                <div class="toggle">
                    <ion-icon name="menu-outline"></ion-icon>
                </div>

                <div class="search">
                    <label>
                        <input type="text" placeholder="Search here">
                        <ion-icon name="search-outline"></ion-icon>
                    </label>
                </div>

                <div class="user-info">
                    <c:choose>
                        <c:when test="${not empty sessionScope.userName}">
                            <span>Xin chào, ${sessionScope.userName}!</span>
                        </c:when>
                    </c:choose>
                </div>
            </div>
            <div class="container-Notification">
        <h1>Thông báo</h1>
        <ul>
            <%
                // Lấy danh sách text từ request attribute
                List<String> notificationTexts = (List<String>) request.getAttribute("notificationTexts");
                if (notificationTexts != null && !notificationTexts.isEmpty()) {
                    for (String text : notificationTexts) {
            %>
                        <li><%= text %></li>
            <%
                    }
                } else {
            %>
                <li>No notifications to display.</li>
            <%
                }
            %>
        </ul>
    </div>
    </div>
        </div>
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>