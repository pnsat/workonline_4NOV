/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.graphics.Typeface
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
package OWS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
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
import com.daydr3am.OWS.Loading;
import com.daydr3am.OWS.MessageTopup;
import com.daydr3am.lib.AudioDemo;
import com.daydr3am.lib.CallImage;

public class SelectPrice
extends IORootActivity {
    String[] imageBG = new String[]{"bg_blue", "bg_pink", "bg_green", "bg_orange"};
    int index_background;
    int[][][] payPrice = new int[][][]{{{10, 20, 30, 40, 50, 60, 100, 200, 300, 500}, {10, 20, 30, 40, 50, 60, 100, 200, 300, 500}, {10, 20, 30, 40, 50, 60, 100, 200, 300, 500}, {20, 50, 100, 300, 500, 800}, {10, 20, 50, 100, 300, 500}, {10, 20, 30, 40, 50, 60, 100, 200, 300, 500, 800}}, {{50, 90, 150, 300, 1000}, {28, 55, 89, 159, 189, 245, 349, 450, 888}, {25, 50, 90, 150, 300, 500, 1000}, {25, 50, 90, 150, 300, 500, 1000}, {49, 149, 299, 399, 555, 999}, {25, 55, 149, 199, 399}, {25, 50, 90, 150, 300, 500, 1000}, {50, 100, 200, 300, 500, 1000}, {50, 90, 150, 300}, {49, 99, 149, 299, 499, 799, 999}}, {{100}, {100, 300}}};

    @Override
    public void changeLanguage(int n) {
        Log.d((String)"debug", (String)("changeLanguage main " + n));
        TextView textView = (TextView)this.findViewById(2131361921);
        textView.setText((CharSequence)MessageTopup.getMessage(9));
        textView.setTypeface(MessageTopup.setFont((Context)this, 0));
        if (this.landTextShow != null) {
            this.landTextShow.setText((CharSequence)MessageTopup.getMessage(9));
        }
    }

    public void landscapeLayout(int n, int n2) {
        ViewGroup viewGroup = (ViewGroup)this.findViewById(2131361922);
        int n3 = this.payPrice[n][n2].length;
        int n4 = 0;
        while (n4 < n3) {
            LinearLayout linearLayout = (LinearLayout)viewGroup.getChildAt(n4 / 4);
            Button button = new Button((Context)this);
            button.setText((CharSequence)String.valueOf((int)this.payPrice[n][n2][n4]));
            button.setTextSize(10.0f);
            button.setBackgroundResource(2130837675);
            button.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-2, -2, 1.0f));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2, 1.0f);
            layoutParams.setMargins(5, 5, 0, 0);
            button.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
            button.setHint((CharSequence)("" + this.payPrice[n][n2][n4]));
            button.setTextSize(40.0f);
            button.setTypeface(null, 1);
            button.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    Button button = (Button)view;
                    Activity activity = (Activity)button.getContext();
                    String string2 = button.getHint().toString();
                    Bundle bundle = activity.getIntent().getExtras();
                    bundle.putString("param3", String.valueOf((Object)string2));
                    bundle.putString("Price", string2);
                    bundle.putInt("Service", 1);
                    bundle.putString("img_background", SelectPrice.this.imageBG[SelectPrice.this.index_background]);
                    Intent intent = new Intent((Context)SelectPrice.this, (Class)Loading.class);
                    intent.putExtras(bundle);
                    SelectPrice.this.startActivityForResult(intent, 0);
                    AudioDemo.Sound().playSound("a3");
                }
            });
            this.changeLanguage(switchID);
            linearLayout.addView((View)button);
            ++n4;
        }
        return;
    }

    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903061);
        super.onCreate(bundle);
        this.findViewById(2131361863).setVisibility(8);
        Bundle bundle2 = this.getIntent().getExtras();
        String string2 = bundle2.getString("param1");
        String string3 = bundle2.getString("param2");
        int n = -1 + Integer.parseInt((String)String.valueOf((char)string2.charAt(6)));
        int n2 = Integer.parseInt((String)string3);
        ((FrameLayout)this.findViewById(2131361799)).setBackgroundDrawable(CallImage.imageDrawableCard(this.imageBG[n]));
        this.index_background = n;
        if (this.getResources().getConfiguration().orientation == 2) {
            this.landscapeLayout(n, n2);
            return;
        }
        this.portraitLayout(n, n2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d((String)"debug", (String)"onResume active price");
        AudioDemo.Sound().playSound("a22");
    }

    public void portraitLayout(int n, int n2) {
        ViewGroup viewGroup = (ViewGroup)this.findViewById(2131361922);
        int n3 = this.payPrice[n][n2].length;
        int n4 = 0;
        while (n4 < n3) {
            LinearLayout linearLayout = (LinearLayout)viewGroup.getChildAt(n4 / 3);
            Button button = new Button((Context)this);
            button.setText((CharSequence)String.valueOf((int)this.payPrice[n][n2][n4]));
            button.setTextSize(10.0f);
            button.setBackgroundResource(2130837558);
            button.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-2, -2, 1.0f));
            button.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-2, -2, 1.0f));
            button.setHint((CharSequence)("" + this.payPrice[n][n2][n4]));
            button.setTextSize(22.0f);
            button.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    Button button = (Button)view;
                    Activity activity = (Activity)button.getContext();
                    String string2 = button.getHint().toString();
                    Bundle bundle = activity.getIntent().getExtras();
                    bundle.putString("param3", String.valueOf((Object)string2));
                    bundle.putString("Price", string2);
                    bundle.putInt("Service", 1);
                    bundle.putString("img_background", SelectPrice.this.imageBG[SelectPrice.this.index_background]);
                    Intent intent = new Intent((Context)SelectPrice.this, (Class)Loading.class);
                    intent.putExtras(bundle);
                    SelectPrice.this.startActivityForResult(intent, 0);
                    AudioDemo.Sound().playSound("a3");
                }
            });
            this.changeLanguage(switchID);
            linearLayout.addView((View)button);
            ++n4;
        }
        return;
    }

}

