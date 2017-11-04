/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.graphics.Typeface
 *  android.os.Bundle
 *  android.os.Environment
 *  android.util.DisplayMetrics
 *  android.util.Log
 *  android.view.Display
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.WindowManager
 *  android.widget.Button
 *  android.widget.TextView
 *  android.widget.VideoView
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 */
package OWS;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.MessageTopup;

public class PlayVideo
extends IORootActivity {
    int id_text_play;
    int id_text_topic;

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void changeLanguage(int n) {
        Log.d((String)"debug", (String)("changeLanguage main " + n));
        TextView textView = (TextView)this.findViewById(2131361918);
        Button button = (Button)this.findViewById(2131361920);
        if (this.id_text_topic == 36) {
            this.id_text_topic = 89;
        } else if (this.id_text_topic == 37) {
            this.id_text_topic = 36;
        }
        textView.setText((CharSequence)MessageTopup.getMessage(this.id_text_topic));
        button.setText((CharSequence)MessageTopup.getMessage(this.id_text_play));
        textView.setTypeface(MessageTopup.setFont((Context)this, 0));
        button.setTypeface(MessageTopup.setFont((Context)this, 0));
    }

    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903060);
        super.onCreate(bundle);
        this.findViewById(2131361863).setVisibility(8);
        final Bundle bundle2 = this.getIntent().getExtras();
        String string2 = bundle2.getString("param1");
        this.id_text_play = 90 + bundle2.getInt("video_id");
        this.id_text_topic = 33 + bundle2.getInt("video_id");
        Log.v((String)"test", (String)string2);
        ((Button)this.findViewById(2131361920)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                String string2 = (Object)Environment.getExternalStorageDirectory() + "/Resource/Video/video" + bundle2.getInt("video_id") + ".mp4";
                Log.v((String)"hello", (String)("play path" + string2));
                VideoView videoView = (VideoView)PlayVideo.this.findViewById(2131361919);
                videoView.setVideoPath(string2);
                videoView.start();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                PlayVideo.this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int n = displayMetrics.heightPixels;
                int n2 = displayMetrics.widthPixels;
                Log.v((String)"hello", (String)("width " + n2 + " height " + n));
            }
        });
        this.changeLanguage(switchID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d((String)"debug", (String)"onResume active");
    }

}

