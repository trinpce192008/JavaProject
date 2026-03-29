/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author TRI
 */
public class Homestay {

        private String homeID;
        private String homeName;
        private int roomNumber;
        private String address;
        private int maximumCapacity;

        public Homestay(String homeID, String homeName, int roomNumber, String address, int maximumCapacity) {
            this.homeID = homeID;
            this.homeName = homeName;
            this.roomNumber = roomNumber;
            this.address = address;
            this.maximumCapacity = maximumCapacity;
        }

        public String getHomeID() {
            return homeID;
        }

        public String getHomeName() {
            return homeName;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public String getAddress() {
            return address;
        }

        public int getMaximumCapacity() {
            return maximumCapacity;
        }

        @Override
        public String toString() {
            return homeID + " | " + homeName + " | rooms=" + roomNumber + " | cap=" + maximumCapacity;
        }
    }

