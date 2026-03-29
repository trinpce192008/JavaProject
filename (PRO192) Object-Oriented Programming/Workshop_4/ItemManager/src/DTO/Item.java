/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Scanner;

/**
 *
 * @author TRI
 */
public class Item {

    private int value;
    private String creator;

    public Item() {
    }

    public Item(int value, String creator) {
        this.value = value;
        this.creator = creator;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void output() {
        System.out.println("Value: " + value + "\nCreator: " + creator);
    }

    public void input() {
        Scanner nl = new Scanner(System.in);
        while (true) {
            System.out.println("Input value: ");
            value = nl.nextInt();
            nl.nextLine();
            if (value > 0)
                break;
            else
            System.out.println("Value must be > 0.");
        }
        
        while (true) {
            System.out.println("Input creator: ");
            creator = nl.nextLine().trim();
            if (creator.isEmpty())
                System.out.println("Creator cannot be empty.");
            else
                break;
        }

    }
}
