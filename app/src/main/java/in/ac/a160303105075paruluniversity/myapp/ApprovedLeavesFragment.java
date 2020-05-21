package in.ac.a160303105075paruluniversity.myapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ApprovedLeavesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ApprovedLeavesAdapter mAdapter;
    private final LinkedList<String> leaveList = new LinkedList<>();
    public ApprovedLeavesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_approved_leaves, container, false);
        mRecyclerView = view.findViewById(R.id.approvedLeaveRecyclerView);
        leaveList.add(new MyLeavesData().approvedLeaveData1.get("Reason"));
        leaveList.add(new MyLeavesData().approvedLeaveData2.get("Reason"));
        mAdapter = new ApprovedLeavesAdapter(this.getActivity(),leaveList,new MyLeavesData());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        return view;
    }
}
