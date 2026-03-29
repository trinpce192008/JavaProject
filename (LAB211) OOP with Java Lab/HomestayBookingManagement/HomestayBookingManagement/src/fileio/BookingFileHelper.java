/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fileio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Booking;
import utils.FileUtils;
import utils.Validator;
import view.View;

/**
 *
 * @author TRI
 */
public class BookingFileHelper implements IFileReadWrite<Booking> {

    private final String FILE_NAME = "src\\fileio\\Booking.txt";

    @Override
    public List<Booking> read() throws Exception {

        List<Booking> bookings = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        List<String> lines = FileUtils.readAllLines(FILE_NAME);

        for (String line : lines) {
            String[] p = line.split("-");
            if (p.length < 5) {
                continue;
            }

            String bookingID = p[0].replace("\uFEFF", "").trim();
            String fullName = p[1].trim();
            String tourID = p[2].trim();
            Date bookingDate = View.parseDate(p[3].trim());
            String phone = p[4].trim();

            if (!Validator.validBookingID(bookingID) || fullName.isEmpty()
                    || !Validator.validTourID(tourID)
                    || bookingDate == null
                    || !Validator.isViettelOrVNPT(phone)) {
                continue;
            }

            String key = bookingID.toUpperCase();
            if (seen.contains(key)) {
                continue;
            }
            seen.add(key);

            bookings.add(new Booking(bookingID, fullName, tourID, bookingDate, phone));
        }
        return bookings;
    }

    @Override
    public boolean write(List<Booking> list) throws Exception {

        List<String> lines = new ArrayList<>();

        for (Booking b : list) {

            String line = b.getBookingID() + "-"
                    + b.getFullName() + "-"
                    + b.getTourID() + "-"
                    + View.formatDate(b.getBookingDate()) + "-"
                    + b.getPhone();

            lines.add(line);
        }

        try {
            FileUtils.writeAllLines(FILE_NAME, lines);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
