package top.maweihao.weather.bean;

import java.util.List;

/**
 * 天气预报bean
 * 对应json数据格式，自动生成
 * Created by limuyang on 2017/6/10.
 */

public class ForecastBean {

    /**
     * status : ok
     * lang : zh_CN
     * result : {"hourly":{"status":"ok","description":"多云，今天下午15点钟后转小雨，其后多云","skycon":[{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-10 04:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 05:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 06:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 07:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 08:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 09:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 10:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 11:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 12:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 13:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 14:00"},{"value":"RAIN","datetime":"2017-06-10 15:00"},{"value":"RAIN","datetime":"2017-06-10 16:00"},{"value":"RAIN","datetime":"2017-06-10 17:00"},{"value":"RAIN","datetime":"2017-06-10 18:00"},{"value":"RAIN","datetime":"2017-06-10 19:00"},{"value":"RAIN","datetime":"2017-06-10 20:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-10 21:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-10 22:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-10 23:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 00:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 01:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 02:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 03:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 04:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 05:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 06:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 07:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 08:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 09:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 10:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 11:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 12:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 13:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 14:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 15:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 16:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 17:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 18:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 19:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 20:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 21:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 22:00"},{"value":"RAIN","datetime":"2017-06-11 23:00"},{"value":"RAIN","datetime":"2017-06-12 00:00"},{"value":"RAIN","datetime":"2017-06-12 01:00"},{"value":"RAIN","datetime":"2017-06-12 02:00"},{"value":"RAIN","datetime":"2017-06-12 03:00"}],"cloudrate":[{"value":0.46,"datetime":"2017-06-10 04:00"},{"value":0.61,"datetime":"2017-06-10 05:00"},{"value":0.66,"datetime":"2017-06-10 06:00"},{"value":0.64,"datetime":"2017-06-10 07:00"},{"value":0.62,"datetime":"2017-06-10 08:00"},{"value":0.62,"datetime":"2017-06-10 09:00"},{"value":0.64,"datetime":"2017-06-10 10:00"},{"value":0.66,"datetime":"2017-06-10 11:00"},{"value":0.67,"datetime":"2017-06-10 12:00"},{"value":0.67,"datetime":"2017-06-10 13:00"},{"value":0.69,"datetime":"2017-06-10 14:00"},{"value":0.73,"datetime":"2017-06-10 15:00"},{"value":0.78,"datetime":"2017-06-10 16:00"},{"value":0.82,"datetime":"2017-06-10 17:00"},{"value":0.84,"datetime":"2017-06-10 18:00"},{"value":0.83,"datetime":"2017-06-10 19:00"},{"value":0.75,"datetime":"2017-06-10 20:00"},{"value":0.62,"datetime":"2017-06-10 21:00"},{"value":0.48,"datetime":"2017-06-10 22:00"},{"value":0.38,"datetime":"2017-06-10 23:00"},{"value":0.37,"datetime":"2017-06-11 00:00"},{"value":0.43,"datetime":"2017-06-11 01:00"},{"value":0.51,"datetime":"2017-06-11 02:00"},{"value":0.59,"datetime":"2017-06-11 03:00"},{"value":0.64,"datetime":"2017-06-11 04:00"},{"value":0.67,"datetime":"2017-06-11 05:00"},{"value":0.67,"datetime":"2017-06-11 06:00"},{"value":0.66,"datetime":"2017-06-11 07:00"},{"value":0.65,"datetime":"2017-06-11 08:00"},{"value":0.66,"datetime":"2017-06-11 09:00"},{"value":0.66,"datetime":"2017-06-11 10:00"},{"value":0.63,"datetime":"2017-06-11 11:00"},{"value":0.58,"datetime":"2017-06-11 12:00"},{"value":0.51,"datetime":"2017-06-11 13:00"},{"value":0.46,"datetime":"2017-06-11 14:00"},{"value":0.45,"datetime":"2017-06-11 15:00"},{"value":0.47,"datetime":"2017-06-11 16:00"},{"value":0.51,"datetime":"2017-06-11 17:00"},{"value":0.55,"datetime":"2017-06-11 18:00"},{"value":0.59,"datetime":"2017-06-11 19:00"},{"value":0.62,"datetime":"2017-06-11 20:00"},{"value":0.64,"datetime":"2017-06-11 21:00"},{"value":0.66,"datetime":"2017-06-11 22:00"},{"value":0.67,"datetime":"2017-06-11 23:00"},{"value":0.69,"datetime":"2017-06-12 00:00"},{"value":0.73,"datetime":"2017-06-12 01:00"},{"value":0.78,"datetime":"2017-06-12 02:00"},{"value":0.86,"datetime":"2017-06-12 03:00"}],"aqi":[{"value":18,"datetime":"2017-06-10 04:00"},{"value":18,"datetime":"2017-06-10 05:00"},{"value":18,"datetime":"2017-06-10 06:00"},{"value":18,"datetime":"2017-06-10 07:00"},{"value":19,"datetime":"2017-06-10 08:00"},{"value":19,"datetime":"2017-06-10 09:00"},{"value":20,"datetime":"2017-06-10 10:00"},{"value":20,"datetime":"2017-06-10 11:00"},{"value":22,"datetime":"2017-06-10 12:00"},{"value":23,"datetime":"2017-06-10 13:00"},{"value":23,"datetime":"2017-06-10 14:00"},{"value":23,"datetime":"2017-06-10 15:00"},{"value":23,"datetime":"2017-06-10 16:00"},{"value":22,"datetime":"2017-06-10 17:00"},{"value":22,"datetime":"2017-06-10 18:00"},{"value":20,"datetime":"2017-06-10 19:00"},{"value":20,"datetime":"2017-06-10 20:00"},{"value":20,"datetime":"2017-06-10 21:00"},{"value":20,"datetime":"2017-06-10 22:00"},{"value":19,"datetime":"2017-06-10 23:00"},{"value":19,"datetime":"2017-06-11 00:00"},{"value":19,"datetime":"2017-06-11 01:00"},{"value":19,"datetime":"2017-06-11 02:00"},{"value":19,"datetime":"2017-06-11 03:00"},{"value":19,"datetime":"2017-06-11 04:00"},{"value":19,"datetime":"2017-06-11 05:00"},{"value":20,"datetime":"2017-06-11 06:00"},{"value":20,"datetime":"2017-06-11 07:00"},{"value":20,"datetime":"2017-06-11 08:00"},{"value":20,"datetime":"2017-06-11 09:00"},{"value":20,"datetime":"2017-06-11 10:00"},{"value":22,"datetime":"2017-06-11 11:00"},{"value":23,"datetime":"2017-06-11 12:00"},{"value":23,"datetime":"2017-06-11 13:00"},{"value":24,"datetime":"2017-06-11 14:00"},{"value":26,"datetime":"2017-06-11 15:00"},{"value":27,"datetime":"2017-06-11 16:00"},{"value":27,"datetime":"2017-06-11 17:00"},{"value":27,"datetime":"2017-06-11 18:00"},{"value":26,"datetime":"2017-06-11 19:00"},{"value":26,"datetime":"2017-06-11 20:00"},{"value":24,"datetime":"2017-06-11 21:00"},{"value":24,"datetime":"2017-06-11 22:00"},{"value":23,"datetime":"2017-06-11 23:00"},{"value":23,"datetime":"2017-06-12 00:00"},{"value":22,"datetime":"2017-06-12 01:00"},{"value":22,"datetime":"2017-06-12 02:00"},{"value":20,"datetime":"2017-06-12 03:00"}],"humidity":[{"value":0.89,"datetime":"2017-06-10 04:00"},{"value":0.88,"datetime":"2017-06-10 05:00"},{"value":0.87,"datetime":"2017-06-10 06:00"},{"value":0.85,"datetime":"2017-06-10 07:00"},{"value":0.82,"datetime":"2017-06-10 08:00"},{"value":0.8,"datetime":"2017-06-10 09:00"},{"value":0.79,"datetime":"2017-06-10 10:00"},{"value":0.78,"datetime":"2017-06-10 11:00"},{"value":0.79,"datetime":"2017-06-10 12:00"},{"value":0.8,"datetime":"2017-06-10 13:00"},{"value":0.81,"datetime":"2017-06-10 14:00"},{"value":0.82,"datetime":"2017-06-10 15:00"},{"value":0.83,"datetime":"2017-06-10 16:00"},{"value":0.84,"datetime":"2017-06-10 17:00"},{"value":0.85,"datetime":"2017-06-10 18:00"},{"value":0.86,"datetime":"2017-06-10 19:00"},{"value":0.88,"datetime":"2017-06-10 20:00"},{"value":0.89,"datetime":"2017-06-10 21:00"},{"value":0.89,"datetime":"2017-06-10 22:00"},{"value":0.9,"datetime":"2017-06-10 23:00"},{"value":0.91,"datetime":"2017-06-11 00:00"},{"value":0.92,"datetime":"2017-06-11 01:00"},{"value":0.93,"datetime":"2017-06-11 02:00"},{"value":0.95,"datetime":"2017-06-11 03:00"},{"value":0.95,"datetime":"2017-06-11 04:00"},{"value":0.95,"datetime":"2017-06-11 05:00"},{"value":0.92,"datetime":"2017-06-11 06:00"},{"value":0.88,"datetime":"2017-06-11 07:00"},{"value":0.83,"datetime":"2017-06-11 08:00"},{"value":0.79,"datetime":"2017-06-11 09:00"},{"value":0.75,"datetime":"2017-06-11 10:00"},{"value":0.72,"datetime":"2017-06-11 11:00"},{"value":0.71,"datetime":"2017-06-11 12:00"},{"value":0.71,"datetime":"2017-06-11 13:00"},{"value":0.72,"datetime":"2017-06-11 14:00"},{"value":0.74,"datetime":"2017-06-11 15:00"},{"value":0.76,"datetime":"2017-06-11 16:00"},{"value":0.78,"datetime":"2017-06-11 17:00"},{"value":0.8,"datetime":"2017-06-11 18:00"},{"value":0.82,"datetime":"2017-06-11 19:00"},{"value":0.84,"datetime":"2017-06-11 20:00"},{"value":0.85,"datetime":"2017-06-11 21:00"},{"value":0.86,"datetime":"2017-06-11 22:00"},{"value":0.86,"datetime":"2017-06-11 23:00"},{"value":0.87,"datetime":"2017-06-12 00:00"},{"value":0.88,"datetime":"2017-06-12 01:00"},{"value":0.89,"datetime":"2017-06-12 02:00"},{"value":0.9,"datetime":"2017-06-12 03:00"}],"pm25":[{"value":11,"datetime":"2017-06-10 04:00"},{"value":11,"datetime":"2017-06-10 05:00"},{"value":11,"datetime":"2017-06-10 06:00"},{"value":11,"datetime":"2017-06-10 07:00"},{"value":12,"datetime":"2017-06-10 08:00"},{"value":12,"datetime":"2017-06-10 09:00"},{"value":13,"datetime":"2017-06-10 10:00"},{"value":13,"datetime":"2017-06-10 11:00"},{"value":14,"datetime":"2017-06-10 12:00"},{"value":15,"datetime":"2017-06-10 13:00"},{"value":15,"datetime":"2017-06-10 14:00"},{"value":15,"datetime":"2017-06-10 15:00"},{"value":15,"datetime":"2017-06-10 16:00"},{"value":14,"datetime":"2017-06-10 17:00"},{"value":14,"datetime":"2017-06-10 18:00"},{"value":13,"datetime":"2017-06-10 19:00"},{"value":13,"datetime":"2017-06-10 20:00"},{"value":13,"datetime":"2017-06-10 21:00"},{"value":13,"datetime":"2017-06-10 22:00"},{"value":12,"datetime":"2017-06-10 23:00"},{"value":12,"datetime":"2017-06-11 00:00"},{"value":12,"datetime":"2017-06-11 01:00"},{"value":12,"datetime":"2017-06-11 02:00"},{"value":12,"datetime":"2017-06-11 03:00"},{"value":12,"datetime":"2017-06-11 04:00"},{"value":12,"datetime":"2017-06-11 05:00"},{"value":13,"datetime":"2017-06-11 06:00"},{"value":13,"datetime":"2017-06-11 07:00"},{"value":13,"datetime":"2017-06-11 08:00"},{"value":13,"datetime":"2017-06-11 09:00"},{"value":13,"datetime":"2017-06-11 10:00"},{"value":14,"datetime":"2017-06-11 11:00"},{"value":15,"datetime":"2017-06-11 12:00"},{"value":15,"datetime":"2017-06-11 13:00"},{"value":16,"datetime":"2017-06-11 14:00"},{"value":17,"datetime":"2017-06-11 15:00"},{"value":18,"datetime":"2017-06-11 16:00"},{"value":18,"datetime":"2017-06-11 17:00"},{"value":18,"datetime":"2017-06-11 18:00"},{"value":17,"datetime":"2017-06-11 19:00"},{"value":17,"datetime":"2017-06-11 20:00"},{"value":16,"datetime":"2017-06-11 21:00"},{"value":16,"datetime":"2017-06-11 22:00"},{"value":15,"datetime":"2017-06-11 23:00"},{"value":15,"datetime":"2017-06-12 00:00"},{"value":14,"datetime":"2017-06-12 01:00"},{"value":14,"datetime":"2017-06-12 02:00"},{"value":13,"datetime":"2017-06-12 03:00"}],"precipitation":[{"value":0,"datetime":"2017-06-10 04:00"},{"value":0,"datetime":"2017-06-10 05:00"},{"value":0,"datetime":"2017-06-10 06:00"},{"value":0,"datetime":"2017-06-10 07:00"},{"value":0,"datetime":"2017-06-10 08:00"},{"value":0,"datetime":"2017-06-10 09:00"},{"value":0,"datetime":"2017-06-10 10:00"},{"value":0,"datetime":"2017-06-10 11:00"},{"value":0,"datetime":"2017-06-10 12:00"},{"value":0,"datetime":"2017-06-10 13:00"},{"value":0.0513,"datetime":"2017-06-10 14:00"},{"value":0.0858,"datetime":"2017-06-10 15:00"},{"value":0.1231,"datetime":"2017-06-10 16:00"},{"value":0.1467,"datetime":"2017-06-10 17:00"},{"value":0.1446,"datetime":"2017-06-10 18:00"},{"value":0.1221,"datetime":"2017-06-10 19:00"},{"value":0.089,"datetime":"2017-06-10 20:00"},{"value":0.0543,"datetime":"2017-06-10 21:00"},{"value":0,"datetime":"2017-06-10 22:00"},{"value":0,"datetime":"2017-06-10 23:00"},{"value":0,"datetime":"2017-06-11 00:00"},{"value":0,"datetime":"2017-06-11 01:00"},{"value":0,"datetime":"2017-06-11 02:00"},{"value":0,"datetime":"2017-06-11 03:00"},{"value":0,"datetime":"2017-06-11 04:00"},{"value":0,"datetime":"2017-06-11 05:00"},{"value":0,"datetime":"2017-06-11 06:00"},{"value":0,"datetime":"2017-06-11 07:00"},{"value":0,"datetime":"2017-06-11 08:00"},{"value":0,"datetime":"2017-06-11 09:00"},{"value":0,"datetime":"2017-06-11 10:00"},{"value":0,"datetime":"2017-06-11 11:00"},{"value":0,"datetime":"2017-06-11 12:00"},{"value":0,"datetime":"2017-06-11 13:00"},{"value":0,"datetime":"2017-06-11 14:00"},{"value":0,"datetime":"2017-06-11 15:00"},{"value":0,"datetime":"2017-06-11 16:00"},{"value":0,"datetime":"2017-06-11 17:00"},{"value":0,"datetime":"2017-06-11 18:00"},{"value":0,"datetime":"2017-06-11 19:00"},{"value":0,"datetime":"2017-06-11 20:00"},{"value":0,"datetime":"2017-06-11 21:00"},{"value":0,"datetime":"2017-06-11 22:00"},{"value":0.1707,"datetime":"2017-06-11 23:00"},{"value":0.5058,"datetime":"2017-06-12 00:00"},{"value":0.9009,"datetime":"2017-06-12 01:00"},{"value":1.1996,"datetime":"2017-06-12 02:00"},{"value":1.2861,"datetime":"2017-06-12 03:00"}],"wind":[{"direction":233.09,"speed":6.54,"datetime":"2017-06-10 04:00"},{"direction":250.57,"speed":8.6,"datetime":"2017-06-10 05:00"},{"direction":256.44,"speed":10.22,"datetime":"2017-06-10 06:00"},{"direction":258.18,"speed":10.94,"datetime":"2017-06-10 07:00"},{"direction":259.52,"speed":10.75,"datetime":"2017-06-10 08:00"},{"direction":262.71,"speed":9.77,"datetime":"2017-06-10 09:00"},{"direction":267.68,"speed":8.35,"datetime":"2017-06-10 10:00"},{"direction":273.04,"speed":6.83,"datetime":"2017-06-10 11:00"},{"direction":276.08,"speed":5.41,"datetime":"2017-06-10 12:00"},{"direction":275.2,"speed":4.15,"datetime":"2017-06-10 13:00"},{"direction":269.01,"speed":3.13,"datetime":"2017-06-10 14:00"},{"direction":256.63,"speed":2.49,"datetime":"2017-06-10 15:00"},{"direction":239.17,"speed":2.32,"datetime":"2017-06-10 16:00"},{"direction":223.26,"speed":2.65,"datetime":"2017-06-10 17:00"},{"direction":213.42,"speed":3.36,"datetime":"2017-06-10 18:00"},{"direction":208.37,"speed":4.19,"datetime":"2017-06-10 19:00"},{"direction":205.63,"speed":4.9,"datetime":"2017-06-10 20:00"},{"direction":204.26,"speed":5.34,"datetime":"2017-06-10 21:00"},{"direction":206.22,"speed":5.51,"datetime":"2017-06-10 22:00"},{"direction":214.4,"speed":5.56,"datetime":"2017-06-10 23:00"},{"direction":229.41,"speed":5.85,"datetime":"2017-06-11 00:00"},{"direction":243.6,"speed":6.48,"datetime":"2017-06-11 01:00"},{"direction":251.67,"speed":6.86,"datetime":"2017-06-11 02:00"},{"direction":252.84,"speed":6.42,"datetime":"2017-06-11 03:00"},{"direction":249.48,"speed":5.67,"datetime":"2017-06-11 04:00"},{"direction":245.57,"speed":5.44,"datetime":"2017-06-11 05:00"},{"direction":246.22,"speed":6.22,"datetime":"2017-06-11 06:00"},{"direction":249.3,"speed":7.52,"datetime":"2017-06-11 07:00"},{"direction":252.34,"speed":8.61,"datetime":"2017-06-11 08:00"},{"direction":254.97,"speed":8.88,"datetime":"2017-06-11 09:00"},{"direction":258.01,"speed":8.31,"datetime":"2017-06-11 10:00"},{"direction":262.72,"speed":7.03,"datetime":"2017-06-11 11:00"},{"direction":271.68,"speed":5.28,"datetime":"2017-06-11 12:00"},{"direction":289.24,"speed":3.65,"datetime":"2017-06-11 13:00"},{"direction":316.33,"speed":2.77,"datetime":"2017-06-11 14:00"},{"direction":337.09,"speed":2.58,"datetime":"2017-06-11 15:00"},{"direction":348.26,"speed":2.36,"datetime":"2017-06-11 16:00"},{"direction":0.56,"speed":1.88,"datetime":"2017-06-11 17:00"},{"direction":30.93,"speed":1.38,"datetime":"2017-06-11 18:00"},{"direction":66.22,"speed":1.34,"datetime":"2017-06-11 19:00"},{"direction":73.69,"speed":1.09,"datetime":"2017-06-11 20:00"},{"direction":350.71,"speed":0.78,"datetime":"2017-06-11 21:00"},{"direction":316.38,"speed":2.64,"datetime":"2017-06-11 22:00"},{"direction":317.3,"speed":4.93,"datetime":"2017-06-11 23:00"},{"direction":323.67,"speed":7.06,"datetime":"2017-06-12 00:00"},{"direction":328.53,"speed":8.67,"datetime":"2017-06-12 01:00"},{"direction":329.12,"speed":9.19,"datetime":"2017-06-12 02:00"},{"direction":322.75,"speed":8.35,"datetime":"2017-06-12 03:00"}],"temperature":[{"value":26,"datetime":"2017-06-10 04:00"},{"value":26,"datetime":"2017-06-10 05:00"},{"value":26,"datetime":"2017-06-10 06:00"},{"value":27,"datetime":"2017-06-10 07:00"},{"value":28,"datetime":"2017-06-10 08:00"},{"value":29,"datetime":"2017-06-10 09:00"},{"value":30,"datetime":"2017-06-10 10:00"},{"value":30,"datetime":"2017-06-10 11:00"},{"value":30,"datetime":"2017-06-10 12:00"},{"value":30,"datetime":"2017-06-10 13:00"},{"value":30,"datetime":"2017-06-10 14:00"},{"value":30,"datetime":"2017-06-10 15:00"},{"value":29,"datetime":"2017-06-10 16:00"},{"value":29,"datetime":"2017-06-10 17:00"},{"value":28,"datetime":"2017-06-10 18:00"},{"value":28,"datetime":"2017-06-10 19:00"},{"value":27,"datetime":"2017-06-10 20:00"},{"value":27,"datetime":"2017-06-10 21:00"},{"value":27,"datetime":"2017-06-10 22:00"},{"value":27,"datetime":"2017-06-10 23:00"},{"value":27.11,"datetime":"2017-06-11 00:00"},{"value":26.86,"datetime":"2017-06-11 01:00"},{"value":26.56,"datetime":"2017-06-11 02:00"},{"value":26.22,"datetime":"2017-06-11 03:00"},{"value":26,"datetime":"2017-06-11 04:00"},{"value":26.09,"datetime":"2017-06-11 05:00"},{"value":26.61,"datetime":"2017-06-11 06:00"},{"value":27.48,"datetime":"2017-06-11 07:00"},{"value":28.52,"datetime":"2017-06-11 08:00"},{"value":29.59,"datetime":"2017-06-11 09:00"},{"value":30.57,"datetime":"2017-06-11 10:00"},{"value":31.34,"datetime":"2017-06-11 11:00"},{"value":31.82,"datetime":"2017-06-11 12:00"},{"value":32,"datetime":"2017-06-11 13:00"},{"value":31.88,"datetime":"2017-06-11 14:00"},{"value":31.49,"datetime":"2017-06-11 15:00"},{"value":30.92,"datetime":"2017-06-11 16:00"},{"value":30.27,"datetime":"2017-06-11 17:00"},{"value":29.63,"datetime":"2017-06-11 18:00"},{"value":29.07,"datetime":"2017-06-11 19:00"},{"value":28.64,"datetime":"2017-06-11 20:00"},{"value":28.36,"datetime":"2017-06-11 21:00"},{"value":28.15,"datetime":"2017-06-11 22:00"},{"value":27.89,"datetime":"2017-06-11 23:00"},{"value":29.05,"datetime":"2017-06-12 00:00"},{"value":27.93,"datetime":"2017-06-12 01:00"},{"value":26.93,"datetime":"2017-06-12 02:00"},{"value":26.28,"datetime":"2017-06-12 03:00"}]},"minutely":{"status":"ok","description":"不会有雨，最近的降雨带在南边128公里外呢","probability":[0.0693088919,0.1096921489,0.1234315932,0.1672359109],"datasource":"radar","precipitation_2h":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"precipitation":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]},"daily":{"status":"ok","coldRisk":[{"index":"3","desc":"易发","datetime":"2017-06-10"},{"index":"3","desc":"易发","datetime":"2017-06-11"},{"index":"3","desc":"易发","datetime":"2017-06-12"},{"index":"3","desc":"易发","datetime":"2017-06-13"},{"index":"3","desc":"易发","datetime":"2017-06-14"}],"temperature":[{"date":"2017-06-10","max":30,"avg":28.2,"min":26},{"date":"2017-06-11","max":32,"avg":28.88,"min":26},{"date":"2017-06-12","max":32,"avg":28.86,"min":26},{"date":"2017-06-13","max":32,"avg":28.13,"min":25},{"date":"2017-06-14","max":31,"avg":28.28,"min":25}],"skycon":[{"date":"2017-06-10","value":"PARTLY_CLOUDY_NIGHT"},{"date":"2017-06-11","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-06-12","value":"RAIN"},{"date":"2017-06-13","value":"RAIN"},{"date":"2017-06-14","value":"RAIN"}],"cloudrate":[{"date":"2017-06-10","max":0.84,"avg":0.66,"min":0},{"date":"2017-06-11","max":0.67,"avg":0.58,"min":0.37},{"date":"2017-06-12","max":1,"avg":0.95,"min":0.69},{"date":"2017-06-13","max":1,"avg":0.89,"min":0.59},{"date":"2017-06-14","max":1,"avg":0.99,"min":0.95}],"aqi":[{"date":"2017-06-10","max":23,"avg":20.35,"min":18},{"date":"2017-06-11","max":27,"avg":22.33,"min":19},{"date":"2017-06-12","max":23,"avg":19.54,"min":18},{"date":"2017-06-13","max":18,"avg":16.12,"min":15},{"date":"2017-06-14","max":18,"avg":13.67,"min":10}],"humidity":[{"date":"2017-06-10","max":0.9,"avg":0.84,"min":0.78},{"date":"2017-06-11","max":0.95,"avg":0.83,"min":0.71},{"date":"2017-06-12","max":0.9,"avg":0.84,"min":0.78},{"date":"2017-06-13","max":0.91,"avg":0.86,"min":0.8},{"date":"2017-06-14","max":0.92,"avg":0.87,"min":0.82}],"astro":[{"date":"2017-06-10","sunset":{"time":"18:43"},"sunrise":{"time":"05:02"}},{"date":"2017-06-11","sunset":{"time":"18:43"},"sunrise":{"time":"05:02"}},{"date":"2017-06-12","sunset":{"time":"18:43"},"sunrise":{"time":"05:02"}},{"date":"2017-06-13","sunset":{"time":"18:44"},"sunrise":{"time":"05:02"}},{"date":"2017-06-14","sunset":{"time":"18:44"},"sunrise":{"time":"05:02"}}],"ultraviolet":[{"index":"2","desc":"弱","datetime":"2017-06-10"},{"index":"2","desc":"弱","datetime":"2017-06-11"},{"index":"1","desc":"最弱","datetime":"2017-06-12"},{"index":"1","desc":"最弱","datetime":"2017-06-13"},{"index":"1","desc":"最弱","datetime":"2017-06-14"}],"pm25":[{"date":"2017-06-10","max":15,"avg":13,"min":11},{"date":"2017-06-11","max":18,"avg":14.54,"min":12},{"date":"2017-06-12","max":15,"avg":12.42,"min":11},{"date":"2017-06-13","max":11,"avg":9.96,"min":9},{"date":"2017-06-14","max":11,"avg":7.96,"min":5}],"dressing":[{"index":"2","desc":"很热","datetime":"2017-06-10"},{"index":"2","desc":"很热","datetime":"2017-06-11"},{"index":"2","desc":"很热","datetime":"2017-06-12"},{"index":"2","desc":"很热","datetime":"2017-06-13"},{"index":"3","desc":"热","datetime":"2017-06-14"}],"carWashing":[{"index":"3","desc":"较不适宜","datetime":"2017-06-10"},{"index":"3","desc":"较不适宜","datetime":"2017-06-11"},{"index":"3","desc":"较不适宜","datetime":"2017-06-12"},{"index":"3","desc":"较不适宜","datetime":"2017-06-13"},{"index":"3","desc":"较不适宜","datetime":"2017-06-14"}],"precipitation":[{"date":"2017-06-10","max":0.1467,"avg":0.0408,"min":0},{"date":"2017-06-11","max":0.1707,"avg":0.0071,"min":0},{"date":"2017-06-12","max":1.2861,"avg":0.3871,"min":0},{"date":"2017-06-13","max":1.6714,"avg":0.4032,"min":0},{"date":"2017-06-14","max":3.2702,"avg":1.6148,"min":0.1423}],"wind":[{"date":"2017-06-10","max":{"direction":258.18,"speed":10.94},"avg":{"direction":233.47,"speed":6.27},"min":{"direction":239.17,"speed":2.32}},{"date":"2017-06-11","max":{"direction":254.97,"speed":8.88},"avg":{"direction":264.16,"speed":4.75},"min":{"direction":350.71,"speed":0.78}},{"date":"2017-06-12","max":{"direction":329.12,"speed":9.19},"avg":{"direction":242.19,"speed":6.25},"min":{"direction":68.11,"speed":0.77}},{"date":"2017-06-13","max":{"direction":57.25,"speed":13.61},"avg":{"direction":39.78,"speed":8.46},"min":{"direction":178.86,"speed":2.43}},{"date":"2017-06-14","max":{"direction":258.12,"speed":30.71},"avg":{"direction":342.29,"speed":18.93},"min":{"direction":232.17,"speed":8.52}}],"desc":[{"date":"2017-06-10","value":"大雨转阴"},{"date":"2017-06-11","value":"多云"},{"date":"2017-06-12","value":"多云"},{"date":"2017-06-13","value":"多云"},{"date":"2017-06-14","value":"多云"}]},"primary":0}
     * server_time : 1497040076
     * api_status : active
     * tzshift : 28800
     * api_version : v2.2
     * unit : metric
     * location : [25.1552,121.6544]
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
         * hourly : {"status":"ok","description":"多云，今天下午15点钟后转小雨，其后多云","skycon":[{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-10 04:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 05:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 06:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 07:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 08:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 09:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 10:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 11:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 12:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 13:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 14:00"},{"value":"RAIN","datetime":"2017-06-10 15:00"},{"value":"RAIN","datetime":"2017-06-10 16:00"},{"value":"RAIN","datetime":"2017-06-10 17:00"},{"value":"RAIN","datetime":"2017-06-10 18:00"},{"value":"RAIN","datetime":"2017-06-10 19:00"},{"value":"RAIN","datetime":"2017-06-10 20:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-10 21:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-10 22:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-10 23:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 00:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 01:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 02:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 03:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 04:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 05:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 06:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 07:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 08:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 09:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 10:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 11:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 12:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 13:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 14:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 15:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 16:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 17:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 18:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 19:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 20:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 21:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 22:00"},{"value":"RAIN","datetime":"2017-06-11 23:00"},{"value":"RAIN","datetime":"2017-06-12 00:00"},{"value":"RAIN","datetime":"2017-06-12 01:00"},{"value":"RAIN","datetime":"2017-06-12 02:00"},{"value":"RAIN","datetime":"2017-06-12 03:00"}],"cloudrate":[{"value":0.46,"datetime":"2017-06-10 04:00"},{"value":0.61,"datetime":"2017-06-10 05:00"},{"value":0.66,"datetime":"2017-06-10 06:00"},{"value":0.64,"datetime":"2017-06-10 07:00"},{"value":0.62,"datetime":"2017-06-10 08:00"},{"value":0.62,"datetime":"2017-06-10 09:00"},{"value":0.64,"datetime":"2017-06-10 10:00"},{"value":0.66,"datetime":"2017-06-10 11:00"},{"value":0.67,"datetime":"2017-06-10 12:00"},{"value":0.67,"datetime":"2017-06-10 13:00"},{"value":0.69,"datetime":"2017-06-10 14:00"},{"value":0.73,"datetime":"2017-06-10 15:00"},{"value":0.78,"datetime":"2017-06-10 16:00"},{"value":0.82,"datetime":"2017-06-10 17:00"},{"value":0.84,"datetime":"2017-06-10 18:00"},{"value":0.83,"datetime":"2017-06-10 19:00"},{"value":0.75,"datetime":"2017-06-10 20:00"},{"value":0.62,"datetime":"2017-06-10 21:00"},{"value":0.48,"datetime":"2017-06-10 22:00"},{"value":0.38,"datetime":"2017-06-10 23:00"},{"value":0.37,"datetime":"2017-06-11 00:00"},{"value":0.43,"datetime":"2017-06-11 01:00"},{"value":0.51,"datetime":"2017-06-11 02:00"},{"value":0.59,"datetime":"2017-06-11 03:00"},{"value":0.64,"datetime":"2017-06-11 04:00"},{"value":0.67,"datetime":"2017-06-11 05:00"},{"value":0.67,"datetime":"2017-06-11 06:00"},{"value":0.66,"datetime":"2017-06-11 07:00"},{"value":0.65,"datetime":"2017-06-11 08:00"},{"value":0.66,"datetime":"2017-06-11 09:00"},{"value":0.66,"datetime":"2017-06-11 10:00"},{"value":0.63,"datetime":"2017-06-11 11:00"},{"value":0.58,"datetime":"2017-06-11 12:00"},{"value":0.51,"datetime":"2017-06-11 13:00"},{"value":0.46,"datetime":"2017-06-11 14:00"},{"value":0.45,"datetime":"2017-06-11 15:00"},{"value":0.47,"datetime":"2017-06-11 16:00"},{"value":0.51,"datetime":"2017-06-11 17:00"},{"value":0.55,"datetime":"2017-06-11 18:00"},{"value":0.59,"datetime":"2017-06-11 19:00"},{"value":0.62,"datetime":"2017-06-11 20:00"},{"value":0.64,"datetime":"2017-06-11 21:00"},{"value":0.66,"datetime":"2017-06-11 22:00"},{"value":0.67,"datetime":"2017-06-11 23:00"},{"value":0.69,"datetime":"2017-06-12 00:00"},{"value":0.73,"datetime":"2017-06-12 01:00"},{"value":0.78,"datetime":"2017-06-12 02:00"},{"value":0.86,"datetime":"2017-06-12 03:00"}],"aqi":[{"value":18,"datetime":"2017-06-10 04:00"},{"value":18,"datetime":"2017-06-10 05:00"},{"value":18,"datetime":"2017-06-10 06:00"},{"value":18,"datetime":"2017-06-10 07:00"},{"value":19,"datetime":"2017-06-10 08:00"},{"value":19,"datetime":"2017-06-10 09:00"},{"value":20,"datetime":"2017-06-10 10:00"},{"value":20,"datetime":"2017-06-10 11:00"},{"value":22,"datetime":"2017-06-10 12:00"},{"value":23,"datetime":"2017-06-10 13:00"},{"value":23,"datetime":"2017-06-10 14:00"},{"value":23,"datetime":"2017-06-10 15:00"},{"value":23,"datetime":"2017-06-10 16:00"},{"value":22,"datetime":"2017-06-10 17:00"},{"value":22,"datetime":"2017-06-10 18:00"},{"value":20,"datetime":"2017-06-10 19:00"},{"value":20,"datetime":"2017-06-10 20:00"},{"value":20,"datetime":"2017-06-10 21:00"},{"value":20,"datetime":"2017-06-10 22:00"},{"value":19,"datetime":"2017-06-10 23:00"},{"value":19,"datetime":"2017-06-11 00:00"},{"value":19,"datetime":"2017-06-11 01:00"},{"value":19,"datetime":"2017-06-11 02:00"},{"value":19,"datetime":"2017-06-11 03:00"},{"value":19,"datetime":"2017-06-11 04:00"},{"value":19,"datetime":"2017-06-11 05:00"},{"value":20,"datetime":"2017-06-11 06:00"},{"value":20,"datetime":"2017-06-11 07:00"},{"value":20,"datetime":"2017-06-11 08:00"},{"value":20,"datetime":"2017-06-11 09:00"},{"value":20,"datetime":"2017-06-11 10:00"},{"value":22,"datetime":"2017-06-11 11:00"},{"value":23,"datetime":"2017-06-11 12:00"},{"value":23,"datetime":"2017-06-11 13:00"},{"value":24,"datetime":"2017-06-11 14:00"},{"value":26,"datetime":"2017-06-11 15:00"},{"value":27,"datetime":"2017-06-11 16:00"},{"value":27,"datetime":"2017-06-11 17:00"},{"value":27,"datetime":"2017-06-11 18:00"},{"value":26,"datetime":"2017-06-11 19:00"},{"value":26,"datetime":"2017-06-11 20:00"},{"value":24,"datetime":"2017-06-11 21:00"},{"value":24,"datetime":"2017-06-11 22:00"},{"value":23,"datetime":"2017-06-11 23:00"},{"value":23,"datetime":"2017-06-12 00:00"},{"value":22,"datetime":"2017-06-12 01:00"},{"value":22,"datetime":"2017-06-12 02:00"},{"value":20,"datetime":"2017-06-12 03:00"}],"humidity":[{"value":0.89,"datetime":"2017-06-10 04:00"},{"value":0.88,"datetime":"2017-06-10 05:00"},{"value":0.87,"datetime":"2017-06-10 06:00"},{"value":0.85,"datetime":"2017-06-10 07:00"},{"value":0.82,"datetime":"2017-06-10 08:00"},{"value":0.8,"datetime":"2017-06-10 09:00"},{"value":0.79,"datetime":"2017-06-10 10:00"},{"value":0.78,"datetime":"2017-06-10 11:00"},{"value":0.79,"datetime":"2017-06-10 12:00"},{"value":0.8,"datetime":"2017-06-10 13:00"},{"value":0.81,"datetime":"2017-06-10 14:00"},{"value":0.82,"datetime":"2017-06-10 15:00"},{"value":0.83,"datetime":"2017-06-10 16:00"},{"value":0.84,"datetime":"2017-06-10 17:00"},{"value":0.85,"datetime":"2017-06-10 18:00"},{"value":0.86,"datetime":"2017-06-10 19:00"},{"value":0.88,"datetime":"2017-06-10 20:00"},{"value":0.89,"datetime":"2017-06-10 21:00"},{"value":0.89,"datetime":"2017-06-10 22:00"},{"value":0.9,"datetime":"2017-06-10 23:00"},{"value":0.91,"datetime":"2017-06-11 00:00"},{"value":0.92,"datetime":"2017-06-11 01:00"},{"value":0.93,"datetime":"2017-06-11 02:00"},{"value":0.95,"datetime":"2017-06-11 03:00"},{"value":0.95,"datetime":"2017-06-11 04:00"},{"value":0.95,"datetime":"2017-06-11 05:00"},{"value":0.92,"datetime":"2017-06-11 06:00"},{"value":0.88,"datetime":"2017-06-11 07:00"},{"value":0.83,"datetime":"2017-06-11 08:00"},{"value":0.79,"datetime":"2017-06-11 09:00"},{"value":0.75,"datetime":"2017-06-11 10:00"},{"value":0.72,"datetime":"2017-06-11 11:00"},{"value":0.71,"datetime":"2017-06-11 12:00"},{"value":0.71,"datetime":"2017-06-11 13:00"},{"value":0.72,"datetime":"2017-06-11 14:00"},{"value":0.74,"datetime":"2017-06-11 15:00"},{"value":0.76,"datetime":"2017-06-11 16:00"},{"value":0.78,"datetime":"2017-06-11 17:00"},{"value":0.8,"datetime":"2017-06-11 18:00"},{"value":0.82,"datetime":"2017-06-11 19:00"},{"value":0.84,"datetime":"2017-06-11 20:00"},{"value":0.85,"datetime":"2017-06-11 21:00"},{"value":0.86,"datetime":"2017-06-11 22:00"},{"value":0.86,"datetime":"2017-06-11 23:00"},{"value":0.87,"datetime":"2017-06-12 00:00"},{"value":0.88,"datetime":"2017-06-12 01:00"},{"value":0.89,"datetime":"2017-06-12 02:00"},{"value":0.9,"datetime":"2017-06-12 03:00"}],"pm25":[{"value":11,"datetime":"2017-06-10 04:00"},{"value":11,"datetime":"2017-06-10 05:00"},{"value":11,"datetime":"2017-06-10 06:00"},{"value":11,"datetime":"2017-06-10 07:00"},{"value":12,"datetime":"2017-06-10 08:00"},{"value":12,"datetime":"2017-06-10 09:00"},{"value":13,"datetime":"2017-06-10 10:00"},{"value":13,"datetime":"2017-06-10 11:00"},{"value":14,"datetime":"2017-06-10 12:00"},{"value":15,"datetime":"2017-06-10 13:00"},{"value":15,"datetime":"2017-06-10 14:00"},{"value":15,"datetime":"2017-06-10 15:00"},{"value":15,"datetime":"2017-06-10 16:00"},{"value":14,"datetime":"2017-06-10 17:00"},{"value":14,"datetime":"2017-06-10 18:00"},{"value":13,"datetime":"2017-06-10 19:00"},{"value":13,"datetime":"2017-06-10 20:00"},{"value":13,"datetime":"2017-06-10 21:00"},{"value":13,"datetime":"2017-06-10 22:00"},{"value":12,"datetime":"2017-06-10 23:00"},{"value":12,"datetime":"2017-06-11 00:00"},{"value":12,"datetime":"2017-06-11 01:00"},{"value":12,"datetime":"2017-06-11 02:00"},{"value":12,"datetime":"2017-06-11 03:00"},{"value":12,"datetime":"2017-06-11 04:00"},{"value":12,"datetime":"2017-06-11 05:00"},{"value":13,"datetime":"2017-06-11 06:00"},{"value":13,"datetime":"2017-06-11 07:00"},{"value":13,"datetime":"2017-06-11 08:00"},{"value":13,"datetime":"2017-06-11 09:00"},{"value":13,"datetime":"2017-06-11 10:00"},{"value":14,"datetime":"2017-06-11 11:00"},{"value":15,"datetime":"2017-06-11 12:00"},{"value":15,"datetime":"2017-06-11 13:00"},{"value":16,"datetime":"2017-06-11 14:00"},{"value":17,"datetime":"2017-06-11 15:00"},{"value":18,"datetime":"2017-06-11 16:00"},{"value":18,"datetime":"2017-06-11 17:00"},{"value":18,"datetime":"2017-06-11 18:00"},{"value":17,"datetime":"2017-06-11 19:00"},{"value":17,"datetime":"2017-06-11 20:00"},{"value":16,"datetime":"2017-06-11 21:00"},{"value":16,"datetime":"2017-06-11 22:00"},{"value":15,"datetime":"2017-06-11 23:00"},{"value":15,"datetime":"2017-06-12 00:00"},{"value":14,"datetime":"2017-06-12 01:00"},{"value":14,"datetime":"2017-06-12 02:00"},{"value":13,"datetime":"2017-06-12 03:00"}],"precipitation":[{"value":0,"datetime":"2017-06-10 04:00"},{"value":0,"datetime":"2017-06-10 05:00"},{"value":0,"datetime":"2017-06-10 06:00"},{"value":0,"datetime":"2017-06-10 07:00"},{"value":0,"datetime":"2017-06-10 08:00"},{"value":0,"datetime":"2017-06-10 09:00"},{"value":0,"datetime":"2017-06-10 10:00"},{"value":0,"datetime":"2017-06-10 11:00"},{"value":0,"datetime":"2017-06-10 12:00"},{"value":0,"datetime":"2017-06-10 13:00"},{"value":0.0513,"datetime":"2017-06-10 14:00"},{"value":0.0858,"datetime":"2017-06-10 15:00"},{"value":0.1231,"datetime":"2017-06-10 16:00"},{"value":0.1467,"datetime":"2017-06-10 17:00"},{"value":0.1446,"datetime":"2017-06-10 18:00"},{"value":0.1221,"datetime":"2017-06-10 19:00"},{"value":0.089,"datetime":"2017-06-10 20:00"},{"value":0.0543,"datetime":"2017-06-10 21:00"},{"value":0,"datetime":"2017-06-10 22:00"},{"value":0,"datetime":"2017-06-10 23:00"},{"value":0,"datetime":"2017-06-11 00:00"},{"value":0,"datetime":"2017-06-11 01:00"},{"value":0,"datetime":"2017-06-11 02:00"},{"value":0,"datetime":"2017-06-11 03:00"},{"value":0,"datetime":"2017-06-11 04:00"},{"value":0,"datetime":"2017-06-11 05:00"},{"value":0,"datetime":"2017-06-11 06:00"},{"value":0,"datetime":"2017-06-11 07:00"},{"value":0,"datetime":"2017-06-11 08:00"},{"value":0,"datetime":"2017-06-11 09:00"},{"value":0,"datetime":"2017-06-11 10:00"},{"value":0,"datetime":"2017-06-11 11:00"},{"value":0,"datetime":"2017-06-11 12:00"},{"value":0,"datetime":"2017-06-11 13:00"},{"value":0,"datetime":"2017-06-11 14:00"},{"value":0,"datetime":"2017-06-11 15:00"},{"value":0,"datetime":"2017-06-11 16:00"},{"value":0,"datetime":"2017-06-11 17:00"},{"value":0,"datetime":"2017-06-11 18:00"},{"value":0,"datetime":"2017-06-11 19:00"},{"value":0,"datetime":"2017-06-11 20:00"},{"value":0,"datetime":"2017-06-11 21:00"},{"value":0,"datetime":"2017-06-11 22:00"},{"value":0.1707,"datetime":"2017-06-11 23:00"},{"value":0.5058,"datetime":"2017-06-12 00:00"},{"value":0.9009,"datetime":"2017-06-12 01:00"},{"value":1.1996,"datetime":"2017-06-12 02:00"},{"value":1.2861,"datetime":"2017-06-12 03:00"}],"wind":[{"direction":233.09,"speed":6.54,"datetime":"2017-06-10 04:00"},{"direction":250.57,"speed":8.6,"datetime":"2017-06-10 05:00"},{"direction":256.44,"speed":10.22,"datetime":"2017-06-10 06:00"},{"direction":258.18,"speed":10.94,"datetime":"2017-06-10 07:00"},{"direction":259.52,"speed":10.75,"datetime":"2017-06-10 08:00"},{"direction":262.71,"speed":9.77,"datetime":"2017-06-10 09:00"},{"direction":267.68,"speed":8.35,"datetime":"2017-06-10 10:00"},{"direction":273.04,"speed":6.83,"datetime":"2017-06-10 11:00"},{"direction":276.08,"speed":5.41,"datetime":"2017-06-10 12:00"},{"direction":275.2,"speed":4.15,"datetime":"2017-06-10 13:00"},{"direction":269.01,"speed":3.13,"datetime":"2017-06-10 14:00"},{"direction":256.63,"speed":2.49,"datetime":"2017-06-10 15:00"},{"direction":239.17,"speed":2.32,"datetime":"2017-06-10 16:00"},{"direction":223.26,"speed":2.65,"datetime":"2017-06-10 17:00"},{"direction":213.42,"speed":3.36,"datetime":"2017-06-10 18:00"},{"direction":208.37,"speed":4.19,"datetime":"2017-06-10 19:00"},{"direction":205.63,"speed":4.9,"datetime":"2017-06-10 20:00"},{"direction":204.26,"speed":5.34,"datetime":"2017-06-10 21:00"},{"direction":206.22,"speed":5.51,"datetime":"2017-06-10 22:00"},{"direction":214.4,"speed":5.56,"datetime":"2017-06-10 23:00"},{"direction":229.41,"speed":5.85,"datetime":"2017-06-11 00:00"},{"direction":243.6,"speed":6.48,"datetime":"2017-06-11 01:00"},{"direction":251.67,"speed":6.86,"datetime":"2017-06-11 02:00"},{"direction":252.84,"speed":6.42,"datetime":"2017-06-11 03:00"},{"direction":249.48,"speed":5.67,"datetime":"2017-06-11 04:00"},{"direction":245.57,"speed":5.44,"datetime":"2017-06-11 05:00"},{"direction":246.22,"speed":6.22,"datetime":"2017-06-11 06:00"},{"direction":249.3,"speed":7.52,"datetime":"2017-06-11 07:00"},{"direction":252.34,"speed":8.61,"datetime":"2017-06-11 08:00"},{"direction":254.97,"speed":8.88,"datetime":"2017-06-11 09:00"},{"direction":258.01,"speed":8.31,"datetime":"2017-06-11 10:00"},{"direction":262.72,"speed":7.03,"datetime":"2017-06-11 11:00"},{"direction":271.68,"speed":5.28,"datetime":"2017-06-11 12:00"},{"direction":289.24,"speed":3.65,"datetime":"2017-06-11 13:00"},{"direction":316.33,"speed":2.77,"datetime":"2017-06-11 14:00"},{"direction":337.09,"speed":2.58,"datetime":"2017-06-11 15:00"},{"direction":348.26,"speed":2.36,"datetime":"2017-06-11 16:00"},{"direction":0.56,"speed":1.88,"datetime":"2017-06-11 17:00"},{"direction":30.93,"speed":1.38,"datetime":"2017-06-11 18:00"},{"direction":66.22,"speed":1.34,"datetime":"2017-06-11 19:00"},{"direction":73.69,"speed":1.09,"datetime":"2017-06-11 20:00"},{"direction":350.71,"speed":0.78,"datetime":"2017-06-11 21:00"},{"direction":316.38,"speed":2.64,"datetime":"2017-06-11 22:00"},{"direction":317.3,"speed":4.93,"datetime":"2017-06-11 23:00"},{"direction":323.67,"speed":7.06,"datetime":"2017-06-12 00:00"},{"direction":328.53,"speed":8.67,"datetime":"2017-06-12 01:00"},{"direction":329.12,"speed":9.19,"datetime":"2017-06-12 02:00"},{"direction":322.75,"speed":8.35,"datetime":"2017-06-12 03:00"}],"temperature":[{"value":26,"datetime":"2017-06-10 04:00"},{"value":26,"datetime":"2017-06-10 05:00"},{"value":26,"datetime":"2017-06-10 06:00"},{"value":27,"datetime":"2017-06-10 07:00"},{"value":28,"datetime":"2017-06-10 08:00"},{"value":29,"datetime":"2017-06-10 09:00"},{"value":30,"datetime":"2017-06-10 10:00"},{"value":30,"datetime":"2017-06-10 11:00"},{"value":30,"datetime":"2017-06-10 12:00"},{"value":30,"datetime":"2017-06-10 13:00"},{"value":30,"datetime":"2017-06-10 14:00"},{"value":30,"datetime":"2017-06-10 15:00"},{"value":29,"datetime":"2017-06-10 16:00"},{"value":29,"datetime":"2017-06-10 17:00"},{"value":28,"datetime":"2017-06-10 18:00"},{"value":28,"datetime":"2017-06-10 19:00"},{"value":27,"datetime":"2017-06-10 20:00"},{"value":27,"datetime":"2017-06-10 21:00"},{"value":27,"datetime":"2017-06-10 22:00"},{"value":27,"datetime":"2017-06-10 23:00"},{"value":27.11,"datetime":"2017-06-11 00:00"},{"value":26.86,"datetime":"2017-06-11 01:00"},{"value":26.56,"datetime":"2017-06-11 02:00"},{"value":26.22,"datetime":"2017-06-11 03:00"},{"value":26,"datetime":"2017-06-11 04:00"},{"value":26.09,"datetime":"2017-06-11 05:00"},{"value":26.61,"datetime":"2017-06-11 06:00"},{"value":27.48,"datetime":"2017-06-11 07:00"},{"value":28.52,"datetime":"2017-06-11 08:00"},{"value":29.59,"datetime":"2017-06-11 09:00"},{"value":30.57,"datetime":"2017-06-11 10:00"},{"value":31.34,"datetime":"2017-06-11 11:00"},{"value":31.82,"datetime":"2017-06-11 12:00"},{"value":32,"datetime":"2017-06-11 13:00"},{"value":31.88,"datetime":"2017-06-11 14:00"},{"value":31.49,"datetime":"2017-06-11 15:00"},{"value":30.92,"datetime":"2017-06-11 16:00"},{"value":30.27,"datetime":"2017-06-11 17:00"},{"value":29.63,"datetime":"2017-06-11 18:00"},{"value":29.07,"datetime":"2017-06-11 19:00"},{"value":28.64,"datetime":"2017-06-11 20:00"},{"value":28.36,"datetime":"2017-06-11 21:00"},{"value":28.15,"datetime":"2017-06-11 22:00"},{"value":27.89,"datetime":"2017-06-11 23:00"},{"value":29.05,"datetime":"2017-06-12 00:00"},{"value":27.93,"datetime":"2017-06-12 01:00"},{"value":26.93,"datetime":"2017-06-12 02:00"},{"value":26.28,"datetime":"2017-06-12 03:00"}]}
         * minutely : {"status":"ok","description":"不会有雨，最近的降雨带在南边128公里外呢","probability":[0.0693088919,0.1096921489,0.1234315932,0.1672359109],"datasource":"radar","precipitation_2h":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"precipitation":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}
         * daily : {"status":"ok","coldRisk":[{"index":"3","desc":"易发","datetime":"2017-06-10"},{"index":"3","desc":"易发","datetime":"2017-06-11"},{"index":"3","desc":"易发","datetime":"2017-06-12"},{"index":"3","desc":"易发","datetime":"2017-06-13"},{"index":"3","desc":"易发","datetime":"2017-06-14"}],"temperature":[{"date":"2017-06-10","max":30,"avg":28.2,"min":26},{"date":"2017-06-11","max":32,"avg":28.88,"min":26},{"date":"2017-06-12","max":32,"avg":28.86,"min":26},{"date":"2017-06-13","max":32,"avg":28.13,"min":25},{"date":"2017-06-14","max":31,"avg":28.28,"min":25}],"skycon":[{"date":"2017-06-10","value":"PARTLY_CLOUDY_NIGHT"},{"date":"2017-06-11","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-06-12","value":"RAIN"},{"date":"2017-06-13","value":"RAIN"},{"date":"2017-06-14","value":"RAIN"}],"cloudrate":[{"date":"2017-06-10","max":0.84,"avg":0.66,"min":0},{"date":"2017-06-11","max":0.67,"avg":0.58,"min":0.37},{"date":"2017-06-12","max":1,"avg":0.95,"min":0.69},{"date":"2017-06-13","max":1,"avg":0.89,"min":0.59},{"date":"2017-06-14","max":1,"avg":0.99,"min":0.95}],"aqi":[{"date":"2017-06-10","max":23,"avg":20.35,"min":18},{"date":"2017-06-11","max":27,"avg":22.33,"min":19},{"date":"2017-06-12","max":23,"avg":19.54,"min":18},{"date":"2017-06-13","max":18,"avg":16.12,"min":15},{"date":"2017-06-14","max":18,"avg":13.67,"min":10}],"humidity":[{"date":"2017-06-10","max":0.9,"avg":0.84,"min":0.78},{"date":"2017-06-11","max":0.95,"avg":0.83,"min":0.71},{"date":"2017-06-12","max":0.9,"avg":0.84,"min":0.78},{"date":"2017-06-13","max":0.91,"avg":0.86,"min":0.8},{"date":"2017-06-14","max":0.92,"avg":0.87,"min":0.82}],"astro":[{"date":"2017-06-10","sunset":{"time":"18:43"},"sunrise":{"time":"05:02"}},{"date":"2017-06-11","sunset":{"time":"18:43"},"sunrise":{"time":"05:02"}},{"date":"2017-06-12","sunset":{"time":"18:43"},"sunrise":{"time":"05:02"}},{"date":"2017-06-13","sunset":{"time":"18:44"},"sunrise":{"time":"05:02"}},{"date":"2017-06-14","sunset":{"time":"18:44"},"sunrise":{"time":"05:02"}}],"ultraviolet":[{"index":"2","desc":"弱","datetime":"2017-06-10"},{"index":"2","desc":"弱","datetime":"2017-06-11"},{"index":"1","desc":"最弱","datetime":"2017-06-12"},{"index":"1","desc":"最弱","datetime":"2017-06-13"},{"index":"1","desc":"最弱","datetime":"2017-06-14"}],"pm25":[{"date":"2017-06-10","max":15,"avg":13,"min":11},{"date":"2017-06-11","max":18,"avg":14.54,"min":12},{"date":"2017-06-12","max":15,"avg":12.42,"min":11},{"date":"2017-06-13","max":11,"avg":9.96,"min":9},{"date":"2017-06-14","max":11,"avg":7.96,"min":5}],"dressing":[{"index":"2","desc":"很热","datetime":"2017-06-10"},{"index":"2","desc":"很热","datetime":"2017-06-11"},{"index":"2","desc":"很热","datetime":"2017-06-12"},{"index":"2","desc":"很热","datetime":"2017-06-13"},{"index":"3","desc":"热","datetime":"2017-06-14"}],"carWashing":[{"index":"3","desc":"较不适宜","datetime":"2017-06-10"},{"index":"3","desc":"较不适宜","datetime":"2017-06-11"},{"index":"3","desc":"较不适宜","datetime":"2017-06-12"},{"index":"3","desc":"较不适宜","datetime":"2017-06-13"},{"index":"3","desc":"较不适宜","datetime":"2017-06-14"}],"precipitation":[{"date":"2017-06-10","max":0.1467,"avg":0.0408,"min":0},{"date":"2017-06-11","max":0.1707,"avg":0.0071,"min":0},{"date":"2017-06-12","max":1.2861,"avg":0.3871,"min":0},{"date":"2017-06-13","max":1.6714,"avg":0.4032,"min":0},{"date":"2017-06-14","max":3.2702,"avg":1.6148,"min":0.1423}],"wind":[{"date":"2017-06-10","max":{"direction":258.18,"speed":10.94},"avg":{"direction":233.47,"speed":6.27},"min":{"direction":239.17,"speed":2.32}},{"date":"2017-06-11","max":{"direction":254.97,"speed":8.88},"avg":{"direction":264.16,"speed":4.75},"min":{"direction":350.71,"speed":0.78}},{"date":"2017-06-12","max":{"direction":329.12,"speed":9.19},"avg":{"direction":242.19,"speed":6.25},"min":{"direction":68.11,"speed":0.77}},{"date":"2017-06-13","max":{"direction":57.25,"speed":13.61},"avg":{"direction":39.78,"speed":8.46},"min":{"direction":178.86,"speed":2.43}},{"date":"2017-06-14","max":{"direction":258.12,"speed":30.71},"avg":{"direction":342.29,"speed":18.93},"min":{"direction":232.17,"speed":8.52}}],"desc":[{"date":"2017-06-10","value":"大雨转阴"},{"date":"2017-06-11","value":"多云"},{"date":"2017-06-12","value":"多云"},{"date":"2017-06-13","value":"多云"},{"date":"2017-06-14","value":"多云"}]}
         * primary : 0
         */

