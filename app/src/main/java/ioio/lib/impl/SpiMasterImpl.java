/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.io.IOException
 *  java.lang.Integer
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.HashMap
 *  java.util.Iterator
 *  java.util.Map
 *  java.util.Queue
 *  java.util.concurrent.ConcurrentLinkedQueue
 */
package ioio.lib.impl;

import android.util.Log;
import ioio.lib.api.SpiMaster;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.impl.AbstractResource;
import ioio.lib.impl.FlowControlledPacketSender;
import ioio.lib.impl.IOIOImpl;
import ioio.lib.impl.IOIOProtocol;
import ioio.lib.impl.IncomingState;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class SpiMasterImpl
extends AbstractResource
implements SpiMaster,
FlowControlledPacketSender.Sender,
IncomingState.DataModuleListener {
    private final int clkPinNum_;
    private final int[] indexToSsPin_;
    private final int misoPinNum_;
    private final int mosiPinNum_;
    private final FlowControlledPacketSender outgoing_;
    private final Queue<SpiResult> pendingRequests_ = new ConcurrentLinkedQueue();
    private final int spiNum_;
    private final Map<Integer, Integer> ssPinToIndex_;

    SpiMasterImpl(IOIOImpl iOIOImpl, int n, int n2, int n3, int n4, int[] arrn) throws ConnectionLostException {
        super(iOIOImpl);
        this.outgoing_ = new FlowControlledPacketSender(this);
        this.spiNum_ = n;
        this.mosiPinNum_ = n2;
        this.misoPinNum_ = n3;
        this.clkPinNum_ = n4;
        this.indexToSsPin_ = (int[])arrn.clone();
        this.ssPinToIndex_ = new HashMap(arrn.length);
        int n5 = 0;
        while (n5 < arrn.length) {
            this.ssPinToIndex_.put((Object)arrn[n5], (Object)n5);
            ++n5;
        }
        return;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void close() {
        SpiMasterImpl spiMasterImpl = this;
        synchronized (spiMasterImpl) {
            super.close();
            this.outgoing_.close();
            this.ioio_.closeSpi(this.spiNum_);
            this.ioio_.closePin(this.mosiPinNum_);
            this.ioio_.closePin(this.misoPinNum_);
            this.ioio_.closePin(this.clkPinNum_);
            int[] arrn = this.indexToSsPin_;
            int n = arrn.length;
            int n2 = 0;
            while (n2 < n) {
                int n3 = arrn[n2];
                this.ioio_.closePin(n3);
                ++n2;
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void dataReceived(byte[] arrby, int n) {
        SpiResult spiResult;
        SpiResult spiResult2 = spiResult = (SpiResult)this.pendingRequests_.remove();
        synchronized (spiResult2) {
            spiResult.ready_ = true;
            System.arraycopy((Object)arrby, (int)0, (Object)spiResult.data_, (int)0, (int)n);
            spiResult.notify();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void disconnected() {
        SpiMasterImpl spiMasterImpl = this;
        synchronized (spiMasterImpl) {
            super.disconnected();
            this.outgoing_.kill();
            Iterator iterator = this.pendingRequests_.iterator();
            boolean bl;
            while (bl = iterator.hasNext()) {
                SpiResult spiResult;
                SpiResult spiResult2 = spiResult = (SpiResult)iterator.next();
                synchronized (spiResult2) {
                    spiResult.notify();
                }
            }
            return;
        }
    }

    @Override
    public void reportAdditionalBuffer(int n) {
        this.outgoing_.readyToSend(n);
    }

    @Override
    public void send(FlowControlledPacketSender.Packet packet) {
        OutgoingPacket outgoingPacket = (OutgoingPacket)packet;
        try {
            this.ioio_.protocol_.spiMasterRequest(this.spiNum_, outgoingPacket.ssPin_, outgoingPacket.writeData_, outgoingPacket.writeSize_, outgoingPacket.totalSize_, outgoingPacket.readSize_);
            return;
        }
        catch (IOException var3_3) {
            Log.e((String)"SpiImpl", (String)"Caught exception", (Throwable)var3_3);
            return;
        }
    }

    @Override
    public void writeRead(int n, byte[] arrby, int n2, int n3, byte[] arrby2, int n4) throws ConnectionLostException, InterruptedException {
        this.writeReadAsync(n, arrby, n2, n3, arrby2, n4).waitReady();
    }

    @Override
    public void writeRead(byte[] arrby, int n, int n2, byte[] arrby2, int n3) throws ConnectionLostException, InterruptedException {
        this.writeRead(0, arrby, n, n2, arrby2, n3);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public SpiResult writeReadAsync(int n, byte[] arrby, int n2, int n3, byte[] arrby2, int n4) throws ConnectionLostException {
        this.checkState();
        SpiResult spiResult = new SpiResult(arrby2);
        OutgoingPacket outgoingPacket = new OutgoingPacket();
        outgoingPacket.writeSize_ = n2;
        outgoingPacket.writeData_ = arrby;
        outgoingPacket.readSize_ = n4;
        outgoingPacket.ssPin_ = this.indexToSsPin_[n];
        outgoingPacket.totalSize_ = n3;
        if (outgoingPacket.readSize_ > 0) {
            SpiMasterImpl spiMasterImpl = this;
            synchronized (spiMasterImpl) {
                this.pendingRequests_.add((Object)spiResult);
            }
        } else {
            spiResult.ready_ = true;
        }
        try {
            this.outgoing_.write(outgoingPacket);
            return spiResult;
        }
        catch (IOException var9_10) {
            Log.e((String)"SpiMasterImpl", (String)"Exception caught", (Throwable)var9_10);
            return spiResult;
        }
    }

    class OutgoingPacket
    implements FlowControlledPacketSender.Packet {
        int readSize_;
        int ssPin_;
        int totalSize_;
        byte[] writeData_;
        int writeSize_;

        OutgoingPacket() {
        }

        @Override
        public int getSize() {
            return 4 + this.writeSize_;
        }
    }

    public class SpiResult
    implements Result {
        final byte[] data_;
        boolean ready_;

        SpiResult(byte[] arrby) {
            this.data_ = arrby;
        }

        @Override
        public void waitReady() throws ConnectionLostException, InterruptedException {
            SpiResult spiResult = this;
            synchronized (spiResult) {
                do {
                    if (this.ready_ || SpiMasterImpl.this.state_ == AbstractResource.State.DISCONNECTED) {
                        SpiMasterImpl.this.checkState();
                        return;
                    }
                    this.wait();
                } while (true);
            }
        }
    }

}

