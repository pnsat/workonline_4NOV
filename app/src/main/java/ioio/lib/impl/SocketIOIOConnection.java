/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.io.IOException
 *  java.io.InputStream
 *  java.io.OutputStream
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.net.ServerSocket
 *  java.net.Socket
 */
package ioio.lib.impl;

import android.util.Log;
import ioio.lib.api.IOIOConnection;
import ioio.lib.api.exception.ConnectionLostException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketIOIOConnection
implements IOIOConnection {
    private static final String TAG = "SocketIOIOConnection";
    private boolean disconnect_ = false;
    private final int port_;
    private ServerSocket server_ = null;
    private boolean server_owned_by_connect_ = true;
    private Socket socket_ = null;
    private boolean socket_owned_by_connect_ = true;

    public SocketIOIOConnection(int n) {
        this.port_ = n;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void disconnect() {
        SocketIOIOConnection socketIOIOConnection = this;
        synchronized (socketIOIOConnection) {
            boolean bl = this.disconnect_;
            if (!bl) {
                boolean bl2;
                Log.v((String)"SocketIOIOConnection", (String)"Client initiated disconnect");
                this.disconnect_ = true;
                boolean bl3 = this.server_owned_by_connect_;
                if (!bl3) {
                    try {
                        this.server_.close();
                    }
                    catch (IOException var7_6) {
                        Log.e((String)"SocketIOIOConnection", (String)"Unexpected exception", (Throwable)var7_6);
                    }
                }
                if (!(bl2 = this.socket_owned_by_connect_)) {
                    try {
                        this.socket_.shutdownOutput();
                    }
                    catch (IOException var6_5) {}
                }
            }
            return;
        }
    }

    @Override
    public InputStream getInputStream() throws ConnectionLostException {
        try {
            InputStream inputStream = this.socket_.getInputStream();
            return inputStream;
        }
        catch (IOException var1_2) {
            throw new ConnectionLostException((Exception)var1_2);
        }
    }

    @Override
    public OutputStream getOutputStream() throws ConnectionLostException {
        try {
            OutputStream outputStream = this.socket_.getOutputStream();
            return outputStream;
        }
        catch (IOException var1_2) {
            throw new ConnectionLostException((Exception)var1_2);
        }
    }

    /*
     * Exception decompiling
     */
    @Override
    public void waitForConnect() throws ConnectionLostException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.util.ConcurrentModificationException
        // java.util.LinkedList$ReverseLinkIterator.next(LinkedList.java:217)
        // org.benf.cfr.reader.bytecode.analysis.structured.statement.Block.extractLabelledBlocks(Block.java:212)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement$LabelledBlockExtractor.transform(Op04StructuredStatement.java:485)
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
}

