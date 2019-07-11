package com.sumon.checkinternet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.sumon.checkinternettool.CheckConnectivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(MainActivity.this, CheckConnectivity.class));

        CheckConnectivity.connectionStatus.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.d(TAG, "connectionStatus onChanged: " + aBoolean);
                if (!aBoolean) {
                    Utils.showDialog(MainActivity.this);
                } else {
                    Utils.stopDialog();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, CheckConnectivity.class));
    }
}
