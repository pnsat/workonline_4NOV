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
 *  android.content.res.Resources
 *  android.content.res.Resources$NotFoundException
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
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Float
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.Thread
 */
package OWS;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Resources;
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

public class InputCoinUtil
extends IORootActivity
implements IOService.ReceiveMoney {
    boolean ToLoading = false;
    boolean backButton = false;
    int bank = 0;
    boolean cancelInput;
    View.OnClickListener clickBack;
    View.OnClickListener clickCancel;
    int coin = 0;
    boolean endInput;
    LogController log;
    private int moneyInput;
    public ServiceConnection service;
    IOService.SettingService setting;
    TextView text_price0;
    TextView text_price1;
    TextView text_price2;

    public InputCoinUtil() {
        this.clickCancel = new View.OnClickListener(){

            public void onClick(View view) {
                view.setClickable(false);
                InputCoinUtil.this.cancelInput = true;
                InputCoinUtil.this.backButton = false;
                InputCoinUtil.this.setting.enableInput(false);
                new Thread(new Runnable(){

                    public void run() {
                        try {
                            if (1.this.InputCoinUtil.this.moneyInput == 0) {
                                Log.v((String)"hello", (String)("come if " + 1.this.InputCoinUtil.this.moneyInput));
                                Intent intent = new Intent(1.this.InputCoinUtil.this.getBaseContext(), (Class)MainPage.class);
                                intent.setFlags(67108864);
                                1.this.InputCoinUtil.this.startActivity(intent);
                                return;
                            }
                            AudioDemo.Sound().playSound("a9");
                            Thread.sleep((long)10000);
                            1.this.InputCoinUtil.this.finishInputMoney(false);
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
        this.clickBack = new View.OnClickListener(){

            public void onClick(View view) {
                view.setClickable(false);
                InputCoinUtil.this.cancelInput = true;
                InputCoinUtil.this.backButton = true;
                InputCoinUtil.this.setting.enableInput(false);
                new Thread(new Runnable(){

                    public void run() {
                        try {
                            if (2.this.InputCoinUtil.this.moneyInput == 0) {
                                Log.v((String)"hello", (String)("come if " + 2.this.InputCoinUtil.this.moneyInput));
                                2.this.InputCoinUtil.this.finish();
                                return;
                            }
                            AudioDemo.Sound().playSound("a9");
                            Thread.sleep((long)10000);
                            2.this.InputCoinUtil.this.finishInputMoney(true);
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
        this.service = new ServiceConnection(){

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                InputCoinUtil.this.setting = (IOService.SettingService)iBinder;
                InputCoinUtil.this.setting.serviceSetup(InputCoinUtil.this);
                InputCoinUtil.this.setting.setupReceiveMoney(InputCoinUtil.this);
                InputCoinUtil.this.setting.enableBox();
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
        Log.v((String)"hello", (String)("finish " + this.endInput));
        if (this.endInput) {
            if (bundle.getString("DataBarcode", "").length() > 0) {
                bundle.putInt("Service", 20);
            } else {
                bundle.putInt("Service", 12);
            }
            AudioDemo.Sound().playSound("a6");
        } else {
            bundle.putInt("Service", 13);
            if (bl) {
                this.finish();
            } else {
                Intent intent = new Intent(this.getBaseContext(), (Class)MainPage.class);
                intent.setFlags(67108864);
                this.startActivity(intent);
            }
        }
        if (!this.getSharedPreferences("hello", 0).getBoolean("inDebugMode", false)) {
            Intent intent = new Intent(this.getBaseContext(), (Class)Loading.class);
            intent.putExtras(bundle);
            this.startActivity(intent);
            return;
        }
        Intent intent = new Intent(this.getBaseContext(), (Class)MainPage.class);
        intent.setFlags(67108864);
        this.startActivity(intent);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void processMoney() throws Exception {
        Bundle bundle = this.getIntent().getExtras();
        float f = Float.parseFloat((String)bundle.getString("Price"));
        int n = bundle.getInt("OR");
        int n2 = bundle.getInt("MC");
        this.moneyInput = this.coin + this.bank;
        Object[] arrobject = new Object[]{Float.valueOf((float)f), Float.valueOf((float)f)};
        Log.v((String)"test", (String)String.format((String)"money %.02f %f", (Object[])arrobject));
        float f2 = f + (float)n - (float)n2;
        this.text_price1.setText((CharSequence)String.valueOf((int)this.moneyInput));
        TextView textView = this.text_price2;
        Object[] arrobject2 = new Object[]{Float.valueOf((float)(f2 - (float)this.moneyInput))};
        textView.setText((CharSequence)String.format((String)"%.02f", (Object[])arrobject2));
        if (f2 - (float)this.moneyInput < 0.0f) {
            this.text_price2.setText((CharSequence)"0.00");
        }
        StringBuilder stringBuilder = new StringBuilder("check ").append(this.moneyInput).append(" ").append(f2).append(" ");
        boolean bl = (float)this.moneyInput >= f2;
        Log.v((String)"hello", (String)stringBuilder.append(bl).toString());
        if ((float)this.moneyInput >= f2) {
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
    }

    /*
     * Enabled aggressive block sorting
     */
    public void changeLanguageCheck(int n) {
        Log.d((String)"debug", (String)("changeLanguage main " + n));
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
        editText.setText((CharSequence)bundle.getString("Mobile"));
        editText.setEnabled(false);
        String string2 = bundle.getString("Price");
        Float.parseFloat((String)string2);
        int n = bundle.getInt("OR");
        int n2 = bundle.getInt("MC");
        TextView textView = (TextView)this.findViewById(2131361796);
        Object[] arrobject = new Object[]{Float.valueOf((float)Float.parseFloat((String)string2))};
        textView.setText((CharSequence)String.format((String)"%7.2f", (Object[])arrobject));
        ((TextView)this.findViewById(2131361798)).setText((CharSequence)String.valueOf((int)n));
        ((TextView)this.findViewById(2131361808)).setText((CharSequence)String.valueOf((int)n2));
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
        Log.v((String)"hello", (String)("on create " + this.moneyInput));
        this.findViewById(2131361863).setVisibility(8);
        this.text_price0 = (TextView)this.findViewById(2131361891);
        this.text_price1 = (TextView)this.findViewById(2131361894);
        this.text_price2 = (TextView)this.findViewById(2131361897);
        EditText editText = (EditText)this.findViewById(2131361802);
        Bundle bundle2 = this.getIntent().getExtras();
        this.log = new LogController(bundle2, this.getSharedPreferences("hello", 0).getBoolean("inDebugMode", false));
        ((FrameLayout)this.findViewById(2131361799)).setBackgroundDrawable(CallImage.imageDrawableCard(bundle2.getString("img_background")));
        editText.setText((CharSequence)bundle2.getString("Mobile"));
        editText.setCursorVisible(false);
        float f = Float.parseFloat((String)bundle2.getString("Price"));
        int n = bundle2.getInt("OR");
        int n2 = bundle2.getInt("MC");
        float f2 = f - (float)n2 > 0.0f ? f + (float)n - (float)n2 : 0.0f;
        TextView textView = this.text_price0;
        Object[] arrobject = new Object[]{Float.valueOf((float)f2)};
        textView.setText((CharSequence)String.valueOf((Object)String.format((String)"%7.2f", (Object[])arrobject)));
        this.text_price1.setText((CharSequence)"0");
        TextView textView2 = this.text_price2;
        Object[] arrobject2 = new Object[]{Float.valueOf((float)f2)};
        textView2.setText((CharSequence)String.valueOf((Object)String.format((String)"%7.2f", (Object[])arrobject2)));
        new Handler().postDelayed(new Runnable(){

            public void run() {
                try {
                    Log.v((String)"hello", (String)"process money create");
                    InputCoinUtil.this.processMoney();
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
    public void onPause() {
        super.onPause();
        this.moneyInput = 0;
        this.coin = 0;
        this.bank = 0;
        if (this.setting != null) {
            this.setting.disableBox();
            this.unbindService(this.service);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (LogController.checkFileList() != 0) {
            Intent intent = new Intent(this.getBaseContext(), (Class)MainPage.class);
            intent.setFlags(67108864);
            this.startActivity(intent);
            return;
        }
        ImageView imageView = (ImageView)this.findViewById(2131361800);
        Bundle bundle = this.getIntent().getExtras();
        Resources resources = this.getResources();
        String string2 = "logo_service" + bundle.getString("Network");
        try {
            imageView.setImageDrawable(resources.getDrawable(resources.getIdentifier(string2, "drawable", this.getPackageName())));
        }
        catch (Resources.NotFoundException var7_6) {
            imageView.setVisibility(8);
            TextView textView = (TextView)this.findViewById(2131361888);
            textView.setText((CharSequence)bundle.getString("NetworkName", ""));
            textView.setVisibility(0);
        }
        if (this.landCancel != null) {
            this.landCancel.setOnClickListener(this.clickCancel);
        }
        if (this.landBack == null) return;
        {
            this.landBack.setOnClickListener(this.clickBack);
            return;
        }
    }

    @Override
    public void receiveBank(int n) {
        this.bank = n + this.bank;
        this.writeLog();
        SharedPreferences sharedPreferences = this.getSharedPreferences("hello", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!sharedPreferences.getBoolean("inDebugMode", false)) {
            editor.putInt("MoneyLog", n + sharedPreferences.getInt("MoneyLog", 0));
        }
        editor.commit();
        try {
            super.processMoney();
            return;
        }
        catch (Exception var5_4) {
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
        this.log.writerUtilityLog(bundle);
    }

}

