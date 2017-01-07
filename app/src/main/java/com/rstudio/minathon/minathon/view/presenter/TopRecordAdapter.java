package com.rstudio.minathon.minathon.view.presenter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rstudio.minathon.minathon.R;

import java.util.List;


/**
 * Created by SilverWolf on 24/03/2016.
 */
public class TopRecordAdapter extends RecyclerView.Adapter<TopRecordAdapter.MyViewHolder> {

    private List<TopRecord> topRecords;
    private Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTime, tvDate;

        public MyViewHolder(View view) {
            super(view);
            tvTime = (TextView) view.findViewById(R.id.tv_time);
            tvDate = (TextView) view.findViewById(R.id.tv_date);
        }
    }


    public TopRecordAdapter(List<TopRecord> topRecords, Activity activity) {
        this.topRecords = topRecords;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_top_record, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final TopRecord topRecord = topRecords.get(position);
        holder.tvTime.setText(topRecord.getTime());
        holder.tvDate.setText(topRecord.getDate());
    }

    @Override
    public int getItemCount() {
        return topRecords.size();
    }
}
