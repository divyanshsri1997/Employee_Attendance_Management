package in.ac.a160303105075paruluniversity.myapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Dictionary;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.ac.a160303105075paruluniversity.myapp.R;
import in.ac.a160303105075paruluniversity.myapp.View.TaskActivity;

public class ApproveLeaveActivity extends AppCompatActivity {

    private Dictionary applicantDetails;
    @BindView(R.id.applicantName)TextView nameTextView;
    @BindView(R.id.fromDate)TextView fromDateTextView;
    @BindView(R.id.toDate)TextView toDateTextView;
    @BindView(R.id.noOfDays)TextView noOfDaysTextView;
    @BindView(R.id.reason)TextView reasonTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_leave);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        ArrayList<Dictionary> applicantDetailsArrayList = new TaskActivity().getTaskList();
        int position = intent.getIntExtra("position",0);
        applicantDetails = applicantDetailsArrayList.get(position);

        displayApplicationDetails();

    }

    void displayApplicationDetails(){
        nameTextView.setText((String)applicantDetails.get("applicantName"));
        fromDateTextView.setText((String) applicantDetails.get("fromDate"));
        toDateTextView.setText((String) applicantDetails.get("toDate"));
        noOfDaysTextView.setText((String) applicantDetails.get("noOfDays"));
        reasonTextView.setText((String) applicantDetails.get("reason"));
    }

}
