/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.os.Environment
 *  android.util.Log
 *  java.io.File
 *  java.io.FileNotFoundException
 *  java.io.FileReader
 *  java.io.FileWriter
 *  java.io.IOException
 *  java.lang.Boolean
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.daydr3am.lib;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class LogController {
    File filePosition;
    boolean isTest;
    JSONObject json;

    public LogController(Bundle bundle, boolean bl) {
        this.isTest = bl;
        File file = new File((Object)Environment.getExternalStorageDirectory() + "/resource/Logs/");
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            this.json = new JSONObject();
            Log.v((String)"hello", (String)("input money " + bundle.getInt("OR") + " " + bundle.getInt("MC")));
            this.json.put("OR", (Object)String.valueOf((int)bundle.getInt("OR")));
            this.json.put("MC", (Object)String.valueOf((int)bundle.getInt("MC")));
            this.json.put("Mobile", (Object)bundle.getString("Mobile"));
            this.json.put("Network", (Object)bundle.getString("Network"));
            this.json.put("Price", (Object)bundle.getString("Price"));
            this.json.put("RandomKey", (Object)bundle.getString("RandomKey"));
            return;
        }
        catch (JSONException var4_4) {
            var4_4.printStackTrace();
            return;
        }
    }

    public static int checkFileList() {
        File file = new File((Object)Environment.getExternalStorageDirectory() + "/resource/Logs/");
        if (!file.exists()) {
            file.mkdir();
        }
        Log.v((String)"hello", (String)("length " + file.list().length));
        return file.list().length;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void deleteLog() {
        File file = new File((Object)Environment.getExternalStorageDirectory() + "/resource/Logs/");
        if (!file.isDirectory()) return;
        String[] arrstring = file.list();
        int n = 0;
        while (n < arrstring.length) {
            new File(file, arrstring[n]).delete();
            ++n;
        }
        return;
    }

    public static void deletefile(String string2) {
        String string3 = (Object)Environment.getExternalStorageDirectory() + "/resource/Logs/" + string2 + ".txt";
        Log.v((String)"hello", (String)("delete" + string3));
        File file = new File(string3);
        if (file.exists()) {
            file.delete();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Bundle getBundle(String string2) {
        try {
            char[] arrc = new char[1024];
            String string3 = (Object)Environment.getExternalStorageDirectory() + "/resource/Logs/" + string2;
            Log.v((String)"hello", (String)("log shoot " + string3));
            Log.v((String)"hello", (String)("pathFile " + string3));
            FileReader fileReader = new FileReader(new File(string3));
            fileReader.read(arrc);
            fileReader.close();
            Log.v((String)"hello", (String)("buffer " + new String(arrc)));
            JSONObject jSONObject = new JSONObject(new String(arrc));
            Bundle bundle = new Bundle();
            bundle.putString("OTP", jSONObject.optString("OTP"));
            bundle.putBoolean("isUtility", jSONObject.optBoolean("isUtility"));
            if (jSONObject.optString("DataBarcode", null) != null) {
                bundle.putBoolean("isBarcode", true);
                bundle.putString("TY", jSONObject.optString("TY"));
                bundle.putString("TRAN", jSONObject.optString("TRAN"));
            }
            bundle.putString("Data1", jSONObject.optString("Data1"));
            bundle.putString("Data2", jSONObject.optString("Data2"));
            bundle.putString("Data3", jSONObject.optString("Data3"));
            bundle.putString("Data4", jSONObject.optString("Data4"));
            bundle.putString("dateUser", jSONObject.optString("dateUser"));
            bundle.putInt("OR", Integer.parseInt((String)jSONObject.getString("OR")));
            bundle.putInt("MC", Integer.parseInt((String)jSONObject.getString("MC")));
            bundle.putString("Mobile", jSONObject.getString("Mobile"));
            bundle.putString("Network", jSONObject.getString("Network"));
            bundle.putString("Price", jSONObject.getString("Price"));
            bundle.putString("RandomKey", jSONObject.getString("RandomKey"));
            bundle.putString("TotalPrice", jSONObject.getString("TotalPrice"));
            bundle.putString("TotalCoin1", jSONObject.getString("TotalCoin1"));
            bundle.putString("TotalCoin2", jSONObject.getString("TotalCoin2"));
            bundle.putString("TotalCoin3", jSONObject.getString("TotalCoin3"));
            bundle.putString("TotalCoin4", jSONObject.getString("TotalCoin4"));
            bundle.putString("TotalBank1", jSONObject.getString("TotalBank1"));
            bundle.putString("TotalBank2", jSONObject.getString("TotalBank2"));
            bundle.putString("OperationRate", jSONObject.getString("OperationRate"));
            Log.v((String)"hello", (String)("total coin1 " + bundle.getString("TotalCoin1")));
            return bundle;
        }
        catch (FileNotFoundException var3_6) {
            var3_6.printStackTrace();
            do {
                return null;
                break;
            } while (true);
        }
        catch (IOException var2_7) {
            var2_7.printStackTrace();
            return null;
        }
        catch (JSONException var1_8) {
            var1_8.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void writerLog(Bundle bundle) {
        if (this.isTest) {
            return;
        }
        Boolean bl = false;
        String string2 = bundle.getString("Mobile");
        this.filePosition = new File((Object)Environment.getExternalStorageDirectory() + "/resource/Logs/" + string2 + ".txt");
        if (!this.filePosition.exists()) {
            try {
                Boolean bl2;
                this.filePosition.createNewFile();
                bl = bl2 = Boolean.valueOf((boolean)true);
            }
            catch (IOException var20_7) {
                var20_7.printStackTrace();
            }
        }
        try {
            this.json.put("TotalPrice", (Object)bundle.getString("TotalPrice"));
            this.json.put("TotalCoin1", (Object)bundle.getString("TotalCoin1"));
            this.json.put("TotalCoin2", (Object)bundle.getString("TotalCoin2"));
            this.json.put("TotalCoin3", (Object)bundle.getString("TotalCoin3"));
            this.json.put("TotalCoin4", (Object)bundle.getString("TotalCoin4"));
            this.json.put("TotalBank1", (Object)bundle.getString("TotalBank1"));
            this.json.put("TotalBank2", (Object)bundle.getString("TotalBank2"));
            this.json.put("OperationRate", (Object)bundle.getString("OperationRate"));
            FileWriter fileWriter = new FileWriter(this.filePosition.getAbsolutePath());
            Log.v((String)"hello", (String)("write " + this.json.toString()));
            fileWriter.write(this.json.toString());
            fileWriter.flush();
            fileWriter.close();
            return;
        }
        catch (JSONException var8_6) {
            if (bl.booleanValue()) {
                this.filePosition.delete();
            }
            var8_6.printStackTrace();
            return;
        }
        catch (IOException var6_8) {
            if (bl.booleanValue()) {
                this.filePosition.delete();
            }
            var6_8.printStackTrace();
            return;
        }
        catch (Exception var4_9) {
            if (bl.booleanValue()) {
                this.filePosition.delete();
            }
            var4_9.printStackTrace();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void writerUtilityLog(Bundle bundle) {
        if (this.isTest) {
            return;
        }
        String string2 = bundle.getString("Mobile");
        this.filePosition = new File((Object)Environment.getExternalStorageDirectory() + "/resource/Logs/" + string2 + ".txt");
        if (!this.filePosition.exists()) {
            try {
                this.filePosition.createNewFile();
            }
            catch (IOException var25_5) {
                var25_5.printStackTrace();
            }
        }
        try {
            this.json.put("isUtility", true);
            if (bundle.getString("DataBarcode", null) != null) {
                this.json.put("DataBarcode", (Object)bundle.getString("DataBarcode"));
            }
            if (bundle.getString("TY", null) != null) {
                this.json.put("TY", (Object)bundle.getString("TY"));
            }
            if (bundle.getString("TRAN", null) != null) {
                this.json.put("TRAN", (Object)bundle.getString("TRAN"));
            }
            this.json.put("OTP", (Object)bundle.getString("OTP"));
            this.json.put("Data1", (Object)bundle.getString("Data1"));
            this.json.put("Data2", (Object)bundle.getString("Data2"));
            this.json.put("Data3", (Object)bundle.getString("Data3"));
            this.json.put("Data4", (Object)bundle.getString("Data4"));
            this.json.put("dateUser", (Object)bundle.getString("dateUser"));
            this.json.put("TotalPrice", (Object)bundle.getString("TotalPrice"));
            this.json.put("TotalCoin1", (Object)bundle.getString("TotalCoin1"));
            this.json.put("TotalCoin2", (Object)bundle.getString("TotalCoin2"));
            this.json.put("TotalCoin3", (Object)bundle.getString("TotalCoin3"));
            this.json.put("TotalCoin4", (Object)bundle.getString("TotalCoin4"));
            this.json.put("TotalBank1", (Object)bundle.getString("TotalBank1"));
            this.json.put("TotalBank2", (Object)bundle.getString("TotalBank2"));
            this.json.put("OperationRate", (Object)bundle.getString("OperationRate"));
            FileWriter fileWriter = new FileWriter(this.filePosition.getAbsolutePath());
            Log.v((String)"hello", (String)("write " + this.json.toString()));
            fileWriter.write(this.json.toString());
            fileWriter.flush();
            fileWriter.close();
            return;
        }
        catch (JSONException var4_4) {
            var4_4.printStackTrace();
            return;
        }
        catch (IOException var3_6) {
            var3_6.printStackTrace();
            return;
        }
    }
}

