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
public class Vase extends Item {

    private int height;
    private String material;

    public Vase() {
    }

    public Vase(int height, String material, int value, String creator) {
        super(value, creator);
        this.height = height;
        this.material = material;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void outputVase() {
        super.output();
        System.out.println("Height:" + height);
        System.out.println("Material:" + material);
    }

    public void inputVase() {
        super.input();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Input a height:");
        height = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Input a material:");
        material = sc.nextLine();

    }

}
