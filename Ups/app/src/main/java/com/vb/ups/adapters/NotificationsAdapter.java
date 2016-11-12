package com.vb.ups.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vb.ups.R;
import com.vb.ups.objects.Notification;

import java.util.ArrayList;

/**
 * Created by Admin on 11/12/2016.
 */

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder> {

    private LayoutInflater inflator;
    private ArrayList<Notification> notificationArrayList = (new ArrayList<Notification>());

    public NotificationsAdapter(Context context) {
        inflator = LayoutInflater.from(context);
        for(int i=0; i<20; i++) {
            notificationArrayList.add(new Notification(""+i,Integer.toString(0-i)));
        }
    }

    //Inflates each custom_row.xml induvidually instead of manually doing it
    @Override
    public NotificationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.custom_row, parent, false);
        NotificationsViewHolder notificationsViewHolder = new NotificationsViewHolder(view);
        return notificationsViewHolder;
    }

    @Override
    public void onBindViewHolder(NotificationsViewHolder holder, int position) {
        Notification currentNotification = notificationArrayList.get(position);
        holder.title.setText(currentNotification.getTitle());
    }

    @Override
    public int getItemCount() {
        return notificationArrayList.size();
    }

    class NotificationsViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        public NotificationsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textItem);
        }
    }
}
