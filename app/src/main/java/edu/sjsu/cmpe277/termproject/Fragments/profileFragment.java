package edu.sjsu.cmpe277.termproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

import edu.sjsu.cmpe277.termproject.R;

/**
 * Created by emy on 11/29/15.
 */
public class profileFragment extends Fragment  {

    private TextView textView;
    private ProfilePictureView profilePictureView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        textView = (TextView)view.findViewById(R.id.textName);
        profilePictureView = (ProfilePictureView)view.findViewById(R.id.profilepic);
        Bundle bundle = this.getArguments();
        Log.d("Messgae", bundle.getString("firstName"));
//      updateProfile(bundle.getString("picId"), bundle.getString("firstName"), bundle.getString("lastName"));
        return view;

    }

//    @Override
//    public void onActivityCreated( Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        textView = (TextView)getActivity().findViewById(R.id.textName);
//        profilePictureView = (ProfilePictureView)getActivity().findViewById(R.id.profilepic);
//
//    }
    public void updateProfile(String picId, String firstName, String lastName) {
        textView.setText(firstName+" "+lastName);
        profilePictureView.setProfileId(picId);

    }


}
