package top.maweihao.weather.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TallWidgetUpdateService extends Service {
    public TallWidgetUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
