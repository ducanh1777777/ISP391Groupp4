<%-- 
    Document   : header
    Created on : Mar 2, 2024, 7:14:03 AM
    Author     : ducanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.17.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
<style>
    /* Custom CSS styles */
    @media (min-width: 992px) { /* Adjust this for different screen sizes as needed */
        .navbar .dropdown-menu {
            left: auto; /* Reset any previously set left value */
            right: 0; /* Align the dropdown to the right */
        }
    }
</style>
<nav class="navbar navbar-expand-lg navbar-light bg-light">

    <a class="navbar-brand" href="home.jsp">DEBTS</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <!-- Thêm vào ph?n n?i dung chính c?a trang, ví d? trong <main> ho?c <section> -->


    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav ml-auto">

            <li class="nav-item dropdown ml-auto">
                <a class="nav-link dropdown-toggle" href="#" id="smileDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-smile"></i>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="smileDropdown">
                    <a class="dropdown-item" href="login.jsp">Đăng nhập</a>
                    <a class="dropdown-item" href="register.jsp">Đăng ký</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
