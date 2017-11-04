/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.TableLayout
 *  android.widget.TableRow
 *  android.widget.TableRow$LayoutParams
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package com.daydr3am.OWS;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.Loading;

public class Record
extends IORootActivity {
    int page;
    String[] service = new String[]{"1 2 Call", "Dtac", "Truemove", "Hutch", "CAT-CDMA", "Truemoney", "@Cash", "True D Serial", "Cookie Card", "Winner Online", "TOT Prepaid", "Play Card", "Gcash", "Tolld", "\u0e16\u0e39\u0e01\u0e14\u0e35", "trueh"};

    private void createHeader() {
        TableLayout tableLayout = (TableLayout)this.findViewById(2131361923);
        TableRow tableRow = new TableRow((Context)this);
        tableRow.setId(99);
        tableRow.setLayoutParams((ViewGroup.LayoutParams)new TableRow.LayoutParams(-1, -2));
        TextView textView = new TextView((Context)this);
        textView.setText((CharSequence)"id");
        textView.setTextColor(-16777216);
        textView.setLayoutParams((ViewGroup.LayoutParams)new TableRow.LayoutParams(30, -2));
        tableRow.addView((View)textView);
        TextView textView2 = new TextView((Context)this);
        textView2.setText((CharSequence)"\u0e27\u0e31\u0e19\u0e17\u0e35\u0e48");
        textView2.setTextColor(-16777216);
        textView2.setLayoutParams((ViewGroup.LayoutParams)new TableRow.LayoutParams(170, -2));
        tableRow.addView((View)textView2);
        TextView textView3 = new TextView((Context)this);
        textView3.setText((CharSequence)"\u0e40\u0e04\u0e23\u0e37\u0e48\u0e2d\u0e02\u0e48\u0e32\u0e22");
        textView3.setTextColor(-16777216);
        textView3.setLayoutParams((ViewGroup.LayoutParams)new TableRow.LayoutParams(80, -2));
        tableRow.addView((View)textView3);
        TextView textView4 = new TextView((Context)this);
        textView4.setText((CharSequence)"\u0e40\u0e1a\u0e2d\u0e23\u0e4c\u0e42\u0e17\u0e23");
        textView4.setTextColor(-16777216);
        textView4.setLayoutParams((ViewGroup.LayoutParams)new TableRow.LayoutParams(100, -2));
        tableRow.addView((View)textView4);
        TextView textView5 = new TextView((Context)this);
        textView5.setText((CharSequence)"\u0e23\u0e32\u0e04\u0e32");
        textView5.setTextColor(-16777216);
        textView5.setLayoutParams((ViewGroup.LayoutParams)new TableRow.LayoutParams(50, -2));
        tableRow.addView((View)textView5);
        TextView textView6 = new TextView((Context)this);
        textView6.setText((CharSequence)"\u0e2a\u0e16\u0e32\u0e19\u0e30");
        textView6.setTextColor(-16777216);
        textView6.setLayoutParams((ViewGroup.LayoutParams)new TableRow.LayoutParams(-1, -2));
        tableRow.addView((View)textView6);
        tableLayout.addView((View)tableRow);
    }

    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903062);
        super.onCreate(bundle);
        this.findViewById(2131361862).setVisibility(8);
        this.findViewById(2131361863).setVisibility(8);
        this.findViewById(2131361924).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("Service", 15);
                bundle.putInt("Page", 1 + Record.this.page);
                Intent intent = new Intent((Context)Record.this, (Class)Loading.class);
                intent.putExtras(bundle);
                Record.this.startActivityForResult(intent, 0);
            }
        });
        this.findViewById(2131361925).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("Service", 15);
                bundle.putInt("Page", -1 + Record.this.page);
                if (-1 + Record.this.page >= 0) {
                    Intent intent = new Intent((Context)Record.this, (Class)Loading.class);
                    intent.putExtras(bundle);
                    Record.this.startActivityForResult(intent, 0);
                }
            }
        });
        Bundle bundle2 = this.getIntent().getExtras();
        if (bundle2 != null && bundle2.getInt("Page") != 0) {
            this.page = bundle2.getInt("Page");
            Log.v((String)"hello", (String)("newpage" + bundle2.getInt("Page")));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    /*
     * Exception decompiling
     */
    @Override
    protected void onResume() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl82 : TryStatement: try { 3[TRYBLOCK]

        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:44)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:22)
        // org.benf.cfr.reader.util.graph.GraphVisitorDFS.process(GraphVisitorDFS.java:68)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner.removeUnreachableCode(Cleaner.java:54)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.RemoveDeterministicJumps.apply(RemoveDeterministicJumps.java:35)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:507)
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

