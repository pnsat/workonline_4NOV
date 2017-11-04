/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.os.Handler
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 */
package com.daydr3am.OWS;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.MainPage;

public class Receive
extends IORootActivity {
    public int moneyInput = 20;

    @Override
    public void changeLanguage(int n) {
    }

    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903043);
        super.onCreate(bundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.changeLanguage(switchID);
        Log.d((String)"debug", (String)"onResume active");
        new Handler().postDelayed(new Runnable(){

            public void run() {
                if (Receive.this.activityActive) {
                    Intent intent = new Intent(Receive.this.getApplicationContext(), (Class)MainPage.class);
                    intent.setFlags(67108864);
                    Receive.this.startActivityForResult(intent, 0);
                }
            }
        }, 60000);
    }

}

