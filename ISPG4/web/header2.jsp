<%-- 
    Document   : header2
    Created on : Mar 2, 2024, 8:27:42 AM
    Author     : ducanh
--%>

<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.17.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>

    /* Custom CSS styles */
    @media (min-width: 992px) {
        .navbar .dropdown-menu {
            left: auto; /* Reset any previously set left value */
            right: 0; /* Align the dropdown to the right */
        }
    }
</style>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="home2.jsp">DEBTS</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <c:if test="${sessionScope.user !=null}">
        <li class="btn">
            <a class="btn" href="viewUserProfile"><span>Xin chào ${sessionScope.user.fullname}</span></a>
        </li>
    </c:if>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <!--        <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Debt
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="#">Debt bills</a>
                            <a class="dropdown-item" href="#">Payment history</a>
                          
                        </div>
                    </li>
                </ul>-->

        <div class="hero__caption">
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="debtDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"  style="color:'#007bff'">
                            Danh sách
                        </a>
                        <div class="dropdown-menu" aria-labelledby="debtDropdown">
                            <a class="dropdown-item" href="partner2">Danh sách người nợ</a>
                            <a class="dropdown-item" href="partner2new">Danh sách đi mượn</a>
                        </div>
                    </li>
                </ul>
                <!-- Các phần khác của navbar ở đây -->
            </div>
            <!--    <a href="partner2" class="btn">Danh sách người nợ</a>-->
        </div>
        <c:if test="${sessionScope.user.isAdmin == 1}">
            <li class="btn">
                <a class="btn" href="AdminControl">Manager Account</a>
            </li>
        </c:if>


        <ul class="navbar-nav ml-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="notificationBell" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" ">
                    <i class="fas fa-bell"></i>

                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="notificationBell">
                    <div id="notificationContainer"></div>
                </div>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="smileDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-smile"></i>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="smileDropdown">
                    <a class="dropdown-item" href="viewUserProfile"> <c:if test="${sessionScope.user !=null}">

                            ${sessionScope.user.fullname}


                        </c:if></a>
                    <a class="dropdown-item" href="changePassword.jsp">Thay đổi mật khẩu</a>
                    <a class="dropdown-item" href="home.jsp">Đăng xuất</a>

                </div>
            </li>
        </ul>
    </div>
</nav>
