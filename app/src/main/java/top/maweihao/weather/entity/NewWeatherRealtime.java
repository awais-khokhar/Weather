package top.maweihao.weather.entity;

import android.support.annotation.NonNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * Created by maweihao on 2017/10/24.
 */
@Entity
public class NewWeatherRealtime implements Comparable<NewWeatherRealtime> {

    /**
     * status : ok
     * lang : zh_CN
     * server_time : 1508853981
     * tzshift : 28800
     * location : [25.1552,121.6544]
     * unit : metric
     * result : {"status":"ok","temperature":21,"skycon":"RAIN","cloudrate":0.68,"aqi":21,"humidity":0.81,"pm25":13,"precipitation":{"nearest":{"status":"ok","distance":1.26,"intensity":0.1875},"local":{"status":"ok","intensity":0.125,"datasource":"radar"}},"wind":{"direction":55.37,"speed":24.46}}
     */
    @Transient
    private String status;
    @Transient
    private String lang;
    @Id
    private long server_time;
    @Transient
    private int tzshift;
    @Transient
    private String unit;
    @Transient
    private ResultBean result;
    @Transient
    private List<Double> location;

    @Property
    private String jsonString;

    @Generated(hash = 990581002)
    public NewWeatherRealtime(long server_time, String jsonString) {
        this.server_time = server_time;
        this.jsonString = jsonString;
    }

    @Generated(hash = 512272162)
    public NewWeatherRealtime() {
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

    public long getServer_time() {
        return server_time;
    }

    public void setServer_time(long server_time) {
        this.server_time = server_time;
    }

    public int getTzshift() {
        return tzshift;
    }

    public void setTzshift(int tzshift) {
        this.tzshift = tzshift;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
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

    @Override
    public int compareTo(@NonNull NewWeatherRealtime weatherRealtime) {
        if (this.server_time < weatherRealtime.server_time) {
            return -1;
        } else if (this.server_time == weatherRealtime.server_time) {
            return 0;
        } else {
            return -1;
        }
    }

    public static class ResultBean {
        /**
         * status : ok
         * temperature : 21.0
         * skycon : RAIN
         * cloudrate : 0.68
         * aqi : 21.0
         * humidity : 0.81
         * pm25 : 13.0
         * precipitation : {"nearest":{"status":"ok","distance":1.26,"intensity":0.1875},"local":{"status":"ok","intensity":0.125,"datasource":"radar"}}
         * wind : {"direction":55.37,"speed":24.46}
         */

        private String status;
        private double temperature;
        private String skycon;
        private double cloudrate;
        private double aqi;
        private double humidity;
        private double pm25;
        private PrecipitationBean precipitation;
        private WindBean wind;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public String getSkycon() {
            return skycon;
        }

        public void setSkycon(String skycon) {
            this.skycon = skycon;
        }

        public double getCloudrate() {
            return cloudrate;
        }

        public void setCloudrate(double cloudrate) {
            this.cloudrate = cloudrate;
        }

        public double getAqi() {
            return aqi;
        }

        public void setAqi(double aqi) {
            this.aqi = aqi;
        }

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        public double getPm25() {
            return pm25;
        }

        public void setPm25(double pm25) {
            this.pm25 = pm25;
        }

        public PrecipitationBean getPrecipitation() {
            return precipitation;
        }

        public void setPrecipitation(PrecipitationBean precipitation) {
            this.precipitation = precipitation;
        }

        public WindBean getWind() {
            return wind;
        }

        public void setWind(WindBean wind) {
            this.wind = wind;
        }

        public static class PrecipitationBean {
            /**
             * nearest : {"status":"ok","distance":1.26,"intensity":0.1875}
             * local : {"status":"ok","intensity":0.125,"datasource":"radar"}
             */

            private NearestBean nearest;
            private LocalBean local;

            public NearestBean getNearest() {
                return nearest;
            }

            public void setNearest(NearestBean nearest) {
                this.nearest = nearest;
            }

            public LocalBean getLocal() {
                return local;
            }

            public void setLocal(LocalBean local) {
                this.local = local;
            }

            public static class NearestBean {
                /**
                 * status : ok
                 * distance : 1.26
                 * intensity : 0.1875
                 */

                private String status;
                private double distance;
                private double intensity;

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public double getDistance() {
                    return distance;
                }

                public void setDistance(double distance) {
                    this.distance = distance;
                }

                public double getIntensity() {
                    return intensity;
                }

                public void setIntensity(double intensity) {
                    this.intensity = intensity;
                }
            }

            public static class LocalBean {
                /**
                 * status : ok
                 * intensity : 0.125
                 * datasource : radar
                 */

                private String status;
                private double intensity;
                private String datasource;

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public double getIntensity() {
                    return intensity;
                }

                public void setIntensity(double intensity) {
                    this.intensity = intensity;
                }

                public String getDatasource() {
                    return datasource;
                }

                public void setDatasource(String datasource) {
                    this.datasource = datasource;
                }
            }
        }

        public static class WindBean {
            /**
             * direction : 55.37
             * speed : 24.46
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
