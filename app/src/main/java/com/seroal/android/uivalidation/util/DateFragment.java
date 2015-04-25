package com.seroal.android.uivalidation.util;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by SeRoAl on 4/24/2015.
 */
public class DateFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {

    TextView textViewDate;
    Calendar copyCal;
    int year,month,day;

    public DateFragment(){
        super();
        copyCal = Calendar.getInstance();
    }

    public void setCalendar(Calendar cal){
        copyCal = cal;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker

        year = copyCal.get(Calendar.YEAR);
        month = copyCal.get(Calendar.MONTH);
        day = copyCal.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        if ((textViewDate!=null)&&(textViewDate.getText().toString().length()<5)) textViewDate.setText(" ");
        final DatePickerDialog picker = new DatePickerDialog(getActivity(), this, year, month, day);
        picker.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String myFormat = "MM/dd/yy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        //

                        copyCal.set(picker.getDatePicker().getYear(), picker.getDatePicker().getMonth(), picker.getDatePicker().getDayOfMonth());

                        if (textViewDate != null)
                            textViewDate.setText(sdf.format(copyCal.getTime()));
                    }
                });
        picker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if ((textViewDate!=null)&& (textViewDate.getText().toString().trim().length()<6))
                            textViewDate.setText(" ");

                    }
                });

        return picker;
    }

    public void setTextView(TextView tv){
        textViewDate = tv;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        copyCal.set(year,monthOfYear,dayOfMonth);

    }



}

