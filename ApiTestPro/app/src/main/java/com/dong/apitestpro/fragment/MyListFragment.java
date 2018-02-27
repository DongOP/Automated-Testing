package com.dong.apitestpro.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dong.apitestpro.R;
import com.dong.apitestpro.activity.BaseMethodActivity;

/**
 * Created by Dong on 2017/4/5 0005.
 */

public class MyListFragment extends Fragment{

    private TextView mRequestMethod;
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        mRequestMethod = (TextView) rootView.findViewById(R.id.request_method);

        mRequestMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), BaseMethodActivity.class));
            }
        });
    }
}
