/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author NGUYEN MINH
 */
public class VerifyOTPServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/enterotp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String otp = request.getParameter("otp");
        String newPassword = request.getParameter("newPassword");

        DAO dao = new DAO();
        boolean isValidOTP = dao.verifyOTP(email, otp);

        if (isValidOTP) {
            // OTP is correct, proceed to update password
            dao.updatePassword(email, newPassword); // Assuming updatePassword method handles password hashing
            request.setAttribute("success", "Your password has been reset successfully.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            // OTP is incorrect, show error message
            request.setAttribute("error", "Invalid OTP. Please try again.");
            request.setAttribute("email", email); // Send back the email to autofill the form
            request.getRequestDispatcher("/enterotp.jsp").forward(request, response);
        }
    }
}