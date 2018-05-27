package top.maweihao.weather.contract;

import github.hellocsl.simpleconfig.annotation.APPLY;
import github.hellocsl.simpleconfig.annotation.GET;


/**
 * 定义配置接口，注解注入
 * Created by limuyang on 2017/6/27.
 */

public interface PreferenceConfigContact {
    String COUNTY_NAME = "countyName";
    String LOCATION_DETAIL = "location_detail";
    String AUTO_LOCATE = "auto_locate";
    String COUNTY_NAME_LAST_UPDATE_TIME = "countyName_last_update_time";
    String REFRESH_INTERVAL = "refresh_interval";
    String WEATHER_NOW = "weather_now";
    String WEATHER_FULL = "weather_full";
    String WEATHER_HE_NOW = "weather_he_now";
    String LAST_SYNC_TIME = "last_sync_time";
    String WIDGET_UPDATE_INTERVAL = "widget_update_interval";

    String WEATHER_NOW_LAST_UPDATE_TIME = "weather_now_last_update_time";
    String WEATHER_FULL_LAST_UPDATE_TIME = "weather_full_last_update_time";
    String WEATHER_HE_NOW_LAST_UPDATE_TIME = "weather_he_now_last_update_time";
    String IP = "IP";
    String COORDINATE="coordinate";
    String COORDINATE_LAST_UPDATE_TIME="coordinate_last_update";
    String CURL="curl";
    String FURL="furl";

    String NOTIFICATION_TIME="notification_time";
    String NOTIFICATION="notification";
    String NOTIFICATION_ALARM = "alarm_notifications";
    String LAST_PUSH_TIME = "last_push_time";
    String LAST_JOB_SCHEDULE_TIME = "last_job_schedule";

    @GET(key = COUNTY_NAME)
    String getCountyName();

    @GET(key = LOCATION_DETAIL)
    String getLocationDetail(String defaultValue);

    @GET(key = AUTO_LOCATE)
    boolean getAutoLocate(boolean defaultValue);

    @GET(key = COUNTY_NAME_LAST_UPDATE_TIME)
    long getCountyNameLastUpdateTime();

    @GET(key = REFRESH_INTERVAL)
    int getRefreshInterval(int defaultValue);

    @GET(key = WEATHER_NOW)
    String getWeatherNow();

    @GET(key = WEATHER_FULL)
    String getWeatherFull();

    @GET(key = WEATHER_HE_NOW)
    String getWeatherHeNow();

    @GET(key = LAST_SYNC_TIME)
    Long getLastSyncTime(long defaultValue);

    @GET(key = WIDGET_UPDATE_INTERVAL)
    int getWidgetUpdateInterval(int defaultValue);

    @GET(key = WEATHER_NOW_LAST_UPDATE_TIME)
    long getWeatherNowLastUpdateTime(long defaultValue);

    @GET(key = WEATHER_FULL_LAST_UPDATE_TIME)
    long getWeatherFullLastUpdateTime(long defaultValue);

    @GET(key = WEATHER_HE_NOW_LAST_UPDATE_TIME)
    long getWeatherHeNowLastUpdateTime(long dafaultValue);

    @GET(key = IP)
    String getIp();

    @GET(key = COORDINATE)
    String getCoordinate();

    @GET(key = COORDINATE_LAST_UPDATE_TIME)
    long getCoordinateLastUpdateTime();

    @GET(key = CURL)
    String getCurl();

    @GET(key = FURL)
    String getFurl();

    @GET(key = NOTIFICATION_TIME)
    String getNotificationTime(String defaultValue);

    @GET(key = NOTIFICATION)
    boolean getNotification(boolean defaultValue);

    @GET(key = NOTIFICATION_ALARM)
    boolean getAlarmNotification(boolean defaultValue);

    @GET(key = LAST_PUSH_TIME)
    long getLastPushTime(long defaultValue);

    @GET(key = LAST_JOB_SCHEDULE_TIME)
    long getLastScheduleTime(long defaultValue);

    @APPLY(key = COUNTY_NAME)
    void applyCountyName(String value);

    @APPLY(key = LOCATION_DETAIL)
    void applyLocationDetail(String value);

    @APPLY(key = AUTO_LOCATE)
    void applyAutoLocate(boolean value);

    @APPLY(key = COUNTY_NAME_LAST_UPDATE_TIME)
    void applyCountyNameLastUpdateTime(long value);

    @APPLY(key = REFRESH_INTERVAL)
    void applyRefreshInterval(int value);

    @APPLY(key = WEATHER_NOW)
    void applyWeatherNow(String value);

    @APPLY(key = WEATHER_FULL)
    void applyWeatherFull(String value);

    @APPLY(key = WEATHER_HE_NOW)
    void applyWeatherHeNow(String value);

    @APPLY(key = LAST_SYNC_TIME)
    void applyLastSyncTime(Long value);

    @APPLY(key = WIDGET_UPDATE_INTERVAL)
    void applyWidgetUpdateInterval(int value);

    @APPLY(key = WEATHER_NOW_LAST_UPDATE_TIME)
    void applyWeatherNowLastUpdateTime(long value);

    @APPLY(key = WEATHER_FULL_LAST_UPDATE_TIME)
    void applyWeatherFullLastUpdateTime(long value);

    @APPLY(key = WEATHER_HE_NOW_LAST_UPDATE_TIME)
    void applyWeatherHeNowLastUpdateTime(long value);

    @APPLY(key = IP)
    void applyIp(String value);

    @APPLY(key = COORDINATE)
    void applyCoordinate(String value);

    @APPLY(key = COORDINATE_LAST_UPDATE_TIME)
    void applyCoordinateLastUpdateTime(long value);

    @APPLY(key = CURL)
    void applyCurl(String value);

    @APPLY(key = FURL)
    void applyFurl(String value);

    @APPLY(key = NOTIFICATION_TIME)
    void applyNotificationTime(String value);

    @APPLY(key = NOTIFICATION)
    void applyNotification(boolean value);

    @APPLY(key = NOTIFICATION_ALARM)
    void applyAlarmNotification(boolean value);

    @APPLY(key = LAST_PUSH_TIME)
    void applyLastPushTime(boolean value);

    @APPLY(key = LAST_JOB_SCHEDULE_TIME)
    void applyLastJobScheduleTime(long time);
}
