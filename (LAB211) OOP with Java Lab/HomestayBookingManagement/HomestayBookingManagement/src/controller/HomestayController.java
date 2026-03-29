/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import fileio.HomestayFileHelper;
import fileio.IFileReadWrite;
import java.util.ArrayList;
import java.util.List;
import model.Homestay;

/**
 *
 * @author TRI
 */
public class HomestayController {

    private final List<Homestay> homestays = new ArrayList<>();
    private final IFileReadWrite<Homestay> fileHelper = new HomestayFileHelper();

    public void load() {
        homestays.clear();
        try {
            homestays.addAll(fileHelper.read());
        } catch (Exception e) {
             System.out.println("Load failed.");
        }
    }

    public Homestay findById(String homeID) {
        for (Homestay h : homestays) {
            if (h.getHomeID().equalsIgnoreCase(homeID)) {
                return h;
            }
        }
        return null;
    }

    public List<Homestay> getAll() {
        return homestays;
    }
}
