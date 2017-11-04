/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.IOException
 *  java.lang.AssertionError
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.util.LinkedList
 *  java.util.Queue
 */
package ioio.lib.impl;

import ioio.lib.api.IcspMaster;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.impl.AbstractResource;
import ioio.lib.impl.IOIOImpl;
import ioio.lib.impl.IOIOProtocol;
import ioio.lib.impl.IncomingState;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class IcspMasterImpl
extends AbstractResource
implements IcspMaster,
IncomingState.DataModuleListener {
    static final /* synthetic */ boolean $assertionsDisabled;
    private Queue<Integer> resultQueue_ = new LinkedList();
    private int rxRemaining_ = 0;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl = !IcspMasterImpl.class.desiredAssertionStatus();
        $assertionsDisabled = bl;
    }

    public IcspMasterImpl(IOIOImpl iOIOImpl) throws ConnectionLostException {
        super(iOIOImpl);
    }

    private static int byteToInt(byte by) {
        return by & 255;
    }

    @Override
    public void close() {
        IcspMasterImpl icspMasterImpl = this;
        synchronized (icspMasterImpl) {
            super.close();
            this.ioio_.closeIcsp();
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
        void var6_3 = this;
        synchronized (var6_3) {
            if (!$assertionsDisabled && n != 2) {
                throw new AssertionError();
            }
            int n2 = IcspMasterImpl.byteToInt(arrby[1]) << 8 | IcspMasterImpl.byteToInt(arrby[0]);
            this.resultQueue_.add((Object)n2);
            this.notifyAll();
            return;
        }
    }

    @Override
    public void disconnected() {
        IcspMasterImpl icspMasterImpl = this;
        synchronized (icspMasterImpl) {
            super.disconnected();
            this.notifyAll();
            return;
        }
    }

    @Override
    public void enterProgramming() throws ConnectionLostException {
        IcspMasterImpl icspMasterImpl = this;
        synchronized (icspMasterImpl) {
            this.checkState();
            try {
                this.ioio_.protocol_.icspEnter();
                return;
            }
            catch (IOException var2_2) {
                throw new ConnectionLostException((Exception)var2_2);
            }
        }
    }

    @Override
    public void executeInstruction(int n) throws ConnectionLostException {
        void var4_2 = this;
        synchronized (var4_2) {
            this.checkState();
            try {
                this.ioio_.protocol_.icspSix(n);
                return;
            }
            catch (IOException var3_3) {
                throw new ConnectionLostException((Exception)var3_3);
            }
        }
    }

    @Override
    public void exitProgramming() throws ConnectionLostException {
        IcspMasterImpl icspMasterImpl = this;
        synchronized (icspMasterImpl) {
            this.checkState();
            try {
                this.ioio_.protocol_.icspExit();
                return;
            }
            catch (IOException var2_2) {
                throw new ConnectionLostException((Exception)var2_2);
            }
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    @Override
    public void readVisi() throws ConnectionLostException, InterruptedException {
        IcspMasterImpl icspMasterImpl = this;
        // MONITORENTER : icspMasterImpl
        this.checkState();
        do {
            if (this.rxRemaining_ >= 2 || this.state_ != AbstractResource.State.OPEN) {
                this.checkState();
                this.rxRemaining_ = -2 + this.rxRemaining_;
                this.ioio_.protocol_.icspRegout();
                // MONITOREXIT : icspMasterImpl
                return;
            }
            this.wait();
        } while (true);
        catch (IOException iOException) {
            throw new ConnectionLostException((Exception)iOException);
        }
    }

    @Override
    public void reportAdditionalBuffer(int n) {
        void var3_2 = this;
        synchronized (var3_2) {
            this.rxRemaining_ = n + this.rxRemaining_;
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
    public int waitVisiResult() throws ConnectionLostException, InterruptedException {
        IcspMasterImpl icspMasterImpl = this;
        synchronized (icspMasterImpl) {
            this.checkState();
            do {
                if (!this.resultQueue_.isEmpty() || this.state_ != AbstractResource.State.OPEN) {
                    this.checkState();
                    return (Integer)this.resultQueue_.remove();
                }
                this.wait();
            } while (true);
        }
    }
}

