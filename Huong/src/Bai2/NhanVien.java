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
public class NhanVien {
        private static String DB_URL = "jdbc:sqlserver://localhost;"
            + "databaseName=Huong_Bai2;"
            + "integratedSecurity=true";
    private static String USER_NAME = "sa";
    private static String PASSWORD = "123456";
    public static void showNhanVien(){
        try {
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select * from NHANVIEN");
            // show data
            System.out.println("THÔNG TIN NHÂN VIÊN");
            while (rs.next()) {
                System.out.println("Mã NV: " + rs.getInt("MaNV") + " --Tên NV: "+ rs.getString("HoTen") + " --Ngày sinh: " + rs.getString("DOB") 
                        + " --Địa chỉ: " + rs.getString("DiaChi")+ " --SĐT: " + rs.getString("SDT")
                                + " --Giới tính: " + rs.getString("GioiTinh")
                );
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    public static void insertNhanVien(){
        System.out.println("-------Nhập thông tin cho nhân viên-----------------");
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Nhập họ tên NV: ");
        String tenNV = sc.nextLine();
        
        System.out.println("Nhập ngày sinh: ");
        String dob = sc.nextLine();
        
        System.out.println("Nhap dia chi: ");
        String diaChi = sc.nextLine();
        System.out.println("Nhap SDT: ");
        String sdt = sc.nextLine();
        System.out.println("Nhap gioi tinh: ");
        String gt = sc.nextLine();
        
        try {
            // connnect to database 'testdb'
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);

            String sql = "INSERT INTO NHANVIEN (HoTen, DOB, DiaChi, SDT, GioiTinh) VALUES (?,?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setNString(1, tenNV);
            statement.setNString(2, dob);
            statement.setNString(3, diaChi);
            statement.setNString(4, sdt);
            statement.setNString(5, gt);

            int rowsInserted = statement.executeUpdate();
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
        
    }
    public static void updateNhanVien(){
        System.out.println("----------Sửa thông tin của nhân viên theo mã--------------");
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma nhan vien muon sua: ");
        int maNV = sc.nextInt();
        
        try {
            // connnect to database 'testdb'
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            // crate statement
            
            String sql = "select * from NHANVIEN WHERE MaNV=? ";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, maNV);
            ResultSet rs = statement.executeQuery();
            if(!rs.next()) {
                conn.close();
                System.out.println("Không tìm thấy nhân viên!");
                return;
            }
            // close connection
            
        } catch (Exception ex) {
            System.out.println("Không tìm thấy nhân viên!");
            ex.printStackTrace();
            return;
        }
        sc.nextLine();
        System.out.println("Nhập thông tin muốn sửa: ");
        System.out.println("Nhap ten nhan vien: ");
        
        String name = sc.nextLine();
        System.out.println("Nhap ngay sinh: ");
        String dob = sc.nextLine();
        System.out.println("Nhap dia chi: ");
        String diaChi = sc.nextLine();
        System.out.println("Nhap SDT: ");
        String sdt = sc.nextLine();
        System.out.println("Nhap gioi tinh: ");
        String gt = sc.nextLine();
        
        try {
            // connnect to database 'testdb'
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);

            String sql = "UPDATE NHANVIEN SET HoTen=?, DOB=?, DiaChi=?, SDT=?, GioiTinh=? WHERE MaNV=?";
            
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setNString(1, name);
            statement.setNString(2, dob);
            statement.setNString(3, diaChi);
            statement.setNString(4, sdt);
            statement.setNString(5, gt);
            statement.setInt(6, maNV);

            int rowsInserted = statement.executeUpdate();
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
        
    }
    public static void deleteNhanVien(){
        System.out.println("-----------XOÁ NHÂN VIÊN THEO MÃ----------");
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã nhân viên cần xoá: ");
        int maNV = sc.nextInt();
        
        try {
            // connnect to database 'testdb'
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            // crate statement
            
            String sql = "select * from NHANVIEN WHERE MaNV=? ";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, maNV);
            ResultSet rs = statement.executeQuery();
            if(!rs.next()) {
                conn.close();
                System.out.println("Không tìm thấy nhân viên!");
                return;
            }
            
            String sql2 = "DELETE FROM NHANVIEN WHERE MaNV=? ";
            
            PreparedStatement statement2 = conn.prepareStatement(sql2);
            statement2.setInt(1, maNV);
            statement2.executeUpdate();
            System.out.println("Xoá thành công!");
            // close connection
            
        } catch (Exception ex) {
            System.out.println("Không tìm thấy nhân viên!");
            ex.printStackTrace();
            return;
        }
        
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
