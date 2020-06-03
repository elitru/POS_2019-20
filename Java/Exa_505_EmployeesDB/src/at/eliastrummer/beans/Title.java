package at.eliastrummer.beans;

import java.time.LocalDate;

public class Title {
    private String title;
    private LocalDate from;
    private LocalDate to;

    public Title(String title, LocalDate from, LocalDate to) {
        this.title = title;
        this.from = from;
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }
}
