package in.ac.a160303105075paruluniversity.myapp;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Dictionary;
import java.util.LinkedList;

/**
 * Created by Divyansh Srivastava on 28-Apr-20.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {

    private final LinkedList<String> taskList;
    private final ApplicantLeaveData applicantData;
    private LayoutInflater mInflater;
    Dictionary<String,String> applicationDetails;
    private TaskActivity ta;
    private Context mContext;

    public TaskListAdapter(Context context, LinkedList<String> taskList, ApplicantLeaveData applicantData) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.taskList = taskList;
        this.applicantData = applicantData;
    }

    @Override
    public TaskListAdapter.TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.task_list_item, parent, false);
        return new TaskViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(TaskListAdapter.TaskViewHolder holder, int position) {
        String name = taskList.get(position);
        String applicantName = "Requested By: "+ name;
        applicationDetails = applicantData.getApplicationDetails(name);
        String applicationTitle = applicationDetails.get("applicationTitle");
        String requestDate = "Requested On: "+ applicationDetails.get("fromDate");
        holder.taskItemTextView.setText(applicationTitle);
        holder.fromDateTextView.setText(requestDate);
        holder.applicantNameTextView.setText(applicantName);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //public static final String applicantData = "in.ac.a160303105075paruluniversity.myapp.extra.MESSAGE";
        public final TextView taskItemTextView;
        public final Button buttonText;
        public final TextView fromDateTextView;
        public final TextView applicantNameTextView;
        final TaskListAdapter mAdapter;

        public TaskViewHolder(View itemView, TaskListAdapter adapter)  {
            super(itemView);
            taskItemTextView = (TextView) itemView.findViewById(R.id.task);
            fromDateTextView = itemView.findViewById(R.id.requestDateTextView);
            applicantNameTextView = itemView.findViewById(R.id.applicantNameTextView);
            buttonText =(Button) itemView.findViewById(R.id.imageButton);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();
            Intent intent = new Intent(mContext,ApproveLeaveActivity.class);
            //intent.putExtra(applicantData, (Parcelable) applicationDetails);
            mContext.startActivity(intent);
        }
    }


}
