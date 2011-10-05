package org.dronix.android.dronixextendedmenu;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import com.stericson.RootTools.RootToolsException;

import java.io.IOException;

/**
 * User: harlem88
 * Date: 9/29/11
 * Time: 8:37 PM
 */
public class IconSwitcherActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iconswt);
        fsm = new FSmanager();
        this.checkT = 0;
        checkbt1 = (CheckBox) findViewById(R.id.check_i_battery1);
        checkbt2 = (CheckBox) findViewById(R.id.check_i_battery2);
        final DEMUtil dem = new DEMUtil(this);
        theme = checkTheme();
        setChecks(theme);

        checkbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCheck(0);
                /*pD=ProgressDialog.show(IconSwitcherActivity.this,"Icon Switcher", "Set  battery 1 icon , now reboot framework",  true ,false);

                                   TaskApplyIcon tAt=new TaskApplyIcon();
                                   tAt.execute("0", "battery1");
                */
                showDialog(PDDIALOG);
                progressBar= 0;
                pD.setProgress(0);
                TaskApplyIcon doTask = new TaskApplyIcon();
                doTask.execute("", "battery1", "");
            }
        });

        checkbt2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setCheck(1);
              /*
               pD=ProgressDialog.show(IconSwitcherActivity.this,"Icon Switcher", "Set  battery 2 icon , now reboot framework",  true ,false);

                       TaskApplyIcon tAt=new TaskApplyIcon();
                        tAt.execute("1", "battery2");
            */
                showDialog(PDDIALOG);
                progressBar=0;
                pD.setProgress(0);
                TaskApplyIcon doTask = new TaskApplyIcon();
                        doTask.execute("","battery2","");
                }
            });
          }
    private void setCheck(int checkT){

                    if(checkT==0)
                           checkbt1.setChecked(true);
                    else checkbt1.setChecked(false);
                    if(checkT==1)
                     checkbt2.setChecked(true);
                    else checkbt2.setChecked(false);
        };
    private class TaskApplyIcon extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            Thread thr=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (progressBar<100) {
                                try {
                                   Thread.sleep(96);
                                       } catch (InterruptedException e) {
                                               e.printStackTrace();
                                       }
                                    progressBar++;
                                    pD.setProgress(progressBar);

                                   }
                                }
                               });
                        thr.start();
                        try {
                            fsm.setTheme(params[1]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (RootToolsException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
              return "Finish !";
        }
        @Override
        protected void onProgressUpdate(String... values) {
                 pD.setMessage(values[0] + "\n");
                   }
        }
   private void setChecks(String theme) {
        if (theme.contains("battery1"))
               checkbt1.setChecked(true);
        else checkbt1.setChecked(false);

        if (theme.contains("battery2"))
            checkbt2.setChecked(true);
         else checkbt2.setChecked(false);
    }
    public String checkTheme(){
       String theme="";
       theme=DEMUtil.exec("grep BATTERY /etc/dronix.prop");
       theme=theme.substring(8);
    return theme;
    }

     protected Dialog onCreateDialog(int id) {
         switch (id) {
             case PDDIALOG: {
                 pD = new ProgressDialog(this);
                 pD.setTitle(getString(R.string.icon_switcher));
                 pD.setMessage(getString(R.string.icon_switcher_set_generic_reboot));
                 pD.setIndeterminate(false);
                 pD.setCancelable(false);
                 pD.setMax(100);
                 pD.setProgress(0);
                 pD.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                 return pD;
             }
         }
        return null;
     }
    private static ProgressDialog pD;
    private static final int PDDIALOG=0;
    private int checkT;
    private static int progressBar; //index of Progress bar
    private FSmanager fsm;
    private CheckBox checkbt1, checkbt2;
    private String theme;
}
