/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.res.Resources
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnCompletionListener
 *  android.util.Log
 *  java.io.File
 *  java.io.IOException
 *  java.lang.IllegalArgumentException
 *  java.lang.IllegalStateException
 *  java.lang.NullPointerException
 *  java.lang.Object
 *  java.lang.SecurityException
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.daydr3am.lib;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.util.Log;
import java.io.File;
import java.io.IOException;

public class AudioDemo
implements MediaPlayer.OnCompletionListener {
    private static String DB_PATH = "/mnt/sdcard/Resource/Command.Mp3/";
    private static String Sound_PATH = "/mnt/sdcard/Resource/";
    public static String active;
    public static Context baseContext;
    public static String close;
    public static String close_box;
    public static String open;
    public static String open_box;
    public static AudioDemo point;
    public static String standby;
    private MediaPlayer mp;
    public int playId;
    public SharedPreferences preferences;

    static {
        open = "0x6F_o.mp3";
        close = "0x66_f.mp3";
        open_box = "0x75_u.mp3";
        close_box = "0x6C_l.mp3";
        standby = "0x30_0.mp3";
        active = "0x31_1.mp3";
    }

    public static AudioDemo Sound() {
        if (point == null) {
            point = new AudioDemo();
        }
        return point;
    }

    private void goBlooey(Throwable throwable) {
    }

    private void pause() {
        this.mp.pause();
    }

    private void stop() {
        this.mp.stop();
        try {
            this.mp.prepare();
            this.mp.seekTo(0);
            return;
        }
        catch (Throwable var1_1) {
            this.goBlooey(var1_1);
            return;
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
    }

    public void play() {
        this.stop();
        this.mp.start();
    }

    public void playAdSound(String string2) {
        if (this.mp != null) {
            this.mp.release();
        }
        this.mp = new MediaPlayer();
        try {
            Log.v((String)"hello", (String)("file sound /mnt/sdcard/Resource/save/loaddata/" + string2));
            this.mp.setDataSource("/mnt/sdcard/Resource/save/loaddata/" + string2);
            this.mp.prepare();
            this.mp.seekTo(0);
            this.play();
            return;
        }
        catch (IllegalArgumentException var4_2) {
            var4_2.printStackTrace();
            return;
        }
        catch (IllegalStateException var3_3) {
            var3_3.printStackTrace();
            return;
        }
        catch (IOException var2_4) {
            var2_4.printStackTrace();
            return;
        }
    }

    public void playCommand(String string2) {
        if (this.mp != null) {
            this.mp.release();
        }
        this.mp = new MediaPlayer();
        try {
            Log.v((String)"test", (String)(String.valueOf((Object)DB_PATH) + string2));
            this.mp.setDataSource(String.valueOf((Object)DB_PATH) + string2);
            this.mp.prepare();
            this.play();
            Log.v((String)"test", (String)"end play");
            return;
        }
        catch (IllegalArgumentException var4_2) {
            var4_2.printStackTrace();
            return;
        }
        catch (IllegalStateException var3_3) {
            var3_3.printStackTrace();
            return;
        }
        catch (IOException var2_4) {
            var2_4.printStackTrace();
            return;
        }
    }

    public void playSound(String string2) {
        if (this.mp != null) {
            this.mp.release();
        }
        try {
            if (new File(String.valueOf((Object)Sound_PATH) + "/" + string2 + ".mp3").exists()) {
                this.mp = new MediaPlayer();
                this.mp.setDataSource(String.valueOf((Object)Sound_PATH) + "/" + string2 + ".mp3");
                this.mp.prepare();
                this.mp.seekTo(0);
                this.play();
                return;
            }
            int n = baseContext.getResources().getIdentifier(string2, "raw", baseContext.getPackageName());
            this.mp = MediaPlayer.create((Context)baseContext, (int)n);
            this.mp.start();
            return;
        }
        catch (IllegalArgumentException var6_3) {
            var6_3.printStackTrace();
            return;
        }
        catch (IllegalStateException var5_4) {
            var5_4.printStackTrace();
            return;
        }
        catch (NullPointerException var4_5) {
            var4_5.printStackTrace();
            return;
        }
        catch (SecurityException var3_6) {
            var3_6.printStackTrace();
            return;
        }
        catch (IOException var2_7) {
            var2_7.printStackTrace();
            return;
        }
    }
}

