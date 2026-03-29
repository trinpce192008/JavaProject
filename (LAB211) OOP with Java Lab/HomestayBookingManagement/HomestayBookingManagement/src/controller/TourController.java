/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import fileio.IFileReadWrite;
import fileio.TourFileHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Tour;

/**
 *
 * @author TRI
 */
public class TourController {

    private final List<Tour> tours = new ArrayList<>();
    private final IFileReadWrite<Tour> fileHelper = new TourFileHelper();

    public void load() {
        tours.clear();
        try {
            tours.addAll(fileHelper.read());
        } catch (Exception e) {
             System.out.println("Load failed.");
        }
    }

    public boolean save() {
        try {
            fileHelper.write(tours);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean add(Tour c) {
        if (c == null) {
            return false;
        }
        tours.add(c);
        return true;
    }

    public boolean existsTourID(String id) {
        return findById(id) != null;
    }

    public Tour findById(String id) {
        for (Tour t : tours) {
            if (t.getTourID().equalsIgnoreCase(id)) {
                return t;
            }
        }
        return null;
    }

    public List<Tour> getAll() {
        return tours;
    }

    public boolean isScheduleOverlapped(String homeID, Date dep, Date end, String ignoreTourID) {
        for (Tour t : tours) {
            if (ignoreTourID != null && t.getTourID().equalsIgnoreCase(ignoreTourID)) {
                continue;
            }
            if (!t.getHomeID().equalsIgnoreCase(homeID)) {
                continue;
            }

            Date a1 = t.getDepartureDate();
            Date a2 = t.getEndDate();

            // overlap if ranges intersect
            boolean overlap = !(end.before(a1) || dep.after(a2));
            if (overlap) {
                return true;
            }
        }
        return false;
    }

}
