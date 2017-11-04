/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Collection
 */
package ioio.lib.impl;

import ioio.lib.api.IOIOConnection;
import ioio.lib.impl.SocketIOIOConnection;
import ioio.lib.spi.IOIOConnectionBootstrap;
import ioio.lib.spi.IOIOConnectionFactory;
import java.util.Collection;

public class SocketIOIOConnectionBootstrap
implements IOIOConnectionBootstrap {
    public static final int IOIO_PORT = 4545;

    @Override
    public void getFactories(Collection<IOIOConnectionFactory> collection) {
        collection.add((Object)new IOIOConnectionFactory(){
            private Integer port_;

            @Override
            public IOIOConnection createConnection() {
                return new SocketIOIOConnection(4545);
            }

            @Override
            public Object getExtra() {
                return this.port_;
            }

            @Override
            public String getType() {
                return SocketIOIOConnection.class.getCanonicalName();
            }
        });
    }

}

