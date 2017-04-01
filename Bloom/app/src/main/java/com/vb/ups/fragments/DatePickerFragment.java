package com.vb.ups.fragments;

/**
 * Created by berna on 11/16/2016.
 */
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.TextView;
import android.widget.DatePicker;
import android.app.Dialog;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.vb.ups.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Use the current date as the default date in the date picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        //Create a new DatePickerDialog instance and return it
        /*
            DatePickerDialog Public Constructors which support Theme declaration
            public DatePickerDialog (Context context, int theme, DatePickerDialog.OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth)
            Supported Themes are....
                THEME_DEVICE_DEFAULT_DARK
                THEME_DEVICE_DEFAULT_LIGHT
                THEME_HOLO_DARK
                THEME_HOLO_LIGHT
                THEME_TRADITIONAL

         */
        //*********** Just uncomment any one below line to apply another Theme ************
        return new DatePickerDialog(getActivity(), this, year, month, day);
        //return new DatePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, this, year, month, day);
        //return new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK, this, year, month, day);
        //return new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, this, year, month, day);
        //return new DatePickerDialog(getActivity(), AlertDialog.THEME_TRADITIONAL, this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        //Do something with the date chosen by the user
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //CreateActivity.time = sdf;
        String dateString = year+"-"+(new DecimalFormat("00").format(month+1))+"-"+(new DecimalFormat("00").format(day));
        //CreateActivity.inputDate.setText(year+"-"+(new DecimalFormat("00").format(month+1))+"-"+(new DecimalFormat("00").format(day)));


        TextView tv = (TextView) getActivity().findViewById(R.id.textDate);

        tv.setText(dateString);

        /*
        tv.setText("Date changed...");
        tv.setText(tv.getText() + "\nYear: " + year);
        tv.setText(tv.getText() + "\nMonth: " + month);
        tv.setText(tv.getText() + "\nDay of Month: " + day);

        String stringOfDate = day + "/" + month + "/" + year;
        tv.setText(tv.getText() + "\n\nFormatted date: " + stringOfDate);
        */
    }
}
