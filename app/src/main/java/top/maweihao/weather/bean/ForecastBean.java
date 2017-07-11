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
     * result : {"hourly":{"status":"ok","description":"小雨，今天晚间21点钟后转多云，其后阴","skycon":[{"value":"RAIN","datetime":"2017-07-08 18:00"},{"value":"RAIN","datetime":"2017-07-08 19:00"},{"value":"RAIN","datetime":"2017-07-08 20:00"},{"value":"CLOUDY","datetime":"2017-07-08 21:00"},{"value":"CLOUDY","datetime":"2017-07-08 22:00"},{"value":"CLOUDY","datetime":"2017-07-08 23:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-09 00:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-09 01:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-09 02:00"},{"value":"CLOUDY","datetime":"2017-07-09 03:00"},{"value":"CLOUDY","datetime":"2017-07-09 04:00"},{"value":"CLOUDY","datetime":"2017-07-09 05:00"},{"value":"CLOUDY","datetime":"2017-07-09 06:00"},{"value":"CLOUDY","datetime":"2017-07-09 07:00"},{"value":"CLOUDY","datetime":"2017-07-09 08:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-09 09:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-09 10:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-09 11:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-09 12:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-09 13:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-09 14:00"},{"value":"CLOUDY","datetime":"2017-07-09 15:00"},{"value":"CLOUDY","datetime":"2017-07-09 16:00"},{"value":"CLOUDY","datetime":"2017-07-09 17:00"},{"value":"RAIN","datetime":"2017-07-09 18:00"},{"value":"RAIN","datetime":"2017-07-09 19:00"},{"value":"CLOUDY","datetime":"2017-07-09 20:00"},{"value":"CLOUDY","datetime":"2017-07-09 21:00"},{"value":"CLOUDY","datetime":"2017-07-09 22:00"},{"value":"CLOUDY","datetime":"2017-07-09 23:00"},{"value":"CLOUDY","datetime":"2017-07-10 00:00"},{"value":"CLOUDY","datetime":"2017-07-10 01:00"},{"value":"CLOUDY","datetime":"2017-07-10 02:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-10 03:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-10 04:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 05:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 06:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 07:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 08:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 09:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 10:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 11:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 12:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 13:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 14:00"},{"value":"RAIN","datetime":"2017-07-10 15:00"},{"value":"RAIN","datetime":"2017-07-10 16:00"},{"value":"RAIN","datetime":"2017-07-10 17:00"}],"cloudrate":[{"value":1,"datetime":"2017-07-08 18:00"},{"value":1,"datetime":"2017-07-08 19:00"},{"value":0.99,"datetime":"2017-07-08 20:00"},{"value":0.94,"datetime":"2017-07-08 21:00"},{"value":0.88,"datetime":"2017-07-08 22:00"},{"value":0.81,"datetime":"2017-07-08 23:00"},{"value":0.75,"datetime":"2017-07-09 00:00"},{"value":0.72,"datetime":"2017-07-09 01:00"},{"value":0.73,"datetime":"2017-07-09 02:00"},{"value":0.81,"datetime":"2017-07-09 03:00"},{"value":0.9,"datetime":"2017-07-09 04:00"},{"value":0.96,"datetime":"2017-07-09 05:00"},{"value":0.96,"datetime":"2017-07-09 06:00"},{"value":0.91,"datetime":"2017-07-09 07:00"},{"value":0.85,"datetime":"2017-07-09 08:00"},{"value":0.79,"datetime":"2017-07-09 09:00"},{"value":0.75,"datetime":"2017-07-09 10:00"},{"value":0.73,"datetime":"2017-07-09 11:00"},{"value":0.72,"datetime":"2017-07-09 12:00"},{"value":0.74,"datetime":"2017-07-09 13:00"},{"value":0.78,"datetime":"2017-07-09 14:00"},{"value":0.84,"datetime":"2017-07-09 15:00"},{"value":0.9,"datetime":"2017-07-09 16:00"},{"value":0.95,"datetime":"2017-07-09 17:00"},{"value":0.96,"datetime":"2017-07-09 18:00"},{"value":0.95,"datetime":"2017-07-09 19:00"},{"value":0.94,"datetime":"2017-07-09 20:00"},{"value":0.92,"datetime":"2017-07-09 21:00"},{"value":0.92,"datetime":"2017-07-09 22:00"},{"value":0.92,"datetime":"2017-07-09 23:00"},{"value":0.91,"datetime":"2017-07-10 00:00"},{"value":0.9,"datetime":"2017-07-10 01:00"},{"value":0.87,"datetime":"2017-07-10 02:00"},{"value":0.8,"datetime":"2017-07-10 03:00"},{"value":0.73,"datetime":"2017-07-10 04:00"},{"value":0.69,"datetime":"2017-07-10 05:00"},{"value":0.68,"datetime":"2017-07-10 06:00"},{"value":0.7,"datetime":"2017-07-10 07:00"},{"value":0.73,"datetime":"2017-07-10 08:00"},{"value":0.74,"datetime":"2017-07-10 09:00"},{"value":0.75,"datetime":"2017-07-10 10:00"},{"value":0.74,"datetime":"2017-07-10 11:00"},{"value":0.74,"datetime":"2017-07-10 12:00"},{"value":0.75,"datetime":"2017-07-10 13:00"},{"value":0.77,"datetime":"2017-07-10 14:00"},{"value":0.82,"datetime":"2017-07-10 15:00"},{"value":0.87,"datetime":"2017-07-10 16:00"},{"value":0.91,"datetime":"2017-07-10 17:00"}],"aqi":[{"value":12,"datetime":"2017-07-08 18:00"},{"value":11,"datetime":"2017-07-08 19:00"},{"value":11,"datetime":"2017-07-08 20:00"},{"value":12,"datetime":"2017-07-08 21:00"},{"value":14,"datetime":"2017-07-08 22:00"},{"value":14,"datetime":"2017-07-08 23:00"},{"value":15,"datetime":"2017-07-09 00:00"},{"value":15,"datetime":"2017-07-09 01:00"},{"value":15,"datetime":"2017-07-09 02:00"},{"value":15,"datetime":"2017-07-09 03:00"},{"value":16,"datetime":"2017-07-09 04:00"},{"value":16,"datetime":"2017-07-09 05:00"},{"value":16,"datetime":"2017-07-09 06:00"},{"value":18,"datetime":"2017-07-09 07:00"},{"value":19,"datetime":"2017-07-09 08:00"},{"value":20,"datetime":"2017-07-09 09:00"},{"value":20,"datetime":"2017-07-09 10:00"},{"value":20,"datetime":"2017-07-09 11:00"},{"value":20,"datetime":"2017-07-09 12:00"},{"value":22,"datetime":"2017-07-09 13:00"},{"value":22,"datetime":"2017-07-09 14:00"},{"value":22,"datetime":"2017-07-09 15:00"},{"value":22,"datetime":"2017-07-09 16:00"},{"value":20,"datetime":"2017-07-09 17:00"},{"value":20,"datetime":"2017-07-09 18:00"},{"value":20,"datetime":"2017-07-09 19:00"},{"value":20,"datetime":"2017-07-09 20:00"},{"value":20,"datetime":"2017-07-09 21:00"},{"value":20,"datetime":"2017-07-09 22:00"},{"value":19,"datetime":"2017-07-09 23:00"},{"value":19,"datetime":"2017-07-10 00:00"},{"value":19,"datetime":"2017-07-10 01:00"},{"value":19,"datetime":"2017-07-10 02:00"},{"value":20,"datetime":"2017-07-10 03:00"},{"value":20,"datetime":"2017-07-10 04:00"},{"value":22,"datetime":"2017-07-10 05:00"},{"value":22,"datetime":"2017-07-10 06:00"},{"value":23,"datetime":"2017-07-10 07:00"},{"value":23,"datetime":"2017-07-10 08:00"},{"value":24,"datetime":"2017-07-10 09:00"},{"value":24,"datetime":"2017-07-10 10:00"},{"value":26,"datetime":"2017-07-10 11:00"},{"value":27,"datetime":"2017-07-10 12:00"},{"value":27,"datetime":"2017-07-10 13:00"},{"value":29,"datetime":"2017-07-10 14:00"},{"value":29,"datetime":"2017-07-10 15:00"},{"value":29,"datetime":"2017-07-10 16:00"},{"value":29,"datetime":"2017-07-10 17:00"}],"humidity":[{"value":0.76,"datetime":"2017-07-08 18:00"},{"value":0.77,"datetime":"2017-07-08 19:00"},{"value":0.78,"datetime":"2017-07-08 20:00"},{"value":0.79,"datetime":"2017-07-08 21:00"},{"value":0.79,"datetime":"2017-07-08 22:00"},{"value":0.8,"datetime":"2017-07-08 23:00"},{"value":0.81,"datetime":"2017-07-09 00:00"},{"value":0.82,"datetime":"2017-07-09 01:00"},{"value":0.82,"datetime":"2017-07-09 02:00"},{"value":0.83,"datetime":"2017-07-09 03:00"},{"value":0.83,"datetime":"2017-07-09 04:00"},{"value":0.83,"datetime":"2017-07-09 05:00"},{"value":0.81,"datetime":"2017-07-09 06:00"},{"value":0.79,"datetime":"2017-07-09 07:00"},{"value":0.76,"datetime":"2017-07-09 08:00"},{"value":0.73,"datetime":"2017-07-09 09:00"},{"value":0.7,"datetime":"2017-07-09 10:00"},{"value":0.68,"datetime":"2017-07-09 11:00"},{"value":0.67,"datetime":"2017-07-09 12:00"},{"value":0.68,"datetime":"2017-07-09 13:00"},{"value":0.69,"datetime":"2017-07-09 14:00"},{"value":0.72,"datetime":"2017-07-09 15:00"},{"value":0.74,"datetime":"2017-07-09 16:00"},{"value":0.77,"datetime":"2017-07-09 17:00"},{"value":0.78,"datetime":"2017-07-09 18:00"},{"value":0.79,"datetime":"2017-07-09 19:00"},{"value":0.8,"datetime":"2017-07-09 20:00"},{"value":0.81,"datetime":"2017-07-09 21:00"},{"value":0.81,"datetime":"2017-07-09 22:00"},{"value":0.82,"datetime":"2017-07-09 23:00"},{"value":0.82,"datetime":"2017-07-10 00:00"},{"value":0.83,"datetime":"2017-07-10 01:00"},{"value":0.84,"datetime":"2017-07-10 02:00"},{"value":0.85,"datetime":"2017-07-10 03:00"},{"value":0.85,"datetime":"2017-07-10 04:00"},{"value":0.85,"datetime":"2017-07-10 05:00"},{"value":0.84,"datetime":"2017-07-10 06:00"},{"value":0.83,"datetime":"2017-07-10 07:00"},{"value":0.8,"datetime":"2017-07-10 08:00"},{"value":0.77,"datetime":"2017-07-10 09:00"},{"value":0.74,"datetime":"2017-07-10 10:00"},{"value":0.72,"datetime":"2017-07-10 11:00"},{"value":0.7,"datetime":"2017-07-10 12:00"},{"value":0.69,"datetime":"2017-07-10 13:00"},{"value":0.69,"datetime":"2017-07-10 14:00"},{"value":0.71,"datetime":"2017-07-10 15:00"},{"value":0.74,"datetime":"2017-07-10 16:00"},{"value":0.76,"datetime":"2017-07-10 17:00"}],"pres":[{"value":98246.7615368365,"datetime":"2017-07-08 18:00"},{"value":98248.0766940549,"datetime":"2017-07-08 19:00"},{"value":98256.0458191215,"datetime":"2017-07-08 20:00"},{"value":98281.4485588495,"datetime":"2017-07-08 21:00"},{"value":98310.7906809261,"datetime":"2017-07-08 22:00"},{"value":98324.5094832569,"datetime":"2017-07-08 23:00"},{"value":98308.7754571279,"datetime":"2017-07-09 00:00"},{"value":98272.6918673465,"datetime":"2017-07-09 01:00"},{"value":98231.0951721006,"datetime":"2017-07-09 02:00"},{"value":98196.9157645531,"datetime":"2017-07-09 03:00"},{"value":98175.4597777664,"datetime":"2017-07-09 04:00"},{"value":98170.1272797777,"datetime":"2017-07-09 05:00"},{"value":98181.7495545232,"datetime":"2017-07-09 06:00"},{"value":98200.8827495329,"datetime":"2017-07-09 07:00"},{"value":98215.5142282352,"datetime":"2017-07-09 08:00"},{"value":98216.2407452333,"datetime":"2017-07-09 09:00"},{"value":98204.0966198285,"datetime":"2017-07-09 10:00"},{"value":98182.7255624965,"datetime":"2017-07-09 11:00"},{"value":98155.0171160804,"datetime":"2017-07-09 12:00"},{"value":98120.8441528924,"datetime":"2017-07-09 13:00"},{"value":98079.325377612,"datetime":"2017-07-09 14:00"},{"value":98031.5637508994,"datetime":"2017-07-09 15:00"},{"value":97986.5992573368,"datetime":"2017-07-09 16:00"},{"value":97955.4561374871,"datetime":"2017-07-09 17:00"},{"value":97946.2865239683,"datetime":"2017-07-09 18:00"},{"value":97955.7541176185,"datetime":"2017-07-09 19:00"},{"value":97977.6505113308,"datetime":"2017-07-09 20:00"},{"value":98004.3668029205,"datetime":"2017-07-09 21:00"},{"value":98022.6921098902,"datetime":"2017-07-09 22:00"},{"value":98018.0150546651,"datetime":"2017-07-09 23:00"},{"value":97982.40183186,"datetime":"2017-07-10 00:00"},{"value":97934.6289248492,"datetime":"2017-07-10 01:00"},{"value":97900.1503891972,"datetime":"2017-07-10 02:00"},{"value":97897.9828590703,"datetime":"2017-07-10 03:00"},{"value":97921.3932830418,"datetime":"2017-07-10 04:00"},{"value":97957.2111882872,"datetime":"2017-07-10 05:00"},{"value":97995.113320888,"datetime":"2017-07-10 06:00"},{"value":98036.1653025511,"datetime":"2017-07-10 07:00"},{"value":98084.2799738894,"datetime":"2017-07-10 08:00"},{"value":98140.7011091255,"datetime":"2017-07-10 09:00"},{"value":98195.9962169206,"datetime":"2017-07-10 10:00"},{"value":98238.0637395456,"datetime":"2017-07-10 11:00"},{"value":98257.6452895099,"datetime":"2017-07-10 12:00"},{"value":98256.8551602764,"datetime":"2017-07-10 13:00"},{"value":98240.6508155466,"datetime":"2017-07-10 14:00"},{"value":98215.3321733754,"datetime":"2017-07-10 15:00"},{"value":98192.5689692304,"datetime":"2017-07-10 16:00"},{"value":98185.373392933,"datetime":"2017-07-10 17:00"}],"pm25":[{"value":6,"datetime":"2017-07-08 18:00"},{"value":6,"datetime":"2017-07-08 19:00"},{"value":6,"datetime":"2017-07-08 20:00"},{"value":7,"datetime":"2017-07-08 21:00"},{"value":8,"datetime":"2017-07-08 22:00"},{"value":8,"datetime":"2017-07-08 23:00"},{"value":9,"datetime":"2017-07-09 00:00"},{"value":9,"datetime":"2017-07-09 01:00"},{"value":9,"datetime":"2017-07-09 02:00"},{"value":9,"datetime":"2017-07-09 03:00"},{"value":10,"datetime":"2017-07-09 04:00"},{"value":10,"datetime":"2017-07-09 05:00"},{"value":10,"datetime":"2017-07-09 06:00"},{"value":11,"datetime":"2017-07-09 07:00"},{"value":12,"datetime":"2017-07-09 08:00"},{"value":13,"datetime":"2017-07-09 09:00"},{"value":13,"datetime":"2017-07-09 10:00"},{"value":13,"datetime":"2017-07-09 11:00"},{"value":13,"datetime":"2017-07-09 12:00"},{"value":14,"datetime":"2017-07-09 13:00"},{"value":14,"datetime":"2017-07-09 14:00"},{"value":14,"datetime":"2017-07-09 15:00"},{"value":14,"datetime":"2017-07-09 16:00"},{"value":13,"datetime":"2017-07-09 17:00"},{"value":13,"datetime":"2017-07-09 18:00"},{"value":13,"datetime":"2017-07-09 19:00"},{"value":13,"datetime":"2017-07-09 20:00"},{"value":13,"datetime":"2017-07-09 21:00"},{"value":13,"datetime":"2017-07-09 22:00"},{"value":12,"datetime":"2017-07-09 23:00"},{"value":12,"datetime":"2017-07-10 00:00"},{"value":12,"datetime":"2017-07-10 01:00"},{"value":12,"datetime":"2017-07-10 02:00"},{"value":13,"datetime":"2017-07-10 03:00"},{"value":13,"datetime":"2017-07-10 04:00"},{"value":14,"datetime":"2017-07-10 05:00"},{"value":14,"datetime":"2017-07-10 06:00"},{"value":15,"datetime":"2017-07-10 07:00"},{"value":15,"datetime":"2017-07-10 08:00"},{"value":16,"datetime":"2017-07-10 09:00"},{"value":16,"datetime":"2017-07-10 10:00"},{"value":17,"datetime":"2017-07-10 11:00"},{"value":18,"datetime":"2017-07-10 12:00"},{"value":18,"datetime":"2017-07-10 13:00"},{"value":19,"datetime":"2017-07-10 14:00"},{"value":19,"datetime":"2017-07-10 15:00"},{"value":19,"datetime":"2017-07-10 16:00"},{"value":19,"datetime":"2017-07-10 17:00"}],"precipitation":[{"value":0.2782,"datetime":"2017-07-08 18:00"},{"value":7.2052,"datetime":"2017-07-08 19:00"},{"value":4.6179,"datetime":"2017-07-08 20:00"},{"value":0,"datetime":"2017-07-08 21:00"},{"value":0,"datetime":"2017-07-08 22:00"},{"value":0,"datetime":"2017-07-08 23:00"},{"value":0,"datetime":"2017-07-09 00:00"},{"value":0,"datetime":"2017-07-09 01:00"},{"value":0,"datetime":"2017-07-09 02:00"},{"value":0,"datetime":"2017-07-09 03:00"},{"value":0,"datetime":"2017-07-09 04:00"},{"value":0,"datetime":"2017-07-09 05:00"},{"value":0,"datetime":"2017-07-09 06:00"},{"value":0,"datetime":"2017-07-09 07:00"},{"value":0,"datetime":"2017-07-09 08:00"},{"value":0,"datetime":"2017-07-09 09:00"},{"value":0,"datetime":"2017-07-09 10:00"},{"value":0,"datetime":"2017-07-09 11:00"},{"value":0,"datetime":"2017-07-09 12:00"},{"value":0,"datetime":"2017-07-09 13:00"},{"value":0,"datetime":"2017-07-09 14:00"},{"value":0,"datetime":"2017-07-09 15:00"},{"value":0.0468,"datetime":"2017-07-09 16:00"},{"value":0.0604,"datetime":"2017-07-09 17:00"},{"value":0.0651,"datetime":"2017-07-09 18:00"},{"value":0.0616,"datetime":"2017-07-09 19:00"},{"value":0.0523,"datetime":"2017-07-09 20:00"},{"value":0,"datetime":"2017-07-09 21:00"},{"value":0,"datetime":"2017-07-09 22:00"},{"value":0,"datetime":"2017-07-09 23:00"},{"value":0,"datetime":"2017-07-10 00:00"},{"value":0,"datetime":"2017-07-10 01:00"},{"value":0,"datetime":"2017-07-10 02:00"},{"value":0,"datetime":"2017-07-10 03:00"},{"value":0,"datetime":"2017-07-10 04:00"},{"value":0,"datetime":"2017-07-10 05:00"},{"value":0,"datetime":"2017-07-10 06:00"},{"value":0,"datetime":"2017-07-10 07:00"},{"value":0,"datetime":"2017-07-10 08:00"},{"value":0,"datetime":"2017-07-10 09:00"},{"value":0,"datetime":"2017-07-10 10:00"},{"value":0,"datetime":"2017-07-10 11:00"},{"value":0,"datetime":"2017-07-10 12:00"},{"value":0,"datetime":"2017-07-10 13:00"},{"value":0,"datetime":"2017-07-10 14:00"},{"value":0.2023,"datetime":"2017-07-10 15:00"},{"value":0.4119,"datetime":"2017-07-10 16:00"},{"value":0.5604,"datetime":"2017-07-10 17:00"}],"wind":[{"direction":104.32,"speed":4.18,"datetime":"2017-07-08 18:00"},{"direction":126.3,"speed":3.65,"datetime":"2017-07-08 19:00"},{"direction":153.06,"speed":3.34,"datetime":"2017-07-08 20:00"},{"direction":178.93,"speed":3.44,"datetime":"2017-07-08 21:00"},{"direction":198.48,"speed":3.88,"datetime":"2017-07-08 22:00"},{"direction":210.11,"speed":4.43,"datetime":"2017-07-08 23:00"},{"direction":214.73,"speed":4.9,"datetime":"2017-07-09 00:00"},{"direction":214.3,"speed":5.12,"datetime":"2017-07-09 01:00"},{"direction":209.51,"speed":5.03,"datetime":"2017-07-09 02:00"},{"direction":200.23,"speed":4.71,"datetime":"2017-07-09 03:00"},{"direction":189.88,"speed":4.44,"datetime":"2017-07-09 04:00"},{"direction":185.22,"speed":4.28,"datetime":"2017-07-09 05:00"},{"direction":191.35,"speed":4.18,"datetime":"2017-07-09 06:00"},{"direction":202.24,"speed":4.12,"datetime":"2017-07-09 07:00"},{"direction":209.61,"speed":3.84,"datetime":"2017-07-09 08:00"},{"direction":207.56,"speed":2.93,"datetime":"2017-07-09 09:00"},{"direction":192.19,"speed":1.58,"datetime":"2017-07-09 10:00"},{"direction":105.88,"speed":0.56,"datetime":"2017-07-09 11:00"},{"direction":27.13,"speed":1.7,"datetime":"2017-07-09 12:00"},{"direction":8.89,"speed":3.08,"datetime":"2017-07-09 13:00"},{"direction":358.2,"speed":3.91,"datetime":"2017-07-09 14:00"},{"direction":347.91,"speed":3.86,"datetime":"2017-07-09 15:00"},{"direction":332.8,"speed":3.25,"datetime":"2017-07-09 16:00"},{"direction":308.84,"speed":2.69,"datetime":"2017-07-09 17:00"},{"direction":280.6,"speed":2.61,"datetime":"2017-07-09 18:00"},{"direction":255.89,"speed":2.97,"datetime":"2017-07-09 19:00"},{"direction":236.16,"speed":3.62,"datetime":"2017-07-09 20:00"},{"direction":220.8,"speed":4.52,"datetime":"2017-07-09 21:00"},{"direction":211.1,"speed":5.51,"datetime":"2017-07-09 22:00"},{"direction":206.81,"speed":6.3,"datetime":"2017-07-09 23:00"},{"direction":207.07,"speed":6.66,"datetime":"2017-07-10 00:00"},{"direction":209.4,"speed":6.57,"datetime":"2017-07-10 01:00"},{"direction":211.58,"speed":6.05,"datetime":"2017-07-10 02:00"},{"direction":211.99,"speed":5.14,"datetime":"2017-07-10 03:00"},{"direction":213.16,"speed":4.19,"datetime":"2017-07-10 04:00"},{"direction":221.16,"speed":3.62,"datetime":"2017-07-10 05:00"},{"direction":237.09,"speed":3.94,"datetime":"2017-07-10 06:00"},{"direction":248.6,"speed":4.84,"datetime":"2017-07-10 07:00"},{"direction":252.95,"speed":5.48,"datetime":"2017-07-10 08:00"},{"direction":252.14,"speed":5.19,"datetime":"2017-07-10 09:00"},{"direction":250.05,"speed":4.24,"datetime":"2017-07-10 10:00"},{"direction":254.23,"speed":3.05,"datetime":"2017-07-10 11:00"},{"direction":280.07,"speed":2.32,"datetime":"2017-07-10 12:00"},{"direction":307.92,"speed":2.7,"datetime":"2017-07-10 13:00"},{"direction":310.85,"speed":3.26,"datetime":"2017-07-10 14:00"},{"direction":291.87,"speed":3.46,"datetime":"2017-07-10 15:00"},{"direction":264.03,"speed":3.93,"datetime":"2017-07-10 16:00"},{"direction":240.61,"speed":4.59,"datetime":"2017-07-10 17:00"}],"temperature":[{"value":27.8,"datetime":"2017-07-08 18:00"},{"value":27,"datetime":"2017-07-08 19:00"},{"value":26.8,"datetime":"2017-07-08 20:00"},{"value":26.6,"datetime":"2017-07-08 21:00"},{"value":26.2,"datetime":"2017-07-08 22:00"},{"value":26.1,"datetime":"2017-07-08 23:00"},{"value":26,"datetime":"2017-07-09 00:00"},{"value":26.1,"datetime":"2017-07-09 01:00"},{"value":26.7,"datetime":"2017-07-09 02:00"},{"value":27.3,"datetime":"2017-07-09 03:00"},{"value":27.9,"datetime":"2017-07-09 04:00"},{"value":28.5,"datetime":"2017-07-09 05:00"},{"value":29.1,"datetime":"2017-07-09 06:00"},{"value":29.8,"datetime":"2017-07-09 07:00"},{"value":30.4,"datetime":"2017-07-09 08:00"},{"value":31.1,"datetime":"2017-07-09 09:00"},{"value":31.8,"datetime":"2017-07-09 10:00"},{"value":32,"datetime":"2017-07-09 11:00"},{"value":31.9,"datetime":"2017-07-09 12:00"},{"value":31.8,"datetime":"2017-07-09 13:00"},{"value":31.7,"datetime":"2017-07-09 14:00"},{"value":31.2,"datetime":"2017-07-09 15:00"},{"value":31,"datetime":"2017-07-09 16:00"},{"value":30.2,"datetime":"2017-07-09 17:00"},{"value":29.9,"datetime":"2017-07-09 18:00"},{"value":29.34,"datetime":"2017-07-09 19:00"},{"value":28.61,"datetime":"2017-07-09 20:00"},{"value":27.95,"datetime":"2017-07-09 21:00"},{"value":27.61,"datetime":"2017-07-09 22:00"},{"value":27.28,"datetime":"2017-07-09 23:00"},{"value":26.98,"datetime":"2017-07-10 00:00"},{"value":26.71,"datetime":"2017-07-10 01:00"},{"value":26.47,"datetime":"2017-07-10 02:00"},{"value":26.28,"datetime":"2017-07-10 03:00"},{"value":26.6,"datetime":"2017-07-10 04:00"},{"value":26,"datetime":"2017-07-10 05:00"},{"value":27.52,"datetime":"2017-07-10 06:00"},{"value":28.06,"datetime":"2017-07-10 07:00"},{"value":28.56,"datetime":"2017-07-10 08:00"},{"value":29.03,"datetime":"2017-07-10 09:00"},{"value":29.5,"datetime":"2017-07-10 10:00"},{"value":29.98,"datetime":"2017-07-10 11:00"},{"value":30.51,"datetime":"2017-07-10 12:00"},{"value":32,"datetime":"2017-07-10 13:00"},{"value":31.4,"datetime":"2017-07-10 14:00"},{"value":31.64,"datetime":"2017-07-10 15:00"},{"value":31.34,"datetime":"2017-07-10 16:00"},{"value":30.96,"datetime":"2017-07-10 17:00"}]},"minutely":{"status":"ok","description":"雨渐大，35分钟后转为大雨，不过一个半小时后雨会再次变小","probability":[0.8228367567,0.9411128163,0.9276605248,0.9082266092],"datasource":"radar","precipitation_2h":[0.1508,0.1756,0.1988,0.2195,0.2368,0.25,0.2585,0.2627,0.2633,0.2609,0.2562,0.25,0.2427,0.2343,0.2247,0.2138,0.2014,0.1875,0.1721,0.1566,0.1426,0.1316,0.1252,0.125,0.1321,0.1454,0.1634,0.1844,0.2068,0.2292,0.2503,0.271,0.2925,0.3162,0.3432,0.375,0.4119,0.4513,0.4895,0.5231,0.5487,0.5625,0.5624,0.551,0.532,0.5095,0.4871,0.4688,0.4574,0.4526,0.4527,0.4564,0.4622,0.4688,0.4748,0.4802,0.4853,0.4901,0.495,0.5,0.5052,0.5097,0.5124,0.5125,0.5087,0.5,0.486,0.4684,0.4493,0.4311,0.416,0.4063,0.4033,0.4059,0.4117,0.4186,0.4244,0.4271,0.4249,0.4186,0.4093,0.3981,0.3863,0.375,0.3652,0.3572,0.351,0.3466,0.3442,0.3438,0.345,0.3463,0.3457,0.3412,0.3308,0.3125,0.2853,0.2518,0.2155,0.1799,0.1486,0.125,0.112,0.1095,0.1166,0.1326,0.1565,0.1875,0.2244,0.2639,0.3024,0.3361,0.3616,0.375,0.3741,0.362,0.3432,0.3222,0.3035,0.2917],"precipitation":[0.1508,0.1756,0.1988,0.2195,0.2368,0.25,0.2585,0.2627,0.2633,0.2609,0.2562,0.25,0.2427,0.2343,0.2247,0.2138,0.2014,0.1875,0.1721,0.1566,0.1426,0.1316,0.1252,0.125,0.1321,0.1454,0.1634,0.1844,0.2068,0.2292,0.2503,0.271,0.2925,0.3162,0.3432,0.375,0.4119,0.4513,0.4895,0.5231,0.5487,0.5625,0.5624,0.551,0.532,0.5095,0.4871,0.4688,0.4574,0.4526,0.4527,0.4564,0.4622,0.4688,0.4748,0.4802,0.4853,0.4901,0.495,0.5]},"daily":{"status":"ok","coldRisk":[{"index":"3","desc":"易发","datetime":"2017-07-08"},{"index":"3","desc":"易发","datetime":"2017-07-09"},{"index":"3","desc":"易发","datetime":"2017-07-10"},{"index":"3","desc":"易发","datetime":"2017-07-11"},{"index":"3","desc":"易发","datetime":"2017-07-12"}],"temperature":[{"date":"2017-07-08","max":30,"avg":26.75,"min":26.1},{"date":"2017-07-09","max":32,"avg":29.38,"min":26},{"date":"2017-07-10","max":32,"avg":28.91,"min":26},{"date":"2017-07-11","max":32,"avg":28.93,"min":26},{"date":"2017-07-12","max":32,"avg":29.08,"min":26}],"skycon":[{"date":"2017-07-08","value":"RAIN"},{"date":"2017-07-09","value":"CLOUDY"},{"date":"2017-07-10","value":"RAIN"},{"date":"2017-07-11","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-07-12","value":"PARTLY_CLOUDY_DAY"}],"cloudrate":[{"date":"2017-07-08","max":1,"avg":0.94,"min":0.69},{"date":"2017-07-09","max":0.96,"avg":0.85,"min":0.72},{"date":"2017-07-10","max":0.93,"avg":0.78,"min":0.54},{"date":"2017-07-11","max":0.93,"avg":0.65,"min":0.44},{"date":"2017-07-12","max":0.93,"avg":0.66,"min":0.4}],"aqi":[{"date":"2017-07-08","max":14,"avg":12.33,"min":0},{"date":"2017-07-09","max":22,"avg":18.83,"min":15},{"date":"2017-07-10","max":30,"avg":25.25,"min":19},{"date":"2017-07-11","max":35,"avg":31.5,"min":29},{"date":"2017-07-12","max":31,"avg":30.46,"min":29}],"humidity":[{"date":"2017-07-08","max":0.81,"avg":0.78,"min":0.68},{"date":"2017-07-09","max":0.83,"avg":0.77,"min":0.67},{"date":"2017-07-10","max":0.85,"avg":0.78,"min":0.69},{"date":"2017-07-11","max":0.86,"avg":0.78,"min":0.71},{"date":"2017-07-12","max":0.85,"avg":0.76,"min":0.66}],"astro":[{"date":"2017-07-08","sunset":{"time":"18:47"},"sunrise":{"time":"05:09"}},{"date":"2017-07-09","sunset":{"time":"18:47"},"sunrise":{"time":"05:09"}},{"date":"2017-07-10","sunset":{"time":"18:47"},"sunrise":{"time":"05:10"}},{"date":"2017-07-11","sunset":{"time":"18:46"},"sunrise":{"time":"05:10"}},{"date":"2017-07-12","sunset":{"time":"18:46"},"sunrise":{"time":"05:11"}}],"pres":[{"date":"2017-07-08","max":98355.27,"avg":98277.94,"min":98167.86},{"date":"2017-07-09","max":98308.78,"avg":98117.08,"min":97946.29},{"date":"2017-07-10","max":98366.92,"avg":98142.03,"min":97897.98},{"date":"2017-07-11","max":98498.7,"avg":98387.6,"min":98268.74},{"date":"2017-07-12","max":98598.62,"avg":98421.92,"min":98258.59}],"ultraviolet":[{"index":"1","desc":"最弱","datetime":"2017-07-08"},{"index":"2","desc":"弱","datetime":"2017-07-09"},{"index":"2","desc":"弱","datetime":"2017-07-10"},{"index":"2","desc":"弱","datetime":"2017-07-11"},{"index":"1","desc":"最弱","datetime":"2017-07-12"}],"pm25":[{"date":"2017-07-08","max":8,"avg":6.83,"min":1},{"date":"2017-07-09","max":14,"avg":11.96,"min":9},{"date":"2017-07-10","max":20,"avg":16.5,"min":12},{"date":"2017-07-11","max":24,"avg":21.17,"min":19},{"date":"2017-07-12","max":21,"avg":20.46,"min":19}],"dressing":[{"index":"2","desc":"很热","datetime":"2017-07-08"},{"index":"2","desc":"很热","datetime":"2017-07-09"},{"index":"2","desc":"很热","datetime":"2017-07-10"},{"index":"2","desc":"很热","datetime":"2017-07-11"},{"index":"2","desc":"很热","datetime":"2017-07-12"}],"carWashing":[{"index":"3","desc":"较不适宜","datetime":"2017-07-08"},{"index":"3","desc":"较不适宜","datetime":"2017-07-09"},{"index":"3","desc":"较不适宜","datetime":"2017-07-10"},{"index":"3","desc":"较不适宜","datetime":"2017-07-11"},{"index":"1","desc":"适宜","datetime":"2017-07-12"}],"precipitation":[{"date":"2017-07-08","max":7.2052,"avg":2.0169,"min":0},{"date":"2017-07-09","max":0.0651,"avg":0.0119,"min":0},{"date":"2017-07-10","max":0.5746,"avg":0.1188,"min":0},{"date":"2017-07-11","max":0.0739,"avg":0.0256,"min":0},{"date":"2017-07-12","max":0,"avg":0,"min":0}],"wind":[{"date":"2017-07-08","max":{"direction":170.9,"speed":5.36},"avg":{"direction":139.32,"speed":3.13},"min":{"direction":254.02,"speed":0.32}},{"date":"2017-07-09","max":{"direction":206.81,"speed":6.3},"avg":{"direction":222.24,"speed":3.74},"min":{"direction":105.88,"speed":0.56}},{"date":"2017-07-10","max":{"direction":207.07,"speed":6.66},"avg":{"direction":222.41,"speed":4.72},"min":{"direction":280.07,"speed":2.32}},{"date":"2017-07-11","max":{"direction":179.79,"speed":5.61},"avg":{"direction":169.08,"speed":4.78},"min":{"direction":286.34,"speed":3.21}},{"date":"2017-07-12","max":{"direction":151.61,"speed":9.78},"avg":{"direction":218.76,"speed":5.91},"min":{"direction":99.86,"speed":1.82}}]},"primary":0}
     * server_time : 1499511556
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
         * hourly : {"status":"ok","description":"小雨，今天晚间21点钟后转多云，其后阴","skycon":[{"value":"RAIN","datetime":"2017-07-08 18:00"},{"value":"RAIN","datetime":"2017-07-08 19:00"},{"value":"RAIN","datetime":"2017-07-08 20:00"},{"value":"CLOUDY","datetime":"2017-07-08 21:00"},{"value":"CLOUDY","datetime":"2017-07-08 22:00"},{"value":"CLOUDY","datetime":"2017-07-08 23:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-09 00:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-09 01:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-09 02:00"},{"value":"CLOUDY","datetime":"2017-07-09 03:00"},{"value":"CLOUDY","datetime":"2017-07-09 04:00"},{"value":"CLOUDY","datetime":"2017-07-09 05:00"},{"value":"CLOUDY","datetime":"2017-07-09 06:00"},{"value":"CLOUDY","datetime":"2017-07-09 07:00"},{"value":"CLOUDY","datetime":"2017-07-09 08:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-09 09:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-09 10:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-09 11:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-09 12:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-09 13:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-09 14:00"},{"value":"CLOUDY","datetime":"2017-07-09 15:00"},{"value":"CLOUDY","datetime":"2017-07-09 16:00"},{"value":"CLOUDY","datetime":"2017-07-09 17:00"},{"value":"RAIN","datetime":"2017-07-09 18:00"},{"value":"RAIN","datetime":"2017-07-09 19:00"},{"value":"CLOUDY","datetime":"2017-07-09 20:00"},{"value":"CLOUDY","datetime":"2017-07-09 21:00"},{"value":"CLOUDY","datetime":"2017-07-09 22:00"},{"value":"CLOUDY","datetime":"2017-07-09 23:00"},{"value":"CLOUDY","datetime":"2017-07-10 00:00"},{"value":"CLOUDY","datetime":"2017-07-10 01:00"},{"value":"CLOUDY","datetime":"2017-07-10 02:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-10 03:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-10 04:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 05:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 06:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 07:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 08:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 09:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 10:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 11:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 12:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 13:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 14:00"},{"value":"RAIN","datetime":"2017-07-10 15:00"},{"value":"RAIN","datetime":"2017-07-10 16:00"},{"value":"RAIN","datetime":"2017-07-10 17:00"}],"cloudrate":[{"value":1,"datetime":"2017-07-08 18:00"},{"value":1,"datetime":"2017-07-08 19:00"},{"value":0.99,"datetime":"2017-07-08 20:00"},{"value":0.94,"datetime":"2017-07-08 21:00"},{"value":0.88,"datetime":"2017-07-08 22:00"},{"value":0.81,"datetime":"2017-07-08 23:00"},{"value":0.75,"datetime":"2017-07-09 00:00"},{"value":0.72,"datetime":"2017-07-09 01:00"},{"value":0.73,"datetime":"2017-07-09 02:00"},{"value":0.81,"datetime":"2017-07-09 03:00"},{"value":0.9,"datetime":"2017-07-09 04:00"},{"value":0.96,"datetime":"2017-07-09 05:00"},{"value":0.96,"datetime":"2017-07-09 06:00"},{"value":0.91,"datetime":"2017-07-09 07:00"},{"value":0.85,"datetime":"2017-07-09 08:00"},{"value":0.79,"datetime":"2017-07-09 09:00"},{"value":0.75,"datetime":"2017-07-09 10:00"},{"value":0.73,"datetime":"2017-07-09 11:00"},{"value":0.72,"datetime":"2017-07-09 12:00"},{"value":0.74,"datetime":"2017-07-09 13:00"},{"value":0.78,"datetime":"2017-07-09 14:00"},{"value":0.84,"datetime":"2017-07-09 15:00"},{"value":0.9,"datetime":"2017-07-09 16:00"},{"value":0.95,"datetime":"2017-07-09 17:00"},{"value":0.96,"datetime":"2017-07-09 18:00"},{"value":0.95,"datetime":"2017-07-09 19:00"},{"value":0.94,"datetime":"2017-07-09 20:00"},{"value":0.92,"datetime":"2017-07-09 21:00"},{"value":0.92,"datetime":"2017-07-09 22:00"},{"value":0.92,"datetime":"2017-07-09 23:00"},{"value":0.91,"datetime":"2017-07-10 00:00"},{"value":0.9,"datetime":"2017-07-10 01:00"},{"value":0.87,"datetime":"2017-07-10 02:00"},{"value":0.8,"datetime":"2017-07-10 03:00"},{"value":0.73,"datetime":"2017-07-10 04:00"},{"value":0.69,"datetime":"2017-07-10 05:00"},{"value":0.68,"datetime":"2017-07-10 06:00"},{"value":0.7,"datetime":"2017-07-10 07:00"},{"value":0.73,"datetime":"2017-07-10 08:00"},{"value":0.74,"datetime":"2017-07-10 09:00"},{"value":0.75,"datetime":"2017-07-10 10:00"},{"value":0.74,"datetime":"2017-07-10 11:00"},{"value":0.74,"datetime":"2017-07-10 12:00"},{"value":0.75,"datetime":"2017-07-10 13:00"},{"value":0.77,"datetime":"2017-07-10 14:00"},{"value":0.82,"datetime":"2017-07-10 15:00"},{"value":0.87,"datetime":"2017-07-10 16:00"},{"value":0.91,"datetime":"2017-07-10 17:00"}],"aqi":[{"value":12,"datetime":"2017-07-08 18:00"},{"value":11,"datetime":"2017-07-08 19:00"},{"value":11,"datetime":"2017-07-08 20:00"},{"value":12,"datetime":"2017-07-08 21:00"},{"value":14,"datetime":"2017-07-08 22:00"},{"value":14,"datetime":"2017-07-08 23:00"},{"value":15,"datetime":"2017-07-09 00:00"},{"value":15,"datetime":"2017-07-09 01:00"},{"value":15,"datetime":"2017-07-09 02:00"},{"value":15,"datetime":"2017-07-09 03:00"},{"value":16,"datetime":"2017-07-09 04:00"},{"value":16,"datetime":"2017-07-09 05:00"},{"value":16,"datetime":"2017-07-09 06:00"},{"value":18,"datetime":"2017-07-09 07:00"},{"value":19,"datetime":"2017-07-09 08:00"},{"value":20,"datetime":"2017-07-09 09:00"},{"value":20,"datetime":"2017-07-09 10:00"},{"value":20,"datetime":"2017-07-09 11:00"},{"value":20,"datetime":"2017-07-09 12:00"},{"value":22,"datetime":"2017-07-09 13:00"},{"value":22,"datetime":"2017-07-09 14:00"},{"value":22,"datetime":"2017-07-09 15:00"},{"value":22,"datetime":"2017-07-09 16:00"},{"value":20,"datetime":"2017-07-09 17:00"},{"value":20,"datetime":"2017-07-09 18:00"},{"value":20,"datetime":"2017-07-09 19:00"},{"value":20,"datetime":"2017-07-09 20:00"},{"value":20,"datetime":"2017-07-09 21:00"},{"value":20,"datetime":"2017-07-09 22:00"},{"value":19,"datetime":"2017-07-09 23:00"},{"value":19,"datetime":"2017-07-10 00:00"},{"value":19,"datetime":"2017-07-10 01:00"},{"value":19,"datetime":"2017-07-10 02:00"},{"value":20,"datetime":"2017-07-10 03:00"},{"value":20,"datetime":"2017-07-10 04:00"},{"value":22,"datetime":"2017-07-10 05:00"},{"value":22,"datetime":"2017-07-10 06:00"},{"value":23,"datetime":"2017-07-10 07:00"},{"value":23,"datetime":"2017-07-10 08:00"},{"value":24,"datetime":"2017-07-10 09:00"},{"value":24,"datetime":"2017-07-10 10:00"},{"value":26,"datetime":"2017-07-10 11:00"},{"value":27,"datetime":"2017-07-10 12:00"},{"value":27,"datetime":"2017-07-10 13:00"},{"value":29,"datetime":"2017-07-10 14:00"},{"value":29,"datetime":"2017-07-10 15:00"},{"value":29,"datetime":"2017-07-10 16:00"},{"value":29,"datetime":"2017-07-10 17:00"}],"humidity":[{"value":0.76,"datetime":"2017-07-08 18:00"},{"value":0.77,"datetime":"2017-07-08 19:00"},{"value":0.78,"datetime":"2017-07-08 20:00"},{"value":0.79,"datetime":"2017-07-08 21:00"},{"value":0.79,"datetime":"2017-07-08 22:00"},{"value":0.8,"datetime":"2017-07-08 23:00"},{"value":0.81,"datetime":"2017-07-09 00:00"},{"value":0.82,"datetime":"2017-07-09 01:00"},{"value":0.82,"datetime":"2017-07-09 02:00"},{"value":0.83,"datetime":"2017-07-09 03:00"},{"value":0.83,"datetime":"2017-07-09 04:00"},{"value":0.83,"datetime":"2017-07-09 05:00"},{"value":0.81,"datetime":"2017-07-09 06:00"},{"value":0.79,"datetime":"2017-07-09 07:00"},{"value":0.76,"datetime":"2017-07-09 08:00"},{"value":0.73,"datetime":"2017-07-09 09:00"},{"value":0.7,"datetime":"2017-07-09 10:00"},{"value":0.68,"datetime":"2017-07-09 11:00"},{"value":0.67,"datetime":"2017-07-09 12:00"},{"value":0.68,"datetime":"2017-07-09 13:00"},{"value":0.69,"datetime":"2017-07-09 14:00"},{"value":0.72,"datetime":"2017-07-09 15:00"},{"value":0.74,"datetime":"2017-07-09 16:00"},{"value":0.77,"datetime":"2017-07-09 17:00"},{"value":0.78,"datetime":"2017-07-09 18:00"},{"value":0.79,"datetime":"2017-07-09 19:00"},{"value":0.8,"datetime":"2017-07-09 20:00"},{"value":0.81,"datetime":"2017-07-09 21:00"},{"value":0.81,"datetime":"2017-07-09 22:00"},{"value":0.82,"datetime":"2017-07-09 23:00"},{"value":0.82,"datetime":"2017-07-10 00:00"},{"value":0.83,"datetime":"2017-07-10 01:00"},{"value":0.84,"datetime":"2017-07-10 02:00"},{"value":0.85,"datetime":"2017-07-10 03:00"},{"value":0.85,"datetime":"2017-07-10 04:00"},{"value":0.85,"datetime":"2017-07-10 05:00"},{"value":0.84,"datetime":"2017-07-10 06:00"},{"value":0.83,"datetime":"2017-07-10 07:00"},{"value":0.8,"datetime":"2017-07-10 08:00"},{"value":0.77,"datetime":"2017-07-10 09:00"},{"value":0.74,"datetime":"2017-07-10 10:00"},{"value":0.72,"datetime":"2017-07-10 11:00"},{"value":0.7,"datetime":"2017-07-10 12:00"},{"value":0.69,"datetime":"2017-07-10 13:00"},{"value":0.69,"datetime":"2017-07-10 14:00"},{"value":0.71,"datetime":"2017-07-10 15:00"},{"value":0.74,"datetime":"2017-07-10 16:00"},{"value":0.76,"datetime":"2017-07-10 17:00"}],"pres":[{"value":98246.7615368365,"datetime":"2017-07-08 18:00"},{"value":98248.0766940549,"datetime":"2017-07-08 19:00"},{"value":98256.0458191215,"datetime":"2017-07-08 20:00"},{"value":98281.4485588495,"datetime":"2017-07-08 21:00"},{"value":98310.7906809261,"datetime":"2017-07-08 22:00"},{"value":98324.5094832569,"datetime":"2017-07-08 23:00"},{"value":98308.7754571279,"datetime":"2017-07-09 00:00"},{"value":98272.6918673465,"datetime":"2017-07-09 01:00"},{"value":98231.0951721006,"datetime":"2017-07-09 02:00"},{"value":98196.9157645531,"datetime":"2017-07-09 03:00"},{"value":98175.4597777664,"datetime":"2017-07-09 04:00"},{"value":98170.1272797777,"datetime":"2017-07-09 05:00"},{"value":98181.7495545232,"datetime":"2017-07-09 06:00"},{"value":98200.8827495329,"datetime":"2017-07-09 07:00"},{"value":98215.5142282352,"datetime":"2017-07-09 08:00"},{"value":98216.2407452333,"datetime":"2017-07-09 09:00"},{"value":98204.0966198285,"datetime":"2017-07-09 10:00"},{"value":98182.7255624965,"datetime":"2017-07-09 11:00"},{"value":98155.0171160804,"datetime":"2017-07-09 12:00"},{"value":98120.8441528924,"datetime":"2017-07-09 13:00"},{"value":98079.325377612,"datetime":"2017-07-09 14:00"},{"value":98031.5637508994,"datetime":"2017-07-09 15:00"},{"value":97986.5992573368,"datetime":"2017-07-09 16:00"},{"value":97955.4561374871,"datetime":"2017-07-09 17:00"},{"value":97946.2865239683,"datetime":"2017-07-09 18:00"},{"value":97955.7541176185,"datetime":"2017-07-09 19:00"},{"value":97977.6505113308,"datetime":"2017-07-09 20:00"},{"value":98004.3668029205,"datetime":"2017-07-09 21:00"},{"value":98022.6921098902,"datetime":"2017-07-09 22:00"},{"value":98018.0150546651,"datetime":"2017-07-09 23:00"},{"value":97982.40183186,"datetime":"2017-07-10 00:00"},{"value":97934.6289248492,"datetime":"2017-07-10 01:00"},{"value":97900.1503891972,"datetime":"2017-07-10 02:00"},{"value":97897.9828590703,"datetime":"2017-07-10 03:00"},{"value":97921.3932830418,"datetime":"2017-07-10 04:00"},{"value":97957.2111882872,"datetime":"2017-07-10 05:00"},{"value":97995.113320888,"datetime":"2017-07-10 06:00"},{"value":98036.1653025511,"datetime":"2017-07-10 07:00"},{"value":98084.2799738894,"datetime":"2017-07-10 08:00"},{"value":98140.7011091255,"datetime":"2017-07-10 09:00"},{"value":98195.9962169206,"datetime":"2017-07-10 10:00"},{"value":98238.0637395456,"datetime":"2017-07-10 11:00"},{"value":98257.6452895099,"datetime":"2017-07-10 12:00"},{"value":98256.8551602764,"datetime":"2017-07-10 13:00"},{"value":98240.6508155466,"datetime":"2017-07-10 14:00"},{"value":98215.3321733754,"datetime":"2017-07-10 15:00"},{"value":98192.5689692304,"datetime":"2017-07-10 16:00"},{"value":98185.373392933,"datetime":"2017-07-10 17:00"}],"pm25":[{"value":6,"datetime":"2017-07-08 18:00"},{"value":6,"datetime":"2017-07-08 19:00"},{"value":6,"datetime":"2017-07-08 20:00"},{"value":7,"datetime":"2017-07-08 21:00"},{"value":8,"datetime":"2017-07-08 22:00"},{"value":8,"datetime":"2017-07-08 23:00"},{"value":9,"datetime":"2017-07-09 00:00"},{"value":9,"datetime":"2017-07-09 01:00"},{"value":9,"datetime":"2017-07-09 02:00"},{"value":9,"datetime":"2017-07-09 03:00"},{"value":10,"datetime":"2017-07-09 04:00"},{"value":10,"datetime":"2017-07-09 05:00"},{"value":10,"datetime":"2017-07-09 06:00"},{"value":11,"datetime":"2017-07-09 07:00"},{"value":12,"datetime":"2017-07-09 08:00"},{"value":13,"datetime":"2017-07-09 09:00"},{"value":13,"datetime":"2017-07-09 10:00"},{"value":13,"datetime":"2017-07-09 11:00"},{"value":13,"datetime":"2017-07-09 12:00"},{"value":14,"datetime":"2017-07-09 13:00"},{"value":14,"datetime":"2017-07-09 14:00"},{"value":14,"datetime":"2017-07-09 15:00"},{"value":14,"datetime":"2017-07-09 16:00"},{"value":13,"datetime":"2017-07-09 17:00"},{"value":13,"datetime":"2017-07-09 18:00"},{"value":13,"datetime":"2017-07-09 19:00"},{"value":13,"datetime":"2017-07-09 20:00"},{"value":13,"datetime":"2017-07-09 21:00"},{"value":13,"datetime":"2017-07-09 22:00"},{"value":12,"datetime":"2017-07-09 23:00"},{"value":12,"datetime":"2017-07-10 00:00"},{"value":12,"datetime":"2017-07-10 01:00"},{"value":12,"datetime":"2017-07-10 02:00"},{"value":13,"datetime":"2017-07-10 03:00"},{"value":13,"datetime":"2017-07-10 04:00"},{"value":14,"datetime":"2017-07-10 05:00"},{"value":14,"datetime":"2017-07-10 06:00"},{"value":15,"datetime":"2017-07-10 07:00"},{"value":15,"datetime":"2017-07-10 08:00"},{"value":16,"datetime":"2017-07-10 09:00"},{"value":16,"datetime":"2017-07-10 10:00"},{"value":17,"datetime":"2017-07-10 11:00"},{"value":18,"datetime":"2017-07-10 12:00"},{"value":18,"datetime":"2017-07-10 13:00"},{"value":19,"datetime":"2017-07-10 14:00"},{"value":19,"datetime":"2017-07-10 15:00"},{"value":19,"datetime":"2017-07-10 16:00"},{"value":19,"datetime":"2017-07-10 17:00"}],"precipitation":[{"value":0.2782,"datetime":"2017-07-08 18:00"},{"value":7.2052,"datetime":"2017-07-08 19:00"},{"value":4.6179,"datetime":"2017-07-08 20:00"},{"value":0,"datetime":"2017-07-08 21:00"},{"value":0,"datetime":"2017-07-08 22:00"},{"value":0,"datetime":"2017-07-08 23:00"},{"value":0,"datetime":"2017-07-09 00:00"},{"value":0,"datetime":"2017-07-09 01:00"},{"value":0,"datetime":"2017-07-09 02:00"},{"value":0,"datetime":"2017-07-09 03:00"},{"value":0,"datetime":"2017-07-09 04:00"},{"value":0,"datetime":"2017-07-09 05:00"},{"value":0,"datetime":"2017-07-09 06:00"},{"value":0,"datetime":"2017-07-09 07:00"},{"value":0,"datetime":"2017-07-09 08:00"},{"value":0,"datetime":"2017-07-09 09:00"},{"value":0,"datetime":"2017-07-09 10:00"},{"value":0,"datetime":"2017-07-09 11:00"},{"value":0,"datetime":"2017-07-09 12:00"},{"value":0,"datetime":"2017-07-09 13:00"},{"value":0,"datetime":"2017-07-09 14:00"},{"value":0,"datetime":"2017-07-09 15:00"},{"value":0.0468,"datetime":"2017-07-09 16:00"},{"value":0.0604,"datetime":"2017-07-09 17:00"},{"value":0.0651,"datetime":"2017-07-09 18:00"},{"value":0.0616,"datetime":"2017-07-09 19:00"},{"value":0.0523,"datetime":"2017-07-09 20:00"},{"value":0,"datetime":"2017-07-09 21:00"},{"value":0,"datetime":"2017-07-09 22:00"},{"value":0,"datetime":"2017-07-09 23:00"},{"value":0,"datetime":"2017-07-10 00:00"},{"value":0,"datetime":"2017-07-10 01:00"},{"value":0,"datetime":"2017-07-10 02:00"},{"value":0,"datetime":"2017-07-10 03:00"},{"value":0,"datetime":"2017-07-10 04:00"},{"value":0,"datetime":"2017-07-10 05:00"},{"value":0,"datetime":"2017-07-10 06:00"},{"value":0,"datetime":"2017-07-10 07:00"},{"value":0,"datetime":"2017-07-10 08:00"},{"value":0,"datetime":"2017-07-10 09:00"},{"value":0,"datetime":"2017-07-10 10:00"},{"value":0,"datetime":"2017-07-10 11:00"},{"value":0,"datetime":"2017-07-10 12:00"},{"value":0,"datetime":"2017-07-10 13:00"},{"value":0,"datetime":"2017-07-10 14:00"},{"value":0.2023,"datetime":"2017-07-10 15:00"},{"value":0.4119,"datetime":"2017-07-10 16:00"},{"value":0.5604,"datetime":"2017-07-10 17:00"}],"wind":[{"direction":104.32,"speed":4.18,"datetime":"2017-07-08 18:00"},{"direction":126.3,"speed":3.65,"datetime":"2017-07-08 19:00"},{"direction":153.06,"speed":3.34,"datetime":"2017-07-08 20:00"},{"direction":178.93,"speed":3.44,"datetime":"2017-07-08 21:00"},{"direction":198.48,"speed":3.88,"datetime":"2017-07-08 22:00"},{"direction":210.11,"speed":4.43,"datetime":"2017-07-08 23:00"},{"direction":214.73,"speed":4.9,"datetime":"2017-07-09 00:00"},{"direction":214.3,"speed":5.12,"datetime":"2017-07-09 01:00"},{"direction":209.51,"speed":5.03,"datetime":"2017-07-09 02:00"},{"direction":200.23,"speed":4.71,"datetime":"2017-07-09 03:00"},{"direction":189.88,"speed":4.44,"datetime":"2017-07-09 04:00"},{"direction":185.22,"speed":4.28,"datetime":"2017-07-09 05:00"},{"direction":191.35,"speed":4.18,"datetime":"2017-07-09 06:00"},{"direction":202.24,"speed":4.12,"datetime":"2017-07-09 07:00"},{"direction":209.61,"speed":3.84,"datetime":"2017-07-09 08:00"},{"direction":207.56,"speed":2.93,"datetime":"2017-07-09 09:00"},{"direction":192.19,"speed":1.58,"datetime":"2017-07-09 10:00"},{"direction":105.88,"speed":0.56,"datetime":"2017-07-09 11:00"},{"direction":27.13,"speed":1.7,"datetime":"2017-07-09 12:00"},{"direction":8.89,"speed":3.08,"datetime":"2017-07-09 13:00"},{"direction":358.2,"speed":3.91,"datetime":"2017-07-09 14:00"},{"direction":347.91,"speed":3.86,"datetime":"2017-07-09 15:00"},{"direction":332.8,"speed":3.25,"datetime":"2017-07-09 16:00"},{"direction":308.84,"speed":2.69,"datetime":"2017-07-09 17:00"},{"direction":280.6,"speed":2.61,"datetime":"2017-07-09 18:00"},{"direction":255.89,"speed":2.97,"datetime":"2017-07-09 19:00"},{"direction":236.16,"speed":3.62,"datetime":"2017-07-09 20:00"},{"direction":220.8,"speed":4.52,"datetime":"2017-07-09 21:00"},{"direction":211.1,"speed":5.51,"datetime":"2017-07-09 22:00"},{"direction":206.81,"speed":6.3,"datetime":"2017-07-09 23:00"},{"direction":207.07,"speed":6.66,"datetime":"2017-07-10 00:00"},{"direction":209.4,"speed":6.57,"datetime":"2017-07-10 01:00"},{"direction":211.58,"speed":6.05,"datetime":"2017-07-10 02:00"},{"direction":211.99,"speed":5.14,"datetime":"2017-07-10 03:00"},{"direction":213.16,"speed":4.19,"datetime":"2017-07-10 04:00"},{"direction":221.16,"speed":3.62,"datetime":"2017-07-10 05:00"},{"direction":237.09,"speed":3.94,"datetime":"2017-07-10 06:00"},{"direction":248.6,"speed":4.84,"datetime":"2017-07-10 07:00"},{"direction":252.95,"speed":5.48,"datetime":"2017-07-10 08:00"},{"direction":252.14,"speed":5.19,"datetime":"2017-07-10 09:00"},{"direction":250.05,"speed":4.24,"datetime":"2017-07-10 10:00"},{"direction":254.23,"speed":3.05,"datetime":"2017-07-10 11:00"},{"direction":280.07,"speed":2.32,"datetime":"2017-07-10 12:00"},{"direction":307.92,"speed":2.7,"datetime":"2017-07-10 13:00"},{"direction":310.85,"speed":3.26,"datetime":"2017-07-10 14:00"},{"direction":291.87,"speed":3.46,"datetime":"2017-07-10 15:00"},{"direction":264.03,"speed":3.93,"datetime":"2017-07-10 16:00"},{"direction":240.61,"speed":4.59,"datetime":"2017-07-10 17:00"}],"temperature":[{"value":27.8,"datetime":"2017-07-08 18:00"},{"value":27,"datetime":"2017-07-08 19:00"},{"value":26.8,"datetime":"2017-07-08 20:00"},{"value":26.6,"datetime":"2017-07-08 21:00"},{"value":26.2,"datetime":"2017-07-08 22:00"},{"value":26.1,"datetime":"2017-07-08 23:00"},{"value":26,"datetime":"2017-07-09 00:00"},{"value":26.1,"datetime":"2017-07-09 01:00"},{"value":26.7,"datetime":"2017-07-09 02:00"},{"value":27.3,"datetime":"2017-07-09 03:00"},{"value":27.9,"datetime":"2017-07-09 04:00"},{"value":28.5,"datetime":"2017-07-09 05:00"},{"value":29.1,"datetime":"2017-07-09 06:00"},{"value":29.8,"datetime":"2017-07-09 07:00"},{"value":30.4,"datetime":"2017-07-09 08:00"},{"value":31.1,"datetime":"2017-07-09 09:00"},{"value":31.8,"datetime":"2017-07-09 10:00"},{"value":32,"datetime":"2017-07-09 11:00"},{"value":31.9,"datetime":"2017-07-09 12:00"},{"value":31.8,"datetime":"2017-07-09 13:00"},{"value":31.7,"datetime":"2017-07-09 14:00"},{"value":31.2,"datetime":"2017-07-09 15:00"},{"value":31,"datetime":"2017-07-09 16:00"},{"value":30.2,"datetime":"2017-07-09 17:00"},{"value":29.9,"datetime":"2017-07-09 18:00"},{"value":29.34,"datetime":"2017-07-09 19:00"},{"value":28.61,"datetime":"2017-07-09 20:00"},{"value":27.95,"datetime":"2017-07-09 21:00"},{"value":27.61,"datetime":"2017-07-09 22:00"},{"value":27.28,"datetime":"2017-07-09 23:00"},{"value":26.98,"datetime":"2017-07-10 00:00"},{"value":26.71,"datetime":"2017-07-10 01:00"},{"value":26.47,"datetime":"2017-07-10 02:00"},{"value":26.28,"datetime":"2017-07-10 03:00"},{"value":26.6,"datetime":"2017-07-10 04:00"},{"value":26,"datetime":"2017-07-10 05:00"},{"value":27.52,"datetime":"2017-07-10 06:00"},{"value":28.06,"datetime":"2017-07-10 07:00"},{"value":28.56,"datetime":"2017-07-10 08:00"},{"value":29.03,"datetime":"2017-07-10 09:00"},{"value":29.5,"datetime":"2017-07-10 10:00"},{"value":29.98,"datetime":"2017-07-10 11:00"},{"value":30.51,"datetime":"2017-07-10 12:00"},{"value":32,"datetime":"2017-07-10 13:00"},{"value":31.4,"datetime":"2017-07-10 14:00"},{"value":31.64,"datetime":"2017-07-10 15:00"},{"value":31.34,"datetime":"2017-07-10 16:00"},{"value":30.96,"datetime":"2017-07-10 17:00"}]}
         * minutely : {"status":"ok","description":"雨渐大，35分钟后转为大雨，不过一个半小时后雨会再次变小","probability":[0.8228367567,0.9411128163,0.9276605248,0.9082266092],"datasource":"radar","precipitation_2h":[0.1508,0.1756,0.1988,0.2195,0.2368,0.25,0.2585,0.2627,0.2633,0.2609,0.2562,0.25,0.2427,0.2343,0.2247,0.2138,0.2014,0.1875,0.1721,0.1566,0.1426,0.1316,0.1252,0.125,0.1321,0.1454,0.1634,0.1844,0.2068,0.2292,0.2503,0.271,0.2925,0.3162,0.3432,0.375,0.4119,0.4513,0.4895,0.5231,0.5487,0.5625,0.5624,0.551,0.532,0.5095,0.4871,0.4688,0.4574,0.4526,0.4527,0.4564,0.4622,0.4688,0.4748,0.4802,0.4853,0.4901,0.495,0.5,0.5052,0.5097,0.5124,0.5125,0.5087,0.5,0.486,0.4684,0.4493,0.4311,0.416,0.4063,0.4033,0.4059,0.4117,0.4186,0.4244,0.4271,0.4249,0.4186,0.4093,0.3981,0.3863,0.375,0.3652,0.3572,0.351,0.3466,0.3442,0.3438,0.345,0.3463,0.3457,0.3412,0.3308,0.3125,0.2853,0.2518,0.2155,0.1799,0.1486,0.125,0.112,0.1095,0.1166,0.1326,0.1565,0.1875,0.2244,0.2639,0.3024,0.3361,0.3616,0.375,0.3741,0.362,0.3432,0.3222,0.3035,0.2917],"precipitation":[0.1508,0.1756,0.1988,0.2195,0.2368,0.25,0.2585,0.2627,0.2633,0.2609,0.2562,0.25,0.2427,0.2343,0.2247,0.2138,0.2014,0.1875,0.1721,0.1566,0.1426,0.1316,0.1252,0.125,0.1321,0.1454,0.1634,0.1844,0.2068,0.2292,0.2503,0.271,0.2925,0.3162,0.3432,0.375,0.4119,0.4513,0.4895,0.5231,0.5487,0.5625,0.5624,0.551,0.532,0.5095,0.4871,0.4688,0.4574,0.4526,0.4527,0.4564,0.4622,0.4688,0.4748,0.4802,0.4853,0.4901,0.495,0.5]}
         * daily : {"status":"ok","coldRisk":[{"index":"3","desc":"易发","datetime":"2017-07-08"},{"index":"3","desc":"易发","datetime":"2017-07-09"},{"index":"3","desc":"易发","datetime":"2017-07-10"},{"index":"3","desc":"易发","datetime":"2017-07-11"},{"index":"3","desc":"易发","datetime":"2017-07-12"}],"temperature":[{"date":"2017-07-08","max":30,"avg":26.75,"min":26.1},{"date":"2017-07-09","max":32,"avg":29.38,"min":26},{"date":"2017-07-10","max":32,"avg":28.91,"min":26},{"date":"2017-07-11","max":32,"avg":28.93,"min":26},{"date":"2017-07-12","max":32,"avg":29.08,"min":26}],"skycon":[{"date":"2017-07-08","value":"RAIN"},{"date":"2017-07-09","value":"CLOUDY"},{"date":"2017-07-10","value":"RAIN"},{"date":"2017-07-11","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-07-12","value":"PARTLY_CLOUDY_DAY"}],"cloudrate":[{"date":"2017-07-08","max":1,"avg":0.94,"min":0.69},{"date":"2017-07-09","max":0.96,"avg":0.85,"min":0.72},{"date":"2017-07-10","max":0.93,"avg":0.78,"min":0.54},{"date":"2017-07-11","max":0.93,"avg":0.65,"min":0.44},{"date":"2017-07-12","max":0.93,"avg":0.66,"min":0.4}],"aqi":[{"date":"2017-07-08","max":14,"avg":12.33,"min":0},{"date":"2017-07-09","max":22,"avg":18.83,"min":15},{"date":"2017-07-10","max":30,"avg":25.25,"min":19},{"date":"2017-07-11","max":35,"avg":31.5,"min":29},{"date":"2017-07-12","max":31,"avg":30.46,"min":29}],"humidity":[{"date":"2017-07-08","max":0.81,"avg":0.78,"min":0.68},{"date":"2017-07-09","max":0.83,"avg":0.77,"min":0.67},{"date":"2017-07-10","max":0.85,"avg":0.78,"min":0.69},{"date":"2017-07-11","max":0.86,"avg":0.78,"min":0.71},{"date":"2017-07-12","max":0.85,"avg":0.76,"min":0.66}],"astro":[{"date":"2017-07-08","sunset":{"time":"18:47"},"sunrise":{"time":"05:09"}},{"date":"2017-07-09","sunset":{"time":"18:47"},"sunrise":{"time":"05:09"}},{"date":"2017-07-10","sunset":{"time":"18:47"},"sunrise":{"time":"05:10"}},{"date":"2017-07-11","sunset":{"time":"18:46"},"sunrise":{"time":"05:10"}},{"date":"2017-07-12","sunset":{"time":"18:46"},"sunrise":{"time":"05:11"}}],"pres":[{"date":"2017-07-08","max":98355.27,"avg":98277.94,"min":98167.86},{"date":"2017-07-09","max":98308.78,"avg":98117.08,"min":97946.29},{"date":"2017-07-10","max":98366.92,"avg":98142.03,"min":97897.98},{"date":"2017-07-11","max":98498.7,"avg":98387.6,"min":98268.74},{"date":"2017-07-12","max":98598.62,"avg":98421.92,"min":98258.59}],"ultraviolet":[{"index":"1","desc":"最弱","datetime":"2017-07-08"},{"index":"2","desc":"弱","datetime":"2017-07-09"},{"index":"2","desc":"弱","datetime":"2017-07-10"},{"index":"2","desc":"弱","datetime":"2017-07-11"},{"index":"1","desc":"最弱","datetime":"2017-07-12"}],"pm25":[{"date":"2017-07-08","max":8,"avg":6.83,"min":1},{"date":"2017-07-09","max":14,"avg":11.96,"min":9},{"date":"2017-07-10","max":20,"avg":16.5,"min":12},{"date":"2017-07-11","max":24,"avg":21.17,"min":19},{"date":"2017-07-12","max":21,"avg":20.46,"min":19}],"dressing":[{"index":"2","desc":"很热","datetime":"2017-07-08"},{"index":"2","desc":"很热","datetime":"2017-07-09"},{"index":"2","desc":"很热","datetime":"2017-07-10"},{"index":"2","desc":"很热","datetime":"2017-07-11"},{"index":"2","desc":"很热","datetime":"2017-07-12"}],"carWashing":[{"index":"3","desc":"较不适宜","datetime":"2017-07-08"},{"index":"3","desc":"较不适宜","datetime":"2017-07-09"},{"index":"3","desc":"较不适宜","datetime":"2017-07-10"},{"index":"3","desc":"较不适宜","datetime":"2017-07-11"},{"index":"1","desc":"适宜","datetime":"2017-07-12"}],"precipitation":[{"date":"2017-07-08","max":7.2052,"avg":2.0169,"min":0},{"date":"2017-07-09","max":0.0651,"avg":0.0119,"min":0},{"date":"2017-07-10","max":0.5746,"avg":0.1188,"min":0},{"date":"2017-07-11","max":0.0739,"avg":0.0256,"min":0},{"date":"2017-07-12","max":0,"avg":0,"min":0}],"wind":[{"date":"2017-07-08","max":{"direction":170.9,"speed":5.36},"avg":{"direction":139.32,"speed":3.13},"min":{"direction":254.02,"speed":0.32}},{"date":"2017-07-09","max":{"direction":206.81,"speed":6.3},"avg":{"direction":222.24,"speed":3.74},"min":{"direction":105.88,"speed":0.56}},{"date":"2017-07-10","max":{"direction":207.07,"speed":6.66},"avg":{"direction":222.41,"speed":4.72},"min":{"direction":280.07,"speed":2.32}},{"date":"2017-07-11","max":{"direction":179.79,"speed":5.61},"avg":{"direction":169.08,"speed":4.78},"min":{"direction":286.34,"speed":3.21}},{"date":"2017-07-12","max":{"direction":151.61,"speed":9.78},"avg":{"direction":218.76,"speed":5.91},"min":{"direction":99.86,"speed":1.82}}]}
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
             * description : 小雨，今天晚间21点钟后转多云，其后阴
             * skycon : [{"value":"RAIN","datetime":"2017-07-08 18:00"},{"value":"RAIN","datetime":"2017-07-08 19:00"},{"value":"RAIN","datetime":"2017-07-08 20:00"},{"value":"CLOUDY","datetime":"2017-07-08 21:00"},{"value":"CLOUDY","datetime":"2017-07-08 22:00"},{"value":"CLOUDY","datetime":"2017-07-08 23:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-09 00:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-09 01:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-09 02:00"},{"value":"CLOUDY","datetime":"2017-07-09 03:00"},{"value":"CLOUDY","datetime":"2017-07-09 04:00"},{"value":"CLOUDY","datetime":"2017-07-09 05:00"},{"value":"CLOUDY","datetime":"2017-07-09 06:00"},{"value":"CLOUDY","datetime":"2017-07-09 07:00"},{"value":"CLOUDY","datetime":"2017-07-09 08:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-09 09:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-09 10:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-09 11:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-09 12:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-09 13:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-09 14:00"},{"value":"CLOUDY","datetime":"2017-07-09 15:00"},{"value":"CLOUDY","datetime":"2017-07-09 16:00"},{"value":"CLOUDY","datetime":"2017-07-09 17:00"},{"value":"RAIN","datetime":"2017-07-09 18:00"},{"value":"RAIN","datetime":"2017-07-09 19:00"},{"value":"CLOUDY","datetime":"2017-07-09 20:00"},{"value":"CLOUDY","datetime":"2017-07-09 21:00"},{"value":"CLOUDY","datetime":"2017-07-09 22:00"},{"value":"CLOUDY","datetime":"2017-07-09 23:00"},{"value":"CLOUDY","datetime":"2017-07-10 00:00"},{"value":"CLOUDY","datetime":"2017-07-10 01:00"},{"value":"CLOUDY","datetime":"2017-07-10 02:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-10 03:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-07-10 04:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 05:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 06:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 07:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 08:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 09:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 10:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 11:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 12:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 13:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-07-10 14:00"},{"value":"RAIN","datetime":"2017-07-10 15:00"},{"value":"RAIN","datetime":"2017-07-10 16:00"},{"value":"RAIN","datetime":"2017-07-10 17:00"}]
             * cloudrate : [{"value":1,"datetime":"2017-07-08 18:00"},{"value":1,"datetime":"2017-07-08 19:00"},{"value":0.99,"datetime":"2017-07-08 20:00"},{"value":0.94,"datetime":"2017-07-08 21:00"},{"value":0.88,"datetime":"2017-07-08 22:00"},{"value":0.81,"datetime":"2017-07-08 23:00"},{"value":0.75,"datetime":"2017-07-09 00:00"},{"value":0.72,"datetime":"2017-07-09 01:00"},{"value":0.73,"datetime":"2017-07-09 02:00"},{"value":0.81,"datetime":"2017-07-09 03:00"},{"value":0.9,"datetime":"2017-07-09 04:00"},{"value":0.96,"datetime":"2017-07-09 05:00"},{"value":0.96,"datetime":"2017-07-09 06:00"},{"value":0.91,"datetime":"2017-07-09 07:00"},{"value":0.85,"datetime":"2017-07-09 08:00"},{"value":0.79,"datetime":"2017-07-09 09:00"},{"value":0.75,"datetime":"2017-07-09 10:00"},{"value":0.73,"datetime":"2017-07-09 11:00"},{"value":0.72,"datetime":"2017-07-09 12:00"},{"value":0.74,"datetime":"2017-07-09 13:00"},{"value":0.78,"datetime":"2017-07-09 14:00"},{"value":0.84,"datetime":"2017-07-09 15:00"},{"value":0.9,"datetime":"2017-07-09 16:00"},{"value":0.95,"datetime":"2017-07-09 17:00"},{"value":0.96,"datetime":"2017-07-09 18:00"},{"value":0.95,"datetime":"2017-07-09 19:00"},{"value":0.94,"datetime":"2017-07-09 20:00"},{"value":0.92,"datetime":"2017-07-09 21:00"},{"value":0.92,"datetime":"2017-07-09 22:00"},{"value":0.92,"datetime":"2017-07-09 23:00"},{"value":0.91,"datetime":"2017-07-10 00:00"},{"value":0.9,"datetime":"2017-07-10 01:00"},{"value":0.87,"datetime":"2017-07-10 02:00"},{"value":0.8,"datetime":"2017-07-10 03:00"},{"value":0.73,"datetime":"2017-07-10 04:00"},{"value":0.69,"datetime":"2017-07-10 05:00"},{"value":0.68,"datetime":"2017-07-10 06:00"},{"value":0.7,"datetime":"2017-07-10 07:00"},{"value":0.73,"datetime":"2017-07-10 08:00"},{"value":0.74,"datetime":"2017-07-10 09:00"},{"value":0.75,"datetime":"2017-07-10 10:00"},{"value":0.74,"datetime":"2017-07-10 11:00"},{"value":0.74,"datetime":"2017-07-10 12:00"},{"value":0.75,"datetime":"2017-07-10 13:00"},{"value":0.77,"datetime":"2017-07-10 14:00"},{"value":0.82,"datetime":"2017-07-10 15:00"},{"value":0.87,"datetime":"2017-07-10 16:00"},{"value":0.91,"datetime":"2017-07-10 17:00"}]
             * aqi : [{"value":12,"datetime":"2017-07-08 18:00"},{"value":11,"datetime":"2017-07-08 19:00"},{"value":11,"datetime":"2017-07-08 20:00"},{"value":12,"datetime":"2017-07-08 21:00"},{"value":14,"datetime":"2017-07-08 22:00"},{"value":14,"datetime":"2017-07-08 23:00"},{"value":15,"datetime":"2017-07-09 00:00"},{"value":15,"datetime":"2017-07-09 01:00"},{"value":15,"datetime":"2017-07-09 02:00"},{"value":15,"datetime":"2017-07-09 03:00"},{"value":16,"datetime":"2017-07-09 04:00"},{"value":16,"datetime":"2017-07-09 05:00"},{"value":16,"datetime":"2017-07-09 06:00"},{"value":18,"datetime":"2017-07-09 07:00"},{"value":19,"datetime":"2017-07-09 08:00"},{"value":20,"datetime":"2017-07-09 09:00"},{"value":20,"datetime":"2017-07-09 10:00"},{"value":20,"datetime":"2017-07-09 11:00"},{"value":20,"datetime":"2017-07-09 12:00"},{"value":22,"datetime":"2017-07-09 13:00"},{"value":22,"datetime":"2017-07-09 14:00"},{"value":22,"datetime":"2017-07-09 15:00"},{"value":22,"datetime":"2017-07-09 16:00"},{"value":20,"datetime":"2017-07-09 17:00"},{"value":20,"datetime":"2017-07-09 18:00"},{"value":20,"datetime":"2017-07-09 19:00"},{"value":20,"datetime":"2017-07-09 20:00"},{"value":20,"datetime":"2017-07-09 21:00"},{"value":20,"datetime":"2017-07-09 22:00"},{"value":19,"datetime":"2017-07-09 23:00"},{"value":19,"datetime":"2017-07-10 00:00"},{"value":19,"datetime":"2017-07-10 01:00"},{"value":19,"datetime":"2017-07-10 02:00"},{"value":20,"datetime":"2017-07-10 03:00"},{"value":20,"datetime":"2017-07-10 04:00"},{"value":22,"datetime":"2017-07-10 05:00"},{"value":22,"datetime":"2017-07-10 06:00"},{"value":23,"datetime":"2017-07-10 07:00"},{"value":23,"datetime":"2017-07-10 08:00"},{"value":24,"datetime":"2017-07-10 09:00"},{"value":24,"datetime":"2017-07-10 10:00"},{"value":26,"datetime":"2017-07-10 11:00"},{"value":27,"datetime":"2017-07-10 12:00"},{"value":27,"datetime":"2017-07-10 13:00"},{"value":29,"datetime":"2017-07-10 14:00"},{"value":29,"datetime":"2017-07-10 15:00"},{"value":29,"datetime":"2017-07-10 16:00"},{"value":29,"datetime":"2017-07-10 17:00"}]
             * humidity : [{"value":0.76,"datetime":"2017-07-08 18:00"},{"value":0.77,"datetime":"2017-07-08 19:00"},{"value":0.78,"datetime":"2017-07-08 20:00"},{"value":0.79,"datetime":"2017-07-08 21:00"},{"value":0.79,"datetime":"2017-07-08 22:00"},{"value":0.8,"datetime":"2017-07-08 23:00"},{"value":0.81,"datetime":"2017-07-09 00:00"},{"value":0.82,"datetime":"2017-07-09 01:00"},{"value":0.82,"datetime":"2017-07-09 02:00"},{"value":0.83,"datetime":"2017-07-09 03:00"},{"value":0.83,"datetime":"2017-07-09 04:00"},{"value":0.83,"datetime":"2017-07-09 05:00"},{"value":0.81,"datetime":"2017-07-09 06:00"},{"value":0.79,"datetime":"2017-07-09 07:00"},{"value":0.76,"datetime":"2017-07-09 08:00"},{"value":0.73,"datetime":"2017-07-09 09:00"},{"value":0.7,"datetime":"2017-07-09 10:00"},{"value":0.68,"datetime":"2017-07-09 11:00"},{"value":0.67,"datetime":"2017-07-09 12:00"},{"value":0.68,"datetime":"2017-07-09 13:00"},{"value":0.69,"datetime":"2017-07-09 14:00"},{"value":0.72,"datetime":"2017-07-09 15:00"},{"value":0.74,"datetime":"2017-07-09 16:00"},{"value":0.77,"datetime":"2017-07-09 17:00"},{"value":0.78,"datetime":"2017-07-09 18:00"},{"value":0.79,"datetime":"2017-07-09 19:00"},{"value":0.8,"datetime":"2017-07-09 20:00"},{"value":0.81,"datetime":"2017-07-09 21:00"},{"value":0.81,"datetime":"2017-07-09 22:00"},{"value":0.82,"datetime":"2017-07-09 23:00"},{"value":0.82,"datetime":"2017-07-10 00:00"},{"value":0.83,"datetime":"2017-07-10 01:00"},{"value":0.84,"datetime":"2017-07-10 02:00"},{"value":0.85,"datetime":"2017-07-10 03:00"},{"value":0.85,"datetime":"2017-07-10 04:00"},{"value":0.85,"datetime":"2017-07-10 05:00"},{"value":0.84,"datetime":"2017-07-10 06:00"},{"value":0.83,"datetime":"2017-07-10 07:00"},{"value":0.8,"datetime":"2017-07-10 08:00"},{"value":0.77,"datetime":"2017-07-10 09:00"},{"value":0.74,"datetime":"2017-07-10 10:00"},{"value":0.72,"datetime":"2017-07-10 11:00"},{"value":0.7,"datetime":"2017-07-10 12:00"},{"value":0.69,"datetime":"2017-07-10 13:00"},{"value":0.69,"datetime":"2017-07-10 14:00"},{"value":0.71,"datetime":"2017-07-10 15:00"},{"value":0.74,"datetime":"2017-07-10 16:00"},{"value":0.76,"datetime":"2017-07-10 17:00"}]
             * pres : [{"value":98246.7615368365,"datetime":"2017-07-08 18:00"},{"value":98248.0766940549,"datetime":"2017-07-08 19:00"},{"value":98256.0458191215,"datetime":"2017-07-08 20:00"},{"value":98281.4485588495,"datetime":"2017-07-08 21:00"},{"value":98310.7906809261,"datetime":"2017-07-08 22:00"},{"value":98324.5094832569,"datetime":"2017-07-08 23:00"},{"value":98308.7754571279,"datetime":"2017-07-09 00:00"},{"value":98272.6918673465,"datetime":"2017-07-09 01:00"},{"value":98231.0951721006,"datetime":"2017-07-09 02:00"},{"value":98196.9157645531,"datetime":"2017-07-09 03:00"},{"value":98175.4597777664,"datetime":"2017-07-09 04:00"},{"value":98170.1272797777,"datetime":"2017-07-09 05:00"},{"value":98181.7495545232,"datetime":"2017-07-09 06:00"},{"value":98200.8827495329,"datetime":"2017-07-09 07:00"},{"value":98215.5142282352,"datetime":"2017-07-09 08:00"},{"value":98216.2407452333,"datetime":"2017-07-09 09:00"},{"value":98204.0966198285,"datetime":"2017-07-09 10:00"},{"value":98182.7255624965,"datetime":"2017-07-09 11:00"},{"value":98155.0171160804,"datetime":"2017-07-09 12:00"},{"value":98120.8441528924,"datetime":"2017-07-09 13:00"},{"value":98079.325377612,"datetime":"2017-07-09 14:00"},{"value":98031.5637508994,"datetime":"2017-07-09 15:00"},{"value":97986.5992573368,"datetime":"2017-07-09 16:00"},{"value":97955.4561374871,"datetime":"2017-07-09 17:00"},{"value":97946.2865239683,"datetime":"2017-07-09 18:00"},{"value":97955.7541176185,"datetime":"2017-07-09 19:00"},{"value":97977.6505113308,"datetime":"2017-07-09 20:00"},{"value":98004.3668029205,"datetime":"2017-07-09 21:00"},{"value":98022.6921098902,"datetime":"2017-07-09 22:00"},{"value":98018.0150546651,"datetime":"2017-07-09 23:00"},{"value":97982.40183186,"datetime":"2017-07-10 00:00"},{"value":97934.6289248492,"datetime":"2017-07-10 01:00"},{"value":97900.1503891972,"datetime":"2017-07-10 02:00"},{"value":97897.9828590703,"datetime":"2017-07-10 03:00"},{"value":97921.3932830418,"datetime":"2017-07-10 04:00"},{"value":97957.2111882872,"datetime":"2017-07-10 05:00"},{"value":97995.113320888,"datetime":"2017-07-10 06:00"},{"value":98036.1653025511,"datetime":"2017-07-10 07:00"},{"value":98084.2799738894,"datetime":"2017-07-10 08:00"},{"value":98140.7011091255,"datetime":"2017-07-10 09:00"},{"value":98195.9962169206,"datetime":"2017-07-10 10:00"},{"value":98238.0637395456,"datetime":"2017-07-10 11:00"},{"value":98257.6452895099,"datetime":"2017-07-10 12:00"},{"value":98256.8551602764,"datetime":"2017-07-10 13:00"},{"value":98240.6508155466,"datetime":"2017-07-10 14:00"},{"value":98215.3321733754,"datetime":"2017-07-10 15:00"},{"value":98192.5689692304,"datetime":"2017-07-10 16:00"},{"value":98185.373392933,"datetime":"2017-07-10 17:00"}]
             * pm25 : [{"value":6,"datetime":"2017-07-08 18:00"},{"value":6,"datetime":"2017-07-08 19:00"},{"value":6,"datetime":"2017-07-08 20:00"},{"value":7,"datetime":"2017-07-08 21:00"},{"value":8,"datetime":"2017-07-08 22:00"},{"value":8,"datetime":"2017-07-08 23:00"},{"value":9,"datetime":"2017-07-09 00:00"},{"value":9,"datetime":"2017-07-09 01:00"},{"value":9,"datetime":"2017-07-09 02:00"},{"value":9,"datetime":"2017-07-09 03:00"},{"value":10,"datetime":"2017-07-09 04:00"},{"value":10,"datetime":"2017-07-09 05:00"},{"value":10,"datetime":"2017-07-09 06:00"},{"value":11,"datetime":"2017-07-09 07:00"},{"value":12,"datetime":"2017-07-09 08:00"},{"value":13,"datetime":"2017-07-09 09:00"},{"value":13,"datetime":"2017-07-09 10:00"},{"value":13,"datetime":"2017-07-09 11:00"},{"value":13,"datetime":"2017-07-09 12:00"},{"value":14,"datetime":"2017-07-09 13:00"},{"value":14,"datetime":"2017-07-09 14:00"},{"value":14,"datetime":"2017-07-09 15:00"},{"value":14,"datetime":"2017-07-09 16:00"},{"value":13,"datetime":"2017-07-09 17:00"},{"value":13,"datetime":"2017-07-09 18:00"},{"value":13,"datetime":"2017-07-09 19:00"},{"value":13,"datetime":"2017-07-09 20:00"},{"value":13,"datetime":"2017-07-09 21:00"},{"value":13,"datetime":"2017-07-09 22:00"},{"value":12,"datetime":"2017-07-09 23:00"},{"value":12,"datetime":"2017-07-10 00:00"},{"value":12,"datetime":"2017-07-10 01:00"},{"value":12,"datetime":"2017-07-10 02:00"},{"value":13,"datetime":"2017-07-10 03:00"},{"value":13,"datetime":"2017-07-10 04:00"},{"value":14,"datetime":"2017-07-10 05:00"},{"value":14,"datetime":"2017-07-10 06:00"},{"value":15,"datetime":"2017-07-10 07:00"},{"value":15,"datetime":"2017-07-10 08:00"},{"value":16,"datetime":"2017-07-10 09:00"},{"value":16,"datetime":"2017-07-10 10:00"},{"value":17,"datetime":"2017-07-10 11:00"},{"value":18,"datetime":"2017-07-10 12:00"},{"value":18,"datetime":"2017-07-10 13:00"},{"value":19,"datetime":"2017-07-10 14:00"},{"value":19,"datetime":"2017-07-10 15:00"},{"value":19,"datetime":"2017-07-10 16:00"},{"value":19,"datetime":"2017-07-10 17:00"}]
             * precipitation : [{"value":0.2782,"datetime":"2017-07-08 18:00"},{"value":7.2052,"datetime":"2017-07-08 19:00"},{"value":4.6179,"datetime":"2017-07-08 20:00"},{"value":0,"datetime":"2017-07-08 21:00"},{"value":0,"datetime":"2017-07-08 22:00"},{"value":0,"datetime":"2017-07-08 23:00"},{"value":0,"datetime":"2017-07-09 00:00"},{"value":0,"datetime":"2017-07-09 01:00"},{"value":0,"datetime":"2017-07-09 02:00"},{"value":0,"datetime":"2017-07-09 03:00"},{"value":0,"datetime":"2017-07-09 04:00"},{"value":0,"datetime":"2017-07-09 05:00"},{"value":0,"datetime":"2017-07-09 06:00"},{"value":0,"datetime":"2017-07-09 07:00"},{"value":0,"datetime":"2017-07-09 08:00"},{"value":0,"datetime":"2017-07-09 09:00"},{"value":0,"datetime":"2017-07-09 10:00"},{"value":0,"datetime":"2017-07-09 11:00"},{"value":0,"datetime":"2017-07-09 12:00"},{"value":0,"datetime":"2017-07-09 13:00"},{"value":0,"datetime":"2017-07-09 14:00"},{"value":0,"datetime":"2017-07-09 15:00"},{"value":0.0468,"datetime":"2017-07-09 16:00"},{"value":0.0604,"datetime":"2017-07-09 17:00"},{"value":0.0651,"datetime":"2017-07-09 18:00"},{"value":0.0616,"datetime":"2017-07-09 19:00"},{"value":0.0523,"datetime":"2017-07-09 20:00"},{"value":0,"datetime":"2017-07-09 21:00"},{"value":0,"datetime":"2017-07-09 22:00"},{"value":0,"datetime":"2017-07-09 23:00"},{"value":0,"datetime":"2017-07-10 00:00"},{"value":0,"datetime":"2017-07-10 01:00"},{"value":0,"datetime":"2017-07-10 02:00"},{"value":0,"datetime":"2017-07-10 03:00"},{"value":0,"datetime":"2017-07-10 04:00"},{"value":0,"datetime":"2017-07-10 05:00"},{"value":0,"datetime":"2017-07-10 06:00"},{"value":0,"datetime":"2017-07-10 07:00"},{"value":0,"datetime":"2017-07-10 08:00"},{"value":0,"datetime":"2017-07-10 09:00"},{"value":0,"datetime":"2017-07-10 10:00"},{"value":0,"datetime":"2017-07-10 11:00"},{"value":0,"datetime":"2017-07-10 12:00"},{"value":0,"datetime":"2017-07-10 13:00"},{"value":0,"datetime":"2017-07-10 14:00"},{"value":0.2023,"datetime":"2017-07-10 15:00"},{"value":0.4119,"datetime":"2017-07-10 16:00"},{"value":0.5604,"datetime":"2017-07-10 17:00"}]
             * wind : [{"direction":104.32,"speed":4.18,"datetime":"2017-07-08 18:00"},{"direction":126.3,"speed":3.65,"datetime":"2017-07-08 19:00"},{"direction":153.06,"speed":3.34,"datetime":"2017-07-08 20:00"},{"direction":178.93,"speed":3.44,"datetime":"2017-07-08 21:00"},{"direction":198.48,"speed":3.88,"datetime":"2017-07-08 22:00"},{"direction":210.11,"speed":4.43,"datetime":"2017-07-08 23:00"},{"direction":214.73,"speed":4.9,"datetime":"2017-07-09 00:00"},{"direction":214.3,"speed":5.12,"datetime":"2017-07-09 01:00"},{"direction":209.51,"speed":5.03,"datetime":"2017-07-09 02:00"},{"direction":200.23,"speed":4.71,"datetime":"2017-07-09 03:00"},{"direction":189.88,"speed":4.44,"datetime":"2017-07-09 04:00"},{"direction":185.22,"speed":4.28,"datetime":"2017-07-09 05:00"},{"direction":191.35,"speed":4.18,"datetime":"2017-07-09 06:00"},{"direction":202.24,"speed":4.12,"datetime":"2017-07-09 07:00"},{"direction":209.61,"speed":3.84,"datetime":"2017-07-09 08:00"},{"direction":207.56,"speed":2.93,"datetime":"2017-07-09 09:00"},{"direction":192.19,"speed":1.58,"datetime":"2017-07-09 10:00"},{"direction":105.88,"speed":0.56,"datetime":"2017-07-09 11:00"},{"direction":27.13,"speed":1.7,"datetime":"2017-07-09 12:00"},{"direction":8.89,"speed":3.08,"datetime":"2017-07-09 13:00"},{"direction":358.2,"speed":3.91,"datetime":"2017-07-09 14:00"},{"direction":347.91,"speed":3.86,"datetime":"2017-07-09 15:00"},{"direction":332.8,"speed":3.25,"datetime":"2017-07-09 16:00"},{"direction":308.84,"speed":2.69,"datetime":"2017-07-09 17:00"},{"direction":280.6,"speed":2.61,"datetime":"2017-07-09 18:00"},{"direction":255.89,"speed":2.97,"datetime":"2017-07-09 19:00"},{"direction":236.16,"speed":3.62,"datetime":"2017-07-09 20:00"},{"direction":220.8,"speed":4.52,"datetime":"2017-07-09 21:00"},{"direction":211.1,"speed":5.51,"datetime":"2017-07-09 22:00"},{"direction":206.81,"speed":6.3,"datetime":"2017-07-09 23:00"},{"direction":207.07,"speed":6.66,"datetime":"2017-07-10 00:00"},{"direction":209.4,"speed":6.57,"datetime":"2017-07-10 01:00"},{"direction":211.58,"speed":6.05,"datetime":"2017-07-10 02:00"},{"direction":211.99,"speed":5.14,"datetime":"2017-07-10 03:00"},{"direction":213.16,"speed":4.19,"datetime":"2017-07-10 04:00"},{"direction":221.16,"speed":3.62,"datetime":"2017-07-10 05:00"},{"direction":237.09,"speed":3.94,"datetime":"2017-07-10 06:00"},{"direction":248.6,"speed":4.84,"datetime":"2017-07-10 07:00"},{"direction":252.95,"speed":5.48,"datetime":"2017-07-10 08:00"},{"direction":252.14,"speed":5.19,"datetime":"2017-07-10 09:00"},{"direction":250.05,"speed":4.24,"datetime":"2017-07-10 10:00"},{"direction":254.23,"speed":3.05,"datetime":"2017-07-10 11:00"},{"direction":280.07,"speed":2.32,"datetime":"2017-07-10 12:00"},{"direction":307.92,"speed":2.7,"datetime":"2017-07-10 13:00"},{"direction":310.85,"speed":3.26,"datetime":"2017-07-10 14:00"},{"direction":291.87,"speed":3.46,"datetime":"2017-07-10 15:00"},{"direction":264.03,"speed":3.93,"datetime":"2017-07-10 16:00"},{"direction":240.61,"speed":4.59,"datetime":"2017-07-10 17:00"}]
             * temperature : [{"value":27.8,"datetime":"2017-07-08 18:00"},{"value":27,"datetime":"2017-07-08 19:00"},{"value":26.8,"datetime":"2017-07-08 20:00"},{"value":26.6,"datetime":"2017-07-08 21:00"},{"value":26.2,"datetime":"2017-07-08 22:00"},{"value":26.1,"datetime":"2017-07-08 23:00"},{"value":26,"datetime":"2017-07-09 00:00"},{"value":26.1,"datetime":"2017-07-09 01:00"},{"value":26.7,"datetime":"2017-07-09 02:00"},{"value":27.3,"datetime":"2017-07-09 03:00"},{"value":27.9,"datetime":"2017-07-09 04:00"},{"value":28.5,"datetime":"2017-07-09 05:00"},{"value":29.1,"datetime":"2017-07-09 06:00"},{"value":29.8,"datetime":"2017-07-09 07:00"},{"value":30.4,"datetime":"2017-07-09 08:00"},{"value":31.1,"datetime":"2017-07-09 09:00"},{"value":31.8,"datetime":"2017-07-09 10:00"},{"value":32,"datetime":"2017-07-09 11:00"},{"value":31.9,"datetime":"2017-07-09 12:00"},{"value":31.8,"datetime":"2017-07-09 13:00"},{"value":31.7,"datetime":"2017-07-09 14:00"},{"value":31.2,"datetime":"2017-07-09 15:00"},{"value":31,"datetime":"2017-07-09 16:00"},{"value":30.2,"datetime":"2017-07-09 17:00"},{"value":29.9,"datetime":"2017-07-09 18:00"},{"value":29.34,"datetime":"2017-07-09 19:00"},{"value":28.61,"datetime":"2017-07-09 20:00"},{"value":27.95,"datetime":"2017-07-09 21:00"},{"value":27.61,"datetime":"2017-07-09 22:00"},{"value":27.28,"datetime":"2017-07-09 23:00"},{"value":26.98,"datetime":"2017-07-10 00:00"},{"value":26.71,"datetime":"2017-07-10 01:00"},{"value":26.47,"datetime":"2017-07-10 02:00"},{"value":26.28,"datetime":"2017-07-10 03:00"},{"value":26.6,"datetime":"2017-07-10 04:00"},{"value":26,"datetime":"2017-07-10 05:00"},{"value":27.52,"datetime":"2017-07-10 06:00"},{"value":28.06,"datetime":"2017-07-10 07:00"},{"value":28.56,"datetime":"2017-07-10 08:00"},{"value":29.03,"datetime":"2017-07-10 09:00"},{"value":29.5,"datetime":"2017-07-10 10:00"},{"value":29.98,"datetime":"2017-07-10 11:00"},{"value":30.51,"datetime":"2017-07-10 12:00"},{"value":32,"datetime":"2017-07-10 13:00"},{"value":31.4,"datetime":"2017-07-10 14:00"},{"value":31.64,"datetime":"2017-07-10 15:00"},{"value":31.34,"datetime":"2017-07-10 16:00"},{"value":30.96,"datetime":"2017-07-10 17:00"}]
             */

            private String status;
            private String description;
            private List<SkyconBean> skycon;
            private List<CloudrateBean> cloudrate;
            private List<AqiBean> aqi;
            private List<HumidityBean> humidity;
            private List<PresBean> pres;
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

            public List<PresBean> getPres() {
                return pres;
            }

            public void setPres(List<PresBean> pres) {
                this.pres = pres;
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
                 * value : RAIN
                 * datetime : 2017-07-08 18:00
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
                 * value : 1
                 * datetime : 2017-07-08 18:00
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

            public static class AqiBean {
                /**
                 * value : 12
                 * datetime : 2017-07-08 18:00
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
                 * value : 0.76
                 * datetime : 2017-07-08 18:00
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

            public static class PresBean {
                /**
                 * value : 98246.7615368365
                 * datetime : 2017-07-08 18:00
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
                 * value : 6
                 * datetime : 2017-07-08 18:00
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
                 * value : 0.2782
                 * datetime : 2017-07-08 18:00
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
                 * direction : 104.32
                 * speed : 4.18
                 * datetime : 2017-07-08 18:00
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
                 * value : 27.8
                 * datetime : 2017-07-08 18:00
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
             * description : 雨渐大，35分钟后转为大雨，不过一个半小时后雨会再次变小
             * probability : [0.8228367567,0.9411128163,0.9276605248,0.9082266092]
             * datasource : radar
             * precipitation_2h : [0.1508,0.1756,0.1988,0.2195,0.2368,0.25,0.2585,0.2627,0.2633,0.2609,0.2562,0.25,0.2427,0.2343,0.2247,0.2138,0.2014,0.1875,0.1721,0.1566,0.1426,0.1316,0.1252,0.125,0.1321,0.1454,0.1634,0.1844,0.2068,0.2292,0.2503,0.271,0.2925,0.3162,0.3432,0.375,0.4119,0.4513,0.4895,0.5231,0.5487,0.5625,0.5624,0.551,0.532,0.5095,0.4871,0.4688,0.4574,0.4526,0.4527,0.4564,0.4622,0.4688,0.4748,0.4802,0.4853,0.4901,0.495,0.5,0.5052,0.5097,0.5124,0.5125,0.5087,0.5,0.486,0.4684,0.4493,0.4311,0.416,0.4063,0.4033,0.4059,0.4117,0.4186,0.4244,0.4271,0.4249,0.4186,0.4093,0.3981,0.3863,0.375,0.3652,0.3572,0.351,0.3466,0.3442,0.3438,0.345,0.3463,0.3457,0.3412,0.3308,0.3125,0.2853,0.2518,0.2155,0.1799,0.1486,0.125,0.112,0.1095,0.1166,0.1326,0.1565,0.1875,0.2244,0.2639,0.3024,0.3361,0.3616,0.375,0.3741,0.362,0.3432,0.3222,0.3035,0.2917]
             * precipitation : [0.1508,0.1756,0.1988,0.2195,0.2368,0.25,0.2585,0.2627,0.2633,0.2609,0.2562,0.25,0.2427,0.2343,0.2247,0.2138,0.2014,0.1875,0.1721,0.1566,0.1426,0.1316,0.1252,0.125,0.1321,0.1454,0.1634,0.1844,0.2068,0.2292,0.2503,0.271,0.2925,0.3162,0.3432,0.375,0.4119,0.4513,0.4895,0.5231,0.5487,0.5625,0.5624,0.551,0.532,0.5095,0.4871,0.4688,0.4574,0.4526,0.4527,0.4564,0.4622,0.4688,0.4748,0.4802,0.4853,0.4901,0.495,0.5]
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
             * coldRisk : [{"index":"3","desc":"易发","datetime":"2017-07-08"},{"index":"3","desc":"易发","datetime":"2017-07-09"},{"index":"3","desc":"易发","datetime":"2017-07-10"},{"index":"3","desc":"易发","datetime":"2017-07-11"},{"index":"3","desc":"易发","datetime":"2017-07-12"}]
             * temperature : [{"date":"2017-07-08","max":30,"avg":26.75,"min":26.1},{"date":"2017-07-09","max":32,"avg":29.38,"min":26},{"date":"2017-07-10","max":32,"avg":28.91,"min":26},{"date":"2017-07-11","max":32,"avg":28.93,"min":26},{"date":"2017-07-12","max":32,"avg":29.08,"min":26}]
             * skycon : [{"date":"2017-07-08","value":"RAIN"},{"date":"2017-07-09","value":"CLOUDY"},{"date":"2017-07-10","value":"RAIN"},{"date":"2017-07-11","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-07-12","value":"PARTLY_CLOUDY_DAY"}]
             * cloudrate : [{"date":"2017-07-08","max":1,"avg":0.94,"min":0.69},{"date":"2017-07-09","max":0.96,"avg":0.85,"min":0.72},{"date":"2017-07-10","max":0.93,"avg":0.78,"min":0.54},{"date":"2017-07-11","max":0.93,"avg":0.65,"min":0.44},{"date":"2017-07-12","max":0.93,"avg":0.66,"min":0.4}]
             * aqi : [{"date":"2017-07-08","max":14,"avg":12.33,"min":0},{"date":"2017-07-09","max":22,"avg":18.83,"min":15},{"date":"2017-07-10","max":30,"avg":25.25,"min":19},{"date":"2017-07-11","max":35,"avg":31.5,"min":29},{"date":"2017-07-12","max":31,"avg":30.46,"min":29}]
             * humidity : [{"date":"2017-07-08","max":0.81,"avg":0.78,"min":0.68},{"date":"2017-07-09","max":0.83,"avg":0.77,"min":0.67},{"date":"2017-07-10","max":0.85,"avg":0.78,"min":0.69},{"date":"2017-07-11","max":0.86,"avg":0.78,"min":0.71},{"date":"2017-07-12","max":0.85,"avg":0.76,"min":0.66}]
             * astro : [{"date":"2017-07-08","sunset":{"time":"18:47"},"sunrise":{"time":"05:09"}},{"date":"2017-07-09","sunset":{"time":"18:47"},"sunrise":{"time":"05:09"}},{"date":"2017-07-10","sunset":{"time":"18:47"},"sunrise":{"time":"05:10"}},{"date":"2017-07-11","sunset":{"time":"18:46"},"sunrise":{"time":"05:10"}},{"date":"2017-07-12","sunset":{"time":"18:46"},"sunrise":{"time":"05:11"}}]
             * pres : [{"date":"2017-07-08","max":98355.27,"avg":98277.94,"min":98167.86},{"date":"2017-07-09","max":98308.78,"avg":98117.08,"min":97946.29},{"date":"2017-07-10","max":98366.92,"avg":98142.03,"min":97897.98},{"date":"2017-07-11","max":98498.7,"avg":98387.6,"min":98268.74},{"date":"2017-07-12","max":98598.62,"avg":98421.92,"min":98258.59}]
             * ultraviolet : [{"index":"1","desc":"最弱","datetime":"2017-07-08"},{"index":"2","desc":"弱","datetime":"2017-07-09"},{"index":"2","desc":"弱","datetime":"2017-07-10"},{"index":"2","desc":"弱","datetime":"2017-07-11"},{"index":"1","desc":"最弱","datetime":"2017-07-12"}]
             * pm25 : [{"date":"2017-07-08","max":8,"avg":6.83,"min":1},{"date":"2017-07-09","max":14,"avg":11.96,"min":9},{"date":"2017-07-10","max":20,"avg":16.5,"min":12},{"date":"2017-07-11","max":24,"avg":21.17,"min":19},{"date":"2017-07-12","max":21,"avg":20.46,"min":19}]
             * dressing : [{"index":"2","desc":"很热","datetime":"2017-07-08"},{"index":"2","desc":"很热","datetime":"2017-07-09"},{"index":"2","desc":"很热","datetime":"2017-07-10"},{"index":"2","desc":"很热","datetime":"2017-07-11"},{"index":"2","desc":"很热","datetime":"2017-07-12"}]
             * carWashing : [{"index":"3","desc":"较不适宜","datetime":"2017-07-08"},{"index":"3","desc":"较不适宜","datetime":"2017-07-09"},{"index":"3","desc":"较不适宜","datetime":"2017-07-10"},{"index":"3","desc":"较不适宜","datetime":"2017-07-11"},{"index":"1","desc":"适宜","datetime":"2017-07-12"}]
             * precipitation : [{"date":"2017-07-08","max":7.2052,"avg":2.0169,"min":0},{"date":"2017-07-09","max":0.0651,"avg":0.0119,"min":0},{"date":"2017-07-10","max":0.5746,"avg":0.1188,"min":0},{"date":"2017-07-11","max":0.0739,"avg":0.0256,"min":0},{"date":"2017-07-12","max":0,"avg":0,"min":0}]
             * wind : [{"date":"2017-07-08","max":{"direction":170.9,"speed":5.36},"avg":{"direction":139.32,"speed":3.13},"min":{"direction":254.02,"speed":0.32}},{"date":"2017-07-09","max":{"direction":206.81,"speed":6.3},"avg":{"direction":222.24,"speed":3.74},"min":{"direction":105.88,"speed":0.56}},{"date":"2017-07-10","max":{"direction":207.07,"speed":6.66},"avg":{"direction":222.41,"speed":4.72},"min":{"direction":280.07,"speed":2.32}},{"date":"2017-07-11","max":{"direction":179.79,"speed":5.61},"avg":{"direction":169.08,"speed":4.78},"min":{"direction":286.34,"speed":3.21}},{"date":"2017-07-12","max":{"direction":151.61,"speed":9.78},"avg":{"direction":218.76,"speed":5.91},"min":{"direction":99.86,"speed":1.82}}]
             */

            private String status;
            private List<ColdRiskBean> coldRisk;
            private List<TemperatureBeanX> temperature;
            private List<SkyconBeanX> skycon;
            private List<CloudrateBeanX> cloudrate;
            private List<AqiBeanX> aqi;
            private List<HumidityBeanX> humidity;
            private List<AstroBean> astro;
            private List<PresBeanX> pres;
            private List<UltravioletBean> ultraviolet;
            private List<Pm25BeanX> pm25;
            private List<DressingBean> dressing;
            private List<CarWashingBean> carWashing;
            private List<PrecipitationBeanX> precipitation;
            private List<WindBeanX> wind;

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

            public List<PresBeanX> getPres() {
                return pres;
            }

            public void setPres(List<PresBeanX> pres) {
                this.pres = pres;
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

            public static class ColdRiskBean {
                /**
                 * index : 3
                 * desc : 易发
                 * datetime : 2017-07-08
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
                 * date : 2017-07-08
                 * max : 30
                 * avg : 26.75
                 * min : 26.1
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
                 * date : 2017-07-08
                 * value : RAIN
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
                 * date : 2017-07-08
                 * max : 1
                 * avg : 0.94
                 * min : 0.69
                 */

                private String date;
                private int max;
                private float avg;
                private float min;

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

                public float getMin() {
                    return min;
                }

                public void setMin(float min) {
                    this.min = min;
                }
            }

            public static class AqiBeanX {
                /**
                 * date : 2017-07-08
                 * max : 14
                 * avg : 12.33
                 * min : 0
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

            public static class HumidityBeanX {
                /**
                 * date : 2017-07-08
                 * max : 0.81
                 * avg : 0.78
                 * min : 0.68
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

            public static class AstroBean {
                /**
                 * date : 2017-07-08
                 * sunset : {"time":"18:47"}
                 * sunrise : {"time":"05:09"}
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
                     * time : 18:47
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
                     * time : 05:09
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

            public static class PresBeanX {
                /**
                 * date : 2017-07-08
                 * max : 98355.27
                 * avg : 98277.94
                 * min : 98167.86
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

            public static class UltravioletBean {
                /**
                 * index : 1
                 * desc : 最弱
                 * datetime : 2017-07-08
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
                 * date : 2017-07-08
                 * max : 8
                 * avg : 6.83
                 * min : 1
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
                 * datetime : 2017-07-08
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
                 * datetime : 2017-07-08
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
                 * date : 2017-07-08
                 * max : 7.2052
                 * avg : 2.0169
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
                 * date : 2017-07-08
                 * max : {"direction":170.9,"speed":5.36}
                 * avg : {"direction":139.32,"speed":3.13}
                 * min : {"direction":254.02,"speed":0.32}
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
                     * direction : 170.9
                     * speed : 5.36
                     */

                    private float direction;
                    private float speed;

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
                }

                public static class AvgBean {
                    /**
                     * direction : 139.32
                     * speed : 3.13
                     */

                    private float direction;
                    private float speed;

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
                }

                public static class MinBean {
                    /**
                     * direction : 254.02
                     * speed : 0.32
                     */

                    private float direction;
                    private float speed;

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
                }
            }
        }
    }
}
