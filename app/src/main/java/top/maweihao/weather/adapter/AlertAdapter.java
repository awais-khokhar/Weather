package top.maweihao.weather.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import top.maweihao.weather.R;
import top.maweihao.weather.bean.Alert;

;

/**
 * recyclerView adapter for AlertActivity
 * Created by ma on 17-7-24.
 */

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.ViewHolder> {

    private List<Alert> alertList;

    public AlertAdapter(List<Alert> alertList) {
        this.alertList = alertList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alert_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Alert alert = alertList.get(position);
        holder.alertIconBg.setImageResource(alert.iconBgId);
        holder.alertIcon.setImageResource(alert.iconId);
        holder.title.setText(alert.title);
        holder.subtitle.setText(alert.subtitle);
        holder.content.setText(alert.content);
    }

    @Override
    public int getItemCount() {
        return alertList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView alertIconBg;
        ImageView alertIcon;
        TextView title;
        TextView subtitle;
        TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            alertIconBg = (ImageView) itemView.findViewById(R.id.alert_item_icon_bg);
            alertIcon = (ImageView) itemView.findViewById(R.id.alert_item_icon);
            title = (TextView) itemView.findViewById(R.id.alert_item_title);
            subtitle = (TextView) itemView.findViewById(R.id.alert_item_subtitle);
            content = (TextView) itemView.findViewById(R.id.alert_item_content);
        }
    }
}
