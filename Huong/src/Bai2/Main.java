
package Bai2;

import java.util.Scanner;

/**
 *
 * @author Huong
 */
public class Main {
    static void showMenu() {
        System.out.println("");
        System.out.println("--------------MENU LỰA CHỌN--------------");
        System.out.println("NHANVIEN");
        System.out.println("------1. Xem     2. Thêm       3. Sửa       4. Xoá");
        
        System.out.println("SANPHAM");
        System.out.println("------5. Xem     6. Thêm       7. Sửa       8. Xoá");
       
        
        System.out.println("0. Exit");
        System.out.println("Choose: ");
    }
    
    public static void main(String[] args) {
        NhanVien nv = new NhanVien();
        SanPham sp = new SanPham();
        Scanner scan = new Scanner(System.in);
        int choose;
        
        do {
            showMenu();
            choose = Integer.parseInt(scan.nextLine());
            
            switch(choose) {
                case 1:
                    nv.showNhanVien();
                    break;
                case 2:
                    nv.insertNhanVien();
                    break;
                case 3:
                    nv.updateNhanVien();
                    break;
                case 4:
                    nv.deleteNhanVien();
                    break;
                case 5:
                    sp.showSanPham();
                    break;
                case 6:
                    sp.insertSanPham();
                    break;
                case 7:
                    sp.updateSanPham();
                    break;
                case 8:
                    sp.deleteSanPham();
                    break;   
                
                case 0:
                    System.out.println("Exit!!!");
                    break;
                default:
                    System.out.println("Failed!!!");
                    break;
            }
        } while(choose != 0);
        
    }
    

}
