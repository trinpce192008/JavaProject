
import controller.BookingController;
import controller.HomestayController;
import controller.TourController;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.Booking;
import model.Homestay;
import model.Tour;
import utils.Validator;
import view.View;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author TRI
 */
public class Main {

    private final BookingController BCtrl = new BookingController();
    private final HomestayController HCtrl = new HomestayController();
    private final View view = new View(new Scanner(System.in));
    private final TourController TCtrl = new TourController();
    private final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    private boolean dirty = false;

    public Main() {
        BCtrl.load();
        HCtrl.load();
        TCtrl.load();
    }

    public static void main(String[] args) {
        Main main = new Main();
        while (true) {
            try {
                int choice = main.view.showMainMenu();
                switch (choice) {
                    case 1:
                        main.addNewTour();
                        break;
                    case 2:
                        main.updateTourById();
                        break;
                    case 3:
                        main.listToursDepartureEarlierThanToday();
                        break;
                    case 4:
                        main.listTotalBookingAmountForToursLaterThanToday();
                        break;
                    case 5:
                        main.addNewBooking();
                        break;
                    case 6:
                        main.removeBookingById();
                        break;
                    case 7:
                        main.updateBookingById();
                        break;
                    case 8:
                        main.listBookingsByName();
                        break;
                    case 9:
                        main.statisticTotalTouristsBookedEachHomestay();
                        break;
                    case 10:
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

    private void addNewTour() {
        while (true) {
            view.showMessage("\n---- Add New Tour ----");

            String tourID;
            while (true) {
                tourID = view.readNonEmpty("Tour ID (T00000): ");
                if (!Validator.validTourID(tourID)) {
                    view.showMessage("Invalid Tour ID format.");
                    continue;
                }
                if (TCtrl.existsTourID(tourID)) {
                    view.showMessage("Tour ID already exists.");
                    continue;
                }
                break;
            }

            String tourName = view.readNonEmpty("Tour Name: ");
            String time = view.readNonEmpty("Time: ");
            int price = view.readPositiveInt("Price (positive integer): ");

            String homeID;
            Homestay home;
            while (true) {
                homeID = view.readNonEmpty("Home ID (HS0000): ");
                if (!Validator.validHomeID(homeID)) {
                    view.showMessage("Invalid Home ID format.");
                    continue;
                }
                home = HCtrl.findById(homeID);
                if (home == null) {
                    view.showMessage("Home ID does not exist in Homestay list.");
                    continue;
                }
                break;
            }

            Date dep = view.readDate("Departure date (dd/MM/yyyy): ");

            Date end;
            while (true) {
                end = view.readDate("End date (dd/MM/yyyy): ");
                if (end.before(dep)) {
                    view.showMessage("End_date must be greater than or equal to Departure_date.");
                    continue;
                }
                break;
            }

            int numTourist;
            while (true) {
                numTourist = view.readPositiveInt("Number_Tourist: ");
                if (numTourist > home.getMaximumCapacity()) {
                    view.showMessage("Number_Tourist must be less than or equal to Homestay Maximum Capacity (" + home.getMaximumCapacity() + ")");
                    continue;
                }
                break;
            }

            if (TCtrl.isScheduleOverlapped(homeID, dep, end, null)) {
                view.showMessage("Cannot add tour: schedule overlapped with another tour of the same homestay.");
                return;
            }

            dirty = TCtrl.add(new Tour(tourID, tourName, time, price, homeID, dep, end, numTourist, false));
            
            view.showMessage("=== Add Tour SUCCESS. ===");
            String cont = view.readOptional("Continue adding new Tours? (Y/N): ");
            if (!cont.equalsIgnoreCase("Y")) {
                break;
            }
        }

    }

    private void updateTourById() {
        while (true) {
            view.showMessage("\n---- Update Tour ----");

            String id = view.readNonEmpty("Enter Tour ID: ");
            Tour t = TCtrl.findById(id);
            if (t == null) {
                view.showMessage("This tour does not exist!");
                return;
            }

            view.showMessage("\n=== Press ENTER to skip a field. ===");

            String newName = view.readOptional("New Tour Name [" + t.getTourName() + "]: ");
            if (!newName.isEmpty()) {
                t.setTourName(newName);
            }

            String newTime = view.readOptional("New Time [" + t.getTime() + "]: ");
            if (!newTime.isEmpty()) {
                t.setTime(newTime);
            }

            String newPriceStr = view.readOptional("New Price [" + t.getPrice() + "]: ");
            if (!newPriceStr.isEmpty()) {
                int p = View.parseIntSafe(newPriceStr, -1);
                if (p <= 0) {
                    view.showMessage("Invalid Price. Update aborted.");
                    return;
                }
                t.setPrice(p);
            }

            String newHomeID = view.readOptional("New Home ID [" + t.getHomeID() + "]: ");
            Homestay newHome;
            if (!newHomeID.isEmpty()) {
                if (!Validator.validHomeID(newHomeID)) {
                    view.showMessage("Invalid Home ID format. Update aborted.");
                    return;
                }
                newHome = HCtrl.findById(newHomeID);
                if (newHome == null) {
                    view.showMessage("Home ID does not exist. Update aborted.");
                    return;
                }
            } else {
                newHomeID = t.getHomeID();
                newHome = HCtrl.findById(newHomeID);
            }
            Date newDep = t.getDepartureDate();
            Date newDepStr = view.readDateAllowEmpty("New Departure_date [" + df.format(t.getDepartureDate()) + "]: ", newDep);

            Date newEnd = t.getEndDate();
            Date newEndStr = view.readDateAllowEmpty("New End_date [" + df.format(t.getEndDate()) + "]: ", newEnd);

            if (newEnd.before(newDep)) {
                view.showMessage("End_date must be greater than or equal to Departure_date. Update aborted.");
                return;
            }

            String newNumStr = view.readOptional("number_Tourist [" + t.getNumberTourist() + "]: ");
            int newNum = t.getNumberTourist();
            if (!newNumStr.isEmpty()) {
                int tmp = View.parseIntSafe(newNumStr, -1);
                if (tmp <= 0) {
                    view.showMessage("Invalid Number_Tourist. Update aborted.");
                    return;
                }
                if (newHome != null && tmp > newHome.getMaximumCapacity()) {
                    view.showMessage("Number_Tourist must be less than or equal to MaxCapacity (" + newHome.getMaximumCapacity() + "). Update aborted.");
                    return;
                }
                newNum = tmp;
            } else {
                if (newHome != null && newNum > newHome.getMaximumCapacity()) {
                    view.showMessage("Current Number_Tourist exceeds new homestay capacity. Update aborted.");
                    return;
                }
            }

            // booking field update
            String newBookingStr = view.readOptional("booking [" + (t.isBooking() ? "TRUE" : "FALSE") + "] (TRUE/FALSE): ");
            boolean newBooking = t.isBooking();
            if (!newBookingStr.isEmpty()) {
                if (!newBookingStr.equalsIgnoreCase("TRUE") && !newBookingStr.equalsIgnoreCase("FALSE")) {
                    view.showMessage("Invalid booking value. Update aborted.");
                    return;
                }
                newBooking = newBookingStr.equalsIgnoreCase("TRUE");

                // If user tries set FALSE while there are bookings -> disallow
                if (!newBooking && BCtrl.hasBookingForTour(t.getTourID())) {
                    view.showMessage("Cannot set booking = FALSE because bookings exist for this tour.");
                    return;
                }
            }

            // overlap check with new schedule & new home
            if (TCtrl.isScheduleOverlapped(newHomeID, newDep, newEnd, t.getTourID())) {
                view.showMessage("Cannot update tour: schedule overlapped with another tour of the same homestay.");
                return;
            }

            // apply updates
            t.setHomeID(newHomeID);
            t.setDepartureDate(newDep);
            t.setEndDate(newEnd);
            t.setNumberTourist(newNum);
            t.setBooking(newBooking);

            dirty = true;
            view.showMessage("Update Tour SUCCESS.");
            String cont = view.readOptional("Continue updating new Tours? (Y/N): ");
            if (!cont.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }

    private void listToursDepartureEarlierThanToday() {
        view.showMessage("\n---- Tours with departure_date earlier than today ----");
        Date today = new Date();

        List<Tour> filtered = new ArrayList<>();
        for (Tour t : TCtrl.getAll()) {
            if (t.getDepartureDate().before(today)) {
                filtered.add(t);
            }
        }

        if (filtered.isEmpty()) {
            view.showMessage("No tours found.");
            return;
        }

        view.printTourTable(filtered);
    }

    private void listTotalBookingAmountForToursLaterThanToday() {
        view.showMessage("\n---- Total Booking Amount (Departure_date later than today) ----");
        Date today = new Date();

        List<Tour> filtered = new ArrayList<>();

        for (Tour t : TCtrl.getAll()) {
            if (t.getDepartureDate().after(today)) {
                filtered.add(t);
            }
        }

        filtered.sort((a, b) -> Integer.compare(b.totalAmount(), a.totalAmount()));

        view.showTourAmountList(filtered);

    }

    private void addNewBooking() {
        while (true) {
            view.showMessage("\n---- Add New Booking ----");

            String bookingID;
            while (true) {
                bookingID = view.readNonEmpty("Booking ID (B00000): ");
                if (!Validator.validBookingID(bookingID)) {
                    view.showMessage("Invalid Booking ID format.");
                    continue;
                }
                if (BCtrl.existsBookingID(bookingID)) {
                    view.showMessage("Booking ID already exists.");
                    continue;
                }
                break;
            }

            String fullName = view.readNonEmpty("Full Name: ");

            String tourID;
            Tour tour;
            while (true) {
                tourID = view.readNonEmpty("TourID (must exist): ");
                tour = TCtrl.findById(tourID);
                if (tour == null) {
                    view.showMessage("TourID does not exist.");
                    continue;
                }
                break;
            }

            Date bookingDate;
            while (true) {
                bookingDate = view.readDate("Booking_date (dd/MM/yyyy): ");
                if (bookingDate == null) {
                    continue;
                }
                if (!bookingDate.before(tour.getDepartureDate())) {
                    view.showMessage("Booking_date must be less than the Departure_date of the tour.");
                    continue;
                }
                break;
            }

            String phone;
            while (true) {
                phone = view.readNonEmpty("Phone (10 digits): ");
                if (!Validator.isViettelOrVNPT(phone)) {
                    view.showMessage("Invalid! Phone must be 10 digits and belong to Viettel/VNPT.");
                    continue;
                }
                break;
            }

            dirty = BCtrl.add(new Booking(bookingID, fullName, tourID, bookingDate, phone));

            // Update tour booking flag
            tour.setBooking(true);
            view.showMessage("=== Add Booking SUCCESS. ===");
            String cont = view.readOptional("Continue adding new Booking? (Y/N): ");
            if (!cont.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }

    private void removeBookingById() {
        while (true) {
            view.showMessage("\n---- Remove Booking ----");
            String id = view.readNonEmpty("Enter Booking ID: ");
            Booking b = BCtrl.findById(id);
            if (b == null) {
                view.showMessage("This booking does not exist!");
                return;
            }

            String tourID = b.getTourID();
            BCtrl.remove(b);
            dirty = true;

            // If no more bookings for that tour -> set booking false
            Tour t = TCtrl.findById(tourID);
            if (t != null) {
                if (!BCtrl.hasBookingForTour(tourID)) {
                    t.setBooking(false);
                }
            }

            view.showMessage("=== Remove Booking SUCCESS. ===");
            String cont = view.readOptional("Continue removing new Booking? (Y/N): ");
            if (!cont.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }

    private void updateBookingById() {
        while (true) {
            view.showMessage("\n---- Update Booking ----");
            String id = view.readNonEmpty("Enter Booking ID: ");
            Booking b = BCtrl.findById(id);
            if (b == null) {
                view.showMessage("This Booking does not exist!");
                return;
            }

            view.showMessage("=== Press ENTER to skip a field. ===");

            String newName = view.readOptional("Full Name [" + b.getFullName() + "]: ");
            if (!newName.isEmpty()) {
                b.setFullName(newName);
            }

            String newTourID = view.readOptional("Tour ID [" + b.getTourID() + "]: ");
            Tour newTour;
            if (!newTourID.isEmpty()) {
                newTour = TCtrl.findById(newTourID);
                if (newTour == null) {
                    view.showMessage("Tour ID does not exist. Update aborted.");
                    return;
                }
            } else {
                newTourID = b.getTourID();
                newTour = TCtrl.findById(newTourID);
            }
            Date oldBookingDate = b.getBookingDate();
            Date newBookingDateStr = view.readDateAllowEmpty("Booking_date [" + df.format(b.getBookingDate()) + "]: ", oldBookingDate);

            if (newTour != null && !newBookingDateStr.before(newTour.getDepartureDate())) {
                view.showMessage("Booking_date must be less than Departure_date of the selected tour. Update aborted.");
                return;
            }

            String newPhone = view.readOptional("Phone [" + b.getPhone() + "]: ");
            if (!newPhone.isEmpty()) {
                if (!Validator.isViettelOrVNPT(newPhone)) {
                    view.showMessage("Invalid phone. Update aborted.");
                    return;
                }
                b.setPhone(newPhone);
            }

            // Apply tour change at last (need update old/new booking flags)
            String oldTourID = b.getTourID();
            b.setTourID(newTourID);
            b.setBookingDate(newBookingDateStr);

            dirty = true;
            // Sync booking flags for old & new tours
            Tour oldT = TCtrl.findById(oldTourID);
            if (oldT != null && !BCtrl.hasBookingForTour(oldTourID)) {
                oldT.setBooking(false);
            }

            Tour nt = TCtrl.findById(newTourID);
            if (nt != null) {
                nt.setBooking(true);
            }

            view.showMessage("=== Update Booking SUCCESS. ===");
            String cont = view.readOptional("Continue updating new Booking by ID? (Y/N): ");
            if (!cont.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }

    private void listBookingsByName() {
        view.showMessage("\n---- Search Bookings by fullName ----");
        String key = view.readNonEmpty("Enter Full Name or partial: ").toLowerCase();

        List<Booking> matched = new ArrayList<>();
        for (Booking b : BCtrl.getAll()) {
            if (b.getFullName().toLowerCase().contains(key)) {
                matched.add(b);
            }
        }

        if (matched.isEmpty()) {
            view.showMessage("=== No matching bookings found. ===");
            return;
        }

        view.printBookingTable(matched);
    }

    private void statisticTotalTouristsBookedEachHomestay() {
        view.showMessage("\n---- Statistics: Total number of tourists who booked each homestay ----");

        // homeID -> total tourists (sum of numberTourist of booked tours)
        Map<String, Integer> map = new LinkedHashMap<>();
        for (Homestay h : HCtrl.getAll()) {
            map.put(h.getHomeID(), 0);
        }

        for (Tour t : TCtrl.getAll()) {
            if (t.isBooking()) {
                map.put(t.getHomeID(), map.getOrDefault(t.getHomeID(), 0) + t.getNumberTourist());
            }
        }
        view.showStatisticTotalTourists(HCtrl.getAll(), map);
    }

    //  9. exit
    private void exit() {
        if (!dirty) {
            view.showMessage("=== No changes to save. Bye! ===");
        } else {
            String cont = view.readOptional("Data has been modified. Save changes? (Y/N): ");

            if (cont.equalsIgnoreCase("Y")) {

                if (TCtrl.save() && BCtrl.save()) {
                    view.showMessage("=== Saved all successfully. Bye! ===");
                } else {
                    view.showMessage("=== Save failed: Changes discarded. Bye! ===");
                }
            }

        }
        view.showMessage("Exiting program...");
        System.exit(0);
    }
}
