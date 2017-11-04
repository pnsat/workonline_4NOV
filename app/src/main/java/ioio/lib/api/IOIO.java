/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package ioio.lib.api;

import ioio.lib.api.AnalogInput;
import ioio.lib.api.DigitalInput;
import ioio.lib.api.DigitalOutput;
import ioio.lib.api.IcspMaster;
import ioio.lib.api.PulseInput;
import ioio.lib.api.PwmOutput;
import ioio.lib.api.SpiMaster;
import ioio.lib.api.TwiMaster;
import ioio.lib.api.Uart;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.api.exception.IncompatibilityException;

public interface IOIO {
    public static final int INVALID_PIN = -1;
    public static final int LED_PIN;

    public void beginBatch() throws ConnectionLostException;

    public void disconnect();

    public void endBatch() throws ConnectionLostException;

    public String getImplVersion(VersionType var1) throws ConnectionLostException;

    public State getState();

    public void hardReset() throws ConnectionLostException;

    public AnalogInput openAnalogInput(int var1) throws ConnectionLostException;

    public DigitalInput openDigitalInput(int var1) throws ConnectionLostException;

    public DigitalInput openDigitalInput(int var1, DigitalInput.Spec.Mode var2) throws ConnectionLostException;

    public DigitalInput openDigitalInput(DigitalInput.Spec var1) throws ConnectionLostException;

    public DigitalOutput openDigitalOutput(int var1) throws ConnectionLostException;

    public DigitalOutput openDigitalOutput(int var1, DigitalOutput.Spec.Mode var2, boolean var3) throws ConnectionLostException;

    public DigitalOutput openDigitalOutput(int var1, boolean var2) throws ConnectionLostException;

    public DigitalOutput openDigitalOutput(DigitalOutput.Spec var1, boolean var2) throws ConnectionLostException;

    public IcspMaster openIcspMaster() throws ConnectionLostException;

    public PulseInput openPulseInput(int var1, PulseInput.PulseMode var2) throws ConnectionLostException;

    public PulseInput openPulseInput(DigitalInput.Spec var1, PulseInput.ClockRate var2, PulseInput.PulseMode var3, boolean var4) throws ConnectionLostException;

    public PwmOutput openPwmOutput(int var1, int var2) throws ConnectionLostException;

    public PwmOutput openPwmOutput(DigitalOutput.Spec var1, int var2) throws ConnectionLostException;

    public SpiMaster openSpiMaster(int var1, int var2, int var3, int var4, SpiMaster.Rate var5) throws ConnectionLostException;

    public SpiMaster openSpiMaster(int var1, int var2, int var3, int[] var4, SpiMaster.Rate var5) throws ConnectionLostException;

    public SpiMaster openSpiMaster(DigitalInput.Spec var1, DigitalOutput.Spec var2, DigitalOutput.Spec var3, DigitalOutput.Spec[] var4, SpiMaster.Config var5) throws ConnectionLostException;

    public TwiMaster openTwiMaster(int var1, TwiMaster.Rate var2, boolean var3) throws ConnectionLostException;

    public Uart openUart(int var1, int var2, int var3, Uart.Parity var4, Uart.StopBits var5) throws ConnectionLostException;

    public Uart openUart(DigitalInput.Spec var1, DigitalOutput.Spec var2, int var3, Uart.Parity var4, Uart.StopBits var5) throws ConnectionLostException;

    public void softReset() throws ConnectionLostException;

    public void waitForConnect() throws ConnectionLostException, IncompatibilityException;

    public void waitForDisconnect() throws InterruptedException;

    public static final class State
    extends Enum<State> {
        public static final /* enum */ State CONNECTED;
        public static final /* enum */ State DEAD;
        private static final /* synthetic */ State[] ENUM$VALUES;
        public static final /* enum */ State INCOMPATIBLE;
        public static final /* enum */ State INIT;

        static {
            INIT = new State("INIT", 0);
            CONNECTED = new State("CONNECTED", 1);
            INCOMPATIBLE = new State("INCOMPATIBLE", 2);
            DEAD = new State("DEAD", 3);
            State[] arrstate = new State[]{INIT, CONNECTED, INCOMPATIBLE, DEAD};
            ENUM$VALUES = arrstate;
        }

        private State(String string3, int n2) {
            super(string2, n);
        }

        public static State valueOf(String string2) {
            return (State)Enum.valueOf((Class)State.class, (String)string2);
        }

        public static State[] values() {
            State[] arrstate = ENUM$VALUES;
            int n = arrstate.length;
            State[] arrstate2 = new State[n];
            System.arraycopy((Object)arrstate, (int)0, (Object)arrstate2, (int)0, (int)n);
            return arrstate2;
        }
    }

    public static final class VersionType
    extends Enum<VersionType> {
        public static final /* enum */ VersionType APP_FIRMWARE_VER;
        public static final /* enum */ VersionType BOOTLOADER_VER;
        private static final /* synthetic */ VersionType[] ENUM$VALUES;
        public static final /* enum */ VersionType HARDWARE_VER;
        public static final /* enum */ VersionType IOIOLIB_VER;

        static {
            HARDWARE_VER = new VersionType("HARDWARE_VER", 0);
            BOOTLOADER_VER = new VersionType("BOOTLOADER_VER", 1);
            APP_FIRMWARE_VER = new VersionType("APP_FIRMWARE_VER", 2);
            IOIOLIB_VER = new VersionType("IOIOLIB_VER", 3);
            VersionType[] arrversionType = new VersionType[]{HARDWARE_VER, BOOTLOADER_VER, APP_FIRMWARE_VER, IOIOLIB_VER};
            ENUM$VALUES = arrversionType;
        }

        private VersionType(String string3, int n2) {
            super(string2, n);
        }

        public static VersionType valueOf(String string2) {
            return (VersionType)Enum.valueOf((Class)VersionType.class, (String)string2);
        }

        public static VersionType[] values() {
            VersionType[] arrversionType = ENUM$VALUES;
            int n = arrversionType.length;
            VersionType[] arrversionType2 = new VersionType[n];
            System.arraycopy((Object)arrversionType, (int)0, (Object)arrversionType2, (int)0, (int)n);
            return arrversionType2;
        }
    }

}

