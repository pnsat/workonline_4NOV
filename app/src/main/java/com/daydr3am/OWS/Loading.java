/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.ServiceConnection
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.pm.PackageManager
 *  android.graphics.Typeface
 *  android.graphics.drawable.Drawable
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.IBinder
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.CheckBox
 *  android.widget.CompoundButton
 *  android.widget.CompoundButton$OnCheckedChangeListener
 *  android.widget.TextView
 *  java.io.BufferedReader
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.IOException
 *  java.io.InputStream
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Float
 *  java.lang.Integer
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.Process
 *  java.lang.Runnable
 *  java.lang.Runtime
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.lang.Void
 *  java.nio.charset.Charset
 *  java.util.Date
 */
package com.daydr3am.OWS;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.daydr3am.OWS.Detail;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.InputCoin;
import com.daydr3am.OWS.InputCoinUtil;
import com.daydr3am.OWS.MainPage;
import com.daydr3am.OWS.MessageTopup;
import com.daydr3am.OWS.NumpadUtil;
import com.daydr3am.OWS.Record;
import com.daydr3am.OWS.SampleActivity;
import com.daydr3am.OWS.SetMachine;
import com.daydr3am.lib.AudioDemo;
import com.daydr3am.lib.CallImage;
import com.daydr3am.lib.IOService;
import com.daydr3am.lib.IOServiceBV20;
import com.daydr3am.lib.LogController;
import com.daydr3am.lib.Service;
import ioio.lib.api.exception.ConnectionLostException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Date;

