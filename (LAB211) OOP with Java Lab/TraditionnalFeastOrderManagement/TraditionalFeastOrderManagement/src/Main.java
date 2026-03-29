
import controller.CustomerController;
import controller.OrderController;
import controller.SetMenuController;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.Customer;
import model.Order;
import model.SetMenu;
import utils.Validator;
import view.View;

public class Main {

    private final CustomerController cusCon = new CustomerController();
    private final OrderController odCon = new OrderController();
    private final View view = new View(new Scanner(System.in));
    private final SetMenuController menuCtrl = new SetMenuController();

    public Main() {
        cusCon.load();
        odCon.load();
        menuCtrl.load();
    }

    public static void main(String[] args) {
        Main main = new Main();
        while (true) {
            try {
                int choice = main.view.showMainMenu();
                switch (choice) {
                    case 1:
                        main.newRegistration();
                        break;
                    case 2:
                        main.updateCustomer();
                        break;
                    case 3:
                        main.searchCustomerByName();
                        break;
                    case 4:
                        main.displayFeastMenu();
                        break;
                    case 5:
                        main.placeOrder();
                        break;
                    case 6:
                        main.updateOrder();
                        break;
                    case 7:
                        main.saveData();
                        break;
                    case 8:
                        main.displayLists();
                        break;
                    case 9:
                        main.exit();
                        break;
                    default:
                        main.view.showMessage("This function is not available.");
                }
            } catch (Exception e) {
                main.view.showMessage("Invalid input. Please try again.");
            }
        }
    }

