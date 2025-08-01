<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="./Css/Office Staf.css">
    <script src="./Js/Officestaf.js"></script>
    <title>Office Staf</title>
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
                <img src="https://static.vecteezy.com/system/resources/previews/012/210/707/non_2x/worker-employee-businessman-avatar-profile-icon-vector.jpg">
            </a>
        </nav>
        <main>
            <h1>Employee management</h1>
            <button onclick="document.getElementById('addModal').style.display='block'" class="btn-add">Add </button>
            
            <!-- Employee Table -->
            <table>
                <thead>
                    <tr>
                        <th>Mã nhân viên</th>
                        <th>Họ tên</th>
                        <th>Ngày sinh</th>
                        <th>Địa chỉ</th>
                        <th>Phone</th>
                        <th>Chức vụ</th>
                        <th>Chức năng</th>
                    </tr>
                </thead>
                <tbody>
                    <%-- Example: Iterate through employee list --%>
                    <%
                        List<Model.wage> staffList = (List<Model.wage>) request.getAttribute("staffList");
                        if (staffList != null) {
                            for (Model.wage staff : staffList) {
                    %>
                    <tr>
                        <td><%= staff.getMnv() %></td>
                        <td><%= staff.getHoTen() %></td>
                        <td><%= staff.getNgaySinh() %></td>
                        <td><%= staff.getDiaChi() %></td>
                        <td><%= staff.getSoDienThoai() %></td>
                        <td><%= staff.getChucVu() %></td>
                        <td>
                            <form action="StaffServlet" method="POST" style="display:inline;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="mnv" value="<%= staff.getMnv()%>">
                                <button type="submit" class="btn-delete">Delete</button>
                            </form>
                            <button onclick="editStaff('<%= staff.getMnv() %>', '<%= staff.getHoTen() %>', '<%= staff.getNgaySinh() %>', '<%= staff.getDiaChi() %>', '<%= staff.getSoDienThoai() %>', '<%= staff.getChucVu() %>')" class="btn-edit">Edit</button>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </main>
        <div id="addModal" class="modal">
            <form action="StaffServlet" method="POST" class="modal-content">
                <span onclick="document.getElementById('addModal').style.display='none'" class="close">&times;</span>
                <input type="hidden" name="action" value="add">
                <label for="mnv">Mã nhân viên:</label>
                <input type="text" name="mnv" required>
                <label for="hoTen">Họ tên:</label>
                <input type="text" name="hoTen" required>
                <label for="ngaySinh">Ngày sinh:</label>
                <input type="date" name="ngaySinh" required>
                <label for="diaChi">Địa chỉ:</label>
                <input type="text" name="diaChi" required>
                <label for="soDienThoai">Phone:</label>
                <input type="text" name="soDienThoai" required>
                <label for="chucVu">Chức vụ</label>
                <input type="text" name="chucVu" required>
                <button type="submit" class="btn-save">Save</button>
            </form>
        </div>
        <div id="editModal" class="modal">
            <form action="StaffServlet" method="POST" class="modal-content">
                <span onclick="document.getElementById('editModal').style.display='none'" class="close">&times;</span>
                <input type="hidden" name="action" value="update">
                <label for="mnv">Mã nhân viên:</label>
                <input type="text" id="edit-mnv" name="mnv" readonly>
                <label for="hoTen">Họ tên:</label>
                <input type="text" id="edit-hoTen" name="hoTen" required>
                <label for="ngaySinh">Ngày sinh:</label>
                <input type="date" id="edit-ngaySinh" name="ngaySinh" required>
                <label for="diaChi">Địa chỉ:</label>
                <input type="text" id="edit-diaChi" name="diaChi" required>
                <label for="soDienThoai">Phone:</label>
                <input type="text" id="edit-soDienThoai" name="soDienThoai" required>
                <label for="chucVu">Chức vụ:</label>
                <input type="text" id="edit-chucVu" name="chucVu" required>
                <button type="submit" class="btn-save">Save</button>
            </form>
        </div>

        <script>
            function editStaff(mnv, hoTen, ngaySinh, diaChi, soDienThoai, chucVu) {
                document.getElementById('edit-mnv').value = mnv;
                document.getElementById('edit-hoTen').value = hoTen;
                document.getElementById('edit-ngaySinh').value = ngaySinh;
                document.getElementById('edit-diaChi').value = diaChi;
                document.getElementById('edit-soDienThoai').value = soDienThoai;
                document.getElementById('edit-chucVu').value = chucVu;
                document.getElementById('editModal').style.display = 'block';
            }
        </script>
    </section>
</body>
</html>
