package com.example.amitrai.fingerprintauthentication.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.amitrai.fingerprintauthentication.R;
import com.example.amitrai.fingerprintauthentication.ui.fragments.LoginFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @Bind(R.id.container)
    FrameLayout container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_acitivity);

        initView();

        replaceFragment(new LoginFragment(), true);
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}
