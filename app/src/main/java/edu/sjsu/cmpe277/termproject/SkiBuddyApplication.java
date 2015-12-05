package edu.sjsu.cmpe277.termproject;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by davchen on 12/3/15.
 */
public class SkiBuddyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(this, "PwkOGqFnv97ycE8GRkiv8dKwwDbVGhuoAmfp70N8", "HKfztWDeYtByougLmaEN2rxLmkKxR2kTdQUn09zs");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }

}
