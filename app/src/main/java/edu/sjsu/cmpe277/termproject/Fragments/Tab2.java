package edu.sjsu.cmpe277.termproject.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.sjsu.cmpe277.termproject.EventInformations;
import edu.sjsu.cmpe277.termproject.R;

/**
 * Created by emy on 11/29/15.
 */
public class Tab2 extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    String[] days = new String[]{"Monday","Tuesday","Wednesday","Thursday"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab2, container, false);
        listView = (ListView)view.findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, days);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), EventInformations.class);
        startActivity(intent);

    }
}
