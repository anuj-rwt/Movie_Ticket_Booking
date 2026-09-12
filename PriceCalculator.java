import java.util.List;

public class PriceCalculator {
    public double calculateTotal(List<Seat> seats) {
        double total = 0;

        for (Seat seat : seats) {
            switch (seat.getSeatType().toUpperCase()) {
                case "SILVER":
                    total += 150;
                    break;
                case "GOLD":
                    total += 250;
                    break;
                case "PLATINUM":
                    total += 400;
                    break;
                default:
                    System.out.println("Unknown seat type: " + seat.getSeatType());
            }
        }

        return total;
    }
}
