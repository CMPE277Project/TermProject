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
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;

import edu.sjsu.cmpe277.termproject.http.HttpPostEvent;
import edu.sjsu.cmpe277.termproject.models.Event;

public class CreateEvent extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Button button, button1, button2, button3, button4;
    private Intent intent;
    private EditText text1, text2;

    //variables hold the start date and end time.
    private TextView textview, textView2, textView3, textView4;

    //start date
    private int s_year, s_month, s_day;

    //end date
    private int  e_year, e_month, e_day;

    //variable to hold current date and time
    private Calendar startDate, endDate,  startTime, endTime;


    static final int DATE_DAILOG_ID =0;
    static final int DATE_DAILOG_NEXTID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        toolbar = (Toolbar)findViewById(R.id.eventToolBar);

        setSupportActionBar(toolbar);
        text1 = (EditText)findViewById(R.id.editTitleEvent);
        text2 = (EditText)findViewById(R.id.editDescriptionEvent);
        textview = (TextView)findViewById(R.id.startDateEvent);

        //get current date
        startDate = Calendar.getInstance();
        s_day = startDate.get(Calendar.DAY_OF_MONTH);
        s_month = startDate.get(Calendar.MONTH);
        s_year = startDate.get(Calendar.YEAR);


        endDate = Calendar.getInstance();
        e_day = endDate.get(Calendar.DAY_OF_MONTH);
        e_month = endDate.get(Calendar.MONTH);
        e_month = endDate.get(Calendar.YEAR);

        //get current time
        startTime = Calendar.getInstance();
        endTime = Calendar.getInstance();

        //capture our view elements for the start date function
        button = (Button)findViewById(R.id.startDateButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showDialog(DATE_DAILOG_ID);
            }
        });

        button1 = (Button)findViewById(R.id.EndDateButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DAILOG_NEXTID);
            }
        });

        //initalizing submit button...
        button4 = (Button)findViewById(R.id.submitButton);
        button4.setOnClickListener(this);



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
        event.setDescription(text2.toString());

        new HttpPostEvent().execute(event);
        Intent intent = new Intent(this, secondActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DATE_DAILOG_ID) {
            return new DatePickerDialog(this, dateListener, s_year, s_month, s_day);
        }
        else if(id == DATE_DAILOG_NEXTID) {
            return new DatePickerDialog(this, dateListener2, e_year, e_month, e_day);
        }
        else
            return null;
    }

    private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            s_day = dayOfMonth;
            s_year = year;
            s_month = monthOfYear+1;


            Toast.makeText(CreateEvent.this, s_month+":"+s_day+":"+s_year,Toast.LENGTH_LONG ).show();
        }
    };

    private DatePickerDialog.OnDateSetListener dateListener2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        }
    };

}
