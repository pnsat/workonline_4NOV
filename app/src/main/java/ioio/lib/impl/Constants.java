/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package ioio.lib.impl;

class Constants {
    static final int BUFFER_SIZE = 1024;
    static final int[] ICSP_PINS;
    static final int[] INCAP_MODULES_DOUBLE;
    static final int[] INCAP_MODULES_SINGLE;
    static final int NUM_ANALOG_PINS = 16;
    static final int NUM_PINS = 49;
    static final int NUM_PWM_MODULES = 9;
    static final int NUM_SPI_MODULES = 3;
    static final int NUM_TWI_MODULES = 3;
    static final int NUM_UART_MODULES = 4;
    static final int PACKET_BUFFER_SIZE = 256;
    static final int[][] TWI_PINS;

    static {
        int[] arrn = new int[3];
        arrn[1] = 2;
        arrn[2] = 4;
        INCAP_MODULES_DOUBLE = arrn;
        INCAP_MODULES_SINGLE = new int[]{6, 7, 8};
        TWI_PINS = new int[][]{{4, 5}, {47, 48}, {26, 25}};
        ICSP_PINS = new int[]{36, 37, 38};
    }

    Constants() {
    }
}

