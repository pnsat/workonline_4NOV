/*
 * Decompiled with CFR 0_110.
 */
package ioio.lib.impl;

import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.impl.AbstractResource;
import ioio.lib.impl.IOIOImpl;

abstract class AbstractPin
extends AbstractResource {
    protected final int pinNum_;

    AbstractPin(IOIOImpl iOIOImpl, int n) throws ConnectionLostException {
        super(iOIOImpl);
        this.pinNum_ = n;
    }

    @Override
    public void close() {
        AbstractPin abstractPin = this;
        synchronized (abstractPin) {
            super.close();
            this.ioio_.closePin(this.pinNum_);
            return;
        }
    }
}

