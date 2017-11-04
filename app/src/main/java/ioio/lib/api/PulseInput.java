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

import ioio.lib.api.Closeable;
import ioio.lib.api.exception.ConnectionLostException;

public interface PulseInput
extends Closeable {
    public float getDuration() throws InterruptedException, ConnectionLostException;

    public float getFrequency() throws InterruptedException, ConnectionLostException;

    public float waitPulseGetDuration() throws InterruptedException, ConnectionLostException;

    public static final class ClockRate
    extends Enum<ClockRate> {
        private static final /* synthetic */ ClockRate[] ENUM$VALUES;
        public static final /* enum */ ClockRate RATE_16MHz = new ClockRate("RATE_16MHz", 0, 16000000);
        public static final /* enum */ ClockRate RATE_250KHz;
        public static final /* enum */ ClockRate RATE_2MHz;
        public static final /* enum */ ClockRate RATE_62KHz;
        public final int hertz;

        static {
            RATE_2MHz = new ClockRate("RATE_2MHz", 1, 2000000);
            RATE_250KHz = new ClockRate("RATE_250KHz", 2, 250000);
            RATE_62KHz = new ClockRate("RATE_62KHz", 3, 62500);
            ClockRate[] arrclockRate = new ClockRate[]{RATE_16MHz, RATE_2MHz, RATE_250KHz, RATE_62KHz};
            ENUM$VALUES = arrclockRate;
        }

        private ClockRate(String string3, int n2, int n3) {
            super(string2, n);
            this.hertz = string3;
        }

        public static ClockRate valueOf(String string2) {
            return (ClockRate)Enum.valueOf((Class)ClockRate.class, (String)string2);
        }

        public static ClockRate[] values() {
            ClockRate[] arrclockRate = ENUM$VALUES;
            int n = arrclockRate.length;
            ClockRate[] arrclockRate2 = new ClockRate[n];
            System.arraycopy((Object)arrclockRate, (int)0, (Object)arrclockRate2, (int)0, (int)n);
            return arrclockRate2;
        }
    }

    public static final class PulseMode
    extends Enum<PulseMode> {
        private static final /* synthetic */ PulseMode[] ENUM$VALUES;
        public static final /* enum */ PulseMode FREQ;
        public static final /* enum */ PulseMode FREQ_SCALE_16;
        public static final /* enum */ PulseMode FREQ_SCALE_4;
        public static final /* enum */ PulseMode NEGATIVE;
        public static final /* enum */ PulseMode POSITIVE;
        public final int scaling;

        static {
            POSITIVE = new PulseMode("POSITIVE", 0, 1);
            NEGATIVE = new PulseMode("NEGATIVE", 1, 1);
            FREQ = new PulseMode("FREQ", 2, 1);
            FREQ_SCALE_4 = new PulseMode("FREQ_SCALE_4", 3, 4);
            FREQ_SCALE_16 = new PulseMode("FREQ_SCALE_16", 4, 16);
            PulseMode[] arrpulseMode = new PulseMode[]{POSITIVE, NEGATIVE, FREQ, FREQ_SCALE_4, FREQ_SCALE_16};
            ENUM$VALUES = arrpulseMode;
        }

        private PulseMode(String string3, int n2, int n3) {
            super(string2, n);
            this.scaling = string3;
        }

        public static PulseMode valueOf(String string2) {
            return (PulseMode)Enum.valueOf((Class)PulseMode.class, (String)string2);
        }

        public static PulseMode[] values() {
            PulseMode[] arrpulseMode = ENUM$VALUES;
            int n = arrpulseMode.length;
            PulseMode[] arrpulseMode2 = new PulseMode[n];
            System.arraycopy((Object)arrpulseMode, (int)0, (Object)arrpulseMode2, (int)0, (int)n);
            return arrpulseMode2;
        }
    }

}

