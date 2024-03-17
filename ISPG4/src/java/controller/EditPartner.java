/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.PartnerDAO;
import entity.Partner;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class EditPartner extends HttpServlet {
private PartnerDAO partnerDAO = new PartnerDAO();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditPartner</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditPartner at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String useridStr = request.getParameter("userid");
        String partneridStr = request.getParameter("partnerid");
    
    if (useridStr != null && partneridStr != null && !useridStr.isEmpty() && !partneridStr.isEmpty()) {
        int userid = Integer.parseInt(useridStr);
        int partnerid = Integer.parseInt(partneridStr);
        
        Partner partner = partnerDAO.getPartner(partnerid);
        
        if (partner != null && partner.getUserid() == userid) {
            request.setAttribute("partner", partner);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Edit.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("partner2.jsp");
        }
    } else {
        response.sendRedirect("partner2.jsp");
    }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String useridStr = request.getParameter("userid");
    String partneridStr = request.getParameter("partnerid");
    String partnerName = request.getParameter("partnerName");
    String partnerPhone = request.getParameter("partnerPhone");
    String partnerEmail = request.getParameter("partnerEmail");
    String partnerAddress = request.getParameter("partnerAddress");
    String amountMoneyStr = request.getParameter("amountMoney");
    partnerPhone = partnerPhone.isEmpty() ? null : partnerPhone;
    partnerEmail = partnerEmail.isEmpty() ? null : partnerEmail;
     if (partnerName == null || partnerName.trim().isEmpty()) {
        request.setAttribute("errorMessage", "Tên người nợ không được để trống.");
        doGet(request, response); // Gọi lại doGet để xử lý hiển thị form với thông báo lỗi
        return;
        }
    // Validate that the mandatory parameters are present
    if (useridStr == null || partneridStr == null || useridStr.isEmpty() || partneridStr.isEmpty()) {
        // Parameters are missing, handle this case properly, e.g., show an error message
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing user ID or partner ID.");
        return;
    }

    // Try to parse the integer parameters
    int userid = 0;
    int partnerid = 0;
    try {
        userid = Integer.parseInt(useridStr);
        partnerid = Integer.parseInt(partneridStr);
    } catch (NumberFormatException e) {
        // The strings did not contain valid integers, handle this case properly
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID or partner ID format.");
        return;
    }

    // Try to parse the double parameter for amountMoney
    double amountMoney = 0;
    try {
        amountMoney = Double.parseDouble(amountMoneyStr);
    } catch (NumberFormatException e) {
        // The string did not contain a valid double, handle this case properly
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid amount format.");
        return;
    }

    // Create a new Partner object with the form data
    Partner partner = new Partner(partnerid, userid, partnerName, partnerPhone, partnerEmail, partnerAddress, amountMoney);

    // Update partner information in the database
    PartnerDAO partnerDAO = new PartnerDAO(); // Log the stack trace for debugging purposes
     partner.setPartnerPhone(partnerPhone);
    partner.setPartnerEmail(partnerEmail);
    // This prints the stack trace to the server logs
    // Provide a more user-friendly message to the client
    partnerDAO.updatePartner(partner);

    // After successful update, redirect to the partner list page
    response.sendRedirect("partner2");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
