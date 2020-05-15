package in.ac.a160303105075paruluniversity.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


import java.util.LinkedList;

public class TaskActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TaskListAdapter mAdapter;
    private final LinkedList<String> taskList = new LinkedList<>();
    public static int taskCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        taskList.add(new ApplicantLeaveData().applicantData1.get("applicantName"));
        taskList.add(new ApplicantLeaveData().applicantData2.get("applicantName"));
        taskCount = taskList.size();
        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new TaskListAdapter(this,taskList,new ApplicantLeaveData());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
