import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private String name;
    private List<Screen> screens;

    public Cinema(String name) {
        this.name = name;
        this.screens = new ArrayList<>();
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }

    public String getName() { return name; }
    public List<Screen> getScreens() { return screens; }
}
