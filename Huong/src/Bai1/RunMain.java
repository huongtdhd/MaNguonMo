/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
public class RunMain {
        private static String DB_URL = "jdbc:sqlserver://localhost;"
            + "databaseName=Huong_Bai1;"
            + "integratedSecurity=true";
    private static String USER_NAME = "sa";
    private static String PASSWORD = "123456";
    
    public static void main(String args[]) throws IOException {
        getSP();
        nhapLoaiSP();
        getLoaiSP();
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
    
    public static void getSP(){
        try {
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select * from SanPham inner join LoaiSanPham on SanPham.MaLoaiSP = LoaiSanPham.MaLoaiSP");
            // show data
            System.out.println("THÔNG TIN SẢN PHẨM");
            while (rs.next()) {
                System.out.println("Tên SP: "+ rs.getString(2) + " Nhà SX: " + rs.getString(3) 
                        + " Loại SP: " + rs.getString(6));
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void nhapLoaiSP() throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Nhap ten loai san pham: ");
        String tenLoaiSP = in.readLine();
        try {
            // connnect to database 'testdb'
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);

            String sql = "INSERT INTO LoaiSanPham ( TenLoaiSP) VALUES (?)";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setNString(1, tenLoaiSP);

            int rowsInserted = statement.executeUpdate();
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void getLoaiSP(){
        try {
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select * from LoaiSanPham");

            System.out.println("THÔNG TIN LOẠI SẢN PHẨM");
            while (rs.next()) {
                System.out.println("Loại SP: "+ rs.getString(2));
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
