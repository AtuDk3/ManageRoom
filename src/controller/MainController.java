/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Lenovo
 */
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.FlightInformation;
import model.Reservation;
import view.ConsoleView;

public class MainController {
    private ConsoleView view = new ConsoleView();
    private List<Reservation> reservations = new ArrayList<>();

    public void run() {
        int choice;
        do {
            choice = view.displayMainMenu();
            switch (choice) {
                case 1:
                    createReservation();
                    break;
                case 2:
                    updateReservation();
                    break;
                case 3:
                    deleteReservation();
                    break;
                case 4:
                    printFlightInformation();
                    break;
                case 5:
                    printAllReservations();
                    break;
                case 6:
                    view.displayMessage("BYE AND SEE YOU NEXT TIME");
                    break;
                default:
                    view.displayMessage("Invalid choice. Try again.");
            }
        } while (choice != 6);
    }

    private void createReservation() {
        Reservation reservation = view.inputReservation();
        reservations.add(reservation);
        view.displayMessage("Information saved successfully.");
    }

    private void updateReservation() {
    System.out.print("Enter booking ID to update: ");
    String bookingID = view.inputString();

    // Tìm kiếm thông tin đặt phòng dựa trên bookingID
    Optional<Reservation> optionalReservation = reservations.stream()
            .filter(reservation -> reservation.getBookingID().equals(bookingID))
            .findFirst();

    if (optionalReservation.isPresent()) {
        Reservation reservation = optionalReservation.get();
        reservation.printReservation();
        
        // Cập nhật từng trường dữ liệu, nhấn Enter để bỏ qua nếu không thay đổi
        System.out.println("If you do not want to change the information just press Enter to skip.");
        
        // Cập nhật tên khách hàng
        System.out.print("Name: ");
        String name = view.inputOptionalString();
        if (!name.isEmpty()) {
            reservation.setCustomerName(name);
        }

        // Cập nhật số điện thoại
        System.out.print("Phone: ");
        String phone = view.inputOptionalString();
        if (!phone.isEmpty()) {
            reservation.setPhoneNumber(phone);
        }

        // Cập nhật số phòng
        System.out.print("Room Number: ");
        String room = view.inputOptionalString();
        if (!room.isEmpty()) {
            reservation.setRoomNumber(room);
        }

        // Cập nhật ngày đặt phòng
        System.out.print("Booking Date (yyyy-MM-dd HH:mm): ");
        String bookingDateStr = view.inputOptionalString();
        if (!bookingDateStr.isEmpty()) {
            LocalDateTime bookingDate = LocalDateTime.parse(bookingDateStr);
            reservation.setBookingDate(bookingDate);
        }

        // Cập nhật thông tin chuyến bay
        System.out.print("Need airport pick up? (Y/N): ");
        String needPickUp = view.inputOptionalString();
        if (needPickUp.equalsIgnoreCase("Y")) {
            System.out.print("Flight: ");
            String flight = view.inputString();
            
            System.out.print("Seat: ");
            String seat = view.inputString();
            
            System.out.print("Time Pick Up (yyyy-MM-dd HH:mm): ");
            LocalDateTime timePickUp = view.inputDateTime();
            
            FlightInformation flightInfo = new FlightInformation(flight, seat, timePickUp);
            reservation.setFlightInformation(flightInfo);
        }

        view.displayMessage("Information updated successfully.");
    } else {
        view.displayMessage("No reservation found with the given booking ID.");
    }
}


    private void deleteReservation() {
    System.out.print("Enter booking ID to delete: ");
    String bookingID = view.inputString();

    // Tìm kiếm thông tin đặt phòng dựa trên bookingID
    Optional<Reservation> optionalReservation = reservations.stream()
            .filter(reservation -> reservation.getBookingID().equals(bookingID))
            .findFirst();

    if (optionalReservation.isPresent()) {
        Reservation reservation = optionalReservation.get();
        reservation.printReservation();

        // Xác nhận từ người dùng trước khi xóa
        System.out.print("Are you sure you want to delete this reservation? (Y/N): ");
        String confirmation = view.inputString();

        if (confirmation.equalsIgnoreCase("Y")) {
            reservations.remove(reservation);
            view.displayMessage("Reservation deleted successfully.");
        } else {
            view.displayMessage("Reservation deletion canceled.");
        }
    } else {
        view.displayMessage("No reservation found with the given booking ID.");
    }
}


    private void printFlightInformation() {
        for (Reservation reservation : reservations) {
            if (reservation.getFlightInformation() != null) {
                reservation.printReservation();
            }
        }
    }

    private void printAllReservations() {
        if (reservations.isEmpty()) {
            view.displayMessage("No information to view.");
        } else {
            for (Reservation reservation : reservations) {
                reservation.printReservation();
            }
        }
    }
}

