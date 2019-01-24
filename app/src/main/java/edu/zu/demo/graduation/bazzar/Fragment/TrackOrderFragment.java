package edu.zu.demo.graduation.bazzar.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.zu.demo.graduation.bazzar.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrackOrderFragment extends Fragment {


    public TrackOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_track_order, container, false);
    }

}
