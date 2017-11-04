/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.graphics.Typeface
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.text.Editable
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
package com.daydr3am.OWS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.Loading;
import com.daydr3am.OWS.MessageTopup;
import com.daydr3am.OWS.SelectPrice;
import com.daydr3am.lib.AudioDemo;
import com.daydr3am.lib.CallImage;

public class Numpad
extends IORootActivity {
    String[] imageBG = new String[]{"bg_blue", "bg_pink", "bg_green", "bg_orange"};
    int index_background;
    View.OnClickListener nextAction;
    int[][][] payPrice = new int[][][]{{{10, 20, 30, 40, 50, 60, 100, 200, 300, 500}, {10, 20, 30, 40, 50, 60, 100, 200, 300, 500}, {10, 20, 30, 40, 50, 60, 100, 200, 300, 500}, {20, 50, 100, 300, 500, 800}, {10, 20, 50, 100, 300, 500}}, {{50, 90, 150, 300, 1000}, {28, 55, 89, 159, 189, 245, 349, 450, 888}, {25, 50, 90, 150, 300, 500, 1000}, {25, 50, 90, 150, 300, 500, 1000}, {49, 149, 299, 399, 555, 999}, {25, 55, 149, 199, 399}, {25, 50, 90, 150, 300, 500, 1000}, {50, 100, 200, 300, 500, 1000}, {50, 90, 150, 300}, {49, 99, 149, 299, 499, 799, 999}}, {{100}, {100, 300}}};

    public Numpad() {
        this.nextAction = new View.OnClickListener(){

            public void onClick(View view) {
                Activity activity = (Activity)((Button)view).getContext();
                EditText editText = (EditText)Numpad.this.findViewById(2131361802);
                Log.v((String)"hello", (String)("length " + editText.getText().toString().length()));
                if (editText.getText().toString().length() == 10) {
                    Bundle bundle = activity.getIntent().getExtras();
                    bundle.putString("param4", editText.getText().toString());
                    bundle.putString("Mobile", editText.getText().toString());
                    bundle.putInt("Service", 1);
                    bundle.putString("img_background", Numpad.this.imageBG[Numpad.this.index_background]);
                    Intent intent = new Intent(view.getContext(), (Class)SelectPrice.class);
                    intent.putExtras(bundle);
                    Numpad.this.startActivityForResult(intent, 0);
                    return;
                }
                Bundle bundle = activity.getIntent().getExtras();
                bundle.putString("img_background", Numpad.this.imageBG[Numpad.this.index_background]);
                bundle.putString("ShowError", MessageTopup.getMessage(68));
                Intent intent = new Intent(view.getContext(), (Class)Loading.class);
                intent.putExtras(bundle);
                Numpad.this.startActivityForResult(intent, 0);
            }
        };
    }

    private void setImageNumpad() {
        Button button = (Button)this.findViewById(2131361797);
        Button button2 = (Button)this.findViewById(2131361826);
        Button button3 = (Button)this.findViewById(2131361827);
        Button button4 = (Button)this.findViewById(2131361829);
        Button button5 = (Button)this.findViewById(2131361830);
        Button button6 = (Button)this.findViewById(2131361831);
        Button button7 = (Button)this.findViewById(2131361833);
        Button button8 = (Button)this.findViewById(2131361834);
        Button button9 = (Button)this.findViewById(2131361835);
        Button button10 = (Button)this.findViewById(2131361837);
        Button button11 = (Button)this.findViewById(2131361838);
        Button button12 = (Button)this.findViewById(2131361839);
        button.setBackgroundDrawable(CallImage.imageDrawableCard("bt_number"));
        button2.setBackgroundDrawable(CallImage.imageDrawableCard("bt_number"));
        button3.setBackgroundDrawable(CallImage.imageDrawableCard("bt_number"));
        button4.setBackgroundDrawable(CallImage.imageDrawableCard("bt_number"));
        button5.setBackgroundDrawable(CallImage.imageDrawableCard("bt_number"));
        button6.setBackgroundDrawable(CallImage.imageDrawableCard("bt_number"));
        button7.setBackgroundDrawable(CallImage.imageDrawableCard("bt_number"));
        button8.setBackgroundDrawable(CallImage.imageDrawableCard("bt_number"));
        button9.setBackgroundDrawable(CallImage.imageDrawableCard("bt_number"));
        button10.setBackgroundDrawable(CallImage.imageDrawableCard("bt_number"));
        button11.setBackgroundDrawable(CallImage.imageDrawableCard("bt_number"));
        button12.setBackgroundDrawable(CallImage.imageDrawableCard("bt_number"));
    }

    @Override
    public void changeLanguage(int n) {
        TextView textView = (TextView)this.findViewById(2131361821);
        textView.setText((CharSequence)MessageTopup.getMessage(8));
        textView.setTypeface(MessageTopup.setFont((Context)this, 0));
        Button button = (Button)this.findViewById(2131361839);
        if (button != null) {
            button.setText((CharSequence)MessageTopup.getMessage(80));
        }
        if (this.landTextShow != null) {
            this.landTextShow.setText((CharSequence)MessageTopup.getMessage(8));
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903057);
        super.onCreate(bundle);
        this.findViewById(2131361863).setVisibility(8);
        String[][] arrarrstring = new String[][]{{"logo_one2call", "logo_happy", "logo_true", "logo_hutch", "logo_cat", "logo_true_h"}, {"logo_true", "logo_cash", "logo_dserial", "logo_cookiecard", "logo_winner", "logo_tot", "logo_play", "logo_gcash"}, {"logo_tolld", "logo_tookdee"}};
        Bundle bundle2 = this.getIntent().getExtras();
        String string2 = bundle2.getString("param1");
        String string3 = bundle2.getString("param2");
        int n = -1 + Integer.parseInt((String)String.valueOf((char)string2.charAt(6)));
        int n2 = Integer.parseInt((String)string3);
        Log.v((String)"test", (String)("index " + n + " " + n2));
        Log.v((String)"test", (String)("picture " + arrarrstring[n][n2]));
        ((EditText)this.findViewById(2131361802)).setEnabled(false);
        Log.d((String)"debug", (String)("MessageTopup >> " + MessageTopup.getMessage(3)));
        Log.d((String)"debug", (String)("logo " + (Object)this.findViewById(2131361820)));
        ((ImageView)this.findViewById(2131361820)).setImageDrawable(CallImage.imageDrawableCard(arrarrstring[n][n2]));
        ((FrameLayout)this.findViewById(2131361799)).setBackgroundDrawable(CallImage.imageDrawableCard(this.imageBG[n]));
        this.index_background = n;
        ViewGroup viewGroup = (ViewGroup)this.findViewById(2131361824);
        int n3 = viewGroup.getChildCount();
        int n4 = 0;
        block0 : do {
            if (n4 >= n3) {
                this.next.setOnClickListener(this.nextAction);
                return;
            }
            ViewGroup viewGroup2 = (ViewGroup)viewGroup.getChildAt(n4);
            int n5 = viewGroup2.getChildCount();
            int n6 = 0;
            do {
                if (n6 >= n5) {
                    ++n4;
                    continue block0;
                }
                Button button = (Button)viewGroup2.getChildAt(n6);
                View.OnClickListener onClickListener = new View.OnClickListener(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    public void onClick(View view) {
                        EditText editText = (EditText)Numpad.this.findViewById(2131361802);
                        Log.v((String)"hello", (String)("length " + editText.getText().length()));
                        String string2 = ((Button)view).getHint().toString();
                        if (string2.equals((Object)"delete")) {
                            if (editText.getText().length() >= 1) {
                                editText.setText(editText.getText().toString().toCharArray(), 0, -1 + editText.getText().length());
                                if (Numpad.this.getSharedPreferences("hello", 0).getBoolean("Sound", true)) {
                                    AudioDemo.Sound().playSound("del");
                                }
                            }
                        } else if (string2.equals((Object)"star") && editText.getText().length() < 10) {
                            editText.setText((CharSequence)(String.valueOf((Object)editText.getText().toString()) + "*"));
                        } else if (editText.getText().length() < 10) {
                            if (Numpad.this.getSharedPreferences("hello", 0).getBoolean("Sound", true)) {
                                AudioDemo.Sound().playSound("z" + string2);
                            }
                            editText.setText((CharSequence)(String.valueOf((Object)editText.getText().toString()) + string2));
                        }
                        if (editText.getText().length() == 10) {
                            Button button = (Button)Numpad.this.findViewById(2131361840);
                            button.setOnClickListener(Numpad.this.nextAction);
                            button.setVisibility(0);
                        }
                    }
                };
                button.setOnClickListener(onClickListener);
                ++n6;
            } while (true);
            break;
        } while (true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d((String)"debug", (String)"onResume active");
        AudioDemo.Sound().playSound("a21");
    }

}

