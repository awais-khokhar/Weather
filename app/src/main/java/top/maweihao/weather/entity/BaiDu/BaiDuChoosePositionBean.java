package top.maweihao.weather.entity.BaiDu;

/**
 * 根据城市位知名，百度定位返回的json bean
 * 根据json数据，自动生成
 * Created by limuyang on 2017/6/10.
 */

public class BaiDuChoosePositionBean {

    /**
     * status : 0
     * result : {"title":{"lng":103.91332638625914,"lat":25.3600574715727},"precise":0,"confidence":14,"level":"区县"}
     */

    private int status;
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * title : {"lng":103.91332638625914,"lat":25.3600574715727}
         * precise : 0
         * confidence : 14
         * level : 区县
         */

        private LocationBean location;
        private int precise;
        private int confidence;
        private String level;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public int getPrecise() {
            return precise;
        }

        public void setPrecise(int precise) {
            this.precise = precise;
        }

        public int getConfidence() {
            return confidence;
        }

        public void setConfidence(int confidence) {
            this.confidence = confidence;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public static class LocationBean {
            /**
             * lng : 103.91332638625914
             * lat : 25.3600574715727
             */

            private double lng;
            private double lat;

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }
        }
    }
}
