/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.IOException
 *  java.lang.AssertionError
 *  java.lang.Exception
 */
package ioio.lib.impl;

import ioio.lib.api.PwmOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.impl.AbstractResource;
import ioio.lib.impl.IOIOImpl;
import ioio.lib.impl.IOIOProtocol;
import java.io.IOException;

class PwmImpl
extends AbstractResource
implements PwmOutput {
    static final /* synthetic */ boolean $assertionsDisabled;
    private final float baseUs_;
    private final int period_;
    private final int pinNum_;
    private final int pwmNum_;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl = !PwmImpl.class.desiredAssertionStatus();
        $assertionsDisabled = bl;
    }

    public PwmImpl(IOIOImpl iOIOImpl, int n, int n2, int n3, float f) throws ConnectionLostException {
        super(iOIOImpl);
        this.pwmNum_ = n2;
        this.pinNum_ = n;
        this.baseUs_ = f;
        this.period_ = n3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void setPulseWidthInClocks(float f) throws ConnectionLostException {
        void var8_2 = this;
        synchronized (var8_2) {
            int n;
            int n2;
            float f2;
            this.checkState();
            if (f > (float)this.period_) {
                int n3 = this.period_;
                f = n3;
            }
            if ((f2 = f - 1.0f) < 1.0f) {
                n = 0;
                n2 = 0;
            } else {
                n = (int)f2;
                n2 = 3 & 4 * (int)f2;
            }
            try {
                this.ioio_.protocol_.setPwmDutyCycle(this.pwmNum_, n, n2);
                return;
            }
            catch (IOException var6_7) {
                throw new ConnectionLostException((Exception)var6_7);
            }
        }
    }

    @Override
    public void close() {
        PwmImpl pwmImpl = this;
        synchronized (pwmImpl) {
            super.close();
            this.ioio_.closePwm(this.pwmNum_);
            this.ioio_.closePin(this.pinNum_);
            return;
        }
    }

    @Override
    public void setDutyCycle(float f) throws ConnectionLostException {
        if (!($assertionsDisabled || f <= 1.0f && f >= 0.0f)) {
            throw new AssertionError();
        }
        super.setPulseWidthInClocks(f * (float)this.period_);
    }

    @Override
    public void setPulseWidth(float f) throws ConnectionLostException {
        if (!$assertionsDisabled && f < 0.0f) {
            throw new AssertionError();
        }
        super.setPulseWidthInClocks(f / this.baseUs_);
    }

    @Override
    public void setPulseWidth(int n) throws ConnectionLostException {
        this.setPulseWidth((float)n);
    }
}

