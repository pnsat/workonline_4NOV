/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.io.IOException
 *  java.io.InputStream
 *  java.io.OutputStream
 *  java.lang.Exception
 *  java.lang.IllegalArgumentException
 *  java.lang.IllegalStateException
 *  java.lang.Integer
 *  java.lang.InterruptedException
 *  java.lang.Math
 *  java.lang.NoSuchFieldError
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package ioio.lib.impl;

import android.util.Log;
import ioio.lib.api.AnalogInput;
import ioio.lib.api.DigitalInput;
import ioio.lib.api.DigitalOutput;
import ioio.lib.api.IOIO;
import ioio.lib.api.IOIOConnection;
import ioio.lib.api.IcspMaster;
import ioio.lib.api.PulseInput;
import ioio.lib.api.PwmOutput;
import ioio.lib.api.SpiMaster;
import ioio.lib.api.TwiMaster;
import ioio.lib.api.Uart;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.api.exception.IncompatibilityException;
import ioio.lib.impl.AnalogInputImpl;
import ioio.lib.impl.Constants;
import ioio.lib.impl.DigitalInputImpl;
import ioio.lib.impl.DigitalOutputImpl;
import ioio.lib.impl.IOIOProtocol;
import ioio.lib.impl.IcspMasterImpl;
import ioio.lib.impl.IncapImpl;
import ioio.lib.impl.IncomingState;
import ioio.lib.impl.ModuleAllocator;
import ioio.lib.impl.PinFunctionMap;
import ioio.lib.impl.PwmImpl;
import ioio.lib.impl.TwiMasterImpl;
import ioio.lib.impl.UartImpl;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * Exception performing whole class analysis ignored.
 */
