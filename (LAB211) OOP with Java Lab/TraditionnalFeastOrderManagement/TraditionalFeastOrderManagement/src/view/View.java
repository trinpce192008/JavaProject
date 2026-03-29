package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.Customer;
import model.Order;
import model.SetMenu;

public class View {

    private final Scanner sc;
    private final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public View(Scanner sc) {
        this.sc = sc;
    }

    public int showMainMenu() {
        System.out.println("                                              ");
        System.out.println("===== Traditional Feast Order Management =====");
        String[] options = {
            "Register Customers",
            "Update Customer information",
            "Search customer by name",
            "Display feast menu",
            "Place a feast order",
            "Update order information",
            "Save data to file",
            "Display customers/orders list",
            "Exit"
        };
        Menu.showMenu(options);
        return Integer.parseInt(sc.nextLine());
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public int showSaveMenu() {
        String[] options = {
            "Save customers data",
            "Save orders data",
            "Save all",
            "Back"
        };
        Menu.showMenu(options);
        return Integer.parseInt(sc.nextLine());
    }

    private String money(long v) {
        return String.format("%,d", v);
    }

    private static String formatName(String fullName) {
        if (fullName == null) {
            return "";
        }
        String s = fullName.trim().replaceAll("\\s+", " ");
        int lastSpace = s.lastIndexOf(' ');
        if (lastSpace < 0) {
            return s; // tên 1 chữ
        }
        String firstName = s.substring(lastSpace + 1); // TÊN
        String rest = s.substring(0, lastSpace);       // HỌ + ĐỆM
        return firstName + ", " + rest;
    }

    public void showCustomers(List<Customer> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Does not have any customer information.");
            return;
        }
        String line = "--------------------------------------------------------------------------";
        System.out.println(line);
        System.out.println("Code   | Customer Name              | Phone      | Email");
        System.out.println(line);
        for (Customer c : list) {
            System.out.printf("%-6s | %-26s | %-10s | %s%n",
                    c.getId(), formatName(c.getName()), c.getPhone(), c.getEmail());
        }
        System.out.println(line);
    }

    public void showOrders(List<Order> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Does not have any order information.");
            return;
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-8s|%12s|%-14s|%-9s|%10s|%8s|%14s%n",
                "ID", "Event date", "Customer ID", "SetMenu", "Price", "Tables", "Cost");
        System.out.println("--------------------------------------------------------------------------------");
        for (Order o : list) {
            System.out.printf("%-8s|%12s|%-14s|%-9s|%10s|%8d|%14s%n",
                    o.getOrderCode(),
                    df.format(o.getEventDate()),
                    o.getCustomerId(),
                    o.getMenuId(),
                    money(o.getPrice()),
                    o.getNumOfTables(),
                    money(o.getTotalCost()));
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    public void showSetMenus(List<SetMenu> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Cannot read data from \"feastMenu.csv\". Please check it.");
            return;
        }
        System.out.println("--------------------------------------------------");
        System.out.println("List of Set Menus for ordering party:");
        System.out.println("--------------------------------------------------");
        for (SetMenu m : list) {
            System.out.println(m);
            System.out.println("--------------------------------------------------");
        }
    }

    public int showDisplaySubMenu() {
        System.out.println("----- Display List -----");
        String[] options = {"Customers (sorted by name)", "Orders (sorted by event date)", "Back"};
        Menu.showMenu(options);
        return readInt("Choose [1..3]: ", 1, 3);
    }

    public void showOrderReceipt(String orderId, Customer c, SetMenu m, Date eventDate, int tables, long price) {
        String line = "--------------------------------------------------------------------------";
        System.out.println(line);
        System.out.println("Customer order information [Order ID: " + orderId + "]");
        System.out.println(line);

        System.out.printf("Code           : %s%n", c.getId());
        System.out.printf("Customer name  : %s%n", c.getName());
        System.out.printf("Phone number   : %s%n", c.getPhone());
        System.out.printf("Email          : %s%n", c.getEmail());

        System.out.println(line);

        System.out.printf("Code of Set Menu: %s%n", m.getMenuId());
        System.out.printf("Set menu name  : %s%n", m.getMenuName());
        System.out.printf("Event date     : %s%n", df.format(eventDate));
        System.out.printf("Number of tables: %d%n", tables);
        System.out.printf("Price          : %s Vnd%n", String.format("%,d", price));
        System.out.println("Ingredients:");
        System.out.println(m.getIngredients());

        System.out.println(line);
        System.out.printf("Total cost     : %s Vnd%n", String.format("%,d", price * (long) tables));
        System.out.println(line);
    }

    public int readInt(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int val = Integer.parseInt(sc.nextLine().trim());
                if (val < min || val > max) {
                    throw new Exception();
                }
                return val;
            } catch (Exception e) {
                System.out.println("Invalid number, try again.");
            }
        }
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int val = Integer.parseInt(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, try again.");
            }
        }
    }

    public int readInt(String prompt, int oldValue) {
        while (true) {
            try {
                int val;
                System.out.print(prompt);
                String line = sc.nextLine().trim();
                if (line.trim().isEmpty()) {
                    val = oldValue;
                } else {
                    val = Integer.parseInt(line.trim());
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, try again.");
            }
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double val = Double.parseDouble(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, try again.");
            }
        }
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    public String readStringAllowEmpty(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    public Date readFutureDateAllowEmpty(String prompt, Date oldValue) {
        while (true) {
            try {
                String s = readString(prompt);
                if (s.isEmpty()) {
                    return oldValue;
                }
                Date d = df.parse(s);
                if (!d.after(new Date())) {
                    System.out.println("Event date must be after current date.");
                    continue;
                }
                return d;
            } catch (ParseException e) {
                System.out.println("Invalid date. Format must be dd/MM/yyyy.");
            }
        }
    }

    public Date readFutureDate(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                Date d = df.parse(sc.nextLine().trim());
                if (!d.after(new Date())) {
                    System.out.println("The preferred event date must be in the future.");
                    continue;
                }
                return d;
            } catch (ParseException e) {
                System.out.println("Invalid date, please use dd/MM/yyyy.");
            }
        }
    }

}
