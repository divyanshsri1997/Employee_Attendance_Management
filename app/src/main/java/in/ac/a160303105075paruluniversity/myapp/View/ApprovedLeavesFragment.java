package in.ac.a160303105075paruluniversity.myapp.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Dictionary;

import butterknife.ButterKnife;
import in.ac.a160303105075paruluniversity.myapp.ApprovedLeavesAdapter;
import in.ac.a160303105075paruluniversity.myapp.Model.MyLeavesData;
import in.ac.a160303105075paruluniversity.myapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ApprovedLeavesFragment extends Fragment {


    private final ArrayList<Dictionary> leaveArrayList = new ArrayList<>();
    public ApprovedLeavesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_approved_leaves, container, false);
        RecyclerView mRecyclerView = view.findViewById(R.id.approvedLeaveRecyclerView);
        leaveArrayList.add(new MyLeavesData().approvedLeaveData1);
        leaveArrayList.add(new MyLeavesData().approvedLeaveData2);
        ApprovedLeavesAdapter mAdapter = new ApprovedLeavesAdapter(this.getActivity(), leaveArrayList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        return view;
    }
}
