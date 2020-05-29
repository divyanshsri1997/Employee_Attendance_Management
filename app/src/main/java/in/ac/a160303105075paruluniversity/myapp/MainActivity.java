package in.ac.a160303105075paruluniversity.myapp;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener {

    CalendarView cv;
    TextView dateTextView, holidayTextView,weekDayTextView, dayTextView, taskTextView,greetTextView,
    nameTextView, presentTextView, absentTextView, plTextView, clTextView, slTextView;
    String currentDate;
    private CheckConnection receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FF1F49"));
        toolbar.setBackground(colorDrawable);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new CheckConnection(this){
            @Override
            protected void onNetworkChange() {
                boolean networkStatus = isConnected();
                if(!networkStatus){
                    //Toast.makeText(getApplicationContext(),"No internet...", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"No Internet Connection",Snackbar.LENGTH_INDEFINITE);
                    snackbar.show();
                }else{
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"Connected...",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    updateDashboard();
                }
            }
        };
        registerReceiver(receiver, filter);
    }


    private void updateDashboard(){
        this.holidayTextView = findViewById(R.id.holidayTextView);
        this.weekDayTextView = findViewById(R.id.weekDayTextView);
        this.dayTextView = findViewById(R.id.dayTextView);
        this.dateTextView = findViewById(R.id.dateTextView);
        this.nameTextView = findViewById(R.id.nameTextView);
        this.presentTextView = findViewById(R.id.presentTextView);
        this.absentTextView = findViewById(R.id.absentTextView);
        this.plTextView = findViewById(R.id.plTextView);
        this.clTextView = findViewById(R.id.clTextView);
        this.slTextView = findViewById(R.id.slTextView);
        this.greetTextView = findViewById(R.id.greetTextView);
        this.taskTextView = findViewById(R.id.taskTextView);
        this.cv = findViewById(R.id.holidayCalendarView);
        long milliSeconds = cv.getDate();
        displayCurrentDate(milliSeconds);
        new FetchEmployeeData(nameTextView, presentTextView, absentTextView, plTextView, slTextView, clTextView)
                .execute();
        new FetchHoliday(dayTextView, weekDayTextView, holidayTextView, currentDate).execute();
    }



    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager)this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void displayCurrentDate(long milliSeconds){
        Date date = new Date(milliSeconds);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df =
                new SimpleDateFormat("dd-MM-YYYY HH");
        String dateWithTime = df.format(date);
        currentDate = dateWithTime.substring(0,10);
        String currentDay = currentDate.substring(0,2);
        int time = Integer.parseInt(dateWithTime.substring(11));
        dateTextView.setText(currentDate);
        dayTextView.setText(currentDay);
        displayGreetMessage(time);
    }
    private void displayGreetMessage(int time){
        if(time >=4 && time < 12){
            greetTextView.setText(R.string.morningMsg);
        }
        else if(time < 16){
            greetTextView.setText(R.string.afternoonMessage);
        }
        else{
            greetTextView.setText(R.string.eveningMessage);
        }
    }

    public void launchTaskActivity(View view) {
        Intent intent = new Intent(this,TaskActivity.class);
        startActivity(intent);
    }

    public void launchMyLeavesActivity(View view) {
        Intent intent = new Intent(this,MyLeavesActivity.class);
        startActivity(intent);
    }

    public void launchAttendanceLogActivity(View view) {
        Intent intent = new Intent(this,AttendanceLogActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {
            //log out logic
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
