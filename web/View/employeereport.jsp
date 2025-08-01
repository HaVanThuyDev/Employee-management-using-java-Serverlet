<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>employee report</title>
    <link rel="stylesheet" href="./Css/css1.css">
    <style>
        /* Messenger Container */
.messenger {
    max-width: 900px;
    margin: 0 auto;
    background: #f5f5f5;
    border-radius: 10px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    padding: 20px;
    font-family: Arial, sans-serif;
}

/* Heading Style */
.messenger h2 {
    text-align: center;
    color: #333;
    margin-bottom: 20px;
}

/* Chat Box */
.chat-box {

    display: flex;
    flex-direction: column;
    background: #ffffff;
    border-radius: 8px;
    padding: 15px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* Form Labels */
.form-label {
    font-size: 14px;
    color: #555;
    margin-bottom: 5px;
}

/* Input Fields */
.chat-input, .chat-textarea {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    font-size: 14px;
    color: #333;
    outline: none;
}

.chat-input:focus, .chat-textarea:focus {
    border-color: #007bff;
    box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}

/* Textarea */
.chat-textarea {
    height: 80px;
    resize: none;
}

/* Submit Button */
.chat-submit-btn {
    width: 100%;
    padding: 10px;
    background: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    font-size: 14px;
    font-weight: bold;
    cursor: pointer;
    transition: background 0.3s ease;
}

.chat-submit-btn:hover {
    background: #0056b3;
}

/* Chat Messages */
.chat-message {
    margin-top: 15px;
    font-size: 14px;
    padding: 10px;
    border-radius: 5px;
    text-align: center;
}

/* Success Message */
.chat-message.success {
    background: #00FF00;
    color: white;
    border: 1px solid #c3e6cb;
}

/* Error Message */
.chat-message.error {
    background: #00FF00;
    color: white;
    border: 1px solid #00FF00;
}

    </style>
</head>
<body>
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
                    <c:otherwise>
                        <span>Xin chào, Khách!</span>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
       <div class="messenger">
    <h2>Messenger</h2>

    <div class="chat-box">
        <form action="employeereportServlet" method="post" class="chat-form">
            <!-- Hidden input to pass action -->
            <input type="hidden" name="action" value="add">
            <!-- Hidden input to pass the sender's name -->
            <input type="hidden" name="sender" value="${sessionScope.userName}">

            <!-- Date input -->
            <label for="ngay" class="form-label">Ngày:</label>
            <input type="date" id="ngay" name="ngay" class="chat-input" required>
            
            <!-- Text input -->
            <label for="text" class="form-label">Nội dung:</label>
        <textarea id="text" name="text" class="chat-textarea" required placeholder="Nhập tin nhắn...">Nguời gửi: ${not empty sessionScope.userName ? sessionScope.userName : 'Khách'}</textarea>

            <!-- Submit button -->
            <button type="submit" class="chat-submit-btn">Gửi</button>
        </form>

        <!-- Display success or error messages -->
        <c:if test="${not empty message}">
            <p class="chat-message ${messageType == 'success' ? 'success' : 'error'}">
                ${message}
            </p>
        </c:if>
    </div>
</div>
      
  </div>
        

    <!-- Ionicons Scripts -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>
