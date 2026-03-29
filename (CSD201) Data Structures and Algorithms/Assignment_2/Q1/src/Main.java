// =========================================================
// Do NOT modify this file 
// =========================================================
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        MyList t = new MyList();

        printMenu();

        Scanner sca = new Scanner(System.in);
        int choice = sca.nextInt();
        sca.nextLine();

        switch (choice) {
            case 0:
                return;
            case 1:
                t.f1();
                System.out.println("Your output:");
                Lib.viewFile("f1.txt");
                break;
            case 2:
                t.f2();
                System.out.println("Your output:");
                Lib.viewFile("f2.txt");
                break;
            case 3:
                t.f3();
                System.out.println("Your output:");
                Lib.viewFile("f3.txt");
                break;
            case 4:
                t.f4();
                System.out.println("Your output:");
                Lib.viewFile("f4.txt");
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    static void printMenu() {
        System.out.println("1. Test f1 (load and display data)");
        System.out.println("2. Test f2 (insert before first multiple-of-5)");
        System.out.println("3. Test f3 (max owner length)");
        System.out.println("4. Test f4 (delete prime priority)");
        System.out.println("0. Exit");
        System.out.print("Your selection (0 -> 4): ");
    }
}
