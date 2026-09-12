import java.util.ArrayList;
import java.util.List;

public class Show {
    private Movie movie;
    private Screen screen;
    private String startTime;
    private List<ShowSeat> showSeats;

    public Show(Movie movie, Screen screen, String startTime) {
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.showSeats = new ArrayList<>();

        for (Seat seat : screen.getSeats()) {
            showSeats.add(new ShowSeat(seat));
        }
    }

    public Movie getMovie() { return movie; }
    public Screen getScreen() { return screen; }
    public String getStartTime() { return startTime; }
    public List<ShowSeat> getShowSeats() { return showSeats; }
}