public class IOIOImpl
implements IOIO,
IncomingState.DisconnectListener {
    private static /* synthetic */ int[] $SWITCH_TABLE$ioio$lib$api$IOIO$VersionType;
    private static final byte[] REQUIRED_INTERFACE_ID = new byte[]{73, 79, 73, 79, 48, 48, 48, 51};
    private static final String TAG = "IOIOImpl";
    private final IOIOConnection connection_;
    private boolean disconnect_ = false;
    private final ModuleAllocator incapAllocatorDouble_ = new ModuleAllocator(Constants.INCAP_MODULES_DOUBLE, "INCAP_DOUBLE");
    private final ModuleAllocator incapAllocatorSingle_ = new ModuleAllocator(Constants.INCAP_MODULES_SINGLE, "INCAP_SINGLE");
    private final IncomingState incomingState_ = new IncomingState();
    private boolean openIcsp_ = false;
    private final boolean[] openPins_ = new boolean[49];
    private final boolean[] openTwi_ = new boolean[3];
    IOIOProtocol protocol_;
    private final ModuleAllocator pwmAllocator_ = new ModuleAllocator(9, "PWM");
    private final ModuleAllocator spiAllocator_ = new ModuleAllocator(3, "SPI");
    private State state_ = State.INIT;
    private final ModuleAllocator uartAllocator_ = new ModuleAllocator(4, "UART");

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    static /* synthetic */ int[] $SWITCH_TABLE$ioio$lib$api$IOIO$VersionType() {
        var0 = IOIOImpl.$SWITCH_TABLE$ioio$lib$api$IOIO$VersionType;
        if (var0 != null) {
            return var0;
        }
        var1_1 = new int[VersionType.values().length];
        try {
            var1_1[VersionType.APP_FIRMWARE_VER.ordinal()] = 3;
        }
        catch (NoSuchFieldError var2_5) {
            ** continue;
        }
lbl9: // 2 sources:
        do {
            try {
                var1_1[VersionType.BOOTLOADER_VER.ordinal()] = 2;
            }
            catch (NoSuchFieldError var3_4) {
                ** continue;
            }
lbl14: // 2 sources:
            do {
                try {
                    var1_1[VersionType.HARDWARE_VER.ordinal()] = 1;
                }
                catch (NoSuchFieldError var4_3) {
                    ** continue;
                }
lbl19: // 2 sources:
                do {
                    try {
                        var1_1[VersionType.IOIOLIB_VER.ordinal()] = 4;
                    }
                    catch (NoSuchFieldError var5_2) {
                        ** continue;
                    }
lbl24: // 2 sources:
                    do {
                        IOIOImpl.$SWITCH_TABLE$ioio$lib$api$IOIO$VersionType = var1_1;
                        return var1_1;
                        break;
                    } while (true);
                    break;
                } while (true);
                break;
            } while (true);
            break;
        } while (true);
    }

    static {
    }

    public IOIOImpl(IOIOConnection iOIOConnection) {
        this.connection_ = iOIOConnection;
    }

    private void checkIcspFree() {
        if (this.openIcsp_) {
            throw new IllegalArgumentException("ICSP already open");
        }
    }

    private void checkInterfaceVersion() throws IncompatibilityException, ConnectionLostException, InterruptedException {
        try {
            this.protocol_.checkInterface(REQUIRED_INTERFACE_ID);
        }
        catch (IOException var1_1) {
            throw new ConnectionLostException((Exception)var1_1);
        }
        if (!this.incomingState_.waitForInterfaceSupport()) {
            this.state_ = State.INCOMPATIBLE;
            Log.e((String)"IOIOImpl", (String)"Required interface ID is not supported");
            throw new IncompatibilityException("IOIO firmware does not support required firmware: " + new String(REQUIRED_INTERFACE_ID));
        }
    }

    private void checkPinFree(int n) {
        if (this.openPins_[n]) {
            throw new IllegalArgumentException("Pin already open: " + n);
        }
    }

    private void checkState() throws ConnectionLostException {
        if (this.state_ == State.DEAD) {
            throw new ConnectionLostException();
        }
        if (this.state_ == State.INCOMPATIBLE) {
            throw new IllegalStateException("Incompatibility has been reported - IOIO cannot be used");
        }
        if (this.state_ != State.CONNECTED) {
            throw new IllegalStateException("Connection has not yet been established");
        }
    }

    private void checkTwiFree(int n) {
        if (this.openTwi_[n]) {
            throw new IllegalArgumentException("TWI already open: " + n);
        }
    }

    void addDisconnectListener(IncomingState.DisconnectListener disconnectListener) throws ConnectionLostException {
        void var3_2 = this;
        synchronized (var3_2) {
            this.incomingState_.addDisconnectListener(disconnectListener);
            return;
        }
    }

    @Override
    public void beginBatch() throws ConnectionLostException {
        IOIOImpl iOIOImpl = this;
        synchronized (iOIOImpl) {
            this.checkState();
            this.protocol_.beginBatch();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    void closeIcsp() {
        IOIOImpl iOIOImpl = this;
        // MONITORENTER : iOIOImpl
        try {
            this.checkState();
            if (!this.openIcsp_) {
                throw new IllegalStateException("ICSP not open");
            }
            this.openIcsp_ = false;
            this.openPins_[Constants.ICSP_PINS[0]] = false;
            this.openPins_[Constants.ICSP_PINS[1]] = false;
            this.protocol_.icspClose();
            return;
        }
        catch (ConnectionLostException var3_2) {
            // MONITOREXIT : iOIOImpl
            return;
        }
        catch (IOException var2_3) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    void closeIncap(int n, boolean bl) {
        void var6_3 = this;
        // MONITORENTER : var6_3
        try {
            super.checkState();
            if (bl) {
                this.incapAllocatorDouble_.releaseModule(n);
            } else {
                this.incapAllocatorSingle_.releaseModule(n);
            }
            this.protocol_.incapClose(n);
            // MONITOREXIT : var6_3
            return;
        }
        catch (IOException var5_4) {
            return;
        }
        catch (ConnectionLostException var3_5) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    void closePin(int n) {
        void var5_2 = this;
        // MONITORENTER : var5_2
        try {
            super.checkState();
            if (!this.openPins_[n]) {
                throw new IllegalStateException("Pin not open: " + n);
            }
            this.protocol_.setPinDigitalIn(n, DigitalInput.Spec.Mode.FLOATING);
            this.openPins_[n] = false;
            return;
        }
        catch (IOException var4_3) {
            // MONITOREXIT : var5_2
            return;
        }
        catch (ConnectionLostException var3_4) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    void closePwm(int n) {
        void var5_2 = this;
        // MONITORENTER : var5_2
        try {
            super.checkState();
            this.pwmAllocator_.releaseModule(n);
            this.protocol_.setPwmPeriod(n, 0, IOIOProtocol.PwmScale.SCALE_1X);
            // MONITOREXIT : var5_2
            return;
        }
        catch (ConnectionLostException var3_3) {
            return;
        }
        catch (IOException var2_4) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    void closeSpi(int n) {
        void var5_2 = this;
        // MONITORENTER : var5_2
        try {
            super.checkState();
            this.spiAllocator_.releaseModule(n);
            this.protocol_.spiClose(n);
            // MONITOREXIT : var5_2
            return;
        }
        catch (ConnectionLostException var3_3) {
            return;
        }
        catch (IOException var2_4) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    void closeTwi(int n) {
        void var5_2 = this;
        // MONITORENTER : var5_2
        try {
            super.checkState();
            if (!this.openTwi_[n]) {
                throw new IllegalStateException("TWI not open: " + n);
            }
            this.openTwi_[n] = false;
            this.openPins_[Constants.TWI_PINS[n][0]] = false;
            this.openPins_[Constants.TWI_PINS[n][1]] = false;
            this.protocol_.i2cClose(n);
            return;
        }
        catch (IOException var4_3) {
            // MONITOREXIT : var5_2
            return;
        }
        catch (ConnectionLostException var3_4) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    void closeUart(int n) {
        void var5_2 = this;
        // MONITORENTER : var5_2
        try {
            super.checkState();
            this.uartAllocator_.releaseModule(n);
            this.protocol_.uartClose(n);
            // MONITOREXIT : var5_2
            return;
        }
        catch (ConnectionLostException var3_3) {
            return;
        }
        catch (IOException var2_4) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void disconnect() {
        IOIOImpl iOIOImpl = this;
        synchronized (iOIOImpl) {
            Log.d((String)"IOIOImpl", (String)"Client requested disconnect.");
            boolean bl = this.disconnect_;
            if (!bl) {
                this.disconnect_ = true;
                try {
                    if (this.protocol_ != null) {
                        this.protocol_.softClose();
                    }
                }
                catch (IOException var4_3) {
                    Log.e((String)"IOIOImpl", (String)"Soft close failed", (Throwable)var4_3);
                }
                this.connection_.disconnect();
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
    public void disconnected() {
        IOIOImpl iOIOImpl = this;
        synchronized (iOIOImpl) {
            this.state_ = State.DEAD;
            boolean bl = this.disconnect_;
            if (!bl) {
                Log.d((String)"IOIOImpl", (String)"Physical disconnect.");
                this.disconnect_ = true;
                this.connection_.disconnect();
            }
            return;
        }
    }

    @Override
    public void endBatch() throws ConnectionLostException {
        IOIOImpl iOIOImpl = this;
        synchronized (iOIOImpl) {
            this.checkState();
            try {
                this.protocol_.endBatch();
                return;
            }
            catch (IOException var2_2) {
                throw new ConnectionLostException((Exception)var2_2);
            }
        }
    }

    @Override
    public String getImplVersion(VersionType versionType) throws ConnectionLostException {
        if (this.state_ == State.INIT) {
            throw new IllegalStateException("Connection has not yet been established");
        }
        switch (IOIOImpl.$SWITCH_TABLE$ioio$lib$api$IOIO$VersionType()[versionType.ordinal()]) {
            default: {
                return null;
            }
            case 1: {
                return this.incomingState_.hardwareId_;
            }
            case 2: {
                return this.incomingState_.bootloaderId_;
            }
            case 3: {
                return this.incomingState_.firmwareId_;
            }
            case 4: 
        }
        return "IOIO0323";
    }

    @Override
    public State getState() {
        return this.state_;
    }

    @Override
    public void hardReset() throws ConnectionLostException {
        IOIOImpl iOIOImpl = this;
        synchronized (iOIOImpl) {
            this.checkState();
            try {
                this.protocol_.hardReset();
                return;
            }
            catch (IOException var2_2) {
                throw new ConnectionLostException((Exception)var2_2);
            }
        }
    }

    @Override
    public AnalogInput openAnalogInput(int n) throws ConnectionLostException {
        void var5_2 = this;
        synchronized (var5_2) {
            super.checkState();
            PinFunctionMap.checkSupportsAnalogInput(n);
            super.checkPinFree(n);
            AnalogInputImpl analogInputImpl = new AnalogInputImpl((IOIOImpl)this, n);
            this.addDisconnectListener(analogInputImpl);
            this.openPins_[n] = true;
            this.incomingState_.addInputPinListener(n, analogInputImpl);
            try {
                this.protocol_.setPinAnalogIn(n);
                this.protocol_.setAnalogInSampling(n, true);
                return analogInputImpl;
            }
            catch (IOException var4_4) {
                analogInputImpl.close();
                throw new ConnectionLostException((Exception)var4_4);
            }
        }
    }

    @Override
    public DigitalInput openDigitalInput(int n) throws ConnectionLostException {
        return this.openDigitalInput(new DigitalInput.Spec(n));
    }

    @Override
    public DigitalInput openDigitalInput(int n, DigitalInput.Spec.Mode mode) throws ConnectionLostException {
        return this.openDigitalInput(new DigitalInput.Spec(n, mode));
    }

    @Override
    public DigitalInput openDigitalInput(DigitalInput.Spec spec) throws ConnectionLostException {
        void var5_2 = this;
        synchronized (var5_2) {
            super.checkState();
            PinFunctionMap.checkValidPin(spec.pin);
            super.checkPinFree(spec.pin);
            DigitalInputImpl digitalInputImpl = new DigitalInputImpl((IOIOImpl)this, spec.pin);
            this.addDisconnectListener(digitalInputImpl);
            this.openPins_[spec.pin] = true;
            this.incomingState_.addInputPinListener(spec.pin, digitalInputImpl);
            try {
                this.protocol_.setPinDigitalIn(spec.pin, spec.mode);
                this.protocol_.setChangeNotify(spec.pin, true);
                return digitalInputImpl;
            }
            catch (IOException var4_4) {
                digitalInputImpl.close();
                throw new ConnectionLostException((Exception)var4_4);
            }
        }
    }

    @Override
    public DigitalOutput openDigitalOutput(int n) throws ConnectionLostException {
        return this.openDigitalOutput(new DigitalOutput.Spec(n), false);
    }

    @Override
    public DigitalOutput openDigitalOutput(int n, DigitalOutput.Spec.Mode mode, boolean bl) throws ConnectionLostException {
        return this.openDigitalOutput(new DigitalOutput.Spec(n, mode), bl);
    }

    @Override
    public DigitalOutput openDigitalOutput(int n, boolean bl) throws ConnectionLostException {
        return this.openDigitalOutput(new DigitalOutput.Spec(n), bl);
    }

    @Override
    public DigitalOutput openDigitalOutput(DigitalOutput.Spec spec, boolean bl) throws ConnectionLostException {
        void var6_3 = this;
        synchronized (var6_3) {
            super.checkState();
            PinFunctionMap.checkValidPin(spec.pin);
            super.checkPinFree(spec.pin);
            DigitalOutputImpl digitalOutputImpl = new DigitalOutputImpl((IOIOImpl)this, spec.pin, bl);
            this.addDisconnectListener(digitalOutputImpl);
            this.openPins_[spec.pin] = true;
            try {
                this.protocol_.setPinDigitalOut(spec.pin, bl, spec.mode);
                return digitalOutputImpl;
            }
            catch (IOException var5_5) {
                digitalOutputImpl.close();
                throw new ConnectionLostException((Exception)var5_5);
            }
        }
    }

    @Override
    public IcspMaster openIcspMaster() throws ConnectionLostException {
        IOIOImpl iOIOImpl = this;
        synchronized (iOIOImpl) {
            this.checkState();
            this.checkIcspFree();
            this.checkPinFree(Constants.ICSP_PINS[0]);
            this.checkPinFree(Constants.ICSP_PINS[1]);
            this.checkPinFree(Constants.ICSP_PINS[2]);
            this.openPins_[Constants.ICSP_PINS[0]] = true;
            this.openPins_[Constants.ICSP_PINS[1]] = true;
            this.openPins_[Constants.ICSP_PINS[2]] = true;
            this.openIcsp_ = true;
            IcspMasterImpl icspMasterImpl = new IcspMasterImpl(this);
            this.addDisconnectListener(icspMasterImpl);
            this.incomingState_.addIcspListener(icspMasterImpl);
            try {
                this.protocol_.icspOpen();
                return icspMasterImpl;
            }
            catch (IOException var3_3) {
                icspMasterImpl.close();
                throw new ConnectionLostException((Exception)var3_3);
            }
        }
    }

    @Override
    public PulseInput openPulseInput(int n, PulseInput.PulseMode pulseMode) throws ConnectionLostException {
        return this.openPulseInput(new DigitalInput.Spec(n), PulseInput.ClockRate.RATE_16MHz, pulseMode, true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public PulseInput openPulseInput(DigitalInput.Spec spec, PulseInput.ClockRate clockRate, PulseInput.PulseMode pulseMode, boolean bl) throws ConnectionLostException {
        super.checkState();
        super.checkPinFree(spec.pin);
        PinFunctionMap.checkSupportsPeripheralInput(spec.pin);
        Integer n = bl ? this.incapAllocatorDouble_.allocateModule() : this.incapAllocatorSingle_.allocateModule();
        int n2 = n;
        IncapImpl incapImpl = new IncapImpl((IOIOImpl)this, pulseMode, n2, spec.pin, clockRate.hertz, pulseMode.scaling, bl);
        this.addDisconnectListener(incapImpl);
        this.incomingState_.addIncapListener(n2, incapImpl);
        this.openPins_[spec.pin] = true;
        try {
            this.protocol_.setPinDigitalIn(spec.pin, spec.mode);
            this.protocol_.setPinIncap(spec.pin, n2, true);
            this.protocol_.incapConfigure(n2, bl, 1 + pulseMode.ordinal(), clockRate.ordinal());
            return incapImpl;
        }
        catch (IOException var8_8) {
            incapImpl.close();
            throw new ConnectionLostException((Exception)var8_8);
        }
    }

    @Override
    public PwmOutput openPwmOutput(int n, int n2) throws ConnectionLostException {
        return this.openPwmOutput(new DigitalOutput.Spec(n), n2);
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
    public PwmOutput openPwmOutput(DigitalOutput.Spec spec, int n) throws ConnectionLostException {
        PwmImpl pwmImpl;
        void var11_3 = this;
        // MONITORENTER : var11_3
        super.checkState();
        PinFunctionMap.checkSupportsPeripheralOutput(spec.pin);
        super.checkPinFree(spec.pin);
        int n2 = this.pwmAllocator_.allocateModule();
        int n3 = 0;
        do {
            int n5;
            int n4;
            if ((n5 = (n4 = 16000000 / IOIOProtocol.PwmScale.values()[n3].scale) / n) > 65536) continue;
            float f = 1000000.0f / (float)n4;
            pwmImpl = new PwmImpl((IOIOImpl)this, spec.pin, n2, n5, f);
            this.addDisconnectListener(pwmImpl);
            this.openPins_[spec.pin] = true;
            this.protocol_.setPinDigitalOut(spec.pin, false, spec.mode);
            this.protocol_.setPinPwm(spec.pin, n2, true);
            this.protocol_.setPwmPeriod(n2, n5 - 1, IOIOProtocol.PwmScale.values()[n3]);
            // MONITOREXIT : var11_3
            return pwmImpl;
        } while (++n3 < IOIOProtocol.PwmScale.values().length);
        throw new IllegalArgumentException("Frequency too low: " + n);
        catch (IOException iOException) {
            pwmImpl.close();
            throw new ConnectionLostException((Exception)iOException);
        }
    }

    @Override
    public SpiMaster openSpiMaster(int n, int n2, int n3, int n4, SpiMaster.Rate rate) throws ConnectionLostException {
        return this.openSpiMaster(n, n2, n3, new int[]{n4}, rate);
    }

    @Override
    public SpiMaster openSpiMaster(int n, int n2, int n3, int[] arrn, SpiMaster.Rate rate) throws ConnectionLostException {
        DigitalOutput.Spec[] arrspec = new DigitalOutput.Spec[arrn.length];
        int n4 = 0;
        while (n4 < arrn.length) {
            arrspec[n4] = new DigitalOutput.Spec(arrn[n4]);
            ++n4;
        }
        return this.openSpiMaster(new DigitalInput.Spec(n, DigitalInput.Spec.Mode.PULL_UP), new DigitalOutput.Spec(n2), new DigitalOutput.Spec(n3), arrspec, new SpiMaster.Config(rate));
    }

    /*
     * Exception decompiling
     */
    @Override
    public SpiMaster openSpiMaster(DigitalInput.Spec var1_4, DigitalOutput.Spec var2_5, DigitalOutput.Spec var3_2, DigitalOutput.Spec[] var4_3, SpiMaster.Config var5_1) throws ConnectionLostException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [8[UNCONDITIONALDOLOOP]], but top level block is 1[TRYBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // com.njlabs.showjava.processor.JavaExtractor$1.run(JavaExtractor.java:100)
        // java.lang.Thread.run(Thread.java:818)
        throw new IllegalStateException("Decompilation failed");
    }

    @Override
    public TwiMaster openTwiMaster(int n, TwiMaster.Rate rate, boolean bl) throws ConnectionLostException {
        void var7_4 = this;
        synchronized (var7_4) {
            super.checkState();
            super.checkTwiFree(n);
            super.checkPinFree(Constants.TWI_PINS[n][0]);
            super.checkPinFree(Constants.TWI_PINS[n][1]);
            this.openPins_[Constants.TWI_PINS[n][0]] = true;
            this.openPins_[Constants.TWI_PINS[n][1]] = true;
            this.openTwi_[n] = true;
            TwiMasterImpl twiMasterImpl = new TwiMasterImpl((IOIOImpl)this, n);
            this.addDisconnectListener(twiMasterImpl);
            this.incomingState_.addTwiListener(n, twiMasterImpl);
            try {
                this.protocol_.i2cConfigureMaster(n, rate, bl);
                return twiMasterImpl;
            }
            catch (IOException var6_6) {
                twiMasterImpl.close();
                throw new ConnectionLostException((Exception)var6_6);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public Uart openUart(int n, int n2, int n3, Uart.Parity parity, Uart.StopBits stopBits) throws ConnectionLostException {
        DigitalInput.Spec spec = n == -1 ? null : new DigitalInput.Spec(n);
        DigitalOutput.Spec spec2 = null;
        if (n2 == -1) {
            return this.openUart(spec, spec2, n3, parity, stopBits);
        }
        spec2 = new DigitalOutput.Spec(n2);
        return this.openUart(spec, spec2, n3, parity, stopBits);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    @Override
    public Uart openUart(DigitalInput.Spec var1_4, DigitalOutput.Spec var2_5, int var3_2, Uart.Parity var4_3, Uart.StopBits var5_1) throws ConnectionLostException {
        var14_6 = this;
        // MONITORENTER : var14_6
        this.checkState();
        if (var1_4 != null) {
            PinFunctionMap.checkSupportsPeripheralInput(var1_4.pin);
            this.checkPinFree(var1_4.pin);
        }
        if (var2_5 != null) {
            PinFunctionMap.checkSupportsPeripheralOutput(var2_5.pin);
            this.checkPinFree(var2_5.pin);
        }
        var7_7 = var1_4 != null ? var1_4.pin : -1;
        var8_8 = var2_5 != null ? var2_5.pin : -1;
        var9_9 = this.uartAllocator_.allocateModule();
        var10_10 = new UartImpl(this, var8_8, var7_7, var9_9);
        this.addDisconnectListener(var10_10);
        this.incomingState_.addUartListener(var9_9, var10_10);
        if (var1_4 == null) ** GOTO lbl21
        try {
            this.openPins_[var1_4.pin] = true;
            this.protocol_.setPinDigitalIn(var1_4.pin, var1_4.mode);
            this.protocol_.setPinUart(var1_4.pin, var9_9, false, true);
lbl21: // 2 sources:
            if (var2_5 != null) {
                this.openPins_[var2_5.pin] = true;
                this.protocol_.setPinDigitalOut(var2_5.pin, true, var2_5.mode);
                this.protocol_.setPinUart(var2_5.pin, var9_9, true, true);
            }
            var12_11 = true;
            var13_12 = -1 + Math.round((float)(4000000.0f / (float)var3_2));
            if (var13_12 > 65535) {
                var12_11 = false;
                var13_12 = -1 + Math.round((float)(1000000.0f / (float)var3_2));
            }
            this.protocol_.uartConfigure(var9_9, var13_12, var12_11, var5_1, var4_3);
            // MONITOREXIT : var14_6
            return var10_10;
        }
        catch (IOException var11_13) {
            var10_10.close();
            throw new ConnectionLostException((Exception)var11_13);
        }
    }

    void removeDisconnectListener(IncomingState.DisconnectListener disconnectListener) {
        void var3_2 = this;
        synchronized (var3_2) {
            this.incomingState_.removeDisconnectListener(disconnectListener);
            return;
        }
    }

    @Override
    public void softReset() throws ConnectionLostException {
        IOIOImpl iOIOImpl = this;
        synchronized (iOIOImpl) {
            this.checkState();
            try {
                this.protocol_.softReset();
                return;
            }
            catch (IOException var2_2) {
                throw new ConnectionLostException((Exception)var2_2);
            }
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
    @Override
    public void waitForConnect() throws ConnectionLostException, IncompatibilityException {
        if (this.state_ == State.CONNECTED) {
            return;
        }
        if (this.state_ == State.DEAD) {
            throw new ConnectionLostException();
        }
        this.addDisconnectListener(this);
        Log.d((String)"IOIOImpl", (String)"Waiting for IOIO connection");
        try {
            Log.v((String)"IOIOImpl", (String)"Waiting for underlying connection");
            this.connection_.waitForConnect();
            var13_1 = this;
            // MONITORENTER : var13_1
            ** if (!this.disconnect_) goto lbl14
        }
        catch (InterruptedException var2_4) {
            Log.e((String)"IOIOImpl", (String)"Unexpected exception", (Throwable)var2_4);
            return;
        }
lbl-1000: // 1 sources:
        {
            throw new ConnectionLostException();
        }
lbl14: // 1 sources:
        ** GOTO lbl23
        {
            catch (ConnectionLostException var4_2) {
                try {
                    this.incomingState_.handleConnectionLost();
                    throw var4_2;
                }
                catch (ConnectionLostException var5_3) {
                    Log.d((String)"IOIOImpl", (String)"Connection lost / aborted");
                    this.state_ = State.DEAD;
                    throw var5_3;
                }
lbl23: // 1 sources:
                this.protocol_ = new IOIOProtocol(this.connection_.getInputStream(), this.connection_.getOutputStream(), this.incomingState_);
                // MONITOREXIT : var13_1
                Log.v((String)"IOIOImpl", (String)"Waiting for handshake");
                this.incomingState_.waitConnectionEstablished();
                Log.v((String)"IOIOImpl", (String)"Querying for required interface ID");
                this.checkInterfaceVersion();
                Log.v((String)"IOIOImpl", (String)"Required interface ID is supported");
                this.state_ = State.CONNECTED;
                Log.i((String)"IOIOImpl", (String)"IOIO connection established");
                return;
            }
        }
    }

    @Override
    public void waitForDisconnect() throws InterruptedException {
        this.incomingState_.waitDisconnect();
    }
}

