package com.vb.ups.activities;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.app.DialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.vb.ups.activities.MainActivity;
import com.vb.ups.adapters.NotificationsAdapter;
import com.vb.ups.fragments.DatePickerFragment;
import com.vb.ups.fragments.TimePickerFragment;
import com.vb.ups.objects.Notification;
import com.vb.ups.services.NotificationsListenerService;
import com.vb.ups.R;

public class FormActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText inputNotification, inputDescription;
    private TextInputLayout inputLayoutNotification, inputLayoutDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        inputLayoutNotification = (TextInputLayout) findViewById(R.id.input_layout_notification);
        inputLayoutDescription = (TextInputLayout) findViewById(R.id.input_layout_description);
        inputNotification = (EditText) findViewById(R.id.input_notification);
        inputDescription = (EditText) findViewById(R.id.input_description);
    }

    public void add(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        NotificationsAdapter.addNotification(new Notification(inputNotification.getText().toString(), inputDescription.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getUid()));
        //NotificationsListenerService.showNotification("YOYO", "wahhhh", "woopdida", getApplicationContext());
    }

    public void button1pressed(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(),"TimePicker");
    }

    public void button2pressed(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(),"Date Picker");
    }
}
