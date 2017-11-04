/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Service
 *  android.content.ContextWrapper
 *  android.content.Intent
 *  java.lang.Object
 *  java.lang.RuntimeException
 *  java.lang.String
 */
package ioio.lib.util.android;

import android.app.Service;
import android.content.ContextWrapper;
import android.content.Intent;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.IOIOLooperProvider;
import ioio.lib.util.android.IOIOAndroidApplicationHelper;

public abstract class IOIOService
extends Service
implements IOIOLooperProvider {
    private final IOIOAndroidApplicationHelper helper_;
    private boolean started_;

    public IOIOService() {
        this.helper_ = new IOIOAndroidApplicationHelper((ContextWrapper)this, this);
        this.started_ = false;
    }

    protected IOIOLooper createIOIOLooper() {
        throw new RuntimeException("Client must override one of the createIOIOLooper overloads!");
    }

    @Override
    public IOIOLooper createIOIOLooper(String string2, Object object) {
        return this.createIOIOLooper();
    }

    public void onCreate() {
        super.onCreate();
        this.helper_.create();
    }

    public void onDestroy() {
        this.stop();
        this.helper_.destroy();
        super.onDestroy();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onStart(Intent intent, int n) {
        super.onStart(intent, n);
        if (!this.started_) {
            this.helper_.start();
            this.started_ = true;
            return;
        } else {
            if ((268435456 & intent.getFlags()) == 0) return;
            {
                this.helper_.restart();
                return;
            }
        }
    }

    protected void stop() {
        if (this.started_) {
            this.helper_.stop();
            this.started_ = false;
        }
    }
}

