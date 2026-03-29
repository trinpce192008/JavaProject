/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import fileio.CustomerFileHelper;
import fileio.IFileReadWrite;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.Customer;

/**
 *
 * @author TRI
 */
public class CustomerController {

    private final IFileReadWrite<Customer> fileHelper = new CustomerFileHelper();
    private final List<Customer> customers = new ArrayList<>();
    private boolean saved = true;

    public void load() {
        customers.clear();
        try {
            customers.addAll(fileHelper.read());
            saved = true;
        } catch (Exception e) {
            saved = true;
        }
    }

    public boolean isSaved() {
        return saved;
    }

    public Customer findById(String id) {
        if (id == null) {
            return null;
        }
        String key = id.trim();
        for (Customer c : customers) {
            if (c.getId().equalsIgnoreCase(key)) {
                return c;
            }
        }
        return null;
    }

    public boolean add(Customer c) {
        if (c == null) {
            return false;
        }
        if (findById(c.getId()) != null) {
            return false;
        }
        customers.add(c);
        saved = false;
        return true;
    }

    public boolean update(Customer x) {
        if (x == null) {
            return false;
        }
        Customer old = findById(x.getId());
        if (old == null) {
            return false;
        }

        old.setName(x.getName());
        old.setPhone(x.getPhone());
        old.setEmail(x.getEmail());
        saved = false;
        return true;
    }

    public List<Customer> searchByName(String keyword) {
        String k = (keyword == null) ? "" : keyword.toLowerCase();
        List<Customer> rs = new ArrayList<>();
        for (Customer c : customers) {
            if (c.getName().toLowerCase().contains(k)) {
                rs.add(c);
            }
        }
        rs.sort(Comparator.comparing(Customer::getName, String.CASE_INSENSITIVE_ORDER));
        return rs;
    }

    public List<Customer> getAllSortedByName() {
        List<Customer> copy = new ArrayList<>(customers);
        copy.sort(Comparator.comparing(Customer::getName, String.CASE_INSENSITIVE_ORDER));
        return copy;
    }

    public boolean save() {
        try {
            fileHelper.write(customers);
            saved = true;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
