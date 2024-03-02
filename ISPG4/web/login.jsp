<%-- 
    Document   : login
    Created on : Mar 2, 2024, 8:40:01 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Form</title>
        <style>
            /* Custom CSS styles */
            body {
                font-family: Arial, sans-serif;
                background: #f7f7f7; /* Just an example; use your own color */
            }
            .form-container {
                width: 300px;
                margin: 50px auto;
                padding: 20px;
                background: #fff; /* Just an example; use your own color */
                border: 1px solid #ccc;
                border-radius: 5px;
                box-shadow: 2px 2px 10px #aaa;
            }
            h2.form-heading {
                text-align: center;
                margin-bottom: 20px;
            }
            input[type=text], input[type=password], input[type=submit], button {
                width: 100%;
                padding: 10px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }
            input[type=submit], button {
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            input[type=submit]:hover, button:hover {
                background-color: #45a049;
            }
            .form-group {
                margin-top: 20px;
                text-align: center;
            }
            .form-group a {
                text-decoration: none;
            }
            .captcha-group {
                display: flex;
                align-items: center;
                justify-content: space-between;
                margin-bottom: 10px;
            }

            .captcha-group img {
                margin-right: 5px; /* Adjust space between captcha image and reload button */
            }

            button.reload-captcha {
                padding: 10px;
                background-color: #dddddd; /* Light grey, change as needed */
                border: 1px solid #ccc;
                border-radius: 4px;
                cursor: pointer;
            }
            /* Add other styles as needed */
        </style>
    </head>
    <body>
        <div class="form-container">
            <!-- Display message if exists -->
            <% if (request.getAttribute("mess") != null) { %>
            <p style="color: red;"><%= request.getAttribute("mess") %></p>
            <% } %>

            <form action="login" method="POST">
                <h2 class="form-heading">Đăng nhập</h2> <!-- Heading for the form -->
                Tên đăng nhập:<input type="text" name="username" required /><br>
                Mật khẩu:<input type="password" name="password" required /><br> 
                <img src="captchaGenerator" id="captchaImage"/>
                <input type="text" name="captcha" required placeholder="Nhập Captcha">
                <button type="button" onclick="reloadCaptcha()">Tải lại captcha</button>
                <input type="submit" value="Đăng nhập" />

                <!-- Register and Forgot Password links -->
                <div class="form-group">
                    <button type="button" onclick="location.href = 'register.jsp'">Đăng ký</button>
                    <a href="forgotPassword.jsp">Quên mật khẩu</a>
                </div>

                <script>
                    function reloadCaptcha() {
                        document.getElementById('captchaImage').src = 'captchaGenerator?' + new Date().getTime();
                    }
                </script>
            </form>
        </div>
    </body>
</html>
