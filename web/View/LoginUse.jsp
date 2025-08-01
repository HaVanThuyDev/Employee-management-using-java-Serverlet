<%-- 
    Document   : LoginUse
    Created on : Dec 4, 2024, 7:38:11 PM
    Author     : hathu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="./Css/LoginUse1.css">
    <title>Login</title>
</head>

<body>

    <div class="container" id="container">
        <div class="form-container sign-up">
            <form method="post" action="LoginServlet">
                <h1>Tạo tài khoản</h1>
                <div class="social-icons">
                    <a href="#" class="icon"><i class="fa-brands fa-google-plus-g"></i></a>
                    <a href="#" class="icon"><i class="fa-brands fa-facebook-f"></i></a>
                    <a href="#" class="icon"><i class="fa-brands fa-github"></i></a>
                    <a href="#" class="icon"><i class="fa-brands fa-linkedin-in"></i></a>
                </div>
                <span>sử dụng email hoặc số điện thoại</span>
                <input type="text" name="hoTen" placeholder="Họ và tên" required>
                <input type="text" name="soDienThoai" placeholder="Số điện thoại" required>
                <input type="password" name="matKhau" placeholder="Mật khẩu" required>
                <input type="hidden" name="action" value="register">
                <button type="submit">Đăng ký</button>
                
                <c:if test="${not empty message}">
                    <div class="success">${message}</div> <!-- Hiển thị thông báo thành công -->
                </c:if>
                <c:if test="${not empty errorMessage}">
                    <div class="error">${errorMessage}</div>
                </c:if>
            </form>
        </div>

        <div class="form-container sign-in">
            <form method="post" action="LoginServlet">
                <h1>Nhân viên</h1>
                <div class="social-icons">
                    <a href="#" class="icon"><i class="fa-brands fa-google-plus-g"></i></a>
                    <a href="#" class="icon"><i class="fa-brands fa-facebook-f"></i></a>
                    <
                    <a href="#" class="icon"><i class="fa-brands fa-github"></i></a>
                    <a href="#" class="icon"><i class="fa-brands fa-linkedin-in"></i></a>
                </div>
                <span>hoặc sử dụng mật khẩu email của bạn</span>
                <input type="text" name="soDienThoai" placeholder="Số điện thoại" required>
                <input type="password" name="matKhau" placeholder="Mật khẩu" required>
                <input type="hidden" name="action" value="login">
                <a href="LoginAdminServlet">Đăng nhập vai trò Admin!</a>
                <button type="submit">Đăng nhập</button>

                <c:if test="${not empty message}">
                    <div class="success">${message}</div> <!-- Hiển thị thông báo thành công -->
                </c:if>
                <c:if test="${not empty errorMessage}">
                    <div class="error">${errorMessage}</div>
                </c:if>
                <c:if test="${not empty unapprovedMessage}">
                    <div class="error">${unapprovedMessage}</div> <!-- Hiển thị thông báo tài khoản chưa được duyệt -->
                </c:if>
            </form>
        </div>

        <div class="toggle-container">
            <div class="toggle">
                <div class="toggle-panel toggle-left">
                    <h1>Chào mừng trở lại!!</h1>
                    <p>Nhập thông tin cá nhân của bạn để sử dụng tất cả các tính năng của phần mềm</p>
                    <button class="hidden" id="login">Quay lại</button>
                </div>
                <div class="toggle-panel toggle-right">
                    <h1>Chào bạn!</h1>
                    <p>Đăng ký với thông tin cá nhân của bạn để sử dụng tất cả các tính năng của phần mềm</p>
                    <button class="hidden" id="register">Đăng ký</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        const container = document.getElementById('container');
        const registerBtn = document.getElementById('register');
        const loginBtn = document.getElementById('login');

        registerBtn.addEventListener('click', () => {
            container.classList.add("active");
        });

        loginBtn.addEventListener('click', () => {
            container.classList.remove("active");
        });
    </script>
    <script>
      // Hàm để ẩn thông báo sau 2 giây
function hideMessages() {
    // Lấy tất cả các thông báo thành công và lỗi
    const successMessages = document.querySelectorAll('.success');
    const errorMessages = document.querySelectorAll('.error');

    // Hàm để ẩn thông báo
    const hideMessage = (messageElement) => {
        setTimeout(() => {
            messageElement.classList.add('hidden'); // Thêm lớp ẩn
        }, 2000); // Thời gian chờ 2 giây
    };

    // Ẩn tất cả thông báo thành công
    successMessages.forEach((message) => {
        hideMessage(message);
    });

    // Ẩn tất cả thông báo lỗi
    errorMessages.forEach((message) => {
        hideMessage(message);
    });
}

// Khi trang tải, gọi hàm ẩn thông báo
window.onload = hideMessages;


    </script>
</body>

</html>
