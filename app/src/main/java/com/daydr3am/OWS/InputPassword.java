/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
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
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package com.daydr3am.OWS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.Loading;
import com.daydr3am.OWS.MainPage;
import com.daydr3am.OWS.MessageTopup;
import com.daydr3am.lib.CallImage;

public class InputPassword
extends IORootActivity {
    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void changeLanguage(int n) {
        int n2;
        Log.d((String)"debug", (String)("changeLanguage main " + n));
        TextView textView = (TextView)this.findViewById(2131361900);
        Button button = (Button)this.findViewById(2131361840);
        if (n == 1) {
            button.setBackgroundDrawable(CallImage.imageDrawableCard("bt_login_th"));
        } else {
            button.setBackgroundDrawable(CallImage.imageDrawableCard("bt_login_en"));
        }
        if ((n2 = this.getIntent().getExtras().getInt("PassType")) == 10) {
            n2 = 5;
        }
        textView.setText((CharSequence)(String.valueOf((Object)MessageTopup.getMessage(45)) + n2));
        textView.setTypeface(MessageTopup.setFont((Context)this, 0));
    }

    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903053);
        super.onCreate(bundle);
        ((EditText)this.findViewById(2131361802)).setEnabled(false);
        ViewGroup viewGroup = (ViewGroup)this.findViewById(2131361824);
        int n = viewGroup.getChildCount();
        int n2 = 0;
        block0 : do {
            if (n2 >= n) {
                ((Button)this.findViewById(2131361840)).setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        InputPassword.this.finish();
                        EditText editText = (EditText)InputPassword.this.findViewById(2131361802);
                        Bundle bundle = ((Activity)view.getContext()).getIntent().getExtras();
                        bundle.putInt("Service", 5);
                        if (bundle.getInt("PassType") == 3) {
                            bundle.putInt("Service", 6);
                        }
                        bundle.putString("Password", editText.getText().toString());
                        Intent intent = new Intent(view.getContext(), (Class)Loading.class);
                        intent.putExtras(bundle);
                        InputPassword.this.startActivityForResult(intent, 0);
                    }
                });
                ((Button)this.findViewById(2131361901)).setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        Bundle bundle = ((Activity)view.getContext()).getIntent().getExtras();
                        if (bundle.getInt("PassType") == 1) {
                            Intent intent = new Intent(view.getContext(), (Class)MainPage.class);
                            intent.putExtras(bundle);
                            InputPassword.this.startActivityForResult(intent, 0);
                            return;
                        }
                        InputPassword.this.finish();
                    }
                });
                this.changeLanguage(switchID);
                return;
            }
            ViewGroup viewGroup2 = (ViewGroup)viewGroup.getChildAt(n2);
            int n3 = viewGroup2.getChildCount();
            int n4 = 0;
            do {
                if (n4 >= n3) {
                    ++n2;
                    continue block0;
                }
                ((Button)viewGroup2.getChildAt(n4)).setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        EditText editText = (EditText)InputPassword.this.findViewById(2131361802);
                        String string2 = ((Button)view).getHint().toString();
                        if (string2.equals((Object)"delete")) {
                            if (editText.getText().length() >= 1) {
                                editText.setText(editText.getText().toString().toCharArray(), 0, -1 + editText.getText().length());
                            }
                            return;
                        }
                        if (string2.equals((Object)"star")) {
                            editText.setText((CharSequence)(String.valueOf((Object)editText.getText().toString()) + "*"));
                            return;
                        }
                        editText.setText((CharSequence)(String.valueOf((Object)editText.getText().toString()) + string2));
                    }
                });
                ++n4;
            } while (true);
            break;
        } while (true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d((String)"debug", (String)"onResume active");
        this.changeLanguage(switchID);
    }

}

