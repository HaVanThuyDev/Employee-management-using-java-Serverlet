<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="./Css/LoginAdmin.css">
    <title>LoginAdmin</title>
</head>
<body>

    <!----------------------- Main Container -------------------------->

     <div class="container d-flex justify-content-center align-items-center min-vh-100">

    <!----------------------- Login Container -------------------------->

       <div class="row border rounded-5 p-3 bg-white shadow box-area">

    <!--------------------------- Left Box ----------------------------->

       <div class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box" style="background: #103cbe;">
           <div class="featured-image mb-3">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnwguLPh2SY6Nfqwcz1Aw_3cSiElE3dQxNbQ&s" class="img-fluid" style="width: 250px;">
           </div>
           <p class="text-white fs-2" style="font-family: 'Courier New', Courier, monospace; font-weight: 600;">Be Verified</p>
           <small class="text-white text-wrap text-center" style="width: 17rem;font-family: 'Courier New', Courier, monospace;">Official page for admin.</small>
       </div> 

    <!-------------------- ------ Right Box ---------------------------->
        
       <div class="col-md-6 right-box">
          <form action="LoginAdminServlet" method="post">
              <div class="row align-items-center">
                  <div class="header-text mb-4">
                       <h2>Hello, Admin</h2>
                       <p>We are happy to have you back.</p>
                  </div>
                  <div class="input-group mb-3">
                      <input type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Username" name="tenTaiKhoan">
                  </div>
                  <div class="input-group mb-1">
                      <input type="password" class="form-control form-control-lg bg-light fs-6" placeholder="Password" name="matKhau">
                  </div>
                  <div class="input-group mb-5 d-flex justify-content-between">
                      <div class="form-check">
                          <input type="checkbox" class="form-check-input" id="formCheck">
                          <label for="formCheck" class="form-check-label text-secondary"><small>Remember Me</small></label>
                      </div>
                      <div class="forgot">
                          <small><a href="LoginServlet">Employee login</a></small>
                      </div>
                  </div>
                  <div class="input-group mb-3">
                      <button type="submit" class="btn btn-lg btn-primary w-100 fs-6">Login</button>
                  </div>
                  <div class="row">
                      <small>Don't have an account? <a href="#">Sign Up</a></small>
                  </div>
              </div>
              <!-- Display error messages -->
              <c:if test="${not empty errorMessage}">
    <div id="error-message" class="alert alert-danger" role="alert" style="display: none;">
        ${errorMessage}
    </div>
</c:if>
          </form>
       </div> 

      </div>
    </div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Check if the error-message element exists
        const errorMessageElement = document.getElementById("error-message");
        if (errorMessageElement) {
            // Display the error message
            errorMessageElement.style.display = "block";

            // Hide the message after 2 seconds (2000 milliseconds)
            setTimeout(() => {
                errorMessageElement.style.display = "none";
            }, 2000);
        }
    });
</script>
</body>
</html>
