package top.maweihao.weather.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * bean for alert
 * Created by ma on 17-7-20.
 */

public class Alert implements Parcelable{

    public String status;
    public int code;
    public String description;
    public String pubdate;
    public String location;

    public Alert buildAlert() {

        return this;
    }

    private Alert(Parcel in) {
        this.status = in.readString();
        this.code = in.readInt();
        this.description = in.readString();
        this.pubdate = in.readString();
        this.location = in.readString();
    }

    public static final Creator<Alert> CREATOR = new Creator<Alert>() {
        @Override
        public Alert createFromParcel(Parcel in) {
            return new Alert(in);
        }

        @Override
        public Alert[] newArray(int size) {
            return new Alert[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeInt(this.code);
        dest.writeString(this.description);
        dest.writeString(this.pubdate);
        dest.writeString(this.location);
    }
}
