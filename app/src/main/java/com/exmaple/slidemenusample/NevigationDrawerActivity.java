package com.exmaple.slidemenusample;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NevigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ExpandableListView.OnChildClickListener {
    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawer;
    private ExpandableHeightListView elv_menuItem;
    List<String> headerDataList;
    HashMap<String, List<String>> childDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nevigation_drawer);

        initializeControls(); // for getting all id from XML
        bindControls(); // for setting all the attributes
        prepareListData(); // for getting all the data from the server or local data base
        setNavigationHeaderItem(); // for setting that nevigation drawable data
    }

    private void bindControls() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initializeControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
    }

    private void setNavigationHeaderItem() {
        View header = navigationView.inflateHeaderView(R.layout.nav_header_layout);
        elv_menuItem = (ExpandableHeightListView) header.findViewById(R.id.elv_menuOption);
        elv_menuItem.setAdapter(new MyExpandableMenuListAdapter(this, headerDataList, childDataList));
        elv_menuItem.setOnChildClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.nevigation_drawer, menu);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void prepareListData() {
        headerDataList = new ArrayList<String>();
        childDataList = new HashMap<String, List<String>>();

        // headers data initialization
        Resources res = getResources();
        String[] headers = res.getStringArray(R.array.nav_drawer_labels);
        headerDataList = Arrays.asList(headers);

        List<String> actorName, b_movie, h_moview;
        String[] arrayActors, arrayBolMovie, arrayHolMovie;

        arrayActors = res.getStringArray(R.array.elements_Actors);
        actorName = Arrays.asList(arrayActors);

        arrayBolMovie = res.getStringArray(R.array.elements_bollywood);
        b_movie = Arrays.asList(arrayBolMovie);

        arrayHolMovie = res.getStringArray(R.array.elements_hollywood);
        h_moview = Arrays.asList(arrayHolMovie);

        //Add to hashMap
        childDataList.put(headerDataList.get(0), actorName);
        childDataList.put(headerDataList.get(1), b_movie);
        childDataList.put(headerDataList.get(2), h_moview);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(getApplicationContext(),headerDataList.get(groupPosition)+ " - "+
                childDataList.get(headerDataList.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
        return false;
    }
}
