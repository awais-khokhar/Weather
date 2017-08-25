package top.maweihao.weather.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 未来 24h 天气 RecyclerView 类
 * 未来 5day 天气 RecyclerView 类
 * Created by ma on 17-7-7.
 */

public class SingleWeather implements Parcelable {
    private String time;
    private int skyconId;
    private String skyconDesc;
    private String temperature;

    public SingleWeather(String time, int skyconId, String skyconDesc, String temperature) {
        this.time = time;
        this.skyconId = skyconId;
        this.skyconDesc = skyconDesc;
        this.temperature = temperature;
    }

    private SingleWeather(Parcel in) {
        this.time = in.readString();
        this.skyconId = in.readInt();
        this.skyconDesc = in.readString();
        this.temperature = in.readString();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSkyconId() {
        return skyconId;
    }

    public void setSkyconId(int skyconId) {
        this.skyconId = skyconId;
    }

    public String getSkyconDesc() {
        return skyconDesc;
    }

    public void setSkyconDesc(String skyconDesc) {
        this.skyconDesc = skyconDesc;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.time);
        dest.writeInt(this.skyconId);
        dest.writeString(this.skyconDesc);
        dest.writeString(this.temperature);
    }

    public static final Creator<SingleWeather> CREATOR = new Creator<SingleWeather>() {

        @Override
        public SingleWeather createFromParcel(Parcel source) {
            return new SingleWeather(source);
        }

        @Override
        public SingleWeather[] newArray(int size) {
            return new SingleWeather[size];
        }
    };
}
