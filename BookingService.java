import java.util.ArrayList;
import java.util.List;

public class BookingService {

    public void listMovies(List<Movie> movies) {
        System.out.println("\nAvailable Movies:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i).getTitle());
        }
    }

    public void listShows(List<Show> shows, Movie selectedMovie) {
        System.out.println("\nShows for " + selectedMovie.getTitle() + ":");
        for (Show show : shows) {
            if (show.getMovie() == selectedMovie) {
                System.out.println("Screen " + show.getScreen().getScreenNumber()
                        + " - " + show.getStartTime());
            }
        }
    }

    public void showSeats(Show show) {
        System.out.println("\nSeat Layout:");
        for (ShowSeat showSeat : show.getShowSeats()) {
            System.out.println("Seat " + showSeat.getSeat().getSeatNumber()
                    + " [" + showSeat.getSeat().getSeatType() + "] : "
                    + showSeat.getStatus());
        }
    }

    public Booking bookSeats(Show show, List<Integer> selectedSeatNumbers,
                             PriceCalculator priceCalculator, Payment payment) {
        List<ShowSeat> selectedShowSeats = new ArrayList<>();
        List<Seat> selectedSeats = new ArrayList<>();

        // Validate every seat first so a bad selection does not change anything.
        for (int seatNumber : selectedSeatNumbers) {
            ShowSeat showSeat = findShowSeat(show, seatNumber);

            if (showSeat == null) {
                System.out.println("Invalid seat number: " + seatNumber);
                return null;
            }

            if (showSeat.getStatus().equals("BOOKED")) {
                System.out.println("Seat " + seatNumber + " is already BOOKED.");
                return null;
            }

            selectedShowSeats.add(showSeat);
            selectedSeats.add(showSeat.getSeat());
        }

        double total = priceCalculator.calculateTotal(selectedSeats);

        for (ShowSeat showSeat : selectedShowSeats) {
            showSeat.bookSeat();
        }

        Booking booking = new Booking(show, selectedShowSeats, total);

        // Payment is passed as the interface type, so the service stays independent
        // of UPI, Card or Cash.
        boolean paymentSuccessful = payment.pay(total);

        if (!paymentSuccessful) {
            booking.cancelBooking();
            System.out.println("Payment failed. Booking was not confirmed.");
            return null;
        }

        booking.confirmBooking();
        return booking;
    }

    public boolean makePayment(Booking booking, Payment payment) {
        if (booking == null) return false;
        return payment.pay(booking.getTotalAmount());
    }

    public void cancelBooking(Booking booking) {
        if (booking == null) {
            System.out.println("No booking found.");
            return;
        }

        booking.cancelBooking();
        System.out.println("Booking " + booking.getBookingId()
                + " cancelled. Seats are AVAILABLE again.");
    }

    private ShowSeat findShowSeat(Show show, int seatNumber) {
        for (ShowSeat showSeat : show.getShowSeats()) {
            if (showSeat.getSeat().getSeatNumber() == seatNumber) {
                return showSeat;
            }
        }
        return null;
    }
}
