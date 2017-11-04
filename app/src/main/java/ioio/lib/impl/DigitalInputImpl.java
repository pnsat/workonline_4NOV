/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.IOException
 *  java.lang.AssertionError
 *  java.lang.InterruptedException
 */
package ioio.lib.impl;

import ioio.lib.api.DigitalInput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.impl.AbstractPin;
import ioio.lib.impl.AbstractResource;
import ioio.lib.impl.IOIOImpl;
import ioio.lib.impl.IOIOProtocol;
import ioio.lib.impl.IncomingState;
import java.io.IOException;

class DigitalInputImpl
extends AbstractPin
implements DigitalInput,
IncomingState.InputPinListener {
    static final /* synthetic */ boolean $assertionsDisabled;
    private boolean valid_ = false;
    private boolean value_;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl = !DigitalInputImpl.class.desiredAssertionStatus();
        $assertionsDisabled = bl;
    }

    DigitalInputImpl(IOIOImpl iOIOImpl, int n) throws ConnectionLostException {
        super(iOIOImpl, n);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    @Override
    public void close() {
        DigitalInputImpl digitalInputImpl = this;
        // MONITORENTER : digitalInputImpl
        super.close();
        try {
            this.ioio_.protocol_.setChangeNotify(this.pinNum_, false);
            // MONITOREXIT : digitalInputImpl
            return;
        }
        catch (IOException var2_2) {
            return;
        }
    }

    @Override
    public void disconnected() {
        DigitalInputImpl digitalInputImpl = this;
        synchronized (digitalInputImpl) {
            super.disconnected();
            this.notifyAll();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public boolean read() throws InterruptedException, ConnectionLostException {
        DigitalInputImpl digitalInputImpl = this;
        synchronized (digitalInputImpl) {
            this.checkState();
            do {
                if (this.valid_ || this.state_ == State.DISCONNECTED) {
                    this.checkState();
                    return this.value_;
                }
                this.wait();
            } while (true);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void setValue(int n) {
        int n2 = 1;
        void var4_3 = this;
        synchronized (var4_3) {
            if (!$assertionsDisabled && n != 0 && n != n2) {
                throw new AssertionError();
            }
            if (n != n2) {
                n2 = 0;
            }
            this.value_ = n2;
            if (!this.valid_) {
                this.valid_ = true;
            }
            this.notifyAll();
            return;
        }
    }

    @Override
    public void waitForValue(boolean bl) throws InterruptedException, ConnectionLostException {
        void var3_2 = this;
        synchronized (var3_2) {
            this.checkState();
            do {
                if (this.valid_ && this.value_ == bl || this.state_ == State.DISCONNECTED) {
                    this.checkState();
                    return;
                }
                this.wait();
            } while (true);
        }
    }
}

