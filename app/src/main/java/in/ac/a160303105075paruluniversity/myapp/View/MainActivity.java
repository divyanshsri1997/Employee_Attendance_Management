package in.ac.a160303105075paruluniversity.myapp.View;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.ac.a160303105075paruluniversity.myapp.CheckConnection;
import in.ac.a160303105075paruluniversity.myapp.Model.EmployeeModel;
import in.ac.a160303105075paruluniversity.myapp.Model.HolidayModel;
import in.ac.a160303105075paruluniversity.myapp.R;
import in.ac.a160303105075paruluniversity.myapp.ViewModel.EmployeeViewModel;

public class MainActivity extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener {

    @BindView(R.id.nameTextView) TextView nameTextView;
    @BindView(R.id.presentTextView) TextView presentTextView;
    @BindView(R.id.absentTextView) TextView absentTextView;
    @BindView(R.id.plTextView) TextView plTextView;
    @BindView(R.id.clTextView) TextView clTextView;
    @BindView(R.id.slTextView) TextView slTextView;
    @BindView(R.id.dateTextView) TextView dateTextView;
    @BindView(R.id.dayTextView) TextView dayTextView;
    @BindView(R.id.greetTextView) TextView greetTextView;
    @BindView(R.id.holidayCalendarView) CalendarView cv;
    @BindView(R.id.weekDayTextView) TextView weekDayTextView;
    @BindView(R.id.holidayTextView) TextView holidayTextView;

    private String currentDate;

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

        ButterKnife.bind(this);

        displayCurrentDate(cv.getDate());

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        CheckConnection receiver = new CheckConnection(this) {
            @Override
            protected void onNetworkChange() {
                boolean networkStatus = isConnected();
                if (!networkStatus) {
                    //Toast.makeText(getApplicationContext(),"No internet...", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "No Internet Connection", Snackbar.LENGTH_INDEFINITE);
                    snackbar.show();
                } else {
                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "Connected...", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    updateDashboard();
                }
            }
        };
        registerReceiver(receiver, filter);
    }


    private void updateDashboard(){
        final String total = "/5";
        EmployeeViewModel employeeViewModel = new EmployeeViewModel();
        employeeViewModel.getEmployeeData().observe(this, new Observer<List<EmployeeModel>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(@Nullable List<EmployeeModel> employeeModels) {
                nameTextView.setText(employeeModels.get(0).getFirstName());
                presentTextView.setText(employeeModels.get(0).getPresent()+"/22");
                absentTextView.setText(employeeModels.get(0).getAbsent()+"/22");
                plTextView.setText(employeeModels.get(0).getPl()+total);
                slTextView.setText(employeeModels.get(0).getSl()+total);
                clTextView.setText(employeeModels.get(0).getCl()+total);
            }
        });
        employeeViewModel.getHolidayData().observe(this, new Observer<List<HolidayModel>>() {
            @Override
            public void onChanged(@Nullable List<HolidayModel> holidaysData) {
                holidayTextView.setText(getHoliday(holidaysData));
                weekDayTextView.setText(getWeekDay());
            }
        });
        //new FetchEmployeeData(nameTextView, presentTextView, absentTextView, plTextView, slTextView, clTextView)
                //.execute();
        //new FetchHoliday(dayTextView, weekDayTextView, holidayTextView, currentDate).execute();
    }

    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager)this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void displayCurrentDate(long milliSeconds){
        Date date = new Date(milliSeconds);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH");
        String dateWithTime = df.format(date);
        currentDate = dateWithTime.substring(0,10);
        String currentDay = currentDate.substring(8, 10);
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

    String getWeekDay(){
        String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(cv.getDate());
        int n = calendar.get(Calendar.DAY_OF_WEEK);
        return days[n-1];
    }

    String getHoliday(List<HolidayModel> holidaysData){
        String dayType = "WorkDay";
        for (HolidayModel holiday:holidaysData) {
            if (holiday.getDate().equals(currentDate)){
                if(holiday.getType().equals("Gazetted Holiday")){
                    dayType = holiday.getName();
                }
            }
        }
        return dayType;
    }

    public void launchTaskActivity(View view) {
        Intent intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }

    public void launchMyLeavesActivity(View view) {
        Intent intent = new Intent(this, MyLeavesActivity.class);
        startActivity(intent);
    }

    public void launchAttendanceLogActivity(View view) {
        Intent intent = new Intent(this, AttendanceLogActivity.class);
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
