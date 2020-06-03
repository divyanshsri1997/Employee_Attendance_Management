package in.ac.a160303105075paruluniversity.myapp.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.ac.a160303105075paruluniversity.myapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RejectedLeavesFragment extends Fragment {


    public RejectedLeavesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rejected_leaves, container, false);
    }

}
