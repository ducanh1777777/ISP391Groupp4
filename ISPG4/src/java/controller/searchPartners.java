/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.PartnerDAO;
import entity.Partner;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
@WebServlet(name="searchPartners", urlPatterns={"/searchPartners"})
public class searchPartners extends HttpServlet {
    
  

    

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
            List<Partner> searchResults = dao.searchPartners(id, name, address, phone, email, debt);
            request.setAttribute("list", searchResults);
           
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error processing search request.");
        }

        request.getRequestDispatcher("partner2.jsp").forward(request, response);
    }
}