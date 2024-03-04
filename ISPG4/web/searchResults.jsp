<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Kết quả tìm kiếm</title>
    

    <style>
        .add-link {
            background-color: #f2f2f2; /* Màu nền xám */
            color: black; /* Màu chữ đen */
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 5px;
            position: absolute; /* Đặt phần tử ở vị trí tuyệt đối */
            top: 13.75%; /* Đặt vào giữa chiều cao của phần tử bên ngoài */
        transform: translateY(-50%); /* Cân chỉnh để chính giữa theo chiều dọc */
        right: 20px;
        }
        h1 {
        position: relative; /* Tạo một context cho vị trí tuyệt đối của .add-link */
        margin-right: 150px; /* Đảm bảo có đủ khoảng để nút không bị che lấp bởi tiêu đề */
    }
        
    </style>
</head>
<body>
    <jsp:include page="header3.jsp"></jsp:include> 
    <h1 style="text-align: center;">Kết quả tìm kiếm</h1>
     <table  class="table table-bordered">
        <!-- Table headers -->
        <tr>
            <!--<th>Id</th>!-->
            <th>Tên</th>
            <th>Địa chỉ</th>
            <th>SDT</th>
            <th>Email</th>
            <th>Tổng nợ</th>
<!--            <th>Chi Tiết</th>-->
            <th>Xóa</th>
        </tr>
        <!-- Table content -->
        <c:forEach var="partner" items="${list}">
            <c:if test="${partner.userid eq sessionScope.user.id}">
            <tr>
                <!--<td>${partner.id}</td>!-->
                <td>${partner.partnerName}</td>
                <td>${partner.partnerAddress}</td>
                <td>${partner.partnerPhone}</td>
                <td>${partner.partnerEmail}</td>
                <td>${partner.amountMoney}</td>
<!--                <td><a href="DetailInvoice?userid=${user.id}&partnerid=${u.id}">Chi Tiết</a></td>-->
                <td><a href="deletePartner?id=${partner.id}" style="color: #0e0e0e;" onclick="return confirm('Bạn có chắc chắn muốn xóa?');">Xóa</a></td>
            </tr>
            </c:if>
        </c:forEach>
    </table>
    <a href="partner2" >Quay lại Danh sách người nợ</a>
</body>
</html>
