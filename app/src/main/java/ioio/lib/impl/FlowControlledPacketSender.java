/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.IOException
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.Thread
 *  java.util.concurrent.ArrayBlockingQueue
 *  java.util.concurrent.BlockingQueue
 */
package ioio.lib.impl;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class FlowControlledPacketSender {
    private boolean closed_;
    private final BlockingQueue<Packet> queue_ = new ArrayBlockingQueue(256);
    private int readyToSend_;
    private final Sender sender_;
    private final FlushThread thread_;

    public FlowControlledPacketSender(Sender sender) {
        this.thread_ = (FlowControlledPacketSender)this.new FlushThread();
        this.readyToSend_ = 0;
        this.closed_ = false;
        this.sender_ = sender;
        this.thread_.start();
    }

    static /* synthetic */ BlockingQueue access$0(FlowControlledPacketSender flowControlledPacketSender) {
        return flowControlledPacketSender.queue_;
    }

    static /* synthetic */ int access$1(FlowControlledPacketSender flowControlledPacketSender) {
        return flowControlledPacketSender.readyToSend_;
    }

    static /* synthetic */ void access$2(FlowControlledPacketSender flowControlledPacketSender, int n) {
        flowControlledPacketSender.readyToSend_ = n;
    }

    static /* synthetic */ Sender access$3(FlowControlledPacketSender flowControlledPacketSender) {
        return flowControlledPacketSender.sender_;
    }

    public void close() {
        FlowControlledPacketSender flowControlledPacketSender = this;
        synchronized (flowControlledPacketSender) {
            this.closed_ = true;
            this.thread_.interrupt();
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

    public void kill() {
        FlowControlledPacketSender flowControlledPacketSender = this;
        synchronized (flowControlledPacketSender) {
            this.thread_.interrupt();
            return;
        }
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
    public void write(Packet var1) throws IOException {
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
            block7 : do {
                var5_2 = var2_3 = FlowControlledPacketSender.this;
                // MONITORENTER : var5_2
                do {
                    if (!FlowControlledPacketSender.access$0(FlowControlledPacketSender.this).isEmpty() && FlowControlledPacketSender.access$1(FlowControlledPacketSender.this) >= ((Packet)FlowControlledPacketSender.access$0(FlowControlledPacketSender.this).peek()).getSize()) {
                        FlowControlledPacketSender.this.notifyAll();
                        var4_1 = FlowControlledPacketSender.this;
                        FlowControlledPacketSender.access$2(var4_1, FlowControlledPacketSender.access$1(var4_1) - ((Packet)FlowControlledPacketSender.access$0(FlowControlledPacketSender.this).peek()).getSize());
                        // MONITOREXIT : var5_2
                        FlowControlledPacketSender.access$3(FlowControlledPacketSender.this).send((Packet)FlowControlledPacketSender.access$0(FlowControlledPacketSender.this).remove());
                        continue block7;
                    }
                    ** GOTO lbl18
                    catch (InterruptedException var1_4) {
                        return;
                    }
lbl18: // 1 sources:
                    FlowControlledPacketSender.this.wait();
                } while (true);
                break;
            } while (true);
        }
    }

    static interface Packet {
        public int getSize();
    }

    static interface Sender {
        public void send(Packet var1);
    }

}

