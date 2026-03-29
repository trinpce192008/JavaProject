/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import fileio.IFileReadWrite;
import fileio.SetMenuFileHelper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.SetMenu;

/**
 *
 * @author TRI
 */
public class SetMenuController {

    private final IFileReadWrite<SetMenu> fileHelper = new SetMenuFileHelper();
    private final List<SetMenu> menus = new ArrayList<>();

    public void load() {
        menus.clear();
        try {
            menus.addAll(fileHelper.read());
        } catch (Exception e) {
        }
    }

    public boolean isValidMenuId(String menuId) {
        return getById(menuId) != null;
    }

    public SetMenu getById(String menuId) {
        if (menuId == null) {
            return null;
        }
        String key = menuId.trim();
        for (SetMenu m : menus) {
            if (m.getMenuId().equalsIgnoreCase(key)) {
                return m;
            }
        }
        return null;
    }

    public List<SetMenu> getSortedByPrice() {
        List<SetMenu> copy = new ArrayList<>(menus);
        copy.sort(Comparator.comparingDouble(SetMenu::getPrice));
        return copy;
    }
}
