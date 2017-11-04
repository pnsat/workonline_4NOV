/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.ClassNotFoundException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.Collection
 *  java.util.Iterator
 *  java.util.LinkedList
 */
package ioio.lib.util;

import android.util.Log;
import ioio.lib.spi.IOIOConnectionBootstrap;
import ioio.lib.spi.IOIOConnectionFactory;
import ioio.lib.spi.NoRuntimeSupportException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class IOIOConnectionRegistry {
    private static final String TAG = "IOIOConnectionRegistry";
    private static Collection<IOIOConnectionBootstrap> bootstraps_ = new LinkedList();

    static {
        IOIOConnectionRegistry.addBootstraps(new String[]{"ioio.lib.impl.SocketIOIOConnectionBootstrap"});
    }

    private static void addBootstrap(String string2) {
        try {
            Class class_ = Class.forName((String)string2).asSubclass((Class)IOIOConnectionBootstrap.class);
            bootstraps_.add((Object)((IOIOConnectionBootstrap)class_.newInstance()));
            Log.d((String)"IOIOConnectionRegistry", (String)("Successfully added bootstrap class: " + string2));
            return;
        }
        catch (ClassNotFoundException var5_2) {
            Log.d((String)"IOIOConnectionRegistry", (String)("Bootstrap class not found: " + string2 + ". Not adding."));
            return;
        }
        catch (NoRuntimeSupportException var3_3) {
            Log.d((String)"IOIOConnectionRegistry", (String)("No runtime support for: " + string2 + ". Not adding."));
            return;
        }
        catch (Throwable var1_4) {
            Log.e((String)"IOIOConnectionRegistry", (String)"Exception caught while attempting to initialize accessory connection factory", (Throwable)var1_4);
            return;
        }
    }

    public static void addBootstraps(String[] arrstring) {
        int n = arrstring.length;
        int n2 = 0;
        while (n2 < n) {
            IOIOConnectionRegistry.addBootstrap(arrstring[n2]);
            ++n2;
        }
        return;
    }

    public static Collection<IOIOConnectionBootstrap> getBootstraps() {
        return bootstraps_;
    }

    public static Collection<IOIOConnectionFactory> getConnectionFactories() {
        LinkedList linkedList = new LinkedList();
        Iterator iterator = bootstraps_.iterator();
        while (iterator.hasNext()) {
            ((IOIOConnectionBootstrap)iterator.next()).getFactories((Collection<IOIOConnectionFactory>)linkedList);
        }
        return linkedList;
    }
}

