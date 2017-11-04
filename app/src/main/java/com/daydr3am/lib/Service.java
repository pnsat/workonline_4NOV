/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.os.Bundle
 *  android.util.Log
 *  java.io.BufferedInputStream
 *  java.io.File
 *  java.io.FileOutputStream
 *  java.io.IOException
 *  java.io.InputStream
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Float
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.System
 *  java.net.URL
 *  java.net.URLConnection
 *  java.util.ArrayList
 *  java.util.List
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.HttpVersion
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.client.HttpClient
 *  org.apache.http.client.entity.UrlEncodedFormEntity
 *  org.apache.http.client.methods.HttpPost
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.conn.scheme.PlainSocketFactory
 *  org.apache.http.conn.scheme.Scheme
 *  org.apache.http.conn.scheme.SchemeRegistry
 *  org.apache.http.conn.scheme.SocketFactory
 *  org.apache.http.conn.ssl.SSLSocketFactory
 *  org.apache.http.impl.client.DefaultHttpClient
 *  org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager
 *  org.apache.http.message.BasicNameValuePair
 *  org.apache.http.params.BasicHttpParams
 *  org.apache.http.params.HttpConnectionParams
 *  org.apache.http.params.HttpParams
 *  org.apache.http.params.HttpProtocolParams
 *  org.apache.http.util.ByteArrayBuffer
 *  org.apache.http.util.EntityUtils
 */
package com.daydr3am.lib;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import com.daydr3am.lib.Coding;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;

public class Service {
    public static SharedPreferences globalPref;
    public static SharedPreferences mainPref;
    public static SharedPreferences pref;

    public static String Connection(Bundle bundle, String string2, int n) throws Exception {
        Log.w((String)"hello", (String)("pass inside " + (Object)globalPref));
        Object[] arrobject = new Object[]{n};
        String string3 = String.valueOf((Object)String.format((String)"api%05d,", (Object[])arrobject)) + string2;
        String string4 = globalPref.getString("HARDWARE_ID", "");
        String string5 = globalPref.getString("KEYTABLE", "");
        Log.v((String)"hello", (String)("test123" + string4 + " " + string5 + " " + string3 + " " + Coding.newencoding(string3, string5)));
        HttpPost httpPost = new HttpPost("http://ows.dyndns.biz/owstopup/oldkiosk_api/kioskapi.php");
        ArrayList arrayList = new ArrayList();
        arrayList.add((Object)new BasicNameValuePair("hwid", string4));
        arrayList.add((Object)new BasicNameValuePair("data", string3));
        arrayList.add((Object)new BasicNameValuePair("sum", Coding.newencoding(string3, string5)));
        HttpClient httpClient = Service.createHttpClient();
        HttpConnectionParams.setConnectionTimeout((HttpParams)httpClient.getParams(), (int)60000);
        HttpConnectionParams.setSoTimeout((HttpParams)httpClient.getParams(), (int)60000);
        httpPost.setHeader("Content-Language", "en-US");
        httpPost.setHeader("Host", "ows.dyndns.biz");
        httpPost.setEntity((HttpEntity)new UrlEncodedFormEntity((List)arrayList, "UTF-8"));
        Log.w((String)"hello", (String)"before ");
        HttpResponse httpResponse = httpClient.execute((HttpUriRequest)httpPost);
        Log.w((String)"hello", (String)"callpass ");
        String string6 = EntityUtils.toString((HttpEntity)httpResponse.getEntity());
        Log.w((String)"hello", (String)("entity " + string6));
        return string6;
    }

