/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import context.DBContext;
import entity.Invoice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class InvoiceDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    public List<Invoice> getInvoicesByPartnerId(int partnerId) {
        List<Invoice> invoices = new ArrayList<>();
        try ( 
             PreparedStatement ps = conn.prepareStatement("SELECT i.*, u.fullname FROM Invoice i JOIN Users u ON i.userid = u.id WHERE i.partnerId = ?")) {
            conn = new DBContext().getConnection();
            ps.setInt(1, partnerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Invoice invoice = new Invoice();
                    // Assuming Invoice class has setters for all fields
                    invoice.setId(rs.getInt("id"));
                    invoice.setUserId(rs.getInt("userId"));
                    invoice.setPartnerId(rs.getInt("partnerid"));
                    invoice.setAmountMoney(rs.getDouble("amountMoney"));
                    invoice.setStatus(rs.getString("status"));
                    invoice.setTime(rs.getTimestamp("time"));
                     invoice.setDebtType(rs.getString("debtType"));
                    // Additional fields as needed
                    invoices.add(invoice);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }
    public ArrayList<Invoice> getAllInvoices(int partnerid, String partnerName) {
        try {
            ArrayList<Invoice> list = new ArrayList<>();
            String query = "SELECT * from invoice LEFT  JOIN partner\n"
                    + " ON partner.id = invoice.partnerid  where partnerid = ? and partnerName = ?";
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, partnerid);
            ps.setString(2, partnerName);
            rs = ps.executeQuery();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setUserId(rs.getInt("userId"));
                invoice.setPartnerId(rs.getInt("partnerId"));
                invoice.setAmountMoney(rs.getDouble("amountMoney"));
                invoice.setStatus(rs.getString("status"));
                invoice.setTime(rs.getTimestamp("time"));
                invoice.setDebtType(rs.getString("debtType"));
                list.add(invoice);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
