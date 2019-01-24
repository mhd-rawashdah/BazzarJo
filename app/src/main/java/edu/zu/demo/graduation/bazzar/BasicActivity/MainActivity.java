package edu.zu.demo.graduation.bazzar.BasicActivity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jcminarro.roundkornerlayout.RoundKornerLinearLayout;

import edu.zu.demo.graduation.bazzar.Fragment.Home;
import edu.zu.demo.graduation.bazzar.Fragment.MyOrderFragment;
import edu.zu.demo.graduation.bazzar.Fragment.NotificationFragment;
import edu.zu.demo.graduation.bazzar.Fragment.OffersFragment;
import edu.zu.demo.graduation.bazzar.Fragment.ShoppinCartFragment;
import edu.zu.demo.graduation.bazzar.Fragment.TrackOrderFragment;
import edu.zu.demo.graduation.bazzar.R;
import edu.zu.demo.graduation.bazzar.SearchActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle mToggle;
    private RoundKornerLinearLayout kornerLinearLayout;
    private Home homeFragment;
    private ShoppinCartFragment shoppinCartFragment;
    private MyOrderFragment myOrderFragment;
    private NotificationFragment notificationFragment;
    private OffersFragment offersFragment;
    private TrackOrderFragment trackOrderFragment;
    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialView();
        setTitle("Home");                                  // set Title to activity
        setSupportActionBar(toolbar);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);   //set toggle to drawer
        mDrawerLayout.addDrawerListener(mToggle);                                                        // add toggle to drawer layout
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);                                            // show button in action bar
        navigationView.setNavigationItemSelectedListener(this);                           // set Click Listner to Drawer menu


    } // end Create()

    public void initialView() {
        toolbar = findViewById(R.id.Home_toolbar);
        mDrawerLayout = findViewById(R.id.Home_drawerlayout);
        navigationView = findViewById(R.id.Home_navigationView);
        frameLayout = findViewById(R.id.Main_frame);
        homeFragment = new Home();
        shoppinCartFragment=new ShoppinCartFragment();
        myOrderFragment =new MyOrderFragment();
        notificationFragment=new NotificationFragment();
        offersFragment=new OffersFragment();
        trackOrderFragment=new TrackOrderFragment();
        setFragment(homeFragment);


    } // end initialView()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;

    }   // end onCreateOptionMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()){
            case R.id.Bar_Search:
             Intent in=new Intent(MainActivity.this, SearchActivity.class);
             startActivity(in);
                break;
            case R.id.Bar_Shopping_card:
                setFragment(shoppinCartFragment);
                setTitle("Shopping Cart");
        }
        return super.onOptionsItemSelected(item);
    }   // end  onOptionItemSelected

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                 setFragment(homeFragment);
                 setTitle("Home");
                Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_card:
                setFragment(shoppinCartFragment);
                setTitle("Shopping Cart");
                Toast.makeText(getApplicationContext(), "Card", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_myOrder:
                setFragment(myOrderFragment);
                setTitle("My Order");
                Toast.makeText(getApplicationContext(), "My order", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_notify:
                setFragment(notificationFragment);
                setTitle("Notifcation");
                Toast.makeText(getApplicationContext(), "Notifcation", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_offers:
                setFragment(offersFragment);
                setTitle("Offers");
                Toast.makeText(getApplicationContext(), "Offers", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_Setting:

                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    } //onNavicationItemSelected()  Method

    public void setFragment(Fragment fragment) {

     FragmentManager fr=getSupportFragmentManager();
     FragmentTransaction ft=fr.beginTransaction();
     ft.replace(R.id.Main_frame,fragment);
     ft.commit();
    }  //end set fragment()

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (true){
            setFragment(homeFragment);
            setTitle("Home");

        }
        else {
        super.onBackPressed();}
    }
} // end Class
