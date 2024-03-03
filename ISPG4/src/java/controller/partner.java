/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.DAO;
import DAO.PartnerDAO;
import entity.Partner;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ducanh
 */
public class partner extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet partner</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet partner at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("addPartner.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int userid = Integer.parseInt(request.getParameter("userid"));
        String partnerName = request.getParameter("partnerName");
        String partnerPhone = request.getParameter("partnerPhone");
        String email = request.getParameter("email");
        String partnerAddress = request.getParameter("partnerAddress");
        Double amountMoney =Double.parseDouble(request.getParameter("amountMoney")) ;
        PartnerDAO dao = new PartnerDAO();
        
        try {
            boolean duplicate = dao.checkForDuplicateContactInfo(userid, partnerPhone);
            if (duplicate) {
                request.setAttribute("errorMessage", "SỐ ĐIỆN THOẠI ĐÃ BỊ TRÙNG, VUI LÒNG NHẬP LẠI.");
                request.getRequestDispatcher("/addPartner.jsp").forward(request, response);
            } else {
                dao.insertPartner(userid, partnerName, partnerPhone, email, partnerAddress, amountMoney);
                ArrayList<Partner> list = dao.getListPartner();
                request.setAttribute("list", list);
                request.getRequestDispatcher("partner2.jsp").forward(request, response);
            }}    catch (SQLException ex) {
            Logger.getLogger(partner.class.getName()).log(Level.SEVERE, null, ex);
        }
          }
    
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
