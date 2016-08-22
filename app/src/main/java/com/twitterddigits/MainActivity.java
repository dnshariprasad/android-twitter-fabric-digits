package com.twitterddigits;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
    private AuthCallback authCallback = new AuthCallback() {
        @Override
        public void success(DigitsSession session, String phoneNumber) {
            // Do something with the session
            Log.d(TAG, "success: phoneNumber" + phoneNumber);
            Log.d(TAG, "success: session" + session.isValidUser());
        }

        @Override
        public void failure(DigitsException exception) {
            // Do something on failure
            Log.e(TAG, "failure: " + exception.getLocalizedMessage());
        }
    };
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(getString(R.string.twitter_key), getString(R.string.twitter_secret));
        Fabric.with(this, new TwitterCore(authConfig), new Digits());
        setContentView(R.layout.activity_main);

        DigitsAuthButton digitsButton = (DigitsAuthButton) findViewById(R.id.auth_button);
        digitsButton.setCallback(authCallback);

    }
}


