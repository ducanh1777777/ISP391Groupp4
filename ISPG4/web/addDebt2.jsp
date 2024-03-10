<%-- 
    Document   : addDebt2
    Created on : Feb 29, 2024, 11:14:47 PM
    Author     : NGUYEN MINH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Thêm Nợ Cho Chủ Nợ</title>
        <style>
            .hidden {
                display: none;
            }
            .line-break {
                margin-bottom: 10px; /* Điều chỉnh khoảng cách tùy ý */
            }
            label {
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

            <div class="container"><h2 >Thêm Nợ Cho Chủ Nợ</h2>
                <form action="AddCreditorServlet" method="post">
                    <input type="hidden" name="userid" required readonly value="${userid}">
                <input type="hidden" name="partnerid" required value="${partnerid}">
                <div class="input-container">
                    <label for="debtType">Loại Nợ:</label>
                    <select name="debtType">
                        <option value="add" >Trả nợ cho chủ nợ</option>
                        <option value="subtract">Đi vay</option>
                    </select>
                </div>
                <div class="input-container">
                    <label for="amountMoney">Số Tiền Nợ:</label>
                    <input type="number" name="amountMoney" required step="1">
                </div>
                 <% if (request.getAttribute("error") != null) { %>
                <div style="color: red; margin-bottom: 20px;">
                <%= request.getAttribute("error") %>
                </div>
                <% } %>
                <div class="input-container">
                    <label for="status">Ghi Chú:</label>
                    <textarea name="status"></textarea>
                </div>
                <div class="input-container">
                    <label for="time">Thời Gian:</label>
                    <input type="datetime-local" name="time" required>
                </div>
                
                
                <input type="submit" value="Thêm Nợ"><br>
                <a href="partner2new">Quay lại Danh sách đi mượn</a>
            </form>
        </div>

    </body>
</html>