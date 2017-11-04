/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Service
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.location.Location
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.IBinder
 *  android.telephony.TelephonyManager
 *  android.util.Log
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.Process
 *  java.lang.Runnable
 *  java.lang.Runtime
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.lang.reflect.Method
 *  java.util.ArrayList
 *  java.util.List
 *  org.apache.http.NameValuePair
 *  org.apache.http.message.BasicNameValuePair
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.daydr3am.lib;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.daydr3am.lib.GPSTracker;
import com.daydr3am.lib.ServiceOperation;
import com.daydr3am.lib.ServiceOperationListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class ConnectService
extends Service
implements ServiceOperationListener,
Runnable {
    static int connectionLostCount;
    static Thread single;
    long MIN_TIME_BW_UPDATES = 60000;
    long TOGGLE_MIN_TIME_BW_UPDATES = 5000;
    GPSTracker gps;
    Handler handler = new Handler();
    double lat = 0.0;
    double lng = 0.0;
    Runnable runSend = null;
    Runnable runToggle = null;

    static {
        single = null;
        connectionLostCount = 0;
    }

    private void CheckSoftware() {
        Log.v((String)"hello", (String)"CheckSoftware");
        try {
            ServiceOperation serviceOperation = new ServiceOperation("http://www.owsth.com/owstopup/api_software/softwareapi.php");
            serviceOperation.listener = this;
            SharedPreferences sharedPreferences = this.getSharedPreferences("hello", 0);
            String string2 = sharedPreferences.getString("HARDWARE_ID", "new");
            String string3 = sharedPreferences.getString("KEYTABLE", "new");
            ArrayList arrayList = new ArrayList();
            arrayList.add((Object)new BasicNameValuePair("username", string2));
            arrayList.add((Object)new BasicNameValuePair("password", string3));
            arrayList.add((Object)new BasicNameValuePair("type", "kiosk"));
            serviceOperation.nameValuePairs = arrayList;
            serviceOperation.beforeExcute("check");
            return;
        }
        catch (Exception var3_6) {
            var3_6.printStackTrace();
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private void GetGPS(boolean var1) {
        if (this.gps.canGetLocation()) {
            this.gps.getLocation();
            var3_2 = this.gps.getLatitude();
            var5_3 = this.gps.getLongitude();
            Class.forName((String)"android.os.SystemProperties").getMethod("get", new Class[]{String.class});
lbl7: // 2 sources:
            do {
                this.lat = var3_2;
                this.lng = var5_3;
                return;
                break;
            } while (true);
        }
        this.gps.showSettingsAlert();
        return;
        catch (Exception var7_4) {
            ** continue;
        }
    }

    private void InitServiceTracking() {
        this.gps = new GPSTracker((Context)this);
        final Handler handler = new Handler();
        this.runToggle = new Runnable(){

            public void run() {
                ConnectService.this.GetGPS(false);
                handler.postDelayed(ConnectService.this.runToggle, ConnectService.this.TOGGLE_MIN_TIME_BW_UPDATES);
            }
        };
        handler.postDelayed(this.runToggle, this.TOGGLE_MIN_TIME_BW_UPDATES);
        final Handler handler2 = new Handler();
        this.runSend = new Runnable(){

            public void run() {
                ConnectService.this.GetGPS(true);
                handler2.postDelayed(ConnectService.this.runSend, ConnectService.this.MIN_TIME_BW_UPDATES);
            }
        };
        handler2.postDelayed(this.runSend, this.MIN_TIME_BW_UPDATES);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private int compareVersionNames(String string2, String string3) {
        String[] arrstring = string2.split("\\.");
        String[] arrstring2 = string3.split("\\.");
        int n = Math.min((int)arrstring.length, (int)arrstring2.length);
        int n2 = 0;
        do {
            int n3;
            int n4 = 0;
            if (n2 >= n) {
                if (n4 != 0) return n4;
                if (arrstring.length == arrstring2.length) return n4;
                if (arrstring.length <= arrstring2.length) return -1;
                return 1;
            }
            int n5 = Integer.valueOf((String)arrstring[n2]);
            if (n5 < (n3 = Integer.valueOf((String)arrstring2[n2]).intValue())) {
                return -1;
            }
            if (n5 > n3) {
                return 1;
            }
            ++n2;
        } while (true);
    }

    private void rebootDevice() {
        try {
            Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot"}).waitFor();
            return;
        }
        catch (Exception var1_1) {
            Log.d((String)"debug", (String)"Could not reboot", (Throwable)var1_1);
            return;
        }
    }

    @Override
    public void ServiceconnectionFail(String string2) {
        if (!string2.equalsIgnoreCase("check")) {
            Log.v((String)"hello", (String)"return check clear");
            SharedPreferences.Editor editor = this.getSharedPreferences("hello", 0).edit();
            editor.putString("DownloadVersion", null);
            editor.commit();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void ServiceconnectionFinish(String string2, String string3) {
        SharedPreferences.Editor editor = this.getSharedPreferences("hello", 0).edit();
        SharedPreferences sharedPreferences = this.getSharedPreferences("hello", 0);
        if (string3.equalsIgnoreCase("check")) {
            Log.v((String)"hello", (String)("return check" + string2));
            try {
                JSONObject jSONObject = new JSONObject(string2);
                String string4 = this.getPackageManager().getPackageInfo((String)this.getPackageName(), (int)0).versionName;
                Log.v((String)"hello", (String)("return check" + sharedPreferences.getString("DownloadVersion", null)));
                if (jSONObject.optString("status").equalsIgnoreCase("OK") && super.compareVersionNames(string4, jSONObject.optString("setversion")) == -1 && sharedPreferences.getString("DownloadVersion", null) == null) {
                    ServiceOperation serviceOperation = new ServiceOperation(jSONObject.optString("link").replace((CharSequence)"https", (CharSequence)"http"));
                    serviceOperation.listener = this;
                    serviceOperation.dataPath = "/mnt/sdcard/Resource/" + jSONObject.optString("setversion") + ".apk";
                    Log.v((String)"hello", (String)("set path check " + serviceOperation.dataPath));
                    String[] arrstring = new String[]{jSONObject.optString("setversion")};
                    serviceOperation.beforeExcute(arrstring);
                    editor.putString("DownloadVersion", jSONObject.optString("setversion"));
                    editor.commit();
                    return;
                }
                if (super.compareVersionNames(string4, jSONObject.optString("setversion")) != 0) return;
                editor.putString("DownloadVersion", null);
                editor.commit();
                return;
            }
            catch (JSONException var11_9) {
                var11_9.printStackTrace();
                return;
            }
            catch (PackageManager.NameNotFoundException var10_10) {
                var10_10.printStackTrace();
                return;
            }
        }
        Log.v((String)"hello", (String)"check save finish");
        editor.putBoolean("DownloadFinish", true);
        editor.commit();
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

    public String getSimOperator() {
        return ((TelephonyManager)this.getSystemService("phone")).getSimOperatorName();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate();
        Log.v((String)"hello", (String)"create thread");
    }

    public void onStart(Intent intent, int n) {
        Log.v((String)"hello", (String)"start command");
        if (single == null) {
            super.InitServiceTracking();
            single = new Thread((Runnable)this);
            single.start();
        }
    }

    /*
     * Exception decompiling
     */
    public void run() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 8[TRYBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // com.njlabs.showjava.processor.JavaExtractor$1.run(JavaExtractor.java:100)
        // java.lang.Thread.run(Thread.java:818)
        throw new IllegalStateException("Decompilation failed");
    }

}