    public static String cancelInputMoney(Bundle bundle) throws Exception {
        Log.v((String)"hello", (String)("input " + bundle.getString("TotalCoin1")));
        StringBuilder stringBuilder = new StringBuilder(String.valueOf((Object)bundle.getString("Mobile"))).append(",");
        Object[] arrobject = new Object[]{Integer.parseInt((String)bundle.getString("Network"))};
        StringBuilder stringBuilder2 = stringBuilder.append(String.format((String)"%03d", (Object[])arrobject)).append(",");
        Object[] arrobject2 = new Object[]{Integer.parseInt((String)bundle.getString("Price"))};
        StringBuilder stringBuilder3 = stringBuilder2.append(String.format((String)"%04d", (Object[])arrobject2)).append(",");
        Object[] arrobject3 = new Object[]{Integer.parseInt((String)bundle.getString("TotalPrice"))};
        StringBuilder stringBuilder4 = stringBuilder3.append(String.format((String)"%04d", (Object[])arrobject3)).append(",");
        Object[] arrobject4 = new Object[]{Integer.parseInt((String)bundle.getString("TotalCoin1"))};
        StringBuilder stringBuilder5 = stringBuilder4.append(String.format((String)"%04d", (Object[])arrobject4)).append(",");
        Object[] arrobject5 = new Object[]{Integer.parseInt((String)bundle.getString("TotalCoin2"))};
        StringBuilder stringBuilder6 = stringBuilder5.append(String.format((String)"%04d", (Object[])arrobject5)).append(",");
        Object[] arrobject6 = new Object[]{Integer.parseInt((String)bundle.getString("TotalCoin3"))};
        StringBuilder stringBuilder7 = stringBuilder6.append(String.format((String)"%04d", (Object[])arrobject6)).append(",");
        Object[] arrobject7 = new Object[]{Integer.parseInt((String)bundle.getString("TotalBank1"))};
        StringBuilder stringBuilder8 = stringBuilder7.append(String.format((String)"%04d", (Object[])arrobject7)).append(",");
        Object[] arrobject8 = new Object[]{Integer.parseInt((String)bundle.getString("TotalBank2"))};
        StringBuilder stringBuilder9 = stringBuilder8.append(String.format((String)"%04d", (Object[])arrobject8)).append(",");
        Object[] arrobject9 = new Object[]{Integer.parseInt((String)bundle.getString("OperationRate"))};
        StringBuilder stringBuilder10 = stringBuilder9.append(String.format((String)"%02d", (Object[])arrobject9)).append(",");
        Object[] arrobject10 = new Object[]{Integer.parseInt((String)bundle.getString("RandomKey"))};
        String string2 = stringBuilder10.append(String.format((String)"%04d", (Object[])arrobject10)).toString();
        Log.v((String)"test", (String)string2);
        String string3 = Service.Connection(bundle, string2, 4);
        Log.v((String)"test", (String)("return" + string3));
        return string3;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String cancelInputMoneyUtil(Bundle bundle) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf((Object)bundle.getString("Mobile"))).append(",");
        Object[] arrobject = new Object[]{Integer.parseInt((String)bundle.getString("Network"))};
        StringBuilder stringBuilder2 = stringBuilder.append(String.format((String)"%03d", (Object[])arrobject)).append(",");
        Object[] arrobject2 = new Object[]{Float.valueOf((float)Float.parseFloat((String)bundle.getString("Price")))};
        StringBuilder stringBuilder3 = stringBuilder2.append(String.format((String)"%08.2f", (Object[])arrobject2)).append(",");
        Object[] arrobject3 = new Object[]{Integer.parseInt((String)bundle.getString("TotalPrice"))};
        StringBuilder stringBuilder4 = stringBuilder3.append(String.format((String)"%04d", (Object[])arrobject3)).append(",");
        Object[] arrobject4 = new Object[]{Integer.parseInt((String)bundle.getString("TotalCoin1"))};
        StringBuilder stringBuilder5 = stringBuilder4.append(String.format((String)"%04d", (Object[])arrobject4)).append(",");
        Object[] arrobject5 = new Object[]{Integer.parseInt((String)bundle.getString("TotalCoin2"))};
        StringBuilder stringBuilder6 = stringBuilder5.append(String.format((String)"%04d", (Object[])arrobject5)).append(",");
        Object[] arrobject6 = new Object[]{Integer.parseInt((String)bundle.getString("TotalCoin3"))};
        StringBuilder stringBuilder7 = stringBuilder6.append(String.format((String)"%04d", (Object[])arrobject6)).append(",");
        Object[] arrobject7 = new Object[]{Integer.parseInt((String)bundle.getString("TotalBank1"))};
        StringBuilder stringBuilder8 = stringBuilder7.append(String.format((String)"%04d", (Object[])arrobject7)).append(",");
        Object[] arrobject8 = new Object[]{Integer.parseInt((String)bundle.getString("TotalBank2"))};
        StringBuilder stringBuilder9 = stringBuilder8.append(String.format((String)"%04d", (Object[])arrobject8)).append(",");
        Object[] arrobject9 = new Object[]{Integer.parseInt((String)bundle.getString("OperationRate"))};
        StringBuilder stringBuilder10 = stringBuilder9.append(String.format((String)"%02d", (Object[])arrobject9)).append(",");
        Object[] arrobject10 = new Object[]{Integer.parseInt((String)bundle.getString("RandomKey"))};
        StringBuilder stringBuilder11 = stringBuilder10.append(String.format((String)"%04d", (Object[])arrobject10)).append(",");
        Object[] arrobject11 = new Object[]{Long.parseLong((String)bundle.getString("OTP"))};
        String string2 = stringBuilder11.append(String.format((String)"%010d", (Object[])arrobject11)).append(",").toString();
        int n = 0;
        do {
            if (n >= 4) {
                String string3 = bundle.getString("dateUser") == null || bundle.getString("dateUser").length() == 0 ? String.valueOf((Object)string2) + "0" : String.valueOf((Object)string2) + bundle.getString("dateUser");
                Log.v((String)"test", (String)("cancel" + string3));
                String string4 = Service.Connection(bundle, string3, 13);
                Log.v((String)"test", (String)("cancel return" + string4));
                return string4;
            }
            string2 = bundle.getString("Data" + (n + 1)) == null || bundle.getString("Data" + (n + 1)).length() == 0 ? String.valueOf((Object)string2) + "0," : String.valueOf((Object)string2) + bundle.getString(new StringBuilder("Data").append(n + 1).toString()) + ",";
            ++n;
        } while (true);
    }

    public static String checkPassword(Bundle bundle) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf((Object)bundle.getString("Password"))).append(",");
        Object[] arrobject = new Object[]{bundle.getInt("PassType")};
        String string2 = stringBuilder.append(String.format((String)"%01d", (Object[])arrobject)).toString();
        Log.v((String)"test", (String)string2);
        String string3 = Service.Connection(bundle, string2, 5);
        Log.v((String)"test", (String)("return" + string3));
        return string3;
    }

    public static HttpClient createHttpClient() {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpProtocolParams.setVersion((HttpParams)basicHttpParams, (ProtocolVersion)HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset((HttpParams)basicHttpParams, (String)"ISO-8859-1");
        HttpProtocolParams.setUseExpectContinue((HttpParams)basicHttpParams, (boolean)true);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", (SocketFactory)PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", (SocketFactory)SSLSocketFactory.getSocketFactory(), 443));
        return new DefaultHttpClient((ClientConnectionManager)new ThreadSafeClientConnManager((HttpParams)basicHttpParams, schemeRegistry), (HttpParams)basicHttpParams);
    }

    public static String getBarcodeDetail(Bundle bundle) throws Exception {
        String string2 = bundle.getString("DataBarcode");
        Log.e((String)"test", (String)("data send" + string2));
        String string3 = Service.Connection(bundle, string2, 18);
        Log.v((String)"test", (String)("return" + string3));
        return string3;
    }

    @SuppressLint(value={"SdCardPath"})
    public static void getContent(String string2) throws IOException {
        String[] arrstring = string2.split("/");
        Log.v((String)"hello", (String)("url " + string2 + " " + string2.length()));
        URL uRL = new URL(string2);
        File file = new File("/mnt/sdcard/Resource/save/loaddata/");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File("/mnt/sdcard/Resource/save/loaddata/" + arrstring[-1 + arrstring.length]);
        System.currentTimeMillis();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(uRL.openConnection().getInputStream());
        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(50);
        do {
            int n;
            if ((n = bufferedInputStream.read()) == -1) {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                fileOutputStream.write(byteArrayBuffer.toByteArray());
                fileOutputStream.close();
                return;
            }
            byteArrayBuffer.append((int)((byte)n));
        } while (true);
    }

    public static String getProfile() throws Exception {
        String string2 = Service.Connection(new Bundle(), null, 16);
        Log.v((String)"test", (String)("return" + string2));
        return string2;
    }

    public static String getScrollText() throws Exception {
        String string2 = Service.Connection(new Bundle(), null, 14);
        Log.v((String)"test", (String)("return scroll text" + string2));
        return string2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Bundle[] getSponser() throws Exception {
        String string2 = Service.Connection(new Bundle(), null, 9);
        Log.v((String)"hello", (String)("return sponser " + string2));
        String string3 = string2.split("lastupdate=")[1].split("&total")[0];
        Bundle[] arrbundle = Service.parserData(string2);
        Log.v((String)"hello", (String)("data len " + arrbundle.length));
        boolean bl = mainPref.getString("lasttime", "").equals((Object)string3);
        boolean bl2 = false;
        if (!bl) {
            bl2 = true;
        }
        Log.v((String)"hello", (String)(String.valueOf((boolean)bl2) + "returnData sponser " + string2));
        if (!string2.contains((CharSequence)"OK") || mainPref.getString("lasttime", "").equals((Object)string3)) return arrbundle;
        {
            int n = 0;
            do {
                if (n >= arrbundle.length) {
                    SharedPreferences.Editor editor = mainPref.edit();
                    editor.putString("lasttime", string3);
                    Log.v((String)"hello", (String)"save time");
                    editor.commit();
                    return arrbundle;
                }
                Log.v((String)"hello", (String)("before send data " + arrbundle[n].getString("bannerURL")));
                Service.getContent(arrbundle[n].getString("bannerURL"));
                Log.v((String)"hello", (String)("load success " + arrbundle[n].getString("bannerURL")));
                if (arrbundle[n].getString("soundURL") != null && arrbundle[n].getString("soundURL").length() > 5) {
                    Service.getContent(arrbundle[n].getString("soundURL"));
                }
                ++n;
            } while (true);
        }
    }

    public static String getTime(Bundle bundle) throws Exception {
        Log.v((String)"hello", (String)("gettime data " + bundle.getString("phonenumraw") + " " + bundle.getString("phonecreditraw")));
        float f = Float.parseFloat((String)bundle.getString("Latititude"));
        float f2 = Float.parseFloat((String)bundle.getString("Longtitude"));
        String string2 = bundle.getString("MacAddress");
        StringBuilder stringBuilder = new StringBuilder("1,").append(bundle.getString("phonenum")).append(",").append(bundle.getString("phonecredit")).append(",").append(bundle.getString("AppVersion")).append(",");
        Object[] arrobject = new Object[]{Float.valueOf((float)f), Float.valueOf((float)f2)};
        String string3 = stringBuilder.append(String.format((String)"%010.6f,%010.6f,", (Object[])arrobject)).append(string2).append(",").append(bundle.getString("phonenumraw")).append(",").append(bundle.getString("phonecreditraw")).toString();
        Log.v((String)"hello", (String)("gettime data " + string3 + " " + bundle.getString("phonecredit")));
        return Service.Connection(bundle, string3, 3);
    }

    public static String getTranSaction(Bundle bundle) throws Exception {
        Object[] arrobject = new Object[]{bundle.getInt("PerPage")};
        StringBuilder stringBuilder = new StringBuilder(String.valueOf((Object)String.format((String)"%03d", (Object[])arrobject))).append(",");
        Object[] arrobject2 = new Object[]{bundle.getInt("Page")};
        String string2 = stringBuilder.append(String.format((String)"%03d", (Object[])arrobject2)).toString();
        Log.v((String)"test", (String)("data send" + string2));
        String string3 = Service.Connection(bundle, string2, 15);
        Log.v((String)"test", (String)("return" + string3));
        return string3;
    }

    public static String inputMoney(Bundle bundle) throws Exception {
        Integer.parseInt((String)bundle.getString("Network"));
        StringBuilder stringBuilder = new StringBuilder(String.valueOf((Object)bundle.getString("Mobile"))).append(",");
        Object[] arrobject = new Object[]{Integer.parseInt((String)bundle.getString("Network"))};
        StringBuilder stringBuilder2 = stringBuilder.append(String.format((String)"%03d", (Object[])arrobject)).append(",");
        Object[] arrobject2 = new Object[]{Integer.parseInt((String)bundle.getString("Price"))};
        StringBuilder stringBuilder3 = stringBuilder2.append(String.format((String)"%04d", (Object[])arrobject2)).append(",");
        Object[] arrobject3 = new Object[]{Integer.parseInt((String)bundle.getString("TotalPrice"))};
        StringBuilder stringBuilder4 = stringBuilder3.append(String.format((String)"%04d", (Object[])arrobject3)).append(",");
        Object[] arrobject4 = new Object[]{Integer.parseInt((String)bundle.getString("TotalCoin1"))};
        StringBuilder stringBuilder5 = stringBuilder4.append(String.format((String)"%04d", (Object[])arrobject4)).append(",");
        Object[] arrobject5 = new Object[]{Integer.parseInt((String)bundle.getString("TotalCoin2"))};
        StringBuilder stringBuilder6 = stringBuilder5.append(String.format((String)"%04d", (Object[])arrobject5)).append(",");
        Object[] arrobject6 = new Object[]{Integer.parseInt((String)bundle.getString("TotalCoin3"))};
        StringBuilder stringBuilder7 = stringBuilder6.append(String.format((String)"%04d", (Object[])arrobject6)).append(",");
        Object[] arrobject7 = new Object[]{Integer.parseInt((String)bundle.getString("TotalBank1"))};
        StringBuilder stringBuilder8 = stringBuilder7.append(String.format((String)"%04d", (Object[])arrobject7)).append(",");
        Object[] arrobject8 = new Object[]{Integer.parseInt((String)bundle.getString("TotalBank2"))};
        StringBuilder stringBuilder9 = stringBuilder8.append(String.format((String)"%04d", (Object[])arrobject8)).append(",");
        Object[] arrobject9 = new Object[]{Integer.parseInt((String)bundle.getString("OperationRate"))};
        StringBuilder stringBuilder10 = stringBuilder9.append(String.format((String)"%02d", (Object[])arrobject9)).append(",");
        Object[] arrobject10 = new Object[]{Integer.parseInt((String)bundle.getString("RandomKey"))};
        String string2 = stringBuilder10.append(String.format((String)"%04d", (Object[])arrobject10)).toString();
        Log.v((String)"", (String)string2);
        String string3 = Service.Connection(bundle, string2, 2);
        Log.v((String)"TopupTime", (String)string3);
        return string3;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String inputMoneyBarcode(Bundle bundle) throws Exception {
        Object[] arrobject = new Object[]{Integer.parseInt((String)bundle.getString("Network"))};
        String string2 = String.format((String)"%03d", (Object[])arrobject);
        if (bundle.containsKey("RealNetwork")) {
            Object[] arrobject2 = new Object[]{Integer.parseInt((String)bundle.getString("RealNetwork"))};
            string2 = String.format((String)"%03d", (Object[])arrobject2);
        }
        StringBuilder stringBuilder = new StringBuilder(String.valueOf((Object)bundle.getString("Mobile"))).append(",").append(string2).append(",");
        Object[] arrobject3 = new Object[]{Float.valueOf((float)Float.parseFloat((String)bundle.getString("Price")))};
        StringBuilder stringBuilder2 = stringBuilder.append(String.format((String)"%08.2f", (Object[])arrobject3)).append(",");
        Object[] arrobject4 = new Object[]{Integer.parseInt((String)bundle.getString("TotalPrice"))};
        StringBuilder stringBuilder3 = stringBuilder2.append(String.format((String)"%04d", (Object[])arrobject4)).append(",");
        Object[] arrobject5 = new Object[]{Integer.parseInt((String)bundle.getString("TotalCoin1"))};
        StringBuilder stringBuilder4 = stringBuilder3.append(String.format((String)"%04d", (Object[])arrobject5)).append(",");
        Object[] arrobject6 = new Object[]{Integer.parseInt((String)bundle.getString("TotalCoin2"))};
        StringBuilder stringBuilder5 = stringBuilder4.append(String.format((String)"%04d", (Object[])arrobject6)).append(",");
        Object[] arrobject7 = new Object[]{Integer.parseInt((String)bundle.getString("TotalCoin3"))};
        StringBuilder stringBuilder6 = stringBuilder5.append(String.format((String)"%04d", (Object[])arrobject7)).append(",");
        Object[] arrobject8 = new Object[]{Integer.parseInt((String)bundle.getString("TotalBank1"))};
        StringBuilder stringBuilder7 = stringBuilder6.append(String.format((String)"%04d", (Object[])arrobject8)).append(",");
        Object[] arrobject9 = new Object[]{Integer.parseInt((String)bundle.getString("TotalBank2"))};
        StringBuilder stringBuilder8 = stringBuilder7.append(String.format((String)"%04d", (Object[])arrobject9)).append(",");
        Object[] arrobject10 = new Object[]{Integer.parseInt((String)bundle.getString("OperationRate"))};
        StringBuilder stringBuilder9 = stringBuilder8.append(String.format((String)"%02d", (Object[])arrobject10)).append(",");
        Object[] arrobject11 = new Object[]{Integer.parseInt((String)bundle.getString("RandomKey"))};
        StringBuilder stringBuilder10 = stringBuilder9.append(String.format((String)"%04d", (Object[])arrobject11)).append(",");
        Object[] arrobject12 = new Object[]{Long.parseLong((String)bundle.getString("OTP"))};
        String string3 = stringBuilder10.append(String.format((String)"%010d", (Object[])arrobject12)).append(",").toString();
        int n = 0;
        do {
            if (n >= 4) {
                String string4 = bundle.getString("dateUser") == null || bundle.getString("dateUser").length() == 0 ? String.valueOf((Object)string3) + "0," : String.valueOf((Object)string3) + bundle.getString("dateUser") + ",";
                String string5 = String.valueOf((Object)new StringBuilder(String.valueOf((Object)string4)).append(bundle.getString("TY")).append(",").toString()) + bundle.getString("TRAN");
                Log.e((String)"test", (String)("pay 20" + string5));
                String string6 = Service.Connection(bundle, string5, 20);
                Log.e((String)"test", (String)("return pay 20" + string6));
                return string6;
            }
            string3 = bundle.getString("Data" + (n + 1)) == null || bundle.getString("Data" + (n + 1)).length() == 0 ? String.valueOf((Object)string3) + "0," : String.valueOf((Object)string3) + bundle.getString(new StringBuilder("Data").append(n + 1).toString()) + ",";
            ++n;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String inputMoneyUtil(Bundle bundle) throws Exception {
        Object[] arrobject = new Object[]{Integer.parseInt((String)bundle.getString("Network"))};
        String string2 = String.format((String)"%03d", (Object[])arrobject);
        if (bundle.containsKey("RealNetwork")) {
            Object[] arrobject2 = new Object[]{Integer.parseInt((String)bundle.getString("RealNetwork"))};
            string2 = String.format((String)"%03d", (Object[])arrobject2);
        }
        Log.v((String)"hello", (String)("call 12 " + string2));
        StringBuilder stringBuilder = new StringBuilder(String.valueOf((Object)bundle.getString("Mobile"))).append(",").append(string2).append(",");
        Object[] arrobject3 = new Object[]{Float.valueOf((float)Float.parseFloat((String)bundle.getString("Price")))};
        StringBuilder stringBuilder2 = stringBuilder.append(String.format((String)"%08.2f", (Object[])arrobject3)).append(",");
        Object[] arrobject4 = new Object[]{Integer.parseInt((String)bundle.getString("TotalPrice"))};
        StringBuilder stringBuilder3 = stringBuilder2.append(String.format((String)"%04d", (Object[])arrobject4)).append(",");
        Object[] arrobject5 = new Object[]{Integer.parseInt((String)bundle.getString("TotalCoin1"))};
        StringBuilder stringBuilder4 = stringBuilder3.append(String.format((String)"%04d", (Object[])arrobject5)).append(",");
        Object[] arrobject6 = new Object[]{Integer.parseInt((String)bundle.getString("TotalCoin2"))};
        StringBuilder stringBuilder5 = stringBuilder4.append(String.format((String)"%04d", (Object[])arrobject6)).append(",");
        Object[] arrobject7 = new Object[]{Integer.parseInt((String)bundle.getString("TotalCoin3"))};
        StringBuilder stringBuilder6 = stringBuilder5.append(String.format((String)"%04d", (Object[])arrobject7)).append(",");
        Object[] arrobject8 = new Object[]{Integer.parseInt((String)bundle.getString("TotalBank1"))};
        StringBuilder stringBuilder7 = stringBuilder6.append(String.format((String)"%04d", (Object[])arrobject8)).append(",");
        Object[] arrobject9 = new Object[]{Integer.parseInt((String)bundle.getString("TotalBank2"))};
        StringBuilder stringBuilder8 = stringBuilder7.append(String.format((String)"%04d", (Object[])arrobject9)).append(",");
        Object[] arrobject10 = new Object[]{Integer.parseInt((String)bundle.getString("OperationRate"))};
        StringBuilder stringBuilder9 = stringBuilder8.append(String.format((String)"%02d", (Object[])arrobject10)).append(",");
        Object[] arrobject11 = new Object[]{Integer.parseInt((String)bundle.getString("RandomKey"))};
        StringBuilder stringBuilder10 = stringBuilder9.append(String.format((String)"%04d", (Object[])arrobject11)).append(",");
        Object[] arrobject12 = new Object[]{Long.parseLong((String)bundle.getString("OTP"))};
        String string3 = stringBuilder10.append(String.format((String)"%010d", (Object[])arrobject12)).append(",").toString();
        int n = 0;
        do {
            if (n >= 4) {
                String string4 = bundle.getString("dateUser") == null || bundle.getString("dateUser").length() == 0 ? String.valueOf((Object)string3) + "0" : String.valueOf((Object)string3) + bundle.getString("dateUser");
                Log.v((String)"test", (String)("pay" + string4));
                String string5 = Service.Connection(bundle, string4, 12);
                Log.v((String)"test", (String)("return pay" + string5));
                return string5;
            }
            string3 = bundle.getString("Data" + (n + 1)) == null || bundle.getString("Data" + (n + 1)).length() == 0 ? String.valueOf((Object)string3) + "0," : String.valueOf((Object)string3) + bundle.getString(new StringBuilder("Data").append(n + 1).toString()) + ",";
            ++n;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static Bundle[] parserData(String var0) {
        Log.v((String)"hello", (String)("return data " + var0));
        var2_1 = var0.split("&");
        var3_2 = null;
        var4_3 = 0;
        block0 : do {
            if (var4_3 >= var2_1.length) {
                return var3_2;
            }
            if (var2_1[var4_3].contains((CharSequence)"total")) ** GOTO lbl25
            if (var2_1[var4_3].contains((CharSequence)"banner")) {
                var9_6 = var2_1[var4_3].split("=");
                var10_9 = var9_6[1];
                var3_2[-1 + Integer.parseInt((String)var9_6[0].replace((CharSequence)"banner", (CharSequence)""))].putString("bannerURL", var10_9);
            } else if (var2_1[var4_3].contains((CharSequence)"sound")) {
                var7_10 = var2_1[var4_3].split("=");
                if (var7_10.length == 2) {
                    var8_4 = var7_10[1];
                    var3_2[-1 + Integer.parseInt((String)var7_10[0].replace((CharSequence)"sound", (CharSequence)""))].putString("soundURL", var8_4);
                }
            } else if (var2_1[var4_3].contains((CharSequence)"timewait")) {
                var5_7 = var2_1[var4_3].split("=");
                var6_11 = var5_7[1];
                var3_2[-1 + Integer.parseInt((String)var5_7[0].replace((CharSequence)"timewait", (CharSequence)""))].putInt("timeShow", Integer.parseInt((String)var6_11.replace((CharSequence)"#", (CharSequence)"").trim()));
            }
            ** GOTO lbl-1000
lbl25: // 1 sources:
            var11_5 = var2_1[var4_3].replace((CharSequence)"total=", (CharSequence)"").replace((CharSequence)"#", (CharSequence)"").trim();
            var3_2 = new Bundle[Integer.parseInt((String)var11_5)];
            var12_8 = 0;
            do {
                if (var12_8 >= Integer.parseInt((String)var11_5)) lbl-1000: // 4 sources:
                {
                    ++var4_3;
                    continue block0;
                }
                var3_2[var12_8] = new Bundle();
                ++var12_8;
            } while (true);
            break;
        } while (true);
    }

    public static String putPicture(Bundle bundle) throws Exception {
        String string2 = bundle.getString("FileName");
        Log.v((String)"test", (String)string2);
        String string3 = Service.Connection(bundle, string2, 7);
        Log.v((String)"test", (String)("return" + string3));
        return string3;
    }

    public static String ready(Bundle bundle) throws Exception {
        Integer.parseInt((String)bundle.getString("Network"));
        StringBuilder stringBuilder = new StringBuilder(String.valueOf((Object)bundle.getString("Mobile"))).append(",");
        Object[] arrobject = new Object[]{Integer.parseInt((String)bundle.getString("Network"))};
        StringBuilder stringBuilder2 = stringBuilder.append(String.format((String)"%03d", (Object[])arrobject)).append(",");
        Object[] arrobject2 = new Object[]{Integer.parseInt((String)bundle.getString("Price"))};
        String string2 = stringBuilder2.append(String.format((String)"%04d", (Object[])arrobject2)).toString();
        Log.v((String)"test", (String)string2);
        String string3 = Service.Connection(bundle, string2, 1);
        Log.v((String)"test", (String)string3);
        return string3;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String readyBarcode(Bundle bundle) throws Exception {
        StringBuilder stringBuilder = new StringBuilder("price ");
        Object[] arrobject = new Object[]{Float.valueOf((float)Float.parseFloat((String)bundle.getString("Price")))};
        Log.v((String)"test", (String)stringBuilder.append(String.format((String)"%05f", (Object[])arrobject)).toString());
        Object[] arrobject2 = new Object[]{Integer.parseInt((String)bundle.getString("Network"))};
        String string2 = String.format((String)"%03d", (Object[])arrobject2);
        if (bundle.containsKey("RealNetwork")) {
            Object[] arrobject3 = new Object[]{Integer.parseInt((String)bundle.getString("RealNetwork"))};
            string2 = String.format((String)"%03d", (Object[])arrobject3);
        }
        Log.v((String)"hello", (String)("str " + string2));
        StringBuilder stringBuilder2 = new StringBuilder(String.valueOf((Object)bundle.getString("Mobile"))).append(",").append(string2).append(",");
        Object[] arrobject4 = new Object[]{Float.valueOf((float)Float.parseFloat((String)bundle.getString("Price")))};
        StringBuilder stringBuilder3 = stringBuilder2.append(String.format((String)"%08.2f", (Object[])arrobject4)).append(",");
        Object[] arrobject5 = new Object[]{Long.parseLong((String)bundle.getString("OTP"))};
        String string3 = stringBuilder3.append(String.format((String)"%010d", (Object[])arrobject5)).append(",").toString();
        int n = 0;
        do {
            if (n >= 4) {
                String string4 = bundle.getString("dateUser") != null ? String.valueOf((Object)string3) + bundle.getString("dateUser") : String.valueOf((Object)string3) + "0";
                Log.v((String)"test", (String)("data send 19 " + string4));
                String string5 = Service.Connection(bundle, string4, 19);
                Log.v((String)"test", (String)("return 19 " + string5));
                return string5;
            }
            string3 = bundle.getString("Data" + (n + 1)) != null ? String.valueOf((Object)string3) + bundle.getString(new StringBuilder("Data").append(n + 1).toString()) + "," : String.valueOf((Object)string3) + "0,";
            ++n;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String readyUtility(Bundle bundle) throws Exception {
        StringBuilder stringBuilder = new StringBuilder("price ");
        Object[] arrobject = new Object[]{Float.valueOf((float)Float.parseFloat((String)bundle.getString("Price")))};
        Log.v((String)"test", (String)stringBuilder.append(String.format((String)"%05f", (Object[])arrobject)).toString());
        Object[] arrobject2 = new Object[]{Integer.parseInt((String)bundle.getString("Network"))};
        String string2 = String.format((String)"%03d", (Object[])arrobject2);
        if (bundle.containsKey("RealNetwork")) {
            Object[] arrobject3 = new Object[]{Integer.parseInt((String)bundle.getString("RealNetwork"))};
            string2 = String.format((String)"%03d", (Object[])arrobject3);
        }
        Log.v((String)"hello", (String)("str " + string2));
        StringBuilder stringBuilder2 = new StringBuilder(String.valueOf((Object)bundle.getString("Mobile"))).append(",").append(string2).append(",");
        Object[] arrobject4 = new Object[]{Float.valueOf((float)Float.parseFloat((String)bundle.getString("Price")))};
        StringBuilder stringBuilder3 = stringBuilder2.append(String.format((String)"%08.2f", (Object[])arrobject4)).append(",");
        Object[] arrobject5 = new Object[]{Long.parseLong((String)bundle.getString("OTP"))};
        String string3 = stringBuilder3.append(String.format((String)"%010d", (Object[])arrobject5)).append(",").toString();
        int n = 0;
        do {
            if (n >= 4) {
                String string4 = bundle.getString("dateUser") != null ? String.valueOf((Object)string3) + bundle.getString("dateUser") : String.valueOf((Object)string3) + "0";
                Log.e((String)"test", (String)("data send " + string4));
                String string5 = Service.Connection(bundle, string4, 11);
                Log.e((String)"test", (String)("return 11 " + string5));
                return string5;
            }
            string3 = bundle.getString("Data" + (n + 1)) != null ? String.valueOf((Object)string3) + bundle.getString(new StringBuilder("Data").append(n + 1).toString()) + "," : String.valueOf((Object)string3) + "0,";
            ++n;
        } while (true);
    }

    public static String resetAmount(Bundle bundle) throws Exception {
        String string2 = bundle.getString("Password");
        Log.v((String)"test", (String)string2);
        String string3 = Service.Connection(bundle, string2, 6);
        Log.v((String)"test", (String)("return" + string3));
        return string3;
    }

    public static String sendProblem(Bundle bundle) throws Exception {
        String string2 = String.valueOf((Object)bundle.getString("Mobile")) + "," + bundle.getString("Word");
        Log.v((String)"test", (String)("send" + string2));
        String string3 = Service.Connection(bundle, string2, 10);
        Log.v((String)"test", (String)("send return" + string3));
        return string3;
    }

    public static String setBoxStatus(Bundle bundle) throws Exception {
        String string2 = bundle.getString("Status");
        Log.v((String)"test", (String)("service17 data" + string2));
        String string3 = Service.Connection(bundle, string2, 17);
        Log.v((String)"test", (String)("return" + string3));
        return string3;
    }

    public static String sirenAlarm(Bundle bundle) throws Exception {
        String string2 = "Silence Alarm " + globalPref.getString("HARDWARE_ID", "") + "|";
        Log.v((String)"test", (String)("silen " + string2));
        String string3 = Service.Connection(bundle, string2, 8);
        Log.v((String)"test", (String)("return" + string3));
        return string3;
    }
}

