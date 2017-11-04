/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.IOException
 *  java.lang.Exception
 */
package ioio.lib.impl;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.impl.AbstractPin;
import ioio.lib.impl.IOIOImpl;
import ioio.lib.impl.IOIOProtocol;
import java.io.IOException;

class DigitalOutputImpl
extends AbstractPin
implements DigitalOutput {
    boolean value_;

    DigitalOutputImpl(IOIOImpl iOIOImpl, int n, boolean bl) throws ConnectionLostException {
        super(iOIOImpl, n);
        this.value_ = bl;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    @Override
    public void write(boolean var1) throws ConnectionLostException {
        var5_2 = this;
        // MONITORENTER : var5_2
        this.checkState();
        var3_3 = this.value_;
        if (var1 == var3_3) ** GOTO lbl9
        try {
            this.ioio_.protocol_.setDigitalOutLevel(this.pinNum_, var1);
            this.value_ = var1;
lbl9: // 2 sources:
            // MONITOREXIT : var5_2
            return;
        }
        catch (IOException var4_4) {
            throw new ConnectionLostException((Exception)var4_4);
        }
    }
}

