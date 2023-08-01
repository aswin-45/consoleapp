package Hotel_booking;

public class Reservation {
    private int id;
    private int hotelId;
    private int roomId;
    private String guestName;
    private String guestEmail;
    private String checkInDate;
    private String checkOutDate;

    public Reservation(int hotelId, int roomId, String guestName, String guestEmail, String checkInDate, String checkOutDate) {
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.guestName = guestName;
        this.guestEmail = guestEmail;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
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

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    public Reservation(int id, int hotelId, int roomId, String guestName, String guestEmail, String checkInDate, String checkOutDate) {
    	this.id = id;
    	this.hotelId = hotelId;
    	this.roomId = roomId;
    	this.guestName = guestName;
    	this.guestEmail = guestEmail;
    	this.checkInDate = checkInDate;
    	this.checkOutDate = checkOutDate;
    }
    @Override
    public String toString() {
        return "User ID: " + id +
               ", hotelId: " + hotelId +
               ", roomId: " + roomId +
               ", guestName: " + guestName +
               ", guestEmail: " + guestEmail +
               ", checkInDate: " + checkInDate + 
               ", checkOutDate: " + checkOutDate;
    }
}
