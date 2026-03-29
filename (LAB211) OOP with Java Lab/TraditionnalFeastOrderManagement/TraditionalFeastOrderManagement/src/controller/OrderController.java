/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import fileio.IFileReadWrite;
import fileio.OrderFileHelper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Order;

/**
 *
 * @author TRI
 */
public class OrderController {

    private final IFileReadWrite<Order> fileHelper = new OrderFileHelper();
    private final Set<Order> orders = new HashSet<>();
    private boolean saved = true;

    public void load() {
        orders.clear();
        try {
            List<Order> list = fileHelper.read();
            orders.addAll(list);
            saved = true;
        } catch (Exception e) {
            saved = true;
        }
    }

    public boolean isSaved() {
        return saved;
    }

    public Order findById(String orderId) {
        if (orderId == null) {
            return null;
        }
        String key = orderId.trim();
        for (Order o : orders) {
            if (o.getOrderCode().equalsIgnoreCase(key)) {
                return o;
            }
        }
        return null;
    }

    public boolean isDuplicate(Order x) {
        return orders.contains(x);
    }

    public String generateNextOrderId() {
        int max = 0;
        for (Order o : orders) {
            String s = o.getOrderCode();
            if (s != null && s.matches("\\d+")) {
                max = Math.max(max, Integer.parseInt(s));
            }
        }
        return String.valueOf(max + 1);
    }

    public boolean placeOrder(Order x) {
        if (x == null) {
            return false;
        }
        if (x.getEventDate() == null || !x.getEventDate().after(new Date())) {
            return false;
        }
        if (isDuplicate(x)) {
            return false;
        }

        orders.add(x);
        saved = false;
        return true;
    }

    public boolean updateOrder(Order newOrder) {
        if (newOrder == null) {
            return false;
        }

        Order old = findById(newOrder.getOrderCode());
        if (old == null) {
            return false;
        }

        if (old.getEventDate() == null || !old.getEventDate().after(new Date())) {
            return false;
        }

        orders.remove(old);
        boolean ok = orders.add(newOrder);
        if (!ok) {
            orders.add(old);
            return false;
        }

        saved = false;
        return true;
    }

    public List<Order> getAllSortedByEventDate() {
        List<Order> list = new ArrayList<>(orders);
        list.sort(Comparator.comparing(Order::getEventDate));
        return list;
    }

    public boolean save() {
        try {
            fileHelper.write(new ArrayList<>(orders));
            saved = true;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
