package Hotel_booking;

public class PremiumRoom extends Room {
    private boolean complimentaryDrinks;

    public PremiumRoom(int hotelId, int roomNumber, String roomType, double price, boolean availability, boolean complimentaryDrinks) {
        super(hotelId, roomNumber, roomType, price, availability);
        this.complimentaryDrinks = complimentaryDrinks;
    }

    public boolean hasComplimentaryDrinks() {
        return complimentaryDrinks;
    }

    public void setComplimentaryDrinks(boolean complimentaryDrinks) {
        this.complimentaryDrinks = complimentaryDrinks;
    }

    @Override
    public String toString() {
        return super.toString() + ", Complimentary Drinks: " + (complimentaryDrinks ? "Yes" : "No");
    }
}

