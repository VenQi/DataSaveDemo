package com.data.www.datasavedemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.data.www.datasavedemo.R;
import com.data.www.datasavedemo.entity.HistoryItem;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by qwh on 2017/11/22.
 */

public class CallHistoryAdapter extends BaseAdapter {
    Context context;
    List<HistoryItem> historyItems;
    public CallHistoryAdapter(List<HistoryItem> items,Context context){
        historyItems = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return historyItems.size();
    }

    @Override
    public Object getItem(int i) {
        return historyItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            view  = LayoutInflater.from(context).inflate(R.layout.call_history_item,null);
            holder.icon_in_out = view.findViewById(R.id.inorout_icon);
            holder.date = view.findViewById(R.id.call_date);
            holder.phone_num = view.findViewById(R.id.call_num);
            holder.call_last_time = view.findViewById(R.id.call_time);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        System.out.println(historyItems.size()+"----");
        HistoryItem item = historyItems.get(i);
        if ("in".equals(item.inorout)){
            holder.icon_in_out.setBackgroundResource(R.mipmap.call_in);
        }else
            holder.icon_in_out.setBackgroundResource(R.mipmap.call_out);
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        holder.date.setText(format.format(new Date(Long.parseLong(item.date))));
        holder.phone_num.setText(item.phone);
        return view;
    }

    class ViewHolder{
        ImageView icon_in_out;
        TextView date,phone_num,call_last_time;
    }
}