package top.maweihao.weather.bean;

import java.util.List;

public class ForecastBean {

    /**
     * status : ok
     * lang : zh_CN
     * result : {"hourly":{"status":"ok","content":"多云转晴，明天上午10点钟后转多云","skycon":[{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-24 20:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-24 21:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-24 22:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-24 23:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-25 00:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-25 01:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-25 02:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-25 03:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-25 04:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-25 05:00"},{"value":"CLEAR_DAY","datetime":"2017-07-25 06:00"},{"value":"CLEAR_DAY","datetime":"2017-07-25 07:00"},{"value":"CLEAR_DAY","datetime":"2017-07-25 08:00"},{"value":"CLEAR_DAY","datetime":"2017-07-25 09:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 10:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 11:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 12:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 13:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 14:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 15:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 16:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 17:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 18:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-25 19:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-25 20:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-25 21:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-25 22:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-25 23:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-26 00:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-26 01:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-26 02:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-26 03:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-26 04:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-26 05:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-26 06:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-26 07:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-26 08:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-26 09:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 10:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 11:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 12:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 13:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 14:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 15:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 16:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-26 17:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 18:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-26 19:00"}],"cloudrate":[{"value":0.41,"datetime":"2017-07-24 20:00"},{"value":0.35,"datetime":"2017-07-24 21:00"},{"value":0.29,"datetime":"2017-07-24 22:00"},{"value":0.26,"datetime":"2017-07-24 23:00"},{"value":0.24,"datetime":"2017-07-25 00:00"},{"value":0.23,"datetime":"2017-07-25 01:00"},{"value":0.22,"datetime":"2017-07-25 02:00"},{"value":0.18,"datetime":"2017-07-25 03:00"},{"value":0.13,"datetime":"2017-07-25 04:00"},{"value":0.08,"datetime":"2017-07-25 05:00"},{"value":0.05,"datetime":"2017-07-25 06:00"},{"value":0.04,"datetime":"2017-07-25 07:00"},{"value":0.07,"datetime":"2017-07-25 08:00"},{"value":0.13,"datetime":"2017-07-25 09:00"},{"value":0.21,"datetime":"2017-07-25 10:00"},{"value":0.28,"datetime":"2017-07-25 11:00"},{"value":0.31,"datetime":"2017-07-25 12:00"},{"value":0.32,"datetime":"2017-07-25 13:00"},{"value":0.3,"datetime":"2017-07-25 14:00"},{"value":0.27,"datetime":"2017-07-25 15:00"},{"value":0.24,"datetime":"2017-07-25 16:00"},{"value":0.23,"datetime":"2017-07-25 17:00"},{"value":0.25,"datetime":"2017-07-25 18:00"},{"value":0.28,"datetime":"2017-07-25 19:00"},{"value":0.27,"datetime":"2017-07-25 20:00"},{"value":0.19,"datetime":"2017-07-25 21:00"},{"value":0.09,"datetime":"2017-07-25 22:00"},{"value":0,"datetime":"2017-07-25 23:00"},{"value":0,"datetime":"2017-07-26 00:00"},{"value":0,"datetime":"2017-07-26 01:00"},{"value":0.04,"datetime":"2017-07-26 02:00"},{"value":0.16,"datetime":"2017-07-26 03:00"},{"value":0.28,"datetime":"2017-07-26 04:00"},{"value":0.37,"datetime":"2017-07-26 05:00"},{"value":0.38,"datetime":"2017-07-26 06:00"},{"value":0.34,"datetime":"2017-07-26 07:00"},{"value":0.28,"datetime":"2017-07-26 08:00"},{"value":0.21,"datetime":"2017-07-26 09:00"},{"value":0.15,"datetime":"2017-07-26 10:00"},{"value":0.1,"datetime":"2017-07-26 11:00"},{"value":0.07,"datetime":"2017-07-26 12:00"},{"value":0.06,"datetime":"2017-07-26 13:00"},{"value":0.08,"datetime":"2017-07-26 14:00"},{"value":0.13,"datetime":"2017-07-26 15:00"},{"value":0.18,"datetime":"2017-07-26 16:00"},{"value":0.21,"datetime":"2017-07-26 17:00"},{"value":0.2,"datetime":"2017-07-26 18:00"},{"value":0.16,"datetime":"2017-07-26 19:00"}],"aqi":[{"value":95,"datetime":"2017-07-24 20:00"},{"value":56,"datetime":"2017-07-24 21:00"},{"value":58,"datetime":"2017-07-24 22:00"},{"value":61,"datetime":"2017-07-24 23:00"},{"value":63,"datetime":"2017-07-25 00:00"},{"value":64,"datetime":"2017-07-25 01:00"},{"value":66,"datetime":"2017-07-25 02:00"},{"value":67,"datetime":"2017-07-25 03:00"},{"value":67,"datetime":"2017-07-25 04:00"},{"value":68,"datetime":"2017-07-25 05:00"},{"value":71,"datetime":"2017-07-25 06:00"},{"value":72,"datetime":"2017-07-25 07:00"},{"value":72,"datetime":"2017-07-25 08:00"},{"value":71,"datetime":"2017-07-25 09:00"},{"value":68,"datetime":"2017-07-25 10:00"},{"value":64,"datetime":"2017-07-25 11:00"},{"value":59,"datetime":"2017-07-25 12:00"},{"value":57,"datetime":"2017-07-25 13:00"},{"value":53,"datetime":"2017-07-25 14:00"},{"value":50,"datetime":"2017-07-25 15:00"},{"value":49,"datetime":"2017-07-25 16:00"},{"value":50,"datetime":"2017-07-25 17:00"},{"value":52,"datetime":"2017-07-25 18:00"},{"value":56,"datetime":"2017-07-25 19:00"},{"value":59,"datetime":"2017-07-25 20:00"},{"value":62,"datetime":"2017-07-25 21:00"},{"value":63,"datetime":"2017-07-25 22:00"},{"value":64,"datetime":"2017-07-25 23:00"},{"value":66,"datetime":"2017-07-26 00:00"},{"value":66,"datetime":"2017-07-26 01:00"},{"value":64,"datetime":"2017-07-26 02:00"},{"value":63,"datetime":"2017-07-26 03:00"},{"value":61,"datetime":"2017-07-26 04:00"},{"value":58,"datetime":"2017-07-26 05:00"},{"value":58,"datetime":"2017-07-26 06:00"},{"value":58,"datetime":"2017-07-26 07:00"},{"value":59,"datetime":"2017-07-26 08:00"},{"value":59,"datetime":"2017-07-26 09:00"},{"value":58,"datetime":"2017-07-26 10:00"},{"value":57,"datetime":"2017-07-26 11:00"},{"value":56,"datetime":"2017-07-26 12:00"},{"value":55,"datetime":"2017-07-26 13:00"},{"value":55,"datetime":"2017-07-26 14:00"},{"value":53,"datetime":"2017-07-26 15:00"},{"value":53,"datetime":"2017-07-26 16:00"},{"value":53,"datetime":"2017-07-26 17:00"},{"value":55,"datetime":"2017-07-26 18:00"},{"value":57,"datetime":"2017-07-26 19:00"}],"humidity":[{"value":0.59,"datetime":"2017-07-24 20:00"},{"value":0.61,"datetime":"2017-07-24 21:00"},{"value":0.62,"datetime":"2017-07-24 22:00"},{"value":0.63,"datetime":"2017-07-24 23:00"},{"value":0.63,"datetime":"2017-07-25 00:00"},{"value":0.64,"datetime":"2017-07-25 01:00"},{"value":0.64,"datetime":"2017-07-25 02:00"},{"value":0.65,"datetime":"2017-07-25 03:00"},{"value":0.66,"datetime":"2017-07-25 04:00"},{"value":0.67,"datetime":"2017-07-25 05:00"},{"value":0.68,"datetime":"2017-07-25 06:00"},{"value":0.68,"datetime":"2017-07-25 07:00"},{"value":0.67,"datetime":"2017-07-25 08:00"},{"value":0.64,"datetime":"2017-07-25 09:00"},{"value":0.6,"datetime":"2017-07-25 10:00"},{"value":0.57,"datetime":"2017-07-25 11:00"},{"value":0.53,"datetime":"2017-07-25 12:00"},{"value":0.51,"datetime":"2017-07-25 13:00"},{"value":0.5,"datetime":"2017-07-25 14:00"},{"value":0.5,"datetime":"2017-07-25 15:00"},{"value":0.52,"datetime":"2017-07-25 16:00"},{"value":0.57,"datetime":"2017-07-25 17:00"},{"value":0.64,"datetime":"2017-07-25 18:00"},{"value":0.72,"datetime":"2017-07-25 19:00"},{"value":0.79,"datetime":"2017-07-25 20:00"},{"value":0.84,"datetime":"2017-07-25 21:00"},{"value":0.87,"datetime":"2017-07-25 22:00"},{"value":0.89,"datetime":"2017-07-25 23:00"},{"value":0.91,"datetime":"2017-07-26 00:00"},{"value":0.92,"datetime":"2017-07-26 01:00"},{"value":0.94,"datetime":"2017-07-26 02:00"},{"value":0.95,"datetime":"2017-07-26 03:00"},{"value":0.96,"datetime":"2017-07-26 04:00"},{"value":0.95,"datetime":"2017-07-26 05:00"},{"value":0.92,"datetime":"2017-07-26 06:00"},{"value":0.88,"datetime":"2017-07-26 07:00"},{"value":0.82,"datetime":"2017-07-26 08:00"},{"value":0.76,"datetime":"2017-07-26 09:00"},{"value":0.7,"datetime":"2017-07-26 10:00"},{"value":0.64,"datetime":"2017-07-26 11:00"},{"value":0.59,"datetime":"2017-07-26 12:00"},{"value":0.56,"datetime":"2017-07-26 13:00"},{"value":0.54,"datetime":"2017-07-26 14:00"},{"value":0.55,"datetime":"2017-07-26 15:00"},{"value":0.58,"datetime":"2017-07-26 16:00"},{"value":0.62,"datetime":"2017-07-26 17:00"},{"value":0.67,"datetime":"2017-07-26 18:00"},{"value":0.73,"datetime":"2017-07-26 19:00"}],"pm25":[{"value":37,"datetime":"2017-07-24 20:00"},{"value":39,"datetime":"2017-07-24 21:00"},{"value":41,"datetime":"2017-07-24 22:00"},{"value":43,"datetime":"2017-07-24 23:00"},{"value":45,"datetime":"2017-07-25 00:00"},{"value":46,"datetime":"2017-07-25 01:00"},{"value":47,"datetime":"2017-07-25 02:00"},{"value":48,"datetime":"2017-07-25 03:00"},{"value":48,"datetime":"2017-07-25 04:00"},{"value":49,"datetime":"2017-07-25 05:00"},{"value":51,"datetime":"2017-07-25 06:00"},{"value":52,"datetime":"2017-07-25 07:00"},{"value":52,"datetime":"2017-07-25 08:00"},{"value":51,"datetime":"2017-07-25 09:00"},{"value":49,"datetime":"2017-07-25 10:00"},{"value":46,"datetime":"2017-07-25 11:00"},{"value":42,"datetime":"2017-07-25 12:00"},{"value":40,"datetime":"2017-07-25 13:00"},{"value":37,"datetime":"2017-07-25 14:00"},{"value":35,"datetime":"2017-07-25 15:00"},{"value":34,"datetime":"2017-07-25 16:00"},{"value":35,"datetime":"2017-07-25 17:00"},{"value":36,"datetime":"2017-07-25 18:00"},{"value":39,"datetime":"2017-07-25 19:00"},{"value":42,"datetime":"2017-07-25 20:00"},{"value":44,"datetime":"2017-07-25 21:00"},{"value":45,"datetime":"2017-07-25 22:00"},{"value":46,"datetime":"2017-07-25 23:00"},{"value":47,"datetime":"2017-07-26 00:00"},{"value":47,"datetime":"2017-07-26 01:00"},{"value":46,"datetime":"2017-07-26 02:00"},{"value":45,"datetime":"2017-07-26 03:00"},{"value":43,"datetime":"2017-07-26 04:00"},{"value":41,"datetime":"2017-07-26 05:00"},{"value":41,"datetime":"2017-07-26 06:00"},{"value":41,"datetime":"2017-07-26 07:00"},{"value":42,"datetime":"2017-07-26 08:00"},{"value":42,"datetime":"2017-07-26 09:00"},{"value":41,"datetime":"2017-07-26 10:00"},{"value":40,"datetime":"2017-07-26 11:00"},{"value":39,"datetime":"2017-07-26 12:00"},{"value":38,"datetime":"2017-07-26 13:00"},{"value":38,"datetime":"2017-07-26 14:00"},{"value":37,"datetime":"2017-07-26 15:00"},{"value":37,"datetime":"2017-07-26 16:00"},{"value":37,"datetime":"2017-07-26 17:00"},{"value":38,"datetime":"2017-07-26 18:00"},{"value":40,"datetime":"2017-07-26 19:00"}],"precipitation":[{"value":0,"datetime":"2017-07-24 20:00"},{"value":0,"datetime":"2017-07-24 21:00"},{"value":0,"datetime":"2017-07-24 22:00"},{"value":0,"datetime":"2017-07-24 23:00"},{"value":0,"datetime":"2017-07-25 00:00"},{"value":0,"datetime":"2017-07-25 01:00"},{"value":0,"datetime":"2017-07-25 02:00"},{"value":0,"datetime":"2017-07-25 03:00"},{"value":0,"datetime":"2017-07-25 04:00"},{"value":0,"datetime":"2017-07-25 05:00"},{"value":0,"datetime":"2017-07-25 06:00"},{"value":0,"datetime":"2017-07-25 07:00"},{"value":0,"datetime":"2017-07-25 08:00"},{"value":0,"datetime":"2017-07-25 09:00"},{"value":0,"datetime":"2017-07-25 10:00"},{"value":0,"datetime":"2017-07-25 11:00"},{"value":0,"datetime":"2017-07-25 12:00"},{"value":0,"datetime":"2017-07-25 13:00"},{"value":0,"datetime":"2017-07-25 14:00"},{"value":0,"datetime":"2017-07-25 15:00"},{"value":0,"datetime":"2017-07-25 16:00"},{"value":0,"datetime":"2017-07-25 17:00"},{"value":0,"datetime":"2017-07-25 18:00"},{"value":0,"datetime":"2017-07-25 19:00"},{"value":0,"datetime":"2017-07-25 20:00"},{"value":0,"datetime":"2017-07-25 21:00"},{"value":0,"datetime":"2017-07-25 22:00"},{"value":0,"datetime":"2017-07-25 23:00"},{"value":0,"datetime":"2017-07-26 00:00"},{"value":0,"datetime":"2017-07-26 01:00"},{"value":0,"datetime":"2017-07-26 02:00"},{"value":0,"datetime":"2017-07-26 03:00"},{"value":0,"datetime":"2017-07-26 04:00"},{"value":0,"datetime":"2017-07-26 05:00"},{"value":0,"datetime":"2017-07-26 06:00"},{"value":0,"datetime":"2017-07-26 07:00"},{"value":0,"datetime":"2017-07-26 08:00"},{"value":0,"datetime":"2017-07-26 09:00"},{"value":0,"datetime":"2017-07-26 10:00"},{"value":0,"datetime":"2017-07-26 11:00"},{"value":0,"datetime":"2017-07-26 12:00"},{"value":0,"datetime":"2017-07-26 13:00"},{"value":0,"datetime":"2017-07-26 14:00"},{"value":0,"datetime":"2017-07-26 15:00"},{"value":0,"datetime":"2017-07-26 16:00"},{"value":0,"datetime":"2017-07-26 17:00"},{"value":0,"datetime":"2017-07-26 18:00"},{"value":0,"datetime":"2017-07-26 19:00"}],"wind":[{"direction":132.64,"speed":6.56,"datetime":"2017-07-24 20:00"},{"direction":143.28,"speed":6.98,"datetime":"2017-07-24 21:00"},{"direction":158.72,"speed":6.69,"datetime":"2017-07-24 22:00"},{"direction":175.65,"speed":6.73,"datetime":"2017-07-24 23:00"},{"direction":186.97,"speed":7.28,"datetime":"2017-07-25 00:00"},{"direction":194.43,"speed":7.68,"datetime":"2017-07-25 01:00"},{"direction":203.23,"speed":7.49,"datetime":"2017-07-25 02:00"},{"direction":218.99,"speed":6.75,"datetime":"2017-07-25 03:00"},{"direction":242.35,"speed":6.24,"datetime":"2017-07-25 04:00"},{"direction":266.86,"speed":6.41,"datetime":"2017-07-25 05:00"},{"direction":286.63,"speed":6.86,"datetime":"2017-07-25 06:00"},{"direction":302.91,"speed":7.37,"datetime":"2017-07-25 07:00"},{"direction":316.7,"speed":8.01,"datetime":"2017-07-25 08:00"},{"direction":327.76,"speed":8.82,"datetime":"2017-07-25 09:00"},{"direction":336,"speed":9.67,"datetime":"2017-07-25 10:00"},{"direction":341.74,"speed":10.37,"datetime":"2017-07-25 11:00"},{"direction":345.33,"speed":10.74,"datetime":"2017-07-25 12:00"},{"direction":346.99,"speed":10.68,"datetime":"2017-07-25 13:00"},{"direction":346.55,"speed":10.08,"datetime":"2017-07-25 14:00"},{"direction":344.54,"speed":8.95,"datetime":"2017-07-25 15:00"},{"direction":348.22,"speed":7.47,"datetime":"2017-07-25 16:00"},{"direction":12.59,"speed":6.61,"datetime":"2017-07-25 17:00"},{"direction":48.17,"speed":9.53,"datetime":"2017-07-25 18:00"},{"direction":64.41,"speed":15.02,"datetime":"2017-07-25 19:00"},{"direction":71.59,"speed":19.25,"datetime":"2017-07-25 20:00"},{"direction":76.73,"speed":19.98,"datetime":"2017-07-25 21:00"},{"direction":82.65,"speed":18.24,"datetime":"2017-07-25 22:00"},{"direction":90.43,"speed":15.94,"datetime":"2017-07-25 23:00"},{"direction":98.84,"speed":14.55,"datetime":"2017-07-26 00:00"},{"direction":104.91,"speed":13.85,"datetime":"2017-07-26 01:00"},{"direction":106.26,"speed":13.1,"datetime":"2017-07-26 02:00"},{"direction":101.46,"speed":11.95,"datetime":"2017-07-26 03:00"},{"direction":92.66,"speed":10.99,"datetime":"2017-07-26 04:00"},{"direction":85.52,"speed":10.74,"datetime":"2017-07-26 05:00"},{"direction":85.5,"speed":11.08,"datetime":"2017-07-26 06:00"},{"direction":90.69,"speed":11.45,"datetime":"2017-07-26 07:00"},{"direction":98.17,"speed":11.37,"datetime":"2017-07-26 08:00"},{"direction":106.93,"speed":10.44,"datetime":"2017-07-26 09:00"},{"direction":116.15,"speed":9.12,"datetime":"2017-07-26 10:00"},{"direction":122.34,"speed":7.86,"datetime":"2017-07-26 11:00"},{"direction":119.68,"speed":6.83,"datetime":"2017-07-26 12:00"},{"direction":108.2,"speed":6.32,"datetime":"2017-07-26 13:00"},{"direction":94.63,"speed":6.6,"datetime":"2017-07-26 14:00"},{"direction":86.45,"speed":7.54,"datetime":"2017-07-26 15:00"},{"direction":84.12,"speed":9.09,"datetime":"2017-07-26 16:00"},{"direction":85.7,"speed":11.48,"datetime":"2017-07-26 17:00"},{"direction":89.04,"speed":14.79,"datetime":"2017-07-26 18:00"},{"direction":92.96,"speed":18.06,"datetime":"2017-07-26 19:00"}],"temperature":[{"value":32,"datetime":"2017-07-24 20:00"},{"value":31.25,"datetime":"2017-07-24 21:00"},{"value":31.02,"datetime":"2017-07-24 22:00"},{"value":30.98,"datetime":"2017-07-24 23:00"},{"value":30.89,"datetime":"2017-07-25 00:00"},{"value":30.67,"datetime":"2017-07-25 01:00"},{"value":30.29,"datetime":"2017-07-25 02:00"},{"value":29.7,"datetime":"2017-07-25 03:00"},{"value":29.24,"datetime":"2017-07-25 04:00"},{"value":29.24,"datetime":"2017-07-25 05:00"},{"value":29.92,"datetime":"2017-07-25 06:00"},{"value":31.09,"datetime":"2017-07-25 07:00"},{"value":32.48,"datetime":"2017-07-25 08:00"},{"value":33.84,"datetime":"2017-07-25 09:00"},{"value":35.04,"datetime":"2017-07-25 10:00"},{"value":35.98,"datetime":"2017-07-25 11:00"},{"value":36.42,"datetime":"2017-07-25 12:00"},{"value":36.7,"datetime":"2017-07-25 13:00"},{"value":36.95,"datetime":"2017-07-25 14:00"},{"value":37.18,"datetime":"2017-07-25 15:00"},{"value":37.18,"datetime":"2017-07-25 16:00"},{"value":36.65,"datetime":"2017-07-25 17:00"},{"value":35.14,"datetime":"2017-07-25 18:00"},{"value":32.85,"datetime":"2017-07-25 19:00"},{"value":30.75,"datetime":"2017-07-25 20:00"},{"value":29.36,"datetime":"2017-07-25 21:00"},{"value":28.58,"datetime":"2017-07-25 22:00"},{"value":28.16,"datetime":"2017-07-25 23:00"},{"value":27.88,"datetime":"2017-07-26 00:00"},{"value":27.61,"datetime":"2017-07-26 01:00"},{"value":27.27,"datetime":"2017-07-26 02:00"},{"value":26.83,"datetime":"2017-07-26 03:00"},{"value":26.53,"datetime":"2017-07-26 04:00"},{"value":26.63,"datetime":"2017-07-26 05:00"},{"value":27.36,"datetime":"2017-07-26 06:00"},{"value":28.56,"datetime":"2017-07-26 07:00"},{"value":30.03,"datetime":"2017-07-26 08:00"},{"value":31.55,"datetime":"2017-07-26 09:00"},{"value":32.99,"datetime":"2017-07-26 10:00"},{"value":34.22,"datetime":"2017-07-26 11:00"},{"value":35.15,"datetime":"2017-07-26 12:00"},{"value":35.82,"datetime":"2017-07-26 13:00"},{"value":36.23,"datetime":"2017-07-26 14:00"},{"value":36.48,"datetime":"2017-07-26 15:00"},{"value":36.43,"datetime":"2017-07-26 16:00"},{"value":35.79,"datetime":"2017-07-26 17:00"},{"value":34.01,"datetime":"2017-07-26 18:00"},{"value":31.82,"datetime":"2017-07-26 19:00"}]},"alert":{"status":"ok","content":[{"province":"江苏","status":"预警中","code":"0703","content":"江都区气象台2017年07月24日08时14分变更高温红色预警信号为高温橙色预警信号。预计今天我区大部分地区最高气温将升至39℃以上，请注意防范。","alertId":"32101241600000_20170724081541","city":"扬州","pubtimestamp":1.500855395E9,"latlon":[32.426564,119.567481],"county":"江都","request_status":"ok","title":"江苏扬州江都","title":"江都区气象局发布高温橙色预警[II级/严重]","regionId":"101190605"}]},"minutely":{"status":"ok","content":"未来两小时不会下雨，放心出门吧","probability":[0,0,0,0],"datasource":"radar","precipitation_2h":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"precipitation":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]},"daily":{"status":"ok","coldRisk":[{"index":"4","desc":"极易发","datetime":"2017-07-24"},{"index":"4","desc":"极易发","datetime":"2017-07-25"},{"index":"4","desc":"极易发","datetime":"2017-07-26"},{"index":"4","desc":"极易发","datetime":"2017-07-27"},{"index":"4","desc":"极易发","datetime":"2017-07-28"},{"index":"4","desc":"极易发","datetime":"2017-07-29"},{"index":"4","desc":"极易发","datetime":"2017-07-30"},{"index":"4","desc":"极易发","datetime":"2017-07-31"},{"index":"3","desc":"易发","datetime":"2017-08-01"},{"index":"3","desc":"易发","datetime":"2017-08-02"},{"index":"4","desc":"极易发","datetime":"2017-08-03"},{"index":"4","desc":"极易发","datetime":"2017-08-04"},{"index":"4","desc":"极易发","datetime":"2017-08-05"},{"index":"4","desc":"极易发","datetime":"2017-08-06"},{"index":"4","desc":"极易发","datetime":"2017-08-07"}],"temperature":[{"date":"2017-07-24","max":37.21,"avg":31.31,"min":28.13},{"date":"2017-07-25","max":37.18,"avg":32.68,"min":28.16},{"date":"2017-07-26","max":36.48,"avg":30.99,"min":26.53},{"date":"2017-07-27","max":36.04,"avg":30.59,"min":25.82},{"date":"2017-07-28","max":35.61,"avg":30.38,"min":26.2},{"date":"2017-07-29","max":34.33,"avg":29.37,"min":25.41},{"date":"2017-07-30","max":33.37,"avg":29.04,"min":25.78},{"date":"2017-07-31","max":34.56,"avg":29.13,"min":25.98},{"date":"2017-08-01","max":28.28,"avg":26.76,"min":25.6},{"date":"2017-08-02","max":30.03,"avg":27.34,"min":25.5},{"date":"2017-08-03","max":32.75,"avg":29.82,"min":25.66},{"date":"2017-08-04","max":32.91,"avg":29.15,"min":25.44},{"date":"2017-08-05","max":33.08,"avg":28.87,"min":25},{"date":"2017-08-06","max":32.27,"avg":26.38,"min":20.51},{"date":"2017-08-07","max":32.41,"avg":25.36,"min":18.33}],"skycon":[{"date":"2017-07-24","value":"PARTLY_CLOUDY_NIGHT"},{"date":"2017-07-25","value":"CLEAR_DAY"},{"date":"2017-07-26","value":"CLEAR_DAY"},{"date":"2017-07-27","value":"CLEAR_DAY"},{"date":"2017-07-28","value":"CLEAR_DAY"},{"date":"2017-07-29","value":"RAIN"},{"date":"2017-07-30","value":"RAIN"},{"date":"2017-07-31","value":"RAIN"},{"date":"2017-08-01","value":"RAIN"},{"date":"2017-08-02","value":"RAIN"},{"date":"2017-08-03","value":"RAIN"},{"date":"2017-08-04","value":"RAIN"},{"date":"2017-08-05","value":"RAIN"},{"date":"2017-08-06","value":"CLEAR_DAY"},{"date":"2017-08-07","value":"CLEAR_DAY"}],"cloudrate":[{"date":"2017-07-24","max":0.95,"avg":0.33,"min":0.15},{"date":"2017-07-25","max":0.32,"avg":0.19,"min":0},{"date":"2017-07-26","max":0.38,"avg":0.15,"min":0},{"date":"2017-07-27","max":0.2,"avg":0.08,"min":0},{"date":"2017-07-28","max":0.05,"avg":0.01,"min":0},{"date":"2017-07-29","max":0.9,"avg":0.27,"min":0},{"date":"2017-07-30","max":1,"avg":0.44,"min":0},{"date":"2017-07-31","max":1,"avg":0.73,"min":0.23},{"date":"2017-08-01","max":1,"avg":1,"min":0.99},{"date":"2017-08-02","max":1,"avg":0.65,"min":0},{"date":"2017-08-03","max":0.36,"avg":0.24,"min":0},{"date":"2017-08-04","max":0.53,"avg":0.49,"min":0.39},{"date":"2017-08-05","max":0.66,"avg":0.39,"min":0.08},{"date":"2017-08-06","max":0.11,"avg":0.03,"min":0},{"date":"2017-08-07","max":0.01,"avg":0,"min":0}],"aqi":[{"date":"2017-07-24","max":175,"avg":67.5,"min":56},{"date":"2017-07-25","max":72,"avg":61.96,"min":49},{"date":"2017-07-26","max":66,"avg":58.17,"min":53},{"date":"2017-07-27","max":61,"avg":54.5,"min":45},{"date":"2017-07-28","max":58,"avg":49.17,"min":39},{"date":"2017-07-29","max":50,"avg":41.12,"min":33},{"date":"2017-07-30","max":43,"avg":34.08,"min":27},{"date":"2017-07-31","max":35,"avg":29.38,"min":24},{"date":"2017-08-01","max":35,"avg":31.88,"min":26},{"date":"2017-08-02","max":35,"avg":30,"min":26},{"date":"2017-08-03","max":33,"avg":31.29,"min":30},{"date":"2017-08-04","max":67,"avg":52.83,"min":33},{"date":"2017-08-05","max":59,"avg":55.04,"min":52},{"date":"2017-08-06","max":80,"avg":63.21,"min":45},{"date":"2017-08-07","max":78,"avg":60.88,"min":45}],"humidity":[{"date":"2017-07-24","max":0.71,"avg":0.61,"min":0.46},{"date":"2017-07-25","max":0.89,"avg":0.65,"min":0.5},{"date":"2017-07-26","max":0.96,"avg":0.77,"min":0.54},{"date":"2017-07-27","max":0.9,"avg":0.74,"min":0.52},{"date":"2017-07-28","max":0.86,"avg":0.73,"min":0.53},{"date":"2017-07-29","max":0.95,"avg":0.8,"min":0.59},{"date":"2017-07-30","max":0.96,"avg":0.84,"min":0.64},{"date":"2017-07-31","max":0.91,"avg":0.8,"min":0.61},{"date":"2017-08-01","max":0.96,"avg":0.92,"min":0.88},{"date":"2017-08-02","max":0.95,"avg":0.89,"min":0.79},{"date":"2017-08-03","max":0.96,"avg":0.82,"min":0.73},{"date":"2017-08-04","max":1,"avg":0.87,"min":0.74},{"date":"2017-08-05","max":0.94,"avg":0.71,"min":0.46},{"date":"2017-08-06","max":0.89,"avg":0.64,"min":0.38},{"date":"2017-08-07","max":0.78,"avg":0.62,"min":0.46}],"astro":[{"date":"2017-07-24","sunset":{"time":"19:05"},"sunrise":{"time":"05:10"}},{"date":"2017-07-25","sunset":{"time":"19:04"},"sunrise":{"time":"05:10"}},{"date":"2017-07-26","sunset":{"time":"19:03"},"sunrise":{"time":"05:11"}},{"date":"2017-07-27","sunset":{"time":"19:03"},"sunrise":{"time":"05:12"}},{"date":"2017-07-28","sunset":{"time":"19:02"},"sunrise":{"time":"05:12"}},{"date":"2017-07-29","sunset":{"time":"19:01"},"sunrise":{"time":"05:13"}},{"date":"2017-07-30","sunset":{"time":"19:01"},"sunrise":{"time":"05:14"}},{"date":"2017-07-31","sunset":{"time":"19:00"},"sunrise":{"time":"05:14"}},{"date":"2017-08-01","sunset":{"time":"18:59"},"sunrise":{"time":"05:15"}},{"date":"2017-08-02","sunset":{"time":"18:58"},"sunrise":{"time":"05:16"}},{"date":"2017-08-03","sunset":{"time":"18:57"},"sunrise":{"time":"05:16"}},{"date":"2017-08-04","sunset":{"time":"18:56"},"sunrise":{"time":"05:17"}},{"date":"2017-08-05","sunset":{"time":"18:56"},"sunrise":{"time":"05:18"}},{"date":"2017-08-06","sunset":{"time":"18:55"},"sunrise":{"time":"05:18"}},{"date":"2017-08-07","sunset":{"time":"18:54"},"sunrise":{"time":"05:19"}}],"ultraviolet":[{"index":"4","desc":"强","datetime":"2017-07-24"},{"index":"4","desc":"强","datetime":"2017-07-25"},{"index":"4","desc":"强","datetime":"2017-07-26"},{"index":"4","desc":"强","datetime":"2017-07-27"},{"index":"4","desc":"强","datetime":"2017-07-28"},{"index":"4","desc":"强","datetime":"2017-07-29"},{"index":"4","desc":"强","datetime":"2017-07-30"},{"index":"3","desc":"中等","datetime":"2017-07-31"},{"index":"1","desc":"最弱","datetime":"2017-08-01"},{"index":"2","desc":"弱","datetime":"2017-08-02"},{"index":"4","desc":"强","datetime":"2017-08-03"},{"index":"3","desc":"中等","datetime":"2017-08-04"},{"index":"3","desc":"中等","datetime":"2017-08-05"},{"index":"4","desc":"强","datetime":"2017-08-06"},{"index":"4","desc":"强","datetime":"2017-08-07"}],"pm25":[{"date":"2017-07-24","max":80,"avg":40,"min":34},{"date":"2017-07-25","max":52,"avg":44.12,"min":34},{"date":"2017-07-26","max":47,"avg":41,"min":37},{"date":"2017-07-27","max":43,"avg":38.12,"min":31},{"date":"2017-07-28","max":41,"avg":34.17,"min":27},{"date":"2017-07-29","max":35,"avg":28.29,"min":22},{"date":"2017-07-30","max":30,"avg":23.04,"min":18},{"date":"2017-07-31","max":24,"avg":19.62,"min":16},{"date":"2017-08-01","max":24,"avg":21.42,"min":17},{"date":"2017-08-02","max":24,"avg":20.04,"min":17},{"date":"2017-08-03","max":22,"avg":20.96,"min":20},{"date":"2017-08-04","max":48,"avg":37.12,"min":22},{"date":"2017-08-05","max":42,"avg":38.42,"min":36},{"date":"2017-08-06","max":59,"avg":45.33,"min":31},{"date":"2017-08-07","max":57,"avg":43.25,"min":31}],"dressing":[{"index":"2","desc":"很热","datetime":"2017-07-24"},{"index":"1","desc":"极热","datetime":"2017-07-25"},{"index":"2","desc":"很热","datetime":"2017-07-26"},{"index":"2","desc":"很热","datetime":"2017-07-27"},{"index":"2","desc":"很热","datetime":"2017-07-28"},{"index":"3","desc":"热","datetime":"2017-07-29"},{"index":"3","desc":"热","datetime":"2017-07-30"},{"index":"3","desc":"热","datetime":"2017-07-31"},{"index":"2","desc":"很热","datetime":"2017-08-01"},{"index":"3","desc":"热","datetime":"2017-08-02"},{"index":"3","desc":"热","datetime":"2017-08-03"},{"index":"2","desc":"很热","datetime":"2017-08-04"},{"index":"2","desc":"很热","datetime":"2017-08-05"},{"index":"2","desc":"很热","datetime":"2017-08-06"},{"index":"3","desc":"热","datetime":"2017-08-07"}],"carWashing":[{"index":"1","desc":"适宜","datetime":"2017-07-24"},{"index":"1","desc":"适宜","datetime":"2017-07-25"},{"index":"1","desc":"适宜","datetime":"2017-07-26"},{"index":"1","desc":"适宜","datetime":"2017-07-27"},{"index":"1","desc":"适宜","datetime":"2017-07-28"},{"index":"3","desc":"较不适宜","datetime":"2017-07-29"},{"index":"3","desc":"较不适宜","datetime":"2017-07-30"},{"index":"3","desc":"较不适宜","datetime":"2017-07-31"},{"index":"3","desc":"较不适宜","datetime":"2017-08-01"},{"index":"3","desc":"较不适宜","datetime":"2017-08-02"},{"index":"3","desc":"较不适宜","datetime":"2017-08-03"},{"index":"3","desc":"较不适宜","datetime":"2017-08-04"},{"index":"3","desc":"较不适宜","datetime":"2017-08-05"},{"index":"1","desc":"适宜","datetime":"2017-08-06"},{"index":"1","desc":"适宜","datetime":"2017-08-07"}],"precipitation":[{"date":"2017-07-24","max":0,"avg":0,"min":0},{"date":"2017-07-25","max":0,"avg":0,"min":0},{"date":"2017-07-26","max":0,"avg":0,"min":0},{"date":"2017-07-27","max":0,"avg":0,"min":0},{"date":"2017-07-28","max":0,"avg":0,"min":0},{"date":"2017-07-29","max":0.9826,"avg":0.1967,"min":0},{"date":"2017-07-30","max":2.7369,"avg":0.6571,"min":0},{"date":"2017-07-31","max":1.7798,"avg":0.3455,"min":0},{"date":"2017-08-01","max":2.108,"avg":1.0671,"min":0.205},{"date":"2017-08-02","max":0.8963,"avg":0.2693,"min":0},{"date":"2017-08-03","max":0.5269,"avg":0.2642,"min":0},{"date":"2017-08-04","max":0.9411,"avg":0.5805,"min":0.26},{"date":"2017-08-05","max":0.6584,"avg":0.1291,"min":0},{"date":"2017-08-06","max":0,"avg":0,"min":0},{"date":"2017-08-07","max":0,"avg":0,"min":0}],"wind":[{"date":"2017-07-24","max":{"direction":248.5,"speed":7.78},"avg":{"direction":210.41,"speed":5.32},"min":{"direction":283.27,"speed":1.09}},{"date":"2017-07-25","max":{"direction":76.73,"speed":19.98},"avg":{"direction":19.33,"speed":10.23},"min":{"direction":242.35,"speed":6.24}},{"date":"2017-07-26","max":{"direction":104.27,"speed":20.16},"avg":{"direction":100.05,"speed":12.23},"min":{"direction":108.2,"speed":6.32}},{"date":"2017-07-27","max":{"direction":100.12,"speed":19.99},"avg":{"direction":106.34,"speed":12.74},"min":{"direction":116.26,"speed":9.86}},{"date":"2017-07-28","max":{"direction":95.35,"speed":21.59},"avg":{"direction":102.64,"speed":14.53},"min":{"direction":117.02,"speed":9.83}},{"date":"2017-07-29","max":{"direction":90.66,"speed":21.17},"avg":{"direction":92.95,"speed":16.6},"min":{"direction":84.56,"speed":10.49}},{"date":"2017-07-30","max":{"direction":95.39,"speed":21.54},"avg":{"direction":98.08,"speed":17.31},"min":{"direction":92.9,"speed":11.82}},{"date":"2017-07-31","max":{"direction":104.33,"speed":26.3},"avg":{"direction":107.29,"speed":21.99},"min":{"direction":103.09,"speed":14.77}},{"date":"2017-08-01","max":{"direction":116.83,"speed":19.53},"avg":{"direction":93.41,"speed":14.73},"min":{"direction":68.97,"speed":11.19}},{"date":"2017-08-02","max":{"direction":72.31,"speed":31.3},"avg":{"direction":73.16,"speed":22.02},"min":{"direction":75.24,"speed":11.62}},{"date":"2017-08-03","max":{"direction":6.36,"speed":20.54},"avg":{"direction":21.91,"speed":17.49},"min":{"direction":38.95,"speed":14.68}},{"date":"2017-08-04","max":{"direction":321.15,"speed":23.42},"avg":{"direction":340.79,"speed":13.34},"min":{"direction":58.13,"speed":4.91}},{"date":"2017-08-05","max":{"direction":322.63,"speed":18.98},"avg":{"direction":329.88,"speed":14.78},"min":{"direction":322.13,"speed":13.37}},{"date":"2017-08-06","max":{"direction":13.4,"speed":12.73},"avg":{"direction":13.63,"speed":8.85},"min":{"direction":17.03,"speed":5.4}},{"date":"2017-08-07","max":{"direction":64.7,"speed":13.1},"avg":{"direction":78.26,"speed":8.32},"min":{"direction":64.28,"speed":4.35}}]},"primary":0}
     * server_time : 1500898189
     * api_status : active
     * tzshift : 28800
     * api_version : v2.2
     * unit : metric
     * title : [32.450265,119.704311]
     */

