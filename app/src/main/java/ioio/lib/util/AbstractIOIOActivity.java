/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.ContextWrapper
 *  android.content.Intent
 *  android.os.Bundle
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.RuntimeException
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.Collection
 *  java.util.Iterator
 *  java.util.LinkedList
 */
package ioio.lib.util;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.spi.IOIOConnectionBootstrap;
import ioio.lib.spi.IOIOConnectionFactory;
import ioio.lib.util.IOIOConnectionRegistry;
import ioio.lib.util.android.ContextWrapperDependent;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class AbstractIOIOActivity
extends Activity {
    private static final String TAG = "AbstractIOIOActivity";
    private Collection<IOIOConnectionBootstrap> bootstraps_ = IOIOConnectionRegistry.getBootstraps();
    private IOIOConnectionFactory currentConnectionFactory_;
    private Collection<IOIOThread> threads_ = new LinkedList();

    static {
        IOIOConnectionRegistry.addBootstraps(new String[]{"ioio.lib.android.accessory.AccessoryConnectionBootstrap", "ioio.lib.android.bluetooth.BluetoothIOIOConnectionBootstrap"});
    }

    private void abortAllThreads() {
        Iterator iterator = this.threads_.iterator();
        while (iterator.hasNext()) {
            ((IOIOThread)((Object)iterator.next())).abort();
        }
        return;
    }

    private void createAllThreads() {
        this.threads_.clear();
        Iterator iterator = IOIOConnectionRegistry.getConnectionFactories().iterator();
        while (iterator.hasNext()) {
            IOIOConnectionFactory iOIOConnectionFactory;
            this.currentConnectionFactory_ = iOIOConnectionFactory = (IOIOConnectionFactory)iterator.next();
            IOIOThread iOIOThread = this.createIOIOThread(iOIOConnectionFactory.getType(), iOIOConnectionFactory.getExtra());
            if (iOIOThread == null) continue;
            this.threads_.add((Object)iOIOThread);
        }
        return;
    }

    private void joinAllThreads() throws InterruptedException {
        Iterator iterator = this.threads_.iterator();
        while (iterator.hasNext()) {
            ((IOIOThread)((Object)iterator.next())).join();
        }
        return;
    }

    private void startAllThreads() {
        Iterator iterator = this.threads_.iterator();
        while (iterator.hasNext()) {
            ((IOIOThread)((Object)iterator.next())).start();
        }
        return;
    }

    protected IOIOThread createIOIOThread() {
        throw new RuntimeException("Client must override on of the createIOIOThread overloads!");
    }

    protected IOIOThread createIOIOThread(String string2, Object object) {
        return this.createIOIOThread();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Iterator iterator = this.bootstraps_.iterator();
        while (iterator.hasNext()) {
            IOIOConnectionBootstrap iOIOConnectionBootstrap = (IOIOConnectionBootstrap)iterator.next();
            if (!(iOIOConnectionBootstrap instanceof ContextWrapperDependent)) continue;
            ((ContextWrapperDependent)((Object)iOIOConnectionBootstrap)).onCreate((ContextWrapper)this);
        }
        return;
    }

    protected void onDestroy() {
        Iterator iterator = this.bootstraps_.iterator();
        do {
            if (!iterator.hasNext()) {
                super.onDestroy();
                return;
            }
            IOIOConnectionBootstrap iOIOConnectionBootstrap = (IOIOConnectionBootstrap)iterator.next();
            if (!(iOIOConnectionBootstrap instanceof ContextWrapperDependent)) continue;
            ((ContextWrapperDependent)((Object)iOIOConnectionBootstrap)).onDestroy();
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if ((268435456 & intent.getFlags()) == 0) return;
        Iterator iterator = this.bootstraps_.iterator();
        while (iterator.hasNext()) {
            IOIOConnectionBootstrap iOIOConnectionBootstrap = (IOIOConnectionBootstrap)iterator.next();
            if (!(iOIOConnectionBootstrap instanceof ContextWrapperDependent)) continue;
            ((ContextWrapperDependent)((Object)iOIOConnectionBootstrap)).open();
        }
        return;
    }

    protected void onStart() {
        super.onStart();
        Iterator iterator = this.bootstraps_.iterator();
        do {
            if (!iterator.hasNext()) {
                this.createAllThreads();
                this.startAllThreads();
                return;
            }
            IOIOConnectionBootstrap iOIOConnectionBootstrap = (IOIOConnectionBootstrap)iterator.next();
            if (!(iOIOConnectionBootstrap instanceof ContextWrapperDependent)) continue;
            ((ContextWrapperDependent)((Object)iOIOConnectionBootstrap)).open();
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    protected void onStop() {
        this.abortAllThreads();
        try {
            this.joinAllThreads();
        }
        catch (InterruptedException var1_1) {
            ** continue;
        }
lbl4: // 2 sources:
        do {
            var2_2 = this.bootstraps_.iterator();
            do {
                if (!var2_2.hasNext()) {
                    super.onStop();
                    return;
                }
                var3_3 = (IOIOConnectionBootstrap)var2_2.next();
                if (!(var3_3 instanceof ContextWrapperDependent)) continue;
                ((ContextWrapperDependent)var3_3).close();
            } while (true);
            break;
        } while (true);
    }

    protected abstract class IOIOThread
    extends Thread {
        private boolean abort_;
        private boolean connected_;
        private final IOIOConnectionFactory connectionFactory_;
        protected IOIO ioio_;

        protected IOIOThread() {
            this.abort_ = false;
            this.connected_ = true;
            this.connectionFactory_ = AbstractIOIOActivity.this.currentConnectionFactory_;
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

        protected void disconnected() {
        }

        protected void incompatible() {
        }

        protected void loop() throws ConnectionLostException, InterruptedException {
            IOIOThread.sleep((long)100000);
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

        protected void setup() throws ConnectionLostException, InterruptedException {
        }
    }

}

