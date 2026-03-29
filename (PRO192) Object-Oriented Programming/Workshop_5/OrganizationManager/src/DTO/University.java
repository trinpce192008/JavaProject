/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author TRI
 */
public class University extends Organization {

    private String name;

    public University() {
    }

    public University(String name, int size) {
        super(size);
        this.name = name;
    }

    public void enroll() {
        System.out.println("The registration for enrollment is only valid when the University has received all enrollment documents and enrollment fees");
    }

    public void educate() {
        System.out.println("provide education at university standard");
    }

    @Override
    public void communicateByTool() {
        System.out.println("in the university, people communicate by voice");
    }

}
