package top.maweihao.weather.adapter;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import top.maweihao.weather.R;
import top.maweihao.weather.entity.SingleWeather;

/**
 * hourlyRecyclerView adapter for per day weather
 * Created by ma on 17-7-11.
 */

public class DailyWeatherAdapter extends RecyclerView.Adapter<DailyWeatherAdapter.ViewHolder> {

    private static final String TAG = DailyWeatherAdapter.class.getSimpleName();
    private List<SingleWeather> weatherList;

    public DailyWeatherAdapter(List<SingleWeather> weatherList) {
        this.weatherList = weatherList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_per_day_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SingleWeather singleWeather = weatherList.get(position);
        holder.time.setText(singleWeather.getTime());
        holder.skyconImage.setImageResource(singleWeather.getSkyconId());
        holder.skyconImage.setContentDescription(singleWeather.getSkyconDesc());
        holder.skyconDesc.setText(singleWeather.getSkyconDesc());
        String temperature = singleWeather.getTemperature();
        int index = temperature.indexOf('/');
        if (index != -1) {
            SpannableString ss = new SpannableString(temperature);
            ss.setSpan(new StyleSpan(Typeface.BOLD), 0, index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.temperature.setText(ss);
        } else {
            holder.temperature.setText(singleWeather.getTemperature());
            Log.e(TAG, "onBindViewHolder: temperature string has no '/' " + temperature);
        }
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView time;
        ImageView skyconImage;
        TextView skyconDesc;
        TextView temperature;

        public ViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.per_day_recycler_time_tv);
            skyconImage = itemView.findViewById(R.id.per_day_recycler_skycon_iv);
            skyconDesc = itemView.findViewById(R.id.per_day_recycler_skycon_tv);
            temperature = itemView.findViewById(R.id.per_day_recycler_temperature_tv);
        }
    }

    public void setWeatherList(List<SingleWeather> list) {
        weatherList = list;
        notifyDataSetChanged();
    }
}
