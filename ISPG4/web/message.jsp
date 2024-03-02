<%-- 
    Document   : message
    Created on : Mar 3, 2024, 12:25:16 AM
    Author     : ducanh
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Success</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
    <style>
        .success-message {
            text-align: center;
            margin-top: 50px;
        }
        .sticker {
            display: inline-block;
            background-color: #4CAF50; /* Green background */
            color: white;
            padding: 20px;
            border-radius: 50%; /* Makes it circular */
            box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2); /* Drop shadow */
            font-size: 2em;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="success-message">
        <div class="sticker">
            <i class="fas fa-check-circle"></i>
        </div>
        <p><%= request.getAttribute("registrationSuccess") %></p>
    </div>
</body>
</html>
