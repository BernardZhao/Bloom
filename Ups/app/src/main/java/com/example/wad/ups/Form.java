package com.example.wad.ups;

import android.app.Notification;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.NotificationCompat;
import android.app.NotificationManager;
import android.widget.EditText;

public class Form extends AppCompatActivity {

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
    public void onAddClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        NotificationsListenerService.showNotification("YOYO", "wahhhh", "woopdida", getApplicationContext());
    }
}
