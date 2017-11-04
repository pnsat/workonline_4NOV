/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.io.IOException
 *  java.io.InputStream
 *  java.io.OutputStream
 *  java.lang.String
 */
package ioio.lib.impl;

import android.util.Log;
import ioio.lib.api.Uart;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.impl.AbstractResource;
import ioio.lib.impl.FlowControlledOutputStream;
import ioio.lib.impl.IOIOImpl;
import ioio.lib.impl.IOIOProtocol;
import ioio.lib.impl.IncomingState;
import ioio.lib.impl.QueueInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class UartImpl
extends AbstractResource
implements Uart,
FlowControlledOutputStream.Sender,
IncomingState.DataModuleListener {
    private static final int MAX_PACKET = 64;
    private final QueueInputStream incoming_;
    private final FlowControlledOutputStream outgoing_;
    private final int rxPinNum_;
    private final int txPinNum_;
    private final int uartNum_;

    public UartImpl(IOIOImpl iOIOImpl, int n, int n2, int n3) throws ConnectionLostException {
        super(iOIOImpl);
        this.outgoing_ = new FlowControlledOutputStream((FlowControlledOutputStream.Sender)this, 64);
        this.incoming_ = new QueueInputStream();
        this.uartNum_ = n3;
        this.rxPinNum_ = n2;
        this.txPinNum_ = n;
    }

    @Override
    public void close() {
        UartImpl uartImpl = this;
        synchronized (uartImpl) {
            super.close();
            this.incoming_.close();
            this.outgoing_.close();
            this.ioio_.closeUart(this.uartNum_);
            if (this.rxPinNum_ != -1) {
                this.ioio_.closePin(this.rxPinNum_);
            }
            if (this.txPinNum_ != -1) {
                this.ioio_.closePin(this.txPinNum_);
            }
            return;
        }
    }

    @Override
    public void dataReceived(byte[] arrby, int n) {
        this.incoming_.write(arrby, n);
    }

    @Override
    public void disconnected() {
        UartImpl uartImpl = this;
        synchronized (uartImpl) {
            super.disconnected();
            this.incoming_.kill();
            this.outgoing_.close();
            return;
        }
    }

    @Override
    public InputStream getInputStream() {
        return this.incoming_;
    }

    @Override
    public OutputStream getOutputStream() {
        return this.outgoing_;
    }

    @Override
    public void reportAdditionalBuffer(int n) {
        this.outgoing_.readyToSend(n);
    }

    @Override
    public void send(byte[] arrby, int n) {
        try {
            this.ioio_.protocol_.uartData(this.uartNum_, n, arrby);
            return;
        }
        catch (IOException var3_3) {
            Log.e((String)"UartImpl", (String)var3_3.getMessage());
            return;
        }
    }
}

