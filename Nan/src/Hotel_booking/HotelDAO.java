package Hotel_booking;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO extends AbstractDAO<Hotel> {
    public HotelDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Hotel hotel) throws SQLException {
        String sql = "INSERT INTO hotels (name, address) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getAddress());
            statement.executeUpdate();
        }
    }

    @Override
    public List<Hotel> getAll() throws SQLException {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT * FROM hotels";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");

                Hotel hotel = new Hotel(id, name, address);
                hotels.add(hotel);
            }
        }

        return hotels;
    }

    @Override
    public void update(Hotel hotel) throws SQLException {
        String sql = "UPDATE hotels SET name = ?, address = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getAddress());
            statement.setInt(3, hotel.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(int hotelId) throws SQLException {
        String sql = "DELETE FROM hotels WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hotelId);
            statement.executeUpdate();
        }
    }
}

