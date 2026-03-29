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
public class Tour {

    private String tourID;
    private String tourName;
    private String time;
    private int price; // per tourist
    private String homeID;
    private Date departureDate;
    private Date endDate;
    private int numberTourist;
    private boolean booking;

    public Tour(String tourID, String tourName, String time, int price, String homeID,
            Date departureDate, Date endDate, int numberTourist, boolean booking) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.time = time;
        this.price = price;
        this.homeID = homeID;
        this.departureDate = departureDate;
        this.endDate = endDate;
        this.numberTourist = numberTourist;
        this.booking = booking;
    }

    public String getTourID() {
        return tourID;
    }

    public String getTourName() {
        return tourName;
    }

    public String getTime() {
        return time;
    }

    public int getPrice() {
        return price;
    }

    public String getHomeID() {
        return homeID;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getNumberTourist() {
        return numberTourist;
    }

    public boolean isBooking() {
        return booking;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setHomeID(String homeID) {
        this.homeID = homeID;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setNumberTourist(int numberTourist) {
        this.numberTourist = numberTourist;
    }

    public void setBooking(boolean booking) {
        this.booking = booking;
    }

    public int totalAmount() {
        return price * numberTourist;
    }
}
