/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.graphics.Typeface
 *  android.os.Bundle
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.TextView
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package OWS;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.daydr3am.OWS.IORootActivity;
import com.daydr3am.OWS.MainPage;
import com.daydr3am.OWS.MessageTopup;

public class FinishReport
extends IORootActivity {
    @Override
    public void onCreate(Bundle bundle) {
        this.setContentView(2130903048);
        super.onCreate(bundle);
        ((TextView)this.findViewById(2131361859)).setTypeface(MessageTopup.setFont((Context)this, 0));
        ((Button)this.findViewById(2131361797)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent intent = new Intent(FinishReport.this.getBaseContext(), (Class)MainPage.class);
                intent.setFlags(67108864);
                FinishReport.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d((String)"debug", (String)"onResume active");
    }

}

