/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.PartnerDAO;
import entity.Partner;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author NGUYEN MINH
 */
public class searchPartners2 extends HttpServlet {
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        int id = 0;
        double debt = 0.0;
        

        // Using try-with-resources to ensure proper closing and handling of resources
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
        }

        try {
            debt = Double.parseDouble(request.getParameter("debt"));
        } catch (NumberFormatException e) {
        }

        PartnerDAO dao = new PartnerDAO();

        try {
            List<Partner> searchResults = dao.searchPartners2(id, name, address, phone, email, debt);
            request.setAttribute("list", searchResults);
           
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error processing search request.");
        }

        request.getRequestDispatcher("searchResults.jsp").forward(request, response);
    }
}
