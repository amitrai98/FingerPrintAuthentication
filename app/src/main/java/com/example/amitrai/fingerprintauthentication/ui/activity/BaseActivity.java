package com.example.amitrai.fingerprintauthentication.ui.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.amitrai.fingerprintauthentication.R;
import com.example.amitrai.fingerprintauthentication.ui.fragments.BaseFragment;
import com.example.amitrai.fingerprintauthentication.listeners.PermissionListener;

import butterknife.Bind;

import static android.content.ContentValues.TAG;

public abstract class BaseActivity extends AppCompatActivity {

    @Bind(R.id.container)
    FrameLayout container;

    private static PermissionListener permissionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public abstract void initView();

    /**
     * replace the fragments and store it in backstack
     * @param fragment to be changed
     * @param saveInBackStack should store in back stack
     */
    public void replaceFragment (BaseFragment fragment, boolean saveInBackStack){
        String backStateName =  fragment.getClass().getName();

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped){ //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.container, fragment, backStateName);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            if (saveInBackStack)
                ft.addToBackStack(backStateName);
            ft.commit();
        }else
            Log.e(TAG, "data");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if(permissionListener != null)
                permissionListener.onPermissionGranted(requestCode);

        } else {
            if(permissionListener != null)
                permissionListener.onPermissionDenied(requestCode);
        }


        switch (requestCode) {
            case 101: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(permissionListener != null)
                        permissionListener.onPermissionGranted(requestCode);

                } else {
                    if(permissionListener != null)
                        permissionListener.onPermissionDenied(requestCode);
                }
            }
        }
    }

    /**
     * sets permission listeners for future call backs.
     * @param listener for permissions
     */
    public static void setPermissionListener(@NonNull PermissionListener listener){
        permissionListener = listener;
    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 1){
            super.onBackPressed();
        }else
            getFragmentManager().popBackStack();



    }
}
