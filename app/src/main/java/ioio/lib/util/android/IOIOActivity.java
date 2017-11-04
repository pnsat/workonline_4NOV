/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.ContextWrapper
 *  android.content.Intent
 *  android.os.Bundle
 *  java.lang.Object
 *  java.lang.RuntimeException
 *  java.lang.String
 */
package ioio.lib.util.android;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.IOIOLooperProvider;
import ioio.lib.util.android.IOIOAndroidApplicationHelper;

public abstract class IOIOActivity
extends Activity
implements IOIOLooperProvider {
    private final IOIOAndroidApplicationHelper helper_;

    public IOIOActivity() {
        this.helper_ = new IOIOAndroidApplicationHelper((ContextWrapper)this, this);
    }

    protected IOIOLooper createIOIOLooper() {
        throw new RuntimeException("Client must override one of the createIOIOLooper overloads!");
    }

    @Override
    public IOIOLooper createIOIOLooper(String string2, Object object) {
        return this.createIOIOLooper();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.helper_.create();
    }

    protected void onDestroy() {
        this.helper_.destroy();
        super.onDestroy();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if ((268435456 & intent.getFlags()) != 0) {
            this.helper_.restart();
        }
    }

    protected void onStart() {
        super.onStart();
        this.helper_.start();
    }

    protected void onStop() {
        this.helper_.stop();
        super.onStop();
    }
}

