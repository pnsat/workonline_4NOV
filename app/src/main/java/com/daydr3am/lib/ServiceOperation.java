/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.ProgressDialog
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.os.AsyncTask
 *  android.os.Environment
 *  android.util.Log
 *  java.io.BufferedReader
 *  java.io.File
 *  java.io.FileReader
 *  java.io.IOException
 *  java.io.Reader
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Void
 *  java.net.SocketException
 *  java.net.SocketTimeoutException
 *  java.net.UnknownHostException
 *  java.util.List
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.NameValuePair
 *  org.apache.http.client.ClientProtocolException
 *  org.apache.http.client.entity.UrlEncodedFormEntity
 *  org.apache.http.client.methods.HttpGet
 *  org.apache.http.client.methods.HttpPost
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.conn.ConnectTimeoutException
 *  org.apache.http.entity.ByteArrayEntity
 *  org.apache.http.impl.client.DefaultHttpClient
 *  org.apache.http.params.HttpConnectionParams
 *  org.apache.http.params.HttpParams
 *  org.apache.http.util.EntityUtils
 *  org.json.JSONException
 */
package com.daydr3am.lib;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import com.daydr3am.lib.ServiceOperationListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

public class ServiceOperation
extends AsyncTask<String, Void, String> {
    public static String AppName;
    public static boolean debug;
    public static int loadingIcon;
    public Activity ac;
    public boolean canResumeDownload = false;
    public String dataPath;
    ProgressDialog dialog;
    public String entity;
    public ServiceOperationListener listener;
    public List<NameValuePair> nameValuePairs;
    boolean networkError = false;
    int oldSizeMB;
    String responseBody;
    private String url;

    static {
        debug = true;
    }

    public ServiceOperation(String string2) {
        this.url = string2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void dialogExit(String string2, String string3, Context context) {
        if (context == null) return;
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage((CharSequence)string3).setTitle((CharSequence)string2).setCancelable(false).setPositiveButton((CharSequence)"OK", new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialogInterface, int n) {
                    dialogInterface.cancel();
                }
            });
            builder.create().show();
            return;
        }
        catch (Exception var4_4) {
            var4_4.printStackTrace();
            return;
        }
    }

    public static void dialogSelect(String string2, String string3, Context context, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage((CharSequence)string3).setTitle((CharSequence)string2).setCancelable(false).setPositiveButton((CharSequence)"OK", onClickListener).setNegativeButton((CharSequence)"Cancel", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }

    public static ProgressDialog drawProgressDialogs(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(2131230720);
        progressDialog.setIcon(2130837619);
        progressDialog.setMessage((CharSequence)"Loading");
        progressDialog.show();
        return progressDialog;
    }

    /*
     * Exception decompiling
     */
    private void fileDownload(HttpResponse var1) throws IOException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 14[UNCONDITIONALDOLOOP]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
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

    public static File getDirectoryPath() {
        if (debug) {
            new File(Environment.getExternalStorageDirectory(), "/OMS").mkdir();
            return new File(Environment.getExternalStorageDirectory(), "/OMS/");
        }
        return new File(Environment.getDataDirectory(), "");
    }

    /*
     * Exception decompiling
     */
    public static String getJsonResource(Activity var0_1, int var1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 15[UNCONDITIONALDOLOOP]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
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

    public static boolean isTablet(Context context) {
        if ((15 & context.getResources().getConfiguration().screenLayout) >= 3) {
            return true;
        }
        return false;
    }

    public static String readJsonFile(String string2) throws IOException, JSONException {
        String string3 = String.valueOf((Object)ServiceOperation.getDirectoryPath().getAbsolutePath()) + "/";
        StringBuffer stringBuffer = new StringBuffer(1000);
        Log.v((String)"hello", (String)("json path " + string3 + string2 + "/" + string2 + ".json"));
        Log.v((String)"hello", (String)("json path " + (Object)Environment.getDataDirectory()));
        BufferedReader bufferedReader = new BufferedReader((Reader)new FileReader(String.valueOf((Object)string3) + string2 + "/" + string2 + ".json"));
        char[] arrc = new char[1024];
        do {
            int n;
            if ((n = bufferedReader.read(arrc)) == -1) {
                bufferedReader.close();
                return stringBuffer.toString();
            }
            stringBuffer.append(String.valueOf((char[])arrc, (int)0, (int)n));
            arrc = new char[1024];
        } while (true);
    }

    public /* varargs */ void beforeExcute(String ... arrstring) {
        if (this.dialog != null) {
            this.dialog.cancel();
        }
        if (this.ac != null) {
            this.dialog = ServiceOperation.drawProgressDialogs((Context)this.ac);
        }
        this.execute((Object[])arrstring);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected /* varargs */ String doInBackground(String ... arrstring) {
        this.publishProgress((Object[])new Void[0]);
        try {
            HttpGet httpGet;
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpConnectionParams.setConnectionTimeout((HttpParams)defaultHttpClient.getParams(), (int)30000);
            HttpConnectionParams.setSoTimeout((HttpParams)defaultHttpClient.getParams(), (int)30000);
            if (this.nameValuePairs == null && this.entity == null) {
                httpGet = new HttpGet(this.url);
            } else if (this.nameValuePairs != null) {
                Log.v((String)"hello", (String)"2");
                httpGet = new HttpPost(this.url);
                ((HttpPost)httpGet).setEntity((HttpEntity)new UrlEncodedFormEntity(this.nameValuePairs));
            } else {
                Log.v((String)"hello", (String)"1");
                httpGet = new HttpPost(this.url);
                ((HttpPost)httpGet).setEntity((HttpEntity)new ByteArrayEntity(this.entity.getBytes("UTF-8")));
                ((HttpPost)httpGet).setHeader("Content-type", "application/json");
            }
            Log.v((String)"hello", (String)("time url " + this.url));
            HttpResponse httpResponse = defaultHttpClient.execute((HttpUriRequest)httpGet);
            if (this.dataPath == null) {
                this.responseBody = EntityUtils.toString((HttpEntity)httpResponse.getEntity());
            } else {
                super.fileDownload(httpResponse);
            }
            Log.v((String)"hello", (String)("time url2 " + this.url));
        }
        catch (ConnectTimeoutException var9_5) {
            this.networkError = true;
            return arrstring[0];
        }
        catch (UnknownHostException var8_6) {
            this.networkError = true;
            return arrstring[0];
        }
        catch (SocketException var7_7) {
            this.networkError = true;
            return arrstring[0];
        }
        catch (ClientProtocolException var6_8) {
            this.networkError = true;
            var6_8.printStackTrace();
            return arrstring[0];
        }
        catch (SocketTimeoutException var5_9) {
            this.networkError = true;
            return arrstring[0];
        }
        catch (IOException var4_10) {
            this.networkError = true;
            var4_10.printStackTrace();
            return arrstring[0];
        }
        catch (Exception var3_11) {
            this.networkError = true;
            var3_11.printStackTrace();
            return arrstring[0];
        }
        return arrstring[0];
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onPostExecute(String string2) {
        Log.v((String)"hello", (String)"post exc");
        if (this.dialog != null) {
            try {
                this.dialog.cancel();
            }
            catch (Exception var3_2) {
                var3_2.printStackTrace();
            }
        }
        if (this.networkError) {
            ServiceOperation.dialogExit(AppName, "Can not connect to the Internet.", (Context)this.ac);
            if (this.listener == null) return;
            {
                this.listener.ServiceconnectionFail(string2);
                return;
            }
        } else {
            if (this.listener == null) return;
            {
                this.listener.ServiceconnectionFinish(this.responseBody, string2);
                return;
            }
        }
    }

    protected void onPreExecute() {
    }

    protected /* varargs */ void onProgressUpdate(Void ... arrvoid) {
    }

}

