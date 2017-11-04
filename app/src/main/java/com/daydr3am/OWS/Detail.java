/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.graphics.Typeface
 *  android.os.Bundle
 *  android.util.Log
 *  android.view.View
 *  android.widget.Button
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.String
 */
package com.daydr3am.OWS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.MessageTopup;

public class Detail
extends IORootActivity {
    @Override
    public void changeLanguage(int n) {
        Log.d((String)"debug", (String)("changeLanguage main " + n));
        TextView textView = (TextView)this.findViewById(2131361842);
        TextView textView2 = (TextView)this.findViewById(2131361843);
        TextView textView3 = (TextView)this.findViewById(2131361845);
        TextView textView4 = (TextView)this.findViewById(2131361847);
        TextView textView5 = (TextView)this.findViewById(2131361849);
        TextView textView6 = (TextView)this.findViewById(2131361851);
        TextView textView7 = (TextView)this.findViewById(2131361853);
        textView.setText((CharSequence)MessageTopup.getMessage(49));
        textView.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView2.setText((CharSequence)MessageTopup.getMessage(50));
        textView2.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView3.setText((CharSequence)MessageTopup.getMessage(51));
        textView3.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView4.setText((CharSequence)MessageTopup.getMessage(52));
        textView4.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView5.setText((CharSequence)MessageTopup.getMessage(53));
        textView5.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView6.setText((CharSequence)MessageTopup.getMessage(54));
        textView6.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView7.setTypeface(MessageTopup.setFont((Context)this, 0));
        this.cancel.setVisibility(8);
        this.next.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void onCreate(Bundle bundle) {
        String string2;
        this.setContentView(2130903045);
        super.onCreate(bundle);
        TextView textView = (TextView)this.findViewById(2131361844);
        TextView textView2 = (TextView)this.findViewById(2131361846);
        TextView textView3 = (TextView)this.findViewById(2131361848);
        TextView textView4 = (TextView)this.findViewById(2131361850);
        TextView textView5 = (TextView)this.findViewById(2131361852);
        TextView textView6 = (TextView)this.findViewById(2131361854);
        TextView textView7 = (TextView)this.findViewById(2131361855);
        TextView textView8 = (TextView)this.findViewById(2131361856);
        String[] arrstring = this.getIntent().getExtras().getString("returned").split("&");
        String string3 = arrstring[1].split("=")[1];
        String string4 = arrstring[2].split("=")[1];
        String string5 = arrstring[3].split("=")[1];
        String string6 = this.getSharedPreferences("hello", 0).getString("HARDWARE_ID", "");
        try {
            string2 = this.getPackageManager().getPackageInfo((String)this.getPackageName(), (int)0).versionName;
        }
        catch (PackageManager.NameNotFoundException var15_17) {
            var15_17.printStackTrace();
            string2 = null;
        }
        textView.setText((CharSequence)string6);
        textView2.setText((CharSequence)string2);
        textView3.setText((CharSequence)string3);
        textView4.setText((CharSequence)string4);
        textView5.setText((CharSequence)string5);
        SharedPreferences sharedPreferences = this.getSharedPreferences("hello", 0);
        textView6.setText((CharSequence)String.valueOf((int)sharedPreferences.getInt("MoneyLog", 0)));
        textView7.setText((CharSequence)sharedPreferences.getString("phonenum", ""));
        textView8.setText((CharSequence)sharedPreferences.getString("phonecredit", ""));
        this.changeLanguage(switchID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d((String)"debug", (String)"onResume active");
        this.changeLanguage(switchID);
    }
}

