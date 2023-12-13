package com.ripalnakiya.fragmentdatapassing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        String str = "";
        if (getArguments() != null) {
            str = getArguments().getString("num");
        }

        TextView textView1 = view.findViewById(R.id.textView1);
        textView1.setText(str);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    ((MainActivity) getActivity()).makeToast();
                }
            }
        });

        Button sendButton = view.findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the MainActivity, Since It contains the implementation of DataPassListener
                if (getActivity() != null) {
                    DataPassListener dataPassListener = ((MainActivity) getActivity()).dataPassListener;
                    dataPassListener.onDataPass("Hello");
                }
            }
        });

        return view;
    }
}