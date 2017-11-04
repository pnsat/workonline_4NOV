/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.AssertionError
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.IllegalStateException
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.HashSet
 *  java.util.Iterator
 *  java.util.Queue
 *  java.util.Set
 *  java.util.concurrent.ConcurrentLinkedQueue
 */
package ioio.lib.impl;

import android.util.Log;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.impl.IOIOProtocol;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

class IncomingState
implements IOIOProtocol.IncomingHandler {
    static final /* synthetic */ boolean $assertionsDisabled;
    public String bootloaderId_;
    private ConnectionState connection_;
    private final Set<DisconnectListener> disconnectListeners_;
    public String firmwareId_;
    public String hardwareId_;
    private final DataModuleState icspState_;
    private final DataModuleState[] incapStates_ = new DataModuleState[2 * Constants.INCAP_MODULES_DOUBLE.length + Constants.INCAP_MODULES_SINGLE.length];
    private final InputPinState[] intputPinStates_ = new InputPinState[49];
    private final DataModuleState[] spiStates_ = new DataModuleState[3];
    private final DataModuleState[] twiStates_ = new DataModuleState[3];
    private final DataModuleState[] uartStates_ = new DataModuleState[4];

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl = !IncomingState.class.desiredAssertionStatus();
        $assertionsDisabled = bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    public IncomingState() {
        this.icspState_ = new DataModuleState();
        this.disconnectListeners_ = new HashSet();
        this.connection_ = ConnectionState.INIT;
        int n = 0;
        do {
            if (n >= this.intputPinStates_.length) break;
            this.intputPinStates_[n] = new InputPinState();
            ++n;
        } while (true);
        int n2 = 0;
        do {
            if (n2 >= this.uartStates_.length) break;
            this.uartStates_[n2] = new DataModuleState();
            ++n2;
        } while (true);
        int n3 = 0;
        do {
            if (n3 >= this.twiStates_.length) break;
            this.twiStates_[n3] = new DataModuleState();
            ++n3;
        } while (true);
        int n4 = 0;
        do {
            if (n4 >= this.spiStates_.length) break;
            this.spiStates_[n4] = new DataModuleState();
            ++n4;
        } while (true);
        int n5 = 0;
        while (n5 < this.incapStates_.length) {
            this.incapStates_[n5] = new DataModuleState();
            ++n5;
        }
        return;
    }

    private void checkNotDisconnected() throws ConnectionLostException {
        if (this.connection_ == ConnectionState.DISCONNECTED) {
            throw new ConnectionLostException();
        }
    }

    public void addDisconnectListener(DisconnectListener disconnectListener) throws ConnectionLostException {
        void var4_2 = this;
        synchronized (var4_2) {
            super.checkNotDisconnected();
            this.disconnectListeners_.add((Object)disconnectListener);
            return;
        }
    }

    public void addIcspListener(DataModuleListener dataModuleListener) {
        this.icspState_.pushListener(dataModuleListener);
    }

    public void addIncapListener(int n, DataModuleListener dataModuleListener) {
        this.incapStates_[n].pushListener(dataModuleListener);
    }

    public void addInputPinListener(int n, InputPinListener inputPinListener) {
        this.intputPinStates_[n].pushListener(inputPinListener);
    }

    public void addSpiListener(int n, DataModuleListener dataModuleListener) {
        this.spiStates_[n].pushListener(dataModuleListener);
    }

    public void addTwiListener(int n, DataModuleListener dataModuleListener) {
        this.twiStates_[n].pushListener(dataModuleListener);
    }

    public void addUartListener(int n, DataModuleListener dataModuleListener) {
        this.uartStates_[n].pushListener(dataModuleListener);
    }

    @Override
    public void handleAnalogPinStatus(int n, boolean bl) {
        if (bl) {
            this.intputPinStates_[n].openNextListener();
            return;
        }
        this.intputPinStates_[n].closeCurrentListener();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void handleCheckInterfaceResponse(boolean bl) {
        void var4_2 = this;
        synchronized (var4_2) {
            ConnectionState connectionState = bl ? ConnectionState.CONNECTED : ConnectionState.UNSUPPORTED_IID;
            this.connection_ = connectionState;
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
    public void handleConnectionLost() {
        IncomingState incomingState = this;
        synchronized (incomingState) {
            this.connection_ = ConnectionState.DISCONNECTED;
        }
        Iterator iterator = this.disconnectListeners_.iterator();
        do {
            if (!iterator.hasNext()) {
                this.disconnectListeners_.clear();
                incomingState = this;
                synchronized (incomingState) {
                    this.notifyAll();
                    return;
                }
            }
            ((DisconnectListener)iterator.next()).disconnected();
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void handleEstablishConnection(byte[] arrby, byte[] arrby2, byte[] arrby3) {
        this.hardwareId_ = new String(arrby);
        this.bootloaderId_ = new String(arrby2);
        this.firmwareId_ = new String(arrby3);
        Log.i((String)"IncomingState", (String)("IOIO Connection established. Hardware ID: " + this.hardwareId_ + " Bootloader ID: " + this.bootloaderId_ + " Firmware ID: " + this.firmwareId_));
        void var6_4 = this;
        synchronized (var6_4) {
            this.connection_ = ConnectionState.ESTABLISHED;
            this.notifyAll();
            return;
        }
    }

    @Override
    public void handleI2cClose(int n) {
        this.twiStates_[n].closeCurrentListener();
    }

    @Override
    public void handleI2cOpen(int n) {
        this.twiStates_[n].openNextListener();
    }

    @Override
    public void handleI2cReportTxStatus(int n, int n2) {
        this.twiStates_[n].reportAdditionalBuffer(n2);
    }

    @Override
    public void handleI2cResult(int n, int n2, byte[] arrby) {
        this.twiStates_[n].dataReceived(arrby, n2);
    }

    @Override
    public void handleIcspClose() {
        this.icspState_.closeCurrentListener();
    }

    @Override
    public void handleIcspOpen() {
        this.icspState_.openNextListener();
    }

    @Override
    public void handleIcspReportRxStatus(int n) {
        this.icspState_.reportAdditionalBuffer(n);
    }

    @Override
    public void handleIcspResult(int n, byte[] arrby) {
        this.icspState_.dataReceived(arrby, n);
    }

    @Override
    public void handleIncapClose(int n) {
        this.incapStates_[n].closeCurrentListener();
    }

    @Override
    public void handleIncapOpen(int n) {
        this.incapStates_[n].openNextListener();
    }

    @Override
    public void handleIncapReport(int n, int n2, byte[] arrby) {
        this.incapStates_[n].dataReceived(arrby, n2);
    }

    @Override
    public void handleRegisterPeriodicDigitalSampling(int n, int n2) {
        if (!$assertionsDisabled) {
            throw new AssertionError();
        }
    }

    @Override
    public void handleReportAnalogInStatus(int[] arrn, int[] arrn2) {
        int n = 0;
        while (n < arrn.length) {
            this.intputPinStates_[arrn[n]].setValue(arrn2[n]);
            ++n;
        }
        return;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void handleReportDigitalInStatus(int n, boolean bl) {
        InputPinState inputPinState = this.intputPinStates_[n];
        int n2 = bl ? 1 : 0;
        inputPinState.setValue(n2);
    }

    @Override
    public void handleReportPeriodicDigitalInStatus(int n, boolean[] arrbl) {
    }

    @Override
    public void handleSetChangeNotify(int n, boolean bl) {
        if (bl) {
            this.intputPinStates_[n].openNextListener();
            return;
        }
        this.intputPinStates_[n].closeCurrentListener();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void handleSoftReset() {
        int n = 0;
        InputPinState[] arrinputPinState = this.intputPinStates_;
        int n2 = arrinputPinState.length;
        int n3 = 0;
        do {
            if (n3 >= n2) break;
            arrinputPinState[n3].closeCurrentListener();
            ++n3;
        } while (true);
        DataModuleState[] arrdataModuleState = this.uartStates_;
        int n4 = arrdataModuleState.length;
        int n5 = 0;
        do {
            if (n5 >= n4) break;
            arrdataModuleState[n5].closeCurrentListener();
            ++n5;
        } while (true);
        DataModuleState[] arrdataModuleState2 = this.twiStates_;
        int n6 = arrdataModuleState2.length;
        int n7 = 0;
        do {
            if (n7 >= n6) break;
            arrdataModuleState2[n7].closeCurrentListener();
            ++n7;
        } while (true);
        DataModuleState[] arrdataModuleState3 = this.spiStates_;
        int n8 = arrdataModuleState3.length;
        int n9 = 0;
        do {
            if (n9 >= n8) break;
            arrdataModuleState3[n9].closeCurrentListener();
            ++n9;
        } while (true);
        DataModuleState[] arrdataModuleState4 = this.incapStates_;
        int n10 = arrdataModuleState4.length;
        do {
            if (n >= n10) {
                this.icspState_.closeCurrentListener();
                return;
            }
            arrdataModuleState4[n].closeCurrentListener();
            ++n;
        } while (true);
    }

    @Override
    public void handleSpiClose(int n) {
        this.spiStates_[n].closeCurrentListener();
    }

    @Override
    public void handleSpiData(int n, int n2, byte[] arrby, int n3) {
        this.spiStates_[n].dataReceived(arrby, n3);
    }

    @Override
    public void handleSpiOpen(int n) {
        this.spiStates_[n].openNextListener();
    }

    @Override
    public void handleSpiReportTxStatus(int n, int n2) {
        this.spiStates_[n].reportAdditionalBuffer(n2);
    }

    @Override
    public void handleUartClose(int n) {
        this.uartStates_[n].closeCurrentListener();
    }

    @Override
    public void handleUartData(int n, int n2, byte[] arrby) {
        this.uartStates_[n].dataReceived(arrby, n2);
    }

    @Override
    public void handleUartOpen(int n) {
        this.uartStates_[n].openNextListener();
    }

    @Override
    public void handleUartReportTxStatus(int n, int n2) {
        this.uartStates_[n].reportAdditionalBuffer(n2);
    }

    public void removeDisconnectListener(DisconnectListener disconnectListener) {
        void var4_2 = this;
        synchronized (var4_2) {
            if (this.connection_ != ConnectionState.DISCONNECTED) {
                this.disconnectListeners_.remove((Object)disconnectListener);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void waitConnectionEstablished() throws InterruptedException, ConnectionLostException {
        IncomingState incomingState = this;
        synchronized (incomingState) {
            do {
                if (this.connection_ != ConnectionState.INIT) {
                    if (this.connection_ != ConnectionState.DISCONNECTED) break;
                    throw new ConnectionLostException();
                }
                this.wait();
            } while (true);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void waitDisconnect() throws InterruptedException {
        IncomingState incomingState = this;
        synchronized (incomingState) {
            ConnectionState connectionState;
            ConnectionState connectionState2;
            while ((connectionState2 = this.connection_) != (connectionState = ConnectionState.DISCONNECTED)) {
                this.wait();
            }
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public boolean waitForInterfaceSupport() throws InterruptedException, ConnectionLostException {
        var5_1 = this;
        // MONITORENTER : var5_1
        if (this.connection_ != ConnectionState.INIT) ** GOTO lbl6
        throw new IllegalStateException("Have to connect before waiting for interface support");
lbl-1000: // 1 sources:
        {
            this.wait();
lbl6: // 2 sources:
            ** while (this.connection_ == ConnectionState.ESTABLISHED)
        }
lbl7: // 1 sources:
        if (this.connection_ == ConnectionState.DISCONNECTED) {
            throw new ConnectionLostException();
        }
        var2_2 = this.connection_;
        var3_3 = ConnectionState.CONNECTED;
        if (var2_2 != var3_3) return false;
        var4_4 = true;
        // MONITOREXIT : var5_1
        return var4_4;
    }

    static final class ConnectionState
    extends Enum<ConnectionState> {
        public static final /* enum */ ConnectionState CONNECTED;
        public static final /* enum */ ConnectionState DISCONNECTED;
        private static final /* synthetic */ ConnectionState[] ENUM$VALUES;
        public static final /* enum */ ConnectionState ESTABLISHED;
        public static final /* enum */ ConnectionState INIT;
        public static final /* enum */ ConnectionState UNSUPPORTED_IID;

        static {
            INIT = new ConnectionState("INIT", 0);
            ESTABLISHED = new ConnectionState("ESTABLISHED", 1);
            CONNECTED = new ConnectionState("CONNECTED", 2);
            DISCONNECTED = new ConnectionState("DISCONNECTED", 3);
            UNSUPPORTED_IID = new ConnectionState("UNSUPPORTED_IID", 4);
            ConnectionState[] arrconnectionState = new ConnectionState[]{INIT, ESTABLISHED, CONNECTED, DISCONNECTED, UNSUPPORTED_IID};
            ENUM$VALUES = arrconnectionState;
        }

        private ConnectionState(String string3, int n2) {
            super(string2, n);
        }

        public static ConnectionState valueOf(String string2) {
            return (ConnectionState)Enum.valueOf((Class)ConnectionState.class, (String)string2);
        }

        public static ConnectionState[] values() {
            ConnectionState[] arrconnectionState = ENUM$VALUES;
            int n = arrconnectionState.length;
            ConnectionState[] arrconnectionState2 = new ConnectionState[n];
            System.arraycopy((Object)arrconnectionState, (int)0, (Object)arrconnectionState2, (int)0, (int)n);
            return arrconnectionState2;
        }
    }

    static interface DataModuleListener {
        public void dataReceived(byte[] var1, int var2);

        public void reportAdditionalBuffer(int var1);
    }

    class DataModuleState {
        static final /* synthetic */ boolean $assertionsDisabled;
        private boolean currentOpen_;
        private Queue<DataModuleListener> listeners_;

        /*
         * Enabled aggressive block sorting
         */
        static {
            boolean bl = !IncomingState.class.desiredAssertionStatus();
            $assertionsDisabled = bl;
        }

        DataModuleState() {
            this.listeners_ = new ConcurrentLinkedQueue();
            this.currentOpen_ = false;
        }

        void closeCurrentListener() {
            if (this.currentOpen_) {
                this.currentOpen_ = false;
                this.listeners_.remove();
            }
        }

        void dataReceived(byte[] arrby, int n) {
            if (!$assertionsDisabled && !this.currentOpen_) {
                throw new AssertionError();
            }
            ((DataModuleListener)this.listeners_.peek()).dataReceived(arrby, n);
        }

        void openNextListener() {
            if (!$assertionsDisabled && this.listeners_.isEmpty()) {
                throw new AssertionError();
            }
            if (!this.currentOpen_) {
                this.currentOpen_ = true;
            }
        }

        void pushListener(DataModuleListener dataModuleListener) {
            this.listeners_.add((Object)dataModuleListener);
        }

        public void reportAdditionalBuffer(int n) {
            if (!$assertionsDisabled && !this.currentOpen_) {
                throw new AssertionError();
            }
            ((DataModuleListener)this.listeners_.peek()).reportAdditionalBuffer(n);
        }
    }

    static interface DisconnectListener {
        public void disconnected();
    }

    static interface InputPinListener {
        public void setValue(int var1);
    }

    class InputPinState {
        static final /* synthetic */ boolean $assertionsDisabled;
        private boolean currentOpen_;
        private Queue<InputPinListener> listeners_;

        /*
         * Enabled aggressive block sorting
         */
        static {
            boolean bl = !IncomingState.class.desiredAssertionStatus();
            $assertionsDisabled = bl;
        }

        InputPinState() {
            this.listeners_ = new ConcurrentLinkedQueue();
            this.currentOpen_ = false;
        }

        void closeCurrentListener() {
            if (this.currentOpen_) {
                this.currentOpen_ = false;
                this.listeners_.remove();
            }
        }

        void openNextListener() {
            if (!$assertionsDisabled && this.listeners_.isEmpty()) {
                throw new AssertionError();
            }
            if (!this.currentOpen_) {
                this.currentOpen_ = true;
            }
        }

        void pushListener(InputPinListener inputPinListener) {
            this.listeners_.add((Object)inputPinListener);
        }

        void setValue(int n) {
            if (!$assertionsDisabled && !this.currentOpen_) {
                throw new AssertionError();
            }
            ((InputPinListener)this.listeners_.peek()).setValue(n);
        }
    }

}

