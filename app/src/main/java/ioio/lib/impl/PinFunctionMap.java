/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.IllegalArgumentException
 *  java.lang.Object
 *  java.lang.String
 */
package ioio.lib.impl;

class PinFunctionMap {
    private static final boolean[] PERIPHERAL_IN;
    private static final boolean[] PERIPHERAL_OUT;

    static {
        boolean[] arrbl = new boolean[49];
        arrbl[0] = true;
        arrbl[3] = true;
        arrbl[4] = true;
        arrbl[5] = true;
        arrbl[6] = true;
        arrbl[7] = true;
        arrbl[10] = true;
        arrbl[11] = true;
        arrbl[12] = true;
        arrbl[13] = true;
        arrbl[14] = true;
        arrbl[27] = true;
        arrbl[28] = true;
        arrbl[29] = true;
        arrbl[30] = true;
        arrbl[31] = true;
        arrbl[32] = true;
        arrbl[34] = true;
        arrbl[35] = true;
        arrbl[36] = true;
        arrbl[37] = true;
        arrbl[38] = true;
        arrbl[39] = true;
        arrbl[40] = true;
        arrbl[45] = true;
        arrbl[46] = true;
        arrbl[47] = true;
        arrbl[48] = true;
        PERIPHERAL_OUT = arrbl;
        boolean[] arrbl2 = new boolean[49];
        arrbl2[0] = true;
        arrbl2[3] = true;
        arrbl2[4] = true;
        arrbl2[5] = true;
        arrbl2[6] = true;
        arrbl2[7] = true;
        arrbl2[9] = true;
        arrbl2[10] = true;
        arrbl2[11] = true;
        arrbl2[12] = true;
        arrbl2[13] = true;
        arrbl2[14] = true;
        arrbl2[27] = true;
        arrbl2[28] = true;
        arrbl2[29] = true;
        arrbl2[30] = true;
        arrbl2[31] = true;
        arrbl2[32] = true;
        arrbl2[34] = true;
        arrbl2[35] = true;
        arrbl2[36] = true;
        arrbl2[37] = true;
        arrbl2[38] = true;
        arrbl2[39] = true;
        arrbl2[40] = true;
        arrbl2[45] = true;
        arrbl2[46] = true;
        arrbl2[47] = true;
        arrbl2[48] = true;
        PERIPHERAL_IN = arrbl2;
    }

    PinFunctionMap() {
    }

    static void checkSupportsAnalogInput(int n) {
        PinFunctionMap.checkValidPin(n);
        if (n < 31 || n > 46) {
            throw new IllegalArgumentException("Pin " + n + " does not support analog input");
        }
    }

    static void checkSupportsPeripheralInput(int n) {
        PinFunctionMap.checkValidPin(n);
        if (!PERIPHERAL_IN[n]) {
            throw new IllegalArgumentException("Pin " + n + " does not support peripheral input");
        }
    }

    static void checkSupportsPeripheralOutput(int n) {
        PinFunctionMap.checkValidPin(n);
        if (!PERIPHERAL_OUT[n]) {
            throw new IllegalArgumentException("Pin " + n + " does not support peripheral output");
        }
    }

    static void checkValidPin(int n) {
        if (n < 0 || n > 48) {
            throw new IllegalArgumentException("Illegal pin: " + n);
        }
    }
}

