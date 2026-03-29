
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author TRI
 */
public class Part3 {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);

        System.out.print("How many students? ");
        int n = Integer.parseInt(sc.nextLine()); // dùng nextLine để tránh nuốt dòng

        String[] names = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Name " + (i + 1) + ": ");
            names[i] = sc.nextLine().toUpperCase();
        }

        System.out.println("Student list (UPPERCASE):");
        for (String s : names) {
            System.out.println(s);
        }
    }
}
