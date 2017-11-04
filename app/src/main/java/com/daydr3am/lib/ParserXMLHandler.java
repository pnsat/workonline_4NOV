/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Message
 *  android.util.Log
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  java.util.Map
 *  org.xml.sax.Attributes
 *  org.xml.sax.SAXException
 *  org.xml.sax.helpers.DefaultHandler
 */
package com.daydr3am.lib;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParserXMLHandler
extends DefaultHandler {
    public static final int DID_FINISH_ELEMENT = 8;
    public static final int DID_START_ELEMENT = 1;
    public static final int END_DOCUMENT = 10;
    public static final int START_DOCUMENT;
    private Boolean currentElement = false;
    private String currentValue = null;
    private Handler handler;

    public void characters(char[] arrc, int n, int n2) throws SAXException {
        if (this.currentElement.booleanValue()) {
            this.currentValue = new String(arrc, n, n2);
            this.currentElement = false;
        }
    }

    public void endDocument() throws SAXException {
        this.handler.sendMessage(Message.obtain((Handler)this.handler, (int)10));
    }

    public void endElement(String string2, String string3, String string4) throws SAXException {
        this.currentElement = false;
        ElementData elementData = (ParserXMLHandler)this.new ElementData();
        elementData.name = string3;
        elementData.value = this.currentValue;
        this.handler.sendMessage(Message.obtain((Handler)this.handler, (int)8, (Object)elementData));
        this.currentValue = "";
    }

    public void startDocument() throws SAXException {
        this.handler.sendMessage(Message.obtain((Handler)this.handler, (int)0));
    }

    public void startElement(String string2, String string3, String string4, Attributes attributes) throws SAXException {
        this.currentElement = true;
        ElementData elementData = (ParserXMLHandler)this.new ElementData();
        elementData.name = string3;
        int n = 0;
        do {
            if (n >= attributes.getLength()) {
                this.handler.sendMessage(Message.obtain((Handler)this.handler, (int)1, (Object)elementData));
                return;
            }
            String string5 = attributes.getLocalName(n);
            String string6 = attributes.getValue(string5);
            elementData.attributes.put((Object)string5, (Object)string6);
            Log.d((String)"Parse Element", (String)("Type :" + string5 + " Value :" + string6));
            ++n;
        } while (true);
    }

    public class ElementData {
        public final Map<String, String> attributes;
        public String name;
        public String value;

        public ElementData() {
            this.name = null;
            this.attributes = new HashMap();
            this.value = null;
        }
    }

}

