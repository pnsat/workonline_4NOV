/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.ServiceConnection
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.graphics.Typeface
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.IBinder
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.TextView
 *  java.lang.Boolean
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.Thread
 */
package OWS;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.Loading;
import com.daydr3am.OWS.MainPage;
import com.daydr3am.OWS.MessageTopup;
import com.daydr3am.OWS.SampleActivity;
import com.daydr3am.lib.AudioDemo;
import com.daydr3am.lib.CallImage;
import com.daydr3am.lib.IOService;
import com.daydr3am.lib.IOServiceBV20;
import com.daydr3am.lib.LogController;

public class InputCoin
extends IORootActivity
implements IOService.ReceiveMoney {
    boolean ToLoading = false;
    boolean backButton = false;
    int bank = 0;
    boolean cancelInput;
    View.OnClickListener clickBack;
    View.OnClickListener clickCancel;
    int coin = 0;
    Boolean connectService = false;
    boolean endInput;
    LogController log;
    private int moneyInput;
    public ServiceConnection service;
    IOService.SettingService setting;
    TextView text_price0;
    TextView text_price1;
    TextView text_price2;

    public InputCoin() {
        this.clickBack = new View.OnClickListener(){

            public void onClick(View view) {
                view.setClickable(false);
                InputCoin.this.cancelInput = true;
                InputCoin.this.backButton = true;
                InputCoin.this.setting.enableInput(false);
                new Thread(new Runnable(){

                    public void run() {
                        try {
                            Log.v((String)"hello", (String)("chk cancel case " + 1.this.InputCoin.this.moneyInput));
                            if (1.this.InputCoin.this.moneyInput == 0) {
                                Log.v((String)"hello", (String)("come if " + 1.this.InputCoin.this.moneyInput));
                                1.this.InputCoin.this.finish();
                                return;
                            }
                            AudioDemo.Sound().playSound("a9");
                            Thread.sleep((long)10000);
                            1.this.InputCoin.this.finishInputMoney(true);
                            return;
                        }
                        catch (InterruptedException var1_1) {
                            var1_1.printStackTrace();
                            return;
                        }
                    }
                }).start();
            }

        };
        this.clickCancel = new View.OnClickListener(){

            public void onClick(View view) {
                view.setClickable(false);
                InputCoin.this.cancelInput = true;
                InputCoin.this.backButton = false;
                InputCoin.this.setting.enableInput(false);
                new Thread(new Runnable(){

                    public void run() {
                        try {
                            if (2.this.InputCoin.this.moneyInput == 0) {
                                Log.v((String)"hello", (String)("come if " + 2.this.InputCoin.this.moneyInput));
                                Intent intent = new Intent(2.this.InputCoin.this.getBaseContext(), (Class)MainPage.class);
                                intent.setFlags(67108864);
                                2.this.InputCoin.this.startActivity(intent);
                                return;
                            }
                            AudioDemo.Sound().playSound("a9");
                            Thread.sleep((long)10000);
                            2.this.InputCoin.this.finishInputMoney(false);
                            return;
                        }
                        catch (InterruptedException var1_2) {
                            var1_2.printStackTrace();
                            return;
                        }
                    }
                }).start();
            }

        };
        this.service = new ServiceConnection(){

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                InputCoin.this.setting = (IOService.SettingService)iBinder;
                InputCoin.this.setting.serviceSetup(InputCoin.this);
                InputCoin.this.setting.setupReceiveMoney(InputCoin.this);
                InputCoin.this.setting.enableBox();
            }

            public void onServiceDisconnected(ComponentName componentName) {
                Log.v((String)"hello", (String)"serviced");
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    private void finishInputMoney(boolean bl) {
        if (this.ToLoading) {
            return;
        }
        this.ToLoading = true;
        Log.w((String)"hello", (String)"finish");
        this.moneyInput = this.coin + this.bank;
        Bundle bundle = this.getIntent().getExtras();
        bundle.putString("TotalPrice", "" + this.moneyInput);
        bundle.putString("TotalCoin1", "" + this.coin);
        bundle.putString("TotalCoin2", "0");
        bundle.putString("TotalCoin3", "0");
        bundle.putString("TotalCoin4", "0");
        bundle.putString("TotalBank1", "" + this.bank);
        bundle.putString("TotalBank2", "0");
        bundle.putString("OperationRate", String.valueOf((int)bundle.getInt("OR")));
        if (this.endInput) {
            bundle.putInt("Service", 2);
            AudioDemo.Sound().playSound("a6");
        } else {
            Log.v((String)"hello", (String)"come finish else");
            bundle.putInt("Service", 4);
            if (bl) {
                this.finish();
            } else {
                Intent intent = new Intent(this.getBaseContext(), (Class)MainPage.class);
                intent.setFlags(67108864);
                this.startActivity(intent);
            }
        }
        if (!this.getSharedPreferences("hello", 0).getBoolean("inDebugMode", false)) {
            Log.w((String)"hello", (String)"to loading");
            Intent intent = new Intent(this.getBaseContext(), (Class)Loading.class);
            intent.putExtras(bundle);
            this.startActivity(intent);
            return;
        }
        Intent intent = new Intent(this.getBaseContext(), (Class)MainPage.class);
        intent.setFlags(67108864);
        this.startActivity(intent);
    }

    private void processMoney() throws Exception {
        Bundle bundle = this.getIntent().getExtras();
        int n = Integer.parseInt((String)bundle.getString("param3"));
        int n2 = bundle.getInt("OR");
        int n3 = bundle.getInt("MC");
        this.moneyInput = this.coin + this.bank;
        Log.v((String)"hello", (String)("process money before " + n + " " + n2 + " " + n3 + " " + this.moneyInput));
        int n4 = n + n2 - n3;
        this.text_price1.setText((CharSequence)String.valueOf((int)this.moneyInput));
        this.text_price2.setText((CharSequence)String.valueOf((int)(n4 - this.moneyInput)));
        if (n4 - this.moneyInput < 0) {
            this.text_price2.setText((CharSequence)"0");
        }
        if (this.moneyInput >= n4) {
            Log.v((String)"hello", (String)("process money pass " + this.moneyInput));
            this.endInput = true;
            this.setting.enableInput(false);
            this.finishInputMoney(true);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void backToMainMenu() {
        try {
            Thread.sleep((long)480000);
        }
        catch (InterruptedException var1_1) {
            var1_1.printStackTrace();
        }
        if (this.activityActive && !this.getClass().equals((Object)SampleActivity.class)) {
            AudioDemo.Sound().playSound("a9");
            try {
                Thread.sleep((long)10000);
            }
            catch (InterruptedException var2_2) {
                var2_2.printStackTrace();
            }
            this.finishInputMoney(false);
        }
    }

    @Override
    public void changeLanguage(int n) {
        Log.d((String)"debug", (String)("changeLanguage main " + n));
        TextView textView = (TextView)this.findViewById(2131361889);
        TextView textView2 = (TextView)this.findViewById(2131361890);
        TextView textView3 = (TextView)this.findViewById(2131361893);
        TextView textView4 = (TextView)this.findViewById(2131361896);
        TextView textView5 = (TextView)this.findViewById(2131361892);
        TextView textView6 = (TextView)this.findViewById(2131361895);
        TextView textView7 = (TextView)this.findViewById(2131361898);
        textView.setText((CharSequence)MessageTopup.getMessage(19));
        textView.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView2.setText((CharSequence)MessageTopup.getMessage(15));
        textView2.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView3.setText((CharSequence)MessageTopup.getMessage(20));
        textView3.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView4.setText((CharSequence)MessageTopup.getMessage(21));
        textView4.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView5.setText((CharSequence)MessageTopup.getMessage(16));
        textView5.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView6.setText((CharSequence)MessageTopup.getMessage(16));
        textView6.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView7.setText((CharSequence)MessageTopup.getMessage(16));
        textView7.setTypeface(MessageTopup.setFont((Context)this, 0));
        this.changeLanguageCheck(n);
        if (this.landTextShow != null) {
            this.landTextShow.setText((CharSequence)MessageTopup.getMessage(19));
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void changeLanguageCheck(int n) {
        TextView textView = (TextView)this.findViewById(2131361803);
        TextView textView2 = (TextView)this.findViewById(2131361805);
        TextView textView3 = (TextView)this.findViewById(2131361807);
        TextView textView4 = (TextView)this.findViewById(2131361804);
        TextView textView5 = (TextView)this.findViewById(2131361806);
        TextView textView6 = (TextView)this.findViewById(2131361809);
        textView.setText((CharSequence)MessageTopup.getMessage(12));
        textView.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView2.setText((CharSequence)MessageTopup.getMessage(13));
        textView2.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView3.setText((CharSequence)MessageTopup.getMessage(14));
        textView3.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView4.setText((CharSequence)MessageTopup.getMessage(16));
        textView4.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView5.setText((CharSequence)MessageTopup.getMessage(16));
        textView5.setTypeface(MessageTopup.setFont((Context)this, 0));
        textView6.setText((CharSequence)MessageTopup.getMessage(16));
        textView6.setTypeface(MessageTopup.setFont((Context)this, 0));
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

    public void createCheck() {
        Bundle bundle = this.getIntent().getExtras();
        EditText editText = (EditText)this.findViewById(2131361802);
        ((FrameLayout)this.findViewById(2131361799)).setBackgroundDrawable(CallImage.imageDrawableCard(bundle.getString("img_background")));
        editText.setText((CharSequence)bundle.getString("param4"));
        editText.setEnabled(false);
        int n = Integer.parseInt((String)bundle.getString("param3"));
        int n2 = bundle.getInt("OR");
        int n3 = bundle.getInt("MC");
        ((TextView)this.findViewById(2131361796)).setText((CharSequence)String.valueOf((int)n));
        ((TextView)this.findViewById(2131361798)).setText((CharSequence)String.valueOf((int)n2));
        ((TextView)this.findViewById(2131361808)).setText((CharSequence)String.valueOf((int)n3));
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903052);
        super.onCreate(bundle);
        if (LogController.checkFileList() != 0) {
            return;
        }
        if (MainPage.isBV20) {
            this.bindService(new Intent((Context)this, (Class)IOServiceBV20.class), this.service, 1);
        } else {
            this.bindService(new Intent((Context)this, (Class)IOService.class), this.service, 1);
        }
        this.connectService = true;
        Log.v((String)"hello", (String)("on create " + this.moneyInput));
        this.findViewById(2131361863).setVisibility(8);
        this.text_price0 = (TextView)this.findViewById(2131361891);
        this.text_price1 = (TextView)this.findViewById(2131361894);
        this.text_price2 = (TextView)this.findViewById(2131361897);
        EditText editText = (EditText)this.findViewById(2131361802);
        Bundle bundle2 = this.getIntent().getExtras();
        this.log = new LogController(bundle2, this.getSharedPreferences("hello", 0).getBoolean("inDebugMode", false));
        ((FrameLayout)this.findViewById(2131361799)).setBackgroundDrawable(CallImage.imageDrawableCard(bundle2.getString("img_background")));
        editText.setText((CharSequence)bundle2.getString("param4"));
        editText.setCursorVisible(false);
        int n = Integer.parseInt((String)bundle2.getString("param3"));
        int n2 = bundle2.getInt("OR");
        int n3 = bundle2.getInt("MC");
        int n4 = n + n2 - n3 > 0 ? n + n2 - n3 : 0;
        this.text_price0.setText((CharSequence)String.valueOf((int)n4));
        this.text_price1.setText((CharSequence)"0");
        this.text_price2.setText((CharSequence)String.valueOf((int)n4));
        Button button = (Button)this.findViewById(2131361899);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    InputCoin.this.receiveCoin(5);
                }
            });
        }
        new Handler().postDelayed(new Runnable(){

            public void run() {
                try {
                    Log.v((String)"hello", (String)"process money create");
                    InputCoin.this.processMoney();
                    return;
                }
                catch (Exception var1_1) {
                    var1_1.printStackTrace();
                    return;
                }
            }
        }, 3000);
        this.back.setOnClickListener(this.clickBack);
        this.cancel.setOnClickListener(this.clickCancel);
        AudioDemo.Sound().playSound("a5");
        this.createCheck();
        this.changeLanguage(switchID);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.connectService.booleanValue()) {
            this.unbindService(this.service);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        this.moneyInput = 0;
        this.coin = 0;
        this.bank = 0;
        if (this.setting != null) {
            this.setting.disableBox();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (LogController.checkFileList() != 0) {
            Intent intent = new Intent(this.getBaseContext(), (Class)MainPage.class);
            intent.setFlags(67108864);
            this.startActivity(intent);
            return;
        } else {
            Log.v((String)"hello", (String)("on resume " + this.moneyInput));
            ImageView imageView = (ImageView)this.findViewById(2131361800);
            String[][] arrarrstring = new String[][]{{"logo_one2call", "logo_happy", "logo_true", "logo_hutch", "logo_cat", "logo_true_h"}, {"logo_true", "logo_cash", "logo_dserial", "logo_cookiecard", "logo_winner", "logo_tot", "logo_play", "logo_gcash"}, {"logo_tolld", "logo_tookdee"}};
            Bundle bundle = this.getIntent().getExtras();
            String string2 = bundle.getString("param1");
            String string3 = bundle.getString("param2");
            int n = -1 + Integer.parseInt((String)String.valueOf((char)string2.charAt(6)));
            int n2 = Integer.parseInt((String)string3);
            Log.v((String)"test", (String)("index " + n + " " + n2));
            Log.v((String)"test", (String)("picture " + arrarrstring[n][n2]));
            Log.d((String)"debug", (String)("logo " + (Object)this.findViewById(2131361820)));
            imageView.setImageDrawable(CallImage.imageDrawableCard(arrarrstring[n][n2]));
            if (this.landCancel != null) {
                this.landCancel.setOnClickListener(this.clickCancel);
            }
            if (this.landBack == null) return;
            {
                this.landBack.setOnClickListener(this.clickBack);
                return;
            }
        }
    }

    @Override
    public void receiveBank(int n) {
        this.bank = n + this.bank;
        this.writeLog();
        try {
            Log.v((String)"hello", (String)("process money receive bank" + n));
            SharedPreferences sharedPreferences = this.getSharedPreferences("hello", 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (!sharedPreferences.getBoolean("inDebugMode", false)) {
                editor.putInt("MoneyLog", n + sharedPreferences.getInt("MoneyLog", 0));
            }
            editor.commit();
            super.processMoney();
            return;
        }
        catch (Exception var2_4) {
            return;
        }
    }

    @Override
    public void receiveCoin(int n) {
        this.coin = n + this.coin;
        this.writeLog();
        SharedPreferences sharedPreferences = this.getSharedPreferences("hello", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!sharedPreferences.getBoolean("inDebugMode", false)) {
            editor.putInt("MoneyLog", n + sharedPreferences.getInt("MoneyLog", 0));
        }
        editor.commit();
        try {
            Log.v((String)"hello", (String)("process money receive coin" + n));
            super.processMoney();
            return;
        }
        catch (Exception var5_4) {
            return;
        }
    }

    @Override
    public void receiveText(String string2) {
    }

    public void writeLog() {
        int n = this.coin + this.bank;
        Bundle bundle = this.getIntent().getExtras();
        bundle.putString("TotalPrice", "" + n);
        bundle.putString("TotalCoin1", "" + this.coin);
        bundle.putString("TotalCoin2", "0");
        bundle.putString("TotalCoin3", "0");
        bundle.putString("TotalCoin4", "0");
        bundle.putString("TotalBank1", "" + this.bank);
        bundle.putString("TotalBank2", "0");
        bundle.putString("OperationRate", String.valueOf((int)bundle.getInt("OR")));
        this.log.writerLog(bundle);
    }

}

