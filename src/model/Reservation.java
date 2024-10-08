/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private String bookingID;
    private String customerName;
    private String phoneNumber;
    private String roomNumber;
    private LocalDateTime bookingDate;
    private FlightInformation flightInformation;

    public Reservation(String bookingID, String customerName, String phoneNumber, String roomNumber, LocalDateTime bookingDate, FlightInformation flightInformation) {
        this.bookingID = bookingID;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.roomNumber = roomNumber;
        this.bookingDate = bookingDate;
        this.flightInformation = flightInformation;
    }

    public Reservation() {
        this.bookingID = "";
        this.customerName = "";
        this.phoneNumber = "";
        this.roomNumber = "";
        this.bookingDate = LocalDateTime.now();
        this.flightInformation = null;
    }

    // Getters và setters cho từng thuộc tính
    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public FlightInformation getFlightInformation() {
        return flightInformation;
    }

    public void setFlightInformation(FlightInformation flightInformation) {
        this.flightInformation = flightInformation;
    }

    public void printReservation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String formattedBookingDate = bookingDate.format(formatter);
    
    System.out.print("ID: " + bookingID + " - Name: " + customerName + " - Phone: " + phoneNumber 
        + " - Room: " + roomNumber + " - BookingDate: " + formattedBookingDate);
    
    if (flightInformation != null) {
        String formattedTimePickUp = flightInformation.getTimePickUp().format(formatter);
        System.out.println(" - Flight: " + flightInformation.getFlightNumber() 
            + " - Seat: " + flightInformation.getSeatNumber() 
            + " - TimePickUp: " + formattedTimePickUp);
    } else {
        System.out.println();
    }
}

}

