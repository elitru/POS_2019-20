package at.dev4fun.a2_plf_preperation.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ThatThing extends Thing implements Parcelable {
    private LocalDate date;

    public ThatThing(int id, LocalDate date) {
        super(id);
        this.date = date;
    }

    protected ThatThing(Parcel in){
        super(in);
        this.date = LocalDate.ofEpochDay(in.readLong());
    }

    public ThatThing(String param){
        this(Integer.parseInt(param.split(",")[1]), LocalDate.parse(param.split(",")[2], DateTimeFormatter.ofPattern("d.M.yyyy")));
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static final Creator<ThatThing> CREATOR = new Creator<ThatThing>() {
        @Override
        public ThatThing createFromParcel(Parcel in) {
            return new ThatThing(in);
        }

        @Override
        public ThatThing[] newArray(int size) {
            return new ThatThing[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(date.toEpochDay());
    }
}
