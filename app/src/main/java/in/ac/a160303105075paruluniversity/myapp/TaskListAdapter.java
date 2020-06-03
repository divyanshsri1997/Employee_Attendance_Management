package in.ac.a160303105075paruluniversity.myapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Dictionary;

import in.ac.a160303105075paruluniversity.myapp.View.ApproveLeaveActivity;

/**
 * Created by Divyansh Srivastava on 28-Apr-20.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {

    private final ArrayList<Dictionary> taskArrayList;
    private LayoutInflater mInflater;
    private Context mContext;

    public TaskListAdapter(Context context, ArrayList<Dictionary> taskArrayList) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.taskArrayList = taskArrayList;
    }

    @Override
    public TaskListAdapter.TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.task_list_item, parent, false);
        return new TaskViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(TaskListAdapter.TaskViewHolder holder, int position) {
        Dictionary applicationDetails;
        applicationDetails = taskArrayList.get(position);
        String name = (String) applicationDetails.get("applicantName");
        String applicantName = "Requested By: "+ name;
        String applicationTitle = (String) applicationDetails.get("applicationTitle");
        String requestDate = "Requested On: "+ applicationDetails.get("fromDate");
        holder.taskItemTextView.setText(applicationTitle);
        holder.fromDateTextView.setText(requestDate);
        holder.applicantNameTextView.setText(applicantName);
        holder.buttonText.setText(setButtonText(applicationTitle));
    }
    private static String setButtonText(String applicationTitle){
        char[] title = applicationTitle.toCharArray();
        StringBuilder buttonText = new StringBuilder(String.valueOf(Character.toUpperCase(title[0])));
        for(int i = 1;i<title.length; i++){
            if(title[i] == ' '){
                buttonText.append(Character.toUpperCase(title[i + 1]));
            }
        }
        return buttonText.toString();
    }


    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final TextView taskItemTextView;
        final Button buttonText;
        final TextView fromDateTextView;
        final TextView applicantNameTextView;
        final TaskListAdapter mAdapter;

        TaskViewHolder(View itemView, TaskListAdapter adapter)  {
            super(itemView);
            taskItemTextView = itemView.findViewById(R.id.task);
            fromDateTextView = itemView.findViewById(R.id.requestDateTextView);
            applicantNameTextView = itemView.findViewById(R.id.applicantNameTextView);
            buttonText = itemView.findViewById(R.id.imageButton);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, ApproveLeaveActivity.class);
            intent.putExtra("position",mPosition);
            mContext.startActivity(intent);
        }
    }


}
