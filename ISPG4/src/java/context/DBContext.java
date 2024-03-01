package context;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * author:
 */
public class DBContext {
//K!8vErrJ5ipESmIsfdkDvgkpar53UwSJ

//    private Connection conn = null;
//    private static DBContext instance;
//    private final String DB_ACC = "IS1704_ISP391_G4_1";
//    private final String DB_PASS = "K!8vErrJ5ipESmIsfdkDvgkpar53UwSJ";
//    private final String DB_HOST = "ongbantat.store";
//    private final String DB_PORT = "3306";
//    private final String DB_NAME = "IS1704_ISP391_G4_1";
//  
    protected Connection connection;

   public DBContext() {
    }
   
    public Connection getConnection() {
      try {
            // Edit URL, username, password to authenticate with your MySQL Server
            String url = "jdbc:mysql://localhost:3306/ISPG4";
            String userName = "root";
            String password = "123456";

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return connection;
    }
    public static void main(String[] args) {
        try {
            DBContext dbContext = new DBContext();
            Connection connection = dbContext.getConnection();

            if (connection != null) {
                System.out.println("Connected to MySQL database!");
                // Thực hiện các thao tác cần thiết với database ở đây

//                // Đóng kết nối sau khi sử dụng
                connection.close();
            } else {
                System.out.println("Failed to connect to MySQL database!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    
// private Connection conn = null;
//    private static DBContext instance;
//    private final String DB_ACC = "IS1704_ISP391_G4_1";
//    private final String DB_PASS = "K!8vErrJ5ipESmIsfdkDvgkpar53UwSJ";
//    private final String DB_HOST = "ongbantat.store";
//    private final String DB_PORT = "3306";
//    private final String DB_NAME = "IS1704_ISP391_G4_1";
//
//    public static DBContext getInstance() {
//        if (instance == null) {
//            instance = new DBContext();
//        }
//        return instance;
//   }
//
//    public ResultSet getData(String sql) {
//        ResultSet rs = null;
//        Statement state;
//        try {
//            state = conn.createStatement(
//                    ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//            rs = state.executeQuery(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return rs;
//    }
//
//    public DBContext() {
//        try {
//            if (conn == null) {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                // connection
//                String connectionString = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
//                conn = DriverManager.getConnection(connectionString, DB_ACC, DB_PASS);
//            }
//            //call driver
//            System.out.println("connected");
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public Connection getConnection() {
//        return conn;
//    }
//
//    public static void main(String[] args) {
//        DBContext.getInstance().getConnection();
//
//    }
//}
//
