/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.InterruptedException
 *  java.lang.Object
 */
package ioio.lib.util;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;

public interface IOIOLooper {
    public void disconnected();

    public void incompatible();

    public void loop() throws ConnectionLostException, InterruptedException;

    public void setup(IOIO var1) throws ConnectionLostException, InterruptedException;
}

