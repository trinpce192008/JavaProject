
import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author TRI
 */
public class Part2 {

    public static void main(String[] args) {
        boolean cont;
        do {
            try {
                String mssv;
                Scanner nl = new Scanner(System.in);
                System.out.print("Input the string: ");
                mssv = nl.nextLine();
                if (mssv != null && mssv.matches("^SE\\d{3}$")) {
                    System.out.println("The string is " + mssv);
                } else {
                    throw new Exception();
                }
                cont = false;
            } catch (Exception e) {
                System.out.println("The string is invalid");
                cont = true;
            }
        } while (cont);
    }
}
