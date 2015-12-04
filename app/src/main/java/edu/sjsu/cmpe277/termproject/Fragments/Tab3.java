package edu.sjsu.cmpe277.termproject.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import edu.sjsu.cmpe277.termproject.R;

/**
 * Created by emy on 11/29/15.
 */
public class Tab3 extends Fragment {

    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab3, container, false);
        listView = (ListView)view.findViewById(R.id.listView3);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
