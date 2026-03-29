/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fileio;

import java.util.ArrayList;
import java.util.List;
import model.Homestay;
import utils.FileUtils;
import view.View;

/**
 *
 * @author TRI
 */
public class HomestayFileHelper implements IFileReadWrite<Homestay> {

    private static final String HOMESTAY_FILE = "src\\fileio\\Homestays.txt";

    @Override
    public List<Homestay> read() throws Exception {

        List<Homestay> homestays = new ArrayList<>();
        List<String> lines = FileUtils.readAllLines(HOMESTAY_FILE);

        for (String line : lines) {
            String[] capSplit = line.split("-(?=\\d+$)", 2);
            if (capSplit.length < 2) {
                continue;
            }
            String[] parts = capSplit[0].split("-", 4);
            if (parts.length < 4) {
                continue;
            }

            String homeID = parts[0].replace("\uFEFF", "").trim();
            String homeName = parts[1].trim();
            int roomNumber = View.parseIntSafe(parts[2].trim(), -1);
            String address = parts[3].trim();
            int maxCap = View.parseIntSafe(capSplit[1].trim(), -1);

            homestays.add(new Homestay(homeID, homeName, roomNumber, address, maxCap));
        }
        return homestays;
    }

    @Override
    public boolean write(List<Homestay> list) throws Exception {
        System.out.println("Homestay data is read-only. No writing performed.");
        return false;
    }

}
