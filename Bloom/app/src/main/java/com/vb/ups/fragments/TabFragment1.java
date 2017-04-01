package com.vb.ups.fragments;

import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vb.ups.R;
import com.vb.ups.adapters.NotificationsAdapter;
import com.vb.ups.async.NotificationFirstUpdater;
import com.vb.ups.objects.Notification;

import java.util.ArrayList;

import static com.vb.ups.adapters.NotificationsAdapter.notificationArrayList;

public class TabFragment1 extends Fragment {
    private NotificationsAdapter notificationsAdapter;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.tab_fragment_1, container, false);
        recyclerView = ((RecyclerView) layout.findViewById(R.id.mainlist));
        notificationsAdapter = new NotificationsAdapter(getActivity());
        recyclerView.setAdapter(notificationsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    @Override
    public void onViewCreated (View view,
                        Bundle savedInstanceState){

    }

}