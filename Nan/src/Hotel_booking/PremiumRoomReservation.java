package Hotel_booking;

public class PremiumRoomReservation extends Reservation {
    private boolean hasComplimentaryDrinks;

    public PremiumRoomReservation(int hotelId, int roomId, String guestName, String guestEmail, String checkInDate, String checkOutDate, boolean hasComplimentaryDrinks) {
        super(hotelId, roomId, guestName, guestEmail, checkInDate, checkOutDate);
        this.hasComplimentaryDrinks = hasComplimentaryDrinks;
    }

    public boolean hasComplimentaryDrinks() {
        return hasComplimentaryDrinks;
    }

    public void setComplimentaryDrinks(boolean hasComplimentaryDrinks) {
        this.hasComplimentaryDrinks = hasComplimentaryDrinks;
    }
}

