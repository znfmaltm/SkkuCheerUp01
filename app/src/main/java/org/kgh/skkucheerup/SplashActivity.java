package org.kgh.skkucheerup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by KGH123 on 2017-06-22.
 */

public class SplashActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
/*
        try{
            Thread.sleep(4000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        ....load({...
           Intent i = new Intent(this,Main1_1Activity.class);
            i.putStringArrayListExtra("a", list);
            startActivity();
            finish();
            });
        */
        Intent i = new Intent(this,Main1_1Activity.class);
        //i.putStringArrayListExtra("a", list);
        startActivity(i);
        finish();
    }
}
