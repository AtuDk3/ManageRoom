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
import java.util.Scanner;
import model.FlightInformation;
import model.Reservation;

public class ConsoleView {
    private Scanner scanner = new Scanner(System.in);

    public int displayMainMenu() {
        System.out.println("*** Reservation Management ***");
        System.out.println("1. Create new reservation");
        System.out.println("2. Update reservation");
        System.out.println("3. Delete reservation");
        System.out.println("4. Print Flight Information");
        System.out.println("5. Print all");
        System.out.println("6. Exit");
        System.out.print("You choose: ");
        return scanner.nextInt();
    }
    
    // Phương thức nhập chuỗi cho các thông tin tùy chọn (có thể bỏ qua)
    public String inputOptionalString() {
        scanner.nextLine();  // Bỏ qua ký tự xuống dòng từ lần nhập trước
        return scanner.nextLine();
    }
    

    public Reservation inputReservation() {
        System.out.print("ID: ");
        String id = inputString();

        System.out.print("Name: ");
        String name = inputString();

        System.out.print("Phone: ");
        String phone = inputString();

        System.out.print("Room Number: ");
        String room = inputString();

        System.out.print("Booking Date (yyyy-MM-dd HH:mm): ");
        LocalDateTime bookingDate = inputDateTime();

        System.out.print("Need airport pick up? (Y/N): ");
        String needPickUp = inputString();

        FlightInformation flightInfo = null;
        if (needPickUp.equalsIgnoreCase("Y")) {
            System.out.print("Flight: ");
            String flight = inputString();

            System.out.print("Seat: ");
            String seat = inputString();

            System.out.print("Time Pick Up (yyyy-MM-dd HH:mm): ");
            LocalDateTime timePickUp = inputDateTime();

            flightInfo = new FlightInformation(flight, seat, timePickUp);
        }

        return new Reservation(id, name, phone, room, bookingDate, flightInfo);
    }

    public String inputString() {
        return scanner.next();
    }

    public  LocalDateTime inputDateTime() {
        String dateTimeStr = scanner.next();
        return LocalDateTime.parse(dateTimeStr);
    }
    
    public void displayMessage(String message) {
        System.out.println(message);
    }
}

