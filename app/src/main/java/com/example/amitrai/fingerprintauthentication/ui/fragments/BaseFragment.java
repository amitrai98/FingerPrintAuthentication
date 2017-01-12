package com.example.amitrai.fingerprintauthentication.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amitrai.fingerprintauthentication.ui.activity.BaseActivity;
import com.example.amitrai.fingerprintauthentication.utility.AppUtils;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public abstract class BaseFragment extends Fragment {

    BaseActivity activity;

    AppUtils utility;

    public BaseFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActivity() instanceof  BaseActivity)
            activity = (BaseActivity) getActivity();

        utility = new AppUtils();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return null;
    }

    public abstract void initView(View view);

}
