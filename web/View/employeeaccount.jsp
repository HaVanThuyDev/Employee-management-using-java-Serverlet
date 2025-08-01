<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>software employee</title>
    <!-- ======= Styles ====== -->
    <link rel="stylesheet" href="./Css/css1.css">
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
                    <a href="employeereportServlet">
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
            <div class="table-database">
                <h1>ACCOUNT INFORMATION</h1>
                <table>
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Họ Tên</th>
                            <th>Số điện thoại</th>
                            <th>Mật Khẩu</th>
                            <th>Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Hiển thị thông tin tài khoản -->
                        <tr>
                            <td>${account.stt}</td>
                            <td>${account.hoTen}</td>
                            <td>${account.soDienThoai}</td>
                            <td>${account.matKhau}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/employeeaccountServlet?action=edit&soDienThoai=${account.soDienThoai}">Sửa</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <!-- ========================= Edit Form ==================== -->
            <div class="form-container">
                <h2>Information</h2>
                <form action="${pageContext.request.contextPath}/employeeaccountServlet" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="soDienThoai" value="${account.soDienThoai}">
                    
                    <div class="form-group">
                        <label for="hoTen">Họ Tên:</label>
                        <input type="text" id="hoTen" name="hoTen" value="${account.hoTen}" required>
                    </div>
                    <div class="form-group">
                        <label for="soDienThoai">Số điện thoại:</label>
                        <input type="text" id="soDienThoai" name="soDienThoai" value="${account.soDienThoai}" required>
                    </div>

                    <div class="form-group">
                        <label for="matKhau">Mật Khẩu:</label>
                        <input type="text" id="matKhau" name="matKhau" value="${account.matKhau}" required>
                    </div>

                    <div class="form-actions">
                        <button type="submit">save</button>
                        <a href="${pageContext.request.contextPath}/employeeaccountServlet">exit</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- =========== Scripts =========  -->
    <script src="assets/js/main.js"></script>

    <!-- ====== ionicons ======= -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    <script>
     document.addEventListener('DOMContentLoaded', function() {
  const editLinks = document.querySelectorAll('a[href*="action=edit"]'); //Select all edit links
  const formContainer = document.querySelector('.form-container');

  editLinks.forEach(link => {
    link.addEventListener('click', function(event) {
      event.preventDefault(); // Prevent default link behavior

      //Show the form
      formContainer.style.display = 'block';
    });
  });

});


    </script>
</body>

</html>
