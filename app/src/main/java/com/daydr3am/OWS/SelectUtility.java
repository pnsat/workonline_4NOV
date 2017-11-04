/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
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
 */
package com.daydr3am.OWS;

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
import com.daydr3am.OWS.NumpadUtil;
import com.daydr3am.lib.AudioDemo;
import com.daydr3am.lib.CallImage;

public class SelectUtility
extends IORootActivity {
    int ScreenHeight;
    int ScreenWidth;
    final int[] value = new int[]{22, 23, 25, 24, 27, 21, 69, 17, 18, 20};
    final String[] word = new String[]{"\u0e44\u0e1f\u0e1f\u0e49\u0e32\u0e19\u0e04\u0e23\u0e2b\u0e25\u0e27\u0e07", "\u0e44\u0e1f\u0e1f\u0e49\u0e32\u0e20\u0e39\u0e21\u0e34\u0e20\u0e32\u0e04", "\u0e1b\u0e30\u0e1b\u0e32\u0e20\u0e39\u0e21\u0e34\u0e20\u0e32\u0e04", "\u0e1b\u0e30\u0e1b\u0e32\u0e19\u0e04\u0e23\u0e2b\u0e25\u0e27\u0e07", "TOT", "GSM", "Dtac", "True home phone", "True mobile phone", "True internet"};

    @Override
    public void changeLanguage(int n) {
        ((TextView)this.findViewById(2131361934)).setText((CharSequence)MessageTopup.getMessage(6));
        if (this.landTextShow != null) {
            this.landTextShow.setText((CharSequence)MessageTopup.getMessage(6));
        }
    }

    public void landscapeLayout(ViewGroup viewGroup, int n, int n2, String[] arrstring) {
        int n3 = 0;
        block0 : while (n3 < n) {
            LinearLayout linearLayout = (LinearLayout)viewGroup.getChildAt(1 + n3 / 4);
            Button button = new Button((Context)this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(7, 5, 7, 5);
            button.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
            Resources resources = this.getResources();
            String string2 = "bt_service" + this.value[n3];
            Log.v((String)"hello", (String)("str " + string2));
            int n4 = resources.getIdentifier(string2, "drawable", this.getPackageName());
            Log.v((String)"hello", (String)("str " + string2 + " " + n4));
            button.setBackgroundDrawable(resources.getDrawable(n4));
            int n5 = 0;
            int n6 = 0;
            do {
                if (n6 >= n2) {
                    button.setTag((Object)("" + n3));
                    button.setOnClickListener(new View.OnClickListener(){

                        /*
                         * Enabled aggressive block sorting
                         */
                        public void onClick(View view) {
                            int n = Integer.parseInt((String)((Button)view).getTag().toString());
                            Bundle bundle = new Bundle();
                            int n2 = n < 2 ? 5 : (n == 2 ? 6 : (n == 3 ? 7 : (n == 4 ? 5 : 3)));
                            bundle.putInt("DataMax", n2);
                            bundle.putInt("CurrentData", 0);
                            bundle.putString("Network", String.valueOf((int)SelectUtility.this.value[n]));
                            bundle.putInt("Index", n);
                            Intent intent = new Intent(view.getContext(), (Class)NumpadUtil.class);
                            intent.putExtras(bundle);
                            SelectUtility.this.startActivityForResult(intent, 0);
                        }
                    });
                    linearLayout.addView((View)button);
                    ++n3;
                    continue block0;
                }
                n5 += arrstring.length;
                ++n6;
            } while (true);
            break;
        }
        return;
    }

    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903064);
        super.onCreate(bundle);
        this.findViewById(2131361863).setVisibility(8);
        ViewGroup viewGroup = (ViewGroup)this.findViewById(2131361933);
        TextView textView = (TextView)this.findViewById(2131361934);
        int n = this.word.length;
        AudioDemo.Sound().playSound("c1");
        textView.setText((CharSequence)MessageTopup.getMessage(6));
        String[] arrstring = new String[]{"bg_blue", "bg_pink", "bg_green", "bg_orange"};
        ((FrameLayout)this.findViewById(2131361799)).setBackgroundDrawable(CallImage.imageDrawableCard(arrstring[3]));
        if (this.getResources().getConfiguration().orientation == 2) {
            this.landscapeLayout(viewGroup, n, 3, this.word);
            return;
        }
        this.portraitLayout(viewGroup, n, 3, this.word);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AudioDemo.Sound().playSound("e100");
        Log.d((String)"debug", (String)"onResume active");
    }

    public void portraitLayout(ViewGroup viewGroup, int n, int n2, String[] arrstring) {
        int n3 = 0;
        block0 : while (n3 < n) {
            LinearLayout linearLayout = (LinearLayout)viewGroup.getChildAt(1 + n3 / 2);
            Log.v((String)"hello", (String)("row " + n3 + " " + (1 + n3 / 2)));
            Button button = new Button((Context)this);
            button.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-2, -2, 1.0f));
            button.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-2, -2));
            Resources resources = this.getResources();
            String string2 = "bt_service" + this.value[n3];
            Log.v((String)"hello", (String)("str " + string2));
            int n4 = resources.getIdentifier(string2, "drawable", this.getPackageName());
            Log.v((String)"hello", (String)("str " + string2 + " " + n4));
            button.setBackgroundDrawable(resources.getDrawable(n4));
            int n5 = 0;
            int n6 = 0;
            do {
                if (n6 >= n2) {
                    button.setTag((Object)("" + n3));
                    button.setOnClickListener(new View.OnClickListener(){

                        /*
                         * Enabled aggressive block sorting
                         */
                        public void onClick(View view) {
                            int n = Integer.parseInt((String)((Button)view).getTag().toString());
                            Bundle bundle = new Bundle();
                            int n2 = n < 2 ? 5 : (n == 2 ? 6 : (n == 3 ? 7 : (n == 4 ? 5 : 3)));
                            bundle.putInt("DataMax", n2);
                            bundle.putInt("CurrentData", 0);
                            bundle.putString("Network", String.valueOf((int)SelectUtility.this.value[n]));
                            bundle.putInt("Index", n);
                            Intent intent = new Intent(view.getContext(), (Class)NumpadUtil.class);
                            intent.putExtras(bundle);
                            SelectUtility.this.startActivityForResult(intent, 0);
                        }
                    });
                    linearLayout.addView((View)button);
                    ++n3;
                    continue block0;
                }
                n5 += arrstring.length;
                ++n6;
            } while (true);
            break;
        }
        return;
    }

}

