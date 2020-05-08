package in.ac.a160303105075paruluniversity.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener {

    CalendarView cv;
    TextView dateTextView, holidayTextView,weekDayTextView, dayTextView, taskTextView,greetTextView;
    TextView nameTextView, presentTextView, absentTextView, plTextView, clTextView, slTextView;


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

        this.taskTextView = findViewById(R.id.taskTextView);
        taskTextView.setText("2");

        cv = findViewById(R.id.holidayCalendarView);
        this.dateTextView = findViewById(R.id.dateTextView);
        long milliSeconds = cv.getDate();
        Date date = new Date(milliSeconds);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df =
                new SimpleDateFormat("dd-MM-YYYY HH");
        String dateWithTime = df.format(date);
        String currentDate = dateWithTime.substring(0,10);
        int time = Integer.parseInt(dateWithTime.substring(11));
        this.greetTextView = findViewById(R.id.greetTextView);
        displayGreetMessage(time);
        dateTextView.setText(currentDate);

        this.holidayTextView = findViewById(R.id.holidayTextView);
        this.weekDayTextView = findViewById(R.id.weekDayTextView);
        this.dayTextView = findViewById(R.id.dayTextView);
        new FetchHoliday(dayTextView,weekDayTextView, holidayTextView, currentDate).execute();

        this.nameTextView = findViewById(R.id.nameTextView);
        this.presentTextView = findViewById(R.id.presentTextView);
        this.absentTextView = findViewById(R.id.absentTextView);
        this.plTextView = findViewById(R.id.plTextView);
        this.clTextView = findViewById(R.id.clTextView);
        this.slTextView = findViewById(R.id.slTextView);
        new FetchEmployeeData(nameTextView,presentTextView,absentTextView,plTextView,slTextView,clTextView)
                .execute();

    }
    private void displayGreetMessage(int time){
        if(time >=5 && time < 12){
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
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
