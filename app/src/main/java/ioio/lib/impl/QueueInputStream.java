/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.io.IOException
 *  java.io.InputStream
 *  java.lang.Byte
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.Queue
 *  java.util.concurrent.ArrayBlockingQueue
 */
package ioio.lib.impl;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

class QueueInputStream
extends InputStream {
    private final Queue<Byte> queue_ = new ArrayBlockingQueue(1024);
    private State state_ = State.OPEN;

    QueueInputStream() {
    }

    public int available() throws IOException {
        QueueInputStream queueInputStream = this;
        synchronized (queueInputStream) {
            int n = this.queue_.size();
            return n;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void close() {
        QueueInputStream queueInputStream = this;
        synchronized (queueInputStream) {
            State state = this.state_;
            State state2 = State.OPEN;
            if (state == state2) {
                this.state_ = State.CLOSED;
                this.notifyAll();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void kill() {
        QueueInputStream queueInputStream = this;
        synchronized (queueInputStream) {
            State state = this.state_;
            State state2 = State.OPEN;
            if (state == state2) {
                this.state_ = State.KILLED;
                this.notifyAll();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public int read() throws IOException {
        QueueInputStream queueInputStream = this;
        synchronized (queueInputStream) {
            try {
                boolean bl;
                do {
                    if (this.state_ != State.OPEN || !this.queue_.isEmpty()) {
                        if (this.state_ != State.KILLED) break;
                        throw new IOException("Stream has been closed");
                    }
                    this.wait();
                } while (true);
                if (this.state_ == State.CLOSED && (bl = this.queue_.isEmpty())) {
                    return -1;
                }
                byte by = ((Byte)this.queue_.remove()).byteValue();
                return by & 255;
            }
            catch (InterruptedException var2_2) {
                throw new IOException("Interrupted");
            }
        }
    }

    /*
     * Exception decompiling
     */
    public int read(byte[] var1, int var2_3, int var3_2) throws IOException {
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

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public void write(byte[] var1, int var2_2) {
        var7_3 = this;
        // MONITORENTER : var7_3
        var3_4 = 0;
        do {
            if (var3_4 >= var2_2) ** GOTO lbl8
            if (this.queue_.size() == 1024) {
                Log.e((String)"QueueInputStream", (String)"Buffer overflow, discarding data");
lbl8: // 2 sources:
                this.notifyAll();
                // MONITOREXIT : var7_3
                return;
            }
            this.queue_.add((Object)Byte.valueOf((byte)var1[var3_4]));
            ++var3_4;
        } while (true);
    }

    private static final class State
    extends Enum<State> {
        public static final /* enum */ State CLOSED;
        private static final /* synthetic */ State[] ENUM$VALUES;
        public static final /* enum */ State KILLED;
        public static final /* enum */ State OPEN;

        static {
            OPEN = new State("OPEN", 0);
            CLOSED = new State("CLOSED", 1);
            KILLED = new State("KILLED", 2);
            State[] arrstate = new State[]{OPEN, CLOSED, KILLED};
            ENUM$VALUES = arrstate;
        }

        private State(String string3, int n2) {
            super(string2, n);
        }

        public static State valueOf(String string2) {
            return (State)Enum.valueOf((Class)State.class, (String)string2);
        }

        public static State[] values() {
            State[] arrstate = ENUM$VALUES;
            int n = arrstate.length;
            State[] arrstate2 = new State[n];
            System.arraycopy((Object)arrstate, (int)0, (Object)arrstate2, (int)0, (int)n);
            return arrstate2;
        }
    }

}

