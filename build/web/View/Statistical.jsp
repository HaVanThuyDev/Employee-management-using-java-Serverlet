<%@page import="Model.Employeesalary"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>employee statistics</title>
    <!-- Boxicons -->
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="./Css/Statistical.css">
    <!-- Chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <!-- SheetJS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.18.5/xlsx.full.min.js"></script>
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
<div class="statistics">
    <h1> STATISTICAL DATA</h1>
    
    <!-- Button to Export Employee and Salary Data -->
    <div class="export-button" style="text-align: center; margin-bottom: 20px;">
        <button id="exportEmployeesBtn" class="btn-export">Xuất Nhân Viên</button>
        <button id="exportSalariesBtn" class="btn-export">Xuất Lương</button>

        <button id="exportExcelBtn" class="btn-export">Xuất Excel</button>
    </div>

    <!-- Chart Canvas -->
    <div style="width: 1000px; margin: 0 auto;">
        <canvas id="statisticsChart"></canvas>
    </div>
</div>
    </section>
    <!-- Chart.js and Excel Export Script -->
    <script>
        // Data sent from JSP as request attributes
        const totalSalary = <%= request.getAttribute("totalSalary") != null ? request.getAttribute("totalSalary") : 0 %>;
        const employeeCount = <%= request.getAttribute("employeeCount") != null ? request.getAttribute("employeeCount") : 0 %>;
        const accountCount = <%= request.getAttribute("accountCount") != null ? request.getAttribute("accountCount") : 0 %>;
        const managerCount = <%= request.getAttribute("managerCount") != null ? request.getAttribute("managerCount") : 0 %>;

        // Render the chart
        const ctx = document.getElementById('statisticsChart').getContext('2d');
        const statisticsChart = new Chart(ctx, {
            type: 'bar', // Bar chart
            data: {
                labels: ['Tổng Lương', 'Số Nhân Viên', 'Số Tài Khoản', 'Số Quản Lý'], // Labels
                datasets: [{
                    label: 'Thống Kê',
                    data: [totalSalary, employeeCount, accountCount, managerCount], // Data
                    backgroundColor: [
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(153, 102, 255, 0.6)'
                    ],
                    borderColor: [
                        'rgba(75, 192, 192, 1)',
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(153, 102, 255, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top'
                    },
                    title: {
                        display: true,
                        text: 'Biểu đồ thống kê'
                    }
                }
            }
        });

        // Export data to Excel
    </script>
    <script>
    // Assuming you have employees and salaries data passed from the servlet
    // Export Total Statistics to Excel
    document.getElementById('exportExcelBtn').addEventListener('click', function () {
        const data = [
            ['Statistical Data'],
            ['Tổng Lương', <%= request.getAttribute("totalSalary") %>],
            ['Số Nhân Viên', <%= request.getAttribute("employeeCount") %>],
            ['Số Tài Khoản', <%= request.getAttribute("accountCount") %>],
            ['Số Quản Lý', <%= request.getAttribute("managerCount") %>],
        ];

        const worksheet = XLSX.utils.aoa_to_sheet(data);
        const workbook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(workbook, worksheet, 'Statistics');

        XLSX.writeFile(workbook, 'Statistical_Data.xlsx');
    });

    // Export Employee Data to Excel
    document.getElementById('exportEmployeeDataBtn').addEventListener('click', function () {
        const data = [
            ['Employee ID', 'Employee Name'],
            // Append employee data
        ];

        employees.forEach(function(employee) {
            data.push([employee.id, employee.name]);
        });

        const worksheet = XLSX.utils.aoa_to_sheet(data);
        const workbook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(workbook, worksheet, 'Employee Data');

        XLSX.writeFile(workbook, 'Employee_Data.xlsx');
    });

    // Export Salary Data to Excel
    document.getElementById('exportSalaryDataBtn').addEventListener('click', function () {
        const data = [
            ['Employee ID', 'Salary'],
            // Append salary data
        ];

        employees.forEach(function(employee) {
            data.push([employee.id, employee.salary]);
        });

        const worksheet = XLSX.utils.aoa_to_sheet(data);
        const workbook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(workbook, worksheet, 'Salary Data');

        XLSX.writeFile(workbook, 'Salary_Data.xlsx');
    });
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.16.9/xlsx.full.min.js"></script>

<!-- Include SheetJS Library -->
<script src="https://cdn.jsdelivr.net/npm/xlsx/dist/xlsx.full.min.js"></script>

<!-- Button to Export Employees -->

<script>
    // Function to export employee data as an Excel file
    function exportData(type) {
        // Fetch data from the Servlet
        fetch('StatisticalServlet?action=export')
            .then(response => {
                if (!response.ok) throw new Error("Cannot connect to server");
                return response.json();
            })
            .then(data => {
                if (data.error) {
                    alert("Error: " + data.error);
                    return;
                }

                if (type === "employees") {
                    // Prepare employee data for Excel
                    const employeeData = [
                        ["Mã Nhân Viên", "Họ Tên", "Ngày Sinh", "Địa Chỉ", "Số Điện Thoại", "Chức Vụ"]
                    ];

                    data.forEach(emp => {
                        employeeData.push([
                            emp.mnv,        // Employee ID
                            emp.hoTen,      // Full Name
                            emp.ngaySinh,   // Date of Birth
                            emp.diaChi,     // Address
                            emp.soDienThoai,// Phone Number
                            emp.chucVu      // Position
                        ]);
                    });

                    // Convert employee data to a worksheet
                    const worksheet = XLSX.utils.aoa_to_sheet(employeeData);
                    const workbook = XLSX.utils.book_new();
                    XLSX.utils.book_append_sheet(workbook, worksheet, "Danh Sách Nhân Viên");

                    // Export as Excel file
                    XLSX.writeFile(workbook, "DanhSachNhanVien.xlsx");
                } else {
                    alert("Unsupported data type.");
                }
            })
            .catch(error => {
                console.error("Error:", error);
                alert("Error exporting data: " + error.message);
            });
    }

    // Bind the export function to the button click
    document.getElementById("exportEmployeesBtn").addEventListener("click", function () {
        exportData("employees");
    });
</script>
<script>
    // Function to export employee salary data as an Excel file
    function exportEmployeeSalaries() {
        // Fetch data from the Servlet
        fetch('StatisticalServlet?action=exportEmployeeSalaries')
            .then(response => {
                if (!response.ok) throw new Error("Cannot connect to server");
                return response.json();
            })
            .then(data => {
                if (data.error) {
                    alert("Error: " + data.error);
                    return;
                }

                // Prepare employee salary data for Excel
                const salaryData = [
                    ["ID", "Họ Tên", "Lương Cơ Bản", "Số Ngày Làm", "Lương Phụ Cấp", "Tổng Lương"]
                ];

                data.forEach(emp => {
                    salaryData.push([
                        emp.id,           // Employee ID
                        emp.hoTen,        // Full Name
                        emp.luongCoBan,   // Basic Salary
                        emp.soNgayLam,    // Working Days
                        emp.luongPhuCap,  // Allowance Salary
                        emp.tongLuong     // Total Salary
                    ]);
                });

                // Convert salary data to a worksheet
                const worksheet = XLSX.utils.aoa_to_sheet(salaryData);
                const workbook = XLSX.utils.book_new();
                XLSX.utils.book_append_sheet(workbook, worksheet, "Danh Sách Lương");

                // Export as Excel file
                XLSX.writeFile(workbook, "DanhSachLuong.xlsx");
            })
            .catch(error => {
                console.error("Error:", error);
                alert("Error exporting salary data: " + error.message);
            });
    }

    // Bind the export function to the button click
    document.getElementById("exportSalariesBtn").addEventListener("click", function () {
        exportEmployeeSalaries();
    });
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.18.5/xlsx.full.min.js"></script>
</body>
</html>
