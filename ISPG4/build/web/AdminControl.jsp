<%-- 
    Document   : ManagerProduct
    Created on : Dec 28, 2020, 5:19:02 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <style>
            .container{
                text-align: center;
            }
            .table-title {
                text-align: center;
                margin-top: 20px; /* Điều chỉnh margin-top nếu cần */
                /* Điều chỉnh padding nếu cần */
            }
            .table-wrapper {
                text-align: center; /* Căn giữa nội dung trong table-wrapper */
            }

            .table {
                margin: 0 auto; /* Căn giữa bảng theo chiều ngang */
            }

            .table-title h2 {
                display: inline-block;
                margin: 0 auto; /* Căn giữa theo chiều ngang */
            }

            .center {
                text-align: center;
                margin-top: 20px; /* Điều chỉnh margin-top nếu cần */
                margin-bottom: 20px; /* Điều chỉnh margin-bottom nếu cần */
            }

            .pagination {
                display: flex;
                justify-content: center;
                margin-bottom: 20px; /* Điều chỉnh margin-bottom nếu cần */
            }

            .pagination a {
                color: black;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border: 1px solid #ddd;
                margin: 0 4px;
            }

            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border: 1px solid #4CAF50;
            }

            .pagination a:hover:not(.active) {
                background-color: #ddd;
            }

            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>  
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Admin Control</h2>
                        </div>

                    </div>
                </div>
                <c:set var="page" value="${requestScope.page}"/>
                <div class="center">
                    <div class="pagination"> 
                        <c:forEach begin="${1}" end="${num}" var="i">
                            <a class="${i==page?"active":""}" href="AdminControl?page=${i}">${i}</a>
                        </c:forEach>
                    </div>
                </div>
                <form action="changeAdmin" method="POST">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>User Name</th>
                                <th>Password</th>
                                <th>Full Name</th>
                                <th>DOB</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>isAdmin</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${data}" var="o">
                                <tr>
                                    <td>${o.id}</td>   
                                    <td>${o.username}</td>
                                    <td>${o.password}</td>
                                    <td>${o.fullname}</td>  
                                    <td>${o.dob}</td> 
                                    <td>${o.email}</td> 
                                    <td>${o.phone}</td>      
                                    <td>
                                        <input type="hidden" name="usernames[]" value="${o.username}" />
                                        <select name="isAdmins[]">
                                            <option value="1" ${o.isAdmin == 1 ? 'selected' : ''}>Yes</option>
                                            <option value="0" ${o.isAdmin == 0 ? 'selected' : ''}>No</option>
                                        </select>
                                    </td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                    <input type="submit" value="save" />
                </form>
                <a href="home2.jsp" >Quay lại DEBTS</a>
            </div>
        </div>


    </body>
</html>