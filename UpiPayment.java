public class UpiPayment implements Payment {
    @Override
    public boolean pay(double amount) {
        System.out.println("Processing UPI payment of Rs. " + amount);
        return amount > 0;
    }
}
