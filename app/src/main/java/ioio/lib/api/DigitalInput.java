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

public interface DigitalInput
extends Closeable {
    public boolean read() throws InterruptedException, ConnectionLostException;

    public void waitForValue(boolean var1) throws InterruptedException, ConnectionLostException;

    public static class Spec {
        public Mode mode;
        public int pin;

        public Spec(int n) {
            super(n, Mode.FLOATING);
        }

        public Spec(int n, Mode mode) {
            this.pin = n;
            this.mode = mode;
        }

        public static final class Mode
        extends Enum<Mode> {
            private static final /* synthetic */ Mode[] ENUM$VALUES;
            public static final /* enum */ Mode FLOATING = new Mode("FLOATING", 0);
            public static final /* enum */ Mode PULL_DOWN;
            public static final /* enum */ Mode PULL_UP;

            static {
                PULL_UP = new Mode("PULL_UP", 1);
                PULL_DOWN = new Mode("PULL_DOWN", 2);
                Mode[] arrmode = new Mode[]{FLOATING, PULL_UP, PULL_DOWN};
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

