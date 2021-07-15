/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
/**
 *
 * @author Huong
 */
public class SanPham {
    private static String DB_URL = "jdbc:sqlserver://localhost;"
            + "databaseName=Huong_Bai2;"
            + "integratedSecurity=true";
    private static String USER_NAME = "sa";
    private static String PASSWORD = "123456";
    public static void showSanPham(){
        try {
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select * from SANPHAM");
            // show data
            System.out.println("THÔNG TIN SẢN PHẨM");
            while (rs.next()) {
                System.out.println("Mã SP: " + rs.getInt("MaSP") + " --Tên SP: "+ rs.getString("TenSP") + " --Đơn vị tính: " + rs.getString("DVTinh") 
                        + " --Giá: " + rs.getString("Gia")
                );
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    public static void insertSanPham(){
        System.out.println("-------Nhập thông tin cho sản phẩm-----------------");
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Nhập tên sản phẩm: ");
        String tenSP = sc.nextLine();
        
        System.out.println("Nhập đơn vị tính: ");
        String dvTinh = sc.nextLine();
        
        System.out.println("Nhap giá tiền: ");
        int gia = sc.nextInt();
        
        try {
            // connnect to database 'testdb'
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);

            String sql = "INSERT INTO SANPHAM(TenSP, DVTinh, Gia) VALUES (?,?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setNString(1, tenSP);
            statement.setNString(2, dvTinh);
            statement.setInt(3, gia);
            

            int rowsInserted = statement.executeUpdate();
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void updateSanPham(){
        System.out.println("----------Sửa thông tin của sản phẩm theo mã--------------");
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma sản phẩm: ");
        int maSP = sc.nextInt();
        
        try {
            // connnect to database 'testdb'
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            // crate statement
            
            String sql = "select * from SANPHAM WHERE MaSP=? ";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, maSP);
            ResultSet rs = statement.executeQuery();
            if(!rs.next()) {
                conn.close();
                System.out.println("Không tìm thấy sản phẩm!");
                return;
            }
            // close connection
            
        } catch (Exception ex) {
            System.out.println("Không tìm thấy sản phẩm!");
            ex.printStackTrace();
            return;
        }
        
        System.out.println("Nhập thông tin muốn sửa: ");
        System.out.println("Nhap ten sản phẩm: ");
        
        String tenSP = sc.nextLine();
        System.out.println("Nhập đơn vị tính: ");
        String dvTinh = sc.nextLine();
        System.out.println("Nhập giá sản phẩm: ");
        int gia = sc.nextInt();
        
        
        try {
            // connnect to database 'testdb'
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);

            String sql = "UPDATE SANPHAM SET TenSP=?, DVTinh=? WHERE MaNV=?";
            
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setNString(1, tenSP);
            statement.setNString(2, dvTinh);
            statement.setInt(3, gia);
            statement.setInt(4, maSP);
            

            int rowsInserted = statement.executeUpdate();
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    public static void deleteSanPham(){
        System.out.println("-----------XOÁ SẢN PHẨM THEO MÃ----------");
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã sản phẩm cần xoá: ");
        int maSP = sc.nextInt();
        
        try {
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            
            String sql = "select * from SANPHAM WHERE MaSP=? ";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, maSP);
            ResultSet rs = statement.executeQuery();
            if(!rs.next()) {
                conn.close();
                System.out.println("Không tìm thấy sản phẩm!");
                return;
            }
            
            String sql2 = "DELETE FROM SANPHAM WHERE MaSP=? ";
            
            PreparedStatement statement2 = conn.prepareStatement(sql);
            statement2.setInt(1, maSP);
            statement2.executeQuery();
            System.out.println("Xoá thành công!");
            // close connection
            
        } catch (Exception ex) {
            System.out.println("Không tìm thấy sản phẩm!");
            ex.printStackTrace();
            return;
        }
        
    }
    
    public SanPham(){
        
    }
        
    public static Connection getConnection(String dbURL, String userName, 
            String password) {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
}
