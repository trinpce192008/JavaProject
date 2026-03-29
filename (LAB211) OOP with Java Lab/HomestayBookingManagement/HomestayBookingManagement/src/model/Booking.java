/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author TRI
 */
public class Booking {

    private String bookingID;
    private String fullName;
    private String tourID;
    private Date bookingDate;
    private String phone;

    public Booking(String bookingID, String fullName, String tourID, Date bookingDate, String phone) {
        this.bookingID = bookingID;
        this.fullName = fullName;
        this.tourID = tourID;
        this.bookingDate = bookingDate;
        this.phone = phone;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTourID() {
        return tourID;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
