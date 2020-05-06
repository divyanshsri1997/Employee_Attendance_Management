package in.ac.a160303105075paruluniversity.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Dictionary;

public class ApproveLeaveActivity extends AppCompatActivity {

    private Dictionary<String,String> applicantDetails;
    private TextView nameTextView,fromDateTextView,toDateTextView,noOfDaysTextView,reasonTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_leave);
        //Intent intent = getIntent();
        //applicantDetails = intent.getParcelableExtra("applicantData");
        nameTextView = findViewById(R.id.applicantName);
        fromDateTextView = findViewById(R.id.fromDate);
        toDateTextView = findViewById(R.id.toDate);
        noOfDaysTextView = findViewById(R.id.noOfDays);
        reasonTextView = findViewById(R.id.reason);

//        String name = applicantDetails.get("applicantName");
//        nameTextView.setText(name);
//        String fromDate = applicantDetails.get("fromDate");
//        fromDateTextView.setText(fromDate);
//        String toDate = applicantDetails.get("ToDate");
//        toDateTextView.setText(toDate);
//        String noOfDays = applicantDetails.get("noOfDays");
//        noOfDaysTextView.setText(noOfDays);
//        String reason = applicantDetails.get("reason");
//        reasonTextView.setText(reason);

    }
}
