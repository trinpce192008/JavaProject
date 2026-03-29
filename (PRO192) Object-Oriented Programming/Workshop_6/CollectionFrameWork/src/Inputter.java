/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author TRI
 */
import java.util.Scanner;
public class Inputter {
	public static Scanner scanner = new Scanner(System.in);
	
	
	public static int inputInt(String msg,int min, int max) {
		if (min>max) {
			int t = min;
			min  = max;
			max = t;
		}
		int data;
		do {
			System.out.println(msg);
			data = Integer.parseInt(scanner.nextLine());
		} while (data<min || data >max);
		return data;
	}
	
	
	public static String intputStr(String msgString) {
		System.out.println(msgString);
		String dataString = scanner.nextLine().trim();
		return dataString;
	}
	
	public static String inputNonBlankStr(String msgString) {
		String dataString;
		do {
			System.out.println(msgString);
			
			dataString = scanner.nextLine().trim();
		} while (dataString.length()==0);
		return dataString;
	}
	public static String inputPattern(String msgString , String patternString) {
		String dataString;
		do {
			System.out.println(msgString);
			dataString = scanner.nextLine().trim();
		} while (!dataString.matches(patternString));
		return dataString;
	}
	
}
