package top.maweihao.weather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by ma on 17-3-5.
 */

public class Province extends DataSupport {

    private String provinceName;

    private int id;

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
