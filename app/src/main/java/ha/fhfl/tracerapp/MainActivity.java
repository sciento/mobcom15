package ha.fhfl.tracerapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import ha.fhfl.tracerapp.controllers.LocationController;
import ha.fhfl.tracerapp.models.Model;
import ha.fhfl.tracerapp.views.FragmentCourse;
import ha.fhfl.tracerapp.views.FragmentMap;
import ha.fhfl.tracerapp.views.FragmentSpeed;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "fhflMainActivity";

    private Model model;

    private FragmentCourse fragmentCourse;
    private FragmentMap fragmentMap;
    private FragmentSpeed fragmentSpeed;
    private LocationController locationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        model = new Model();
        locationController = new LocationController(this, model);

        fragmentMap = new FragmentMap();
        fragmentCourse = new FragmentCourse();
        fragmentSpeed = new FragmentSpeed();

        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragmentMap).commit();


        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // END Toolbar


        // Navigation
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // END Navigation
    }

    public Model getModel(){
        Log.d(TAG, "getDoc():  ");
        return model;
    }

    public void upDateAll() {
        Log.d(TAG, "upDateAll():  ");

        Fragment currentFragment = getFragmentManager().findFragmentById(R.id.fragment_container);

        if(currentFragment instanceof FragmentMap){
            Log.d(TAG, "upDateAll(): fragmentMap.onUpDate(); ");
            fragmentMap.onUpDate();
        }

        else if(currentFragment instanceof FragmentSpeed){
            Log.d(TAG, "upDateAll(): fragmentSpeed.onUpDate(); ");
            fragmentSpeed.onUpDate();
        }

        else if(currentFragment instanceof FragmentCourse){
            Log.d(TAG, "upDateAll(): fragmentCourse.onUpDate(); ");
            fragmentCourse.onUpDate();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if(id == R.id.walk) {
            FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
            fragTransaction.replace(R.id.fragment_container, fragmentMap);
            fragTransaction.addToBackStack(null);
            fragTransaction.commit();
        }

        if(id == R.id.bike) {
            FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
            fragTransaction.replace(R.id.fragment_container, fragmentSpeed);
            fragTransaction.addToBackStack(null);
            fragTransaction.commit();
        }

        if(id == R.id.tracks) {
            FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
            fragTransaction.replace(R.id.fragment_container, fragmentCourse);
            fragTransaction.addToBackStack(null);
            fragTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
