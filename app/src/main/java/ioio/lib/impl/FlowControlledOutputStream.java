/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.IOException
 *  java.io.OutputStream
 *  java.lang.Byte
 *  java.lang.InterruptedException
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.Thread
 *  java.util.concurrent.ArrayBlockingQueue
 *  java.util.concurrent.BlockingQueue
 */
package ioio.lib.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class FlowControlledOutputStream
extends OutputStream {
    private boolean closed_;
    private final int maxPacket_;
    private final byte[] packet_;
    private final BlockingQueue<Byte> queue_ = new ArrayBlockingQueue(1024);
    private int readyToSend_;
    private final Sender sender_;
    private final FlushThread thread_;

    public FlowControlledOutputStream(Sender sender, int n) {
        this.thread_ = (FlowControlledOutputStream)this.new FlushThread();
        this.readyToSend_ = 0;
        this.closed_ = false;
        this.sender_ = sender;
        this.maxPacket_ = n;
        this.packet_ = new byte[n];
        this.thread_.start();
    }

    static /* synthetic */ int access$0(FlowControlledOutputStream flowControlledOutputStream) {
        return flowControlledOutputStream.readyToSend_;
    }

    static /* synthetic */ BlockingQueue access$1(FlowControlledOutputStream flowControlledOutputStream) {
        return flowControlledOutputStream.queue_;
    }

    static /* synthetic */ int access$2(FlowControlledOutputStream flowControlledOutputStream) {
        return flowControlledOutputStream.maxPacket_;
    }

    static /* synthetic */ byte[] access$3(FlowControlledOutputStream flowControlledOutputStream) {
        return flowControlledOutputStream.packet_;
    }

    static /* synthetic */ void access$4(FlowControlledOutputStream flowControlledOutputStream, int n) {
        flowControlledOutputStream.readyToSend_ = n;
    }

    static /* synthetic */ Sender access$5(FlowControlledOutputStream flowControlledOutputStream) {
        return flowControlledOutputStream.sender_;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void close() {
        FlowControlledOutputStream flowControlledOutputStream = this;
        synchronized (flowControlledOutputStream) {
            boolean bl = this.closed_;
            if (!bl) {
                this.closed_ = true;
                this.notifyAll();
                this.thread_.interrupt();
            }
            return;
        }
    }

    /*
     * Exception decompiling
     */
    public void flush() throws IOException {
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
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // com.njlabs.showjava.processor.JavaExtractor$1.run(JavaExtractor.java:100)
        // java.lang.Thread.run(Thread.java:818)
        throw new IllegalStateException("Decompilation failed");
    }

    public void readyToSend(int n) {
        void var3_2 = this;
        synchronized (var3_2) {
            this.readyToSend_ = n + this.readyToSend_;
            this.notifyAll();
            return;
        }
    }

    /*
     * Exception decompiling
     */
    public void write(int var1) throws IOException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.util.ConcurrentModificationException
        // java.util.LinkedList$ReverseLinkIterator.next(LinkedList.java:217)
        // org.benf.cfr.reader.bytecode.analysis.structured.statement.Block.extractLabelledBlocks(Block.java:212)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement$LabelledBlockExtractor.transform(Op04StructuredStatement.java:485)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.transform(Op04StructuredStatement.java:639)
        // org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredDo.transformStructuredChildren(StructuredDo.java:53)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement$LabelledBlockExtractor.transform(Op04StructuredStatement.java:487)
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
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // com.njlabs.showjava.processor.JavaExtractor$1.run(JavaExtractor.java:100)
        // java.lang.Thread.run(Thread.java:818)
        throw new IllegalStateException("Decompilation failed");
    }

    class FlushThread
    extends Thread {
        FlushThread() {
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Converted monitor instructions to comments
         * Lifted jumps to return sites
         */
        public void run() {
            super.run();
            block8 : do {
                var7_2 = var2_5 = FlowControlledOutputStream.this;
                // MONITORENTER : var7_2
                do {
                    if (FlowControlledOutputStream.access$0(FlowControlledOutputStream.this) != 0 && !FlowControlledOutputStream.access$1(FlowControlledOutputStream.this).isEmpty()) break;
                    FlowControlledOutputStream.this.wait();
                } while (true);
                var4_1 = Math.min((int)FlowControlledOutputStream.access$2(FlowControlledOutputStream.this), (int)Math.min((int)FlowControlledOutputStream.access$0(FlowControlledOutputStream.this), (int)FlowControlledOutputStream.access$1(FlowControlledOutputStream.this).size()));
                var5_4 = 0;
                do {
                    if (var5_4 >= var4_1) {
                        var6_3 = FlowControlledOutputStream.this;
                        FlowControlledOutputStream.access$4(var6_3, FlowControlledOutputStream.access$0(var6_3) - var4_1);
                        FlowControlledOutputStream.this.notifyAll();
                        // MONITOREXIT : var7_2
                        FlowControlledOutputStream.access$5(FlowControlledOutputStream.this).send(FlowControlledOutputStream.access$3(FlowControlledOutputStream.this), var4_1);
                        continue block8;
                    }
                    ** GOTO lbl24
                    catch (InterruptedException var1_6) {
                        return;
                    }
lbl24: // 1 sources:
                    FlowControlledOutputStream.access$3((FlowControlledOutputStream)FlowControlledOutputStream.this)[var5_4] = ((Byte)FlowControlledOutputStream.access$1(FlowControlledOutputStream.this).remove()).byteValue();
                    ++var5_4;
                } while (true);
                break;
            } while (true);
        }
    }

    static interface Sender {
        public void send(byte[] var1, int var2);
    }

}

