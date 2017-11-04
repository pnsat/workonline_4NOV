/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
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
 *  android.widget.FrameLayout
 *  android.widget.RadioButton
 *  android.widget.RadioGroup
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package OWS;

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
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.Loading;
import com.daydr3am.OWS.MessageTopup;
import com.daydr3am.lib.AudioDemo;
import com.daydr3am.lib.CallImage;

public class ReportPage
extends IORootActivity {
    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void changeLanguage(int n) {
        Button button;
        Log.d((String)"debug", (String)("changeLanguage main " + n));
        Log.d((String)"debug", (String)("yai main " + n));
        RadioButton radioButton = (RadioButton)this.findViewById(2131361929);
        RadioButton radioButton2 = (RadioButton)this.findViewById(2131361930);
        RadioButton radioButton3 = (RadioButton)this.findViewById(2131361931);
        RadioButton radioButton4 = (RadioButton)this.findViewById(2131361932);
        TextView textView = (TextView)this.findViewById(2131361926);
        TextView textView2 = (TextView)this.findViewById(2131361927);
        textView.setText((CharSequence)MessageTopup.getMessage(8));
        textView.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView2.setText((CharSequence)MessageTopup.getMessage(37));
        textView2.setTypeface(MessageTopup.setFont((Context)this, 0));
        radioButton.setText((CharSequence)MessageTopup.getMessage(38));
        radioButton.setTypeface(MessageTopup.setFont((Context)this, 0));
        radioButton2.setText((CharSequence)MessageTopup.getMessage(39));
        radioButton2.setTypeface(MessageTopup.setFont((Context)this, 0));
        radioButton3.setText((CharSequence)MessageTopup.getMessage(40));
        radioButton3.setTypeface(MessageTopup.setFont((Context)this, 0));
        radioButton4.setText((CharSequence)MessageTopup.getMessage(41));
        radioButton4.setTypeface(MessageTopup.setFont((Context)this, 0));
        String string2 = "";
        if (n == 1) {
            string2 = String.valueOf((Object)string2) + "_th";
        } else if (n == 2) {
            string2 = String.valueOf((Object)string2) + "_en";
        }
        if (this.next != null) {
            this.next.setBackgroundDrawable(CallImage.imageDrawableCard("bt_inform" + string2));
        }
        if ((button = (Button)this.findViewById(2131361839)) != null) {
            button.setText((CharSequence)MessageTopup.getMessage(80));
        }
        if (this.landTextShow != null) {
            this.landTextShow.setText((CharSequence)MessageTopup.getMessage(37));
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903063);
        super.onCreate(bundle);
        ((FrameLayout)this.findViewById(2131361799)).setBackgroundDrawable(CallImage.imageDrawableCard("bg_orange"));
        ((EditText)this.findViewById(2131361802)).setEnabled(false);
        ViewGroup viewGroup = (ViewGroup)this.findViewById(2131361824);
        int n = viewGroup.getChildCount();
        int n2 = 0;
        block0 : do {
            if (n2 >= n) {
                ((Button)this.findViewById(2131361863)).setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        EditText editText = (EditText)ReportPage.this.findViewById(2131361802);
                        Bundle bundle = new Bundle();
                        RadioGroup radioGroup = (RadioGroup)ReportPage.this.findViewById(2131361928);
                        int n = radioGroup.indexOfChild(radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()));
                        String[] arrstring = new String[]{"topup not success", "wrong number", "machine problem", "comment"};
                        bundle.putString("Mobile", editText.getText().toString());
                        bundle.putString("Word", arrstring[n]);
                        bundle.putInt("Service", 10);
                        Intent intent = new Intent(view.getContext(), (Class)Loading.class);
                        intent.putExtras(bundle);
                        view.getContext().startActivity(intent);
                    }
                });
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

                    /*
                     * Enabled aggressive block sorting
                     */
                    public void onClick(View view) {
                        EditText editText = (EditText)ReportPage.this.findViewById(2131361802);
                        String string2 = ((Button)view).getHint().toString();
                        if (string2.equals((Object)"delete")) {
                            if (editText.getText().length() < 1) return;
                            {
                                editText.setText(editText.getText().toString().toCharArray(), 0, -1 + editText.getText().length());
                                return;
                            }
                        } else {
                            if (string2.equals((Object)"star") && editText.getText().length() < 10) {
                                editText.setText((CharSequence)(String.valueOf((Object)editText.getText().toString()) + "*"));
                                return;
                            }
                            if (editText.getText().length() >= 10) return;
                            {
                                editText.setText((CharSequence)(String.valueOf((Object)editText.getText().toString()) + string2));
                                return;
                            }
                        }
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
        this.changeLanguage(switchID);
        Log.d((String)"debug", (String)"onResume active");
        AudioDemo.Sound().playSound("d1");
    }

}

