/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.accessibilityservice.AccessibilityService
 *  android.accessibilityservice.AccessibilityServiceInfo
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Handler
 *  android.provider.Settings
 *  android.provider.Settings$Secure
 *  android.provider.Settings$SettingNotFoundException
 *  android.text.TextUtils
 *  android.text.TextUtils$SimpleStringSplitter
 *  android.util.Log
 *  android.view.accessibility.AccessibilityEvent
 *  android.view.accessibility.AccessibilityNodeInfo
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.util.Iterator
 *  java.util.List
 */
package com.daydr3am.lib;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.Iterator;
import java.util.List;

public class UssdAccessibilityService
extends AccessibilityService {
    private static final String TAG = "MyAccessibilityService";
    AccessibilityNodeInfo accessibilitynodeinfo1;
    Runnable clickUpdate;
    Runnable close;
    String data;
    private final AccessibilityServiceInfo info;
    Runnable sendData;

    public UssdAccessibilityService() {
        this.clickUpdate = new Runnable(){

            public void run() {
                AccessibilityNodeInfo accessibilityNodeInfo = UssdAccessibilityService.getButtonUpdate(UssdAccessibilityService.this.accessibilitynodeinfo1, "Button");
                if (accessibilityNodeInfo != null) {
                    accessibilityNodeInfo.performAction(16);
                }
            }
        };
        this.sendData = new Runnable(){

            public void run() {
                Log.e((String)"hello", (String)("send value " + UssdAccessibilityService.this.data));
                AccessibilityNodeInfo accessibilityNodeInfo = UssdAccessibilityService.getButton(UssdAccessibilityService.this.accessibilitynodeinfo1, "Button");
                if (accessibilityNodeInfo != null) {
                    accessibilityNodeInfo.performAction(16);
                }
                try {
                    Intent intent = new Intent("com.example.notificationservice.CATCH_TOAST");
                    intent.putExtra("extra_cutmessage", UssdAccessibilityService.this.getUSSDValue(UssdAccessibilityService.this.data));
                    intent.putExtra("extra_message", UssdAccessibilityService.this.data);
                    UssdAccessibilityService.this.getApplicationContext().sendBroadcast(intent);
                    return;
                }
                catch (Exception var4_3) {
                    Log.v((String)"MyAccessibilityService", (String)var4_3.toString());
                    return;
                }
            }
        };
        this.close = new Runnable(){

            public void run() {
                AccessibilityNodeInfo accessibilityNodeInfo = UssdAccessibilityService.getButton(UssdAccessibilityService.this.accessibilitynodeinfo1, "Button");
                if (accessibilityNodeInfo != null) {
                    accessibilityNodeInfo.performAction(16);
                }
            }
        };
        this.info = new AccessibilityServiceInfo();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static AccessibilityNodeInfo getButton(AccessibilityNodeInfo accessibilityNodeInfo, String string2) {
        int n = 0;
        while (n < accessibilityNodeInfo.getChildCount()) {
            AccessibilityNodeInfo accessibilityNodeInfo2 = accessibilityNodeInfo.getChild(n);
            if (accessibilityNodeInfo2.getClassName().toString().contains((CharSequence)string2)) return accessibilityNodeInfo2;
            accessibilityNodeInfo2.recycle();
            ++n;
        }
        return null;
    }

    private static AccessibilityNodeInfo getButtonUpdate(AccessibilityNodeInfo accessibilityNodeInfo, String string2) {
        if (accessibilityNodeInfo.getChildCount() == 5 && accessibilityNodeInfo.getChild(4).getClassName().toString().contains((CharSequence)string2)) {
            return accessibilityNodeInfo.getChild(4);
        }
        return null;
    }

    private String getEventText(AccessibilityEvent accessibilityEvent) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator iterator = accessibilityEvent.getText().iterator();
        while (iterator.hasNext()) {
            stringBuilder.append((CharSequence)iterator.next());
        }
        return stringBuilder.toString();
    }

    private String getEventType(AccessibilityEvent accessibilityEvent) {
        switch (accessibilityEvent.getEventType()) {
            default: {
                return "default";
            }
            case 64: {
                return "TYPE_NOTIFICATION_STATE_CHANGED";
            }
            case 1: {
                return "TYPE_VIEW_CLICKED";
            }
            case 8: {
                return "TYPE_VIEW_FOCUSED";
            }
            case 2: {
                return "TYPE_VIEW_LONG_CLICKED";
            }
            case 4: {
                return "TYPE_VIEW_SELECTED";
            }
            case 32: {
                return "TYPE_WINDOW_STATE_CHANGED";
            }
            case 16: 
        }
        return "TYPE_VIEW_TEXT_CHANGED";
    }

    /*
     * Exception decompiling
     */
    private static String getMessage(AccessibilityNodeInfo var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.util.ConcurrentModificationException
        // java.util.LinkedList$ReverseLinkIterator.next(LinkedList.java:217)
        // org.benf.cfr.reader.bytecode.analysis.structured.statement.Block.extractLabelledBlocks(Block.java:212)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement$LabelledBlockExtractor.transform(Op04StructuredStatement.java:485)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.transform(Op04StructuredStatement.java:639)
        // org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredDo.transformStructuredChildren(StructuredDo.java:53)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement$LabelledBlockExtractor.transform(Op04StructuredStatement.java:487)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.transform(Op04StructuredStatement.java:639)
        // org.benf.cfr.reader.bytecode.analysis.structured.statement.Block.transformStructuredChildren(Block.java:378)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement$LabelledBlockExtractor.transform(Op04StructuredStatement.java:487)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.transform(Op04StructuredStatement.java:639)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.insertLabelledBlocks(Op04StructuredStatement.java:649)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:816)
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

    /*
     * Exception decompiling
     */
    private String getUSSDValue(String var1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.util.ConcurrentModificationException
        // java.util.LinkedList$ReverseLinkIterator.next(LinkedList.java:217)
        // org.benf.cfr.reader.bytecode.analysis.structured.statement.Block.extractLabelledBlocks(Block.java:212)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement$LabelledBlockExtractor.transform(Op04StructuredStatement.java:485)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.transform(Op04StructuredStatement.java:639)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.insertLabelledBlocks(Op04StructuredStatement.java:649)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:816)
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

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static boolean isAccessibilitySettingsOn(Context context) {
        String string2;
        int n = 0;
        try {
            n = Settings.Secure.getInt((ContentResolver)context.getApplicationContext().getContentResolver(), (String)"accessibility_enabled");
            Log.v((String)"MyAccessibilityService", (String)("accessibilityEnabled = " + n));
        }
        catch (Settings.SettingNotFoundException var2_2) {
            Log.e((String)"MyAccessibilityService", (String)("Error finding setting, default accessibility to not found: " + var2_2.getMessage()));
        }
        TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
        if (n != 1) {
            Log.v((String)"MyAccessibilityService", (String)"***ACCESSIBILIY IS DISABLED***");
            return false;
        }
        Log.v((String)"MyAccessibilityService", (String)"***ACCESSIBILIY IS ENABLED*** -----------------");
        String string3 = Settings.Secure.getString((ContentResolver)context.getApplicationContext().getContentResolver(), (String)"enabled_accessibility_services");
        if (string3 == null) return false;
        simpleStringSplitter.setString(string3);
        do {
            if (!simpleStringSplitter.hasNext()) {
                return false;
            }
            string2 = simpleStringSplitter.next();
            Log.v((String)"MyAccessibilityService", (String)("-------------- > accessabilityService :: " + string2));
        } while (!string2.equalsIgnoreCase("com.example.notificationservice/com.example.notificationservice.MyAccessibilityService"));
        Log.v((String)"MyAccessibilityService", (String)"We've found the correct setting - accessibility is switched on!");
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        int n = accessibilityEvent.getEventType();
        Log.v((String)"hello", (String)"message onAccessibilityEvent");
        Object[] arrobject = new Object[]{super.getEventType(accessibilityEvent), accessibilityEvent.getClassName(), accessibilityEvent.getPackageName(), accessibilityEvent.getEventTime(), super.getEventText(accessibilityEvent), n};
        Log.i((String)"MyAccessibilityService", (String)String.format((String)"onAccessibilityEvent: [type] %s [class] %s [package] %s [time] %s [text] %s [eventCode] %s", (Object[])arrobject));
        if (n == 32) {
            if (accessibilityEvent.getClassName().toString().contains((CharSequence)"UssdAlertActivity") && accessibilityEvent.getSource() != null) {
                Object[] arrobject2 = new Object[]{super.getEventType(accessibilityEvent), accessibilityEvent.getClassName(), accessibilityEvent.getPackageName(), accessibilityEvent.getEventTime(), super.getEventText(accessibilityEvent), n};
                Log.i((String)"MyAccessibilityService", (String)String.format((String)"onAccessibilityEvent: [type] %s [class] %s [package] %s [time] %s [text] %s [eventCode] %s", (Object[])arrobject2));
            }
            this.accessibilitynodeinfo1 = accessibilityEvent.getSource();
            this.data = UssdAccessibilityService.getMessage(this.accessibilitynodeinfo1);
            Log.e((String)"MyAccessibilityService", (String)("data " + this.data));
            Log.e((String)"MyAccessibilityService", (String)("event " + super.getEventText(accessibilityEvent)));
            if (this.data != null) {
                Log.e((String)"hello", (String)(String.valueOf((Object)this.getString(2131230723)) + " " + super.getEventText(accessibilityEvent)));
                if (super.getEventText(accessibilityEvent).equalsIgnoreCase("phone") || super.getEventText(accessibilityEvent).equalsIgnoreCase(this.getString(2131230723))) {
                    new Handler().postDelayed(this.sendData, 5000);
                } else {
                    Log.e((String)"MyAccessibilityService", (String)("close data " + this.data));
                    Log.e((String)"MyAccessibilityService", (String)("close event " + super.getEventText(accessibilityEvent)));
                    new Handler().post(this.close);
                }
            }
            if (!super.getEventText(accessibilityEvent).equalsIgnoreCase(this.getString(2131230724))) return;
            {
                new Handler().post(this.clickUpdate);
                return;
            }
        } else {
            Log.v((String)"hello", (String)"tag6");
            if (!super.getEventType(accessibilityEvent).equals((Object)"default") || !accessibilityEvent.getPackageName().equals((Object)"com.android.mms")) return;
            {
                Object[] arrobject3 = new Object[]{super.getEventType(accessibilityEvent), accessibilityEvent.getClassName(), accessibilityEvent.getPackageName(), accessibilityEvent.getEventTime(), super.getEventText(accessibilityEvent), n};
                Log.v((String)"MyAccessibilityService", (String)String.format((String)"onAccessibilityEvent: [type] %s [class] %s [package] %s [time] %s [text] %s [eventCode] %s", (Object[])arrobject3));
                return;
            }
        }
    }

    public void onInterrupt() {
    }

    public static final class Constants {
        public static final String ACTION_CATCH_NOTIFICATION = "com.example.notificationservice.CATCH_NOTIFICATION";
        public static final String ACTION_CATCH_TOAST = "com.example.notificationservice.CATCH_TOAST";
        public static final String EXTRA_CUTMESSAGE = "extra_cutmessage";
        public static final String EXTRA_MESSAGE = "extra_message";
        public static final String EXTRA_PACKAGE = "extra_package";
    }

}

