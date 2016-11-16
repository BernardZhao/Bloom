package com.vb.ups.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vb.ups.R;
import com.vb.ups.adapters.NotificationsAdapter;

public class TabFragment1 extends Fragment {
    private NotificationsAdapter notificationsAdapter;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.tab_fragment_1, container, false);
        notificationsAdapter = new NotificationsAdapter(getActivity());
        recyclerView = ((RecyclerView) layout.findViewById(R.id.mainlist));
        recyclerView.setAdapter(notificationsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }
}