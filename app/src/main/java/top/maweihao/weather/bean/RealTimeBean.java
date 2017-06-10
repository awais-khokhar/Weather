package top.maweihao.weather.bean;

import java.util.List;

/**
 * 实时天气bean
 * 对应json数据格式，自动生成
 * Created by limuyang on 2017/6/10.
 */

public class RealTimeBean {

    /**
     * status : ok
     * lang : zh_CN
     * server_time : 1497041225
     * tzshift : 28800
     * location : [25.1552,121.6544]
     * unit : metric
     * result : {"status":"ok","temperature":26,"skycon":"PARTLY_CLOUDY_NIGHT","cloudrate":0.46,"aqi":18,"humidity":0.89,"pm25":11,"precipitation":{"nearest":{"status":"ok","distance":178.47,"intensity":0.25},"local":{"status":"ok","intensity":0,"datasource":"radar"}},"wind":{"direction":233.09,"speed":6.54}}
     */

    private String status;
    private String lang;
    private int server_time;
    private int tzshift;
    private String unit;
    private ResultBean result;
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

    public int getServer_time() {
        return server_time;
    }

    public void setServer_time(int server_time) {
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

    public static class ResultBean {
        /**
         * status : ok
         * temperature : 26.0
         * skycon : PARTLY_CLOUDY_NIGHT
         * cloudrate : 0.46
         * aqi : 18.0
         * humidity : 0.89
         * pm25 : 11.0
         * precipitation : {"nearest":{"status":"ok","distance":178.47,"intensity":0.25},"local":{"status":"ok","intensity":0,"datasource":"radar"}}
         * wind : {"direction":233.09,"speed":6.54}
         */

        private String status;
        private float temperature;
        private String skycon;
        private float cloudrate;
        private float aqi;
        private float humidity;
        private float pm25;
        private PrecipitationBean precipitation;
        private WindBean wind;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public float getTemperature() {
            return temperature;
        }

        public void setTemperature(float temperature) {
            this.temperature = temperature;
        }

        public String getSkycon() {
            return skycon;
        }

        public void setSkycon(String skycon) {
            this.skycon = skycon;
        }

        public float getCloudrate() {
            return cloudrate;
        }

        public void setCloudrate(float cloudrate) {
            this.cloudrate = cloudrate;
        }

        public float getAqi() {
            return aqi;
        }

        public void setAqi(float aqi) {
            this.aqi = aqi;
        }

        public float getHumidity() {
            return humidity;
        }

        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }

        public float getPm25() {
            return pm25;
        }

        public void setPm25(float pm25) {
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
             * nearest : {"status":"ok","distance":178.47,"intensity":0.25}
             * local : {"status":"ok","intensity":0,"datasource":"radar"}
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
                 * distance : 178.47
                 * intensity : 0.25
                 */

                private String status;
                private float distance;
                private float intensity;

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public float getDistance() {
                    return distance;
                }

                public void setDistance(float distance) {
                    this.distance = distance;
                }

                public float getIntensity() {
                    return intensity;
                }

                public void setIntensity(float intensity) {
                    this.intensity = intensity;
                }
            }

            public static class LocalBean {
                /**
                 * status : ok
                 * intensity : 0.0
                 * datasource : radar
                 */

                private String status;
                private float intensity;
                private String datasource;

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public float getIntensity() {
                    return intensity;
                }

                public void setIntensity(float intensity) {
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
             * direction : 233.09
             * speed : 6.54
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
