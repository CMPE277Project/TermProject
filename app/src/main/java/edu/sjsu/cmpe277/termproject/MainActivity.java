package edu.sjsu.cmpe277.termproject;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;

import edu.sjsu.cmpe277.termproject.Fragments.Tab1;
import edu.sjsu.cmpe277.termproject.Fragments.ViewPageAdapter;
import edu.sjsu.cmpe277.termproject.Fragments.profileFragment;
import edu.sjsu.cmpe277.termproject.http.PostUserInfo;
import edu.sjsu.cmpe277.termproject.interfaces.Communicator;
import edu.sjsu.cmpe277.termproject.models.User;
import com.android4devs.slidingtab.SlidingTabLayout;

public class MainActivity extends AppCompatActivity /*implements Communicator*/ {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPageAdapter viewPageAdapter;
    private SlidingTabLayout slidingTabLayout;
    private CharSequence[] charSequenceTitles=new CharSequence[]{"Profile","Events", "Invitation"};
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private User user;
    private Intent intent;
    private Tab1 tab1;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        FaceBookSetup();

//        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(), charSequenceTitles, charSequenceTitles.length);
//        viewPager = (ViewPager)findViewById(R.id.pager);
//        viewPager.setAdapter(viewPageAdapter);
//
//        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
//        slidingTabLayout.setDistributeEvenly(true);
//
//        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
//            @Override
//            public int getIndicatorColor(int position) {
//                return getResources().getColor(R.color.com_facebook_blue);
//            }
//        });
//        slidingTabLayout.setViewPager(viewPager);

       /* if(savedInstanceState==null) {
            tab1 = new Tab1();
            FragmentManager mananger = getSupportFragmentManager();
            FragmentTransaction transaction = mananger.beginTransaction();
            transaction.add(R.id.pager,tab1,"tab1fragment").commit();
        }*/



        //initialize facebook sdk


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
//        if(id == R.id.newMenu){
//            Intent intent = new Intent(MainActivity.this, CreateEvent.class);
//            startActivity(intent);
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    private void FaceBookSetup() {

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
        loginButton = (LoginButton)findViewById(R.id.login_button);
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
                            user.setImageFile(jsonObject.getString("id"));

//                            user.setImageFile(jsonObject.getString("picture"));
                           intent = new Intent(MainActivity.this, secondActivity.class);
               intent.putExtra("firstName", user.getFirstName());
               intent.putExtra("lastName", user.getLastName());
               intent.putExtra("Id", user.getUserId());
               startActivity(intent);
                            Log.d("VISUAL: ", user.getLastName());

                        }
                        catch(JSONException ex) {
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
       });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



//    @Override
//    public void updateProfile(String userId, String firstName, String LastName) {
//       profileFragment profilefragment = new profileFragment();
////        FragmentManager manager = getSupportFragmentManager();
////        FragmentTransaction transaction = manager.beginTransaction();
////        transaction.replace(R.id.pager, profilefragment, "profileFrage");
////        transaction.addToBackStack("tab1Fragment");
////        transaction.commit();
//
//        Bundle bundle = new Bundle();
//        bundle.putString("picId", userId );
//        bundle.putString("firstName", firstName);
//        bundle.putString("lastName", LastName);
//
//        profilefragment.setArguments(bundle);
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.pager, profilefragment, "profileFrags");
//        transaction.addToBackStack("tab1Fragment");
//        transaction.commit();
//       // tr
//    }

    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    public void onResume() {
        super.onResume();
    }



}
