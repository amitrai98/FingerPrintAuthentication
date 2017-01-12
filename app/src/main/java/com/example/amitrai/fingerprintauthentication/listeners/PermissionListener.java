package com.example.amitrai.fingerprintauthentication.listeners;

public interface PermissionListener {
     void onPermissionGranted(int permission_code);
     void onPermissionDenied(int permission_code);
}
