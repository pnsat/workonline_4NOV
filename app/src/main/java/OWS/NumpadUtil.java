/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.res.Resources
 *  android.content.res.Resources$NotFoundException
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
 *  java.lang.Object
 *  java.lang.String
 */
package OWS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
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
import com.daydr3am.OWS.DateUtility;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.Loading;
import com.daydr3am.OWS.MessageTopup;
import com.daydr3am.lib.AudioDemo;
import com.daydr3am.lib.CallImage;

public class NumpadUtil
extends IORootActivity {
    String[] imageBG = new String[]{"bg_blue", "bg_pink", "bg_green", "bg_orange"};
    int index_background;
    View.OnClickListener nextAction;
    View.OnClickListener numpadClick;
    final int[] value = new int[]{29, 30, 32, 31, 34, 28, 77, 21, 22, 24};
    final String[] wordFormat = new String[]{"\u0e22\u0e2d\u0e14\u0e0a\u0e33\u0e23\u0e30", "\u0e40\u0e1a\u0e2d\u0e23\u0e4c\u0e21\u0e37\u0e2d\u0e16\u0e37\u0e2d\u0e40\u0e1e\u0e37\u0e48\u0e2d\u0e23\u0e31\u0e1a\u0e1c\u0e25\u0e01\u0e32\u0e23\u0e0a\u0e33\u0e23\u0e30", "\u0e01\u0e23\u0e38\u0e13\u0e32\u0e01\u0e23\u0e2d\u0e01 OTP \u0e08\u0e32\u0e01\u0e04\u0e23\u0e31\u0e49\u0e07\u0e01\u0e48\u0e2d\u0e19 \u0e2b\u0e32\u0e01\u0e44\u0e21\u0e48\u0e21\u0e35\u0e43\u0e2a\u0e48 0"};
    final String[][] wordinput = new String[][]{{"\u0e40\u0e25\u0e02\u0e17\u0e35\u0e48\u0e2a\u0e31\u0e0d\u0e0d\u0e32", "\u0e40\u0e25\u0e02\u0e17\u0e35\u0e48\u0e43\u0e1a\u0e41\u0e08\u0e49\u0e07\u0e2b\u0e19\u0e35\u0e49"}, {"\u0e2b\u0e21\u0e32\u0e22\u0e40\u0e25\u0e02\u0e1c\u0e39\u0e49\u0e43\u0e0a\u0e49\u0e44\u0e1f\u0e1f\u0e49\u0e32", "\u0e23\u0e2b\u0e31\u0e2a\u0e01\u0e32\u0e23\u0e44\u0e1f\u0e1f\u0e49\u0e32"}, {"\u0e40\u0e25\u0e02\u0e17\u0e35\u0e48\u0e43\u0e1a\u0e41\u0e08\u0e49\u0e07\u0e2b\u0e19\u0e35\u0e49", "\u0e40\u0e25\u0e02\u0e17\u0e35\u0e48\u0e1c\u0e39\u0e49\u0e43\u0e0a\u0e49\u0e19\u0e49\u0e33", "\u0e40\u0e25\u0e02\u0e17\u0e35\u0e48\u0e2b\u0e19\u0e48\u0e27\u0e22\u0e07\u0e32\u0e19"}, {"\u0e2b\u0e21\u0e32\u0e22\u0e40\u0e25\u0e02\u0e2a\u0e32\u0e02\u0e32", "\u0e2b\u0e21\u0e32\u0e22\u0e40\u0e25\u0e02\u0e40\u0e02\u0e15", "\u0e40\u0e25\u0e02\u0e17\u0e30\u0e40\u0e1a\u0e35\u0e22\u0e19\u0e1c\u0e39\u0e49\u0e43\u0e0a\u0e49\u0e19\u0e49\u0e33", "\u0e40\u0e25\u0e02\u0e17\u0e35\u0e48\u0e43\u0e1a\u0e41\u0e08\u0e49\u0e07\u0e2b\u0e19\u0e35\u0e49"}, {"\u0e2b\u0e21\u0e32\u0e22\u0e40\u0e25\u0e02\u0e1a\u0e23\u0e34\u0e01\u0e32\u0e23", "Account no 14 \u0e2b\u0e25\u0e31\u0e01", "Invoice No 14 \u0e2b\u0e25\u0e31\u0e01"}, {"\u0e01\u0e23\u0e38\u0e13\u0e32\u0e23\u0e30\u0e1a\u0e38\u0e40\u0e1a\u0e2d\u0e23\u0e4c\u0e42\u0e17\u0e23\u0e28\u0e31\u0e1e\u0e17\u0e4c\u0e17\u0e35\u0e48\u0e15\u0e49\u0e2d\u0e07\u0e01\u0e32\u0e23\u0e0a\u0e33\u0e23\u0e30"}, {"\u0e01\u0e23\u0e38\u0e13\u0e32\u0e23\u0e30\u0e1a\u0e38\u0e40\u0e1a\u0e2d\u0e23\u0e4c\u0e42\u0e17\u0e23\u0e28\u0e31\u0e1e\u0e17\u0e4c\u0e17\u0e35\u0e48\u0e15\u0e49\u0e2d\u0e07\u0e01\u0e32\u0e23\u0e0a\u0e33\u0e23\u0e30"}, {"\u0e01\u0e23\u0e38\u0e13\u0e32\u0e23\u0e30\u0e1a\u0e38\u0e40\u0e1a\u0e2d\u0e23\u0e4c\u0e42\u0e17\u0e23\u0e28\u0e31\u0e1e\u0e17\u0e4c\u0e17\u0e35\u0e48\u0e15\u0e49\u0e2d\u0e07\u0e01\u0e32\u0e23\u0e0a\u0e33\u0e23\u0e30"}, {"\u0e01\u0e23\u0e38\u0e13\u0e32\u0e23\u0e30\u0e1a\u0e38\u0e40\u0e1a\u0e2d\u0e23\u0e4c\u0e42\u0e17\u0e23\u0e28\u0e31\u0e1e\u0e17\u0e4c\u0e17\u0e35\u0e48\u0e15\u0e49\u0e2d\u0e07\u0e01\u0e32\u0e23\u0e0a\u0e33\u0e23\u0e30"}, {"\u0e01\u0e23\u0e38\u0e13\u0e32\u0e23\u0e30\u0e1a\u0e38\u0e2b\u0e21\u0e32\u0e22\u0e40\u0e25\u0e02\u0e1a\u0e23\u0e34\u0e01\u0e32\u0e23"}, {"\u0e40\u0e25\u0e02\u0e17\u0e35\u0e48\u0e2a\u0e31\u0e0d\u0e0d\u0e32,\u0e1a\u0e31\u0e15\u0e23\u0e40\u0e04\u0e23\u0e14\u0e34\u0e15"}, {"\u0e2b\u0e21\u0e32\u0e22\u0e40\u0e25\u0e02\u0e2a\u0e21\u0e32\u0e0a\u0e34\u0e01/\u0e2b\u0e21\u0e32\u0e22\u0e40\u0e25\u0e02\u0e1a\u0e31\u0e0d\u0e0a\u0e35"}, {"\u0e2b\u0e21\u0e32\u0e22\u0e40\u0e25\u0e02\u0e1a\u0e31\u0e15\u0e23\u0e40\u0e04\u0e23\u0e14\u0e34\u0e15"}, {"\u0e2b\u0e21\u0e32\u0e22\u0e40\u0e25\u0e02\u0e2a\u0e21\u0e32\u0e0a\u0e34\u0e01 (Account No.)", "\u0e40\u0e25\u0e02\u0e17\u0e35\u0e48 (Invoice No.)"}, {"\u0e23\u0e2b\u0e31\u0e2a\u0e1c\u0e39\u0e49\u0e08\u0e33\u0e2b\u0e19\u0e48\u0e32\u0e22", "\u0e40\u0e25\u0e02\u0e17\u0e35\u0e48\u0e43\u0e1a\u0e2a\u0e48\u0e07\u0e02\u0e2d\u0e07"}, {"\u0e2b\u0e21\u0e32\u0e22\u0e40\u0e25\u0e02\u0e2d\u0e49\u0e32\u0e07\u0e2d\u0e34\u0e07"}, new String[0]};

    public NumpadUtil() {
        this.nextAction = new View.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View view) {
                Activity activity = (Activity)((Button)view).getContext();
                EditText editText = (EditText)NumpadUtil.this.findViewById(2131361802);
                Log.v((String)"hello", (String)("length " + editText.getText().toString().length()));
                Bundle bundle = activity.getIntent().getExtras();
                int n = bundle.getInt("CurrentData");
                if (editText.getText().toString().length() == 0 && n != 6) {
                    Intent intent = new Intent(view.getContext(), (Class)Loading.class);
                    bundle.putString("ShowError", "\u0e01\u0e23\u0e38\u0e13\u0e32\u0e01\u0e23\u0e2d\u0e01\u0e02\u0e49\u0e2d\u0e21\u0e39\u0e25\u0e01\u0e48\u0e2d\u0e19\u0e44\u0e1b\u0e2b\u0e19\u0e49\u0e32\u0e16\u0e31\u0e14\u0e44\u0e1b");
                    intent.putExtras(bundle);
                    NumpadUtil.this.startActivityForResult(intent, 0);
                    return;
                }
                Log.v((String)"hello", (String)("save data " + n));
                if (n == 0) {
                    bundle.putString("Data1", editText.getText().toString());
                } else if (n == 1) {
                    bundle.putString("Data2", editText.getText().toString());
                } else if (n == 2) {
                    bundle.putString("Data3", editText.getText().toString());
                } else if (n == 3) {
                    bundle.putString("Data4", editText.getText().toString());
                } else if (n == 4) {
                    bundle.putString("Price", editText.getText().toString());
                } else if (n == 5) {
                    if (editText.getText().toString().length() != 10) {
                        bundle.putString("img_background", NumpadUtil.this.imageBG[NumpadUtil.this.index_background]);
                        bundle.putString("ShowError", MessageTopup.getMessage(68));
                        Intent intent = new Intent(view.getContext(), (Class)Loading.class);
                        intent.putExtras(bundle);
                        NumpadUtil.this.startActivityForResult(intent, 0);
                        return;
                    }
                    bundle.putString("Mobile", editText.getText().toString());
                } else if (n == 6) {
                    if (editText.getText().toString().length() == 0) {
                        bundle.putString("OTP", "0");
                    } else {
                        bundle.putString("OTP", editText.getText().toString());
                    }
                }
                bundle.putInt("CurrentData", n + 1);
                if (bundle.getString("DataBarcode", "").length() > 0) {
                    bundle.putInt("Service", 19);
                } else {
                    bundle.putInt("Service", 11);
                }
                int n2 = n + 1;
                bundle.putString("img_background", NumpadUtil.this.imageBG[NumpadUtil.this.index_background]);
                Log.v((String)"hello", (String)("cur " + n2 + " max " + bundle.getInt("DataMax")));
                if (n2 == NumpadUtil.this.wordinput[bundle.getInt("Index")].length) {
                    bundle.putInt("CurrentData", 4);
                    if (bundle.getInt("Index") > 4 && bundle.getInt("Index") != 15) {
                        Log.v((String)"hello", (String)("current " + bundle.getInt("CurrentData")));
                        Intent intent = new Intent(view.getContext(), (Class)NumpadUtil.class);
                        intent.putExtras(bundle);
                        NumpadUtil.this.startActivityForResult(intent, 0);
                        return;
                    }
                    Intent intent = new Intent(view.getContext(), (Class)DateUtility.class);
                    intent.putExtras(bundle);
                    NumpadUtil.this.startActivityForResult(intent, 0);
                    return;
                }
                if (n2 == 7) {
                    Intent intent = new Intent(view.getContext(), (Class)Loading.class);
                    intent.putExtras(bundle);
                    NumpadUtil.this.startActivityForResult(intent, 0);
                    return;
                }
                Intent intent = new Intent(view.getContext(), (Class)NumpadUtil.class);
                intent.putExtras(bundle);
                NumpadUtil.this.startActivityForResult(intent, 0);
            }
        };
        this.numpadClick = new View.OnClickListener(){

            public void onClick(View view) {
                EditText editText = (EditText)NumpadUtil.this.findViewById(2131361802);
                Log.v((String)"hello", (String)("length " + editText.getText().length()));
                String string2 = ((Button)view).getHint().toString();
                if (string2.equals((Object)"delete")) {
                    if (editText.getText().length() >= 1) {
                        editText.setText(editText.getText().toString().toCharArray(), 0, -1 + editText.getText().length());
                        if (NumpadUtil.this.getSharedPreferences("hello", 0).getBoolean("Sound", true)) {
                            AudioDemo.Sound().playSound("del");
                        }
                    }
                    return;
                }
                if (string2.equals((Object)"star")) {
                    editText.setText((CharSequence)(String.valueOf((Object)editText.getText().toString()) + "."));
                    return;
                }
                if (NumpadUtil.this.getSharedPreferences("hello", 0).getBoolean("Sound", true)) {
                    AudioDemo.Sound().playSound("z" + string2);
                }
                editText.setText((CharSequence)(String.valueOf((Object)editText.getText().toString()) + string2));
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void changeLanguage(int n) {
        TextView textView = (TextView)this.findViewById(2131361821);
        Bundle bundle = this.getIntent().getExtras();
        if (bundle.getInt("CurrentData") < this.wordinput[bundle.getInt("Index")].length) {
            textView.setText((CharSequence)this.wordinput[bundle.getInt("Index")][bundle.getInt("CurrentData")]);
            if (this.landTextShow != null) {
                this.landTextShow.setText((CharSequence)this.wordinput[bundle.getInt("Index")][bundle.getInt("CurrentData")]);
            }
        } else {
            int n2 = -4 + bundle.getInt("CurrentData");
            textView.setText((CharSequence)this.wordFormat[n2]);
            if (this.landTextShow != null) {
                this.landTextShow.setText((CharSequence)this.wordFormat[n2]);
            }
        }
        textView.setTypeface(MessageTopup.setFont((Context)this, 0));
        Button button = (Button)this.findViewById(2131361839);
        if (button != null) {
            button.setText((CharSequence)MessageTopup.getMessage(80));
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903058);
        super.onCreate(bundle);
        ((EditText)this.findViewById(2131361802)).setEnabled(false);
        Log.d((String)"debug", (String)("MessageTopup >> " + MessageTopup.getMessage(3)));
        Log.d((String)"debug", (String)("logo " + (Object)this.findViewById(2131361820)));
        ImageView imageView = (ImageView)this.findViewById(2131361820);
        ((FrameLayout)this.findViewById(2131361799)).setBackgroundDrawable(CallImage.imageDrawableCard(this.imageBG[3]));
        this.index_background = 3;
        ViewGroup viewGroup = (ViewGroup)this.findViewById(2131361824);
        int n = viewGroup.getChildCount();
        int n2 = 0;
        block2 : do {
            if (n2 >= n) {
                this.next.setOnClickListener(this.nextAction);
                Bundle bundle2 = this.getIntent().getExtras();
                Resources resources = this.getResources();
                String string2 = "logo_service" + bundle2.getString("Network");
                try {
                    imageView.setImageDrawable(resources.getDrawable(resources.getIdentifier(string2, "drawable", this.getPackageName())));
                    return;
                }
                catch (Resources.NotFoundException notFoundException) {
                    imageView.setVisibility(8);
                    TextView textView = (TextView)this.findViewById(2131361888);
                    textView.setText((CharSequence)bundle2.getString("NetworkName", ""));
                    textView.setVisibility(0);
                    return;
                }
            }
            ViewGroup viewGroup2 = (ViewGroup)viewGroup.getChildAt(n2);
            int n3 = viewGroup2.getChildCount();
            int n4 = 0;
            do {
                if (n4 >= n3) {
                    ++n2;
                    continue block2;
                }
                ((Button)viewGroup2.getChildAt(n4)).setOnClickListener(this.numpadClick);
                ++n4;
            } while (true);
            break;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d((String)"debug", (String)"onResume active");
        Bundle bundle = this.getIntent().getExtras();
        int n = bundle.getInt("Index");
        int n2 = bundle.getInt("CurrentData");
        if (n <= 9) {
            Log.v((String)"hello", (String)("play name e" + n + n2));
            if (n2 < this.wordinput[n].length) {
                AudioDemo.Sound().playSound("e" + n + n2);
            } else {
                AudioDemo.Sound().playSound("e0" + n2);
            }
        } else if (n2 < this.wordinput[n].length) {
            int n3 = n - 10;
            Log.v((String)"hello", (String)("play name f" + n3 + n2));
            AudioDemo.Sound().playSound("f" + n3 + n2);
        } else {
            Log.v((String)"hello", (String)("play name f0" + n2));
            AudioDemo.Sound().playSound("f0" + n2);
        }
        EditText editText = (EditText)this.findViewById(2131361802);
        if (bundle.getInt("Index") == 1 && bundle.getInt("CurrentData") == 3) {
            editText.setEnabled(true);
            editText.requestFocus();
            this.findViewById(2131361824).setVisibility(4);
        }
        if (this.findViewById(2131361841) != null) {
            this.findViewById(2131361841).setOnClickListener(this.nextAction);
        }
    }

}

