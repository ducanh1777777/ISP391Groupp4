<%-- 
    Document   : Edit
    Created on : Mar 8, 2024, 8:37:25 AM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sửa Thông Tin Người Nợ</title>
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
        }
        .title-style {/* Định dạng cho tiêu đề nếu cần */
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
    <h2>Sửa Thông Tin Người Nợ</h2>
    <form action="EditPartner" method="post" class="border-style">
      <input type="hidden" name="userid" value="${partner.userid}" />
      <input type="hidden" name="partnerid" value="${partner.id}" />
        <div class="input-container">
                <label for="partnerName">Tên:</label>
                <input type="text" name="partnerName" value="${partner.partnerName}" /><br>
                 <% if (request.getAttribute("errorMessage") != null) { %>
        <div style="color: red;"><%= request.getAttribute("errorMessage").toString() %></div>
    <% } %>
            </div>
            <div class="input-container">
                <label for="partnerPhone">SDT:</label>
                <input type="text" name="partnerPhone" value="${partner.partnerPhone}" /><br>
            </div>
            <div class="input-container">
                <label for="partnerEmail">Email:</label>
                <input type="email" name="partnerEmail" value="${partner.partnerEmail}" /><br>
            </div>
            <div class="input-container">
                <label for="partnerAddress">Địa chỉ:</label>
                <input type="text" name="partnerAddress" value="${partner.partnerAddress}"/><br>
            </div>
            <div class="input-container">
                <label for="amountMoney">Số tiền nợ:</label>
                <input type="number" name="amountMoney" value="${partner.amountMoney}" /><br>
            </div>
        <input type="submit" value="Cập nhật" />
    </form>
    </div>
</body>
</html>