/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.Typeface
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.text.Editable
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.TextView
 *  java.io.PrintStream
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.text.ParseException
 *  java.text.SimpleDateFormat
 *  java.util.Date
 *  java.util.Locale
 */
package com.daydr3am.OWS;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.MessageTopup;
import com.daydr3am.lib.AudioDemo;
import com.daydr3am.lib.CallImage;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtility
extends IORootActivity {
    View.OnClickListener clickAction;
    int idActive = 2131361822;
    String[] imageBG = new String[]{"bg_blue", "bg_pink", "bg_green", "bg_orange"};
    int index_background;
    View.OnClickListener nextAction;
    int[][][] payPrice = new int[][][]{{{10, 20, 30, 40, 50, 60, 100, 200, 300, 500}, {10, 20, 30, 40, 50, 60, 100, 200, 300, 500}, {10, 20, 30, 40, 50, 60, 100, 200, 300, 500}, {20, 50, 100, 300, 500, 800}, {10, 20, 50, 100, 300, 500}}, {{50, 90, 150, 300, 1000}, {28, 55, 89, 159, 189, 245, 349, 450, 888}, {25, 50, 90, 150, 300, 500, 1000}, {25, 50, 90, 150, 300, 500, 1000}, {49, 149, 299, 399, 555, 999}, {25, 55, 149, 199, 399}, {25, 50, 90, 150, 300, 500, 1000}, {50, 100, 200, 300, 500, 1000}, {50, 90, 150, 300}, {49, 99, 149, 299, 499, 799, 999}}, {{100}, {100, 300}}};

    public DateUtility() {
        this.clickAction = new View.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View view) {
                EditText editText = (EditText)DateUtility.this.findViewById(DateUtility.this.idActive);
                Log.v((String)"hello", (String)("length " + editText.getText().length()));
                String string2 = ((Button)view).getHint().toString();
                if (string2.equals((Object)"delete")) {
                    if (editText.getText().length() >= 1) {
                        editText.setText(editText.getText().toString().toCharArray(), 0, -1 + editText.getText().length());
                        AudioDemo.Sound().playSound("del");
                    } else if (editText.getText().length() == 0 && DateUtility.this.idActive == 2131361802) {
                        DateUtility.this.idActive = 2131361823;
                        editText = (EditText)DateUtility.this.findViewById(DateUtility.this.idActive);
                        editText.setText(editText.getText().toString().toCharArray(), 0, -1 + editText.getText().length());
                        AudioDemo.Sound().playSound("del");
                    } else if (editText.getText().length() == 0 && DateUtility.this.idActive == 2131361823) {
                        DateUtility.this.idActive = 2131361822;
                        editText = (EditText)DateUtility.this.findViewById(DateUtility.this.idActive);
                        editText.setText(editText.getText().toString().toCharArray(), 0, -1 + editText.getText().length());
                        AudioDemo.Sound().playSound("del");
                    }
                } else if (string2.equals((Object)"star") && editText.getText().length() < 10) {
                    editText.setText((CharSequence)(String.valueOf((Object)editText.getText().toString()) + "."));
                } else if (editText.getText().length() < 2) {
                    AudioDemo.Sound().playSound("z" + string2);
                    editText.setText((CharSequence)(String.valueOf((Object)editText.getText().toString()) + string2));
                }
                if (editText.getText().length() == 2 && DateUtility.this.idActive == 2131361822) {
                    DateUtility.this.idActive = 2131361823;
                    return;
                } else {
                    if (editText.getText().length() != 2 || DateUtility.this.idActive != 2131361823) return;
                    {
                        DateUtility.this.idActive = 2131361802;
                        return;
                    }
                }
            }
        };
        this.nextAction = new View.OnClickListener(){

            /*
             * Exception decompiling
             */
            public void onClick(View var1) {
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
                // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
                // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
                // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
                // org.benf.cfr.reader.Main.doJar(Main.java:128)
                // com.njlabs.showjava.processor.JavaExtractor$1.run(JavaExtractor.java:100)
                // java.lang.Thread.run(Thread.java:818)
                throw new IllegalStateException("Decompilation failed");
            }
        };
    }

    @Override
    public void changeLanguage(int n) {
        TextView textView = (TextView)this.findViewById(2131361821);
        textView.setText((CharSequence)"\u0e01\u0e23\u0e38\u0e13\u0e32\u0e01\u0e23\u0e2d\u0e01\u0e27\u0e31\u0e19\u0e17\u0e35\u0e48 \u0e27\u0e31\u0e19-\u0e40\u0e14\u0e37\u0e2d\u0e19-\u0e1b\u0e35");
        textView.setTypeface(MessageTopup.setFont((Context)this, 0));
        Button button = (Button)this.findViewById(2131361839);
        if (button != null) {
            button.setText((CharSequence)MessageTopup.getMessage(80));
        }
        if (this.landTextShow != null) {
            this.landTextShow.setText((CharSequence)"\u0e27\u0e31\u0e19\u0e04\u0e23\u0e1a\u0e01\u0e33\u0e2b\u0e19\u0e14\u0e0a\u0e33\u0e23\u0e30");
        }
    }

    public Date isThisDateValid(String string2, String string3) {
        if (string2 == null) {
            return null;
        }
        Log.v((String)"test", (String)("locale " + (Object)new Locale("th", "th")));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(string3, new Locale("th", "th"));
        simpleDateFormat.setLenient(false);
        Date date = null;
        try {
            date = simpleDateFormat.parse(string2);
            System.out.println((Object)date);
            return date;
        }
        catch (ParseException var6_5) {
            var6_5.printStackTrace();
            return date;
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903044);
        super.onCreate(bundle);
        this.findViewById(2131361822).setEnabled(false);
        this.findViewById(2131361823).setEnabled(false);
        this.findViewById(2131361802).setEnabled(false);
        Log.d((String)"debug", (String)("MessageTopup >> " + MessageTopup.getMessage(3)));
        Log.d((String)"debug", (String)("logo " + (Object)this.findViewById(2131361820)));
        ImageView imageView = (ImageView)this.findViewById(2131361820);
        Bundle bundle2 = this.getIntent().getExtras();
        Resources resources = this.getResources();
        imageView.setImageDrawable(resources.getDrawable(resources.getIdentifier("logo_service" + bundle2.getString("Network"), "drawable", this.getPackageName())));
        ((FrameLayout)this.findViewById(2131361799)).setBackgroundDrawable(CallImage.imageDrawableCard(this.imageBG[3]));
        this.index_background = 3;
        ViewGroup viewGroup = (ViewGroup)this.findViewById(2131361824);
        int n = viewGroup.getChildCount();
        int n2 = 0;
        block0 : do {
            if (n2 >= n) {
                this.next.setOnClickListener(this.nextAction);
                return;
            }
            ViewGroup viewGroup2 = (ViewGroup)viewGroup.getChildAt(n2);
            int n3 = viewGroup2.getChildCount();
            int n4 = 0;
            do {
                if (n4 >= n3) {
                    ++n2;
                    continue block0;
                }
                ((Button)viewGroup2.getChildAt(n4)).setOnClickListener(this.clickAction);
                ++n4;
            } while (true);
            break;
        } while (true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AudioDemo.Sound().playSound("e09");
        if (this.findViewById(2131361841) != null) {
            this.findViewById(2131361841).setOnClickListener(this.nextAction);
        }
    }

}

