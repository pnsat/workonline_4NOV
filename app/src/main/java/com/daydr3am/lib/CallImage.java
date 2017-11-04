/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.util.Log
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.util.HashMap
 */
package com.daydr3am.lib;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.util.HashMap;

public class CallImage {
    private static String IMAGE_PATH = "/mnt/sdcard/Resource/";
    public static Context baseContext;
    private static HashMap<String, Drawable> mDrawables_button;
    private static HashMap<String, Drawable> mDrawables_image;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static Drawable getBackground(String string2) {
        try {
            if (mDrawables_image == null) {
                mDrawables_image = new HashMap();
            }
            if (mDrawables_image.containsKey((Object)string2)) return (Drawable)mDrawables_image.get((Object)string2);
            mDrawables_image.put((Object)string2, (Object)BitmapDrawable.createFromPath((String)("/sdcard/Resource/save/loaddata/" + string2)));
            Log.v((String)"hello", (String)("load path /sdcard/Resource/save/loaddata/" + string2));
            Log.v((String)"hello", (String)("load obj" + (Object)BitmapDrawable.createFromPath((String)new StringBuilder("/sdcard/Resource/save/loaddata/").append(string2).toString())));
        }
        catch (Exception var1_1) {
            var1_1.printStackTrace();
            return (Drawable)mDrawables_image.get((Object)string2);
        }
        return (Drawable)mDrawables_image.get((Object)string2);
    }

    public static Drawable getimage(String string2) {
        try {
            Drawable drawable2 = BitmapDrawable.createFromPath((String)(String.valueOf((Object)IMAGE_PATH) + string2 + ".png"));
            return drawable2;
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static Drawable imageDrawableCard(String string2) {
        try {
            if (mDrawables_button == null) {
                mDrawables_button = new HashMap();
            }
            if (mDrawables_button.containsKey((Object)string2)) return (Drawable)mDrawables_button.get((Object)string2);
            Log.v((String)"test", (String)("file name " + string2 + " " + (Object)baseContext));
            int n = baseContext.getResources().getIdentifier(string2, "drawable", baseContext.getPackageName());
            Drawable drawable2 = baseContext.getResources().getDrawable(n);
            Log.v((String)"test", (String)("get base " + string2 + "  " + (Object)drawable2));
            mDrawables_button.put((Object)string2, (Object)drawable2);
        }
        catch (Exception var1_3) {
            var1_3.printStackTrace();
            return (Drawable)mDrawables_button.get((Object)string2);
        }
        return (Drawable)mDrawables_button.get((Object)string2);
    }
}

