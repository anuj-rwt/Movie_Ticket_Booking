public class TicketPrinter {
    public void printTicket(Booking booking) {
        System.out.println("\n========== MOVIE TICKET ==========");
        System.out.println("Booking ID : " + booking.getBookingId());
        System.out.println("Movie      : " + booking.getShow().getMovie().getTitle());
        System.out.println("Screen     : " + booking.getShow().getScreen().getScreenNumber());
        System.out.println("Show Time  : " + booking.getShow().getStartTime());

        System.out.print("Seats      : ");
        for (int i = 0; i < booking.getShowSeats().size(); i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(booking.getShowSeats().get(i).getSeat().getSeatNumber());
        }

        System.out.println();
        System.out.println("Total      : Rs. " + booking.getTotalAmount());
        System.out.println("Status     : " + booking.getStatus());
        System.out.println("==================================\n");
    }
}
