package Hotel_booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    private Connection connection;

    public ReservationDAO(Connection connection) {
        this.connection = connection;
    }

    public void createReservation(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO reservations (hotel_id, room_id, guest_name, guest_email, check_in_date, check_out_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservation.getHotelId());
            statement.setInt(2, reservation.getRoomId());
            statement.setString(3, reservation.getGuestName());
            statement.setString(4, reservation.getGuestEmail());
            statement.setString(5, reservation.getCheckInDate());
            statement.setString(6, reservation.getCheckOutDate());
            statement.executeUpdate();
        }
    }

    public List<Reservation> getReservationsByHotelId(int hotelId) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE hotel_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hotelId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int roomId = resultSet.getInt("room_id");
                    String guestName = resultSet.getString("guest_name");
                    String guestEmail = resultSet.getString("guest_email");
                    String checkInDate = resultSet.getString("check_in_date");
                    String checkOutDate = resultSet.getString("check_out_date");

                    Reservation reservation = new Reservation(id, hotelId, roomId, guestName, guestEmail, checkInDate, checkOutDate);
                    reservations.add(reservation);
                }
            }
        }

        return reservations;
    }

    public List<Reservation> getReservationsByGuestEmail(String guestEmail) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE guest_email = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, guestEmail);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int hotelId = resultSet.getInt("hotel_id");
                    int roomId = resultSet.getInt("room_id");
                    String guestName = resultSet.getString("guest_name");
                    String checkInDate = resultSet.getString("check_in_date");
                    String checkOutDate = resultSet.getString("check_out_date");

                    Reservation reservation = new Reservation(id, hotelId, roomId, guestName, guestEmail, checkInDate, checkOutDate);
                    reservations.add(reservation);
                }
            }
        }

        return reservations;
    }
    public void createPremiumRoomReservation(PremiumRoomReservation reservation) throws SQLException {
        String sql = "INSERT INTO reservations (hotel_id, room_id, guest_name, guest_email, check_in_date, check_out_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservation.getHotelId());
            statement.setInt(2, reservation.getRoomId());
            statement.setString(3, reservation.getGuestName());
            statement.setString(4, reservation.getGuestEmail());
            statement.setString(5, reservation.getCheckInDate());
            statement.setString(6, reservation.getCheckOutDate());
            statement.executeUpdate();
        }
    }
    
    public void deleteReservation(int reservationId) throws SQLException {
        String sql = "DELETE FROM reservations WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservationId);
            statement.executeUpdate();
        }
    }

    // Implement updateReservation and deleteReservation methods similarly
}