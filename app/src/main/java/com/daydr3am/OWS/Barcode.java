/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.ServiceConnection
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.IBinder
 *  android.util.Log
 *  android.view.View
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.TextView
 *  java.lang.Boolean
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 */
package com.daydr3am.OWS;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.Loading;
import com.daydr3am.lib.AudioDemo;
import com.daydr3am.lib.IOService;
import com.daydr3am.lib.IOServiceBV20;

public class Barcode
extends IORootActivity
implements IOService.ReceiveMoney {
    EditText barcode;
    Boolean isDisplay;
    public ServiceConnection service;
    IOService.SettingService setting;

    public Barcode() {
        this.service = new ServiceConnection(){

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Barcode.this.setting = (IOService.SettingService)iBinder;
                Barcode.this.setting.serviceSetup(Barcode.this);
                Barcode.this.setting.setupReceiveMoney(Barcode.this);
            }

            public void onServiceDisconnected(ComponentName componentName) {
                Log.v((String)"hello", (String)"serviced");
            }
        };
    }

    @Override
    public void changeLanguage(int n) {
        if (this.landTextShow != null) {
            this.landTextShow.setText((CharSequence)"\u0e2a\u0e41\u0e01\u0e19\u0e1a\u0e32\u0e23\u0e4c\u0e42\u0e04\u0e49\u0e14");
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903040);
        super.onCreate(bundle);
        this.barcode = (EditText)this.findViewById(2131361795);
        this.bindService(new Intent((Context)this, (Class)IOServiceBV20.class), this.service, 1);
        this.next.setVisibility(8);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unbindService(this.service);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.isDisplay = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.barcode.setText((CharSequence)"");
        this.isDisplay = true;
        AudioDemo.Sound().playSound("f60");
    }

    @Override
    public void receiveBank(int n) {
    }

    @Override
    public void receiveCoin(int n) {
    }

    @Override
    public void receiveText(final String string2) {
        this.barcode.setText((CharSequence)string2);
        Log.v((String)"hello", (String)("text " + string2));
        new Handler().postDelayed(new Runnable(){

            public void run() {
                if (Barcode.this.isDisplay.booleanValue()) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("Service", 18);
                    bundle.putString("DataBarcode", string2);
                    Intent intent = new Intent(Barcode.this.getBaseContext(), (Class)Loading.class);
                    intent.putExtras(bundle);
                    Barcode.this.startActivity(intent);
                }
            }
        }, 1000);
    }

}

