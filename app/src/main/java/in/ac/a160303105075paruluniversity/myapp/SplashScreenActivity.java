package in.ac.a160303105075paruluniversity.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.WindowDecorActionBar;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    long delay = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash_screen);

        Timer runSplash = new Timer();
        TimerTask showSplash = new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
                startActivity(intent);
            }
        };
        runSplash.schedule(showSplash,delay);
    }
}
