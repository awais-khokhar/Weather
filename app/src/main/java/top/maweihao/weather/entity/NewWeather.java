package top.maweihao.weather.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class NewWeather {

    /**
     * status : ok
     * lang : zh_CN
     * result : {"hourly":{"status":"ok","description":"晴，明天凌晨4点钟后转阴","skycon":[{"value":"CLEAR_NIGHT","datetime":"2017-10-24 21:00"},{"value":"CLEAR_NIGHT","datetime":"2017-10-24 22:00"},{"value":"CLEAR_NIGHT","datetime":"2017-10-24 23:00"},{"value":"CLEAR_NIGHT","datetime":"2017-10-25 00:00"},{"value":"CLEAR_NIGHT","datetime":"2017-10-25 01:00"},{"value":"CLEAR_NIGHT","datetime":"2017-10-25 02:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 03:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 04:00"},{"value":"CLOUDY","datetime":"2017-10-25 05:00"},{"value":"CLOUDY","datetime":"2017-10-25 06:00"},{"value":"CLOUDY","datetime":"2017-10-25 07:00"},{"value":"CLOUDY","datetime":"2017-10-25 08:00"},{"value":"CLOUDY","datetime":"2017-10-25 09:00"},{"value":"CLOUDY","datetime":"2017-10-25 10:00"},{"value":"CLOUDY","datetime":"2017-10-25 11:00"},{"value":"CLOUDY","datetime":"2017-10-25 12:00"},{"value":"CLOUDY","datetime":"2017-10-25 13:00"},{"value":"CLOUDY","datetime":"2017-10-25 14:00"},{"value":"CLOUDY","datetime":"2017-10-25 15:00"},{"value":"CLOUDY","datetime":"2017-10-25 16:00"},{"value":"CLOUDY","datetime":"2017-10-25 17:00"},{"value":"CLOUDY","datetime":"2017-10-25 18:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 19:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 20:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 21:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 22:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 23:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 00:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 01:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 02:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 03:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 04:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 05:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 06:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 07:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 08:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 09:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 10:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 11:00"},{"value":"CLEAR_DAY","datetime":"2017-10-26 12:00"},{"value":"CLEAR_DAY","datetime":"2017-10-26 13:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 14:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 15:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 16:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 17:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 18:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 19:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 20:00"}],"cloudrate":[{"value":0.01,"datetime":"2017-10-24 21:00"},{"value":0.02,"datetime":"2017-10-24 22:00"},{"value":0,"datetime":"2017-10-24 23:00"},{"value":0,"datetime":"2017-10-25 00:00"},{"value":0,"datetime":"2017-10-25 01:00"},{"value":0.03,"datetime":"2017-10-25 02:00"},{"value":0.27,"datetime":"2017-10-25 03:00"},{"value":0.57,"datetime":"2017-10-25 04:00"},{"value":0.81,"datetime":"2017-10-25 05:00"},{"value":0.92,"datetime":"2017-10-25 06:00"},{"value":0.92,"datetime":"2017-10-25 07:00"},{"value":0.9,"datetime":"2017-10-25 08:00"},{"value":0.92,"datetime":"2017-10-25 09:00"},{"value":0.96,"datetime":"2017-10-25 10:00"},{"value":1,"datetime":"2017-10-25 11:00"},{"value":1,"datetime":"2017-10-25 12:00"},{"value":1,"datetime":"2017-10-25 13:00"},{"value":1,"datetime":"2017-10-25 14:00"},{"value":1,"datetime":"2017-10-25 15:00"},{"value":1,"datetime":"2017-10-25 16:00"},{"value":0.97,"datetime":"2017-10-25 17:00"},{"value":0.86,"datetime":"2017-10-25 18:00"},{"value":0.71,"datetime":"2017-10-25 19:00"},{"value":0.58,"datetime":"2017-10-25 20:00"},{"value":0.48,"datetime":"2017-10-25 21:00"},{"value":0.43,"datetime":"2017-10-25 22:00"},{"value":0.42,"datetime":"2017-10-25 23:00"},{"value":0.43,"datetime":"2017-10-26 00:00"},{"value":0.45,"datetime":"2017-10-26 01:00"},{"value":0.46,"datetime":"2017-10-26 02:00"},{"value":0.45,"datetime":"2017-10-26 03:00"},{"value":0.43,"datetime":"2017-10-26 04:00"},{"value":0.41,"datetime":"2017-10-26 05:00"},{"value":0.4,"datetime":"2017-10-26 06:00"},{"value":0.39,"datetime":"2017-10-26 07:00"},{"value":0.37,"datetime":"2017-10-26 08:00"},{"value":0.33,"datetime":"2017-10-26 09:00"},{"value":0.28,"datetime":"2017-10-26 10:00"},{"value":0.23,"datetime":"2017-10-26 11:00"},{"value":0.19,"datetime":"2017-10-26 12:00"},{"value":0.17,"datetime":"2017-10-26 13:00"},{"value":0.21,"datetime":"2017-10-26 14:00"},{"value":0.29,"datetime":"2017-10-26 15:00"},{"value":0.38,"datetime":"2017-10-26 16:00"},{"value":0.43,"datetime":"2017-10-26 17:00"},{"value":0.41,"datetime":"2017-10-26 18:00"},{"value":0.32,"datetime":"2017-10-26 19:00"},{"value":0.22,"datetime":"2017-10-26 20:00"}],"aqi":[{"value":132,"datetime":"2017-10-24 21:00"},{"value":129,"datetime":"2017-10-24 22:00"},{"value":128,"datetime":"2017-10-24 23:00"},{"value":128,"datetime":"2017-10-25 00:00"},{"value":129,"datetime":"2017-10-25 01:00"},{"value":130,"datetime":"2017-10-25 02:00"},{"value":132,"datetime":"2017-10-25 03:00"},{"value":133,"datetime":"2017-10-25 04:00"},{"value":134,"datetime":"2017-10-25 05:00"},{"value":134,"datetime":"2017-10-25 06:00"},{"value":132,"datetime":"2017-10-25 07:00"},{"value":128,"datetime":"2017-10-25 08:00"},{"value":124,"datetime":"2017-10-25 09:00"},{"value":122,"datetime":"2017-10-25 10:00"},{"value":119,"datetime":"2017-10-25 11:00"},{"value":117,"datetime":"2017-10-25 12:00"},{"value":114,"datetime":"2017-10-25 13:00"},{"value":112,"datetime":"2017-10-25 14:00"},{"value":108,"datetime":"2017-10-25 15:00"},{"value":106,"datetime":"2017-10-25 16:00"},{"value":105,"datetime":"2017-10-25 17:00"},{"value":103,"datetime":"2017-10-25 18:00"},{"value":103,"datetime":"2017-10-25 19:00"},{"value":103,"datetime":"2017-10-25 20:00"},{"value":103,"datetime":"2017-10-25 21:00"},{"value":100,"datetime":"2017-10-25 22:00"},{"value":98,"datetime":"2017-10-25 23:00"},{"value":95,"datetime":"2017-10-26 00:00"},{"value":96,"datetime":"2017-10-26 01:00"},{"value":99,"datetime":"2017-10-26 02:00"},{"value":102,"datetime":"2017-10-26 03:00"},{"value":105,"datetime":"2017-10-26 04:00"},{"value":106,"datetime":"2017-10-26 05:00"},{"value":107,"datetime":"2017-10-26 06:00"},{"value":108,"datetime":"2017-10-26 07:00"},{"value":109,"datetime":"2017-10-26 08:00"},{"value":112,"datetime":"2017-10-26 09:00"},{"value":113,"datetime":"2017-10-26 10:00"},{"value":114,"datetime":"2017-10-26 11:00"},{"value":117,"datetime":"2017-10-26 12:00"},{"value":118,"datetime":"2017-10-26 13:00"},{"value":118,"datetime":"2017-10-26 14:00"},{"value":119,"datetime":"2017-10-26 15:00"},{"value":121,"datetime":"2017-10-26 16:00"},{"value":122,"datetime":"2017-10-26 17:00"},{"value":123,"datetime":"2017-10-26 18:00"},{"value":125,"datetime":"2017-10-26 19:00"},{"value":128,"datetime":"2017-10-26 20:00"}],"humidity":[{"value":0.58,"datetime":"2017-10-24 21:00"},{"value":0.59,"datetime":"2017-10-24 22:00"},{"value":0.61,"datetime":"2017-10-24 23:00"},{"value":0.62,"datetime":"2017-10-25 00:00"},{"value":0.63,"datetime":"2017-10-25 01:00"},{"value":0.65,"datetime":"2017-10-25 02:00"},{"value":0.66,"datetime":"2017-10-25 03:00"},{"value":0.66,"datetime":"2017-10-25 04:00"},{"value":0.67,"datetime":"2017-10-25 05:00"},{"value":0.67,"datetime":"2017-10-25 06:00"},{"value":0.66,"datetime":"2017-10-25 07:00"},{"value":0.64,"datetime":"2017-10-25 08:00"},{"value":0.59,"datetime":"2017-10-25 09:00"},{"value":0.54,"datetime":"2017-10-25 10:00"},{"value":0.5,"datetime":"2017-10-25 11:00"},{"value":0.47,"datetime":"2017-10-25 12:00"},{"value":0.47,"datetime":"2017-10-25 13:00"},{"value":0.49,"datetime":"2017-10-25 14:00"},{"value":0.51,"datetime":"2017-10-25 15:00"},{"value":0.54,"datetime":"2017-10-25 16:00"},{"value":0.57,"datetime":"2017-10-25 17:00"},{"value":0.6,"datetime":"2017-10-25 18:00"},{"value":0.62,"datetime":"2017-10-25 19:00"},{"value":0.64,"datetime":"2017-10-25 20:00"},{"value":0.65,"datetime":"2017-10-25 21:00"},{"value":0.66,"datetime":"2017-10-25 22:00"},{"value":0.66,"datetime":"2017-10-25 23:00"},{"value":0.67,"datetime":"2017-10-26 00:00"},{"value":0.68,"datetime":"2017-10-26 01:00"},{"value":0.69,"datetime":"2017-10-26 02:00"},{"value":0.7,"datetime":"2017-10-26 03:00"},{"value":0.72,"datetime":"2017-10-26 04:00"},{"value":0.73,"datetime":"2017-10-26 05:00"},{"value":0.73,"datetime":"2017-10-26 06:00"},{"value":0.72,"datetime":"2017-10-26 07:00"},{"value":0.69,"datetime":"2017-10-26 08:00"},{"value":0.64,"datetime":"2017-10-26 09:00"},{"value":0.58,"datetime":"2017-10-26 10:00"},{"value":0.53,"datetime":"2017-10-26 11:00"},{"value":0.5,"datetime":"2017-10-26 12:00"},{"value":0.5,"datetime":"2017-10-26 13:00"},{"value":0.51,"datetime":"2017-10-26 14:00"},{"value":0.54,"datetime":"2017-10-26 15:00"},{"value":0.58,"datetime":"2017-10-26 16:00"},{"value":0.61,"datetime":"2017-10-26 17:00"},{"value":0.64,"datetime":"2017-10-26 18:00"},{"value":0.66,"datetime":"2017-10-26 19:00"},{"value":0.67,"datetime":"2017-10-26 20:00"}],"pm25":[{"value":100,"datetime":"2017-10-24 21:00"},{"value":98,"datetime":"2017-10-24 22:00"},{"value":97,"datetime":"2017-10-24 23:00"},{"value":97,"datetime":"2017-10-25 00:00"},{"value":98,"datetime":"2017-10-25 01:00"},{"value":99,"datetime":"2017-10-25 02:00"},{"value":100,"datetime":"2017-10-25 03:00"},{"value":101,"datetime":"2017-10-25 04:00"},{"value":102,"datetime":"2017-10-25 05:00"},{"value":102,"datetime":"2017-10-25 06:00"},{"value":100,"datetime":"2017-10-25 07:00"},{"value":97,"datetime":"2017-10-25 08:00"},{"value":94,"datetime":"2017-10-25 09:00"},{"value":92,"datetime":"2017-10-25 10:00"},{"value":90,"datetime":"2017-10-25 11:00"},{"value":88,"datetime":"2017-10-25 12:00"},{"value":86,"datetime":"2017-10-25 13:00"},{"value":84,"datetime":"2017-10-25 14:00"},{"value":81,"datetime":"2017-10-25 15:00"},{"value":79,"datetime":"2017-10-25 16:00"},{"value":78,"datetime":"2017-10-25 17:00"},{"value":77,"datetime":"2017-10-25 18:00"},{"value":77,"datetime":"2017-10-25 19:00"},{"value":77,"datetime":"2017-10-25 20:00"},{"value":77,"datetime":"2017-10-25 21:00"},{"value":75,"datetime":"2017-10-25 22:00"},{"value":73,"datetime":"2017-10-25 23:00"},{"value":71,"datetime":"2017-10-26 00:00"},{"value":72,"datetime":"2017-10-26 01:00"},{"value":74,"datetime":"2017-10-26 02:00"},{"value":76,"datetime":"2017-10-26 03:00"},{"value":78,"datetime":"2017-10-26 04:00"},{"value":79,"datetime":"2017-10-26 05:00"},{"value":80,"datetime":"2017-10-26 06:00"},{"value":81,"datetime":"2017-10-26 07:00"},{"value":82,"datetime":"2017-10-26 08:00"},{"value":84,"datetime":"2017-10-26 09:00"},{"value":85,"datetime":"2017-10-26 10:00"},{"value":86,"datetime":"2017-10-26 11:00"},{"value":88,"datetime":"2017-10-26 12:00"},{"value":89,"datetime":"2017-10-26 13:00"},{"value":89,"datetime":"2017-10-26 14:00"},{"value":90,"datetime":"2017-10-26 15:00"},{"value":91,"datetime":"2017-10-26 16:00"},{"value":92,"datetime":"2017-10-26 17:00"},{"value":93,"datetime":"2017-10-26 18:00"},{"value":95,"datetime":"2017-10-26 19:00"},{"value":97,"datetime":"2017-10-26 20:00"}],"precipitation":[{"value":0,"datetime":"2017-10-24 21:00"},{"value":0,"datetime":"2017-10-24 22:00"},{"value":0,"datetime":"2017-10-24 23:00"},{"value":0,"datetime":"2017-10-25 00:00"},{"value":0,"datetime":"2017-10-25 01:00"},{"value":0,"datetime":"2017-10-25 02:00"},{"value":0,"datetime":"2017-10-25 03:00"},{"value":0,"datetime":"2017-10-25 04:00"},{"value":0,"datetime":"2017-10-25 05:00"},{"value":0,"datetime":"2017-10-25 06:00"},{"value":0,"datetime":"2017-10-25 07:00"},{"value":0,"datetime":"2017-10-25 08:00"},{"value":0,"datetime":"2017-10-25 09:00"},{"value":0,"datetime":"2017-10-25 10:00"},{"value":0,"datetime":"2017-10-25 11:00"},{"value":0,"datetime":"2017-10-25 12:00"},{"value":0,"datetime":"2017-10-25 13:00"},{"value":0,"datetime":"2017-10-25 14:00"},{"value":0,"datetime":"2017-10-25 15:00"},{"value":0,"datetime":"2017-10-25 16:00"},{"value":0,"datetime":"2017-10-25 17:00"},{"value":0,"datetime":"2017-10-25 18:00"},{"value":0,"datetime":"2017-10-25 19:00"},{"value":0,"datetime":"2017-10-25 20:00"},{"value":0,"datetime":"2017-10-25 21:00"},{"value":0,"datetime":"2017-10-25 22:00"},{"value":0,"datetime":"2017-10-25 23:00"},{"value":0,"datetime":"2017-10-26 00:00"},{"value":0,"datetime":"2017-10-26 01:00"},{"value":0,"datetime":"2017-10-26 02:00"},{"value":0,"datetime":"2017-10-26 03:00"},{"value":0,"datetime":"2017-10-26 04:00"},{"value":0,"datetime":"2017-10-26 05:00"},{"value":0,"datetime":"2017-10-26 06:00"},{"value":0,"datetime":"2017-10-26 07:00"},{"value":0,"datetime":"2017-10-26 08:00"},{"value":0,"datetime":"2017-10-26 09:00"},{"value":0,"datetime":"2017-10-26 10:00"},{"value":0,"datetime":"2017-10-26 11:00"},{"value":0,"datetime":"2017-10-26 12:00"},{"value":0,"datetime":"2017-10-26 13:00"},{"value":0,"datetime":"2017-10-26 14:00"},{"value":0,"datetime":"2017-10-26 15:00"},{"value":0,"datetime":"2017-10-26 16:00"},{"value":0,"datetime":"2017-10-26 17:00"},{"value":0,"datetime":"2017-10-26 18:00"},{"value":0,"datetime":"2017-10-26 19:00"},{"value":0,"datetime":"2017-10-26 20:00"}],"wind":[{"direction":62.34,"speed":3.16,"datetime":"2017-10-24 21:00"},{"direction":44.83,"speed":3.64,"datetime":"2017-10-24 22:00"},{"direction":33.4,"speed":4.14,"datetime":"2017-10-24 23:00"},{"direction":25.79,"speed":4.57,"datetime":"2017-10-25 00:00"},{"direction":20.35,"speed":4.92,"datetime":"2017-10-25 01:00"},{"direction":16.17,"speed":5.21,"datetime":"2017-10-25 02:00"},{"direction":12.71,"speed":5.47,"datetime":"2017-10-25 03:00"},{"direction":9.3,"speed":5.71,"datetime":"2017-10-25 04:00"},{"direction":5.31,"speed":5.92,"datetime":"2017-10-25 05:00"},{"direction":0.74,"speed":6.09,"datetime":"2017-10-25 06:00"},{"direction":357.38,"speed":5.92,"datetime":"2017-10-25 07:00"},{"direction":357.36,"speed":5.07,"datetime":"2017-10-25 08:00"},{"direction":5.86,"speed":3.33,"datetime":"2017-10-25 09:00"},{"direction":49.02,"speed":1.47,"datetime":"2017-10-25 10:00"},{"direction":133.81,"speed":2.23,"datetime":"2017-10-25 11:00"},{"direction":158.13,"speed":4.11,"datetime":"2017-10-25 12:00"},{"direction":168.64,"speed":5.7,"datetime":"2017-10-25 13:00"},{"direction":173.29,"speed":6.69,"datetime":"2017-10-25 14:00"},{"direction":173.27,"speed":6.87,"datetime":"2017-10-25 15:00"},{"direction":170.06,"speed":6.32,"datetime":"2017-10-25 16:00"},{"direction":163.98,"speed":5.2,"datetime":"2017-10-25 17:00"},{"direction":153.37,"speed":3.75,"datetime":"2017-10-25 18:00"},{"direction":131.01,"speed":2.43,"datetime":"2017-10-25 19:00"},{"direction":92.33,"speed":1.96,"datetime":"2017-10-25 20:00"},{"direction":65.51,"speed":2.27,"datetime":"2017-10-25 21:00"},{"direction":53.65,"speed":2.65,"datetime":"2017-10-25 22:00"},{"direction":46.26,"speed":2.88,"datetime":"2017-10-25 23:00"},{"direction":38.51,"speed":3.02,"datetime":"2017-10-26 00:00"},{"direction":30.86,"speed":3.11,"datetime":"2017-10-26 01:00"},{"direction":24.85,"speed":3.17,"datetime":"2017-10-26 02:00"},{"direction":21.38,"speed":3.19,"datetime":"2017-10-26 03:00"},{"direction":18.98,"speed":3.28,"datetime":"2017-10-26 04:00"},{"direction":16.12,"speed":3.58,"datetime":"2017-10-26 05:00"},{"direction":12.81,"speed":4.12,"datetime":"2017-10-26 06:00"},{"direction":11.35,"speed":4.38,"datetime":"2017-10-26 07:00"},{"direction":14,"speed":3.68,"datetime":"2017-10-26 08:00"},{"direction":34.83,"speed":1.72,"datetime":"2017-10-26 09:00"},{"direction":149.37,"speed":1.75,"datetime":"2017-10-26 10:00"},{"direction":176.17,"speed":4.26,"datetime":"2017-10-26 11:00"},{"direction":189.07,"speed":6.14,"datetime":"2017-10-26 12:00"},{"direction":198.29,"speed":7.24,"datetime":"2017-10-26 13:00"},{"direction":202.82,"speed":7.36,"datetime":"2017-10-26 14:00"},{"direction":200.31,"speed":6.3,"datetime":"2017-10-26 15:00"},{"direction":189.78,"speed":4.65,"datetime":"2017-10-26 16:00"},{"direction":169.81,"speed":3.2,"datetime":"2017-10-26 17:00"},{"direction":145.88,"speed":2.28,"datetime":"2017-10-26 18:00"},{"direction":116.15,"speed":1.58,"datetime":"2017-10-26 19:00"},{"direction":64.14,"speed":1.36,"datetime":"2017-10-26 20:00"}],"temperature":[{"value":11,"datetime":"2017-10-24 21:00"},{"value":11.13,"datetime":"2017-10-24 22:00"},{"value":10.47,"datetime":"2017-10-24 23:00"},{"value":9.8,"datetime":"2017-10-25 00:00"},{"value":8.33,"datetime":"2017-10-25 01:00"},{"value":7.57,"datetime":"2017-10-25 02:00"},{"value":7.2,"datetime":"2017-10-25 03:00"},{"value":7.1,"datetime":"2017-10-25 04:00"},{"value":7,"datetime":"2017-10-25 05:00"},{"value":8.6,"datetime":"2017-10-25 06:00"},{"value":9.4,"datetime":"2017-10-25 07:00"},{"value":10.1,"datetime":"2017-10-25 08:00"},{"value":10.7,"datetime":"2017-10-25 09:00"},{"value":11.3,"datetime":"2017-10-25 10:00"},{"value":11.9,"datetime":"2017-10-25 11:00"},{"value":12.7,"datetime":"2017-10-25 12:00"},{"value":13.5,"datetime":"2017-10-25 13:00"},{"value":15,"datetime":"2017-10-25 14:00"},{"value":14.9,"datetime":"2017-10-25 15:00"},{"value":14.8,"datetime":"2017-10-25 16:00"},{"value":14.4,"datetime":"2017-10-25 17:00"},{"value":13.8,"datetime":"2017-10-25 18:00"},{"value":13.5,"datetime":"2017-10-25 19:00"},{"value":12.14,"datetime":"2017-10-25 20:00"},{"value":11.11,"datetime":"2017-10-25 21:00"},{"value":10.29,"datetime":"2017-10-25 22:00"},{"value":9.61,"datetime":"2017-10-25 23:00"},{"value":9.02,"datetime":"2017-10-26 00:00"},{"value":8.73,"datetime":"2017-10-26 01:00"},{"value":8.46,"datetime":"2017-10-26 02:00"},{"value":8.24,"datetime":"2017-10-26 03:00"},{"value":9,"datetime":"2017-10-26 04:00"},{"value":8,"datetime":"2017-10-26 05:00"},{"value":10.85,"datetime":"2017-10-26 06:00"},{"value":11.76,"datetime":"2017-10-26 07:00"},{"value":12.56,"datetime":"2017-10-26 08:00"},{"value":13.25,"datetime":"2017-10-26 09:00"},{"value":13.89,"datetime":"2017-10-26 10:00"},{"value":14.58,"datetime":"2017-10-26 11:00"},{"value":15.38,"datetime":"2017-10-26 12:00"},{"value":18,"datetime":"2017-10-26 13:00"},{"value":17,"datetime":"2017-10-26 14:00"},{"value":17.62,"datetime":"2017-10-26 15:00"},{"value":17.21,"datetime":"2017-10-26 16:00"},{"value":16.61,"datetime":"2017-10-26 17:00"},{"value":15.84,"datetime":"2017-10-26 18:00"},{"value":14.95,"datetime":"2017-10-26 19:00"},{"value":13.32,"datetime":"2017-10-26 20:00"}]},"alert":{"status":"ok","content":[{"province":"北京","status":"预警中","code":"1202","description":"怀柔区气象台24日20时45分发布大雾黄色预警,预计今日后半夜至明日上午，怀柔区有大雾天气，能见度小于1千米，部分地区小于500米，请注意防范。","alertId":"11011641600000_20171024204500","city":"北京","pubtimestamp":1.5088491E9,"latlon":[40.324272,116.637122],"county":"怀柔","request_status":"ok","location":"北京北京怀柔","title":"怀柔区气象台发布大雾黄色预警[III/较大]","regionId":"101010500"}]},"minutely":{"status":"ok","description":"未来两小时不会下雨，放心出门吧","probability":[0,0,0,0],"datasource":"radar","precipitation_2h":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"precipitation":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]},"daily":{"status":"ok","coldRisk":[{"index":"4","desc":"极易发","datetime":"2017-10-24"},{"index":"4","desc":"极易发","datetime":"2017-10-25"},{"index":"4","desc":"极易发","datetime":"2017-10-26"},{"index":"4","desc":"极易发","datetime":"2017-10-27"},{"index":"4","desc":"极易发","datetime":"2017-10-28"},{"index":"4","desc":"极易发","datetime":"2017-10-29"},{"index":"4","desc":"极易发","datetime":"2017-10-30"},{"index":"3","desc":"易发","datetime":"2017-10-31"},{"index":"4","desc":"极易发","datetime":"2017-11-01"},{"index":"4","desc":"极易发","datetime":"2017-11-02"},{"index":"4","desc":"极易发","datetime":"2017-11-03"},{"index":"4","desc":"极易发","datetime":"2017-11-04"},{"index":"4","desc":"极易发","datetime":"2017-11-05"},{"index":"3","desc":"易发","datetime":"2017-11-06"},{"index":"4","desc":"极易发","datetime":"2017-11-07"}],"temperature":[{"date":"2017-10-24","max":18,"avg":10.87,"min":4},{"date":"2017-10-25","max":15,"avg":11.03,"min":7},{"date":"2017-10-26","max":18,"avg":12.77,"min":8},{"date":"2017-10-27","max":20,"avg":13.71,"min":7},{"date":"2017-10-28","max":16,"avg":11.94,"min":4.4},{"date":"2017-10-29","max":13,"avg":7.54,"min":3},{"date":"2017-10-30","max":15,"avg":8.77,"min":3},{"date":"2017-10-31","max":13,"avg":9.23,"min":6},{"date":"2017-11-01","max":13,"avg":8.48,"min":4},{"date":"2017-11-02","max":13,"avg":7.04,"min":2},{"date":"2017-11-03","max":8,"avg":4.48,"min":0},{"date":"2017-11-04","max":9,"avg":5.49,"min":1},{"date":"2017-11-05","max":10,"avg":6.04,"min":2},{"date":"2017-11-06","max":10,"avg":5.81,"min":3},{"date":"2017-11-07","max":9,"avg":6.09,"min":1}],"skycon":[{"date":"2017-10-24","value":"CLEAR_NIGHT"},{"date":"2017-10-25","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-10-26","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-10-27","value":"CLEAR_DAY"},{"date":"2017-10-28","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-10-29","value":"CLEAR_DAY"},{"date":"2017-10-30","value":"CLEAR_DAY"},{"date":"2017-10-31","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-11-01","value":"CLEAR_DAY"},{"date":"2017-11-02","value":"CLEAR_DAY"},{"date":"2017-11-03","value":"CLEAR_DAY"},{"date":"2017-11-04","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-11-05","value":"CLEAR_DAY"},{"date":"2017-11-06","value":"CLEAR_DAY"},{"date":"2017-11-07","value":"CLEAR_DAY"}],"cloudrate":[{"date":"2017-10-24","max":0.02,"avg":0.01,"min":0},{"date":"2017-10-25","max":1,"avg":0.7,"min":0},{"date":"2017-10-26","max":0.46,"avg":0.31,"min":0},{"date":"2017-10-27","max":0,"avg":0,"min":0},{"date":"2017-10-28","max":0.96,"avg":0.26,"min":0},{"date":"2017-10-29","max":0,"avg":0,"min":0},{"date":"2017-10-30","max":0,"avg":0,"min":0},{"date":"2017-10-31","max":0.92,"avg":0.33,"min":0},{"date":"2017-11-01","max":0.31,"avg":0.08,"min":0},{"date":"2017-11-02","max":0.19,"avg":0.02,"min":0},{"date":"2017-11-03","max":0.26,"avg":0.11,"min":0},{"date":"2017-11-04","max":1,"avg":0.5,"min":0},{"date":"2017-11-05","max":0.03,"avg":0,"min":0},{"date":"2017-11-06","max":0,"avg":0,"min":0},{"date":"2017-11-07","max":0.01,"avg":0,"min":0}],"aqi":[{"date":"2017-10-24","max":132,"avg":129.67,"min":33},{"date":"2017-10-25","max":134,"avg":117.38,"min":98},{"date":"2017-10-26","max":128,"avg":113.88,"min":95},{"date":"2017-10-27","max":143,"avg":132.67,"min":121},{"date":"2017-10-28","max":141,"avg":74.96,"min":27},{"date":"2017-10-29","max":38,"avg":31.83,"min":26},{"date":"2017-10-30","max":64,"avg":46.33,"min":35},{"date":"2017-10-31","max":88,"avg":73.33,"min":63},{"date":"2017-11-01","max":71,"avg":56,"min":30},{"date":"2017-11-02","max":29,"avg":25.5,"min":19},{"date":"2017-11-03","max":42,"avg":30,"min":18},{"date":"2017-11-04","max":57,"avg":53.46,"min":42},{"date":"2017-11-05","max":66,"avg":56.46,"min":45},{"date":"2017-11-06","max":43,"avg":36.58,"min":29},{"date":"2017-11-07","max":52,"avg":34,"min":27}],"humidity":[{"date":"2017-10-24","max":0.65,"avg":0.59,"min":0.43},{"date":"2017-10-25","max":0.67,"avg":0.6,"min":0.47},{"date":"2017-10-26","max":0.73,"avg":0.64,"min":0.5},{"date":"2017-10-27","max":0.76,"avg":0.62,"min":0.41},{"date":"2017-10-28","max":0.68,"avg":0.33,"min":0.18},{"date":"2017-10-29","max":0.33,"avg":0.27,"min":0.17},{"date":"2017-10-30","max":0.37,"avg":0.31,"min":0.2},{"date":"2017-10-31","max":0.51,"avg":0.4,"min":0.3},{"date":"2017-11-01","max":0.54,"avg":0.39,"min":0.15},{"date":"2017-11-02","max":0.46,"avg":0.35,"min":0.23},{"date":"2017-11-03","max":0.35,"avg":0.25,"min":0.17},{"date":"2017-11-04","max":0.36,"avg":0.25,"min":0.13},{"date":"2017-11-05","max":0.28,"avg":0.21,"min":0.14},{"date":"2017-11-06","max":0.37,"avg":0.27,"min":0.17},{"date":"2017-11-07","max":0.42,"avg":0.31,"min":0.21}],"astro":[{"date":"2017-10-24","sunset":{"time":"17:21"},"sunrise":{"time":"06:33"}},{"date":"2017-10-25","sunset":{"time":"17:20"},"sunrise":{"time":"06:35"}},{"date":"2017-10-26","sunset":{"time":"17:18"},"sunrise":{"time":"06:36"}},{"date":"2017-10-27","sunset":{"time":"17:17"},"sunrise":{"time":"06:37"}},{"date":"2017-10-28","sunset":{"time":"17:16"},"sunrise":{"time":"06:38"}},{"date":"2017-10-29","sunset":{"time":"17:14"},"sunrise":{"time":"06:39"}},{"date":"2017-10-30","sunset":{"time":"17:13"},"sunrise":{"time":"06:40"}},{"date":"2017-10-31","sunset":{"time":"17:12"},"sunrise":{"time":"06:41"}},{"date":"2017-11-01","sunset":{"time":"17:11"},"sunrise":{"time":"06:42"}},{"date":"2017-11-02","sunset":{"time":"17:09"},"sunrise":{"time":"06:44"}},{"date":"2017-11-03","sunset":{"time":"17:08"},"sunrise":{"time":"06:45"}},{"date":"2017-11-04","sunset":{"time":"17:07"},"sunrise":{"time":"06:46"}},{"date":"2017-11-05","sunset":{"time":"17:06"},"sunrise":{"time":"06:47"}},{"date":"2017-11-06","sunset":{"time":"17:05"},"sunrise":{"time":"06:48"}},{"date":"2017-11-07","sunset":{"time":"17:04"},"sunrise":{"time":"06:49"}}],"ultraviolet":[{"index":"4","desc":"强","datetime":"2017-10-24"},{"index":"1","desc":"最弱","datetime":"2017-10-25"},{"index":"4","desc":"强","datetime":"2017-10-26"},{"index":"4","desc":"强","datetime":"2017-10-27"},{"index":"1","desc":"最弱","datetime":"2017-10-28"},{"index":"4","desc":"强","datetime":"2017-10-29"},{"index":"4","desc":"强","datetime":"2017-10-30"},{"index":"2","desc":"弱","datetime":"2017-10-31"},{"index":"4","desc":"强","datetime":"2017-11-01"},{"index":"4","desc":"强","datetime":"2017-11-02"},{"index":"4","desc":"强","datetime":"2017-11-03"},{"index":"1","desc":"最弱","datetime":"2017-11-04"},{"index":"4","desc":"强","datetime":"2017-11-05"},{"index":"4","desc":"强","datetime":"2017-11-06"},{"index":"4","desc":"强","datetime":"2017-11-07"}],"pm25":[{"date":"2017-10-24","max":100,"avg":98.33,"min":23},{"date":"2017-10-25","max":102,"avg":88.5,"min":73},{"date":"2017-10-26","max":97,"avg":85.71,"min":71},{"date":"2017-10-27","max":109,"avg":100.92,"min":91},{"date":"2017-10-28","max":108,"avg":55.08,"min":18},{"date":"2017-10-29","max":26,"avg":21.38,"min":17},{"date":"2017-10-30","max":46,"avg":32.21,"min":24},{"date":"2017-10-31","max":65,"avg":53.29,"min":45},{"date":"2017-11-01","max":51,"avg":39.71,"min":20},{"date":"2017-11-02","max":19,"avg":16.75,"min":12},{"date":"2017-11-03","max":29,"avg":20.12,"min":11},{"date":"2017-11-04","max":40,"avg":37.21,"min":29},{"date":"2017-11-05","max":47,"avg":39.83,"min":31},{"date":"2017-11-06","max":30,"avg":24.96,"min":19},{"date":"2017-11-07","max":36,"avg":22.92,"min":18}],"dressing":[{"index":"5","desc":"凉爽","datetime":"2017-10-24"},{"index":"5","desc":"凉爽","datetime":"2017-10-25"},{"index":"5","desc":"凉爽","datetime":"2017-10-26"},{"index":"5","desc":"凉爽","datetime":"2017-10-27"},{"index":"5","desc":"凉爽","datetime":"2017-10-28"},{"index":"6","desc":"冷","datetime":"2017-10-29"},{"index":"6","desc":"冷","datetime":"2017-10-30"},{"index":"6","desc":"冷","datetime":"2017-10-31"},{"index":"6","desc":"冷","datetime":"2017-11-01"},{"index":"6","desc":"冷","datetime":"2017-11-02"},{"index":"7","desc":"寒冷","datetime":"2017-11-03"},{"index":"6","desc":"冷","datetime":"2017-11-04"},{"index":"6","desc":"冷","datetime":"2017-11-05"},{"index":"6","desc":"冷","datetime":"2017-11-06"},{"index":"6","desc":"冷","datetime":"2017-11-07"}],"carWashing":[{"index":"2","desc":"较适宜","datetime":"2017-10-24"},{"index":"2","desc":"较适宜","datetime":"2017-10-25"},{"index":"2","desc":"较适宜","datetime":"2017-10-26"},{"index":"2","desc":"较适宜","datetime":"2017-10-27"},{"index":"2","desc":"较适宜","datetime":"2017-10-28"},{"index":"1","desc":"适宜","datetime":"2017-10-29"},{"index":"1","desc":"适宜","datetime":"2017-10-30"},{"index":"2","desc":"较适宜","datetime":"2017-10-31"},{"index":"1","desc":"适宜","datetime":"2017-11-01"},{"index":"1","desc":"适宜","datetime":"2017-11-02"},{"index":"1","desc":"适宜","datetime":"2017-11-03"},{"index":"1","desc":"适宜","datetime":"2017-11-04"},{"index":"1","desc":"适宜","datetime":"2017-11-05"},{"index":"1","desc":"适宜","datetime":"2017-11-06"},{"index":"1","desc":"适宜","datetime":"2017-11-07"}],"precipitation":[{"date":"2017-10-24","max":0,"avg":0,"min":0},{"date":"2017-10-25","max":0,"avg":0,"min":0},{"date":"2017-10-26","max":0,"avg":0,"min":0},{"date":"2017-10-27","max":0,"avg":0,"min":0},{"date":"2017-10-28","max":0,"avg":0,"min":0},{"date":"2017-10-29","max":0,"avg":0,"min":0},{"date":"2017-10-30","max":0,"avg":0,"min":0},{"date":"2017-10-31","max":0,"avg":0,"min":0},{"date":"2017-11-01","max":0,"avg":0,"min":0},{"date":"2017-11-02","max":0,"avg":0,"min":0},{"date":"2017-11-03","max":0,"avg":0,"min":0},{"date":"2017-11-04","max":0,"avg":0,"min":0},{"date":"2017-11-05","max":0,"avg":0,"min":0},{"date":"2017-11-06","max":0,"avg":0,"min":0},{"date":"2017-11-07","max":0,"avg":0,"min":0}],"wind":[{"date":"2017-10-24","max":{"direction":183.07,"speed":9.72},"avg":{"direction":135.67,"speed":4.6},"min":{"direction":118.89,"speed":1.31}},{"date":"2017-10-25","max":{"direction":173.27,"speed":6.87},"avg":{"direction":61.45,"speed":4.45},"min":{"direction":49.02,"speed":1.47}},{"date":"2017-10-26","max":{"direction":202.82,"speed":7.36},"avg":{"direction":89.92,"speed":3.75},"min":{"direction":64.14,"speed":1.36}},{"date":"2017-10-27","max":{"direction":182.74,"speed":9.58},"avg":{"direction":71.84,"speed":5.14},"min":{"direction":347.22,"speed":1.39}},{"date":"2017-10-28","max":{"direction":340.67,"speed":21.44},"avg":{"direction":340.2,"speed":14.11},"min":{"direction":356.62,"speed":6.36}},{"date":"2017-10-29","max":{"direction":323.66,"speed":9.64},"avg":{"direction":323.15,"speed":6.34},"min":{"direction":138.27,"speed":0.76}},{"date":"2017-10-30","max":{"direction":185.66,"speed":10.95},"avg":{"direction":140.17,"speed":6},"min":{"direction":310.07,"speed":0.57}},{"date":"2017-10-31","max":{"direction":173.89,"speed":7.8},"avg":{"direction":19.55,"speed":4.79},"min":{"direction":273.57,"speed":0.1}},{"date":"2017-11-01","max":{"direction":318.28,"speed":23.07},"avg":{"direction":331.69,"speed":10.26},"min":{"direction":338.05,"speed":3.2}},{"date":"2017-11-02","max":{"direction":342.31,"speed":9.97},"avg":{"direction":344.72,"speed":7.56},"min":{"direction":7.54,"speed":2.34}},{"date":"2017-11-03","max":{"direction":355.6,"speed":8.16},"avg":{"direction":304.53,"speed":6.17},"min":{"direction":339.47,"speed":5.06}},{"date":"2017-11-04","max":{"direction":257.71,"speed":11.36},"avg":{"direction":297.62,"speed":7.7},"min":{"direction":276.68,"speed":3.25}},{"date":"2017-11-05","max":{"direction":288.63,"speed":13.98},"avg":{"direction":298.59,"speed":10.4},"min":{"direction":318.53,"speed":7.57}},{"date":"2017-11-06","max":{"direction":296.28,"speed":11.15},"avg":{"direction":311.86,"speed":8.89},"min":{"direction":339.92,"speed":6.85}},{"date":"2017-11-07","max":{"direction":208.07,"speed":10.7},"avg":{"direction":278.82,"speed":8.4},"min":{"direction":320.78,"speed":3.92}}]},"primary":0}
     * server_time : 1508853261
     * api_status : active
     * tzshift : 28800
     * api_version : v2.2
     * unit : metric
     * location : [40.319077,116.632878]
     */
    @Transient
    private String status;
    @Transient
    private String lang;
    @Transient
    private ResultBean result;
    @Id
    private long server_time;
    @Transient
    private String api_status;
    @Transient
    private int tzshift;
    @Transient
    private String api_version;
    @Transient
    private String unit;
    @Transient
    private List<Double> location;

    @Property
    private String jsonString;

    @Generated(hash = 2072301913)
    public NewWeather(long server_time, String jsonString) {
        this.server_time = server_time;
        this.jsonString = jsonString;
    }

    @Generated(hash = 549911425)
    public NewWeather() {
    }

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

    public long getServer_time() {
        return server_time;
    }

    public void setServer_time(long server_time) {
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

    public String getJsonString() {
        return this.jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public static class ResultBean {
        /**
         * hourly : {"status":"ok","description":"晴，明天凌晨4点钟后转阴","skycon":[{"value":"CLEAR_NIGHT","datetime":"2017-10-24 21:00"},{"value":"CLEAR_NIGHT","datetime":"2017-10-24 22:00"},{"value":"CLEAR_NIGHT","datetime":"2017-10-24 23:00"},{"value":"CLEAR_NIGHT","datetime":"2017-10-25 00:00"},{"value":"CLEAR_NIGHT","datetime":"2017-10-25 01:00"},{"value":"CLEAR_NIGHT","datetime":"2017-10-25 02:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 03:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 04:00"},{"value":"CLOUDY","datetime":"2017-10-25 05:00"},{"value":"CLOUDY","datetime":"2017-10-25 06:00"},{"value":"CLOUDY","datetime":"2017-10-25 07:00"},{"value":"CLOUDY","datetime":"2017-10-25 08:00"},{"value":"CLOUDY","datetime":"2017-10-25 09:00"},{"value":"CLOUDY","datetime":"2017-10-25 10:00"},{"value":"CLOUDY","datetime":"2017-10-25 11:00"},{"value":"CLOUDY","datetime":"2017-10-25 12:00"},{"value":"CLOUDY","datetime":"2017-10-25 13:00"},{"value":"CLOUDY","datetime":"2017-10-25 14:00"},{"value":"CLOUDY","datetime":"2017-10-25 15:00"},{"value":"CLOUDY","datetime":"2017-10-25 16:00"},{"value":"CLOUDY","datetime":"2017-10-25 17:00"},{"value":"CLOUDY","datetime":"2017-10-25 18:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 19:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 20:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 21:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 22:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 23:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 00:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 01:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 02:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 03:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 04:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 05:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 06:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 07:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 08:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 09:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 10:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 11:00"},{"value":"CLEAR_DAY","datetime":"2017-10-26 12:00"},{"value":"CLEAR_DAY","datetime":"2017-10-26 13:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 14:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 15:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 16:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 17:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 18:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 19:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 20:00"}],"cloudrate":[{"value":0.01,"datetime":"2017-10-24 21:00"},{"value":0.02,"datetime":"2017-10-24 22:00"},{"value":0,"datetime":"2017-10-24 23:00"},{"value":0,"datetime":"2017-10-25 00:00"},{"value":0,"datetime":"2017-10-25 01:00"},{"value":0.03,"datetime":"2017-10-25 02:00"},{"value":0.27,"datetime":"2017-10-25 03:00"},{"value":0.57,"datetime":"2017-10-25 04:00"},{"value":0.81,"datetime":"2017-10-25 05:00"},{"value":0.92,"datetime":"2017-10-25 06:00"},{"value":0.92,"datetime":"2017-10-25 07:00"},{"value":0.9,"datetime":"2017-10-25 08:00"},{"value":0.92,"datetime":"2017-10-25 09:00"},{"value":0.96,"datetime":"2017-10-25 10:00"},{"value":1,"datetime":"2017-10-25 11:00"},{"value":1,"datetime":"2017-10-25 12:00"},{"value":1,"datetime":"2017-10-25 13:00"},{"value":1,"datetime":"2017-10-25 14:00"},{"value":1,"datetime":"2017-10-25 15:00"},{"value":1,"datetime":"2017-10-25 16:00"},{"value":0.97,"datetime":"2017-10-25 17:00"},{"value":0.86,"datetime":"2017-10-25 18:00"},{"value":0.71,"datetime":"2017-10-25 19:00"},{"value":0.58,"datetime":"2017-10-25 20:00"},{"value":0.48,"datetime":"2017-10-25 21:00"},{"value":0.43,"datetime":"2017-10-25 22:00"},{"value":0.42,"datetime":"2017-10-25 23:00"},{"value":0.43,"datetime":"2017-10-26 00:00"},{"value":0.45,"datetime":"2017-10-26 01:00"},{"value":0.46,"datetime":"2017-10-26 02:00"},{"value":0.45,"datetime":"2017-10-26 03:00"},{"value":0.43,"datetime":"2017-10-26 04:00"},{"value":0.41,"datetime":"2017-10-26 05:00"},{"value":0.4,"datetime":"2017-10-26 06:00"},{"value":0.39,"datetime":"2017-10-26 07:00"},{"value":0.37,"datetime":"2017-10-26 08:00"},{"value":0.33,"datetime":"2017-10-26 09:00"},{"value":0.28,"datetime":"2017-10-26 10:00"},{"value":0.23,"datetime":"2017-10-26 11:00"},{"value":0.19,"datetime":"2017-10-26 12:00"},{"value":0.17,"datetime":"2017-10-26 13:00"},{"value":0.21,"datetime":"2017-10-26 14:00"},{"value":0.29,"datetime":"2017-10-26 15:00"},{"value":0.38,"datetime":"2017-10-26 16:00"},{"value":0.43,"datetime":"2017-10-26 17:00"},{"value":0.41,"datetime":"2017-10-26 18:00"},{"value":0.32,"datetime":"2017-10-26 19:00"},{"value":0.22,"datetime":"2017-10-26 20:00"}],"aqi":[{"value":132,"datetime":"2017-10-24 21:00"},{"value":129,"datetime":"2017-10-24 22:00"},{"value":128,"datetime":"2017-10-24 23:00"},{"value":128,"datetime":"2017-10-25 00:00"},{"value":129,"datetime":"2017-10-25 01:00"},{"value":130,"datetime":"2017-10-25 02:00"},{"value":132,"datetime":"2017-10-25 03:00"},{"value":133,"datetime":"2017-10-25 04:00"},{"value":134,"datetime":"2017-10-25 05:00"},{"value":134,"datetime":"2017-10-25 06:00"},{"value":132,"datetime":"2017-10-25 07:00"},{"value":128,"datetime":"2017-10-25 08:00"},{"value":124,"datetime":"2017-10-25 09:00"},{"value":122,"datetime":"2017-10-25 10:00"},{"value":119,"datetime":"2017-10-25 11:00"},{"value":117,"datetime":"2017-10-25 12:00"},{"value":114,"datetime":"2017-10-25 13:00"},{"value":112,"datetime":"2017-10-25 14:00"},{"value":108,"datetime":"2017-10-25 15:00"},{"value":106,"datetime":"2017-10-25 16:00"},{"value":105,"datetime":"2017-10-25 17:00"},{"value":103,"datetime":"2017-10-25 18:00"},{"value":103,"datetime":"2017-10-25 19:00"},{"value":103,"datetime":"2017-10-25 20:00"},{"value":103,"datetime":"2017-10-25 21:00"},{"value":100,"datetime":"2017-10-25 22:00"},{"value":98,"datetime":"2017-10-25 23:00"},{"value":95,"datetime":"2017-10-26 00:00"},{"value":96,"datetime":"2017-10-26 01:00"},{"value":99,"datetime":"2017-10-26 02:00"},{"value":102,"datetime":"2017-10-26 03:00"},{"value":105,"datetime":"2017-10-26 04:00"},{"value":106,"datetime":"2017-10-26 05:00"},{"value":107,"datetime":"2017-10-26 06:00"},{"value":108,"datetime":"2017-10-26 07:00"},{"value":109,"datetime":"2017-10-26 08:00"},{"value":112,"datetime":"2017-10-26 09:00"},{"value":113,"datetime":"2017-10-26 10:00"},{"value":114,"datetime":"2017-10-26 11:00"},{"value":117,"datetime":"2017-10-26 12:00"},{"value":118,"datetime":"2017-10-26 13:00"},{"value":118,"datetime":"2017-10-26 14:00"},{"value":119,"datetime":"2017-10-26 15:00"},{"value":121,"datetime":"2017-10-26 16:00"},{"value":122,"datetime":"2017-10-26 17:00"},{"value":123,"datetime":"2017-10-26 18:00"},{"value":125,"datetime":"2017-10-26 19:00"},{"value":128,"datetime":"2017-10-26 20:00"}],"humidity":[{"value":0.58,"datetime":"2017-10-24 21:00"},{"value":0.59,"datetime":"2017-10-24 22:00"},{"value":0.61,"datetime":"2017-10-24 23:00"},{"value":0.62,"datetime":"2017-10-25 00:00"},{"value":0.63,"datetime":"2017-10-25 01:00"},{"value":0.65,"datetime":"2017-10-25 02:00"},{"value":0.66,"datetime":"2017-10-25 03:00"},{"value":0.66,"datetime":"2017-10-25 04:00"},{"value":0.67,"datetime":"2017-10-25 05:00"},{"value":0.67,"datetime":"2017-10-25 06:00"},{"value":0.66,"datetime":"2017-10-25 07:00"},{"value":0.64,"datetime":"2017-10-25 08:00"},{"value":0.59,"datetime":"2017-10-25 09:00"},{"value":0.54,"datetime":"2017-10-25 10:00"},{"value":0.5,"datetime":"2017-10-25 11:00"},{"value":0.47,"datetime":"2017-10-25 12:00"},{"value":0.47,"datetime":"2017-10-25 13:00"},{"value":0.49,"datetime":"2017-10-25 14:00"},{"value":0.51,"datetime":"2017-10-25 15:00"},{"value":0.54,"datetime":"2017-10-25 16:00"},{"value":0.57,"datetime":"2017-10-25 17:00"},{"value":0.6,"datetime":"2017-10-25 18:00"},{"value":0.62,"datetime":"2017-10-25 19:00"},{"value":0.64,"datetime":"2017-10-25 20:00"},{"value":0.65,"datetime":"2017-10-25 21:00"},{"value":0.66,"datetime":"2017-10-25 22:00"},{"value":0.66,"datetime":"2017-10-25 23:00"},{"value":0.67,"datetime":"2017-10-26 00:00"},{"value":0.68,"datetime":"2017-10-26 01:00"},{"value":0.69,"datetime":"2017-10-26 02:00"},{"value":0.7,"datetime":"2017-10-26 03:00"},{"value":0.72,"datetime":"2017-10-26 04:00"},{"value":0.73,"datetime":"2017-10-26 05:00"},{"value":0.73,"datetime":"2017-10-26 06:00"},{"value":0.72,"datetime":"2017-10-26 07:00"},{"value":0.69,"datetime":"2017-10-26 08:00"},{"value":0.64,"datetime":"2017-10-26 09:00"},{"value":0.58,"datetime":"2017-10-26 10:00"},{"value":0.53,"datetime":"2017-10-26 11:00"},{"value":0.5,"datetime":"2017-10-26 12:00"},{"value":0.5,"datetime":"2017-10-26 13:00"},{"value":0.51,"datetime":"2017-10-26 14:00"},{"value":0.54,"datetime":"2017-10-26 15:00"},{"value":0.58,"datetime":"2017-10-26 16:00"},{"value":0.61,"datetime":"2017-10-26 17:00"},{"value":0.64,"datetime":"2017-10-26 18:00"},{"value":0.66,"datetime":"2017-10-26 19:00"},{"value":0.67,"datetime":"2017-10-26 20:00"}],"pm25":[{"value":100,"datetime":"2017-10-24 21:00"},{"value":98,"datetime":"2017-10-24 22:00"},{"value":97,"datetime":"2017-10-24 23:00"},{"value":97,"datetime":"2017-10-25 00:00"},{"value":98,"datetime":"2017-10-25 01:00"},{"value":99,"datetime":"2017-10-25 02:00"},{"value":100,"datetime":"2017-10-25 03:00"},{"value":101,"datetime":"2017-10-25 04:00"},{"value":102,"datetime":"2017-10-25 05:00"},{"value":102,"datetime":"2017-10-25 06:00"},{"value":100,"datetime":"2017-10-25 07:00"},{"value":97,"datetime":"2017-10-25 08:00"},{"value":94,"datetime":"2017-10-25 09:00"},{"value":92,"datetime":"2017-10-25 10:00"},{"value":90,"datetime":"2017-10-25 11:00"},{"value":88,"datetime":"2017-10-25 12:00"},{"value":86,"datetime":"2017-10-25 13:00"},{"value":84,"datetime":"2017-10-25 14:00"},{"value":81,"datetime":"2017-10-25 15:00"},{"value":79,"datetime":"2017-10-25 16:00"},{"value":78,"datetime":"2017-10-25 17:00"},{"value":77,"datetime":"2017-10-25 18:00"},{"value":77,"datetime":"2017-10-25 19:00"},{"value":77,"datetime":"2017-10-25 20:00"},{"value":77,"datetime":"2017-10-25 21:00"},{"value":75,"datetime":"2017-10-25 22:00"},{"value":73,"datetime":"2017-10-25 23:00"},{"value":71,"datetime":"2017-10-26 00:00"},{"value":72,"datetime":"2017-10-26 01:00"},{"value":74,"datetime":"2017-10-26 02:00"},{"value":76,"datetime":"2017-10-26 03:00"},{"value":78,"datetime":"2017-10-26 04:00"},{"value":79,"datetime":"2017-10-26 05:00"},{"value":80,"datetime":"2017-10-26 06:00"},{"value":81,"datetime":"2017-10-26 07:00"},{"value":82,"datetime":"2017-10-26 08:00"},{"value":84,"datetime":"2017-10-26 09:00"},{"value":85,"datetime":"2017-10-26 10:00"},{"value":86,"datetime":"2017-10-26 11:00"},{"value":88,"datetime":"2017-10-26 12:00"},{"value":89,"datetime":"2017-10-26 13:00"},{"value":89,"datetime":"2017-10-26 14:00"},{"value":90,"datetime":"2017-10-26 15:00"},{"value":91,"datetime":"2017-10-26 16:00"},{"value":92,"datetime":"2017-10-26 17:00"},{"value":93,"datetime":"2017-10-26 18:00"},{"value":95,"datetime":"2017-10-26 19:00"},{"value":97,"datetime":"2017-10-26 20:00"}],"precipitation":[{"value":0,"datetime":"2017-10-24 21:00"},{"value":0,"datetime":"2017-10-24 22:00"},{"value":0,"datetime":"2017-10-24 23:00"},{"value":0,"datetime":"2017-10-25 00:00"},{"value":0,"datetime":"2017-10-25 01:00"},{"value":0,"datetime":"2017-10-25 02:00"},{"value":0,"datetime":"2017-10-25 03:00"},{"value":0,"datetime":"2017-10-25 04:00"},{"value":0,"datetime":"2017-10-25 05:00"},{"value":0,"datetime":"2017-10-25 06:00"},{"value":0,"datetime":"2017-10-25 07:00"},{"value":0,"datetime":"2017-10-25 08:00"},{"value":0,"datetime":"2017-10-25 09:00"},{"value":0,"datetime":"2017-10-25 10:00"},{"value":0,"datetime":"2017-10-25 11:00"},{"value":0,"datetime":"2017-10-25 12:00"},{"value":0,"datetime":"2017-10-25 13:00"},{"value":0,"datetime":"2017-10-25 14:00"},{"value":0,"datetime":"2017-10-25 15:00"},{"value":0,"datetime":"2017-10-25 16:00"},{"value":0,"datetime":"2017-10-25 17:00"},{"value":0,"datetime":"2017-10-25 18:00"},{"value":0,"datetime":"2017-10-25 19:00"},{"value":0,"datetime":"2017-10-25 20:00"},{"value":0,"datetime":"2017-10-25 21:00"},{"value":0,"datetime":"2017-10-25 22:00"},{"value":0,"datetime":"2017-10-25 23:00"},{"value":0,"datetime":"2017-10-26 00:00"},{"value":0,"datetime":"2017-10-26 01:00"},{"value":0,"datetime":"2017-10-26 02:00"},{"value":0,"datetime":"2017-10-26 03:00"},{"value":0,"datetime":"2017-10-26 04:00"},{"value":0,"datetime":"2017-10-26 05:00"},{"value":0,"datetime":"2017-10-26 06:00"},{"value":0,"datetime":"2017-10-26 07:00"},{"value":0,"datetime":"2017-10-26 08:00"},{"value":0,"datetime":"2017-10-26 09:00"},{"value":0,"datetime":"2017-10-26 10:00"},{"value":0,"datetime":"2017-10-26 11:00"},{"value":0,"datetime":"2017-10-26 12:00"},{"value":0,"datetime":"2017-10-26 13:00"},{"value":0,"datetime":"2017-10-26 14:00"},{"value":0,"datetime":"2017-10-26 15:00"},{"value":0,"datetime":"2017-10-26 16:00"},{"value":0,"datetime":"2017-10-26 17:00"},{"value":0,"datetime":"2017-10-26 18:00"},{"value":0,"datetime":"2017-10-26 19:00"},{"value":0,"datetime":"2017-10-26 20:00"}],"wind":[{"direction":62.34,"speed":3.16,"datetime":"2017-10-24 21:00"},{"direction":44.83,"speed":3.64,"datetime":"2017-10-24 22:00"},{"direction":33.4,"speed":4.14,"datetime":"2017-10-24 23:00"},{"direction":25.79,"speed":4.57,"datetime":"2017-10-25 00:00"},{"direction":20.35,"speed":4.92,"datetime":"2017-10-25 01:00"},{"direction":16.17,"speed":5.21,"datetime":"2017-10-25 02:00"},{"direction":12.71,"speed":5.47,"datetime":"2017-10-25 03:00"},{"direction":9.3,"speed":5.71,"datetime":"2017-10-25 04:00"},{"direction":5.31,"speed":5.92,"datetime":"2017-10-25 05:00"},{"direction":0.74,"speed":6.09,"datetime":"2017-10-25 06:00"},{"direction":357.38,"speed":5.92,"datetime":"2017-10-25 07:00"},{"direction":357.36,"speed":5.07,"datetime":"2017-10-25 08:00"},{"direction":5.86,"speed":3.33,"datetime":"2017-10-25 09:00"},{"direction":49.02,"speed":1.47,"datetime":"2017-10-25 10:00"},{"direction":133.81,"speed":2.23,"datetime":"2017-10-25 11:00"},{"direction":158.13,"speed":4.11,"datetime":"2017-10-25 12:00"},{"direction":168.64,"speed":5.7,"datetime":"2017-10-25 13:00"},{"direction":173.29,"speed":6.69,"datetime":"2017-10-25 14:00"},{"direction":173.27,"speed":6.87,"datetime":"2017-10-25 15:00"},{"direction":170.06,"speed":6.32,"datetime":"2017-10-25 16:00"},{"direction":163.98,"speed":5.2,"datetime":"2017-10-25 17:00"},{"direction":153.37,"speed":3.75,"datetime":"2017-10-25 18:00"},{"direction":131.01,"speed":2.43,"datetime":"2017-10-25 19:00"},{"direction":92.33,"speed":1.96,"datetime":"2017-10-25 20:00"},{"direction":65.51,"speed":2.27,"datetime":"2017-10-25 21:00"},{"direction":53.65,"speed":2.65,"datetime":"2017-10-25 22:00"},{"direction":46.26,"speed":2.88,"datetime":"2017-10-25 23:00"},{"direction":38.51,"speed":3.02,"datetime":"2017-10-26 00:00"},{"direction":30.86,"speed":3.11,"datetime":"2017-10-26 01:00"},{"direction":24.85,"speed":3.17,"datetime":"2017-10-26 02:00"},{"direction":21.38,"speed":3.19,"datetime":"2017-10-26 03:00"},{"direction":18.98,"speed":3.28,"datetime":"2017-10-26 04:00"},{"direction":16.12,"speed":3.58,"datetime":"2017-10-26 05:00"},{"direction":12.81,"speed":4.12,"datetime":"2017-10-26 06:00"},{"direction":11.35,"speed":4.38,"datetime":"2017-10-26 07:00"},{"direction":14,"speed":3.68,"datetime":"2017-10-26 08:00"},{"direction":34.83,"speed":1.72,"datetime":"2017-10-26 09:00"},{"direction":149.37,"speed":1.75,"datetime":"2017-10-26 10:00"},{"direction":176.17,"speed":4.26,"datetime":"2017-10-26 11:00"},{"direction":189.07,"speed":6.14,"datetime":"2017-10-26 12:00"},{"direction":198.29,"speed":7.24,"datetime":"2017-10-26 13:00"},{"direction":202.82,"speed":7.36,"datetime":"2017-10-26 14:00"},{"direction":200.31,"speed":6.3,"datetime":"2017-10-26 15:00"},{"direction":189.78,"speed":4.65,"datetime":"2017-10-26 16:00"},{"direction":169.81,"speed":3.2,"datetime":"2017-10-26 17:00"},{"direction":145.88,"speed":2.28,"datetime":"2017-10-26 18:00"},{"direction":116.15,"speed":1.58,"datetime":"2017-10-26 19:00"},{"direction":64.14,"speed":1.36,"datetime":"2017-10-26 20:00"}],"temperature":[{"value":11,"datetime":"2017-10-24 21:00"},{"value":11.13,"datetime":"2017-10-24 22:00"},{"value":10.47,"datetime":"2017-10-24 23:00"},{"value":9.8,"datetime":"2017-10-25 00:00"},{"value":8.33,"datetime":"2017-10-25 01:00"},{"value":7.57,"datetime":"2017-10-25 02:00"},{"value":7.2,"datetime":"2017-10-25 03:00"},{"value":7.1,"datetime":"2017-10-25 04:00"},{"value":7,"datetime":"2017-10-25 05:00"},{"value":8.6,"datetime":"2017-10-25 06:00"},{"value":9.4,"datetime":"2017-10-25 07:00"},{"value":10.1,"datetime":"2017-10-25 08:00"},{"value":10.7,"datetime":"2017-10-25 09:00"},{"value":11.3,"datetime":"2017-10-25 10:00"},{"value":11.9,"datetime":"2017-10-25 11:00"},{"value":12.7,"datetime":"2017-10-25 12:00"},{"value":13.5,"datetime":"2017-10-25 13:00"},{"value":15,"datetime":"2017-10-25 14:00"},{"value":14.9,"datetime":"2017-10-25 15:00"},{"value":14.8,"datetime":"2017-10-25 16:00"},{"value":14.4,"datetime":"2017-10-25 17:00"},{"value":13.8,"datetime":"2017-10-25 18:00"},{"value":13.5,"datetime":"2017-10-25 19:00"},{"value":12.14,"datetime":"2017-10-25 20:00"},{"value":11.11,"datetime":"2017-10-25 21:00"},{"value":10.29,"datetime":"2017-10-25 22:00"},{"value":9.61,"datetime":"2017-10-25 23:00"},{"value":9.02,"datetime":"2017-10-26 00:00"},{"value":8.73,"datetime":"2017-10-26 01:00"},{"value":8.46,"datetime":"2017-10-26 02:00"},{"value":8.24,"datetime":"2017-10-26 03:00"},{"value":9,"datetime":"2017-10-26 04:00"},{"value":8,"datetime":"2017-10-26 05:00"},{"value":10.85,"datetime":"2017-10-26 06:00"},{"value":11.76,"datetime":"2017-10-26 07:00"},{"value":12.56,"datetime":"2017-10-26 08:00"},{"value":13.25,"datetime":"2017-10-26 09:00"},{"value":13.89,"datetime":"2017-10-26 10:00"},{"value":14.58,"datetime":"2017-10-26 11:00"},{"value":15.38,"datetime":"2017-10-26 12:00"},{"value":18,"datetime":"2017-10-26 13:00"},{"value":17,"datetime":"2017-10-26 14:00"},{"value":17.62,"datetime":"2017-10-26 15:00"},{"value":17.21,"datetime":"2017-10-26 16:00"},{"value":16.61,"datetime":"2017-10-26 17:00"},{"value":15.84,"datetime":"2017-10-26 18:00"},{"value":14.95,"datetime":"2017-10-26 19:00"},{"value":13.32,"datetime":"2017-10-26 20:00"}]}
         * alert : {"status":"ok","content":[{"province":"北京","status":"预警中","code":"1202","description":"怀柔区气象台24日20时45分发布大雾黄色预警,预计今日后半夜至明日上午，怀柔区有大雾天气，能见度小于1千米，部分地区小于500米，请注意防范。","alertId":"11011641600000_20171024204500","city":"北京","pubtimestamp":1.5088491E9,"latlon":[40.324272,116.637122],"county":"怀柔","request_status":"ok","location":"北京北京怀柔","title":"怀柔区气象台发布大雾黄色预警[III/较大]","regionId":"101010500"}]}
         * minutely : {"status":"ok","description":"未来两小时不会下雨，放心出门吧","probability":[0,0,0,0],"datasource":"radar","precipitation_2h":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"precipitation":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}
         * daily : {"status":"ok","coldRisk":[{"index":"4","desc":"极易发","datetime":"2017-10-24"},{"index":"4","desc":"极易发","datetime":"2017-10-25"},{"index":"4","desc":"极易发","datetime":"2017-10-26"},{"index":"4","desc":"极易发","datetime":"2017-10-27"},{"index":"4","desc":"极易发","datetime":"2017-10-28"},{"index":"4","desc":"极易发","datetime":"2017-10-29"},{"index":"4","desc":"极易发","datetime":"2017-10-30"},{"index":"3","desc":"易发","datetime":"2017-10-31"},{"index":"4","desc":"极易发","datetime":"2017-11-01"},{"index":"4","desc":"极易发","datetime":"2017-11-02"},{"index":"4","desc":"极易发","datetime":"2017-11-03"},{"index":"4","desc":"极易发","datetime":"2017-11-04"},{"index":"4","desc":"极易发","datetime":"2017-11-05"},{"index":"3","desc":"易发","datetime":"2017-11-06"},{"index":"4","desc":"极易发","datetime":"2017-11-07"}],"temperature":[{"date":"2017-10-24","max":18,"avg":10.87,"min":4},{"date":"2017-10-25","max":15,"avg":11.03,"min":7},{"date":"2017-10-26","max":18,"avg":12.77,"min":8},{"date":"2017-10-27","max":20,"avg":13.71,"min":7},{"date":"2017-10-28","max":16,"avg":11.94,"min":4.4},{"date":"2017-10-29","max":13,"avg":7.54,"min":3},{"date":"2017-10-30","max":15,"avg":8.77,"min":3},{"date":"2017-10-31","max":13,"avg":9.23,"min":6},{"date":"2017-11-01","max":13,"avg":8.48,"min":4},{"date":"2017-11-02","max":13,"avg":7.04,"min":2},{"date":"2017-11-03","max":8,"avg":4.48,"min":0},{"date":"2017-11-04","max":9,"avg":5.49,"min":1},{"date":"2017-11-05","max":10,"avg":6.04,"min":2},{"date":"2017-11-06","max":10,"avg":5.81,"min":3},{"date":"2017-11-07","max":9,"avg":6.09,"min":1}],"skycon":[{"date":"2017-10-24","value":"CLEAR_NIGHT"},{"date":"2017-10-25","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-10-26","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-10-27","value":"CLEAR_DAY"},{"date":"2017-10-28","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-10-29","value":"CLEAR_DAY"},{"date":"2017-10-30","value":"CLEAR_DAY"},{"date":"2017-10-31","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-11-01","value":"CLEAR_DAY"},{"date":"2017-11-02","value":"CLEAR_DAY"},{"date":"2017-11-03","value":"CLEAR_DAY"},{"date":"2017-11-04","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-11-05","value":"CLEAR_DAY"},{"date":"2017-11-06","value":"CLEAR_DAY"},{"date":"2017-11-07","value":"CLEAR_DAY"}],"cloudrate":[{"date":"2017-10-24","max":0.02,"avg":0.01,"min":0},{"date":"2017-10-25","max":1,"avg":0.7,"min":0},{"date":"2017-10-26","max":0.46,"avg":0.31,"min":0},{"date":"2017-10-27","max":0,"avg":0,"min":0},{"date":"2017-10-28","max":0.96,"avg":0.26,"min":0},{"date":"2017-10-29","max":0,"avg":0,"min":0},{"date":"2017-10-30","max":0,"avg":0,"min":0},{"date":"2017-10-31","max":0.92,"avg":0.33,"min":0},{"date":"2017-11-01","max":0.31,"avg":0.08,"min":0},{"date":"2017-11-02","max":0.19,"avg":0.02,"min":0},{"date":"2017-11-03","max":0.26,"avg":0.11,"min":0},{"date":"2017-11-04","max":1,"avg":0.5,"min":0},{"date":"2017-11-05","max":0.03,"avg":0,"min":0},{"date":"2017-11-06","max":0,"avg":0,"min":0},{"date":"2017-11-07","max":0.01,"avg":0,"min":0}],"aqi":[{"date":"2017-10-24","max":132,"avg":129.67,"min":33},{"date":"2017-10-25","max":134,"avg":117.38,"min":98},{"date":"2017-10-26","max":128,"avg":113.88,"min":95},{"date":"2017-10-27","max":143,"avg":132.67,"min":121},{"date":"2017-10-28","max":141,"avg":74.96,"min":27},{"date":"2017-10-29","max":38,"avg":31.83,"min":26},{"date":"2017-10-30","max":64,"avg":46.33,"min":35},{"date":"2017-10-31","max":88,"avg":73.33,"min":63},{"date":"2017-11-01","max":71,"avg":56,"min":30},{"date":"2017-11-02","max":29,"avg":25.5,"min":19},{"date":"2017-11-03","max":42,"avg":30,"min":18},{"date":"2017-11-04","max":57,"avg":53.46,"min":42},{"date":"2017-11-05","max":66,"avg":56.46,"min":45},{"date":"2017-11-06","max":43,"avg":36.58,"min":29},{"date":"2017-11-07","max":52,"avg":34,"min":27}],"humidity":[{"date":"2017-10-24","max":0.65,"avg":0.59,"min":0.43},{"date":"2017-10-25","max":0.67,"avg":0.6,"min":0.47},{"date":"2017-10-26","max":0.73,"avg":0.64,"min":0.5},{"date":"2017-10-27","max":0.76,"avg":0.62,"min":0.41},{"date":"2017-10-28","max":0.68,"avg":0.33,"min":0.18},{"date":"2017-10-29","max":0.33,"avg":0.27,"min":0.17},{"date":"2017-10-30","max":0.37,"avg":0.31,"min":0.2},{"date":"2017-10-31","max":0.51,"avg":0.4,"min":0.3},{"date":"2017-11-01","max":0.54,"avg":0.39,"min":0.15},{"date":"2017-11-02","max":0.46,"avg":0.35,"min":0.23},{"date":"2017-11-03","max":0.35,"avg":0.25,"min":0.17},{"date":"2017-11-04","max":0.36,"avg":0.25,"min":0.13},{"date":"2017-11-05","max":0.28,"avg":0.21,"min":0.14},{"date":"2017-11-06","max":0.37,"avg":0.27,"min":0.17},{"date":"2017-11-07","max":0.42,"avg":0.31,"min":0.21}],"astro":[{"date":"2017-10-24","sunset":{"time":"17:21"},"sunrise":{"time":"06:33"}},{"date":"2017-10-25","sunset":{"time":"17:20"},"sunrise":{"time":"06:35"}},{"date":"2017-10-26","sunset":{"time":"17:18"},"sunrise":{"time":"06:36"}},{"date":"2017-10-27","sunset":{"time":"17:17"},"sunrise":{"time":"06:37"}},{"date":"2017-10-28","sunset":{"time":"17:16"},"sunrise":{"time":"06:38"}},{"date":"2017-10-29","sunset":{"time":"17:14"},"sunrise":{"time":"06:39"}},{"date":"2017-10-30","sunset":{"time":"17:13"},"sunrise":{"time":"06:40"}},{"date":"2017-10-31","sunset":{"time":"17:12"},"sunrise":{"time":"06:41"}},{"date":"2017-11-01","sunset":{"time":"17:11"},"sunrise":{"time":"06:42"}},{"date":"2017-11-02","sunset":{"time":"17:09"},"sunrise":{"time":"06:44"}},{"date":"2017-11-03","sunset":{"time":"17:08"},"sunrise":{"time":"06:45"}},{"date":"2017-11-04","sunset":{"time":"17:07"},"sunrise":{"time":"06:46"}},{"date":"2017-11-05","sunset":{"time":"17:06"},"sunrise":{"time":"06:47"}},{"date":"2017-11-06","sunset":{"time":"17:05"},"sunrise":{"time":"06:48"}},{"date":"2017-11-07","sunset":{"time":"17:04"},"sunrise":{"time":"06:49"}}],"ultraviolet":[{"index":"4","desc":"强","datetime":"2017-10-24"},{"index":"1","desc":"最弱","datetime":"2017-10-25"},{"index":"4","desc":"强","datetime":"2017-10-26"},{"index":"4","desc":"强","datetime":"2017-10-27"},{"index":"1","desc":"最弱","datetime":"2017-10-28"},{"index":"4","desc":"强","datetime":"2017-10-29"},{"index":"4","desc":"强","datetime":"2017-10-30"},{"index":"2","desc":"弱","datetime":"2017-10-31"},{"index":"4","desc":"强","datetime":"2017-11-01"},{"index":"4","desc":"强","datetime":"2017-11-02"},{"index":"4","desc":"强","datetime":"2017-11-03"},{"index":"1","desc":"最弱","datetime":"2017-11-04"},{"index":"4","desc":"强","datetime":"2017-11-05"},{"index":"4","desc":"强","datetime":"2017-11-06"},{"index":"4","desc":"强","datetime":"2017-11-07"}],"pm25":[{"date":"2017-10-24","max":100,"avg":98.33,"min":23},{"date":"2017-10-25","max":102,"avg":88.5,"min":73},{"date":"2017-10-26","max":97,"avg":85.71,"min":71},{"date":"2017-10-27","max":109,"avg":100.92,"min":91},{"date":"2017-10-28","max":108,"avg":55.08,"min":18},{"date":"2017-10-29","max":26,"avg":21.38,"min":17},{"date":"2017-10-30","max":46,"avg":32.21,"min":24},{"date":"2017-10-31","max":65,"avg":53.29,"min":45},{"date":"2017-11-01","max":51,"avg":39.71,"min":20},{"date":"2017-11-02","max":19,"avg":16.75,"min":12},{"date":"2017-11-03","max":29,"avg":20.12,"min":11},{"date":"2017-11-04","max":40,"avg":37.21,"min":29},{"date":"2017-11-05","max":47,"avg":39.83,"min":31},{"date":"2017-11-06","max":30,"avg":24.96,"min":19},{"date":"2017-11-07","max":36,"avg":22.92,"min":18}],"dressing":[{"index":"5","desc":"凉爽","datetime":"2017-10-24"},{"index":"5","desc":"凉爽","datetime":"2017-10-25"},{"index":"5","desc":"凉爽","datetime":"2017-10-26"},{"index":"5","desc":"凉爽","datetime":"2017-10-27"},{"index":"5","desc":"凉爽","datetime":"2017-10-28"},{"index":"6","desc":"冷","datetime":"2017-10-29"},{"index":"6","desc":"冷","datetime":"2017-10-30"},{"index":"6","desc":"冷","datetime":"2017-10-31"},{"index":"6","desc":"冷","datetime":"2017-11-01"},{"index":"6","desc":"冷","datetime":"2017-11-02"},{"index":"7","desc":"寒冷","datetime":"2017-11-03"},{"index":"6","desc":"冷","datetime":"2017-11-04"},{"index":"6","desc":"冷","datetime":"2017-11-05"},{"index":"6","desc":"冷","datetime":"2017-11-06"},{"index":"6","desc":"冷","datetime":"2017-11-07"}],"carWashing":[{"index":"2","desc":"较适宜","datetime":"2017-10-24"},{"index":"2","desc":"较适宜","datetime":"2017-10-25"},{"index":"2","desc":"较适宜","datetime":"2017-10-26"},{"index":"2","desc":"较适宜","datetime":"2017-10-27"},{"index":"2","desc":"较适宜","datetime":"2017-10-28"},{"index":"1","desc":"适宜","datetime":"2017-10-29"},{"index":"1","desc":"适宜","datetime":"2017-10-30"},{"index":"2","desc":"较适宜","datetime":"2017-10-31"},{"index":"1","desc":"适宜","datetime":"2017-11-01"},{"index":"1","desc":"适宜","datetime":"2017-11-02"},{"index":"1","desc":"适宜","datetime":"2017-11-03"},{"index":"1","desc":"适宜","datetime":"2017-11-04"},{"index":"1","desc":"适宜","datetime":"2017-11-05"},{"index":"1","desc":"适宜","datetime":"2017-11-06"},{"index":"1","desc":"适宜","datetime":"2017-11-07"}],"precipitation":[{"date":"2017-10-24","max":0,"avg":0,"min":0},{"date":"2017-10-25","max":0,"avg":0,"min":0},{"date":"2017-10-26","max":0,"avg":0,"min":0},{"date":"2017-10-27","max":0,"avg":0,"min":0},{"date":"2017-10-28","max":0,"avg":0,"min":0},{"date":"2017-10-29","max":0,"avg":0,"min":0},{"date":"2017-10-30","max":0,"avg":0,"min":0},{"date":"2017-10-31","max":0,"avg":0,"min":0},{"date":"2017-11-01","max":0,"avg":0,"min":0},{"date":"2017-11-02","max":0,"avg":0,"min":0},{"date":"2017-11-03","max":0,"avg":0,"min":0},{"date":"2017-11-04","max":0,"avg":0,"min":0},{"date":"2017-11-05","max":0,"avg":0,"min":0},{"date":"2017-11-06","max":0,"avg":0,"min":0},{"date":"2017-11-07","max":0,"avg":0,"min":0}],"wind":[{"date":"2017-10-24","max":{"direction":183.07,"speed":9.72},"avg":{"direction":135.67,"speed":4.6},"min":{"direction":118.89,"speed":1.31}},{"date":"2017-10-25","max":{"direction":173.27,"speed":6.87},"avg":{"direction":61.45,"speed":4.45},"min":{"direction":49.02,"speed":1.47}},{"date":"2017-10-26","max":{"direction":202.82,"speed":7.36},"avg":{"direction":89.92,"speed":3.75},"min":{"direction":64.14,"speed":1.36}},{"date":"2017-10-27","max":{"direction":182.74,"speed":9.58},"avg":{"direction":71.84,"speed":5.14},"min":{"direction":347.22,"speed":1.39}},{"date":"2017-10-28","max":{"direction":340.67,"speed":21.44},"avg":{"direction":340.2,"speed":14.11},"min":{"direction":356.62,"speed":6.36}},{"date":"2017-10-29","max":{"direction":323.66,"speed":9.64},"avg":{"direction":323.15,"speed":6.34},"min":{"direction":138.27,"speed":0.76}},{"date":"2017-10-30","max":{"direction":185.66,"speed":10.95},"avg":{"direction":140.17,"speed":6},"min":{"direction":310.07,"speed":0.57}},{"date":"2017-10-31","max":{"direction":173.89,"speed":7.8},"avg":{"direction":19.55,"speed":4.79},"min":{"direction":273.57,"speed":0.1}},{"date":"2017-11-01","max":{"direction":318.28,"speed":23.07},"avg":{"direction":331.69,"speed":10.26},"min":{"direction":338.05,"speed":3.2}},{"date":"2017-11-02","max":{"direction":342.31,"speed":9.97},"avg":{"direction":344.72,"speed":7.56},"min":{"direction":7.54,"speed":2.34}},{"date":"2017-11-03","max":{"direction":355.6,"speed":8.16},"avg":{"direction":304.53,"speed":6.17},"min":{"direction":339.47,"speed":5.06}},{"date":"2017-11-04","max":{"direction":257.71,"speed":11.36},"avg":{"direction":297.62,"speed":7.7},"min":{"direction":276.68,"speed":3.25}},{"date":"2017-11-05","max":{"direction":288.63,"speed":13.98},"avg":{"direction":298.59,"speed":10.4},"min":{"direction":318.53,"speed":7.57}},{"date":"2017-11-06","max":{"direction":296.28,"speed":11.15},"avg":{"direction":311.86,"speed":8.89},"min":{"direction":339.92,"speed":6.85}},{"date":"2017-11-07","max":{"direction":208.07,"speed":10.7},"avg":{"direction":278.82,"speed":8.4},"min":{"direction":320.78,"speed":3.92}}]}
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
             * description : 晴，明天凌晨4点钟后转阴
             * skycon : [{"value":"CLEAR_NIGHT","datetime":"2017-10-24 21:00"},{"value":"CLEAR_NIGHT","datetime":"2017-10-24 22:00"},{"value":"CLEAR_NIGHT","datetime":"2017-10-24 23:00"},{"value":"CLEAR_NIGHT","datetime":"2017-10-25 00:00"},{"value":"CLEAR_NIGHT","datetime":"2017-10-25 01:00"},{"value":"CLEAR_NIGHT","datetime":"2017-10-25 02:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 03:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 04:00"},{"value":"CLOUDY","datetime":"2017-10-25 05:00"},{"value":"CLOUDY","datetime":"2017-10-25 06:00"},{"value":"CLOUDY","datetime":"2017-10-25 07:00"},{"value":"CLOUDY","datetime":"2017-10-25 08:00"},{"value":"CLOUDY","datetime":"2017-10-25 09:00"},{"value":"CLOUDY","datetime":"2017-10-25 10:00"},{"value":"CLOUDY","datetime":"2017-10-25 11:00"},{"value":"CLOUDY","datetime":"2017-10-25 12:00"},{"value":"CLOUDY","datetime":"2017-10-25 13:00"},{"value":"CLOUDY","datetime":"2017-10-25 14:00"},{"value":"CLOUDY","datetime":"2017-10-25 15:00"},{"value":"CLOUDY","datetime":"2017-10-25 16:00"},{"value":"CLOUDY","datetime":"2017-10-25 17:00"},{"value":"CLOUDY","datetime":"2017-10-25 18:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 19:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 20:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 21:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 22:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-25 23:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 00:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 01:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 02:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 03:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 04:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 05:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 06:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 07:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 08:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 09:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 10:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 11:00"},{"value":"CLEAR_DAY","datetime":"2017-10-26 12:00"},{"value":"CLEAR_DAY","datetime":"2017-10-26 13:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 14:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 15:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2017-10-26 16:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 17:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 18:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 19:00"},{"value":"PARTLY_CLOUDY_NIGHT","datetime":"2017-10-26 20:00"}]
             * cloudrate : [{"value":0.01,"datetime":"2017-10-24 21:00"},{"value":0.02,"datetime":"2017-10-24 22:00"},{"value":0,"datetime":"2017-10-24 23:00"},{"value":0,"datetime":"2017-10-25 00:00"},{"value":0,"datetime":"2017-10-25 01:00"},{"value":0.03,"datetime":"2017-10-25 02:00"},{"value":0.27,"datetime":"2017-10-25 03:00"},{"value":0.57,"datetime":"2017-10-25 04:00"},{"value":0.81,"datetime":"2017-10-25 05:00"},{"value":0.92,"datetime":"2017-10-25 06:00"},{"value":0.92,"datetime":"2017-10-25 07:00"},{"value":0.9,"datetime":"2017-10-25 08:00"},{"value":0.92,"datetime":"2017-10-25 09:00"},{"value":0.96,"datetime":"2017-10-25 10:00"},{"value":1,"datetime":"2017-10-25 11:00"},{"value":1,"datetime":"2017-10-25 12:00"},{"value":1,"datetime":"2017-10-25 13:00"},{"value":1,"datetime":"2017-10-25 14:00"},{"value":1,"datetime":"2017-10-25 15:00"},{"value":1,"datetime":"2017-10-25 16:00"},{"value":0.97,"datetime":"2017-10-25 17:00"},{"value":0.86,"datetime":"2017-10-25 18:00"},{"value":0.71,"datetime":"2017-10-25 19:00"},{"value":0.58,"datetime":"2017-10-25 20:00"},{"value":0.48,"datetime":"2017-10-25 21:00"},{"value":0.43,"datetime":"2017-10-25 22:00"},{"value":0.42,"datetime":"2017-10-25 23:00"},{"value":0.43,"datetime":"2017-10-26 00:00"},{"value":0.45,"datetime":"2017-10-26 01:00"},{"value":0.46,"datetime":"2017-10-26 02:00"},{"value":0.45,"datetime":"2017-10-26 03:00"},{"value":0.43,"datetime":"2017-10-26 04:00"},{"value":0.41,"datetime":"2017-10-26 05:00"},{"value":0.4,"datetime":"2017-10-26 06:00"},{"value":0.39,"datetime":"2017-10-26 07:00"},{"value":0.37,"datetime":"2017-10-26 08:00"},{"value":0.33,"datetime":"2017-10-26 09:00"},{"value":0.28,"datetime":"2017-10-26 10:00"},{"value":0.23,"datetime":"2017-10-26 11:00"},{"value":0.19,"datetime":"2017-10-26 12:00"},{"value":0.17,"datetime":"2017-10-26 13:00"},{"value":0.21,"datetime":"2017-10-26 14:00"},{"value":0.29,"datetime":"2017-10-26 15:00"},{"value":0.38,"datetime":"2017-10-26 16:00"},{"value":0.43,"datetime":"2017-10-26 17:00"},{"value":0.41,"datetime":"2017-10-26 18:00"},{"value":0.32,"datetime":"2017-10-26 19:00"},{"value":0.22,"datetime":"2017-10-26 20:00"}]
             * aqi : [{"value":132,"datetime":"2017-10-24 21:00"},{"value":129,"datetime":"2017-10-24 22:00"},{"value":128,"datetime":"2017-10-24 23:00"},{"value":128,"datetime":"2017-10-25 00:00"},{"value":129,"datetime":"2017-10-25 01:00"},{"value":130,"datetime":"2017-10-25 02:00"},{"value":132,"datetime":"2017-10-25 03:00"},{"value":133,"datetime":"2017-10-25 04:00"},{"value":134,"datetime":"2017-10-25 05:00"},{"value":134,"datetime":"2017-10-25 06:00"},{"value":132,"datetime":"2017-10-25 07:00"},{"value":128,"datetime":"2017-10-25 08:00"},{"value":124,"datetime":"2017-10-25 09:00"},{"value":122,"datetime":"2017-10-25 10:00"},{"value":119,"datetime":"2017-10-25 11:00"},{"value":117,"datetime":"2017-10-25 12:00"},{"value":114,"datetime":"2017-10-25 13:00"},{"value":112,"datetime":"2017-10-25 14:00"},{"value":108,"datetime":"2017-10-25 15:00"},{"value":106,"datetime":"2017-10-25 16:00"},{"value":105,"datetime":"2017-10-25 17:00"},{"value":103,"datetime":"2017-10-25 18:00"},{"value":103,"datetime":"2017-10-25 19:00"},{"value":103,"datetime":"2017-10-25 20:00"},{"value":103,"datetime":"2017-10-25 21:00"},{"value":100,"datetime":"2017-10-25 22:00"},{"value":98,"datetime":"2017-10-25 23:00"},{"value":95,"datetime":"2017-10-26 00:00"},{"value":96,"datetime":"2017-10-26 01:00"},{"value":99,"datetime":"2017-10-26 02:00"},{"value":102,"datetime":"2017-10-26 03:00"},{"value":105,"datetime":"2017-10-26 04:00"},{"value":106,"datetime":"2017-10-26 05:00"},{"value":107,"datetime":"2017-10-26 06:00"},{"value":108,"datetime":"2017-10-26 07:00"},{"value":109,"datetime":"2017-10-26 08:00"},{"value":112,"datetime":"2017-10-26 09:00"},{"value":113,"datetime":"2017-10-26 10:00"},{"value":114,"datetime":"2017-10-26 11:00"},{"value":117,"datetime":"2017-10-26 12:00"},{"value":118,"datetime":"2017-10-26 13:00"},{"value":118,"datetime":"2017-10-26 14:00"},{"value":119,"datetime":"2017-10-26 15:00"},{"value":121,"datetime":"2017-10-26 16:00"},{"value":122,"datetime":"2017-10-26 17:00"},{"value":123,"datetime":"2017-10-26 18:00"},{"value":125,"datetime":"2017-10-26 19:00"},{"value":128,"datetime":"2017-10-26 20:00"}]
             * humidity : [{"value":0.58,"datetime":"2017-10-24 21:00"},{"value":0.59,"datetime":"2017-10-24 22:00"},{"value":0.61,"datetime":"2017-10-24 23:00"},{"value":0.62,"datetime":"2017-10-25 00:00"},{"value":0.63,"datetime":"2017-10-25 01:00"},{"value":0.65,"datetime":"2017-10-25 02:00"},{"value":0.66,"datetime":"2017-10-25 03:00"},{"value":0.66,"datetime":"2017-10-25 04:00"},{"value":0.67,"datetime":"2017-10-25 05:00"},{"value":0.67,"datetime":"2017-10-25 06:00"},{"value":0.66,"datetime":"2017-10-25 07:00"},{"value":0.64,"datetime":"2017-10-25 08:00"},{"value":0.59,"datetime":"2017-10-25 09:00"},{"value":0.54,"datetime":"2017-10-25 10:00"},{"value":0.5,"datetime":"2017-10-25 11:00"},{"value":0.47,"datetime":"2017-10-25 12:00"},{"value":0.47,"datetime":"2017-10-25 13:00"},{"value":0.49,"datetime":"2017-10-25 14:00"},{"value":0.51,"datetime":"2017-10-25 15:00"},{"value":0.54,"datetime":"2017-10-25 16:00"},{"value":0.57,"datetime":"2017-10-25 17:00"},{"value":0.6,"datetime":"2017-10-25 18:00"},{"value":0.62,"datetime":"2017-10-25 19:00"},{"value":0.64,"datetime":"2017-10-25 20:00"},{"value":0.65,"datetime":"2017-10-25 21:00"},{"value":0.66,"datetime":"2017-10-25 22:00"},{"value":0.66,"datetime":"2017-10-25 23:00"},{"value":0.67,"datetime":"2017-10-26 00:00"},{"value":0.68,"datetime":"2017-10-26 01:00"},{"value":0.69,"datetime":"2017-10-26 02:00"},{"value":0.7,"datetime":"2017-10-26 03:00"},{"value":0.72,"datetime":"2017-10-26 04:00"},{"value":0.73,"datetime":"2017-10-26 05:00"},{"value":0.73,"datetime":"2017-10-26 06:00"},{"value":0.72,"datetime":"2017-10-26 07:00"},{"value":0.69,"datetime":"2017-10-26 08:00"},{"value":0.64,"datetime":"2017-10-26 09:00"},{"value":0.58,"datetime":"2017-10-26 10:00"},{"value":0.53,"datetime":"2017-10-26 11:00"},{"value":0.5,"datetime":"2017-10-26 12:00"},{"value":0.5,"datetime":"2017-10-26 13:00"},{"value":0.51,"datetime":"2017-10-26 14:00"},{"value":0.54,"datetime":"2017-10-26 15:00"},{"value":0.58,"datetime":"2017-10-26 16:00"},{"value":0.61,"datetime":"2017-10-26 17:00"},{"value":0.64,"datetime":"2017-10-26 18:00"},{"value":0.66,"datetime":"2017-10-26 19:00"},{"value":0.67,"datetime":"2017-10-26 20:00"}]
             * pm25 : [{"value":100,"datetime":"2017-10-24 21:00"},{"value":98,"datetime":"2017-10-24 22:00"},{"value":97,"datetime":"2017-10-24 23:00"},{"value":97,"datetime":"2017-10-25 00:00"},{"value":98,"datetime":"2017-10-25 01:00"},{"value":99,"datetime":"2017-10-25 02:00"},{"value":100,"datetime":"2017-10-25 03:00"},{"value":101,"datetime":"2017-10-25 04:00"},{"value":102,"datetime":"2017-10-25 05:00"},{"value":102,"datetime":"2017-10-25 06:00"},{"value":100,"datetime":"2017-10-25 07:00"},{"value":97,"datetime":"2017-10-25 08:00"},{"value":94,"datetime":"2017-10-25 09:00"},{"value":92,"datetime":"2017-10-25 10:00"},{"value":90,"datetime":"2017-10-25 11:00"},{"value":88,"datetime":"2017-10-25 12:00"},{"value":86,"datetime":"2017-10-25 13:00"},{"value":84,"datetime":"2017-10-25 14:00"},{"value":81,"datetime":"2017-10-25 15:00"},{"value":79,"datetime":"2017-10-25 16:00"},{"value":78,"datetime":"2017-10-25 17:00"},{"value":77,"datetime":"2017-10-25 18:00"},{"value":77,"datetime":"2017-10-25 19:00"},{"value":77,"datetime":"2017-10-25 20:00"},{"value":77,"datetime":"2017-10-25 21:00"},{"value":75,"datetime":"2017-10-25 22:00"},{"value":73,"datetime":"2017-10-25 23:00"},{"value":71,"datetime":"2017-10-26 00:00"},{"value":72,"datetime":"2017-10-26 01:00"},{"value":74,"datetime":"2017-10-26 02:00"},{"value":76,"datetime":"2017-10-26 03:00"},{"value":78,"datetime":"2017-10-26 04:00"},{"value":79,"datetime":"2017-10-26 05:00"},{"value":80,"datetime":"2017-10-26 06:00"},{"value":81,"datetime":"2017-10-26 07:00"},{"value":82,"datetime":"2017-10-26 08:00"},{"value":84,"datetime":"2017-10-26 09:00"},{"value":85,"datetime":"2017-10-26 10:00"},{"value":86,"datetime":"2017-10-26 11:00"},{"value":88,"datetime":"2017-10-26 12:00"},{"value":89,"datetime":"2017-10-26 13:00"},{"value":89,"datetime":"2017-10-26 14:00"},{"value":90,"datetime":"2017-10-26 15:00"},{"value":91,"datetime":"2017-10-26 16:00"},{"value":92,"datetime":"2017-10-26 17:00"},{"value":93,"datetime":"2017-10-26 18:00"},{"value":95,"datetime":"2017-10-26 19:00"},{"value":97,"datetime":"2017-10-26 20:00"}]
             * precipitation : [{"value":0,"datetime":"2017-10-24 21:00"},{"value":0,"datetime":"2017-10-24 22:00"},{"value":0,"datetime":"2017-10-24 23:00"},{"value":0,"datetime":"2017-10-25 00:00"},{"value":0,"datetime":"2017-10-25 01:00"},{"value":0,"datetime":"2017-10-25 02:00"},{"value":0,"datetime":"2017-10-25 03:00"},{"value":0,"datetime":"2017-10-25 04:00"},{"value":0,"datetime":"2017-10-25 05:00"},{"value":0,"datetime":"2017-10-25 06:00"},{"value":0,"datetime":"2017-10-25 07:00"},{"value":0,"datetime":"2017-10-25 08:00"},{"value":0,"datetime":"2017-10-25 09:00"},{"value":0,"datetime":"2017-10-25 10:00"},{"value":0,"datetime":"2017-10-25 11:00"},{"value":0,"datetime":"2017-10-25 12:00"},{"value":0,"datetime":"2017-10-25 13:00"},{"value":0,"datetime":"2017-10-25 14:00"},{"value":0,"datetime":"2017-10-25 15:00"},{"value":0,"datetime":"2017-10-25 16:00"},{"value":0,"datetime":"2017-10-25 17:00"},{"value":0,"datetime":"2017-10-25 18:00"},{"value":0,"datetime":"2017-10-25 19:00"},{"value":0,"datetime":"2017-10-25 20:00"},{"value":0,"datetime":"2017-10-25 21:00"},{"value":0,"datetime":"2017-10-25 22:00"},{"value":0,"datetime":"2017-10-25 23:00"},{"value":0,"datetime":"2017-10-26 00:00"},{"value":0,"datetime":"2017-10-26 01:00"},{"value":0,"datetime":"2017-10-26 02:00"},{"value":0,"datetime":"2017-10-26 03:00"},{"value":0,"datetime":"2017-10-26 04:00"},{"value":0,"datetime":"2017-10-26 05:00"},{"value":0,"datetime":"2017-10-26 06:00"},{"value":0,"datetime":"2017-10-26 07:00"},{"value":0,"datetime":"2017-10-26 08:00"},{"value":0,"datetime":"2017-10-26 09:00"},{"value":0,"datetime":"2017-10-26 10:00"},{"value":0,"datetime":"2017-10-26 11:00"},{"value":0,"datetime":"2017-10-26 12:00"},{"value":0,"datetime":"2017-10-26 13:00"},{"value":0,"datetime":"2017-10-26 14:00"},{"value":0,"datetime":"2017-10-26 15:00"},{"value":0,"datetime":"2017-10-26 16:00"},{"value":0,"datetime":"2017-10-26 17:00"},{"value":0,"datetime":"2017-10-26 18:00"},{"value":0,"datetime":"2017-10-26 19:00"},{"value":0,"datetime":"2017-10-26 20:00"}]
             * wind : [{"direction":62.34,"speed":3.16,"datetime":"2017-10-24 21:00"},{"direction":44.83,"speed":3.64,"datetime":"2017-10-24 22:00"},{"direction":33.4,"speed":4.14,"datetime":"2017-10-24 23:00"},{"direction":25.79,"speed":4.57,"datetime":"2017-10-25 00:00"},{"direction":20.35,"speed":4.92,"datetime":"2017-10-25 01:00"},{"direction":16.17,"speed":5.21,"datetime":"2017-10-25 02:00"},{"direction":12.71,"speed":5.47,"datetime":"2017-10-25 03:00"},{"direction":9.3,"speed":5.71,"datetime":"2017-10-25 04:00"},{"direction":5.31,"speed":5.92,"datetime":"2017-10-25 05:00"},{"direction":0.74,"speed":6.09,"datetime":"2017-10-25 06:00"},{"direction":357.38,"speed":5.92,"datetime":"2017-10-25 07:00"},{"direction":357.36,"speed":5.07,"datetime":"2017-10-25 08:00"},{"direction":5.86,"speed":3.33,"datetime":"2017-10-25 09:00"},{"direction":49.02,"speed":1.47,"datetime":"2017-10-25 10:00"},{"direction":133.81,"speed":2.23,"datetime":"2017-10-25 11:00"},{"direction":158.13,"speed":4.11,"datetime":"2017-10-25 12:00"},{"direction":168.64,"speed":5.7,"datetime":"2017-10-25 13:00"},{"direction":173.29,"speed":6.69,"datetime":"2017-10-25 14:00"},{"direction":173.27,"speed":6.87,"datetime":"2017-10-25 15:00"},{"direction":170.06,"speed":6.32,"datetime":"2017-10-25 16:00"},{"direction":163.98,"speed":5.2,"datetime":"2017-10-25 17:00"},{"direction":153.37,"speed":3.75,"datetime":"2017-10-25 18:00"},{"direction":131.01,"speed":2.43,"datetime":"2017-10-25 19:00"},{"direction":92.33,"speed":1.96,"datetime":"2017-10-25 20:00"},{"direction":65.51,"speed":2.27,"datetime":"2017-10-25 21:00"},{"direction":53.65,"speed":2.65,"datetime":"2017-10-25 22:00"},{"direction":46.26,"speed":2.88,"datetime":"2017-10-25 23:00"},{"direction":38.51,"speed":3.02,"datetime":"2017-10-26 00:00"},{"direction":30.86,"speed":3.11,"datetime":"2017-10-26 01:00"},{"direction":24.85,"speed":3.17,"datetime":"2017-10-26 02:00"},{"direction":21.38,"speed":3.19,"datetime":"2017-10-26 03:00"},{"direction":18.98,"speed":3.28,"datetime":"2017-10-26 04:00"},{"direction":16.12,"speed":3.58,"datetime":"2017-10-26 05:00"},{"direction":12.81,"speed":4.12,"datetime":"2017-10-26 06:00"},{"direction":11.35,"speed":4.38,"datetime":"2017-10-26 07:00"},{"direction":14,"speed":3.68,"datetime":"2017-10-26 08:00"},{"direction":34.83,"speed":1.72,"datetime":"2017-10-26 09:00"},{"direction":149.37,"speed":1.75,"datetime":"2017-10-26 10:00"},{"direction":176.17,"speed":4.26,"datetime":"2017-10-26 11:00"},{"direction":189.07,"speed":6.14,"datetime":"2017-10-26 12:00"},{"direction":198.29,"speed":7.24,"datetime":"2017-10-26 13:00"},{"direction":202.82,"speed":7.36,"datetime":"2017-10-26 14:00"},{"direction":200.31,"speed":6.3,"datetime":"2017-10-26 15:00"},{"direction":189.78,"speed":4.65,"datetime":"2017-10-26 16:00"},{"direction":169.81,"speed":3.2,"datetime":"2017-10-26 17:00"},{"direction":145.88,"speed":2.28,"datetime":"2017-10-26 18:00"},{"direction":116.15,"speed":1.58,"datetime":"2017-10-26 19:00"},{"direction":64.14,"speed":1.36,"datetime":"2017-10-26 20:00"}]
             * temperature : [{"value":11,"datetime":"2017-10-24 21:00"},{"value":11.13,"datetime":"2017-10-24 22:00"},{"value":10.47,"datetime":"2017-10-24 23:00"},{"value":9.8,"datetime":"2017-10-25 00:00"},{"value":8.33,"datetime":"2017-10-25 01:00"},{"value":7.57,"datetime":"2017-10-25 02:00"},{"value":7.2,"datetime":"2017-10-25 03:00"},{"value":7.1,"datetime":"2017-10-25 04:00"},{"value":7,"datetime":"2017-10-25 05:00"},{"value":8.6,"datetime":"2017-10-25 06:00"},{"value":9.4,"datetime":"2017-10-25 07:00"},{"value":10.1,"datetime":"2017-10-25 08:00"},{"value":10.7,"datetime":"2017-10-25 09:00"},{"value":11.3,"datetime":"2017-10-25 10:00"},{"value":11.9,"datetime":"2017-10-25 11:00"},{"value":12.7,"datetime":"2017-10-25 12:00"},{"value":13.5,"datetime":"2017-10-25 13:00"},{"value":15,"datetime":"2017-10-25 14:00"},{"value":14.9,"datetime":"2017-10-25 15:00"},{"value":14.8,"datetime":"2017-10-25 16:00"},{"value":14.4,"datetime":"2017-10-25 17:00"},{"value":13.8,"datetime":"2017-10-25 18:00"},{"value":13.5,"datetime":"2017-10-25 19:00"},{"value":12.14,"datetime":"2017-10-25 20:00"},{"value":11.11,"datetime":"2017-10-25 21:00"},{"value":10.29,"datetime":"2017-10-25 22:00"},{"value":9.61,"datetime":"2017-10-25 23:00"},{"value":9.02,"datetime":"2017-10-26 00:00"},{"value":8.73,"datetime":"2017-10-26 01:00"},{"value":8.46,"datetime":"2017-10-26 02:00"},{"value":8.24,"datetime":"2017-10-26 03:00"},{"value":9,"datetime":"2017-10-26 04:00"},{"value":8,"datetime":"2017-10-26 05:00"},{"value":10.85,"datetime":"2017-10-26 06:00"},{"value":11.76,"datetime":"2017-10-26 07:00"},{"value":12.56,"datetime":"2017-10-26 08:00"},{"value":13.25,"datetime":"2017-10-26 09:00"},{"value":13.89,"datetime":"2017-10-26 10:00"},{"value":14.58,"datetime":"2017-10-26 11:00"},{"value":15.38,"datetime":"2017-10-26 12:00"},{"value":18,"datetime":"2017-10-26 13:00"},{"value":17,"datetime":"2017-10-26 14:00"},{"value":17.62,"datetime":"2017-10-26 15:00"},{"value":17.21,"datetime":"2017-10-26 16:00"},{"value":16.61,"datetime":"2017-10-26 17:00"},{"value":15.84,"datetime":"2017-10-26 18:00"},{"value":14.95,"datetime":"2017-10-26 19:00"},{"value":13.32,"datetime":"2017-10-26 20:00"}]
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
                 * value : CLEAR_NIGHT
                 * datetime : 2017-10-24 21:00
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
                 * value : 0.01
                 * datetime : 2017-10-24 21:00
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
                 * value : 132.0
                 * datetime : 2017-10-24 21:00
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
                 * value : 0.58
                 * datetime : 2017-10-24 21:00
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
                 * value : 100.0
                 * datetime : 2017-10-24 21:00
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
                 * datetime : 2017-10-24 21:00
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
                 * direction : 62.34
                 * speed : 3.16
                 * datetime : 2017-10-24 21:00
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
                 * value : 11.0
                 * datetime : 2017-10-24 21:00
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
             * content : [{"province":"北京","status":"预警中","code":"1202","description":"怀柔区气象台24日20时45分发布大雾黄色预警,预计今日后半夜至明日上午，怀柔区有大雾天气，能见度小于1千米，部分地区小于500米，请注意防范。","alertId":"11011641600000_20171024204500","city":"北京","pubtimestamp":1.5088491E9,"latlon":[40.324272,116.637122],"county":"怀柔","request_status":"ok","location":"北京北京怀柔","title":"怀柔区气象台发布大雾黄色预警[III/较大]","regionId":"101010500"}]
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
                 * province : 北京
                 * status : 预警中
                 * code : 1202
                 * description : 怀柔区气象台24日20时45分发布大雾黄色预警,预计今日后半夜至明日上午，怀柔区有大雾天气，能见度小于1千米，部分地区小于500米，请注意防范。
                 * alertId : 11011641600000_20171024204500
                 * city : 北京
                 * pubtimestamp : 1.5088491E9
                 * latlon : [40.324272,116.637122]
                 * county : 怀柔
                 * request_status : ok
                 * location : 北京北京怀柔
                 * title : 怀柔区气象台发布大雾黄色预警[III/较大]
                 * regionId : 101010500
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
             * description : 未来两小时不会下雨，放心出门吧
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
             * coldRisk : [{"index":"4","desc":"极易发","datetime":"2017-10-24"},{"index":"4","desc":"极易发","datetime":"2017-10-25"},{"index":"4","desc":"极易发","datetime":"2017-10-26"},{"index":"4","desc":"极易发","datetime":"2017-10-27"},{"index":"4","desc":"极易发","datetime":"2017-10-28"},{"index":"4","desc":"极易发","datetime":"2017-10-29"},{"index":"4","desc":"极易发","datetime":"2017-10-30"},{"index":"3","desc":"易发","datetime":"2017-10-31"},{"index":"4","desc":"极易发","datetime":"2017-11-01"},{"index":"4","desc":"极易发","datetime":"2017-11-02"},{"index":"4","desc":"极易发","datetime":"2017-11-03"},{"index":"4","desc":"极易发","datetime":"2017-11-04"},{"index":"4","desc":"极易发","datetime":"2017-11-05"},{"index":"3","desc":"易发","datetime":"2017-11-06"},{"index":"4","desc":"极易发","datetime":"2017-11-07"}]
             * temperature : [{"date":"2017-10-24","max":18,"avg":10.87,"min":4},{"date":"2017-10-25","max":15,"avg":11.03,"min":7},{"date":"2017-10-26","max":18,"avg":12.77,"min":8},{"date":"2017-10-27","max":20,"avg":13.71,"min":7},{"date":"2017-10-28","max":16,"avg":11.94,"min":4.4},{"date":"2017-10-29","max":13,"avg":7.54,"min":3},{"date":"2017-10-30","max":15,"avg":8.77,"min":3},{"date":"2017-10-31","max":13,"avg":9.23,"min":6},{"date":"2017-11-01","max":13,"avg":8.48,"min":4},{"date":"2017-11-02","max":13,"avg":7.04,"min":2},{"date":"2017-11-03","max":8,"avg":4.48,"min":0},{"date":"2017-11-04","max":9,"avg":5.49,"min":1},{"date":"2017-11-05","max":10,"avg":6.04,"min":2},{"date":"2017-11-06","max":10,"avg":5.81,"min":3},{"date":"2017-11-07","max":9,"avg":6.09,"min":1}]
             * skycon : [{"date":"2017-10-24","value":"CLEAR_NIGHT"},{"date":"2017-10-25","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-10-26","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-10-27","value":"CLEAR_DAY"},{"date":"2017-10-28","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-10-29","value":"CLEAR_DAY"},{"date":"2017-10-30","value":"CLEAR_DAY"},{"date":"2017-10-31","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-11-01","value":"CLEAR_DAY"},{"date":"2017-11-02","value":"CLEAR_DAY"},{"date":"2017-11-03","value":"CLEAR_DAY"},{"date":"2017-11-04","value":"PARTLY_CLOUDY_DAY"},{"date":"2017-11-05","value":"CLEAR_DAY"},{"date":"2017-11-06","value":"CLEAR_DAY"},{"date":"2017-11-07","value":"CLEAR_DAY"}]
             * cloudrate : [{"date":"2017-10-24","max":0.02,"avg":0.01,"min":0},{"date":"2017-10-25","max":1,"avg":0.7,"min":0},{"date":"2017-10-26","max":0.46,"avg":0.31,"min":0},{"date":"2017-10-27","max":0,"avg":0,"min":0},{"date":"2017-10-28","max":0.96,"avg":0.26,"min":0},{"date":"2017-10-29","max":0,"avg":0,"min":0},{"date":"2017-10-30","max":0,"avg":0,"min":0},{"date":"2017-10-31","max":0.92,"avg":0.33,"min":0},{"date":"2017-11-01","max":0.31,"avg":0.08,"min":0},{"date":"2017-11-02","max":0.19,"avg":0.02,"min":0},{"date":"2017-11-03","max":0.26,"avg":0.11,"min":0},{"date":"2017-11-04","max":1,"avg":0.5,"min":0},{"date":"2017-11-05","max":0.03,"avg":0,"min":0},{"date":"2017-11-06","max":0,"avg":0,"min":0},{"date":"2017-11-07","max":0.01,"avg":0,"min":0}]
             * aqi : [{"date":"2017-10-24","max":132,"avg":129.67,"min":33},{"date":"2017-10-25","max":134,"avg":117.38,"min":98},{"date":"2017-10-26","max":128,"avg":113.88,"min":95},{"date":"2017-10-27","max":143,"avg":132.67,"min":121},{"date":"2017-10-28","max":141,"avg":74.96,"min":27},{"date":"2017-10-29","max":38,"avg":31.83,"min":26},{"date":"2017-10-30","max":64,"avg":46.33,"min":35},{"date":"2017-10-31","max":88,"avg":73.33,"min":63},{"date":"2017-11-01","max":71,"avg":56,"min":30},{"date":"2017-11-02","max":29,"avg":25.5,"min":19},{"date":"2017-11-03","max":42,"avg":30,"min":18},{"date":"2017-11-04","max":57,"avg":53.46,"min":42},{"date":"2017-11-05","max":66,"avg":56.46,"min":45},{"date":"2017-11-06","max":43,"avg":36.58,"min":29},{"date":"2017-11-07","max":52,"avg":34,"min":27}]
             * humidity : [{"date":"2017-10-24","max":0.65,"avg":0.59,"min":0.43},{"date":"2017-10-25","max":0.67,"avg":0.6,"min":0.47},{"date":"2017-10-26","max":0.73,"avg":0.64,"min":0.5},{"date":"2017-10-27","max":0.76,"avg":0.62,"min":0.41},{"date":"2017-10-28","max":0.68,"avg":0.33,"min":0.18},{"date":"2017-10-29","max":0.33,"avg":0.27,"min":0.17},{"date":"2017-10-30","max":0.37,"avg":0.31,"min":0.2},{"date":"2017-10-31","max":0.51,"avg":0.4,"min":0.3},{"date":"2017-11-01","max":0.54,"avg":0.39,"min":0.15},{"date":"2017-11-02","max":0.46,"avg":0.35,"min":0.23},{"date":"2017-11-03","max":0.35,"avg":0.25,"min":0.17},{"date":"2017-11-04","max":0.36,"avg":0.25,"min":0.13},{"date":"2017-11-05","max":0.28,"avg":0.21,"min":0.14},{"date":"2017-11-06","max":0.37,"avg":0.27,"min":0.17},{"date":"2017-11-07","max":0.42,"avg":0.31,"min":0.21}]
             * astro : [{"date":"2017-10-24","sunset":{"time":"17:21"},"sunrise":{"time":"06:33"}},{"date":"2017-10-25","sunset":{"time":"17:20"},"sunrise":{"time":"06:35"}},{"date":"2017-10-26","sunset":{"time":"17:18"},"sunrise":{"time":"06:36"}},{"date":"2017-10-27","sunset":{"time":"17:17"},"sunrise":{"time":"06:37"}},{"date":"2017-10-28","sunset":{"time":"17:16"},"sunrise":{"time":"06:38"}},{"date":"2017-10-29","sunset":{"time":"17:14"},"sunrise":{"time":"06:39"}},{"date":"2017-10-30","sunset":{"time":"17:13"},"sunrise":{"time":"06:40"}},{"date":"2017-10-31","sunset":{"time":"17:12"},"sunrise":{"time":"06:41"}},{"date":"2017-11-01","sunset":{"time":"17:11"},"sunrise":{"time":"06:42"}},{"date":"2017-11-02","sunset":{"time":"17:09"},"sunrise":{"time":"06:44"}},{"date":"2017-11-03","sunset":{"time":"17:08"},"sunrise":{"time":"06:45"}},{"date":"2017-11-04","sunset":{"time":"17:07"},"sunrise":{"time":"06:46"}},{"date":"2017-11-05","sunset":{"time":"17:06"},"sunrise":{"time":"06:47"}},{"date":"2017-11-06","sunset":{"time":"17:05"},"sunrise":{"time":"06:48"}},{"date":"2017-11-07","sunset":{"time":"17:04"},"sunrise":{"time":"06:49"}}]
             * ultraviolet : [{"index":"4","desc":"强","datetime":"2017-10-24"},{"index":"1","desc":"最弱","datetime":"2017-10-25"},{"index":"4","desc":"强","datetime":"2017-10-26"},{"index":"4","desc":"强","datetime":"2017-10-27"},{"index":"1","desc":"最弱","datetime":"2017-10-28"},{"index":"4","desc":"强","datetime":"2017-10-29"},{"index":"4","desc":"强","datetime":"2017-10-30"},{"index":"2","desc":"弱","datetime":"2017-10-31"},{"index":"4","desc":"强","datetime":"2017-11-01"},{"index":"4","desc":"强","datetime":"2017-11-02"},{"index":"4","desc":"强","datetime":"2017-11-03"},{"index":"1","desc":"最弱","datetime":"2017-11-04"},{"index":"4","desc":"强","datetime":"2017-11-05"},{"index":"4","desc":"强","datetime":"2017-11-06"},{"index":"4","desc":"强","datetime":"2017-11-07"}]
             * pm25 : [{"date":"2017-10-24","max":100,"avg":98.33,"min":23},{"date":"2017-10-25","max":102,"avg":88.5,"min":73},{"date":"2017-10-26","max":97,"avg":85.71,"min":71},{"date":"2017-10-27","max":109,"avg":100.92,"min":91},{"date":"2017-10-28","max":108,"avg":55.08,"min":18},{"date":"2017-10-29","max":26,"avg":21.38,"min":17},{"date":"2017-10-30","max":46,"avg":32.21,"min":24},{"date":"2017-10-31","max":65,"avg":53.29,"min":45},{"date":"2017-11-01","max":51,"avg":39.71,"min":20},{"date":"2017-11-02","max":19,"avg":16.75,"min":12},{"date":"2017-11-03","max":29,"avg":20.12,"min":11},{"date":"2017-11-04","max":40,"avg":37.21,"min":29},{"date":"2017-11-05","max":47,"avg":39.83,"min":31},{"date":"2017-11-06","max":30,"avg":24.96,"min":19},{"date":"2017-11-07","max":36,"avg":22.92,"min":18}]
             * dressing : [{"index":"5","desc":"凉爽","datetime":"2017-10-24"},{"index":"5","desc":"凉爽","datetime":"2017-10-25"},{"index":"5","desc":"凉爽","datetime":"2017-10-26"},{"index":"5","desc":"凉爽","datetime":"2017-10-27"},{"index":"5","desc":"凉爽","datetime":"2017-10-28"},{"index":"6","desc":"冷","datetime":"2017-10-29"},{"index":"6","desc":"冷","datetime":"2017-10-30"},{"index":"6","desc":"冷","datetime":"2017-10-31"},{"index":"6","desc":"冷","datetime":"2017-11-01"},{"index":"6","desc":"冷","datetime":"2017-11-02"},{"index":"7","desc":"寒冷","datetime":"2017-11-03"},{"index":"6","desc":"冷","datetime":"2017-11-04"},{"index":"6","desc":"冷","datetime":"2017-11-05"},{"index":"6","desc":"冷","datetime":"2017-11-06"},{"index":"6","desc":"冷","datetime":"2017-11-07"}]
             * carWashing : [{"index":"2","desc":"较适宜","datetime":"2017-10-24"},{"index":"2","desc":"较适宜","datetime":"2017-10-25"},{"index":"2","desc":"较适宜","datetime":"2017-10-26"},{"index":"2","desc":"较适宜","datetime":"2017-10-27"},{"index":"2","desc":"较适宜","datetime":"2017-10-28"},{"index":"1","desc":"适宜","datetime":"2017-10-29"},{"index":"1","desc":"适宜","datetime":"2017-10-30"},{"index":"2","desc":"较适宜","datetime":"2017-10-31"},{"index":"1","desc":"适宜","datetime":"2017-11-01"},{"index":"1","desc":"适宜","datetime":"2017-11-02"},{"index":"1","desc":"适宜","datetime":"2017-11-03"},{"index":"1","desc":"适宜","datetime":"2017-11-04"},{"index":"1","desc":"适宜","datetime":"2017-11-05"},{"index":"1","desc":"适宜","datetime":"2017-11-06"},{"index":"1","desc":"适宜","datetime":"2017-11-07"}]
             * precipitation : [{"date":"2017-10-24","max":0,"avg":0,"min":0},{"date":"2017-10-25","max":0,"avg":0,"min":0},{"date":"2017-10-26","max":0,"avg":0,"min":0},{"date":"2017-10-27","max":0,"avg":0,"min":0},{"date":"2017-10-28","max":0,"avg":0,"min":0},{"date":"2017-10-29","max":0,"avg":0,"min":0},{"date":"2017-10-30","max":0,"avg":0,"min":0},{"date":"2017-10-31","max":0,"avg":0,"min":0},{"date":"2017-11-01","max":0,"avg":0,"min":0},{"date":"2017-11-02","max":0,"avg":0,"min":0},{"date":"2017-11-03","max":0,"avg":0,"min":0},{"date":"2017-11-04","max":0,"avg":0,"min":0},{"date":"2017-11-05","max":0,"avg":0,"min":0},{"date":"2017-11-06","max":0,"avg":0,"min":0},{"date":"2017-11-07","max":0,"avg":0,"min":0}]
             * wind : [{"date":"2017-10-24","max":{"direction":183.07,"speed":9.72},"avg":{"direction":135.67,"speed":4.6},"min":{"direction":118.89,"speed":1.31}},{"date":"2017-10-25","max":{"direction":173.27,"speed":6.87},"avg":{"direction":61.45,"speed":4.45},"min":{"direction":49.02,"speed":1.47}},{"date":"2017-10-26","max":{"direction":202.82,"speed":7.36},"avg":{"direction":89.92,"speed":3.75},"min":{"direction":64.14,"speed":1.36}},{"date":"2017-10-27","max":{"direction":182.74,"speed":9.58},"avg":{"direction":71.84,"speed":5.14},"min":{"direction":347.22,"speed":1.39}},{"date":"2017-10-28","max":{"direction":340.67,"speed":21.44},"avg":{"direction":340.2,"speed":14.11},"min":{"direction":356.62,"speed":6.36}},{"date":"2017-10-29","max":{"direction":323.66,"speed":9.64},"avg":{"direction":323.15,"speed":6.34},"min":{"direction":138.27,"speed":0.76}},{"date":"2017-10-30","max":{"direction":185.66,"speed":10.95},"avg":{"direction":140.17,"speed":6},"min":{"direction":310.07,"speed":0.57}},{"date":"2017-10-31","max":{"direction":173.89,"speed":7.8},"avg":{"direction":19.55,"speed":4.79},"min":{"direction":273.57,"speed":0.1}},{"date":"2017-11-01","max":{"direction":318.28,"speed":23.07},"avg":{"direction":331.69,"speed":10.26},"min":{"direction":338.05,"speed":3.2}},{"date":"2017-11-02","max":{"direction":342.31,"speed":9.97},"avg":{"direction":344.72,"speed":7.56},"min":{"direction":7.54,"speed":2.34}},{"date":"2017-11-03","max":{"direction":355.6,"speed":8.16},"avg":{"direction":304.53,"speed":6.17},"min":{"direction":339.47,"speed":5.06}},{"date":"2017-11-04","max":{"direction":257.71,"speed":11.36},"avg":{"direction":297.62,"speed":7.7},"min":{"direction":276.68,"speed":3.25}},{"date":"2017-11-05","max":{"direction":288.63,"speed":13.98},"avg":{"direction":298.59,"speed":10.4},"min":{"direction":318.53,"speed":7.57}},{"date":"2017-11-06","max":{"direction":296.28,"speed":11.15},"avg":{"direction":311.86,"speed":8.89},"min":{"direction":339.92,"speed":6.85}},{"date":"2017-11-07","max":{"direction":208.07,"speed":10.7},"avg":{"direction":278.82,"speed":8.4},"min":{"direction":320.78,"speed":3.92}}]
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

            public static class ColdRiskBean {
                /**
                 * index : 4
                 * desc : 极易发
                 * datetime : 2017-10-24
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
                 * date : 2017-10-24
                 * max : 18.0
                 * avg : 10.87
                 * min : 4.0
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
                 * date : 2017-10-24
                 * value : CLEAR_NIGHT
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
                 * date : 2017-10-24
                 * max : 0.02
                 * avg : 0.01
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

            public static class AqiBeanX {
                /**
                 * date : 2017-10-24
                 * max : 132.0
                 * avg : 129.67
                 * min : 33.0
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
                 * date : 2017-10-24
                 * max : 0.65
                 * avg : 0.59
                 * min : 0.43
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
                 * date : 2017-10-24
                 * sunset : {"time":"17:21"}
                 * sunrise : {"time":"06:33"}
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
                     * time : 17:21
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
                     * time : 06:33
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
                 * datetime : 2017-10-24
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
                 * date : 2017-10-24
                 * max : 100.0
                 * avg : 98.33
                 * min : 23.0
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
                 * index : 5
                 * desc : 凉爽
                 * datetime : 2017-10-24
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
                 * index : 2
                 * desc : 较适宜
                 * datetime : 2017-10-24
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
                 * date : 2017-10-24
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
                 * date : 2017-10-24
                 * max : {"direction":183.07,"speed":9.72}
                 * avg : {"direction":135.67,"speed":4.6}
                 * min : {"direction":118.89,"speed":1.31}
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
                     * direction : 183.07
                     * speed : 9.72
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
                     * direction : 135.67
                     * speed : 4.6
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
                     * direction : 118.89
                     * speed : 1.31
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
        }
    }
}