        private HourlyBean hourly;
        private MinutelyBean minutely;
        private DailyBean daily;
        private int primary;

        public HourlyBean getHourly() {
            return hourly;
        }

        public void setHourly(HourlyBean hourly) {
            this.hourly = hourly;
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
             * description : 多云，今天下午15点钟后转小雨，其后多云
             * skycon : [{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-10 04:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 05:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 06:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 07:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 08:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 09:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 10:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 11:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 12:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 13:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-10 14:00"},{"value":"RAIN","datetime":"2017-06-10 15:00"},{"value":"RAIN","datetime":"2017-06-10 16:00"},{"value":"RAIN","datetime":"2017-06-10 17:00"},{"value":"RAIN","datetime":"2017-06-10 18:00"},{"value":"RAIN","datetime":"2017-06-10 19:00"},{"value":"RAIN","datetime":"2017-06-10 20:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-10 21:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-10 22:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-10 23:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 00:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 01:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 02:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 03:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 04:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 05:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 06:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 07:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 08:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 09:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 10:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 11:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 12:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 13:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 14:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 15:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 16:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 17:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-06-11 18:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 19:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 20:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 21:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-06-11 22:00"},{"value":"RAIN","datetime":"2017-06-11 23:00"},{"value":"RAIN","datetime":"2017-06-12 00:00"},{"value":"RAIN","datetime":"2017-06-12 01:00"},{"value":"RAIN","datetime":"2017-06-12 02:00"},{"value":"RAIN","datetime":"2017-06-12 03:00"}]
             * cloudrate : [{"value":0.46,"datetime":"2017-06-10 04:00"},{"value":0.61,"datetime":"2017-06-10 05:00"},{"value":0.66,"datetime":"2017-06-10 06:00"},{"value":0.64,"datetime":"2017-06-10 07:00"},{"value":0.62,"datetime":"2017-06-10 08:00"},{"value":0.62,"datetime":"2017-06-10 09:00"},{"value":0.64,"datetime":"2017-06-10 10:00"},{"value":0.66,"datetime":"2017-06-10 11:00"},{"value":0.67,"datetime":"2017-06-10 12:00"},{"value":0.67,"datetime":"2017-06-10 13:00"},{"value":0.69,"datetime":"2017-06-10 14:00"},{"value":0.73,"datetime":"2017-06-10 15:00"},{"value":0.78,"datetime":"2017-06-10 16:00"},{"value":0.82,"datetime":"2017-06-10 17:00"},{"value":0.84,"datetime":"2017-06-10 18:00"},{"value":0.83,"datetime":"2017-06-10 19:00"},{"value":0.75,"datetime":"2017-06-10 20:00"},{"value":0.62,"datetime":"2017-06-10 21:00"},{"value":0.48,"datetime":"2017-06-10 22:00"},{"value":0.38,"datetime":"2017-06-10 23:00"},{"value":0.37,"datetime":"2017-06-11 00:00"},{"value":0.43,"datetime":"2017-06-11 01:00"},{"value":0.51,"datetime":"2017-06-11 02:00"},{"value":0.59,"datetime":"2017-06-11 03:00"},{"value":0.64,"datetime":"2017-06-11 04:00"},{"value":0.67,"datetime":"2017-06-11 05:00"},{"value":0.67,"datetime":"2017-06-11 06:00"},{"value":0.66,"datetime":"2017-06-11 07:00"},{"value":0.65,"datetime":"2017-06-11 08:00"},{"value":0.66,"datetime":"2017-06-11 09:00"},{"value":0.66,"datetime":"2017-06-11 10:00"},{"value":0.63,"datetime":"2017-06-11 11:00"},{"value":0.58,"datetime":"2017-06-11 12:00"},{"value":0.51,"datetime":"2017-06-11 13:00"},{"value":0.46,"datetime":"2017-06-11 14:00"},{"value":0.45,"datetime":"2017-06-11 15:00"},{"value":0.47,"datetime":"2017-06-11 16:00"},{"value":0.51,"datetime":"2017-06-11 17:00"},{"value":0.55,"datetime":"2017-06-11 18:00"},{"value":0.59,"datetime":"2017-06-11 19:00"},{"value":0.62,"datetime":"2017-06-11 20:00"},{"value":0.64,"datetime":"2017-06-11 21:00"},{"value":0.66,"datetime":"2017-06-11 22:00"},{"value":0.67,"datetime":"2017-06-11 23:00"},{"value":0.69,"datetime":"2017-06-12 00:00"},{"value":0.73,"datetime":"2017-06-12 01:00"},{"value":0.78,"datetime":"2017-06-12 02:00"},{"value":0.86,"datetime":"2017-06-12 03:00"}]
             * aqi : [{"value":18,"datetime":"2017-06-10 04:00"},{"value":18,"datetime":"2017-06-10 05:00"},{"value":18,"datetime":"2017-06-10 06:00"},{"value":18,"datetime":"2017-06-10 07:00"},{"value":19,"datetime":"2017-06-10 08:00"},{"value":19,"datetime":"2017-06-10 09:00"},{"value":20,"datetime":"2017-06-10 10:00"},{"value":20,"datetime":"2017-06-10 11:00"},{"value":22,"datetime":"2017-06-10 12:00"},{"value":23,"datetime":"2017-06-10 13:00"},{"value":23,"datetime":"2017-06-10 14:00"},{"value":23,"datetime":"2017-06-10 15:00"},{"value":23,"datetime":"2017-06-10 16:00"},{"value":22,"datetime":"2017-06-10 17:00"},{"value":22,"datetime":"2017-06-10 18:00"},{"value":20,"datetime":"2017-06-10 19:00"},{"value":20,"datetime":"2017-06-10 20:00"},{"value":20,"datetime":"2017-06-10 21:00"},{"value":20,"datetime":"2017-06-10 22:00"},{"value":19,"datetime":"2017-06-10 23:00"},{"value":19,"datetime":"2017-06-11 00:00"},{"value":19,"datetime":"2017-06-11 01:00"},{"value":19,"datetime":"2017-06-11 02:00"},{"value":19,"datetime":"2017-06-11 03:00"},{"value":19,"datetime":"2017-06-11 04:00"},{"value":19,"datetime":"2017-06-11 05:00"},{"value":20,"datetime":"2017-06-11 06:00"},{"value":20,"datetime":"2017-06-11 07:00"},{"value":20,"datetime":"2017-06-11 08:00"},{"value":20,"datetime":"2017-06-11 09:00"},{"value":20,"datetime":"2017-06-11 10:00"},{"value":22,"datetime":"2017-06-11 11:00"},{"value":23,"datetime":"2017-06-11 12:00"},{"value":23,"datetime":"2017-06-11 13:00"},{"value":24,"datetime":"2017-06-11 14:00"},{"value":26,"datetime":"2017-06-11 15:00"},{"value":27,"datetime":"2017-06-11 16:00"},{"value":27,"datetime":"2017-06-11 17:00"},{"value":27,"datetime":"2017-06-11 18:00"},{"value":26,"datetime":"2017-06-11 19:00"},{"value":26,"datetime":"2017-06-11 20:00"},{"value":24,"datetime":"2017-06-11 21:00"},{"value":24,"datetime":"2017-06-11 22:00"},{"value":23,"datetime":"2017-06-11 23:00"},{"value":23,"datetime":"2017-06-12 00:00"},{"value":22,"datetime":"2017-06-12 01:00"},{"value":22,"datetime":"2017-06-12 02:00"},{"value":20,"datetime":"2017-06-12 03:00"}]
             * humidity : [{"value":0.89,"datetime":"2017-06-10 04:00"},{"value":0.88,"datetime":"2017-06-10 05:00"},{"value":0.87,"datetime":"2017-06-10 06:00"},{"value":0.85,"datetime":"2017-06-10 07:00"},{"value":0.82,"datetime":"2017-06-10 08:00"},{"value":0.8,"datetime":"2017-06-10 09:00"},{"value":0.79,"datetime":"2017-06-10 10:00"},{"value":0.78,"datetime":"2017-06-10 11:00"},{"value":0.79,"datetime":"2017-06-10 12:00"},{"value":0.8,"datetime":"2017-06-10 13:00"},{"value":0.81,"datetime":"2017-06-10 14:00"},{"value":0.82,"datetime":"2017-06-10 15:00"},{"value":0.83,"datetime":"2017-06-10 16:00"},{"value":0.84,"datetime":"2017-06-10 17:00"},{"value":0.85,"datetime":"2017-06-10 18:00"},{"value":0.86,"datetime":"2017-06-10 19:00"},{"value":0.88,"datetime":"2017-06-10 20:00"},{"value":0.89,"datetime":"2017-06-10 21:00"},{"value":0.89,"datetime":"2017-06-10 22:00"},{"value":0.9,"datetime":"2017-06-10 23:00"},{"value":0.91,"datetime":"2017-06-11 00:00"},{"value":0.92,"datetime":"2017-06-11 01:00"},{"value":0.93,"datetime":"2017-06-11 02:00"},{"value":0.95,"datetime":"2017-06-11 03:00"},{"value":0.95,"datetime":"2017-06-11 04:00"},{"value":0.95,"datetime":"2017-06-11 05:00"},{"value":0.92,"datetime":"2017-06-11 06:00"},{"value":0.88,"datetime":"2017-06-11 07:00"},{"value":0.83,"datetime":"2017-06-11 08:00"},{"value":0.79,"datetime":"2017-06-11 09:00"},{"value":0.75,"datetime":"2017-06-11 10:00"},{"value":0.72,"datetime":"2017-06-11 11:00"},{"value":0.71,"datetime":"2017-06-11 12:00"},{"value":0.71,"datetime":"2017-06-11 13:00"},{"value":0.72,"datetime":"2017-06-11 14:00"},{"value":0.74,"datetime":"2017-06-11 15:00"},{"value":0.76,"datetime":"2017-06-11 16:00"},{"value":0.78,"datetime":"2017-06-11 17:00"},{"value":0.8,"datetime":"2017-06-11 18:00"},{"value":0.82,"datetime":"2017-06-11 19:00"},{"value":0.84,"datetime":"2017-06-11 20:00"},{"value":0.85,"datetime":"2017-06-11 21:00"},{"value":0.86,"datetime":"2017-06-11 22:00"},{"value":0.86,"datetime":"2017-06-11 23:00"},{"value":0.87,"datetime":"2017-06-12 00:00"},{"value":0.88,"datetime":"2017-06-12 01:00"},{"value":0.89,"datetime":"2017-06-12 02:00"},{"value":0.9,"datetime":"2017-06-12 03:00"}]
             * pm25 : [{"value":11,"datetime":"2017-06-10 04:00"},{"value":11,"datetime":"2017-06-10 05:00"},{"value":11,"datetime":"2017-06-10 06:00"},{"value":11,"datetime":"2017-06-10 07:00"},{"value":12,"datetime":"2017-06-10 08:00"},{"value":12,"datetime":"2017-06-10 09:00"},{"value":13,"datetime":"2017-06-10 10:00"},{"value":13,"datetime":"2017-06-10 11:00"},{"value":14,"datetime":"2017-06-10 12:00"},{"value":15,"datetime":"2017-06-10 13:00"},{"value":15,"datetime":"2017-06-10 14:00"},{"value":15,"datetime":"2017-06-10 15:00"},{"value":15,"datetime":"2017-06-10 16:00"},{"value":14,"datetime":"2017-06-10 17:00"},{"value":14,"datetime":"2017-06-10 18:00"},{"value":13,"datetime":"2017-06-10 19:00"},{"value":13,"datetime":"2017-06-10 20:00"},{"value":13,"datetime":"2017-06-10 21:00"},{"value":13,"datetime":"2017-06-10 22:00"},{"value":12,"datetime":"2017-06-10 23:00"},{"value":12,"datetime":"2017-06-11 00:00"},{"value":12,"datetime":"2017-06-11 01:00"},{"value":12,"datetime":"2017-06-11 02:00"},{"value":12,"datetime":"2017-06-11 03:00"},{"value":12,"datetime":"2017-06-11 04:00"},{"value":12,"datetime":"2017-06-11 05:00"},{"value":13,"datetime":"2017-06-11 06:00"},{"value":13,"datetime":"2017-06-11 07:00"},{"value":13,"datetime":"2017-06-11 08:00"},{"value":13,"datetime":"2017-06-11 09:00"},{"value":13,"datetime":"2017-06-11 10:00"},{"value":14,"datetime":"2017-06-11 11:00"},{"value":15,"datetime":"2017-06-11 12:00"},{"value":15,"datetime":"2017-06-11 13:00"},{"value":16,"datetime":"2017-06-11 14:00"},{"value":17,"datetime":"2017-06-11 15:00"},{"value":18,"datetime":"2017-06-11 16:00"},{"value":18,"datetime":"2017-06-11 17:00"},{"value":18,"datetime":"2017-06-11 18:00"},{"value":17,"datetime":"2017-06-11 19:00"},{"value":17,"datetime":"2017-06-11 20:00"},{"value":16,"datetime":"2017-06-11 21:00"},{"value":16,"datetime":"2017-06-11 22:00"},{"value":15,"datetime":"2017-06-11 23:00"},{"value":15,"datetime":"2017-06-12 00:00"},{"value":14,"datetime":"2017-06-12 01:00"},{"value":14,"datetime":"2017-06-12 02:00"},{"value":13,"datetime":"2017-06-12 03:00"}]
             * precipitation : [{"value":0,"datetime":"2017-06-10 04:00"},{"value":0,"datetime":"2017-06-10 05:00"},{"value":0,"datetime":"2017-06-10 06:00"},{"value":0,"datetime":"2017-06-10 07:00"},{"value":0,"datetime":"2017-06-10 08:00"},{"value":0,"datetime":"2017-06-10 09:00"},{"value":0,"datetime":"2017-06-10 10:00"},{"value":0,"datetime":"2017-06-10 11:00"},{"value":0,"datetime":"2017-06-10 12:00"},{"value":0,"datetime":"2017-06-10 13:00"},{"value":0.0513,"datetime":"2017-06-10 14:00"},{"value":0.0858,"datetime":"2017-06-10 15:00"},{"value":0.1231,"datetime":"2017-06-10 16:00"},{"value":0.1467,"datetime":"2017-06-10 17:00"},{"value":0.1446,"datetime":"2017-06-10 18:00"},{"value":0.1221,"datetime":"2017-06-10 19:00"},{"value":0.089,"datetime":"2017-06-10 20:00"},{"value":0.0543,"datetime":"2017-06-10 21:00"},{"value":0,"datetime":"2017-06-10 22:00"},{"value":0,"datetime":"2017-06-10 23:00"},{"value":0,"datetime":"2017-06-11 00:00"},{"value":0,"datetime":"2017-06-11 01:00"},{"value":0,"datetime":"2017-06-11 02:00"},{"value":0,"datetime":"2017-06-11 03:00"},{"value":0,"datetime":"2017-06-11 04:00"},{"value":0,"datetime":"2017-06-11 05:00"},{"value":0,"datetime":"2017-06-11 06:00"},{"value":0,"datetime":"2017-06-11 07:00"},{"value":0,"datetime":"2017-06-11 08:00"},{"value":0,"datetime":"2017-06-11 09:00"},{"value":0,"datetime":"2017-06-11 10:00"},{"value":0,"datetime":"2017-06-11 11:00"},{"value":0,"datetime":"2017-06-11 12:00"},{"value":0,"datetime":"2017-06-11 13:00"},{"value":0,"datetime":"2017-06-11 14:00"},{"value":0,"datetime":"2017-06-11 15:00"},{"value":0,"datetime":"2017-06-11 16:00"},{"value":0,"datetime":"2017-06-11 17:00"},{"value":0,"datetime":"2017-06-11 18:00"},{"value":0,"datetime":"2017-06-11 19:00"},{"value":0,"datetime":"2017-06-11 20:00"},{"value":0,"datetime":"2017-06-11 21:00"},{"value":0,"datetime":"2017-06-11 22:00"},{"value":0.1707,"datetime":"2017-06-11 23:00"},{"value":0.5058,"datetime":"2017-06-12 00:00"},{"value":0.9009,"datetime":"2017-06-12 01:00"},{"value":1.1996,"datetime":"2017-06-12 02:00"},{"value":1.2861,"datetime":"2017-06-12 03:00"}]
             * wind : [{"direction":233.09,"speed":6.54,"datetime":"2017-06-10 04:00"},{"direction":250.57,"speed":8.6,"datetime":"2017-06-10 05:00"},{"direction":256.44,"speed":10.22,"datetime":"2017-06-10 06:00"},{"direction":258.18,"speed":10.94,"datetime":"2017-06-10 07:00"},{"direction":259.52,"speed":10.75,"datetime":"2017-06-10 08:00"},{"direction":262.71,"speed":9.77,"datetime":"2017-06-10 09:00"},{"direction":267.68,"speed":8.35,"datetime":"2017-06-10 10:00"},{"direction":273.04,"speed":6.83,"datetime":"2017-06-10 11:00"},{"direction":276.08,"speed":5.41,"datetime":"2017-06-10 12:00"},{"direction":275.2,"speed":4.15,"datetime":"2017-06-10 13:00"},{"direction":269.01,"speed":3.13,"datetime":"2017-06-10 14:00"},{"direction":256.63,"speed":2.49,"datetime":"2017-06-10 15:00"},{"direction":239.17,"speed":2.32,"datetime":"2017-06-10 16:00"},{"direction":223.26,"speed":2.65,"datetime":"2017-06-10 17:00"},{"direction":213.42,"speed":3.36,"datetime":"2017-06-10 18:00"},{"direction":208.37,"speed":4.19,"datetime":"2017-06-10 19:00"},{"direction":205.63,"speed":4.9,"datetime":"2017-06-10 20:00"},{"direction":204.26,"speed":5.34,"datetime":"2017-06-10 21:00"},{"direction":206.22,"speed":5.51,"datetime":"2017-06-10 22:00"},{"direction":214.4,"speed":5.56,"datetime":"2017-06-10 23:00"},{"direction":229.41,"speed":5.85,"datetime":"2017-06-11 00:00"},{"direction":243.6,"speed":6.48,"datetime":"2017-06-11 01:00"},{"direction":251.67,"speed":6.86,"datetime":"2017-06-11 02:00"},{"direction":252.84,"speed":6.42,"datetime":"2017-06-11 03:00"},{"direction":249.48,"speed":5.67,"datetime":"2017-06-11 04:00"},{"direction":245.57,"speed":5.44,"datetime":"2017-06-11 05:00"},{"direction":246.22,"speed":6.22,"datetime":"2017-06-11 06:00"},{"direction":249.3,"speed":7.52,"datetime":"2017-06-11 07:00"},{"direction":252.34,"speed":8.61,"datetime":"2017-06-11 08:00"},{"direction":254.97,"speed":8.88,"datetime":"2017-06-11 09:00"},{"direction":258.01,"speed":8.31,"datetime":"2017-06-11 10:00"},{"direction":262.72,"speed":7.03,"datetime":"2017-06-11 11:00"},{"direction":271.68,"speed":5.28,"datetime":"2017-06-11 12:00"},{"direction":289.24,"speed":3.65,"datetime":"2017-06-11 13:00"},{"direction":316.33,"speed":2.77,"datetime":"2017-06-11 14:00"},{"direction":337.09,"speed":2.58,"datetime":"2017-06-11 15:00"},{"direction":348.26,"speed":2.36,"datetime":"2017-06-11 16:00"},{"direction":0.56,"speed":1.88,"datetime":"2017-06-11 17:00"},{"direction":30.93,"speed":1.38,"datetime":"2017-06-11 18:00"},{"direction":66.22,"speed":1.34,"datetime":"2017-06-11 19:00"},{"direction":73.69,"speed":1.09,"datetime":"2017-06-11 20:00"},{"direction":350.71,"speed":0.78,"datetime":"2017-06-11 21:00"},{"direction":316.38,"speed":2.64,"datetime":"2017-06-11 22:00"},{"direction":317.3,"speed":4.93,"datetime":"2017-06-11 23:00"},{"direction":323.67,"speed":7.06,"datetime":"2017-06-12 00:00"},{"direction":328.53,"speed":8.67,"datetime":"2017-06-12 01:00"},{"direction":329.12,"speed":9.19,"datetime":"2017-06-12 02:00"},{"direction":322.75,"speed":8.35,"datetime":"2017-06-12 03:00"}]
             * temperature : [{"value":26,"datetime":"2017-06-10 04:00"},{"value":26,"datetime":"2017-06-10 05:00"},{"value":26,"datetime":"2017-06-10 06:00"},{"value":27,"datetime":"2017-06-10 07:00"},{"value":28,"datetime":"2017-06-10 08:00"},{"value":29,"datetime":"2017-06-10 09:00"},{"value":30,"datetime":"2017-06-10 10:00"},{"value":30,"datetime":"2017-06-10 11:00"},{"value":30,"datetime":"2017-06-10 12:00"},{"value":30,"datetime":"2017-06-10 13:00"},{"value":30,"datetime":"2017-06-10 14:00"},{"value":30,"datetime":"2017-06-10 15:00"},{"value":29,"datetime":"2017-06-10 16:00"},{"value":29,"datetime":"2017-06-10 17:00"},{"value":28,"datetime":"2017-06-10 18:00"},{"value":28,"datetime":"2017-06-10 19:00"},{"value":27,"datetime":"2017-06-10 20:00"},{"value":27,"datetime":"2017-06-10 21:00"},{"value":27,"datetime":"2017-06-10 22:00"},{"value":27,"datetime":"2017-06-10 23:00"},{"value":27.11,"datetime":"2017-06-11 00:00"},{"value":26.86,"datetime":"2017-06-11 01:00"},{"value":26.56,"datetime":"2017-06-11 02:00"},{"value":26.22,"datetime":"2017-06-11 03:00"},{"value":26,"datetime":"2017-06-11 04:00"},{"value":26.09,"datetime":"2017-06-11 05:00"},{"value":26.61,"datetime":"2017-06-11 06:00"},{"value":27.48,"datetime":"2017-06-11 07:00"},{"value":28.52,"datetime":"2017-06-11 08:00"},{"value":29.59,"datetime":"2017-06-11 09:00"},{"value":30.57,"datetime":"2017-06-11 10:00"},{"value":31.34,"datetime":"2017-06-11 11:00"},{"value":31.82,"datetime":"2017-06-11 12:00"},{"value":32,"datetime":"2017-06-11 13:00"},{"value":31.88,"datetime":"2017-06-11 14:00"},{"value":31.49,"datetime":"2017-06-11 15:00"},{"value":30.92,"datetime":"2017-06-11 16:00"},{"value":30.27,"datetime":"2017-06-11 17:00"},{"value":29.63,"datetime":"2017-06-11 18:00"},{"value":29.07,"datetime":"2017-06-11 19:00"},{"value":28.64,"datetime":"2017-06-11 20:00"},{"value":28.36,"datetime":"2017-06-11 21:00"},{"value":28.15,"datetime":"2017-06-11 22:00"},{"value":27.89,"datetime":"2017-06-11 23:00"},{"value":29.05,"datetime":"2017-06-12 00:00"},{"value":27.93,"datetime":"2017-06-12 01:00"},{"value":26.93,"datetime":"2017-06-12 02:00"},{"value":26.28,"datetime":"2017-06-12 03:00"}]
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
                 * datetime : 2017-06-10 04:00
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
                 * value : 0.46
                 * datetime : 2017-06-10 04:00
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
                 * value : 18
                 * datetime : 2017-06-10 04:00
                 */

                private int value;
                private String datetime;

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
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
                 * value : 0.89
                 * datetime : 2017-06-10 04:00
                 */

                private float value;
                private String datetime;

                public float getValue() {
                    return value;
                }

                public void setValue(float value) {
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
                 * value : 11
                 * datetime : 2017-06-10 04:00
                 */

                private int value;
                private String datetime;

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
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
                 * value : 0
                 * datetime : 2017-06-10 04:00
                 */

                private float value;
                private String datetime;

                public float getValue() {
                    return value;
                }

                public void setValue(float value) {
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
                 * direction : 233.09
                 * speed : 6.54
                 * datetime : 2017-06-10 04:00
                 */

                private float direction;
                private float speed;
                private String datetime;

                public float getDirection() {
                    return direction;
                }

                public void setDirection(float direction) {
                    this.direction = direction;
                }

                public float getSpeed() {
                    return speed;
                }

                public void setSpeed(float speed) {
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
                 * value : 26
                 * datetime : 2017-06-10 04:00
                 */

                private float value;
                private String datetime;

                public float getValue() {
                    return value;
                }

                public void setValue(float value) {
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

        public static class MinutelyBean {
            /**
             * status : ok
             * description : 不会有雨，最近的降雨带在南边128公里外呢
             * probability : [0.0693088919,0.1096921489,0.1234315932,0.1672359109]
             * datasource : radar
             * precipitation_2h : [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
             * precipitation : [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
             */

            private String status;
            private String description;
            private String datasource;
            private List<Double> probability;
            private List<Integer> precipitation_2h;
            private List<Integer> precipitation;

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

            public List<Integer> getPrecipitation_2h() {
                return precipitation_2h;
            }

            public void setPrecipitation_2h(List<Integer> precipitation_2h) {
                this.precipitation_2h = precipitation_2h;
            }

            public List<Integer> getPrecipitation() {
                return precipitation;
            }

            public void setPrecipitation(List<Integer> precipitation) {
                this.precipitation = precipitation;
            }
        }

        public static class DailyBean {
            /**
             * status : ok
             * coldRisk : [{"index":"3","desc":"易发","datetime":"2017-06-10"},{"index":"3","desc":"易发","datetime":"2017-06-11"},{"index":"3","desc":"易发","datetime":"2017-06-12"},{"index":"3","desc":"易发","datetime":"2017-06-13"},{"index":"3","desc":"易发","datetime":"2017-06-14"}]
             * temperature : [{"date":"2017-06-10","max":30,"avg":28.2,"min":26},{"date":"2017-06-11","max":32,"avg":28.88,"min":26},{"date":"2017-06-12","max":32,"avg":28.86,"min":26},{"date":"2017-06-13","max":32,"avg":28.13,"min":25},{"date":"2017-06-14","max":31,"avg":28.28,"min":25}]
             * skycon : [{"date":"2017-06-10","value":"PARTLY_CLOUDY_NIGHT"},{"date":"2017-06-11","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-06-12","value":"RAIN"},{"date":"2017-06-13","value":"RAIN"},{"date":"2017-06-14","value":"RAIN"}]
             * cloudrate : [{"date":"2017-06-10","max":0.84,"avg":0.66,"min":0},{"date":"2017-06-11","max":0.67,"avg":0.58,"min":0.37},{"date":"2017-06-12","max":1,"avg":0.95,"min":0.69},{"date":"2017-06-13","max":1,"avg":0.89,"min":0.59},{"date":"2017-06-14","max":1,"avg":0.99,"min":0.95}]
             * aqi : [{"date":"2017-06-10","max":23,"avg":20.35,"min":18},{"date":"2017-06-11","max":27,"avg":22.33,"min":19},{"date":"2017-06-12","max":23,"avg":19.54,"min":18},{"date":"2017-06-13","max":18,"avg":16.12,"min":15},{"date":"2017-06-14","max":18,"avg":13.67,"min":10}]
             * humidity : [{"date":"2017-06-10","max":0.9,"avg":0.84,"min":0.78},{"date":"2017-06-11","max":0.95,"avg":0.83,"min":0.71},{"date":"2017-06-12","max":0.9,"avg":0.84,"min":0.78},{"date":"2017-06-13","max":0.91,"avg":0.86,"min":0.8},{"date":"2017-06-14","max":0.92,"avg":0.87,"min":0.82}]
             * astro : [{"date":"2017-06-10","sunset":{"time":"18:43"},"sunrise":{"time":"05:02"}},{"date":"2017-06-11","sunset":{"time":"18:43"},"sunrise":{"time":"05:02"}},{"date":"2017-06-12","sunset":{"time":"18:43"},"sunrise":{"time":"05:02"}},{"date":"2017-06-13","sunset":{"time":"18:44"},"sunrise":{"time":"05:02"}},{"date":"2017-06-14","sunset":{"time":"18:44"},"sunrise":{"time":"05:02"}}]
             * ultraviolet : [{"index":"2","desc":"弱","datetime":"2017-06-10"},{"index":"2","desc":"弱","datetime":"2017-06-11"},{"index":"1","desc":"最弱","datetime":"2017-06-12"},{"index":"1","desc":"最弱","datetime":"2017-06-13"},{"index":"1","desc":"最弱","datetime":"2017-06-14"}]
             * pm25 : [{"date":"2017-06-10","max":15,"avg":13,"min":11},{"date":"2017-06-11","max":18,"avg":14.54,"min":12},{"date":"2017-06-12","max":15,"avg":12.42,"min":11},{"date":"2017-06-13","max":11,"avg":9.96,"min":9},{"date":"2017-06-14","max":11,"avg":7.96,"min":5}]
             * dressing : [{"index":"2","desc":"很热","datetime":"2017-06-10"},{"index":"2","desc":"很热","datetime":"2017-06-11"},{"index":"2","desc":"很热","datetime":"2017-06-12"},{"index":"2","desc":"很热","datetime":"2017-06-13"},{"index":"3","desc":"热","datetime":"2017-06-14"}]
             * carWashing : [{"index":"3","desc":"较不适宜","datetime":"2017-06-10"},{"index":"3","desc":"较不适宜","datetime":"2017-06-11"},{"index":"3","desc":"较不适宜","datetime":"2017-06-12"},{"index":"3","desc":"较不适宜","datetime":"2017-06-13"},{"index":"3","desc":"较不适宜","datetime":"2017-06-14"}]
             * precipitation : [{"date":"2017-06-10","max":0.1467,"avg":0.0408,"min":0},{"date":"2017-06-11","max":0.1707,"avg":0.0071,"min":0},{"date":"2017-06-12","max":1.2861,"avg":0.3871,"min":0},{"date":"2017-06-13","max":1.6714,"avg":0.4032,"min":0},{"date":"2017-06-14","max":3.2702,"avg":1.6148,"min":0.1423}]
             * wind : [{"date":"2017-06-10","max":{"direction":258.18,"speed":10.94},"avg":{"direction":233.47,"speed":6.27},"min":{"direction":239.17,"speed":2.32}},{"date":"2017-06-11","max":{"direction":254.97,"speed":8.88},"avg":{"direction":264.16,"speed":4.75},"min":{"direction":350.71,"speed":0.78}},{"date":"2017-06-12","max":{"direction":329.12,"speed":9.19},"avg":{"direction":242.19,"speed":6.25},"min":{"direction":68.11,"speed":0.77}},{"date":"2017-06-13","max":{"direction":57.25,"speed":13.61},"avg":{"direction":39.78,"speed":8.46},"min":{"direction":178.86,"speed":2.43}},{"date":"2017-06-14","max":{"direction":258.12,"speed":30.71},"avg":{"direction":342.29,"speed":18.93},"min":{"direction":232.17,"speed":8.52}}]
             * desc : [{"date":"2017-06-10","value":"大雨转阴"},{"date":"2017-06-11","value":"多云"},{"date":"2017-06-12","value":"多云"},{"date":"2017-06-13","value":"多云"},{"date":"2017-06-14","value":"多云"}]
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
            }

            public void setDesc(List<DescBean> desc) {
                this.desc = desc;
            }

            public static class ColdRiskBean {
                /**
                 * index : 3
                 * desc : 易发
                 * datetime : 2017-06-10
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
                 * date : 2017-06-10
                 * max : 30
                 * avg : 28.2
                 * min : 26
                 */

                private String date;
                private float max;
                private float avg;
                private float min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public float getMax() {
                    return max;
                }

                public void setMax(float max) {
                    this.max = max;
                }

                public float getAvg() {
                    return avg;
                }

                public void setAvg(float avg) {
                    this.avg = avg;
                }

                public float getMin() {
                    return min;
                }

                public void setMin(float min) {
                    this.min = min;
                }
            }

            public static class SkyconBeanX {
                /**
                 * date : 2017-06-10
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
                 * date : 2017-06-10
                 * max : 0.84
                 * avg : 0.66
                 * min : 0
                 */

                private String date;
                private double max;
                private double avg;
                private int min;

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

                public int getMin() {
                    return min;
                }

                public void setMin(int min) {
                    this.min = min;
                }
            }

            public static class AqiBeanX {
                /**
                 * date : 2017-06-10
                 * max : 23
                 * avg : 20.35
                 * min : 18
                 */

                private String date;
                private int max;
                private double avg;
                private int min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public int getMax() {
                    return max;
                }

                public void setMax(int max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public int getMin() {
                    return min;
                }

                public void setMin(int min) {
                    this.min = min;
                }
            }

            public static class HumidityBeanX {
                /**
                 * date : 2017-06-10
                 * max : 0.9
                 * avg : 0.84
                 * min : 0.78
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
                 * date : 2017-06-10
                 * sunset : {"time":"18:43"}
                 * sunrise : {"time":"05:02"}
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
                     * time : 18:43
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
                     * time : 05:02
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
                 * index : 2
                 * desc : 弱
                 * datetime : 2017-06-10
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
                 * date : 2017-06-10
                 * max : 15
                 * avg : 13
                 * min : 11
                 */

                private String date;
                private int max;
                private float avg;
                private int min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public int getMax() {
                    return max;
                }

                public void setMax(int max) {
                    this.max = max;
                }

                public float getAvg() {
                    return avg;
                }

                public void setAvg(float avg) {
                    this.avg = avg;
                }

                public int getMin() {
                    return min;
                }

                public void setMin(int min) {
                    this.min = min;
                }
            }

            public static class DressingBean {
                /**
                 * index : 2
                 * desc : 很热
                 * datetime : 2017-06-10
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
                 * index : 3
                 * desc : 较不适宜
                 * datetime : 2017-06-10
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
                 * date : 2017-06-10
                 * max : 0.1467
                 * avg : 0.0408
                 * min : 0
                 */

                private String date;
                private float max;
                private float avg;
                private float min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public float getMax() {
                    return max;
                }

                public void setMax(float max) {
                    this.max = max;
                }

                public float getAvg() {
                    return avg;
                }

                public void setAvg(float avg) {
                    this.avg = avg;
                }

                public float getMin() {
                    return min;
                }

                public void setMin(float min) {
                    this.min = min;
                }
            }

            public static class WindBeanX {
                /**
                 * date : 2017-06-10
                 * max : {"direction":258.18,"speed":10.94}
                 * avg : {"direction":233.47,"speed":6.27}
                 * min : {"direction":239.17,"speed":2.32}
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
                     * direction : 258.18
                     * speed : 10.94
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
                     * direction : 233.47
                     * speed : 6.27
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
                     * direction : 239.17
                     * speed : 2.32
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
                 * date : 2017-06-10
                 * value : 大雨转阴
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
        }
    }
}
