package in.ac.a160303105075paruluniversity.myapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Dictionary;
import java.util.LinkedList;

public class ApprovedLeavesAdapter extends RecyclerView.Adapter<ApprovedLeavesAdapter.ApprovedLeavesViewHolder> {

    private final LinkedList<String> leaveList;
    private final MyLeavesData leavesData;
    private LayoutInflater mInflater;
    private Context mContext;

    ApprovedLeavesAdapter(Context context, LinkedList<String> leavesList, MyLeavesData leavesData){
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.leaveList = leavesList;
        this.leavesData = leavesData;
    }

    @Override
    public ApprovedLeavesAdapter.ApprovedLeavesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.approved_leaves_list, parent, false);
        return new ApprovedLeavesViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ApprovedLeavesAdapter.ApprovedLeavesViewHolder holder, int position) {
        Dictionary<String,String> leaves;
        String reason = leaveList.get(position);
        leaves = leavesData.getLeaveDetails(reason);
        holder.leaveItemTextView.setText(reason);
        if(leaves.get("type") == "SL"){
            holder.leaveButton.setTextColor(Color.RED);
        }
        holder.leaveButton.setText(leaves.get("type"));
        holder.leaveDateTextView.setText(leaves.get("date"));
    }

    @Override
    public int getItemCount() {
        return leaveList.size();
    }

    class ApprovedLeavesViewHolder extends RecyclerView.ViewHolder{

        final TextView leaveItemTextView,leaveDateTextView;
        final Button leaveButton;
        final ApprovedLeavesAdapter mAdapter;
        ApprovedLeavesViewHolder(View mItemView, ApprovedLeavesAdapter approvedLeavesAdapter) {
            super(mItemView);
            leaveItemTextView = mItemView.findViewById(R.id.approvedLeaveTextView);
            leaveButton = mItemView.findViewById(R.id.leaveBtn);
            leaveDateTextView = mItemView.findViewById(R.id.leaveDateTextView);
            this.mAdapter = approvedLeavesAdapter;
        }
    }
}
