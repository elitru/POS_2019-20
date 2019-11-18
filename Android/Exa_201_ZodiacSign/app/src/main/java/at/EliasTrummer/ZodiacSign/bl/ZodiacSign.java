package at.EliasTrummer.ZodiacSign.bl;

import androidx.recyclerview.widget.RecyclerView;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

public class ZodiacSign {

    private String name;
    private MonthDay startDate;
    private int drawableId;

    public static final  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd. MMMM");

    public ZodiacSign(String name, MonthDay startDate, int drawableId) {
        this.name = name;
        this.startDate = startDate;
        this.drawableId = drawableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDateString() {
        return dtf.format(startDate);
    }

    public MonthDay getStartDate() {
        return startDate;
    }

    public void setStartDate(MonthDay startDate) {
        this.startDate = startDate;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }
}
