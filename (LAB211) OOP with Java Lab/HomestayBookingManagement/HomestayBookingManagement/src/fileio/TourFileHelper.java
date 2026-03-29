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
import model.Tour;
import utils.FileUtils;
import utils.Validator;
import view.View;

/**
 *
 * @author TRI
 */
public class TourFileHelper implements IFileReadWrite<Tour> {

    private static final String TOUR_FILE = "src\\fileio\\Tours.txt";

    @Override
    public List<Tour> read() throws Exception {
        
        List<Tour> tours = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        List<String> lines = FileUtils.readAllLines(TOUR_FILE);
        
        for (String line : lines) {
            String[] p = line.split(",");
            if (p.length < 9) {
                continue;
            }

            String tourID = p[0].replace("\uFEFF", "").trim();
            String tourName = p[1].trim();
            String time = p[2].trim();
            int price = View.parseIntSafe(p[3].trim(), -1);
            String homeID = p[4].trim();
            Date dep = View.parseDate(p[5].trim());
            Date end = View.parseDate(p[6].trim());
            int num = View.parseIntSafe(p[7].trim(), -1);
            boolean booking = p[8].trim().equalsIgnoreCase("TRUE");

            if (!Validator.validTourID(tourID) || tourName.isEmpty() || time.isEmpty()
                    || price <= 0 || dep == null || end == null || num <= 0) {
                continue;
            }

            // TourID must be unique: if duplicate in file, skip later ones
            String key = tourID.toUpperCase();
            if (seen.contains(key)) {
                continue;
            }
            seen.add(key);

            tours.add(new Tour(tourID, tourName, time, price, homeID, dep, end, num, booking));
        }
        return tours;
    }

    @Override
    public boolean write(List<Tour> list) throws Exception {
        
        List<String> lines = new ArrayList<>();

        for (Tour t : list) {

            String line = t.getTourID() + ","
                    + t.getTourName() + ","
                    + t.getTime() + ","
                    + t.getPrice() + ","
                    + t.getHomeID() + ","
                    + View.formatDate(t.getDepartureDate()) + ","
                    + View.formatDate(t.getEndDate()) + ","
                    + t.getNumberTourist() + ","
                    + t.isBooking();

            lines.add(line);
        }
        try {
            FileUtils.writeAllLines(TOUR_FILE, lines);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
