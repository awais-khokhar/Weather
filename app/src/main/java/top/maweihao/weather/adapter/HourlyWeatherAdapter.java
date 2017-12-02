package top.maweihao.weather.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import top.maweihao.weather.R;
import top.maweihao.weather.entity.SingleWeather;

/**
 * hourlyRecyclerView adapter for 24hour weather
 * Created by ma on 17-7-7.
 */

public class HourlyWeatherAdapter extends RecyclerView.Adapter<HourlyWeatherAdapter.ViewHolder> {

    private List<SingleWeather> weatherList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView time;
        ImageView skyconImage;
        TextView skyconDesc;
        TextView temperature;

        public ViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.few_hour_time_tv);
            skyconImage = (ImageView) itemView.findViewById(R.id.few_hour_skycon_iv);
            skyconDesc = (TextView) itemView.findViewById(R.id.few_hour_skycon_tv);
            temperature = (TextView) itemView.findViewById(R.id.few_hour_tem_tv);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_few_hour_RL, parent, false);
        return new ViewHolder(view);
    }

    public HourlyWeatherAdapter(List<SingleWeather> weatherList) {
        this.weatherList = weatherList;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SingleWeather singleWeather = weatherList.get(position);
        holder.time.setText(singleWeather.getTime());
        holder.skyconImage.setImageResource(singleWeather.getSkyconId());
        holder.skyconDesc.setText(singleWeather.getSkyconDesc());
        holder.temperature.setText(singleWeather.getTemperature());
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}
