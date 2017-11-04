/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.io.IOException
 *  java.io.InputStream
 *  java.io.OutputStream
 *  java.lang.AssertionError
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.IllegalArgumentException
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.util.HashSet
 *  java.util.Iterator
 *  java.util.Set
 */
package ioio.lib.impl;

import android.util.Log;
import ioio.lib.api.DigitalInput;
import ioio.lib.api.DigitalOutput;
import ioio.lib.api.SpiMaster;
import ioio.lib.api.TwiMaster;
import ioio.lib.api.Uart;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class IOIOProtocol {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int CHECK_INTERFACE = 2;
    static final int CHECK_INTERFACE_RESPONSE = 2;
    static final int ESTABLISH_CONNECTION = 0;
    static final int HARD_RESET = 0;
    static final int I2C_CONFIGURE_MASTER = 19;
    static final int I2C_REPORT_TX_STATUS = 21;
    static final int I2C_RESULT = 20;
    static final int I2C_STATUS = 19;
    static final int I2C_WRITE_READ = 20;
    static final int ICSP_CONFIG = 26;
    static final int ICSP_PROG_ENTER = 24;
    static final int ICSP_PROG_EXIT = 25;
    static final int ICSP_REGOUT = 23;
    static final int ICSP_REPORT_RX_STATUS = 22;
    static final int ICSP_RESULT = 23;
    static final int ICSP_SIX = 22;
    static final int INCAP_CONFIGURE = 27;
    static final int INCAP_REPORT = 28;
    static final int INCAP_STATUS = 27;
    static final int REGISTER_PERIODIC_DIGITAL_SAMPLING = 7;
    static final int REPORT_ANALOG_IN_FORMAT = 12;
    static final int REPORT_ANALOG_IN_STATUS = 11;
    static final int REPORT_DIGITAL_IN_STATUS = 4;
    static final int REPORT_PERIODIC_DIGITAL_IN_STATUS = 5;
    static final int[] SCALE_DIV;
    static final int SET_ANALOG_IN_SAMPLING = 12;
    static final int SET_CHANGE_NOTIFY = 6;
    static final int SET_DIGITAL_OUT_LEVEL = 4;
    static final int SET_PIN_ANALOG_IN = 11;
    static final int SET_PIN_DIGITAL_IN = 5;
    static final int SET_PIN_DIGITAL_OUT = 3;
    static final int SET_PIN_INCAP = 28;
    static final int SET_PIN_PWM = 8;
    static final int SET_PIN_SPI = 18;
    static final int SET_PIN_UART = 15;
    static final int SET_PWM_DUTY_CYCLE = 9;
    static final int SET_PWM_PERIOD = 10;
    static final int SOFT_CLOSE = 29;
    static final int SOFT_RESET = 1;
    static final int SPI_CONFIGURE_MASTER = 16;
    static final int SPI_DATA = 17;
    static final int SPI_MASTER_REQUEST = 17;
    static final int SPI_REPORT_TX_STATUS = 18;
    static final int SPI_STATUS = 16;
    private static final String TAG = "IOIOProtocol";
    static final int UART_CONFIG = 13;
    static final int UART_DATA = 14;
    static final int UART_REPORT_TX_STATUS = 15;
    static final int UART_STATUS = 13;
    private int batchCounter_ = 0;
    private final IncomingHandler handler_;
    private final InputStream in_;
    private final OutputStream out_;
    private byte[] outbuf_ = new byte[256];
    private int pos_ = 0;
    private final IncomingThread thread_;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl = !IOIOProtocol.class.desiredAssertionStatus();
        $assertionsDisabled = bl;
        SCALE_DIV = new int[]{31, 30, 29, 28, 27, 26, 23, 22, 21, 20, 19, 18, 15, 14, 13, 12, 11, 10, 7, 6, 5, 4, 3, 2, 1};
    }

    public IOIOProtocol(InputStream inputStream, OutputStream outputStream, IncomingHandler incomingHandler) {
        this.thread_ = (IOIOProtocol)this.new IncomingThread();
        this.in_ = inputStream;
        this.out_ = outputStream;
        this.handler_ = incomingHandler;
        this.thread_.start();
    }

    static /* synthetic */ IncomingHandler access$1(IOIOProtocol iOIOProtocol) {
        return iOIOProtocol.handler_;
    }

    private void flush() throws IOException {
        try {
            this.out_.write(this.outbuf_, 0, this.pos_);
            return;
        }
        finally {
            this.pos_ = 0;
        }
    }

    private void writeByte(int n) throws IOException {
        if (!($assertionsDisabled || n >= 0 && n < 256)) {
            throw new AssertionError();
        }
        if (this.pos_ == this.outbuf_.length) {
            super.flush();
        }
        byte[] arrby = this.outbuf_;
        int n2 = this.pos_;
        this.pos_ = n2 + 1;
        arrby[n2] = (byte)n;
    }

    private void writeThreeBytes(int n) throws IOException {
        super.writeByte(n & 255);
        super.writeByte(255 & n >> 8);
        super.writeByte(255 & n >> 16);
    }

    private void writeTwoBytes(int n) throws IOException {
        super.writeByte(n & 255);
        super.writeByte(n >> 8);
    }

    public void beginBatch() {
        IOIOProtocol iOIOProtocol = this;
        synchronized (iOIOProtocol) {
            this.batchCounter_ = 1 + this.batchCounter_;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void checkInterface(byte[] arrby) throws IOException {
        void var4_2 = this;
        synchronized (var4_2) {
            if (arrby.length != 8) {
                throw new IllegalArgumentException("interface ID must be exactly 8 bytes long");
            }
            this.beginBatch();
            super.writeByte(2);
            int n = 0;
            do {
                if (n >= 8) {
                    this.endBatch();
                    return;
                }
                super.writeByte(arrby[n]);
                ++n;
            } while (true);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void endBatch() throws IOException {
        IOIOProtocol iOIOProtocol = this;
        synchronized (iOIOProtocol) {
            int n;
            this.batchCounter_ = n = -1 + this.batchCounter_;
            if (n == 0) {
                this.flush();
            }
            return;
        }
    }

    public void hardReset() throws IOException {
        IOIOProtocol iOIOProtocol = this;
        synchronized (iOIOProtocol) {
            this.beginBatch();
            this.writeByte(0);
            this.writeByte(73);
            this.writeByte(79);
            this.writeByte(73);
            this.writeByte(79);
            this.endBatch();
            return;
        }
    }

    public void i2cClose(int n) throws IOException {
        void var3_2 = this;
        synchronized (var3_2) {
            this.beginBatch();
            super.writeByte(19);
            super.writeByte(n);
            this.endBatch();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void i2cConfigureMaster(int n, TwiMaster.Rate rate, boolean bl) throws IOException {
        void var8_4 = this;
        synchronized (var8_4) {
            TwiMaster.Rate rate2;
            int n2 = rate == TwiMaster.Rate.RATE_1MHz ? 3 : (rate == (rate2 = TwiMaster.Rate.RATE_400KHz) ? 2 : 1);
            this.beginBatch();
            super.writeByte(19);
            int n3 = bl ? 128 : 0;
            super.writeByte(n | (n3 | n2 << 5));
            this.endBatch();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void i2cWriteRead(int n, boolean bl, int n2, int n3, int n4, byte[] arrby) throws IOException {
        IOIOProtocol iOIOProtocol = this;
        synchronized (iOIOProtocol) {
            this.beginBatch();
            this.writeByte(20);
            int n5 = n2 >> 8 << 6;
            int n6 = bl ? 32 : 0;
            this.writeByte(n | (n6 | n5));
            this.writeByte(n2 & 255);
            this.writeByte(n3);
            this.writeByte(n4);
            int n7 = 0;
            do {
                if (n7 >= n3) {
                    this.endBatch();
                    return;
                }
                this.writeByte(255 & arrby[n7]);
                ++n7;
            } while (true);
        }
    }

    public void icspClose() throws IOException {
        this.beginBatch();
        this.writeByte(26);
        this.writeByte(0);
        this.endBatch();
    }

    public void icspEnter() throws IOException {
        this.beginBatch();
        this.writeByte(24);
        this.endBatch();
    }

    public void icspExit() throws IOException {
        this.beginBatch();
        this.writeByte(25);
        this.endBatch();
    }

    public void icspOpen() throws IOException {
        this.beginBatch();
        this.writeByte(26);
        this.writeByte(1);
        this.endBatch();
    }

    public void icspRegout() throws IOException {
        this.beginBatch();
        this.writeByte(23);
        this.endBatch();
    }

    public void icspSix(int n) throws IOException {
        this.beginBatch();
        super.writeByte(22);
        super.writeThreeBytes(n);
        this.endBatch();
    }

    public void incapClose(int n) throws IOException {
        void var3_2 = this;
        synchronized (var3_2) {
            this.beginBatch();
            super.writeByte(27);
            super.writeByte(n);
            super.writeByte(0);
            this.endBatch();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void incapConfigure(int n, boolean bl, int n2, int n3) throws IOException {
        void var7_5 = this;
        synchronized (var7_5) {
            this.beginBatch();
            super.writeByte(27);
            super.writeByte(n);
            int n4 = bl ? 128 : 0;
            super.writeByte(n3 | (n4 | n2 << 3));
            this.endBatch();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public void registerPeriodicDigitalSampling(int n, int n2) throws IOException {
        void var3_3 = this;
        // MONITORENTER : var3_3
        // MONITOREXIT : var3_3
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setAnalogInSampling(int n, boolean bl) throws IOException {
        void var5_3 = this;
        synchronized (var5_3) {
            this.beginBatch();
            super.writeByte(12);
            int n2 = bl ? 128 : 0;
            super.writeByte(n2 | n & 63);
            this.endBatch();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setChangeNotify(int n, boolean bl) throws IOException {
        void var6_3 = this;
        synchronized (var6_3) {
            this.beginBatch();
            super.writeByte(6);
            int n2 = n << 2;
            int n3 = bl ? 1 : 0;
            super.writeByte(n3 | n2);
            this.endBatch();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setDigitalOutLevel(int n, boolean bl) throws IOException {
        void var6_3 = this;
        synchronized (var6_3) {
            this.beginBatch();
            super.writeByte(4);
            int n2 = n << 2;
            int n3 = bl ? 1 : 0;
            super.writeByte(n3 | n2);
            this.endBatch();
            return;
        }
    }

    public void setPinAnalogIn(int n) throws IOException {
        void var3_2 = this;
        synchronized (var3_2) {
            this.beginBatch();
            super.writeByte(11);
            super.writeByte(n);
            this.endBatch();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setPinDigitalIn(int n, DigitalInput.Spec.Mode mode) throws IOException {
        void var6_3 = this;
        synchronized (var6_3) {
            int n2;
            if (mode == DigitalInput.Spec.Mode.PULL_UP) {
                n2 = 1;
            } else {
                DigitalInput.Spec.Mode mode2 = DigitalInput.Spec.Mode.PULL_DOWN;
                n2 = 0;
                if (mode == mode2) {
                    n2 = 2;
                }
            }
            this.beginBatch();
            super.writeByte(5);
            super.writeByte(n2 | n << 2);
            this.endBatch();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setPinDigitalOut(int n, boolean bl, DigitalOutput.Spec.Mode mode) throws IOException {
        void var9_4 = this;
        synchronized (var9_4) {
            this.beginBatch();
            super.writeByte(3);
            int n2 = n << 2;
            int n3 = mode == DigitalOutput.Spec.Mode.OPEN_DRAIN ? 1 : 0;
            int n4 = n3 | n2;
            int n5 = 0;
            if (bl) {
                n5 = 2;
            }
            super.writeByte(n5 | n4);
            this.endBatch();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setPinIncap(int n, int n2, boolean bl) throws IOException {
        void var6_4 = this;
        synchronized (var6_4) {
            this.beginBatch();
            super.writeByte(28);
            super.writeByte(n);
            int n3 = bl ? 128 : 0;
            super.writeByte(n3 | n2);
            this.endBatch();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setPinPwm(int n, int n2, boolean bl) throws IOException {
        void var6_4 = this;
        synchronized (var6_4) {
            this.beginBatch();
            super.writeByte(8);
            super.writeByte(n & 63);
            int n3 = bl ? 128 : 0;
            super.writeByte(n3 | n2 & 15);
            this.endBatch();
            return;
        }
    }

    public void setPinSpi(int n, int n2, boolean bl, int n3) throws IOException {
        void var6_5 = this;
        synchronized (var6_5) {
            this.beginBatch();
            super.writeByte(18);
            super.writeByte(n);
            super.writeByte(n3 | (16 | n2 << 2));
            this.endBatch();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setPinUart(int n, int n2, boolean bl, boolean bl2) throws IOException {
        void var8_5 = this;
        synchronized (var8_5) {
            this.beginBatch();
            super.writeByte(15);
            super.writeByte(n);
            int n3 = bl2 ? 128 : 0;
            int n4 = 0;
            if (bl) {
                n4 = 64;
            }
            super.writeByte(n2 | (n4 | n3));
            this.endBatch();
            return;
        }
    }

    public void setPwmDutyCycle(int n, int n2, int n3) throws IOException {
        void var5_4 = this;
        synchronized (var5_4) {
            this.beginBatch();
            super.writeByte(9);
            super.writeByte(n3 | n << 2);
            super.writeTwoBytes(n2);
            this.endBatch();
            return;
        }
    }

    public void setPwmPeriod(int n, int n2, PwmScale pwmScale) throws IOException {
        void var5_4 = this;
        synchronized (var5_4) {
            this.beginBatch();
            super.writeByte(10);
            super.writeByte((2 & pwmScale.encoding) << 6 | n << 1 | 1 & pwmScale.encoding);
            super.writeTwoBytes(n2);
            this.endBatch();
            return;
        }
    }

    public void softClose() throws IOException {
        IOIOProtocol iOIOProtocol = this;
        synchronized (iOIOProtocol) {
            this.beginBatch();
            this.writeByte(29);
            this.endBatch();
            return;
        }
    }

    public void softReset() throws IOException {
        IOIOProtocol iOIOProtocol = this;
        synchronized (iOIOProtocol) {
            this.beginBatch();
            this.writeByte(1);
            this.endBatch();
            return;
        }
    }

    public void spiClose(int n) throws IOException {
        void var3_2 = this;
        synchronized (var3_2) {
            this.beginBatch();
            super.writeByte(16);
            super.writeByte(n << 5);
            super.writeByte(0);
            this.endBatch();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void spiConfigureMaster(int n, SpiMaster.Config config) throws IOException {
        void var7_3 = this;
        synchronized (var7_3) {
            this.beginBatch();
            super.writeByte(16);
            super.writeByte(n << 5 | SCALE_DIV[config.rate.ordinal()]);
            int n2 = config.sampleOnTrailing ? 0 : 2;
            boolean bl = config.invertClk;
            int n3 = 0;
            if (bl) {
                n3 = 1;
            }
            super.writeByte(n3 | n2);
            this.endBatch();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void spiMasterRequest(int n, int n2, byte[] arrby, int n3, int n4, int n5) throws IOException {
        boolean bl = true;
        IOIOProtocol iOIOProtocol = this;
        synchronized (iOIOProtocol) {
            boolean bl2 = n3 != n4 ? bl : false;
            if (n5 == n4) {
                bl = false;
            }
            this.beginBatch();
            this.writeByte(17);
            this.writeByte(n2 | n << 6);
            int n6 = bl2 ? 128 : 0;
            int n7 = 0;
            if (bl) {
                n7 = 64;
            }
            this.writeByte(n7 | n6 | n4 - 1);
            if (bl2) {
                this.writeByte(n3);
            }
            if (bl) {
                this.writeByte(n5);
            }
            int n8 = 0;
            do {
                if (n8 >= n3) {
                    this.endBatch();
                    return;
                }
                this.writeByte(255 & arrby[n8]);
                ++n8;
            } while (true);
        }
    }

    public void uartClose(int n) throws IOException {
        void var3_2 = this;
        synchronized (var3_2) {
            this.beginBatch();
            super.writeByte(13);
            super.writeByte(n << 6);
            super.writeTwoBytes(0);
            this.endBatch();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void uartConfigure(int n, int n2, boolean bl, Uart.StopBits stopBits, Uart.Parity parity) throws IOException {
        IOIOProtocol iOIOProtocol = this;
        synchronized (iOIOProtocol) {
            Uart.Parity parity2;
            int n3 = parity == Uart.Parity.EVEN ? 1 : (parity == (parity2 = Uart.Parity.ODD) ? 2 : 0);
            this.beginBatch();
            this.writeByte(13);
            int n4 = n << 6;
            int n5 = bl ? 8 : 0;
            int n6 = n5 | n4;
            Uart.StopBits stopBits2 = Uart.StopBits.TWO;
            int n7 = 0;
            if (stopBits == stopBits2) {
                n7 = 4;
            }
            this.writeByte(n3 | (n7 | n6));
            this.writeTwoBytes(n2);
            this.endBatch();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void uartData(int n, int n2, byte[] arrby) throws IOException {
        void var6_4 = this;
        synchronized (var6_4) {
            if (n2 > 64) {
                throw new IllegalArgumentException("A maximum of 64 bytes can be sent in one uartData message. Got: " + n2);
            }
            this.beginBatch();
            super.writeByte(14);
            super.writeByte(n2 - 1 | n << 6);
            int n3 = 0;
            do {
                if (n3 >= n2) {
                    this.endBatch();
                    return;
                }
                super.writeByte(255 & arrby[n3]);
                ++n3;
            } while (true);
        }
    }

    public static interface IncomingHandler {
        public void handleAnalogPinStatus(int var1, boolean var2);

        public void handleCheckInterfaceResponse(boolean var1);

        public void handleConnectionLost();

        public void handleEstablishConnection(byte[] var1, byte[] var2, byte[] var3);

        public void handleI2cClose(int var1);

        public void handleI2cOpen(int var1);

        public void handleI2cReportTxStatus(int var1, int var2);

        public void handleI2cResult(int var1, int var2, byte[] var3);

        public void handleIcspClose();

        public void handleIcspOpen();

        public void handleIcspReportRxStatus(int var1);

        public void handleIcspResult(int var1, byte[] var2);

        public void handleIncapClose(int var1);

        public void handleIncapOpen(int var1);

        public void handleIncapReport(int var1, int var2, byte[] var3);

        public void handleRegisterPeriodicDigitalSampling(int var1, int var2);

        public void handleReportAnalogInStatus(int[] var1, int[] var2);

        public void handleReportDigitalInStatus(int var1, boolean var2);

        public void handleReportPeriodicDigitalInStatus(int var1, boolean[] var2);

        public void handleSetChangeNotify(int var1, boolean var2);

        public void handleSoftReset();

        public void handleSpiClose(int var1);

        public void handleSpiData(int var1, int var2, byte[] var3, int var4);

        public void handleSpiOpen(int var1);

        public void handleSpiReportTxStatus(int var1, int var2);

        public void handleUartClose(int var1);

        public void handleUartData(int var1, int var2, byte[] var3);

        public void handleUartOpen(int var1);

        public void handleUartReportTxStatus(int var1, int var2);
    }

    class IncomingThread
    extends Thread {
        private Set<Integer> addedPins_;
        private int[] analogFramePins_;
        private byte[] inbuf_;
        private int readOffset_;
        private Set<Integer> removedPins_;
        private int validBytes_;

        IncomingThread() {
            this.readOffset_ = 0;
            this.validBytes_ = 0;
            this.inbuf_ = new byte[64];
            this.analogFramePins_ = new int[0];
            this.removedPins_ = new HashSet(16);
            this.addedPins_ = new HashSet(16);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private void fillBuf() throws IOException {
            try {
                this.validBytes_ = IOIOProtocol.this.in_.read(this.inbuf_, 0, this.inbuf_.length);
                if (this.validBytes_ <= 0) {
                    throw new IOException("Unexpected stream closure");
                }
                this.readOffset_ = 0;
                return;
            }
            catch (IOException var1_1) {
                Log.i((String)"IOIOProtocol", (String)"IOIO disconnected");
                throw var1_1;
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        private void findDelta(int[] arrn) {
            int n = 0;
            this.removedPins_.clear();
            this.addedPins_.clear();
            int[] arrn2 = this.analogFramePins_;
            int n2 = arrn2.length;
            int n3 = 0;
            do {
                if (n3 >= n2) break;
                int n4 = arrn2[n3];
                this.removedPins_.add((Object)n4);
                ++n3;
            } while (true);
            int n5 = arrn.length;
            do {
                if (n >= n5) break;
                int n6 = arrn[n];
                this.addedPins_.add((Object)n6);
                ++n;
            } while (true);
            Iterator iterator = this.removedPins_.iterator();
            while (iterator.hasNext()) {
                Integer n7 = (Integer)iterator.next();
                if (!this.addedPins_.contains((Object)n7)) continue;
                iterator.remove();
                this.addedPins_.remove((Object)n7);
            }
            return;
        }

        private int readByte() throws IOException {
            if (this.readOffset_ == this.validBytes_) {
                this.fillBuf();
            }
            byte[] arrby = this.inbuf_;
            int n = this.readOffset_;
            this.readOffset_ = n + 1;
            return 255 & arrby[n];
        }

        private void readBytes(int n, byte[] arrby) throws IOException {
            int n2 = 0;
            while (n2 < n) {
                arrby[n2] = (byte)super.readByte();
                ++n2;
            }
            return;
        }

        /*
         * Exception decompiling
         */
        public void run() {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [23[CASE]], but top level block is 2[TRYBLOCK]
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
            // org.benf.cfr.reader.Main.doJar(Main.java:128)
            // com.njlabs.showjava.processor.JavaExtractor$1.run(JavaExtractor.java:100)
            // java.lang.Thread.run(Thread.java:818)
            throw new IllegalStateException("Decompilation failed");
        }
    }

    static final class PwmScale
    extends Enum<PwmScale> {
        private static final /* synthetic */ PwmScale[] ENUM$VALUES;
        public static final /* enum */ PwmScale SCALE_1X = new PwmScale("SCALE_1X", 0, 1, 0);
        public static final /* enum */ PwmScale SCALE_256X;
        public static final /* enum */ PwmScale SCALE_64X;
        public static final /* enum */ PwmScale SCALE_8X;
        private final int encoding;
        public final int scale;

        static {
            SCALE_8X = new PwmScale("SCALE_8X", 1, 8, 3);
            SCALE_64X = new PwmScale("SCALE_64X", 2, 64, 2);
            SCALE_256X = new PwmScale("SCALE_256X", 3, 256, 1);
            PwmScale[] arrpwmScale = new PwmScale[]{SCALE_1X, SCALE_8X, SCALE_64X, SCALE_256X};
            ENUM$VALUES = arrpwmScale;
        }

        private PwmScale(String string3, int n2, int n3, int n4) {
            super(string2, n);
            this.scale = string3;
            this.encoding = n2;
        }

        public static PwmScale valueOf(String string2) {
            return (PwmScale)Enum.valueOf((Class)PwmScale.class, (String)string2);
        }

        public static PwmScale[] values() {
            PwmScale[] arrpwmScale = ENUM$VALUES;
            int n = arrpwmScale.length;
            PwmScale[] arrpwmScale2 = new PwmScale[n];
            System.arraycopy((Object)arrpwmScale, (int)0, (Object)arrpwmScale2, (int)0, (int)n);
            return arrpwmScale2;
        }
    }

}

