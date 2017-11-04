/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.ServiceConnection
 *  android.content.SharedPreferences
 *  android.graphics.drawable.Drawable
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.IBinder
 *  android.util.Log
 *  android.view.LayoutInflater
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.Window
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.TextView
 *  java.io.BufferedReader
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.InputStream
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.lang.ArrayIndexOutOfBoundsException
 *  java.lang.Boolean
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Void
 *  java.nio.charset.Charset
 */
package com.daydr3am.OWS;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.InputPassword;
import com.daydr3am.lib.AudioDemo;
import com.daydr3am.lib.CallImage;
import com.daydr3am.lib.IOService;
import com.daydr3am.lib.IOServiceBV20;
import com.daydr3am.lib.LogChecker;
import com.daydr3am.lib.LogController;
import com.daydr3am.lib.Service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public class SampleActivity
extends IORootActivity
implements View.OnTouchListener {
    public static boolean showAlert;
    public static boolean stopIO;
    float X = 0.0f;
    float Y = 0.0f;
    String error = "\u0e41\u0e08\u0e49\u0e07\u0e1b\u0e31\u0e0d\u0e2b\u0e32 084-555-7930";
    Boolean isBV20 = false;
    public ServiceConnection service;
    IOService.SettingService setting;
    TextView text_MS3;
    Thread thread;

    static {
        stopIO = false;
        showAlert = false;
    }

    public SampleActivity() {
        this.service = new ServiceConnection(){

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                SampleActivity.this.setting = (IOService.SettingService)iBinder;
                SampleActivity.this.setting.serviceSetup(SampleActivity.this);
            }

            public void onServiceDisconnected(ComponentName componentName) {
            }
        };
    }

    public static String convertStreamToString(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader((Reader)new InputStreamReader(inputStream, Charset.forName((String)"UTF-8")));
        StringBuilder stringBuilder = new StringBuilder();
        do {
            String string2;
            if ((string2 = bufferedReader.readLine()) == null) {
                bufferedReader.close();
                return stringBuilder.toString();
            }
            stringBuilder.append(string2).append("\n");
        } while (true);
    }

    public static String getStringFromFile(String string2) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File(string2));
        String string3 = SampleActivity.convertStreamToString((InputStream)fileInputStream);
        fileInputStream.close();
        return string3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void onCreate(Bundle bundle) {
        this.getWindow().addFlags(128);
        View view = this.getLayoutInflater().inflate(2130903055, null);
        this.setContentView(view);
        Service.pref = this.getPreferences(0);
        view.setOnTouchListener((View.OnTouchListener)this);
        super.onCreate(bundle);
        this.findViewById(2131361871).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent intent = new Intent((Context)SampleActivity.this, (Class)InputPassword.class);
                Bundle bundle = new Bundle();
                bundle.putInt("PassType", 1);
                intent.putExtras(bundle);
                SampleActivity.this.startActivity(intent);
            }
        });
        try {
            this.error = SampleActivity.getStringFromFile("/mnt/sdcard/Resource/Error.txt");
        }
        catch (Exception var3_3) {
            this.error = "\ufffd?\ufffd?\ufffd?\ufffd? 084-555-7930";
        }
        Log.v((String)"hello", (String)("error message " + this.error));
        this.back.setVisibility(8);
        this.next.setVisibility(8);
        this.cancel.setVisibility(8);
        Log.v((String)"hello", (String)"preview create");
        if (!this.getSharedPreferences("hello", 0).getBoolean("isICT", false)) {
            this.isBV20 = true;
        }
        if (this.isBV20.booleanValue()) {
            IOServiceBV20.uselight = true;
            return;
        }
        IOService.uselight = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogChecker.isSplash = false;
        Log.v((String)"hello", (String)"preview pause");
        this.thread.interrupt();
        this.unbindService(this.service);
        if (!this.getSharedPreferences("hello", 0).getBoolean("isICT", false)) {
            this.isBV20 = true;
        }
        if (this.isBV20.booleanValue()) {
            IOServiceBV20.uselight = false;
            return;
        }
        IOService.uselight = false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.v((String)"hello", (String)"preview resume");
        LogChecker.isSplash = true;
        final SharedPreferences sharedPreferences = this.getSharedPreferences("hello", 0);
        int n = sharedPreferences.getInt("count", 0);
        final int[] arrn = new int[n];
        int n2 = 0;
        do {
            if (n2 >= n) {
                if (!this.getSharedPreferences("hello", 0).getBoolean("isICT", false)) {
                    this.isBV20 = true;
                    Log.v((String)"hello", (String)"isbv20");
                    this.bindService(new Intent((Context)this, (Class)IOServiceBV20.class), this.service, 1);
                } else {
                    Log.v((String)"hello", (String)"isnotbv20");
                    this.bindService(new Intent((Context)this, (Class)IOService.class), this.service, 1);
                }
                this.findViewById(2131361870).setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        Intent intent = new Intent((Context)SampleActivity.this, (Class)InputPassword.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("PassType", 1);
                        intent.putExtras(bundle);
                        SampleActivity.this.startActivity(intent);
                    }
                });
                this.thread = new Thread(new Runnable(){
                    int index;

                    /*
                     * Enabled aggressive block sorting
                     * Enabled unnecessary exception pruning
                     * Enabled aggressive exception aggregation
                     */
                    public void run() {
                        try {
                            do {
                                try {
                                    while (arrn.length == 0) {
                                        Thread.sleep((long)30000);
                                    }
                                }
                                catch (ArrayIndexOutOfBoundsException var2_5) {
                                    var2_5.printStackTrace();
                                    Thread.sleep((long)5000);
                                    continue;
                                }
                                Log.v((String)"hello", (String)"test 1");
                                String[] arrstring = sharedPreferences.getString("bannerURL" + this.index, "").split("/");
                                String string2 = arrstring[-1 + arrstring.length];
                                new LongOperation(SampleActivity.this, null).execute((Object[])new String[]{string2});
                                Log.v((String)"hello", (String)"test 2");
                                String[] arrstring2 = sharedPreferences.getString("soundURL" + this.index, "").split("/");
                                String string3 = arrstring2[-1 + arrstring2.length];
                                if (string3.length() > 0) {
                                    AudioDemo.Sound().playAdSound(string3);
                                }
                                Log.v((String)"hello", (String)("test 3 " + sharedPreferences.getString(new StringBuilder("soundURL").append(this.index).toString(), "")));
                                if (arrn.length > this.index) {
                                    Thread.sleep((long)(1000 * arrn[this.index]));
                                }
                                this.index = 1 + this.index;
                                if (this.index < arrn.length) continue;
                                this.index = 0;
                                continue;
                                break;
                            } while (true);
                        }
                        catch (InterruptedException var1_6) {
                            var1_6.printStackTrace();
                            return;
                        }
                    }
                });
                this.thread.start();
                TextView textView = (TextView)this.findViewById(2131361873);
                Log.v((String)"hello", (String)("final " + textView.isFocused()));
                return;
            }
            arrn[n2] = sharedPreferences.getInt("timeURL" + n2, 0);
            ++n2;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0: {
                this.X = motionEvent.getX();
                this.Y = motionEvent.getY();
            }
            default: {
                return true;
            }
            case 1: 
        }
        new Handler().post(new Runnable(){

            public void run() {
                if (LogController.checkFileList() != 0) {
                    SampleActivity.showAlert = true;
                    new Thread(new Runnable(new AlertDialog.Builder((Context)SampleActivity.this).setTitle((CharSequence)"").setMessage((CharSequence)("internet \u0e02\u0e31\u0e14\u0e02\u0e49\u0e2d\u0e07  " + SampleActivity.this.error)).setNegativeButton(17039369, new DialogInterface.OnClickListener(){

                        public void onClick(DialogInterface dialogInterface, int n) {
                            SampleActivity.showAlert = false;
                        }
                    }).show()){
                        private final /* synthetic */ AlertDialog val$dialog;

                        /*
                         * Enabled aggressive block sorting
                         * Enabled unnecessary exception pruning
                         * Enabled aggressive exception aggregation
                         */
                        public void run() {
                            try {
                                Thread.sleep((long)13000);
                            }
                            catch (InterruptedException var1_1) {
                                var1_1.printStackTrace();
                            }
                            if (this.val$dialog.isShowing()) {
                                this.val$dialog.dismiss();
                            }
                            SampleActivity.showAlert = false;
                        }
                    }).start();
                    return;
                }
                SampleActivity.this.finish();
            }

        });
        return true;
    }

    private class LongOperation
    extends AsyncTask<String, Void, String> {
        String name;
        final /* synthetic */ SampleActivity this$0;

        private LongOperation(SampleActivity sampleActivity) {
            this.this$0 = sampleActivity;
            this.name = "";
        }

        /* synthetic */ LongOperation(SampleActivity sampleActivity, LongOperation longOperation) {
            super(sampleActivity);
        }

        protected /* varargs */ String doInBackground(String ... arrstring) {
            this.name = arrstring[0];
            Log.v((String)"hello", (String)("doin " + this.name));
            this.publishProgress((Object[])new Void[0]);
            return "Executed";
        }

        protected void onPostExecute(String string2) {
        }

        protected void onPreExecute() {
        }

        protected /* varargs */ void onProgressUpdate(Void ... arrvoid) {
            ((ImageView)this.this$0.findViewById(2131361794)).setImageDrawable(CallImage.getBackground(this.name));
        }
    }

}

