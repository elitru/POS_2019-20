package at.dev4fun.a2_plf_preperation.beans;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class ThisThing extends Thing implements Parcelable {
    private Uri uri;

    public ThisThing(int id, Uri uri) {
        super(id);
        this.uri = uri;
    }

    public ThisThing(String param){
        this(Integer.parseInt(param.split(",")[1]), Uri.parse(param.split(",")[3]));
    }

    protected ThisThing(Parcel in){
        super(in);
        this.uri = Uri.parse(in.readString());
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public static final Creator<ThisThing> CREATOR = new Creator<ThisThing>() {
        @Override
        public ThisThing createFromParcel(Parcel in) {
            return new ThisThing(in);
        }

        @Override
        public ThisThing[] newArray(int size) {
            return new ThisThing[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(uri.toString());
    }
}
