/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.os.Binder
 *  android.os.Bundle
 *  android.os.IBinder
 *  java.io.IOException
 *  java.io.InputStream
 *  java.io.OutputStream
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.IllegalStateException
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.Thread
 */
package com.daydr3am.lib;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import com.daydr3am.OWS.SelectSetting;
import com.daydr3am.lib.Service;
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

public class IOService
extends IOIOService {
    public static boolean doResetWIFI;
    public static boolean lockbox;
    public static boolean uselight;
    Activity activity;
    boolean betweenSilenAlarm = false;
    private final Binder binder;
    boolean enableInput = false;
    ReceiveMoney moneyReceiver;
    IOClass myio;

    static {
        uselight = false;
        doResetWIFI = false;
    }

    public IOService() {
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
    }

    private class IOClass
    extends BaseIOIOLooper
    implements Runnable {
        int Sum;
        byte[] bankInput;
        byte[] bankKeyCode;
        int bankMoney;
        Thread bankThread;
        InputStream bin;
        OutputStream bout;
        private Uart buart;
        boolean catchingDoorSensor;
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
        DigitalInput leg9;
        boolean oldBoxStatus;
        boolean stopThread;
        Runnable tempuratureControl;

        public IOClass() {
            this.bankInput = new byte[2];
            this.indexBank = -1;
            this.bankMoney = 0;
            this.coinInput = new byte[6];
            this.indexCoin = -1;
            this.Sum = 0;
            this.bankKeyCode = new byte[]{64, 65, 66, 67, 68};
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

                static /* synthetic */ IOClass access$0(IOClass var0) {
                    return var0.IOClass.this;
                }

                /*
                 * Loose catch block
                 * Enabled aggressive block sorting
                 * Enabled unnecessary exception pruning
                 * Enabled aggressive exception aggregation
                 * Lifted jumps to return sites
                 */
                public void run() {
                    do {
                        if (!IOClass.this.stopThread) {
                            return;
                        }
                        try {
                            boolean bl = !IOClass.this.leg9.read();
                            SharedPreferences sharedPreferences = IOClass.access$1((IOClass)IOClass.this).activity.getSharedPreferences("hello", 0);
                            if (bl ^ IOClass.this.oldBoxStatus) {
                                Bundle bundle = new Bundle();
                                if (bl) {
                                    bundle.putString("Status", "CLOSE");
                                    bundle.putString("Password", "3333");
                                    if (sharedPreferences.getBoolean("autoReset", false)) {
                                        Service.resetAmount(bundle);
                                    }
                                    Service.setBoxStatus(bundle);
                                } else {
                                    bundle.putString("Status", "OPEN");
                                    Service.setBoxStatus(bundle);
                                }
                            }
                            IOClass.this.oldBoxStatus = bl;
                            if (IOClass.this.catchingDoorSensor != bl && IOClass.access$1((IOClass)IOClass.this).activity.getClass().equals((Object)SelectSetting.class) && ((SelectSetting)IOClass.access$1((IOClass)IOClass.this).activity).activityActive) {
                                IOClass.access$1((IOClass)IOClass.this).activity.runOnUiThread(new Runnable(){

                                    /*
                                     * Enabled aggressive block sorting
                                     */
                                    public void run() {
                                        String string2 = IOClass.access$0((IOClass)2.this).catchingDoorSensor ? "Door close " : "Door open";
                                        new AlertDialog.Builder((Context)IOClass.access$1((IOClass)IOClass.access$0((IOClass)2.this)).activity).setTitle((CharSequence)"").setMessage((CharSequence)string2).setNegativeButton(17039369, new DialogInterface.OnClickListener(){

                                            public void onClick(DialogInterface dialogInterface, int n) {
                                            }
                                        }).show();
                                    }

                                });
                            }
                            IOClass.this.catchingDoorSensor = bl;
                            if (!bl && IOService.lockbox) {
                                if (!IOClass.access$1((IOClass)IOClass.this).betweenSilenAlarm && IOClass.access$1((IOClass)IOClass.this).activity.getClass().equals((Object)SelectSetting.class)) {
                                    Service.sirenAlarm(new Bundle());
                                }
                                IOClass.access$1((IOClass)IOClass.this).betweenSilenAlarm = true;
                                IOClass.this.leg11.write(true);
                            } else {
                                IOClass.access$1((IOClass)IOClass.this).betweenSilenAlarm = false;
                                IOClass.this.leg11.write(false);
                            }
                        }
                        catch (InterruptedException var5_4) {
                            IOClass.this.IOService.this.showText((Object)var5_4 + "4 " + " " + IOClass.this.ioio_);
                            var5_4.printStackTrace();
                        }
                        catch (ConnectionLostException var3_6) {
                            IOClass.this.IOService.this.showText((Object)((Object)var3_6) + "3 " + " " + IOClass.this.ioio_);
                        }
                        try {
                            Thread.sleep((long)5000);
                        }
                        catch (InterruptedException var4_2) {
                            var4_2.printStackTrace();
                        }
                    } while (true);
                    catch (IllegalStateException illegalStateException) {
                        IOClass.this.IOService.this.showText((Object)illegalStateException + "2 " + " " + IOClass.this.ioio_);
                        return;
                    }
                    catch (Exception exception) {
                        IOClass.this.IOService.this.showText((Object)exception + "1 " + " " + IOClass.this.ioio_);
                        return;
                    }
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
                        if ((double)var2_1 < 3.035) {
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
            this.count2 = 0;
        }

        public void accepctBank() {
            try {
                byte[] arrby = new byte[]{2};
                this.bout.write(arrby);
                IOService.this.showText("write3");
                return;
            }
            catch (IOException var2_2) {
                IOService.this.showText("error " + (Object)var2_2);
                var2_2.printStackTrace();
                return;
            }
        }

        public void checkResetWIFI() {
            new Thread(new Runnable(){

                public void run() {
                    try {
                        do {
                            if (IOService.doResetWIFI) {
                                IOClass.this.leg16.write(true);
                                Thread.sleep((long)500);
                                IOClass.this.leg16.write(false);
                                IOService.doResetWIFI = false;
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

        public void closeLight() {
            try {
                if (this.leg12 != null && this.leg13 != null) {
                    this.leg14.write(true);
                    this.leg13.write(false);
                    this.leg12.write(false);
                    this.leg0.write(false);
                }
                return;
            }
            catch (ConnectionLostException var1_1) {
                IOService.this.showText("connect lost " + (Object)((Object)var1_1));
                var1_1.printStackTrace();
                return;
            }
        }

        public void disable() {
            try {
                this.leg10.write(false);
                return;
            }
            catch (ConnectionLostException var1_1) {
                var1_1.printStackTrace();
                return;
            }
        }

        public void disableBank() {
            if (this.bout == null) {
                IOService.this.showText("bout null");
                return;
            }
            try {
                byte[] arrby = new byte[]{94};
                this.bout.write(arrby);
                IOService.this.showText("write2");
                return;
            }
            catch (IOException var2_2) {
                IOService.this.showText("error " + (Object)var2_2);
                var2_2.printStackTrace();
                return;
            }
        }

        public void disableCoin() {
            if (this.cout == null) {
                IOService.this.showText("cout null");
                return;
            }
            try {
                byte[] arrby = new byte[]{-112, 5, 2, 3, -102};
                this.cout.write(arrby);
                this.cout.flush();
                return;
            }
            catch (IOException var2_2) {
                IOService.this.showText("error " + (Object)var2_2);
                var2_2.printStackTrace();
                return;
            }
        }

        public void enable() {
            try {
                this.leg10.write(true);
                Thread.sleep((long)2000);
                this.enableBank();
                this.enableCoin();
                this.bankMoney = 0;
                this.coinMoney = 0;
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

        public void enableBank() {
            try {
                byte[] arrby = new byte[]{2};
                this.bout.write(arrby);
                arrby[0] = 62;
                this.bout.write(arrby);
                return;
            }
            catch (IOException var3_2) {
                IOService.this.showText("error " + (Object)var3_2);
                var3_2.printStackTrace();
                return;
            }
            catch (Exception var2_3) {
                IOService.this.showText("error " + (Object)var2_3);
                return;
            }
        }

        public void enableCoin() {
            try {
                byte[] arrby = new byte[]{-112, 5, 1, 3, -103};
                this.cout.write(arrby);
                byte[] arrby2 = new byte[]{-112, 5, 17, 3, -87};
                this.cout.write(arrby2);
                IOService.this.showText("new coin");
                return;
            }
            catch (IOException var3_3) {
                IOService.this.showText("error " + (Object)var3_3);
                var3_3.printStackTrace();
                return;
            }
            catch (Exception var2_4) {
                IOService.this.showText("exc " + (Object)var2_4);
                return;
            }
        }

        /*
         * Exception decompiling
         */
        @Override
        public void loop() throws ConnectionLostException, InterruptedException {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.CannotPerformDecode: reachable test BLOCK was exited and re-entered.
            // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.getFarthestReachableInRange(Misc.java:143)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:385)
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

        public void openLight() throws ConnectionLostException {
            if (IOService.uselight) {
                this.leg14.write(false);
                this.leg13.write(true);
                this.leg12.write(true);
                this.leg0.write(true);
                return;
            }
            this.leg14.write(true);
        }

        public void rejectBank() {
            try {
                byte[] arrby = new byte[]{15};
                this.bout.write(arrby);
                IOService.this.showText("write4");
                return;
            }
            catch (IOException var2_2) {
                IOService.this.showText("error " + (Object)var2_2);
                var2_2.printStackTrace();
                return;
            }
        }

        public void resetCoin() {
            try {
                this.leg10.write(false);
                Thread.sleep((long)100);
                this.leg10.write(true);
                this.bankMoney = 0;
                this.coinMoney = 0;
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
            IOService.this.showText("setup " + (Object)IOService.this.activity);
            try {
                this.leg9 = this.ioio_.openDigitalInput(9);
                this.leg10 = this.ioio_.openDigitalOutput(10, false);
                IOService.this.showText("leg10 " + this.leg10);
                this.leg13 = this.ioio_.openDigitalOutput(13, true);
                this.leg12 = this.ioio_.openDigitalOutput(12, true);
                this.leg11 = this.ioio_.openDigitalOutput(11, false);
                this.leg0 = this.ioio_.openDigitalOutput(0, false);
                this.leg14 = this.ioio_.openDigitalOutput(14, true);
                this.leg16 = this.ioio_.openDigitalOutput(16, false);
                this.leg15 = this.ioio_.openDigitalOutput(15, false);
                this.leg46 = this.ioio_.openAnalogInput(46);
                this.buart = this.ioio_.openUart(5, 6, 9600, Uart.Parity.EVEN, Uart.StopBits.ONE);
                this.cuart = this.ioio_.openUart(3, 4, 9600, Uart.Parity.NONE, Uart.StopBits.ONE);
                this.cin = this.cuart.getInputStream();
                this.cout = this.cuart.getOutputStream();
                this.bin = this.buart.getInputStream();
                this.bout = this.buart.getOutputStream();
                this.bin = this.buart.getInputStream();
                this.bankThread = new Thread((Runnable)this);
                this.bankThread.start();
                this.led = new Thread(this.ledRun);
                this.led.start();
                this.checkBox = new Thread(this.checkOpenBox);
                this.checkBox.start();
                new Thread(this.tempuratureControl).start();
                this.checkResetWIFI();
                if (IOService.this.enableInput) {
                    IOService.this.activity.runOnUiThread(new Runnable(){

                        public void run() {
                            IOClass.this.enableBank();
                            IOClass.this.enableCoin();
                        }
                    });
                }
                return;
            }
            catch (Exception var1_1) {
                IOService.this.showText("setup crash" + (Object)var1_1);
                var1_1.printStackTrace();
                return;
            }
        }

    }

    public class LocalBinder
    extends Binder
    implements SettingService {
        @Override
        public void disableBox() {
            IOService.this.myio.disable();
        }

        @Override
        public void enableBox() {
            IOService.this.myio.enable();
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void enableInput(boolean bl) {
            IOService.this.enableInput = bl;
            if (bl && IOService.this.myio != null) {
                IOService.this.myio.enableBank();
                IOService.this.myio.enableCoin();
                return;
            } else {
                if (IOService.this.myio == null) return;
                {
                    IOService.this.myio.disable();
                    return;
                }
            }
        }

        @Override
        public void serviceSetup(Activity activity) {
            IOService.this.activity = activity;
        }

        @Override
        public void setupReceiveMoney(ReceiveMoney receiveMoney) {
            IOService.this.moneyReceiver = receiveMoney;
        }

        @Override
        public void setupboxStatue(boolean bl) {
            IOService.this.showText("setup lockbox" + bl);
            IOService.lockbox = bl;
        }

        @Override
        public void testwrite(String string2) {
        }

        @Override
        public void writeConfirmOrder(Bundle bundle) {
        }
    }

    public static interface ReceiveMoney {
        public void receiveBank(int var1);

        public void receiveCoin(int var1);

        public void receiveText(String var1);
    }

    public static interface SettingService {
        public void disableBox();

        public void enableBox();

        public void enableInput(boolean var1);

        public void serviceSetup(Activity var1);

        public void setupReceiveMoney(ReceiveMoney var1);

        public void setupboxStatue(boolean var1);

        public void testwrite(String var1);

        public void writeConfirmOrder(Bundle var1);
    }

}

