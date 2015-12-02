package edu.sjsu.cmpe277.termproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android4devs.slidingtab.SlidingTabLayout;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import edu.sjsu.cmpe277.termproject.Fragments.CreateFragments;
import edu.sjsu.cmpe277.termproject.Fragments.FirstFragment;
import edu.sjsu.cmpe277.termproject.Fragments.Tab1;
import edu.sjsu.cmpe277.termproject.Fragments.UserFragmentDetails;
import edu.sjsu.cmpe277.termproject.Fragments.ViewPageAdapter;
import edu.sjsu.cmpe277.termproject.models.User;

public class secondActivity extends AppCompatActivity {

   /* private TextView textView, textView2;
    private ProfilePictureView profilePictureView;
    private Intent intent;
    private Toolbar toolbar;
    private ListView listView;
    private DrawerLayout drawerLayout;
    private String[] nav_drawerList;
    private ObjectDrawer[] objectDrawers = new ObjectDrawer[3];
    private CustomDrawerAdapter customDrawerAdapter;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private CharSequence charSequence, title;*/
//    private Intent intent;
//    private Toolbar toolbar;
//    private String firstName, profileId;
//    private FirstFragment firstFragment;

    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPageAdapter viewPageAdapter;
    private SlidingTabLayout slidingTabLayout;
    private CharSequence[] charSequenceTitles=new CharSequence[]{"Profile","Events", "Invitation"};
    private User user;
    private Intent intent;
    private Tab1 tab1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

//        intent = getIntent();
//        firstName = intent.getStringExtra("firstName");
//        profileId = intent.getStringExtra("Id");

        toolbar = (Toolbar)findViewById(R.id.secondToolBar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_menu_image);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        if(savedInstanceState == null) {
//
//           getSupportFragmentManager().beginTransaction().add(R.id.fragment1, getFragment()).commit();
//        }
//        firstFragment = new FirstFragment();
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.drawer, firstFragment, "Fragment1").commit();
//        firstFragment = new FirstFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("names", firstName);
//        bundle.putString("profileId", profileId);
//        firstFragment.setArguments(bundle);
//
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.drawer, firstFragment,"newFrag").commit();





      /*  title = charSequence = getTitle();

       intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String name = intent.getStringExtra("lastName");
        String userId = intent.getStringExtra("Id");



//Test to see if receiving data from fb.
        profilePictureView = (ProfilePictureView)findViewById(R.id.profilePic);
        profilePictureView.setProfileId(userId);
       // textView = (TextView)findViewById(R.id.displayName);
        textView2 =(TextView)findViewById(R.id.textName);
        textView2.setText(firstName + " " + name);
        //textView.setText(name);
        toolbar = (Toolbar)findViewById(R.id.toolBar);

        //toolbar.setTitle("Welcome");

        toolbar.setLogo(R.drawable.ic_menu_image);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listView = (ListView)findViewById(R.id.left_drawer);
        nav_drawerList = getResources().getStringArray(R.array.drawer_items);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);



        objectDrawers[0] = new ObjectDrawer(R.drawable.ic_open_event, "Create Event");
        objectDrawers[1] = new ObjectDrawer(R.drawable.ic_create_event, "Invitations");
       // objectDrawers[2] = new ObjectDrawer(R.drawable.ic)
        objectDrawers[2] = new ObjectDrawer(R.drawable.ic_logout, "Logout");

        customDrawerAdapter = new CustomDrawerAdapter(this, R.layout.listviews_items, objectDrawers);

        listView.setAdapter(customDrawerAdapter);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(charSequence);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        listView.setOnItemClickListener(new DrawerListener());


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
*/


        //getSupportActionBar().setHomeButtonEnabled(true);

        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(), charSequenceTitles, charSequenceTitles.length);
        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(viewPageAdapter);

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        slidingTabLayout.setDistributeEvenly(true);

        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.com_facebook_blue);
            }
        });
        slidingTabLayout.setViewPager(viewPager);


        Tab1 tab1 = new Tab1();
        Bundle bundle = new Bundle();
        bundle.putString("firstName", getIntent().getExtras().getString("firstName"));
        bundle.putString("lastName", getIntent().getExtras().getString("lastName"));
        bundle.putString("imageId", getIntent().getExtras().getString("imageId"));
        tab1.setArguments(bundle);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        switch(item.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(this, secondActivity.class );
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                return true;
        }


        if(id == R.id.newMenu){
            Intent intent = new Intent(this, CreateEvent.class);
            startActivity(intent);
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
