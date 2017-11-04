/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.InterruptedException
 *  java.lang.Object
 */
package ioio.lib.api;

import ioio.lib.api.Closeable;
import ioio.lib.api.exception.ConnectionLostException;

public interface AnalogInput
extends Closeable {
    public int available() throws ConnectionLostException;

    public int getOverflowCount() throws ConnectionLostException;

    public float getReference();

    public float getSampleRate() throws ConnectionLostException;

    public float getVoltage() throws InterruptedException, ConnectionLostException;

    public float getVoltageBuffered() throws InterruptedException, ConnectionLostException;

    public float read() throws InterruptedException, ConnectionLostException;

    public float readBuffered() throws InterruptedException, ConnectionLostException;

    public void setBuffer(int var1) throws ConnectionLostException;
}

