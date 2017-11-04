/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.graphics.Typeface
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.telephony.PhoneStateListener
 *  android.telephony.SignalStrength
 *  android.telephony.TelephonyManager
 *  android.text.method.MovementMethod
 *  android.text.method.ScrollingMovementMethod
 *  android.util.Log
 *  android.view.SurfaceHolder
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.Button
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.ImageView
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.text.SimpleDateFormat
 *  java.util.Calendar
 *  java.util.Date
 */
package com.daydr3am.OWS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.daydr3am.OWS.InputCoin;
import com.daydr3am.OWS.Loading;
import com.daydr3am.OWS.MainPage;
import com.daydr3am.OWS.MessageTopup;
import com.daydr3am.OWS.Preview;
import com.daydr3am.OWS.SampleActivity;
import com.daydr3am.OWS.SetMachine;
import com.daydr3am.lib.AudioDemo;
import com.daydr3am.lib.CallImage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class IORootActivity
extends Activity {
    public static final String PREFERENCES_FILENAME = "menu.header";
    public static SharedPreferences sharepreferences;
    public static int switchID;
    MyPhoneStateListener MyListener;
    TelephonyManager Tel;
    public boolean activityActive;
    Button back;
    Thread backTime;
    Button cancel;
    private SharedPreferences.Editor editor;
    View landBack;
    View landCancel;
    TextView landTextShow;
    Button next;
    String operatorName;
    Preview preview;
    public IORootActivity root;
    SurfaceHolder surfaceHolder;

    static /* synthetic */ void access$0(IORootActivity iORootActivity, SharedPreferences.Editor editor) {
        iORootActivity.editor = editor;
    }

    private String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int n = calendar.get(1);
        int n2 = 1 + calendar.get(2);
        int n3 = calendar.get(5);
        String[] arrstring = this.getDate(String.valueOf((int)n2) + "/" + n3 + "/" + n).split("/");
        String string2 = arrstring[0];
        String string3 = arrstring[1];
        Log.d((String)"debug", (String)("Day == > " + string2 + ", " + string3 + " " + n3 + ", " + n));
        return String.valueOf((Object)string2) + ", " + string3 + " " + n3 + ", " + n;
    }

    private String getDate(String string2) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String string3 = new SimpleDateFormat("E/MMM").format(simpleDateFormat.parse(string2));
            return string3;
        }
        catch (Exception var3_4) {
            var3_4.printStackTrace();
            return null;
        }
    }

    public void LandScapeEachPage() {
    }

    public void backToMainMenu() {
        Log.v((String)"hello", (String)("start come  " + this.getClass().toString()));
        try {
            Thread.sleep((long)120000);
            if (this.getClass().equals((Object)SetMachine.class)) {
                Thread.sleep((long)300000);
            }
            if (this.activityActive && !this.getClass().equals((Object)SampleActivity.class)) {
                Log.v((String)"hello", (String)("back to come customtime " + this.getClass().toString()));
                Intent intent = new Intent(this.getBaseContext(), (Class)MainPage.class);
                intent.setFlags(67108864);
                this.startActivity(intent);
            }
            return;
        }
        catch (InterruptedException var2_2) {
            var2_2.printStackTrace();
            return;
        }
    }

    public void changeLanguage(int n) {
        Log.d((String)"debug", (String)"changeLanguage");
    }

    /*
     * Enabled aggressive block sorting
     */
    public void changeLanguageRoot(int n) {
        Log.d((String)"debug", (String)"changeLanguageRoot");
        Button button = (Button)this.findViewById(2131361874);
        TextView textView = (TextView)this.findViewById(2131361876);
        String string2 = "";
        if (n == 1) {
            string2 = String.valueOf((Object)string2) + "_th";
            button.setBackgroundDrawable(CallImage.imageDrawableCard("thai"));
        } else if (n == 2) {
            string2 = String.valueOf((Object)string2) + "_en";
            button.setBackgroundDrawable(CallImage.imageDrawableCard("eng"));
        }
        textView.setText((CharSequence)super.getCurrentDate());
        if (this.back != null) {
            this.back.setBackgroundDrawable(CallImage.imageDrawableCard("bt_back" + string2));
        }
        if (this.cancel != null) {
            this.cancel.setBackgroundDrawable(CallImage.imageDrawableCard("bt_cancel" + string2));
        }
        if (this.next != null) {
            this.next.setBackgroundDrawable(CallImage.imageDrawableCard("bt_next" + string2));
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void manageLandscape() {
        View view = this.findViewById(2131361882);
        View view2 = this.findViewById(2131361883);
        View view3 = this.findViewById(2131361865);
        View view4 = this.findViewById(2131361887);
        if (view4 != null && !(this instanceof MainPage)) {
            view4.setVisibility(8);
        }
        if (view != null && !(this instanceof SampleActivity)) {
            view.setVisibility(8);
            this.landCancel = this.findViewById(2131361884);
            this.landBack = this.findViewById(2131361886);
            if (this.landBack != null) {
                Log.v((String)"hello", (String)"set back");
                this.landBack.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        IORootActivity.this.back.setOnClickListener(null);
                        IORootActivity.this.cancel.setOnClickListener(null);
                        IORootActivity.this.finish();
                        Log.v((String)"hello", (String)"call back");
                    }
                });
            }
            if (this.landCancel != null) {
                this.landCancel.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        IORootActivity.this.back.setOnClickListener(null);
                        IORootActivity.this.cancel.setOnClickListener(null);
                        Intent intent = new Intent(IORootActivity.this.getBaseContext(), (Class)MainPage.class);
                        intent.setFlags(67108864);
                        IORootActivity.this.startActivity(intent);
                    }
                });
            }
        }
        if (view2 != null && this instanceof SampleActivity) {
            view2.setVisibility(8);
        }
        if (view3 != null && !(this instanceof MainPage)) {
            view3.setVisibility(8);
        }
        TextView textView = (TextView)this.findViewById(2131361866);
        TextView textView2 = (TextView)this.findViewById(2131361867);
        try {
            String string2 = this.getPackageManager().getPackageInfo((String)this.getPackageName(), (int)0).versionName;
            if (textView2 != null) {
                textView2.setText((CharSequence)("[V" + string2 + "]"));
            }
        }
        catch (PackageManager.NameNotFoundException var7_8) {
            var7_8.printStackTrace();
        }
        this.getSharedPreferences("hello", 0);
        if (textView == null) return;
        try {
            textView.setText((CharSequence)Loading.getStringFromFile("/mnt/sdcard/Resource/Error.txt"));
            return;
        }
        catch (Exception var9_9) {
            if (textView == null) return;
            textView.setText((CharSequence)"\u0e41\u0e08\u0e49\u0e07\u0e1b\u0e31\u0e0d\u0e2b\u0e32 090-885-2896");
            return;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.root = this;
        CallImage.baseContext = this.getBaseContext();
        AudioDemo.baseContext = this.getBaseContext();
        MessageTopup.baseContext = this.getBaseContext();
        TextView textView = (TextView)this.findViewById(2131361873);
        TextView textView2 = (TextView)this.findViewById(2131361864);
        Button button = (Button)this.findViewById(2131361874);
        this.back = (Button)this.findViewById(2131361861);
        this.cancel = (Button)this.findViewById(2131361862);
        this.next = (Button)this.findViewById(2131361863);
        if (textView != null) {
            textView.setTypeface(MessageTopup.setFont((Context)this, 0));
        }
        if (textView2 != null) {
            textView2.setText(2131230721);
            textView2.setTypeface(MessageTopup.setFont((Context)this, 0));
        }
        sharepreferences = this.getSharedPreferences("menu.header", 0);
        switchID = sharepreferences.getInt("lang_id", 1);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener(){

                /*
                 * Enabled aggressive block sorting
                 */
                public void onClick(View view) {
                    if (IORootActivity.switchID == 1) {
                        IORootActivity.switchID = 2;
                    } else if (IORootActivity.switchID == 2) {
                        IORootActivity.switchID = 1;
                    }
                    IORootActivity.sharepreferences = IORootActivity.this.getSharedPreferences("menu.header", 0);
                    IORootActivity.access$0(IORootActivity.this, IORootActivity.sharepreferences.edit());
                    IORootActivity.this.editor.putInt("lang_id", IORootActivity.switchID);
                    IORootActivity.this.editor.commit();
                    IORootActivity.this.changeLanguageRoot(IORootActivity.switchID);
                    IORootActivity.this.changeLanguage(IORootActivity.switchID);
                }
            });
        }
        if (this.back != null) {
            this.back.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    IORootActivity.this.back.setOnClickListener(null);
                    IORootActivity.this.cancel.setOnClickListener(null);
                    IORootActivity.this.finish();
                }
            });
        }
        if (this.cancel != null) {
            this.cancel.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    IORootActivity.this.back.setOnClickListener(null);
                    IORootActivity.this.cancel.setOnClickListener(null);
                    Log.v((String)"hello", (String)"cancel click 1");
                    Intent intent = new Intent(IORootActivity.this.getBaseContext(), (Class)MainPage.class);
                    intent.setFlags(67108864);
                    IORootActivity.this.startActivity(intent);
                }
            });
        }
        Log.v((String)"hello", (String)"call from cre");
        this.changeLanguageRoot(switchID);
        ((TextView)this.findViewById(2131361873)).setMovementMethod((MovementMethod)new ScrollingMovementMethod());
        this.MyListener = new MyPhoneStateListener((IORootActivity)this, null);
        this.Tel = (TelephonyManager)this.getSystemService("phone");
        this.Tel.listen((PhoneStateListener)this.MyListener, 256);
        this.operatorName = this.Tel.getSimOperatorName();
        if (this.operatorName.length() == 0) {
            this.operatorName = this.Tel.getSimOperator();
        }
        Log.v((String)"hello", (String)("Operator=" + this.operatorName.length() + "X"));
    }

    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    protected void onPause() {
        this.activityActive = false;
        if (this.preview != null) {
            this.preview.pause();
        }
        if (this.backTime != null) {
            this.backTime.interrupt();
        }
        this.Tel.listen((PhoneStateListener)this.MyListener, 0);
        super.onPause();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onResume() {
        super.onResume();
        this.Tel.listen((PhoneStateListener)this.MyListener, 256);
        this.activityActive = true;
        this.landTextShow = (TextView)this.findViewById(2131361885);
        TextView textView = (TextView)this.findViewById(2131361873);
        textView.setFocusable(true);
        textView.setFocusableInTouchMode(true);
        textView.requestFocus();
        TextView textView2 = (TextView)this.findViewById(2131361864);
        SharedPreferences sharedPreferences = this.getSharedPreferences("hello", 0);
        textView.setText((CharSequence)sharedPreferences.getString("scrollText", "hello").trim());
        if (this.getClass().equals((Object)InputCoin.class)) {
            textView.setText((CharSequence)"");
        }
        if (sharedPreferences.getBoolean("NetworkOK", true)) {
            String string2;
            String string3 = null;
            try {
                string3 = this.getPackageManager().getPackageInfo((String)this.getPackageName(), (int)0).versionName;
            }
            catch (PackageManager.NameNotFoundException var11_12) {
                var11_12.printStackTrace();
            }
            SharedPreferences sharedPreferences2 = this.getSharedPreferences("hello", 0);
            try {
                String string4;
                string2 = string4 = String.valueOf((Object)string3) + " : " + Loading.getStringFromFile("/mnt/sdcard/Resource/Error.txt");
            }
            catch (Exception var13_13) {
                string2 = String.valueOf((Object)string3) + " : " + "\u0e41\u0e08\u0e49\u0e07\u0e1b\u0e31\u0e0d\u0e2b\u0e32 090-885-2896";
            }
            TextView textView3 = (TextView)this.findViewById(2131361872);
            if (sharedPreferences2.getString("PARTNER_ID", "") == null || sharedPreferences2.getString("PARTNER_ID", "").length() == 0) {
                textView3.setText((CharSequence)("000-" + sharedPreferences2.getString("HARDWARE_ID", "").substring(4)));
            } else {
                textView3.setText((CharSequence)(String.valueOf((Object)sharedPreferences2.getString("PARTNER_ID", "")) + "-" + sharedPreferences2.getString("HARDWARE_ID", "").substring(4)));
            }
            textView2.setText((CharSequence)string2);
            textView2.setTypeface(MessageTopup.setFont((Context)this, 0));
        } else {
            textView2.setText((CharSequence)"Can't Connect network");
            textView2.setTextColor(-65536);
        }
        this.backTime = new Thread(new Runnable(){

            public void run() {
                IORootActivity.this.root.backToMainMenu();
            }
        });
        this.backTime.start();
        ImageView imageView = new ImageView((Context)this);
        ImageView imageView2 = (ImageView)this.findViewById(2131361870);
        Drawable drawable2 = CallImage.getimage("ic_launcher");
        Log.v((String)"hello", (String)("draw " + (Object)drawable2 + " " + (Object)imageView2));
        if (drawable2 == null) {
            imageView.setBackgroundDrawable(CallImage.imageDrawableCard("ic_launcher"));
            imageView2.setImageDrawable(CallImage.imageDrawableCard("ic_launcher"));
        } else {
            imageView.setBackgroundDrawable(drawable2);
            imageView2.setImageDrawable(drawable2);
        }
        imageView.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2, 17));
        ((FrameLayout)this.findViewById(2131361871)).addView((View)imageView);
        this.changeLanguage(switchID);
        this.changeLanguageRoot(switchID);
        this.manageLandscape();
        this.LandScapeEachPage();
        Log.v((String)"hello", (String)("head " + textView.isFocused()));
    }

    private class MyPhoneStateListener
    extends PhoneStateListener {
        final /* synthetic */ IORootActivity this$0;

        private MyPhoneStateListener(IORootActivity iORootActivity) {
            this.this$0 = iORootActivity;
        }

        /* synthetic */ MyPhoneStateListener(IORootActivity iORootActivity, MyPhoneStateListener myPhoneStateListener) {
            super(iORootActivity);
        }

        /*
         * Enabled aggressive block sorting
         */
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            TextView textView = (TextView)this.this$0.findViewById(2131361877);
            ImageView imageView = (ImageView)this.this$0.findViewById(2131361878);
            if (imageView != null) {
                int n = -113 + 2 * signalStrength.getGsmSignalStrength();
                Log.v((String)"hello", (String)("signal now " + n));
                if (n >= -65) {
                    imageView.setBackgroundResource(2130837686);
                } else if (n < -65 && n >= -79) {
                    imageView.setBackgroundResource(2130837685);
                } else if (n < -80 && n >= -89) {
                    imageView.setBackgroundResource(2130837684);
                } else if (n < -90 && n >= -99) {
                    imageView.setBackgroundResource(2130837683);
                } else if (n < -100 && n >= -105) {
                    imageView.setBackgroundResource(2130837682);
                } else if (n < -105) {
                    imageView.setBackgroundResource(2130837681);
                }
            }
            if (this.this$0.operatorName.length() == 0) {
                textView.setText((CharSequence)" ");
                return;
            }
            if (signalStrength.getGsmSignalStrength() <= 35) {
                "" + signalStrength.getGsmSignalStrength();
            }
            textView.setText((CharSequence)this.this$0.operatorName);
        }
    }

}

