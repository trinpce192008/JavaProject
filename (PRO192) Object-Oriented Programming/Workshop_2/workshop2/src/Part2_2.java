
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author TRI
 */
public class Part2_2 {

    public String inputString() throws Exception {
        String mssv;
        Scanner nl = new Scanner(System.in);
        System.out.print("Input the string: ");
        mssv = nl.nextLine();
        if (mssv != null && mssv.matches("^SE\\d{3}$")) {
            return mssv;
        } else {
            throw new Exception();
        }
    }

    public static void main(String[] args) {
        Part2_2 test = new Part2_2();
        boolean cont;
        do {
            try {
                String nl = test.inputString();
                System.out.println("The string is" + nl);
                cont = false;
            } catch (Exception e) {
                System.out.println("The string is invalid");
                cont = true;
            }
        } while (cont);
    }
}
