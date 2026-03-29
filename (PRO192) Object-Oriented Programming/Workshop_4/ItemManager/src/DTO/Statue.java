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
public class Statue extends Item {

    private int weight;
    private String colour;

    public Statue() {
    }

    public Statue(int weight, String colour, int value, String creator) {
        super(value, creator);
        this.weight = weight;
        this.colour = colour;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void outputStatue() {
        super.output();
        System.out.println("Weight:" + weight);
        System.out.println("Colour: " + colour);

    }

    public void inputStatue() {
        super.input();
        Scanner nl = new Scanner(System.in);
        
        System.out.println("Input weight: ");
        weight = nl.nextInt();
        nl.nextLine();
        
        System.out.println("Input colour: ");
        colour = nl.nextLine();
    }
}
