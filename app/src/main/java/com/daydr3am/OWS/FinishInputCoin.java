/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.util.Log
 *  java.lang.String
 *  java.lang.System
 */
package com.daydr3am.OWS;

import android.os.Bundle;
import android.util.Log;
import com.daydr3am.OWS.IORootActivity;

public class FinishInputCoin
extends IORootActivity {
    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903047);
        super.onCreate(bundle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d((String)"debug", (String)"onResume active");
    }
}

