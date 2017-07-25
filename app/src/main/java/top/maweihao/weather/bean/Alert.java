package top.maweihao.weather.bean;

import android.os.Parcel;
import android.os.Parcelable;

import top.maweihao.weather.R;

/**
 * bean for alert
 * Created by ma on 17-7-20.
 */

public class Alert implements Parcelable{

    public String status;
    //预警code字段，格式为AABB，AA取01-16，分别表示：台风、暴雨、暴雪、寒潮、大风、沙尘暴、高温、干旱、雷电、冰雹、霜冻、大雾、霾、道路结冰、森林火灾、雷雨大风，BB取01-04，分别表示：蓝色、黄色、橙色、红色。
    public int code;
    public int iconId;
    public int iconBgId;
    public String content;
    public String alertId;
    public String title;
    public String subtitle;

    public Alert() {
    }

    public Alert(String status, int code, String content, String alertId, String title, String subtitle) {
        this.status = status;
        this.code = code;
        this.content = content;
        this.alertId = alertId;
        this.title = title;
        this.subtitle = subtitle;

        iconId = chooseAlertIcon(code);
        iconBgId = chooseAlertBg(code);
    }

    private Alert(Parcel in) {
        this.status = in.readString();
        this.code = in.readInt();
        this.iconId = in.readInt();
        this.iconBgId = in.readInt();
        this.content = in.readString();
        this.alertId = in.readString();
        this.title = in.readString();
        this.subtitle = in.readString();
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
        dest.writeInt(this.iconId);
        dest.writeInt(this.iconBgId);
        dest.writeString(this.content);
        dest.writeString(this.alertId);
        dest.writeString(this.title);
        dest.writeString(this.subtitle);
    }

    private int chooseAlertIcon(int code) {
        switch (code / 100) {  //code 前两位
            case 1:
                return R.mipmap.ic_warning_typhoon;
            case 2:
                return R.mipmap.ic_warning_rain_storm;
            case 3:
                return R.mipmap.ic_warning_snow_storm;
            case 4:
                return R.mipmap.ic_warning_cold_wave;
            case 5:
                return R.mipmap.ic_warning_gale;
            case 6:
                return R.mipmap.ic_warning_sand_storm;
            case 7:
                return R.mipmap.ic_warning_heat_wave;
            case 8:
                return R.mipmap.ic_warning_drough;
            case 9:
                return R.mipmap.ic_warning_lighting;
            case 10:
                return R.mipmap.ic_warning_hail;
            case 11:
                return R.mipmap.ic_warning_frost;
            case 12:
                return R.mipmap.ic_warning_heavy_fog;
            case 13:
                return R.mipmap.ic_warning_haze;
            case 14:
                return R.mipmap.ic_warning_road_icing;
            case 15:
                return R.mipmap.ic_warn_forest_fire;
            case 16:
                return R.mipmap.ic_warn_thunderstorm;
            default:
                return R.mipmap.ic_warning_others;
        }
    }

    private int chooseAlertBg(int code) {
        switch (code % 100) {  //code 后两位
            case 1:
                return R.mipmap.ic_warn_blue_bg;
            case 2:
                return R.mipmap.ic_warn_yellow_bg;
            case 3:
                return R.mipmap.ic_warn_orange_bg;
            case 4:
                return R.mipmap.ic_warn_red_bg;
            default:
                return R.mipmap.ic_warn_grey_bg;
        }
    }

}
