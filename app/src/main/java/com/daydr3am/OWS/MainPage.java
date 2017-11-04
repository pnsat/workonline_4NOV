/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.BroadcastReceiver
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.ServiceConnection
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.pm.PackageManager
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.os.IBinder
 *  android.telephony.PhoneStateListener
 *  android.util.Log
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.widget.Button
 *  android.widget.TextView
 *  android.widget.Toast
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.Calendar
 */
package com.daydr3am.OWS;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.daydr3am.OWS.Barcode;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.MessageTopup;
import com.daydr3am.OWS.ReportPage;
import com.daydr3am.OWS.SampleActivity;
import com.daydr3am.OWS.SelectOtherUtility;
import com.daydr3am.OWS.SelectService;
import com.daydr3am.OWS.SelectUtility;
import com.daydr3am.lib.AudioDemo;
import com.daydr3am.lib.CallImage;
import com.daydr3am.lib.IOService;
import com.daydr3am.lib.IOServiceBV20;
import com.daydr3am.lib.LogChecker;
import java.util.Calendar;

public class MainPage
extends IORootActivity {
    static boolean isBV20;
    static boolean pause;
    static Thread th;
    boolean passCreate;
    SharedPreferences preferences;
    public ServiceConnection service;
    IOService.SettingService setting;
    private final BroadcastReceiver ussdCatcherReceiver;

    static {
        pause = false;
    }

    public MainPage() {
        this.service = new ServiceConnection(){

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                MainPage.this.setting = (IOService.SettingService)iBinder;
                Log.v((String)"hello", (String)("already bind" + MainPage.this.setting));
                MainPage.this.setting.serviceSetup(MainPage.this);
            }

            public void onServiceDisconnected(ComponentName componentName) {
            }
        };
        this.ussdCatcherReceiver = new BroadcastReceiver(){

            /*
             * Enabled aggressive block sorting
             */
            public void onReceive(Context context, Intent intent) {
                String string2;
                String string3 = intent.getStringExtra("extra_message");
                String string4 = intent.getStringExtra("extra_cutmessage");
                int n = Calendar.getInstance().get(11);
                String string5 = "message2";
                if (n == 18) {
                    string2 = "phonenumraw";
                    string5 = "phonenum";
                } else if (n == 1) {
                    string2 = "phonecreditraw";
                    string5 = "phonecredit";
                } else {
                    string2 = "message";
                }
                SharedPreferences.Editor editor = MainPage.this.getSharedPreferences("hello", 0).edit();
                editor.putString(string2, string3);
                editor.putString(string5, string4);
                editor.commit();
                Toast.makeText((Context)MainPage.this, (CharSequence)string3, (int)1).show();
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    public void ImageButton(int n, int n2) {
        String string2;
        Button button = (Button)this.findViewById(2131361908);
        Button button2 = (Button)this.findViewById(2131361909);
        Button button3 = (Button)this.findViewById(2131361910);
        Button button4 = (Button)this.findViewById(2131361911);
        Button button5 = (Button)this.findViewById(2131361905);
        Button button6 = (Button)this.findViewById(2131361906);
        if (n2 == 1) {
            string2 = "";
        } else {
            string2 = null;
            if (n2 == 2) {
                string2 = "_on";
            }
        }
        if (switchID == 1) {
            if (n == 1) {
                button.setBackgroundDrawable(CallImage.imageDrawableCard("mobile_topup_th" + string2));
            }
            if (n == 2) {
                button2.setBackgroundDrawable(CallImage.imageDrawableCard("game_th" + string2));
            }
            if (n == 3) {
                button3.setBackgroundDrawable(CallImage.imageDrawableCard("card_th" + string2));
            }
            if (n == 4) {
                button4.setBackgroundDrawable(CallImage.imageDrawableCard("billing_th" + string2));
            }
            if (n == 5) {
                button5.setBackgroundDrawable(CallImage.imageDrawableCard("how_to_use_th" + string2));
            }
            if (n != 11) return;
            {
                button6.setBackgroundDrawable(CallImage.imageDrawableCard("report_th" + string2));
                return;
            }
        } else {
            if (switchID != 2) return;
            {
                if (n == 1) {
                    button.setBackgroundDrawable(CallImage.imageDrawableCard("mobile_topup_en" + string2));
                }
                if (n == 2) {
                    button2.setBackgroundDrawable(CallImage.imageDrawableCard("game_en" + string2));
                }
                if (n == 3) {
                    button3.setBackgroundDrawable(CallImage.imageDrawableCard("card_en" + string2));
                }
                if (n == 4) {
                    button4.setBackgroundDrawable(CallImage.imageDrawableCard("billing_en" + string2));
                }
                if (n == 5) {
                    button5.setBackgroundDrawable(CallImage.imageDrawableCard("how_to_use_en" + string2));
                }
                if (n != 11) return;
                {
                    button6.setBackgroundDrawable(CallImage.imageDrawableCard("report_en" + string2));
                    return;
                }
            }
        }
    }

    @Override
    public void LandScapeEachPage() {
        if (this.landBack != null) {
            Button button = (Button)this.landBack;
            button.setText((CharSequence)"\u0e41\u0e08\u0e49\u0e07\u0e1b\u0e31\u0e0d\u0e2b\u0e32       ");
            button.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), (Class)ReportPage.class);
                    view.getContext().startActivity(intent);
                }
            });
        }
        Log.v((String)"hello", (String)("land show " + (Object)this.landTextShow));
        if (this.landTextShow != null) {
            this.landTextShow.setText((CharSequence)"\u0e40\u0e25\u0e37\u0e2d\u0e01\u0e1a\u0e23\u0e34\u0e01\u0e32\u0e23\u0e17\u0e35\u0e48\u0e17\u0e48\u0e32\u0e19\u0e15\u0e49\u0e2d\u0e07\u0e01\u0e32\u0e23");
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void changeLanguage(int n) {
        Log.d((String)"debug", (String)("changeLanguage main " + n));
        Log.d((String)"debug", (String)("changeLanguage MessageTopup >> " + MessageTopup.getMessage(5)));
        Button button = (Button)this.findViewById(2131361908);
        Button button2 = (Button)this.findViewById(2131361909);
        Button button3 = (Button)this.findViewById(2131361910);
        Button button4 = (Button)this.findViewById(2131361911);
        Button button5 = (Button)this.findViewById(2131361905);
        Button button6 = (Button)this.findViewById(2131361913);
        Button button7 = (Button)this.findViewById(2131361912);
        Button button8 = (Button)this.findViewById(2131361906);
        if (n == 1) {
            button.setBackgroundDrawable(CallImage.imageDrawableCard("mobile_topup_th"));
            button2.setBackgroundDrawable(CallImage.imageDrawableCard("game_th"));
            button3.setBackgroundDrawable(CallImage.imageDrawableCard("card_th"));
            button4.setBackgroundDrawable(CallImage.imageDrawableCard("billing_th"));
            button7.setBackgroundDrawable(CallImage.imageDrawableCard("billing_other_th"));
            button5.setBackgroundDrawable(CallImage.imageDrawableCard("how_to_use_th"));
            button6.setBackgroundDrawable(CallImage.imageDrawableCard("barcode_th"));
            button8.setBackgroundDrawable(CallImage.imageDrawableCard("report_th"));
        } else if (n == 2) {
            button.setBackgroundDrawable(CallImage.imageDrawableCard("mobile_topup_en"));
            button2.setBackgroundDrawable(CallImage.imageDrawableCard("game_en"));
            button3.setBackgroundDrawable(CallImage.imageDrawableCard("card_en"));
            button4.setBackgroundDrawable(CallImage.imageDrawableCard("billing_en"));
            button7.setBackgroundDrawable(CallImage.imageDrawableCard("billing_other_en"));
            button5.setBackgroundDrawable(CallImage.imageDrawableCard("how_to_use_en"));
            button6.setBackgroundDrawable(CallImage.imageDrawableCard("barcode_en"));
            button8.setBackgroundDrawable(CallImage.imageDrawableCard("report_en"));
        }
        TextView textView = (TextView)this.findViewById(2131361796);
        TextView textView2 = (TextView)this.findViewById(2131361798);
        TextView textView3 = (TextView)this.findViewById(2131361858);
        TextView textView4 = (TextView)this.findViewById(2131361811);
        TextView textView5 = (TextView)this.findViewById(2131361915);
        TextView textView6 = (TextView)this.findViewById(2131361916);
        if (n == 1) {
            if (textView != null) {
                textView.setText((CharSequence)"\u0e40\u0e15\u0e34\u0e21\u0e40\u0e07\u0e34\u0e19\u0e21\u0e37\u0e2d\u0e16\u0e37\u0e2d");
            }
            if (textView2 != null) {
                textView2.setText((CharSequence)"\u0e0b\u0e37\u0e49\u0e2d\u0e1a\u0e31\u0e15\u0e23\u0e40\u0e07\u0e34\u0e19\u0e2a\u0e14\u0e40\u0e01\u0e21\u0e2a\u0e4c");
            }
            if (textView3 != null) {
                textView3.setText((CharSequence)"\u0e1a\u0e31\u0e15\u0e23\u0e42\u0e17\u0e23\u0e28\u0e31\u0e1e\u0e17\u0e4c\u0e15\u0e48\u0e32\u0e07\u0e1b\u0e23\u0e30\u0e40\u0e17\u0e28");
            }
            if (textView4 != null) {
                textView4.setText((CharSequence)"\u0e0a\u0e33\u0e23\u0e30\u0e04\u0e48\u0e32\u0e19\u0e49\u0e33 \u0e44\u0e1f \u0e42\u0e17\u0e23\u0e28\u0e31\u0e1e\u0e22\u0e4c");
            }
            if (textView5 != null) {
                textView5.setText((CharSequence)"\u0e0a\u0e33\u0e23\u0e30\u0e04\u0e48\u0e32\u0e1a\u0e23\u0e34\u0e01\u0e32\u0e23\u0e2d\u0e37\u0e48\u0e19\u0e46");
            }
            if (textView6 == null) return;
            {
                textView6.setText((CharSequence)"\u0e2a\u0e41\u0e01\u0e19\u0e1a\u0e32\u0e23\u0e4c\u0e42\u0e04\u0e49\u0e14");
                return;
            }
        } else {
            if (textView != null) {
                textView.setText((CharSequence)"prepaid mobile");
            }
            if (textView2 != null) {
                textView2.setText((CharSequence)"prepaid game");
            }
            if (textView3 != null) {
                textView3.setText((CharSequence)"prepaid telephone");
            }
            if (textView4 != null) {
                textView4.setText((CharSequence)"pay for utility");
            }
            if (textView5 != null) {
                textView5.setText((CharSequence)"other utility");
            }
            if (textView6 == null) return;
            {
                textView6.setText((CharSequence)"scan barcode");
                return;
            }
        }
    }

    /*
     * Exception decompiling
     */
    @Override
    public void onCreate(Bundle var1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.util.ConcurrentModificationException
        // java.util.LinkedList$ReverseLinkIterator.next(LinkedList.java:217)
        // org.benf.cfr.reader.bytecode.analysis.structured.statement.Block.extractLabelledBlocks(Block.java:212)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement$LabelledBlockExtractor.transform(Op04StructuredStatement.java:485)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.transform(Op04StructuredStatement.java:639)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.insertLabelledBlocks(Op04StructuredStatement.java:649)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:816)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // com.njlabs.showjava.processor.JavaExtractor$1.run(JavaExtractor.java:100)
        // java.lang.Thread.run(Thread.java:818)
        throw new IllegalStateException("Decompilation failed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(this.ussdCatcherReceiver);
    }

    @Override
    public void onPause() {
        Log.v((String)"hello", (String)"unbind");
        this.unbindService(this.service);
        pause = true;
        super.onPause();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onResume() {
        super.onResume();
        this.cancel.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                MainPage.this.cancel.setOnClickListener(null);
                Intent intent = new Intent(view.getContext(), (Class)SampleActivity.class);
                MainPage.this.startActivityForResult(intent, 0);
            }
        });
        sharepreferences = this.getSharedPreferences("menu.header", 0);
        this.changeLanguage(sharepreferences.getInt("lang_id", 0));
        if (!this.passCreate) {
            AudioDemo.Sound().playSound("a0");
        }
        this.passCreate = false;
        if (!this.getSharedPreferences("hello", 0).getBoolean("isICT", false)) {
            isBV20 = true;
            Log.v((String)"hello", (String)("isbv20 " + IOServiceBV20.class));
            Intent intent = new Intent((Context)this, (Class)IOServiceBV20.class);
            this.startService(intent);
            this.bindService(intent, this.service, 1);
        } else {
            Log.v((String)"hello", (String)"isnotbv20");
            Intent intent = new Intent((Context)this, (Class)IOService.class);
            this.startService(intent);
            this.bindService(intent, this.service, 1);
        }
        SharedPreferences sharedPreferences = this.getSharedPreferences("hello", 0);
        Log.v((String)"hello", (String)("box open " + sharedPreferences.getBoolean("boxopen", true)));
        if (sharedPreferences.getBoolean("boxopen", true)) {
            if (isBV20) {
                IOServiceBV20.lockbox = false;
            } else {
                IOService.lockbox = false;
            }
            Log.v((String)"hello", (String)"can box open");
        } else {
            if (isBV20) {
                IOServiceBV20.lockbox = true;
            } else {
                IOService.lockbox = true;
            }
            Log.v((String)"hello", (String)"can't box open");
        }
        this.startService(new Intent((Context)this, (Class)LogChecker.class));
    }

    private class PhoneCallListener
    extends PhoneStateListener {
        String TAG;
        private boolean phoneCalling;
        final /* synthetic */ MainPage this$0;

        private PhoneCallListener(MainPage mainPage) {
            this.this$0 = mainPage;
            this.TAG = "LOGGING PHONE CALL";
            this.phoneCalling = false;
        }

        /* synthetic */ PhoneCallListener(MainPage mainPage, PhoneCallListener phoneCallListener) {
            super(mainPage);
        }

        public void onCallStateChanged(int n, String string2) {
            if (1 == n) {
                Log.i((String)this.TAG, (String)("RINGING, number: " + string2));
            }
            if (2 == n) {
                Log.i((String)this.TAG, (String)"OFFHOOK");
                this.phoneCalling = true;
            }
            if (n == 0) {
                Log.i((String)this.TAG, (String)"IDLE");
                if (this.phoneCalling) {
                    Log.i((String)this.TAG, (String)"restart app");
                    Intent intent = this.this$0.getBaseContext().getPackageManager().getLaunchIntentForPackage(this.this$0.getBaseContext().getPackageName());
                    intent.addFlags(67108864);
                    this.this$0.startActivity(intent);
                    this.phoneCalling = false;
                }
            }
        }
    }

}

