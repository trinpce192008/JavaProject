
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
        Scanner sc = new Scanner(System.in);

        System.out.print("Input the number 1: ");
        float a = sc.nextFloat();

        System.out.print("Input the number 2: ");
        float b = sc.nextFloat();

        System.out.print("Input the operator (+ - * /): ");
        char op = sc.next().charAt(0);

        float result;
        switch (op) {
            case '+': result = a + b; break;
            case '-': result = a - b; break;
            case '*': result = a * b; break;
            case '/':
                if (b == 0) { System.out.println("Error: divide by zero"); return; }
                result = a / b; break;
            default:
                System.out.println("Invalid operator!");
                return;
        }
        System.out.println("The result of " + a + op + b + " = " + result);
    }
}
