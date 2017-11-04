/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContextWrapper
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Collection
 *  java.util.Iterator
 */
package ioio.lib.util.android;

import android.content.ContextWrapper;
import ioio.lib.spi.IOIOConnectionBootstrap;
import ioio.lib.util.IOIOApplicationHelper;
import ioio.lib.util.IOIOConnectionRegistry;
import ioio.lib.util.IOIOLooperProvider;
import ioio.lib.util.android.ContextWrapperDependent;
import java.util.Collection;
import java.util.Iterator;

public class IOIOAndroidApplicationHelper
extends IOIOApplicationHelper {
    private final ContextWrapper contextWrapper_;

    static {
        IOIOConnectionRegistry.addBootstraps(new String[]{"ioio.lib.android.accessory.AccessoryConnectionBootstrap", "ioio.lib.android.bluetooth.BluetoothIOIOConnectionBootstrap"});
    }

    public IOIOAndroidApplicationHelper(ContextWrapper contextWrapper, IOIOLooperProvider iOIOLooperProvider) {
        super(iOIOLooperProvider);
        this.contextWrapper_ = contextWrapper;
    }

    public void create() {
        Iterator iterator = this.bootstraps_.iterator();
        while (iterator.hasNext()) {
            IOIOConnectionBootstrap iOIOConnectionBootstrap = (IOIOConnectionBootstrap)iterator.next();
            if (!(iOIOConnectionBootstrap instanceof ContextWrapperDependent)) continue;
            ((ContextWrapperDependent)((Object)iOIOConnectionBootstrap)).onCreate(this.contextWrapper_);
        }
        return;
    }

    public void destroy() {
        Iterator iterator = this.bootstraps_.iterator();
        while (iterator.hasNext()) {
            IOIOConnectionBootstrap iOIOConnectionBootstrap = (IOIOConnectionBootstrap)iterator.next();
            if (!(iOIOConnectionBootstrap instanceof ContextWrapperDependent)) continue;
            ((ContextWrapperDependent)((Object)iOIOConnectionBootstrap)).onDestroy();
        }
        return;
    }

    public void restart() {
        Iterator iterator = this.bootstraps_.iterator();
        while (iterator.hasNext()) {
            IOIOConnectionBootstrap iOIOConnectionBootstrap = (IOIOConnectionBootstrap)iterator.next();
            if (!(iOIOConnectionBootstrap instanceof ContextWrapperDependent)) continue;
            ((ContextWrapperDependent)((Object)iOIOConnectionBootstrap)).reopen();
        }
        return;
    }

    @Override
    public void start() {
        Iterator iterator = this.bootstraps_.iterator();
        do {
            if (!iterator.hasNext()) {
                super.start();
                return;
            }
            IOIOConnectionBootstrap iOIOConnectionBootstrap = (IOIOConnectionBootstrap)iterator.next();
            if (!(iOIOConnectionBootstrap instanceof ContextWrapperDependent)) continue;
            ((ContextWrapperDependent)((Object)iOIOConnectionBootstrap)).open();
        } while (true);
    }

    @Override
    public void stop() {
        super.stop();
        Iterator iterator = this.bootstraps_.iterator();
        while (iterator.hasNext()) {
            IOIOConnectionBootstrap iOIOConnectionBootstrap = (IOIOConnectionBootstrap)iterator.next();
            if (!(iOIOConnectionBootstrap instanceof ContextWrapperDependent)) continue;
            ((ContextWrapperDependent)((Object)iOIOConnectionBootstrap)).close();
        }
        return;
    }
}

