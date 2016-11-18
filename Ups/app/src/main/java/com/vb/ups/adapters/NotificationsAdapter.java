package com.vb.ups.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vb.ups.R;
import com.vb.ups.objects.Notification;

import java.util.ArrayList;

import static com.google.android.gms.plus.PlusOneDummyView.TAG;

/**
 * Created by Admin on 11/12/2016.
 */

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder> {

    private LayoutInflater inflator;
    public static ArrayList<Notification> notificationArrayList = (new ArrayList<Notification>());

    public NotificationsAdapter(Context context) {
        inflator = LayoutInflater.from(context);


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
        holder.description.setText(currentNotification.getDescription());
    }

    @Override
    public int getItemCount() {
        return notificationArrayList.size();
    }

    class NotificationsViewHolder extends RecyclerView.ViewHolder{
        TextView description;
        TextView title;
        public NotificationsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textItem);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }

    public static void addNotification(Notification n){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(n.getTitle());
        myRef.setValue(n);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Notification value = dataSnapshot.getValue(Notification.class);
                boolean preexistingObject = false;
                Log.d(TAG, "Value is: " + value.toString());
                for(Notification a : notificationArrayList){
                    if(a.equals(value))
                        preexistingObject = true;
                }
                if (!preexistingObject && value.getUserID() == FirebaseAuth.getInstance().getCurrentUser().getUid())
                    notificationArrayList.add(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    public static void receiveNotifications(Notification n){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(n.getTitle());

        myRef.setValue(n);
        NotificationPusher.sendNotification(n);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Notification value = dataSnapshot.getValue(Notification.class);
                boolean preexistingObject = false;
                Log.d(TAG, "Value is: " + value.toString());
                for(Notification a : notificationArrayList){
                    if(a.equals(value))
                        preexistingObject = true;
                }
                if (!preexistingObject && value.getUserID() == FirebaseAuth.getInstance().getCurrentUser().getUid())
                    notificationArrayList.add(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

}
