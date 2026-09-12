import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Cinema cinema = createCinema();
        List<Movie> movies = createMovies();
        List<Show> shows = createShows(cinema, movies);

        BookingService bookingService = new BookingService();
        PriceCalculator priceCalculator = new PriceCalculator();
        TicketPrinter ticketPrinter = new TicketPrinter();

        Booking lastBooking = null;

        while (true) {
            System.out.println("\n===== MOVIE TICKET BOOKING =====");
            System.out.println("1. List Movies");
            System.out.println("2. List Shows");
            System.out.println("3. Show Seats");
            System.out.println("4. Book Seat and Pay");
            System.out.println("5. Print Last Ticket");
            System.out.println("6. Cancel Last Booking");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = readInt(scanner);

            switch (choice) {
                case 1:
                    bookingService.listMovies(movies);
                    break;

                case 2:
                    bookingService.listShows(shows, chooseMovie(scanner, movies));
                    break;

                case 3:
                    Show show = chooseShow(scanner, shows);
                    if (show != null) bookingService.showSeats(show);
                    break;

                case 4:
                    Show selectedShow = chooseShow(scanner, shows);
                    if (selectedShow == null) break;

                    bookingService.showSeats(selectedShow);

                    System.out.print("Enter seat number to book: ");
                    int seatNumber = readInt(scanner);

                    System.out.println("1. UPI");
                    System.out.println("2. Card");
                    System.out.println("3. Cash");
                    System.out.print("Choose payment method: ");
                    int paymentChoice = readInt(scanner);

                    Payment payment = createPayment(paymentChoice);

                    if (payment == null) {
                        System.out.println("Invalid payment choice.");
                        break;
                    }

                    // Runtime polymorphism: the Payment reference calls the
                    // implementation chosen at runtime.
                    lastBooking = bookingService.bookSeats(
                            selectedShow,
                            Arrays.asList(seatNumber),
                            priceCalculator,
                            payment
                    );

                    if (lastBooking != null) {
                        ticketPrinter.printTicket(lastBooking);
                    }
                    break;

                case 5:
                    if (lastBooking == null) {
                        System.out.println("No booking available.");
                    } else {
                        ticketPrinter.printTicket(lastBooking);
                    }
                    break;

                case 6:
                    bookingService.cancelBooking(lastBooking);
                    break;

                case 7:
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid menu choice. Please try again.");
            }
        }
    }

    private static Cinema createCinema() {
        Cinema cinema = new Cinema("City Cinema");

        Screen screen1 = new Screen(1);
        Screen screen2 = new Screen(2);

        screen1.addSeat(new Seat(1, "SILVER"));
        screen1.addSeat(new Seat(2, "SILVER"));
        screen1.addSeat(new Seat(3, "GOLD"));
        screen1.addSeat(new Seat(4, "GOLD"));
        screen1.addSeat(new Seat(5, "PLATINUM"));

        screen2.addSeat(new Seat(1, "SILVER"));
        screen2.addSeat(new Seat(2, "GOLD"));
        screen2.addSeat(new Seat(3, "PLATINUM"));

        cinema.addScreen(screen1);
        cinema.addScreen(screen2);
        return cinema;
    }

    private static List<Movie> createMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Interstellar", "English", 169));
        movies.add(new Movie("3 Idiots", "Hindi", 170));
        movies.add(new Movie("Inception", "English", 148));
        return movies;
    }

    private static List<Show> createShows(Cinema cinema, List<Movie> movies) {
        List<Show> shows = new ArrayList<>();
        shows.add(new Show(movies.get(0), cinema.getScreens().get(0), "10:00 AM"));
        shows.add(new Show(movies.get(1), cinema.getScreens().get(0), "02:00 PM"));
        shows.add(new Show(movies.get(2), cinema.getScreens().get(1), "06:30 PM"));
        return shows;
    }

    private static Movie chooseMovie(Scanner scanner, List<Movie> movies) {
        System.out.print("Enter movie number: ");
        int number = readInt(scanner);

        if (number < 1 || number > movies.size()) {
            System.out.println("Invalid movie choice.");
            return null;
        }
        return movies.get(number - 1);
    }

    private static Show chooseShow(Scanner scanner, List<Show> shows) {
        System.out.println("\nAvailable Shows:");

        for (int i = 0; i < shows.size(); i++) {
            Show show = shows.get(i);
            System.out.println((i + 1) + ". " + show.getMovie().getTitle()
                    + " | Screen " + show.getScreen().getScreenNumber()
                    + " | " + show.getStartTime());
        }

        System.out.print("Enter show number: ");
        int number = readInt(scanner);

        if (number < 1 || number > shows.size()) {
            System.out.println("Invalid show choice.");
            return null;
        }
        return shows.get(number - 1);
    }

    private static Payment createPayment(int choice) {
        switch (choice) {
            case 1: return new UpiPayment();
            case 2: return new CardPayment();
            case 3: return new CashPayment();
            default: return null;
        }
    }

    private static int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
