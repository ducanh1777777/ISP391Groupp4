<%-- 
    Document   : viewUserProfile
    Created on : Jan 28, 2024, 10:23:05 PM
    Author     : ducanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f0f0; /* Màu nền xung quanh */
                color: #333;
                margin: 0;
                padding: 0;
            }
            h1 {
                color: #ff5733;
                text-align: center;
            }
            form {
                max-width: 400px;
                margin: 20px auto;
                padding: 20px;
                background-color: #f9f9f9;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            input[type="text"], input[type="date"] {
                width: 100%;
                padding: 10px;
                margin: 5px 0;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }
            input[type="submit"] {
                width: 100%;
                background-color: #ff5733;
                color: #fff;
                padding: 10px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #e04e2b;
            }
            a {
                color: #ff5733;
                text-decoration: none;
                display: block;
                margin-top: 10px;
                text-align: center;
            }
            a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header2.jsp"></jsp:include> 
        <h1>User Profile</h1>
        <form action="viewUserProfile" method="POST">
            Username: <input type="text" name="username" value="${sessionScope.user.username}" readonly /><br>
            Fullname: <input type="text" name="fullname" value="${sessionScope.user.fullname}" /><br>
            Date of birth: <input type="date" name="dob" value="${sessionScope.user.dob}" /><br>
            Email: <input type="text" name="email" value="${sessionScope.user.email}" readonly /><br>
            Phone: <input type="text" name="phone" value="${sessionScope.user.phone}" /><br>
            
            <input type="submit" value="Update" /><br><!-- comment -->
            <a href="home2.jsp">Quay lại Debts</a>
        </form>
    </body>
</html>

