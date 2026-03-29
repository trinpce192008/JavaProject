
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
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();

        System.out.print("Enter number of columns: ");
        int cols = sc.nextInt();

        int[][] m = new int[rows][cols];
        int sum = 0;

        System.out.println("Enter the matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("m[" + i + "][" + j + "]=");
                m[i][j] = sc.nextInt();
            }
        }

        System.out.println("Matrix inputted:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.format("%3d", m[i][j]);
                sum += m[i][j];
            }
            System.out.println();
        }

        System.out.println("Sum: " + sum);
        System.out.println("Average: " + (float) sum / (rows * cols));
    }
}
