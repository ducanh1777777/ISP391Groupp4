/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import context.DBContext;
import entity.Partner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class PartnerDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void insertPartner(int userid, String partnerName, String partnerPhone, String email, String partnerAddress, double amountMoney) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new DBContext().getConnection();
            conn.setAutoCommit(false);
            // First, check if the partner already exists
            Partner existingPartner = checkPartnerExists(partnerName, partnerPhone, email);

            if (existingPartner != null) {
                // Partner exists, so we adjust their debt instead of inserting a new partner
                adjustPartnerDebt(existingPartner.getId(), amountMoney);
            } else {
                // Partner doesn't exist, proceed with insertion
                String sql = "INSERT INTO Partner (userid, partnerName, partnerAddress, partnerPhone, partnerEmail, amountMoney) VALUES (?, ?, ?, ?, ?, ?);";
                conn = new DBContext().getConnection();
                ps = conn.prepareStatement(sql);
                ps.setInt(1, userid);
                ps.setString(2, partnerName);
                ps.setString(3, partnerAddress);
                ps.setString(4, partnerPhone);
                ps.setString(5, email);
                ps.setDouble(6, amountMoney);
                ps.executeUpdate();

                // Notification logic here...
                conn.commit();
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Partner checkPartnerExists(String partnerName, String partnerPhone, String partnerEmail) {
        Partner existingPartner = null;
        try {
            String sql = "SELECT * FROM Partner WHERE partnerName = ? AND partnerPhone = ? AND partnerEmail = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, partnerName);
            ps.setString(2, partnerPhone);
            ps.setString(3, partnerEmail);
            rs = ps.executeQuery();

            if (rs.next()) {
                existingPartner = new Partner();
                // Assume Partner has a constructor that takes all fields or setters for all fields
                existingPartner.setId(rs.getInt("id"));
                existingPartner.setUserid(rs.getInt("userid"));
                existingPartner.setPartnerName(rs.getString("partnerName"));
                existingPartner.setPartnerAddress(rs.getString("partnerAddress"));
                existingPartner.setPartnerPhone(rs.getString("partnerPhone"));
                existingPartner.setPartnerEmail(rs.getString("partnerEmail"));
                existingPartner.setAmountMoney(rs.getDouble("amountMoney"));
                // ... set other fields as needed
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return existingPartner;
    }

    public void adjustPartnerDebt(int partnerid, double amount) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new DBContext().getConnection();
            conn.setAutoCommit(false);

            // Cập nhật tổng nợ trong bảng Partner
            String updateSql = "UPDATE Partner SET amountMoney = amountMoney + ? WHERE id = ?";
            ps = conn.prepareStatement(updateSql);
            ps.setDouble(1, amount); // amount có thể là dương hoặc âm
            ps.setInt(2, partnerid);
            ps.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean checkForDuplicateContactInfo(int userid, String partnerPhone) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Partner WHERE userid = ? AND partnerPhone = ? ";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userid);
            ps.setString(2, partnerPhone);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public ArrayList<Partner> getListPartner() {
        String sql1 = "SELECT p.id, p.userid, p.partnerName, p.partnerAddress, p.partnerPhone, p.partnerEmail, p.amountMoney "
                + "FROM Partner p "
                + "LEFT JOIN Users u ON p.userid = u.id "
                + "WHERE p.is_delete = 0 and amountMoney>=0";
        try {
            ArrayList<Partner> list = new ArrayList<>();
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql1);
            rs = ps.executeQuery();
            while (rs.next()) {
                Partner u = new Partner();
                u.setId(rs.getInt("id"));
                u.setUserid(rs.getInt("userid"));
                u.setPartnerName(rs.getString("partnerName"));
                u.setPartnerAddress(rs.getString("partnerAddress"));
                u.setPartnerPhone(rs.getString("partnerPhone"));
                u.setPartnerEmail(rs.getString("partnerEmail"));
                u.setAmountMoney(rs.getDouble("amountMoney"));
                list.add(u);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void insertInvoice(int userid,int partnerid, String status, double amountMoney, Timestamp time,  String debtType) {
    Connection conn = null;
    PreparedStatement ps = null;
    try {
        conn = new DBContext().getConnection();
        conn.setAutoCommit(false);

        String sql = "INSERT INTO Invoice (userid, partnerid, amountMoney, status, time, debtType) VALUES (?, ?, ?, ?, ?, ?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, userid);
        ps.setInt(2, partnerid);
        ps.setDouble(3, amountMoney); // amountMoney đã bao gồm dấu (+/-) dựa trên loại nợ
        ps.setString(4, status);
        ps.setTimestamp(5, time);
        ps.setString(6, debtType);
        ps.executeUpdate();
        
//        String updateSql = "UPDATE Partner SET amountMoney = amountMoney + ? WHERE id = ?;";
//        ps = conn.prepareStatement(updateSql);
//        ps.setDouble(1, amountMoney); // Số tiền này đã bao gồm dấu (+/-) dựa trên loại nợ
//        ps.setInt(2, partnerid);
//        ps.executeUpdate();
        
        conn.commit();
    } catch (SQLException e) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        e.printStackTrace();
    } finally {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
      public Partner getPartner(int id) {
        Partner partner = null;
        String sql = "SELECT * FROM Partner WHERE id = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                partner = new Partner(
                        rs.getInt("id"),
                        rs.getInt("userid"),
                        rs.getString("partnerName"),
                        rs.getString("partnerPhone"),
                        rs.getString("partnerEmail"),
                        rs.getString("partnerAddress"),
                        rs.getDouble("amountMoney")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
        return partner;
    }
         public void updatePartner(Partner partner) {
    String sql = "UPDATE Partner SET userid = ?, partnerName = ?, partnerPhone = ?, partnerEmail = ?, partnerAddress = ? WHERE id = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, partner.getUserid());
        ps.setString(2, partner.getPartnerName());
        ps.setString(3, partner.getPartnerPhone());
        ps.setString(4, partner.getPartnerEmail());
        ps.setString(5, partner.getPartnerAddress());
        // Uncomment the following line if you want to update the amountMoney field.
        // ps.setDouble(6, partner.getAmountMoney());
        ps.setInt(6, partner.getId()); // This should be the last parameter now after removing the extra comma.

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Updating partner failed, no rows affected.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle exception
    }

    }
     
     public Partner getPartnerById(int partnerId) {
        Partner partner = null;
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Partner WHERE id = ?")) {
            ps.setInt(1, partnerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    partner = new Partner();
                    partner.setId(rs.getInt("id"));
                    partner.setUserid(rs.getInt("userid"));
                    partner.setPartnerName(rs.getString("partnerName"));
                    partner.setPartnerPhone(rs.getString("partnerPhone"));
                    partner.setPartnerEmail(rs.getString("partnerEmail"));
                    partner.setPartnerAddress(rs.getString("partnerAddress"));
                    partner.setAmountMoney(rs.getDouble("amountMoney"));
                    // ... populate other fields as needed
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partner;
    }
     

    public List<Partner> searchPartners(int id, String name, String address, String phone, String email, double debt) {
    List<Partner> partners = new ArrayList<>();
    // Bắt đầu với một câu truy vấn cơ bản không có điều kiện tìm kiếm cụ thể
    String sql = "SELECT * FROM Partner WHERE is_delete=0 and amountMoney>=0";
    List<Object> params = new ArrayList<>();

    if (id > 0) {
        sql += " AND id = ?";
        params.add(id);
    }
    if (name != null && !name.trim().isEmpty()) {
        sql += " AND (partnerName LIKE ? OR partnerName LIKE ?)";
        params.add(name.trim() + "%"); 
        params.add("% " + name.trim() + "%"); // Matches the start of any subsequent word in the name
    }

    if (address != null && !address.trim().isEmpty()) {
    sql += " AND (partnerAddress LIKE ? OR partnerAddress LIKE ?)";
    params.add(address.trim() + "%"); // Matches the start of the address
    params.add("% " + address.trim() + "%"); // Matches the start of any subsequent word in the address
}
    if (phone != null && !phone.trim().isEmpty()) {
        sql += " AND partnerPhone LIKE ?";
        params.add("%" + phone.trim() + "%");
    }
    if (email != null && !email.trim().isEmpty()) {
        sql += " AND LOWER(partnerEmail) LIKE LOWER(?)";
        params.add("%" + email.trim() + "%");
    }
    if (debt > 0) { // Chỉ thêm điều kiện này nếu debt > 0
        sql += " AND amountMoney >= ?"; // Tìm các đối tác có khoản nợ lớn hơn hoặc bằng giá trị nhập vào
        params.add(debt);
    }

    // Xóa bỏ logic thừa liên quan đến xử lý 'debt' ở đây

    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
         
        // Đặt giá trị cho các tham số của PreparedStatement
        for (int i = 0; i < params.size(); i++) {
            ps.setObject(i + 1, params.get(i));
        }
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Partner partner = new Partner();
                partner.setId(rs.getInt("id"));
                partner.setUserid(rs.getInt("userid"));
                partner.setPartnerName(rs.getString("partnerName"));
                partner.setPartnerAddress(rs.getString("partnerAddress"));
                partner.setPartnerPhone(rs.getString("partnerPhone"));
                partner.setPartnerEmail(rs.getString("partnerEmail"));
                partner.setAmountMoney(rs.getDouble("amountMoney"));
                partners.add(partner);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return partners;
}
     
      public List<Partner> searchPartners2(int id, String name, String address, String phone, String email, double debt) {
    List<Partner> partners = new ArrayList<>();
    // Bắt đầu với một câu truy vấn cơ bản không có điều kiện tìm kiếm cụ thể
    String sql = "SELECT * FROM Partner WHERE is_delete=0 and amountMoney<0";
    List<Object> params = new ArrayList<>();

    if (id > 0) {
        sql += " AND id = ?";
        params.add(id);
    }
    if (name != null && !name.trim().isEmpty()) {
        sql += " AND (partnerName LIKE ? OR partnerName LIKE ?)";
        params.add(name.trim() + "%"); 
        params.add("% " + name.trim() + "%"); // Matches the start of any subsequent word in the name
    }

    if (address != null && !address.trim().isEmpty()) {
    sql += " AND (partnerAddress LIKE ? OR partnerAddress LIKE ?)";
    params.add(address.trim() + "%"); // Matches the start of the address
    params.add("% " + address.trim() + "%"); // Matches the start of any subsequent word in the address
}
    if (phone != null && !phone.trim().isEmpty()) {
        sql += " AND partnerPhone LIKE ?";
        params.add("%" + phone.trim() + "%");
    }
    if (email != null && !email.trim().isEmpty()) {
        sql += " AND LOWER(partnerEmail) LIKE LOWER(?)";
        params.add("%" + email.trim() + "%");
    }
    if (debt > 0) { // Chỉ thêm điều kiện này nếu debt > 0
        sql += " AND amountMoney >= ?"; // Tìm các đối tác có khoản nợ lớn hơn hoặc bằng giá trị nhập vào
        params.add(debt);
    }

    // Xóa bỏ logic thừa liên quan đến xử lý 'debt' ở đây

    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
         
        // Đặt giá trị cho các tham số của PreparedStatement
        for (int i = 0; i < params.size(); i++) {
            ps.setObject(i + 1, params.get(i));
        }
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Partner partner = new Partner();
                partner.setId(rs.getInt("id"));
                partner.setUserid(rs.getInt("userid"));
                partner.setPartnerName(rs.getString("partnerName"));
                partner.setPartnerAddress(rs.getString("partnerAddress"));
                partner.setPartnerPhone(rs.getString("partnerPhone"));
                partner.setPartnerEmail(rs.getString("partnerEmail"));
                partner.setAmountMoney(rs.getDouble("amountMoney"));
                partners.add(partner);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return partners;
}
      
      
     // Method to soft-delete invoices by partner ID
    public void deletePartnerInvoices(int partnerId) {
        String sql = "UPDATE Invoice SET is_delete = 1 WHERE partnerid = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, partnerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging this error or throwing a custom exception
        }
    }
    
    // Method to soft-delete a partner by ID
    public void deletePartner(int id) {
        String sql = "UPDATE Partner SET is_delete = 1 WHERE id = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging this error or throwing a custom exception
        }
    }
    
    // Method to soft-delete both partner and their invoices
    public void deletePartnerAndInvoices(int partnerId) {
        // Soft-delete invoices first to maintain referential integrity
        deletePartnerInvoices(partnerId);
        // Then soft-delete the partner
        deletePartner(partnerId);
    }
    
}
