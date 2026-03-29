/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import fileio.BookingFileHelper;
import fileio.IFileReadWrite;
import java.util.ArrayList;
import java.util.List;
import model.Booking;

/**
 *
 * @author TRI
 */
public class BookingController {

    private final IFileReadWrite<Booking> fileHelper = new BookingFileHelper();
    private final List<Booking> bookings = new ArrayList<>();

    public void load() {
        bookings.clear();
        try {
            bookings.addAll(fileHelper.read());
        } catch (Exception e) {
            System.out.println("Load failed.");
        }
    }

    public boolean save() {
        try {
            fileHelper.write(bookings);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean add(Booking c) {
        if (c == null) {
            return false;
        }
        bookings.add(c);
        return true;
    }

    public boolean existsBookingID(String id) {
        return findById(id) != null;
    }

    public Booking findById(String id) {
        for (Booking b : bookings) {
            if (b.getBookingID().equalsIgnoreCase(id)) {
                return b;
            }
        }
        return null;
    }

    public boolean hasBookingForTour(String tourID) {
        for (Booking b : bookings) {
            if (b.getTourID().equalsIgnoreCase(tourID)) {
                return true;
            }
        }
        return false;
    }

    public List<Booking> getAll() {
        return bookings;
    }

    public void remove(Booking b) {
        bookings.remove(b);
    }

}
