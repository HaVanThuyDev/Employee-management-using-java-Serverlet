<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Software Employee</title>
    <!-- ======= Styles ====== -->
    <link rel="stylesheet" href="./Css/Css2.css">
    <style>

        .table-database {
            margin-top: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        table th,
        table td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: left;
        }

        table th {
            background-color: #0000FF;
        }

        /* Dialog styles */
        #editDialog,
        #addDialog {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 400px;
            padding: 20px;
            background: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            display: none;
            z-index: 1000;
        }

        #overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
            display: none;
            z-index: 999;
        }

        form label {
            display: block;
            margin: 10px 0 5px;
        }

        form input {
            width: 100%;
            padding: 5px;
        }

        form button {
            margin-top: 10px;
        }
    </style>
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
                <div class="user">
                    <img src="https://thietkewebchuyen.com/wp-content/uploads/logo-mixigaming-3.jpg" alt="">
                </div>
            </div>

            <div class="table-database">
                <h1>Manage Employee Salaries</h1>
                <button class="btn-blue" onclick="showAddDialog()">add</button>

                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Họ Tên</th>
                            <th>Lương Cơ Bản</th>
                            <th>Số Ngày Làm</th>
                            <th>Lương Phụ Cấp</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="employee" items="${employeeList}">
                            <tr>
                                <td>${employee.id}</td>
                                <td>${employee.hoTen}</td>
                                <td>${employee.luongCoBan}</td>
                                <td>${employee.soNgayLam}</td>
                                <td>${employee.luongPhuCap}</td>
                                <td>
    <a href="javascript:void(0);" 
       class="btn-blu" 
       onclick="editEmployee(${employee.id}, '${employee.hoTen}', ${employee.luongCoBan}, ${employee.soNgayLam}, ${employee.luongPhuCap})">
       Sửa
    </a>
    |
    <a href="EnteremployeesalariesServlet?action=delete&id=${employee.id}" 
       class="btn-red" 
       onclick="return confirm('Bạn có chắc chắn muốn xóa không?');">
       Xóa
    </a>
</td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Overlay -->
    <div id="overlay" onclick="closeDialogs()"></div>

    <!-- Add Employee Dialog -->
    <!-- Add Employee Dialog -->
<div id="addDialog">
    <h2>Thêm nhân viên</h2>
    <form id="addForm" action="EnteremployeesalariesServlet" method="post">
        <!-- Hidden field to specify the action -->
        <input type="hidden" name="action" value="add">

        <!-- Input fields for employee details -->
        <label>Họ Tên:</label>
        <input type="text" name="hoTen" required placeholder="Nhập họ tên">

        <label>Lương Cơ Bản:</label>
        <input type="number" name="luongCoBan" step="0.01" required placeholder="Nhập lương cơ bản">

        <label>Số Ngày Làm:</label>
        <input type="number" name="soNgayLam" required placeholder="Nhập số ngày làm">

        <label>Lương Phụ Cấp:</label>
        <input type="number" name="luongPhuCap" step="0.01" required placeholder="Nhập lương phụ cấp">

        <!-- Buttons for submit and cancel -->
        <button type="submit" class="btn-blu">Thêm</button>
        <button type="button" class="btn-red" onclick="closeAddDialog()">Hủy</button>
    </form>
</div>


    <!-- Edit Employee Dialog -->
    <div id="editDialog">
    <h2>Sửa nhân viên</h2>
    <form id="editForm" action="EnteremployeesalariesServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" id="editId">
        <label>Họ Tên:</label> 
        <input type="text" name="hoTen" id="editHoTen" required>
        <label>Lương Cơ Bản:</label> 
        <input type="number" name="luongCoBan" id="editLuongCoBan" step="0.01" required>
        <label>Số Ngày Làm:</label> 
        <input type="number" name="soNgayLam" id="editSoNgayLam" required>
        <label>Lương Phụ Cấp:</label> 
        <input type="number" name="luongPhuCap" id="editLuongPhuCap" step="0.01" required>

        <!-- Buttons with specific styling classes -->
        <button type="submit" class="btn-blu">save</button>
        <button type="button" class="btn-red" onclick="closeEditDialog()">Hủy</button>
    </form>
</div>


    <!-- =========== Scripts =========  -->
    <script>
        function showAddDialog() {
            document.getElementById('addDialog').style.display = 'block';
            document.getElementById('overlay').style.display = 'block';
        }

        function closeAddDialog() {
            document.getElementById('addDialog').style.display = 'none';
            document.getElementById('overlay').style.display = 'none';
        }

        function editEmployee(id, hoTen, luongCoBan, soNgayLam, luongPhuCap) {
            document.getElementById('editId').value = id;
            document.getElementById('editHoTen').value = hoTen;
            document.getElementById('editLuongCoBan').value = luongCoBan;
            document.getElementById('editSoNgayLam').value = soNgayLam;
            document.getElementById('editLuongPhuCap').value = luongPhuCap;
            document.getElementById('editDialog').style.display = 'block';
            document.getElementById('overlay').style.display = 'block';
        }

        function closeEditDialog() {
            document.getElementById('editDialog').style.display = 'none';
            document.getElementById('overlay').style.display = 'none';
        }

        function closeDialogs() {
            closeAddDialog();
            closeEditDialog();
        }
    </script>

    <!-- ====== ionicons ======= -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>
