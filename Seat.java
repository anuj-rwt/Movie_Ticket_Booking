public class Seat {
    private int seatNumber;
    private String seatType;

    public Seat(int seatNumber, String seatType) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
    }

    public int getSeatNumber() { return seatNumber; }
    public String getSeatType() { return seatType; }
}
