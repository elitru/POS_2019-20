package at.dev4fun.a2_plf_preperation.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Thing implements Parcelable {
    private int id;

    public Thing(int id) {
        this.id = id;
    }

    protected Thing(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<Thing> CREATOR = new Creator<Thing>() {
        @Override
        public Thing createFromParcel(Parcel in) {
            return new Thing(in);
        }

        @Override
        public Thing[] newArray(int size) {
            return new Thing[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
