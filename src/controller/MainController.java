package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        view.displayMessage("Enter booking ID to update: ");
        String bookingID = view.inputString();

        // Tìm kiếm thông tin đặt phòng dựa trên bookingID
        Optional<Reservation> optionalReservation = reservations.stream()
                .filter(reservation -> reservation.getBookingID().equals(bookingID))
                .findFirst();

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.printReservation();

            view.displayMessage("If you do not want to change the information just press Enter to skip.");

            // Cập nhật tên khách hàng
            view.displayMessage("Name: ");
            String name = view.inputOptionalString();
            if (!name.isEmpty()) {
                reservation.setCustomerName(name);
            }

            // Cập nhật số điện thoại
            view.displayMessage("Phone: ");
            String phone = view.inputOptionalString();
            if (!phone.isEmpty()) {
                reservation.setPhoneNumber(phone);
            }

            // Cập nhật số phòng
            view.displayMessage("Room Number: ");
            String room = view.inputOptionalString();
            if (!room.isEmpty()) {
                reservation.setRoomNumber(room);
            }

            // Cập nhật ngày đặt phòng
            view.displayMessage("Booking Date (yyyy-MM-dd HH:mm): ");
            String bookingDateStr = view.inputOptionalString();
            if (!bookingDateStr.isEmpty()) {
                try {
                    LocalDateTime bookingDate = LocalDateTime.parse(bookingDateStr, view.getFormatter());
                    reservation.setBookingDate(bookingDate);
                } catch (Exception e) {
                    view.displayMessage("Invalid date format. No changes made to booking date.");
                }
            }

            // Cập nhật thông tin chuyến bay
            view.displayMessage("Need airport pick up? (Y/N): ");
            String needPickUp = view.inputOptionalString();
            if (needPickUp.equalsIgnoreCase("Y")) {
                view.displayMessage("Flight: ");
                String flight = view.inputString();

                view.displayMessage("Seat: ");
                String seat = view.inputString();

                view.displayMessage("Time Pick Up (yyyy-MM-dd HH:mm): ");
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
        view.displayMessage("Enter booking ID to delete: ");
        String bookingID = view.inputString();

        // Tìm kiếm thông tin đặt phòng dựa trên bookingID
        Optional<Reservation> optionalReservation = reservations.stream()
                .filter(reservation -> reservation.getBookingID().equals(bookingID))
                .findFirst();

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.printReservation();

            // Xác nhận từ người dùng trước khi xóa
            view.displayMessage("Are you sure you want to delete this reservation? (Y/N): ");
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
