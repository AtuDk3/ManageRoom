/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Lenovo
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.FlightInformation;
import model.Reservation;

public class ConsoleView {
    private Scanner scanner = new Scanner(System.in);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public int displayMainMenu() {
        System.out.println("===== MAIN MENU =====");
        System.out.println("1. Create Reservation");
        System.out.println("2. Update Reservation");
        System.out.println("3. Delete Reservation");
        System.out.println("4. View Flight Information");
        System.out.println("5. View All Reservations");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        return inputInt();
    }

    public Reservation inputReservation() {
        System.out.println("===== INPUT RESERVATION =====");
        System.out.print("Booking ID: ");
        String bookingID = inputString();

        System.out.print("Customer Name: ");
        String customerName = inputString();

        System.out.print("Phone Number: ");
        String phoneNumber = inputString();

        System.out.print("Room Number: ");
        String roomNumber = inputString();

        System.out.print("Booking Date (yyyy-MM-dd HH:mm): ");
        LocalDateTime bookingDate = inputDateTime();

        System.out.print("Need airport pick up? (Y/N): ");
        String needPickUp = inputString();
        FlightInformation flightInfo = null;

        if (needPickUp.equalsIgnoreCase("Y")) {
            System.out.print("Flight Number: ");
            String flightNumber = inputString();

            System.out.print("Seat Number: ");
            String seatNumber = inputString();

            System.out.print("Time Pick Up (yyyy-MM-dd HH:mm): ");
            LocalDateTime timePickUp = inputDateTime();

            flightInfo = new FlightInformation(flightNumber, seatNumber, timePickUp);
        }

        return new Reservation(bookingID, customerName, phoneNumber, roomNumber, bookingDate, flightInfo);
    }

    public String inputString() {
        return scanner.nextLine().trim();
    }

    public String inputOptionalString() {
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? "" : input;
    }

    public int inputInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    public LocalDateTime inputDateTime() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return LocalDateTime.parse(input, formatter);
            } catch (Exception e) {
                System.out.print("Invalid date format. Please use 'yyyy-MM-dd HH:mm': ");
            }
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}
