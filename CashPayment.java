public class CashPayment implements Payment {
    @Override
    public boolean pay(double amount) {
        System.out.println("Collecting cash of Rs. " + amount);
        return amount > 0;
    }
}
