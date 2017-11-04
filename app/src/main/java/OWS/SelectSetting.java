/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.graphics.drawable.Drawable
 *  android.media.AudioManager
 *  android.os.Bundle
 *  android.provider.Settings
 *  android.provider.Settings$SettingNotFoundException
 *  android.provider.Settings$System
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.CheckBox
 *  android.widget.CompoundButton
 *  android.widget.CompoundButton$OnCheckedChangeListener
 *  android.widget.SeekBar
 *  android.widget.SeekBar$OnSeekBarChangeListener
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Process
 *  java.lang.Runtime
 *  java.lang.String
 *  java.lang.Throwable
 */
package OWS;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.InputPassword;
import com.daydr3am.OWS.Loading;
import com.daydr3am.OWS.MainPage;
import com.daydr3am.OWS.MessageTopup;
import com.daydr3am.OWS.Record;
import com.daydr3am.lib.CallImage;
import com.daydr3am.lib.LogController;

public class SelectSetting
extends IORootActivity {
    AudioManager audio;

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public void changeLanguage(int var1) {
        Log.d((String)"debug", (String)("changeLanguage main " + var1));
        var3_2 = (TextView)this.findViewById(2131361936);
        var4_3 = (Button)this.findViewById(2131361908);
        var5_4 = (Button)this.findViewById(2131361909);
        var6_5 = (Button)this.findViewById(2131361910);
        var7_6 = (Button)this.findViewById(2131361911);
        var8_7 = (Button)this.findViewById(2131361905);
        var9_8 = (Button)this.findViewById(2131361913);
        var10_9 = (Button)this.findViewById(2131361940);
        if (var1 != 1) ** GOTO lbl14
        try {
            var10_9.setBackgroundDrawable(CallImage.imageDrawableCard("bt_the_first_page_th"));
            ** GOTO lbl15
lbl14: // 1 sources:
            var10_9.setBackgroundDrawable(CallImage.imageDrawableCard("bt_the_first_page_en"));
lbl15: // 2 sources:
            var3_2.setText((CharSequence)MessageTopup.getMessage(48));
            var4_3.setText((CharSequence)"\u0e14\u0e39\u0e23\u0e32\u0e22\u0e25\u0e30\u0e40\u0e2d\u0e35\u0e22\u0e14\u0e15\u0e39\u0e49");
            var5_4.setText((CharSequence)"\u0e40\u0e1b\u0e34\u0e14\u0e15\u0e39\u0e49\u0e40\u0e15\u0e34\u0e21\u0e40\u0e07\u0e34\u0e19");
            var6_5.setText((CharSequence)"\u0e23\u0e32\u0e22\u0e07\u0e32\u0e19\u0e40\u0e15\u0e34\u0e21\u0e40\u0e07\u0e34\u0e19");
            var7_6.setText((CharSequence)"Clear \u0e22\u0e2d\u0e14\u0e40\u0e07\u0e34\u0e19");
            var8_7.setText((CharSequence)"set id system");
            var9_8.setText((CharSequence)"\u0e2d\u0e2d\u0e01\u0e08\u0e32\u0e01\u0e23\u0e30\u0e1a\u0e1a");
        }
        catch (Exception var11_10) {
            var11_10.printStackTrace();
        }
        if (this.landTextShow == null) return;
        this.landTextShow.setText((CharSequence)MessageTopup.getMessage(48));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903065);
        super.onCreate(bundle);
        this.getSharedPreferences("hello", 0);
        Button button = (Button)this.findViewById(2131361908);
        Button button2 = (Button)this.findViewById(2131361909);
        Button button3 = (Button)this.findViewById(2131361910);
        Button button4 = (Button)this.findViewById(2131361911);
        Button button5 = (Button)this.findViewById(2131361905);
        Button button6 = (Button)this.findViewById(2131361913);
        Button button7 = (Button)this.findViewById(2131361937);
        Button button8 = (Button)this.findViewById(2131361940);
        Button button9 = (Button)this.findViewById(2131361906);
        CheckBox checkBox = (CheckBox)this.findViewById(2131361935);
        SharedPreferences sharedPreferences = this.getSharedPreferences("hello", 0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        checkBox.setChecked(sharedPreferences.getBoolean("Sound", true));
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener(){

            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                editor.putBoolean("Sound", bl);
                editor.commit();
            }
        };
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        this.audio = (AudioManager)this.getSystemService("audio");
        Log.v((String)"hello", (String)("sound " + this.audio.getStreamVolume(1)));
        SeekBar seekBar = (SeekBar)this.findViewById(2131361938);
        seekBar.setMax(this.audio.getStreamMaxVolume(3));
        seekBar.setProgress(this.audio.getStreamVolume(3));
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener(){

            public void onProgressChanged(SeekBar seekBar, int n, boolean bl) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                SelectSetting.this.audio.setStreamVolume(3, seekBar.getProgress(), 4);
            }
        };
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        SeekBar seekBar2 = (SeekBar)this.findViewById(2131361939);
        seekBar2.setMax(255);
        try {
            int n = Settings.System.getInt((ContentResolver)this.getContentResolver(), (String)"screen_brightness");
            seekBar2.setProgress(n);
            Log.v((String)"hello", (String)("previous " + n));
        }
        catch (Settings.SettingNotFoundException var20_29) {
            var20_29.printStackTrace();
        }
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener2 = new SeekBar.OnSeekBarChangeListener(){

            public void onProgressChanged(SeekBar seekBar, int n, boolean bl) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Settings.System.putInt((ContentResolver)SelectSetting.this.getContentResolver(), (String)"screen_brightness", (int)seekBar.getProgress());
            }
        };
        seekBar2.setOnSeekBarChangeListener(onSeekBarChangeListener2);
        if (button != null) {
            View.OnClickListener onClickListener = new View.OnClickListener(){

                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), (Class)Loading.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("Service", 16);
                    intent.putExtras(bundle);
                    SelectSetting.this.startActivityForResult(intent, 0);
                }
            };
            button.setOnClickListener(onClickListener);
        }
        if (button2 != null) {
            View.OnClickListener onClickListener = new View.OnClickListener(){

                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), (Class)InputPassword.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("PassType", 2);
                    intent.putExtras(bundle);
                    SelectSetting.this.startActivityForResult(intent, 0);
                }
            };
            button2.setOnClickListener(onClickListener);
        }
        if (button3 != null) {
            View.OnClickListener onClickListener = new View.OnClickListener(){

                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), (Class)Record.class);
                    SelectSetting.this.startActivityForResult(intent, 0);
                }
            };
            button3.setOnClickListener(onClickListener);
        }
        if (button4 != null) {
            View.OnClickListener onClickListener = new View.OnClickListener(){

                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), (Class)InputPassword.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("PassType", 3);
                    intent.putExtras(bundle);
                    SelectSetting.this.startActivityForResult(intent, 0);
                }
            };
            button4.setOnClickListener(onClickListener);
        }
        if (button5 != null) {
            View.OnClickListener onClickListener = new View.OnClickListener(){

                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), (Class)InputPassword.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("PassType", 4);
                    intent.putExtras(bundle);
                    SelectSetting.this.startActivityForResult(intent, 0);
                }
            };
            button5.setOnClickListener(onClickListener);
        }
        if (button6 != null) {
            View.OnClickListener onClickListener = new View.OnClickListener(){

                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), (Class)InputPassword.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("PassType", 5);
                    intent.putExtras(bundle);
                    SelectSetting.this.startActivityForResult(intent, 0);
                }
            };
            button6.setOnClickListener(onClickListener);
        }
        if (button7 != null) {
            View.OnClickListener onClickListener = new View.OnClickListener(){

                public void onClick(View view) {
                    try {
                        Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot"}).waitFor();
                        return;
                    }
                    catch (Exception var2_2) {
                        Log.d((String)"debug", (String)"Could not reboot", (Throwable)var2_2);
                        return;
                    }
                }
            };
            button7.setOnClickListener(onClickListener);
        }
        if (button8 != null) {
            View.OnClickListener onClickListener = new View.OnClickListener(){

                public void onClick(View view) {
                    Log.v((String)"hello", (String)"button click 09");
                    Intent intent = new Intent(SelectSetting.this.getBaseContext(), (Class)MainPage.class);
                    intent.setFlags(67108864);
                    SelectSetting.this.startActivity(intent);
                }
            };
            button8.setOnClickListener(onClickListener);
        }
        if (button9 != null) {
            View.OnClickListener onClickListener = new View.OnClickListener(){

                public void onClick(View view) {
                    LogController.deleteLog();
                }
            };
            button9.setOnClickListener(onClickListener);
        }
        try {
            this.changeLanguage(switchID);
            return;
        }
        catch (Exception var31_30) {
            var31_30.printStackTrace();
            return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d((String)"debug", (String)"onResume active");
        this.changeLanguage(switchID);
    }

}

