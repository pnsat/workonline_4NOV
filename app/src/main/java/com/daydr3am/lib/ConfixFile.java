/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.BufferedReader
 *  java.io.FileNotFoundException
 *  java.io.FileReader
 *  java.io.IOException
 *  java.io.Reader
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  org.json.JSONException
 */
package com.daydr3am.lib;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.json.JSONException;

public class ConfixFile {
    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public String readJsonFile(String string2) throws IOException, JSONException {
        StringBuffer stringBuffer = new StringBuffer(1000);
        try {
            BufferedReader bufferedReader = new BufferedReader((Reader)new FileReader(string2));
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
        catch (FileNotFoundException var4_6) {
            var4_6.printStackTrace();
            return stringBuffer.toString();
        }
    }
}

