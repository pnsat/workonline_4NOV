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

public interface SpiMaster
extends Closeable {
    public void writeRead(int var1, byte[] var2, int var3, int var4, byte[] var5, int var6) throws ConnectionLostException, InterruptedException;

    public void writeRead(byte[] var1, int var2, int var3, byte[] var4, int var5) throws ConnectionLostException, InterruptedException;

    public Result writeReadAsync(int var1, byte[] var2, int var3, int var4, byte[] var5, int var6) throws ConnectionLostException;

    public static class Config {
        public boolean invertClk;
        public Rate rate;
        public boolean sampleOnTrailing;

        public Config(Rate rate) {
            super(rate, false, false);
        }

        public Config(Rate rate, boolean bl, boolean bl2) {
            this.rate = rate;
            this.invertClk = bl;
            this.sampleOnTrailing = bl2;
        }
    }

    public static final class Rate
    extends Enum<Rate> {
        private static final /* synthetic */ Rate[] ENUM$VALUES;
        public static final /* enum */ Rate RATE_125K;
        public static final /* enum */ Rate RATE_142K;
        public static final /* enum */ Rate RATE_166K;
        public static final /* enum */ Rate RATE_1M;
        public static final /* enum */ Rate RATE_1_3M;
        public static final /* enum */ Rate RATE_200K;
        public static final /* enum */ Rate RATE_250K;
        public static final /* enum */ Rate RATE_2M;
        public static final /* enum */ Rate RATE_2_2M;
        public static final /* enum */ Rate RATE_2_6M;
        public static final /* enum */ Rate RATE_31K;
        public static final /* enum */ Rate RATE_333K;
        public static final /* enum */ Rate RATE_35K;
        public static final /* enum */ Rate RATE_3_2M;
        public static final /* enum */ Rate RATE_41K;
        public static final /* enum */ Rate RATE_4M;
        public static final /* enum */ Rate RATE_500K;
        public static final /* enum */ Rate RATE_50K;
        public static final /* enum */ Rate RATE_571K;
        public static final /* enum */ Rate RATE_5_3M;
        public static final /* enum */ Rate RATE_62K;
        public static final /* enum */ Rate RATE_666K;
        public static final /* enum */ Rate RATE_800K;
        public static final /* enum */ Rate RATE_83K;
        public static final /* enum */ Rate RATE_8M;

        static {
            RATE_31K = new Rate("RATE_31K", 0);
            RATE_35K = new Rate("RATE_35K", 1);
            RATE_41K = new Rate("RATE_41K", 2);
            RATE_50K = new Rate("RATE_50K", 3);
            RATE_62K = new Rate("RATE_62K", 4);
            RATE_83K = new Rate("RATE_83K", 5);
            RATE_125K = new Rate("RATE_125K", 6);
            RATE_142K = new Rate("RATE_142K", 7);
            RATE_166K = new Rate("RATE_166K", 8);
            RATE_200K = new Rate("RATE_200K", 9);
            RATE_250K = new Rate("RATE_250K", 10);
            RATE_333K = new Rate("RATE_333K", 11);
            RATE_500K = new Rate("RATE_500K", 12);
            RATE_571K = new Rate("RATE_571K", 13);
            RATE_666K = new Rate("RATE_666K", 14);
            RATE_800K = new Rate("RATE_800K", 15);
            RATE_1M = new Rate("RATE_1M", 16);
            RATE_1_3M = new Rate("RATE_1_3M", 17);
            RATE_2M = new Rate("RATE_2M", 18);
            RATE_2_2M = new Rate("RATE_2_2M", 19);
            RATE_2_6M = new Rate("RATE_2_6M", 20);
            RATE_3_2M = new Rate("RATE_3_2M", 21);
            RATE_4M = new Rate("RATE_4M", 22);
            RATE_5_3M = new Rate("RATE_5_3M", 23);
            RATE_8M = new Rate("RATE_8M", 24);
            Rate[] arrrate = new Rate[]{RATE_31K, RATE_35K, RATE_41K, RATE_50K, RATE_62K, RATE_83K, RATE_125K, RATE_142K, RATE_166K, RATE_200K, RATE_250K, RATE_333K, RATE_500K, RATE_571K, RATE_666K, RATE_800K, RATE_1M, RATE_1_3M, RATE_2M, RATE_2_2M, RATE_2_6M, RATE_3_2M, RATE_4M, RATE_5_3M, RATE_8M};
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
        public void waitReady() throws ConnectionLostException, InterruptedException;
    }

}

