/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.annotation.TargetApi
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.hardware.Camera
 *  android.hardware.Camera$Parameters
 *  android.hardware.Camera$PictureCallback
 *  android.hardware.Camera$ShutterCallback
 *  android.hardware.Camera$Size
 *  android.os.AsyncTask
 *  android.util.Log
 *  android.view.Surface
 *  android.view.SurfaceHolder
 *  android.view.SurfaceHolder$Callback
 *  android.view.SurfaceView
 *  java.io.File
 *  java.io.FileOutputStream
 *  java.io.IOException
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.lang.Void
 *  java.util.Calendar
 *  java.util.Iterator
 *  java.util.List
 */
package OWS;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

class Preview
extends SurfaceView
implements Runnable {
    private static final String TAG = "Preview";
    public static int count;
    private static boolean inPreview;
    public Camera camera;
    private boolean cameraConfigured = false;
    public String hwID;
    SurfaceHolder mHolder;
    private SurfaceHolder previewHolder = null;
    SurfaceHolder.Callback surfaceCallback;
    Thread th;

    static {
        inPreview = false;
        count = 0;
    }

    Preview(Context context) {
        super(context);
        this.surfaceCallback = new SurfaceHolder.Callback(){

            public void surfaceChanged(SurfaceHolder surfaceHolder, int n, int n2, int n3) {
                Preview.this.initPreview(n2, n3);
                Preview.this.startPreview();
            }

            @SuppressLint(value={"NewApi"})
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                File file = new File("/mnt/sdcard/Resource/Camera/");
                int n = file.listFiles().length;
                int n2 = 0;
                if (n > 0) {
                    n2 = Integer.parseInt((String)file.listFiles()[0].getName().replace((CharSequence)"rotate", (CharSequence)"").replace((CharSequence)".json", (CharSequence)""));
                    Log.v((String)"Ant", (String)String.valueOf((int)n2));
                }
                try {
                    Log.v((String)"Ant", (String)("create " + inPreview));
                    Preview.this.camera = Camera.open((int)1);
                    Log.v((String)"Ant", (String)("camera " + (Object)Preview.this.camera));
                    Preview.this.camera.setDisplayOrientation(n2);
                    return;
                }
                catch (Exception var5_5) {
                    Log.v((String)"test", (String)("class " + this + " " + var5_5.toString()));
                    try {
                        Preview.this.camera = Camera.open((int)0);
                        Preview.this.camera.setDisplayOrientation(n2);
                        return;
                    }
                    catch (Exception var7_6) {
                        return;
                    }
                }
            }

            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            }
        };
        this.previewHolder = this.getHolder();
        this.previewHolder.addCallback(this.surfaceCallback);
        this.previewHolder.setType(3);
    }

    private Camera.Size getBestPreviewSize(int n, int n2, Camera.Parameters parameters) {
        Camera.Size size = null;
        Iterator iterator = parameters.getSupportedPreviewSizes().iterator();
        while (iterator.hasNext()) {
            Camera.Size size2 = (Camera.Size)iterator.next();
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

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void initPreview(int n, int n2) {
        if (this.camera != null && this.previewHolder.getSurface() != null) {
            Camera.Parameters parameters;
            Camera.Size size;
            try {
                this.camera.setPreviewDisplay(this.previewHolder);
            }
            catch (Throwable var3_5) {
                Log.e((String)"PreviewDemo-surfaceCallback", (String)"Exception in setPreviewDisplay()", (Throwable)var3_5);
            }
            if (!this.cameraConfigured && (size = super.getBestPreviewSize(n, n2, parameters = this.camera.getParameters())) != null) {
                parameters.setPreviewSize(size.width, size.height);
                this.camera.setParameters(parameters);
                this.cameraConfigured = true;
            }
        }
    }

    private void startPreview() {
        if (this.cameraConfigured && this.camera != null) {
            this.camera.startPreview();
            inPreview = true;
            this.th = new Thread((Runnable)this);
            this.th.start();
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint(-65536);
        Log.d((String)"Preview", (String)"draw");
        canvas.drawText("PREVIEW", (float)(canvas.getWidth() / 2), (float)(canvas.getHeight() / 2), paint);
    }

    /*
     * Exception decompiling
     */
    public void ftpFunction() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 7[CATCHBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
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

    @TargetApi(value=9)
    public void pause() {
        Log.v((String)"Ant", (String)"pause");
        if (inPreview && this.camera != null) {
            this.camera.stopPreview();
            this.th.interrupt();
        }
        if (this.camera != null) {
            this.camera.release();
        }
        this.camera = null;
        inPreview = false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void run() {
        try {
            do {
                this.takePicture();
                count = 1 + count;
                if (count == 1000) {
                    count = 0;
                }
                int n = Calendar.getInstance().get(11);
                Log.v((String)"hello", (String)("countnow" + count));
                if (n == 3 && count > 100) {
                    this.ftpFunction();
                    count = 0;
                }
                Thread.sleep((long)300000);
            } while (true);
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    public void takePicture() {
        new TakePictureTask(this, null).execute((Object[])new Void[0]);
    }

    private class TakePictureTask
    extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ Preview this$0;

        private TakePictureTask(Preview preview) {
            this.this$0 = preview;
        }

        /* synthetic */ TakePictureTask(Preview preview, TakePictureTask takePictureTask) {
            super(preview);
        }

        protected /* varargs */ Void doInBackground(Void ... arrvoid) {
            Camera.PictureCallback pictureCallback = new Camera.PictureCallback(){

                @SuppressLint(value={"SdCardPath"})
                public void onPictureTaken(byte[] arrby, Camera camera) {
                    File file = new File("/sdcard/Resource/save/camera/hello" + Preview.count + ".jpg");
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(arrby);
                        fileOutputStream.close();
                        return;
                    }
                    catch (IOException var5_5) {
                        var5_5.printStackTrace();
                        return;
                    }
                }
            };
            this.this$0.camera.takePicture(null, null, pictureCallback);
            try {
                Thread.sleep((long)300);
                return null;
            }
            catch (InterruptedException var3_3) {
                var3_3.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(Void void_) {
            if (this.this$0.camera != null) {
                this.this$0.camera.startPreview();
            }
        }

    }

}

