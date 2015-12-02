package edu.sjsu.cmpe277.termproject.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import edu.sjsu.cmpe277.termproject.R;
import edu.sjsu.cmpe277.termproject.http.HttpRequestUser;
import edu.sjsu.cmpe277.termproject.http.PostUserInfo;
import edu.sjsu.cmpe277.termproject.interfaces.Communicator;
import edu.sjsu.cmpe277.termproject.models.User;
import edu.sjsu.cmpe277.termproject.secondActivity;

/**
 * Created by emy on 11/29/15.
 */
public class Tab1 extends Fragment /*implements View.OnClickListener*/{

//    private LoginButton loginButton;
//    private CallbackManager callbackManager;
//    private User user;
//    private AccessTokenTracker accessTokenTracker;
//    private ProfileTracker profileTracker;
//    private Communicator communicator;

  /*  private FacebookCallback<LoginResult> loginResultFacebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                    try {
                        user = new User();
                        user.setFirstName(jsonObject.getString("first_name"));
                        user.setLastName(jsonObject.getString("last_name"));
                        user.setImageFile(jsonObject.getString("id"));

                        new HttpRequestUser().execute(user);
                        profileFragment profilefragment = new profileFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("picId", user.getUserId() );
                        bundle.putString("firstName", user.getFirstName());
                        bundle.putString("lastName", user.getLastName());

//                        profilefragment.setArguments(bundle);
//                        FragmentManager manager = getFragmentManager();
//                        FragmentTransaction transaction = manager.beginTransaction();
//                        transaction.replace(R.id.tab_1, profilefragment, "profileFrags");
//                        transaction.addToBackStack("tab1Fragment");
//                        transaction.commit();
//                        Log.d("VISUAL: ", user.getImageFile());

                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }

            });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "first_name,last_name,id");
            graphRequest.setParameters(parameters);
            graphRequest.executeAsync();
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }

    };*/

    private ProfilePictureView profilePictureView;
    private TextView textView;

    public Tab1() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab1, container, false);
        return view;
    }


   /* private void facebookSetup(View view){

        //callbackManager = CallbackManager.Factory.create();
       loginButton = (LoginButton)view.findViewById(R.id.login_button);

        loginButton.setReadPermissions(Arrays.asList("public_profile"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                        try {
                            user = new User();
                            user.setFirstName(jsonObject.getString("first_name"));
                            user.setLastName(jsonObject.getString("last_name"));
                            user.setUserId(jsonObject.getString("id"));
//                            user.setImageFile(jsonObject.getString("picture"));
//                            intent = new Intent(MainActivity.this, secondActivity.class);
//                            intent.putExtra("firstName", user.getFirstName());
//                            intent.putExtra("lastName", user.getLastName());
//                            intent.putExtra("Id", user.getUserId());
//                            startActivity(intent);
                            Log.d("VISUAL: ", user.getLastName());

                        }
                        catch(JSONException ex) {
                            ex.printStackTrace();
                        }
                    }

                });
//               intent = new Intent(MainActivity.this, secondActivity.class);
//               intent.putExtra("firstName", user.getFirstName());
//               intent.putExtra("lastName", user.getLastName());
//               startActivity(intent);
                Bundle parameters = new Bundle();
                parameters.putString("fields", "first_name,last_name");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });

    }*/

   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken OldaccessToken, AccessToken NewaccessToken) {

            }
        };
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldprofile, Profile newprofile) {

                if(newprofile!=null) {
                    user = new User();
                    user.setImageFile(newprofile.getId());
                    user.setLastName(newprofile.getLastName());
                    user.setFirstName(newprofile.getFirstName());
                }

            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
        //facebookSetup();
    }*/

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        loginButton = (LoginButton)view.findViewById(R.id.login_button);
//        loginButton.setReadPermissions(Arrays.asList("public_profile"));
//        loginButton.setFragment(this);
//        loginButton.registerCallback(callbackManager, loginResultFacebookCallback);
//        facebookSetup(view);
        String firstName = getActivity().getIntent().getStringExtra("firstName");
        String lastName = getActivity().getIntent().getStringExtra("lastName");
        String imageId = getActivity().getIntent().getStringExtra("imageId");
        textView = (TextView)view.findViewById(R.id.textName);
        profilePictureView = (ProfilePictureView)view.findViewById(R.id.profilePic);

        textView.setText(firstName+" "+lastName);
        profilePictureView.setProfileId(imageId);

    }


   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    public void onResume() {
        super.onResume();
    }*/

   /* @Override
    public void onClick(View v) {
        communicator.updateProfile(user.getImageFile(), user.getFirstName(), user.getLastName());
    }*/
}
