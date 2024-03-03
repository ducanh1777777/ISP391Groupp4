package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import DAO.DAO;

public class ResetPassword extends HttpServlet {

//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String otp = request.getParameter("otp");
//        String newPassword = request.getParameter("newPassword");
//
//        DAO dao = new DAO();
//
//        // Kiểm tra OTP có hợp lệ và đúng với email tương ứng
//        String email = dao.getEmailByOTP(otp);
//        if (email != null && dao.checkOTPMatchesEmail(email, otp)) {
//            // Kiểm tra thời gian hết hạn của OTP
//            if (dao.checkOTPNotExpired(email, otp)) {
//                 
//                // Thay đổi mật khẩu
//                dao.changePassword(email, newPassword);
//               
//                // Xóa OTP sau khi sử dụng
//                dao.resetOTP(email);
//
//                // Đặt thông báo thành công và chuyển hướng người dùng
//                session.setAttribute("message", "Mật khẩu đã được đặt lại thành công. Vui lòng đăng nhập.");
//                response.sendRedirect("login.jsp");
//            } else {
//                // Xử lý trường hợp OTP đã hết hạn
//                session.setAttribute("message", "OTP đã hết hạn. Vui lòng yêu cầu mã OTP mới.");
//                response.sendRedirect("enterotp.jsp");
//            }
//        } else {
//            // Xử lý trường hợp OTP không hợp lệ hoặc không tìm thấy email
//            session.setAttribute("message", "OTP không hợp lệ hoặc đã hết hạn. Vui lòng thử lại.");
//            response.sendRedirect("forgotPassword.jsp");
//        }
//    }
//
//    // Các phương thức khác nếu cần
//    // ...
//}


protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession();
    String otp = request.getParameter("otp");

    DAO dao = new DAO();
    String email = dao.getEmailByOTP(otp);
   if (email != null && dao.checkOTPMatchesEmail(email, otp) && dao.checkOTPNotExpired(email, otp)) {
    session.setAttribute("email", email); 
    response.sendRedirect("resetPassword.jsp");
   }else {
        session.setAttribute("message", "OTP không hợp lệ hoặc đã hết hạn. Vui lòng thử lại.");
        response.sendRedirect("forgotPassword.jsp");
    }
}

}

