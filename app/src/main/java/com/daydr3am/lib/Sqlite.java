/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Random
 */
package com.daydr3am.lib;

import android.os.Bundle;
import java.util.Random;

public class Sqlite {
    /*
     * Enabled aggressive block sorting
     */
    public static String encoding(Bundle bundle) {
        int n = bundle.getInt("SERVICE_ID");
        int n2 = bundle.getInt("API_ID");
        int n3 = bundle.getInt("PARTNER_ID");
        int n4 = bundle.getInt("HARDWARE_ID");
        String string2 = Sqlite.generate200(bundle.getString("COMMAND_DATA"));
        String string3 = bundle.getString("HARDWARE_SN");
        Random random = new Random();
        int n5 = 10000 + random.nextInt(40000);
        int n6 = 10000 + random.nextInt(40000);
        int n7 = 10000 + random.nextInt(40000);
        int n8 = 10000 + random.nextInt(40000);
        String string4 = String.valueOf((int)(n5 + n * 1));
        String string5 = String.valueOf((int)(n6 + n2 * 1));
        String string6 = String.valueOf((int)(n7 + n3 * 1));
        String string7 = String.valueOf((int)(n8 + n4 * 1));
        String string8 = string2.substring(0, 100);
        String string9 = string2.substring(100, 200);
        String string10 = "" + n7 + n5 + string8 + string4 + string6 + n8 + n6 + string9 + string5 + string7;
        String string11 = string3;
        int n9 = 0;
        do {
            if (n9 >= 23) break;
            string11 = String.valueOf((Object)string11) + string3;
            ++n9;
        } while (true);
        int n10 = 1 + random.nextInt(8);
        int n11 = 1 + random.nextInt(8);
        String string12 = "";
        int n12 = 0;
        do {
            int n13;
            if (n12 >= (n13 = string11.length())) break;
            int n14 = n11 + (n10 + ((short)string11.charAt(n12) + (short)string10.charAt(n12)));
            Object[] arrobject = new Object[]{n14};
            String string13 = String.format((String)"%03d", (Object[])arrobject);
            string12 = String.valueOf((Object)string12) + string13;
            ++n12;
        } while (true);
        String string14 = String.valueOf((int)n10) + string12 + n11;
        int n15 = 0;
        int n16 = 0;
        do {
            int n17;
            if (n16 > (n17 = -1 + string14.length())) {
                Object[] arrobject = new Object[]{n15};
                String string15 = String.format((String)"%04d", (Object[])arrobject);
                String string16 = string15.substring(0, 2);
                String string17 = string15.substring(2, 4);
                String string18 = string14.substring(0, 361);
                String string19 = string14.substring(361, 722);
                return String.valueOf((Object)string16) + string18 + string17 + string19;
            }
            n15 += Integer.parseInt((String)("" + string14.charAt(n16)));
            ++n16;
        } while (true);
    }

    public static String generate200(String string2) {
        String string3 = String.valueOf((Object)string2) + "|";
        Random random = new Random();
        while (string3.length() < 200) {
            string3 = String.valueOf((Object)string3) + random.nextInt(10);
        }
        return string3;
    }
}

