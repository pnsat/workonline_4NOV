/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.IOException
 *  java.lang.AssertionError
 *  java.lang.IllegalStateException
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.String
 */
package ioio.lib.impl;

import ioio.lib.api.AnalogInput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.impl.AbstractPin;
import ioio.lib.impl.AbstractResource;
import ioio.lib.impl.IOIOImpl;
import ioio.lib.impl.IOIOProtocol;
import ioio.lib.impl.IncomingState;
import java.io.IOException;

class AnalogInputImpl
extends AbstractPin
implements AnalogInput,
IncomingState.InputPinListener {
    static final /* synthetic */ boolean $assertionsDisabled;
    int bufferCapacity_;
    int bufferOverflowCount_ = 0;
    int bufferReadCursor_;
    int bufferSize_;
    int bufferWriteCursor_;
    short[] buffer_;
    private boolean valid_ = false;
    private int value_;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl = !AnalogInputImpl.class.desiredAssertionStatus();
        $assertionsDisabled = bl;
    }

    AnalogInputImpl(IOIOImpl iOIOImpl, int n) throws ConnectionLostException {
        super(iOIOImpl, n);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private short bufferPull() throws InterruptedException, ConnectionLostException {
        var5_1 = this;
        // MONITORENTER : var5_1
        if (this.buffer_ != null) ** GOTO lbl6
        throw new IllegalStateException("Need to call setBuffer() before reading buffered values.");
lbl-1000: // 1 sources:
        {
            this.wait();
lbl6: // 2 sources:
            ** while (this.bufferSize_ == 0 && this.state_ == AbstractResource.State.OPEN)
        }
lbl7: // 1 sources:
        this.checkState();
        var2_2 = this.buffer_;
        var3_3 = this.bufferReadCursor_;
        this.bufferReadCursor_ = var3_3 + 1;
        var4_4 = var2_2[var3_3];
        if (this.bufferReadCursor_ == this.bufferCapacity_) {
            this.bufferReadCursor_ = 0;
        }
        this.bufferSize_ = -1 + this.bufferSize_;
        // MONITOREXIT : var5_1
        return var4_4;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void bufferPush(short s) {
        if (this.buffer_ == null) {
            return;
        }
        if (this.bufferSize_ == this.bufferCapacity_) {
            this.bufferOverflowCount_ = 1 + this.bufferOverflowCount_;
        } else {
            this.bufferSize_ = 1 + this.bufferSize_;
        }
        short[] arrs = this.buffer_;
        int n = this.bufferWriteCursor_;
        this.bufferWriteCursor_ = n + 1;
        arrs[n] = s;
        if (this.bufferWriteCursor_ == this.bufferCapacity_) {
            this.bufferWriteCursor_ = 0;
        }
        this.notifyAll();
    }

    @Override
    public int available() throws ConnectionLostException {
        return this.bufferSize_;
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
        AnalogInputImpl analogInputImpl = this;
        // MONITORENTER : analogInputImpl
        super.close();
        try {
            this.ioio_.protocol_.setAnalogInSampling(this.pinNum_, false);
            // MONITOREXIT : analogInputImpl
            return;
        }
        catch (IOException var2_2) {
            return;
        }
    }

    @Override
    public void disconnected() {
        AnalogInputImpl analogInputImpl = this;
        synchronized (analogInputImpl) {
            super.disconnected();
            this.notifyAll();
            return;
        }
    }

    @Override
    public int getOverflowCount() throws ConnectionLostException {
        return this.bufferOverflowCount_;
    }

    @Override
    public float getReference() {
        return 3.3f;
    }

    @Override
    public float getSampleRate() throws ConnectionLostException {
        return 1000.0f;
    }

    @Override
    public float getVoltage() throws InterruptedException, ConnectionLostException {
        return this.read() * this.getReference();
    }

    @Override
    public float getVoltageBuffered() throws InterruptedException, ConnectionLostException {
        return this.readBuffered() * this.getReference();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public float read() throws InterruptedException, ConnectionLostException {
        AnalogInputImpl analogInputImpl = this;
        synchronized (analogInputImpl) {
            this.checkState();
            do {
                if (this.valid_ || this.state_ != AbstractResource.State.OPEN) {
                    this.checkState();
                    int n = this.value_;
                    return (float)n / 1023.0f;
                }
                this.wait();
            } while (true);
        }
    }

    @Override
    public float readBuffered() throws InterruptedException, ConnectionLostException {
        this.checkState();
        return (float)this.bufferPull() / 1023.0f;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void setBuffer(int n) throws ConnectionLostException {
        void var3_2 = this;
        synchronized (var3_2) {
            this.checkState();
            this.buffer_ = n <= 0 ? (Object)null : new short[n];
            this.bufferCapacity_ = n;
            this.bufferSize_ = 0;
            this.bufferReadCursor_ = 0;
            this.bufferWriteCursor_ = 0;
            this.bufferOverflowCount_ = 0;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void setValue(int n) {
        void var3_2 = this;
        synchronized (var3_2) {
            if (!($assertionsDisabled || n >= 0 && n < 1024)) {
                throw new AssertionError();
            }
            this.value_ = n;
            if (!this.valid_) {
                this.valid_ = true;
                this.notifyAll();
            }
            super.bufferPush((short)n);
            return;
        }
    }
}

