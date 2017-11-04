/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Collection
 *  java.util.Iterator
 *  java.util.NoSuchElementException
 */
package ioio.lib.api;

import android.util.Log;
import ioio.lib.api.IOIO;
import ioio.lib.api.IOIOConnection;
import ioio.lib.impl.IOIOImpl;
import ioio.lib.spi.IOIOConnectionFactory;
import ioio.lib.util.IOIOConnectionRegistry;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IOIOFactory {
    private static final String TAG = "IOIOFactory";

    public static IOIO create() {
        Collection<IOIOConnectionFactory> collection = IOIOConnectionRegistry.getConnectionFactories();
        try {
            IOIO iOIO = IOIOFactory.create(((IOIOConnectionFactory)collection.iterator().next()).createConnection());
            return iOIO;
        }
        catch (NoSuchElementException var1_2) {
            Log.e((String)"IOIOFactory", (String)"No connection is available. This shouldn't happen.");
            throw var1_2;
        }
    }

    public static IOIO create(IOIOConnection iOIOConnection) {
        return new IOIOImpl(iOIOConnection);
    }
}

