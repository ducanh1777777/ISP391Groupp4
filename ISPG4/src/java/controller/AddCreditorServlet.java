/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.PartnerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 *
 * @author NGUYEN MINH
 */
public class AddCreditorServlet extends HttpServlet {
   
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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddDebtServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddDebtServlet at " + request.getContextPath() + "</h1>");
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
        String userid = request.getParameter("userid");
        String partnerid = request.getParameter("partnerid");
        request.setAttribute("userid", userid);
        request.setAttribute("partnerid", partnerid);
        request.getRequestDispatcher("addDebt2.jsp").forward(request, response);
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
        int userid = Integer.parseInt(request.getParameter("userid"));
        int partnerid = Integer.parseInt(request.getParameter("partnerid"));
        String debtType = request.getParameter("debtType"); 
        double amountMoney = Double.parseDouble(request.getParameter("amountMoney"));
        String status = request.getParameter("status");
        Timestamp time = new Timestamp(System.currentTimeMillis());
        
        if (amountMoney < 0) {
        
        request.setAttribute("error", "Số tiền không thể âm.");
        request.getRequestDispatcher("addDebt2.jsp").forward(request, response);
        return;
    }

        PartnerDAO dao = new PartnerDAO();
        if ("subtract".equals(debtType)) {
            dao.adjustPartnerDebt(partnerid, -amountMoney); 
        } else {         
            dao.adjustPartnerDebt(partnerid, amountMoney);
        }
        dao.insertInvoice(userid, partnerid, status, amountMoney, time, debtType);
        response.sendRedirect("partner2new");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "AddDebtServlet adds a debt to a partner and updates their total debt";
    }// </editor-fold>

}
