import java.util.Scanner;

public class HotelBookingManagementSystem {
    private static final int TOTAL_ROOMS = 10;
    private static final boolean[] booked = new boolean[TOTAL_ROOMS + 1];
    private static final String[] guestNames = new String[TOTAL_ROOMS + 1];
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        System.out.println("======================================");
        System.out.println("   HOTEL BOOKING MANAGEMENT SYSTEM");
        System.out.println("======================================");

        do {
            showMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    viewAllRooms();
                    break;
                case 5:
                    System.out.println("Thank you for using the system!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 to 5.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n----------- MAIN MENU -----------");
        System.out.println("1. View Available Rooms");
        System.out.println("2. Book a Room");
        System.out.println("3. Cancel a Booking");
        System.out.println("4. View All Rooms");
        System.out.println("5. Exit");
    }

    private static void viewAvailableRooms() {
        int count = 0;
        System.out.println("\n------- AVAILABLE ROOMS -------");

        for (int room = 1; room <= TOTAL_ROOMS; room++) {
            if (!booked[room]) {
                System.out.print(room + " ");
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No rooms are currently available.");
        } else {
            System.out.println("\nTotal available rooms: " + count);
        }
    }

    private static void bookRoom() {
        viewAvailableRooms();
        int room = readInt("Enter room number to book (1-" + TOTAL_ROOMS + "): ");

        if (room < 1 || room > TOTAL_ROOMS) {
            System.out.println("Invalid room number.");
            return;
        }

        if (booked[room]) {
            System.out.println("Sorry, this room is already booked.");
            return;
        }

        String name;
        while (true) {
            System.out.print("Enter guest name: ");
            name = scanner.nextLine().trim();

            if (!name.isEmpty()) {
                break;
            }
            System.out.println("Guest name cannot be empty. Please try again.");
        }

        booked[room] = true;
        guestNames[room] = name;
        System.out.println("Room " + room + " has been booked successfully for " + name + ".");
    }

    private static void cancelBooking() {
        int room = readInt("Enter room number to cancel (1-" + TOTAL_ROOMS + "): ");

        if (room < 1 || room > TOTAL_ROOMS) {
            System.out.println("Invalid room number.");
            return;
        }

        if (!booked[room]) {
            System.out.println("This room is not currently booked.");
            return;
        }

        System.out.println("Booking for " + guestNames[room] + " in room " + room + " has been cancelled.");
        booked[room] = false;
        guestNames[room] = null;
    }

    private static void viewAllRooms() {
        System.out.println("\n---------- ROOM STATUS ----------");
        System.out.printf("%-12s %-15s %s%n", "Room Number", "Status", "Guest Name");
        System.out.println("----------------------------------------------");

        for (int room = 1; room <= TOTAL_ROOMS; room++) {
            String status = booked[room] ? "Booked" : "Available";
            String guest = booked[room] ? guestNames[room] : "-";
            System.out.printf("%-12d %-15s %s%n", room, status, guest);
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }
}
