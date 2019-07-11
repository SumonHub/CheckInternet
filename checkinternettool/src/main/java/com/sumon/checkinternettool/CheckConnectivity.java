package com.sumon.checkinternettool;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by SumOn on 7/11/2019.
 */

public class CheckConnectivity extends Service {
    private static final String TAG = "CheckConnectivity";
    private static final String CONNECTIVITY_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    public static MutableLiveData<Boolean> connectionStatus = new MutableLiveData<>();
    BroadcastReceiver receiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand ()");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (CONNECTIVITY_CHANGE_ACTION.equals(intent.getAction())) {
                    boolean noNetworkStatus = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
                    if (noNetworkStatus) {
                        connectionStatus.setValue(false);
                        Log.d(TAG, "onReceive: false");
                    } else {
                        connectionStatus.setValue(true);
                        Log.d(TAG, "onReceive: true");
                    }
                } else connectionStatus.setValue(true);
            }
        };

        registerReceiver(receiver, intentFilter);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}