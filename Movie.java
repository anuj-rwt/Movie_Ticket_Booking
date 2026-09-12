public class Movie {
    private String title;
    private String language;
    private int duration;

    public Movie(String title, String language, int duration) {
        this.title = title;
        this.language = language;
        this.duration = duration;
    }

    public String getTitle() { return title; }
    public String getLanguage() { return language; }
    public int getDuration() { return duration; }
}
