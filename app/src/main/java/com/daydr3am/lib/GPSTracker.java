/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.Service
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.location.Location
 *  android.location.LocationListener
 *  android.location.LocationManager
 *  android.os.Bundle
 *  android.os.IBinder
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 */
package com.daydr3am.lib;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class GPSTracker
extends Service
implements LocationListener {
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 60000;
    float accuracy = 0.0f;
    float bearing = 0.0f;
    boolean canGetLocation = false;
    boolean isGPSEnabled = true;
    boolean isNetworkEnabled = true;
    double latitude;
    Location location;
    protected LocationManager locationManager;
    double longitude;
    private final Context mContext;
    int satelliteCount = 0;
    float speed;

    public GPSTracker(Context context) {
        this.mContext = context;
        this.getLocation();
    }

    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    public float getAccuracy() {
        return this.accuracy;
    }

    public float getBearing() {
        return this.bearing;
    }

    public String getLat() {
        return Data.SAVE_LOCATION_LAT;
    }

    public double getLatitude() {
        if (this.location != null) {
            this.latitude = this.location.getLatitude();
        }
        return this.latitude;
    }

    /*
     * Exception decompiling
     */
    public Location getLocation() {
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

    public String getLong() {
        return Data.SAVE_LOCATION_LONG;
    }

    public double getLongitude() {
        if (this.location != null) {
            this.longitude = this.location.getLongitude();
        }
        return this.longitude;
    }

    public int getSatelliteCount() {
        return this.satelliteCount;
    }

    public float getSpeed() {
        return this.speed;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onLocationChanged(Location location) {
    }

    public void onProviderDisabled(String string2) {
    }

    public void onProviderEnabled(String string2) {
    }

    public void onStatusChanged(String string2, int n, Bundle bundle) {
    }

    public void setSatelliteCount(int n) {
        this.satelliteCount = n;
    }

    public void setSpeed(float f) {
        this.speed = f;
    }

    public void showSettingsAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setTitle((CharSequence)"GPS is settings");
        builder.setMessage((CharSequence)"GPS is not enabled. Do you want to go to settings menu?");
        builder.setPositiveButton((CharSequence)"Settings", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
                GPSTracker.this.mContext.startActivity(intent);
            }
        });
        builder.setNegativeButton((CharSequence)"Cancel", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    public void stopUsingGPS() {
        if (this.locationManager != null) {
            this.locationManager.removeUpdates((LocationListener)this);
        }
    }

    private static class Data {
        public static String SAVE_LOCATION_LAT;
        public static String SAVE_LOCATION_LONG;
        public static String SAVE_LOCATION_SPEED;
        public static String SAVE_LOCATION_TYPE;

        private Data() {
        }
    }

}

