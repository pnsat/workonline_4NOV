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

public interface TwiMaster
extends Closeable {
    public boolean writeRead(int var1, boolean var2, byte[] var3, int var4, byte[] var5, int var6) throws ConnectionLostException, InterruptedException;

    public Result writeReadAsync(int var1, boolean var2, byte[] var3, int var4, byte[] var5, int var6) throws ConnectionLostException;

    public static final class Rate
    extends Enum<Rate> {
        private static final /* synthetic */ Rate[] ENUM$VALUES;
        public static final /* enum */ Rate RATE_100KHz = new Rate("RATE_100KHz", 0);
        public static final /* enum */ Rate RATE_1MHz;
        public static final /* enum */ Rate RATE_400KHz;

        static {
            RATE_400KHz = new Rate("RATE_400KHz", 1);
            RATE_1MHz = new Rate("RATE_1MHz", 2);
            Rate[] arrrate = new Rate[]{RATE_100KHz, RATE_400KHz, RATE_1MHz};
            ENUM$VALUES = arrrate;
        }

        private Rate(String string3, int n2) {
            super(string2, n);
        }

        public static Rate valueOf(String string2) {
            return (Rate)Enum.valueOf((Class)Rate.class, (String)string2);
        }

        public static Rate[] values() {
            Rate[] arrrate = ENUM$VALUES;
            int n = arrrate.length;
            Rate[] arrrate2 = new Rate[n];
            System.arraycopy((Object)arrrate, (int)0, (Object)arrrate2, (int)0, (int)n);
            return arrrate2;
        }
    }

    public static interface Result {
        public boolean waitReady() throws ConnectionLostException, InterruptedException;
    }

}

