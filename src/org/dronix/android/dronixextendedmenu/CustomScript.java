package org.dronix.android.dronixextendedmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Author: Ivan Morgillo
 * E-mail: imorgillo [at] gmail [dot] com
 * Date: 10/1/11
 */
public class CustomScript extends Activity {
    private DEMUtil alert;

    public void onCreate(Bundle savedInstanceState) {
       alert = new DEMUtil(this);

       super.onCreate(savedInstanceState);
       setContentView(R.layout.custom_script);

       final Button button = (Button) findViewById(R.id.custom_script_run_button);
       button.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
/*               String[] str ={"su","-c","/system/bin/bash /system/xbin/theme_switcher ginger"};
               try {
                   Process p = Runtime.getRuntime().exec(str);
               } catch (IOException e) {
                   e.printStackTrace();
               }*/
               alert.ts("KABOOM");
           }
       });
   }
}
