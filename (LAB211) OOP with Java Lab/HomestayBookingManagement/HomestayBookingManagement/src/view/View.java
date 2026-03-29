package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Booking;
import model.Homestay;
import model.Tour;

public class View {

    private final Scanner sc;
    private static final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public View(Scanner sc) {
        this.sc = sc;
    }

    public int showMainMenu() {
        System.out.println("                                              ");
        System.out.println("\n===== HOMESTAY BOOKING =====");
        String[] options = {
            "Add a new Tour",
            "Update a Tour by ID",
            "List the Tours with departure dates earlier than the current date",
            "List the total Booking amount for tours with departure dates later than the current date",
            "Add a new Booking",
            "Remove a Booking by bookingID",
            "Update a Booking by bookingID",
            "List all Booking by the fullName or a partial fullName",
            "Statistics on the total number of tourists who have booked homestays",
            "Exit"
        };
        Menu.showMenu(options);
        return Integer.parseInt(sc.nextLine());
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) {
                return s;
            }
            System.out.println("Input cannot be empty.");
        }
    }

    public String readOptional(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim(); // may be empty
    }

    public int readPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            int v = parseIntSafe(s, -1);
            if (v > 0) {
                return v;
            }
            System.out.println("Invalid number. Must be a positive integer.");
        }
    }

    public Date readDate(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                Date d = df.parse(sc.nextLine().trim());
                return d;
            } catch (ParseException e) {
                System.out.println("Invalid date, please use dd/MM/yyyy.");
            }
        }
    }
    
    public Date readDateAllowEmpty(String prompt, Date oldValue) {
        while (true) {
            try {
                String s = readOptional(prompt);
                if (s.isEmpty()) {
                    return oldValue;
                }
                Date d = df.parse(s);
                return d;
            } catch (ParseException e) {
                System.out.println("Invalid date. Format must be dd/MM/yyyy.");
            }
        }
    }
        
    public static String formatDate(Date date) {
        return df.format(date);
    }
    
    public static Date parseDate(String date) {
        try {
            Date d = df.parse(date);
            return d;
        } catch (ParseException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static int parseIntSafe(String s, int fallback) {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    public void printTourTable(List<Tour> list) {
        System.out.printf("%-8s | %-20s | %-20s | %-6s | %-8s | %-10s | %-10s | %-6s | %-6s%n",
                "TourID", "TourName", "Time", "Price", "HomeID", "Depart", "End", "Num", "Booked");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        for (Tour t : list) {
            System.out.printf("%-8s | %-20s | %-20s | %-6d | %-8s | %-10s | %-10s | %-6d | %-6s%n",
                    t.getTourID(),
                    t.getTourName(),
                    t.getTime(),
                    t.getPrice(),
                    t.getHomeID(),
                    df.format(t.getDepartureDate()),
                    df.format(t.getEndDate()),
                    t.getNumberTourist(),
                    t.isBooking() ? "TRUE" : "FALSE"
            );
        }
    }

    public void printBookingTable(List<Booking> list) {
        System.out.printf("%-8s | %-25s | %-8s | %-10s | %-10s%n",
                "BookID", "FullName", "TourID", "BookDate", "Phone");
        System.out.println("--------------------------------------------------------------------------");
        for (Booking b : list) {
            System.out.printf("%-8s | %-25s | %-8s | %-10s | %-10s%n",
                    b.getBookingID(),
                    b.getFullName(),
                    b.getTourID(),
                    df.format(b.getBookingDate()),
                    b.getPhone()
            );
        }
    }

    public void showTourAmountList(List<Tour> list) {
        if (list.isEmpty()) {
            System.out.println("No tours found.");
            return;
        }

        System.out.printf("%-8s | %-20s | %-8s | %-6s | %-8s | %-10s | %-10s | %-15s | %-6s | %-12s%n",
                "TourID", "TourName", "Time", "Price", "HomeID", "Depart", "End", "Number_Tourist", "Booked", "TotalAmount");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        for (Tour t : list) {
            System.out.printf("%-8s | %-20s | %-8s | %-6d | %-8s | %-10s | %-10s | %-15d | %-6s | %-12d%n",
                    t.getTourID(),
                    t.getTourName(),
                    t.getTime(),
                    t.getPrice(),
                    t.getHomeID(),
                    df.format(t.getDepartureDate()),
                    df.format(t.getEndDate()),
                    t.getNumberTourist(),
                    t.isBooking() ? "TRUE" : "FALSE",
                    t.totalAmount()
            );
        }
    }

    public void showStatisticTotalTourists(List<Homestay> homes,
            Map<String, Integer> map) {

        System.out.printf("%-30s | %-12s%n", "HomeName", "Number_Tourist");
        System.out.println("------------------------------------------------");

        for (Homestay h : homes) {

            int total = map.getOrDefault(h.getHomeID(), 0);

            System.out.printf("%-30s | %-12d%n",
                    h.getHomeName(),
                    total);
        }
    }



}
