/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.InputStream
 *  java.io.OutputStream
 *  java.lang.Object
 */
package ioio.lib.api;

import ioio.lib.api.exception.ConnectionLostException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IOIOConnection {
    public void disconnect();

    public InputStream getInputStream() throws ConnectionLostException;

    public OutputStream getOutputStream() throws ConnectionLostException;

    public void waitForConnect() throws ConnectionLostException;
}

