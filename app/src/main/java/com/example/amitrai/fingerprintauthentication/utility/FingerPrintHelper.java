package com.example.amitrai.fingerprintauthentication.utility;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.amitrai.fingerprintauthentication.ui.activity.BaseActivity;
import com.example.amitrai.fingerprintauthentication.ui.fragments.HomeFragment;

import static android.content.ContentValues.TAG;

/**
 * Created by amitrai on 12/1/17.
 * see more at www.github.com/amitrai98
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerPrintHelper extends FingerprintManager.AuthenticationCallback {

    private BaseActivity context;

    // Constructor
    public FingerPrintHelper(BaseActivity mContext) {
        context = mContext;
    }

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        this.update("Fingerprint Authentication error\n" + errString);
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        this.update("Fingerprint Authentication help\n" + helpString);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update("Fingerprint Authentication failed.");
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {

        context.replaceFragment(new HomeFragment(), true);
    }

    private void update(String e){
        Log.e(TAG, "got error "+e);
        Toast.makeText(context, e, Toast.LENGTH_SHORT).show();
    }
}