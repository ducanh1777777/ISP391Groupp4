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
            /* Thiết lập màu sắc tổng thể */
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa; /* Màu nền tổng thể */
                color: #495057; /* Màu văn bản chính */
            }

            .container {
                width: 80%; /* Độ rộng của phần nội dung */
                margin: 0 auto; /* Căn giữa nội dung */
            }

            /* Thiết lập kiểu cho tiêu đề "Quản lý Users" */
            .page-title {
                text-align: center;
                color: #007bff; /* Màu sắc cho tiêu đề */
            }

            /* Thiết lập kiểu cho form tìm kiếm */
            .search-form {
                display: flex;
                justify-content: center;
                margin-bottom: 20px;
            }

            .input-group {
                width: 50%; /* Điều chỉnh độ rộng của input */
            }

            .form-control {
                display: block;
                width: 100%;
                padding: .5rem .75rem;
                font-size: 1rem;
                line-height: 1.5;
                color: #495057;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #ced4da;
                border-radius: .25rem;
                transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
            }

            .input-group-append {
                display: flex;
            }

            .btn-primary {
                color: #fff;
                background-color: #007bff;
                border-color: #007bff;
            }

            .btn-primary:hover {
                color: #fff;
                background-color: #0056b3;
                border-color: #0056b3;
            }

            /* Thiết lập kiểu cho bảng người dùng */
            .user-table {
                width: 100%;
                border-collapse: collapse;
                background-color: #fff; /* Màu nền cho bảng */
                margin-bottom: 20px;
            }

            .user-table th, .user-table td {
                padding: .75rem;
                border: 1px solid #dee2e6; /* Viền của ô */
                text-align: center;
            }

            .user-table th {
                background-color: #007bff; /* Màu nền cho dòng tiêu đề */
                color: #fff; /* Màu văn bản cho tiêu đề */
            }

            .user-table tbody tr:nth-child(even) {
                background-color: #f8f9fa; /* Màu nền cho các dòng chẵn */
            }

            /* Thiết lập kiểu cho phân trang */
            .pagination {
                display: flex;
                justify-content: center;
            }

            .pagination li {
                margin: 0 5px;
            }

            .pagination li a {
                display: inline-block;
                padding: 8px 12px;
                text-decoration: none;
                color: #007bff;
                background-color: #fff;
                border: 1px solid #007bff;
                border-radius: .25rem;
            }

            .pagination li.active a {
                color: #fff;
                background-color: #007bff;
                border-color: #007bff;
            }

            .pagination li a:hover {
                color: #fff;
                background-color: #0056b3;
                border-color: #0056b3;
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
                <form action="searchUsers" method="get" class="form-inline my-2-lg-0">
                    <div class="input-goup input-group-sm">
                        <input value="${txtS}" name="txt" type="text" class="form-control" aria-label="Small">
                        <div class="input-group-append">
                            <button type="submit" class="btn btn-secondary btn-number">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>


                </form>
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