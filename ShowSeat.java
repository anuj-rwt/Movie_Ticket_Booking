public class ShowSeat {
    private Seat seat;
    private String status;

    public ShowSeat(Seat seat) {
        this.seat = seat;
        this.status = "AVAILABLE";
    }

    public Seat getSeat() { return seat; }
    public String getStatus() { return status; }

    public boolean bookSeat() {
        if (status.equals("BOOKED")) {
            return false;
        }
        status = "BOOKED";
        return true;
    }

    public void cancelSeat() {
        status = "AVAILABLE";
    }
}
