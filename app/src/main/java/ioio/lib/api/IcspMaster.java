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

public interface IcspMaster
extends Closeable {
    public void enterProgramming() throws ConnectionLostException;

    public void executeInstruction(int var1) throws ConnectionLostException;

    public void exitProgramming() throws ConnectionLostException;

    public void readVisi() throws ConnectionLostException, InterruptedException;

    public int waitVisiResult() throws ConnectionLostException, InterruptedException;
}

