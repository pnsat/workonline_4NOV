/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.Thread
 */
package ioio.lib.util;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.IOIOLooper;

public class BaseIOIOLooper
implements IOIOLooper {
    protected IOIO ioio_;

    @Override
    public void disconnected() {
    }

    @Override
    public void incompatible() {
    }

    @Override
    public void loop() throws ConnectionLostException, InterruptedException {
        Thread.sleep((long)20);
    }

    protected void setup() throws ConnectionLostException, InterruptedException {
    }

    @Override
    public final void setup(IOIO iOIO) throws ConnectionLostException, InterruptedException {
        this.ioio_ = iOIO;
        this.setup();
    }
}

