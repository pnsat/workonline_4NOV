/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package com.daydr3am.lib;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.daydr3am.OWS.MainPage;

public class ScreenReceiver
extends BroadcastReceiver {
    Activity activity;

    public ScreenReceiver(Activity activity) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        activity.registerReceiver((BroadcastReceiver)this, intentFilter);
        this.activity = activity;
    }

    public void onReceive(Context context, Intent intent) {
        Log.v((String)"test", (String)"call");
        if (intent.getAction().equals((Object)"android.intent.action.SCREEN_OFF")) {
            Intent intent2 = new Intent(this.activity.getBaseContext(), (Class)MainPage.class);
            intent2.setFlags(67108864);
            this.activity.startActivity(intent2);
            return;
        }
        intent.getAction().equals((Object)"android.intent.action.SCREEN_ON");
    }
}

