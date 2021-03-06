package edu.sjsu.cmpe277.termproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Sessions extends AppCompatActivity {
    //add for google part
    static final LatLng MyPoint = new LatLng(21 , 57);
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions);

        System.out.println("@@@@@@@@@@@In Sessions onCreate@@@@@@@@@@@@@@");

        //add for google map
        try {
            if (googleMap == null) {
                System.out.println("@@@@@@@@@@@googleMap is null@@@@@@@@@@@@@@");

                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();

            }

            // What if googleMap != null?

            System.out.println("@@@@@@@@@@@googleMap is leaving if statement@@@@@@@@@@@@@@");

            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            Marker TP = googleMap.addMarker(new MarkerOptions().
                    position(MyPoint).title("MyPoint"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sessions, menu);
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
}
