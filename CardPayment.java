public class CardPayment implements Payment {
    @Override
    public boolean pay(double amount) {
        System.out.println("Processing Card payment of Rs. " + amount);
        return amount > 0;
    }
}
