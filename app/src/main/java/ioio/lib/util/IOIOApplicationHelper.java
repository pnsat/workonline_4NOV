/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.Collection
 *  java.util.Iterator
 *  java.util.LinkedList
 */
package ioio.lib.util;

import ioio.lib.api.IOIO;
import ioio.lib.spi.IOIOConnectionBootstrap;
import ioio.lib.spi.IOIOConnectionFactory;
import ioio.lib.util.IOIOConnectionRegistry;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.IOIOLooperProvider;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class IOIOApplicationHelper {
    protected static final String TAG = "IOIOAndroidApplicationHelper";
    protected Collection<IOIOConnectionBootstrap> bootstraps_ = IOIOConnectionRegistry.getBootstraps();
    protected final IOIOLooperProvider looperProvider_;
    private Collection<IOIOThread> threads_ = new LinkedList();

    public IOIOApplicationHelper(IOIOLooperProvider iOIOLooperProvider) {
        this.looperProvider_ = iOIOLooperProvider;
    }

    protected void abortAllThreads() {
        Iterator iterator = this.threads_.iterator();
        while (iterator.hasNext()) {
            ((IOIOThread)((Object)iterator.next())).abort();
        }
        return;
    }

    protected void createAllThreads() {
        this.threads_.clear();
        Iterator iterator = IOIOConnectionRegistry.getConnectionFactories().iterator();
        while (iterator.hasNext()) {
            IOIOConnectionFactory iOIOConnectionFactory = (IOIOConnectionFactory)iterator.next();
            IOIOLooper iOIOLooper = this.looperProvider_.createIOIOLooper(iOIOConnectionFactory.getType(), iOIOConnectionFactory.getExtra());
            if (iOIOLooper == null) continue;
            this.threads_.add((Object)new IOIOThread(iOIOLooper, iOIOConnectionFactory));
        }
        return;
    }

    protected void joinAllThreads() throws InterruptedException {
        Iterator iterator = this.threads_.iterator();
        while (iterator.hasNext()) {
            ((IOIOThread)((Object)iterator.next())).join();
        }
        return;
    }

    public void start() {
        this.createAllThreads();
        this.startAllThreads();
    }

    protected void startAllThreads() {
        Iterator iterator = this.threads_.iterator();
        while (iterator.hasNext()) {
            ((IOIOThread)((Object)iterator.next())).start();
        }
        return;
    }

    public void stop() {
        this.abortAllThreads();
        try {
            this.joinAllThreads();
            return;
        }
        catch (InterruptedException var1_1) {
            return;
        }
    }

    private static class IOIOThread
    extends Thread {
        private boolean abort_ = false;
        private boolean connected_ = true;
        private final IOIOConnectionFactory connectionFactory_;
        protected IOIO ioio_;
        private final IOIOLooper looper_;

        IOIOThread(IOIOLooper iOIOLooper, IOIOConnectionFactory iOIOConnectionFactory) {
            this.looper_ = iOIOLooper;
            this.connectionFactory_ = iOIOConnectionFactory;
        }

        public final void abort() {
            IOIOThread iOIOThread = this;
            synchronized (iOIOThread) {
                this.abort_ = true;
                if (this.ioio_ != null) {
                    this.ioio_.disconnect();
                }
                if (this.connected_) {
                    this.interrupt();
                }
                return;
            }
        }

        /*
         * Exception decompiling
         */
        public final void run() {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [4[TRYBLOCK]], but top level block is 48[UNCONDITIONALDOLOOP]
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
    }

}

