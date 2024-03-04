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
