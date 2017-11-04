/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Exception
 *  java.lang.String
 *  java.lang.Throwable
 */
package ioio.lib.api.exception;

public class ConnectionLostException
extends Exception {
    private static final long serialVersionUID = 7422862446246046772L;

    public ConnectionLostException() {
        super("Connection lost");
    }

    public ConnectionLostException(Exception exception) {
        super((Throwable)exception);
    }
}

