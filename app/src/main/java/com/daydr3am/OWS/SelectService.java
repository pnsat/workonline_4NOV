/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.Button
 *  android.widget.FrameLayout
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 */
package com.daydr3am.OWS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.MessageTopup;
import com.daydr3am.OWS.Numpad;
import com.daydr3am.lib.AudioDemo;
import com.daydr3am.lib.CallImage;

public class SelectService
extends IORootActivity {
    int ScreenHeight;
    int ScreenWidth;

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void changeLanguage(int n) {
        Log.d((String)"debug", (String)("changeLanguage select service " + n));
        String string2 = this.getIntent().getExtras().getString("param1");
        TextView textView = (TextView)this.findViewById(2131361934);
        int n2 = -1 + Integer.parseInt((String)String.valueOf((char)string2.charAt(6)));
        if (n2 == 0) {
            textView.setText((CharSequence)MessageTopup.getMessage(5));
            if (this.landTextShow == null) return;
            {
                this.landTextShow.setText((CharSequence)MessageTopup.getMessage(5));
                return;
            }
        } else if (n2 == 1) {
            textView.setText((CharSequence)MessageTopup.getMessage(6));
            if (this.landTextShow == null) return;
            {
                this.landTextShow.setText((CharSequence)MessageTopup.getMessage(6));
                return;
            }
        } else {
            if (n2 != 2) return;
            {
                textView.setText((CharSequence)MessageTopup.getMessage(6));
                if (this.landTextShow == null) return;
                {
                    this.landTextShow.setText((CharSequence)MessageTopup.getMessage(6));
                    return;
                }
            }
        }
    }

    public void landscapeLayout(ViewGroup viewGroup, int n, int n2, String[][] arrstring) {
        int n3 = 0;
        block0 : while (n3 < n) {
            Log.v((String)"hello", (String)("index at " + n3 / 3));
            LinearLayout linearLayout = (LinearLayout)viewGroup.getChildAt(1 + n3 / 3);
            Button button = new Button((Context)this);
            button.setBackgroundDrawable(CallImage.imageDrawableCard(arrstring[n2][n3]));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(10, 5, 10, 5);
            button.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
            int n4 = 0;
            int n5 = 0;
            do {
                if (n5 >= n2) {
                    button.setTag((Object)("" + (n3 + n4) + " " + n2 + n3));
                    button.setOnClickListener(new View.OnClickListener(){

                        /*
                         * Enabled aggressive block sorting
                         */
                        public void onClick(View view) {
                            Button button = (Button)view;
                            Activity activity = (Activity)button.getContext();
                            String string2 = button.getTag().toString();
                            String string3 = string2.split(" ")[1];
                            String string4 = string2.split(" ")[0];
                            Log.v((String)"test", (String)("tag " + string4));
                            StringBuilder stringBuilder = new StringBuilder("N");
                            Object[] arrobject = new Object[]{1 + Integer.parseInt((String)string4)};
                            stringBuilder.append(String.format((String)"%03d", (Object[])arrobject)).toString();
                            Bundle bundle = activity.getIntent().getExtras();
                            bundle.putString("param2", String.valueOf((char)string3.charAt(1)));
                            Log.v((String)"hello", (String)("param 2 " + string3.charAt(1) + " " + string4));
                            int n = Integer.parseInt((String)String.valueOf((char)string3.charAt(0)));
                            int n2 = n == 0 ? 1 : (n == 1 ? 6 : 14);
                            Log.v((String)"hello", (String)(String.valueOf((int)n2) + " " + Integer.parseInt((String)new StringBuilder(String.valueOf((char)string3.charAt(1))).toString())));
                            bundle.putString("Network", String.valueOf((int)(n2 + Integer.parseInt((String)new StringBuilder(String.valueOf((char)string3.charAt(1))).toString()))));
                            if (n == 0 && Integer.parseInt((String)String.valueOf((char)string3.charAt(1))) == 5) {
                                bundle.putString("Network", "16");
                            }
                            Log.v((String)"test", (String)("network number " + bundle.getString("Network")));
                            Log.v((String)"test", (String)("setting " + n + " " + Integer.parseInt((String)new StringBuilder(String.valueOf((char)string3.charAt(1))).toString())));
                            Intent intent = new Intent(view.getContext(), (Class)Numpad.class);
                            intent.putExtras(bundle);
                            SelectService.this.startActivityForResult(intent, 0);
                        }
                    });
                    linearLayout.addView((View)button);
                    ++n3;
                    continue block0;
                }
                n4 += arrstring[n5].length;
                ++n5;
            } while (true);
            break;
        }
        return;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903064);
        super.onCreate(bundle);
        this.findViewById(2131361863).setVisibility(8);
        String string2 = this.getIntent().getExtras().getString("param1");
        ViewGroup viewGroup = (ViewGroup)this.findViewById(2131361933);
        TextView textView = (TextView)this.findViewById(2131361934);
        String[][] arrstring = new String[][]{{"bt_one2call", "bt_happy", "bt_true", "bt_hutch", "bt_cat", "bt_true_h"}, {"bt_true_money", "bt_cash", "bt_true_d_serial", "bt_cookie_card", "bt_winner", "bt_tot", "bt_play_card", "bt_gcash"}, {"bt_tolld", "bt_tookdee"}};
        int n = -1 + Integer.parseInt((String)String.valueOf((char)string2.charAt(6)));
        int n2 = arrstring[n].length;
        if (n == 0) {
            AudioDemo.Sound().playSound("a1");
            textView.setText((CharSequence)MessageTopup.getMessage(5));
        } else if (n == 1) {
            AudioDemo.Sound().playSound("b1");
            textView.setText((CharSequence)MessageTopup.getMessage(6));
        } else if (n == 2) {
            AudioDemo.Sound().playSound("c1");
            textView.setText((CharSequence)MessageTopup.getMessage(6));
        }
        String[] arrstring2 = new String[]{"bg_blue", "bg_pink", "bg_green", "bg_orange"};
        ((FrameLayout)this.findViewById(2131361799)).setBackgroundDrawable(CallImage.imageDrawableCard(arrstring2[n]));
        if (this.getResources().getConfiguration().orientation == 2) {
            this.landscapeLayout(viewGroup, n2, n, arrstring);
            return;
        }
        this.portraitLayout(viewGroup, n2, n, arrstring);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d((String)"debug", (String)"onResume active");
        Log.v((String)"hello", (String)"resume customtime");
    }

    public void portraitLayout(ViewGroup viewGroup, int n, int n2, String[][] arrstring) {
        int n3 = 0;
        block0 : while (n3 < n) {
            Log.v((String)"hello", (String)("index at " + n3 / 2));
            LinearLayout linearLayout = (LinearLayout)viewGroup.getChildAt(1 + n3 / 2);
            Button button = new Button((Context)this);
            button.setBackgroundDrawable(CallImage.imageDrawableCard(arrstring[n2][n3]));
            button.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-2, -2, 1.0f));
            button.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-2, -2));
            int n4 = 0;
            int n5 = 0;
            do {
                if (n5 >= n2) {
                    button.setTag((Object)("" + (n3 + n4) + " " + n2 + n3));
                    button.setOnClickListener(new View.OnClickListener(){

                        /*
                         * Enabled aggressive block sorting
                         */
                        public void onClick(View view) {
                            Button button = (Button)view;
                            Activity activity = (Activity)button.getContext();
                            String string2 = button.getTag().toString();
                            String string3 = string2.split(" ")[1];
                            String string4 = string2.split(" ")[0];
                            Log.v((String)"test", (String)("tag " + string4));
                            StringBuilder stringBuilder = new StringBuilder("N");
                            Object[] arrobject = new Object[]{1 + Integer.parseInt((String)string4)};
                            stringBuilder.append(String.format((String)"%03d", (Object[])arrobject)).toString();
                            Bundle bundle = activity.getIntent().getExtras();
                            bundle.putString("param2", String.valueOf((char)string3.charAt(1)));
                            Log.v((String)"hello", (String)("param 2 " + string3.charAt(1) + " " + string4));
                            int n = Integer.parseInt((String)String.valueOf((char)string3.charAt(0)));
                            int n2 = n == 0 ? 1 : (n == 1 ? 6 : 14);
                            Log.v((String)"hello", (String)(String.valueOf((int)n2) + " " + Integer.parseInt((String)new StringBuilder(String.valueOf((char)string3.charAt(1))).toString())));
                            bundle.putString("Network", String.valueOf((int)(n2 + Integer.parseInt((String)new StringBuilder(String.valueOf((char)string3.charAt(1))).toString()))));
                            if (n == 0 && Integer.parseInt((String)String.valueOf((char)string3.charAt(1))) == 5) {
                                bundle.putString("Network", "16");
                            }
                            Log.v((String)"test", (String)("network number " + bundle.getString("Network")));
                            Log.v((String)"test", (String)("setting " + n + " " + Integer.parseInt((String)new StringBuilder(String.valueOf((char)string3.charAt(1))).toString())));
                            Intent intent = new Intent(view.getContext(), (Class)Numpad.class);
                            intent.putExtras(bundle);
                            SelectService.this.startActivityForResult(intent, 0);
                        }
                    });
                    linearLayout.addView((View)button);
                    ++n3;
                    continue block0;
                }
                n4 += arrstring[n5].length;
                ++n5;
            } while (true);
            break;
        }
        return;
    }

}

