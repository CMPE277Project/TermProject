package edu.sjsu.cmpe277.termproject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Date;
import java.util.Calendar;

import edu.sjsu.cmpe277.termproject.http.HttpPostEvent;
import edu.sjsu.cmpe277.termproject.models.Event;

public class CreateEvent extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Button button;
    private Button button2;
    private Intent intent;
    private EditText text1, text2, text3, text4;

    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        toolbar = (Toolbar)findViewById(R.id.eventToolBar);

        setSupportActionBar(toolbar);
        text1 = (EditText)findViewById(R.id.editTitleEvent);
        text2 = (EditText)findViewById(R.id.editDescriptionEvent);
        textview = (TextView)findViewById(R.id.startDateEvent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Event event = new Event();
        event.setTitle(text1.toString());
        event.setTitle(text2.toString());
        event.setStartTime(Date.valueOf(text3.toString()));
        event.setEndTime(Date.valueOf(text4.toString()));

        new HttpPostEvent().execute(event);
        Intent intent = new Intent(this, secondActivity.class);
        startActivity(intent);
        finish();
    }

    public void showTimePicker(View view) {

        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePicker(View view) {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "datePicker");
    }


    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener

    {
        TextView textView;

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            
        }
    }
}