    private String status;
    private String lang;
    private ResultBean result;
    private int server_time;
    private String api_status;
    private int tzshift;
    private String api_version;
    private String unit;
    private List<Double> location;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getServer_time() {
        return server_time;
    }

    public void setServer_time(int server_time) {
        this.server_time = server_time;
    }

    public String getApi_status() {
        return api_status;
    }

    public void setApi_status(String api_status) {
        this.api_status = api_status;
    }

    public int getTzshift() {
        return tzshift;
    }

    public void setTzshift(int tzshift) {
        this.tzshift = tzshift;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

    public static class ResultBean {
        /**
         * hourly : {"status":"ok","content":"多云转晴，明天上午10点钟后转多云","skycon":[{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-24 20:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-24 21:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-24 22:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-24 23:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-25 00:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-25 01:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-25 02:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-25 03:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-25 04:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-25 05:00"},{"value":"CLEAR_DAY","datetime":"2017-07-25 06:00"},{"value":"CLEAR_DAY","datetime":"2017-07-25 07:00"},{"value":"CLEAR_DAY","datetime":"2017-07-25 08:00"},{"value":"CLEAR_DAY","datetime":"2017-07-25 09:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 10:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 11:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 12:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 13:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 14:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 15:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 16:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 17:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 18:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-25 19:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-25 20:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-25 21:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-25 22:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-25 23:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-26 00:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-26 01:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-26 02:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-26 03:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-26 04:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-26 05:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-26 06:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-26 07:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-26 08:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-26 09:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 10:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 11:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 12:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 13:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 14:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 15:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 16:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-26 17:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 18:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-26 19:00"}],"cloudrate":[{"value":0.41,"datetime":"2017-07-24 20:00"},{"value":0.35,"datetime":"2017-07-24 21:00"},{"value":0.29,"datetime":"2017-07-24 22:00"},{"value":0.26,"datetime":"2017-07-24 23:00"},{"value":0.24,"datetime":"2017-07-25 00:00"},{"value":0.23,"datetime":"2017-07-25 01:00"},{"value":0.22,"datetime":"2017-07-25 02:00"},{"value":0.18,"datetime":"2017-07-25 03:00"},{"value":0.13,"datetime":"2017-07-25 04:00"},{"value":0.08,"datetime":"2017-07-25 05:00"},{"value":0.05,"datetime":"2017-07-25 06:00"},{"value":0.04,"datetime":"2017-07-25 07:00"},{"value":0.07,"datetime":"2017-07-25 08:00"},{"value":0.13,"datetime":"2017-07-25 09:00"},{"value":0.21,"datetime":"2017-07-25 10:00"},{"value":0.28,"datetime":"2017-07-25 11:00"},{"value":0.31,"datetime":"2017-07-25 12:00"},{"value":0.32,"datetime":"2017-07-25 13:00"},{"value":0.3,"datetime":"2017-07-25 14:00"},{"value":0.27,"datetime":"2017-07-25 15:00"},{"value":0.24,"datetime":"2017-07-25 16:00"},{"value":0.23,"datetime":"2017-07-25 17:00"},{"value":0.25,"datetime":"2017-07-25 18:00"},{"value":0.28,"datetime":"2017-07-25 19:00"},{"value":0.27,"datetime":"2017-07-25 20:00"},{"value":0.19,"datetime":"2017-07-25 21:00"},{"value":0.09,"datetime":"2017-07-25 22:00"},{"value":0,"datetime":"2017-07-25 23:00"},{"value":0,"datetime":"2017-07-26 00:00"},{"value":0,"datetime":"2017-07-26 01:00"},{"value":0.04,"datetime":"2017-07-26 02:00"},{"value":0.16,"datetime":"2017-07-26 03:00"},{"value":0.28,"datetime":"2017-07-26 04:00"},{"value":0.37,"datetime":"2017-07-26 05:00"},{"value":0.38,"datetime":"2017-07-26 06:00"},{"value":0.34,"datetime":"2017-07-26 07:00"},{"value":0.28,"datetime":"2017-07-26 08:00"},{"value":0.21,"datetime":"2017-07-26 09:00"},{"value":0.15,"datetime":"2017-07-26 10:00"},{"value":0.1,"datetime":"2017-07-26 11:00"},{"value":0.07,"datetime":"2017-07-26 12:00"},{"value":0.06,"datetime":"2017-07-26 13:00"},{"value":0.08,"datetime":"2017-07-26 14:00"},{"value":0.13,"datetime":"2017-07-26 15:00"},{"value":0.18,"datetime":"2017-07-26 16:00"},{"value":0.21,"datetime":"2017-07-26 17:00"},{"value":0.2,"datetime":"2017-07-26 18:00"},{"value":0.16,"datetime":"2017-07-26 19:00"}],"aqi":[{"value":95,"datetime":"2017-07-24 20:00"},{"value":56,"datetime":"2017-07-24 21:00"},{"value":58,"datetime":"2017-07-24 22:00"},{"value":61,"datetime":"2017-07-24 23:00"},{"value":63,"datetime":"2017-07-25 00:00"},{"value":64,"datetime":"2017-07-25 01:00"},{"value":66,"datetime":"2017-07-25 02:00"},{"value":67,"datetime":"2017-07-25 03:00"},{"value":67,"datetime":"2017-07-25 04:00"},{"value":68,"datetime":"2017-07-25 05:00"},{"value":71,"datetime":"2017-07-25 06:00"},{"value":72,"datetime":"2017-07-25 07:00"},{"value":72,"datetime":"2017-07-25 08:00"},{"value":71,"datetime":"2017-07-25 09:00"},{"value":68,"datetime":"2017-07-25 10:00"},{"value":64,"datetime":"2017-07-25 11:00"},{"value":59,"datetime":"2017-07-25 12:00"},{"value":57,"datetime":"2017-07-25 13:00"},{"value":53,"datetime":"2017-07-25 14:00"},{"value":50,"datetime":"2017-07-25 15:00"},{"value":49,"datetime":"2017-07-25 16:00"},{"value":50,"datetime":"2017-07-25 17:00"},{"value":52,"datetime":"2017-07-25 18:00"},{"value":56,"datetime":"2017-07-25 19:00"},{"value":59,"datetime":"2017-07-25 20:00"},{"value":62,"datetime":"2017-07-25 21:00"},{"value":63,"datetime":"2017-07-25 22:00"},{"value":64,"datetime":"2017-07-25 23:00"},{"value":66,"datetime":"2017-07-26 00:00"},{"value":66,"datetime":"2017-07-26 01:00"},{"value":64,"datetime":"2017-07-26 02:00"},{"value":63,"datetime":"2017-07-26 03:00"},{"value":61,"datetime":"2017-07-26 04:00"},{"value":58,"datetime":"2017-07-26 05:00"},{"value":58,"datetime":"2017-07-26 06:00"},{"value":58,"datetime":"2017-07-26 07:00"},{"value":59,"datetime":"2017-07-26 08:00"},{"value":59,"datetime":"2017-07-26 09:00"},{"value":58,"datetime":"2017-07-26 10:00"},{"value":57,"datetime":"2017-07-26 11:00"},{"value":56,"datetime":"2017-07-26 12:00"},{"value":55,"datetime":"2017-07-26 13:00"},{"value":55,"datetime":"2017-07-26 14:00"},{"value":53,"datetime":"2017-07-26 15:00"},{"value":53,"datetime":"2017-07-26 16:00"},{"value":53,"datetime":"2017-07-26 17:00"},{"value":55,"datetime":"2017-07-26 18:00"},{"value":57,"datetime":"2017-07-26 19:00"}],"humidity":[{"value":0.59,"datetime":"2017-07-24 20:00"},{"value":0.61,"datetime":"2017-07-24 21:00"},{"value":0.62,"datetime":"2017-07-24 22:00"},{"value":0.63,"datetime":"2017-07-24 23:00"},{"value":0.63,"datetime":"2017-07-25 00:00"},{"value":0.64,"datetime":"2017-07-25 01:00"},{"value":0.64,"datetime":"2017-07-25 02:00"},{"value":0.65,"datetime":"2017-07-25 03:00"},{"value":0.66,"datetime":"2017-07-25 04:00"},{"value":0.67,"datetime":"2017-07-25 05:00"},{"value":0.68,"datetime":"2017-07-25 06:00"},{"value":0.68,"datetime":"2017-07-25 07:00"},{"value":0.67,"datetime":"2017-07-25 08:00"},{"value":0.64,"datetime":"2017-07-25 09:00"},{"value":0.6,"datetime":"2017-07-25 10:00"},{"value":0.57,"datetime":"2017-07-25 11:00"},{"value":0.53,"datetime":"2017-07-25 12:00"},{"value":0.51,"datetime":"2017-07-25 13:00"},{"value":0.5,"datetime":"2017-07-25 14:00"},{"value":0.5,"datetime":"2017-07-25 15:00"},{"value":0.52,"datetime":"2017-07-25 16:00"},{"value":0.57,"datetime":"2017-07-25 17:00"},{"value":0.64,"datetime":"2017-07-25 18:00"},{"value":0.72,"datetime":"2017-07-25 19:00"},{"value":0.79,"datetime":"2017-07-25 20:00"},{"value":0.84,"datetime":"2017-07-25 21:00"},{"value":0.87,"datetime":"2017-07-25 22:00"},{"value":0.89,"datetime":"2017-07-25 23:00"},{"value":0.91,"datetime":"2017-07-26 00:00"},{"value":0.92,"datetime":"2017-07-26 01:00"},{"value":0.94,"datetime":"2017-07-26 02:00"},{"value":0.95,"datetime":"2017-07-26 03:00"},{"value":0.96,"datetime":"2017-07-26 04:00"},{"value":0.95,"datetime":"2017-07-26 05:00"},{"value":0.92,"datetime":"2017-07-26 06:00"},{"value":0.88,"datetime":"2017-07-26 07:00"},{"value":0.82,"datetime":"2017-07-26 08:00"},{"value":0.76,"datetime":"2017-07-26 09:00"},{"value":0.7,"datetime":"2017-07-26 10:00"},{"value":0.64,"datetime":"2017-07-26 11:00"},{"value":0.59,"datetime":"2017-07-26 12:00"},{"value":0.56,"datetime":"2017-07-26 13:00"},{"value":0.54,"datetime":"2017-07-26 14:00"},{"value":0.55,"datetime":"2017-07-26 15:00"},{"value":0.58,"datetime":"2017-07-26 16:00"},{"value":0.62,"datetime":"2017-07-26 17:00"},{"value":0.67,"datetime":"2017-07-26 18:00"},{"value":0.73,"datetime":"2017-07-26 19:00"}],"pm25":[{"value":37,"datetime":"2017-07-24 20:00"},{"value":39,"datetime":"2017-07-24 21:00"},{"value":41,"datetime":"2017-07-24 22:00"},{"value":43,"datetime":"2017-07-24 23:00"},{"value":45,"datetime":"2017-07-25 00:00"},{"value":46,"datetime":"2017-07-25 01:00"},{"value":47,"datetime":"2017-07-25 02:00"},{"value":48,"datetime":"2017-07-25 03:00"},{"value":48,"datetime":"2017-07-25 04:00"},{"value":49,"datetime":"2017-07-25 05:00"},{"value":51,"datetime":"2017-07-25 06:00"},{"value":52,"datetime":"2017-07-25 07:00"},{"value":52,"datetime":"2017-07-25 08:00"},{"value":51,"datetime":"2017-07-25 09:00"},{"value":49,"datetime":"2017-07-25 10:00"},{"value":46,"datetime":"2017-07-25 11:00"},{"value":42,"datetime":"2017-07-25 12:00"},{"value":40,"datetime":"2017-07-25 13:00"},{"value":37,"datetime":"2017-07-25 14:00"},{"value":35,"datetime":"2017-07-25 15:00"},{"value":34,"datetime":"2017-07-25 16:00"},{"value":35,"datetime":"2017-07-25 17:00"},{"value":36,"datetime":"2017-07-25 18:00"},{"value":39,"datetime":"2017-07-25 19:00"},{"value":42,"datetime":"2017-07-25 20:00"},{"value":44,"datetime":"2017-07-25 21:00"},{"value":45,"datetime":"2017-07-25 22:00"},{"value":46,"datetime":"2017-07-25 23:00"},{"value":47,"datetime":"2017-07-26 00:00"},{"value":47,"datetime":"2017-07-26 01:00"},{"value":46,"datetime":"2017-07-26 02:00"},{"value":45,"datetime":"2017-07-26 03:00"},{"value":43,"datetime":"2017-07-26 04:00"},{"value":41,"datetime":"2017-07-26 05:00"},{"value":41,"datetime":"2017-07-26 06:00"},{"value":41,"datetime":"2017-07-26 07:00"},{"value":42,"datetime":"2017-07-26 08:00"},{"value":42,"datetime":"2017-07-26 09:00"},{"value":41,"datetime":"2017-07-26 10:00"},{"value":40,"datetime":"2017-07-26 11:00"},{"value":39,"datetime":"2017-07-26 12:00"},{"value":38,"datetime":"2017-07-26 13:00"},{"value":38,"datetime":"2017-07-26 14:00"},{"value":37,"datetime":"2017-07-26 15:00"},{"value":37,"datetime":"2017-07-26 16:00"},{"value":37,"datetime":"2017-07-26 17:00"},{"value":38,"datetime":"2017-07-26 18:00"},{"value":40,"datetime":"2017-07-26 19:00"}],"precipitation":[{"value":0,"datetime":"2017-07-24 20:00"},{"value":0,"datetime":"2017-07-24 21:00"},{"value":0,"datetime":"2017-07-24 22:00"},{"value":0,"datetime":"2017-07-24 23:00"},{"value":0,"datetime":"2017-07-25 00:00"},{"value":0,"datetime":"2017-07-25 01:00"},{"value":0,"datetime":"2017-07-25 02:00"},{"value":0,"datetime":"2017-07-25 03:00"},{"value":0,"datetime":"2017-07-25 04:00"},{"value":0,"datetime":"2017-07-25 05:00"},{"value":0,"datetime":"2017-07-25 06:00"},{"value":0,"datetime":"2017-07-25 07:00"},{"value":0,"datetime":"2017-07-25 08:00"},{"value":0,"datetime":"2017-07-25 09:00"},{"value":0,"datetime":"2017-07-25 10:00"},{"value":0,"datetime":"2017-07-25 11:00"},{"value":0,"datetime":"2017-07-25 12:00"},{"value":0,"datetime":"2017-07-25 13:00"},{"value":0,"datetime":"2017-07-25 14:00"},{"value":0,"datetime":"2017-07-25 15:00"},{"value":0,"datetime":"2017-07-25 16:00"},{"value":0,"datetime":"2017-07-25 17:00"},{"value":0,"datetime":"2017-07-25 18:00"},{"value":0,"datetime":"2017-07-25 19:00"},{"value":0,"datetime":"2017-07-25 20:00"},{"value":0,"datetime":"2017-07-25 21:00"},{"value":0,"datetime":"2017-07-25 22:00"},{"value":0,"datetime":"2017-07-25 23:00"},{"value":0,"datetime":"2017-07-26 00:00"},{"value":0,"datetime":"2017-07-26 01:00"},{"value":0,"datetime":"2017-07-26 02:00"},{"value":0,"datetime":"2017-07-26 03:00"},{"value":0,"datetime":"2017-07-26 04:00"},{"value":0,"datetime":"2017-07-26 05:00"},{"value":0,"datetime":"2017-07-26 06:00"},{"value":0,"datetime":"2017-07-26 07:00"},{"value":0,"datetime":"2017-07-26 08:00"},{"value":0,"datetime":"2017-07-26 09:00"},{"value":0,"datetime":"2017-07-26 10:00"},{"value":0,"datetime":"2017-07-26 11:00"},{"value":0,"datetime":"2017-07-26 12:00"},{"value":0,"datetime":"2017-07-26 13:00"},{"value":0,"datetime":"2017-07-26 14:00"},{"value":0,"datetime":"2017-07-26 15:00"},{"value":0,"datetime":"2017-07-26 16:00"},{"value":0,"datetime":"2017-07-26 17:00"},{"value":0,"datetime":"2017-07-26 18:00"},{"value":0,"datetime":"2017-07-26 19:00"}],"wind":[{"direction":132.64,"speed":6.56,"datetime":"2017-07-24 20:00"},{"direction":143.28,"speed":6.98,"datetime":"2017-07-24 21:00"},{"direction":158.72,"speed":6.69,"datetime":"2017-07-24 22:00"},{"direction":175.65,"speed":6.73,"datetime":"2017-07-24 23:00"},{"direction":186.97,"speed":7.28,"datetime":"2017-07-25 00:00"},{"direction":194.43,"speed":7.68,"datetime":"2017-07-25 01:00"},{"direction":203.23,"speed":7.49,"datetime":"2017-07-25 02:00"},{"direction":218.99,"speed":6.75,"datetime":"2017-07-25 03:00"},{"direction":242.35,"speed":6.24,"datetime":"2017-07-25 04:00"},{"direction":266.86,"speed":6.41,"datetime":"2017-07-25 05:00"},{"direction":286.63,"speed":6.86,"datetime":"2017-07-25 06:00"},{"direction":302.91,"speed":7.37,"datetime":"2017-07-25 07:00"},{"direction":316.7,"speed":8.01,"datetime":"2017-07-25 08:00"},{"direction":327.76,"speed":8.82,"datetime":"2017-07-25 09:00"},{"direction":336,"speed":9.67,"datetime":"2017-07-25 10:00"},{"direction":341.74,"speed":10.37,"datetime":"2017-07-25 11:00"},{"direction":345.33,"speed":10.74,"datetime":"2017-07-25 12:00"},{"direction":346.99,"speed":10.68,"datetime":"2017-07-25 13:00"},{"direction":346.55,"speed":10.08,"datetime":"2017-07-25 14:00"},{"direction":344.54,"speed":8.95,"datetime":"2017-07-25 15:00"},{"direction":348.22,"speed":7.47,"datetime":"2017-07-25 16:00"},{"direction":12.59,"speed":6.61,"datetime":"2017-07-25 17:00"},{"direction":48.17,"speed":9.53,"datetime":"2017-07-25 18:00"},{"direction":64.41,"speed":15.02,"datetime":"2017-07-25 19:00"},{"direction":71.59,"speed":19.25,"datetime":"2017-07-25 20:00"},{"direction":76.73,"speed":19.98,"datetime":"2017-07-25 21:00"},{"direction":82.65,"speed":18.24,"datetime":"2017-07-25 22:00"},{"direction":90.43,"speed":15.94,"datetime":"2017-07-25 23:00"},{"direction":98.84,"speed":14.55,"datetime":"2017-07-26 00:00"},{"direction":104.91,"speed":13.85,"datetime":"2017-07-26 01:00"},{"direction":106.26,"speed":13.1,"datetime":"2017-07-26 02:00"},{"direction":101.46,"speed":11.95,"datetime":"2017-07-26 03:00"},{"direction":92.66,"speed":10.99,"datetime":"2017-07-26 04:00"},{"direction":85.52,"speed":10.74,"datetime":"2017-07-26 05:00"},{"direction":85.5,"speed":11.08,"datetime":"2017-07-26 06:00"},{"direction":90.69,"speed":11.45,"datetime":"2017-07-26 07:00"},{"direction":98.17,"speed":11.37,"datetime":"2017-07-26 08:00"},{"direction":106.93,"speed":10.44,"datetime":"2017-07-26 09:00"},{"direction":116.15,"speed":9.12,"datetime":"2017-07-26 10:00"},{"direction":122.34,"speed":7.86,"datetime":"2017-07-26 11:00"},{"direction":119.68,"speed":6.83,"datetime":"2017-07-26 12:00"},{"direction":108.2,"speed":6.32,"datetime":"2017-07-26 13:00"},{"direction":94.63,"speed":6.6,"datetime":"2017-07-26 14:00"},{"direction":86.45,"speed":7.54,"datetime":"2017-07-26 15:00"},{"direction":84.12,"speed":9.09,"datetime":"2017-07-26 16:00"},{"direction":85.7,"speed":11.48,"datetime":"2017-07-26 17:00"},{"direction":89.04,"speed":14.79,"datetime":"2017-07-26 18:00"},{"direction":92.96,"speed":18.06,"datetime":"2017-07-26 19:00"}],"temperature":[{"value":32,"datetime":"2017-07-24 20:00"},{"value":31.25,"datetime":"2017-07-24 21:00"},{"value":31.02,"datetime":"2017-07-24 22:00"},{"value":30.98,"datetime":"2017-07-24 23:00"},{"value":30.89,"datetime":"2017-07-25 00:00"},{"value":30.67,"datetime":"2017-07-25 01:00"},{"value":30.29,"datetime":"2017-07-25 02:00"},{"value":29.7,"datetime":"2017-07-25 03:00"},{"value":29.24,"datetime":"2017-07-25 04:00"},{"value":29.24,"datetime":"2017-07-25 05:00"},{"value":29.92,"datetime":"2017-07-25 06:00"},{"value":31.09,"datetime":"2017-07-25 07:00"},{"value":32.48,"datetime":"2017-07-25 08:00"},{"value":33.84,"datetime":"2017-07-25 09:00"},{"value":35.04,"datetime":"2017-07-25 10:00"},{"value":35.98,"datetime":"2017-07-25 11:00"},{"value":36.42,"datetime":"2017-07-25 12:00"},{"value":36.7,"datetime":"2017-07-25 13:00"},{"value":36.95,"datetime":"2017-07-25 14:00"},{"value":37.18,"datetime":"2017-07-25 15:00"},{"value":37.18,"datetime":"2017-07-25 16:00"},{"value":36.65,"datetime":"2017-07-25 17:00"},{"value":35.14,"datetime":"2017-07-25 18:00"},{"value":32.85,"datetime":"2017-07-25 19:00"},{"value":30.75,"datetime":"2017-07-25 20:00"},{"value":29.36,"datetime":"2017-07-25 21:00"},{"value":28.58,"datetime":"2017-07-25 22:00"},{"value":28.16,"datetime":"2017-07-25 23:00"},{"value":27.88,"datetime":"2017-07-26 00:00"},{"value":27.61,"datetime":"2017-07-26 01:00"},{"value":27.27,"datetime":"2017-07-26 02:00"},{"value":26.83,"datetime":"2017-07-26 03:00"},{"value":26.53,"datetime":"2017-07-26 04:00"},{"value":26.63,"datetime":"2017-07-26 05:00"},{"value":27.36,"datetime":"2017-07-26 06:00"},{"value":28.56,"datetime":"2017-07-26 07:00"},{"value":30.03,"datetime":"2017-07-26 08:00"},{"value":31.55,"datetime":"2017-07-26 09:00"},{"value":32.99,"datetime":"2017-07-26 10:00"},{"value":34.22,"datetime":"2017-07-26 11:00"},{"value":35.15,"datetime":"2017-07-26 12:00"},{"value":35.82,"datetime":"2017-07-26 13:00"},{"value":36.23,"datetime":"2017-07-26 14:00"},{"value":36.48,"datetime":"2017-07-26 15:00"},{"value":36.43,"datetime":"2017-07-26 16:00"},{"value":35.79,"datetime":"2017-07-26 17:00"},{"value":34.01,"datetime":"2017-07-26 18:00"},{"value":31.82,"datetime":"2017-07-26 19:00"}]}
         * alert : {"status":"ok","content":[{"province":"江苏","status":"预警中","code":"0703","content":"江都区气象台2017年07月24日08时14分变更高温红色预警信号为高温橙色预警信号。预计今天我区大部分地区最高气温将升至39℃以上，请注意防范。","alertId":"32101241600000_20170724081541","city":"扬州","pubtimestamp":1.500855395E9,"latlon":[32.426564,119.567481],"county":"江都","request_status":"ok","title":"江苏扬州江都","title":"江都区气象局发布高温橙色预警[II级/严重]","regionId":"101190605"}]}
         * minutely : {"status":"ok","content":"未来两小时不会下雨，放心出门吧","probability":[0,0,0,0],"datasource":"radar","precipitation_2h":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"precipitation":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}
         * daily : {"status":"ok","coldRisk":[{"index":"4","desc":"极易发","datetime":"2017-07-24"},{"index":"4","desc":"极易发","datetime":"2017-07-25"},{"index":"4","desc":"极易发","datetime":"2017-07-26"},{"index":"4","desc":"极易发","datetime":"2017-07-27"},{"index":"4","desc":"极易发","datetime":"2017-07-28"},{"index":"4","desc":"极易发","datetime":"2017-07-29"},{"index":"4","desc":"极易发","datetime":"2017-07-30"},{"index":"4","desc":"极易发","datetime":"2017-07-31"},{"index":"3","desc":"易发","datetime":"2017-08-01"},{"index":"3","desc":"易发","datetime":"2017-08-02"},{"index":"4","desc":"极易发","datetime":"2017-08-03"},{"index":"4","desc":"极易发","datetime":"2017-08-04"},{"index":"4","desc":"极易发","datetime":"2017-08-05"},{"index":"4","desc":"极易发","datetime":"2017-08-06"},{"index":"4","desc":"极易发","datetime":"2017-08-07"}],"temperature":[{"date":"2017-07-24","max":37.21,"avg":31.31,"min":28.13},{"date":"2017-07-25","max":37.18,"avg":32.68,"min":28.16},{"date":"2017-07-26","max":36.48,"avg":30.99,"min":26.53},{"date":"2017-07-27","max":36.04,"avg":30.59,"min":25.82},{"date":"2017-07-28","max":35.61,"avg":30.38,"min":26.2},{"date":"2017-07-29","max":34.33,"avg":29.37,"min":25.41},{"date":"2017-07-30","max":33.37,"avg":29.04,"min":25.78},{"date":"2017-07-31","max":34.56,"avg":29.13,"min":25.98},{"date":"2017-08-01","max":28.28,"avg":26.76,"min":25.6},{"date":"2017-08-02","max":30.03,"avg":27.34,"min":25.5},{"date":"2017-08-03","max":32.75,"avg":29.82,"min":25.66},{"date":"2017-08-04","max":32.91,"avg":29.15,"min":25.44},{"date":"2017-08-05","max":33.08,"avg":28.87,"min":25},{"date":"2017-08-06","max":32.27,"avg":26.38,"min":20.51},{"date":"2017-08-07","max":32.41,"avg":25.36,"min":18.33}],"skycon":[{"date":"2017-07-24","value":"PARTLY_CLOUDY_NIGHT"},{"date":"2017-07-25","value":"CLEAR_DAY"},{"date":"2017-07-26","value":"CLEAR_DAY"},{"date":"2017-07-27","value":"CLEAR_DAY"},{"date":"2017-07-28","value":"CLEAR_DAY"},{"date":"2017-07-29","value":"RAIN"},{"date":"2017-07-30","value":"RAIN"},{"date":"2017-07-31","value":"RAIN"},{"date":"2017-08-01","value":"RAIN"},{"date":"2017-08-02","value":"RAIN"},{"date":"2017-08-03","value":"RAIN"},{"date":"2017-08-04","value":"RAIN"},{"date":"2017-08-05","value":"RAIN"},{"date":"2017-08-06","value":"CLEAR_DAY"},{"date":"2017-08-07","value":"CLEAR_DAY"}],"cloudrate":[{"date":"2017-07-24","max":0.95,"avg":0.33,"min":0.15},{"date":"2017-07-25","max":0.32,"avg":0.19,"min":0},{"date":"2017-07-26","max":0.38,"avg":0.15,"min":0},{"date":"2017-07-27","max":0.2,"avg":0.08,"min":0},{"date":"2017-07-28","max":0.05,"avg":0.01,"min":0},{"date":"2017-07-29","max":0.9,"avg":0.27,"min":0},{"date":"2017-07-30","max":1,"avg":0.44,"min":0},{"date":"2017-07-31","max":1,"avg":0.73,"min":0.23},{"date":"2017-08-01","max":1,"avg":1,"min":0.99},{"date":"2017-08-02","max":1,"avg":0.65,"min":0},{"date":"2017-08-03","max":0.36,"avg":0.24,"min":0},{"date":"2017-08-04","max":0.53,"avg":0.49,"min":0.39},{"date":"2017-08-05","max":0.66,"avg":0.39,"min":0.08},{"date":"2017-08-06","max":0.11,"avg":0.03,"min":0},{"date":"2017-08-07","max":0.01,"avg":0,"min":0}],"aqi":[{"date":"2017-07-24","max":175,"avg":67.5,"min":56},{"date":"2017-07-25","max":72,"avg":61.96,"min":49},{"date":"2017-07-26","max":66,"avg":58.17,"min":53},{"date":"2017-07-27","max":61,"avg":54.5,"min":45},{"date":"2017-07-28","max":58,"avg":49.17,"min":39},{"date":"2017-07-29","max":50,"avg":41.12,"min":33},{"date":"2017-07-30","max":43,"avg":34.08,"min":27},{"date":"2017-07-31","max":35,"avg":29.38,"min":24},{"date":"2017-08-01","max":35,"avg":31.88,"min":26},{"date":"2017-08-02","max":35,"avg":30,"min":26},{"date":"2017-08-03","max":33,"avg":31.29,"min":30},{"date":"2017-08-04","max":67,"avg":52.83,"min":33},{"date":"2017-08-05","max":59,"avg":55.04,"min":52},{"date":"2017-08-06","max":80,"avg":63.21,"min":45},{"date":"2017-08-07","max":78,"avg":60.88,"min":45}],"humidity":[{"date":"2017-07-24","max":0.71,"avg":0.61,"min":0.46},{"date":"2017-07-25","max":0.89,"avg":0.65,"min":0.5},{"date":"2017-07-26","max":0.96,"avg":0.77,"min":0.54},{"date":"2017-07-27","max":0.9,"avg":0.74,"min":0.52},{"date":"2017-07-28","max":0.86,"avg":0.73,"min":0.53},{"date":"2017-07-29","max":0.95,"avg":0.8,"min":0.59},{"date":"2017-07-30","max":0.96,"avg":0.84,"min":0.64},{"date":"2017-07-31","max":0.91,"avg":0.8,"min":0.61},{"date":"2017-08-01","max":0.96,"avg":0.92,"min":0.88},{"date":"2017-08-02","max":0.95,"avg":0.89,"min":0.79},{"date":"2017-08-03","max":0.96,"avg":0.82,"min":0.73},{"date":"2017-08-04","max":1,"avg":0.87,"min":0.74},{"date":"2017-08-05","max":0.94,"avg":0.71,"min":0.46},{"date":"2017-08-06","max":0.89,"avg":0.64,"min":0.38},{"date":"2017-08-07","max":0.78,"avg":0.62,"min":0.46}],"astro":[{"date":"2017-07-24","sunset":{"time":"19:05"},"sunrise":{"time":"05:10"}},{"date":"2017-07-25","sunset":{"time":"19:04"},"sunrise":{"time":"05:10"}},{"date":"2017-07-26","sunset":{"time":"19:03"},"sunrise":{"time":"05:11"}},{"date":"2017-07-27","sunset":{"time":"19:03"},"sunrise":{"time":"05:12"}},{"date":"2017-07-28","sunset":{"time":"19:02"},"sunrise":{"time":"05:12"}},{"date":"2017-07-29","sunset":{"time":"19:01"},"sunrise":{"time":"05:13"}},{"date":"2017-07-30","sunset":{"time":"19:01"},"sunrise":{"time":"05:14"}},{"date":"2017-07-31","sunset":{"time":"19:00"},"sunrise":{"time":"05:14"}},{"date":"2017-08-01","sunset":{"time":"18:59"},"sunrise":{"time":"05:15"}},{"date":"2017-08-02","sunset":{"time":"18:58"},"sunrise":{"time":"05:16"}},{"date":"2017-08-03","sunset":{"time":"18:57"},"sunrise":{"time":"05:16"}},{"date":"2017-08-04","sunset":{"time":"18:56"},"sunrise":{"time":"05:17"}},{"date":"2017-08-05","sunset":{"time":"18:56"},"sunrise":{"time":"05:18"}},{"date":"2017-08-06","sunset":{"time":"18:55"},"sunrise":{"time":"05:18"}},{"date":"2017-08-07","sunset":{"time":"18:54"},"sunrise":{"time":"05:19"}}],"ultraviolet":[{"index":"4","desc":"强","datetime":"2017-07-24"},{"index":"4","desc":"强","datetime":"2017-07-25"},{"index":"4","desc":"强","datetime":"2017-07-26"},{"index":"4","desc":"强","datetime":"2017-07-27"},{"index":"4","desc":"强","datetime":"2017-07-28"},{"index":"4","desc":"强","datetime":"2017-07-29"},{"index":"4","desc":"强","datetime":"2017-07-30"},{"index":"3","desc":"中等","datetime":"2017-07-31"},{"index":"1","desc":"最弱","datetime":"2017-08-01"},{"index":"2","desc":"弱","datetime":"2017-08-02"},{"index":"4","desc":"强","datetime":"2017-08-03"},{"index":"3","desc":"中等","datetime":"2017-08-04"},{"index":"3","desc":"中等","datetime":"2017-08-05"},{"index":"4","desc":"强","datetime":"2017-08-06"},{"index":"4","desc":"强","datetime":"2017-08-07"}],"pm25":[{"date":"2017-07-24","max":80,"avg":40,"min":34},{"date":"2017-07-25","max":52,"avg":44.12,"min":34},{"date":"2017-07-26","max":47,"avg":41,"min":37},{"date":"2017-07-27","max":43,"avg":38.12,"min":31},{"date":"2017-07-28","max":41,"avg":34.17,"min":27},{"date":"2017-07-29","max":35,"avg":28.29,"min":22},{"date":"2017-07-30","max":30,"avg":23.04,"min":18},{"date":"2017-07-31","max":24,"avg":19.62,"min":16},{"date":"2017-08-01","max":24,"avg":21.42,"min":17},{"date":"2017-08-02","max":24,"avg":20.04,"min":17},{"date":"2017-08-03","max":22,"avg":20.96,"min":20},{"date":"2017-08-04","max":48,"avg":37.12,"min":22},{"date":"2017-08-05","max":42,"avg":38.42,"min":36},{"date":"2017-08-06","max":59,"avg":45.33,"min":31},{"date":"2017-08-07","max":57,"avg":43.25,"min":31}],"dressing":[{"index":"2","desc":"很热","datetime":"2017-07-24"},{"index":"1","desc":"极热","datetime":"2017-07-25"},{"index":"2","desc":"很热","datetime":"2017-07-26"},{"index":"2","desc":"很热","datetime":"2017-07-27"},{"index":"2","desc":"很热","datetime":"2017-07-28"},{"index":"3","desc":"热","datetime":"2017-07-29"},{"index":"3","desc":"热","datetime":"2017-07-30"},{"index":"3","desc":"热","datetime":"2017-07-31"},{"index":"2","desc":"很热","datetime":"2017-08-01"},{"index":"3","desc":"热","datetime":"2017-08-02"},{"index":"3","desc":"热","datetime":"2017-08-03"},{"index":"2","desc":"很热","datetime":"2017-08-04"},{"index":"2","desc":"很热","datetime":"2017-08-05"},{"index":"2","desc":"很热","datetime":"2017-08-06"},{"index":"3","desc":"热","datetime":"2017-08-07"}],"carWashing":[{"index":"1","desc":"适宜","datetime":"2017-07-24"},{"index":"1","desc":"适宜","datetime":"2017-07-25"},{"index":"1","desc":"适宜","datetime":"2017-07-26"},{"index":"1","desc":"适宜","datetime":"2017-07-27"},{"index":"1","desc":"适宜","datetime":"2017-07-28"},{"index":"3","desc":"较不适宜","datetime":"2017-07-29"},{"index":"3","desc":"较不适宜","datetime":"2017-07-30"},{"index":"3","desc":"较不适宜","datetime":"2017-07-31"},{"index":"3","desc":"较不适宜","datetime":"2017-08-01"},{"index":"3","desc":"较不适宜","datetime":"2017-08-02"},{"index":"3","desc":"较不适宜","datetime":"2017-08-03"},{"index":"3","desc":"较不适宜","datetime":"2017-08-04"},{"index":"3","desc":"较不适宜","datetime":"2017-08-05"},{"index":"1","desc":"适宜","datetime":"2017-08-06"},{"index":"1","desc":"适宜","datetime":"2017-08-07"}],"precipitation":[{"date":"2017-07-24","max":0,"avg":0,"min":0},{"date":"2017-07-25","max":0,"avg":0,"min":0},{"date":"2017-07-26","max":0,"avg":0,"min":0},{"date":"2017-07-27","max":0,"avg":0,"min":0},{"date":"2017-07-28","max":0,"avg":0,"min":0},{"date":"2017-07-29","max":0.9826,"avg":0.1967,"min":0},{"date":"2017-07-30","max":2.7369,"avg":0.6571,"min":0},{"date":"2017-07-31","max":1.7798,"avg":0.3455,"min":0},{"date":"2017-08-01","max":2.108,"avg":1.0671,"min":0.205},{"date":"2017-08-02","max":0.8963,"avg":0.2693,"min":0},{"date":"2017-08-03","max":0.5269,"avg":0.2642,"min":0},{"date":"2017-08-04","max":0.9411,"avg":0.5805,"min":0.26},{"date":"2017-08-05","max":0.6584,"avg":0.1291,"min":0},{"date":"2017-08-06","max":0,"avg":0,"min":0},{"date":"2017-08-07","max":0,"avg":0,"min":0}],"wind":[{"date":"2017-07-24","max":{"direction":248.5,"speed":7.78},"avg":{"direction":210.41,"speed":5.32},"min":{"direction":283.27,"speed":1.09}},{"date":"2017-07-25","max":{"direction":76.73,"speed":19.98},"avg":{"direction":19.33,"speed":10.23},"min":{"direction":242.35,"speed":6.24}},{"date":"2017-07-26","max":{"direction":104.27,"speed":20.16},"avg":{"direction":100.05,"speed":12.23},"min":{"direction":108.2,"speed":6.32}},{"date":"2017-07-27","max":{"direction":100.12,"speed":19.99},"avg":{"direction":106.34,"speed":12.74},"min":{"direction":116.26,"speed":9.86}},{"date":"2017-07-28","max":{"direction":95.35,"speed":21.59},"avg":{"direction":102.64,"speed":14.53},"min":{"direction":117.02,"speed":9.83}},{"date":"2017-07-29","max":{"direction":90.66,"speed":21.17},"avg":{"direction":92.95,"speed":16.6},"min":{"direction":84.56,"speed":10.49}},{"date":"2017-07-30","max":{"direction":95.39,"speed":21.54},"avg":{"direction":98.08,"speed":17.31},"min":{"direction":92.9,"speed":11.82}},{"date":"2017-07-31","max":{"direction":104.33,"speed":26.3},"avg":{"direction":107.29,"speed":21.99},"min":{"direction":103.09,"speed":14.77}},{"date":"2017-08-01","max":{"direction":116.83,"speed":19.53},"avg":{"direction":93.41,"speed":14.73},"min":{"direction":68.97,"speed":11.19}},{"date":"2017-08-02","max":{"direction":72.31,"speed":31.3},"avg":{"direction":73.16,"speed":22.02},"min":{"direction":75.24,"speed":11.62}},{"date":"2017-08-03","max":{"direction":6.36,"speed":20.54},"avg":{"direction":21.91,"speed":17.49},"min":{"direction":38.95,"speed":14.68}},{"date":"2017-08-04","max":{"direction":321.15,"speed":23.42},"avg":{"direction":340.79,"speed":13.34},"min":{"direction":58.13,"speed":4.91}},{"date":"2017-08-05","max":{"direction":322.63,"speed":18.98},"avg":{"direction":329.88,"speed":14.78},"min":{"direction":322.13,"speed":13.37}},{"date":"2017-08-06","max":{"direction":13.4,"speed":12.73},"avg":{"direction":13.63,"speed":8.85},"min":{"direction":17.03,"speed":5.4}},{"date":"2017-08-07","max":{"direction":64.7,"speed":13.1},"avg":{"direction":78.26,"speed":8.32},"min":{"direction":64.28,"speed":4.35}}]}
         * primary : 0
         */

        private HourlyBean hourly;
        private AlertBean alert;
        private MinutelyBean minutely;
        private DailyBean daily;
        private int primary;

        public HourlyBean getHourly() {
            return hourly;
        }

        public void setHourly(HourlyBean hourly) {
            this.hourly = hourly;
        }

        public AlertBean getAlert() {
            return alert;
        }

        public void setAlert(AlertBean alert) {
            this.alert = alert;
        }

        public MinutelyBean getMinutely() {
            return minutely;
        }

        public void setMinutely(MinutelyBean minutely) {
            this.minutely = minutely;
        }

        public DailyBean getDaily() {
            return daily;
        }

        public void setDaily(DailyBean daily) {
            this.daily = daily;
        }

        public int getPrimary() {
            return primary;
        }

        public void setPrimary(int primary) {
            this.primary = primary;
        }

        public static class HourlyBean {
            /**
             * status : ok
             * content : 多云转晴，明天上午10点钟后转多云
             * skycon : [{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-24 20:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-24 21:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-24 22:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-24 23:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-25 00:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-25 01:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-25 02:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-25 03:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-25 04:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-25 05:00"},{"value":"CLEAR_DAY","datetime":"2017-07-25 06:00"},{"value":"CLEAR_DAY","datetime":"2017-07-25 07:00"},{"value":"CLEAR_DAY","datetime":"2017-07-25 08:00"},{"value":"CLEAR_DAY","datetime":"2017-07-25 09:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 10:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 11:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 12:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 13:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 14:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 15:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 16:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 17:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-25 18:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-25 19:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-25 20:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-25 21:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-25 22:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-25 23:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-26 00:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-26 01:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-26 02:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-26 03:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-26 04:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-26 05:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-26 06:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-26 07:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-26 08:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-26 09:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 10:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 11:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 12:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 13:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 14:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 15:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 16:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-26 17:00"},{"value":"CLEAR_DAY","datetime":"2017-07-26 18:00"},{"value":"CLEAR_NIGHT","datetime":"2017-07-26 19:00"}]
             * cloudrate : [{"value":0.41,"datetime":"2017-07-24 20:00"},{"value":0.35,"datetime":"2017-07-24 21:00"},{"value":0.29,"datetime":"2017-07-24 22:00"},{"value":0.26,"datetime":"2017-07-24 23:00"},{"value":0.24,"datetime":"2017-07-25 00:00"},{"value":0.23,"datetime":"2017-07-25 01:00"},{"value":0.22,"datetime":"2017-07-25 02:00"},{"value":0.18,"datetime":"2017-07-25 03:00"},{"value":0.13,"datetime":"2017-07-25 04:00"},{"value":0.08,"datetime":"2017-07-25 05:00"},{"value":0.05,"datetime":"2017-07-25 06:00"},{"value":0.04,"datetime":"2017-07-25 07:00"},{"value":0.07,"datetime":"2017-07-25 08:00"},{"value":0.13,"datetime":"2017-07-25 09:00"},{"value":0.21,"datetime":"2017-07-25 10:00"},{"value":0.28,"datetime":"2017-07-25 11:00"},{"value":0.31,"datetime":"2017-07-25 12:00"},{"value":0.32,"datetime":"2017-07-25 13:00"},{"value":0.3,"datetime":"2017-07-25 14:00"},{"value":0.27,"datetime":"2017-07-25 15:00"},{"value":0.24,"datetime":"2017-07-25 16:00"},{"value":0.23,"datetime":"2017-07-25 17:00"},{"value":0.25,"datetime":"2017-07-25 18:00"},{"value":0.28,"datetime":"2017-07-25 19:00"},{"value":0.27,"datetime":"2017-07-25 20:00"},{"value":0.19,"datetime":"2017-07-25 21:00"},{"value":0.09,"datetime":"2017-07-25 22:00"},{"value":0,"datetime":"2017-07-25 23:00"},{"value":0,"datetime":"2017-07-26 00:00"},{"value":0,"datetime":"2017-07-26 01:00"},{"value":0.04,"datetime":"2017-07-26 02:00"},{"value":0.16,"datetime":"2017-07-26 03:00"},{"value":0.28,"datetime":"2017-07-26 04:00"},{"value":0.37,"datetime":"2017-07-26 05:00"},{"value":0.38,"datetime":"2017-07-26 06:00"},{"value":0.34,"datetime":"2017-07-26 07:00"},{"value":0.28,"datetime":"2017-07-26 08:00"},{"value":0.21,"datetime":"2017-07-26 09:00"},{"value":0.15,"datetime":"2017-07-26 10:00"},{"value":0.1,"datetime":"2017-07-26 11:00"},{"value":0.07,"datetime":"2017-07-26 12:00"},{"value":0.06,"datetime":"2017-07-26 13:00"},{"value":0.08,"datetime":"2017-07-26 14:00"},{"value":0.13,"datetime":"2017-07-26 15:00"},{"value":0.18,"datetime":"2017-07-26 16:00"},{"value":0.21,"datetime":"2017-07-26 17:00"},{"value":0.2,"datetime":"2017-07-26 18:00"},{"value":0.16,"datetime":"2017-07-26 19:00"}]
             * aqi : [{"value":95,"datetime":"2017-07-24 20:00"},{"value":56,"datetime":"2017-07-24 21:00"},{"value":58,"datetime":"2017-07-24 22:00"},{"value":61,"datetime":"2017-07-24 23:00"},{"value":63,"datetime":"2017-07-25 00:00"},{"value":64,"datetime":"2017-07-25 01:00"},{"value":66,"datetime":"2017-07-25 02:00"},{"value":67,"datetime":"2017-07-25 03:00"},{"value":67,"datetime":"2017-07-25 04:00"},{"value":68,"datetime":"2017-07-25 05:00"},{"value":71,"datetime":"2017-07-25 06:00"},{"value":72,"datetime":"2017-07-25 07:00"},{"value":72,"datetime":"2017-07-25 08:00"},{"value":71,"datetime":"2017-07-25 09:00"},{"value":68,"datetime":"2017-07-25 10:00"},{"value":64,"datetime":"2017-07-25 11:00"},{"value":59,"datetime":"2017-07-25 12:00"},{"value":57,"datetime":"2017-07-25 13:00"},{"value":53,"datetime":"2017-07-25 14:00"},{"value":50,"datetime":"2017-07-25 15:00"},{"value":49,"datetime":"2017-07-25 16:00"},{"value":50,"datetime":"2017-07-25 17:00"},{"value":52,"datetime":"2017-07-25 18:00"},{"value":56,"datetime":"2017-07-25 19:00"},{"value":59,"datetime":"2017-07-25 20:00"},{"value":62,"datetime":"2017-07-25 21:00"},{"value":63,"datetime":"2017-07-25 22:00"},{"value":64,"datetime":"2017-07-25 23:00"},{"value":66,"datetime":"2017-07-26 00:00"},{"value":66,"datetime":"2017-07-26 01:00"},{"value":64,"datetime":"2017-07-26 02:00"},{"value":63,"datetime":"2017-07-26 03:00"},{"value":61,"datetime":"2017-07-26 04:00"},{"value":58,"datetime":"2017-07-26 05:00"},{"value":58,"datetime":"2017-07-26 06:00"},{"value":58,"datetime":"2017-07-26 07:00"},{"value":59,"datetime":"2017-07-26 08:00"},{"value":59,"datetime":"2017-07-26 09:00"},{"value":58,"datetime":"2017-07-26 10:00"},{"value":57,"datetime":"2017-07-26 11:00"},{"value":56,"datetime":"2017-07-26 12:00"},{"value":55,"datetime":"2017-07-26 13:00"},{"value":55,"datetime":"2017-07-26 14:00"},{"value":53,"datetime":"2017-07-26 15:00"},{"value":53,"datetime":"2017-07-26 16:00"},{"value":53,"datetime":"2017-07-26 17:00"},{"value":55,"datetime":"2017-07-26 18:00"},{"value":57,"datetime":"2017-07-26 19:00"}]
             * humidity : [{"value":0.59,"datetime":"2017-07-24 20:00"},{"value":0.61,"datetime":"2017-07-24 21:00"},{"value":0.62,"datetime":"2017-07-24 22:00"},{"value":0.63,"datetime":"2017-07-24 23:00"},{"value":0.63,"datetime":"2017-07-25 00:00"},{"value":0.64,"datetime":"2017-07-25 01:00"},{"value":0.64,"datetime":"2017-07-25 02:00"},{"value":0.65,"datetime":"2017-07-25 03:00"},{"value":0.66,"datetime":"2017-07-25 04:00"},{"value":0.67,"datetime":"2017-07-25 05:00"},{"value":0.68,"datetime":"2017-07-25 06:00"},{"value":0.68,"datetime":"2017-07-25 07:00"},{"value":0.67,"datetime":"2017-07-25 08:00"},{"value":0.64,"datetime":"2017-07-25 09:00"},{"value":0.6,"datetime":"2017-07-25 10:00"},{"value":0.57,"datetime":"2017-07-25 11:00"},{"value":0.53,"datetime":"2017-07-25 12:00"},{"value":0.51,"datetime":"2017-07-25 13:00"},{"value":0.5,"datetime":"2017-07-25 14:00"},{"value":0.5,"datetime":"2017-07-25 15:00"},{"value":0.52,"datetime":"2017-07-25 16:00"},{"value":0.57,"datetime":"2017-07-25 17:00"},{"value":0.64,"datetime":"2017-07-25 18:00"},{"value":0.72,"datetime":"2017-07-25 19:00"},{"value":0.79,"datetime":"2017-07-25 20:00"},{"value":0.84,"datetime":"2017-07-25 21:00"},{"value":0.87,"datetime":"2017-07-25 22:00"},{"value":0.89,"datetime":"2017-07-25 23:00"},{"value":0.91,"datetime":"2017-07-26 00:00"},{"value":0.92,"datetime":"2017-07-26 01:00"},{"value":0.94,"datetime":"2017-07-26 02:00"},{"value":0.95,"datetime":"2017-07-26 03:00"},{"value":0.96,"datetime":"2017-07-26 04:00"},{"value":0.95,"datetime":"2017-07-26 05:00"},{"value":0.92,"datetime":"2017-07-26 06:00"},{"value":0.88,"datetime":"2017-07-26 07:00"},{"value":0.82,"datetime":"2017-07-26 08:00"},{"value":0.76,"datetime":"2017-07-26 09:00"},{"value":0.7,"datetime":"2017-07-26 10:00"},{"value":0.64,"datetime":"2017-07-26 11:00"},{"value":0.59,"datetime":"2017-07-26 12:00"},{"value":0.56,"datetime":"2017-07-26 13:00"},{"value":0.54,"datetime":"2017-07-26 14:00"},{"value":0.55,"datetime":"2017-07-26 15:00"},{"value":0.58,"datetime":"2017-07-26 16:00"},{"value":0.62,"datetime":"2017-07-26 17:00"},{"value":0.67,"datetime":"2017-07-26 18:00"},{"value":0.73,"datetime":"2017-07-26 19:00"}]
             * pm25 : [{"value":37,"datetime":"2017-07-24 20:00"},{"value":39,"datetime":"2017-07-24 21:00"},{"value":41,"datetime":"2017-07-24 22:00"},{"value":43,"datetime":"2017-07-24 23:00"},{"value":45,"datetime":"2017-07-25 00:00"},{"value":46,"datetime":"2017-07-25 01:00"},{"value":47,"datetime":"2017-07-25 02:00"},{"value":48,"datetime":"2017-07-25 03:00"},{"value":48,"datetime":"2017-07-25 04:00"},{"value":49,"datetime":"2017-07-25 05:00"},{"value":51,"datetime":"2017-07-25 06:00"},{"value":52,"datetime":"2017-07-25 07:00"},{"value":52,"datetime":"2017-07-25 08:00"},{"value":51,"datetime":"2017-07-25 09:00"},{"value":49,"datetime":"2017-07-25 10:00"},{"value":46,"datetime":"2017-07-25 11:00"},{"value":42,"datetime":"2017-07-25 12:00"},{"value":40,"datetime":"2017-07-25 13:00"},{"value":37,"datetime":"2017-07-25 14:00"},{"value":35,"datetime":"2017-07-25 15:00"},{"value":34,"datetime":"2017-07-25 16:00"},{"value":35,"datetime":"2017-07-25 17:00"},{"value":36,"datetime":"2017-07-25 18:00"},{"value":39,"datetime":"2017-07-25 19:00"},{"value":42,"datetime":"2017-07-25 20:00"},{"value":44,"datetime":"2017-07-25 21:00"},{"value":45,"datetime":"2017-07-25 22:00"},{"value":46,"datetime":"2017-07-25 23:00"},{"value":47,"datetime":"2017-07-26 00:00"},{"value":47,"datetime":"2017-07-26 01:00"},{"value":46,"datetime":"2017-07-26 02:00"},{"value":45,"datetime":"2017-07-26 03:00"},{"value":43,"datetime":"2017-07-26 04:00"},{"value":41,"datetime":"2017-07-26 05:00"},{"value":41,"datetime":"2017-07-26 06:00"},{"value":41,"datetime":"2017-07-26 07:00"},{"value":42,"datetime":"2017-07-26 08:00"},{"value":42,"datetime":"2017-07-26 09:00"},{"value":41,"datetime":"2017-07-26 10:00"},{"value":40,"datetime":"2017-07-26 11:00"},{"value":39,"datetime":"2017-07-26 12:00"},{"value":38,"datetime":"2017-07-26 13:00"},{"value":38,"datetime":"2017-07-26 14:00"},{"value":37,"datetime":"2017-07-26 15:00"},{"value":37,"datetime":"2017-07-26 16:00"},{"value":37,"datetime":"2017-07-26 17:00"},{"value":38,"datetime":"2017-07-26 18:00"},{"value":40,"datetime":"2017-07-26 19:00"}]
             * precipitation : [{"value":0,"datetime":"2017-07-24 20:00"},{"value":0,"datetime":"2017-07-24 21:00"},{"value":0,"datetime":"2017-07-24 22:00"},{"value":0,"datetime":"2017-07-24 23:00"},{"value":0,"datetime":"2017-07-25 00:00"},{"value":0,"datetime":"2017-07-25 01:00"},{"value":0,"datetime":"2017-07-25 02:00"},{"value":0,"datetime":"2017-07-25 03:00"},{"value":0,"datetime":"2017-07-25 04:00"},{"value":0,"datetime":"2017-07-25 05:00"},{"value":0,"datetime":"2017-07-25 06:00"},{"value":0,"datetime":"2017-07-25 07:00"},{"value":0,"datetime":"2017-07-25 08:00"},{"value":0,"datetime":"2017-07-25 09:00"},{"value":0,"datetime":"2017-07-25 10:00"},{"value":0,"datetime":"2017-07-25 11:00"},{"value":0,"datetime":"2017-07-25 12:00"},{"value":0,"datetime":"2017-07-25 13:00"},{"value":0,"datetime":"2017-07-25 14:00"},{"value":0,"datetime":"2017-07-25 15:00"},{"value":0,"datetime":"2017-07-25 16:00"},{"value":0,"datetime":"2017-07-25 17:00"},{"value":0,"datetime":"2017-07-25 18:00"},{"value":0,"datetime":"2017-07-25 19:00"},{"value":0,"datetime":"2017-07-25 20:00"},{"value":0,"datetime":"2017-07-25 21:00"},{"value":0,"datetime":"2017-07-25 22:00"},{"value":0,"datetime":"2017-07-25 23:00"},{"value":0,"datetime":"2017-07-26 00:00"},{"value":0,"datetime":"2017-07-26 01:00"},{"value":0,"datetime":"2017-07-26 02:00"},{"value":0,"datetime":"2017-07-26 03:00"},{"value":0,"datetime":"2017-07-26 04:00"},{"value":0,"datetime":"2017-07-26 05:00"},{"value":0,"datetime":"2017-07-26 06:00"},{"value":0,"datetime":"2017-07-26 07:00"},{"value":0,"datetime":"2017-07-26 08:00"},{"value":0,"datetime":"2017-07-26 09:00"},{"value":0,"datetime":"2017-07-26 10:00"},{"value":0,"datetime":"2017-07-26 11:00"},{"value":0,"datetime":"2017-07-26 12:00"},{"value":0,"datetime":"2017-07-26 13:00"},{"value":0,"datetime":"2017-07-26 14:00"},{"value":0,"datetime":"2017-07-26 15:00"},{"value":0,"datetime":"2017-07-26 16:00"},{"value":0,"datetime":"2017-07-26 17:00"},{"value":0,"datetime":"2017-07-26 18:00"},{"value":0,"datetime":"2017-07-26 19:00"}]
             * wind : [{"direction":132.64,"speed":6.56,"datetime":"2017-07-24 20:00"},{"direction":143.28,"speed":6.98,"datetime":"2017-07-24 21:00"},{"direction":158.72,"speed":6.69,"datetime":"2017-07-24 22:00"},{"direction":175.65,"speed":6.73,"datetime":"2017-07-24 23:00"},{"direction":186.97,"speed":7.28,"datetime":"2017-07-25 00:00"},{"direction":194.43,"speed":7.68,"datetime":"2017-07-25 01:00"},{"direction":203.23,"speed":7.49,"datetime":"2017-07-25 02:00"},{"direction":218.99,"speed":6.75,"datetime":"2017-07-25 03:00"},{"direction":242.35,"speed":6.24,"datetime":"2017-07-25 04:00"},{"direction":266.86,"speed":6.41,"datetime":"2017-07-25 05:00"},{"direction":286.63,"speed":6.86,"datetime":"2017-07-25 06:00"},{"direction":302.91,"speed":7.37,"datetime":"2017-07-25 07:00"},{"direction":316.7,"speed":8.01,"datetime":"2017-07-25 08:00"},{"direction":327.76,"speed":8.82,"datetime":"2017-07-25 09:00"},{"direction":336,"speed":9.67,"datetime":"2017-07-25 10:00"},{"direction":341.74,"speed":10.37,"datetime":"2017-07-25 11:00"},{"direction":345.33,"speed":10.74,"datetime":"2017-07-25 12:00"},{"direction":346.99,"speed":10.68,"datetime":"2017-07-25 13:00"},{"direction":346.55,"speed":10.08,"datetime":"2017-07-25 14:00"},{"direction":344.54,"speed":8.95,"datetime":"2017-07-25 15:00"},{"direction":348.22,"speed":7.47,"datetime":"2017-07-25 16:00"},{"direction":12.59,"speed":6.61,"datetime":"2017-07-25 17:00"},{"direction":48.17,"speed":9.53,"datetime":"2017-07-25 18:00"},{"direction":64.41,"speed":15.02,"datetime":"2017-07-25 19:00"},{"direction":71.59,"speed":19.25,"datetime":"2017-07-25 20:00"},{"direction":76.73,"speed":19.98,"datetime":"2017-07-25 21:00"},{"direction":82.65,"speed":18.24,"datetime":"2017-07-25 22:00"},{"direction":90.43,"speed":15.94,"datetime":"2017-07-25 23:00"},{"direction":98.84,"speed":14.55,"datetime":"2017-07-26 00:00"},{"direction":104.91,"speed":13.85,"datetime":"2017-07-26 01:00"},{"direction":106.26,"speed":13.1,"datetime":"2017-07-26 02:00"},{"direction":101.46,"speed":11.95,"datetime":"2017-07-26 03:00"},{"direction":92.66,"speed":10.99,"datetime":"2017-07-26 04:00"},{"direction":85.52,"speed":10.74,"datetime":"2017-07-26 05:00"},{"direction":85.5,"speed":11.08,"datetime":"2017-07-26 06:00"},{"direction":90.69,"speed":11.45,"datetime":"2017-07-26 07:00"},{"direction":98.17,"speed":11.37,"datetime":"2017-07-26 08:00"},{"direction":106.93,"speed":10.44,"datetime":"2017-07-26 09:00"},{"direction":116.15,"speed":9.12,"datetime":"2017-07-26 10:00"},{"direction":122.34,"speed":7.86,"datetime":"2017-07-26 11:00"},{"direction":119.68,"speed":6.83,"datetime":"2017-07-26 12:00"},{"direction":108.2,"speed":6.32,"datetime":"2017-07-26 13:00"},{"direction":94.63,"speed":6.6,"datetime":"2017-07-26 14:00"},{"direction":86.45,"speed":7.54,"datetime":"2017-07-26 15:00"},{"direction":84.12,"speed":9.09,"datetime":"2017-07-26 16:00"},{"direction":85.7,"speed":11.48,"datetime":"2017-07-26 17:00"},{"direction":89.04,"speed":14.79,"datetime":"2017-07-26 18:00"},{"direction":92.96,"speed":18.06,"datetime":"2017-07-26 19:00"}]
             * temperature : [{"value":32,"datetime":"2017-07-24 20:00"},{"value":31.25,"datetime":"2017-07-24 21:00"},{"value":31.02,"datetime":"2017-07-24 22:00"},{"value":30.98,"datetime":"2017-07-24 23:00"},{"value":30.89,"datetime":"2017-07-25 00:00"},{"value":30.67,"datetime":"2017-07-25 01:00"},{"value":30.29,"datetime":"2017-07-25 02:00"},{"value":29.7,"datetime":"2017-07-25 03:00"},{"value":29.24,"datetime":"2017-07-25 04:00"},{"value":29.24,"datetime":"2017-07-25 05:00"},{"value":29.92,"datetime":"2017-07-25 06:00"},{"value":31.09,"datetime":"2017-07-25 07:00"},{"value":32.48,"datetime":"2017-07-25 08:00"},{"value":33.84,"datetime":"2017-07-25 09:00"},{"value":35.04,"datetime":"2017-07-25 10:00"},{"value":35.98,"datetime":"2017-07-25 11:00"},{"value":36.42,"datetime":"2017-07-25 12:00"},{"value":36.7,"datetime":"2017-07-25 13:00"},{"value":36.95,"datetime":"2017-07-25 14:00"},{"value":37.18,"datetime":"2017-07-25 15:00"},{"value":37.18,"datetime":"2017-07-25 16:00"},{"value":36.65,"datetime":"2017-07-25 17:00"},{"value":35.14,"datetime":"2017-07-25 18:00"},{"value":32.85,"datetime":"2017-07-25 19:00"},{"value":30.75,"datetime":"2017-07-25 20:00"},{"value":29.36,"datetime":"2017-07-25 21:00"},{"value":28.58,"datetime":"2017-07-25 22:00"},{"value":28.16,"datetime":"2017-07-25 23:00"},{"value":27.88,"datetime":"2017-07-26 00:00"},{"value":27.61,"datetime":"2017-07-26 01:00"},{"value":27.27,"datetime":"2017-07-26 02:00"},{"value":26.83,"datetime":"2017-07-26 03:00"},{"value":26.53,"datetime":"2017-07-26 04:00"},{"value":26.63,"datetime":"2017-07-26 05:00"},{"value":27.36,"datetime":"2017-07-26 06:00"},{"value":28.56,"datetime":"2017-07-26 07:00"},{"value":30.03,"datetime":"2017-07-26 08:00"},{"value":31.55,"datetime":"2017-07-26 09:00"},{"value":32.99,"datetime":"2017-07-26 10:00"},{"value":34.22,"datetime":"2017-07-26 11:00"},{"value":35.15,"datetime":"2017-07-26 12:00"},{"value":35.82,"datetime":"2017-07-26 13:00"},{"value":36.23,"datetime":"2017-07-26 14:00"},{"value":36.48,"datetime":"2017-07-26 15:00"},{"value":36.43,"datetime":"2017-07-26 16:00"},{"value":35.79,"datetime":"2017-07-26 17:00"},{"value":34.01,"datetime":"2017-07-26 18:00"},{"value":31.82,"datetime":"2017-07-26 19:00"}]
             */

            private String status;
            private String description;
            private List<SkyconBean> skycon;
            private List<CloudrateBean> cloudrate;
            private List<AqiBean> aqi;
            private List<HumidityBean> humidity;
            private List<Pm25Bean> pm25;
            private List<PrecipitationBean> precipitation;
            private List<WindBean> wind;
            private List<TemperatureBean> temperature;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public List<SkyconBean> getSkycon() {
                return skycon;
            }

            public void setSkycon(List<SkyconBean> skycon) {
                this.skycon = skycon;
            }

            public List<CloudrateBean> getCloudrate() {
                return cloudrate;
            }

            public void setCloudrate(List<CloudrateBean> cloudrate) {
                this.cloudrate = cloudrate;
            }

            public List<AqiBean> getAqi() {
                return aqi;
            }

            public void setAqi(List<AqiBean> aqi) {
                this.aqi = aqi;
            }

            public List<HumidityBean> getHumidity() {
                return humidity;
            }

            public void setHumidity(List<HumidityBean> humidity) {
                this.humidity = humidity;
            }

            public List<Pm25Bean> getPm25() {
                return pm25;
            }

            public void setPm25(List<Pm25Bean> pm25) {
                this.pm25 = pm25;
            }

            public List<PrecipitationBean> getPrecipitation() {
                return precipitation;
            }

            public void setPrecipitation(List<PrecipitationBean> precipitation) {
                this.precipitation = precipitation;
            }

            public List<WindBean> getWind() {
                return wind;
            }

            public void setWind(List<WindBean> wind) {
                this.wind = wind;
            }

            public List<TemperatureBean> getTemperature() {
                return temperature;
            }

            public void setTemperature(List<TemperatureBean> temperature) {
                this.temperature = temperature;
            }

            public static class SkyconBean {
                /**
                 * value : PARTLY_CLOUDY_NIGHT
                 * datetime : 2017-07-24 20:00
                 */

                private String value;
                private String datetime;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class CloudrateBean {
                /**
                 * value : 0.41
                 * datetime : 2017-07-24 20:00
                 */

                private double value;
                private String datetime;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class AqiBean {
                /**
                 * value : 95.0
                 * datetime : 2017-07-24 20:00
                 */

                private double value;
                private String datetime;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class HumidityBean {
                /**
                 * value : 0.59
                 * datetime : 2017-07-24 20:00
                 */

                private double value;
                private String datetime;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class Pm25Bean {
                /**
                 * value : 37.0
                 * datetime : 2017-07-24 20:00
                 */

                private double value;
                private String datetime;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class PrecipitationBean {
                /**
                 * value : 0.0
                 * datetime : 2017-07-24 20:00
                 */

                private double value;
                private String datetime;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class WindBean {
                /**
                 * direction : 132.64
                 * speed : 6.56
                 * datetime : 2017-07-24 20:00
                 */

                private double direction;
                private double speed;
                private String datetime;

                public double getDirection() {
                    return direction;
                }

                public void setDirection(double direction) {
                    this.direction = direction;
                }

                public double getSpeed() {
                    return speed;
                }

                public void setSpeed(double speed) {
                    this.speed = speed;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class TemperatureBean {
                /**
                 * value : 32.0
                 * datetime : 2017-07-24 20:00
                 */

                private double value;
                private String datetime;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }
        }

        public static class AlertBean {
            /**
             * status : ok
             * content : [{"province":"江苏","status":"预警中","code":"0703","content":"江都区气象台2017年07月24日08时14分变更高温红色预警信号为高温橙色预警信号。预计今天我区大部分地区最高气温将升至39℃以上，请注意防范。","alertId":"32101241600000_20170724081541","city":"扬州","pubtimestamp":1.500855395E9,"latlon":[32.426564,119.567481],"county":"江都","request_status":"ok","title":"江苏扬州江都","title":"江都区气象局发布高温橙色预警[II级/严重]","regionId":"101190605"}]
             */

            private String status;
            private List<ContentBean> content;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public List<ContentBean> getContent() {
                return content;
            }

            public void setContent(List<ContentBean> content) {
                this.content = content;
            }

            public static class ContentBean {
                /**
                 * province : 江苏
                 * status : 预警中
                 * code : 0703
                 * content : 江都区气象台2017年07月24日08时14分变更高温红色预警信号为高温橙色预警信号。预计今天我区大部分地区最高气温将升至39℃以上，请注意防范。
                 * alertId : 32101241600000_20170724081541
                 * city : 扬州
                 * pubtimestamp : 1.500855395E9
                 * latlon : [32.426564,119.567481]
                 * county : 江都
                 * request_status : ok
                 * title : 江苏扬州江都
                 * title : 江都区气象局发布高温橙色预警[II级/严重]
                 * regionId : 101190605
                 */

                private String province;
                private String status;
                private String code;
                private String description;
                private String alertId;
                private String city;
                private double pubtimestamp;
                private String county;
                private String request_status;
                private String location;
                private String title;
                private String regionId;
                private List<Double> latlon;

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getAlertId() {
                    return alertId;
                }

                public void setAlertId(String alertId) {
                    this.alertId = alertId;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public double getPubtimestamp() {
                    return pubtimestamp;
                }

                public void setPubtimestamp(double pubtimestamp) {
                    this.pubtimestamp = pubtimestamp;
                }

                public String getCounty() {
                    return county;
                }

                public void setCounty(String county) {
                    this.county = county;
                }

                public String getRequest_status() {
                    return request_status;
                }

                public void setRequest_status(String request_status) {
                    this.request_status = request_status;
                }

                public String getLocation() {
                    return location;
                }

                public void setLocation(String location) {
                    this.location = location;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getRegionId() {
                    return regionId;
                }

                public void setRegionId(String regionId) {
                    this.regionId = regionId;
                }

                public List<Double> getLatlon() {
                    return latlon;
                }

                public void setLatlon(List<Double> latlon) {
                    this.latlon = latlon;
                }
            }
        }

        public static class MinutelyBean {
            /**
             * status : ok
             * content : 未来两小时不会下雨，放心出门吧
             * probability : [0,0,0,0]
             * datasource : radar
             * precipitation_2h : [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
             * precipitation : [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
             */

            private String status;
            private String description;
            private String datasource;
            private List<Double> probability;
            private List<Double> precipitation_2h;
            private List<Double> precipitation;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDatasource() {
                return datasource;
            }

            public void setDatasource(String datasource) {
                this.datasource = datasource;
            }

            public List<Double> getProbability() {
                return probability;
            }

            public void setProbability(List<Double> probability) {
                this.probability = probability;
            }

            public List<Double> getPrecipitation_2h() {
                return precipitation_2h;
            }

            public void setPrecipitation_2h(List<Double> precipitation_2h) {
                this.precipitation_2h = precipitation_2h;
            }

            public List<Double> getPrecipitation() {
                return precipitation;
            }

            public void setPrecipitation(List<Double> precipitation) {
                this.precipitation = precipitation;
            }
        }

        public static class DailyBean {
            /**
             * status : ok
             * coldRisk : [{"index":"4","desc":"极易发","datetime":"2017-07-24"},{"index":"4","desc":"极易发","datetime":"2017-07-25"},{"index":"4","desc":"极易发","datetime":"2017-07-26"},{"index":"4","desc":"极易发","datetime":"2017-07-27"},{"index":"4","desc":"极易发","datetime":"2017-07-28"},{"index":"4","desc":"极易发","datetime":"2017-07-29"},{"index":"4","desc":"极易发","datetime":"2017-07-30"},{"index":"4","desc":"极易发","datetime":"2017-07-31"},{"index":"3","desc":"易发","datetime":"2017-08-01"},{"index":"3","desc":"易发","datetime":"2017-08-02"},{"index":"4","desc":"极易发","datetime":"2017-08-03"},{"index":"4","desc":"极易发","datetime":"2017-08-04"},{"index":"4","desc":"极易发","datetime":"2017-08-05"},{"index":"4","desc":"极易发","datetime":"2017-08-06"},{"index":"4","desc":"极易发","datetime":"2017-08-07"}]
             * temperature : [{"date":"2017-07-24","max":37.21,"avg":31.31,"min":28.13},{"date":"2017-07-25","max":37.18,"avg":32.68,"min":28.16},{"date":"2017-07-26","max":36.48,"avg":30.99,"min":26.53},{"date":"2017-07-27","max":36.04,"avg":30.59,"min":25.82},{"date":"2017-07-28","max":35.61,"avg":30.38,"min":26.2},{"date":"2017-07-29","max":34.33,"avg":29.37,"min":25.41},{"date":"2017-07-30","max":33.37,"avg":29.04,"min":25.78},{"date":"2017-07-31","max":34.56,"avg":29.13,"min":25.98},{"date":"2017-08-01","max":28.28,"avg":26.76,"min":25.6},{"date":"2017-08-02","max":30.03,"avg":27.34,"min":25.5},{"date":"2017-08-03","max":32.75,"avg":29.82,"min":25.66},{"date":"2017-08-04","max":32.91,"avg":29.15,"min":25.44},{"date":"2017-08-05","max":33.08,"avg":28.87,"min":25},{"date":"2017-08-06","max":32.27,"avg":26.38,"min":20.51},{"date":"2017-08-07","max":32.41,"avg":25.36,"min":18.33}]
             * skycon : [{"date":"2017-07-24","value":"PARTLY_CLOUDY_NIGHT"},{"date":"2017-07-25","value":"CLEAR_DAY"},{"date":"2017-07-26","value":"CLEAR_DAY"},{"date":"2017-07-27","value":"CLEAR_DAY"},{"date":"2017-07-28","value":"CLEAR_DAY"},{"date":"2017-07-29","value":"RAIN"},{"date":"2017-07-30","value":"RAIN"},{"date":"2017-07-31","value":"RAIN"},{"date":"2017-08-01","value":"RAIN"},{"date":"2017-08-02","value":"RAIN"},{"date":"2017-08-03","value":"RAIN"},{"date":"2017-08-04","value":"RAIN"},{"date":"2017-08-05","value":"RAIN"},{"date":"2017-08-06","value":"CLEAR_DAY"},{"date":"2017-08-07","value":"CLEAR_DAY"}]
             * cloudrate : [{"date":"2017-07-24","max":0.95,"avg":0.33,"min":0.15},{"date":"2017-07-25","max":0.32,"avg":0.19,"min":0},{"date":"2017-07-26","max":0.38,"avg":0.15,"min":0},{"date":"2017-07-27","max":0.2,"avg":0.08,"min":0},{"date":"2017-07-28","max":0.05,"avg":0.01,"min":0},{"date":"2017-07-29","max":0.9,"avg":0.27,"min":0},{"date":"2017-07-30","max":1,"avg":0.44,"min":0},{"date":"2017-07-31","max":1,"avg":0.73,"min":0.23},{"date":"2017-08-01","max":1,"avg":1,"min":0.99},{"date":"2017-08-02","max":1,"avg":0.65,"min":0},{"date":"2017-08-03","max":0.36,"avg":0.24,"min":0},{"date":"2017-08-04","max":0.53,"avg":0.49,"min":0.39},{"date":"2017-08-05","max":0.66,"avg":0.39,"min":0.08},{"date":"2017-08-06","max":0.11,"avg":0.03,"min":0},{"date":"2017-08-07","max":0.01,"avg":0,"min":0}]
             * aqi : [{"date":"2017-07-24","max":175,"avg":67.5,"min":56},{"date":"2017-07-25","max":72,"avg":61.96,"min":49},{"date":"2017-07-26","max":66,"avg":58.17,"min":53},{"date":"2017-07-27","max":61,"avg":54.5,"min":45},{"date":"2017-07-28","max":58,"avg":49.17,"min":39},{"date":"2017-07-29","max":50,"avg":41.12,"min":33},{"date":"2017-07-30","max":43,"avg":34.08,"min":27},{"date":"2017-07-31","max":35,"avg":29.38,"min":24},{"date":"2017-08-01","max":35,"avg":31.88,"min":26},{"date":"2017-08-02","max":35,"avg":30,"min":26},{"date":"2017-08-03","max":33,"avg":31.29,"min":30},{"date":"2017-08-04","max":67,"avg":52.83,"min":33},{"date":"2017-08-05","max":59,"avg":55.04,"min":52},{"date":"2017-08-06","max":80,"avg":63.21,"min":45},{"date":"2017-08-07","max":78,"avg":60.88,"min":45}]
             * humidity : [{"date":"2017-07-24","max":0.71,"avg":0.61,"min":0.46},{"date":"2017-07-25","max":0.89,"avg":0.65,"min":0.5},{"date":"2017-07-26","max":0.96,"avg":0.77,"min":0.54},{"date":"2017-07-27","max":0.9,"avg":0.74,"min":0.52},{"date":"2017-07-28","max":0.86,"avg":0.73,"min":0.53},{"date":"2017-07-29","max":0.95,"avg":0.8,"min":0.59},{"date":"2017-07-30","max":0.96,"avg":0.84,"min":0.64},{"date":"2017-07-31","max":0.91,"avg":0.8,"min":0.61},{"date":"2017-08-01","max":0.96,"avg":0.92,"min":0.88},{"date":"2017-08-02","max":0.95,"avg":0.89,"min":0.79},{"date":"2017-08-03","max":0.96,"avg":0.82,"min":0.73},{"date":"2017-08-04","max":1,"avg":0.87,"min":0.74},{"date":"2017-08-05","max":0.94,"avg":0.71,"min":0.46},{"date":"2017-08-06","max":0.89,"avg":0.64,"min":0.38},{"date":"2017-08-07","max":0.78,"avg":0.62,"min":0.46}]
             * astro : [{"date":"2017-07-24","sunset":{"time":"19:05"},"sunrise":{"time":"05:10"}},{"date":"2017-07-25","sunset":{"time":"19:04"},"sunrise":{"time":"05:10"}},{"date":"2017-07-26","sunset":{"time":"19:03"},"sunrise":{"time":"05:11"}},{"date":"2017-07-27","sunset":{"time":"19:03"},"sunrise":{"time":"05:12"}},{"date":"2017-07-28","sunset":{"time":"19:02"},"sunrise":{"time":"05:12"}},{"date":"2017-07-29","sunset":{"time":"19:01"},"sunrise":{"time":"05:13"}},{"date":"2017-07-30","sunset":{"time":"19:01"},"sunrise":{"time":"05:14"}},{"date":"2017-07-31","sunset":{"time":"19:00"},"sunrise":{"time":"05:14"}},{"date":"2017-08-01","sunset":{"time":"18:59"},"sunrise":{"time":"05:15"}},{"date":"2017-08-02","sunset":{"time":"18:58"},"sunrise":{"time":"05:16"}},{"date":"2017-08-03","sunset":{"time":"18:57"},"sunrise":{"time":"05:16"}},{"date":"2017-08-04","sunset":{"time":"18:56"},"sunrise":{"time":"05:17"}},{"date":"2017-08-05","sunset":{"time":"18:56"},"sunrise":{"time":"05:18"}},{"date":"2017-08-06","sunset":{"time":"18:55"},"sunrise":{"time":"05:18"}},{"date":"2017-08-07","sunset":{"time":"18:54"},"sunrise":{"time":"05:19"}}]
             * ultraviolet : [{"index":"4","desc":"强","datetime":"2017-07-24"},{"index":"4","desc":"强","datetime":"2017-07-25"},{"index":"4","desc":"强","datetime":"2017-07-26"},{"index":"4","desc":"强","datetime":"2017-07-27"},{"index":"4","desc":"强","datetime":"2017-07-28"},{"index":"4","desc":"强","datetime":"2017-07-29"},{"index":"4","desc":"强","datetime":"2017-07-30"},{"index":"3","desc":"中等","datetime":"2017-07-31"},{"index":"1","desc":"最弱","datetime":"2017-08-01"},{"index":"2","desc":"弱","datetime":"2017-08-02"},{"index":"4","desc":"强","datetime":"2017-08-03"},{"index":"3","desc":"中等","datetime":"2017-08-04"},{"index":"3","desc":"中等","datetime":"2017-08-05"},{"index":"4","desc":"强","datetime":"2017-08-06"},{"index":"4","desc":"强","datetime":"2017-08-07"}]
             * pm25 : [{"date":"2017-07-24","max":80,"avg":40,"min":34},{"date":"2017-07-25","max":52,"avg":44.12,"min":34},{"date":"2017-07-26","max":47,"avg":41,"min":37},{"date":"2017-07-27","max":43,"avg":38.12,"min":31},{"date":"2017-07-28","max":41,"avg":34.17,"min":27},{"date":"2017-07-29","max":35,"avg":28.29,"min":22},{"date":"2017-07-30","max":30,"avg":23.04,"min":18},{"date":"2017-07-31","max":24,"avg":19.62,"min":16},{"date":"2017-08-01","max":24,"avg":21.42,"min":17},{"date":"2017-08-02","max":24,"avg":20.04,"min":17},{"date":"2017-08-03","max":22,"avg":20.96,"min":20},{"date":"2017-08-04","max":48,"avg":37.12,"min":22},{"date":"2017-08-05","max":42,"avg":38.42,"min":36},{"date":"2017-08-06","max":59,"avg":45.33,"min":31},{"date":"2017-08-07","max":57,"avg":43.25,"min":31}]
             * dressing : [{"index":"2","desc":"很热","datetime":"2017-07-24"},{"index":"1","desc":"极热","datetime":"2017-07-25"},{"index":"2","desc":"很热","datetime":"2017-07-26"},{"index":"2","desc":"很热","datetime":"2017-07-27"},{"index":"2","desc":"很热","datetime":"2017-07-28"},{"index":"3","desc":"热","datetime":"2017-07-29"},{"index":"3","desc":"热","datetime":"2017-07-30"},{"index":"3","desc":"热","datetime":"2017-07-31"},{"index":"2","desc":"很热","datetime":"2017-08-01"},{"index":"3","desc":"热","datetime":"2017-08-02"},{"index":"3","desc":"热","datetime":"2017-08-03"},{"index":"2","desc":"很热","datetime":"2017-08-04"},{"index":"2","desc":"很热","datetime":"2017-08-05"},{"index":"2","desc":"很热","datetime":"2017-08-06"},{"index":"3","desc":"热","datetime":"2017-08-07"}]
             * carWashing : [{"index":"1","desc":"适宜","datetime":"2017-07-24"},{"index":"1","desc":"适宜","datetime":"2017-07-25"},{"index":"1","desc":"适宜","datetime":"2017-07-26"},{"index":"1","desc":"适宜","datetime":"2017-07-27"},{"index":"1","desc":"适宜","datetime":"2017-07-28"},{"index":"3","desc":"较不适宜","datetime":"2017-07-29"},{"index":"3","desc":"较不适宜","datetime":"2017-07-30"},{"index":"3","desc":"较不适宜","datetime":"2017-07-31"},{"index":"3","desc":"较不适宜","datetime":"2017-08-01"},{"index":"3","desc":"较不适宜","datetime":"2017-08-02"},{"index":"3","desc":"较不适宜","datetime":"2017-08-03"},{"index":"3","desc":"较不适宜","datetime":"2017-08-04"},{"index":"3","desc":"较不适宜","datetime":"2017-08-05"},{"index":"1","desc":"适宜","datetime":"2017-08-06"},{"index":"1","desc":"适宜","datetime":"2017-08-07"}]
             * precipitation : [{"date":"2017-07-24","max":0,"avg":0,"min":0},{"date":"2017-07-25","max":0,"avg":0,"min":0},{"date":"2017-07-26","max":0,"avg":0,"min":0},{"date":"2017-07-27","max":0,"avg":0,"min":0},{"date":"2017-07-28","max":0,"avg":0,"min":0},{"date":"2017-07-29","max":0.9826,"avg":0.1967,"min":0},{"date":"2017-07-30","max":2.7369,"avg":0.6571,"min":0},{"date":"2017-07-31","max":1.7798,"avg":0.3455,"min":0},{"date":"2017-08-01","max":2.108,"avg":1.0671,"min":0.205},{"date":"2017-08-02","max":0.8963,"avg":0.2693,"min":0},{"date":"2017-08-03","max":0.5269,"avg":0.2642,"min":0},{"date":"2017-08-04","max":0.9411,"avg":0.5805,"min":0.26},{"date":"2017-08-05","max":0.6584,"avg":0.1291,"min":0},{"date":"2017-08-06","max":0,"avg":0,"min":0},{"date":"2017-08-07","max":0,"avg":0,"min":0}]
             * wind : [{"date":"2017-07-24","max":{"direction":248.5,"speed":7.78},"avg":{"direction":210.41,"speed":5.32},"min":{"direction":283.27,"speed":1.09}},{"date":"2017-07-25","max":{"direction":76.73,"speed":19.98},"avg":{"direction":19.33,"speed":10.23},"min":{"direction":242.35,"speed":6.24}},{"date":"2017-07-26","max":{"direction":104.27,"speed":20.16},"avg":{"direction":100.05,"speed":12.23},"min":{"direction":108.2,"speed":6.32}},{"date":"2017-07-27","max":{"direction":100.12,"speed":19.99},"avg":{"direction":106.34,"speed":12.74},"min":{"direction":116.26,"speed":9.86}},{"date":"2017-07-28","max":{"direction":95.35,"speed":21.59},"avg":{"direction":102.64,"speed":14.53},"min":{"direction":117.02,"speed":9.83}},{"date":"2017-07-29","max":{"direction":90.66,"speed":21.17},"avg":{"direction":92.95,"speed":16.6},"min":{"direction":84.56,"speed":10.49}},{"date":"2017-07-30","max":{"direction":95.39,"speed":21.54},"avg":{"direction":98.08,"speed":17.31},"min":{"direction":92.9,"speed":11.82}},{"date":"2017-07-31","max":{"direction":104.33,"speed":26.3},"avg":{"direction":107.29,"speed":21.99},"min":{"direction":103.09,"speed":14.77}},{"date":"2017-08-01","max":{"direction":116.83,"speed":19.53},"avg":{"direction":93.41,"speed":14.73},"min":{"direction":68.97,"speed":11.19}},{"date":"2017-08-02","max":{"direction":72.31,"speed":31.3},"avg":{"direction":73.16,"speed":22.02},"min":{"direction":75.24,"speed":11.62}},{"date":"2017-08-03","max":{"direction":6.36,"speed":20.54},"avg":{"direction":21.91,"speed":17.49},"min":{"direction":38.95,"speed":14.68}},{"date":"2017-08-04","max":{"direction":321.15,"speed":23.42},"avg":{"direction":340.79,"speed":13.34},"min":{"direction":58.13,"speed":4.91}},{"date":"2017-08-05","max":{"direction":322.63,"speed":18.98},"avg":{"direction":329.88,"speed":14.78},"min":{"direction":322.13,"speed":13.37}},{"date":"2017-08-06","max":{"direction":13.4,"speed":12.73},"avg":{"direction":13.63,"speed":8.85},"min":{"direction":17.03,"speed":5.4}},{"date":"2017-08-07","max":{"direction":64.7,"speed":13.1},"avg":{"direction":78.26,"speed":8.32},"min":{"direction":64.28,"speed":4.35}}]
             */

            private String status;
            private List<ColdRiskBean> coldRisk;
            private List<TemperatureBeanX> temperature;
            private List<SkyconBeanX> skycon;
            private List<CloudrateBeanX> cloudrate;
            private List<AqiBeanX> aqi;
            private List<HumidityBeanX> humidity;
            private List<AstroBean> astro;
            private List<UltravioletBean> ultraviolet;
            private List<Pm25BeanX> pm25;
            private List<DressingBean> dressing;
            private List<CarWashingBean> carWashing;
            private List<PrecipitationBeanX> precipitation;
            private List<WindBeanX> wind;
            private List<DescBean> desc;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public List<ColdRiskBean> getColdRisk() {
                return coldRisk;
            }

            public void setColdRisk(List<ColdRiskBean> coldRisk) {
                this.coldRisk = coldRisk;
            }

            public List<TemperatureBeanX> getTemperature() {
                return temperature;
            }

            public void setTemperature(List<TemperatureBeanX> temperature) {
                this.temperature = temperature;
            }

            public List<SkyconBeanX> getSkycon() {
                return skycon;
            }

            public void setSkycon(List<SkyconBeanX> skycon) {
                this.skycon = skycon;
            }

            public List<CloudrateBeanX> getCloudrate() {
                return cloudrate;
            }

            public void setCloudrate(List<CloudrateBeanX> cloudrate) {
                this.cloudrate = cloudrate;
            }

            public List<AqiBeanX> getAqi() {
                return aqi;
            }

            public void setAqi(List<AqiBeanX> aqi) {
                this.aqi = aqi;
            }

            public List<HumidityBeanX> getHumidity() {
                return humidity;
            }

            public void setHumidity(List<HumidityBeanX> humidity) {
                this.humidity = humidity;
            }

            public List<AstroBean> getAstro() {
                return astro;
            }

            public void setAstro(List<AstroBean> astro) {
                this.astro = astro;
            }

            public List<UltravioletBean> getUltraviolet() {
                return ultraviolet;
            }

            public void setUltraviolet(List<UltravioletBean> ultraviolet) {
                this.ultraviolet = ultraviolet;
            }

            public List<Pm25BeanX> getPm25() {
                return pm25;
            }

            public void setPm25(List<Pm25BeanX> pm25) {
                this.pm25 = pm25;
            }

            public List<DressingBean> getDressing() {
                return dressing;
            }

            public void setDressing(List<DressingBean> dressing) {
                this.dressing = dressing;
            }

            public List<CarWashingBean> getCarWashing() {
                return carWashing;
            }

            public void setCarWashing(List<CarWashingBean> carWashing) {
                this.carWashing = carWashing;
            }

            public List<PrecipitationBeanX> getPrecipitation() {
                return precipitation;
            }

            public void setPrecipitation(List<PrecipitationBeanX> precipitation) {
                this.precipitation = precipitation;
            }

            public List<WindBeanX> getWind() {
                return wind;
            }

            public void setWind(List<WindBeanX> wind) {
                this.wind = wind;
            }

            public List<DescBean> getDesc() {
                return desc;
            };

            public void setDesc(List<DescBean> desc) {
                this.desc = desc;
            };

            public static class ColdRiskBean {
                /**
                 * index : 4
                 * desc : 极易发
                 * datetime : 2017-07-24
                 */

                private String index;
                private String desc;
                private String datetime;

                public String getIndex() {
                    return index;
                }

                public void setIndex(String index) {
                    this.index = index;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class TemperatureBeanX {
                /**
                 * date : 2017-07-24
                 * max : 37.21
                 * avg : 31.31
                 * min : 28.13
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }

            public static class SkyconBeanX {
                /**
                 * date : 2017-07-24
                 * value : PARTLY_CLOUDY_NIGHT
                 */

                private String date;
                private String value;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class CloudrateBeanX {
                /**
                 * date : 2017-07-24
                 * max : 0.95
                 * avg : 0.33
                 * min : 0.15
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }

            public static class AqiBeanX {
                /**
                 * date : 2017-07-24
                 * max : 175.0
                 * avg : 67.5
                 * min : 56.0
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }

            public static class HumidityBeanX {
                /**
                 * date : 2017-07-24
                 * max : 0.71
                 * avg : 0.61
                 * min : 0.46
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }

            public static class AstroBean {
                /**
                 * date : 2017-07-24
                 * sunset : {"time":"19:05"}
                 * sunrise : {"time":"05:10"}
                 */

                private String date;
                private SunsetBean sunset;
                private SunriseBean sunrise;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public SunsetBean getSunset() {
                    return sunset;
                }

                public void setSunset(SunsetBean sunset) {
                    this.sunset = sunset;
                }

                public SunriseBean getSunrise() {
                    return sunrise;
                }

                public void setSunrise(SunriseBean sunrise) {
                    this.sunrise = sunrise;
                }

                public static class SunsetBean {
                    /**
                     * time : 19:05
                     */

                    private String time;

                    public String getTime() {
                        return time;
                    }

                    public void setTime(String time) {
                        this.time = time;
                    }
                }

                public static class SunriseBean {
                    /**
                     * time : 05:10
                     */

                    private String time;

                    public String getTime() {
                        return time;
                    }

                    public void setTime(String time) {
                        this.time = time;
                    }
                }
            }

            public static class UltravioletBean {
                /**
                 * index : 4
                 * desc : 强
                 * datetime : 2017-07-24
                 */

                private String index;
                private String desc;
                private String datetime;

                public String getIndex() {
                    return index;
                }

                public void setIndex(String index) {
                    this.index = index;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class Pm25BeanX {
                /**
                 * date : 2017-07-24
                 * max : 80.0
                 * avg : 40.0
                 * min : 34.0
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }

            public static class DressingBean {
                /**
                 * index : 2
                 * desc : 很热
                 * datetime : 2017-07-24
                 */

                private String index;
                private String desc;
                private String datetime;

                public String getIndex() {
                    return index;
                }

                public void setIndex(String index) {
                    this.index = index;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class CarWashingBean {
                /**
                 * index : 1
                 * desc : 适宜
                 * datetime : 2017-07-24
                 */

                private String index;
                private String desc;
                private String datetime;

                public String getIndex() {
                    return index;
                }

                public void setIndex(String index) {
                    this.index = index;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class PrecipitationBeanX {
                /**
                 * date : 2017-07-24
                 * max : 0.0
                 * avg : 0.0
                 * min : 0.0
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }

            public static class WindBeanX {
                /**
                 * date : 2017-07-24
                 * max : {"direction":248.5,"speed":7.78}
                 * avg : {"direction":210.41,"speed":5.32}
                 * min : {"direction":283.27,"speed":1.09}
                 */

                private String date;
                private MaxBean max;
                private AvgBean avg;
                private MinBean min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public MaxBean getMax() {
                    return max;
                }

                public void setMax(MaxBean max) {
                    this.max = max;
                }

                public AvgBean getAvg() {
                    return avg;
                }

                public void setAvg(AvgBean avg) {
                    this.avg = avg;
                }

                public MinBean getMin() {
                    return min;
                }

                public void setMin(MinBean min) {
                    this.min = min;
                }

                public static class MaxBean {
                    /**
                     * direction : 248.5
                     * speed : 7.78
                     */

                    private double direction;
                    private double speed;

                    public double getDirection() {
                        return direction;
                    }

                    public void setDirection(double direction) {
                        this.direction = direction;
                    }

                    public double getSpeed() {
                        return speed;
                    }

                    public void setSpeed(double speed) {
                        this.speed = speed;
                    }
                }

                public static class AvgBean {
                    /**
                     * direction : 210.41
                     * speed : 5.32
                     */

                    private double direction;
                    private double speed;

                    public double getDirection() {
                        return direction;
                    }

                    public void setDirection(double direction) {
                        this.direction = direction;
                    }

                    public double getSpeed() {
                        return speed;
                    }

                    public void setSpeed(double speed) {
                        this.speed = speed;
                    }
                }

                public static class MinBean {
                    /**
                     * direction : 283.27
                     * speed : 1.09
                     */

                    private double direction;
                    private double speed;

                    public double getDirection() {
                        return direction;
                    }

                    public void setDirection(double direction) {
                        this.direction = direction;
                    }

                    public double getSpeed() {
                        return speed;
                    }

                    public void setSpeed(double speed) {
                        this.speed = speed;
                    }
                }
            }

            public static class DescBean {
                /**
                 * date : 2017-07-21
                 * value : 多云
                 */

                private String date;
                private String value;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            };
        }
    }
}