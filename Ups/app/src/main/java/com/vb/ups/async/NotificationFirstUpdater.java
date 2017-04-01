package com.vb.ups.async;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vb.ups.objects.Notification;

import java.util.ArrayList;

import static com.vb.ups.adapters.NotificationsAdapter.notificationArrayList;

/**
 * Created by Admin on 11/18/2016.
 * fuk
 */

public class NotificationFirstUpdater extends AsyncTask<Notification, Void, Notification> {

    @Override
    protected Notification doInBackground(Notification... notifications) {
        retrieveNotifications();
        return null;
    }

    @Override
    protected void onPostExecute(Notification notification) {
        super.onPostExecute(notification);

    }
    public void retrieveNotifications() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("NotificationsAdapter", dataSnapshot.getChildrenCount()+"");
                for(DataSnapshot a : dataSnapshot.getChildren()) {
                    Notification notification = a.getValue(Notification.class);
                    Log.v("NotificationsAdapter", notification.toString());
                    if(!containsIn(notification, notificationArrayList) && notification.getUserID().equals(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                        notificationArrayList.add(notification);
                }
                //Log.v("NotificationsAdapter",notification.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }

    public boolean containsIn(Notification o, ArrayList<Notification> objectArrayList){
        for(Notification t : objectArrayList){
            if(t.equals(o))
                return true;
        }
        return false;
    }
}