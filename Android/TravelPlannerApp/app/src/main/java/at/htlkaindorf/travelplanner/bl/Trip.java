package at.htlkaindorf.travelplanner.bl;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Trip implements Serializable {
    private String city;
    private String country;
    private String countryCode;
    private LocalDate startDate;
    private int duration;

    public Trip(String city, String country, String countryCode, LocalDate startDate, int duration) {
        this.city = city;
        this.country = country;
        countryCode = countryCode;
        this.startDate = startDate;
        this.duration = duration;
    }

    public Trip(String param){
        String[] parts = param.split(" - ");
        this.city = parts[0];
        this.country = parts[1];
        this.countryCode = parts[2];
        this.startDate = LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("d.M.yyyy"));
        this.duration = Integer.parseInt(parts[4]);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
