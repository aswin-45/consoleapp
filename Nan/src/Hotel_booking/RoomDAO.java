package Hotel_booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private Connection connection;

    public RoomDAO(Connection connection) {
        this.connection = connection;
    }

    public void createRoom(Room room) throws SQLException {
        if (room instanceof PremiumRoom) {
            createPremiumRoom((PremiumRoom) room);
        } else {
            createRegularRoom(room);
        }
    }

    private void createRegularRoom(Room room) throws SQLException {
        String sql = "INSERT INTO rooms (hotel_id, room_number, room_type, price, availability) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, room.getHotelId());
            statement.setInt(2, room.getRoomNumber());
            statement.setString(3, room.getRoomType());
            statement.setDouble(4, room.getPrice());
            statement.setBoolean(5, room.isAvailability());
            statement.executeUpdate();
        }
    }

    private void createPremiumRoom(PremiumRoom premiumRoom) throws SQLException {
        String sql = "INSERT INTO rooms (hotel_id, room_number, room_type, price, availability) VALUES (?, ?, ?, ?, ?)";
        String premiumSql = "INSERT INTO premium_rooms (room_id, complimentary_drinks) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, premiumRoom.getHotelId());
            statement.setInt(2, premiumRoom.getRoomNumber());
            statement.setString(3, premiumRoom.getRoomType());
            statement.setDouble(4, premiumRoom.getPrice());
            statement.setBoolean(5, premiumRoom.isAvailability());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int roomId = generatedKeys.getInt(1);
                    try (PreparedStatement premiumStatement = connection.prepareStatement(premiumSql)) {
                        premiumStatement.setInt(1, roomId);
                        premiumStatement.setBoolean(2, premiumRoom.hasComplimentaryDrinks());
                        premiumStatement.executeUpdate();
                    }
                } else {
                    throw new SQLException("Creating premium room failed, no ID obtained.");
                }
            }
        }
    }

    public List<Room> getRoomsByHotelId(int hotelId) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms WHERE hotel_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hotelId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int roomNumber = resultSet.getInt("room_number");
                    String roomType = resultSet.getString("room_type");
                    double price = resultSet.getDouble("price");
                    boolean availability = resultSet.getBoolean("availability");

                    Room room;
                    if (resultSet.getBoolean("is_premium")) {
                        boolean complimentaryDrinks = resultSet.getBoolean("complimentary_drinks");
                        room = new PremiumRoom(id, hotelId, roomType, price, availability, complimentaryDrinks);
                    } else {
                        room = new Room(id, hotelId, roomNumber, roomType, price, availability);
                    }
                    rooms.add(room);
                }
            }
        }

        return rooms;
    }


    public void updateRoom(Room room) throws SQLException {
        String sql = "UPDATE rooms SET room_number = ?, room_type = ?, price = ?, availability = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, room.getRoomNumber());
            statement.setString(2, room.getRoomType());
            statement.setDouble(3, room.getPrice());
            statement.setBoolean(4, room.isAvailability());
            statement.setInt(5, room.getId());
            statement.executeUpdate();
        }
    }

    public void deleteRoom(int roomId) throws SQLException {
        String sql = "DELETE FROM rooms WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, roomId);
            statement.executeUpdate();
        }
    }
    
    
    
}