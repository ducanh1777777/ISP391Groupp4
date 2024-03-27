<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>About Us</title>
    <style>
        .image-container {
            display: flex;
            justify-content: center;
            margin-top: 50px; /* Adjust as necessary for your layout */
        }
        .image-container img {
            box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2); /* Simple shadow */
            transform: rotate(-8deg); /* Tilt the image */
            margin: 0 15px; /* Adjust the spacing around the image */
            transition: transform 0.3s ease-in-out; /* Smooth transition for hover effect */
        }
        .image-container img:hover {
            transform: rotate(0deg); /* Straighten the image on hover */
            box-shadow: 0 8px 16px 0 rgba(0,0,0,0.3); /* Increase shadow size on hover */
        }
    </style>
</head>
<body>
    <h1>About Us</h1>
    <div class="image-container">
        <!-- Insert your image below -->
        <img src="https://i.pinimg.com/564x/6b/af/b7/6bafb771518fd1febed4ba0211ebf673.jpg" alt="Description of the image">
        <!-- Rest of your content -->
    </div>
</body>
</html>
