/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.Cursor
 *  android.database.SQLException
 *  android.database.sqlite.SQLiteDatabase
 *  android.database.sqlite.SQLiteDatabase$CursorFactory
 *  android.database.sqlite.SQLiteException
 *  android.database.sqlite.SQLiteOpenHelper
 *  android.util.Log
 *  java.io.BufferedReader
 *  java.io.IOException
 *  java.io.InputStream
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.IllegalStateException
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Thread
 *  java.net.HttpURLConnection
 *  java.net.MalformedURLException
 *  java.net.URL
 *  java.net.URLConnection
 */
package com.daydr3am.lib;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DBAdapter {
    private static final String DATABASE_NAME = "DataBase1";
    private static final int DATABASE_VERSION = 2;
    public static final String OPTIMIZATION_SQL = "PRAGMA synchronous=OFF; PRAGMA count_changes=OFF; VACUUM;";
    private String base = "";
    private final Context mCtx;
    private SQLiteDatabase mDb;
    private DatabaseHelper mDbHelper;

    public DBAdapter(Context context) {
        this.mCtx = context;
    }

    static String getValue(String string2, String string3, String string4) {
        try {
            String string5 = string2.substring(string2.indexOf(string3.toString()), string2.indexOf(string4.toString())).replaceAll(string3, "").replace((CharSequence)"<![CDATA[", (CharSequence)"").replace((CharSequence)"]]>", (CharSequence)"").replace((CharSequence)"'", (CharSequence)"");
            return string5;
        }
        catch (Exception var3_4) {
            return "";
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void chekDbAvailable() {
        do {
            if (!this.isDbLockedByCurrentThread() && !this.isDbLockedByOtherThreads()) continue;
            try {
                Thread.sleep((long)1000);
                continue;
            }
            catch (InterruptedException var2_2) {
                continue;
            }
            catch (SQLiteException var1_1) {}
        } while (this.isOpen() && (this.isDbLockedByCurrentThread() || this.isDbLockedByOtherThreads()));
    }

    public void deleteAritcles(String string2) {
        this.mDb.execSQL("delete from articles where stype = '" + string2 + "' ;");
    }

    public void deleteAritclesAll() {
        this.mDb.execSQL("delete from articles  ;");
    }

    public void deleteLogin() {
        this.mDb.execSQL("delete from login;");
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public void feedrss(String var1, String var2_2) {
        if (this.getAritcles(var2_2) != false) return;
        Log.i((String)"TAG", (String)("surl==============================>" + var1));
        try {
            var5_4 = var4_3 = new URL(var1);
        }
        catch (MalformedURLException var23_5) {
            var23_5.printStackTrace();
            var5_4 = null;
        }
        if (var5_4 == null) ** GOTO lbl12
        ** GOTO lbl14
lbl12: // 1 sources:
        Log.i((String)"TAG", (String)"url is null");
        return;
lbl14: // 1 sources:
        var9_6 = (HttpURLConnection)var5_4.openConnection();
        var9_6.setDoInput(true);
        var9_6.setDoOutput(true);
        var9_6.connect();
        var10_7 = var9_6.getInputStream();
        var11_8 = new InputStreamReader(var10_7);
        var12_9 = new BufferedReader((Reader)var11_8);
        var13_10 = new StringBuffer();
        do {
            if ((var14_11 = var12_9.readLine()) == null) {
                var12_9.close();
                var11_8.close();
                var10_7.close();
                var9_6.disconnect();
                if (var1.equals((Object)"http://www.komchadluek.net/rss/ipad/new_feed_kom_ipad/feed_kom_politic.xml")) {
                    Log.v((String)"test", (String)((Object)var13_10));
                }
                var13_10.toString().replace((CharSequence)"'", (CharSequence)"");
                var16_12 = var13_10.toString().split("</item>");
                Log.i((String)"TAG", (String)("str.length==============================>" + var16_12.length));
                var18_13 = 0;
                break;
            }
            var13_10.append(var14_11).append("\n");
        } while (true);
        do {
            if (var18_13 >= (var20_15 = (var19_14 = var16_12.length) - 1)) {
                return;
            }
            try {
                this.insertAritcles(var2_2, DBAdapter.getValue(var16_12[var18_13], "<column>", "</column>"), DBAdapter.getValue(var16_12[var18_13], "<label>", "</label>"), DBAdapter.getValue(var16_12[var18_13], "<title>", "</title>"), DBAdapter.getValue(var16_12[var18_13], "<byline>", "</byline>"), DBAdapter.getValue(var16_12[var18_13], "<description>", "</description>"), DBAdapter.getValue(var16_12[var18_13], "<detail>", "</detail>"), DBAdapter.getValue(var16_12[var18_13], "<link>", "</link>"), DBAdapter.getValue(var16_12[var18_13], "<pubDate>", "</pubDate>"), DBAdapter.getValue(var16_12[var18_13], "<thumbnail>", "</thumbnail>"), DBAdapter.getValue(var16_12[var18_13], "<photo>", "</photo>"), DBAdapter.getValue(var16_12[var18_13], "<adpic>", "</adpic>"));
            }
            catch (IOException var7_16) {
                Log.i((String)"TAG", (String)("IOException" + (Object)var7_16));
                return;
            }
            ++var18_13;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public void feedrss2(String var1, String var2_2) {
        if (this.getAritcles(var2_2) != false) return;
        Log.i((String)"TAG", (String)("surl==============================>" + var1));
        try {
            var5_4 = var4_3 = new URL(var1);
        }
        catch (MalformedURLException var25_5) {
            var25_5.printStackTrace();
            var5_4 = null;
        }
        if (var5_4 == null) ** GOTO lbl12
        ** GOTO lbl14
lbl12: // 1 sources:
        Log.i((String)"TAG", (String)"url is null");
        return;
lbl14: // 1 sources:
        var9_6 = (HttpURLConnection)var5_4.openConnection();
        var9_6.setDoInput(true);
        var9_6.setDoOutput(true);
        var9_6.connect();
        var10_7 = var9_6.getInputStream();
        var11_8 = new InputStreamReader(var10_7);
        var12_9 = new BufferedReader((Reader)var11_8);
        var13_10 = new StringBuffer();
        do {
            if ((var14_11 = var12_9.readLine()) == null) {
                var12_9.close();
                var11_8.close();
                var10_7.close();
                var9_6.disconnect();
                Log.i((String)"TAG", (String)("==============================>" + var13_10.toString()));
                var13_10.toString().replace((CharSequence)"'", (CharSequence)"");
                var17_12 = var13_10.toString().split("</item>");
                Log.i((String)"TAG", (String)("str.length==============================>" + var17_12.length));
                var19_13 = 0;
                break;
            }
            var13_10.append(var14_11).append("\n");
        } while (true);
        do {
            if (var19_13 >= (var21_14 = (var20_15 = var17_12.length) - 1)) {
                return;
            }
            try {
                Log.i((String)"TAG", (String)("enclosure==============================>" + DBAdapter.getValue(var17_12[var19_13], "<enclosure url=", "/>")));
                Log.i((String)"TAG", (String)("video==============================>" + DBAdapter.getValue(var17_12[var19_13], "<video>", "</video>")));
                this.insertAritcles(var2_2, DBAdapter.getValue(var17_12[var19_13], "<column>", "</column>"), DBAdapter.getValue(var17_12[var19_13], "<label>", "</label>"), DBAdapter.getValue(var17_12[var19_13], "<enclosure url=", "/>"), DBAdapter.getValue(var17_12[var19_13], "<byline>", "</byline>"), DBAdapter.getValue(var17_12[var19_13], "<video>", "</video>"), DBAdapter.getValue(var17_12[var19_13], "<detail>", "</detail>"), DBAdapter.getValue(var17_12[var19_13], "<link>", "</link>"), DBAdapter.getValue(var17_12[var19_13], "<pubDate>", "</pubDate>"), DBAdapter.getValue(var17_12[var19_13], "<thumbnail>", "</thumbnail>"), DBAdapter.getValue(var17_12[var19_13], "<photo>", "</photo>"), DBAdapter.getValue(var17_12[var19_13], "<adpic>", "</adpic>"));
            }
            catch (IOException var7_16) {
                Log.i((String)"TAG", (String)("IOException" + (Object)var7_16));
                return;
            }
            ++var19_13;
        } while (true);
    }

    public boolean getAritcles(String string2) {
        int n = this.mDb.query(true, "articles", new String[]{"*"}, " stype = '" + string2 + "'", null, null, null, null, null).getCount();
        Log.d((String)"dev", (String)(String.valueOf((Object)string2) + "numRows=====>" + n));
        return false;
    }

    /*
     * Exception decompiling
     */
    public boolean getAritclesEx(String var1) {
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

    public boolean getFirst(String string2) {
        if (this.mDb.query(true, "first", new String[]{"*"}, " datetime = '" + string2 + "'", null, null, null, null, null).getCount() > 0) {
            return true;
        }
        return false;
    }

    public String getLogin() {
        Cursor cursor = this.mDb.query(true, "login", new String[]{"*"}, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getString(cursor.getColumnIndex("user"));
        }
        return "0";
    }

    public String getLoginemail() {
        Cursor cursor = this.mDb.query(true, "login", new String[]{"*"}, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getString(cursor.getColumnIndex("email"));
        }
        return "";
    }

    public String getLoginpwd() {
        Cursor cursor = this.mDb.query(true, "login", new String[]{"*"}, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getString(cursor.getColumnIndex("pwd"));
        }
        return "0";
    }

    public Cursor getValueAritcles(String string2) {
        Cursor cursor = this.mDb.query(true, "articles", new String[]{"*"}, " stype = '" + string2 + "'", null, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getValueAritclesbyID(int n) {
        Cursor cursor = this.mDb.query(true, "articles", new String[]{"*"}, " _id = '" + n + "'  order by _id asc", null, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getValueAritclesbyID2(int n, String string2) {
        Cursor cursor = this.mDb.query(true, "articles", new String[]{"*"}, " _id > '" + n + "' and stype = '" + string2 + "' order by _id ASC", null, null, null, null, null);
        if (cursor.getCount() > 0) {
            // empty if block
        }
        return cursor;
    }

    public Cursor getValueAritclesbyID3(int n, String string2) {
        Cursor cursor = this.mDb.query(true, "articles", new String[]{"*"}, " _id < '" + n + "' and stype = '" + string2 + "' ", null, null, null, null, null);
        if (cursor.getCount() > 0) {
            // empty if block
        }
        return cursor;
    }

    public Cursor getValueAritclesbyStype(String string2) {
        Cursor cursor = this.mDb.query(true, "articles", new String[]{"*"}, " stype = '" + string2 + "'", null, null, null, null, null);
        if (cursor.getCount() > 0) {
            // empty if block
        }
        return cursor;
    }

    public void insertAritcles(String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11, String string12, String string13) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("stype", string2);
        contentValues.put("column", string3);
        contentValues.put("label", string4);
        contentValues.put("title", string5);
        contentValues.put("byline", string6);
        contentValues.put("description", string7);
        contentValues.put("detail", string8);
        contentValues.put("link", string9);
        contentValues.put("pubDate", string10);
        contentValues.put("thumbnail", string11);
        contentValues.put("photo", string12);
        contentValues.put("adpic", string13);
        this.mDb.insert("articles", null, contentValues);
    }

    public void insertData(ContentValues contentValues) {
        this.mDb.insert("record", null, contentValues);
    }

    public void install() {
        this.mDb.execSQL("CREATE TABLE IF NOT EXISTS record (Id integer primary key autoincrement , stype text,Date text,ServiceType text,PhoneNumber text,Price text,Status text);");
    }

    public boolean isDbLockedByCurrentThread() {
        if (this.mDb != null && this.mDb.isDbLockedByCurrentThread()) {
            return true;
        }
        return false;
    }

    public boolean isDbLockedByOtherThreads() {
        if (this.mDb != null && this.mDb.isDbLockedByOtherThreads()) {
            return true;
        }
        return false;
    }

    public boolean isOpen() {
        if (this.mDb != null && this.mDb.isOpen()) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public DBAdapter open() throws SQLException {
        this.chekDbAvailable();
        try {
            this.mDbHelper = new DatabaseHelper(this.mCtx);
            this.mDb = this.mDbHelper.getWritableDatabase();
            this.mDb.execSQL("PRAGMA synchronous=OFF; PRAGMA count_changes=OFF; VACUUM;");
            if (this.mDb.isDbLockedByCurrentThread()) {
                throw new IllegalStateException(" isDbLockedByCurrentThread ");
            }
            if (!this.mDb.isDbLockedByOtherThreads()) return this;
            {
                throw new IllegalStateException(" isDbLockedByOtherThreads ");
            }
        }
        catch (SQLiteException var1_1) {
            Log.d((String)"nation", (String)("-------------------------> SQLiteException" + (Object)var1_1));
            return this;
        }
    }

    public Cursor query() {
        Cursor cursor = this.mDb.query(true, "record", new String[]{"*"}, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            // empty if block
        }
        return cursor;
    }

    public void saveFirst(String string2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("datetime", string2);
        this.mDb.insert("first", null, contentValues);
    }

    public void saveLogin(String string2, String string3, String string4) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", string2);
        contentValues.put("pwd", string3);
        contentValues.put("email", string4);
        this.mDb.insert("login", null, contentValues);
    }

    private static class DatabaseHelper
    extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, "DataBase1", null, 2);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int n, int n2) {
            Log.w((String)"nation", (String)("Upgrading database from version " + n + " to " + n2 + ", which will destroy all old data"));
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS notes");
            this.onCreate(sQLiteDatabase);
        }
    }

}

