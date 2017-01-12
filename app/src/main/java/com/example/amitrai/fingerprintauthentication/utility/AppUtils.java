package com.example.amitrai.fingerprintauthentication.utility;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.amitrai.fingerprintauthentication.listeners.PermissionListener;
import com.example.amitrai.fingerprintauthentication.ui.activity.BaseActivity;

import static android.content.ContentValues.TAG;

/**
 * Created by amitrai on 11/1/17.
 * see more at www.github.com/amitrai98
 */

public class AppUtils {

    /**
     * checks for a permission
     */
    public void checkPermission(Activity activity, String permission , PermissionListener listener){
        BaseActivity.setPermissionListener(listener);
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(permission)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                listener.onPermissionGranted(102);
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(activity, new String[]{permission},
                        101);
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            listener.onPermissionGranted(102);
        }
    }
}
