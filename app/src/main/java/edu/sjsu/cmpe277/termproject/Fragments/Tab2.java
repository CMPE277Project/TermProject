package edu.sjsu.cmpe277.termproject.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import edu.sjsu.cmpe277.termproject.EventInformations;
import edu.sjsu.cmpe277.termproject.R;
import edu.sjsu.cmpe277.termproject.http.ApiServerGetTaskArray;

/**
 * Created by emy on 11/29/15.
 */
public class Tab2 extends Fragment implements AdapterView.OnItemClickListener {
//    private static final String baseURL = "http://79.170.44.117/rajat-bansal.com/skiBuddy/";
    private ListView listView;
//    private Intent selectedEvent;
//
//    String[] days = new String[]{"Monday","Tuesday","Wednesday","Thursday"};
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.tab2, container, false);
//        listView = (ListView)view.findViewById(R.id.listView);
//        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, days);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(this);
//        return view;
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//        Intent intent = new Intent(getActivity(), EventInformations.class);
//        startActivity(intent);
//
//    }
private static final String baseURL = "http://79.170.44.117/rajat-bansal.com/skiBuddy/";
    private ListView listView_;
    private Intent selectedEvent;

    private ArrayList<String> eventsList_ = new ArrayList<String>();
    private ArrayAdapter<String> lobbyAdapter_;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_view);
//
//        this.listView_ = (ListView)findViewById(R.id.listview);
//
//        this.listView_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String eventId = (String) ((TextView) view).getText();
//                try {
//                    eventDetail(eventId);
//                } catch (Exception e) {
//                    System.out.println(e.toString());
//                }
//            }
//        });
//
//        populateEvents();
//    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab2, container, false);
        listView = (ListView)view.findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, eventsList_);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        populateEvents();
    }

    //    private void eventDetail(String eventId) {
//        selectedEvent = new Intent(ListViewActivity.this, MembersListActivity.class);
//        selectedEvent.putExtra("eventId", eventId);
//        startActivity(selectedEvent);
//    }

        @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), EventInformations.class);
        startActivity(intent);

    }

    private void populateEvents() {

//        String JSONString = "{\"status\":\"success\",\"data\":{\"events\":[\"Beginners Cup\",\"Intermediates Cup\",\"Advanced Cup\"]}}";
        ApiServerGetTaskArray apiServerGetTaskArray = new ApiServerGetTaskArray();
        String endpoint = baseURL + "getAllEvents.php";

        try {

//            JSONObject jsonObj = apiServerGetTask.execute(endpoint).get();
            JSONArray jsonArray = apiServerGetTaskArray.execute(endpoint).get();
//            System.out.println(jsonObj.toString());
            System.out.println("JSONArray String is: " + jsonArray.toString());
//            JSONArray jEvents = jsonObj.getJSONObject("data").getJSONArray("events");
            JSONObject json = jsonArray.getJSONObject(0);
            System.out.println("JSON TITLE: "+ json.toString());
//            JSONObject jEvents = jsonObj.getJSONObject("title");
            ArrayList<String> events = new ArrayList<String>();
            for(int i = 0; i < jsonArray.length(); i++){
                events.add((String) jsonArray.getJSONObject(i).get("title"));
                System.out.println("event contents are: " + events.get(i));
            }
            System.out.println("event contents are: " + events);
//            for(int i = 0; i < jEvents.length(); i++){
//                events.add(jEvents.getString(i));
//            }

            this.eventsList_ = events;
//            this.lobbyAdapter_ = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.eventsList_);
//            this.listView_.setAdapter(this.lobbyAdapter_);
        }  catch (InterruptedException e) {
            System.out.println(e.toString());
        } catch (ExecutionException e) {
            System.out.println(e.toString());
        } catch (JSONException e) {
            System.out.println(e.toString());
        }

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
