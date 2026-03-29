/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author TRI
 */
public class Car {

    private String Colour;
    private int EnginePower;
    private boolean Convertible;
    private boolean ParkingBrake;

    //Contructor
    public Car() {
    }

    public Car(String Colour, int EnginePower, boolean Convertible,
            boolean ParkingBrake) {
    }

    //getter
    public String getColour() {
        return Colour;
    }

    public int getEnginePower() {
        return EnginePower;
    }

    public boolean getConvertible() {
        return Convertible;
    }

    public boolean getParkingBrake() {
        return ParkingBrake;
    }

    //Setter
    public void setColour(String Colour) {
        this.Colour = Colour;
    }

    public void setEnginePower(int EnginePower) {
        this.EnginePower = EnginePower;
    }

    public void setConvertible(boolean Convertible) {
        this.Convertible = Convertible;
    }

    public void setParkingBrake(boolean ParkingBrake) {
        this.ParkingBrake = ParkingBrake;
    }
    
//Other method
    public void pressStartButton() {
        System.out.println("You have pressed the start button");
    }

    public void pressAcceleratorButton() {
        System.out.println("You have pressed the Accelerator button");
    }

    public void output() {
        System.out.println(Colour + " " + EnginePower + " " + Convertible + " " + ParkingBrake);
    }
}
