/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

import DAO.DAO; 
import java.sql.Timestamp;
import static ultil.EmailUtils.generateOTP;
import ultil.SendMail; 

//public class ForgotPassword extends HttpServlet {
//
////    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
////        throws ServletException, IOException {
////    response.setContentType("text/html;charset=UTF-8");
////    try (PrintWriter out = response.getWriter()) {
////        // Lấy email từ người dùng nhập
////        String email = request.getParameter("email");
////
////        // Kiểm tra xem email có được nhập hay không
////        if (email == null || email.isEmpty()) {
////            // Email chưa được nhập
////            // Hiển thị thông báo lỗi và chuyển hướng người dùng lại trang quên mật khẩu
////            HttpSession session = request.getSession();
////            session.setAttribute("resetError", "Vui lòng nhập địa chỉ email.");
////            response.sendRedirect("forgotPassword.jsp");
////        } else {
////            // Email đã được nhập
////            // Kiểm tra email xem có tồn tại trong cơ sở dữ liệu không
////            DAO dao = new DAO();
////            if (dao.checkEmailExists(email)) {
////                // Tạo mã OTP ngẫu nhiên
////                String otp = generateOTP();
////                long currentTimeMillis = System.currentTimeMillis();
////                long expiryTimeMillis = currentTimeMillis + 15 * 60 * 1000; // 15 phút
////                Timestamp expiryTime = new Timestamp(expiryTimeMillis);
////
////                // Lưu mã OTP và thời gian hết hạn vào cơ sở dữ liệu
////                dao.saveOTP(email, otp, expiryTime);
////
////                // Gửi email chứa mã OTP
////                sendOTPByEmail(email, otp);
////                // Lưu mã OTP vào cơ sở dữ liệu
////                dao.saveOTP(email, otp);
////
////                // Gửi email chứa mã OTP
////                sendOTPByEmail(email, otp);
////
////                // Lưu email và thông báo thành công vào session để hiển thị trên trang khác
////                HttpSession session = request.getSession();
////                session.setAttribute("resetEmail", email);
////                session.setAttribute("resetMessage", "Đã gửi mã OTP đến địa chỉ email của bạn.");
////
////                // Chuyển hướng người dùng đến trang nhập mã OTP
////                response.sendRedirect("enterotp.jsp");
////            } else {
////                // Email không tồn tại trong cơ sở dữ liệu
////                // Hiển thị thông báo lỗi và chuyển hướng người dùng lại trang quên mật khẩu
////                HttpSession session = request.getSession();
////                session.setAttribute("resetError", "Email không tồn tại trong hệ thống.");
////                response.sendRedirect("forgotPassword.jsp");
////            }
////        }
////    }
////
////
////    }
////
////    @Override
////    protected void doGet(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////        processRequest(request, response);
////    }
////
////    @Override
////    protected void doPost(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////        processRequest(request, response);
////    }
////
////    @Override
////    public String getServletInfo() {
////        return "Forgot Password Servlet";
////    }
////
////    // Phương thức để tạo mã OTP ngẫu nhiên
////    private String generateOTP() {
////        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
////        int otpLength = 6; // Độ dài mã OTP
////
////        StringBuilder otp = new StringBuilder(otpLength);
////        Random random = new Random();
////
////        for (int i = 0; i < otpLength; i++) {
////            int index = random.nextInt(characters.length());
////            char digit = characters.charAt(index);
////            otp.append(digit);
////        }
////
////        return otp.toString();
////    }
////
////    // Phương thức để gửi mã OTP qua email
////    private void sendOTPByEmail(String email, String otp) {
////    String host = "smtp.gmail.com";
////    String port = "587";
////    String username = "minhnhnhe172717@fpt.edu.vn"; 
////    String password = "phpi lkzz oesd kevx"; 
////
////    Properties props = new Properties();
////    props.put("mail.smtp.auth", "true");
////    props.put("mail.smtp.starttls.enable", "true");
////    props.put("mail.smtp.host", host);
////    props.put("mail.smtp.port", port);
////
////    Session session = Session.getInstance(props, new Authenticator() {
////        protected PasswordAuthentication getPasswordAuthentication() {
////            return new PasswordAuthentication(username, password);
////        }
////    });
////
////    try {
////        Message message = new MimeMessage(session);
////        message.setFrom(new InternetAddress(username));
////        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
////        message.setSubject("OTP Change Password");
////        message.setText("Your OTP: " + otp);
////        Transport.send(message);
////        System.out.println("Email sent successfully");
////    } catch (MessagingException e) {
////        e.printStackTrace();
////    }
////    }
////}
//
//    
//protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    String email = request.getParameter("email");
//    DAO dao = new DAO();
//    
//    if (dao.checkEmailExists(email)) {
//        String otp = generateOTP();
//        dao.saveOTP(email, otp);
//        sendOTPByEmail(email, otp);
//        
//        // Redirect user to the OTP verification page with a success message
//    } else {
//        // Redirect back with an error message saying email does not exist
//    }
//}
//}

public class ForgotPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to forgot password form
        request.getRequestDispatcher("/forgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        request.setAttribute("email", email);
        DAO dao = new DAO();
        boolean result = dao.sendOTP(email);

        if (result) {
            // Forward to enter OTP page
            request.setAttribute("message", "OTP sent to your email. Please check your inbox.");
            request.getRequestDispatcher("/enterotp.jsp").forward(request, response);
        } else {
            // Show error message
            request.setAttribute("error", "Failed to send OTP. Please try again or contact support.");
            request.getRequestDispatcher("/forgotPassword.jsp").forward(request, response);
        }
    }
}