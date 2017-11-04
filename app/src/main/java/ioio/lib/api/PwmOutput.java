/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package ioio.lib.api;

import ioio.lib.api.Closeable;
import ioio.lib.api.exception.ConnectionLostException;

public interface PwmOutput
extends Closeable {
    public void setDutyCycle(float var1) throws ConnectionLostException;

    public void setPulseWidth(float var1) throws ConnectionLostException;

    public void setPulseWidth(int var1) throws ConnectionLostException;
}

