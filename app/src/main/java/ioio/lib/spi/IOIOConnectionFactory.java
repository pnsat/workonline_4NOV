/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package ioio.lib.spi;

import ioio.lib.api.IOIOConnection;

public interface IOIOConnectionFactory {
    public IOIOConnection createConnection();

    public Object getExtra();

    public String getType();
}

