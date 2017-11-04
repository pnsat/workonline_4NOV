/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Intent
 *  android.graphics.Bitmap
 *  android.graphics.Color
 *  android.os.Binder
 *  android.os.Bundle
 *  android.os.IBinder
 *  android.util.Log
 *  java.io.IOException
 *  java.io.InputStream
 *  java.io.OutputStream
 *  java.lang.Exception
 *  java.lang.InterruptedException
 *  java.lang.NullPointerException
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.Thread
 */
package com.daydr3am.lib;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.daydr3am.lib.IOService;
import ioio.lib.api.AnalogInput;
import ioio.lib.api.DigitalInput;
import ioio.lib.api.DigitalOutput;
import ioio.lib.api.IOIO;
import ioio.lib.api.Uart;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOService;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOServiceBV20
extends IOIOService {
    public static Activity activity;
    public static boolean doResetWIFI;
    public static int hour;
    public static boolean lockbox;
    public static boolean uselight;
    boolean betweenSilenAlarm = false;
    private final Binder binder;
    boolean enableInput = false;
    IOService.ReceiveMoney moneyReceiver;
    IOClass myio;
    int resetCount;
    public boolean sendSMS;

    static {
        uselight = false;
        doResetWIFI = false;
        hour = 0;
    }

    public IOServiceBV20() {
        this.binder = new LocalBinder();
    }

    @Override
    protected IOIOLooper createIOIOLooper() {
        IOClass iOClass;
        this.showText("create io");
        this.myio = iOClass = new IOClass();
        return iOClass;
    }

    public IBinder onBind(Intent intent) {
        this.showText("bind " + (Object)this.binder);
        return this.binder;
    }

    @Override
    public void onCreate() {
    }

    public void showText(String string2) {
        Log.e((String)"hello", (String)("showtext " + string2));
    }

    private class IOClass
    extends BaseIOIOLooper
    implements Runnable {
        int Sum;
        byte[] arraySet;
        byte backupTmp;
        byte[] bankInput;
        byte[] bankKeyCode;
        int bankMoney;
        Thread bankThread;
        Thread barCode;
        Runnable barCodeRun;
        InputStream barin;
        private Uart baruart;
        InputStream bin;
        OutputStream bout;
        private Uart buart;
        Thread checkBox;
        Runnable checkOpenBox;
        InputStream cin;
        byte[] coinInput;
        int coinMoney;
        int count;
        int count2;
        OutputStream cout;
        private Uart cuart;
        int indexBank;
        int indexCoin;
        Thread led;
        Runnable ledRun;
        DigitalOutput leg0;
        DigitalOutput leg10;
        DigitalOutput leg11;
        DigitalOutput leg12;
        DigitalOutput leg13;
        DigitalOutput leg14;
        DigitalOutput leg15;
        DigitalOutput leg16;
        AnalogInput leg46;
        DigitalOutput leg7;
        DigitalInput leg9;
        boolean oldBoxStatus;
        OutputStream printout;
        private Uart printuart;
        boolean stopThread;
        Runnable tempuratureControl;

        public IOClass() {
            this.bankInput = new byte[2];
            this.indexBank = -1;
            this.bankMoney = 0;
            this.coinInput = new byte[6];
            this.indexCoin = -1;
            this.Sum = 0;
            this.bankKeyCode = new byte[]{1, 2, 3, 4, 5};
            this.stopThread = true;
            this.ledRun = new Runnable(){

                /*
                 * Exception decompiling
                 */
                public void run() {
                    // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
                    // java.util.ConcurrentModificationException
                    // java.util.LinkedList$ReverseLinkIterator.next(LinkedList.java:217)
                    // org.benf.cfr.reader.bytecode.analysis.structured.statement.Block.extractLabelledBlocks(Block.java:212)
                    // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement$LabelledBlockExtractor.transform(Op04StructuredStatement.java:485)
                    // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.transform(Op04StructuredStatement.java:639)
                    // org.benf.cfr.reader.bytecode.analysis.structured.statement.Block.transformStructuredChildren(Block.java:378)
                    // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement$LabelledBlockExtractor.transform(Op04StructuredStatement.java:487)
                    // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.transform(Op04StructuredStatement.java:639)
                    // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.insertLabelledBlocks(Op04StructuredStatement.java:649)
                    // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:816)
                    // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
                    // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
                    // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
                    // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
                    // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
                    // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
                    // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
                    // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
                    // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
                    // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
                    // org.benf.cfr.reader.Main.doJar(Main.java:128)
                    // com.njlabs.showjava.processor.JavaExtractor$1.run(JavaExtractor.java:100)
                    // java.lang.Thread.run(Thread.java:818)
                    throw new IllegalStateException("Decompilation failed");
                }
            };
            this.checkOpenBox = new Runnable(){

                /*
                 * Exception decompiling
                 */
                public void run() {
                    // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
                    // org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
                    // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:371)
                    // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:449)
                    // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
                    // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
                    // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
                    // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
                    // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
                    // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
                    // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
                    // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
                    // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
                    // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
                    // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
                    // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
                    // org.benf.cfr.reader.Main.doJar(Main.java:128)
                    // com.njlabs.showjava.processor.JavaExtractor$1.run(JavaExtractor.java:100)
                    // java.lang.Thread.run(Thread.java:818)
                    throw new IllegalStateException("Decompilation failed");
                }
            };
            this.tempuratureControl = new Runnable(){

                /*
                 * Unable to fully structure code
                 * Enabled aggressive block sorting
                 * Enabled unnecessary exception pruning
                 * Enabled aggressive exception aggregation
                 * Lifted jumps to return sites
                 */
                public void run() {
                    do lbl-1000: // 3 sources:
                    {
                        if (!IOClass.this.stopThread) {
                            return;
                        }
                        var2_1 = IOClass.this.leg46.getVoltage();
                        break;
                    } while (true);
                    catch (Exception var1_3) {
                        IOClass.access$1(IOClass.this).showText((Object)var1_3 + "1 " + " " + IOClass.access$0(IOClass.this));
                        return;
                    }
                    {
                        if ((double)var2_1 > 3.04) {
                            IOClass.this.leg15.write(true);
                        }
                        if ((double)var2_1 < 3.02) {
                            IOClass.this.leg15.write(false);
                        }
                        try {
                            Thread.sleep((long)1000);
                        }
                        catch (InterruptedException var3_2) {
                            var3_2.printStackTrace();
                        }
                        ** while (true)
                    }
                }
            };
            this.arraySet = new byte[0];
            this.barCodeRun = new Runnable(){

                /*
                 * Exception decompiling
                 */
                public void run() {
                    // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
                    // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [9[UNCONDITIONALDOLOOP]], but top level block is 3[TRYBLOCK]
                    // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
                    // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
                    // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
                    // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
                    // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
                    // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
                    // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
                    // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
                    // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
                    // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
                    // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
                    // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
                    // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
                    // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
                    // org.benf.cfr.reader.Main.doJar(Main.java:128)
                    // com.njlabs.showjava.processor.JavaExtractor$1.run(JavaExtractor.java:100)
                    // java.lang.Thread.run(Thread.java:818)
                    throw new IllegalStateException("Decompilation failed");
                }

            };
            this.count2 = 0;
        }

        static /* synthetic */ IOIO access$0(IOClass iOClass) {
            return iOClass.ioio_;
        }

        private void sendMoney() {
            IOServiceBV20.activity.runOnUiThread(new Runnable(){

                public void run() {
                    IOClass.access$1((IOClass)IOClass.this).moneyReceiver.receiveBank(IOClass.this.bankMoney);
                }
            });
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        public void AcceptorPowerOn() {
            this.leg10.write(true);
            this.leg7.write(true);
            var3_1 = 0;
            do {
                if (var3_1 < 10) ** GOTO lbl11
                Thread.sleep((long)3000);
                var4_2 = 0;
                ** GOTO lbl26
lbl11: // 1 sources:
                Thread.sleep((long)100);
                this.disableCoin();
                ++var3_1;
                continue;
                break;
            } while (true);
lbl-1000: // 1 sources:
            {
                try {
                    Thread.sleep((long)100);
                    this.disableBank();
                    ++var4_2;
                    continue;
                }
                catch (ConnectionLostException var2_3) {
                    var2_3.printStackTrace();
                    return;
                }
                catch (InterruptedException var1_4) {
                    var1_4.printStackTrace();
                    return;
                }
lbl26: // 2 sources:
                ** while (var4_2 < 10)
            }
lbl27: // 1 sources:
        }

        /*
         * Loose catch block
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        public void PrintBmp(int n, Bitmap bitmap) throws IOException {
            byte[] arrby = new byte[]{13, 10};
            byte[] arrby2 = new byte[8];
            arrby2[0] = 29;
            arrby2[1] = 118;
            arrby2[2] = 48;
            arrby2[3] = 48;
            arrby2[6] = 1;
            int n2 = n + bitmap.getWidth();
            int n3 = bitmap.getHeight();
            if (n2 > 384) {
                n2 = 384;
            }
            int n4 = (n2 + 7) / 8;
            byte[] arrby3 = new byte[n4];
            byte by = (byte)(n4 % 256);
            byte by2 = (byte)(n4 / 256);
            arrby2[4] = by;
            arrby2[5] = by2;
            arrby2[6] = (byte)(n3 % 256);
            arrby2[7] = (byte)(n3 / 256);
            this.printout.write(arrby2);
            int n5 = 0;
            while (n5 < n3) {
                int n6 = 0;
                do {
                    if (n6 >= n4) break;
                    arrby3[n6] = 0;
                    ++n6;
                } while (true);
                int n7 = n;
                do {
                    if (n7 >= n2) {
                        Thread.sleep((long)50);
                    }
                    int n8 = bitmap.getPixel(n7 - n, n5);
                    if (Color.red((int)n8) == 0 || Color.green((int)n8) == 0 || Color.blue((int)n8) == 0) {
                        int n9 = n7 / 8;
                        arrby3[n9] = (byte)(arrby3[n9] + (128 >> n7 % 8));
                    }
                    ++n7;
                } while (true);
                catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                this.printout.write(arrby3);
                ++n5;
            }
            return;
        }

        /*
         * Exception decompiling
         */
        public void PrintImage(Bitmap var1) {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [5[UNCONDITIONALDOLOOP]], but top level block is 8[UNCONDITIONALDOLOOP]
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
            // org.benf.cfr.reader.Main.doJar(Main.java:128)
            // com.njlabs.showjava.processor.JavaExtractor$1.run(JavaExtractor.java:100)
            // java.lang.Thread.run(Thread.java:818)
            throw new IllegalStateException("Decompilation failed");
        }

        public void accepctBank() {
            try {
                byte[] arrby = new byte[]{-84};
                this.bout.write(arrby);
                IOServiceBV20.this.showText("write3");
                return;
            }
            catch (IOException var2_2) {
                IOServiceBV20.this.showText("error " + (Object)var2_2);
                var2_2.printStackTrace();
                return;
            }
        }

        public char[] bytesToHex(byte[] arrby, int n) {
            char[] arrc = new char[n];
            int n2 = 0;
            while (n2 < n) {
                arrc[n2] = arrby[n2];
                ++n2;
            }
            return arrc;
        }

        public void checkResetWIFI() {
            new Thread(new Runnable(){

                public void run() {
                    try {
                        do {
                            if (IOServiceBV20.doResetWIFI) {
                                IOClass.this.leg16.write(true);
                                Thread.sleep((long)500);
                                IOClass.this.leg16.write(false);
                                IOServiceBV20.doResetWIFI = false;
                            }
                            Thread.sleep((long)1000);
                        } while (true);
                    }
                    catch (ConnectionLostException var2_1) {
                        var2_1.printStackTrace();
                        return;
                    }
                    catch (InterruptedException var1_2) {
                        var1_2.printStackTrace();
                        return;
                    }
                }
            }).start();
        }

        public void closeCoin() {
            try {
                this.leg10.write(false);
                this.leg7.write(false);
                IOServiceBV20.this.showText("close receiver");
                return;
            }
            catch (ConnectionLostException var1_1) {
                var1_1.printStackTrace();
                return;
            }
        }

        public void closeLight() {
            try {
                if (this.leg12 != null && this.leg13 != null) {
                    this.leg13.write(false);
                    this.leg12.write(true);
                    this.leg0.write(false);
                }
                return;
            }
            catch (ConnectionLostException var1_1) {
                IOServiceBV20.this.showText("connect lost " + (Object)((Object)var1_1));
                var1_1.printStackTrace();
                return;
            }
        }

        public void disable() {
            try {
                this.disableCoin();
                Thread.sleep((long)1000);
                this.disableBank();
                if (this.leg10 != null) {
                    this.leg10.write(true);
                }
                if (this.leg7 != null) {
                    this.leg7.write(true);
                }
                return;
            }
            catch (ConnectionLostException var2_1) {
                var2_1.printStackTrace();
                return;
            }
            catch (InterruptedException var1_2) {
                var1_2.printStackTrace();
                return;
            }
        }

        public void disableBank() {
            if (this.bout == null) {
                IOServiceBV20.this.showText("bout null");
                return;
            }
            try {
                byte[] arrby = new byte[]{-71};
                this.bout.write(arrby);
                return;
            }
            catch (IOException var2_2) {
                IOServiceBV20.this.showText("error " + (Object)var2_2);
                var2_2.printStackTrace();
                return;
            }
        }

        public void disableCoin() {
            if (this.cout == null) {
                IOServiceBV20.this.showText("cout null");
                return;
            }
            try {
                byte[] arrby = new byte[]{-112, 5, 2, 3, -102};
                this.cout.write(arrby);
                this.cout.flush();
                return;
            }
            catch (IOException var2_2) {
                IOServiceBV20.this.showText("error " + (Object)var2_2);
                var2_2.printStackTrace();
                return;
            }
        }

        public void enable() {
            try {
                this.leg10.write(true);
                this.leg7.write(true);
                Thread.sleep((long)10);
                this.enableCoin();
                this.enableBank();
                this.bankMoney = 0;
                this.coinMoney = 0;
                return;
            }
            catch (ConnectionLostException var3_1) {
                var3_1.printStackTrace();
                return;
            }
            catch (InterruptedException var2_2) {
                var2_2.printStackTrace();
                return;
            }
            catch (NullPointerException var1_3) {
                var1_3.printStackTrace();
                return;
            }
        }

        public void enableBank() {
            try {
                byte[] arrby = new byte[]{-86};
                this.bout.write(arrby);
                Thread.sleep((long)100);
                byte[] arrby2 = new byte[]{-72};
                this.bout.write(arrby2);
                return;
            }
            catch (IOException var3_3) {
                IOServiceBV20.this.showText("error " + (Object)var3_3);
                var3_3.printStackTrace();
                return;
            }
            catch (Exception var2_4) {
                IOServiceBV20.this.showText("error " + (Object)var2_4);
                return;
            }
        }

        public void enableCoin() {
            try {
                byte[] arrby = new byte[]{-112, 5, 1, 3, -103};
                this.cout.write(arrby);
                byte[] arrby2 = new byte[]{-112, 5, 17, 3, -87};
                this.cout.write(arrby2);
                IOServiceBV20.this.showText("new coin");
                return;
            }
            catch (IOException var3_3) {
                IOServiceBV20.this.showText("error " + (Object)var3_3);
                var3_3.printStackTrace();
                return;
            }
            catch (Exception var2_4) {
                IOServiceBV20.this.showText("exc " + (Object)var2_4);
                return;
            }
        }

        public void endWrite() {
            try {
                byte[] arrby = new byte[]{27, 100, 5};
                this.printout.write(arrby);
                byte[] arrby2 = new byte[]{29, 86, 1};
                this.printout.write(arrby2);
                return;
            }
            catch (IOException var2_3) {
                var2_3.printStackTrace();
                return;
            }
        }

        public void enter() {
            try {
                this.printout.write(10);
                return;
            }
            catch (IOException var1_1) {
                var1_1.printStackTrace();
                return;
            }
        }

        /*
         * Exception decompiling
         */
        @Override
        public void loop() throws ConnectionLostException, InterruptedException {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [5[TRYBLOCK]], but top level block is 6[TRYBLOCK]
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
            // org.benf.cfr.reader.Main.doJar(Main.java:128)
            // com.njlabs.showjava.processor.JavaExtractor$1.run(JavaExtractor.java:100)
            // java.lang.Thread.run(Thread.java:818)
            throw new IllegalStateException("Decompilation failed");
        }

        public void openLight() throws ConnectionLostException {
            if (IOServiceBV20.uselight) {
                this.leg13.write(true);
                this.leg12.write(false);
                this.leg0.write(true);
                return;
            }
            this.leg12.write(true);
        }

        public void rejectBank() {
            try {
                byte[] arrby = new byte[]{-83};
                this.bout.write(arrby);
                IOServiceBV20.this.showText("write4");
                return;
            }
            catch (IOException var2_2) {
                IOServiceBV20.this.showText("error " + (Object)var2_2);
                var2_2.printStackTrace();
                return;
            }
        }

        public void resetCoin() {
            try {
                IOServiceBV20.this.showText("reset");
                IOServiceBV20 iOServiceBV20 = IOServiceBV20.this;
                iOServiceBV20.resetCount = 1 + iOServiceBV20.resetCount;
                this.leg10.write(false);
                this.leg7.write(false);
                Thread.sleep((long)100);
                this.leg10.write(true);
                this.leg7.write(true);
                this.bankMoney = 0;
                this.coinMoney = 0;
                Thread.sleep((long)10);
                this.enableBank();
                this.enableCoin();
                return;
            }
            catch (ConnectionLostException var2_2) {
                var2_2.printStackTrace();
                return;
            }
            catch (InterruptedException var1_3) {
                var1_3.printStackTrace();
                return;
            }
        }

        /*
         * Exception decompiling
         */
        public void run() {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.ConfusedCFRException: Extractable last case doesn't follow previous
            // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:486)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:65)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:422)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
            // org.benf.cfr.reader.Main.doJar(Main.java:128)
            // com.njlabs.showjava.processor.JavaExtractor$1.run(JavaExtractor.java:100)
            // java.lang.Thread.run(Thread.java:818)
            throw new IllegalStateException("Decompilation failed");
        }

        @Override
        protected void setup() throws ConnectionLostException, InterruptedException {
            IOServiceBV20.this.showText("setup BV20" + (Object)IOServiceBV20.activity);
            try {
                IOServiceBV20.this.resetCount = 0;
                this.leg9 = this.ioio_.openDigitalInput(9);
                this.leg10 = this.ioio_.openDigitalOutput(10, false);
                this.leg7 = this.ioio_.openDigitalOutput(7, false);
                IOServiceBV20.this.showText("leg10 " + this.leg10);
                this.leg13 = this.ioio_.openDigitalOutput(13, true);
                this.leg12 = this.ioio_.openDigitalOutput(12, true);
                this.leg11 = this.ioio_.openDigitalOutput(11, false);
                this.leg0 = this.ioio_.openDigitalOutput(0, false);
                this.leg14 = this.ioio_.openDigitalOutput(14, true);
                this.leg16 = this.ioio_.openDigitalOutput(16, false);
                this.leg15 = this.ioio_.openDigitalOutput(15, false);
                this.leg46 = this.ioio_.openAnalogInput(46);
                this.buart = this.ioio_.openUart(5, 6, 9600, Uart.Parity.NONE, Uart.StopBits.ONE);
                this.cuart = this.ioio_.openUart(3, 4, 9600, Uart.Parity.NONE, Uart.StopBits.ONE);
                this.baruart = this.ioio_.openUart(27, 28, 9600, Uart.Parity.NONE, Uart.StopBits.ONE);
                this.printuart = this.ioio_.openUart(29, 30, 9600, Uart.Parity.NONE, Uart.StopBits.ONE);
                this.cin = this.cuart.getInputStream();
                this.cout = this.cuart.getOutputStream();
                this.bin = this.buart.getInputStream();
                this.bout = this.buart.getOutputStream();
                this.barin = this.baruart.getInputStream();
                this.printout = this.printuart.getOutputStream();
                OutputStream outputStream = this.baruart.getOutputStream();
                this.bankThread = new Thread((Runnable)this);
                this.bankThread.start();
                this.led = new Thread(this.ledRun);
                this.led.start();
                this.checkBox = new Thread(this.checkOpenBox);
                this.checkBox.start();
                this.barCode = new Thread(this.barCodeRun);
                this.barCode.start();
                IOServiceBV20.this.showText("checkBox start" + IOServiceBV20.lockbox + " ");
                new Thread(this.tempuratureControl).start();
                this.checkResetWIFI();
                outputStream.write(102);
                this.AcceptorPowerOn();
                if (IOServiceBV20.this.enableInput) {
                    IOServiceBV20.activity.runOnUiThread(new Runnable(){

                        public void run() {
                            IOClass.this.enableBank();
                            IOClass.this.enableCoin();
                            IOClass.this.startupPrinter();
                        }
                    });
                }
                return;
            }
            catch (Exception var1_2) {
                IOServiceBV20.this.showText("setup crash" + (Object)var1_2);
                var1_2.printStackTrace();
                return;
            }
        }

        public void startupPrinter() {
            Log.e((String)"hello", (String)"startup printer");
            try {
                byte[] arrby = new byte[]{27, 55, 27, 116, 47};
                this.printout.write(arrby);
                byte[] arrby2 = new byte[]{27, 61, 1};
                this.printout.write(arrby2);
                byte[] arrby3 = new byte[]{29, 33, (byte)2040};
                this.printout.write(arrby3);
                return;
            }
            catch (IOException var3_4) {
                var3_4.printStackTrace();
                return;
            }
        }

        public void writeMessage(String string2) {
            try {
                byte[] arrby = string2.getBytes("tis-620");
                this.printout.write(arrby);
                this.printout.write(10);
                return;
            }
            catch (IOException var2_3) {
                var2_3.printStackTrace();
                return;
            }
        }

    }

    public class LocalBinder
    extends Binder
    implements IOService.SettingService {
        static /* synthetic */ IOServiceBV20 access$0(LocalBinder localBinder) {
            return localBinder.IOServiceBV20.this;
        }

        @Override
        public void disableBox() {
            IOServiceBV20.this.myio.disable();
        }

        @Override
        public void enableBox() {
            IOServiceBV20.this.myio.enable();
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void enableInput(boolean bl) {
            IOServiceBV20.this.enableInput = bl;
            if (bl && IOServiceBV20.this.myio != null) {
                IOServiceBV20.this.myio.enableBank();
                IOServiceBV20.this.myio.enableCoin();
                return;
            } else {
                if (IOServiceBV20.this.myio == null) return;
                {
                    IOServiceBV20.this.myio.disable();
                    return;
                }
            }
        }

        @Override
        public void serviceSetup(Activity activity) {
            IOServiceBV20.activity = activity;
            IOServiceBV20.this.showText("setup ac");
        }

        @Override
        public void setupReceiveMoney(IOService.ReceiveMoney receiveMoney) {
            IOServiceBV20.this.moneyReceiver = receiveMoney;
        }

        @Override
        public void setupboxStatue(boolean bl) {
            IOServiceBV20.this.showText("setup lockbox" + bl);
            IOServiceBV20.lockbox = bl;
        }

        @Override
        public void testwrite(String string2) {
            IOServiceBV20.this.myio.writeMessage(string2);
        }

        @Override
        public void writeConfirmOrder(Bundle bundle) {
            if (bundle == null) {
                bundle = new Bundle();
                bundle.putInt("OD", 12345);
                bundle.putString("Mobile", "0892533425");
                bundle.putString("Price", "80.70");
                bundle.putInt("OR", 10);
                bundle.putString("TotalPrice", "95");
                bundle.putInt("CD", 4);
                bundle.putString("TOPUPSTATUS", "\u0e2a\u0e33\u0e40\u0e23\u0e47\u0e08");
            }
            new Thread(new Runnable(){

                public void run() {
                    LocalBinder.access$0((LocalBinder)LocalBinder.this).myio.startupPrinter();
                    LocalBinder.access$0((LocalBinder)LocalBinder.this).myio.writeMessage("1234567890\u0e1f\u0e2b\u0e01\u0e14\u0e33\u0e1f\u0e2b\u0e01\u0e14\u0e23\u0e1f\u0e2b\u0e01\u0e14\u0e33\u0e1f\u0e2b\u0e01\u0e14\u0e23\u0e1f\u0e2b\u0e01\u0e14\u0e33\u0e1f\u0e2b\u0e01\u0e14\u0e23");
                    LocalBinder.access$0((LocalBinder)LocalBinder.this).myio.endWrite();
                }
            }).start();
        }

    }

}

