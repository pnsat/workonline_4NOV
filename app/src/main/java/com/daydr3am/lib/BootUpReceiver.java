/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.String
 */
package com.daydr3am.lib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.daydr3am.OWS.SampleActivity;

public class BootUpReceiver
extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.v((String)"test", (String)"Hello");
        Intent intent2 = new Intent(context, (Class)SampleActivity.class);
        intent2.addFlags(268435456);
        context.startActivity(intent2);
    }
}