    //1. New Customer
    private void newRegistration() {
        while (true) {
            String id;
            while (true) {
                id = view.readString("Enter Customer ID: ");
                if (Validator.validCustomer(id)) {
                    if (cusCon.findById(id) == null) {
                        break;
                    }
                    view.showMessage("ID already exists!");
                } else {
                    view.showMessage("Invalid ID format (C1234)!");
                }
            }

            String name = view.readString("Enter Name: ");
            while (!Validator.validName(name)) {
                name = view.readString("Invalid! Name must be 2-25 chars: ");
            }

            String phone = view.readString("Enter Phone: ");
            while (!Validator.isViettelOrVNPT(phone)) {
                phone = view.readString("Invalid! Phone must be 10 digits and belong to Viettel/VNPT: ");
            }

            String email = view.readString("Enter Email: ");
            while (!Validator.validEmail(email)) {
                email = view.readString("Invalid email format!");
            }
            Customer c = new Customer(id, name, phone, email);
            cusCon.add(c);
            view.showMessage("Customer registered successfully.");

            String cont = view.readString("Continue registering new customers? (Y/N): ");
            if (!cont.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }

    //2. Update Customer
    private void updateCustomer() {
        while (true) {
            String id = view.readString("Customer code to update: ").toUpperCase();
            Customer old = cusCon.findById(id);
            if (old == null) {
                System.out.println("This customer does not exist.");
                return;
            }

            System.out.println("Leave blank to keep old value.");

            String name = view.readString("New name: ");
            if (name.isEmpty()) {
                name = old.getName();
            } else if (!Validator.validName(name)) {
                System.out.println("Invalid name. Update cancelled.");
                return;
            }

            String phone = view.readString("New phone: ");
            if (phone.isEmpty()) {
                phone = old.getPhone();
            } else {
                if (!Validator.validPhone(phone) || !Validator.isViettelOrVNPT(phone)) {
                    System.out.println("Invalid phone. Update cancelled.");
                    return;
                }
            }

            String email = view.readString("New email: ");
            if (email.isEmpty()) {
                email = old.getEmail();
            } else if (!Validator.validEmail(email)) {
                System.out.println("Invalid email. Update cancelled.");
                return;
            }

            boolean ok = cusCon.update(new Customer(id, name, phone, email));
            System.out.println(ok ? "Update successful." : "Update failed.");

            String cont = view.readString("Continue with another update? (Y/N): ");
            if (!cont.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }

//    3. search customer by name
    private void searchCustomerByName() {
        String key = view.readString("Enter name keyword: ");
        List<Customer> rs = cusCon.searchByName(key);
        if (rs.isEmpty()) {
            System.out.println("No one matches the search criteria!");
            return;
        }
        System.out.println("Matching Customers: " + key);
        view.showCustomers(rs);
    }

//  4. display feast menu
    private void displayFeastMenu() {
        view.showSetMenus(menuCtrl.getSortedByPrice());
    }
    
//  5. place oder
    private void placeOrder() {
        while (true) {
            String cusCode = view.readString("Customer code: ");
            if (!Validator.validCustomer(cusCode) || cusCon.findById(cusCode) == null) {
                view.showMessage("Invalid customer code.");
                continue;
            }

            String menuCode = view.readString("Code of Set Menu: ");
            if (!Validator.validMenuId(menuCode) || menuCtrl.getById(menuCode) == null) {
                view.showMessage("Invalid menu code.");
                continue;
            }

            String t = view.readString("Number of tables: ");
            if (!Validator.validPositiveInt(t)) {
                view.showMessage("Invalid number of tables.");
                continue;
            }
            int tables = Integer.parseInt(t);

            Date eventDate = view.readFutureDate("Preferred event date (dd/MM/yyyy): ");

            Customer c = cusCon.findById(cusCode);
            SetMenu m = menuCtrl.getById(menuCode);

            String newId = odCon.generateNextOrderId();
            Order o = new Order(newId, cusCode, menuCode, (long) m.getPrice(), tables, eventDate);

            if (odCon.isDuplicate(o)) {
                view.showMessage("Dupplicate data !");
            } else {
                boolean ok = odCon.placeOrder(o);
                if (ok) {
                    view.showOrderReceipt(newId, c, m, eventDate, tables, (long) m.getPrice());
                } else {
                    view.showMessage("Place order failed.");
                }
            }

            String cont = view.readString("Continue place another order? (Y/N): ");
            if (!cont.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }

//  6. update oder
    private void updateOrder() {
        while (true) {
            String orderId = view.readString("Order ID to update: ");
            Order old = odCon.findById(orderId);
            if (old == null) {
                view.showMessage("This Order does not exist.");
                return;
            }
            if (old.getEventDate() == null || !old.getEventDate().after(new Date())) {
                view.showMessage("Cannot update an order whose event date occurred before current date.");
                return;
            }

            String newMenuId = view.readString("New menu code (Enter to keep): ");
            if (newMenuId.isEmpty()) {
                newMenuId = old.getMenuId();
            } else {
                if (!Validator.validMenuId(newMenuId) || menuCtrl.getById(newMenuId) == null) {
                    view.showMessage("Invalid menu code / menu not found.");
                    return;
                }
            }

            int tables = old.getNumOfTables();
            String t = view.readString("New tables (Enter to keep): ");
            if (!t.isEmpty()) {
                if (!Validator.validPositiveInt(t)) {
                    view.showMessage("Invalid tables.");
                    return;
                }
                tables = Integer.parseInt(t);
            }

            Date newDate = view.readFutureDateAllowEmpty("New event date dd/MM/yyyy (Enter to keep): ", old.getEventDate());

            SetMenu m = menuCtrl.getById(newMenuId);
            long price = (long) ((m != null) ? m.getPrice() : old.getPrice());

            Order updated = new Order(old.getOrderCode(), old.getCustomerId(), newMenuId, price, tables, newDate);

            if (odCon.updateOrder(updated)) {
                view.showMessage("Update successful.");
            } else {
                view.showMessage("Update failed (maybe duplicate after update).");
            }
            String cont = view.readString("Continue with another update? (Y/N): ");
            if (!cont.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }
    
//  7. save customer, order
    private void saveData() {
        while (true) {
            int c = view.showSaveMenu();
            if (c == 4) {
                return;
            }

            if (c == 1 || c == 3) {
                boolean ok = cusCon.save();
                if (ok) {
                    view.showMessage("Customer data has been successfully saved to \"customers.dat\".");
                } else {
                    view.showMessage("Save customers failed.");
                }
            }
            if (c == 2 || c == 3) {
                boolean ok = odCon.save();
                if (ok) {
                    view.showMessage("Order data has been successfully saved to \"feast_order_service.dat\".");
                } else {
                    view.showMessage("Save orders failed.");
                }
            }
        }
    }

//  8. Display customer, order
    private void displayLists() {
        while (true) {
            int c = view.showDisplaySubMenu();
            if (c == 3) {
                return;
            }

            if (c == 1) {
                view.showMessage("\nCustomers information:");
                view.showCustomers(cusCon.getAllSortedByName());
            } else if (c == 2) {
                view.showMessage("\nOrders information:");
                view.showOrders(odCon.getAllSortedByEventDate());
            }
        }
    }
    
//  9. exit
    private void exit() {
        view.showMessage("Exiting program...");
        System.exit(0);
    }
}
