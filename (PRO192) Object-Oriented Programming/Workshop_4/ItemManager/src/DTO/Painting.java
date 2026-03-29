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
public class Painting extends Item {
    private int height;
    private int width;
    private boolean isWatercolour;
    private boolean isFramed;

    public Painting() {
    }

    public Painting(int height, int width, boolean isWatercolour, boolean isFramed, int value, String creator) {
        super(value, creator);
        this.height = height;
        this.width = width;
        this.isWatercolour = isWatercolour;
        this.isFramed = isFramed;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isIsWatercolour() {
        return isWatercolour;
    }

    public void setIsWatercolour(boolean isWatercolour) {
        this.isWatercolour = isWatercolour;
    }

    public boolean isIsFramed() {
        return isFramed;
    }

    public void setIsFramed(boolean isFramed) {
        this.isFramed = isFramed;
    }
    public void outputPainting(){
        super.output();
        System.out.println("Height: "+ height);
        System.out.println("Weight: "+ width);
        System.out.println("Watercolour: "+ isWatercolour);
        System.out.println("Framed: "+isFramed);
    }
    public void inputPainting(){
        super.input();
        Scanner nl= new Scanner(System.in);
        
        System.out.println("Input height: ");
        height = nl.nextInt();
        nl.nextLine();
        
        System.out.println("Input width: ");
        width = nl.nextInt();
        
        System.out.println("Is watercolour (True/False): ");
        isWatercolour = nl.nextBoolean();
        
        System.out.println("Is framed (True/False): ");
        isFramed = nl.nextBoolean();
    }
}
