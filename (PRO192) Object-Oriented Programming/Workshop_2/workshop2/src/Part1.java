
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author TRI
 */
public class Part1 {

    public static void main(String[] args) {
        boolean cont;
        do {
            try {
                int n;
                System.out.print("Enter the number: ");
                Scanner nl = new Scanner(System.in);
                n = nl.nextInt();
                if (n < 1) {
                    throw new Exception();
                }
                System.out.println("The number is " + n);
                cont = false;
            } catch (Exception e) {
                System.out.println("The number is invalid");
                cont = true;
            }
        } while (cont);
    }
}
