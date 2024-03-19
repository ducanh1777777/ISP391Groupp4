/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import context.DBContext;
import entity.Partner;
import entity.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import ultil.MD5;
import ultil.SendMail;


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
    public String registerUser(String username, String pass, String birth, String email, String name) {
        try {

            String password = MD5.getMd5(pass);
            String token = UUID.randomUUID().toString(); // Tạo mã token ngẫu nhiên
            //chuan bi string sql
            String sql = "insert into Users (username, password, fullname, dob, email, is_active, created_at, token )\n"
                    + "values (?,?,?,?,?,0,NOW(),?)";
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(sql);
            //set bien dungs voiw thuw tu bien trong string tren
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setString(4, birth);
            ps.setString(5, email);
            ps.setString(6, token);

            //goi cau lenh execute
            ps.executeUpdate();

            SendMail.send(email, "Verify new user", "<h2>Welcome to my system</h2>"
                    + "<a href=\"http://localhost:8080/ISPG4/verify-user?username="
                    + username + "&token=" + token + " \" > Click here to verify your account!</a> ");
            return username;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean accAccount(String username, String token) {
        try {
            String sql = " update Users set is_active = 1 where username = ? and token = ?";
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, token);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
     public Users updateProfileUser(String username, String fullname, String dob, String email, String phone) {
        Users updatedUser = null;
        try {
            String sql = "update Users set fullname = ?, dob = ?, email = ?, phone = ?,update_at = CURRENT_TIMESTAMP where username = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, fullname);
            ps.setString(2, dob);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, username);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                // If the update was successful, retrieve the updated user information
                String selectSql = "select * from Users where username = ?";
                ps = conn.prepareStatement(selectSql);
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    // Create a User object with the updated information
                    updatedUser = new Users();
                    updatedUser.setUsername(rs.getString("username"));
                    updatedUser.setFullname(rs.getString("fullname"));
                    updatedUser.setDob(rs.getString("dob"));
                    updatedUser.setEmail(rs.getString("email"));
                    updatedUser.setPhone(rs.getString("phone"));
                }

                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources (Connection, PreparedStatement, etc.)
            // You should handle closing resources properly, preferably in a try-with-resources block
        }

        return updatedUser;
    }
    
    public boolean checkUserExists(String username) {
        String sql = "SELECT COUNT(*) FROM Users WHERE username = ?";
        try {

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public boolean checkEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM Users WHERE email = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public void updatePassword(String email, String newPassword) {
        try {
            String sql = "UPDATE Users SET password = ? WHERE email = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, MD5.getMd5(newPassword)); // Mã hóa mật khẩu mới
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//public void saveOTP(String email, String otp, Timestamp expiryTime) {
//    try {
//        String sql = "INSERT INTO otp_table (email, otp, expiry_time) VALUES (?, ?, ?)";
//        conn = new DBContext().getConnection();
//        ps = conn.prepareStatement(sql);
//        ps.setString(1, email);
//        ps.setString(2, otp);
//        ps.setTimestamp(3, expiryTime);
//        ps.executeUpdate();
//    } catch (Exception e) {
//        e.printStackTrace();
//    } 
//}
    public void saveOTP(String email, String otp) {
        try {
            String sql = "INSERT INTO otp_table (email, otp) VALUES (?, ?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, otp);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveOTP(String email, String otp, Timestamp expiryTime) {
        try {
            String sql = "UPDATE Users SET otp = ?, otp_expiry = ? WHERE email = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, otp);
            ps.setTimestamp(2, expiryTime);
            ps.setString(3, email);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//public boolean checkOTP(String email, String otp) {
//    try {
//        String sql = "SELECT COUNT(*) FROM otp_table WHERE email = ? AND otp = ? AND expiry_time > CURRENT_TIMESTAMP";
//        conn = new DBContext().getConnection();
//        ps = conn.prepareStatement(sql);
//        ps.setString(1, email);
//        ps.setString(2, otp);
//        rs = ps.executeQuery();
//        if (rs.next()) {
//            return rs.getInt(1) > 0;
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    } 
//    return false;
//}
    public boolean checkOTP(String email, String otp) {
        try {
            String sql = "SELECT COUNT(*) FROM Users WHERE email = ? AND otp = ? AND otp_expiry > CURRENT_TIMESTAMP";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, otp);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkOTPNotExpired(String email, String otp) {
        try {
            String sql = "SELECT otp_expiry FROM Users WHERE email = ? AND otp = ? AND otp_expiry > CURRENT_TIMESTAMP";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, otp);
            rs = ps.executeQuery();
            return rs.next(); // Nếu có bất kỳ kết quả nào, tức là OTP hợp lệ và chưa hết hạn
        } catch (Exception e) {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false; // Nếu có lỗi xảy ra hoặc không tìm thấy OTP hợp lệ
    }

    public String getEmailByOTP(String otp) {
        String sql = "SELECT email FROM Users WHERE otp = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, otp);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("email");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void changePassword(String email, String newPassword) {
        String sql = "UPDATE Users SET password = ? WHERE email = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetOTP(String email) {
        String sql = "UPDATE Users SET otp = null WHERE email = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkOTPMatchesEmail(String email, String otp) {
        String sql = "SELECT otp FROM Users WHERE email = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                String storedOTP = rs.getString("otp");
                return otp.equals(storedOTP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean checkOldPassword(String username, String oldPassword) {
        try {
            String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, MD5.getMd5(oldPassword)); // Sử dụng MD5 để mã hóa mật khẩu cũ
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void changePassword1(String username, String newPassword) {
        try {
            String sql = "UPDATE Users SET password = ? WHERE username = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, MD5.getMd5(newPassword)); // Mã hóa mật khẩu mới
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public ArrayList<Users> getAllUsers() {

        try {
            ArrayList<Users> list = new ArrayList<>();
            String query = "select * from Users";
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setFullname(rs.getString(4));
                user.setDob(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setPhone(rs.getString(7));
                user.setIsAdmin(rs.getInt(16));
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<Users> getTotalUsers() {
        List<Users> list = new ArrayList<>();
        String query = "select count(*) from Users";
        try {

            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Users u = new Users();
                list.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Users> getListByPage(List<Users> list, int start, int end) {
        ArrayList<Users> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }
    
    public ArrayList<Partner> getListPartner() {
        String sql1 = "SELECT p.id, p.userid, p.partnerName, p.partnerAddress, p.partnerPhone, p.partnerEmail, p.amountMoney "
               + "FROM Partner p "
               + "LEFT JOIN Users u ON p.userid = u.id "
               + "WHERE p.is_delete = 0";
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
    
    public boolean sendOTP(String email) {
    if (!checkEmailExists(email)) {
        return false;
    }
    Random random = new Random();
    String otp = String.format("%06d", random.nextInt(999999));
    long currentTimeInMillis = System.currentTimeMillis();
    long otpExpiryTimeInMillis = currentTimeInMillis + (5 * 60 * 1000);
    Timestamp otpExpiry = new Timestamp(otpExpiryTimeInMillis);

    try {
        String updateOtpSql = "UPDATE Users SET otp = ?, otp_expiry = ? WHERE email = ?";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(updateOtpSql);
        ps.setString(1, otp);
        ps.setTimestamp(2, otpExpiry);
        ps.setString(3, email);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    try {
        SendMail.send(email, "Your OTP for password reset", "Your OTP is: " + otp + ". It expires in 5 minutes.");
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    return true;
}
}
