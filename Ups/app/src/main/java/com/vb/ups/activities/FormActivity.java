package com.vb.ups.activities;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.app.DialogFragment;
import android.widget.TextView;

import java.text.SimpleDateFormat;


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
    private TextInputLayout inputLayoutNotification, inputLayoutDescription, inputLayoutDate, inputLayoutTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        inputLayoutNotification = (TextInputLayout) findViewById(R.id.input_layout_notification);
        inputLayoutDescription = (TextInputLayout) findViewById(R.id.input_layout_description);
        inputLayoutDate = (TextInputLayout) findViewById(R.id.input_layout_date);
        inputLayoutTime = (TextInputLayout) findViewById(R.id.input_layout_time);
        inputNotification = (EditText) findViewById(R.id.input_notification);
        inputDescription = (EditText) findViewById(R.id.input_description);
    }

    private boolean validateNotification() {
        if (inputNotification.getText().toString().trim().isEmpty()) {
            inputLayoutNotification.setError(getString(R.string.err_msg_name));
            requestFocus(inputNotification);
            return false;
        } else {
            inputLayoutNotification.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateDescription() {
        //String email = inputDescription.getText().toString().trim();

        if (inputDescription.getText().toString().trim().isEmpty()) {
            inputLayoutDescription.setError(getString(R.string.err_msg_description));
            requestFocus(inputDescription);
            return false;
        } else {
            inputLayoutDescription.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateDate() {
        TextView textView1 = (TextView) findViewById(R.id.textview1);

        String currentlyShownText = textView1.getText().toString();
        if (currentlyShownText.trim().isEmpty()) {
            inputLayoutDate.setError(getString(R.string.err_msg_date));
            requestFocus(textView1);
            return false;
        } else {
            inputLayoutDate.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateTime() {
        TextView textView2 = (TextView) findViewById(R.id.textview2);

        String currentlyShownText = textView2.getText().toString();

        if (currentlyShownText.trim().isEmpty()) {
            inputLayoutTime.setError(getString(R.string.err_msg_time));
            requestFocus(textView2);
            return false;
        } else {
            inputLayoutTime.setErrorEnabled(false);
        }

        return true;
    }

    public void add(View view) {
        if (!validateNotification()) {
            return;
        }

        if (!validateDescription()) {
            return;
        }

        if (!validateDate()) {
            return;
        }

        if (!validateTime()) {
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        NotificationsAdapter.addNotification(new Notification(inputNotification.getText().toString(), inputDescription.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getUid()));
        //NotificationsListenerService.showNotification("YOYO", "wahhhh", "woopdida", getApplicationContext());
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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
