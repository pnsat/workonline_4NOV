/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Service
 *  android.content.Intent
 *  android.os.Bundle
 *  android.os.Environment
 *  android.os.IBinder
 *  android.util.Log
 *  java.io.File
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.Thread
 */
package com.daydr3am.lib;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import com.daydr3am.lib.LogController;
import com.daydr3am.lib.Service;
import java.io.File;

public class LogChecker
extends android.app.Service
implements Runnable {
    public static boolean isSplash;
    static Thread single;
    File file;
    String[] oldListFile;

    static {
        single = null;
    }

    public void cancelInput(Bundle bundle) {
        try {
            Log.v((String)"hello", (String)"do cancel");
            String string2 = Service.cancelInputMoney(bundle);
            Log.v((String)"hello", (String)("do cancel" + string2));
            if (string2.split("&")[0].split("=")[1].equals((Object)"OK")) {
                LogController.deletefile(bundle.getString("Mobile"));
                return;
            }
            this.logCardFail(bundle, string2);
            LogController.deletefile(bundle.getString("Mobile"));
            return;
        }
        catch (Exception var2_3) {
            var2_3.printStackTrace();
            return;
        }
    }

    public void cancelInputUtility(Bundle bundle) {
        try {
            Log.v((String)"hello", (String)"do cancel");
            String string2 = Service.cancelInputMoneyUtil(bundle);
            Log.v((String)"hello", (String)("do cancel" + string2));
            if (string2.split("&")[0].split("=")[1].equals((Object)"OK")) {
                LogController.deletefile(bundle.getString("Mobile"));
                return;
            }
            this.logUtilFail(bundle, string2);
            LogController.deletefile(bundle.getString("Mobile"));
            return;
        }
        catch (Exception var2_3) {
            var2_3.printStackTrace();
            return;
        }
    }

    public void logCardFail(Bundle bundle, String string2) throws Exception {
        Log.v((String)"hello", (String)("card " + (-1 + Integer.parseInt((String)bundle.getString("Network")))));
        Bundle bundle2 = new Bundle();
        bundle2.putString("Mobile", bundle.getString("Mobile"));
        bundle2.putString("Word", "Log Card  " + bundle.getString("Network") + " T" + bundle.getString("TotalPrice") + " B" + bundle.getString("TotalBank1") + " C" + bundle.getString("TotalCoin1") + " " + string2);
        bundle2.putInt("Service", 10);
        Service.sendProblem(bundle2);
    }

    public void logUtilFail(Bundle bundle, String string2) throws Exception {
        Bundle bundle2 = new Bundle();
        bundle2.putString("Mobile", bundle.getString("Mobile"));
        bundle2.putString("Word", "Log Utility " + bundle.getString("Network") + " T" + bundle.getString("TotalPrice") + " B" + bundle.getString("TotalBank1") + " C" + bundle.getString("TotalCoin1") + " " + string2);
        bundle2.putInt("Service", 10);
        Service.sendProblem(bundle2);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate();
    }

    public void onStart(Intent intent, int n) {
        Log.v((String)"hello", (String)"start command");
        if (single == null) {
            single = new Thread((Runnable)this);
            single.start();
            this.file = new File((Object)Environment.getExternalStorageDirectory() + "/resource/Logs/");
        }
    }

    public void payMoney(Bundle bundle) {
        try {
            Log.v((String)"hello", (String)"do pay");
            String string2 = Service.inputMoney(bundle);
            Log.v((String)"hello", (String)("return message " + string2));
            if (string2.split("&")[0].split("=")[1].equals((Object)"OK")) {
                LogController.deletefile(bundle.getString("Mobile"));
                return;
            }
            this.logCardFail(bundle, string2);
            LogController.deletefile(bundle.getString("Mobile"));
            return;
        }
        catch (Exception var2_3) {
            var2_3.printStackTrace();
            return;
        }
    }

    public void payMoneyBarcode(Bundle bundle) {
        try {
            Log.e((String)"hello", (String)"call by log");
            String string2 = Service.inputMoneyBarcode(bundle);
            Log.e((String)"hello", (String)("return message " + string2));
            if (string2.split("&")[0].split("=")[1].equals((Object)"OK")) {
                LogController.deletefile(bundle.getString("Mobile"));
                return;
            }
            this.logUtilFail(bundle, string2);
            LogController.deletefile(bundle.getString("Mobile"));
            return;
        }
        catch (Exception var2_3) {
            var2_3.printStackTrace();
            return;
        }
    }

    public void payMoneyUtility(Bundle bundle) {
        try {
            Log.v((String)"hello", (String)"do pay");
            String string2 = Service.inputMoneyUtil(bundle);
            Log.v((String)"hello", (String)("return message " + string2));
            if (string2.split("&")[0].split("=")[1].equals((Object)"OK")) {
                LogController.deletefile(bundle.getString("Mobile"));
                return;
            }
            this.logUtilFail(bundle, string2);
            LogController.deletefile(bundle.getString("Mobile"));
            return;
        }
        catch (Exception var2_3) {
            var2_3.printStackTrace();
            return;
        }
    }

    /*
     * Exception decompiling
     */
    public void run() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 11[UNCONDITIONALDOLOOP]
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

