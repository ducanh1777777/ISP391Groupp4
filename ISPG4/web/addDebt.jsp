<%-- 
    Document   : addDebt
    Created on : Mar 4, 2024, 9:55:15 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Thêm Nợ Cho Người Nợ</title>
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

            <div class="container"><h2 >Thêm Nợ Cho Người Nợ</h2>
                <form action="AddDebtServlet" method="post">
                    <input type="hidden" name="userid" required readonly value="${userid}">
                <input type="hidden" name="partnerid" required value="${partnerid}">
                <div class="input-container">
                    <label for="debtType">Loại Nợ:</label>
                    <select name="debtType">
                        <option value="add" >Cho vay</option>
                        <option value="subtract">Trả nợ cho mình</option>
                    </select>
                </div>
                <div class="input-container">
                    <label for="amountMoney">Số Tiền Nợ:</label>
                    <input type="number" name="amountMoney" required step="1">
                </div>
                <div class="input-container">
                    <label for="status">Ghi Chú:</label>
                    <textarea name="status"></textarea>
                </div>
                <div class="input-container">
                    <label for="time">Thời Gian:</label>
                    <input type="datetime-local" name="time" required>
                </div>
                
                
                <input type="submit" value="Thêm Nợ"><br>
                <a href="partner2">Quay lại Danh sách người nợ</a>
            </form>
        </div>

    </body>
</html>
