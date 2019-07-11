package com.sumon.checkinternet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

/**
 * Created by SumOn on 7/11/2019.
 */

class Utils {

    private static AlertDialog dialog;
    private static boolean isShowing = false;

    static void showDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Opps!");
        builder.setMessage("The Internet? Is that thing still around?");
        // add the buttons
        builder.setPositiveButton("go to check", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        });

        dialog = builder.create();
        dialog.show();
        if (dialog.isShowing()) {
            isShowing = true;
        }
    }

    static void stopDialog() {
        if (isShowing) {
            dialog.dismiss();
        }
    }
}
