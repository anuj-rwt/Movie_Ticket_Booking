import java.util.ArrayList;
import java.util.List;

public class Booking {
    private static int nextBookingId = 1001;

    private int bookingId;
    private Show show;
    private List<ShowSeat> showSeats;
    private double totalAmount;
    private String status;

    // Overloaded constructors demonstrate compile-time polymorphism.
    public Booking(Show show, ShowSeat showSeat, double totalAmount) {
        this(show, List.of(showSeat), totalAmount);
    }

    public Booking(Show show, List<ShowSeat> showSeats, double totalAmount) {
        this.bookingId = nextBookingId++;
        this.show = show;
        this.showSeats = new ArrayList<>(showSeats);
        this.totalAmount = totalAmount;
        this.status = "PENDING";
    }

    public int getBookingId() { return bookingId; }
    public Show getShow() { return show; }
    public List<ShowSeat> getShowSeats() { return showSeats; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }

    public void confirmBooking() {
        status = "CONFIRMED";
    }

    public void cancelBooking() {
        for (ShowSeat showSeat : showSeats) {
            showSeat.cancelSeat();
        }
        status = "CANCELLED";
    }
}
