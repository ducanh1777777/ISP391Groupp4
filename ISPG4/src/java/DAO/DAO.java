/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import context.DBContext;
import entity.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import ultil.MD5;


/**
 *
 * @author Admin
 */
public class DAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    public Users login(String user, String pass) {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ? AND is_active = 1";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, MD5.getMd5(pass));
            rs = ps.executeQuery();
            if (rs.next()) {
                Users a = new Users();
                a.setId(rs.getInt(1));
                a.setUsername(rs.getString(2));
                a.setFullname(rs.getString(4));
                a.setDob(rs.getString(5));
                a.setEmail(rs.getString(6));
                a.setPhone(rs.getString(7));
                a.setIsAdmin(rs.getInt(16));

                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
