/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.TargetApi
 *  android.app.Activity
 *  android.content.Context
 *  android.hardware.Camera
 *  android.hardware.Camera$CameraInfo
 *  android.hardware.Camera$Parameters
 *  android.hardware.Camera$PictureCallback
 *  android.hardware.Camera$Size
 *  android.os.AsyncTask
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Environment
 *  android.util.Log
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.Surface
 *  android.view.SurfaceHolder
 *  android.view.SurfaceHolder$Callback
 *  android.view.SurfaceView
 *  android.widget.Toast
 *  java.io.File
 *  java.io.FileOutputStream
 *  java.io.IOException
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.Iterator
 *  java.util.List
 */
package com.daydr3am.lib;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class PictureDemo
extends Activity {
    private Camera camera = null;
    private boolean cameraConfigured = false;
    private boolean inPreview = false;
    Camera.PictureCallback photoCallback;
    public SurfaceView preview = null;
    private SurfaceHolder previewHolder = null;
    SurfaceHolder.Callback surfaceCallback;

    public PictureDemo() {
        this.surfaceCallback = new SurfaceHolder.Callback(){

            public void surfaceChanged(SurfaceHolder surfaceHolder, int n, int n2, int n3) {
                PictureDemo.this.initPreview(n2, n3);
                PictureDemo.this.startPreview();
            }

            public void surfaceCreated(SurfaceHolder surfaceHolder) {
            }

            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            }
        };
        this.photoCallback = new Camera.PictureCallback(){

            public void onPictureTaken(byte[] arrby, Camera camera) {
                new SavePhotoTask().execute((Object[])new byte[][]{arrby});
                camera.startPreview();
                PictureDemo.access$2(PictureDemo.this, true);
            }
        };
    }

    static /* synthetic */ void access$2(PictureDemo pictureDemo, boolean bl) {
        pictureDemo.inPreview = bl;
    }

    private Camera.Size getBestPreviewSize(int n, int n2, Camera.Parameters parameters) {
        Camera.Size size = null;
        Iterator iterator = parameters.getSupportedPreviewSizes().iterator();
        while (iterator.hasNext()) {
            Camera.Size size2 = (Camera.Size)iterator.next();
            if (size2.width > n || size2.height > n2) continue;
            if (size == null) {
                size = size2;
                continue;
            }
            int n3 = size.width * size.height;
            if (size2.width * size2.height <= n3) continue;
            size = size2;
        }
        return size;
    }

    private Camera.Size getSmallestPictureSize(Camera.Parameters parameters) {
        Camera.Size size = null;
        Iterator iterator = parameters.getSupportedPictureSizes().iterator();
        while (iterator.hasNext()) {
            Camera.Size size2 = (Camera.Size)iterator.next();
            if (size == null) {
                size = size2;
                continue;
            }
            int n = size.width * size.height;
            if (size2.width * size2.height >= n) continue;
            size = size2;
        }
        return size;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void initPreview(int n, int n2) {
        if (this.camera != null && this.previewHolder.getSurface() != null) {
            try {
                this.camera.setPreviewDisplay(this.previewHolder);
            }
            catch (Throwable var3_6) {
                Log.e((String)"PreviewDemo-surfaceCallback", (String)"Exception in setPreviewDisplay()", (Throwable)var3_6);
                Toast.makeText((Context)this, (CharSequence)var3_6.getMessage(), (int)1).show();
            }
            if (!this.cameraConfigured) {
                Camera.Parameters parameters = this.camera.getParameters();
                Camera.Size size = super.getBestPreviewSize(n, n2, parameters);
                Camera.Size size2 = super.getSmallestPictureSize(parameters);
                if (size != null && size2 != null) {
                    parameters.setPreviewSize(size.width, size.height);
                    parameters.setPictureSize(size2.width, size2.height);
                    parameters.setPictureFormat(256);
                    this.camera.setParameters(parameters);
                    this.cameraConfigured = true;
                }
            }
        }
    }

    private void startPreview() {
        if (this.cameraConfigured && this.camera != null) {
            this.camera.startPreview();
            this.inPreview = true;
        }
    }

    public void onCreate(Bundle bundle) {
        Log.v((String)"Ant", (String)"create");
        this.previewHolder = this.preview.getHolder();
        this.previewHolder.addCallback(this.surfaceCallback);
        this.previewHolder.setType(3);
    }

    public boolean onCreateOptionsMenu(Menu menu2) {
        Log.v((String)"Ant", (String)"createoption");
        new MenuInflater((Context)this).inflate(2131296256, menu2);
        return super.onCreateOptionsMenu(menu2);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Log.v((String)"Ant", (String)"item");
        return super.onOptionsItemSelected(menuItem);
    }

    public void onPause() {
        Log.v((String)"Ant", (String)"pause");
        if (this.inPreview) {
            this.camera.stopPreview();
        }
        this.camera.release();
        this.camera = null;
        this.inPreview = false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @TargetApi(value=9)
    public void onResume() {
        if (Build.VERSION.SDK_INT >= 9) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Log.v((String)"test", (String)("" + Camera.getNumberOfCameras()));
            for (int i = 0; i < Camera.getNumberOfCameras(); ++i) {
                Camera.getCameraInfo((int)i, (Camera.CameraInfo)cameraInfo);
                if (cameraInfo.facing != 1) continue;
                this.camera = Camera.open((int)i);
            }
        }
        if (this.camera == null) {
            this.camera = Camera.open();
        }
        this.startPreview();
    }

    public void setPreview(SurfaceView surfaceView) {
        this.preview = surfaceView;
    }

    class SavePhotoTask
    extends AsyncTask<byte[], String, String> {
        SavePhotoTask() {
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        protected /* varargs */ String doInBackground(byte[] ... arrby) {
            File file = new File(Environment.getExternalStorageDirectory(), "photo.jpg");
            if (file.exists()) {
                file.delete();
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
                fileOutputStream.write(arrby[0]);
                fileOutputStream.close();
                do {
                    return null;
                    break;
                } while (true);
            }
            catch (IOException var4_4) {
                Log.e((String)"PictureDemo", (String)"Exception in photoCallback", (Throwable)var4_4);
                return null;
            }
        }
    }

}

