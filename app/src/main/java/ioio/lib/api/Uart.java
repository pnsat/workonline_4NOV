/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.InputStream
 *  java.io.OutputStream
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package ioio.lib.api;

import ioio.lib.api.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

public interface Uart
extends Closeable {
    public InputStream getInputStream();

    public OutputStream getOutputStream();

    public static final class Parity
    extends Enum<Parity> {
        private static final /* synthetic */ Parity[] ENUM$VALUES;
        public static final /* enum */ Parity EVEN;
        public static final /* enum */ Parity NONE;
        public static final /* enum */ Parity ODD;

        static {
            NONE = new Parity("NONE", 0);
            EVEN = new Parity("EVEN", 1);
            ODD = new Parity("ODD", 2);
            Parity[] arrparity = new Parity[]{NONE, EVEN, ODD};
            ENUM$VALUES = arrparity;
        }

        private Parity(String string3, int n2) {
            super(string2, n);
        }

        public static Parity valueOf(String string2) {
            return (Parity)Enum.valueOf((Class)Parity.class, (String)string2);
        }

        public static Parity[] values() {
            Parity[] arrparity = ENUM$VALUES;
            int n = arrparity.length;
            Parity[] arrparity2 = new Parity[n];
            System.arraycopy((Object)arrparity, (int)0, (Object)arrparity2, (int)0, (int)n);
            return arrparity2;
        }
    }

    public static final class StopBits
    extends Enum<StopBits> {
        private static final /* synthetic */ StopBits[] ENUM$VALUES;
        public static final /* enum */ StopBits ONE = new StopBits("ONE", 0);
        public static final /* enum */ StopBits TWO = new StopBits("TWO", 1);

        static {
            StopBits[] arrstopBits = new StopBits[]{ONE, TWO};
            ENUM$VALUES = arrstopBits;
        }

        private StopBits(String string3, int n2) {
            super(string2, n);
        }

        public static StopBits valueOf(String string2) {
            return (StopBits)Enum.valueOf((Class)StopBits.class, (String)string2);
        }

        public static StopBits[] values() {
            StopBits[] arrstopBits = ENUM$VALUES;
            int n = arrstopBits.length;
            StopBits[] arrstopBits2 = new StopBits[n];
            System.arraycopy((Object)arrstopBits, (int)0, (Object)arrstopBits2, (int)0, (int)n);
            return arrstopBits2;
        }
    }

}

