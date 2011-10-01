package org.dronix.android.dronixextendedmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.stericson.RootTools.RootTools;
import com.stericson.RootTools.RootToolsException;

import java.io.IOException;

/**
 * Author: Ivan Morgillo
 * E-mail: imorgillo [at] gmail [dot] com
 * Date: 10/1/11
 */
public class CustomScript extends Activity {
    private DEMUtil alert;
    private static final String TAG = "CustomScript";

    public void onCreate(Bundle savedInstanceState) {
       alert = new DEMUtil(this);

       super.onCreate(savedInstanceState);
       setContentView(R.layout.custom_script);

       final Button button = (Button) findViewById(R.id.custom_script_run_button);
       button.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               try {
                   RootTools.sendShell("/system/xbin/theme_switcher ginger");
               } catch (IOException e) {
                   e.printStackTrace();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               } catch (RootToolsException e) {
                   e.printStackTrace();
               }
               /*String[] cmd ={"su","-c","/system/xbin/theme_switcher ginger"};
               try {
                   Process p1 = Runtime.getRuntime().exec(cmd);
               } catch (IOException e) {
                   e.printStackTrace();
               }*/
               alert.ts("KABOOM");
           }
       });
   }
}
