<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm người nợ</title>
        <style>
        .hidden {
            display: none;
        }
        .line-break {
            margin-bottom: 10px; /* Điều chỉnh khoảng cách tùy ý */
        }label {
            width: 100px; /* Định kích thước cho nhãn */
            display: inline-block; /* Cho phép các nhãn thẳng hàng với nhau */
            text-align: left; /* Căn chỉnh văn bản bên trong nhãn sang phải */
            margin-right: 0px; /* Khoảng cách giữa nhãn và khung nhập liệu */
        }/* Định dạng cho các khung nhập liệu (input containers) */
        .input-container {
            margin-bottom: 10px; /* Khoảng cách giữa các khung nhập liệu */
        }/* Định dạng cho nút submit */
        input[type="submit"] {
            margin-left: 110px; /* Đẩy nút submit xuống dưới tương ứng với kích thước của nhãn */
        }
        .border-style {
    border: 2px solid black; /* This sets the border color to black */
    border-radius: 15px; /* This will give you rounded corners like in the image */
    display: inline-block; /* Shrinks the border to fit the content */
    padding: 10px; 
    
    /* Adjust the padding as needed */
    /* Additional styles can be added here if needed */
}

        .title-style {
    /* Định dạng cho tiêu đề nếu cần */
}
        .container {
    display: flex;
    flex-direction: column; /* Sắp xếp các items theo chiều dọc */
    align-items: center; /* Căn giữa các items theo chiều ngang */
    justify-content: center; /* Căn giữa các items theo chiều dọc nếu cần */
    height: 68vh; /* Chiều cao bằng với viewport, bạn có thể điều chỉnh theo ý muốn */
    width: 100vw; /* Chiều rộng bằng với viewport, bạn có thể điều chỉnh theo ý muốn */
}   
    </style>
    </head>
    
    <body>
         <jsp:include page="header3.jsp"></jsp:include> 
         <div class="container">
    <div class="title-style">
         <h1>Thêm người nợ</h1>
    </div>
        <form action="partner" method="POST" class="border-style">
        <input type="number" name="userid" value="${sessionScope.user.id}" class="hidden" readonly/><br>
        <div class="input-container">
            <label for="partnerName">Tên:</label>
            <input type="text" name="partnerName" required/>
        </div>
        <div class="input-container">
            <label for="partnerPhone">SDT:</label>
            <input type="text" name="partnerPhone"/>
        </div>
        <div class="input-container">
            <label for="email">Email:</label>
            <input type="text" name="email" />
        </div>
        <div class="input-container">
            <label for="partnerAddress">Địa chỉ:</label>
            <input type="text" name="partnerAddress" />
        </div>
        <div class="input-container">
            <label for="amountMoney">Tổng nợ:</label>
            <input type="text" name="amountMoney" value="0" readonly/>
        </div>
        <input type="submit" value="Thêm" /><br>
         <a href="partner2" >Quay lại Danh sách người nợ</a>
        <% if(request.getAttribute("errorMessage") != null) { %>
        <div style="color: red; margin-bottom: 20px;">
            <%= request.getAttribute("errorMessage") %>
        </div>
        <% } %>
       
    </form>
         </div>
        
</body>
</html>
