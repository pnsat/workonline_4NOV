/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package ioio.lib.api;

import ioio.lib.api.Closeable;
import ioio.lib.api.exception.ConnectionLostException;

public interface DigitalOutput
extends Closeable {
    public void write(boolean var1) throws ConnectionLostException;

    public static class Spec {
        public Mode mode;
        public int pin;

        public Spec(int n) {
            super(n, Mode.NORMAL);
        }

        public Spec(int n, Mode mode) {
            this.pin = n;
            this.mode = mode;
        }

        public static final class Mode
        extends Enum<Mode> {
            private static final /* synthetic */ Mode[] ENUM$VALUES;
            public static final /* enum */ Mode NORMAL = new Mode("NORMAL", 0);
            public static final /* enum */ Mode OPEN_DRAIN = new Mode("OPEN_DRAIN", 1);

            static {
                Mode[] arrmode = new Mode[]{NORMAL, OPEN_DRAIN};
                ENUM$VALUES = arrmode;
            }

            private Mode(String string3, int n2) {
                super(string2, n);
            }

            public static Mode valueOf(String string2) {
                return (Mode)Enum.valueOf((Class)Mode.class, (String)string2);
            }

            public static Mode[] values() {
                Mode[] arrmode = ENUM$VALUES;
                int n = arrmode.length;
                Mode[] arrmode2 = new Mode[n];
                System.arraycopy((Object)arrmode, (int)0, (Object)arrmode2, (int)0, (int)n);
                return arrmode2;
            }
        }

    }

}

