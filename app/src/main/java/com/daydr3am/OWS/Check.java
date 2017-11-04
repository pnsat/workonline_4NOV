/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.graphics.Typeface
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.os.Handler
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 */
package com.daydr3am.OWS;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.InputCoin;
import com.daydr3am.OWS.MessageTopup;
import com.daydr3am.lib.AudioDemo;
import com.daydr3am.lib.CallImage;

public class Check
extends IORootActivity {
    public int moneyInput = 20;

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void changeLanguage(int n) {
        Log.d((String)"debug", (String)("changeLanguage main " + n));
        TextView textView = (TextView)this.findViewById(2131361801);
        TextView textView2 = (TextView)this.findViewById(2131361803);
        TextView textView3 = (TextView)this.findViewById(2131361805);
        TextView textView4 = (TextView)this.findViewById(2131361807);
        TextView textView5 = (TextView)this.findViewById(2131361810);
        TextView textView6 = (TextView)this.findViewById(2131361804);
        TextView textView7 = (TextView)this.findViewById(2131361806);
        TextView textView8 = (TextView)this.findViewById(2131361809);
        TextView textView9 = (TextView)this.findViewById(2131361812);
        TextView textView10 = (TextView)this.findViewById(2131361813);
        textView.setText((CharSequence)MessageTopup.getMessage(11));
        textView.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView2.setText((CharSequence)MessageTopup.getMessage(12));
        textView2.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView3.setText((CharSequence)MessageTopup.getMessage(13));
        textView3.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView4.setText((CharSequence)MessageTopup.getMessage(14));
        textView4.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView5.setText((CharSequence)MessageTopup.getMessage(15));
        textView5.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView6.setText((CharSequence)MessageTopup.getMessage(16));
        textView6.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView7.setText((CharSequence)MessageTopup.getMessage(16));
        textView7.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView8.setText((CharSequence)MessageTopup.getMessage(16));
        textView8.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView9.setText((CharSequence)MessageTopup.getMessage(16));
        textView9.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView10.setText((CharSequence)MessageTopup.getMessage(17));
        textView10.setTypeface(MessageTopup.setFont((Context)this, 0));
        String string2 = "";
        if (n == 1) {
            string2 = String.valueOf((Object)string2) + "_th";
        } else if (n == 2) {
            string2 = String.valueOf((Object)string2) + "_en";
        }
        if (this.next != null) {
            this.next.setBackgroundDrawable(CallImage.imageDrawableCard("bt_pay" + string2));
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903042);
        super.onCreate(bundle);
        ImageView imageView = (ImageView)this.findViewById(2131361800);
        String[][] arrarrstring = new String[][]{{"logo_one2call", "logo_happy", "logo_true", "logo_hutch", "logo_cat", "logo_true_h"}, {"logo_true", "logo_cash", "logo_dserial", "logo_cookiecard", "logo_winner", "logo_tot", "logo_play", "logo_gcash"}, {"logo_tolld", "logo_tookdee"}};
        Bundle bundle2 = this.getIntent().getExtras();
        String string2 = bundle2.getString("param1");
        String string3 = bundle2.getString("param2");
        int n = -1 + Integer.parseInt((String)String.valueOf((char)string2.charAt(6)));
        int n2 = Integer.parseInt((String)string3);
        Log.v((String)"test", (String)("index " + n + " " + n2));
        Log.v((String)"test", (String)("picture " + arrarrstring[n][n2]));
        Log.d((String)"debug", (String)("logo " + (Object)this.findViewById(2131361820)));
        imageView.setImageDrawable(CallImage.imageDrawableCard(arrarrstring[n][n2]));
        EditText editText = (EditText)this.findViewById(2131361802);
        ((FrameLayout)this.findViewById(2131361799)).setBackgroundDrawable(CallImage.imageDrawableCard(bundle2.getString("img_background")));
        editText.setText((CharSequence)bundle2.getString("param4"));
        editText.setEnabled(false);
        int n3 = Integer.parseInt((String)bundle2.getString("param3"));
        int n4 = bundle2.getInt("OR");
        int n5 = bundle2.getInt("MC");
        ((TextView)this.findViewById(2131361796)).setText((CharSequence)String.valueOf((int)n3));
        ((TextView)this.findViewById(2131361798)).setText((CharSequence)String.valueOf((int)n4));
        ((TextView)this.findViewById(2131361808)).setText((CharSequence)String.valueOf((int)n5));
        TextView textView = (TextView)this.findViewById(2131361811);
        if (n3 + n4 - n5 > 0) {
            textView.setText((CharSequence)String.valueOf((int)(n3 + n4 - n5)));
        } else {
            textView.setText((CharSequence)"0");
        }
        Button button = (Button)this.findViewById(2131361863);
        View.OnClickListener onClickListener = new View.OnClickListener(){

            public void onClick(View view) {
                Bundle bundle = ((Check)((Button)view).getContext()).getIntent().getExtras();
                Intent intent = new Intent(view.getContext(), (Class)InputCoin.class);
                intent.putExtras(bundle);
                Check.this.startActivityForResult(intent, 0);
            }
        };
        button.setOnClickListener(onClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.changeLanguage(switchID);
        Log.d((String)"debug", (String)"onResume active");
        AudioDemo.Sound().playSound("a4");
        new Handler().postDelayed(new Runnable(){

            public void run() {
                if (Check.this.activityActive) {
                    Bundle bundle = Check.this.getIntent().getExtras();
                    Intent intent = new Intent(Check.this.getApplicationContext(), (Class)InputCoin.class);
                    intent.putExtras(bundle);
                    Check.this.startActivityForResult(intent, 0);
                }
            }
        }, 4000);
    }

}

