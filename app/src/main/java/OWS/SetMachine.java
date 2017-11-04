/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.database.Cursor
 *  android.graphics.Typeface
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.os.Bundle
 *  android.provider.ContactsContract
 *  android.provider.ContactsContract$PhoneLookup
 *  android.telephony.TelephonyManager
 *  android.text.Editable
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.CheckBox
 *  android.widget.CompoundButton
 *  android.widget.CompoundButton$OnCheckedChangeListener
 *  android.widget.EditText
 *  android.widget.TextView
 *  java.io.File
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Field
 *  java.util.ArrayList
 */
package OWS;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.MessageTopup;
import com.daydr3am.lib.CallImage;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class SetMachine
extends IORootActivity {
    private String ID_msg;
    private ArrayList<Bundle> array_sort_msgt = new ArrayList();
    private Bundle bundledata_sort_msg;

    private boolean deleteSms(String string2) {
        try {
            this.getContentResolver().delete(Uri.parse((String)("content://sms/" + string2)), null, null);
            return true;
        }
        catch (Exception var2_2) {
            return false;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private String getContactName(String var1) {
        var2_2 = ContactsContract.PhoneLookup.CONTENT_FILTER_URI;
        var3_3 = new String[]{"display_name"};
        try {
            var2_2 = (Uri)Class.forName((String)"android.provider.ContactsContract$PhoneLookup").getField("CONTENT_FILTER_URI").get((Object)var2_2);
            var3_3 = var8_4 = new String[]{"display_name"};
        }
        catch (Exception var4_8) {
            ** continue;
        }
lbl8: // 2 sources:
        do {
            var5_5 = Uri.withAppendedPath((Uri)var2_2, (String)Uri.encode((String)var1));
            var6_6 = this.getContentResolver().query(var5_5, var3_3, null, null, null);
            var7_7 = "";
            if (var6_6.moveToFirst()) {
                var7_7 = var6_6.getString(0);
            }
            var6_6.close();
            return var7_7;
            break;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void readDataSetmechine() {
        String string2 = "818370602";
        if (this.getString(2131230720).equalsIgnoreCase("OWS")) {
            string2 = "818370602";
        } else if (this.getString(2131230720).equalsIgnoreCase("VDC")) {
            string2 = "964269990";
        }
        Log.d((String)"debug", (String)("array_sort_msgt size >>> : " + this.array_sort_msgt.size()));
        int n = 0;
        while (n < this.array_sort_msgt.size()) {
            this.bundledata_sort_msg = (Bundle)this.array_sort_msgt.get(n);
            String string3 = this.bundledata_sort_msg.getString("messageId");
            String string4 = this.bundledata_sort_msg.getString("address");
            String string5 = this.bundledata_sort_msg.getString("body");
            if (string4.contains((CharSequence)"845557930")) {
                this.setDataSetmechine(string5);
                this.ID_msg = string3;
                return;
            }
            if (string4.contains((CharSequence)string2)) {
                this.setDataSetmechine(string5);
                this.ID_msg = string3;
                return;
            }
            ++n;
        }
        return;
    }

    private void readSMS() {
        Log.d((String)"debug", (String)"readSMS >>> : ");
        Cursor cursor = this.getContentResolver().query(Uri.parse((String)"content://sms/inbox"), new String[]{"_id", "thread_id", "address", "person", "date", "body"}, null, null, "date DESC");
        String string2 = "";
        do {
            if (!cursor.moveToNext()) {
                cursor.close();
                this.readDataSetmechine();
                return;
            }
            long l = cursor.getLong(0);
            long l2 = cursor.getLong(1);
            String string3 = cursor.getString(2);
            long l3 = cursor.getLong(3);
            String string4 = String.valueOf((long)l3);
            long l4 = cursor.getLong(4);
            String string5 = cursor.getString(5);
            string2 = String.valueOf((Object)string2) + "messageId " + l + "\nthreadId " + l2 + "\naddress " + string3 + "\ncontactId " + l3 + "\ncontactId_string " + string4 + "\ntimestamp " + l4 + "\nbody " + string5 + "\n\n\n";
            this.bundledata_sort_msg = new Bundle();
            this.bundledata_sort_msg.putString("messageId", "" + l);
            this.bundledata_sort_msg.putString("NameContact", this.getContactName(string3));
            this.bundledata_sort_msg.putString("address", string3);
            this.bundledata_sort_msg.putString("timestamp", "" + l4);
            this.bundledata_sort_msg.putString("body", string5);
            this.array_sort_msgt.add((Object)this.bundledata_sort_msg);
        } while (true);
    }

    private void setDataSetmechine(String string2) {
        String[] arrstring = string2.split("/");
        Log.v((String)"hello", (String)("body " + string2));
        EditText editText = (EditText)this.findViewById(2131361823);
        EditText editText2 = (EditText)this.findViewById(2131361822);
        ((EditText)this.findViewById(2131361949)).setText((CharSequence)arrstring[2]);
        editText.setText((CharSequence)arrstring[0]);
        editText2.setText((CharSequence)arrstring[1]);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void callUSSD(int var1) {
        var2_2 = this.getSimOperator();
        if (var2_2.length() != 0) ** GOTO lbl5
        var4_3 = var1 == 1 ? Uri.parse((String)("tel:" + Uri.encode((String)"*") + "101" + Uri.encode((String)"#"))) : Uri.parse((String)("tel:" + Uri.encode((String)"*") + "102" + Uri.encode((String)"#")));
        ** GOTO lbl12
lbl5: // 1 sources:
        if (!var2_2.equalsIgnoreCase("ais")) ** GOTO lbl8
        var4_3 = var1 == 1 ? Uri.parse((String)("tel:" + Uri.encode((String)"*") + "121" + Uri.encode((String)"#"))) : Uri.parse((String)("tel:" + Uri.encode((String)"*") + "545" + Uri.encode((String)"#")));
        ** GOTO lbl12
lbl8: // 1 sources:
        var3_5 = var2_2.equalsIgnoreCase("true-h");
        var4_3 = null;
        if (var3_5) {
            var4_3 = var1 == 1 ? Uri.parse((String)("tel:" + Uri.encode((String)"#") + "123" + Uri.encode((String)"#"))) : Uri.parse((String)("tel:" + Uri.encode((String)"*") + "933" + Uri.encode((String)"#")));
lbl12: // 3 sources:
            if (var4_3 != null) {
                var5_4 = new Intent("android.intent.action.CALL");
                var5_4.setData(var4_3);
                var5_4.addFlags(268435456);
                var5_4.addFlags(4);
                this.startActivity(var5_4);
                return;
            }
        }
        Log.e((String)"hello", (String)"unknown operator");
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void changeLanguage(int n) {
        Log.d((String)"debug", (String)("changeLanguage main " + n));
        TextView textView = (TextView)this.findViewById(2131361947);
        Button button = (Button)this.findViewById(2131361797);
        Button button2 = (Button)this.findViewById(2131361826);
        Button button3 = (Button)this.findViewById(2131361827);
        if (n == 1) {
            button.setBackgroundDrawable(CallImage.imageDrawableCard("bt_test_network_th"));
            button2.setBackgroundDrawable(CallImage.imageDrawableCard("bt_record_th"));
            button3.setBackgroundDrawable(CallImage.imageDrawableCard("bt_back_th"));
        } else {
            button.setBackgroundDrawable(CallImage.imageDrawableCard("bt_test_network_en"));
            button2.setBackgroundDrawable(CallImage.imageDrawableCard("bt_record_en"));
            button3.setBackgroundDrawable(CallImage.imageDrawableCard("bt_back_en"));
        }
        textView.setText((CharSequence)MessageTopup.getMessage(65));
        textView.setTypeface(MessageTopup.setFont((Context)this, 0));
    }

    public String getSimOperator() {
        return ((TelephonyManager)this.getSystemService("phone")).getSimOperatorName();
    }

    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903067);
        super.onCreate(bundle);
        EditText editText = (EditText)this.findViewById(2131361823);
        EditText editText2 = (EditText)this.findViewById(2131361822);
        EditText editText3 = (EditText)this.findViewById(2131361949);
        SharedPreferences sharedPreferences = this.getSharedPreferences("hello", 0);
        editText.setText((CharSequence)sharedPreferences.getString("HARDWARE_ID", ""));
        editText2.setText((CharSequence)sharedPreferences.getString("KEYTABLE", ""));
        editText3.setText((CharSequence)sharedPreferences.getString("PARTNER_ID", ""));
        Button button = (Button)this.findViewById(2131361797);
        View.OnClickListener onClickListener = new View.OnClickListener(){

            public void onClick(View view) {
            }
        };
        button.setOnClickListener(onClickListener);
        Button button2 = (Button)this.findViewById(2131361826);
        View.OnClickListener onClickListener2 = new View.OnClickListener(){

            public void onClick(View view) {
                EditText editText = (EditText)SetMachine.this.findViewById(2131361823);
                EditText editText2 = (EditText)SetMachine.this.findViewById(2131361822);
                EditText editText3 = (EditText)SetMachine.this.findViewById(2131361949);
                SharedPreferences.Editor editor = SetMachine.this.getSharedPreferences("hello", 0).edit();
                editor.putString("HARDWARE_ID", editText.getText().toString());
                editor.putString("KEYTABLE", editText2.getText().toString());
                editor.putString("PARTNER_ID", editText3.getText().toString());
                editor.commit();
                SetMachine.this.deleteSms(SetMachine.this.ID_msg);
                SetMachine.this.finish();
            }
        };
        button2.setOnClickListener(onClickListener2);
        Button button3 = (Button)this.findViewById(2131361958);
        View.OnClickListener onClickListener3 = new View.OnClickListener(){

            public void onClick(View view) {
                SetMachine.this.startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
            }
        };
        button3.setOnClickListener(onClickListener3);
        Button button4 = (Button)this.findViewById(2131361959);
        View.OnClickListener onClickListener4 = new View.OnClickListener(){

            public void onClick(View view) {
                SetMachine.this.callUSSD(1);
            }
        };
        button4.setOnClickListener(onClickListener4);
        Button button5 = (Button)this.findViewById(2131361960);
        View.OnClickListener onClickListener5 = new View.OnClickListener(){

            public void onClick(View view) {
                SetMachine.this.callUSSD(0);
            }
        };
        button5.setOnClickListener(onClickListener5);
        Button button6 = (Button)this.findViewById(2131361827);
        View.OnClickListener onClickListener6 = new View.OnClickListener(){

            public void onClick(View view) {
                SetMachine.this.finish();
            }
        };
        button6.setOnClickListener(onClickListener6);
        Button button7 = (Button)this.findViewById(2131361955);
        View.OnClickListener onClickListener7 = new View.OnClickListener(){

            public void onClick(View view) {
                SetMachine.this.readSMS();
            }
        };
        button7.setOnClickListener(onClickListener7);
        Button button8 = (Button)this.findViewById(2131361956);
        View.OnClickListener onClickListener8 = new View.OnClickListener(){

            public void onClick(View view) {
                SetMachine.this.startActivityForResult(new Intent("android.settings.SETTINGS"), 0);
            }
        };
        button8.setOnClickListener(onClickListener8);
        CheckBox checkBox = (CheckBox)this.findViewById(2131361954);
        SharedPreferences sharedPreferences2 = this.getSharedPreferences("hello", 0);
        final SharedPreferences.Editor editor = sharedPreferences2.edit();
        checkBox.setChecked(sharedPreferences2.getBoolean("inDebugMode", false));
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener(){

            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                editor.putBoolean("inDebugMode", bl);
                editor.commit();
            }
        };
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        CheckBox checkBox2 = (CheckBox)this.findViewById(2131361953);
        checkBox2.setChecked(sharedPreferences2.getBoolean("isICT", false));
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener2 = new CompoundButton.OnCheckedChangeListener(){

            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                Log.v((String)"hello", (String)("is ict " + bl));
                editor.putBoolean("isICT", bl);
                editor.commit();
            }
        };
        checkBox2.setOnCheckedChangeListener(onCheckedChangeListener2);
        CheckBox checkBox3 = (CheckBox)this.findViewById(2131361952);
        checkBox3.setChecked(sharedPreferences2.getBoolean("autoReset", false));
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener3 = new CompoundButton.OnCheckedChangeListener(){

            public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                editor.putBoolean("autoReset", bl);
                editor.commit();
            }
        };
        checkBox3.setOnCheckedChangeListener(onCheckedChangeListener3);
        this.changeLanguage(switchID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d((String)"debug", (String)"onResume active");
        final SharedPreferences sharedPreferences = this.getSharedPreferences("hello", 0);
        if (sharedPreferences.getBoolean("DownloadFinish", false)) {
            Button button = (Button)this.findViewById(2131361957);
            button.setTextColor(-16777216);
            button.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    String string2 = "/mnt/sdcard/Resource/" + sharedPreferences.getString("DownloadVersion", "") + ".apk";
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setDataAndType(Uri.fromFile((File)new File(string2)), "application/vnd.android.package-archive");
                    SetMachine.this.startActivity(intent);
                }
            });
        }
    }

}

