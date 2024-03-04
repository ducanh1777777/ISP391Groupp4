<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Partner</title>
        <meta charset="utf-8">

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

           .search-form {
    display: flex; /* Tạo ra một flex container */
    flex-wrap: nowrap; /* Ngăn chặn các mục bên trong chuyển xuống hàng mới */
    gap: 10px; /* Khoảng cách giữa các mục */
    align-items: center; /* Căn giữa các mục theo chiều dọc */
    justify-content: center; /* Căn giữa các mục theo chiều ngang */
}

.search-form input[type="text"],
.search-form input[type="number"] {
    flex-grow: 0; /* Ngăn không cho các trường nhập liệu mở rộng tự do */
    width: auto; /* Đặt chiều rộng tự động dựa vào nội dung bên trong */
    max-width: 150px; /* Đặt chiều rộng tối đa cho các trường nhập liệu */
}

.search-form input[type="submit"] {
    flex-grow: 0; /* Ngăn không cho nút tìm kiếm mở rộng tự do */
    width: auto; /* Đặt chiều rộng tự động dựa vào nội dung bên trong */
}
            h1 {
                position: relative; /* Tạo một context cho vị trí tuyệt đối của .add-link */
                margin-right: 150px; /* Đảm bảo có đủ khoảng để nút không bị che lấp bởi tiêu đề */
            }
        </style>
    </head>
    <body>
        <jsp:include page="header3.jsp"></jsp:include> 

            <h1 style="text-align: center;">Danh sách đi mượn</h1>
            <a class="add-link" href="partner2new">Thêm chủ nợ</a>
            <!--        <form action="searchPartners" method="get">
                   
                        Tên: <input type="text" name="name" placeholder="Nhập tên"  />
                        Địa chỉ: <input type="text" name="address" placeholder="Nhập địa chỉ" />
                        SDT: <input type="text" name="phone" placeholder="Nhập số điện thoại" />
                        Email: <input type="text" name="email" placeholder="Nhập email"  />
                        Tổng nợ: <input type="number" name="debt" placeholder="Nhập tổng nợ"  />
                        <input type="submit" value="Tìm kiếm" />
                       
                    </form>-->
            <div class="search-form">
                <form action="searchPartners2" method="get">
                    <label for="name">Tên:</label>
                    <input type="text" id="name" name="name" placeholder="Nhập tên">

                    <label for="address">Địa chỉ:</label>
                    <input type="text" id="address" name="address" placeholder="Nhập địa chỉ">

                    <label for="phone">SDT:</label>
                    <input type="text" id="phone" name="phone" placeholder="Nhập số điện thoại">

                    <label for="email">Email:</label>
                    <input type="text" id="email" name="email" placeholder="Nhập email">

                    <label for="debt">Tổng nợ:</label>
                    <input type="number" id="debt" name="debt" placeholder="Nhập tổng nợ">

                    <input type="submit" value="Tìm kiếm">
                </form>
            </div>

            <br>
            <table <table class="table table-bordered">
                <thead>
                    <tr>
                        <!--                    <th>Id</th>               
                                            <th>UserId</th>               -->
                        <th>Tên</th>
                        <th>Địa chỉ</th>
                        <th>SDT</th>
                        <th>Email</th>
                        <th>Tổng nợ</th>  
                        <th>Hành động</th>
                        <th>Sửa</th>
                        <th>Xóa</th>
                        <th>Chi Tiết</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="u">
                    <c:choose>
                        <c:when test="${sessionScope.user.isAdmin == 1 }">
                            <tr>
                                <!-- <td>${u.id}</td>
                                <td>${u.userid}</td> -->
                                <td>${u.partnerName}</td>
                                <td>${u.partnerAddress}</td>
                                <td>${u.partnerPhone}</td>
                                <td>${u.partnerEmail}</td>
                                <td>${u.amountMoney}</td>
                                <td><a href="AddCreditorServlet?userid=${u.userid}&partnerid=${u.id}">Sửa nợ</a></td>
                                <td><a href="EditPartner?userid=${u.userid}&partnerid=${u.id}">Sửa</a></td>
                                <td><a href="deletePartner?id=${u.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa?');">Xóa</a></td>
                                <td><a href="DetailInvoice?userid=${u.userid}&partnerid=${u.id}">Chi Tiết</a></td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${u.userid eq sessionScope.user.id}">
                                <tr>
        <!--                            <td>${u.id}</td>
                                    <td>${u.userid}</td>-->
                                    <td>${u.partnerName}</td>
                                    <td>${u.partnerAddress}</td>
                                    <td>${u.partnerPhone}</td>
                                    <td>${u.partnerEmail}</td>
                                    <td>${u.amountMoney}</td>
                                    <td><a href="AddCreditorServlet?userid=${u.userid}&partnerid=${u.id}">Sửa nợ</a></td>
                                    <td><a href="EditPartner?userid=${u.userid}&partnerid=${u.id}">Sửa</a></td>
                                    <td><a href="deletePartner?id=${u.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa?');">Xóa</a></td>
                                    <td><a href="DetailInvoice?userid=${u.userid}&partnerid=${u.id}&partnername=${u.partnerName}">Chi tiết</a></td>
                                    </form>

                                </tr>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tbody>
        </table>
        <a href="home2.jsp">Quay lại DEBTS</a>
    </body>
</html>
