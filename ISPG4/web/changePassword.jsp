<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Change Password</title>
</head>
<body>
    <h2>Change Password</h2>
    <%
        // Không cần khai báo session vì nó đã tự động có sẵn
        String message = (String) session.getAttribute("message");
        if (message != null) {
            out.println("<p>" + message + "</p>");
            session.removeAttribute("message");
        }
    %>
    <form action="changePassword2" method="post">
        Username: <input type="text" name="username" required><br>
        Old Password: <input type="password" name="oldPassword" required><br>
        New Password: <input type="password" name="newPassword" required><br>
        Confirm New Password: <input type="password" name="confirmNewPassword" required><br>
        <input type="submit" value="Change Password"><br>
         <a href="home2.jsp">Back to Home</a>
    </form>
</body>
</html>
