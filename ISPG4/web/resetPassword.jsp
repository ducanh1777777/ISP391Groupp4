<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đổi Mật Khẩu Mới</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9; /* Light background color */
            display: flex;
            justify-content: center;
            align-items: flex-start; /* Aligns items to the start of the container */
            height: 90vh; /* 90% of the viewport height */
            margin: 0;
            padding-top: 40px; /* Top padding */
        }
        .form-container {
            width: 350px; /* Container width */
            background-color: #fff; /* White background */
            padding: 30px; /* Padding around the form */
            border-radius: 8px; /* Rounded corners */
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1); /* Box shadow for 3D effect */
            text-align: center; /* Center align text */
        }
        h2 {
            color: #4CAF50; /* Green color for the heading */
            margin-bottom: 20px; /* Spacing below the heading */
        }
        label {
            display: block; /* Label on its own line */
            margin-bottom: 10px; /* Spacing below the label */
            color: #333; /* Dark gray color for the text */
        }
        input[type="password"], input[type="submit"] {
            width: calc(100% - 20px); /* Full width minus padding */
            padding: 10px; /* Padding inside the input fields */
            margin: 10px 0; /* Spacing around inputs */
            border-radius: 4px; /* Rounded corners for input fields */
            border: 1px solid #ddd; /* Border for input fields */
            box-sizing: border-box; /* Box sizing */
        }
        input[type="submit"] {
            background-color: #4CAF50; /* Green background for submit button */
            color: white; /* White text color */
            cursor: pointer; /* Pointer cursor on hover */
            border: none; /* No border */
        }
        input[type="submit"]:hover {
            background-color: #45a049; /* Darker green on hover */
        }
        p {
            color: #ff0000; /* Red color for error messages */
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Đặt mật khẩu mới của bạn</h2>

    <%-- Hiển thị thông báo từ session --%>
    <%
        String message = (String) session.getAttribute("message");
        if (message != null) {
            out.println("<p>" + message + "</p>");
            session.removeAttribute("message");
        }
        String email = request.getParameter("email"); // Lấy email từ request
        String otp = request.getParameter("otp"); // Lấy otp từ request
    %>

    <form action="changePassword" method="post">
        <input type="hidden" name="email" value="<%= email %>">
        <input type="hidden" name="otp" value="<%= otp %>">

        <label for="newPassword">Mật khẩu mới:</label>
        <input type="password" id="newPassword" name="newPassword" required><br><br>

        <input type="submit" value="Đặt lại mật khẩu">
    </form>
</div>
</body>
</html>