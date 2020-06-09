package in.ac.a160303105075paruluniversity.myapp.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Dictionary;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.ac.a160303105075paruluniversity.myapp.Model.ApplicantLeaveData;
import in.ac.a160303105075paruluniversity.myapp.R;
import in.ac.a160303105075paruluniversity.myapp.TaskListAdapter;

public class TaskActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)RecyclerView mRecyclerView;
    private static ArrayList<Dictionary> taskArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ButterKnife.bind(this);
        taskArrayList = new ArrayList<>();
        taskArrayList.add(new ApplicantLeaveData().applicantData1);
        taskArrayList.add(new ApplicantLeaveData().applicantData2);
        TaskListAdapter mAdapter = new TaskListAdapter(this, taskArrayList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public ArrayList<Dictionary> getTaskList(){
        return taskArrayList;
    }
}
