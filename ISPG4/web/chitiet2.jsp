<%-- 
    Document   : chitiet
    Created on : Feb 20, 2024, 8:06:50 PM
    Author     : ducanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        .add-link {
            background-color: #f2f2f2; /* Màu nền xám */
            color: black; /* Màu chữ đen */
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 5px;
            position: absolute; /* Đặt phần tử ở vị trí tuyệt đối */
            top: 17.15%; /* Đặt vào giữa chiều cao của phần tử bên ngoài */
        transform: translateY(-50%); /* Cân chỉnh để chính giữa theo chiều dọc */
        right: 20px;
        }
        h1 {
        position: relative; /* Tạo một context cho vị trí tuyệt đối của .add-link */
        margin-right: 150px; /* Đảm bảo có đủ khoảng để nút không bị che lấp bởi tiêu đề */
    }
        
    </style>
    <body>
        <jsp:include page="header3.jsp"></jsp:include> 
        <a href="AddCreditorServlet?userid=${userid}&partnerid=${partnerid}" class="add-link">Thêm phiếu nợ</a><br>
        <h1 style="text-align: center;">Chi tiết hóa đơn của ${partnername}</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <!-- comment <th>ID</th>
                    <th>UserId</th>
                    <th>PartnerId</th>-->
                    <th>Số tiền</th>
                    <th>Loại Nợ</th>
                    <th>Ghi chú</th>
                    <th>Thời gian tạo</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="c">
                    <tr>
                        <!-- <td>${c.id}</td>
                        <td>${c.userId}</td>
                        <td>${c.partnerId}</td>-->
                        <td>${c.amountMoney}</td>
<!--                        <td>${c.debtType}</td>-->
                        <td>
            <%-- Kiểm tra giá trị của biến debtType và hiển thị tương ứng --%>
            <c:choose>
                <c:when test="${c.debtType eq 'add'}">
                    Trả nợ
                </c:when>
                <c:when test="${c.debtType eq 'subtract'}">
                    Cho vay
                </c:when>
                <c:otherwise>
                    Loại nợ không xác định
                </c:otherwise>
            </c:choose>
        </td>
                        <td>${c.status}</td>
                        <td>${c.time}</td>
                    </tr>
                </c:forEach> 
            </tbody>
        </table>
        <a href="partner2new">Quay lại Danh sách đi mượn</a>
    </body>
</html>