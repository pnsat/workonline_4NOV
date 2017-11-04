/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.ContextWrapper
 *  android.content.res.AssetManager
 *  android.graphics.Typeface
 *  android.util.Log
 *  java.io.Reader
 *  java.io.StringReader
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  javax.xml.parsers.DocumentBuilder
 *  javax.xml.parsers.DocumentBuilderFactory
 *  org.w3c.dom.CharacterData
 *  org.w3c.dom.Document
 *  org.w3c.dom.Node
 *  org.w3c.dom.NodeList
 *  org.xml.sax.InputSource
 */
package OWS;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;
import com.daydr3am.OWS.IORootActivity;
import java.io.Reader;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class MessageTopup {
    public static final String EN_TEXT0 = "text test eng 0";
    private static String FONTS_PATH = "/mnt/sdcard/Resource/message/";
    public static final String TH_TEXT0 = "text test thai 0";
    public static Context baseContext;
    static NodeList nodes;

    /*
     * Exception decompiling
     */
    public static void LoadMessage() {
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

    public static String getCharacterDataFromElement(Node node) {
        Node node2 = node.getFirstChild();
        if (node2 instanceof CharacterData) {
            return ((CharacterData)node2).getData();
        }
        return "";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void getData(String string2) {
        int n;
        String string3 = "<topup>" + MessageTopup.getValue(string2, "<topup>", "</topup>") + "</topup>";
        Log.d((String)"debug", (String)("messageLang >> " + string3));
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream((Reader)new StringReader(string3));
            nodes = documentBuilder.parse(inputSource).getElementsByTagName("message");
            Log.d((String)"debug", (String)(" nodes " + nodes.getLength()));
            n = 0;
        }
        catch (Exception var3_8) {
            var3_8.printStackTrace();
            return;
        }
        while (n < nodes.getLength()) {
            Node node = nodes.item(n);
            Node node2 = node.getChildNodes().item(1);
            Node node3 = node.getChildNodes().item(3);
            MessageTopup.getCharacterDataFromElement(node2);
            MessageTopup.getCharacterDataFromElement(node3);
            ++n;
        }
        return;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String getMessage(int n) {
        String string2;
        String string3;
        if (nodes == null) {
            MessageTopup.LoadMessage();
            Node node = nodes.item(n);
            Node node2 = node.getChildNodes().item(1);
            Node node3 = node.getChildNodes().item(3);
            string2 = MessageTopup.getCharacterDataFromElement(node2);
            string3 = MessageTopup.getCharacterDataFromElement(node3);
        } else {
            Node node = nodes.item(n);
            Node node4 = node.getChildNodes().item(1);
            Node node5 = node.getChildNodes().item(3);
            string2 = MessageTopup.getCharacterDataFromElement(node4);
            string3 = MessageTopup.getCharacterDataFromElement(node5);
        }
        String string4 = IORootActivity.switchID == 1 ? string2 : string3;
        if (string4 == null) return "Not message";
        if (!string4.equalsIgnoreCase("null")) return string4;
        return "Not message";
    }

    public static String getValue(String string2, String string3, String string4) {
        Log.d((String)"debug_data", (String)"getvalue --------------------- star ");
        try {
            String string5 = string2.substring(string2.indexOf(string3.toString()), string2.indexOf(string4.toString())).replaceAll(string3, "").replace((CharSequence)"<![CDATA[", (CharSequence)"").replace((CharSequence)"]]>", (CharSequence)"").replace((CharSequence)"'", (CharSequence)"");
            return string5;
        }
        catch (Exception var4_4) {
            Log.d((String)"debug", (String)("Exception " + (Object)var4_4));
            return "";
        }
    }

    public static Typeface setFont(Context context, int n) {
        String[] arrstring = new String[]{"PSLxKittithada Bold.ttf", "PSLxKittithada BoldItalic.ttf", "PSLxKittithada Italic.ttf", "PSLxKittithada.ttf"};
        try {
            Typeface typeface = Typeface.createFromAsset((AssetManager)((ContextWrapper)context).getBaseContext().getAssets(), (String)arrstring[n]);
            return typeface;
        }
        catch (Exception var3_4) {
            return null;
        }
    }
}

