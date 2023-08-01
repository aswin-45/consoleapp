package Hotel_booking;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class HotelBookingApp {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel_ms";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "#PonniyinSelvan";
    private static void createPremiumRoomReservation(ReservationDAO reservationDAO, Scanner scanner) throws SQLException {
        System.out.print("Enter hotel ID: ");
        int hotelId = scanner.nextInt();
        System.out.print("Enter room ID: ");
        int roomId = scanner.nextInt();
        System.out.print("Enter guest name: ");
        String guestName = scanner.next();
        System.out.print("Enter guest email: ");
        String guestEmail = scanner.next();
        System.out.print("Enter check-in date: ");
        String checkInDate = scanner.next();
        System.out.print("Enter check-out date: ");
        String checkOutDate = scanner.next();
        System.out.print("Enter complimentary drinks availability (true/false): ");
        boolean hasComplimentaryDrinks = scanner.nextBoolean();

        PremiumRoomReservation reservation = new PremiumRoomReservation(hotelId, roomId, guestName, guestEmail, checkInDate, checkOutDate, hasComplimentaryDrinks);
        reservationDAO.createPremiumRoomReservation(reservation);
        System.out.println("Premium Room Reservation created successfully!");
    }

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            HotelDAO hotelDAO = new HotelDAO(connection);
            RoomDAO roomDAO = new RoomDAO(connection);
            ReservationDAO reservationDAO = new ReservationDAO(connection);

            Scanner scanner = new Scanner(System.in);

            while (true) {
            	
            	System.out.println("1.Hotels");
            	System.out.println("2.Guest");
            	System.out.println("Enter your choice :");
            	int n = scanner.nextInt();
            	
            	switch(n)
            	{
            	case 1:
            		System.out.println("1. Create a hotel");
            		System.out.println("3. Create a room");
            		System.out.println("6. View reservations by hotel");
            		System.out.println("9. Delete Room by id");
            		System.out.println("10. Delete Hotel by id");
            		System.out.println("11. Update room by id");
//            		System.out.println("12. Create a Premium_room");
            		break;
            	
            	case 2:
            		System.out.println("2. View all hotels");
            		System.out.println("4. View all rooms for a hotel");
            		System.out.println("5. Make a reservation");
            		System.out.println("7. View reservations by guest email");
            		System.out.println("8. Delete reservations by guest id");
//            		System.out.println("13. Create a Premium_roomReservation");
            		break;
            		
            	}
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        createHotel(hotelDAO, scanner);
                        break;
                    case 2:
                        viewAllHotels(hotelDAO);
                        break;
                    case 3:
                        createRoom(roomDAO, scanner);
                        break;
                    case 4:
                        viewAllRoomsForHotel(roomDAO, scanner);
                        break;
                    case 5:
                        makeReservation(reservationDAO, scanner);
                        break;
                    case 6:
                        viewReservationsByHotel(reservationDAO, scanner);
                        break;
                    case 7:
                        viewReservationsByGuestEmail(reservationDAO, scanner);
                        break;
                    case 8:
                    	deleteReservation(reservationDAO, scanner);
                    	break;
                    case 9: 
                        deleteRoom(roomDAO, scanner);
                        break;

                    case 10: 
                        deleteHotel(hotelDAO, scanner);
                        break;
                    case 11: 
                        updateRoom(roomDAO, scanner);
                        break;
//                    case 12: 
//                    	 createPremiumRoom(roomDAO, scanner);
//                        break;
//                    case 13:
//                        createPremiumRoomReservation(reservationDAO, scanner);
//                        break;
                    case 0:
                        System.out.println("Thank You!");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    private static void deleteReservationId(ReservationDAO reservationDAO, Scanner scanner) {
//    	System.out.print("Enter guest id: ");
//        int id = scanner.nextInt();
//        reservationDAO.createReservation(reservation);
//        System.out.println("Reservation created successfully!");
//		
//	}
    private static void deleteReservation(ReservationDAO reservationDAO, Scanner scanner) throws SQLException {
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();
        reservationDAO.deleteReservation(reservationId);
        System.out.println("Reservation with ID " + reservationId + " deleted successfully!");
    }
    private static void deleteRoom(RoomDAO roomDAO, Scanner scanner) throws SQLException {
        System.out.print("Enter room ID: ");
        int roomId = scanner.nextInt();
        roomDAO.deleteRoom(roomId);
        System.out.println("Room with ID " + roomId + " deleted successfully!");
    }

    private static void deleteHotel(HotelDAO hotelDAO, Scanner scanner) throws SQLException {
        System.out.print("Enter hotel ID: ");
        int hotelId = scanner.nextInt();
        hotelDAO.delete(hotelId);
        System.out.println("Hotel with ID " + hotelId + " deleted successfully!");
    }

	private static void createHotel(HotelDAO hotelDAO, Scanner scanner) throws SQLException {
        System.out.print("Enter hotel name: ");
        String name = scanner.next();
        System.out.print("Enter hotel address: ");
        String address = scanner.next();
        Hotel hotel = new Hotel(name, address);
        hotelDAO.create(hotel);
        System.out.println("Hotel created successfully!");
    }

    private static void viewAllHotels(HotelDAO hotelDAO) throws SQLException {
        List<Hotel> hotels = hotelDAO.getAll();
        System.out.println("Hotels:");
        for (Hotel hotel : hotels) {
            System.out.println(hotel.getId() + ". " + hotel.getName() + " - " + hotel.getAddress());
        }
    }

    private static void createRoom(RoomDAO roomDAO, Scanner scanner) throws SQLException {
        System.out.print("Enter hotel ID: ");
        int hotelId = scanner.nextInt();
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter room type: ");
        String roomType = scanner.next();
        System.out.print("Enter room price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter room availability (true/false): ");
        boolean availability = scanner.nextBoolean();
        Room room = new Room(hotelId, roomNumber, roomType, price, availability);
        roomDAO.createRoom(room);
        System.out.println("Room created successfully!");
    }
    
//    private static void createPremiumRoom(RoomDAO roomDAO, Scanner scanner) throws SQLException {
//        System.out.print("Enter hotel ID: ");
//        int hotelId = scanner.nextInt();
//        System.out.print("Enter room number: ");
//        int roomNumber = scanner.nextInt();
//        System.out.print("Enter room type: ");
//        String roomType = scanner.next();
//        System.out.print("Enter room price: ");
//        double price = scanner.nextDouble();
//        System.out.print("Enter room availability (true/false): ");
//        boolean availability = scanner.nextBoolean();
//        System.out.print("Enter complimentary drinks availability (true/false): ");
//        boolean complimentaryDrinks = scanner.nextBoolean();
//        PremiumRoom premiumRoom = new PremiumRoom(hotelId, roomNumber, roomType, price, availability, complimentaryDrinks);
//        roomDAO.createRoom(premiumRoom);
//        System.out.println("Premium_room created successfully!");
//    }
       

    private static void viewAllRoomsForHotel(RoomDAO roomDAO, Scanner scanner) throws SQLException {
        System.out.print("Enter hotel ID: ");
        int hotelId = scanner.nextInt();
        List<Room> rooms = roomDAO.getRoomsByHotelId(hotelId);
        System.out.println("Rooms for Hotel ID " + hotelId + ":");
        for (Room room : rooms) {
            System.out.println(room.getId() + ". Room Number: " + room.getRoomNumber() +
                    ", Type: " + room.getRoomType() + ", Price: " + room.getPrice() +
                    ", Availability: " + room.isAvailability());
        }
    }

    private static void makeReservation(ReservationDAO reservationDAO, Scanner scanner) throws SQLException {
        System.out.println("Enter hotel ID: ");
        int hotelId = scanner.nextInt();
        System.out.println("Enter room ID: ");
        int roomId = scanner.nextInt();
        System.out.println("Enter guest name: ");
        String guestName = scanner.next();
        System.out.println("Enter guest email: ");
        String guestEmail = scanner.next();
        System.out.println("Enter check-in date: (YYYY-MM-DD)");
        String checkInDate = scanner.next();
        System.out.println("Enter check-out date: (YYYY-MM-DD)");
        String checkOutDate = scanner.next();
        Reservation reservation = new Reservation(hotelId, roomId, guestName, guestEmail, checkInDate, checkOutDate);
        reservationDAO.createReservation(reservation);
        System.out.println("Reservation created successfully!");
    }

    private static void viewReservationsByHotel(ReservationDAO reservationDAO, Scanner scanner) throws SQLException {
        System.out.print("Enter hotel ID: ");
        int hotelId = scanner.nextInt();
        List<Reservation> reservations = reservationDAO.getReservationsByHotelId(hotelId);
        System.out.println("Reservations for Hotel ID " + hotelId + ":");
        for (Reservation reservation : reservations) {
            System.out.println("Reservation ID: " + reservation.getId() +
                    ", Guest Name: " + reservation.getGuestName() +
                    ", Guest Email: " + reservation.getGuestEmail() +
                    ", Check-in Date: " + reservation.getCheckInDate() +
                    ", Check-out Date: " + reservation.getCheckOutDate());
        }
    }

    private static void viewReservationsByGuestEmail(ReservationDAO reservationDAO, Scanner scanner) throws SQLException {
        System.out.print("Enter guest email: ");
        String guestEmail = scanner.next ();
        List<Reservation> reservations = reservationDAO.getReservationsByGuestEmail(guestEmail);
        System.out.println("Reservations for Guest Email " + guestEmail + ":");
        for (Reservation reservation : reservations) {
            System.out.println("Reservation ID: " + reservation.getId() +
                    ", Hotel ID: " + reservation.getHotelId() +
                    ", Room ID: " + reservation.getRoomId() +
                    ", Check-in Date: " + reservation.getCheckInDate() +
                    ", Check-out Date: " + reservation.getCheckOutDate());
        }
    }
    private static void updateRoom(RoomDAO roomDAO, Scanner scanner) throws SQLException {
        System.out.print("Enter room ID: ");
        int roomId = scanner.nextInt();
        System.out.print("Enter new room number: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter new room type: ");
        String roomType = scanner.next();
        System.out.print("Enter new room price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter new room availability (true/false): ");
        boolean availability = scanner.nextBoolean();
        Room updatedRoom = new Room(roomId, roomNumber, roomType, price, availability);
        roomDAO.updateRoom(updatedRoom);
        System.out.println("Room with ID " + roomId + " updated successfully!");
    }

}

