/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.IllegalStateException
 *  java.lang.InterruptedException
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.util.LinkedList
 *  java.util.Queue
 */
package ioio.lib.impl;

import ioio.lib.api.PulseInput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.impl.AbstractPin;
import ioio.lib.impl.AbstractResource;
import ioio.lib.impl.IOIOImpl;
import ioio.lib.impl.IncomingState;
import java.util.LinkedList;
import java.util.Queue;

class IncapImpl
extends AbstractPin
implements PulseInput,
IncomingState.DataModuleListener {
    private static final int MAX_QUEUE_LEN = 32;
    private final boolean doublePrecision_;
    private final int incapNum_;
    private long lastDuration_;
    private final PulseMode mode_;
    private Queue<Long> pulseQueue_ = new LinkedList();
    private final float timeBase_;
    private boolean valid_ = false;

    public IncapImpl(IOIOImpl iOIOImpl, PulseMode pulseMode, int n, int n2, int n3, int n4, boolean bl) throws ConnectionLostException {
        super(iOIOImpl, n2);
        this.mode_ = pulseMode;
        this.incapNum_ = n;
        this.timeBase_ = 1.0f / (float)(n4 * n3);
        this.doublePrecision_ = bl;
    }

    private static long ByteArrayToLong(byte[] arrby, int n) {
        long l = 0;
        int n2 = n;
        do {
            int n3 = n2 - 1;
            if (n2 <= 0) {
                if (l == 0) {
                    l = 1 << n * 8;
                }
                return l;
            }
            l = l << 8 | (long)(255 & arrby[n3]);
            n2 = n3;
        } while (true);
    }

    @Override
    public void close() {
        IncapImpl incapImpl = this;
        synchronized (incapImpl) {
            this.ioio_.closeIncap(this.incapNum_, this.doublePrecision_);
            super.close();
            return;
        }
    }

    @Override
    public void dataReceived(byte[] arrby, int n) {
        void var6_3 = this;
        synchronized (var6_3) {
            this.lastDuration_ = IncapImpl.ByteArrayToLong(arrby, n);
            if (this.pulseQueue_.size() == 32) {
                this.pulseQueue_.remove();
            }
            this.pulseQueue_.add((Object)this.lastDuration_);
            this.valid_ = true;
            this.notifyAll();
            return;
        }
    }

    @Override
    public void disconnected() {
        IncapImpl incapImpl = this;
        synchronized (incapImpl) {
            this.notifyAll();
            super.disconnected();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public float getDuration() throws InterruptedException, ConnectionLostException {
        IncapImpl incapImpl = this;
        synchronized (incapImpl) {
            this.checkState();
            do {
                if (this.valid_) {
                    float f = this.timeBase_;
                    long l = this.lastDuration_;
                    return f * (float)l;
                }
                this.wait();
                this.checkState();
            } while (true);
        }
    }

    @Override
    public float getFrequency() throws InterruptedException, ConnectionLostException {
        if (this.mode_ != PulseMode.FREQ && this.mode_ != PulseMode.FREQ_SCALE_4 && this.mode_ != PulseMode.FREQ_SCALE_16) {
            throw new IllegalStateException("Cannot query frequency when module was not opened in frequency mode.");
        }
        return 1.0f / this.getDuration();
    }

    /*
     * Enabled aggressive block sorting
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    @Override
    public void reportAdditionalBuffer(int n) {
        void var2_2 = this;
        // MONITORENTER : var2_2
        // MONITOREXIT : var2_2
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public float waitPulseGetDuration() throws InterruptedException, ConnectionLostException {
        IncapImpl incapImpl = this;
        synchronized (incapImpl) {
            if (this.mode_ != PulseMode.POSITIVE && this.mode_ != PulseMode.NEGATIVE) {
                throw new IllegalStateException("Cannot wait for pulse when module was not opened in pulse mode.");
            }
            this.checkState();
            do {
                if (!this.pulseQueue_.isEmpty() || this.state_ != AbstractResource.State.OPEN) {
                    this.checkState();
                    float f = this.timeBase_;
                    long l = (Long)this.pulseQueue_.remove();
                    return f * (float)l;
                }
                this.wait();
            } while (true);
        }
    }
}

