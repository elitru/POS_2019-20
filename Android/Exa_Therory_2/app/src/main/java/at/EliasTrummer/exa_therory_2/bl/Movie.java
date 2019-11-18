package at.EliasTrummer.exa_therory_2.bl;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Movie {
    private String name;
    private YearMonth releaseDate;
    private int duration;

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("MMMM - yyyy");

    public Movie(String name, YearMonth releaseDate, int duration) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseDate(YearMonth releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getReleaseDate() {
        return DTF.format(releaseDate);
    }

    public String getDuration(){
        return String.format("%02d:%02d", duration / 60, duration % 60);
    }
}
