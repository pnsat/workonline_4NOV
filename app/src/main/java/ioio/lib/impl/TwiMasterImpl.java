/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.io.IOException
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.Iterator
 *  java.util.Queue
 *  java.util.concurrent.ConcurrentLinkedQueue
 */
package ioio.lib.impl;

import android.util.Log;
import ioio.lib.api.TwiMaster;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.impl.AbstractResource;
import ioio.lib.impl.FlowControlledPacketSender;
import ioio.lib.impl.IOIOImpl;
import ioio.lib.impl.IOIOProtocol;
import ioio.lib.impl.IncomingState;
import java.io.IOException;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class TwiMasterImpl
extends AbstractResource
implements TwiMaster,
FlowControlledPacketSender.Sender,
IncomingState.DataModuleListener {
    private final FlowControlledPacketSender outgoing_;
    private final Queue<TwiResult> pendingRequests_ = new ConcurrentLinkedQueue();
    private final int twiNum_;

    TwiMasterImpl(IOIOImpl iOIOImpl, int n) throws ConnectionLostException {
        super(iOIOImpl);
        this.outgoing_ = new FlowControlledPacketSender((FlowControlledPacketSender.Sender)this);
        this.twiNum_ = n;
    }

    @Override
    public void close() {
        TwiMasterImpl twiMasterImpl = this;
        synchronized (twiMasterImpl) {
            super.close();
            this.outgoing_.close();
            this.ioio_.closeTwi(this.twiNum_);
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
        TwiResult twiResult;
        boolean bl = true;
        TwiResult twiResult2 = twiResult = (TwiResult)this.pendingRequests_.remove();
        synchronized (twiResult2) {
            twiResult.ready_ = true;
            if (n == 255) {
                bl = false;
            }
            twiResult.success_ = bl;
            if (twiResult.success_) {
                System.arraycopy((Object)arrby, (int)0, (Object)twiResult.data_, (int)0, (int)n);
            }
            twiResult.notify();
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
        TwiMasterImpl twiMasterImpl = this;
        synchronized (twiMasterImpl) {
            super.disconnected();
            this.outgoing_.kill();
            Iterator iterator = this.pendingRequests_.iterator();
            boolean bl;
            while (bl = iterator.hasNext()) {
                TwiResult twiResult;
                TwiResult twiResult2 = twiResult = (TwiResult)iterator.next();
                synchronized (twiResult2) {
                    twiResult.notify();
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
            this.ioio_.protocol_.i2cWriteRead(this.twiNum_, outgoingPacket.tenBitAddr_, outgoingPacket.addr_, outgoingPacket.writeSize_, outgoingPacket.readSize_, outgoingPacket.writeData_);
            return;
        }
        catch (IOException var3_3) {
            Log.e((String)"TwiImpl", (String)"Caught exception", (Throwable)var3_3);
            return;
        }
    }

    @Override
    public boolean writeRead(int n, boolean bl, byte[] arrby, int n2, byte[] arrby2, int n3) throws ConnectionLostException, InterruptedException {
        return this.writeReadAsync(n, bl, arrby, n2, arrby2, n3).waitReady();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public Result writeReadAsync(int n, boolean bl, byte[] arrby, int n2, byte[] arrby2, int n3) throws ConnectionLostException {
        this.checkState();
        TwiResult twiResult = new TwiResult(arrby2);
        OutgoingPacket outgoingPacket = new OutgoingPacket();
        outgoingPacket.writeSize_ = n2;
        outgoingPacket.writeData_ = arrby;
        outgoingPacket.tenBitAddr_ = bl;
        outgoingPacket.readSize_ = n3;
        outgoingPacket.addr_ = n;
        TwiMasterImpl twiMasterImpl = this;
        synchronized (twiMasterImpl) {
            this.pendingRequests_.add((Object)twiResult);
            try {
                this.outgoing_.write(outgoingPacket);
            }
            catch (IOException var11_10) {
                Log.e((String)"SpiMasterImpl", (String)"Exception caught", (Throwable)var11_10);
            }
            return twiResult;
        }
    }

    class OutgoingPacket
    implements FlowControlledPacketSender.Packet {
        int addr_;
        int readSize_;
        boolean tenBitAddr_;
        byte[] writeData_;
        int writeSize_;

        OutgoingPacket() {
        }

        @Override
        public int getSize() {
            return 4 + this.writeSize_;
        }
    }

    class TwiResult
    implements Result {
        final byte[] data_;
        boolean ready_;
        boolean success_;

        public TwiResult(byte[] arrby) {
            this.ready_ = false;
            this.data_ = arrby;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public boolean waitReady() throws ConnectionLostException, InterruptedException {
            TwiResult twiResult = this;
            synchronized (twiResult) {
                do {
                    if (this.ready_ || TwiMasterImpl.this.state_ == AbstractResource.State.DISCONNECTED) {
                        TwiMasterImpl.this.checkState();
                        return this.success_;
                    }
                    this.wait();
                } while (true);
            }
        }
    }

}

