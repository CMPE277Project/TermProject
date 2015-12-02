package edu.sjsu.cmpe277.termproject.Fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

import edu.sjsu.cmpe277.termproject.R;

/**
 * Created by emy on 11/28/15.
 */
public class FirstFragment extends Fragment   {

    private ProfilePictureView profilePictureView;
    private TextView textView;
    private UserFragmentDetails userFragmentDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.first_fragment, container, false);
//        textView = (TextView)rootView.findViewById(R.id.textName);
//        profilePictureView = (ProfilePictureView)rootView.findViewById(R.id.profilePic);
//
//        Bundle bundle = getArguments();
//        String name = bundle.getString("Name");
//        String userId = bundle.getString("Id");
//
//        textView.setText(name);
//        profilePictureView.setProfileId(userId);
        Bundle bundle = new Bundle();
        bundle = this.getArguments();
//        String name = bundle.getString("names");
        String profileId = bundle.getString("profileId");
        TextView textView = (TextView)rootView.findViewById(R.id.textName);
        profilePictureView = (ProfilePictureView)rootView.findViewById(R.id.profilePic);

        textView.setText(bundle.getString("names"));
        profilePictureView.setProfileId(bundle.getString("profileId"));


        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        try{
//
//            userFragmentDetails = (UserFragmentDetails)context;
//        }
//        catch(ClassCastException ex) {
//            throw new ClassCastException(ex.toString());
//        }
    }

}