public class Loading
extends IORootActivity {
    int connectionLostCount = 0;
    int delayValue = 1000;
    String error = "\u0e41\u0e08\u0e49\u0e07\u0e1b\u0e31\u0e0d\u0e2b\u0e32 084-555-7930";
    boolean isbind = false;
    Bundle printBundle;
    public Bundle savedInstance;
    public ServiceConnection service;
    IOService.SettingService setting;

    public Loading() {
        this.service = new ServiceConnection(){

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Loading.this.setting = (IOService.SettingService)iBinder;
                Loading.this.setting.serviceSetup(Loading.this);
            }

            public void onServiceDisconnected(ComponentName componentName) {
                Log.v((String)"hello", (String)"serviced");
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

    private void delayback() {
        this.runOnUiThread(new Runnable(){

            public void run() {
                new Handler().postDelayed(new Runnable(){

                    public void run() {
                        if (.access$0(()11.this).activityActive && !this.getClass().equals((Object)MainPage.class) && !this.getClass().equals((Object)SampleActivity.class)) {
                            Intent intent = new Intent(11.this.Loading.this.getBaseContext(), (Class)MainPage.class);
                            intent.setFlags(67108864);
                            11.this.Loading.this.startActivity(intent);
                        }
                    }
                }, 13000);
            }

        });
    }

    private void delayback2() {
        this.runOnUiThread(new Runnable(){

            public void run() {
                new Handler().postDelayed(new Runnable(){

                    public void run() {
                        if (.access$0(()12.this).activityActive && !this.getClass().equals((Object)MainPage.class) && !this.getClass().equals((Object)SampleActivity.class)) {
                            Intent intent = new Intent(12.this.Loading.this.getBaseContext(), (Class)MainPage.class);
                            intent.setFlags(67108864);
                            12.this.Loading.this.startActivity(intent);
                        }
                    }
                }, 30000);
            }

        });
    }

    public static String getStringFromFile(String string2) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File(string2));
        String string3 = Loading.convertStreamToString((InputStream)fileInputStream);
        fileInputStream.close();
        return string3;
    }

    public void backToMain() {
        this.findViewById(2131361860).setVisibility(0);
        AudioDemo.Sound().playSound("a7");
        Bundle bundle = this.getIntent().getExtras();
        super.onPause();
        this.setContentView(2130903047);
        super.onCreate(this.savedInstance);
        super.onResume();
        TextView textView = (TextView)this.findViewById(2131361796);
        TextView textView2 = (TextView)this.findViewById(2131361798);
        Log.v((String)"hello", (String)"back main");
        this.findViewById(2131361819).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent intent = new Intent(Loading.this.getBaseContext(), (Class)MainPage.class);
                intent.setFlags(67108864);
                Loading.this.startActivity(intent);
                Loading.this.setting.writeConfirmOrder(Loading.this.printBundle);
            }
        });
        textView.setText((CharSequence)(String.valueOf((Object)MessageTopup.getMessage(23)) + "... " + MessageTopup.getMessage(24) + " " + bundle.getString("Price") + " " + MessageTopup.getMessage(16) + " " + MessageTopup.getMessage(25)));
        textView.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView2.setText((CharSequence)(String.valueOf((Object)MessageTopup.getMessage(26)) + MessageTopup.getMessage(27) + " " + bundle.getInt("CD") + " " + MessageTopup.getMessage(16)));
        textView2.setTypeface(MessageTopup.setFont((Context)this, 0));
        if (this.findViewById(2131361858) != null) {
            TextView textView3 = (TextView)this.findViewById(2131361858);
            TextView textView4 = (TextView)this.findViewById(2131361811);
            textView.setText((CharSequence)(String.valueOf((Object)MessageTopup.getMessage(23)) + "... " + MessageTopup.getMessage(24) + " " + bundle.getString("Price") + " " + MessageTopup.getMessage(16) + " "));
            textView3.setText((CharSequence)MessageTopup.getMessage(25));
            textView2.setText((CharSequence)MessageTopup.getMessage(26));
            textView4.setText((CharSequence)(String.valueOf((Object)MessageTopup.getMessage(27)) + " " + bundle.getInt("CD") + " " + MessageTopup.getMessage(16)));
            textView3.setTypeface(MessageTopup.setFont((Context)this, 0));
            textView4.setTypeface(MessageTopup.setFont((Context)this, 0));
        }
        ((Button)this.findViewById(2131361797)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent intent = new Intent(Loading.this.getBaseContext(), (Class)MainPage.class);
                intent.setFlags(67108864);
                Loading.this.startActivity(intent);
            }
        });
    }

    public void backToMain2() {
        this.findViewById(2131361860).setVisibility(0);
        Bundle bundle = this.getIntent().getExtras();
        super.onPause();
        this.setContentView(2130903041);
        super.onCreate(this.savedInstance);
        super.onResume();
        TextView textView = (TextView)this.findViewById(2131361796);
        TextView textView2 = (TextView)this.findViewById(2131361798);
        textView.setText((CharSequence)(String.valueOf((Object)MessageTopup.getMessage(29)) + "\n" + MessageTopup.getMessage(30) + bundle.getInt("CD") + " " + MessageTopup.getMessage(16) + " " + MessageTopup.getMessage(31)));
        textView.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView2.setText((CharSequence)"");
        ((Button)this.findViewById(2131361797)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent intent = new Intent(Loading.this.getBaseContext(), (Class)MainPage.class);
                intent.setFlags(67108864);
                Loading.this.startActivity(intent);
            }
        });
    }

    public void backToMain3() {
        this.findViewById(2131361860).setVisibility(0);
        this.getIntent().getExtras();
        super.onPause();
        this.setContentView(2130903047);
        super.onCreate(this.savedInstance);
        super.onResume();
        TextView textView = (TextView)this.findViewById(2131361796);
        TextView textView2 = (TextView)this.findViewById(2131361798);
        textView.setText((CharSequence)MessageTopup.getMessage(43));
        textView.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView2.setText((CharSequence)"");
        ((Button)this.findViewById(2131361797)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent intent = new Intent(Loading.this.getBaseContext(), (Class)MainPage.class);
                intent.setFlags(67108864);
                Loading.this.startActivity(intent);
            }
        });
        AudioDemo.Sound().playSound("d2");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903054);
        this.findViewById(2131361902).setBackgroundDrawable(CallImage.imageDrawableCard("bg_loading"));
        this.savedInstance = bundle;
        super.onCreate(this.savedInstance);
        try {
            this.error = Loading.getStringFromFile("/mnt/sdcard/Resource/Error.txt");
        }
        catch (Exception var2_8) {
            this.error = "\u0e41\u0e08\u0e49\u0e07\u0e1b\u0e31\u0e0d\u0e2b\u0e32 084-555-7930";
        }
        Log.v((String)"hello", (String)("error message " + this.error));
        Bundle bundle2 = this.getIntent().getExtras();
        int n = bundle2.getInt("Service");
        if (n == 2 || n == 4 || n == 1) {
            this.delayValue = 200;
        }
        String string2 = bundle2.getString("ShowError");
        Date date = new Date();
        int n2 = 100 * (1 + date.getDay()) + date.getDay() - date.getHours();
        Log.v((String)"hello", (String)("number" + date.getDay() + " " + date.getHours() + " " + n2));
        Log.v((String)"hello", (String)("pass " + bundle2.getInt("PassType") + " " + bundle2.getString("Password")));
        if (n == 5 && bundle2.getInt("PassType") == 1 && Integer.parseInt((String)bundle2.getString("Password")) == n2) {
            this.getPackageManager().clearPackagePreferredActivities(this.getPackageName());
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            intent.setFlags(268435456);
            this.startActivity(intent);
            return;
        }
        int n3 = n2 / (1 + date.getDay());
        if (n == 5 && bundle2.getInt("PassType") == 1 && Integer.parseInt((String)bundle2.getString("Password")) == n3) {
            this.startActivityForResult(new Intent("android.settings.SETTINGS"), 0);
        }
        int n4 = date.getHours() * date.getHours() - date.getDay();
        if (n == 5 && bundle2.getInt("PassType") == 1 && Integer.parseInt((String)bundle2.getString("Password")) == n4) {
            this.finish();
            this.startActivityForResult(new Intent((Context)this, (Class)SetMachine.class), 0);
            Runnable runnable = new Runnable(){

                public void run() {
                    Loading.this.root.backToMainMenu();
                }
            };
            this.backTime = new Thread(runnable);
            this.backTime.start();
            return;
        }
        if (n == 5 && bundle2.getInt("PassType") == 1 && Integer.parseInt((String)bundle2.getString("Password")) == 9990) {
            try {
                Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot"}).waitFor();
                return;
            }
            catch (Exception var16_12) {
                Log.d((String)"debug", (String)"Could not reboot", (Throwable)var16_12);
                return;
            }
        }
        if (string2 == null) {
            final LongOperation longOperation = new LongOperation((Loading)this, null);
            longOperation.parent = this;
            Runnable runnable = new Runnable(){

                public void run() {
                    try {
                        Thread.sleep((long)Loading.this.delayValue);
                        longOperation.execute((Object[])new String[]{"hello3"});
                        return;
                    }
                    catch (InterruptedException var1_1) {
                        var1_1.printStackTrace();
                        return;
                    }
                }
            };
            new Thread(runnable).start();
            this.findViewById(2131361860).setVisibility(4);
            return;
        }
        Log.v((String)"test", (String)("message " + string2));
        this.showError(0, string2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (this.isbind) {
            this.unbindService(this.service);
        }
        this.isbind = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d((String)"debug", (String)"onResume active");
    }

    /*
     * Enabled aggressive block sorting
     */
    public void openMachine() {
        this.findViewById(2131361860).setVisibility(0);
        super.onPause();
        this.setContentView(2130903059);
        super.onCreate(this.savedInstance);
        super.onResume();
        this.isbind = true;
        if (MainPage.isBV20) {
            this.bindService(new Intent((Context)this, (Class)IOServiceBV20.class), this.service, 1);
        } else {
            this.bindService(new Intent((Context)this, (Class)IOService.class), this.service, 1);
        }
        SharedPreferences sharedPreferences = this.getSharedPreferences("hello", 0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        CheckBox checkBox = (CheckBox)this.findViewById(2131361917);
        checkBox.setChecked(sharedPreferences.getBoolean("alertSMS", false));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                Log.v((String)"hello", (String)("is ict " + bl));
                editor.putBoolean("alertSMS", bl);
                editor.commit();
            }
        });
        Log.v((String)"hello", (String)("start open " + (Object)this.cancel + " " + (Object)this.next));
        this.cancel = (Button)this.findViewById(2131361862);
        this.next = (Button)this.findViewById(2131361863);
        this.back = (Button)this.findViewById(2131361861);
        this.cancel.setVisibility(8);
        this.next.setVisibility(8);
        Log.v((String)"hello", (String)"if else ");
        TextView textView = (TextView)this.findViewById(2131361796);
        TextView textView2 = (TextView)this.findViewById(2131361798);
        textView.setText((CharSequence)"");
        textView.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView2.setText((CharSequence)"");
        Button button = (Button)this.findViewById(2131361797);
        Button button2 = (Button)this.findViewById(2131361826);
        if (this.getSharedPreferences("hello", 0).getBoolean("boxopen", true)) {
            ((TextView)this.findViewById(2131361808)).setText((CharSequence)"\u0e02\u0e13\u0e30\u0e19\u0e35\u0e49\u0e2a\u0e32\u0e21\u0e32\u0e23\u0e16\u0e40\u0e1b\u0e34\u0e14\u0e0a\u0e48\u0e2d\u0e07\u0e40\u0e01\u0e47\u0e1a\u0e40\u0e07\u0e34\u0e19\u0e44\u0e14\u0e49\u0e41\u0e25\u0e49\u0e27");
        } else {
            ((TextView)this.findViewById(2131361808)).setText((CharSequence)"\u0e01\u0e14\u0e1b\u0e38\u0e48\u0e21\u0e40\u0e1b\u0e34\u0e14\u0e15\u0e39\u0e49\u0e01\u0e48\u0e2d\u0e19\u0e17\u0e33\u0e01\u0e32\u0e23\u0e40\u0e1b\u0e34\u0e14\u0e0a\u0e48\u0e2d\u0e07\u0e40\u0e01\u0e47\u0e1a\u0e40\u0e07\u0e34\u0e19");
        }
        button2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                SharedPreferences.Editor editor = Loading.this.getSharedPreferences("hello", 0).edit();
                editor.putBoolean("boxopen", true);
                editor.commit();
                Loading.this.setting.setupboxStatue(false);
                ((TextView)Loading.this.findViewById(2131361808)).setText((CharSequence)"\u0e02\u0e13\u0e30\u0e19\u0e35\u0e49\u0e2a\u0e32\u0e21\u0e32\u0e23\u0e16\u0e40\u0e1b\u0e34\u0e14\u0e0a\u0e48\u0e2d\u0e07\u0e40\u0e01\u0e47\u0e1a\u0e40\u0e07\u0e34\u0e19\u0e44\u0e14\u0e49\u0e41\u0e25\u0e49\u0e27");
            }
        });
        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                SharedPreferences.Editor editor = Loading.this.getSharedPreferences("hello", 0).edit();
                editor.putBoolean("boxopen", false);
                editor.commit();
                Loading.this.setting.setupboxStatue(true);
                ((TextView)Loading.this.findViewById(2131361808)).setText((CharSequence)"\u0e01\u0e14\u0e1b\u0e38\u0e48\u0e21\u0e40\u0e1b\u0e34\u0e14\u0e15\u0e39\u0e49\u0e01\u0e48\u0e2d\u0e19\u0e17\u0e33\u0e01\u0e32\u0e23\u0e40\u0e1b\u0e34\u0e14\u0e0a\u0e48\u0e2d\u0e07\u0e40\u0e01\u0e47\u0e1a\u0e40\u0e07\u0e34\u0e19");
            }
        });
    }

    public void showError(int n, final String string2) {
        this.runOnUiThread(new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            public void run() {
                Loading.this.findViewById(2131361860).setVisibility(0);
                Loading.this.onPause();
                Loading.this.setContentView(2130903046);
                Loading.this.onCreate(Loading.this.savedInstance);
                Loading.this.onResume();
                Loading.this.findViewById(2131361863).setVisibility(8);
                TextView textView = (TextView)Loading.this.findViewById(2131361857);
                Bundle bundle = Loading.this.getIntent().getExtras();
                int n = bundle.getInt("Service");
                int n2 = string2.length();
                boolean bl = false;
                int n3 = 0;
                if (n2 == 3) {
                    boolean bl2 = string2.contains((CharSequence)"E");
                    bl = false;
                    n3 = 0;
                    if (bl2) {
                        bl = true;
                        n3 = Integer.parseInt((String)string2.replace((CharSequence)"E", (CharSequence)""));
                    }
                }
                switch (n) {
                    default: {
                        return;
                    }
                    case 0: {
                        textView.setText((CharSequence)string2);
                        return;
                    }
                    case 1: {
                        if (bl) {
                            textView.setText((CharSequence)MessageTopup.getMessage(n3 + 94));
                            return;
                        }
                        textView.setText((CharSequence)string2);
                        return;
                    }
                    case 2: {
                        if (bl) {
                            textView.setText((CharSequence)MessageTopup.getMessage(n3 + 94));
                        } else {
                            textView.setText((CharSequence)string2);
                        }
                        Loading.this.printBundle = bundle;
                        Loading.this.printBundle.putString("TOPUPSTATUS", "\u0e44\u0e21\u0e48\u0e2a\u0e33\u0e40\u0e23\u0e47\u0e08");
                        return;
                    }
                    case 4: {
                        if (bl) {
                            textView.setText((CharSequence)MessageTopup.getMessage(n3 + 94));
                            return;
                        }
                        textView.setText((CharSequence)string2);
                        return;
                    }
                    case 5: {
                        textView.setText((CharSequence)string2);
                        return;
                    }
                    case 6: {
                        textView.setText((CharSequence)string2);
                        return;
                    }
                    case 10: {
                        textView.setText((CharSequence)MessageTopup.getMessage(74));
                        return;
                    }
                    case 11: {
                        if (bl) {
                            textView.setText((CharSequence)MessageTopup.getMessage(n3 + 94));
                            return;
                        }
                        textView.setText((CharSequence)string2);
                        return;
                    }
                    case 12: {
                        Log.v((String)"hello", (String)("get message " + n3));
                        if (bl) {
                            textView.setText((CharSequence)MessageTopup.getMessage(n3 + 94));
                            return;
                        }
                        textView.setText((CharSequence)string2);
                        return;
                    }
                    case 13: {
                        if (bl) {
                            textView.setText((CharSequence)MessageTopup.getMessage(n3 + 94));
                            return;
                        }
                        textView.setText((CharSequence)string2);
                        return;
                    }
                    case 15: {
                        textView.setText((CharSequence)string2);
                        return;
                    }
                    case 16: {
                        textView.setText((CharSequence)string2);
                        return;
                    }
                    case 18: 
                }
                if (bl) {
                    textView.setText((CharSequence)MessageTopup.getMessage(n3 + 94));
                    return;
                }
                textView.setText((CharSequence)string2);
            }
        });
    }

    private class LongOperation
    extends AsyncTask<String, Void, String> {
        public Loading parent;
        final /* synthetic */ Loading this$0;

        private LongOperation(Loading loading) {
            this.this$0 = loading;
        }

        /* synthetic */ LongOperation(Loading loading, LongOperation longOperation) {
            super(loading);
        }

        public void cancelInput() {
            Bundle bundle = this.parent.getIntent().getExtras();
            try {
                String string2 = Service.cancelInputMoney(bundle);
                String[] arrstring = string2.split("&");
                if (arrstring[0].split("=")[1].equals((Object)"OK")) {
                    int n = Integer.parseInt((String)arrstring[1].split("=")[1]);
                    int n2 = Integer.parseInt((String)arrstring[2].split("=")[1]);
                    int n3 = Integer.parseInt((String)arrstring[3].split("=")[1].substring(0, 6));
                    bundle.putInt("OD", n);
                    bundle.putInt("CD", n2);
                    bundle.putInt("HC", n3);
                    this.parent.getIntent().putExtras(bundle);
                    LogController.deletefile(bundle.getString("Mobile"));
                    this.this$0.runOnUiThread(new Runnable(){

                        public void run() {
                            LongOperation.this.parent.backToMain2();
                        }
                    });
                    this.this$0.delayback();
                    return;
                }
                LogController.deletefile(bundle.getString("Mobile"));
                String string3 = string2.split("st=")[1].split("#")[0];
                this.parent.showError(4, string3);
                this.this$0.delayback();
                return;
            }
            catch (Exception var2_8) {
                var2_8.printStackTrace();
                if (this.checkErrorCase(var2_8, bundle.getString("Mobile"))) {
                    this.cancelInput();
                }
                return;
            }
        }

        public void cancelInputUtil() {
            Bundle bundle = this.parent.getIntent().getExtras();
            try {
                String string2 = Service.cancelInputMoneyUtil(bundle);
                String[] arrstring = string2.split("&");
                if (arrstring[0].split("=")[1].equals((Object)"OK")) {
                    int n = Integer.parseInt((String)arrstring[1].split("=")[1]);
                    int n2 = Integer.parseInt((String)arrstring[2].split("=")[1]);
                    int n3 = Integer.parseInt((String)arrstring[3].split("=")[1].substring(0, 6));
                    bundle.putInt("OD", n);
                    bundle.putInt("CD", n2);
                    bundle.putInt("HC", n3);
                    this.parent.getIntent().putExtras(bundle);
                    LogController.deletefile(bundle.getString("Mobile"));
                    this.this$0.runOnUiThread(new Runnable(){

                        public void run() {
                            LongOperation.this.parent.backToMain2();
                        }
                    });
                    this.this$0.delayback();
                    return;
                }
                String string3 = string2.split("st=")[1].split("#")[0];
                this.parent.showError(4, string3);
                this.this$0.delayback();
                return;
            }
            catch (Exception var2_8) {
                var2_8.printStackTrace();
                if (this.checkErrorCase(var2_8, bundle.getString("Mobile"))) {
                    this.cancelInputUtil();
                }
                return;
            }
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean checkErrorCase(Exception exception, String string2) {
            if (exception instanceof IOException || exception instanceof ConnectionLostException) {
                if (this.this$0.connectionLostCount < 3) {
                    Loading loading = this.this$0;
                    loading.connectionLostCount = 1 + loading.connectionLostCount;
                    try {
                        Thread.sleep((long)10000);
                        do {
                            return true;
                            break;
                        } while (true);
                    }
                    catch (InterruptedException var4_4) {
                        var4_4.printStackTrace();
                        return true;
                    }
                }
                this.parent.showError(0, "internet \u0e02\u0e31\u0e14\u0e02\u0e49\u0e2d\u0e07  " + this.this$0.error);
                this.this$0.delayback();
                return false;
            }
            if (string2 != null) {
                LogController.deletefile(string2);
            }
            this.parent.showError(0, "\u0e01\u0e32\u0e23\u0e40\u0e0a\u0e37\u0e48\u0e2d\u0e21\u0e15\u0e48\u0e2d\u0e21\u0e35\u0e1b\u0e31\u0e0d\u0e2b\u0e32");
            this.this$0.delayback();
            return false;
        }

        /*
         * Exception decompiling
         */
        public void checkPassword() {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.CannotPerformDecode: reachable test BLOCK was exited and re-entered.
            // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.getFarthestReachableInRange(Misc.java:143)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:385)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:65)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:422)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
            // org.benf.cfr.reader.Main.doJar(Main.java:128)
            // com.njlabs.showjava.processor.JavaExtractor$1.run(JavaExtractor.java:100)
            // java.lang.Thread.run(Thread.java:818)
            throw new IllegalStateException("Decompilation failed");
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        protected /* varargs */ String doInBackground(String ... var1) {
            var2_2 = this.parent.getIntent().getExtras().getInt("Service");
            Log.v((String)"test", (String)("service " + var2_2));
            switch (var2_2) {
                case 1: {
                    this.ready();
                    ** break;
                }
                case 2: {
                    this.payMoney();
                    ** break;
                }
                case 4: {
                    this.cancelInput();
                    ** break;
                }
                case 5: {
                    this.checkPassword();
                    ** break;
                }
                case 6: {
                    this.resetAmount();
                    ** break;
                }
                case 10: {
                    this.sendProblem();
                    ** break;
                }
                case 11: {
                    this.readyUtil();
                    ** break;
                }
                case 12: {
                    this.payMoneyUtil();
                    ** break;
                }
                case 13: {
                    this.cancelInputUtil();
                    ** break;
                }
                case 15: {
                    this.getTransaction();
                    ** break;
                }
                case 16: {
                    this.getdetail();
                    ** break;
                }
                case 18: {
                    this.getBarcodeDetail();
                    ** break;
                }
                case 19: {
                    this.readyBarcode();
                }
lbl42: // 14 sources:
                default: {
                    ** GOTO lbl46
                }
                case 20: 
            }
            this.payMoneyBarcode();
lbl46: // 2 sources:
            this.publishProgress((Object[])new Void[0]);
            return "Executed";
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void getBarcodeDetail() {
            try {
                Bundle bundle = this.parent.getIntent().getExtras();
                String string2 = Service.getBarcodeDetail(bundle);
                Log.e((String)"test", (String)("return " + string2));
                String[] arrstring = string2.replace((CharSequence)"#", (CharSequence)"").split("&");
                if (arrstring[0].split("=")[1].equals((Object)"OK")) {
                    Log.e((String)"hello", (String)"case ok");
                    bundle.putInt("DataMax", 0);
                    if ((double)Float.parseFloat((String)arrstring[3].split("=")[1]) == 0.0) {
                        bundle.putInt("CurrentData", 4);
                    } else {
                        bundle.putInt("CurrentData", 5);
                    }
                    bundle.putString("Network", arrstring[1].split("=")[1]);
                    bundle.putString("NetworkName", arrstring[2].split("=")[1]);
                    bundle.putString("Price", arrstring[3].split("=")[1]);
                    bundle.putInt("Index", 16);
                    if (arrstring[4].split("=").length == 2) {
                        bundle.putString("Data1", arrstring[4].split("=")[1]);
                    }
                    if (arrstring[5].split("=").length == 2) {
                        bundle.putString("Data2", arrstring[5].split("=")[1]);
                    }
                    if (arrstring[6].split("=").length == 2) {
                        bundle.putString("Data3", arrstring[6].split("=")[1]);
                    }
                    if (arrstring[7].split("=").length == 2) {
                        bundle.putString("Data4", arrstring[7].split("=")[1]);
                    }
                    if (arrstring[8].split("=").length == 2) {
                        bundle.putString("Data5", arrstring[8].split("=")[1]);
                    }
                    Intent intent = new Intent(this.this$0.getBaseContext(), (Class)NumpadUtil.class);
                    intent.putExtras(bundle);
                    this.this$0.startActivityForResult(intent, 0);
                    this.this$0.finish();
                    return;
                }
                Log.e((String)"hello", (String)"case error");
                String string3 = string2.split("st=")[1].split("#")[0];
                this.parent.showError(10, string3);
                return;
            }
            catch (Exception var1_5) {
                var1_5.printStackTrace();
                if (!this.checkErrorCase(var1_5, null)) return;
                this.getdetail();
                return;
            }
        }

        public void getTransaction() {
            Bundle bundle = this.parent.getIntent().getExtras();
            try {
                bundle.putInt("PerPage", 20);
                String string2 = Service.getTranSaction(bundle);
                if (string2.contains((CharSequence)"OK")) {
                    bundle.putString("dataRet", string2);
                    Intent intent = new Intent(this.this$0.getBaseContext(), (Class)Record.class);
                    intent.putExtras(bundle);
                    intent.setFlags(67108864);
                    this.this$0.startActivity(intent);
                    return;
                }
                this.parent.showError(15, "\u0e02\u0e49\u0e2d\u0e21\u0e39\u0e25\u0e44\u0e21\u0e48\u0e16\u0e39\u0e01\u0e15\u0e49\u0e2d\u0e07");
                return;
            }
            catch (Exception var2_4) {
                var2_4.printStackTrace();
                if (this.checkErrorCase(var2_4, null)) {
                    this.getTransaction();
                }
                return;
            }
        }

        public void getdetail() {
            try {
                String string2 = Service.getProfile();
                if (string2.replace((CharSequence)"#", (CharSequence)"").split("&")[0].split("=")[1].equals((Object)"OK")) {
                    Intent intent = new Intent(this.this$0.getBaseContext(), (Class)Detail.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("returned", string2.replace((CharSequence)"#", (CharSequence)""));
                    intent.putExtras(bundle);
                    this.this$0.startActivityForResult(intent, 0);
                    this.this$0.finish();
                    return;
                }
                String string3 = string2.split("st=")[1].split("#")[0];
                this.parent.showError(10, string3);
                return;
            }
            catch (Exception var1_5) {
                var1_5.printStackTrace();
                if (this.checkErrorCase(var1_5, null)) {
                    this.getdetail();
                }
                return;
            }
        }

        protected void onPostExecute(String string2) {
        }

        protected void onPreExecute() {
        }

        protected /* varargs */ void onProgressUpdate(Void ... arrvoid) {
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void payMoney() {
            Bundle bundle = this.parent.getIntent().getExtras();
            try {
                if (MainPage.isBV20) {
                    Intent intent = new Intent((Context)this.this$0, (Class)IOServiceBV20.class);
                    this.this$0.bindService(intent, this.this$0.service, 1);
                } else {
                    Intent intent = new Intent((Context)this.this$0, (Class)IOService.class);
                    this.this$0.bindService(intent, this.this$0.service, 1);
                }
                this.this$0.isbind = true;
                String string2 = Service.inputMoney(bundle);
                Log.e((String)"hello", (String)("return message " + string2));
                String[] arrstring = string2.split("&");
                if (arrstring[0].split("=")[1].equals((Object)"OK")) {
                    int n = Integer.parseInt((String)arrstring[1].split("=")[1]);
                    int n2 = Integer.parseInt((String)arrstring[2].split("=")[1]);
                    Log.v((String)"hello", (String)("cd val" + n2));
                    int n3 = Integer.parseInt((String)arrstring[3].split("=")[1].substring(0, 6));
                    bundle.putInt("OD", n);
                    bundle.putInt("CD", n2);
                    bundle.putInt("HC", n3);
                    bundle.putString("TOPUPSTATUS", "\u0e2a\u0e33\u0e40\u0e23\u0e47\u0e08");
                    this.parent.getIntent().putExtras(bundle);
                    this.this$0.printBundle = bundle;
                    LogController.deletefile(bundle.getString("Mobile"));
                    this.this$0.runOnUiThread(new Runnable(){

                        public void run() {
                            LongOperation.this.parent.backToMain();
                        }
                    });
                    this.this$0.delayback2();
                    return;
                }
                LogController.deletefile(bundle.getString("Mobile"));
                AudioDemo.Sound().playSound("a8");
                String string3 = string2.split("st=")[1].split("#")[0];
                this.parent.showError(2, string3);
                return;
            }
            catch (Exception var2_9) {
                var2_9.printStackTrace();
                if (!this.checkErrorCase(var2_9, bundle.getString("Mobile"))) return;
                this.payMoney();
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void payMoneyBarcode() {
            Bundle bundle = this.parent.getIntent().getExtras();
            try {
                if (MainPage.isBV20) {
                    Intent intent = new Intent((Context)this.this$0, (Class)IOServiceBV20.class);
                    this.this$0.bindService(intent, this.this$0.service, 1);
                } else {
                    Intent intent = new Intent((Context)this.this$0, (Class)IOService.class);
                    this.this$0.bindService(intent, this.this$0.service, 1);
                }
                String string2 = Service.inputMoneyBarcode(bundle);
                Log.v((String)"hello", (String)("return message " + string2));
                String[] arrstring = string2.split("&");
                if (arrstring[0].split("=")[1].equals((Object)"OK")) {
                    int n = Integer.parseInt((String)arrstring[1].split("=")[1]);
                    int n2 = Integer.parseInt((String)arrstring[2].split("=")[1]);
                    Log.v((String)"hello", (String)("cd val barcode" + n2));
                    int n3 = Integer.parseInt((String)arrstring[3].split("=")[1].substring(0, 6));
                    bundle.putInt("OD", n);
                    bundle.putInt("CD", n2);
                    bundle.putInt("HC", n3);
                    bundle.putString("TOPUPSTATUS", "\u0e2a\u0e33\u0e40\u0e23\u0e47\u0e08");
                    this.this$0.printBundle = bundle;
                    this.parent.getIntent().putExtras(bundle);
                    LogController.deletefile(bundle.getString("Mobile"));
                    this.this$0.runOnUiThread(new Runnable(){

                        public void run() {
                            LongOperation.this.parent.backToMain();
                        }
                    });
                    return;
                }
                AudioDemo.Sound().playSound("a8");
                String string3 = string2.split("st=")[1].split("#")[0];
                this.parent.showError(2, string3);
                return;
            }
            catch (Exception var2_9) {
                var2_9.printStackTrace();
                if (!this.checkErrorCase(var2_9, bundle.getString("Mobile"))) return;
                this.payMoneyUtil();
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void payMoneyUtil() {
            Bundle bundle = this.parent.getIntent().getExtras();
            try {
                if (MainPage.isBV20) {
                    Intent intent = new Intent((Context)this.this$0, (Class)IOServiceBV20.class);
                    this.this$0.bindService(intent, this.this$0.service, 1);
                } else {
                    Intent intent = new Intent((Context)this.this$0, (Class)IOService.class);
                    this.this$0.bindService(intent, this.this$0.service, 1);
                }
                this.this$0.isbind = true;
                String string2 = Service.inputMoneyUtil(bundle);
                Log.v((String)"hello", (String)("return message " + string2));
                String[] arrstring = string2.split("&");
                if (arrstring[0].split("=")[1].equals((Object)"OK")) {
                    int n = Integer.parseInt((String)arrstring[1].split("=")[1]);
                    int n2 = Integer.parseInt((String)arrstring[2].split("=")[1]);
                    Log.v((String)"hello", (String)("cd val" + n2));
                    int n3 = Integer.parseInt((String)arrstring[3].split("=")[1].substring(0, 6));
                    bundle.putInt("OD", n);
                    bundle.putInt("CD", n2);
                    bundle.putInt("HC", n3);
                    bundle.putString("TOPUPSTATUS", "\u0e2a\u0e33\u0e40\u0e23\u0e47\u0e08");
                    this.this$0.printBundle = bundle;
                    this.parent.getIntent().putExtras(bundle);
                    LogController.deletefile(bundle.getString("Mobile"));
                    this.this$0.runOnUiThread(new Runnable(){

                        public void run() {
                            LongOperation.this.parent.backToMain();
                        }
                    });
                    return;
                }
                AudioDemo.Sound().playSound("a8");
                String string3 = string2.split("st=")[1].split("#")[0];
                this.parent.showError(2, string3);
                return;
            }
            catch (Exception var2_9) {
                var2_9.printStackTrace();
                if (!this.checkErrorCase(var2_9, bundle.getString("Mobile"))) return;
                this.payMoneyUtil();
                return;
            }
        }

        public void ready() {
            Bundle bundle = this.parent.getIntent().getExtras();
            try {
                String string2 = Service.ready(bundle);
                String[] arrstring = string2.split("&");
                if (arrstring[0].split("=")[1].equals((Object)"OK")) {
                    int n = Integer.parseInt((String)arrstring[1].split("=")[1]);
                    int n2 = Integer.parseInt((String)arrstring[2].split("=")[1]);
                    int n3 = Integer.parseInt((String)arrstring[3].split("=")[1]);
                    bundle.putString("RandomKey", arrstring[4].split("=")[1].substring(0, 4));
                    bundle.putInt("MC", n);
                    bundle.putInt("HC", n2);
                    bundle.putInt("OR", n3);
                    this.this$0.finish();
                    Log.v((String)"hello", (String)"goto check");
                    Intent intent = new Intent(this.this$0.findViewById(2131361904).getContext(), (Class)InputCoin.class);
                    intent.putExtras(bundle);
                    this.this$0.startActivityForResult(intent, 0);
                    return;
                }
                String string3 = string2.split("st=")[1].split("#")[0];
                this.parent.showError(1, string3);
                return;
            }
            catch (Exception var2_9) {
                var2_9.printStackTrace();
                if (this.checkErrorCase(var2_9, null)) {
                    this.ready();
                }
                return;
            }
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        public void readyBarcode() {
            var1_1 = this.parent.getIntent().getExtras();
            try {
                var3_2 = Service.readyBarcode(var1_1);
                Log.e((String)"hello", (String)("ready barcode return " + var3_2));
                var5_3 = var3_2.split("&");
                if (!var5_3[0].split("=")[1].equals((Object)"OK")) ** GOTO lbl32
                var7_4 = Integer.parseInt((String)var5_3[1].split("=")[1]);
                var8_5 = Integer.parseInt((String)var5_3[2].split("=")[1]);
                var9_6 = Integer.parseInt((String)var5_3[3].split("=")[1]);
                var10_7 = Float.parseFloat((String)var5_3[4].split("=")[1]);
                var11_8 = var5_3[-1 + var5_3.length].split("=")[1].substring(0, 4);
                Log.e((String)"hello", (String)("rk value " + var11_8));
                var1_1.putString("RandomKey", var11_8);
                var1_1.putInt("MC", var7_4);
                var1_1.putInt("HC", var8_5);
                var1_1.putInt("OR", var9_6);
            }
            catch (Exception var2_11) {
                var2_11.printStackTrace();
                if (this.checkErrorCase(var2_11, null) == false) return;
                this.readyUtil();
                return;
            }
            if ((double)var10_7 <= 0.0) ** GOTO lbl25
            var1_1.putString("Price", String.valueOf((float)var10_7));
lbl25: // 2 sources:
            var1_1.putString("TY", var5_3[5].split("=")[1]);
            var1_1.putString("TRAN", var5_3[6].split("=")[1]);
            this.this$0.finish();
            var13_9 = new Intent(this.this$0.findViewById(2131361904).getContext(), (Class)InputCoinUtil.class);
            var13_9.putExtras(var1_1);
            this.this$0.startActivityForResult(var13_9, 0);
            return;
lbl32: // 1 sources:
            var6_10 = var3_2.split("st=")[1].split("#")[0];
            this.parent.showError(1, var6_10);
        }

        public void readyUtil() {
            Bundle bundle = this.parent.getIntent().getExtras();
            try {
                String string2 = Service.readyUtility(bundle);
                Log.v((String)"hello", (String)("ready util return " + string2));
                String[] arrstring = string2.split("&");
                if (arrstring[0].split("=")[1].equals((Object)"OK")) {
                    int n = Integer.parseInt((String)arrstring[1].split("=")[1]);
                    int n2 = Integer.parseInt((String)arrstring[2].split("=")[1]);
                    int n3 = Integer.parseInt((String)arrstring[3].split("=")[1]);
                    bundle.putString("RandomKey", arrstring[4].split("=")[1].substring(0, 4));
                    bundle.putInt("MC", n);
                    bundle.putInt("HC", n2);
                    bundle.putInt("OR", n3);
                    this.this$0.finish();
                    Intent intent = new Intent(this.this$0.findViewById(2131361904).getContext(), (Class)InputCoinUtil.class);
                    intent.putExtras(bundle);
                    this.this$0.startActivityForResult(intent, 0);
                    return;
                }
                String string3 = string2.split("st=")[1].split("#")[0];
                this.parent.showError(1, string3);
                return;
            }
            catch (Exception var2_9) {
                var2_9.printStackTrace();
                if (this.checkErrorCase(var2_9, null)) {
                    this.readyUtil();
                }
                return;
            }
        }

        public void resetAmount() {
            Bundle bundle = this.parent.getIntent().getExtras();
            try {
                if (Service.resetAmount(bundle).split("&")[0].split("=")[1].substring(0, 2).equals((Object)"OK")) {
                    this.this$0.finish();
                    this.this$0.finish();
                    return;
                }
                this.parent.showError(6, "Wrong Password");
                return;
            }
            catch (Exception var2_2) {
                var2_2.printStackTrace();
                if (this.checkErrorCase(var2_2, null)) {
                    this.resetAmount();
                }
                return;
            }
        }

        public void sendProblem() {
            Bundle bundle = this.parent.getIntent().getExtras();
            try {
                String string2 = Service.sendProblem(bundle);
                if (string2.split("#")[0].split("=")[1].equals((Object)"OK")) {
                    this.this$0.runOnUiThread(new Runnable(){

                        public void run() {
                            LongOperation.this.parent.backToMain3();
                        }
                    });
                    return;
                }
                String string3 = string2.split("return=")[1].split("#")[0];
                this.parent.showError(10, string3);
                return;
            }
            catch (Exception var2_4) {
                var2_4.printStackTrace();
                if (this.checkErrorCase(var2_4, null)) {
                    this.sendProblem();
                }
                return;
            }
        }

    }

}

