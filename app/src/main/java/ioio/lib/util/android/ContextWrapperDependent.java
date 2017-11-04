/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContextWrapper
 *  java.lang.Object
 */
package ioio.lib.util.android;

import android.content.ContextWrapper;

public interface ContextWrapperDependent {
    public void close();

    public void onCreate(ContextWrapper var1);

    public void onDestroy();

    public void open();

    public void reopen();
}

