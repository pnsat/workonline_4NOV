/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.graphics.Typeface
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.FrameLayout
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package com.daydr3am.OWS;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.MessageTopup;
import com.daydr3am.OWS.PlayVideo;
import com.daydr3am.lib.CallImage;

public class SelectVideo
extends IORootActivity {
    @Override
    public void changeLanguage(int n) {
        Log.d((String)"debug", (String)("changeLanguage main " + n));
        TextView textView = (TextView)this.findViewById(2131361941);
        Button button = (Button)this.findViewById(2131361942);
        Button button2 = (Button)this.findViewById(2131361943);
        Button button3 = (Button)this.findViewById(2131361944);
        Button button4 = (Button)this.findViewById(2131361945);
        Button button5 = (Button)this.findViewById(2131361946);
        button.setBackgroundDrawable(CallImage.imageDrawableCard("bt_menu"));
        button2.setBackgroundDrawable(CallImage.imageDrawableCard("bt_menu"));
        button3.setBackgroundDrawable(CallImage.imageDrawableCard("bt_menu"));
        button4.setBackgroundDrawable(CallImage.imageDrawableCard("bt_menu"));
        button5.setBackgroundDrawable(CallImage.imageDrawableCard("bt_menu"));
        textView.setText((CharSequence)MessageTopup.getMessage(7));
        textView.setTypeface(MessageTopup.setFont((Context)this, 0));
        button.setText((CharSequence)MessageTopup.getMessage(33));
        button.setTypeface(MessageTopup.setFont((Context)this, 0));
        button2.setText((CharSequence)MessageTopup.getMessage(34));
        button2.setTypeface(MessageTopup.setFont((Context)this, 0));
        button3.setText((CharSequence)MessageTopup.getMessage(35));
        button3.setTypeface(MessageTopup.setFont((Context)this, 0));
        button4.setText((CharSequence)MessageTopup.getMessage(89));
        button4.setTypeface(MessageTopup.setFont((Context)this, 0));
        button5.setText((CharSequence)MessageTopup.getMessage(36));
        button5.setTypeface(MessageTopup.setFont((Context)this, 0));
    }

    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903066);
        super.onCreate(bundle);
        this.findViewById(2131361863).setVisibility(8);
        ((FrameLayout)this.findViewById(2131361799)).setBackgroundDrawable(CallImage.imageDrawableCard("bg_orange"));
        ((Button)this.findViewById(2131361942)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("param1", "click01");
                bundle.putInt("video_id", 0);
                Intent intent = new Intent(view.getContext(), (Class)PlayVideo.class);
                intent.putExtras(bundle);
                SelectVideo.this.startActivityForResult(intent, 0);
            }
        });
        ((Button)this.findViewById(2131361943)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("param1", "click01");
                bundle.putInt("video_id", 1);
                Intent intent = new Intent(view.getContext(), (Class)PlayVideo.class);
                intent.putExtras(bundle);
                SelectVideo.this.startActivityForResult(intent, 0);
            }
        });
        ((Button)this.findViewById(2131361944)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("param1", "click01");
                bundle.putInt("video_id", 2);
                Intent intent = new Intent(view.getContext(), (Class)PlayVideo.class);
                intent.putExtras(bundle);
                SelectVideo.this.startActivityForResult(intent, 0);
            }
        });
        ((Button)this.findViewById(2131361945)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("param1", "click01");
                bundle.putInt("video_id", 3);
                Intent intent = new Intent(view.getContext(), (Class)PlayVideo.class);
                intent.putExtras(bundle);
                SelectVideo.this.startActivityForResult(intent, 0);
            }
        });
        ((Button)this.findViewById(2131361946)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("param1", "click01");
                bundle.putInt("video_id", 4);
                Intent intent = new Intent(view.getContext(), (Class)PlayVideo.class);
                intent.putExtras(bundle);
                SelectVideo.this.startActivityForResult(intent, 0);
            }
        });
        this.changeLanguage(switchID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d((String)"debug", (String)"onResume active");
    }

}

