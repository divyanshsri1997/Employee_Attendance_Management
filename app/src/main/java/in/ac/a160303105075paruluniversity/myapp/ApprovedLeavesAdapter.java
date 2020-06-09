package in.ac.a160303105075paruluniversity.myapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Dictionary;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ApprovedLeavesAdapter extends RecyclerView.Adapter<ApprovedLeavesAdapter.ApprovedLeavesViewHolder> {

    private final ArrayList<Dictionary> leaveArrayList;
    private LayoutInflater mInflater;
    private Context mContext;

    public ApprovedLeavesAdapter(Context context, ArrayList<Dictionary> leaveArrayList){
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.leaveArrayList = leaveArrayList;
    }

    @Override
    public ApprovedLeavesAdapter.ApprovedLeavesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.approved_leaves_list, parent, false);
        return new ApprovedLeavesViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ApprovedLeavesAdapter.ApprovedLeavesViewHolder holder, int position) {
        Dictionary leaves;
        leaves = leaveArrayList.get(position);
        holder.leaveItemTextView.setText((String)leaves.get("Reason"));
        if(leaves.get("type").equals("SL")){
            holder.leaveButton.setTextColor(Color.RED);
        }else if(leaves.get("type").equals("PL")){
            holder.leaveButton.setTextColor(Color.GREEN);
        }else{
            holder.leaveButton.setTextColor(Color.BLUE);
        }
        holder.leaveButton.setText((String)leaves.get("type"));
        holder.leaveDateTextView.setText((String)leaves.get("date"));
    }

    @Override
    public int getItemCount() {
        return leaveArrayList.size();
    }

    static class ApprovedLeavesViewHolder extends RecyclerView.ViewHolder{

        final ApprovedLeavesAdapter mAdapter;
        @BindView(R.id.approvedLeaveTextView) TextView leaveItemTextView;
        @BindView(R.id.leaveBtn) Button leaveButton;
        @BindView(R.id.leaveDateTextView) TextView leaveDateTextView;
        ApprovedLeavesViewHolder(View mItemView, ApprovedLeavesAdapter approvedLeavesAdapter) {
            super(mItemView);
            ButterKnife.bind(this,mItemView);
            this.mAdapter = approvedLeavesAdapter;
        }
    }
}
