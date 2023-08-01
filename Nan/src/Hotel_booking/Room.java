package Hotel_booking;

public class Room {
    private int id;
    private int hotelId;
    private int roomNumber;
    private String roomType;
    private double price;
    private boolean availability;
    public Room(int hotelId, int roomNumber, String roomType, double price, boolean availability) {
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.availability = availability;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    public Room(int id, int hotelId, int roomNumber, String roomType, double price, boolean availability) {
    	this.id = id;
    	this.hotelId = hotelId;
    	this.roomNumber = roomNumber;
    	this.roomType = roomType;
    	this.price = price;
    	this.availability = availability;
    }
    @Override
    public String toString() {
        return super.toString()+ "User ID: " + id +
               ", hotelId: " + hotelId +
               ", roomNumber: " + roomNumber +
               ", roomType: " + roomType +
               ", price: " + price + 
               ", availability: " + availability;
    }
}
