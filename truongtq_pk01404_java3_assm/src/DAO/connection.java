package DAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//class connection để
//kết nối csdl 
//thực hiện các lệnh truy vấn 
//insert update delete
//select
public class connection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/QL_khachsan?useSSL=false";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "huynh";
    private static Connection conn = null;

    // Khởi tạo kết nối tĩnh khi lớp được tải
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("Kết nối thành công!");
        } catch (ClassNotFoundException ex) {
            System.err.println("Lỗi: Thiếu driver MySQL JDBC! " + ex.getMessage());
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.err.println("Lỗi: Không thể kết nối đến cơ sở dữ liệu! " + ex.getMessage());
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Trả về kết nối (dùng để kiểm tra)
    public static Connection getConnection() {
        return conn;
    }

    // Phương thức thực thi câu lệnh SELECT
    public static ResultSet Getdata(String cauTruyVan) {
        if (conn == null) {
            System.err.println("Lỗi: Kết nối chưa được khởi tạo!");
            return null;
        }
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(cauTruyVan);
            return rs;
        } catch (SQLException ex) {
            System.err.println("Lỗi truy vấn SELECT: " + cauTruyVan + " - " + ex.getMessage());
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            // Không đóng Statement ở đây vì ResultSet cần nó
        }
    }

    // Phương thức thực thi các câu lệnh INSERT, UPDATE, DELETE
    public static int ExecuteTruyVan(String cauTruyVan) {
        if (conn == null) {
            System.err.println("Lỗi: Kết nối chưa được khởi tạo!");
            return -1;
        }
        Statement stm = null;
        try {
            stm = conn.createStatement();
            return stm.executeUpdate(cauTruyVan);
        } catch (SQLException ex) {
            System.err.println("Lỗi thực thi truy vấn: " + cauTruyVan + " - " + ex.getMessage());
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    // Phương thức chuẩn bị PreparedStatement
    public static PreparedStatement preparedStatement(String cauTruyVan) {
        if (conn == null) {
            System.err.println("Lỗi: Kết nối chưa được khởi tạo!");
            return null;
        }
        try {
            return conn.prepareStatement(cauTruyVan);
        } catch (SQLException ex) {
            System.err.println("Lỗi tạo PreparedStatement: " + cauTruyVan + " - " + ex.getMessage());
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    // Đóng kết nối
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Đã đóng kết nối!");
            } catch (SQLException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}