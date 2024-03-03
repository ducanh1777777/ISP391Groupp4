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
import java.util.ArrayList;

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
}